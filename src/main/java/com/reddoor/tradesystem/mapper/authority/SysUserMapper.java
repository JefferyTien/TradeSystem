package com.reddoor.tradesystem.mapper.authority;

import java.util.List;

import com.reddoor.tradesystem.domain.authority.SysUser;
import com.reddoor.tradesystem.domain.authority.SysUserExample;


public interface SysUserMapper {
	List<SysUser> selectByExample(SysUserExample example);
}
