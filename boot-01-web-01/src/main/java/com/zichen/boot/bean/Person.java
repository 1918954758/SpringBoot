package com.zichen.boot.bean;

import lombok.Data;

import java.util.Date;

/**
 * @name: Person
 * @description:
 * @author: zichen
 * @date: 2021/5/8  11:53
 */
@Data
public class Person {

    private String userName;
    private Integer age;
    private Date birth;
    private Pet pet;

}
