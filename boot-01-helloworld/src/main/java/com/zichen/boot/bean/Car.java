package com.zichen.boot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @name: Car
 * @description:
 * @author: zichen
 * @date: 2021/5/3  11:55
 */

/**
 * 只有在容器中的组件才会拥有SpringBoot提供的功能
 */
//@Component
@ConfigurationProperties(prefix = "mycar")
@AllArgsConstructor  //全参构造器
@NoArgsConstructor  //无参构造器
@Data   //setter、getter方法
@ToString   //toString方法
public class Car {

    private String brand;
    private Integer price;

}
