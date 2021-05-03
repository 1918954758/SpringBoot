package com.zichen.boot.controller;

import com.zichen.boot.bean.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: HelloController
 * @description:
 * @author: zichen
 * @date: 2021/5/2  18:31
 */
//@ResponseBody
//@Controller
@RestController
@Slf4j
public class HelloController {

    @Autowired
    private Car car;

    @RequestMapping("/car")
    public Car car() {
        return car;
    }

    @RequestMapping("/hello")
    public String handle01(@RequestParam("name") String name) {
        log.info("日志进来了...");
        return "hello" + name;
    }
}
