package com.freeter.modules.answer.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;



/**
 * 题库问题
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-14 14:05:57
 */
@TableName("cd_answer_question")
@ApiModel(value = "AnswerQuestion")
public class AnswerQuestionEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public AnswerQuestionEntity() {
		
	}
	
	public AnswerQuestionEntity(T t) {
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
	private Integer answerQuestionId;

	/**
	 * 所属分类
	 */

	@NotNull (message = "所属分类不能为空")
	@ApiModelProperty(value = "所属分类")
	private Integer answerCateId;
	/**
	 * 问题
	 */
						
	@ApiModelProperty(value = "问题")
	private String title;
	/**
	 * 类型
	 */

	@NotNull (message = "类型不能为空")
	@ApiModelProperty(value = "类型")
	private Integer type;
	/**
	 * 设置：
	 */
	public void setAnswerQuestionId(Integer answerQuestionId) {
		this.answerQuestionId = answerQuestionId;
	}
	/**
	 * 获取：
	 */
	public Integer getAnswerQuestionId() {
		return answerQuestionId;
	}

	/**
	 * 设置：问题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：问题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：所属分类
	 */
	public void setAnswerCateId(Integer answerCateId) {
		this.answerCateId = answerCateId;
	}
	/**
	 * 获取：所属分类
	 */
	public Integer getAnswerCateId() {
		return answerCateId;
	}
	/**
	 * 设置：类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类型
	 */
	public Integer getType() {
		return type;
	}
}
