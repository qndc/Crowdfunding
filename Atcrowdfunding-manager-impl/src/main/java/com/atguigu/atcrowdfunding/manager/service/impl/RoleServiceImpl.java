package com.atguigu.atcrowdfunding.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.RolePermission;
import com.atguigu.atcrowdfunding.manager.dao.RoleMapper;
import com.atguigu.atcrowdfunding.manager.dao.RolePermissionMapper;
import com.atguigu.atcrowdfunding.manager.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Override
	public List<Role> queryAllRole() {
		
		return roleMapper.selectAll();
	}

	@Override
	public void deletePermissionByRoleId(Integer roleId) {
		
		rolePermissionMapper.deletePermissionByRoleId(roleId);

	}

	@Override
	public void insertPermission(RolePermission rp) {
		
		rolePermissionMapper.insert(rp);
	}

}
