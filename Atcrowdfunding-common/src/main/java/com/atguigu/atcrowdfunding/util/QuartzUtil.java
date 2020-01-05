package com.atguigu.atcrowdfunding.util;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzUtil {
	private static Scheduler scheduler;

	static {
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public static void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
			Class jobClass, String cron) {
		try {
			JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();

			TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();

			triggerBuilder.withIdentity(triggerName, triggerGroupName);
			triggerBuilder.startNow();

			triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));

			CronTrigger trigger = (CronTrigger) triggerBuilder.build();

			scheduler.scheduleJob(jobDetail, trigger);

			scheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void modifyJobTime(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
			String cron) {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);

			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			if (trigger == null) {
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(cron)) {
				TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
				triggerBuilder.withIdentity(triggerName, triggerGroupName);
				triggerBuilder.startNow();
				triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
				trigger = (CronTrigger) triggerBuilder.build();
				scheduler.rescheduleJob(triggerKey, trigger);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
