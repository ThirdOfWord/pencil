package com.freeter.modules.answer.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 答题分类
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-14 14:05:57
 */
@ApiModel(value = "AnswerClassifyVO")
public class AnswerClassifyVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 标题
	 */
	
	@ApiModelProperty(value = "标题")
	private String title;
		
	/**
	 * 背景
	 */
	
	@ApiModelProperty(value = "背景") 
	private String thumb;
		
	/**
	 * 启用
	 */
	
	@ApiModelProperty(value = "启用") 
	private Integer status;
		
	/**
	 * 合成来源编号
	 */
	
	@ApiModelProperty(value = "合成来源编号") 
	private Integer mergeId;
		
	/**
	 * 排序
	 */
	
	@ApiModelProperty(value = "排序") 
	private Integer sortsid;
				
	
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
	 * 设置：背景
	 */
	 
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	
	/**
	 * 获取：背景
	 */
	public String getThumb() {
		return thumb;
	}
				
	
	/**
	 * 设置：启用
	 */
	 
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * 获取：启用
	 */
	public Integer getStatus() {
		return status;
	}
				
	
	/**
	 * 设置：合成来源编号
	 */
	 
	public void setMergeId(Integer mergeId) {
		this.mergeId = mergeId;
	}
	
	/**
	 * 获取：合成来源编号
	 */
	public Integer getMergeId() {
		return mergeId;
	}
				
	
	/**
	 * 设置：排序
	 */
	 
	public void setSortsid(Integer sortsid) {
		this.sortsid = sortsid;
	}
	
	/**
	 * 获取：排序
	 */
	public Integer getSortsid() {
		return sortsid;
	}
			
}
