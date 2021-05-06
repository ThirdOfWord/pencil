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
 * 兑换订单红包
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-05-03 23:41:35
 */
@TableName("cd_exchange_bonus")
@ApiModel(value = "ExchangeBonus")
public class ExchangeBonusEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ExchangeBonusEntity() {
		
	}
	
	public ExchangeBonusEntity(T t) {
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
	private Integer exchangeBonusId;
	
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
	 * 所属兑换订单
	 */
			
	@NotNull (message = "所属兑换订单不能为空") 				
	@ApiModelProperty(value = "所属兑换订单")
	private Integer exchangeOrderId;
	
	/**
	 * 所属答题卡
	 */
			
	@NotNull (message = "所属答题卡不能为空") 				
	@ApiModelProperty(value = "所属答题卡")
	private Integer answerClassifyId;
	
	/**
	 * 所属用户
	 */
			
	@NotNull (message = "所属用户不能为空") 				
	@ApiModelProperty(value = "所属用户")
	private Integer memberId;
	
	/**
	 * 昵称
	 */
						
	@ApiModelProperty(value = "昵称")
	private String nickname;
	
	/**
	 * 手机号
	 */
						
	@ApiModelProperty(value = "手机号")
	private String mobile;
	
	/**
	 * 总金额
	 */
			
	@NotNull (message = "总金额不能为空") 				
	@ApiModelProperty(value = "总金额")
	private BigDecimal moneyTotal;
	
	/**
	 * 数量
	 */
			
	@NotNull (message = "数量不能为空") 				
	@ApiModelProperty(value = "数量")
	private Integer num;
	
	/**
	 * 单价
	 */
			
	@NotNull (message = "单价不能为空") 				
	@ApiModelProperty(value = "单价")
	private BigDecimal price;
	
	/**
	 * 领取量
	 */
			
	@NotNull (message = "领取量不能为空") 				
	@ApiModelProperty(value = "领取量")
	private Integer sales;
	
	/**
	 * 口令
	 */
						
	@ApiModelProperty(value = "口令")
	private String coding;
	
	/**
	 * 状态
	 */
			
	@NotNull (message = "状态不能为空") 				
	@ApiModelProperty(value = "状态")
	private Integer status;
	
	/**
	 * 分享标题
	 */
						
	@ApiModelProperty(value = "分享标题")
	private String shareTitle;
	
	/**
	 * 分享简介
	 */
						
	@ApiModelProperty(value = "分享简介")
	private String shareDes;
	
	/**
	 * 分享图片
	 */
						
	@ApiModelProperty(value = "分享图片")
	private String shareThumb;
	@ApiModelProperty(value = "卡片名称")
	private String cardTitle;
	@ApiModelProperty(value = "需要分享次数")
	private Integer shareNum;


	public Integer getShareNum() {
		return shareNum;
	}

	public void setShareNum(Integer shareNum) {
		this.shareNum = shareNum;
	}

	public String getCardTitle() {
		return cardTitle;
	}

	public void setCardTitle(String cardTitle) {
		this.cardTitle = cardTitle;
	}

	/**
	 * 设置：
	 */
	public void setExchangeBonusId(Integer exchangeBonusId) {
		this.exchangeBonusId = exchangeBonusId;
	}
	/**
	 * 获取：
	 */
	public Integer getExchangeBonusId() {
		return exchangeBonusId;
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
	 * 设置：所属兑换订单
	 */
	public void setExchangeOrderId(Integer exchangeOrderId) {
		this.exchangeOrderId = exchangeOrderId;
	}
	/**
	 * 获取：所属兑换订单
	 */
	public Integer getExchangeOrderId() {
		return exchangeOrderId;
	}
	/**
	 * 设置：所属答题卡
	 */
	public void setAnswerClassifyId(Integer answerClassifyId) {
		this.answerClassifyId = answerClassifyId;
	}
	/**
	 * 获取：所属答题卡
	 */
	public Integer getAnswerClassifyId() {
		return answerClassifyId;
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
	 * 设置：总金额
	 */
	public void setMoneyTotal(BigDecimal moneyTotal) {
		this.moneyTotal = moneyTotal;
	}
	/**
	 * 获取：总金额
	 */
	public BigDecimal getMoneyTotal() {
		return moneyTotal;
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
	 * 设置：单价
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：单价
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 设置：领取量
	 */
	public void setSales(Integer sales) {
		this.sales = sales;
	}
	/**
	 * 获取：领取量
	 */
	public Integer getSales() {
		return sales;
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
	 * 设置：状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：分享标题
	 */
	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}
	/**
	 * 获取：分享标题
	 */
	public String getShareTitle() {
		return shareTitle;
	}
	/**
	 * 设置：分享简介
	 */
	public void setShareDes(String shareDes) {
		this.shareDes = shareDes;
	}
	/**
	 * 获取：分享简介
	 */
	public String getShareDes() {
		return shareDes;
	}
	/**
	 * 设置：分享图片
	 */
	public void setShareThumb(String shareThumb) {
		this.shareThumb = shareThumb;
	}
	/**
	 * 获取：分享图片
	 */
	public String getShareThumb() {
		return shareThumb;
	}
}
