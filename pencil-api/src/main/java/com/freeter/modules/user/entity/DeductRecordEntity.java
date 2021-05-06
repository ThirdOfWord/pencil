package com.freeter.modules.user.entity;

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
 * 提现记录表
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-22 16:14:16
 */
@TableName("cd_deduct_record")
@ApiModel(value = "DeductRecord")
public class DeductRecordEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public DeductRecordEntity() {
		
	}
	
	public DeductRecordEntity(T t) {
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
	private Integer deductRecordId;
	
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
	 * 用户编号
	 */
			
	@NotNull (message = "用户编号不能为空") 				
	@ApiModelProperty(value = "用户编号")
	private Integer memberId;
	
	/**
	 * 提成金额
	 */
			
	@NotNull (message = "提成金额不能为空") 				
	@ApiModelProperty(value = "提成金额")
	private BigDecimal money;
	
	/**
	 * 状态,0申请中,1成功,2失败
	 */
			
	@NotNull (message = "状态,0申请中,1成功,2失败不能为空") 				
	@ApiModelProperty(value = "状态,0申请中,1成功,2失败")
	private Integer status;
	
	/**
	 * 之后
	 */
			
	@NotNull (message = "之后不能为空") 				
	@ApiModelProperty(value = "之后")
	private BigDecimal after;
	
	/**
	 * 之前
	 */
			
	@NotNull (message = "之前不能为空") 				
	@ApiModelProperty(value = "之前")
	private BigDecimal before;
	
	/**
	 * 用户名
	 */
						
	@ApiModelProperty(value = "用户名")
	private String name;
	
	/**
	 * 账号
	 */
						
	@ApiModelProperty(value = "账号")
	private String account;
	
	/**
	 * 设置：
	 */
	public void setDeductRecordId(Integer deductRecordId) {
		this.deductRecordId = deductRecordId;
	}
	/**
	 * 获取：
	 */
	public Integer getDeductRecordId() {
		return deductRecordId;
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
	 * 设置：用户编号
	 */
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：用户编号
	 */
	public Integer getMemberId() {
		return memberId;
	}
	/**
	 * 设置：提成金额
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	/**
	 * 获取：提成金额
	 */
	public BigDecimal getMoney() {
		return money;
	}
	/**
	 * 设置：状态,0申请中,1成功,2失败
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态,0申请中,1成功,2失败
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：之后
	 */
	public void setAfter(BigDecimal after) {
		this.after = after;
	}
	/**
	 * 获取：之后
	 */
	public BigDecimal getAfter() {
		return after;
	}
	/**
	 * 设置：之前
	 */
	public void setBefore(BigDecimal before) {
		this.before = before;
	}
	/**
	 * 获取：之前
	 */
	public BigDecimal getBefore() {
		return before;
	}
	/**
	 * 设置：用户名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：用户名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：账号
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * 获取：账号
	 */
	public String getAccount() {
		return account;
	}
}
