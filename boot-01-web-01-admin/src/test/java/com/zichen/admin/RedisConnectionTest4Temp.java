package com.zichen.admin;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * class: RedisConnectionTest4Temp
 * description:
 * author: ~~~
 * date: 2021/5/31 - 14:21
 */
@Deprecated
@Slf4j
@SpringBootTest
public class RedisConnectionTest4Temp {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @DisplayName("Test Redis-Connection")
    @Test
    public void connectionRedisTest() {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set("key1", "zichen");
        operations.set("key2", "lemon");

        String key1 = operations.get("key1");
        String key2 = operations.get("key2");
        log.info("key1 = {}", key1);
        log.info("key2 = {}", key2);

    }
}
