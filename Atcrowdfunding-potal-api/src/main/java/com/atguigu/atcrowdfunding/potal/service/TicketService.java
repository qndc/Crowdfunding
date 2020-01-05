package com.atguigu.atcrowdfunding.potal.service;

import com.atguigu.atcrowdfunding.bean.TTicket;

public abstract interface TicketService
{
  public abstract TTicket queryTicketByMid(Integer paramInteger);
  
  public abstract void insertTicket(TTicket paramTTicket);
  
  public abstract void updateTicket(TTicket paramTTicket);
}


/* Location:              C:\Users\dc\Desktop\jar包\Atcrowdfunding-potal-api-0.0.1-SNAPSHOT.jar!\com\atguigu\atcrowdfunding\potal\service\TicketService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */