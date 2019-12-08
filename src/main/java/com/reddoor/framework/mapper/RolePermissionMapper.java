package com.reddoor.framework.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.reddoor.framework.domain.RolePermission;

import tk.mybatis.mapper.common.Mapper;

public interface RolePermissionMapper extends Mapper<RolePermission> {
	public List<Integer> findPermissionIds(Integer roleId);
	
	public void deleteRP(@Param("roleId")Integer roleId,@Param("permissionId")Integer permissionId);
}