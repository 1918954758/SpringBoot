package com.zichen.admin.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @name: City
 * @description:
 * @author: zichen
 * @date: 2021/5/23  16:42
 */
@Data
@ToString
public class City {
    private Long id;
    private String name;
    private String state;
    private String country;
}
