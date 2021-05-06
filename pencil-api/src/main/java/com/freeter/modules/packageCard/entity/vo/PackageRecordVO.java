package com.freeter.modules.packageCard.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 卡包领取记录
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-04-27 18:29:40
 */
@ApiModel(value = "PackageRecordVO")
public class PackageRecordVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 				
	/**
	 * 所属用户
	 */
	
	@ApiModelProperty(value = "所属用户")
	private Integer memberId;
		
	/**
	 * 昵称
	 */
	
	@ApiModelProperty(value = "昵称") 
	private String nickname;
		
	/**
	 * 手机号
	 */
	
	@ApiModelProperty(value = "手机号") 
	private String mobile;
		
	/**
	 * 所属卡包
	 */
	
	@ApiModelProperty(value = "所属卡包") 
	private Integer packageId;
		
	/**
	 * 卡包
	 */
	
	@ApiModelProperty(value = "卡包") 
	private String packageTitle;
		
	/**
	 * 数量
	 */
	
	@ApiModelProperty(value = "数量") 
	private Integer num;
						
	
	/**
	 * 设置：所属用户
	 */
	 
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	
	/**
	 * 获取：所属用户
	 */
	public Integer getMemberId() {
		return memberId;
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
	 * 设置：手机号
	 */
	 
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * 获取：手机号
	 */
	public String getMobile() {
		return mobile;
	}
				
	
	/**
	 * 设置：所属卡包
	 */
	 
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}
	
	/**
	 * 获取：所属卡包
	 */
	public Integer getPackageId() {
		return packageId;
	}
				
	
	/**
	 * 设置：卡包
	 */
	 
	public void setPackageTitle(String packageTitle) {
		this.packageTitle = packageTitle;
	}
	
	/**
	 * 获取：卡包
	 */
	public String getPackageTitle() {
		return packageTitle;
	}
				
	
	/**
	 * 设置：数量
	 */
	 
	public void setNum(Integer num) {
		this.num = num;
	}
	
	/**
	 * 获取：数量
	 */
	public Integer getNum() {
		return num;
	}
			
}
