/*    */ package com.atguigu.atcrowdfunding.potal.service.impl;
/*    */ 
/*    */ import com.atguigu.atcrowdfunding.bean.Member;
/*    */ import com.atguigu.atcrowdfunding.potal.dao.MemberMapper;
/*    */ import com.atguigu.atcrowdfunding.potal.service.MemberService;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class MemberServiceImpl
/*    */   implements MemberService
/*    */ {
/*    */   @Autowired
/*    */   private MemberMapper memberMapper;
/*    */   
/*    */   public Member queryMemberlogin(Map<String, Object> paramMap)
/*    */   {
/* 20 */     return this.memberMapper.queryMebmerlogin(paramMap);
/*    */   }
/*    */   
/*    */ 
/*    */   public void updateMember(Member member)
/*    */   {
/* 26 */     this.memberMapper.updateByPrimaryKey(member);
/*    */   }
/*    */   
/*    */ 
/*    */   public Member queryMemberByMid(Integer memberid)
/*    */   {
/* 32 */     Member member = this.memberMapper.selectByPrimaryKey(memberid);
/* 33 */     return member;
/*    */   }
/*    */   
/*    */   public void insertMember(Member member)
/*    */   {
/* 38 */     this.memberMapper.insert(member);
/*    */   }
/*    */   
/*    */ 
/*    */   public Member selectMemberByTel(String tel)
/*    */   {
/* 44 */     return this.memberMapper.selectByTel(tel);
/*    */   }
/*    */   
/*    */ 
/*    */   public Member selectMemberByLoginacct(String loginacct)
/*    */   {
/* 50 */     return this.memberMapper.selectByLoginacct(loginacct);
/*    */   }
/*    */ }


/* Location:              C:\Users\dc\Desktop\jar包\Atcrowdfunding-potal-impl-0.0.1-SNAPSHOT.jar!\com\atguigu\atcrowdfunding\potal\service\impl\MemberServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */