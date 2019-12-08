package com.reddoor.framework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reddoor.framework.domain.Permission;
import com.reddoor.framework.mapper.PermissionMapper;
import com.reddoor.framework.service.PermissionService;

@Service("PermissionServiceImpl")
public class PermissionServiceImpl implements PermissionService{
	
	@Autowired
	PermissionMapper permissionMapper;
	
	@Override
	public List<Permission> getMenus(Integer userId) {
		return permissionMapper.getMenus(userId);
	}

	@Override
	public List<Permission> getPermissions(Integer userId) {
		return permissionMapper.getPermissions(userId);
	}

	@Override
	public List<Permission> getAllMenus() {
		return permissionMapper.getAllMenus();
	}
	
	@Override
	public List<Permission> getAll() {
		return permissionMapper.findAll();
	}
	
	@Transactional(readOnly = false)
	@Override
	public void insert(Permission permission) {
		permissionMapper.insert(permission);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void update(Permission permission) {
		permissionMapper.update(permission);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void delete(Integer id) {
		permissionMapper.delete(id);
	}

	@Override
	public Permission get(Integer id) {
		return permissionMapper.get(id);
	}

	@Override
	public List<Permission> getMenuOperation(Integer pid) {
		return permissionMapper.getMenuOperation(pid);
	}
	}
