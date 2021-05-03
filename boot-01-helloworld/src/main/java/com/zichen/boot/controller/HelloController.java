package com.zichen.boot.controller;

import com.zichen.boot.bean.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class HelloController {

    @Autowired
    private Car car;

    @RequestMapping("/car")
    public Car car() {
        return car;
    }

    @RequestMapping("/hello")
    public String handle01() {
        return "hello";
    }
}
