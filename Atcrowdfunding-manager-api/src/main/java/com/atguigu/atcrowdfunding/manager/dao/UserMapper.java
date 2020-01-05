package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.User;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public abstract interface UserMapper
{
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract int insert(User paramUser);
  
  public abstract User selectByPrimaryKey(Integer paramInteger);
  
  public abstract List<User> selectAll();
  
  public abstract int updateByPrimaryKey(User paramUser);
  
  public abstract User queryUserlogin(Map<String, Object> paramMap);
  
  public abstract List<User> queryList(@Param("start") Integer paramInteger1, @Param("pageSize") Integer paramInteger2);
  
  public abstract Integer queryCounts();
  
  public abstract List<User> queryList(@Param("start") Integer paramInteger1, @Param("pageSize") Integer paramInteger2, @Param("queryText") String paramString);
  
  public abstract Integer queryCounts(@Param("queryText") String paramString);
}
