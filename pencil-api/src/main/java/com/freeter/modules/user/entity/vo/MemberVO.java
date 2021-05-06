package com.freeter.modules.user.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.io.Serializable;
 

/**
 * 用户表
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-07 16:14:11
 */
@ApiModel(value = "MemberVO")
public class MemberVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 手机号
	 */
	
	@ApiModelProperty(value = "手机号") 
	private String mobile;
		
	/**
	 * 昵称
	 */
	
	@ApiModelProperty(value = "昵称") 
	private String nickname;
		
	/**
	 * 性别
	 */
	
	@ApiModelProperty(value = "性别") 
	private Integer six;
		
	/**
	 * 邀请码
	 */
	
	@ApiModelProperty(value = "邀请码") 
	private String inviteCode;
				
	/**
	 * 余额
	 */
	
	@ApiModelProperty(value = "余额") 
	private BigDecimal wallet;
		
	/**
	 * 删除
	 */
	
	@ApiModelProperty(value = "删除") 
	private Integer isDel;
		
	/**
	 * 启用
	 */
	
	@ApiModelProperty(value = "启用") 
	private Integer status;
				
	
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
	 * 设置：性别
	 */
	 
	public void setSix(Integer six) {
		this.six = six;
	}
	
	/**
	 * 获取：性别
	 */
	public Integer getSix() {
		return six;
	}
				
	
	/**
	 * 设置：邀请码
	 */
	 
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	
	/**
	 * 获取：邀请码
	 */
	public String getInviteCode() {
		return inviteCode;
	}
								
	
	/**
	 * 设置：余额
	 */
	 
	public void setWallet(BigDecimal wallet) {
		this.wallet = wallet;
	}
	
	/**
	 * 获取：余额
	 */
	public BigDecimal getWallet() {
		return wallet;
	}
				
	
	/**
	 * 设置：删除
	 */
	 
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	
	/**
	 * 获取：删除
	 */
	public Integer getIsDel() {
		return isDel;
	}
				
	
	/**
	 * 设置：启用
	 */
	 
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * 获取：启用
	 */
	public Integer getStatus() {
		return status;
	}
			
}
