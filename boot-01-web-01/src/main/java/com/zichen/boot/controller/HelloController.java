package com.zichen.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @name: HelloController
 * @description:
 * @author: zichen
 * @date: 2021/5/4  13:34
 */
//@RestController = @Controller + @ResponseBody
@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "aaa";
    }

    @RequestMapping("/")
    public String index() {
        return "index.html";
    }
}
