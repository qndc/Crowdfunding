package com.atguigu.atcrowdfunding.potal.service;

import com.atguigu.atcrowdfunding.bean.Project;
import com.atguigu.atcrowdfunding.bean.TProjectTicket;
import com.atguigu.atcrowdfunding.bean.TReturn;
import java.util.List;

public abstract interface ProjectTicketService
{
  public abstract TProjectTicket getTicketByMemId(Integer paramInteger);
  
  public abstract void createTicket(TProjectTicket paramTProjectTicket);
  
  public abstract void updateProjectTicket(TProjectTicket paramTProjectTicket);
  
  public abstract void insertProject(Project paramProject);
  
  public abstract Project getProjectByMemberId(Integer paramInteger);
  
  public abstract void insertReturn(TReturn paramTReturn);
  
  public abstract List<TReturn> getReturns(Integer paramInteger);
  
  public abstract TProjectTicket getTicketByPId(String paramString);
  
  public abstract Project getProjectByProId(Integer paramInteger);
  
  public abstract void updateProject(Project paramProject);
}


/* Location:              C:\Users\dc\Desktop\jar包\Atcrowdfunding-potal-api-0.0.1-SNAPSHOT.jar!\com\atguigu\atcrowdfunding\potal\service\ProjectTicketService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */