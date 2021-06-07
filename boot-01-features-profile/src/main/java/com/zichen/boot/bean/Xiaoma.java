package com.zichen.boot.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * class: Xiaoma
 * description:
 * author: ~~~
 * date: 2021/6/7 - 09:31
 */
@Profile(value = {"test"})
@Data
@Component
@ConfigurationProperties(value = "person")
public class Xiaoma implements User {
    private String name;
    private Integer age;
}
