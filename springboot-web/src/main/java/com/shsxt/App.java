package com.shsxt;

import com.shsxt.filter.SecondFilter;
import com.shsxt.listener.SecondListener;
import com.shsxt.servlet.SecondServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * 启动类
 * 通过bean注解注册listener
 */
@SpringBootApplication
@ServletComponentScan
public class App {
    public static void main(String[] args) {

        SpringApplication.run(App.class);
    }


    // 通过方法完成 Listener 组件注册
    @Bean
    public ServletListenerRegistrationBean<SecondListener>
    getServletListenerRegistrationBean() {
        return new ServletListenerRegistrationBean(new SecondListener());
    }


    // 通过方法完成 Servlet 组件的注册
    @Bean
    public ServletRegistrationBean getServletRegistrationBean() {

        ServletRegistrationBean bean = new ServletRegistrationBean(new SecondServlet());

        bean.addUrlMappings("/second");

        return bean;
    }

    // 通过方法完成 Filter 组件的注册
    @Bean
    public FilterRegistrationBean getFilterRegistrationBean() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new SecondFilter());
        bean.addUrlPatterns("/second");
        return bean;
    }


}
