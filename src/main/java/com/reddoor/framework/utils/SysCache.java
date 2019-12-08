package com.reddoor.framework.utils;

import java.util.Map;
import java.util.Properties;

import com.reddoor.framework.domain.Configuration;

public class SysCache {
	public static Map<String, Configuration> sysConfigMap = null;
	public static Properties sysProps = null;
	
	public static String getConfigByCode(String code){
		Configuration sysConfig = sysConfigMap.get(code);
		String value = null;
		if(null != sysConfig){
			value = sysConfig.getValue();
		}
		return value;
	}
	
	public static String getPropByKey(String key){
		return sysProps.getProperty(key);
	}
}
