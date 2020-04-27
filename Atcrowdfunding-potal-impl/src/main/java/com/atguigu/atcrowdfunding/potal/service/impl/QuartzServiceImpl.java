package com.atguigu.atcrowdfunding.potal.service.impl;

import com.atguigu.atcrowdfunding.potal.service.QuartzService;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class QuartzServiceImpl implements QuartzService {
	@Autowired
	private Scheduler quartzScheduler;

	public void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class cls,
			String cron,String proId) {
		MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
		bean.setConcurrent(false);
		try {
			Scheduler sched = this.quartzScheduler;
			sched.start();

			JobDetail job = JobBuilder.newJob(cls).withIdentity(jobName, jobGroupName).build();
			job.getJobDataMap().put("proId", proId);

			CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName)
					.withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();

			sched.scheduleJob(job, trigger);

			sched.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean modifyJobTime(String oldjobName, String oldjobGroup, String oldtriggerName, String oldtriggerGroup,
			String jobName, String jobGroup, String triggerName, String triggerGroup, String cron) {
		try {
			Scheduler sched = this.quartzScheduler;
			CronTrigger trigger = (CronTrigger) sched
					.getTrigger(TriggerKey.triggerKey(oldtriggerName, oldtriggerGroup));
			if (trigger == null) {
				return false;
			}

			JobKey jobKey = JobKey.jobKey(oldjobName, oldjobGroup);
			TriggerKey triggerKey = TriggerKey.triggerKey(oldtriggerName, oldtriggerGroup);

			JobDetail job = sched.getJobDetail(jobKey);
			Class jobClass = job.getJobClass();

			sched.pauseTrigger(triggerKey);

			sched.unscheduleJob(triggerKey);

			sched.deleteJob(jobKey);

			//addJob(jobName, jobGroup, triggerName, triggerGroup, jobClass, cron);

			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void modifyJobTime(String triggerName, String triggerGroupName, String time) {
		try {
			Scheduler sched = this.quartzScheduler;
			CronTrigger trigger = (CronTrigger) sched.getTrigger(TriggerKey.triggerKey(triggerName, triggerGroupName));
			if (trigger == null) {
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(time)) {
				CronTrigger ct = trigger;

				ct.getTriggerBuilder().withSchedule(CronScheduleBuilder.cronSchedule(time)).build();

				sched.resumeTrigger(TriggerKey.triggerKey(triggerName, triggerGroupName));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void pauseJob(String jobName, String jobGroupName) {
		try {
			this.quartzScheduler.pauseJob(JobKey.jobKey(jobName, jobGroupName));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public void resumeJob(String jobName, String jobGroupName) {
		try {
			this.quartzScheduler.resumeJob(JobKey.jobKey(jobName, jobGroupName));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
		try {
			Scheduler sched = this.quartzScheduler;

			sched.pauseTrigger(TriggerKey.triggerKey(triggerName, triggerGroupName));

			sched.unscheduleJob(TriggerKey.triggerKey(triggerName, triggerGroupName));

			sched.deleteJob(JobKey.jobKey(jobName, jobGroupName));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void shutdownSchedule() {
		try {
			Scheduler sched = this.quartzScheduler;
			if (!sched.isShutdown()) {
				sched.shutdown();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void startSchedule(String jobName, String jobGroupName) {
		try {
			Scheduler sched = this.quartzScheduler;
			JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
			//先触发
			sched.triggerJob(jobKey);
			//然后删除定时任务
			//sched.deleteJob(jobKey);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	
}
