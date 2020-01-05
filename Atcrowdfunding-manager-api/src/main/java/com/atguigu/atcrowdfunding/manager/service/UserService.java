package com.atguigu.atcrowdfunding.manager.service;

import java.util.List;
import java.util.Map;


import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.bean.UserRole;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.Role;
public interface UserService {

	User queryUserlogin(Map<String, Object> paramMap);

	Page queryPage(Integer pageTotal, Integer pageSeize);

	Page queryPage(Integer pageTotal, Integer pageSeize, String queryText);

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	void saveUser(User user);

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	User queryUserById(Integer id);

	/**
	 * 更新用户
	 * @param user
	 */
	void updateUser(User user);

	/**
	 * 删除
	 * @param id
	 */
	void deleteUser(Integer id);

	/**
	 * 批量删除
	 * @param id
	 */
	void deleteAllChecked(Integer[] id);

	/**
	 * 查询所有角色
	 * @return
	 */
	List<Role> queryAllRole();

	/**
	 * 查询当前用户拥有的角色
	 * @param id
	 * @return
	 */
	List<Integer> queryBelongRole(Integer id);

	/**
	 * 为当前用户分配角色
	 * @param userid
	 * @param roles
	 */
	void assignRole(List<UserRole> userRoles);

	/**
	 * 删除当前用户的角色分配
	 * @param userRoles
	 */
	void deleteRole(List<UserRole> userRoles);

	/**
	 * 根据用户id查询当前用户所有权限
	 * @param id
	 * @return
	 */
	List<Permission> selectPermissionByUserId(Integer id);
	
	

}
