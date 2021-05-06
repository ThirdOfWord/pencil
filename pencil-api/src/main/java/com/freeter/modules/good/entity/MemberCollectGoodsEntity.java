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
 * 收藏商品表
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-04-12 23:09:17
 */
@TableName("cd_member_collect_goods")
@ApiModel(value = "MemberCollectGoods")
public class MemberCollectGoodsEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public MemberCollectGoodsEntity() {
		
	}
	
	public MemberCollectGoodsEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 收藏商品表id
	 */
	
	@TableId 					
	@ApiModelProperty(value = "收藏商品表id",hidden = true)
	private Integer id;
	
	/**
	 * 用户表ID
	 */
			
	@NotNull (message = "用户表ID不能为空") 				
	@ApiModelProperty(value = "用户表ID")
	private Integer memberId;
	
	/**
	 * 商品ID
	 */
			
	@NotNull (message = "商品ID不能为空") 				
	@ApiModelProperty(value = "商品ID")
	private Long supplierGoodsId;
	
	/**
	 * 创建时间
	 */
			
	@NotNull (message = "创建时间不能为空") 					
	@TableField(fill = FieldFill.INSERT) 
	@ApiModelProperty(value = "创建时间",hidden = true)
	private Integer createTime;
	
	/**
	 * 设置：收藏商品表id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：收藏商品表id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：用户表ID
	 */
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：用户表ID
	 */
	public Integer getMemberId() {
		return memberId;
	}
	/**
	 * 设置：商品ID
	 */
	public void setSupplierGoodsId(Long supplierGoodsId) {
		this.supplierGoodsId = supplierGoodsId;
	}
	/**
	 * 获取：商品ID
	 */
	public Long getSupplierGoodsId() {
		return supplierGoodsId;
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
}
