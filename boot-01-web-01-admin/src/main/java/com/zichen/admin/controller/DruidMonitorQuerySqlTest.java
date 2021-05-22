package com.zichen.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @name: DruidMonitorQuerySqlTest
 * @description:
 * @author: zichen
 * @date: 2021/5/22  11:15
 */
@Controller
public class DruidMonitorQuerySqlTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @ResponseBody
    @GetMapping("/sql")
    public String querySqlForDruidMonitor() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from testdb.usertb");
        return maps.toString();
    }
}
