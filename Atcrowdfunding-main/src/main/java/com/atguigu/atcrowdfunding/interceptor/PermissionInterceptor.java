package com.atguigu.atcrowdfunding.interceptor;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.manager.service.PermissionService;
import com.atguigu.atcrowdfunding.util.Const;

/**
 * 权限访问拦截器
 * @author dc
 *
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String servletPath = request.getServletPath();
		
		// 1.先判断用户登录成功后的访问路径是否包含在权限表中
		Set<String> uris = (Set<String>) request.getSession().getServletContext().getAttribute(Const.ALL_PERMISSION_URI);
		
		// 2.判断访问路径是否是用户所拥有的权限路径
		if (uris.contains(servletPath)) {
			Set<String> uriSet = (Set<String>) request.getSession().getAttribute(Const.LOGIN_AUTH_PERMISSION);
			Iterator<String> iterator = uriSet.iterator();
			if (uriSet.contains(servletPath)) {
				return true;
			}else {
				response.sendRedirect(request.getContextPath()+"/login.htm");
				return false;
			}
			
		}else {
			return true;
		}
	}

}
