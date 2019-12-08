package com.reddoor.framework.mapper;

import com.reddoor.framework.domain.User;

import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
	User getUser(String loginName);
	
	User getUserByPhone(String phone);
	
	void updateUser(User user);
	
	void updatePwd(User user);
}