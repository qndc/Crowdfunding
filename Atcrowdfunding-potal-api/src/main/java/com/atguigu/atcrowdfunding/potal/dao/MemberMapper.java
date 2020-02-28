package com.atguigu.atcrowdfunding.potal.dao;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.User;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

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
  
  //后台用户维护
  public abstract List<Member> queryList(@Param("start") Integer paramInteger1, @Param("pageSize") Integer paramInteger2, @Param("queryText") String paramString);
  
  public abstract Integer queryCounts(@Param("queryText") String paramString);
}
