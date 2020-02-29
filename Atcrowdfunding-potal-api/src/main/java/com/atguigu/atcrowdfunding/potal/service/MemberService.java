package com.atguigu.atcrowdfunding.potal.service;

import com.atguigu.atcrowdfunding.bean.Member;
import java.util.Map;

public abstract interface MemberService
{
  public abstract Member queryMemberlogin(Map<String, Object> paramMap);
  
  public abstract void updateMember(Member paramMember);
  
  public abstract Member queryMemberByMid(Integer paramInteger);
  
  public abstract void insertMember(Member paramMember);
  
  public abstract Member selectMemberByTel(String paramString);
  
  public abstract Member selectMemberByLoginacct(String paramString);
}