package com.reddoor.framework.service.boot;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;
/**
 * 注意这里是不能直接调用service的,执行到这里的时候spring可能还没有加载完
 * @author dell
 *
 */
public class ExContextLoaderListener extends ContextLoaderListener{
	@Override
	public void contextInitialized(ServletContextEvent event){
		// 加载...
		
		super.contextInitialized(event);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event){
		super.contextDestroyed(event);
	}
}
