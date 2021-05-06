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
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;



/**
 * 供应商商品
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-27 16:20:41
 */
@TableName("cd_goods")
@ApiModel(value = "Goods")
public class GoodsEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public GoodsEntity() {
		
	}
	
	public GoodsEntity(T t) {
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
	private Integer goodsId;
	
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
	 * 所属供应商
	 */
			
	@NotNull (message = "所属供应商不能为空") 				
	@ApiModelProperty(value = "所属供应商")
	private Integer supplierId;
	
	/**
	 * 所属分类
	 */
			
	@NotNull (message = "所属分类不能为空") 				
	@ApiModelProperty(value = "所属分类")
	private Integer goodsClassifyId;
	
	/**
	 * 标题
	 */
						
	@ApiModelProperty(value = "标题")
	private String title;
	
	/**
	 * 价格
	 */
			
	@NotNull (message = "价格不能为空") 				
	@ApiModelProperty(value = "价格")
	private BigDecimal price;
	
	/**
	 * 上架
	 */
			
	@NotNull (message = "上架不能为空") 				
	@ApiModelProperty(value = "上架")
	private Integer status;
	
	/**
	 * 原价
	 */
			
	@NotNull (message = "原价不能为空") 				
	@ApiModelProperty(value = "原价")
	private BigDecimal oprice;
	
	/**
	 * 销量
	 */
			
	@NotNull (message = "销量不能为空") 				
	@ApiModelProperty(value = "销量")
	private Integer sales;
	
	/**
	 * 精选
	 */
			
	@NotNull (message = "精选不能为空") 				
	@ApiModelProperty(value = "精选")
	private Integer isChoice;
	
	/**
	 * 短链接
	 */
						
	@ApiModelProperty(value = "短链接")
	private String urlShort;
	
	/**
	 * 长链接
	 */
						
	@ApiModelProperty(value = "长链接")
	private String urlLong;
	
	/**
	 * 图片
	 */
						
	@ApiModelProperty(value = "图片")
	private String thumb;
	
	/**
	 * 推广图片
	 */
						
	@ApiModelProperty(value = "推广图片")
	private String thumbSpread;
	
	/**
	 * 商品ID
	 */
			
	@NotNull (message = "商品ID不能为空") 				
	@ApiModelProperty(value = "商品ID")
	private Long supplierGoodsId;

	@ApiModelProperty(value = "红包")
	private BigDecimal bonus;

	@ApiModelProperty(value = "省多少")
	private BigDecimal omit;

	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public BigDecimal getOmit() {
		return omit;
	}

	public void setOmit(BigDecimal omit) {
		this.omit = omit;
	}

	/**
	 * 设置：
	 */
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取：
	 */
	public Integer getGoodsId() {
		return goodsId;
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
	 * 设置：所属供应商
	 */
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * 获取：所属供应商
	 */
	public Integer getSupplierId() {
		return supplierId;
	}
	/**
	 * 设置：所属分类
	 */
	public void setGoodsClassifyId(Integer goodsClassifyId) {
		this.goodsClassifyId = goodsClassifyId;
	}
	/**
	 * 获取：所属分类
	 */
	public Integer getGoodsClassifyId() {
		return goodsClassifyId;
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
	 * 设置：价格
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：价格
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 设置：上架
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：上架
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：原价
	 */
	public void setOprice(BigDecimal oprice) {
		this.oprice = oprice;
	}
	/**
	 * 获取：原价
	 */
	public BigDecimal getOprice() {
		return oprice;
	}
	/**
	 * 设置：销量
	 */
	public void setSales(Integer sales) {
		this.sales = sales;
	}
	/**
	 * 获取：销量
	 */
	public Integer getSales() {
		return sales;
	}
	/**
	 * 设置：精选
	 */
	public void setIsChoice(Integer isChoice) {
		this.isChoice = isChoice;
	}
	/**
	 * 获取：精选
	 */
	public Integer getIsChoice() {
		return isChoice;
	}
	/**
	 * 设置：短链接
	 */
	public void setUrlShort(String urlShort) {
		this.urlShort = urlShort;
	}
	/**
	 * 获取：短链接
	 */
	public String getUrlShort() {
		return urlShort;
	}
	/**
	 * 设置：长链接
	 */
	public void setUrlLong(String urlLong) {
		this.urlLong = urlLong;
	}
	/**
	 * 获取：长链接
	 */
	public String getUrlLong() {
		return urlLong;
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
	 * 设置：推广图片
	 */
	public void setThumbSpread(String thumbSpread) {
		this.thumbSpread = thumbSpread;
	}
	/**
	 * 获取：推广图片
	 */
	public String getThumbSpread() {
		return thumbSpread;
	}
	/**
	 * 设置：商品ID
	 */
	public void setSupplierGoodsId(Long supplierGoodsId) {
		this.supplierGoodsId = supplierGoodsId;
	}
	/**
	 * 获取：商品ID
	 */
	public Long getSupplierGoodsId() {
		return supplierGoodsId;
	}
}
