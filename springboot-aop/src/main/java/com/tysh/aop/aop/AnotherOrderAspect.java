package com.tysh.aop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(0)
@Aspect
@Component
public class AnotherOrderAspect {


    @Before("@annotation(Dot)")
    public void doBefore() {
        System.out.println("in AnotherOrderAspect before!");
    }
}