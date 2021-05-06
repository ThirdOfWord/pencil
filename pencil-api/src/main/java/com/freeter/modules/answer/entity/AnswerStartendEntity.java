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
 * 助力起止时间
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-05-02 11:54:04
 */
@TableName("cd_answer_startend")
@ApiModel(value = "AnswerStartend")
public class AnswerStartendEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public AnswerStartendEntity() {
		
	}
	
	public AnswerStartendEntity(T t) {
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
	private Integer answerStartendId;

	/**
	 * 开始时间
	 */
						
	@ApiModelProperty(value = "开始时间")
	private Integer timeStart;
	
	/**
	 * 结束时间
	 */
						
	@ApiModelProperty(value = "结束时间")
	private Integer endTime;
	
	/**
	 * 间隔(分）
	 */
			
	@NotNull (message = "间隔(分）不能为空") 				
	@ApiModelProperty(value = "间隔(分）")
	private Integer gap;

	/**
	 * 设置：
	 */
	public void setAnswerStartendId(Integer answerStartendId) {
		this.answerStartendId = answerStartendId;
	}
	/**
	 * 获取：
	 */
	public Integer getAnswerStartendId() {
		return answerStartendId;
	}

	/**
	 * 设置：开始时间
	 */
	public void setTimeStart(Integer timeStart) {
		this.timeStart = timeStart;
	}
	/**
	 * 获取：开始时间
	 */
	public Integer getTimeStart() {
		return timeStart;
	}
	/**
	 * 设置：结束时间
	 */
	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：结束时间
	 */
	public Integer getEndTime() {
		return endTime;
	}
	/**
	 * 设置：间隔(分）
	 */
	public void setGap(Integer gap) {
		this.gap = gap;
	}
	/**
	 * 获取：间隔(分）
	 */
	public Integer getGap() {
		return gap;
	}

}
