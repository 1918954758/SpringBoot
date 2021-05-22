package com.zichen.admin;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @name: Boot01Web01AdminApplication
 * @description:
 * @author: zichen
 * @date: 2021/5/22  0:51
 */
@Slf4j
@SpringBootTest
public class Boot01Web01AdminApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Test
    public void contextLoads() {
        Integer count = jdbcTemplate.queryForObject("select count(*) from employee", Integer.class);
        log.info("{}", count);

        log.info("自定义数据源类型：{}", dataSource.getClass());
    }
}
