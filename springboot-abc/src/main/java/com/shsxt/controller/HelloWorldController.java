package com.shsxt.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 第一个springboot项目
 */
//该注解表示,该类下每个方法返回JSON数据
@RestController
public class HelloWorldController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

    @RequestMapping("/getMap")
    public  Map<String,String> getMap(){
        Map<String,String> map =new HashMap<>();
        map.put("code","200");
        map.put("message","success");
        return  map;
    }


}
