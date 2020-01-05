package com.atguigu.atcrowdfunding.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.manager.dao.PermissionMapper;
import com.atguigu.atcrowdfunding.manager.dao.RolePermissionMapper;
import com.atguigu.atcrowdfunding.manager.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	private PermissionMapper permissionMapper;
	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Override
	public List<Permission> queryPermissonByPid(int pid) {
		
		return permissionMapper.selectByPid(pid);
	}

	@Override
	public List<Permission> queryAllPermission() {
		
		return permissionMapper.selectAll();
	}

	@Override
	public void insertPermission(Permission permission) {
		
		 permissionMapper.insert(permission);
		
	}

	@Override
	public Permission queryPermissionById(Integer id) {
		
		return permissionMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updatePermission(Permission permission) {
		permissionMapper.updateByPrimaryKey(permission);
		
	}

	@Override
	public void deletePermission(Integer id) {
		
		permissionMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public List<Integer> queryPermissionByRoleId(Integer roleid) {
		
		return rolePermissionMapper.selectPermissionByRoleId(roleid);
	}

	
}
