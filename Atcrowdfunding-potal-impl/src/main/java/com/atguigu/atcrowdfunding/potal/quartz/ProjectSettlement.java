package com.atguigu.atcrowdfunding.potal.quartz;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.Project;
import com.atguigu.atcrowdfunding.potal.service.HomePageService;
import com.atguigu.atcrowdfunding.potal.service.MemberService;
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
  private Logger log = LoggerFactory.getLogger(getClass());
  
  public void execute(JobExecutionContext context) throws JobExecutionException
  {
    LocalDateTime now = LocalDateTime.now();
    //查询众筹完成情况
    ApplicationContext applicationContext = ApplicationContextUtils.applicationContext;
    HomePageService homePageService = applicationContext.getBean(HomePageService.class);
    MemberService memberService = applicationContext.getBean(MemberService.class);
    //获取quartz框架传递过来的项目id
    JobDataMap dataMap = context.getJobDetail().getJobDataMap();   
    Project project = homePageService.getProsById(Integer.valueOf(dataMap.getString("proId")));
    Member member = memberService.queryMemberByMid(project.getMemberid());
    AlipayConfig.logResult("众筹时间到，审核开始..."+now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    AlipayConfig.logResult("项目信息："+new Gson().toJson(project));
    AlipayConfig.logResult("发起人信息："+new Gson().toJson(member));
    //如果众筹金额>=总金额的90%，可视为项目众筹成功,否则失败
    if (project.getCompletion() >= 90) {
		//成功后通知众筹成功-众筹发起人，众筹支持者
    	AlipayConfig.logResult("Completion >= 90% SUCCESS !");
	}else {
		//失败后通知众筹失败-众筹发起人，众筹支持者，并退款
		AlipayConfig.logResult("Completion < 90% ,FAILURE !");
	}
    AlipayConfig.logResult("-------------------------------------------------------");
  }
}





