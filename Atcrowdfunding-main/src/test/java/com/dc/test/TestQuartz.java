package com.dc.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

import com.atguigu.atcrowdfunding.potal.quartz.ProjectSettlement;
import com.atguigu.atcrowdfunding.potal.service.QuartzService;
import com.atguigu.atcrowdfunding.quertztask.FinishWorkTask;
import com.atguigu.atcrowdfunding.util.QuartzUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/spring/spring-*.xml")
public class TestQuartz {

	@Autowired
	private QuartzService quartzService;
	
	@Test
	public void demo() {
		//quartzService.startSchedule();
		//quartzService.addJob("2_jname", "3_jgname", "4_tname", "5_tgname", ProjectSettlement.class, "0/3 * * * * ?");
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
		System.out.println(str);
	}
}
