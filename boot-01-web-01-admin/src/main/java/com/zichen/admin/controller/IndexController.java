package com.zichen.admin.controller;

import com.zichen.admin.bean.User;
import com.zichen.admin.util.Validate;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.session.StandardSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@Slf4j
public class IndexController {

    @GetMapping({"/", "/login"})
    public String login() {
        return "login";
    }

    /**
     * 登录（页面）
     * @param user
     * @param session
     * @param model
     * @return
     */
    @PostMapping("/login")
    public String index4Page(User user, HttpSession session, Model model) {
        if (Validate.validatelogin(session, user, model)) {
            // 防止表单重复提交
            return "redirect:/main.html";
        } else {
            return "login";
        }

    }

    /**
     * 登录（postman）
     * @param userName
     * @param passWord
     * @param model
     * @return
     */
    @GetMapping("/login4Postman")
    public String index4Postman(@RequestParam(value = "userName") String userName,
                                @RequestParam(value = "passWord") String passWord,
                                @RequestHeader Map<String, String> header,
                                HttpSession session,
                                @RequestParam(value = "model", required = false) Model model) {
        User user = new User();
        user.setUserName(userName);
        user.setPassWord(passWord);
        log.info(header + "");
        session.setAttribute("loginUser", user);
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
        log.info("indexPage 执行");
        return "index";
    }

}
