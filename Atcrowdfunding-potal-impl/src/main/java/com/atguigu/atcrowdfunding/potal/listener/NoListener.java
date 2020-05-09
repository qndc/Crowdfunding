package com.atguigu.atcrowdfunding.potal.listener;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.Project;
import com.atguigu.atcrowdfunding.bean.TProjectTicket;
import com.atguigu.atcrowdfunding.bean.TProjectTicketExample;
import com.atguigu.atcrowdfunding.potal.dao.MemberMapper;
import com.atguigu.atcrowdfunding.potal.dao.TProjectTicketMapper;
import com.atguigu.atcrowdfunding.potal.service.ProjectTicketService;
import com.atguigu.atcrowdfunding.util.ApplicationContextUtils;
import java.util.List;

import javax.jms.MapMessage;
import javax.jms.Session;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;

public class NoListener implements ExecutionListener {
	public void notify(DelegateExecution arg0) throws Exception {
		ApplicationContext applicationContext = ApplicationContextUtils.applicationContext;

		Integer proId = (Integer) arg0.getVariable("proId");
		String result = arg0.getVariable("result").toString();

		ProjectTicketService ticketService = (ProjectTicketService) applicationContext
				.getBean(ProjectTicketService.class);
		Project project = ticketService.getProjectByProId(proId);
		project.setStatus("5");//审核失败
		ticketService.updateProject(project);

		TProjectTicketMapper mapper = (TProjectTicketMapper) applicationContext.getBean(TProjectTicketMapper.class);
		MemberMapper memberMapper = (MemberMapper) applicationContext.getBean(MemberMapper.class);
		TProjectTicketExample example = new TProjectTicketExample();
		TProjectTicketExample.Criteria criteria = example.createCriteria();
		criteria.andProidEqualTo(proId);
		List<TProjectTicket> list = mapper.selectByExample(example);
		list.forEach(tProjectTicket -> {
			tProjectTicket.setStatus("0");
			tProjectTicket.setPstep("init");
			mapper.updateByPrimaryKey(tProjectTicket);
			Member member = memberMapper.selectByPrimaryKey(tProjectTicket.getMemberid());
			
			JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
			jmsTemplate.send((Session session) -> {
				MapMessage mapMessage = session.createMapMessage();
				mapMessage.setString("tel", member.getTel());
				mapMessage.setString("templateCode", "SMS_181200841");
				mapMessage.setString("name", member.getRealname());
				mapMessage.setString("result", result);
				return mapMessage;
			});
		});
	}
 }
