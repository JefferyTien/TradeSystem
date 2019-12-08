package com.reddoor.tradesystem.mapper.authority;

import java.util.List;

import com.reddoor.tradesystem.domain.authority.SysPermission;
import com.reddoor.tradesystem.domain.authority.SysPermissionExample;


public interface SysPermissionMapper {

	List<SysPermission> selectByExample(SysPermissionExample example);
}
