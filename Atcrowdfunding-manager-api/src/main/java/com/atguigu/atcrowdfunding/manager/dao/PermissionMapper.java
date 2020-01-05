package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.Permission;
import java.util.List;

public abstract interface PermissionMapper
{
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract int insert(Permission paramPermission);
  
  public abstract Permission selectByPrimaryKey(Integer paramInteger);
  
  public abstract List<Permission> selectAll();
  
  public abstract int updateByPrimaryKey(Permission paramPermission);
  
  public abstract List<Permission> selectByPid(int paramInt);
}
