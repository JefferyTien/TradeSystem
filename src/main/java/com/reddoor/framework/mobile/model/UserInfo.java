package com.reddoor.framework.mobile.model;

import java.util.ArrayList;
import java.util.List;

import com.reddoor.framework.domain.Dict;
import com.reddoor.framework.domain.Permission;

public class UserInfo extends BaseModel{
	
	private String name;
	
	private String loginName;
	
	// 权限列表
	private List<Permission> permissionList = new ArrayList<Permission>();
	// 字典
	private List<Dict> codeList = new ArrayList<Dict>();


	public String getName() {
		return name;
	}

	public String getLoginName() {
		return loginName;
	}

	public List<Permission> getPermissionList() {
		return permissionList;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}

	public List<Dict> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<Dict> codeList) {
		this.codeList = codeList;
	}
}
