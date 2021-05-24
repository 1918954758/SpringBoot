package com.zichen.admin.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @name: UserTb
 * @description:
 * @author: zichen
 * @date: 2021/5/23  16:14
 */
@Data
@ToString
public class UserTb {

    private Integer id;
    private String uName;
    private String  uCreateTime;
    private int age;

}
