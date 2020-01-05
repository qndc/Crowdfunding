package com.atguigu.atcrowdfunding.potal.listener;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.TTicket;
import com.atguigu.atcrowdfunding.bean.TTicketExample;
import com.atguigu.atcrowdfunding.bean.TTicketExample.Criteria;
import com.atguigu.atcrowdfunding.potal.dao.TTicketMapper;
import com.atguigu.atcrowdfunding.potal.service.MemberService;
import com.atguigu.atcrowdfunding.util.ApplicationContextUtils;

/**
 * 	实名认证审核拒绝的操作
 * @author dc
 *
 */
public class PassListener implements ExecutionListener {
	
	/**
	 * @Autowired 不能直接进行自动装配，因为流程监听器是我们自己创建的
	 * private MemberService memberService;
	 */

	/**
	 * 审核通过
	 */
	@Override
	public void notify(DelegateExecution arg0) throws Exception {
		
		/**
		 * ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(); 
		 * 	不能自己创建application对象，因为spring已经帮我们创建了applicationContext对象，保证唯一性
		 * 	通过WebApplicationUtils工具类传入application参数来获取容器，但是application作用域怎么获取呢？通过完成任务时设置流程变量或者ThreadLocal
		 * 
		 */
		//使用自定义工具类，通过自定义工具类，实现spring接口，以属性注入的方式获取IOC容器对象
		ApplicationContext applicationContext = ApplicationContextUtils.applicationContext;
		//获取完成任务时设置的参数
		Integer memberid = (Integer)arg0.getVariable("memberid");
		
		//更新状态：t_member中的authstatus状态---->2认证成功
		MemberService memberService = applicationContext.getBean(MemberService.class);
		Member member = memberService.queryMemberByMid(memberid);
		member.setAuthstatus("2");
		memberService.updateMember(member);
		
		//更新状态：t_ticket中的status状态----->1审核完毕
		TTicketMapper ticketMapper = applicationContext.getBean(TTicketMapper.class);
		TTicketExample example = new TTicketExample();
		Criteria criteria = example.createCriteria();
		criteria.andMemberidEqualTo(memberid);
		List<TTicket> list = ticketMapper.selectByExample(example);
		for (TTicket tTicket : list) {
			tTicket.setStatus("1");
			ticketMapper.updateByPrimaryKey(tTicket);
		}
	}

}
