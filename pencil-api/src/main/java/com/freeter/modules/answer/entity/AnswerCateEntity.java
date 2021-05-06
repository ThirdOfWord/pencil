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
 * 题库分类
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-03-03 19:41:59
 */
@TableName("cd_answer_cate")
@ApiModel(value = "AnswerCate")
public class AnswerCateEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public AnswerCateEntity() {
		
	}
	
	public AnswerCateEntity(T t) {
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
	private Integer answerCateId;
	
	/**
	 * 分类
	 */
						
	@ApiModelProperty(value = "分类")
	private String title;
	
	/**
	 * 启用
	 */
			
	@NotNull (message = "启用不能为空") 				
	@ApiModelProperty(value = "启用")
	private Integer status;
	
	/**
	 * 设置：
	 */
	public void setAnswerCateId(Integer answerCateId) {
		this.answerCateId = answerCateId;
	}
	/**
	 * 获取：
	 */
	public Integer getAnswerCateId() {
		return answerCateId;
	}
	/**
	 * 设置：分类
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：分类
	 */
	public String getTitle() {
		return title;
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
}
