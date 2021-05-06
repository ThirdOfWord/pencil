package com.freeter.modules.order.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 兑换订单显示表
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-20 16:06:26
 */

public class Order_GoodsVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单状态,0取消,1待支付,2待发货,3待收货,4已完成
	 */
	@ApiModelProperty(value = "订单状态,0取消,1待支付,2待发货,3待收货,4已完成")
	private Integer status;

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
	 * 所兑换订单
	 */

	@ApiModelProperty(value = "所兑换订单")
	private Integer exchangeOrderId;

	/**
	 * 所属商品
	 */

	@ApiModelProperty(value = "所属商品")
	private Integer exchangeGoodsId;

	/**
	 * 商品标题
	 */

	@ApiModelProperty(value = "商品标题")
	private String title;

	/**
	 * 图片
	 */

	@ApiModelProperty(value = "图片")
	private String thumb;

	/**
	 * 数量
	 */
	@ApiModelProperty(value = "数量")
	private Integer num;

	@ApiModelProperty(value = "创建时间",hidden = true)
	private Integer createTime;

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	/**
	 * 设置：所兑换订单
	 */

	public void setExchangeOrderId(Integer exchangeOrderId) {
		this.exchangeOrderId = exchangeOrderId;
	}

	/**
	 * 获取：所兑换订单
	 */
	public Integer getExchangeOrderId() {
		return exchangeOrderId;
	}


	/**
	 * 设置：所属商品
	 */

	public void setExchangeGoodsId(Integer exchangeGoodsId) {
		this.exchangeGoodsId = exchangeGoodsId;
	}

	/**
	 * 获取：所属商品
	 */
	public Integer getExchangeGoodsId() {
		return exchangeGoodsId;
	}


	/**
	 * 设置：商品标题
	 */

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取：商品标题
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * 设置：图片
	 */

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	/**
	 * 获取：图片
	 */
	public String getThumb() {
		return thumb;
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
