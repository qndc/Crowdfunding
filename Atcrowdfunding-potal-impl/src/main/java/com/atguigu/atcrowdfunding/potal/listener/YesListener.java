package com.atguigu.atcrowdfunding.potal.listener;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.Project;
import com.atguigu.atcrowdfunding.bean.TProjectTicket;
import com.atguigu.atcrowdfunding.bean.TProjectTicketExample;
import com.atguigu.atcrowdfunding.bean.TProjectTicketExample.Criteria;
import com.atguigu.atcrowdfunding.potal.dao.MemberMapper;
import com.atguigu.atcrowdfunding.potal.dao.TProjectTicketMapper;
import com.atguigu.atcrowdfunding.potal.quartz.ProjectSettlement;
import com.atguigu.atcrowdfunding.potal.service.ProjectTicketService;
import com.atguigu.atcrowdfunding.potal.service.QuartzService;
import com.atguigu.atcrowdfunding.util.ApplicationContextUtils;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.context.ApplicationContext;

public class YesListener implements ExecutionListener {

	public void notify(DelegateExecution arg0) throws Exception {
		ApplicationContext applicationContext = ApplicationContextUtils.applicationContext;

		Integer proId = (Integer) arg0.getVariable("proId");
		System.err.println(proId);

		ProjectTicketService ticketService = (ProjectTicketService) applicationContext
				.getBean(ProjectTicketService.class);
		Project project = ticketService.getProjectByProId(proId);
		project.setStatus("1");
		LocalDateTime rightNow = LocalDateTime.now();
		project.setDeploydate(rightNow.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		ticketService.updateProject(project);

		QuartzService quartzService = (QuartzService) applicationContext.getBean(QuartzService.class);
		Integer day = project.getDay();
		String deploydate = project.getDeploydate();
		String cron = getTimeStr(day, deploydate);

		String jobName = project.getId() + "_jname";
		String triggerName = project.getId() + "_tname";
		String jobGroupName = project.getMemberid() + "_jgname";
		String triggerGroupName = project.getMemberid() + "_tgname";
		quartzService.addJob(jobName, jobGroupName, triggerName, triggerGroupName, ProjectSettlement.class, cron);

		TProjectTicketMapper mapper = (TProjectTicketMapper) applicationContext.getBean(TProjectTicketMapper.class);
		MemberMapper memberMapper = (MemberMapper) applicationContext.getBean(MemberMapper.class);
		TProjectTicketExample example = new TProjectTicketExample();
		TProjectTicketExample.Criteria criteria = example.createCriteria();
		criteria.andProidEqualTo(proId);
		List<TProjectTicket> list = mapper.selectByExample(example);
		for (TProjectTicket tProjectTicket : list) {
			tProjectTicket.setStatus("1");
			mapper.updateByPrimaryKey(tProjectTicket);

			Member member = memberMapper.selectByPrimaryKey(tProjectTicket.getMemberid());

			Map map = new HashMap();
			map.put("name", member.getRealname());
		}
	}

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
