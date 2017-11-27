package com.cyb.spring.boot.demo.core.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cyb.spring.boot.demo.core.config.WechatProperties;
import com.cyb.spring.boot.demo.domain.wechat.WechatAccessToken;
import com.cyb.spring.boot.demo.domain.wechat.WechatException;
import com.cyb.spring.boot.demo.domain.wechat.WechatIpList;
import com.cyb.spring.boot.demo.domain.wechat.WechatResponse;
import com.cyb.spring.boot.demo.domain.wechat.WechatUserInfo;

import cyb.spring.boot.demo.common.json.JsonUtils;
import cyb.spring.boot.demo.common.type.ParameterizedTypeImpl;

/**
 * 微信服务
 * 
 * @author Administrator
 * @param <T>
 *
 */
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@EnableConfigurationProperties(WechatProperties.class)
@Service
public class WechatService {

	/**
	 * 日志接口
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(WechatService.class);

	/**
	 * 微信属性配置
	 */
	@Resource
	private WechatProperties wechatProperties;

	/**
	 * Rest调用接口
	 */
	@Resource
	private RestTemplate restTemplate;

	/**
	 * Redis接口
	 */
	@Resource
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * Redis键值操作接口
	 */
	@Resource(name = "stringRedisTemplate")
	private ValueOperations<String, String> valueOperations;

	/**
	 * 获取微信接口调用凭证
	 * 
	 * @return 微信接口调用凭证
	 */
	public String getAccessToken() {
		if (logger.isDebugEnabled()) {
			logger.debug("WechatService.getAccessToken: start");
		}

		String accessTokenCacheKey = wechatProperties.getAccessTokenCacheKey();
		String accessToken = valueOperations.get(accessTokenCacheKey);
		if (accessToken == null) {
			String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appid}&secret={secret}";
			WechatAccessToken wechatAccessToken = get(url,
					WechatAccessToken.class, wechatProperties.getAppId(),
					wechatProperties.getAppSecret());

			accessToken = wechatAccessToken.getAccessToken();
			valueOperations.set(accessTokenCacheKey, accessToken,
					wechatAccessToken.getExpiresIn(), TimeUnit.SECONDS);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("WechatService.getAccessToken: end, return = {}",
					accessToken);
		}

		return accessToken;
	}

	/**
	 * 获取IP地址列表
	 * 
	 * @return IP地址列表
	 */
	public List<String> getIpList() {
		if (logger.isDebugEnabled()) {
			logger.debug("WechatService.getAccessToken: start");
		}

		String accessToken = getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token={accessToken}";
		WechatIpList wechatIpList = get(url, WechatIpList.class, accessToken);
		List<String> ipList = wechatIpList.getIpList();
		if (logger.isDebugEnabled()) {
			logger.debug("WechatService.getAccessToken: end, return = {}",
					JsonUtils.bean2Json(ipList));
		}

		return ipList;
	}

	/**
	 * 获取微信用户信息
	 * 
	 * @param openId
	 *            用户唯一标识
	 * @return 微信用户信息
	 */
	public WechatUserInfo getUserInfo(String openId) {
		if (logger.isDebugEnabled()) {
			logger.debug("WechatService.getAccessToken: start");
		}

		String accessToken = getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token={accessToken}&openid={openId}&lang=zh_CN";
		WechatUserInfo userInfo = get(url, WechatUserInfo.class, accessToken,
				openId);
		if (logger.isDebugEnabled()) {
			logger.debug("WechatService.getUserInfo: end, return = {}",
					JsonUtils.bean2Json(userInfo));
		}

		return userInfo;
	}

	/**
	 * 设置用户备注名
	 * 
	 * @param openId
	 *            微信用户唯一标识
	 * @param remark
	 *            备注
	 */
	public void saveUserRemark(String openId, String remark) {
		if (logger.isDebugEnabled()) {
			logger.debug(
					"WechatService.saveUserRemark: start, openId = {}, remark = {}",
					openId, remark);
		}

		String accessToken = getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token={accessToken}";
		WechatUserInfo userInfo = new WechatUserInfo();
		userInfo.setOpenId(openId);
		userInfo.setRemark(remark);

		HttpEntity<WechatUserInfo> entity = new HttpEntity<WechatUserInfo>(
				userInfo);
		post(url, entity, Void.class, accessToken);
		if (logger.isDebugEnabled()) {
			logger.debug("WechatService.saveUserRemark: end");
		}
	}

	/**
	 * GET方式请求微信API接口并返回响应数据
	 * 
	 * @param url
	 *            微信API接口地址
	 * @param responseType
	 *            响应数据类型
	 * @param uriVariables
	 *            地址参数
	 * @return 响应数据
	 */
	private <T> T get(String url, Class<T> responseType, Object... uriVariables) {
		return request(url, HttpMethod.GET, HttpEntity.EMPTY, responseType,
				uriVariables);
	}

	/**
	 * POST方式请求微信API接口并返回响应数据
	 * 
	 * @param url
	 *            微信API接口地址
	 * @param entity
	 *            请求数据
	 * @param responseType
	 *            响应数据类型
	 * @param uriVariables
	 *            地址参数
	 * @return
	 */
	private <T> T post(String url, HttpEntity<?> entity, Class<T> responseType,
			Object... uriVariables) {
		return request(url, HttpMethod.POST, entity, responseType, uriVariables);
	}

	/**
	 * 请求微信API接口并返回响应数据
	 * 
	 * @param url
	 *            API接口地址
	 * @param method
	 *            请求方式
	 * @param entity
	 *            请求数据
	 * @param responseType
	 *            响应数据类型
	 * @param uriVariables
	 *            地址参数
	 * @return 响应数据
	 */
	private <T> T request(String url, HttpMethod method, HttpEntity<?> entity,
			Class<T> responseType, Object... uriVariables) {
		ParameterizedTypeReference<WechatResponse<T>> typeReference = new ParameterizedTypeReference<WechatResponse<T>>() {

			@Override
			public Type getType() {
				return new ParameterizedTypeImpl(
						(ParameterizedType) super.getType(),
						new Type[] { responseType });
			}
		};
		if (logger.isDebugEnabled()) {
			logger.debug(
					"WechatService.request: request, url = {}, uriVariables = {}, method = {}, entity = {}",
					url, JsonUtils.bean2Json(uriVariables), method,
					JsonUtils.bean2Json(entity));
		}

		ResponseEntity<WechatResponse<T>> responseEntity = restTemplate
				.exchange(url, method, entity, typeReference, uriVariables);
		WechatResponse<T> wechatResponse = responseEntity.getBody();
		if (logger.isDebugEnabled()) {
			logger.debug("WechatService.request: response = {}",
					JsonUtils.bean2Json(wechatResponse));
		}

		if (!wechatResponse.success()) {
			throw new WechatException(wechatResponse.getErrorCode(),
					wechatResponse.getErrorMessage());
		}

		return wechatResponse.getData();
	}
}
