package com.reddoor.framework.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;

/**
 * session过滤器，修改response对象，设置session超时标识
 *
 * @version  0.0.1
 */
public class SessionFilter implements Filter {
	
	private List<String> excludedUrls = new ArrayList<String>();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	    String excludePattern = filterConfig.getInitParameter("excludedUrls");
	    excludedUrls = Arrays.asList(excludePattern.split(","));
	}

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,
            ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        
        StringBuffer sb= request.getRequestURL();
        
        //判断session里是否有用户信息,且是否为ajax请求，如果是ajax请求响应头会有，x-requested-with
        if (request.getHeader("x-requested-with") != null
                && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
        	if (!sb.toString().endsWith("ajaxLogin.do")) {
//        		if(!SecurityUtils.getSubject().isAuthenticated() && !SecurityUtils.getSubject().isRemembered()){
        		if(!SecurityUtils.getSubject().isAuthenticated()){
        			response.setHeader("session-status", "timeout");//在响应头设置session状态
                    response.getWriter().print("session-status");
    				return;
        		}
            }
        }
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

}
