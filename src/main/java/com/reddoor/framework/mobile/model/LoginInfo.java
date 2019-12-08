package com.reddoor.framework.mobile.model;

public class LoginInfo {
    private String loginName;
    private String password;
    private Integer deviceId;
    private String deviceName;
    private String token;
    private String verificationCode;
    
	public String getLoginName() {
		return loginName;
	}
	public String getPassword() {
		return password;
	}
	public Integer getDeviceId() {
		return deviceId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public String getToken() {
		return token;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
}
