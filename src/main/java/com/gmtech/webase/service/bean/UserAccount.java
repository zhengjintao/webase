package com.gmtech.webase.service.bean;

import java.util.Date;

import lombok.Data;

@Data
public class UserAccount {
	/**
     * 主键ID
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;
    
    /**
     * 邮箱地址
     */
    private String mailAddress;

    /**
     * 密码
     */
    private String password;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
