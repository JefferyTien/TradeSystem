package com.reddoor.tradesystem.mapper.authority;

import com.reddoor.tradesystem.domain.vo.RoleVO;

public interface SysRoleMapper {
	
	RoleVO selectByPrimaryKey(String id);
}
