package com.zichen.admin.controller;

import com.zichen.admin.bean.City;
import com.zichen.admin.serivce.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @name: CityController
 * @description:
 * @author: zichen
 * @date: 2021/5/23  16:50
 */
@Controller
public class CityController {

    @Autowired
    private CityService cityService;

    @ResponseBody
    @GetMapping("/queryCityById")
    public City getCityById(@RequestParam("id") int id) {
        return cityService.getCityById(id);
    }
}
