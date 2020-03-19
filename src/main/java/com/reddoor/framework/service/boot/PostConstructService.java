package com.reddoor.framework.service.boot;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reddoor.framework.domain.Configuration;
import com.reddoor.framework.service.ConfigurationService;
import com.reddoor.framework.utils.PropertiesUtils;
import com.reddoor.framework.utils.SysCache;

@Service("postConstructService")
public class PostConstructService  {
	
	@Autowired
	ConfigurationService configurationService;
	
	@PostConstruct
	public void init() throws Exception{
		// 加载数据库中sys_configuration表的所有系统配置, 并放入syscache缓存
		List<Configuration> sysConfigList = configurationService.findAll();
		Map<String, Configuration> sysConfigMap = new HashMap<String, Configuration>();
		if(null != sysConfigList){
			for(Configuration eachConfig:sysConfigList){
				sysConfigMap.put(eachConfig.getCode(), eachConfig);
			}
		}
		SysCache.sysConfigMap = sysConfigMap;
		
		// 加载classes根路径下的application.properties文件
		String path = this.getClass().getResource("/").getPath();
		String sysPropsPath = path + "/application.properties";
		File sysPropsFile = new File(sysPropsPath);
		if(sysPropsFile.exists() && sysPropsFile.isFile()){
			Properties sysProps = PropertiesUtils.getProperties(sysPropsPath);
			SysCache.sysProps = sysProps;
		}
	}
}
