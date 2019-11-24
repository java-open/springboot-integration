package com.springboot.controller;

import com.springboot.group.ValidateGroupForAll;
import com.springboot.group.ValidateGroupForName;
import com.springboot.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 页面跳转
     *
     * @param page
     * @return
     */
    @RequestMapping("/{page}")
    public String page(@PathVariable String page, User user) {
        return page;
    }

    /**
     * 添加用户
     *
     * @param user  @Validated：表示对哪个参数进行校验
     *              BindingResult：接收校验提示信息
     *              @Validated 和 BindingResult 是配对出现，并且形参顺序是固定的
     * @param model
     * @return
     */
    @PostMapping("/insertUser")
    public String insertUser(@Validated(value = {ValidateGroupForAll.class}) User user, BindingResult bindingResult, Model model) {
        model.addAttribute("user", user);
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error: errors) {
                System.out.println(error.getDefaultMessage());// 错误信息
            }
        }
        return bindingResult.hasErrors() ? "register" : "success";
    }

    /**
     * 用户登录。只校验用户名
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping("/login")
    public String login(@Validated(value = {ValidateGroupForName.class}) User user, BindingResult bindingResult) {
        return bindingResult.hasErrors() ? "login" : "success";
    }

}