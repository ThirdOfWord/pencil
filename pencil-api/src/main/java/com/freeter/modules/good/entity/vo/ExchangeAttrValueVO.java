package com.freeter.modules.good.entity.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
 

/**
 * 规格管理
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-05 16:23:57
 */
@ApiModel(value = "ExchangeAttrValueVO")
public class ExchangeAttrValueVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 					
	/**
	 * 所属属性编号
	 */
	
	@ApiModelProperty(value = "所属属性编号") 
	private Integer exchangeAttrId;
		
	/**
	 * 规格
	 */
	
	@ApiModelProperty(value = "规格") 
	private String title;

	private Integer exchangeAttrValueId;
	/**
	 * 设置：所属属性编号
	 */
	 
	public void setExchangeAttrId(Integer exchangeAttrId) {
		this.exchangeAttrId = exchangeAttrId;
	}
	
	/**
	 * 获取：所属属性编号
	 */
	public Integer getExchangeAttrId() {
		return exchangeAttrId;
	}
				
	
	/**
	 * 设置：规格
	 */
	 
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 获取：规格
	 */
	public String getTitle() {
		return title;
	}

	public Integer getExchangeAttrValueId() {
		return exchangeAttrValueId;
	}

	public void setExchangeAttrValueId(Integer exchangeAttrValueId) {
		this.exchangeAttrValueId = exchangeAttrValueId;
	}
}
