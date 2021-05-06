package com.freeter.modules.taskOrder.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;



/**
 * 推广商城订单
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-04-19 22:37:31
 */
@TableName("cd_goods_order")
@ApiModel(value = "GoodsOrder")
public class GoodsOrderEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	public GoodsOrderEntity() {
		
	}
	public GoodsOrderEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@TableId 					
	@ApiModelProperty(value = "",hidden = true)
	private Integer goodsOrderId;
	/**
	 * 类型
	 */
	@ApiModelProperty(value = "所属供应商")
	private Integer supplierId;
	/**
	 * 下单时间
	 */
	@ApiModelProperty(value = "下单时间")
	private Integer ordertime;
	/**
	 * 所属用户
	 */
	@ApiModelProperty(value = "所属用户")
	private Integer memberId;
	/**
	 * 订单ID
	 */
	@ApiModelProperty(value = "订单ID")
	private String orderid;
	
	/**
	 * 佣金比例
	 */
	@ApiModelProperty(value = "佣金比例")
	private Long commissionrate;
	
	/**
	 * 实际佣金
	 */
	@ApiModelProperty(value = "实际佣金")
	private Long actualfee;
	
	/**
	 * 实际计算佣金的金额
	 */
	@ApiModelProperty(value = "实际计算佣金的金额")
	private BigDecimal actualcosprice;
	
	/**
	 * 推广位ID
	 */
	@ApiModelProperty(value = "推广位ID")
	private String positionid;
	
	/**
	 * 预估结算时间
	 */
	@ApiModelProperty(value = "预估结算时间")
	private String paymonth;
	
	/**
	 * 商品ID
	 */
	@ApiModelProperty(value = "商品ID")
	private Long skuid;
	
	/**
	 * 商品名称
	 */
	@ApiModelProperty(value = "商品名称")
	private String skuname;
	/**
	 * 商品数量
	 */
	@ApiModelProperty(value = "商品数量")
	private Integer skunum;
	/**
	 * 商品缩略图
	 */
	@ApiModelProperty(value = "商品缩略图")
	private String thumbnailUrl;
	/**
	 * 商品单价
	 */
	@ApiModelProperty(value = "商品单价")
	private BigDecimal price;
	/**
	 * 订单完成时间
	 */
	@ApiModelProperty(value = "订单完成时间")
	private Integer finishtime;
	/**
	 * 付款金额
	 */
	@ApiModelProperty(value = "付款金额")
	private BigDecimal paymoney;
	/**
	 * 订单状态
	 */
	@ApiModelProperty(value = "订单状态")
	private Integer orderStatus;
	/**
	 * 支付时间
	 */
	@ApiModelProperty(value = "支付时间")
	private Integer payTime;
	/**
	 * 确认收货时间
	 */
	@ApiModelProperty(value = "确认收货时间")
	private Integer receiveTime;
	@ApiModelProperty(value = "供应商")
	private String supplierName;
	@ApiModelProperty(value = "用户昵称")
	private String nickname;
	@ApiModelProperty(value = "用户手机号")
	private String mobile;

	@ApiModelProperty(value = "用戶佣金")
	private BigDecimal feeMember;
	@ApiModelProperty(value = "定时结算")
	private Integer isSettle;
	@ApiModelProperty(value = "所属分享者")
	private Integer shareMemberId;
	@ApiModelProperty(value = "推广订单状态")
	private Integer orderStatusOld;
	@ApiModelProperty(value = "佣金结算")
	private Integer isRrive;

	@ApiModelProperty(value = "淘宝渠道ID")
	private String relationId;

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public Integer getOrderStatusOld() {
		return orderStatusOld;
	}

	public void setOrderStatusOld(Integer orderStatusOld) {
		this.orderStatusOld = orderStatusOld;
	}

	public Integer getIsRrive() {
		return isRrive;
	}

	public void setIsRrive(Integer isRrive) {
		this.isRrive = isRrive;
	}

	public BigDecimal getFeeMember() {
		return feeMember;
	}

	public void setFeeMember(BigDecimal feeMember) {
		this.feeMember = feeMember;
	}

	public Integer getIsSettle() {
		return isSettle;
	}

	public void setIsSettle(Integer isSettle) {
		this.isSettle = isSettle;
	}

	public Integer getShareMemberId() {
		return shareMemberId;
	}

	public void setShareMemberId(Integer shareMemberId) {
		this.shareMemberId = shareMemberId;
	}

	/**
	 * 设置：
	 */
	public void setGoodsOrderId(Integer goodsOrderId) {
		this.goodsOrderId = goodsOrderId;
	}
	/**
	 * 获取：
	 */
	public Integer getGoodsOrderId() {
		return goodsOrderId;
	}
	/**
	 * 设置：下单时间
	 */
	public void setOrdertime(Integer ordertime) {
		this.ordertime = ordertime;
	}
	/**
	 * 获取：下单时间
	 */
	public Integer getOrdertime() {
		return ordertime;
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
	 * 设置：订单ID
	 */
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	/**
	 * 获取：订单ID
	 */
	public String getOrderid() {
		return orderid;
	}
	/**
	 * 设置：佣金比例
	 */
	public void setCommissionrate(Long commissionrate) {
		this.commissionrate = commissionrate;
	}
	/**
	 * 获取：佣金比例
	 */
	public Long getCommissionrate() {
		return commissionrate;
	}
	/**
	 * 设置：实际佣金
	 */
	public void setActualfee(Long actualfee) {
		this.actualfee = actualfee;
	}
	/**
	 * 获取：实际佣金
	 */
	public Long getActualfee() {
		return actualfee;
	}
	/**
	 * 设置：实际计算佣金的金额
	 */
	public void setActualcosprice(BigDecimal actualcosprice) {
		this.actualcosprice = actualcosprice;
	}
	/**
	 * 获取：实际计算佣金的金额
	 */
	public BigDecimal getActualcosprice() {
		return actualcosprice;
	}
	/**
	 * 设置：推广位ID
	 */
	public void setPositionid(String positionid) {
		this.positionid = positionid;
	}
	/**
	 * 获取：推广位ID
	 */
	public String getPositionid() {
		return positionid;
	}
	/**
	 * 设置：预估结算时间
	 */
	public void setPaymonth(String paymonth) {
		this.paymonth = paymonth;
	}
	/**
	 * 获取：预估结算时间
	 */
	public String getPaymonth() {
		return paymonth;
	}
	/**
	 * 设置：商品ID
	 */
	public void setSkuid(Long skuid) {
		this.skuid = skuid;
	}
	/**
	 * 获取：商品ID
	 */
	public Long getSkuid() {
		return skuid;
	}
	/**
	 * 设置：商品名称
	 */
	public void setSkuname(String skuname) {
		this.skuname = skuname;
	}
	/**
	 * 获取：商品名称
	 */
	public String getSkuname() {
		return skuname;
	}
	/**
	 * 设置：商品数量
	 */
	public void setSkunum(Integer skunum) {
		this.skunum = skunum;
	}
	/**
	 * 获取：商品数量
	 */
	public Integer getSkunum() {
		return skunum;
	}
	/**
	 * 设置：商品缩略图
	 */
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	/**
	 * 获取：商品缩略图
	 */
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	/**
	 * 设置：商品单价
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：商品单价
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 设置：订单完成时间
	 */
	public void setFinishtime(Integer finishtime) {
		this.finishtime = finishtime;
	}
	/**
	 * 获取：订单完成时间
	 */
	public Integer getFinishtime() {
		return finishtime;
	}
	/**
	 * 设置：付款金额
	 */
	public void setPaymoney(BigDecimal paymoney) {
		this.paymoney = paymoney;
	}
	/**
	 * 获取：付款金额
	 */
	public BigDecimal getPaymoney() {
		return paymoney;
	}
	/**
	 * 设置：订单状态
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * 获取：订单状态
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}
	/**
	 * 设置：支付时间
	 */
	public void setPayTime(Integer payTime) {
		this.payTime = payTime;
	}
	/**
	 * 获取：支付时间
	 */
	public Integer getPayTime() {
		return payTime;
	}
	/**
	 * 设置：确认收货时间
	 */
	public void setReceiveTime(Integer receiveTime) {
		this.receiveTime = receiveTime;
	}
	/**
	 * 获取：确认收货时间
	 */
	public Integer getReceiveTime() {
		return receiveTime;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
