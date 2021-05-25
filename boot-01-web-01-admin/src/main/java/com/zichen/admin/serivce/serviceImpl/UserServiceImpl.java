package com.zichen.admin.serivce.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zichen.admin.bean.User;
import com.zichen.admin.mapper.UserMapper;
import com.zichen.admin.serivce.UserService;
import org.springframework.stereotype.Service;

/**
 * @name: UserServiceImpl
 * @description:
 * @author: zichen
 * @date: 2021/5/25  21:22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
