package com.atguigu.atcrowdfunding.manager.controller;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.RolePermission;
import com.atguigu.atcrowdfunding.manager.service.PermissionService;
import com.atguigu.atcrowdfunding.manager.service.RoleService;
import com.atguigu.atcrowdfunding.util.AjaxResult;

@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionServiceImpl;

	@RequestMapping("/role/toIndex")
	public String index() {
		return "/role/index";
	}

	/**
	 * 展示角色列表
	 * 
	 * @return
	 */
	@RequestMapping("/role/list")
	@ResponseBody
	public Object list() {
		AjaxResult result = new AjaxResult();
		try {
			List<Role> roles = roleService.queryAllRole();
			result.setStatus(200);
			result.setMessage(roles);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(200);
			result.setMessage("查询失败！");
		}
		return result;
	}

	/**
	 * 跳转到许可分配页面
	 * 
	 * @return
	 */
	@RequestMapping("/role/toAssignPermission")
	public String toAssignPermission() {
		return "/role/assignPermission";
	}

	/**
	 * 查询所有许可以及当前角色所拥有的的许可（权限）
	 * 
	 * @return
	 */
	@RequestMapping("/role/loadDataAsync")
	@ResponseBody
	public Object loadDataAsync(Integer roleid) {

		// 加载所有权限
		List<Permission> list = permissionServiceImpl.queryPermissonByPid(0);
		queryPermissons(list.get(0));

		//加载当前角色所拥有的的许可
		List<Integer> permissionsForRoleId = permissionServiceImpl.queryPermissionByRoleId(roleid);
		for (Permission permission : list) {
			check(permission, permissionsForRoleId);
		}
		return list;
	}
	
	/**
	 * 分配权限（许可）
	 * @param roleid
	 * @return
	 */
	@RequestMapping("/role/assignPermission")
	@ResponseBody
	public Object assignPermission(String ids,Integer roleId) {

		AjaxResult result = new AjaxResult();
		try {
			//先删除之前分配的权限，在添加新分配的权限
			String[] perId = ids.split("&");
			roleService.deletePermissionByRoleId(roleId);
			for (String id : perId) {
				RolePermission rp = new RolePermission();
				rp.setRoleid(roleId);
				rp.setPermissionid(Integer.parseInt(id));
				roleService.insertPermission(rp);
			}
			result.setStatus(200);
			result.setMessage("成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(200);
			result.setMessage("权限分配失败！");
		}
		return result;
	}

	/**
	 * 根据pid，父级id查询递归子节点,递归多次访问数据库，当数据量过大时，查询效率低 所以采取一次加载所有数据，然后在内存中进行分阶
	 * 
	 * @param pid
	 * @return
	 */
	private void queryPermissons(Permission permission) {
		List<Permission> children = permissionServiceImpl.queryPermissonByPid(permission.getId());
		permission.setChildren(children);
		for (Permission innerChildren : children) {
			queryPermissons(innerChildren);
		}
	}
	
	private void check(Permission permission,List<Integer> permissionsForRoleId) {
		if (permissionsForRoleId.contains(permission.getId())) {
			permission.setChecked(true);
			if (permission.getChildren() != null) {
				for (Permission p : permission.getChildren()) {
					check(p, permissionsForRoleId);
				}
			}
		}
	}

}
