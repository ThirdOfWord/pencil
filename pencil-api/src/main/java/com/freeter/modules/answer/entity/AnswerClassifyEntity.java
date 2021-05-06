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
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;



/**
 * 答题分类
 * 数据库通用操作实体类（普通增删改查）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-14 14:05:57
 */
@TableName("cd_answer_classify")
@ApiModel(value = "AnswerClassify")
public class AnswerClassifyEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public AnswerClassifyEntity() {
		
	}
	
	public AnswerClassifyEntity(T t) {
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
	private Integer answerClassifyId;
	
	/**
	 * 标题
	 */
						
	@ApiModelProperty(value = "标题")
	private String title;
	
	/**
	 * 背景
	 */
						
	@ApiModelProperty(value = "背景")
	private String thumb;
	
	/**
	 * 启用
	 */
			
	@NotNull (message = "启用不能为空") 				
	@ApiModelProperty(value = "启用")
	private Integer status;
	
	/**
	 * 合成来源编号
	 */
						
	@ApiModelProperty(value = "合成来源编号")
	private Integer mergeId;

	/**
	 * 排序等级
	 */

	@ApiModelProperty(value = "排序等级")
	private Integer sortsid;

	/**
	 * 合成
	 */

	@NotNull (message = "合成不能为空")
	@ApiModelProperty(value = "合成")
	private Integer isMerge;

	/**
	 * 拆分
	 */

	@NotNull (message = "拆分不能为空")
	@ApiModelProperty(value = "拆分")
	private Integer isSplit;

	/**
	 * 兑换
	 */

	@NotNull (message = "兑换不能为空")
	@ApiModelProperty(value = "兑换")
	private Integer isExchange;

	/**
	 * 奖金
	 */

	@ApiModelProperty(value = "奖金")
	private BigDecimal awardMoney;

	@ApiModelProperty(value = "红包数量")
	private Integer bonusNum;
	@ApiModelProperty(value = "红包金额")
	private BigDecimal bonusMoney;

	public Integer getBonusNum() {
		return bonusNum;
	}

	public void setBonusNum(Integer bonusNum) {
		this.bonusNum = bonusNum;
	}

	public BigDecimal getBonusMoney() {
		return bonusMoney;
	}

	public void setBonusMoney(BigDecimal bonusMoney) {
		this.bonusMoney = bonusMoney;
	}

	/**
	 * 设置：
	 */
	public void setAnswerClassifyId(Integer answerClassifyId) {
		this.answerClassifyId = answerClassifyId;
	}
	/**
	 * 获取：
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
	 * 设置：背景
	 */
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	/**
	 * 获取：背景
	 */
	public String getThumb() {
		return thumb;
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
	 * 设置：合成来源编号
	 */
	public void setMergeId(Integer mergeId) {
		this.mergeId = mergeId;
	}
	/**
	 * 获取：合成来源编号
	 */
	public Integer getMergeId() {
		return mergeId;
	}
	/**
	 * 设置：排序等级
	 */
	public void setSortsid(Integer sortsid) {
		this.sortsid = sortsid;
	}
	/**
	 * 获取：排序等级
	 */
	public Integer getSortsid() {
		return sortsid;
	}
	/**
	 * 设置：合成
	 */
	public void setIsMerge(Integer isMerge) {
		this.isMerge = isMerge;
	}
	/**
	 * 获取：合成
	 */
	public Integer getIsMerge() {
		return isMerge;
	}
	/**
	 * 设置：拆分
	 */
	public void setIsSplit(Integer isSplit) {
		this.isSplit = isSplit;
	}
	/**
	 * 获取：拆分
	 */
	public Integer getIsSplit() {
		return isSplit;
	}
	/**
	 * 设置：兑换
	 */
	public void setIsExchange(Integer isExchange) {
		this.isExchange = isExchange;
	}
	/**
	 * 获取：兑换
	 */
	public Integer getIsExchange() {
		return isExchange;
	}
	/**
	 * 设置：奖金
	 */
	public void setAwardMoney(BigDecimal awardMoney) {
		this.awardMoney = awardMoney;
	}
	/**
	 * 获取：奖金
	 */
	public BigDecimal getAwardMoney() {
		return awardMoney;
	}
}
