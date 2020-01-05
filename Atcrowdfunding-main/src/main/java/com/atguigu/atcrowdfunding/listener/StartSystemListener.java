package com.atguigu.atcrowdfunding.listener;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.manager.service.PermissionService;
import com.atguigu.atcrowdfunding.util.Const;

public class StartSystemListener implements ServletContextListener {
	
	

	//在服务器启动时,创建application对象时需要执行的方法.
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//1.将项目上下文路径(request.getContextPath())放置到application域中.
		ServletContext application = sce.getServletContext();
		String contextPath = application.getContextPath();
		application.setAttribute("APP_PATH", contextPath);
		
		//2.将所有权限放入application域中
		ApplicationContext ico = WebApplicationContextUtils.getWebApplicationContext(application);
		System.err.println(ico);
		PermissionService permissionService = ico.getBean(PermissionService.class);
		List<Permission> allPermission = permissionService.queryAllPermission();
		Set<String> uris = new HashSet<String>();
		for (Permission permission : allPermission) {
			if (StringUtils.isNotEmpty(permission.getUrl())) {
				uris.add("/"+permission.getUrl());
			}
		}
		application.setAttribute(Const.ALL_PERMISSION_URI,uris);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
