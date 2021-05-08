package com.zichen.boot.controller;

import com.zichen.boot.bean.Person;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @name: ParameterTest
 * @description:
 * @author: zichen
 * @date: 2021/5/5  16:04
 */
@RestController
public class ParameterTestController {

    @PostMapping("/saveuser")
    public Person saveuser(Person person) {
        return person;
    }

    @GetMapping("/car/{id}/owner/{username}")
    public Map<String, Object> getCar(@PathVariable("id") Integer id,
                                      @PathVariable("username") String name,
                                      @PathVariable Map<String, String> pv,
                                      @RequestHeader("User-Agent") String userAgent,
                                      @RequestHeader Map<String, String> header,
                                      @RequestParam("age") Integer age,
                                      @RequestParam("inters") List<String> inters,
                                      @RequestParam Map<String, String> params
                                      /*@CookieValue("_ga") String _ga,
                                      @CookieValue Cookie cookie*/) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("pv", pv);
        map.put("userAgent", userAgent);
        map.put("header", header);
        map.put("age", age);
        map.put("inters", inters);
        map.put("params", params);
        //map.put("_ga", _ga);
        //map.put("cookie", cookie);
        return map;
    }

    @PostMapping("/save")
    public Map postMethod(@RequestBody String content) {
        Map<String, Object> map = new HashMap<>();
        map.put("content", content);
        return map;
    }

    // 1. 语法 /cars/sell;low=34;brand=byd,audi,yd
    // 2. SpringBoot默认是禁用矩阵变量的，需要手动开启
    @GetMapping("/cars/{path}")
    public Map carsSell(@MatrixVariable("low") Integer low,
                        @MatrixVariable("brand") List<String> brand,
                        @PathVariable("path") String path) {//矩阵变量是和路径绑定在一起的，英雌需要这样使用
        Map<String, Object> map = new HashMap<>();
        map.put("low", low);
        map.put("brand", brand);
        map.put("path", path);
        return map;
    }

    //  /boss/1;age=20/2;age=10
    @GetMapping("/boss/{bossId}/{empId}")
    public Map boss(@MatrixVariable(value = "age", pathVar = "bossId") Integer bossAge,
                    @MatrixVariable(value = "age", pathVar = "empId") Integer empAge) {
        Map<String, Object> map = new HashMap<>();
        map.put("bossAge", bossAge);
        map.put("empAge", empAge);
        return map;
    }
}
