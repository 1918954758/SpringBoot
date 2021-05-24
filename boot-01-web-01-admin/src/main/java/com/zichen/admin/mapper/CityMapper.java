package com.zichen.admin.mapper;

import com.zichen.admin.bean.City;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
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

    //@Insert("insert into city(`name`, `state`, `country`) values (#{name}, #{state}, #{country})")
    //@Options(useGeneratedKeys = true, keyProperty = "id")
    void saveCity(City city);

    void insertCity(City city);
}
