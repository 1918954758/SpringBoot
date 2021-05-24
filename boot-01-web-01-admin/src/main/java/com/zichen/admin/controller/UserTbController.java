package com.zichen.admin.controller;

import com.zichen.admin.bean.Dpet;
import com.zichen.admin.bean.UserTb;
import com.zichen.admin.serivce.UserTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @name: UserTbController
 * @description:
 * @author: zichen
 * @date: 2021/5/23  12:55
 */
@Controller
public class UserTbController {

    @Autowired
    private UserTbService userTbService;

    @ResponseBody
    @GetMapping("/getUserTb")
    public String getUserTbByDpetNo(@RequestParam("id") Integer dpetNo) {
        UserTb userTbByDpetNo = userTbService.getUserTbByDpetNo(dpetNo);
        return userTbByDpetNo.toString();
    }

    //dpetno=#{dpetNo}, dpetname=#{dpetName}, loc=#{loc}
    // localhost:8080/insert?dpetNo=23&dpetName=的丽热巴&loc=23
    @ResponseBody
    @GetMapping("/insert")
    public void insertDpet(@RequestParam("dpetNo") Integer dpetNo, @RequestParam("dpetName") String dpetName, @RequestParam("loc") String loc) {
        Map<String, Object> map = new HashMap<>();
        map.put("dpetNo", dpetNo);
        map.put("dpetName", dpetName);
        map.put("loc", loc);
        userTbService.insertDpet(map);
    }

    @ResponseBody
    @PostMapping("/saveUserTb")
    public UserTb saveUserTb(UserTb userTb) {
        userTbService.saveUserTb(userTb);
        return userTb;
    }

    @ResponseBody
    @PostMapping("/saveUserTb4Annotation")
    public UserTb saveUserTb4Annotation(UserTb userTb) {
        userTbService.saveUserTb4Annotation(userTb);
        return userTb;
    }
}
