package com.reddoor.tradesystem.mapper.authority;

import java.util.List;

import com.reddoor.tradesystem.domain.authority.SysUserRole;
import com.reddoor.tradesystem.domain.authority.SysUserRoleExample;

public interface SysUserRoleMapper {

	List<SysUserRole> selectByExample(SysUserRoleExample example);
}
