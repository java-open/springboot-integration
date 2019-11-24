package com.shsxt.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 *通过注解扫描完成 Filter 组件的注册
 * 拦截web的第一个请求,
 */
@WebFilter(filterName = "FirstFilter",urlPatterns = "/first")
public class FirstFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       System.out.println("First Fileter begin----");
       filterChain.doFilter(servletRequest,servletResponse);
       System.out.println("First Fileter end----");
    }

    @Override
    public void destroy() {

    }
}
