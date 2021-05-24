package com.zichen.admin.mapper;

import com.zichen.admin.bean.UserTb;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserTbMapper {

    UserTb getUserTb(Integer id);

    void insertDpet(Map map);

    void saveUserTb(UserTb userTb);
}
