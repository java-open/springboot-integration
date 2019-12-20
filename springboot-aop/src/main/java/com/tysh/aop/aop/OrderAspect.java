package com.tysh.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Order(10)
@Component
@Aspect
public class OrderAspect {

    @Before("@annotation(Dot)")
    public void doAnoBefore(JoinPoint joinPoint) {
        System.out.println("dp AnoBefore");
    }

    @Pointcut("execution(public * com.tysh.aop.bean.*.*())")
    public void point() {
    }

    @Before(value = "point()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("do before!");
    }

    @After(value = "point()")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("do after!");
    }

    @AfterReturning(value = "point()", returning = "ans")
    public void doAfterReturning(JoinPoint joinPoint, String ans) {
        System.out.println("do after return: " + ans);
    }

    @Around("point()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            System.out.println("do in around before");
            return joinPoint.proceed();
        } finally {
            System.out.println("do in around over!");
        }
    }
}