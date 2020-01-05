package com.atguigu.atcrowdfunding.manager.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.manager.dao.UserRoleMapper;
import com.atguigu.atcrowdfunding.manager.service.PermissionService;
import com.atguigu.atcrowdfunding.util.AjaxResult;

@Controller
public class PermissionController {
	
	@Autowired
	private PermissionService permissionServiceImpl;
	
	@RequestMapping("/permission/toIndex")
	public String toIndex() { 
		return "/permission/index";
		
	}

	@RequestMapping("/permission/loadData")
	@ResponseBody
	public Object loadData() { 
		AjaxResult result = new AjaxResult();
		
		try {
			//加载所有权限
			List<Permission> list = permissionServiceImpl.queryPermissonByPid(0);
			queryPermissons(list.get(0));
			result.setStatus(200);
			result.setMessage(list);
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("加载失败");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 	根据pid，父级id查询递归子节点,递归多次访问数据库，当数据量过大时，查询效率低
	 * 	所以采取一次加载所有数据，然后在内存中进行分阶
	 * @param pid
	 * @return
	 */
	private void queryPermissons(Permission permission){
		List<Permission> children = permissionServiceImpl.queryPermissonByPid(permission.getId());
		permission.setChildren(children);
		for (Permission innerChildren : children) {
			queryPermissons(innerChildren);
		}
	}

	/**
	 * 跳转到添加许可页面
	 * @return
	 */
	@RequestMapping("/permission/toAdd")
	public String toAdd() {
		return "/permission/add";
	}
	
	/**
	 * 添加许可
	 * @return
	 */
	@RequestMapping("/permission/add")
	@ResponseBody
	public Object addPermission(Permission permission) {
		AjaxResult result = new AjaxResult();
		try {
			permissionServiceImpl.insertPermission(permission);
			result.setStatus(200);
			result.setMessage("许可添加成功！");
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("许可添加失败！");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 	先根据id查询当前需要的许可，跳转到修改许可页面
	 * @return
	 */
	@RequestMapping("/permission/toUpdate")
	public String toUpdate(@RequestParam(required = true) Integer id,Map map) {
		Permission permission = permissionServiceImpl.queryPermissionById(id);
		map.put("permission", permission);
		return "/permission/update";
	}
	
	/**
	 * 	修改许可
	 * @return
	 */
	@RequestMapping("/permission/update")
	@ResponseBody
	public Object updatePermission(Permission permission) {
		AjaxResult result = new AjaxResult();
		try {
			permissionServiceImpl.updatePermission(permission);
			result.setStatus(200);
			result.setMessage("许可修改成功！");
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("许可修改失败！");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 	修改许可
	 * @return
	 */
	@RequestMapping("/permission/delete")
	@ResponseBody
	public Object deletePermission(Integer id) {
		AjaxResult result = new AjaxResult();
		try {
			permissionServiceImpl.deletePermission(id);
			result.setStatus(200);
			result.setMessage("许可删除成功！");
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("许可删除失败！");
			e.printStackTrace();
		}
		return result;
	}
}
