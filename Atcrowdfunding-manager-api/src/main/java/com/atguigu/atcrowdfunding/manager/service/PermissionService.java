package com.atguigu.atcrowdfunding.manager.service;

import java.util.List;

import com.atguigu.atcrowdfunding.bean.Permission;



public interface PermissionService {

	/**
	 * 查询根节点
	 * @param i
	 * @return
	 */
	List<Permission> queryPermissonByPid(int pid);

	/**
	 * 	查询所有权限
	 * @return
	 */
	List<Permission> queryAllPermission();

	/**
	 * 添加许可（权限）
	 * @param permission
	 */
	void insertPermission(Permission permission);

	/**
	 * 	根据id查询许可
	 * @param id
	 * @return
	 */
	Permission queryPermissionById(Integer id);

	/**
	 * 	修改许可
	 * @param permission
	 */
	void updatePermission(Permission permission);

	/**
	 * 	删除许可
	 * @param id
	 */
	void deletePermission(Integer id);

	/**
	 * 根据角色id查询当前角色所拥有的的权限
	 * @param roleid
	 * @return
	 */
	List<Integer> queryPermissionByRoleId(Integer roleid);

}
