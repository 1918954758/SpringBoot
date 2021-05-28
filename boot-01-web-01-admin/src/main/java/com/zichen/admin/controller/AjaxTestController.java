package com.zichen.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * class: AjaxTest
 * description:
 * author: ~~~
 * date: 2021/5/28 - 15:17
 */
@Slf4j
@Controller
public class AjaxTestController {

    /**
     * ajax get request test
     * @param id
     * @param name
     * @param page
     */
    @ResponseBody
    @GetMapping("/test/ajax_get")
    public void ajaxGetTest(@RequestParam("id") Integer id,
                           @RequestParam("name") String name,
                           @RequestParam("page") Integer page) {
        log.info("GET请求获取到的参数信息：【{}, {}, {}】", id, name, page);
    }

    /**
     * ajax post request test
     * @param id
     * @param name
     * @param page
     */
    @ResponseBody
    @PostMapping("/test/ajax_post")
    public void ajaxPostTest(@RequestParam("id") Integer id,
                             @RequestParam("name") String name,
                             @RequestParam("page") Integer page) {
        log.info("POST请求获取到的参数信息：【{}, {}, {}】", id, name, page);
    }

    /**
     * jump to ajax.html
     * @return
     */
    @GetMapping("/jump_to_ajax")
    public String gotoAjaxPage() {
        return "ajax_test/ajax";
    }
}
