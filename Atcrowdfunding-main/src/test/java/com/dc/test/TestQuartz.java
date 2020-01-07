package com.dc.test;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.atcrowdfunding.bean.AliPayBean;
import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.Project;
import com.atguigu.atcrowdfunding.bean.TOrder;
import com.atguigu.atcrowdfunding.potal.quartz.ProjectSettlement;
import com.atguigu.atcrowdfunding.potal.service.HomePageService;
import com.atguigu.atcrowdfunding.potal.service.MemberService;
import com.atguigu.atcrowdfunding.potal.service.QuartzService;
import com.atguigu.atcrowdfunding.quertztask.FinishWorkTask;
import com.atguigu.atcrowdfunding.util.AlipayConfig;
import com.atguigu.atcrowdfunding.util.QuartzUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/spring/spring-*.xml")
public class TestQuartz {

	@Autowired
	private QuartzService quartzService;
	@Autowired
	private HomePageService homePageService;
	@Autowired
	private MemberService memberService;
	
	
	@Test
	public void demo() {
		Project project = homePageService.getProsById(11);
		String jobName = project.getId() + "_jname";
		String triggerName = project.getId() + "_tname";
		String jobGroupName = project.getMemberid() + "_jgname";
		String triggerGroupName = project.getMemberid() + "_tgname";
		//quartzService.addJob(jobName, jobGroupName, triggerName, triggerGroupName, ProjectSettlement.class, "10 32 21 6 1 ?",project.getId().toString());
	}
	
	@Test
	public void time() {
		String date = "2019-12-26 11:29:40";
		Integer temp = 30;
		LocalDateTime oldDate = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		LocalDateTime newDate = oldDate.plusDays(temp.longValue());
		
		int year = newDate.getYear();
		int month = newDate.getMonthValue();
		int day = newDate.getDayOfMonth();
		int hour = newDate.getHour();
		int minute = newDate.getMinute();
		int second = newDate.getSecond();
		
		String str = second+" "+minute+" "+hour+" "+day+" "+month+" ? "+year;
	}
	
	@Test
	public void getAllSupporter() {
		List<TOrder> list = homePageService.getOrderByProId(11);
		Set<Integer> memberIds = new HashSet<>();
		for (TOrder tOrder : list) {
			memberIds.add(tOrder.getMemberid());
		}
		for (Integer mid : memberIds) {
			Member member = memberService.queryMemberByMid(mid);
			System.err.println(member.getTel());
		}
	}
	
	@Test
	public void TestRefund() {
		List<AliPayBean> beans = new ArrayList<AliPayBean>();
		
	}
}
