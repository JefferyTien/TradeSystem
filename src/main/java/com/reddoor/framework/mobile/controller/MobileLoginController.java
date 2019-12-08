package com.reddoor.framework.mobile.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reddoor.framework.common.Constants;
import com.reddoor.framework.domain.User;
import com.reddoor.framework.domain.UserToken;
import com.reddoor.framework.mobile.model.Credential;
import com.reddoor.framework.mobile.model.LoginInfo;
import com.reddoor.framework.mobile.model.UserInfo;
import com.reddoor.framework.service.UserService;
import com.reddoor.framework.service.UserTokenService;
import com.reddoor.framework.utils.JwtUtil;

@RestController
@RequestMapping("rest/mobile")
public class MobileLoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserTokenService userTokenService;
	
	/**
	 * 自动登录
	 */
	@RequestMapping(value="autoLogin")
	public UserInfo autologin(@RequestBody LoginInfo loginInfo, HttpServletRequest request) {
		UserInfo userInfo = new UserInfo();
		String token = loginInfo.getToken();
		if(null == token || "".equals(token)){
			userInfo.setMessage("token无效!");
			return userInfo;
		}
		
		// 验证登录用户
		String loginName = loginInfo.getLoginName();
		User user = userService.getUser(loginName);
		if(null == user){
			user = userService.getUserByPhone(loginName);
			if(null == user){
				userInfo.setMessage("用户不存在!");
				return userInfo;
			}
		}
		
		// 验证 token/密码
		Integer deviceId = loginInfo.getDeviceId(); 
		UserToken userToken = userTokenService.findUserToken(user.getId(), deviceId);
		if(null == userToken || !token.equals(userToken.getToken())){
			userInfo.setMessage("token无效!");
			return userInfo;
		}
		else{
			JwtUtil jwtUtil = new JwtUtil();
			// 校验是否token是否过期, 过期则重新登录
			if(!jwtUtil.isValid(token)){
				userInfo.setMessage("token无效!");
				return userInfo;
			}
			Credential credential = new Credential(user.getId(), user.getPhone(),loginInfo.getDeviceId(),token);
			userInfo.setCredential(credential);
			userInfo.setLoginName(user.getLoginName());
			userInfo.setName(user.getName());
		}
		
		return userInfo;
	}
	
	@RequestMapping(value="login")
	public UserInfo login(@RequestBody LoginInfo loginInfo, HttpServletRequest request) {
		UserInfo userInfo = new UserInfo();
		
		// 验证登录用户
		String loginName = loginInfo.getLoginName();
		User user = userService.getUser(loginName);
		if(null == user){
			user = userService.getUserByPhone(loginName);
			if(null == user){
				userInfo.setMessage("用户不存在!");
				return userInfo;
			}
		}
				
		String password = loginInfo.getPassword();
		if(null == password || "".equals(password)){
			userInfo.setMessage("请输入密码!");
			return userInfo;
		}
		Integer deviceId = loginInfo.getDeviceId(); 
		// 验证登录用户名/手机号 ,密码/验证码
		boolean pass = userService.checkPassword(user, password);
		if(!pass){
			userInfo.setMessage("密码错误!");
			return userInfo;
		}
		else{
			UserToken userToken = userTokenService.findUserToken(user.getId(), deviceId);
			String jwt;
			if(null == userToken){
				// 用户名,密码验证通过后, 生成token入库并回传client
				JwtUtil jwtUtil = new JwtUtil();
				String subject = "{username:'"+user.getLoginName()+"'}";
				try {
					jwt = jwtUtil.createJWT(Constants.JWT_ID, Constants.JWT_ISSUER, subject, Constants.JWT_TTL);
				}
				catch (Exception e) {
					e.printStackTrace();
					userInfo.setMessage("token生成失败!");
					return userInfo;
				}
				
				userToken = new UserToken();
				userToken.setUserId(user.getId());
				userToken.setDeviceId(loginInfo.getDeviceId());
				userToken.setToken(jwt);
				userToken.setCreateDate(new Date());
				userTokenService.add(userToken);
			}
			else{
				JwtUtil jwtUtil = new JwtUtil();
				// 校验是否token是否过期, 过期则重新登录
				if(jwtUtil.isValid(userToken.getToken())){
					jwt = userToken.getToken();
				}
				else{
					String subject = "{username:'"+user.getLoginName()+"'}";
					try {
						jwt = jwtUtil.createJWT(Constants.JWT_ID, Constants.JWT_ISSUER, subject, Constants.JWT_TTL);
					}
					catch (Exception e) {
						e.printStackTrace();
						userInfo.setMessage("token生成失败!");
						return userInfo;
					}
				}
				
				userToken.setToken(jwt);
				userToken.setCreateDate(new Date());
				userTokenService.updateByPrimaryKey(userToken);
			}
			
			Credential credential = new Credential(user.getId(), user.getPhone(),loginInfo.getDeviceId(),jwt);
			userInfo.setCredential(credential);
			userInfo.setLoginName(user.getLoginName());
			userInfo.setName(user.getName());
		}
		
		return userInfo;
	}
}
