package com.zichen.admin.serivce.serviceImpl;

import com.zichen.admin.bean.City;
import com.zichen.admin.mapper.CityMapper;
import com.zichen.admin.serivce.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @name: CityService
 * @description:
 * @author: zichen
 * @date: 2021/5/23  16:49
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;

    public City getCityById(int id) {
        return cityMapper.getCityById(id);
    }

    public void saveCity(City city) {
        cityMapper.saveCity(city);
    }

    public void insertCity(City city) {
        cityMapper.insertCity(city);
    }
}
