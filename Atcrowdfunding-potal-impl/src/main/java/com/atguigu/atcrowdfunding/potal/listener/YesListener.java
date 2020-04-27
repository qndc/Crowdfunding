package com.atguigu.atcrowdfunding.potal.listener;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.Project;
import com.atguigu.atcrowdfunding.bean.TProjectTicket;
import com.atguigu.atcrowdfunding.bean.TProjectTicketExample;
import com.atguigu.atcrowdfunding.potal.dao.MemberMapper;
import com.atguigu.atcrowdfunding.potal.dao.TProjectTicketMapper;
import com.atguigu.atcrowdfunding.potal.quartz.ProjectSettlement;
import com.atguigu.atcrowdfunding.potal.service.ProjectTicketService;
import com.atguigu.atcrowdfunding.potal.service.QuartzService;
import com.atguigu.atcrowdfunding.util.ApplicationContextUtils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.jms.MapMessage;
import javax.jms.Session;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;

public class YesListener implements ExecutionListener {

	public void notify(DelegateExecution arg0) throws Exception {
		ApplicationContext applicationContext = ApplicationContextUtils.applicationContext;

		Integer proId = (Integer) arg0.getVariable("proId");

		//更新项目审批单：项目状态->1 ,发布时间
		ProjectTicketService ticketService = (ProjectTicketService) applicationContext
				.getBean(ProjectTicketService.class);
		Project project = ticketService.getProjectByProId(proId);
		project.setStatus("1");
		LocalDateTime rightNow = LocalDateTime.now();
		project.setDeploydate(rightNow.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		ticketService.updateProject(project);

		//开启定时任务，定时时间审批时间：项目开始时间+众筹天数
		QuartzService quartzService = (QuartzService) applicationContext.getBean(QuartzService.class);
		Integer day = project.getDay();
		String deploydate = project.getDeploydate();
		String cron = getTimeStr(day, deploydate);

		String jobName = project.getId() + "_jname";
		String triggerName = project.getId() + "_tname";
		String jobGroupName = project.getMemberid() + "_jgname";
		String triggerGroupName = project.getMemberid() + "_tgname";
		quartzService.addJob(jobName, jobGroupName, triggerName, triggerGroupName, ProjectSettlement.class, cron,project.getId().toString());

		TProjectTicketMapper mapper = (TProjectTicketMapper) applicationContext.getBean(TProjectTicketMapper.class);
		MemberMapper memberMapper = (MemberMapper) applicationContext.getBean(MemberMapper.class);
		TProjectTicketExample example = new TProjectTicketExample();
		TProjectTicketExample.Criteria criteria = example.createCriteria();
		criteria.andProidEqualTo(proId);
		List<TProjectTicket> list = mapper.selectByExample(example);
		list.forEach(tProjectTicket -> {
			tProjectTicket.setStatus("1");
			mapper.updateByPrimaryKey(tProjectTicket);
			Member member = memberMapper.selectByPrimaryKey(tProjectTicket.getMemberid());
			JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
			jmsTemplate.send((Session session) -> {
				MapMessage mapMessage = session.createMapMessage();
				mapMessage.setString("tel", member.getTel());
				mapMessage.setString("templateCode", "SMS_181195868");
				mapMessage.setString("name", member.getRealname());
				return mapMessage;
			});
		});
	}

	//组合项目审批时间
	private static String getTimeStr(Integer temp, String deploydate) {
		LocalDateTime oldDate = LocalDateTime.parse(deploydate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		LocalDateTime newDate = oldDate.plusDays(temp.longValue());
		int year = newDate.getYear();
		int month = newDate.getMonthValue();
		int day = newDate.getDayOfMonth();
		int hour = newDate.getHour();
		int minute = newDate.getMinute();
		int second = newDate.getSecond();
		String str = second + " " + minute + " " + hour + " " + day + " " + month + " ? " + year;
		return str;
	}
}
