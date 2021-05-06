package com.freeter.modules.community.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 社区推广商品
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-14 17:14:38
 */
@ApiModel(value = "CommunityArticleGoodsVO")
public class CommunityArticleGoodsVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "",hidden = true)
	private Integer communityArticleGoodsId;

	/**
	 * 商品主图
	 */
	@ApiModelProperty(value = "商品主图") 
	private String thumb;

	/**
	 * 分享海报
	 */
	@ApiModelProperty(value = "分享海报")
	private String poster;

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	/**
	 * 设置：商品主图
	 */
	 
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	
	/**
	 * 获取：商品主图
	 */
	public String getThumb() {
		return thumb;
	}
	public Integer getCommunityArticleGoodsId() {
		return communityArticleGoodsId;
	}

	public void setCommunityArticleGoodsId(Integer communityArticleGoodsId) {
		this.communityArticleGoodsId = communityArticleGoodsId;
	}
}
