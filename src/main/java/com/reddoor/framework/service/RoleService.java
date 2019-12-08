package com.reddoor.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reddoor.framework.domain.Role;
import com.reddoor.framework.mapper.RoleMapper;
import com.reddoor.framework.mapper.RolePermissionMapper;
import com.reddoor.framework.service.impl.BaseServiceImpl;

import tk.mybatis.mapper.common.Mapper;

@Service
public class RoleService extends BaseServiceImpl<Role, Integer>{

	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	
	@Override
	public Mapper<Role> getMapper() {
		return roleMapper;
	}
	
	@Transactional(readOnly = false)
	public void deleteRole(Integer roleId, List<Integer> permIdList){
		//是否删除
		for(int i=0,j=permIdList.size();i<j;i++){
			rolePermissionMapper.deleteRP(roleId,permIdList.get(i));
		}
		
		roleMapper.deleteByPrimaryKey(roleId);
	}
	
}
