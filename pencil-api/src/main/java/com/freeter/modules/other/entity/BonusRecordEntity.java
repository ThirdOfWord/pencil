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
 * 红包领取记录
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-03-17 15:47:20
 */
@TableName("cd_bonus_record")
@ApiModel(value = "BonusRecord")
public class BonusRecordEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public BonusRecordEntity() {
		
	}
	
	public BonusRecordEntity(T t) {
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
	private Integer bonusRecordId;
	
	/**
	 * 创建时间
	 */
							
	@TableField(fill = FieldFill.INSERT) 
	@ApiModelProperty(value = "创建时间",hidden = true)
	private Integer createTime;
	
	/**
	 * 所属红包
	 */
			
	@NotNull (message = "所属红包不能为空") 				
	@ApiModelProperty(value = "所属红包")
	private Integer bonusSetId;
	
	/**
	 * 所属用户
	 */
			
	@NotNull (message = "所属用户不能为空") 				
	@ApiModelProperty(value = "所属用户")
	private Integer memberId;
	
	/**
	 * 金额
	 */
						
	@ApiModelProperty(value = "金额")
	private BigDecimal money;
	
	/**
	 * 口令
	 */
						
	@ApiModelProperty(value = "口令")
	private String coding;
	
	/**
	 * 设置：
	 */
	public void setBonusRecordId(Integer bonusRecordId) {
		this.bonusRecordId = bonusRecordId;
	}
	/**
	 * 获取：
	 */
	public Integer getBonusRecordId() {
		return bonusRecordId;
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
	 * 设置：所属红包
	 */
	public void setBonusSetId(Integer bonusSetId) {
		this.bonusSetId = bonusSetId;
	}
	/**
	 * 获取：所属红包
	 */
	public Integer getBonusSetId() {
		return bonusSetId;
	}
	/**
	 * 设置：所属用户
	 */
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：所属用户
	 */
	public Integer getMemberId() {
		return memberId;
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
}
