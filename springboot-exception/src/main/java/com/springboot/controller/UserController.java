package com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/show")
    public String show() {
        Integer.parseInt("D");
        return "index";
    }

    @RequestMapping("/info")
    public String info() {
        int i = 2 / 0;
        return "index";
    }

}