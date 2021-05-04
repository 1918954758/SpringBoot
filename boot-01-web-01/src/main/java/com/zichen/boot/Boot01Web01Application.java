package com.zichen.boot;

import com.zichen.boot.config.WebConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class Boot01Web01Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Boot01Web01Application.class, args);

        // 测试有没有添加的组件 HiddenHttpMethodFilter
        HiddenHttpMethodFilter hiddenHttpMethodFilter = run.getBean("hiddenHttpMethodFilter", HiddenHttpMethodFilter.class);
        log.info("hiddenHttpMethodFilter组件 = " + hiddenHttpMethodFilter);
    }

}
