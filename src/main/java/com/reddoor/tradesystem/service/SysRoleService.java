package com.reddoor.tradesystem.service;

import com.reddoor.tradesystem.domain.vo.RoleVO;

public interface SysRoleService {
	RoleVO findRoleByUserId(String userId) throws Exception;
}
