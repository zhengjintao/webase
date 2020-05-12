package com.gmtech.webase.controller.request;

import javax.validation.constraints.NotEmpty;

import lombok.Value;

@Value
public class RegistRequest {
	String username;
	@NotEmpty(message = "{user.name.notBlank}")
	String mailAddress;
	@NotEmpty(message = "{user.name.notBlank}")
	String password;
}
