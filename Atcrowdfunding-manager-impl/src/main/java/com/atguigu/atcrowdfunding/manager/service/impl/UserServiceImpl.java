package com.atguigu.atcrowdfunding.manager.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.bean.UserRole;
import com.atguigu.atcrowdfunding.exception.LoginFailException;
import com.atguigu.atcrowdfunding.manager.dao.PermissionMapper;
import com.atguigu.atcrowdfunding.manager.dao.RoleMapper;
import com.atguigu.atcrowdfunding.manager.dao.RolePermissionMapper;
import com.atguigu.atcrowdfunding.manager.dao.UserMapper;
import com.atguigu.atcrowdfunding.manager.dao.UserRoleMapper;
import com.atguigu.atcrowdfunding.manager.service.UserService;
import com.atguigu.atcrowdfunding.util.MD5Util;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.Role;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper ;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public User queryUserlogin(Map<String, Object> paramMap) {
		User user = userMapper.queryUserlogin(paramMap);
		return user;
	}

	@Override
	public Page queryPage(Integer pageTotal, Integer pageSize) {
		Page page = new Page(pageTotal, pageSize);
		//获取分页查询的记录数
		List<User> data = (List<User>) userMapper.queryList(page.startIndex(), pageSize);
		page.setData(data);
		//查询总记录数
		Integer counts = userMapper.queryCounts();
		page.setCounts(counts);
		return page;
	}

	@Override
	public Page queryPage(Integer pageTotal, Integer pageSize, String queryText) {

		Page page = new Page(pageTotal, pageSize);
		//获取分页查询的记录数
		List<User> data = (List<User>) userMapper.queryList(page.startIndex(),pageSize,queryText);
		page.setData(data);
		//查询总记录数
		Integer counts = userMapper.queryCounts(queryText);
		page.setCounts(counts);
		return page;
	}

	@Override
	public void saveUser(User user) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		user.setCreatetime(sFormat.format(new Date()));
		user.setUserpswd(MD5Util.getMD5(user.getLoginacct()));
		userMapper.insert(user);
	}

	@Override
	public User queryUserById(Integer id) {
		User user = userMapper.selectByPrimaryKey(id);
		return user;
	}

	@Override
	public void updateUser(User user) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		user.setCreatetime(sFormat.format(new Date()));
		user.setUserpswd(MD5Util.getMD5(user.getLoginacct()));
		userMapper.updateByPrimaryKey(user);
	}

	@Override
	public void deleteUser(Integer id) {
		//删除分配给用户的角色
		userRoleMapper.deleteUserRole(id);
		//删除用户
		userMapper.deleteByPrimaryKey(id);
		
		
	}

	@Override
	public void deleteAllChecked(Integer[] id) {
		
	
		for (Integer i : id) {
			userRoleMapper.deleteUserRole(i);
			userMapper.deleteByPrimaryKey(i);
		}
		
	}

	@Override
	public List<Role> queryAllRole() {
		List<Role> allRoles = roleMapper.selectAll();
		return allRoles;
	}

	@Override
	public List<Integer> queryBelongRole(Integer id) {
		List<Integer> roList = userRoleMapper.selectByUserId(id);
		return roList;
	}

	@Override
	public void assignRole(List<UserRole> userRoles) {
		int count= 0;
		for (UserRole userRole : userRoles) {
			int i = userRoleMapper.insert(userRole);
			if (i > 0) {
				count ++;
			}
		}
		if (count != userRoles.size()) {
			throw new RuntimeException("分配角色失败！");
		}
	}

	
	@Override
	public void deleteRole(List<UserRole> userRoles) {
		int count = 0;
		for (UserRole userRole : userRoles) {
			int result = userRoleMapper.deleteRole(userRole.getUserid(),userRole.getRoleid());
			if (result > 0) {
				count ++;
			}
		}
		if (count != userRoles.size()) {
			throw new RuntimeException("角色删除失败!");
		}
		
	}

	@Override
	public List<Permission> selectPermissionByUserId(Integer id) {
		List<Integer> roleIds = userRoleMapper.selectByUserId(id);
		Set<Integer> perIds = new HashSet<Integer>();
		for (Integer roleId : roleIds) {
			List<Integer> permissions = rolePermissionMapper.selectPermissionByRoleId(roleId);
			for (Integer integer : permissions) {
				//存入set集合，是因为set集合是无序且不可重复的，具有自动去除重复的功能
				perIds.add(integer);
			}
		}
		//当前用户拥有的所有的权限
		List<Permission> pers = new ArrayList<Permission>();
		Iterator<Integer> iterator = perIds.iterator();
		while (iterator.hasNext()) {
			Integer integer = (Integer) iterator.next();
			Permission permission = permissionMapper.selectByPrimaryKey(integer);
			pers.add(permission);
		}
		return pers;
	}
	
}
