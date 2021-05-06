package com.freeter.modules.advert.entity;

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
 * 文章列表
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-24 22:06:57
 */
@TableName("cd_article")
@ApiModel(value = "Article")
public class ArticleEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ArticleEntity() {
		
	}
	
	public ArticleEntity(T t) {
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
	private Integer articleId;
	
	/**
	 * 创建时间
	 */
							
	@TableField(fill = FieldFill.INSERT) 
	@ApiModelProperty(value = "创建时间",hidden = true)
	private Integer createTime;
	
	/**
	 * 更新时间
	 */
						
	/*@TableField(fill = FieldFill.UPDATE)
	@ApiModelProperty(value = "更新时间",hidden = true)
	private Integer updateTime;*/
	
	/**
	 * 所属分类
	 */
			
	/*@NotNull (message = "所属分类不能为空")
	@ApiModelProperty(value = "所属分类")
	private Integer articleCategoryId;*/
	
	/**
	 * 标题
	 */
						
	@ApiModelProperty(value = "标题")
	private String title;
	
	/**
	 * 启用
	 */
			
	/*@NotNull (message = "启用不能为空")
	@ApiModelProperty(value = "启用")
	private Integer status;*/
	
	/**
	 * 内容
	 */
						
	@ApiModelProperty(value = "内容")
	private String content;
	
	/**
	 * 设置：
	 */
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	/**
	 * 获取：
	 */
	public Integer getArticleId() {
		return articleId;
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
	 * 设置：内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：内容
	 */
	public String getContent() {
		return content;
	}
}
