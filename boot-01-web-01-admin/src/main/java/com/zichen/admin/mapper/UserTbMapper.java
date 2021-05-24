package com.zichen.admin.mapper;

import com.zichen.admin.bean.UserTb;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.Map;

@Mapper
public interface UserTbMapper {

    UserTb getUserTb(Integer id);

    void insertDpet(Map map);

    void saveUserTb(UserTb userTb);

    @Insert("insert into userTb(uName, uCreateTime, age) values (#{uName}, #{uCreateTime}, #{age})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void saveUserTb4Annotation(UserTb userTb);
}
