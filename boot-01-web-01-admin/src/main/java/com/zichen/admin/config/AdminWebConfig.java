package com.zichen.admin.config;

import com.zichen.admin.interceptor.LoginInterceptor;
import com.zichen.admin.interceptor.RedisUrlCountInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @name: AdminWebConfig
 * @description:
 * @author: zichen
 * @date: 2021/5/15  15:22
 */
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {

    @Autowired
    RedisUrlCountInterceptor redisUrlCountInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "/login4Postman", "/sql", "/css/**", "/fonts/**", "/images/**", "/js/**", "/saveUserTb", "/saveUserTb4Annotation", "/insertCity");
        registry.addInterceptor(redisUrlCountInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "/login4Postman", "/sql", "/css/**", "/fonts/**", "/images/**", "/js/**", "/saveUserTb", "/saveUserTb4Annotation", "/insertCity");
    }
}
