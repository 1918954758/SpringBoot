package com.zichen.admin.regexp;

/**
 * @name: RegExp_09
 * @description:反向引用实例（正则表达式外部）
 * @author: zichen
 * @date: 2021/6/5  14:54
 */
public class RegExp_09 {
    public static void main(String[] args) {
        String newStr = "abc def".replaceFirst("(\\w+)\\s+(\\w+)", "$2 $1");
        System.out.println(newStr);
    }
}
/**
 * def abc
 */