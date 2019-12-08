package com.reddoor.tradesystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reddoor.tradesystem.domain.authority.SysUserRole;
import com.reddoor.tradesystem.domain.authority.SysUserRoleExample;
import com.reddoor.tradesystem.domain.vo.RoleVO;
import com.reddoor.tradesystem.mapper.authority.SysRoleMapper;
import com.reddoor.tradesystem.mapper.authority.SysUserRoleMapper;
import com.reddoor.tradesystem.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
    SysRoleMapper sysRoleMapper;
	
	@Autowired
	SysUserRoleMapper sysUserRoleMapper;
	
	@Override
	public RoleVO findRoleByUserId(String userId) throws Exception {
		SysUserRoleExample example = new SysUserRoleExample();
		SysUserRoleExample.Criteria criteria = example.createCriteria();
		criteria.andSysUserIdEqualTo(userId);
		SysUserRole sysUserRole = sysUserRoleMapper.selectByExample(example).get(0);
		RoleVO sysRole = sysRoleMapper.selectByPrimaryKey(sysUserRole.getSysRoleId());
		return sysRole;
	}

}
