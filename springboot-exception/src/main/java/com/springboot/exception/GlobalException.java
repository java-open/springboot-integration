package com.springboot.exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理类
 */
@Configuration
public class GlobalException implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler, Exception ex) {
        ModelAndView mv = new ModelAndView();
        // 判断异常类型，做不同视图跳转
        if(ex instanceof ArithmeticException){
            mv.setViewName("arithmeticException");
        }
        if(ex instanceof NumberFormatException){
            mv.setViewName("numberFormatException");
        }
        mv.addObject("error", ex.toString());
        return mv;
    }

}