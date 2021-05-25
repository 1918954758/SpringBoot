package com.zichen.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zichen.admin.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
