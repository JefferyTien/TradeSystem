package com.reddoor.framework.common.permission;

import java.lang.annotation.Annotation;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RequirePermissionHandler extends HandlerInterceptorAdapter {
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
			HandlerMethod myHandlerMethod = (HandlerMethod)handler;
			//通过handler获取请求对应的方法，并获取方法上的RequirePermission注解
			Annotation requirePermission = myHandlerMethod.getMethod().getAnnotation(RequirePermission.class);
			if(requirePermission != null){
				//如果有权限注解，获取用户对应的权限
				Set<String> hasPermissions = (Set<String>)request.getSession().getAttribute("permissions");
				//获取方法注解中要求的权限
				String permission = ((RequirePermission)requirePermission).value();
				if(CollectionUtils.isEmpty(hasPermissions)||!hasPermissions.contains(permission)){
					//用户没有权限，或不包含注解要求的权限，抛出异常
					throw new RequirePermissionException("user doesn't have this permission!");
				}
			}
			return true;
		}
}
