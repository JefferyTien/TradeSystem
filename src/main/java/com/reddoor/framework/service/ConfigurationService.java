package com.reddoor.framework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reddoor.framework.domain.Configuration;
import com.reddoor.framework.mapper.ConfigurationMapper;
import com.reddoor.framework.service.impl.BaseServiceImpl;

import tk.mybatis.mapper.common.Mapper;


@Service
public class ConfigurationService extends BaseServiceImpl<Configuration, Integer>{
	@Autowired
	private ConfigurationMapper configurationMapper;
	
	@Override
	public Mapper<Configuration> getMapper() {
		return configurationMapper;
	}
}

