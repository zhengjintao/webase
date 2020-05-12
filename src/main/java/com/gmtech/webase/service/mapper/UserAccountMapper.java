package com.gmtech.webase.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gmtech.webase.service.bean.UserAccount;

@Mapper
public interface UserAccountMapper {

    @Select({
            "select * from wb_account"
    })
    List<UserAccount> listAll();
    
    @Select({
        "select * from wb_account where username = #{userName}"
    })
    UserAccount getUserByUserName(String userName);
    
    @Select({
        "select * from wb_account where mail_address = #{mailAddress} limit 1"
    })
    UserAccount getUserByMailAddress(String mailAddress);

    @Insert({
            "insert into wb_account(`username`,`mail_address`, `password`) values(#{username}, #{mailAddress}, #{password})"
    })
    int insert(UserAccount user);

    @Delete({
            "delete from wb_account where id = #{userId}"
    })
    int remove(Integer userId);

    @Update({
            "update wb_account set username = #{username}, password = #{password} where id = #{id}"
    })
    int update(UserAccount user);
}