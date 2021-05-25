package com.zichen.admin;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.zichen.admin.bean.User;
import com.zichen.admin.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * class: SampleTest
 * description:
 * author: ~~~
 * date: 2021/5/25 - 14:31
 */
@Slf4j
@SpringBootTest
public class MyBaitsPlusSimpleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void select() {
        User user = userMapper.selectById(6L);
        log.info("查询到的信息：{}", user);
    }

    @Test
    public void selectAll() {
        List<User> users = userMapper.selectList(null);
        log.info("selectAll() : {}", users);
    }

    @Test
    public void insert() {
        User user = new User();
        user.setId(9L);
        user.setName("zichen22");
        user.setAge(44);
        user.setEmail("zichen@554.com");
        userMapper.insert(user);
        log.info("插入的数据是：{}", user);
    }


    @Test
    public void update() {
        /**
         * 封装 set数据（要更新的数据值）
         */
        User user1 = new User();
        user1.setAge(27);
        user1.setEmail("123456@zichen.com");
        Wrapper<User> wUser = new Wrapper<User>() {
            @Override
            public String getSqlSegment() {
                return null;
            }

            /**
             * 封装where 条件
             * @return
             */
            @Override
            public User getEntity() {
                User user1 = new User();
                user1.setId(6L);
                return user1;
            }

            @Override
            public MergeSegments getExpression() {
                return null;
            }

            @Override
            public void clear() {

            }
        };
        log.info("查询到更新前的信息：{}", userMapper.selectById(6L));
        log.info("======更新开始======");
        userMapper.update(user1, wUser);
        log.info("======更新完成======");
        log.info("查询到更新后的信息：{}", userMapper.selectById(6L));
    }


    @Test
    public void delete() {
        Wrapper<User> userWrapper = new Wrapper<User>() {
            /**
             * 用于生成 where 条件
             * @return
             */
            @Override
            public User getEntity() {
                User user = new User();
                user.setId(9L);
                return user;
            }

            @Override
            public MergeSegments getExpression() {
                return null;
            }

            @Override
            public void clear() {

            }

            @Override
            public String getSqlSegment() {
                return null;
            }
        };
        log.info("查询到delete前的信息：{}", userMapper.selectById(9L));
        userMapper.delete(userWrapper);
        log.info("查询到delete后的信息：{}", userMapper.selectById(9L));
    }

    @Test
    public void deleteById() {
        log.info("查询到delete前的信息：{}", userMapper.selectById(8L));
        userMapper.deleteById(8L);
        log.info("查询到delete前的信息：{}", userMapper.selectById(8L));

    }
}
