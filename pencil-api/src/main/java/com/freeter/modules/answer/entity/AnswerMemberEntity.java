package com.freeter.modules.answer.entity;

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
 * 用户拥有的答题卡数
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-10 15:14:57
 */
@TableName("cd_answer_member")
@ApiModel(value = "AnswerMember")
public class AnswerMemberEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public AnswerMemberEntity() {
		
	}
	
	public AnswerMemberEntity(T t) {
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
	private Integer answerMemberId;
	
	/**
	 * 创建时间
	 */
							
	@TableField(fill = FieldFill.INSERT) 
	@ApiModelProperty(value = "创建时间",hidden = true)
	private Integer createTime;
	
	/**
	 * 修改时间
	 */
						
	@TableField(fill = FieldFill.UPDATE) 	
	@ApiModelProperty(value = "修改时间",hidden = true)
	private Integer updateTime;
	
	/**
	 * 用户编号
	 */
			
	@NotNull (message = "用户编号不能为空") 				
	@ApiModelProperty(value = "用户编号")
	private Integer memberId;
	
	/**
	 * 分类编号
	 */
			
	@NotNull (message = "分类编号不能为空") 				
	@ApiModelProperty(value = "分类编号")
	private Integer answerClassifyId;
	
	/**
	 * 数量
	 */
			
	@NotNull (message = "数量不能为空") 				
	@ApiModelProperty(value = "数量")
	private Integer total;
	
	/**
	 * 设置：
	 */
	public void setAnswerMemberId(Integer answerMemberId) {
		this.answerMemberId = answerMemberId;
	}
	/**
	 * 获取：
	 */
	public Integer getAnswerMemberId() {
		return answerMemberId;
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
	 * 设置：修改时间
	 */
	public void setUpdateTime(Integer updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Integer getUpdateTime() {
		return updateTime;
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
	 * 设置：分类编号
	 */
	public void setAnswerClassifyId(Integer answerClassifyId) {
		this.answerClassifyId = answerClassifyId;
	}
	/**
	 * 获取：分类编号
	 */
	public Integer getAnswerClassifyId() {
		return answerClassifyId;
	}
	/**
	 * 设置：数量
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}
	/**
	 * 获取：数量
	 */
	public Integer getTotal() {
		return total;
	}
}
