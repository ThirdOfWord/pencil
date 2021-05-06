/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.freeter.modules.pingtaiGoods.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.zip.DataFormatException;


@ApiModel(value = "淘礼金")
public class TaoLiJin {

	private String campaignType;

	private String postId;
	private String postCode;
	private Long adzoneId;
	@NotBlank(message="宝贝id不能为空")
	private Long itemId;
	@NotBlank(message="淘礼金总个数不能为空")
    private Long totalNum;
	@NotBlank(message="淘礼金名称，最大10个字符不能为空")
    private String name;
	@NotBlank(message="单用户累计中奖次数上限不能为空")
    private Long userTotalWinNumLimit;
	@NotBlank(message="单个淘礼金面额不能为空")
    private String perFace;

	private String sendStartTime;

	private String sendEndTime;

	private String text;

	private String useEndTime;
	private Long useEndTimeMode;


	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPostId() {
		return postId;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getCampaignType() {
		return campaignType;
	}

	public void setCampaignType(String campaignType) {
		this.campaignType = campaignType;
	}

	public Long getAdzoneId() {
		return adzoneId;
	}

	public void setAdzoneId(Long adzoneId) {
		this.adzoneId = adzoneId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUserTotalWinNumLimit() {
		return userTotalWinNumLimit;
	}

	public void setUserTotalWinNumLimit(Long userTotalWinNumLimit) {
		this.userTotalWinNumLimit = userTotalWinNumLimit;
	}

	public String getPerFace() {
		return perFace;
	}

	public void setPerFace(String perFace) {
		this.perFace = perFace;
	}

	public String getSendStartTime() {
		return sendStartTime;
	}

	public void setSendStartTime(String sendStartTime) {
		this.sendStartTime = sendStartTime;
	}

	public String getSendEndTime() {
		return sendEndTime;
	}

	public void setSendEndTime(String sendEndTime) {
		this.sendEndTime = sendEndTime;
	}

	public String getUseEndTime() {
		return useEndTime;
	}

	public void setUseEndTime(String useEndTime) {
		this.useEndTime = useEndTime;
	}

	public Long getUseEndTimeMode() {
		return useEndTimeMode;
	}

	public void setUseEndTimeMode(Long useEndTimeMode) {
		this.useEndTimeMode = useEndTimeMode;
	}
}
