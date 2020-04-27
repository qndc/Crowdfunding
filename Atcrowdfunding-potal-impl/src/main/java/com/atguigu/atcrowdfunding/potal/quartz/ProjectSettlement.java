package com.atguigu.atcrowdfunding.potal.quartz;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.jms.MapMessage;
import javax.jms.Session;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.atguigu.atcrowdfunding.bean.AliPayBean;
import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.Project;
import com.atguigu.atcrowdfunding.bean.TOrder;
import com.atguigu.atcrowdfunding.potal.service.HomePageService;
import com.atguigu.atcrowdfunding.potal.service.MemberService;
import com.atguigu.atcrowdfunding.util.AliPayUtil;
import com.atguigu.atcrowdfunding.util.AlipayConfig;
import com.atguigu.atcrowdfunding.util.ApplicationContextUtils;
import com.google.gson.Gson;

/**
 * 定时任务执行主体
 * @author dc
 *
 */
public class ProjectSettlement implements Job
{
  
  public void execute(JobExecutionContext context) throws JobExecutionException
  {
    //查询众筹完成情况
    ApplicationContext applicationContext = ApplicationContextUtils.applicationContext;
    HomePageService homePageService = applicationContext.getBean(HomePageService.class);
    MemberService memberService = applicationContext.getBean(MemberService.class);
    //获取quartz框架传递过来的项目id，查询项目信息及发起人信息
    JobDataMap dataMap = context.getJobDetail().getJobDataMap();   
    Project project = homePageService.getProsById(Integer.valueOf(dataMap.getString("proId")));
    Member member = memberService.queryMemberByMid(project.getMemberid());
    JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
    
    //查询项目订单
    List<TOrder> list = homePageService.getOrderByProId(project.getId());
	Set<Integer> memberIds = new HashSet<>();
	for (TOrder tOrder : list) {
		memberIds.add(tOrder.getMemberid());
	}
    AlipayConfig.logResult(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"))+":Time to start accounting...");
    AlipayConfig.logResult(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"))+":Project Info："+new Gson().toJson(project));
    AlipayConfig.logResult(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"))+":Originator Info："+new Gson().toJson(member));
    try {
		//如果众筹金额>=总金额的90%，可视为项目众筹成功,否则失败
		if (project.getCompletion() >= 90) {
			//成功后通知众筹成功-众筹发起人，众筹支持者,扣除手续费后打款50%
			AlipayConfig.logResult(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"))+":Completion >= 90% SUCCESS !");
			//发送短信验证码-项目发起者
			jmsTemplate.send((Session session) -> {
				MapMessage mapMessage = session.createMapMessage();
				mapMessage.setString("tel", member.getTel());
				mapMessage.setString("templateCode", "SMS_181853713");
				mapMessage.setString("name", member.getRealname());
				return mapMessage;
			});
			//发送短信验证码-项目支持者，查询该项目的所有支持者
			for (Integer memberId : memberIds) {
				Member mm = memberService.queryMemberByMid(memberId);
				jmsTemplate.send((Session session) -> {
					MapMessage mapMessage = session.createMapMessage();
					mapMessage.setString("tel", mm.getTel());
					mapMessage.setString("templateCode", "SMS_181853714");
					mapMessage.setString("name", mm.getRealname());
					return mapMessage;
				});
			}
			//更新数据库项目状态
			project.setStatus("2");
			homePageService.updatePro(project);
		} else {
			//失败后通知众筹失败-众筹发起人，众筹支持者，并退款
			AlipayConfig.logResult(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"))+":Completion < 90% ,FAILURE !");
			//发送短信验证码-项目发起者
			jmsTemplate.send((Session session) -> {
				MapMessage mapMessage = session.createMapMessage();
				mapMessage.setString("tel", member.getTel());
				mapMessage.setString("templateCode", "SMS_181863587");
				mapMessage.setString("name", member.getRealname());
				return mapMessage;
			});
			//发送短信验证码-项目支持者
			for (Integer memberId : memberIds) {
				Member mm = memberService.queryMemberByMid(memberId);
				jmsTemplate.send((Session session) -> {
					MapMessage mapMessage = session.createMapMessage();
					mapMessage.setString("tel", mm.getTel());
					mapMessage.setString("templateCode", "SMS_181853715");
					mapMessage.setString("name", mm.getRealname());
					return mapMessage;
				});
			}
			//发起退款
			if (!list.isEmpty()) {
				list.forEach(tOrder -> {
					AliPayBean bean = new AliPayBean();
					bean.setOut_trade_no(tOrder.getOrdernum());
					bean.setTrade_no(tOrder.getTradeno());
					bean.setRefund_amount(tOrder.getMoney().toString());
					bean.setRefund_reason(project.getName()+"众筹失败退款");
					bean.setOut_request_no(tOrder.getId().toString());
					AlipayTradeRefundResponse response;
					try {
						//此处存在同一个人多笔订单的情况，此时只需要发送一条短信
						response = AliPayUtil.Refund(bean);
						if (response.isSuccess()) {
							AlipayConfig.logResult(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"))+":"+"退款成功，商户单号："+response.getOutTradeNo()+"，支付宝交易号："+response.getTradeNo());
							Member mmm = memberService.queryMemberByMid(tOrder.getMemberid());
							jmsTemplate.send((Session session) -> {
								MapMessage mapMessage = session.createMapMessage();
								mapMessage.setString("tel", mmm.getTel());
								mapMessage.setString("templateCode", "SMS_182375151");
								mapMessage.setString("name", mmm.getRealname());
								return mapMessage;
							});
							//更新订单状态为3-交易关闭
							tOrder.setStatus("3");
							homePageService.updateOrder(tOrder);
							
						}else {
							AlipayConfig.logResult("退款失败，失败原因："+response.getSubMsg());
						}
					} catch (IOException e) {
						e.printStackTrace();
					} catch (AlipayApiException e) {
						e.printStackTrace();
					}
				});
			}
			//更新数据库
			project.setStatus("3");
			homePageService.updatePro(project);
		} 
	} catch (Exception e) {
		AlipayConfig.logResult(e.getMessage());
	}
	AlipayConfig.logResult("-------------------------------------------------------");
  }

}





