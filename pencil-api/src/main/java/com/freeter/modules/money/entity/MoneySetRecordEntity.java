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
 * 平台钱包记录
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-04-27 22:14:20
 */
@TableName("cd_money_set_record")
@ApiModel(value = "MoneySetRecord")
public class MoneySetRecordEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public MoneySetRecordEntity() {
		
	}
	
	public MoneySetRecordEntity(T t) {
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
	private Integer moneySetRecordId;
	
	/**
	 * 创建时间
	 */
							
	@TableField(fill = FieldFill.INSERT) 
	@ApiModelProperty(value = "创建时间",hidden = true)
	private Integer createTime;
	
	/**
	 * 所属用户
	 */
			
	@NotNull (message = "所属用户不能为空") 				
	@ApiModelProperty(value = "所属用户")
	private Integer memberId;
	
	/**
	 * 所属钱包
	 */
			
	@NotNull (message = "所属钱包不能为空") 				
	@ApiModelProperty(value = "所属钱包")
	private Integer moneySetId;
	
	/**
	 * 手机号
	 */
						
	@ApiModelProperty(value = "手机号")
	private String mobile;
	
	/**
	 * 昵称
	 */
						
	@ApiModelProperty(value = "昵称")
	private String nickname;
	
	/**
	 * 钱包
	 */
						
	@ApiModelProperty(value = "钱包")
	private String title;
	
	/**
	 * 加减
	 */
			
	@NotNull (message = "加减不能为空") 				
	@ApiModelProperty(value = "加减")
	private Integer addCut;
	
	/**
	 * 前
	 */
						
	@ApiModelProperty(value = "前")
	private BigDecimal old;
	
	/**
	 * 金额
	 */
						
	@ApiModelProperty(value = "金额")
	private BigDecimal money;
	
	/**
	 * 后
	 */
						
	@ApiModelProperty(value = "后")
	private BigDecimal after;
	
	/**
	 * 来源类型
	 */
			
	@NotNull (message = "来源类型不能为空") 				
	@ApiModelProperty(value = "来源类型")
	private Integer sourceType;
	
	/**
	 * 来源ID
	 */
			
	@NotNull (message = "来源ID不能为空") 				
	@ApiModelProperty(value = "来源ID")
	private Integer sourceId;
	
	/**
	 * 备注
	 */
						
	@ApiModelProperty(value = "备注")
	private String remark;
	
	/**
	 * 设置：
	 */
	public void setMoneySetRecordId(Integer moneySetRecordId) {
		this.moneySetRecordId = moneySetRecordId;
	}
	/**
	 * 获取：
	 */
	public Integer getMoneySetRecordId() {
		return moneySetRecordId;
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
	 * 设置：所属钱包
	 */
	public void setMoneySetId(Integer moneySetId) {
		this.moneySetId = moneySetId;
	}
	/**
	 * 获取：所属钱包
	 */
	public Integer getMoneySetId() {
		return moneySetId;
	}
	/**
	 * 设置：手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 获取：昵称
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * 设置：钱包
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：钱包
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：加减
	 */
	public void setAddCut(Integer addCut) {
		this.addCut = addCut;
	}
	/**
	 * 获取：加减
	 */
	public Integer getAddCut() {
		return addCut;
	}
	/**
	 * 设置：前
	 */
	public void setOld(BigDecimal old) {
		this.old = old;
	}
	/**
	 * 获取：前
	 */
	public BigDecimal getOld() {
		return old;
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
	 * 设置：后
	 */
	public void setAfter(BigDecimal after) {
		this.after = after;
	}
	/**
	 * 获取：后
	 */
	public BigDecimal getAfter() {
		return after;
	}
	/**
	 * 设置：来源类型
	 */
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
	/**
	 * 获取：来源类型
	 */
	public Integer getSourceType() {
		return sourceType;
	}
	/**
	 * 设置：来源ID
	 */
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}
	/**
	 * 获取：来源ID
	 */
	public Integer getSourceId() {
		return sourceId;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
}
