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
import org.springframework.data.redis.core.RedisTemplate;
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
	@Autowired
	private RedisTemplate redisTemplate;
	
	
	@Test
	public void demo() {
		Project project = homePageService.getProsById(13);
		String jobName = project.getId() + "_jname";
		String triggerName = project.getId() + "_tname";
		String jobGroupName = project.getMemberid() + "_jgname";
		String triggerGroupName = project.getMemberid() + "_tgname";
		//quartzService.addJob(jobName, jobGroupName, triggerName, triggerGroupName, ProjectSettlement.class, "0 0 10 9 1 ?",project.getId().toString());
	}
	
	@Test
	public void redisDemo() {
		
//		redisTemplate.opsForValue().set("k2","k22");
//		String str = (String) redisTemplate.opsForValue().get("k2") ;
//		System.err.println(str);
		
	}
	
	@Test
	public void redisList() {
		
//		redisTemplate.boundListOps("projects").rightPush("1");
//		redisTemplate.boundListOps("projects").rightPush("2");
//		redisTemplate.boundListOps("projects").rightPush("3");
//		List<String> range = redisTemplate.boundListOps("projects").range(0, 10);
//		for (String string : range) {
//			System.err.println(string);
//		}
		
	}
}
