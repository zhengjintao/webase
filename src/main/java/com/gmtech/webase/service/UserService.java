package com.gmtech.webase.service;

import com.gmtech.webase.service.bean.User;

public interface UserService {

    Object listAll(int page, int size);
    
    User getUserByUserName(String userName);

    int insert(User user);

    int remove(Integer userId);

    int update(User user);
}