package com.freeter.modules.order.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
 

/**
 * 兑换订单商品
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-20 16:06:27
 */
@ApiModel(value = "ExchangeOrderGoodsVO")
public class ExchangeOrderGoodsVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
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
	 * 属性规格
	 */
	
	@ApiModelProperty(value = "属性规格") 
	private String attrTitle;
		
	/**
	 * 数量
	 */
	
	@ApiModelProperty(value = "数量") 
	private Integer num;
				
	
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
