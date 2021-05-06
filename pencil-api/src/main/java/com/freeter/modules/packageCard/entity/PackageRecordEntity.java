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
 * 卡包领取记录
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-04-27 18:29:40
 */
@TableName("cd_package_record")
@ApiModel(value = "PackageRecord")
public class PackageRecordEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public PackageRecordEntity() {
		
	}
	
	public PackageRecordEntity(T t) {
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
	private Integer packageRecordId;
	
	/**
	 * 创建时间
	 */
							
	@TableField(fill = FieldFill.INSERT) 
	@ApiModelProperty(value = "创建时间",hidden = true)
	private Integer createTime;
	
	/**
	 * 所属用户
	 */
			
	@NotNull (message = "所属用户不能为空") 				
	@ApiModelProperty(value = "所属用户")
	private Integer memberId;
	
	/**
	 * 昵称
	 */
						
	@ApiModelProperty(value = "昵称")
	private String nickname;
	
	/**
	 * 手机号
	 */
						
	@ApiModelProperty(value = "手机号")
	private String mobile;
	
	/**
	 * 所属卡包
	 */
			
	@NotNull (message = "所属卡包不能为空") 				
	@ApiModelProperty(value = "所属卡包")
	private Integer packageId;
	
	/**
	 * 卡包
	 */
						
	@ApiModelProperty(value = "卡包")
	private String packageTitle;
	
	/**
	 * 数量
	 */
			
	@NotNull (message = "数量不能为空") 				
	@ApiModelProperty(value = "数量")
	private Integer num;
	
	/**
	 * 设置：
	 */
	public void setPackageRecordId(Integer packageRecordId) {
		this.packageRecordId = packageRecordId;
	}
	/**
	 * 获取：
	 */
	public Integer getPackageRecordId() {
		return packageRecordId;
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
	/**
	 * 设置：昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 获取：昵称
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * 设置：手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：所属卡包
	 */
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}
	/**
	 * 获取：所属卡包
	 */
	public Integer getPackageId() {
		return packageId;
	}
	/**
	 * 设置：卡包
	 */
	public void setPackageTitle(String packageTitle) {
		this.packageTitle = packageTitle;
	}
	/**
	 * 获取：卡包
	 */
	public String getPackageTitle() {
		return packageTitle;
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
}
