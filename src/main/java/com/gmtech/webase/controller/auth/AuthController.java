package com.gmtech.webase.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmtech.webase.common.Message;
import com.gmtech.webase.common.ResponseEntity;
import com.gmtech.webase.controller.request.LoginRequest;
import com.gmtech.webase.controller.response.LoginResponse;
import com.gmtech.webase.service.AuthService;
import com.gmtech.webase.util.JwtsUtil;
import com.gmtech.webase.util.ResponseEntityUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
    private AuthService authService;
	
	@RequestMapping("/login")
	private ResponseEntity login(@Validated LoginRequest request, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()){
			 return ResponseEntityUtil.success(bindingResult.getFieldError().getDefaultMessage());
	           
		} 
		authService.login(request.getUsername(), request.getPassword());
		String token = JwtsUtil.encode(request.getUsername());
		
	    LoginResponse res = new LoginResponse("20",token);
	    return ResponseEntityUtil.success(res).addMessage(new Message()).addMessage(new Message());
	}
}
