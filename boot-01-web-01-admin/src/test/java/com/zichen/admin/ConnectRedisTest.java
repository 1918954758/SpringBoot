package com.zichen.admin;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * @name: ConnectRedisTest
 * @description:
 * @author: zichen
 * @date: 2021/5/29  14:53
 */
@Slf4j
@SpringBootTest
public class ConnectRedisTest {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Test
    public void connectRedisTest() {
        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        stringStringValueOperations.set("userName", "zichen");
        String userName = stringStringValueOperations.get("userName");
        log.info(userName);
    }

    @Test
    public void redisConnectionTypeTest() {
        Class<? extends RedisConnectionFactory> aClass = redisConnectionFactory.getClass();
        log.info(aClass.getName());
    }
}
