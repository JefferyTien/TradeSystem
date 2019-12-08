package com.reddoor.framework.mapper;

import java.util.List;

import com.reddoor.framework.domain.Permission;
import com.reddoor.framework.domain.PermissionExample;


public interface PermissionMapper {

	List<Permission> selectByExample(PermissionExample example);
	
	List<Permission> getMenus(Integer userId);
	
	List<Permission> getAllMenus();
	
	public List<Permission> findAll();
	
	List<Permission> getPermissions(Integer userId);
	
	Permission get(Integer id);
	
	void insert(Permission permission);
	
	void update(Permission permission);
	
	void delete(Integer id);
	
	List<Permission> getMenuOperation(Integer pid);
}
