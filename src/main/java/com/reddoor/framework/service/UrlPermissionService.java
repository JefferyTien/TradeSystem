package com.reddoor.framework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reddoor.framework.domain.UrlPermission;
import com.reddoor.framework.mapper.UrlPermissionMapper;
import com.reddoor.framework.service.impl.BaseServiceImpl;

import tk.mybatis.mapper.common.Mapper;

@Component
public class UrlPermissionService extends BaseServiceImpl<UrlPermission, Integer>{

	@Autowired
	private UrlPermissionMapper urlPermissionMapper;
	
	@Override
	public Mapper<UrlPermission> getMapper() {
		return urlPermissionMapper;
	}

}
