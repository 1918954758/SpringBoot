package com.zichen.admin.mapper;

import com.zichen.admin.bean.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @name: CityMapper
 * @description:
 * @author: zichen
 * @date: 2021/5/23  16:47
 */
@Mapper
public interface CityMapper {

    @Select("select * from City where id = #{id}")
    City getCityById(int id);

    void insertCity(City city);
}
