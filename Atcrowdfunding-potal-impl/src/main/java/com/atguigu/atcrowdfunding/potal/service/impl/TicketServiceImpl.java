/*    */ package com.atguigu.atcrowdfunding.potal.service.impl;
/*    */ 
/*    */ import com.atguigu.atcrowdfunding.bean.TTicket;
/*    */ import com.atguigu.atcrowdfunding.bean.TTicketExample;
/*    */ import com.atguigu.atcrowdfunding.bean.TTicketExample.Criteria;
/*    */ import com.atguigu.atcrowdfunding.potal.dao.TTicketMapper;
/*    */ import com.atguigu.atcrowdfunding.potal.service.TicketService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class TicketServiceImpl
/*    */   implements TicketService
/*    */ {
/*    */   @Autowired
/*    */   private TTicketMapper tTicketMapper;
/*    */   
/*    */   public TTicket queryTicketByMid(Integer id)
/*    */   {
/* 23 */     TTicketExample example = new TTicketExample();
/* 24 */     TTicketExample.Criteria criteria = example.createCriteria();
/* 25 */     criteria.andMemberidEqualTo(id);
/* 26 */     criteria.andStatusEqualTo("0");
/* 27 */     List<TTicket> list = this.tTicketMapper.selectByExample(example);
/* 28 */     if (!list.isEmpty()) {
/* 29 */       return (TTicket)list.get(0);
/*    */     }
/* 31 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void insertTicket(TTicket tTicket)
/*    */   {
/* 38 */     this.tTicketMapper.insert(tTicket);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void updateTicket(TTicket tTicket)
/*    */   {
/* 45 */     this.tTicketMapper.updateByPrimaryKey(tTicket);
/*    */   }
/*    */ }


/* Location:              C:\Users\dc\Desktop\jar包\Atcrowdfunding-potal-impl-0.0.1-SNAPSHOT.jar!\com\atguigu\atcrowdfunding\potal\service\impl\TicketServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */