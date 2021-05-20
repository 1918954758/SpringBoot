package com.zichen.admin.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * class: MyServlet
 * description:
 * author: ~~~
 * date: 2021/5/20 - 15:22
 */
@Slf4j
//@WebServlet(urlPatterns = {"/test"})
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.getWriter().write("使用@SpringComponentScan() 和 @WebServlet/@WebFilter/@WebListener");  注解的方式测试
        resp.setCharacterEncoding("gbk");
        resp.getWriter().write("使用注入ServletRegistrationBean/FilterRegistrationBean/ServletListenerRegistrationBean");
    }
}
