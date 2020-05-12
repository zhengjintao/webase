package com.gmtech.webase.controller.auth;

import java.nio.charset.StandardCharsets;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmtech.webase.common.Message;
import com.gmtech.webase.common.ResponseEntity;
import com.gmtech.webase.controller.request.LoginRequest;
import com.gmtech.webase.controller.request.RegistRequest;
import com.gmtech.webase.controller.response.LoginResponse;
import com.gmtech.webase.service.AuthService;
import com.gmtech.webase.service.UserAccountService;
import com.gmtech.webase.service.bean.UserAccount;
import com.gmtech.webase.util.JwtsUtil;
import com.gmtech.webase.util.ResponseEntityUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
    private AuthService authService;
	
	@Autowired
    private UserAccountService userAccountService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
    private Configuration config;
	
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
	
	@RequestMapping("/regist")
	private ResponseEntity regist(@Validated RegistRequest request, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()){
			 return ResponseEntityUtil.success(bindingResult.getFieldError().getDefaultMessage());
		}
		
		if(userAccountService.getUserByMailAddress(request.getMailAddress()) != null) {
			
			return ResponseEntityUtil.success(null).addMessage(Message.failure().setMsg("mail address is used."));
		}
		
		UserAccount account = new UserAccount();
		account.setUsername(request.getUsername());
		account.setMailAddress(request.getMailAddress());
		account.setPassword(passwordEncoder.encode(request.getPassword()));
		
		userAccountService.insert(account);
		
		mailSender.send(new MimeMessagePreparator() {

	        @Override
	        public void prepare(MimeMessage mimeMessage) throws Exception {
	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
	                    StandardCharsets.UTF_8.name());
	            helper.setFrom("EXAMPLE.COM <info@example.com>");
	            helper.setTo(account.getMailAddress());
	            helper.setSubject("Registration confirmation.");
	            config.setClassForTemplateLoading(this.getClass(), "/freemarker");
	            Template template = config
	                    .getTemplate("registration-confirmation.ftl"); // (2)
	            String text = FreeMarkerTemplateUtils
	                    .processTemplateIntoString(template, account); // (3)
	            helper.setText(text, true);
	        }
	    });
		
	    return ResponseEntityUtil.success(null);
	}
}
