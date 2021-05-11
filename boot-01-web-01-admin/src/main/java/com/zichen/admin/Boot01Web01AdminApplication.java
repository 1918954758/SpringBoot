package com.zichen.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Boot01Web01AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(Boot01Web01AdminApplication.class, args);
    }

}
