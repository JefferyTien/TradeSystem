package com.reddoor.framework.service.boot;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component("springContextUtil")
public class SpringContextUtil implements ApplicationContextAware {  
    
    private static ApplicationContext applicationContext;  
   
    @Override 
    public void setApplicationContext(ApplicationContext applicationContext)  
            throws BeansException {  
    	SpringContextUtil.applicationContext = applicationContext;  
    }  
       
    public static ApplicationContext getApplicationContext(){  
        return applicationContext;  
    }  
       
    public static Object getBean(String name){  
        return applicationContext.getBean(name);  
    }  
       
    public static <T> T getBean(String name, Class<T>  requiredClass){  
        return applicationContext.getBean(name, requiredClass);  
    }  
    
    public static <T> Map<String, T> getBeans(Class<T> type) {
        return applicationContext.getBeansOfType(type);
    }
   
}