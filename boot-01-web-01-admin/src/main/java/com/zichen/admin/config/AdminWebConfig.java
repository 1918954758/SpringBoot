package com.zichen.admin.config;

import com.zichen.admin.interceptor.LoginInterceptor;
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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "/login4Postman", "/sql", "/css/**", "/fonts/**", "/images/**", "/js/**", "/saveUserTb");
    }
}
