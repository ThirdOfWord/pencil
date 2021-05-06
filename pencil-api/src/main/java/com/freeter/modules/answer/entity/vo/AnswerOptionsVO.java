package com.freeter.modules.answer.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
 

/**
 * 题库问题选项
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-10 13:31:38
 */
@ApiModel(value = "AnswerOptionsVO")
public class AnswerOptionsVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 					
	/**
	 * 题库编号
	 */
	
	@ApiModelProperty(value = "题库编号") 
	private Integer answerQuestionId;
		
	/**
	 * 选项
	 */
	
	@ApiModelProperty(value = "选项") 
	private String title;
		
	/**
	 * 正确
	 */
	
	@ApiModelProperty(value = "正确") 
	private Integer yes;
								
	
	/**
	 * 设置：题库编号
	 */
	 
	public void setAnswerQuestionId(Integer answerQuestionId) {
		this.answerQuestionId = answerQuestionId;
	}
	
	/**
	 * 获取：题库编号
	 */
	public Integer getAnswerQuestionId() {
		return answerQuestionId;
	}
				
	
	/**
	 * 设置：选项
	 */
	 
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 获取：选项
	 */
	public String getTitle() {
		return title;
	}
				
	
	/**
	 * 设置：正确
	 */
	 
	public void setYes(Integer yes) {
		this.yes = yes;
	}
	
	/**
	 * 获取：正确
	 */
	public Integer getYes() {
		return yes;
	}
			
}
