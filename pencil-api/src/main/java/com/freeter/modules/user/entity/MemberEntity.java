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
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;



/**
 * 用户表
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-07 16:14:11
 */
@TableName("cd_member")
@ApiModel(value = "Member")
public class MemberEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public MemberEntity() {
		
	}
	
	public MemberEntity(T t) {
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
	private Integer memberId;
	
	/**
	 * 手机号
	 */
						
	@ApiModelProperty(value = "手机号")
	private String mobile;
	
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
	private Integer six;
	
	/**
	 * 邀请码
	 */
						
	@ApiModelProperty(value = "邀请码")
	private String inviteCode;
	@ApiModelProperty(value = "二维码")
	private String inviteQrcode;
	
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

	private Long deviceTime; //淘宝授权时间

	/**
	 * 余额
	 */
						
	@ApiModelProperty(value = "余额")
	private BigDecimal wallet;
	
	/**
	 * 删除
	 */
			
	@NotNull (message = "删除不能为空") 				
	@ApiModelProperty(value = "删除")
	private Integer isDel;
	
	/**
	 * 启用
	 */
			
	@NotNull (message = "启用不能为空") 				
	@ApiModelProperty(value = "启用")
	private Integer status;
	@ApiModelProperty(value = "头像")
	private String headimgurl;
	/**
	 * 姓名
	 */

	@ApiModelProperty(value = "姓名")
	private String name;

	/**
	 * 支付宝账号
	 */

	@ApiModelProperty(value = "支付宝账号")
	private String accountAlipay;

	@ApiModelProperty(value = "所属用户")
	private Integer pid;
	@ApiModelProperty(value = "预估收益")
	private BigDecimal walletEstimate;
	@ApiModelProperty(value = "今日收益")
	private BigDecimal walletToday;

	@ApiModelProperty(value = "淘宝渠道ID")
	private String relationId;

	@ApiModelProperty(value = "会员运营ID")
	private String specialId;

	@ApiModelProperty(value = "淘宝授权用户id")
	private String openId;

	@ApiModelProperty(value = "淘宝授权用户token")
	private String accessToken;

	private String deviceType;

	private String deviceValue;

	public String getDeviceType() {
		return deviceType;
	}

	public Long getDeviceTime() {
		return deviceTime;
	}

	public void setDeviceTime(Long deviceTime) {
		this.deviceTime = deviceTime;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceValue() {
		return deviceValue;
	}

	public void setDeviceValue(String deviceValue) {
		this.deviceValue = deviceValue;
	}

	public String getSpecialId() {
		return specialId;
	}

	public void setSpecialId(String specialId) {
		this.specialId = specialId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public BigDecimal getWalletEstimate() {
		return walletEstimate;
	}

	public void setWalletEstimate(BigDecimal walletEstimate) {
		this.walletEstimate = walletEstimate;
	}

	public BigDecimal getWalletToday() {
		return walletToday;
	}

	public void setWalletToday(BigDecimal walletToday) {
		this.walletToday = walletToday;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getHeadimgurl() {

		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getInviteQrcode() {
		return inviteQrcode;
	}

	public void setInviteQrcode(String inviteQrcode) {
		this.inviteQrcode = inviteQrcode;
	}

	/**
	 * 设置：
	 */
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：
	 */
	public Integer getMemberId() {
		return memberId;
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
	public void setSix(Integer six) {
		this.six = six;
	}
	/**
	 * 获取：性别
	 */
	public Integer getSix() {
		return six;
	}
	/**
	 * 设置：邀请码
	 */
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	/**
	 * 获取：邀请码
	 */
	public String getInviteCode() {
		return inviteCode;
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
	 * 设置：余额
	 */
	public void setWallet(BigDecimal wallet) {
		this.wallet = wallet;
	}
	/**
	 * 获取：余额
	 */
	public BigDecimal getWallet() {
		return wallet;
	}
	/**
	 * 设置：删除
	 */
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	/**
	 * 获取：删除
	 */
	public Integer getIsDel() {
		return isDel;
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
	 * 设置：支付宝账号
	 */
	public void setAccountAlipay(String accountAlipay) {
		this.accountAlipay = accountAlipay;
	}
	/**
	 * 获取：支付宝账号
	 */
	public String getAccountAlipay() {
		return accountAlipay;
	}
}
