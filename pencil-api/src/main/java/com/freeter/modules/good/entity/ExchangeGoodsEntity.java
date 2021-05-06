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
 * 兑换商品
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-05 16:23:56
 */
@TableName("cd_exchange_goods")
@ApiModel(value = "ExchangeGoods")
public class ExchangeGoodsEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ExchangeGoodsEntity() {
		
	}
	
	public ExchangeGoodsEntity(T t) {
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
	private Integer exchangeGoodsId;
	
	/**
	 * 排序
	 */
						
	@ApiModelProperty(value = "排序")
	private Integer sortid;
	
	/**
	 * 标题
	 */
						
	@ApiModelProperty(value = "标题")
	private String title;
	
	/**
	 * 主图
	 */
						
	@ApiModelProperty(value = "主图")
	private String thumb;
	
	/**
	 * 库存
	 */
						
	@ApiModelProperty(value = "库存")
	private Integer stock;
	
	/**
	 * 销量
	 */
						
	@ApiModelProperty(value = "销量")
	private Integer sales;
	
	/**
	 * 属性规格
	 */
						
	@ApiModelProperty(value = "属性规格")
	private Integer isAttrStatus;
	
	/**
	 * 属性编号
	 */
						
	@ApiModelProperty(value = "属性编号")
	private String attrIdStr;
	
	/**
	 * 所属答题卡
	 */
						
	@ApiModelProperty(value = "所属答题卡")
	private Integer answerClassifyId;
	
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
	 * 上架
	 */
						
	@ApiModelProperty(value = "上架")
	private Integer status;
	
	/**
	 * 设置：
	 */
	public void setExchangeGoodsId(Integer exchangeGoodsId) {
		this.exchangeGoodsId = exchangeGoodsId;
	}
	/**
	 * 获取：
	 */
	public Integer getExchangeGoodsId() {
		return exchangeGoodsId;
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
	 * 设置：主图
	 */
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	/**
	 * 获取：主图
	 */
	public String getThumb() {
		return thumb;
	}
	/**
	 * 设置：库存
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	/**
	 * 获取：库存
	 */
	public Integer getStock() {
		return stock;
	}
	/**
	 * 设置：销量
	 */
	public void setSales(Integer sales) {
		this.sales = sales;
	}
	/**
	 * 获取：销量
	 */
	public Integer getSales() {
		return sales;
	}
	/**
	 * 设置：属性规格
	 */
	public void setIsAttrStatus(Integer isAttrStatus) {
		this.isAttrStatus = isAttrStatus;
	}
	/**
	 * 获取：属性规格
	 */
	public Integer getIsAttrStatus() {
		return isAttrStatus;
	}
	/**
	 * 设置：属性编号
	 */
	public void setAttrIdStr(String attrIdStr) {
		this.attrIdStr = attrIdStr;
	}
	/**
	 * 获取：属性编号
	 */
	public String getAttrIdStr() {
		return attrIdStr;
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
	 * 设置：上架
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：上架
	 */
	public Integer getStatus() {
		return status;
	}
}
