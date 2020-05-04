package com.gmtech.webase.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gmtech.webase.filter.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
        .antMatchers(
                HttpMethod.GET,
                "/",
                "/*.html",
                "/favicon.ico",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js"
        ).permitAll()
        .antMatchers(HttpMethod.GET, "/books/**").hasRole("user")
        .antMatchers(HttpMethod.POST, "/books").hasRole("admin")
        .antMatchers(HttpMethod.PUT, "/books/**").hasRole("admin")
        .antMatchers(HttpMethod.PATCH, "/books/**").hasRole("admin")
        .antMatchers("/test/**").hasRole("admin")
        .antMatchers("/**").permitAll()
        .antMatchers("/api-docs").permitAll()
        .antMatchers("/swagger-ui/**").permitAll()
        .antMatchers("/auth/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .addFilter(createFilter())
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Autowired
    public void configure(AuthenticationManagerBuilder auth,
                                UserDetailsService userDetailsService,
                                PasswordEncoder passwordEncoder) throws Exception {
        auth
        	.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder);
    }
	
	@Bean
    public FilterRegistrationBean<JWTAuthenticationFilter> jwtFilter() {
        FilterRegistrationBean<JWTAuthenticationFilter>  registrationBean = new FilterRegistrationBean<JWTAuthenticationFilter> ();
        registrationBean.setFilter(createFilter());
        
        return registrationBean;
    }
	
	@Bean
	JWTAuthenticationFilter createFilter(){
		try {
			return new JWTAuthenticationFilter(authenticationManager());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
    @Bean
    AuthenticationManager setAuthenticationManager() throws Exception {
        return authenticationManager();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}