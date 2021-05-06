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
 * 消息表
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-16 16:18:35
 */
@TableName("cd_message")
@ApiModel(value = "Message")
public class MessageEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public MessageEntity() {
		
	}
	
	public MessageEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 消息编号
	 */
	
	@TableId 					
	@ApiModelProperty(value = "消息编号",hidden = true)
	private Integer messageId;
	
	/**
	 * 用户编号
	 */
						
	@ApiModelProperty(value = "用户编号")
	private Integer memberId;
	
	/**
	 * 消息类型；0->卡包
	 */
						
	@ApiModelProperty(value = "消息类型；0->卡包")
	private Integer type;
	
	/**
	 * 消息ID
	 */
						
	@ApiModelProperty(value = "消息ID")
	private Integer typeId;
	
	/**
	 * 消息体
	 */
						
	@ApiModelProperty(value = "消息体")
	private String message;
	
	/**
	 * 是否阅读；0：未；1：已
	 */
						
	@ApiModelProperty(value = "是否阅读；0：未；1：已")
	private Integer isRead;
	
	/**
	 * 创建时间
	 */
							
	@TableField(fill = FieldFill.INSERT) 
	@ApiModelProperty(value = "创建时间",hidden = true)
	private Integer createTime;
	
	/**
	 * 设置：消息编号
	 */
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	/**
	 * 获取：消息编号
	 */
	public Integer getMessageId() {
		return messageId;
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
	 * 设置：消息类型；0->卡包
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：消息类型；0->卡包
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：消息ID
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	/**
	 * 获取：消息ID
	 */
	public Integer getTypeId() {
		return typeId;
	}
	/**
	 * 设置：消息体
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * 获取：消息体
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * 设置：是否阅读；0：未；1：已
	 */
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	/**
	 * 获取：是否阅读；0：未；1：已
	 */
	public Integer getIsRead() {
		return isRead;
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
