package com.freeter.modules.packageCard.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 卡包领取记录卡片
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-04-27 18:29:40
 */
@ApiModel(value = "PackageRecordCardVO")
public class PackageRecordCardVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 				
	/**
	 * 所属记录
	 */
	
	@ApiModelProperty(value = "所属记录") 
	private Integer packageRecordId;
		
	/**
	 * 所属卡
	 */
	
	@ApiModelProperty(value = "所属卡") 
	private Integer answerClassifyId;
		
	/**
	 * 标题
	 */
	
	@ApiModelProperty(value = "标题") 
	private String title;
		
	/**
	 * 缩略图
	 */
	
	@ApiModelProperty(value = "缩略图") 
	private String thumb;
		
	/**
	 * 数量
	 */
	
	@ApiModelProperty(value = "数量") 
	private Integer num;
		
	/**
	 * 所属用户
	 */
	
	@ApiModelProperty(value = "所属用户") 
	private Integer memberId;
						
	
	/**
	 * 设置：所属记录
	 */
	 
	public void setPackageRecordId(Integer packageRecordId) {
		this.packageRecordId = packageRecordId;
	}
	
	/**
	 * 获取：所属记录
	 */
	public Integer getPackageRecordId() {
		return packageRecordId;
	}
				
	
	/**
	 * 设置：所属卡
	 */
	 
	public void setAnswerClassifyId(Integer answerClassifyId) {
		this.answerClassifyId = answerClassifyId;
	}
	
	/**
	 * 获取：所属卡
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
	 * 设置：缩略图
	 */
	 
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	
	/**
	 * 获取：缩略图
	 */
	public String getThumb() {
		return thumb;
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
			
}
