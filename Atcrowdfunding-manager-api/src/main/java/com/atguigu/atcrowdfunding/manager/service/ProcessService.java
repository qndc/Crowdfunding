package com.atguigu.atcrowdfunding.manager.service;

import java.util.List;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.RolePermission;

public interface ProcessService {

	/**
	 * 查询流程定义列表
	 * @return
	 */
	List<Process> queryProcessList();

	


}
