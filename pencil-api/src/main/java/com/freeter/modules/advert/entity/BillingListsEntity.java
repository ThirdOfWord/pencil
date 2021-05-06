package com.freeter.modules.advert.entity;

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
 * 广告列表
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-09 15:45:09
 */
@TableName("cd_billing_lists")
@ApiModel(value = "BillingLists")
public class BillingListsEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public BillingListsEntity() {
		
	}
	
	public BillingListsEntity(T t) {
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
	private Integer billingListsId;
	
	/**
	 * 所属位置
	 */
			
	@NotNull (message = "所属位置不能为空") 				
	@ApiModelProperty(value = "所属位置")
	private Integer billingSiteId;
	
	/**
	 * 类型
	 */
			
	@NotNull (message = "类型不能为空") 				
	@ApiModelProperty(value = "类型")
	private Integer type;
	
	/**
	 * 值
	 */
						
	@ApiModelProperty(value = "值")
	private String param;
	
	/**
	 * 图片
	 */
						
	@ApiModelProperty(value = "图片")
	private String thumb;

	
	/**
	 * 设置：
	 */
	public void setBillingListsId(Integer billingListsId) {
		this.billingListsId = billingListsId;
	}
	/**
	 * 获取：
	 */
	public Integer getBillingListsId() {
		return billingListsId;
	}
	/**
	 * 设置：所属位置
	 */
	public void setBillingSiteId(Integer billingSiteId) {
		this.billingSiteId = billingSiteId;
	}
	/**
	 * 获取：所属位置
	 */
	public Integer getBillingSiteId() {
		return billingSiteId;
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
	/**
	 * 设置：值
	 */
	public void setParam(String param) {
		this.param = param;
	}
	/**
	 * 获取：值
	 */
	public String getParam() {
		return param;
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
}
