package com.shsxt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务提供者
 */

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        System.out.println("服务提供者被调用！");
        return "hello world!";
    }

}
