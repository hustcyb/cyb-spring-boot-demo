package com.cyb.spring.boot.demo.domain.wechat;

/**
 * 微信异常
 * 
 * @author Administrator
 *
 */
public class WechatException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 异常代码
	 */
	private int errorCode;

	/**
	 * 初始化微信异常
	 * 
	 * @param errorCode
	 *            异常代码
	 * @param message
	 *            异常信息
	 */
	public WechatException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * 初始化微信异常
	 * 
	 * @param errorCode
	 *            异常代码
	 * @param message
	 *            异常信息
	 * @param cause
	 *            失败原因
	 */
	public WechatException(int errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}
}
