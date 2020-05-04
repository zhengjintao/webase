package com.gmtech.webase.controller.request;

import javax.validation.constraints.NotEmpty;

import lombok.Value;

@Value
public class LoginRequest {
	@NotEmpty(message = "{user.name.notBlank}")
	String username;
	String password;
}
