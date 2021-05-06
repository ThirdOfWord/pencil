package com.freeter.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;


/**
 * 
 * 数据库通用操作实体类（普通增删改查）
 * @author xuchen
 * @email 171998110@qq.com
 * @date 2019-05-26 13:45:01
 */
@TableName("my_log_sms")
@ApiModel(value = "MyLogSms")
public class MyLogSmsEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public MyLogSmsEntity() {
		
	}
	
	public MyLogSmsEntity(T t) {
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
	private Integer id;
	
	/**
	 * 发送者ID
	 */
						
	@ApiModelProperty(value = "发送者ID")
	private Integer smsuserid;
	
	/**
	 * 短信内容
	 */
						
	@ApiModelProperty(value = "短信内容")
	private String smscontent;
	
	/**
	 * 短信号码
	 */
						
	@ApiModelProperty(value = "短信号码")
	private String smsphonenumber;
	
	/**
	 * 短信返回值
	 */
						
	@ApiModelProperty(value = "短信返回值")
	private String smsreturncode;
	
	/**
	 * 短信中的验证码
	 */
						
	@ApiModelProperty(value = "短信中的验证码")
	private String smscode;
	
	/**
	 * 调用短信的接口
	 */
						
	@ApiModelProperty(value = "调用短信的接口")
	private String 	smsfunc;
	
	/**
	 * IP地址
	 */
						
	@ApiModelProperty(value = "IP地址")
	private String smsip;
	
	/**
	 * 创建时间
	 */
					
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 		
	@ApiModelProperty(value = "创建时间")
	private Date createtime;
	
	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：发送者ID
	 */
	public void setSmsuserid(Integer smsuserid) {
		this.smsuserid = smsuserid;
	}
	/**
	 * 获取：发送者ID
	 */
	public Integer getSmsuserid() {
		return smsuserid;
	}
	/**
	 * 设置：短信内容
	 */
	public void setSmscontent(String smscontent) {
		this.smscontent = smscontent;
	}
	/**
	 * 获取：短信内容
	 */
	public String getSmscontent() {
		return smscontent;
	}
	/**
	 * 设置：短信号码
	 */
	public void setSmsphonenumber(String smsphonenumber) {
		this.smsphonenumber = smsphonenumber;
	}
	/**
	 * 获取：短信号码
	 */
	public String getSmsphonenumber() {
		return smsphonenumber;
	}
	/**
	 * 设置：短信返回值
	 */
	public void setSmsreturncode(String smsreturncode) {
		this.smsreturncode = smsreturncode;
	}
	/**
	 * 获取：短信返回值
	 */
	public String getSmsreturncode() {
		return smsreturncode;
	}
	/**
	 * 设置：短信中的验证码
	 */
	public void setSmscode(String smscode) {
		this.smscode = smscode;
	}
	/**
	 * 获取：短信中的验证码
	 */
	public String getSmscode() {
		return smscode;
	}

	/**
	 * 设置：IP地址
	 */
	public void setSmsip(String smsip) {
		this.smsip = smsip;
	}
	/**
	 * 获取：IP地址
	 */
	public String getSmsip() {
		return smsip;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreatetime() {
		return createtime;
	}

	public String getSmsfunc() {
		return smsfunc;
	}

	public void setSmsfunc(String smsfunc) {
		this.smsfunc = smsfunc;
	}
}
