package com.zichen.admin.controller;

import com.zichen.admin.bean.DataTest_01;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
public class TableController {

    @GetMapping("/basic_table")
    public String basic_table() {
        log.info(10/0 + "");
        return "table/basic_table";
    }
    //templates/table/basic_table.html

    @GetMapping("/dynamic_table")
    public String dynamic_table(Model model) {
        List<DataTest_01> dataTest_01s = Arrays.asList(new DataTest_01("aaa", "12"),
                new DataTest_01("bbb", "13"),
                new DataTest_01("ccc", "14"),
                new DataTest_01("ddd", "15"));
        model.addAttribute("dataTest_01s", dataTest_01s);
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
