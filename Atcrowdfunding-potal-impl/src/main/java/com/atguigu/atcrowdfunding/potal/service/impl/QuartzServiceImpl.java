/*     */ package com.atguigu.atcrowdfunding.potal.service.impl;
/*     */ 
/*     */ import com.atguigu.atcrowdfunding.potal.service.QuartzService;
/*     */ import org.quartz.CronScheduleBuilder;
/*     */ import org.quartz.CronTrigger;
/*     */ import org.quartz.JobBuilder;
/*     */ import org.quartz.JobDetail;
/*     */ import org.quartz.JobKey;
/*     */ import org.quartz.Scheduler;
/*     */ import org.quartz.SchedulerException;
/*     */ import org.quartz.TriggerBuilder;
/*     */ import org.quartz.TriggerKey;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ 
/*     */ @Service
/*     */ public class QuartzServiceImpl
/*     */   implements QuartzService
/*     */ {
/*     */   @Autowired
/*     */   private Scheduler quartzScheduler;
/*     */   
/*     */   public void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class cls, String cron)
/*     */   {
/*  27 */     MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
/*  28 */     bean.setConcurrent(false);
/*     */     try
/*     */     {
/*  31 */       Scheduler sched = this.quartzScheduler;
/*  32 */       sched.start();
/*     */       
/*     */ 
/*  35 */       JobDetail job = JobBuilder.newJob(cls).withIdentity(jobName, jobGroupName).build();
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*  40 */       CronTrigger trigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName).withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
/*     */       
/*  42 */       sched.scheduleJob(job, trigger);
/*     */       
/*  44 */       sched.start();
/*     */     } catch (Exception e) {
/*  46 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean modifyJobTime(String oldjobName, String oldjobGroup, String oldtriggerName, String oldtriggerGroup, String jobName, String jobGroup, String triggerName, String triggerGroup, String cron)
/*     */   {
/*     */     try
/*     */     {
/*  55 */       Scheduler sched = this.quartzScheduler;
/*  56 */       CronTrigger trigger = (CronTrigger)sched.getTrigger(
/*  57 */         TriggerKey.triggerKey(oldtriggerName, oldtriggerGroup));
/*  58 */       if (trigger == null) {
/*  59 */         return false;
/*     */       }
/*     */       
/*  62 */       JobKey jobKey = JobKey.jobKey(oldjobName, oldjobGroup);
/*  63 */       TriggerKey triggerKey = TriggerKey.triggerKey(oldtriggerName, oldtriggerGroup);
/*     */       
/*     */ 
/*  66 */       JobDetail job = sched.getJobDetail(jobKey);
/*  67 */       Class jobClass = job.getJobClass();
/*     */       
/*  69 */       sched.pauseTrigger(triggerKey);
/*     */       
/*  71 */       sched.unscheduleJob(triggerKey);
/*     */       
/*  73 */       sched.deleteJob(jobKey);
/*     */       
/*  75 */       addJob(jobName, jobGroup, triggerName, triggerGroup, jobClass, cron);
/*     */       
/*     */ 
/*  78 */       return true;
/*     */     } catch (Exception e) {
/*  80 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void modifyJobTime(String triggerName, String triggerGroupName, String time)
/*     */   {
/*     */     try {
/*  87 */       Scheduler sched = this.quartzScheduler;
/*  88 */       CronTrigger trigger = (CronTrigger)sched.getTrigger(
/*  89 */         TriggerKey.triggerKey(triggerName, triggerGroupName));
/*  90 */       if (trigger == null) {
/*  91 */         return;
/*     */       }
/*  93 */       String oldTime = trigger.getCronExpression();
/*  94 */       if (!oldTime.equalsIgnoreCase(time)) {
/*  95 */         CronTrigger ct = trigger;
/*     */         
/*  97 */         ct.getTriggerBuilder()
/*  98 */           .withSchedule(CronScheduleBuilder.cronSchedule(time))
/*  99 */           .build();
/*     */         
/* 101 */         sched.resumeTrigger(TriggerKey.triggerKey(triggerName, triggerGroupName));
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 105 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void pauseJob(String jobName, String jobGroupName)
/*     */   {
/*     */     try
/*     */     {
/* 113 */       this.quartzScheduler.pauseJob(JobKey.jobKey(jobName, jobGroupName));
/*     */     } catch (SchedulerException e) {
/* 115 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void resumeJob(String jobName, String jobGroupName)
/*     */   {
/*     */     try
/*     */     {
/* 123 */       this.quartzScheduler.resumeJob(JobKey.jobKey(jobName, jobGroupName));
/*     */     } catch (SchedulerException e) {
/* 125 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName)
/*     */   {
/*     */     try
/*     */     {
/* 133 */       Scheduler sched = this.quartzScheduler;
/*     */       
/* 135 */       sched.pauseTrigger(TriggerKey.triggerKey(triggerName, triggerGroupName));
/*     */       
/*     */ 
/* 138 */       sched.unscheduleJob(TriggerKey.triggerKey(triggerName, triggerGroupName));
/*     */       
/*     */ 
/* 141 */       sched.deleteJob(JobKey.jobKey(jobName, jobGroupName));
/*     */     } catch (Exception e) {
/* 143 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void startSchedule()
/*     */   {
/*     */     try
/*     */     {
/* 151 */       Scheduler sched = this.quartzScheduler;
/* 152 */       sched.start();
/*     */     } catch (Exception e) {
/* 154 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public void shutdownSchedule()
/*     */   {
/*     */     try
/*     */     {
/* 162 */       Scheduler sched = this.quartzScheduler;
/* 163 */       if (!sched.isShutdown()) {
/* 164 */         sched.shutdown();
/*     */       }
/*     */     } catch (Exception e) {
/* 167 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\dc\Desktop\jar包\Atcrowdfunding-potal-impl-0.0.1-SNAPSHOT.jar!\com\atguigu\atcrowdfunding\potal\service\impl\QuartzServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */