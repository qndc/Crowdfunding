package com.atguigu.atcrowdfunding.manager.service;

import java.util.List;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.RolePermission;

public interface RoleService {

	List<Role> queryAllRole();

	void deletePermissionByRoleId(Integer roleId);

	void insertPermission(RolePermission rp);


}
