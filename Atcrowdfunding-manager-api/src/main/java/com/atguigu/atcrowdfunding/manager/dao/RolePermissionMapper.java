package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.RolePermission;
import java.util.List;

public abstract interface RolePermissionMapper
{
  public abstract int deleteByPrimaryKey(Integer paramInteger);
  
  public abstract int insert(RolePermission paramRolePermission);
  
  public abstract RolePermission selectByPrimaryKey(Integer paramInteger);
  
  public abstract List<RolePermission> selectAll();
  
  public abstract int updateByPrimaryKey(RolePermission paramRolePermission);
  
  public abstract List<Integer> selectPermissionByRoleId(Integer paramInteger);
  
  public abstract int deletePermissionByRoleId(Integer paramInteger);
}
