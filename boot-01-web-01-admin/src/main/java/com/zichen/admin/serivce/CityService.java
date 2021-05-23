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
public class CityService {

    @Autowired
    private CityMapper cityMapper;

    public City getCityById(int id) {
        return cityMapper.getCityById(id);
    }
}
