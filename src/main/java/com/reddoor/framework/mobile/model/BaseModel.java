package com.reddoor.framework.mobile.model;

public class BaseModel {
	private Credential credential;
	private String message;
	private String code;   // 错误码
	
	public Credential getCredential() {
		return credential;
	}
	public String getMessage() {
		return message;
	}
	public void setCredential(Credential credential) {
		this.credential = credential;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
