package com.freeter.modules.taskOrder.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 推广商城订单
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-04-19 22:37:31
 */
@ApiModel(value = "GoodsOrderVO")
public class GoodsOrderVO  implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer goodsOrderId;

	@ApiModelProperty(value = "所属供应商")
	private Integer supplierId;
		
	/**
	 * 下单时间
	 */
	
	@ApiModelProperty(value = "下单时间") 
	private Integer ordertime;

	/**
	 * 订单ID
	 */
	
	@ApiModelProperty(value = "订单ID") 
	private String orderid;


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

	@ApiModelProperty(value = "用戶佣金")
	private BigDecimal feeMember;
	@ApiModelProperty(value = "佣金结算状态")
	private Integer isRrive;

	public BigDecimal getFeeMember() {
		return feeMember;
	}

	public void setFeeMember(BigDecimal feeMember) {
		this.feeMember = feeMember;
	}

	public Integer getIsRrive() {
		return isRrive;
	}

	public void setIsRrive(Integer isRrive) {
		this.isRrive = isRrive;
	}

	public Integer getGoodsOrderId() {
		return goodsOrderId;
	}

	public void setGoodsOrderId(Integer goodsOrderId) {
		this.goodsOrderId = goodsOrderId;
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

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
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
			
}
