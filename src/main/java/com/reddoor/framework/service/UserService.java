package com.reddoor.framework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reddoor.framework.domain.User;
import com.reddoor.framework.mapper.UserMapper;
import com.reddoor.framework.service.impl.BaseServiceImpl;
import com.reddoor.framework.utils.DateUtils;
import com.reddoor.framework.utils.security.Digests;
import com.reddoor.framework.utils.security.Encodes;

import tk.mybatis.mapper.common.Mapper;

@Service
public class UserService extends BaseServiceImpl<User, Integer>{

	/**加密方法*/
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;	//盐长度
	
	@Autowired
	UserMapper userMapper;
	
	@Override
	public Mapper<User> getMapper(){
		return userMapper;
	}
	
	public User getUser(String userName) {
		return userMapper.getUser(userName);
	}
	
	public User getUserByPhone(String phone) {
		return userMapper.getUserByPhone(phone);
	}
	
	public void create(User user) {
		entryptPassword(user);
		user.setCreateDate(DateUtils.getSysTimestamp());
		add(user);
	}
	
	@Transactional(readOnly=false)
	public void updateUser(User user){
		userMapper.updateUser(user);
	}

	
	public void updateUserLogin(User user){
		
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(User user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(),salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}
	
	/**
	 * 验证原密码是否正确
	 * @param user
	 * @param oldPwd
	 * @return
	 */
	public boolean checkPassword(User user,String oldPassword){
		byte[] salt =Encodes.decodeHex(user.getSalt()) ;
		byte[] hashPassword = Digests.sha1(oldPassword.getBytes(),salt, HASH_INTERATIONS);
		if(user.getPassword().equals(Encodes.encodeHex(hashPassword))){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 修改密码
	 * @param user
	 */
	@Transactional(readOnly=false)
	public void updatePwd(User user) {
		entryptPassword(user);
		userMapper.updatePwd(user);
	}
}
