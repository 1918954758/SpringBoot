package com.zichen.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    @GetMapping({"/", "/login"})
    public String login() {
        return "login";
    }

    /*
     * 主页
     */
    @PostMapping("/index")
    public String index(String username, String password) {
        // 放置表单重复提交
        return "redirect:/main.html";
    }

    // 刷新页面执行该方法，而不是重复提交POST，调用/index请求
    @GetMapping("/main.html")
    public String indexPage() {
        return "index";
    }

}
