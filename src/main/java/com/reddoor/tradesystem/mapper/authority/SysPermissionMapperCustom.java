package com.reddoor.tradesystem.mapper.authority;

import java.util.List;

import com.reddoor.tradesystem.domain.authority.SysPermission;

public interface SysPermissionMapperCustom {
	
	public String findPermissionByUserId(String userId) throws Exception;
	
	public List<SysPermission> findPermissionListByUserId(String userid) throws Exception;
	
	public List<SysPermission> findMenuListByUserId(String userid)throws Exception;
	
}
