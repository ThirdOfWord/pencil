package com.freeter.login.form;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class SpeedForm {

	@ApiModelProperty(value = "手机号")
	@NotBlank(message = "手机号不能为空")
	@Length(max = 11, min = 11, message = "手机号格式不对")
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
