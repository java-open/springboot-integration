package com.tysh.aop.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;


/**
 * Created by Administrator on 2019/12/18/018.
 */
@Aspect
@Component
public class AnoAspect {



    @Before("@annotation(Dot)")
    public void anoBefore(){
        System.out.println("AnoAspect");

    }

    @Before("execution(public * com.tysh.aop.aop.*.*(*))")
    public  void doBefore(JoinPoint joinPoint){
        System.out.println("do in Aspect before method ---> :args:"+JSON.toJSONString(joinPoint.getArgs()));

    }


    @Pointcut("execution(public * com.tysh.aop.aop.*.*(*))")
        public void point(){

        }

    @After("point()")
    public void doAfter(JoinPoint joinPoint){
        System.out.println("do in Aspect after method<<<<< :args:"+JSON.toJSONString(joinPoint.getArgs()));
    }

    /**
     * 执行完毕后,通过args指定参数,通过returning指定返回的结果,要求返回类型匹配.
     * @param time
     * @param result
     */
    @AfterReturning(value = "point() && args(time)" ,returning = "result")
    public void doAfterReturning(long time,String result){
        System.out.println("do in Aspect after method return: args:" +time +"ans" + result);

    }

    //环绕通知
    @Around("point()")
    public  Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("do in Aspect Around ---->before");
        Object ans = joinPoint.proceed();
        System.out.println("do in Aspect Around ---->over---ans:" +ans);
        return  ans;

    }







    }





