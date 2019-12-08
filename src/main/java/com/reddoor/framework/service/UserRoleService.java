package com.reddoor.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reddoor.framework.domain.UserRole;
import com.reddoor.framework.mapper.UserRoleMapper;
import com.reddoor.framework.service.impl.BaseServiceImpl;

import tk.mybatis.mapper.common.Mapper;

@Service
public class UserRoleService extends BaseServiceImpl<UserRole, Integer>{

	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Override
	public Mapper<UserRole> getMapper() {
		return userRoleMapper;
	}
	
	public List<Integer> getRolesById(Integer userId){
		return userRoleMapper.getRolesById(userId);
	}	
	
	@Transactional(readOnly = false)
	public void updateUserRole(Integer userId, List<Integer> oldList, List<Integer> newRoleList){
		for(Integer eachOld:oldList){
			if(!newRoleList.contains(eachOld)){
				userRoleMapper.deleteByUserIdRoleId(userId, eachOld);
			}
		}
		
		for(Integer eachNew:newRoleList){
			if(!oldList.contains(eachNew)){
				userRoleMapper.insert(buildUserRole(userId, eachNew));
			}
		}
	}
	
	/**
	 * 构建UserRole
	 * 
	 * @param userId
	 * @param roleId
	 * @return UserRole
	 */
	private UserRole buildUserRole(Integer userId, Integer roleId) {
		UserRole ur = new UserRole();
		ur.setUserId(userId);
		ur.setRoleId(roleId);
		return ur;
	}

}
