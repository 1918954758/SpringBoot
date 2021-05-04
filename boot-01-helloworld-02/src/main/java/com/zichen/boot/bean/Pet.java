package com.zichen.boot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @name: Pet
 * @description:
 * @author: zichen
 * @date: 2021/5/4  11:42
 */
//@ConfigurationProperties(prefix = "pet") 和properties配置文件绑定注解
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Pet {
    private String name;
    private Double weight;
}
