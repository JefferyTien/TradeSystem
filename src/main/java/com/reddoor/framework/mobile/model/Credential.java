package com.reddoor.framework.mobile.model;

public class Credential {
	private Integer userId;
	private String phone;
	private Integer deviceId;//设备编号
	private String token;
	
	public Credential(Integer userId, String phone, Integer deviceId, String token){
		this.userId = userId;
		this.phone = phone;
		this.deviceId = deviceId;
		this.token = token;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public String getPhone() {
		return phone;
	}
	public String getToken() {
		return token;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
}
