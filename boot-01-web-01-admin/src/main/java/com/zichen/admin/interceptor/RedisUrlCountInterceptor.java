package com.zichen.admin.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @name: RedisUrlCountInterceptor
 * @description:
 * @author: zichen
 * @date: 2021/5/29  16:02
 */
@Slf4j
@Component
public class RedisUrlCountInterceptor implements HandlerInterceptor {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 每次访问，uri访问次数会加1
        String uri = request.getRequestURI();
        log.info("redis保存页面访问次数，拦截的路径是：{}", uri);
        redisTemplate.opsForValue().increment(uri);
        return true;
    }
}
