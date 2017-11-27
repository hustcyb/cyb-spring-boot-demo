package com.cyb.spring.boot.demo.domain.wechat;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 微信接口调用凭证
 * 
 * @author Administrator
 *
 */
public class WechatAccessToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4284590968973502813L;

	/**
	 * 凭证
	 */
	@JsonProperty("access_token")
	private String accessToken;

	/**
	 * 凭证有效时间，单位秒
	 */
	@JsonProperty("expires_in")
	private Integer expiresIn;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}
}
