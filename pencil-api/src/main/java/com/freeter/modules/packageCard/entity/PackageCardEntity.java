package com.freeter.modules.packageCard.entity;

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
 * 卡包卡片
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-04-27 18:29:40
 */
@TableName("cd_package_card")
@ApiModel(value = "PackageCard")
public class PackageCardEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public PackageCardEntity() {
		
	}
	
	public PackageCardEntity(T t) {
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
	private Integer packageCardId;
	
	/**
	 * 所属卡片
	 */
			
	@NotNull (message = "所属卡片不能为空") 				
	@ApiModelProperty(value = "所属卡片")
	private Integer answerClassifyId;
	
	/**
	 * 数量
	 */
			
	@NotNull (message = "数量不能为空") 				
	@ApiModelProperty(value = "数量")
	private Integer num;
	
	/**
	 * 所属卡包
	 */
			
	@NotNull (message = "所属卡包不能为空") 				
	@ApiModelProperty(value = "所属卡包")
	private Integer packageId;
	
	/**
	 * 设置：
	 */
	public void setPackageCardId(Integer packageCardId) {
		this.packageCardId = packageCardId;
	}
	/**
	 * 获取：
	 */
	public Integer getPackageCardId() {
		return packageCardId;
	}
	/**
	 * 设置：所属卡片
	 */
	public void setAnswerClassifyId(Integer answerClassifyId) {
		this.answerClassifyId = answerClassifyId;
	}
	/**
	 * 获取：所属卡片
	 */
	public Integer getAnswerClassifyId() {
		return answerClassifyId;
	}
	/**
	 * 设置：数量
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	/**
	 * 获取：数量
	 */
	public Integer getNum() {
		return num;
	}
	/**
	 * 设置：所属卡包
	 */
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}
	/**
	 * 获取：所属卡包
	 */
	public Integer getPackageId() {
		return packageId;
	}
}
