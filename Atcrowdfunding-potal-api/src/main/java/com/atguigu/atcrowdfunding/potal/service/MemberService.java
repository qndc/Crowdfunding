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


/* Location:              C:\Users\dc\Desktop\jar包\Atcrowdfunding-potal-api-0.0.1-SNAPSHOT.jar!\com\atguigu\atcrowdfunding\potal\service\MemberService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */