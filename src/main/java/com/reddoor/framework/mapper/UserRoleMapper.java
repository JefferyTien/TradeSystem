package com.reddoor.framework.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.reddoor.framework.domain.UserRole;

import tk.mybatis.mapper.common.Mapper;

public interface UserRoleMapper extends Mapper<UserRole> {
	public List<Integer> getRolesById(Integer userId);
	
	public void deleteByUserIdRoleId(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
}