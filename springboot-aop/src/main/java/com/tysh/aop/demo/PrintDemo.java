package com.tysh.aop.demo;

import org.springframework.stereotype.Component;

import java.util.UUID;

// 这个bean下的方法，演示正则方式的拦截
// 注意前面的参数为..，表示任意参数类型和个数的方法都会拦截

@Component
public class PrintDemo {

    public String genRand(int seed, String suffix) {
        return seed + UUID.randomUUID().toString() + suffix;
    }
}