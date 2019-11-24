package com.shsxt.controller;

import com.shsxt.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class ThymeleafController {

    @RequestMapping("/show")
    public String showMsg(Model model,
                          HttpServletRequest request,
                          HttpServletResponse response) {

        // 字符串
        model.addAttribute("msg", "Thymeleaf 入门案例");
        // 日期时间
        model.addAttribute("myDate", new Date());
        // 条件判断if
        model.addAttribute("sex", 1);
        // 条件判断switch
        model.addAttribute("id", 1);
        // 对象
        model.addAttribute("user", new User(1, "张三", 20));

        // 迭代遍历list
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "张三", 20));
        userList.add(new User(2, "李四", 22));
        userList.add(new User(3, "王五", 24));
        model.addAttribute("userList", userList);

        // 迭代遍历map
        Map<String, User> userMap = new HashMap<>();
        userMap.put("u1", new User(1, "张三", 20));
        userMap.put("u2", new User(2, "李四", 22));
        userMap.put("u3", new User(3, "王五", 24));
        model.addAttribute("userMap", userMap);

        // 域对象操作
        request.setAttribute("req", "HttpServletRequest");
        request.getSession().setAttribute("sess", "HttpSession");
        request.getSession().getServletContext().setAttribute("app", "Application");
        return "msg";

    }

    /**
     * URL表达式-相对路径
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * URL表达式-普通传参
     * @param id
     * @param username
     * @return
     */
    @RequestMapping("/user")
    public String user(Integer id, String username) {
        System.out.println("id：" + id + " username：" + username);
        return "user";
    }

    /**
     * URL表达式-restful传参
     * @param id
     * @param username
     * @return
     */
    @RequestMapping("/person/{id}/{username}")
    public String person(@PathVariable Integer id, @PathVariable String username) {
        System.out.println("id：" + id + " username：" + username);
        return "person";
    }

}