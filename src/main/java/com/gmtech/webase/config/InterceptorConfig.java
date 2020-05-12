package com.gmtech.webase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.MappedInterceptor;

import com.gmtech.webase.interceptor.RequestLoggerInterceptor;

@Configuration
public class InterceptorConfig {
	@Bean
    public RequestLoggerInterceptor loggerInterceptor() {
        return new RequestLoggerInterceptor();
    }

    @Bean
    public MappedInterceptor interceptor() {
        return new MappedInterceptor(new String[]{"/**"}, loggerInterceptor());
    }
}