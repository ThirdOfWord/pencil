package com.freeter.modules.taskOrder.entity;

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
 * 订单拉取失败日志
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-12-06 20:46:22
 */
@TableName("cd_goods_order_error_log")
@ApiModel(value = "GoodsOrderErrorLog")
public class GoodsOrderErrorLogEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public GoodsOrderErrorLogEntity() {
		
	}
	
	public GoodsOrderErrorLogEntity(T t) {
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
	private Long id;
	
	/**
	 * 开始时间
	 */
	@ApiModelProperty(value = "开始时间")
	private String startTime;
	
	/**
	 * 结束时间
	 */
	@ApiModelProperty(value = "结束时间")
	private String endTime;
	
	/**
	 * 异常内容
	 */
						
	@ApiModelProperty(value = "异常内容")
	private String errorLog;
	
	/**
	 * 获取订单的内容
	 */
						
	@ApiModelProperty(value = "获取订单的内容")
	private String content;
	
	/**
	 * 创建时间
	 */
					
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 			
	@TableField(fill = FieldFill.INSERT) 
	@ApiModelProperty(value = "创建时间",hidden = true)
	private Date createTime;
	
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
	 * 设置：开始时间
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：开始时间
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * 设置：结束时间
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：结束时间
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * 设置：异常内容
	 */
	public void setErrorLog(String errorLog) {
		this.errorLog = errorLog;
	}
	/**
	 * 获取：异常内容
	 */
	public String getErrorLog() {
		return errorLog;
	}
	/**
	 * 设置：获取订单的内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：获取订单的内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
