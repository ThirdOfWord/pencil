package com.freeter.modules.good.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationTargetException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.io.Serializable;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;



/**
 * 商品收藏

 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-06-06 10:46:53
 */
@TableName("cd_collect_goods")
@ApiModel(value = "CollectGoods")
public class CollectGoodsEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	public CollectGoodsEntity() {
		
	}
	public CollectGoodsEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@TableId 					
	@ApiModelProperty(value = "",hidden = true)
	private Long id;
	
	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号")
	private Integer memberId;
	
	/**
	 * 平台id
	 */
			
	@NotNull (message = "平台id不能为空") 				
	@ApiModelProperty(value = "平台id")
	private Integer supplierId;
	
	/**
	 * 商品id
	 */
			
	@NotNull (message = "商品id不能为空") 				
	@ApiModelProperty(value = "商品id")
	private Long goodsId;
	
	/**
	 * 商品名称
	 */
						
	@ApiModelProperty(value = "商品名称")
	private String goodsName;
	
	/**
	 * 商品主图
	 */
						
	@ApiModelProperty(value = "商品主图")
	private String goodsImageUrl;
	
	/**
	 * 销量
	 */
						
	@ApiModelProperty(value = "销量")
	private String salesTip;
	
	/**
	 * 最低价
	 */
						
	@ApiModelProperty(value = "最低价")
	private String minPrice;
	
	/**
	 * 优惠卷面额
	 */
						
	@ApiModelProperty(value = "优惠卷面额")
	private String couponDiscount;
	
	/**
	 * 佣金
	 */
						
	@ApiModelProperty(value = "佣金")
	private String promotionAmount;

	@ApiModelProperty(value = "原价")
	private String price;
	/**
	 * 
	 */
							
	//@TableField(fill = FieldFill.INSERT)
	//@ApiModelProperty(value = "",hidden = true)
	private Integer createTime;


	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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
	 * 设置：平台id
	 */
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * 获取：平台id
	 */
	public Integer getSupplierId() {
		return supplierId;
	}
	/**
	 * 设置：商品id
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取：商品id
	 */
	public Long getGoodsId() {
		return goodsId;
	}
	/**
	 * 设置：商品名称
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	/**
	 * 获取：商品名称
	 */
	public String getGoodsName() {
		return goodsName;
	}
	/**
	 * 设置：商品主图
	 */
	public void setGoodsImageUrl(String goodsImageUrl) {
		this.goodsImageUrl = goodsImageUrl;
	}
	/**
	 * 获取：商品主图
	 */
	public String getGoodsImageUrl() {
		return goodsImageUrl;
	}
	/**
	 * 设置：销量
	 */
	public void setSalesTip(String salesTip) {
		this.salesTip = salesTip;
	}
	/**
	 * 获取：销量
	 */
	public String getSalesTip() {
		return salesTip;
	}

	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

	public String getCouponDiscount() {
		return couponDiscount;
	}

	public void setCouponDiscount(String couponDiscount) {
		this.couponDiscount = couponDiscount;
	}

	public String getPromotionAmount() {
		return promotionAmount;
	}

	public void setPromotionAmount(String promotionAmount) {
		this.promotionAmount = promotionAmount;
	}

	/**
	 * 设置：
	 */
	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Integer getCreateTime() {
		return createTime;
	}
}
