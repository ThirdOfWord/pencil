package com.freeter.modules.order.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;



/**
 * 兑换订单
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-20 16:06:26
 */
@TableName("cd_exchange_order")
@ApiModel(value = "ExchangeOrder")
public class ExchangeOrderEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ExchangeOrderEntity() {
		
	}
	
	public ExchangeOrderEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	
	@TableId 					
	@ApiModelProperty(value = "",hidden = true)
	private Integer exchangeOrderId;
	
	/**
	 * 创建时间
	 */
							
	@TableField(fill = FieldFill.INSERT) 
	@ApiModelProperty(value = "创建时间",hidden = true)
	private Integer createTime;
	
	/**
	 * 更新时间
	 */
						
	@TableField(fill = FieldFill.UPDATE) 	
	@ApiModelProperty(value = "更新时间",hidden = true)
	private Integer updateTime;
	
	/**
	 * 所属用户
	 */
			
	@NotNull (message = "所属用户不能为空") 				
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
			
	@NotNull (message = "所属地址不能为空") 				
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
			
	@NotNull (message = "订单状态,0取消,1待支付,2待发货,3待收货,4已完成不能为空") 				
	@ApiModelProperty(value = "订单状态,0取消,1待支付,2待发货,3待收货,4已完成")
	private Integer status;
	
	/**
	 * 所属卡片
	 */
			
	@NotNull (message = "所属卡片不能为空") 				
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
			
	@NotNull (message = "发货,0否,1是不能为空") 				
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
	 * 设置：
	 */
	public void setExchangeOrderId(Integer exchangeOrderId) {
		this.exchangeOrderId = exchangeOrderId;
	}
	/**
	 * 获取：
	 */
	public Integer getExchangeOrderId() {
		return exchangeOrderId;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Integer getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Integer updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Integer getUpdateTime() {
		return updateTime;
	}
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
