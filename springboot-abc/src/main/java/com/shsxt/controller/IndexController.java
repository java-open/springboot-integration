package com.shsxt.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 有两个controller
 */
@RestController
public class IndexController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }


}
