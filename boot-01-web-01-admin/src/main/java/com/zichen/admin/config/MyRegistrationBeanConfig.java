package com.zichen.admin.config;

import com.zichen.admin.servlet.MyFilter;
import com.zichen.admin.servlet.MyServlet;
import com.zichen.admin.servlet.MyServletContextListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * class: MyServletConfig
 * description:
 * author: ~~~
 * date: 2021/5/20 - 16:17
 */
@Slf4j
@Configuration
public class MyRegistrationBeanConfig {

    @Bean
    public ServletRegistrationBean myServlet() {
        MyServlet myServlet = new MyServlet();
        return new ServletRegistrationBean(myServlet, "/myServlet");
    }

    @Bean
    public FilterRegistrationBean myFilter() {
        return new FilterRegistrationBean(new MyFilter(), myServlet());
    }

    /*@Bean
    public FilterRegistrationBean myFilter02() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new MyFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/myFilter", "/css/*"));
        return filterRegistrationBean;
    }*/

    @Bean
    public ServletListenerRegistrationBean myListener() {
        return new ServletListenerRegistrationBean(new MyServletContextListener());
    }
}
