package com.reddoor.tradesystem.controller;

import static com.reddoor.framework.common.Constants.VALIDATE_CODE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.reddoor.framework.domain.Permission;
import com.reddoor.framework.service.PermissionService;
import com.reddoor.tradesystem.domain.authority.LoginUserInfo;
import com.reddoor.tradesystem.domain.authority.SysPermission;
import com.reddoor.tradesystem.domain.authority.SysUser;
import com.reddoor.tradesystem.service.SysService;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private SysService sysService;
	
	@Autowired
	private PermissionService permissionService;
	
	@RequestMapping(value = "/ajaxLogin.do")
	@ResponseBody
	public Map<String, Object> ajaxLogin(@RequestParam String username,
			@RequestParam String password, @RequestParam(required=false) String randomcode, @RequestParam(required=false) String rememberme,  
			HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(null != randomcode && !"".equals(randomcode)){
			String validateCode = (String)session.getAttribute(VALIDATE_CODE);
			if(null != validateCode && !validateCode.equals(randomcode)){
				map.put("msg", "randomcode_error");
				return map;
			}
		}
		
//		this.testMybatis();
		
		Subject currentUser = SecurityUtils.getSubject();
		if(!currentUser.isAuthenticated()){
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//			if("on".equals(rememberme)){
//				token.setRememberMe(true);
//			}
			try{
	            currentUser.login(token);
	        }catch(UnknownAccountException ex){
	        	map.put("msg", "account_error");
	        }catch(IncorrectCredentialsException ex){
	        	map.put("msg", "password_error");
	        }catch(AuthenticationException ex){
	        	map.put("msg", "authentication_error");
	        	ex.printStackTrace();
	        }
		}
		
		// 若认证成功, 原生的session中也放一份 menus 和 permissions
		if(currentUser.isAuthenticated()){
			fillSession(session, username);
		}
		
		return map;
	}
	
	@RequestMapping(value = "/load.do")
	@ResponseBody
	public Map<String, Object> load(HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
	
	private void fillSession(HttpSession session, String username){
		
//		SysUser sysUser = null;
//		try{
//			sysUser = sysService.getSysUserByName(username);
//		}
//		catch(Exception e){
//			System.out.println(e);
//		}
//		
//		List<SysPermission> menus = null;
//		try{
//			menus = sysService.findMenuListByUserId(sysUser.getId());
//		}
//		catch (Exception e){
//			
//		}
//		
//		List<SysPermission> permissionList = null;
//		try {
//			permissionList = sysService.findPermissionListByUserId(sysUser.getId());
//		}
//		catch (Exception e){
//			
//		}
//		
//		List<String> permissions = new ArrayList<String>();
//		if(null != permissionList){
//			for(SysPermission sysPermission:permissionList){
//				permissions.add(sysPermission.getPercode());
//			}
//		}
		
		// fill in session
//		if(null != sysUser){
//			LoginUserInfo userInfo = LoginUserInfo.build(sysUser);
//			session.setAttribute("user", userInfo);
//		}
//		
//		if(null != menus){
//			session.setAttribute("menus", menus);
//		}
		
//		if(null != permissionList){
//			session.setAttribute("permissions", permissions);
//		}
		
		// framwork service
		List<Permission> permissionsList = permissionService.getPermissions(1);
		List<String> permStrList = new ArrayList<String>();
		for(Permission eachPerm : permissionsList){
			permStrList.add(eachPerm.getPermCode());
		}
		
		List<Permission> menusList = permissionService.getMenus(1);
		if(null != menusList){
			List<Permission> parentList = new ArrayList<Permission>();
			Map<Integer, List<Permission>> menusMap = new HashMap<Integer, List<Permission>>();
			for(Permission eachPerm:menusList){
				// root menu
				if(eachPerm.getPid() == null){
					parentList.add(eachPerm);
				}
				else{
					List<Permission> childrenList = menusMap.get(eachPerm.getPid());
					if(null == childrenList){
						childrenList = new ArrayList<Permission>();
						menusMap.put(eachPerm.getPid(), childrenList);
					}
					childrenList.add(eachPerm);
				}
			}
			session.setAttribute("parentMenus", parentList);
			session.setAttribute("menuMap", menusMap);
			session.setAttribute("permissions", permStrList);
		}
		
	}
	
	
	private void testMybatis(){
		SysUser sysUser = null;
		try{
			sysUser = sysService.getSysUserByName("22");
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		
		List<SysPermission> permissionList = null;
		try {
			permissionList = sysService.findPermissionListByUserId(sysUser.getId());
		}
		catch (Exception e){
			
		}
	}
	
	@RequestMapping(value="logout.do")
	public String logout(Model model) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "login";
	}
	
}
