package com.gmtech.webase.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class ValidatorConfig extends WebMvcConfigurationSupport{
	
	@Bean
	public MessageSource getMessageSource() {
		ResourceBundleMessageSource messageSource=new ResourceBundleMessageSource();
    	messageSource.setBasename("ValidationMessages/ValidationMessages");
    	return messageSource;
	}
	
	@Override
    public Validator getValidator() {
        return validator();
    }

    /**
     * Validation message i18n
     * @return Validator
     */
    @Bean
    public Validator validator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(getMessageSource());
        return validator;
    }
}
