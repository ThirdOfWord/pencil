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
 * 社区推广商品
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-14 17:14:38
 */
@TableName("cd_community_article_goods")
@ApiModel(value = "CommunityArticleGoods")
public class CommunityArticleGoodsEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public CommunityArticleGoodsEntity() {
		
	}
	
	public CommunityArticleGoodsEntity(T t) {
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
	private Integer communityArticleGoodsId;
	
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
	 * 推广编号
	 */
			
	@NotNull (message = "推广编号不能为空") 				
	@ApiModelProperty(value = "推广编号")
	private Integer communityArticleId;
	
	/**
	 * 标题
	 */
						
	@ApiModelProperty(value = "标题")
	private String title;
	
	/**
	 * 商品主图
	 */
						
	@ApiModelProperty(value = "商品主图")
	private String thumb;
	
	/**
	 * 分享
	 */
			
	@NotNull (message = "分享不能为空") 				
	@ApiModelProperty(value = "分享")
	private Integer shares;
	
	/**
	 * 链接
	 */
						
	@ApiModelProperty(value = "链接")
	private String url;
	
	/**
	 * 关联商品编号
	 */
			
	@NotNull (message = "关联商品编号不能为空") 				
	@ApiModelProperty(value = "关联商品编号")
	private Integer goodsId;
	
	/**
	 * 口令
	 */
						
	@ApiModelProperty(value = "口令")
	private String token;
	
	/**
	 * 设置：
	 */
	public void setCommunityArticleGoodsId(Integer communityArticleGoodsId) {
		this.communityArticleGoodsId = communityArticleGoodsId;
	}
	/**
	 * 获取：
	 */
	public Integer getCommunityArticleGoodsId() {
		return communityArticleGoodsId;
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
	 * 设置：推广编号
	 */
	public void setCommunityArticleId(Integer communityArticleId) {
		this.communityArticleId = communityArticleId;
	}
	/**
	 * 获取：推广编号
	 */
	public Integer getCommunityArticleId() {
		return communityArticleId;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：商品主图
	 */
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	/**
	 * 获取：商品主图
	 */
	public String getThumb() {
		return thumb;
	}
	/**
	 * 设置：分享
	 */
	public void setShares(Integer shares) {
		this.shares = shares;
	}
	/**
	 * 获取：分享
	 */
	public Integer getShares() {
		return shares;
	}
	/**
	 * 设置：链接
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：链接
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：关联商品编号
	 */
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取：关联商品编号
	 */
	public Integer getGoodsId() {
		return goodsId;
	}
	/**
	 * 设置：口令
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * 获取：口令
	 */
	public String getToken() {
		return token;
	}
}
