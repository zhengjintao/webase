package com.gmtech.webase.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gmtech.webase.service.AuthService;
import com.gmtech.webase.service.UserAccountService;
import com.gmtech.webase.service.bean.UserAccount;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
    private AuthenticationManager authenticationManager;
	@Autowired
    private UserDetailsService userDetailsService;
	@Autowired
	private UserAccountService userService;

    @Override
    public int register(UserAccount user) {
        final String username = user.getUsername();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = user.getPassword();
        user.setPassword(encoder.encode(rawPassword));
        return userService.insert(user);
    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        
        if(authentication.isAuthenticated()){
        	SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        return null;
    }

    @Override
    public String refresh(String oldToken) {

        return null;
    }
}
