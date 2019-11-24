package com.springboot.controller;

import com.springboot.pojo.User;
import com.springboot.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.RequestWrapper;

/**
 * 操作持久层
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceI userService;

    /**
     * 页面跳转
     */
    @RequestMapping("/{page}")
    public  String page(@PathVariable String page){
        return page;
    }
    /**
     * 添加用户
     */
    @PostMapping("/insertUser")
    public String insertUser(User user){
        int result =userService.insertUser(user);
        return "success";
    }

    /**
     * 查询用户列表
     */
    @GetMapping("/selectUserList")
    public String selectUserList(Model model) {
        model.addAttribute("userList", userService.selectUserList());
        return "user-list";
    }

    /**
     * 根据主键查询
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.selectUserById(id));
        return "updateUser";
    }
    /**
     * 修改用户保存
     * 修改之前需要先根据ID查询
     */
    @PostMapping("/updateUser")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "success";
    }

    /**
     * 删除用户
     */
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "success";
    }

}
