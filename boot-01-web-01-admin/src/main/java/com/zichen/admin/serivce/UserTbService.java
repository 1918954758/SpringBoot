package com.zichen.admin.serivce;

import com.zichen.admin.bean.UserTb;
import com.zichen.admin.mapper.UserTbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @name: UserTbService
 * @description:
 * @author: zichen
 * @date: 2021/5/23  12:51
 */
@Service
public interface UserTbService {

    public UserTb getUserTbByDpetNo(Integer id);

    public void insertDpet(Map map);

    public void saveUserTb(UserTb userTb);

    public void saveUserTb4Annotation(UserTb userTb);
}
