<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page isELIgnored="false"%>
<!-- 抽取左侧菜单 -->
<ul style="padding-left: 0px;" class="list-group">

	<!-- RBAC权限管理，根据所属角色显示对应的权限 -->
	<c:forEach items="${sessionScope.permissionroot.children }" var="permission">
		<c:if test="${empty permission.children }">
			<li class="list-group-item tree-closed">
				<a href="javascript:void(0)" style="text-decoration: none;color: #333"><i class="${permission.icon}"></i> ${permission.name}</a>
			</li>
		</c:if>
		<c:if test="${not empty permission.children }">
			<li class="list-group-item tree-closed">
				<span><i class="${permission.icon}"></i> ${permission.name} <span class="badge" style="float: right">${fn:length(permission.children)}</span></span>
				<ul style="margin-top: 10px;display: none;">
					<c:forEach items="${permission.children }" var="child">
						<li style="height: 30px;">
							<a href="${APP_PATH }/${child.url}"><i class="${child.icon}"></i> ${child.name}</a>
						</li>
					</c:forEach>
				</ul>
			</li>
		</c:if>
	</c:forEach>
	
	
	
</ul>