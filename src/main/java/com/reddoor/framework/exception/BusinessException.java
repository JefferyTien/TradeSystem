package com.reddoor.framework.exception;

/**
 * created on 2016年9月6日 
 *
 * 系统自定义的异常类型(实际开发中可能要定义多种异常类型)
 *
 * @author  megagao
 * @version  0.0.1
 */
public class BusinessException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private String message; //异常信息
	
	public BusinessException(String message){
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 构造带指定详细消息和原因的异常
	 * @param msg  指定详细消息
	 * @param cause 指定的原因
	 */
	public BusinessException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}
}
