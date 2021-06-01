package com.zichen.admin;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.nio.charset.StandardCharsets;

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
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Test
    public void connectRedisTest() {
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set("userName", "zichen");
        String userName = stringStringValueOperations.get("userName");
        log.info(userName);
    }

    @Test
    public void redisConnectionTypeTest() {
        Class<? extends RedisConnectionFactory> aClass = redisConnectionFactory.getClass();
        log.info(aClass.getName());
    }


    @Test
    public void redisConnectionTest() {
        ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
        //ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        //String key1 = stringStringValueOperations.get("key1");
        //String key2 = stringStringValueOperations.get("key2");
        Object key1 = operations.get("key1");
        Object key2 = operations.get("key2");
        log.info("key1 【{}】 | key2 【{}】", key1 ,key2);

        /**
         * 1. 这里使用 RedisTemplate 获取到null：
         *      - redisTemplate 中存取数据都是字节数组。
         *      - 当redis中存入的数据是可读形式而非字节数组时，
         *      - 使用redisTemplate取值的时候会无法获取导出数据，获得的值为null。
         *      - 可以使用 StringRedisTemplate 试试。
         */
    }

    @Test
    public void redisTemplateTest() {
        ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
        operations.set("binary1", "123456");

        Object binary1 = operations.get("binary1");
        log.info("binary1 = 【{}】", binary1);
    }

}
