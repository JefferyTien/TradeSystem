package com.reddoor.tradesystem.domain.authority;

public class LoginUserInfo {
	private String id;

    private String username;
    
    public static LoginUserInfo build(SysUser sysUser){
    	LoginUserInfo userInfo = new LoginUserInfo();
    	userInfo.id = sysUser.getId();
    	userInfo.username = sysUser.getUsername();
    	return userInfo;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    
    
}
