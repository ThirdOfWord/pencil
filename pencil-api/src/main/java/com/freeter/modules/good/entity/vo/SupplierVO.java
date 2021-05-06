package com.freeter.modules.good.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-27 16:20:42
 */
@ApiModel(value = "SupplierVO")
public class SupplierVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 姓名
	 */
	@ApiModelProperty(value = "姓名")
	private String name;

	@ApiModelProperty(value = "",hidden = true)
	private Integer supplierId;
	@ApiModelProperty(value = "饿了么的url")
	private String url;
	@ApiModelProperty(value = "缩略图")
	private String thumb;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumb() {
		return "http://xxqbt.xxqbt.com"+thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	/**
	 * 设置：姓名
	 */
	 
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
}
