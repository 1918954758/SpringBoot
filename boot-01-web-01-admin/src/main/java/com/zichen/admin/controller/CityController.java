package com.zichen.admin.controller;

import com.zichen.admin.bean.City;
import com.zichen.admin.serivce.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    //localhost:8080/saveCity?name=重庆&state=03&country=中国
    @ResponseBody
    //@PostMapping("/city")
    @GetMapping("/saveCity")
    public void saveCity(@RequestParam("name") String name, @RequestParam("state") String state, @RequestParam("country") String country) {
        City city = new City();
        city.setName(name);
        city.setState(state);
        city.setCountry(country);
        cityService.saveCity(city);
    }

    @ResponseBody
    @PostMapping("/insertCity")
    public City insertCity(City city) {
        cityService.insertCity(city);
        return city;
    }
}
