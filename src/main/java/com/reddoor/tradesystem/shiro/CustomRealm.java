package com.reddoor.tradesystem.shiro;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reddoor.tradesystem.domain.authority.SysPermission;
import com.reddoor.tradesystem.domain.authority.SysUser;
import com.reddoor.tradesystem.domain.customize.ActiveUser;
import com.reddoor.tradesystem.domain.vo.RoleVO;
import com.reddoor.tradesystem.service.SysRoleService;
import com.reddoor.tradesystem.service.SysService;

@Service("customRealm")
public class CustomRealm extends AuthorizingRealm{

	@Autowired
	private SysService sysService;
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Override
	public void setName(String name) {
		super.setName("customRealm");
	}
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String)token.getPrincipal();
		
		SysUser sysUser = null;
		try{
			sysUser = sysService.getSysUserByName(username);
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		if(null == sysUser){
			return null;
		}
		String password = sysUser.getPassword();
		ActiveUser activeUser = new ActiveUser();
		activeUser.setUserId(sysUser.getId());
		activeUser.setUserName(sysUser.getUsername());
		activeUser.setUserStatus(sysUser.getLocked());
		
		RoleVO sysRole = null;
		try{
			sysRole = sysRoleService.findRoleByUserId(sysUser.getId());
		}
		catch (Exception e){
			
		}
		activeUser.setRoleName(sysRole.getRoleName());
		activeUser.setRoleStatus(sysRole.getAvailable());
		
		List<SysPermission> menus = null;
		try{
			menus = sysService.findMenuListByUserId(sysUser.getId());
		}
		catch (Exception e){
			
		}
		activeUser.setMenus(menus);
		
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(activeUser, password, this.getName());
		
		return simpleAuthenticationInfo;
	}

	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
		//从 principals获取主身份信息
		//将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型）
		ActiveUser activeUser = (ActiveUser)principals.getPrimaryPrincipal();
		
		List<SysPermission> permissionList = null;
		try {
			permissionList = sysService.findPermissionListByUserId(activeUser.getUserId());
		}
		catch (Exception e){
			
		}
		
		List<String> permissions = new ArrayList<String>();
		if(null != permissionList){
			for(SysPermission sysPermission:permissionList){
				permissions.add(sysPermission.getPercode());
			}
		}
		
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addStringPermissions(permissions);
		
		return simpleAuthorizationInfo;
	}
	
	public void clearCached(){
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}
	
	@PostConstruct
	public void initCredentialsMatcher(){
		SimpleCredentialsMatcher matcher = new SimpleCredentialsMatcher();
		setCredentialsMatcher(matcher);
	}
	
}
