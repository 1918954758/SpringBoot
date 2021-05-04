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

    @ResponseBody
    //@RequestMapping(value = "/user", method = RequestMethod.GET)
    @GetMapping("/user")
    public String getUser() {
        return "GET - 张三";
    }

    @ResponseBody
    //@RequestMapping(value = "/user", method = RequestMethod.POST)
    @PostMapping("/user")
    public String saveUser() {
        return "POST - 张三";
    }

    @ResponseBody
    //@RequestMapping(value = "/user", method = RequestMethod.PUT)
    @PutMapping("/user")
    public String putUser() {
        return "PUT - 张三";
    }

    @ResponseBody
    //@RequestMapping(value = "/user", method = RequestMethod.DELETE)
    @DeleteMapping("/user")
    public String deleteUser() {
        return "DELETE - 张三";
    }
}
