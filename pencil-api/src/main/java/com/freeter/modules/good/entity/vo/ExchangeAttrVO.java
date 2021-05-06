package com.freeter.modules.good.entity.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
 

/**
 * 属性管理
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-05 16:23:58
 */
@ApiModel(value = "ExchangeAttrVO")
public class ExchangeAttrVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 					
	/**
	 * 属性
	 */
	
	@ApiModelProperty(value = "属性") 
	private String title;
		
	/**
	 * 排序
	 */
	
	@ApiModelProperty(value = "排序") 
	private Integer sortid;
								
	
	/**
	 * 设置：属性
	 */
	 
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 获取：属性
	 */
	public String getTitle() {
		return title;
	}
				
	
	/**
	 * 设置：排序
	 */
	 
	public void setSotrid(Integer sortid) {
		this.sortid = sortid;
	}
	
	/**
	 * 获取：排序
	 */
	public Integer getSotrid() {
		return sortid;
	}
			
}
