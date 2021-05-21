package com.zichen.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan(basePackages = {"com.zichen.admin"})
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class Boot01Web01AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(Boot01Web01AdminApplication.class, args);
    }

}
