package com.reddoor.framework.mapper;

import org.apache.ibatis.annotations.Param;

import com.reddoor.framework.domain.UserToken;

import tk.mybatis.mapper.common.Mapper;

public interface UserTokenMapper extends Mapper<UserToken> {
	public UserToken findUserToken(@Param("userId")Integer userId, @Param("deviceId")Integer deviceId);
}