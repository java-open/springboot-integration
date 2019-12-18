package com.gitee.taven.aop;

import com.gitee.taven.utils.RedisLock2;
import com.gitee.taven.utils.RequestUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author why
 * @Description 自定义注解切面实现
 **/
@Aspect
@Component
public class SubmitAspect {

    @Autowired
    private RedisLock2 redisLock2;

    @Pointcut("@annotation(noRepeatSubmit)")
    private  void pointCut( NoRepeatSubmit noRepeatSubmit){

    }


    @Around("@annotation(noRepeatSubmit)")
    public Object around(ProceedingJoinPoint point , NoRepeatSubmit noRepeatSubmit){

        //获得注解时间
        int lockTime = noRepeatSubmit.lockTime();

        //从httpRequset 获得id
        HttpServletRequest request = RequestUtils.getRequest();
        Assert.notNull(request, "request can not null");
        System.out.println(request.getServletPath()+"---"+request.getHeader("userId")+"---"+request.getParameter("userId")+request.getHeader("Authorization"));
        String id = (String) Objects.requireNonNull(request).getAttribute("userId");
        System.out.println("id的值---------"+id);

        //获取请求url路径
        String path = request.getServletPath();
        String key =getKey(id,path);
        System.out.println("key的值---"+key);

        boolean isSuccess = redisLock2.tryLock(key, id, lockTime);
        if(isSuccess){
            //获取锁成功
         Object result;
            try {
                result = point.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }finally {
                redisLock2.releaseLock(key,id);
            }
            return  request;
        }else{
            //获取锁失败,认为是重复提交的请求
            throw new RuntimeException("重复提交请求!");
        }
    }

    private String getKey(String id, String path) {
        return  id+path;
    }


}
