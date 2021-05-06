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
 * 兑换订单商品
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-20 16:06:27
 */
@TableName("cd_exchange_order_goods")
@ApiModel(value = "ExchangeOrderGoods")
public class ExchangeOrderGoodsEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ExchangeOrderGoodsEntity() {
		
	}
	
	public ExchangeOrderGoodsEntity(T t) {
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
	private Integer exchangeOrderGoodsId;
	
	/**
	 * 所兑换订单
	 */
			
	@NotNull (message = "所兑换订单不能为空") 				
	@ApiModelProperty(value = "所兑换订单")
	private Integer exchangeOrderId;
	
	/**
	 * 所属商品
	 */
			
	@NotNull (message = "所属商品不能为空") 				
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
	 * 属性规格
	 */
						
	@ApiModelProperty(value = "属性规格")
	private String attrTitle;
	
	/**
	 * 数量
	 */
			
	@NotNull (message = "数量不能为空") 				
	@ApiModelProperty(value = "数量")
	private Integer num;
	
	/**
	 * 设置：
	 */
	public void setExchangeOrderGoodsId(Integer exchangeOrderGoodsId) {
		this.exchangeOrderGoodsId = exchangeOrderGoodsId;
	}
	/**
	 * 获取：
	 */
	public Integer getExchangeOrderGoodsId() {
		return exchangeOrderGoodsId;
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
	 * 设置：属性规格
	 */
	public void setAttrTitle(String attrTitle) {
		this.attrTitle = attrTitle;
	}
	/**
	 * 获取：属性规格
	 */
	public String getAttrTitle() {
		return attrTitle;
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
