package com.freeter.modules.packageCard.entity;

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
 * 卡包领取记录卡片
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-04-27 18:29:40
 */
@TableName("cd_package_record_card")
@ApiModel(value = "PackageRecordCard")
public class PackageRecordCardEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public PackageRecordCardEntity() {
		
	}
	
	public PackageRecordCardEntity(T t) {
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
	private Integer packageRecordCardId;
	
	/**
	 * 创建时间
	 */
							
	@TableField(fill = FieldFill.INSERT) 
	@ApiModelProperty(value = "创建时间",hidden = true)
	private Integer createTime;
	
	/**
	 * 所属记录
	 */
						
	@ApiModelProperty(value = "所属记录")
	private Integer packageRecordId;
	
	/**
	 * 所属卡
	 */
			
	@NotNull (message = "所属卡不能为空") 				
	@ApiModelProperty(value = "所属卡")
	private Integer answerClassifyId;
	
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
	
	/**
	 * 数量
	 */
			
	@NotNull (message = "数量不能为空") 				
	@ApiModelProperty(value = "数量")
	private Integer num;
	
	/**
	 * 所属用户
	 */
			
	@NotNull (message = "所属用户不能为空") 				
	@ApiModelProperty(value = "所属用户")
	private Integer memberId;
	
	/**
	 * 设置：
	 */
	public void setPackageRecordCardId(Integer packageRecordCardId) {
		this.packageRecordCardId = packageRecordCardId;
	}
	/**
	 * 获取：
	 */
	public Integer getPackageRecordCardId() {
		return packageRecordCardId;
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
	 * 设置：所属记录
	 */
	public void setPackageRecordId(Integer packageRecordId) {
		this.packageRecordId = packageRecordId;
	}
	/**
	 * 获取：所属记录
	 */
	public Integer getPackageRecordId() {
		return packageRecordId;
	}
	/**
	 * 设置：所属卡
	 */
	public void setAnswerClassifyId(Integer answerClassifyId) {
		this.answerClassifyId = answerClassifyId;
	}
	/**
	 * 获取：所属卡
	 */
	public Integer getAnswerClassifyId() {
		return answerClassifyId;
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
	/**
	 * 设置：数量
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	/**
	 * 获取：数量
	 */
	public Integer getNum() {
		return num;
	}
	/**
	 * 设置：所属用户
	 */
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：所属用户
	 */
	public Integer getMemberId() {
		return memberId;
	}
}
