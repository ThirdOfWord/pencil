package com.freeter.modules.user.entity;

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
 * 用户地址
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-10 15:14:57
 */
@TableName("cd_member_address")
@ApiModel(value = "MemberAddress")
public class MemberAddressEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public MemberAddressEntity() {
		
	}
	
	public MemberAddressEntity(T t) {
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
	private Integer memberAddressId;
	
	/**
	 * 用户编号
	 */
			
	@NotNull (message = "用户编号不能为空") 				
	@ApiModelProperty(value = "用户编号")
	private Integer memberId;
	
	/**
	 * 姓名
	 */
						
	@ApiModelProperty(value = "姓名")
	private String name;
	
	/**
	 * 手机号
	 */
						
	@ApiModelProperty(value = "手机号")
	private String mobile;
	
	/**
	 * 省市区
	 */
						
	@ApiModelProperty(value = "省市区")
	private String province;
	
	/**
	 * 省市区
	 */
						
	@ApiModelProperty(value = "省市区")
	private String city;
	
	/**
	 * 省市区
	 */
						
	@ApiModelProperty(value = "省市区")
	private String district;
	
	/**
	 * 详细地址
	 */
						
	@ApiModelProperty(value = "详细地址")
	private String content;
	
	/**
	 * 默认
	 */
			
	@NotNull (message = "默认不能为空") 				
	@ApiModelProperty(value = "默认")
	private Integer isDef;

	/**
	 * 创建时间
	 */

	@TableField(fill = FieldFill.INSERT)
	@ApiModelProperty(value = "创建时间",hidden = true)
	private Long createTime;

	/**
	 * 更新时间
	 */

	@TableField(fill = FieldFill.UPDATE)
	@ApiModelProperty(value = "更新时间",hidden = true)
	private Long updateTime;

   /* public Long getCreateTime() {
        return createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }*/

    /**
	 * 设置：
	 */
	public void setMemberAddressId(Integer memberAddressId) {
		this.memberAddressId = memberAddressId;
	}
	/**
	 * 获取：
	 */
	public Integer getMemberAddressId() {
		return memberAddressId;
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
	 * 设置：省市区
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 获取：省市区
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * 设置：省市区
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取：省市区
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 设置：省市区
	 */
	public void setDistrict(String district) {
		this.district = district;
	}
	/**
	 * 获取：省市区
	 */
	public String getDistrict() {
		return district;
	}
	/**
	 * 设置：详细地址
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：详细地址
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：默认
	 */
	public void setIsDef(Integer isDef) {
		this.isDef = isDef;
	}
	/**
	 * 获取：默认
	 */
	public Integer getIsDef() {
		return isDef;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

}
