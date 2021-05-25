package com.zichen.admin.serivce;

import com.zichen.admin.bean.City;
import com.zichen.admin.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @name: CityService
 * @description:
 * @author: zichen
 * @date: 2021/5/23  16:49
 */
@Service
public interface CityService {

    public City getCityById(int id);

    public void saveCity(City city);

    public void insertCity(City city);
}
