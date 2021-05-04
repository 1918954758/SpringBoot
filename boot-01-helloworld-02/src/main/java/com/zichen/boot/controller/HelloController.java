package com.zichen.boot.controller;

import com.zichen.boot.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: MyController
 * @description:
 * @author: zichen
 * @date: 2021/5/4  11:03
 */
@RestController
public class HelloController {

    @Autowired
    private Person person;

    @RequestMapping("/test")
    public String test(){
        return "test()";
    }

    @RequestMapping("person")
    public Person person() {
        return person;
    }
}
