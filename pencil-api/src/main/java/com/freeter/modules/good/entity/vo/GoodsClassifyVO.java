package com.freeter.modules.good.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
 

/**
 * 商品分类
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-09 10:55:26
 */
@ApiModel(value = "GoodsClassifyVO")
public class GoodsClassifyVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 上级分类
	 */
	
	@ApiModelProperty(value = "上级分类") 
	private Integer pid;
		
	/**
	 * 标题
	 */
	
	@ApiModelProperty(value = "标题") 
	private String title;
		
	/**
	 * 缩略图
	 */
	
	@ApiModelProperty(value = "缩略图") 
	private String thumb;
		
	/**
	 * 状态
	 */
	
	@ApiModelProperty(value = "状态") 
	private Integer status;
				
	
	/**
	 * 设置：上级分类
	 */
	 
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	/**
	 * 获取：上级分类
	 */
	public Integer getPid() {
		return pid;
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
	 * 设置：状态
	 */
	 
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * 获取：状态
	 */
	public Integer getStatus() {
		return status;
	}
			
}
