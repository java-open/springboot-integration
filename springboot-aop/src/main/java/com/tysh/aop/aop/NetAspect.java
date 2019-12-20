package com.tysh.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 *
 */
@Aspect
@Component
public class NetAspect {

    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println(" in NetAspect do Around Before");
        Object ans = joinPoint.proceed();
        System.out.println("in NetAspect do Around over| ans:"+ ans);
        return ans;
    }


}
