package com.gmtech.webase.controller.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
	@Autowired
    private MailSender sender;
	
	@PostMapping("/mailsend")
	public String handleFileUpload() {
		SimpleMailMessage msg = new SimpleMailMessage();

        msg.setFrom("xiaonei0912@gmail.com");
        msg.setTo("xiaonei0912@gmail.com");
        msg.setSubject("テストメール"); //タイトルの設定
        msg.setText("Spring Boot より本文送信"); //本文の設定

        this.sender.send(msg);

		return msg.toString();
	}

}
