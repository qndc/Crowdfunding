 package com.atguigu.atcrowdfunding.potal.service.impl;
 
 import com.atguigu.atcrowdfunding.bean.Member;
 import com.atguigu.atcrowdfunding.potal.dao.MemberMapper;
 import com.atguigu.atcrowdfunding.potal.service.MemberService;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
@Service
public class MemberServiceImpl implements MemberService
{
  @Autowired
  private MemberMapper memberMapper;
  
  public Member queryMemberlogin(Map<String, Object> paramMap)
  {
    return this.memberMapper.queryMebmerlogin(paramMap);
  }
  
	public void updateMember(Member member)   {
     this.memberMapper.updateByPrimaryKey(member);
   }
   
 
   public Member queryMemberByMid(Integer memberid)
   {
     Member member = this.memberMapper.selectByPrimaryKey(memberid);
     return member;
   }
   
   public void insertMember(Member member)
   {
     this.memberMapper.insert(member);
   }
   
 
   public Member selectMemberByTel(String tel)
   {
     return this.memberMapper.selectByTel(tel);
   }
   
 
   public Member selectMemberByLoginacct(String loginacct)
   {
     return this.memberMapper.selectByLoginacct(loginacct);
   }
 }