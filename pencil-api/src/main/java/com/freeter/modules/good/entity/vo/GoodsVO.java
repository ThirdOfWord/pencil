package com.freeter.modules.good.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 供应商商品
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-27 16:20:41
 */
@ApiModel(value = "GoodsVO")
public class GoodsVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "",hidden = true)
	private Integer goodsId;
	/**
	 * 所属供应商
	 */
	@ApiModelProperty(value = "所属供应商")
	private Integer supplierId;

	/**
	 * 标题
	 */
	@ApiModelProperty(value = "标题") 
	private String title;
		
	/**
	 * 价格
	 */
	@ApiModelProperty(value = "价格") 
	private BigDecimal price;

	/**
	 * 原价
	 */
	@ApiModelProperty(value = "原价") 
	private BigDecimal oprice;
		
	/**
	 * 销量
	 */
	@ApiModelProperty(value = "销量") 
	private Integer sales;

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

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
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
