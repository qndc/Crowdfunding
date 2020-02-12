package com.atguigu.atcrowdfunding.potal.quartz;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

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
import com.atguigu.atcrowdfunding.util.SmsUtil;
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
    
    Map<String, String> memberMap = new HashMap<>();
    memberMap.put("name", member.getRealname());
    
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
			SmsUtil.sendSms("SMS_181853713", new Gson().toJson(memberMap), member.getTel(), "我的学习分享");
			//发送短信验证码-项目支持者，查询该项目的所有支持者
			for (Integer memberId : memberIds) {
				Member mm = memberService.queryMemberByMid(memberId);
				Map<String, String> supporterMap = new HashMap<>();
				supporterMap.put("name", mm.getRealname());
				SmsUtil.sendSms("SMS_181853714", new Gson().toJson(supporterMap), mm.getTel(), "我的学习分享");
			}
			//更新数据库项目状态
			project.setStatus("2");
			homePageService.updatePro(project);
		} else {
			//失败后通知众筹失败-众筹发起人，众筹支持者，并退款
			AlipayConfig.logResult(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"))+":Completion < 90% ,FAILURE !");
			//发送短信验证码-项目发起者
			SmsUtil.sendSms("SMS_181863587", new Gson().toJson(memberMap), member.getTel(), "我的学习分享");
			//发送短信验证码-项目支持者
			for (Integer memberId : memberIds) {
				Member mm = memberService.queryMemberByMid(memberId);
				Map<String, String> supporterMap = new HashMap<>();
				supporterMap.put("name", mm.getRealname());
				SmsUtil.sendSms("SMS_181853715", new Gson().toJson(supporterMap), mm.getTel(), "我的学习分享");
			}
			//发起退款
			for (TOrder tOrder : list) {
				AliPayBean bean = new AliPayBean();
				bean.setOut_trade_no(tOrder.getOrdernum());
				bean.setTrade_no(tOrder.getTradeno());
				bean.setRefund_amount(tOrder.getMoney().toString());
				bean.setRefund_reason(project.getName()+"众筹失败退款");
				bean.setOut_request_no(tOrder.getId().toString());
				AlipayTradeRefundResponse response = AliPayUtil.Refund(bean);
				if (response.isSuccess()) {
					AlipayConfig.logResult(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"))+":"+"退款成功，商户单号："+response.getOutTradeNo()+"，支付宝交易号："+response.getTradeNo());
					Member mmm = memberService.queryMemberByMid(tOrder.getMemberid());
					Map<String, String> sm = new HashMap<>();
					sm.put("name", mmm.getRealname());
					SmsUtil.sendSms("SMS_182375151", new Gson().toJson(sm), mmm.getTel(), "我的学习分享");
					//更新订单状态为3-交易关闭
					tOrder.setStatus("3");
					homePageService.updateOrder(tOrder);
					
				}else {
					AlipayConfig.logResult("退款失败，失败原因："+response.getSubMsg());
				}
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





