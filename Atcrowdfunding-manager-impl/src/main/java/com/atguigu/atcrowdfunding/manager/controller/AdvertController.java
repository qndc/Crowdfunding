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
public class AdvertController {
	
	/**
	 * 广告展示列表
	 * @return
	 */
	@RequestMapping("/advert/toIndex")
	public String toIndex() {
		return "/advert/index";
	}
	
	/**
	 * 广告添加页面
	 * @return
	 */
	@RequestMapping("/advert/toAdd")
	public String toAdd() {
		return "/advert/add";
	}
	

}
