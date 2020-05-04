package com.gmtech.webase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WebaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebaseApplication.class, args);
	}

}
