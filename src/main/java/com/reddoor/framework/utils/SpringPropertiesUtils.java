package com.reddoor.framework.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;


//<bean class="com.reddoor.framework.utils.SpringPropertiesUtils">
//<property name="locations">
//    <list>
//        <value>classpath:epay.properties</value>
//    </list>
//</property>
//</bean>

public class SpringPropertiesUtils extends PropertyPlaceholderConfigurer {
	private static Map<String, String> ctxPropertiesMap;
	
	@Override
	protected void loadProperties(Properties props) throws IOException{
		super.loadProperties(props);
		ctxPropertiesMap = new HashMap<String, String>();
		for(Object key : props.keySet()){
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			ctxPropertiesMap.put(keyStr, value);
		}
	}
	
	public static String getString(String key) {
        try {
            return ctxPropertiesMap.get(key);
        } catch (MissingResourceException e) {
            return null;
        }
    }
}