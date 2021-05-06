package com.freeter.modules.other.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;



/**
 * 红包设置
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-03-17 15:47:19
 */
@TableName("cd_bonus_set")
@ApiModel(value = "BonusSet")
public class BonusSetEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public BonusSetEntity() {
		
	}
	
	public BonusSetEntity(T t) {
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
	private Integer bonusSetId;

	/**
	 * 金额
	 */
			
	@NotNull (message = "金额不能为空") 				
	@ApiModelProperty(value = "金额")
	private BigDecimal money;
	
	/**
	 * 口令
	 */
						
	@ApiModelProperty(value = "口令")
	private String coding;
	
	/**
	 * 数量
	 */
			
	@NotNull (message = "数量不能为空") 				
	@ApiModelProperty(value = "数量")
	private Integer num;
	
	/**
	 * 销量
	 */
			
	@NotNull (message = "销量不能为空") 				
	@ApiModelProperty(value = "销量")
	private Integer sales;
	

	
	/**
	 * 设置：
	 */
	public void setBonusSetId(Integer bonusSetId) {
		this.bonusSetId = bonusSetId;
	}
	/**
	 * 获取：
	 */
	public Integer getBonusSetId() {
		return bonusSetId;
	}
	/**
	 * 设置：金额
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	/**
	 * 获取：金额
	 */
	public BigDecimal getMoney() {
		return money;
	}
	/**
	 * 设置：口令
	 */
	public void setCoding(String coding) {
		this.coding = coding;
	}
	/**
	 * 获取：口令
	 */
	public String getCoding() {
		return coding;
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
	 * 设置：销量
	 */
	public void setSales(Integer sales) {
		this.sales = sales;
	}
	/**
	 * 获取：销量
	 */
	public Integer getSales() {
		return sales;
	}

}
