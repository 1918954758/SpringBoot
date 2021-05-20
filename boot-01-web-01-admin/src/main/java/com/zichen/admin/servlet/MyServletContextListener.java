package com.zichen.admin.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * class: MyServletContextListener
 * description:
 * author: ~~~
 * date: 2021/5/20 - 15:51
 */
@Slf4j
//@WebListener
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("【contextInitialized start ...】");
        ServletContext servletContext = sce.getServletContext();
        log.info("【ServletContextName】 = " + servletContext.getServletContextName());
        log.info("【ContextPath】 = " + servletContext.getContextPath());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("【contextDestroyed start ...】");
    }
}
