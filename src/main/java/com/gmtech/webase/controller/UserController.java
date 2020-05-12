package com.gmtech.webase.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gmtech.webase.service.UserAccountService;
import com.gmtech.webase.service.bean.UserAccount;

@RestController
public class UserController {

    @Resource
    private UserAccountService userService;

    /**
     * 查询全部
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/listAll")
    public Object listAll(@RequestParam(value = "page",defaultValue = "1")int page,
                          @RequestParam(value = "size",defaultValue = "10")int size){
        return userService.listAll(page, size);
    }

    /**
     * 添加数据
     * @param user
     * @return
     */
    @RequestMapping("/insert")
    public int insert (UserAccount user){
        return userService.insert(user);
    }

    /**
     * 删除
     * @param userId
     * @return
     */
    @RequestMapping("/remove")
    public int remove(Integer userId){
        return userService.remove(userId);
    }

    /**
     * 修改
     * @param user
     * @return
     */
    @RequestMapping("/update")
    public int update(UserAccount user){
        return userService.update(user);
    }
}