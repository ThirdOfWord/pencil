package com.freeter.modules.good.entity;

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
 * 
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-05 16:23:59
 */
@TableName("cd_exchange_goods_attrs")
@ApiModel(value = "ExchangeGoodsAttrs")
public class ExchangeGoodsAttrsEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ExchangeGoodsAttrsEntity() {
		
	}
	
	public ExchangeGoodsAttrsEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 编号
	 */
	
	@TableId 					
	@ApiModelProperty(value = "编号",hidden = true)
	private Integer exchangeGoodsAttrsId;
	
	/**
	 * 所属商品编号
	 */
						
	@ApiModelProperty(value = "所属商品编号")
	private Integer exchangeGoodsId;
	
	/**
	 * 缩略图
	 */
						
	@ApiModelProperty(value = "缩略图")
	private String thumb;
	
	/**
	 * 标题
	 */
						
	@ApiModelProperty(value = "标题")
	private String title;
	
	/**
	 * 规格组合
	 */
						
	@ApiModelProperty(value = "规格组合")
	private String exchangeAttrValueIdStr;
	
	/**
	 * 库存
	 */
						
	@ApiModelProperty(value = "库存")
	private Integer stock;
	
	/**
	 * 设置：编号
	 */
	public void setExchangeGoodsAttrsId(Integer exchangeGoodsAttrsId) {
		this.exchangeGoodsAttrsId = exchangeGoodsAttrsId;
	}
	/**
	 * 获取：编号
	 */
	public Integer getExchangeGoodsAttrsId() {
		return exchangeGoodsAttrsId;
	}
	/**
	 * 设置：所属商品编号
	 */
	public void setExchangeGoodsId(Integer exchangeGoodsId) {
		this.exchangeGoodsId = exchangeGoodsId;
	}
	/**
	 * 获取：所属商品编号
	 */
	public Integer getExchangeGoodsId() {
		return exchangeGoodsId;
	}
	/**
	 * 设置：缩略图
	 */
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	/**
	 * 获取：缩略图
	 */
	public String getThumb() {
		return thumb;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：规格组合
	 */
	public void setExchangeAttrValueIdStr(String exchangeAttrValueIdStr) {
		this.exchangeAttrValueIdStr = exchangeAttrValueIdStr;
	}
	/**
	 * 获取：规格组合
	 */
	public String getExchangeAttrValueIdStr() {
		return exchangeAttrValueIdStr;
	}
	/**
	 * 设置：库存
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	/**
	 * 获取：库存
	 */
	public Integer getStock() {
		return stock;
	}
}
