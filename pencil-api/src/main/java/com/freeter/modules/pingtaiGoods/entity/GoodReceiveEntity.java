package com.freeter.modules.pingtaiGoods.entity;

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
 * 用户领取淘礼金记录表
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-08-17 23:22:49
 */
@TableName("cd_good_receive")
@ApiModel(value = "GoodReceive")
public class GoodReceiveEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public GoodReceiveEntity() {
		
	}
	
	public GoodReceiveEntity(T t) {
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
	private Long id;
	
	/**
	 * 淘礼金id
	 */
						
	@ApiModelProperty(value = "淘礼金id")
	private Integer goodsGoldRushId;
	
	/**
	 * 用户id
	 */
						
	@ApiModelProperty(value = "用户id")
	private Integer memberId;

	private String mobile;

	@ApiModelProperty(value = "淘宝授权用户id")
	private String openId;
	/**
	 * 领取时间
	 */
							
	@TableField(fill = FieldFill.INSERT) 
	@ApiModelProperty(value = "领取时间",hidden = true)
	private Integer createTime;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置：淘礼金id
	 */
	public void setGoodsGoldRushId(Integer goodsGoldRushId) {
		this.goodsGoldRushId = goodsGoldRushId;
	}
	/**
	 * 获取：淘礼金id
	 */
	public Integer getGoodsGoldRushId() {
		return goodsGoldRushId;
	}
	/**
	 * 设置：用户id
	 */
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：用户id
	 */
	public Integer getMemberId() {
		return memberId;
	}
	/**
	 * 设置：领取时间
	 */
	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：领取时间
	 */
	public Integer getCreateTime() {
		return createTime;
	}
}
