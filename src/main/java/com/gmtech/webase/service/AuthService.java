package com.gmtech.webase.service;

import com.gmtech.webase.service.bean.UserAccount;

public interface AuthService {
	int register(UserAccount user);
    String login(String username, String password);
    String refresh(String oldToken);
}
