package com.reddoor.tradesystem.service;

import com.reddoor.tradesystem.domain.vo.RoleVO;

public interface RoleService {
	RoleVO findRoleByUserId(String userId) throws Exception;
}
