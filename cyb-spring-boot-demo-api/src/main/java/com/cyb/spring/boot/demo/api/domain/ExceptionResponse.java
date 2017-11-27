package com.cyb.spring.boot.demo.api.domain;

/**
 * 异常响应
 * 
 * @author Administrator
 *
 */
public class ExceptionResponse {

	/**
	 * 状态码
	 */
	private int status;

	/**
	 * 异常信息
	 */
	private String message;

	/**
	 * 初始化异常响应
	 * 
	 * @param status
	 *            状态码
	 * @param message
	 *            异常信息
	 */
	public ExceptionResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
}
