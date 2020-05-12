package com.gmtech.webase.service;

import com.gmtech.webase.service.bean.UserAccount;

public interface UserAccountService {

    Object listAll(int page, int size);
    
    UserAccount getUserByUserName(String userName);
    
    UserAccount getUserByMailAddress(String mailAddress);

    int insert(UserAccount user);

    int remove(Integer userId);

    int update(UserAccount user);
}