package com.spring.springboot.controller;

import com.spring.springboot.entity.User;
import com.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *控制层
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 通过用户名获取用户信息,以及从库的地址信息
     */
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public User findUserByName(@RequestParam(value = "name",required = true) String name){
        return  userService.findByName(name);

    }
}
