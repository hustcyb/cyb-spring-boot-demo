package com.cyb.spring.boot.demo.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyb.spring.boot.demo.core.service.WechatService;
import com.cyb.spring.boot.demo.domain.wechat.WechatUserInfo;

import cyb.spring.boot.demo.common.json.JsonUtils;

/**
 * 微信控制器
 * 
 * @author Administrator
 *
 */
@RequestMapping("wechat")
@RestController
public class WechatController {

	/**
	 * 日志接口
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(WechatController.class);

	/**
	 * 微信服务
	 */
	private final WechatService wechatService;

	@Autowired
	public WechatController(WechatService wechatService) {
		this.wechatService = wechatService;
	}

	/**
	 * 获取微信接口调用凭证
	 * 
	 * @return 微信接口调用凭证
	 */
	@GetMapping("access-token")
	public String getAccessToken() {
		if (logger.isDebugEnabled()) {
			logger.debug("WechatController.getAccessToken: start");
		}

		String accessToken = wechatService.getAccessToken();
		if (logger.isDebugEnabled()) {
			logger.debug("WechatController.getAccessToken: end, return = {}",
					accessToken);
		}

		return accessToken;
	}

	/**
	 * 获取微信服务器IP地址列表
	 * 
	 * @return 微信服务器IP地址列表
	 */
	@GetMapping("ip-list")
	public List<String> getIpList() {
		if (logger.isDebugEnabled()) {
			logger.debug("WechatController.getIpList: start");
		}

		List<String> ipList = wechatService.getIpList();
		if (logger.isDebugEnabled()) {
			logger.debug("WechatController.getIpList: end, return = {}",
					JsonUtils.bean2Json(ipList));
		}

		return ipList;
	}

	/**
	 * 微信用户信息
	 * 
	 * @param openId
	 *            用户唯一标识
	 * @return 微信用户信息
	 */
	@GetMapping("users/{openId}")
	public WechatUserInfo getUserInfo(@PathVariable String openId) {
		if (logger.isDebugEnabled()) {
			logger.debug("WechatController.getUserInfo: start, openId = {}",
					openId);
		}

		WechatUserInfo userInfo = wechatService.getUserInfo(openId);
		if (logger.isDebugEnabled()) {
			logger.debug("WechatController.getUserInfo: end, return = {}",
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
	 *            备注名
	 */
	@PutMapping("users/{openId}")
	public void saveUserRemark(@PathVariable String openId,
			@RequestBody String remark) {
		if (logger.isDebugEnabled()) {
			logger.debug(
					"WechatController.saveUserRemark: start, openId = {}, remark = {}",
					openId, remark);
		}

		wechatService.saveUserRemark(openId, remark);
		if (logger.isDebugEnabled()) {
			logger.debug("WechatController.saveUserRemark: end");
		}
	}
}
