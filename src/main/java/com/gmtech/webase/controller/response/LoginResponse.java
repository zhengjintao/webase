package com.gmtech.webase.controller.response;


import lombok.Value;

@Value
public class LoginResponse {
	String status;
	String accessToken;
}
