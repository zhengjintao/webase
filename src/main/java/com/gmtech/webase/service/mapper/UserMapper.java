package com.gmtech.webase.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gmtech.webase.service.bean.User;

@Mapper
public interface UserMapper {

    @Select({
            "select * from user"
    })
    List<User> listAll();
    
    @Select({
        "select * from user where username = #{userName}"
    })
    User getUserByUserName(String userName);

    @Insert({
            "insert into user(`username`, `password`) values(#{username}, #{password})"
    })
    int insert(User user);

    @Delete({
            "delete from user where id = #{userId}"
    })
    int remove(Integer userId);

    @Update({
            "update user set username = #{username}, password = #{password} where id = #{id}"
    })
    int update(User user);
}