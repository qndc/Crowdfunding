package com.atguigu.atcrowdfunding.potal.dao;

import com.atguigu.atcrowdfunding.bean.Member;
import java.util.List;
import java.util.Map;

public abstract interface MemberMapper
{
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract int insert(Member paramMember);
  
  public abstract Member selectByPrimaryKey(Integer paramInteger);
  
  public abstract List<Member> selectAll();
  
  public abstract int updateByPrimaryKey(Member paramMember);
  
  public abstract Member queryMebmerlogin(Map<String, Object> paramMap);
  
  public abstract void updateAcctType(Member paramMember);
  
  public abstract void updateBasicinfo(Member paramMember);
  
  public abstract void updateEmail(Member paramMember);
  
  public abstract void updateAuthstatus(Member paramMember);
  
  public abstract Member selectByTel(String paramString);
  
  public abstract Member selectByLoginacct(String paramString);
}


/* Location:              C:\Users\dc\Desktop\jar包\Atcrowdfunding-potal-api-0.0.1-SNAPSHOT.jar!\com\atguigu\atcrowdfunding\potal\dao\MemberMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */