package com.atguigu.atcrowdfunding.quertztask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.activiti.engine.runtime.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Quertz石英调度任务类,实现以下功能 1.定时备份数据库 2.定时发送邮件
 * 
 * @author dc
 *
 */
public class FinishWorkTask implements org.quartz.Job {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		LocalDateTime now = LocalDateTime.now();
		this.log.info("现在时间为：" + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

	}

}
