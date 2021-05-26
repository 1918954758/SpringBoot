package com.zichen.admin.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zichen.admin.bean.DataTest_01;
import com.zichen.admin.bean.User;
import com.zichen.admin.serivce.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
public class TableController {

    @Autowired
    private UserService userService;

    @GetMapping("/basic_table")
    public String basic_table() {
        //log.info(10/0 + "");
        return "table/basic_table";
    }
    //templates/table/basic_table.html

    @GetMapping("/dynamic_table")
    public String dynamic_table(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage, Model model) {
        List<DataTest_01> dataTest_01s = Arrays.asList(new DataTest_01("aaa", "12"),
                new DataTest_01("bbb", "13"),
                new DataTest_01("ccc", "14"),
                new DataTest_01("ddd", "15"));
        model.addAttribute("dataTest_01s", dataTest_01s);

        Wrapper wrapper = new QueryWrapper();
        //wrapper.getCustomSqlSegment();

        //List<User> list = userService.list();
        //model.addAttribute("users", list);

        Page<User> userPage = new Page<>(currentPage, 5);
        Page<User> page = userService.page(userPage);
        // 当前页数
        long current = page.getCurrent();
        // 页数
        long pages = page.getPages();
        // 总条数
        long total = page.getTotal();
        // 查询到的记录数据
        List<User> records = page.getRecords();

        model.addAttribute("pages", page);

        return "table/dynamic_table";
    }

    @GetMapping("/responsive_table")
    public String responsive_table() {
        return "table/responsive_table";
    }

    @GetMapping("/editable_table")
    public String editable_table() {
        return "table/editable_table";
    }
}
