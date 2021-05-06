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
 * 
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-27 16:20:42
 */
@TableName("cd_supplier")
@ApiModel(value = "Supplier")
public class SupplierEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public SupplierEntity() {
		
	}
	
	public SupplierEntity(T t) {
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
	private Integer supplierId;
	
	/**
	 * 姓名
	 */
						
	@ApiModelProperty(value = "姓名")
	private String name;

	/**
	 * 启用
	 */
			
	@NotNull (message = "启用不能为空") 				
	@ApiModelProperty(value = "启用")
	private Integer status;
	
	/**
	 * accessToken
	 */
						
	@ApiModelProperty(value = "accessToken")
	private String accessToken;
	
	/**
	 * expiresAt
	 */
						
	@ApiModelProperty(value = "expiresAt")
	private Integer expiresAt;
	
	/**
	 * refreshTokenExpiresAt
	 */
						
	@ApiModelProperty(value = "refreshTokenExpiresAt")
	private Integer refreshTokenExpiresAt;
	
	/**
	 * refreshToken
	 */
						
	@ApiModelProperty(value = "refreshToken")
	private String refreshToken;
	
	/**
	 * ownerId
	 */
						
	@ApiModelProperty(value = "ownerId")
	private Integer ownerId;
	
	/**
	 * ownerName
	 */
						
	@ApiModelProperty(value = "ownerName")
	private String ownerName;
	
	/**
	 * scope
	 */
						
	@ApiModelProperty(value = "scope")
	private String scope;
	@ApiModelProperty(value = "饿了么的url")
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 设置：
	 */
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * 获取：
	 */
	public Integer getSupplierId() {
		return supplierId;
	}
	/**
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
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
	 * 设置：accessToken
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	/**
	 * 获取：accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}
	/**
	 * 设置：expiresAt
	 */
	public void setExpiresAt(Integer expiresAt) {
		this.expiresAt = expiresAt;
	}
	/**
	 * 获取：expiresAt
	 */
	public Integer getExpiresAt() {
		return expiresAt;
	}
	/**
	 * 设置：refreshTokenExpiresAt
	 */
	public void setRefreshTokenExpiresAt(Integer refreshTokenExpiresAt) {
		this.refreshTokenExpiresAt = refreshTokenExpiresAt;
	}
	/**
	 * 获取：refreshTokenExpiresAt
	 */
	public Integer getRefreshTokenExpiresAt() {
		return refreshTokenExpiresAt;
	}
	/**
	 * 设置：refreshToken
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	/**
	 * 获取：refreshToken
	 */
	public String getRefreshToken() {
		return refreshToken;
	}
	/**
	 * 设置：ownerId
	 */
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	/**
	 * 获取：ownerId
	 */
	public Integer getOwnerId() {
		return ownerId;
	}
	/**
	 * 设置：ownerName
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	/**
	 * 获取：ownerName
	 */
	public String getOwnerName() {
		return ownerName;
	}
	/**
	 * 设置：scope
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}
	/**
	 * 获取：scope
	 */
	public String getScope() {
		return scope;
	}
}
