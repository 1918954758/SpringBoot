package com.zichen.admin.controller;

import com.zichen.admin.bean.User;
import com.zichen.admin.util.Utils;
import com.zichen.admin.util.Validate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public String index(User user, HttpSession session, Model model) {
        if (Validate.validatelogin(session, user, model)) {
            // 防止表单重复提交
            return "redirect:/main.html";
        } else {
            return "login";
        }

    }

    // 刷新页面执行该方法，而不是重复提交POST，调用/index请求
    @GetMapping("/main.html")
    public String indexPage() {
        return "index";
    }

}
