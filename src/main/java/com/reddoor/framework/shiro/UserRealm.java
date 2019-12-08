package com.reddoor.framework.shiro;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reddoor.framework.common.Constants;
import com.reddoor.framework.domain.Permission;
import com.reddoor.framework.domain.User;
import com.reddoor.framework.domain.UserRole;
import com.reddoor.framework.service.PermissionService;
import com.reddoor.framework.service.UserService;
import com.reddoor.framework.utils.security.Encodes;

@Service
public class UserRealm extends AuthorizingRealm{

	@Autowired
	private UserService userService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		User user = userService.getUser(shiroUser.getLoginName());
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//赋予角色
		for(UserRole userRole:user.getUserRoles()){
			info.addRole(userRole.getRole().getName());
		}
		//赋予权限
		for(Permission permission:permissionService.getPermissions(user.getId())){
			if(StringUtils.isNotBlank(permission.getPermCode()))
			info.addStringPermission(permission.getPermCode());
		}
		
		//设置登录次数、时间
		userService.updateUserLogin(user);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
		String username = (String)token.getPrincipal();
		User user = userService.getUser(username);
		if(null != user){
			byte[] salt = Encodes.decodeHex(user.getSalt());
			ShiroUser shiroUser=new ShiroUser(user.getId(), user.getLoginName(), user.getName());
			Session session =SecurityUtils.getSubject().getSession();
			session.setAttribute("user", user);
			return new SimpleAuthenticationInfo(shiroUser,user.getPassword(), ByteSource.Util.bytes(salt), getName());
		}
		return null;
	}

	@PostConstruct
	public void initCredentialsMatcher(){
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
		matcher.setHashAlgorithmName(Constants.HASH_ALGORITHM);
		matcher.setHashIterations(Constants.HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}
	
	@Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
