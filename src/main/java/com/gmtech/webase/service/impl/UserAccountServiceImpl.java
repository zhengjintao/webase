package com.gmtech.webase.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gmtech.webase.service.UserAccountService;
import com.gmtech.webase.service.bean.UserAccount;
import com.gmtech.webase.service.mapper.UserAccountMapper;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Resource
    private UserAccountMapper userMapper;

    @Override
    public Object listAll(int page, int size) {
        PageHelper.startPage(page, size);
        List<UserAccount> userList = userMapper.listAll();
        PageInfo<UserAccount> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    @Override
    public int insert(UserAccount user) {
        return userMapper.insert(user);
    }

    @Override
    public int remove(Integer userId) {
        return userMapper.remove(userId);
    }

    @Override
    public int update(UserAccount user) {
        return userMapper.update(user);
    }

	@Override
	public UserAccount getUserByUserName(String userName) {
		return userMapper.getUserByUserName(userName);
	}

	@Override
	public UserAccount getUserByMailAddress(String mailAddress) {
		return userMapper.getUserByUserName(mailAddress);
	}
}