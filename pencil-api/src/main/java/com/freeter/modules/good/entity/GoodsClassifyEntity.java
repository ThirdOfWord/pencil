package com.freeter.modules.good.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationTargetException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import org.apache.commons.beanutils.BeanUtils;




/**
 * 商品分类
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-09 10:55:26
 */
@TableName("cd_goods_classify")
@ApiModel(value = "GoodsClassify")
public class GoodsClassifyEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public GoodsClassifyEntity() {
		
	}
	
	public GoodsClassifyEntity(T t) {
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
	private Integer goodsClassifyId;
	
	/**
	 * 上级分类
	 */
			
	@NotNull (message = "上级分类不能为空") 				
	@ApiModelProperty(value = "上级分类")
	private Integer pid;
	
	/**
	 * 标题
	 */
						
	@ApiModelProperty(value = "标题")
	private String title;
	
	/**
	 * 缩略图
	 */
						
	@ApiModelProperty(value = "缩略图")
	private String thumb;

	@ApiModelProperty(value = "淘宝分类id")
	private String cat;

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	/**
	 * 设置：
	 */
	public void setGoodsClassifyId(Integer goodsClassifyId) {
		this.goodsClassifyId = goodsClassifyId;
	}
	/**
	 * 获取：
	 */
	public Integer getGoodsClassifyId() {
		return goodsClassifyId;
	}
	/**
	 * 设置：上级分类
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	/**
	 * 获取：上级分类
	 */
	public Integer getPid() {
		return pid;
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

}
