package com.zichen.boot.controller;

import com.zichen.boot.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.testng.annotations.Test;

/**
 * @name: HelloController
 * @description:
 * @author: zichen
 * @date: 2021/6/6  12:58
 */
@RestController
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);
    @Value("${person.name:李四}")
    private String name;

    @Autowired
    private User user;

    @Value("${JAVA_HOME}")
    private String javaHome;

    @Value("${MAVEN_HOME}")
    private String mavenHome;

    @Value("${os.name}")
    private String osName;

    @GetMapping("/")
    public String hello() {
        log.info("request into ...");
        return "Hello" + name;
    }

    @Test
    public void test() {
        log.info("fweqafdas");
    }

    @GetMapping("/getUser")
    public User getUser() {
        return user;
    }


    @GetMapping("/getJavaHome")
    public String getJavaHome() {
        return javaHome;
        //D:\software\jdk\jdk1.8\jdk\jdk1.8.0_131
    }

    @GetMapping("/getMavenHome")
    public String getMavenHome() {
        return mavenHome;
    }

    @GetMapping("/getOsName")
    public String getOsName() {
        return osName;
    }
}
