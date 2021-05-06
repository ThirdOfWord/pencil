package com.freeter.modules.community.entity;

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
 * 社区推广
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-14 17:14:38
 */
@TableName("cd_community_article")
@ApiModel(value = "CommunityArticle")
public class CommunityArticleEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public CommunityArticleEntity() {
		
	}
	
	public CommunityArticleEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 编号
	 */
	
	@TableId 					
	@ApiModelProperty(value = "编号",hidden = true)
	private Integer communityArticleId;
	
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
	 * 商家编号
	 */
			
	@NotNull (message = "商家编号不能为空") 				
	@ApiModelProperty(value = "商家编号")
	private Integer storeUserId;
	
	/**
	 * 分类编号
	 */
			
	@NotNull (message = "分类编号不能为空") 				
	@ApiModelProperty(value = "分类编号")
	private Integer goodsClassifyId;
	
	/**
	 * 描述
	 */
						
	@ApiModelProperty(value = "描述")
	private String des;
	
	/**
	 * 分享次数
	 */
			
	@NotNull (message = "分享次数不能为空") 				
	@ApiModelProperty(value = "分享次数")
	private Integer shares;
	
	/**
	 * 启用
	 */
			
	@NotNull (message = "启用不能为空") 				
	@ApiModelProperty(value = "启用")
	private Integer status;
	
	/**
	 * 设置：编号
	 */
	public void setCommunityArticleId(Integer communityArticleId) {
		this.communityArticleId = communityArticleId;
	}
	/**
	 * 获取：编号
	 */
	public Integer getCommunityArticleId() {
		return communityArticleId;
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
	 * 设置：商家编号
	 */
	public void setStoreUserId(Integer storeUserId) {
		this.storeUserId = storeUserId;
	}
	/**
	 * 获取：商家编号
	 */
	public Integer getStoreUserId() {
		return storeUserId;
	}
	/**
	 * 设置：分类编号
	 */
	public void setGoodsClassifyId(Integer goodsClassifyId) {
		this.goodsClassifyId = goodsClassifyId;
	}
	/**
	 * 获取：分类编号
	 */
	public Integer getGoodsClassifyId() {
		return goodsClassifyId;
	}
	/**
	 * 设置：描述
	 */
	public void setDes(String des) {
		this.des = des;
	}
	/**
	 * 获取：描述
	 */
	public String getDes() {
		return des;
	}
	/**
	 * 设置：分享次数
	 */
	public void setShares(Integer shares) {
		this.shares = shares;
	}
	/**
	 * 获取：分享次数
	 */
	public Integer getShares() {
		return shares;
	}
	/**
	 * 设置：启用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：启用
	 */
	public Integer getStatus() {
		return status;
	}
}
