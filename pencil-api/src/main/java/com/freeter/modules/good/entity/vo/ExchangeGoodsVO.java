package com.freeter.modules.good.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 兑换商品
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-05 16:23:56
 */
@ApiModel(value = "ExchangeGoodsVO")
public class ExchangeGoodsVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer exchangeGoodsId;
	/**
	 * 标题
	 */
	@ApiModelProperty(value = "标题") 
	private String title;
		
	/**
	 * 主图
	 */
	@ApiModelProperty(value = "主图") 
	private String thumb;
		
	/**
	 * 库存
	 */
	@ApiModelProperty(value = "库存") 
	private Integer stock;

	/**
	 * 属性规格
	 */
	@ApiModelProperty(value = "属性规格") 
	private Integer isAttrStatus;
		
	/**
	 * 属性编号
	 */
	@ApiModelProperty(value = "属性编号") 
	private String attrIdStr;

	public Integer getExchangeGoodsId() {
		return exchangeGoodsId;
	}

	public void setExchangeGoodsId(Integer exchangeGoodsId) {
		this.exchangeGoodsId = exchangeGoodsId;
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
	 * 设置：主图
	 */
	 
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	
	/**
	 * 获取：主图
	 */
	public String getThumb() {
		return thumb;
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
	/**
	 * 设置：属性规格
	 */
	 
	public void setIsAttrStatus(Integer isAttrStatus) {
		this.isAttrStatus = isAttrStatus;
	}
	
	/**
	 * 获取：属性规格
	 */
	public Integer getIsAttrStatus() {
		return isAttrStatus;
	}
				
	
	/**
	 * 设置：属性编号
	 */
	 
	public void setAttrIdStr(String attrIdStr) {
		this.attrIdStr = attrIdStr;
	}
	
	/**
	 * 获取：属性编号
	 */
	public String getAttrIdStr() {
		return attrIdStr;
	}
}
