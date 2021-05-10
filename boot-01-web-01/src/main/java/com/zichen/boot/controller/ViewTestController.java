package com.zichen.boot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class ViewTestController {

    @GetMapping("/goto/home")
    public String gotoHome(Map<String, Object> map, Model model) {
        model.addAttribute("age", 18);
        map.put("name", "zichen");
        map.put("baidu", "http://www.baidu.com");
        return "home";
    }

}
