package com.zichen.boot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @name: User
 * @description:
 * @author: zichen
 * @date: 2021/5/2  22:03
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class User {
    private String name;
    private Integer age;

    private Pet pet;

    /*
     * 定制有参构造器，暂时没有对应的注解
     */
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
