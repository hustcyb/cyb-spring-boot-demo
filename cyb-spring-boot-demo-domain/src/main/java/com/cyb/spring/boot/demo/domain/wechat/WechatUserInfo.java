package com.cyb.spring.boot.demo.domain.wechat;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import cyb.spring.boot.demo.common.json.UnixTimestampDeserializer;

/**
 * 微信用户信息
 * 
 * @author Administrator
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class WechatUserInfo {

	/**
	 * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，摘取不到其余信息
	 */
	private byte subscribe;
	
	/**
	 * 用户标识，对当前公众号唯一
	 */
	@JsonProperty("openid")
	private String openId;
	
	/**
	 * 用户昵称
	 */
	private String nickname;
	
	/**
	 * 用户的性别，0 - 未知，1 - 男， 2 - 女
	 */
	private byte sex;
	
	/**
	 * 用户的语言，简体中文为zh_CN
	 */
	private String language;
	
	/**
	 * 用户所在城市
	 */
	private String city;
	
	/**
	 * 用户所在省份
	 */
	private String province;
	
	/**
	 * 用户所在国家
	 */
	private String country;
	
	/**
	 * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
	 */
	@JsonProperty("headimgurl")
	private String headImgUrl;
	
	/**
	 * 用户关注时间，为时间戳，如果用户曾多次关注，则取最后关注时间
	 */
	@JsonDeserialize(using = UnixTimestampDeserializer.class)
	@JsonProperty("subscribe_time")
	private Date subscribeTime;
	
	/**
	 * 只有在用户将公众号绑定到微信开放平台账号时，才会出现该字段
	 */
	@JsonProperty("unionid")
	private String unionId;
	
	/**
	 * 	公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
	 */
	private String remark;
	
	/**
	 * 用户所在分组ID
	 */
	private String groupId;
	
	/**
	 * 用户被打上的标签ID列表
	 */
	private int[] tagIdList;

	public Byte getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Byte subscribe) {
		this.subscribe = subscribe;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public byte getSex() {
		return sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public Date getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(Date subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public int[] getTagIdList() {
		return tagIdList;
	}

	public void setTagIdList(int[] tagIdList) {
		this.tagIdList = tagIdList;
	}
}
