package com.shsxt.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 通过方法完成 Filter 组件的注册
 */
@WebFilter(filterName = "SecondFilter",urlPatterns = "/second")
public class SecondFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Second Fileter begin----");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("Second Fileter end----");
    }

    @Override
    public void destroy() {

    }
}
