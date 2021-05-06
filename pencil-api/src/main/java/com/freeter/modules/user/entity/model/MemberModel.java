package com.freeter.modules.user.entity.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "MemberModel")
public class MemberModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	public MemberModel(String nickname, String headimgurl, Integer sponsor) {
		this.nickname = nickname;
		this.headimgurl = headimgurl;
		this.sponsor = sponsor;
	}

	public MemberModel() {
		super();
	}

	/**
	 * 昵称
	 */
	@ApiModelProperty(value = "昵称")
	private String nickname;
	
	/**
	 * 头像
	 */
	@ApiModelProperty(value = "头像") 
	private String headimgurl;
	@ApiModelProperty(value = "发起人:0:成员，1：发起人")
	private Integer sponsor;

	public Integer getSponsor() {
		return sponsor;
	}

	public void setSponsor(Integer sponsor) {
		this.sponsor = sponsor;
	}

	/**
	 * 设置：昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * 获取：昵称
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 设置：头像
	 */
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	
	/**
	 * 获取：头像
	 */
	public String getHeadimgurl() {
		return headimgurl;
	}
 		
}
