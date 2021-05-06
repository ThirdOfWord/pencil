package com.freeter.modules.answer.entity.vo;


import com.baomidou.mybatisplus.annotations.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 题库问题
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-14 14:05:57
 */
@ApiModel(value = "AnswerQuestionVO")
public class AnswerQuestionVO  implements Serializable {
	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "",hidden = true)
	private Integer answerQuestionId;

	/**
	 * 所属分类
	 */
	@NotNull(message = "所属分类不能为空")
	@ApiModelProperty(value = "所属分类")
	private Integer answerCateId;
	/**
	 * 问题
	 */
	
	@ApiModelProperty(value = "问题") 
	private String title;
	@NotNull (message = "类型不能为空")
	@ApiModelProperty(value = "类型")
	private Integer type;

	public Integer getAnswerQuestionId() {
		return answerQuestionId;
	}

	public void setAnswerQuestionId(Integer answerQuestionId) {
		this.answerQuestionId = answerQuestionId;
	}

	public Integer getAnswerCateId() {
		return answerCateId;
	}

	public void setAnswerCateId(Integer answerCateId) {
		this.answerCateId = answerCateId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 设置：问题
	 */
	 
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 获取：问题
	 */
	public String getTitle() {
		return title;
	}

			
}
