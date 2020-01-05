package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.Role;
import java.util.List;

public abstract interface RoleMapper
{
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract int insert(Role paramRole);
  
  public abstract Role selectByPrimaryKey(Integer paramInteger);
  
  public abstract List<Role> selectAll();
  
  public abstract int updateByPrimaryKey(Role paramRole);
}
