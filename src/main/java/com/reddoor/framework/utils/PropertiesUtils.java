package com.reddoor.framework.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtils {
	public static Properties getProperties(String path){
		Properties props = null;
		
		props = new Properties();
//		try {
//			InputStream in = PropertiesUtils.class.getResourceAsStream(path);
//			BufferedReader bf = new BufferedReader(new InputStreamReader(in));
//			props.load(bf);
//			in.close();
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}
		return props;
	}
	
}
