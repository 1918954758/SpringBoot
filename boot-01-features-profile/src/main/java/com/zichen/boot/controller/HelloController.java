package com.zichen.boot.controller;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: HelloController
 * @description:
 * @author: zichen
 * @date: 2021/6/6  12:58
 */
@RestController
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);
    @Value("${person.name:李四}")
    private String name;

    @GetMapping("/")
    public String hello() {
        log.info("request into ...");
        return "Hello" + name;
    }

    @Test
    public void test() {
        log.info("fweqafdas");
    }
}
