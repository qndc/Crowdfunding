package com.atguigu.atcrowdfunding.manager.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.bean.UserRole;
import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.manager.service.UserService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Page;


@Controller
public class UserController {
	
	@Resource
	private UserService userService;
	
	/**
	 * 	异步分页查询用户列表
	 * @return
	 */
	@RequestMapping("/user/list")
	@ResponseBody
	public Object list(@RequestParam(defaultValue = "1")Integer pageTotal,@RequestParam(defaultValue = "10")Integer pageSeize,String queryText) {
		AjaxResult result = new AjaxResult();
		try {
			Page queryPage = userService.queryPage(pageTotal,pageSeize,queryText);
			result.setStatus(200);
			result.setMessage(queryPage);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
			result.setMessage("查询数据失败！");
		}
		return result;	
	}
	
	/**
	 * 	异步添加用户
	 * @return
	 */
	@RequestMapping(value = "/user/add",method = RequestMethod.POST)
	@ResponseBody
	public Object add(User user) {
		AjaxResult result = new AjaxResult();
		try {
			userService.saveUser(user);
			System.out.println(user.getId());
			List<UserRole> userRoles = new ArrayList<UserRole>();
			UserRole userRole = new UserRole();
			userRole.setUserid(user.getId());
			userRole.setRoleid(10);
			userRoles.add(userRole);
			userService.assignRole(userRoles);
			result.setStatus(200);
			result.setMessage("添加成功！");
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("添加失败");
			e.printStackTrace();
		}
		return result;	
	}
	
	/**
	 * 	异步删除/批量删除用户
	 * @return
	 */
	@RequestMapping(value = "/user/delete")
	@ResponseBody
	public Object delete(Integer id) {
		AjaxResult result = new AjaxResult();
		try {
			userService.deleteUser(id);
			result.setStatus(200);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("删除失败！");
			e.printStackTrace();
		}
		return result;	
	}
	
	@RequestMapping(value = "/user/deleteAll")
	@ResponseBody
	public Object deleteAll(Integer[] id) {
		AjaxResult result = new AjaxResult();
		try {
			userService.deleteAllChecked(id);
			result.setStatus(200);
			result.setMessage("批量删除成功！");
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("批量删除失败！");
			e.printStackTrace();
		}
		return result;	
	}
	
	/**
	 * 	异步修改用户
	 * @return
	 */
	@RequestMapping(value = "/user/update")
	@ResponseBody
	public Object update(User user) {
		AjaxResult result = new AjaxResult();
		try {
			userService.updateUser(user);
			result.setStatus(200);
			result.setMessage("修改成功！");
		} catch (Exception e) {
			result.setStatus(500);
			result.setMessage("修改失败！");
			e.printStackTrace();
		}
		return result;	
	}
	
	/**
	 * 	用户先查询后修改
	 * @return
	 */
	@RequestMapping("/user/toUpdate")
	public String toUpdate(@RequestParam(required = true)Integer id,Map map) {
		User user = userService.queryUserById(id);
		map.put("user", user);
		return "user/update";	
	}
	
	/**
	 * 	异步加载用户列表，首先请求跳转到用户列表展示页面
	 * @return
	 */
	@RequestMapping("/user/toUserIndex")
	public String toUserIndex() {
		return "user/index";	
	}
	
	/**
	 * 跳转到用户添加页面
	 * @return
	 */
	@RequestMapping("/user/toAdd")
	public String toAdd() {
		return "user/add";	
	}
	
	/**
	 * 跳转到用户权限分配页面
	 * @return
	 */
	@RequestMapping("/user/toAssignRole")
	public String toAssignRole(Integer id,Map map ) {
		//先查询所有的角色
		List<Role> roleList = userService.queryAllRole();
		//再查询已分配的角色
		List<Integer> roles = userService.queryBelongRole(id);
		
		//未分配角色
		List<Role> leftRoles = new ArrayList<Role>();
		//已分配角色
		List<Role> rightRoles = new ArrayList<Role>();
		
		for (Role role : roleList) {
			if (roles.contains(role.getId())) {
				rightRoles.add(role);
			}else {
				leftRoles.add(role);
			}
		}
		map.put("leftRoles", leftRoles);
		map.put("rightRoles", rightRoles);
		return "user/assignRole";	
	}

	/**
	 * 分配权限
	 * @param roleid
	 * @return
	 */
	@RequestMapping("/user/assignRole")
	@ResponseBody
	public AjaxResult assignRole(Integer userid,String roleid) {
		AjaxResult result = new AjaxResult();
		String[] split = roleid.split("&");
		List<UserRole> userRoles = new ArrayList<UserRole>();
		for (String string : split) {
			UserRole userRole = new UserRole();
			userRole.setUserid(userid);
			userRole.setRoleid(Integer.parseInt(string));
			userRoles.add(userRole);
		}
		try {
			userService.assignRole(userRoles);
			result.setStatus(200);
			result.setMessage("角色分配成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
			result.setMessage("角色分配失败！");
		}
		return result;
	}
	
	/**
	 * 删除权限
	 * @param roleid
	 * @return
	 */
	@RequestMapping("/user/delRole")
	@ResponseBody
	public AjaxResult delRole(Integer userid,String roleid) {
		AjaxResult result = new AjaxResult();
		String[] split = roleid.split("&");
		List<UserRole> userRoles = new ArrayList<UserRole>();
		for (String string : split) {
			UserRole userRole = new UserRole();
			userRole.setUserid(userid);
			userRole.setRoleid(Integer.parseInt(string));
			userRoles.add(userRole);
		}
		try {
			userService.deleteRole(userRoles);
			result.setStatus(200);
			result.setMessage("角色删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(500);
			result.setMessage("角色删除失败！");
		}
		return result;
	}
}
