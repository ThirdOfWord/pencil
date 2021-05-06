package com.freeter.modules.money.entity;

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
 * 兑换订单红包领取记录
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-05-03 23:41:35
 */
@TableName("cd_exchange_bonus_record")
@ApiModel(value = "ExchangeBonusRecord")
public class ExchangeBonusRecordEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ExchangeBonusRecordEntity() {
		
	}
	
	public ExchangeBonusRecordEntity(T t) {
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
	private Integer exchangeBonusRecordId;
	
	/**
	 * 创建时间
	 */
							
	@TableField(fill = FieldFill.INSERT) 
	@ApiModelProperty(value = "创建时间",hidden = true)
	private Integer createTime;
	
	/**
	 * 修改时间
	 */
						
	@TableField(fill = FieldFill.UPDATE) 	
	@ApiModelProperty(value = "修改时间",hidden = true)
	private Integer updateTime;
	
	/**
	 * 所属兑换红包
	 */
			
	@NotNull (message = "所属兑换红包不能为空") 				
	@ApiModelProperty(value = "所属兑换红包")
	private Integer exchangeBonusId;
	
	/**
	 * 所属用户
	 */
			
	@NotNull (message = "所属用户不能为空") 				
	@ApiModelProperty(value = "所属用户")
	private Integer memberId;
	
	/**
	 * 用户昵称
	 */
						
	@ApiModelProperty(value = "用户昵称")
	private String nickname;
	
	/**
	 * 用户手机号
	 */
						
	@ApiModelProperty(value = "用户手机号")
	private String mobile;
	
	/**
	 * 金额
	 */
			
	@NotNull (message = "金额不能为空") 				
	@ApiModelProperty(value = "金额")
	private BigDecimal price;
	
	/**
	 * 设置：
	 */
	public void setExchangeBonusRecordId(Integer exchangeBonusRecordId) {
		this.exchangeBonusRecordId = exchangeBonusRecordId;
	}
	/**
	 * 获取：
	 */
	public Integer getExchangeBonusRecordId() {
		return exchangeBonusRecordId;
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
	 * 设置：修改时间
	 */
	public void setUpdateTime(Integer updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Integer getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：所属兑换红包
	 */
	public void setExchangeBonusId(Integer exchangeBonusId) {
		this.exchangeBonusId = exchangeBonusId;
	}
	/**
	 * 获取：所属兑换红包
	 */
	public Integer getExchangeBonusId() {
		return exchangeBonusId;
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
	 * 设置：用户昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 获取：用户昵称
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * 设置：用户手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：用户手机号
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：金额
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：金额
	 */
	public BigDecimal getPrice() {
		return price;
	}
}
