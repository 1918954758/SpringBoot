package com.zichen.controller;

import com.zichen.hello.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: HelloController
 * @description:
 * @author: zichen
 * @date: 2021/6/12  1:24
 */
@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String sayHello() {
        String str = helloService.sayHello("张三");
        return str;
    }
}
