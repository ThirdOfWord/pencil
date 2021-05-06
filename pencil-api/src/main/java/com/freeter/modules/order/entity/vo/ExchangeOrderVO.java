package com.freeter.modules.order.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 兑换订单
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-20 16:06:26
 */
@ApiModel(value = "ExchangeOrderVO")
public class ExchangeOrderVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 					
	/**
	 * 所属用户
	 */
	
	@ApiModelProperty(value = "所属用户")
	private Integer memberId;
		
	/**
	 * 用户名
	 */
	
	@ApiModelProperty(value = "用户名") 
	private String nickname;
		
	/**
	 * 手机号
	 */
	
	@ApiModelProperty(value = "手机号") 
	private String mobile;
		
	/**
	 * 所属地址
	 */
	
	@ApiModelProperty(value = "所属地址") 
	private Integer memberAddressId;
		
	/**
	 * 省
	 */
	
	@ApiModelProperty(value = "省") 
	private String province;
		
	/**
	 * 市
	 */
	
	@ApiModelProperty(value = "市") 
	private String city;
		
	/**
	 * 区
	 */
	
	@ApiModelProperty(value = "区") 
	private String district;
		
	/**
	 * 详细地址
	 */
	
	@ApiModelProperty(value = "详细地址") 
	private String address;
		
	/**
	 * 订单状态,0取消,1待支付,2待发货,3待收货,4已完成
	 */
	
	@ApiModelProperty(value = "订单状态,0取消,1待支付,2待发货,3待收货,4已完成") 
	private Integer status;
		
	/**
	 * 所属卡片
	 */
	
	@ApiModelProperty(value = "所属卡片") 
	private Integer answerClassifyId;
		
	/**
	 * 卡片标题
	 */
	
	@ApiModelProperty(value = "卡片标题") 
	private String answerClassifyTitle;
		
	/**
	 * 发货,0否,1是
	 */
	
	@ApiModelProperty(value = "发货,0否,1是") 
	private Integer isShipping;
		
	/**
	 * 物流code
	 */
	
	@ApiModelProperty(value = "物流code") 
	private String shippingCode;
		
	/**
	 * 物流名称
	 */
	
	@ApiModelProperty(value = "物流名称") 
	private String shippingName;
								
	
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
	 * 设置：用户名
	 */
	 
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * 获取：用户名
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
	 * 设置：所属地址
	 */
	 
	public void setMemberAddressId(Integer memberAddressId) {
		this.memberAddressId = memberAddressId;
	}
	
	/**
	 * 获取：所属地址
	 */
	public Integer getMemberAddressId() {
		return memberAddressId;
	}
				
	
	/**
	 * 设置：省
	 */
	 
	public void setProvince(String province) {
		this.province = province;
	}
	
	/**
	 * 获取：省
	 */
	public String getProvince() {
		return province;
	}
				
	
	/**
	 * 设置：市
	 */
	 
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * 获取：市
	 */
	public String getCity() {
		return city;
	}
				
	
	/**
	 * 设置：区
	 */
	 
	public void setDistrict(String district) {
		this.district = district;
	}
	
	/**
	 * 获取：区
	 */
	public String getDistrict() {
		return district;
	}
				
	
	/**
	 * 设置：详细地址
	 */
	 
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 获取：详细地址
	 */
	public String getAddress() {
		return address;
	}
				
	
	/**
	 * 设置：订单状态,0取消,1待支付,2待发货,3待收货,4已完成
	 */
	 
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * 获取：订单状态,0取消,1待支付,2待发货,3待收货,4已完成
	 */
	public Integer getStatus() {
		return status;
	}
				
	
	/**
	 * 设置：所属卡片
	 */
	 
	public void setAnswerClassifyId(Integer answerClassifyId) {
		this.answerClassifyId = answerClassifyId;
	}
	
	/**
	 * 获取：所属卡片
	 */
	public Integer getAnswerClassifyId() {
		return answerClassifyId;
	}
				
	
	/**
	 * 设置：卡片标题
	 */
	 
	public void setAnswerClassifyTitle(String answerClassifyTitle) {
		this.answerClassifyTitle = answerClassifyTitle;
	}
	
	/**
	 * 获取：卡片标题
	 */
	public String getAnswerClassifyTitle() {
		return answerClassifyTitle;
	}
				
	
	/**
	 * 设置：发货,0否,1是
	 */
	 
	public void setIsShipping(Integer isShipping) {
		this.isShipping = isShipping;
	}
	
	/**
	 * 获取：发货,0否,1是
	 */
	public Integer getIsShipping() {
		return isShipping;
	}
				
	
	/**
	 * 设置：物流code
	 */
	 
	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}
	
	/**
	 * 获取：物流code
	 */
	public String getShippingCode() {
		return shippingCode;
	}
				
	
	/**
	 * 设置：物流名称
	 */
	 
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}
	
	/**
	 * 获取：物流名称
	 */
	public String getShippingName() {
		return shippingName;
	}
			
}
