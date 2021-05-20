package com.zichen.admin.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * class: MyFilter
 * description:
 * author: ~~~
 * date: 2021/5/20 - 15:33
 */
@Slf4j
//@WebFilter(urlPatterns = {"/css/*", "/js/*", "/test"})
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("【filter init ...】");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("【filter doFilter ...】");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info("【filter destroy ...】");
    }
}
