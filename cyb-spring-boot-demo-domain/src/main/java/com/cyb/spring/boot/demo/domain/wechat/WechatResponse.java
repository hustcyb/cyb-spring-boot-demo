package com.cyb.spring.boot.demo.domain.wechat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * 微信响应数据
 * 
 * @author Administrator
 *
 * @param <T>
 *            响应数据类型
 */
public class WechatResponse<T> {

	/**
	 * 错误码
	 */
	@JsonProperty("errcode")
	private Integer errorCode;

	/**
	 * 描述
	 */
	@JsonProperty("errmsg")
	private String errorMessage;

	/**
	 * 响应数据
	 */
	@JsonUnwrapped
	private T data;

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	/**
	 * 请求是否成功
	 * 
	 * @return true - 请示成功; false - 请求失败
	 */
	public boolean success() {
		return this.errorCode == null || this.errorCode == 0;
	}
}
