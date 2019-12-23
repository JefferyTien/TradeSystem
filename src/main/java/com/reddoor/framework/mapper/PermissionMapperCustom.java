package com.reddoor.framework.mapper;

import java.util.List;

import com.reddoor.framework.domain.Permission;

public interface PermissionMapperCustom {
	
	public String findPermissionByUserId(String userId) throws Exception;
	
	public List<Permission> findPermissionListByUserId(String userid) throws Exception;
	
	public List<Permission> findMenuListByUserId(String userid)throws Exception;
	
}
