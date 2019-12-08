package com.reddoor.framework.mapper;

import java.util.List;

import com.reddoor.tradesystem.domain.authority.SysPermission;

public interface PermissionMapperCustom {
	
	public String findPermissionByUserId(String userId) throws Exception;
	
	public List<SysPermission> findPermissionListByUserId(String userid) throws Exception;
	
	public List<SysPermission> findMenuListByUserId(String userid)throws Exception;
	
}
