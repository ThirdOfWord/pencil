package com.freeter.modules.community.entity.vo;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.FieldFill;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 社区推广
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-14 17:14:38
 */
@ApiModel(value = "CommunityArticleVO")
public class CommunityArticleVO  implements Serializable {
	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "编号",hidden = true)
	private Integer communityArticleId;
	/**
	 * 商家编号
	 */
	@ApiModelProperty(value = "商家编号")
	private Integer storeUserId;
	/**
	 * 昵称
	 */
	@ApiModelProperty(value = "昵称")
	private String nickname;

	/**
	 * 头像
	 */
	@ApiModelProperty(value = "头像")
	private String headimgurl;

	/**
	 * 描述
	 */
	@ApiModelProperty(value = "描述") 
	private String des;

	@ApiModelProperty(value = "分享次数")
	private Integer shares;

	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.UPDATE)
	@ApiModelProperty(value = "更新时间",hidden = true)
	private Integer updateTime;
		
    private List<CommunityArticleGoodsVO> goodsVOS;//多图显示

	public Integer getCommunityArticleId() {
		return communityArticleId;
	}

	public void setCommunityArticleId(Integer communityArticleId) {
		this.communityArticleId = communityArticleId;
	}

	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Integer updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Integer getUpdateTime() {
		return updateTime;
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
	 * 设置：头像
	 */
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	/**
	 * 获取：头像
	 */
	public String getHeadimgurl() {
		return headimgurl;
	}
	public List<CommunityArticleGoodsVO> getGoodsVOS() {
		return goodsVOS;
	}

	public void setGoodsVOS(List<CommunityArticleGoodsVO> goodsVOS) {
		this.goodsVOS = goodsVOS;
	}

	/**
	 * 设置：商家编号
	 */
	 
	public void setStoreUserId(Integer storeUserId) {
		this.storeUserId = storeUserId;
	}
	
	/**
	 * 获取：商家编号
	 */
	public Integer getStoreUserId() {
		return storeUserId;
	}
	/**
	 * 设置：描述
	 */
	 
	public void setDes(String des) {
		this.des = des;
	}
	
	/**
	 * 获取：描述
	 */
	public String getDes() {
		return des;
	}

	public Integer getShares() {
		return shares;
	}

	public void setShares(Integer shares) {
		this.shares = shares;
	}
}
