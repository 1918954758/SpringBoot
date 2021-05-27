package com.zichen.admin.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @TableField(exist = false)
    private String userName;
    @TableField(exist = false)
    private String passWord;
    @TableField(exist = false)
    private Integer currentPage;

    //@TableId(type = IdType.AUTO)//主键自增策略，默认会生成 19 位的id
    private Long id;
    private String name;
    private Integer age;
    private String email;

    public User(Long id, String name, Integer age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
