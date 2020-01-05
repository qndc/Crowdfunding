package com.atguigu.atcrowdfunding.potal.quartz;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProjectSettlement implements Job
{
  private Logger log = LoggerFactory.getLogger(getClass());
  
  public void execute(JobExecutionContext context) throws JobExecutionException
  {
    LocalDateTime now = LocalDateTime.now();
    this.log.info("现在时间为：" + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
  }
}





