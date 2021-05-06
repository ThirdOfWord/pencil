package com.freeter.modules.answer.entity.query;


import com.freeter.common.annotation.OwnerTable;
import com.freeter.modules.answer.entity.AnswerClassifyEntity;
import com.freeter.modules.good.entity.vo.ExchangeGoodsVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 显示兑换商品页面
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-09 17:08:04
 */
@OwnerTable(AnswerClassifyEntity.class)
@ApiModel(value = "AnswerClassifyQuery")
public class AnswerClassifyQuery  implements Serializable {
	private static final long serialVersionUID = 1L;

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

	@ApiModelProperty(value = "是否可兑换；0：否，1：兑换")
	private Integer isDuiHuanStatus;

	private List<ExchangeGoodsVO> goodsList;

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

	public Integer getAnswerClassifyId() {
		return answerClassifyId;
	}

	public void setAnswerClassifyId(Integer answerClassifyId) {
		this.answerClassifyId = answerClassifyId;
	}

	public Integer getIsDuiHuanStatus() {
		return isDuiHuanStatus;
	}

	public void setIsDuiHuanStatus(Integer isDuiHuanStatus) {
		this.isDuiHuanStatus = isDuiHuanStatus;
	}

	public List<ExchangeGoodsVO> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<ExchangeGoodsVO> goodsList) {
		this.goodsList = goodsList;
	}
}
