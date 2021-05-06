package com.freeter.modules.packageCard.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 卡包卡片
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-04-27 18:29:40
 */
@ApiModel(value = "PackageCardVO")
public class PackageCardVO  implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 所属卡片
	 */
	@ApiModelProperty(value = "所属卡片")
	private Integer answerClassifyId;
	/**
	 * 数量
	 */
	@ApiModelProperty(value = "数量") 
	private Integer num;
	@ApiModelProperty(value = "奖金")
	private BigDecimal awardMoney;
	@ApiModelProperty(value = "答题卡标题")
	private String title;
	@ApiModelProperty(value = "答题卡图片")
	private String thumb;


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public BigDecimal getAwardMoney() {
		return awardMoney;
	}

	public void setAwardMoney(BigDecimal awardMoney) {
		this.awardMoney = awardMoney;
	}

	/**
	 * 设置：所属卡片
	 */
	 
	public void setAnswerClassifyId(Integer answerClassifyId) {
		this.answerClassifyId = answerClassifyId;
	}
	
	/**
	 * 获取：所属卡片
	 */
	public Integer getAnswerClassifyId() {
		return answerClassifyId;
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
