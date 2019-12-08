package com.reddoor.framework.shiro;

import java.util.List;

import com.reddoor.framework.domain.Permission;

public class ShiroUser implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String loginName;
	private String name;
	private List<Permission> menus;// 菜单
	private List<Permission> permissions;// 权限
	
	public ShiroUser(Integer id, String loginName, String name) {
		this.id = id;
		this.loginName = loginName;
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	public String getLoginName() {
		return loginName;
	}
	public String getName() {
		return name;
	}
	public List<Permission> getMenus() {
		return menus;
	}
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMenus(List<Permission> menus) {
		this.menus = menus;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
}
