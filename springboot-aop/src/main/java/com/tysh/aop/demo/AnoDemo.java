package com.tysh.aop.demo;

import com.tysh.aop.demoaop.AnoDot;
import org.springframework.stereotype.Component;

import java.util.UUID;

// 这个bean下的方法，演示注解拦截

@Component
public class AnoDemo {

    @AnoDot
    public String gen(String ans) {
        return UUID.randomUUID() + "<>" + ans;
    }
}


