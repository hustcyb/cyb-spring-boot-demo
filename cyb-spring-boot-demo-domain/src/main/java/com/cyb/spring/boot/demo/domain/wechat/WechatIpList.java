package com.cyb.spring.boot.demo.domain.wechat;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 微信服务器IP地址列表
 * 
 * @author Administrator
 *
 */
public class WechatIpList {

	/**
	 * 服务器IP地址列表
	 */
	@JsonProperty("ip_list")
	private List<String> ipList = new ArrayList<String>();

	public List<String> getIpList() {
		return ipList;
	}
}
