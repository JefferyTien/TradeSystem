package com.reddoor.framework.service;

import java.util.List;

import com.reddoor.framework.domain.Permission;

public interface PermissionService {
	public List<Permission> getMenus(Integer userId);
	
	public List<Permission> getAllMenus();
	
	public List<Permission> getAll();
	
	public List<Permission> getPermissions(Integer userId);
	
	public Permission get(Integer id);
	
	public List<Permission> getMenuOperation(Integer pid);
	
	public void insert(Permission permission);
	
	public void update(Permission permission);
	
	public void delete(Integer id);
	
}
