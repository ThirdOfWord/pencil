package com.freeter.modules.good.entity;

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
 * 属性管理
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-05 16:23:58
 */
@TableName("cd_exchange_attr")
@ApiModel(value = "ExchangeAttr")
public class ExchangeAttrEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ExchangeAttrEntity() {
		
	}
	
	public ExchangeAttrEntity(T t) {
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
	private Integer exchangeAttrId;
	
	/**
	 * 创建时间
	 */
							
	@TableField(fill = FieldFill.INSERT) 
	@ApiModelProperty(value = "创建时间",hidden = true)
	private Integer createTime;
	
	/**
	 * 更新时间
	 */
						
	@TableField(fill = FieldFill.UPDATE) 	
	@ApiModelProperty(value = "更新时间",hidden = true)
	private Integer updateTime;
	
	/**
	 * 属性
	 */
						
	@ApiModelProperty(value = "属性")
	private String title;
	
	/**
	 * 排序
	 */
			
	@NotNull (message = "排序不能为空") 				
	@ApiModelProperty(value = "排序")
	private Integer sortid;
	
	/**
	 * 设置：
	 */
	public void setExchangeAttrId(Integer exchangeAttrId) {
		this.exchangeAttrId = exchangeAttrId;
	}
	/**
	 * 获取：
	 */
	public Integer getExchangeAttrId() {
		return exchangeAttrId;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Integer getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Integer updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Integer getUpdateTime() {
		return updateTime;
	}
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
