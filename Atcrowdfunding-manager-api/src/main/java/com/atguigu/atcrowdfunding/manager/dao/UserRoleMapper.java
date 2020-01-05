package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.UserRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface UserRoleMapper
{
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract int insert(UserRole paramUserRole);
  
  public abstract UserRole selectByPrimaryKey(Integer paramInteger);
  
  public abstract List<UserRole> selectAll();
  
  public abstract int updateByPrimaryKey(UserRole paramUserRole);
  
  public abstract List<Integer> selectByUserId(Integer paramInteger);
  
  public abstract int deleteRole(@Param("userid") Integer paramInteger1, @Param("roleid") Integer paramInteger2);
  
  public abstract int deleteUserRole(@Param("userid") Integer paramInteger);
}
