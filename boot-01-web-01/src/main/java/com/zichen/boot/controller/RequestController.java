package com.zichen.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @name: RequestController
 * @description:
 * @author: zichen
 * @date: 2021/5/5  22:03
 */
@Controller
public class RequestController {

    @GetMapping("/goto")
    public String goToPage(HttpServletRequest request) {
        request.setAttribute("msg", "成功了...");
        request.setAttribute("code", 200);
        return "forward:success"; //转发到 /success请求
    }

    @GetMapping("/param")
    public String testParam(Map<String, Object> map,
                            Model model,
                            HttpServletRequest httpServletRequest,
                            HttpServletResponse httpServletResponse) {
        map.put("map", "map_setValue");
        model.addAttribute("model", "model_addValue");
        httpServletRequest.setAttribute("httpServletRequest", "httpServletRequest_setValue");

        Cookie cookie = new Cookie("cookie_key", "cookie_value");
        httpServletResponse.addCookie(cookie);
        return "forward:/success";//forward:/success   --  视图名
    }

    @ResponseBody
    @GetMapping("/success")
    public Map success(@RequestAttribute(value = "msg", required = false) String msg,
                       @RequestAttribute(value = "code", required = false) Integer code,
                       HttpServletRequest request) {
        Object msg1 = request.getAttribute("msg");
        Map<String, Object> map = new HashMap<>();
        map.put("reMethod_msg", msg1);
        map.put("annotation_msg1", msg);
        map.put("annotation_msg2", code);

        map.put("map", request.getAttribute("map"));
        map.put("model", request.getAttribute("model"));
        map.put("httpServletRequest", request.getAttribute("httpServletRequest"));
        return map;
    }
}
