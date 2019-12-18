package com.gitee.taven.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *@Description 防止重复提交注解
 *@author why
 *@Date 15:02 2019/12/13
 *@returen
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoRepeatSubmit {

    /**
     * 设置请求锁定时间
     *
     * @return
     */
    int lockTime() default 10;

}
