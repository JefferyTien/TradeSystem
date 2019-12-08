package com.reddoor.framework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reddoor.framework.domain.UserToken;
import com.reddoor.framework.mapper.UserTokenMapper;
import com.reddoor.framework.service.impl.BaseServiceImpl;

import tk.mybatis.mapper.common.Mapper;

@Service
public class UserTokenService extends BaseServiceImpl<UserToken, Integer>{

	@Autowired
	private UserTokenMapper userTokenMapper;
	
	@Override
	public Mapper<UserToken> getMapper() {
		return userTokenMapper;
	}
	
	public UserToken findUserToken(Integer userId, Integer deviceId){
		return userTokenMapper.findUserToken(userId, deviceId);
	}
}
