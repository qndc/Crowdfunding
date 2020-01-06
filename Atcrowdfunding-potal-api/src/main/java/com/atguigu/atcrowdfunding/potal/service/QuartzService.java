package com.atguigu.atcrowdfunding.potal.service;

public abstract interface QuartzService
{
  public abstract void addJob(String paramString1, String paramString2, String paramString3, String paramString4, Class paramClass, String paramString5,String proId);
  
  public abstract boolean modifyJobTime(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9);
  
  public abstract void modifyJobTime(String paramString1, String paramString2, String paramString3);
  
  public abstract void pauseJob(String paramString1, String paramString2);
  
  public abstract void resumeJob(String paramString1, String paramString2);
  
  public abstract void removeJob(String paramString1, String paramString2, String paramString3, String paramString4);
  
  public abstract void startSchedule();
  
  public abstract void shutdownSchedule();
}

