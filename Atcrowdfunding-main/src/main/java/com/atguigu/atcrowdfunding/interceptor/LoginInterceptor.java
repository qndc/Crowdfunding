package com.atguigu.atcrowdfunding.interceptor;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.util.Const;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	/**
	 * 在请求到达控制器之前执行
	 * true:放行
	 * false:不放行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//1.过滤不需要拦截的路径
		Set<String> uriSet = new HashSet<String>();
		uriSet.add("/login.htm");
		uriSet.add("/reg.htm");
		uriSet.add("/reg.do");
		uriSet.add("/index.htm");
		uriSet.add("/logout.do");
		uriSet.add("/doLogin.do");
		uriSet.add("/sendCode.do");
	    uriSet.add("/signUp.do");
	    uriSet.add("/check.do");
	    uriSet.add("/homepage/notifyOrder.do");
		
		String servletPath = request.getServletPath();
		String requestURI = request.getRequestURI();
		
		if (uriSet.contains(servletPath)) {
			return true;
		}

		//2.判断用户是否已经登录
		User user = (User) request.getSession().getAttribute(Const.LOGIN_USER);
		Member member = (Member) request.getSession().getAttribute(Const.LOGIN_MEMBER);
		if (user != null || member != null) {
			
			return true;
		}else {
			
			response.sendRedirect(request.getContextPath()+"/login.htm");
			return false;
		}
		
		
	}

}
