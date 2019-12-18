package com.gitee.taven.aop.demo;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnoAspcet {
    /**
     *在方法调用之前，需要执行一些操作，这个时候可以使用 @Before 注解来声明before advice
     *
     * 一种可使用姿势如下，我们的切点直接在注解中进行定义，使用正则表达式的方式
     **/

    @Before("execution(public * com.gitee.taven.aop.demo.*.*(*))")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("do in Aspect before method called! args: " + JSON.toJSONString(joinPoint.getArgs()));
    }



    /**
    在方法调用完毕之后，再执行一些操作，这个时候after就可以派上用场，为了考虑切点的通用性，我们可以考虑声明一个切点，使用@Pointcut注解
    */
    @Pointcut("execution(public * com.gitee.taven.aop.demo.*.*(*))")
    public void point() {

    }


    @After("point()")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("do in Aspect after method called! args: " + JSON.toJSONString(joinPoint.getArgs()));
    }


    /**
     * 执行完毕之后，通过 args指定参数；通过 returning 指定返回的结果，要求返回值类型匹配
     *
     * @param time
     * @param result
     */
    @AfterReturning(value = "point() && args(time)", returning = "result")
    public void doAfterReturning(long time, String result) {
        System.out.println("do in Aspect after method return! args: " + time + " ans: " + result);
    }

    /**
     *使用这个advice需要注意的是传入参数类型为 ProceedingJoinPoint，需要在方法内部显示执行org.aspectj.lang.ProceedingJoinPoint#proceed()来表示调用方法
     */

    @Around("point()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("do in Aspect around ------ before");
        Object ans = joinPoint.proceed();
        System.out.println("do in Aspect around ------- over! ans: " + ans);
        return ans;
    }



}