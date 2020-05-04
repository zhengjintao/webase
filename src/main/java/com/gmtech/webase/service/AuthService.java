package com.gmtech.webase.service;

import com.gmtech.webase.service.bean.User;

public interface AuthService {
	int register(User user);
    String login(String username, String password);
    String refresh(String oldToken);
}
