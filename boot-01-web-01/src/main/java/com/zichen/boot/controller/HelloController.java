package com.zichen.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: HelloController
 * @description:
 * @author: zichen
 * @date: 2021/5/4  13:34
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "aaa";
    }

}
