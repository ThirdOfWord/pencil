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

import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;



/**
 * 微信用户
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-07 16:14:11
 */
@TableName("cd_weixin_member")
@ApiModel(value = "WeixinMember")
public class WeixinMemberEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public WeixinMemberEntity() {
		
	}
	
	public WeixinMemberEntity(T t) {
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
	private Integer weixinMemberId;
	
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
	
	/**
	 * openId
	 */
						
	@ApiModelProperty(value = "openId")
	private String openid;
	
	/**
	 * unionId
	 */
						
	@ApiModelProperty(value = "unionId")
	private String unionid;
	
	/**
	 * 昵称
	 */
						
	@ApiModelProperty(value = "昵称")
	private String nickname;
	
	/**
	 * 性别
	 */
			
	@NotNull (message = "性别不能为空") 				
	@ApiModelProperty(value = "性别")
	private Integer sex;
	
	/**
	 * 头像
	 */
						
	@ApiModelProperty(value = "头像")
	private String headimgurl;
	
	/**
	 * 授权token
	 */
						
	@ApiModelProperty(value = "授权token")
	private String accessToken;
	
	/**
	 * 过期时间
	 */
						
	@ApiModelProperty(value = "过期时间")
	private Integer expiresIn;
	
	/**
	 * 刷新token
	 */
						
	@ApiModelProperty(value = "刷新token")
	private String refreshToken;
	
	/**
	 * 国家
	 */
						
	@ApiModelProperty(value = "国家")
	private String country;
	
	/**
	 * 省
	 */
						
	@ApiModelProperty(value = "省")
	private String province;
	
	/**
	 * 市
	 */
						
	@ApiModelProperty(value = "市")
	private String city;
	
	/**
	 * 用户ID
	 */
			
	@NotNull (message = "用户ID不能为空") 				
	@ApiModelProperty(value = "用户ID")
	private Integer memberId;
	
	/**
	 * 设置：
	 */
	public void setWeixinMemberId(Integer weixinMemberId) {
		this.weixinMemberId = weixinMemberId;
	}
	/**
	 * 获取：
	 */
	public Integer getWeixinMemberId() {
		return weixinMemberId;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Long getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Long getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：openId
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * 获取：openId
	 */
	public String getOpenid() {
		return openid;
	}
	/**
	 * 设置：unionId
	 */
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	/**
	 * 获取：unionId
	 */
	public String getUnionid() {
		return unionid;
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
	 * 设置：性别
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * 设置：头像
	 */
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	/**
	 * 获取：头像
	 */
	public String getHeadimgurl() {
		return headimgurl;
	}
	/**
	 * 设置：授权token
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	/**
	 * 获取：授权token
	 */
	public String getAccessToken() {
		return accessToken;
	}
	/**
	 * 设置：过期时间
	 */
	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}
	/**
	 * 获取：过期时间
	 */
	public Integer getExpiresIn() {
		return expiresIn;
	}
	/**
	 * 设置：刷新token
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	/**
	 * 获取：刷新token
	 */
	public String getRefreshToken() {
		return refreshToken;
	}
	/**
	 * 设置：国家
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * 获取：国家
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * 设置：省
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 获取：省
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * 设置：市
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取：市
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 设置：用户ID
	 */
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：用户ID
	 */
	public Integer getMemberId() {
		return memberId;
	}
}
