package com.cyb.spring.boot.demo.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 微信属性配置
 * 
 * @author Administrator
 *
 */
@ConfigurationProperties(prefix = "cyb.wechat")
public class WechatProperties {

	/**
	 * 应用唯一凭证
	 */
	private String appId;

	/**
	 * 应用唯一凭证密码
	 */
	private String appSecret;

	/**
	 * 微信接口调用凭证缓存键名称
	 */
	private String accessTokenCacheKey = "wechat.access-token";

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getAccessTokenCacheKey() {
		return accessTokenCacheKey;
	}

	public void setAccessTokenCacheKey(String accessTokenCacheKey) {
		this.accessTokenCacheKey = accessTokenCacheKey;
	}
}
