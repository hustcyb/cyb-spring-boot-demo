package com.cyb.spring.boot.demo.api.domain;

public enum ErrorCodeEnum {
	UNKNOWN(-1, "示知异常"), VALIDATION(1001, "数据验证异常"), WECHAT(1002, "微信请求异常");

	/**
	 * 错误代码
	 */
	private int code;

	/**
	 * 错误信息
	 */
	private String message;

	private ErrorCodeEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
