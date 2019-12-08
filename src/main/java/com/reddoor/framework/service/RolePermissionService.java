package com.reddoor.framework.service;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reddoor.framework.domain.Permission;
import com.reddoor.framework.domain.Role;
import com.reddoor.framework.domain.RolePermission;
import com.reddoor.framework.mapper.RolePermissionMapper;
import com.reddoor.framework.service.impl.BaseServiceImpl;
import com.reddoor.framework.shiro.UserRealm;

import tk.mybatis.mapper.common.Mapper;

/**
 * 角色权限service
 */
@Service
@Transactional(readOnly=true)
public class RolePermissionService extends BaseServiceImpl<RolePermission, Integer> {
	
	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	
	@Override
	public Mapper<RolePermission> getMapper() {
		return rolePermissionMapper;
	}
	
	/**
	 * 获取角色权限id集合
	 * @param id
	 * @return List
	 */
	public List<Integer> getPermissionIds(Integer roleId){
		return rolePermissionMapper.findPermissionIds(roleId);
	}
	
	/**
	 * 修改角色权限
	 * @param id
	 * @param oldList
	 * @param newList
	 */
	@Transactional(readOnly = false)
	public void updateRolePermission(Integer id,List<Integer> oldList,List<Integer> newList){
		//是否删除
		for(int i=0,j=oldList.size();i<j;i++){
			if(!newList.contains(oldList.get(i))){
				rolePermissionMapper.deleteRP(id,oldList.get(i));
			}
		}
		
		//是否添加
		for(int i=0,j=newList.size();i<j;i++){
			if(!oldList.contains(newList.get(i))){
				rolePermissionMapper.insert(getRolePermission(id,newList.get(i)));
			}
		}
	}
	
	/**
	 * 清空该角色用户的权限缓存
	 */
	public void clearUserPermCache(PrincipalCollection pc){
		RealmSecurityManager securityManager =  (RealmSecurityManager) SecurityUtils.getSecurityManager();
		UserRealm userRealm = (UserRealm) securityManager.getRealms().iterator().next();
	    userRealm.clearCachedAuthorizationInfo(pc);
	}
	
	/**
	 * 构造角色权限对象
	 * @param roleId
	 * @param permissionId
	 * @return RolePermission
	 */
	private RolePermission getRolePermission(Integer roleId,Integer permissionId){
		RolePermission rp=new RolePermission();
		rp.setRoleId(roleId);
		rp.setPermissionId(permissionId);
		return rp;
	}

}
