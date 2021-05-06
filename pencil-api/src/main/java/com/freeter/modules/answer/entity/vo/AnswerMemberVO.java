package com.freeter.modules.answer.entity.vo;

import com.baomidou.mybatisplus.annotations.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
 

/**
 * 用户拥有的答题卡数
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-14 13:47:07
 */
@ApiModel(value = "AnswerMemberVO")
public class AnswerMemberVO  implements Serializable {
	private static final long serialVersionUID = 1L;



	@ApiModelProperty(value = "",hidden = true)
	private Integer answerMemberId;
	/**
	 * 分类编号
	 */
	@ApiModelProperty(value = "分类编号") 
	private Integer answerClassifyId;

	@ApiModelProperty(value = "标题")
	private String title;

	/**
	 * 背景
	 */
	@ApiModelProperty(value = "背景")
	private String thumb;
	/**
	 * 数量
	 */
	@ApiModelProperty(value = "数量") 
	private Integer total;
	@ApiModelProperty(value = "合成来源编号")
	private Integer mergeId;

	@ApiModelProperty(value = "排序等级")
	private Integer sortsid;

	public Integer getSortsid() {
		return sortsid;
	}

	public void setSortsid(Integer sortsid) {
		this.sortsid = sortsid;
	}

	public Integer getMergeId() {
		return mergeId;
	}

	public void setMergeId(Integer mergeId) {
		this.mergeId = mergeId;
	}

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

	public Integer getAnswerMemberId() {
		return answerMemberId;
	}

	public void setAnswerMemberId(Integer answerMemberId) {
		this.answerMemberId = answerMemberId;
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
