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
 * 题库问题选项
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-10 13:31:38
 */
@TableName("cd_answer_options")
@ApiModel(value = "AnswerOptions")
public class AnswerOptionsEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public AnswerOptionsEntity() {
		
	}
	
	public AnswerOptionsEntity(T t) {
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
	private Integer answerOptionsId;
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
	@NotNull (message = "正确不能为空") 				
	@ApiModelProperty(value = "正确")
	private Integer yes;
	
	/**
	 * 设置：
	 */
	public void setAnswerOptionsId(Integer answerOptionsId) {
		this.answerOptionsId = answerOptionsId;
	}
	/**
	 * 获取：
	 */
	public Integer getAnswerOptionsId() {
		return answerOptionsId;
	}
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
