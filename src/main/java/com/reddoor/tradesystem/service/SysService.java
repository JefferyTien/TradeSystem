package com.reddoor.tradesystem.service;

import java.util.List;

import com.reddoor.tradesystem.domain.authority.SysPermission;
import com.reddoor.tradesystem.domain.authority.SysUser;

public interface SysService {
	SysUser getSysUserByName(String username)throws Exception;

	List<SysPermission> findMenuListByUserId(String userId) throws Exception;

	List<SysPermission> findPermissionListByUserId(String userId) throws Exception;
}
