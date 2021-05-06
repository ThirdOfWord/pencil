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
 * 分享者
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-06-28 22:42:55
 */
@TableName("cd_goods_share_link")
@ApiModel(value = "GoodsShareLink")
public class GoodsShareLinkEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public GoodsShareLinkEntity() {
		
	}
	
	public GoodsShareLinkEntity(T t) {
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
	private Integer goodsShareLinkId;
	
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
	 * 所属发起者
	 */
						
	@ApiModelProperty(value = "所属发起者")
	private Integer memberId;
	
	/**
	 * 所属点击者
	 */
						
	@ApiModelProperty(value = "所属点击者")
	private Integer clickMemberId;
	
	/**
	 * 所属商品ID
	 */
						
	@ApiModelProperty(value = "所属商品ID")
	private String goodsId;
	
	/**
	 * 所属供应商ID
	 */
						
	@ApiModelProperty(value = "所属供应商ID")
	private Integer supplierId;
	
	/**
	 * 设置：
	 */
	public void setGoodsShareLinkId(Integer goodsShareLinkId) {
		this.goodsShareLinkId = goodsShareLinkId;
	}
	/**
	 * 获取：
	 */
	public Integer getGoodsShareLinkId() {
		return goodsShareLinkId;
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
	 * 设置：所属发起者
	 */
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：所属发起者
	 */
	public Integer getMemberId() {
		return memberId;
	}
	/**
	 * 设置：所属点击者
	 */
	public void setClickMemberId(Integer clickMemberId) {
		this.clickMemberId = clickMemberId;
	}
	/**
	 * 获取：所属点击者
	 */
	public Integer getClickMemberId() {
		return clickMemberId;
	}
	/**
	 * 设置：所属商品ID
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取：所属商品ID
	 */
	public String getGoodsId() {
		return goodsId;
	}
	/**
	 * 设置：所属供应商ID
	 */
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * 获取：所属供应商ID
	 */
	public Integer getSupplierId() {
		return supplierId;
	}
}
