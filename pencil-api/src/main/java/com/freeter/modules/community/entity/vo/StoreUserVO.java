package com.freeter.modules.community.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 社区商家
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-14 17:14:38
 */
@ApiModel(value = "StoreUserVO")
public class StoreUserVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 					
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
