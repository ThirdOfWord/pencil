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
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;



/**
 * 淘礼金
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-08-08 11:19:04
 */
@TableName("cd_goods_gold_rush")
@ApiModel(value = "GoodsGoldRush")
public class GoodsGoldRushEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public GoodsGoldRushEntity() {
		
	}
	
	public GoodsGoldRushEntity(T t) {
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
	private Integer goodsGoldRushId;
	
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
	 * 排序
	 */
			
	@NotNull (message = "排序不能为空") 				
	@ApiModelProperty(value = "排序")
	private Integer sortid;
	
	/**
	 * 启用
	 */
			
	@NotNull (message = "启用不能为空") 				
	@ApiModelProperty(value = "启用")
	private Integer status;
	
	/**
	 * 妈妈广告位Id
	 */
						
	@ApiModelProperty(value = "妈妈广告位Id")
	private String adzoneId;
	
	/**
	 * 宝贝id
	 */
						
	@ApiModelProperty(value = "宝贝id")
	private String itemId;
	
	/**
	 * 数量
	 */
			
	@NotNull (message = "数量不能为空") 				
	@ApiModelProperty(value = "数量")
	private Integer totalNum;
	
	/**
	 * 领取上线
	 */
			
	@NotNull (message = "领取上线不能为空") 				
	@ApiModelProperty(value = "领取上线")
	private Integer userTotalWinNumLimit;
	
	/**
	 * 淘礼金名称
	 */
						
	@ApiModelProperty(value = "淘礼金名称")
	private String name;
	
	/**
	 * 面额
	 */
						
	@ApiModelProperty(value = "面额")
	private BigDecimal perFace;
	
	/**
	 * 发放开始时间
	 */
						
	@ApiModelProperty(value = "发放开始时间")
	private Integer sendStartTime;
	
	/**
	 * 发放结束时间
	 */
						
	@ApiModelProperty(value = "发放结束时间")
	private Integer sendEndTime;
	/**
	 * 缩略图
	 */
						
	@ApiModelProperty(value = "缩略图")
	private String thumb;
	
	/**
	 * 领取链接
	 */
						
	@ApiModelProperty(value = "领取链接")
	private String urlLink;
	
	/**
	 * 请求验证
	 */
			
	@NotNull (message = "请求验证不能为空") 				
	@ApiModelProperty(value = "请求验证")
	private Integer postCode;
	
	/**
	 * 设置：
	 */
	public void setGoodsGoldRushId(Integer goodsGoldRushId) {
		this.goodsGoldRushId = goodsGoldRushId;
	}
	/**
	 * 获取：
	 */
	public Integer getGoodsGoldRushId() {
		return goodsGoldRushId;
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
	 * 设置：排序
	 */
	public void setSortid(Integer sortid) {
		this.sortid = sortid;
	}
	/**
	 * 获取：排序
	 */
	public Integer getSortid() {
		return sortid;
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
	/**
	 * 设置：妈妈广告位Id
	 */
	public void setAdzoneId(String adzoneId) {
		this.adzoneId = adzoneId;
	}
	/**
	 * 获取：妈妈广告位Id
	 */
	public String getAdzoneId() {
		return adzoneId;
	}
	/**
	 * 设置：宝贝id
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	/**
	 * 获取：宝贝id
	 */
	public String getItemId() {
		return itemId;
	}
	/**
	 * 设置：数量
	 */
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	/**
	 * 获取：数量
	 */
	public Integer getTotalNum() {
		return totalNum;
	}
	/**
	 * 设置：领取上线
	 */
	public void setUserTotalWinNumLimit(Integer userTotalWinNumLimit) {
		this.userTotalWinNumLimit = userTotalWinNumLimit;
	}
	/**
	 * 获取：领取上线
	 */
	public Integer getUserTotalWinNumLimit() {
		return userTotalWinNumLimit;
	}
	/**
	 * 设置：淘礼金名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：淘礼金名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：面额
	 */
	public void setPerFace(BigDecimal perFace) {
		this.perFace = perFace;
	}
	/**
	 * 获取：面额
	 */
	public BigDecimal getPerFace() {
		return perFace;
	}
	/**
	 * 设置：发放开始时间
	 */
	public void setSendStartTime(Integer sendStartTime) {
		this.sendStartTime = sendStartTime;
	}
	/**
	 * 获取：发放开始时间
	 */
	public Integer getSendStartTime() {
		return sendStartTime;
	}
	/**
	 * 设置：发放结束时间
	 */
	public void setSendEndTime(Integer sendEndTime) {
		this.sendEndTime = sendEndTime;
	}
	/**
	 * 获取：发放结束时间
	 */
	public Integer getSendEndTime() {
		return sendEndTime;
	}
	/**
	 * 设置：缩略图
	 */
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	/**
	 * 获取：缩略图
	 */
	public String getThumb() {
		return thumb;
	}
	/**
	 * 设置：领取链接
	 */
	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}
	/**
	 * 获取：领取链接
	 */
	public String getUrlLink() {
		return urlLink;
	}
	/**
	 * 设置：请求验证
	 */
	public void setPostCode(Integer postCode) {
		this.postCode = postCode;
	}
	/**
	 * 获取：请求验证
	 */
	public Integer getPostCode() {
		return postCode;
	}
}
