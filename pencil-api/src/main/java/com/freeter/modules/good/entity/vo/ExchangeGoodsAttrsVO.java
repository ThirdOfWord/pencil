package com.freeter.modules.good.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-05 16:23:59
 */
@ApiModel(value = "ExchangeGoodsAttrsVO")
public class ExchangeGoodsAttrsVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
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
