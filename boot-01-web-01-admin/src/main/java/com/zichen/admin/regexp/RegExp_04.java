package com.zichen.admin.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @name: RegExp_04
 * @description: 分组： (?=a|b|c)  mmma中的mmm或者mmmb中的mmm或者mmmc中的mmm   找不到 mmmd中的mmm。。。
 * @author: zichen
 * @date: 2021/6/5  12:46
 */
public class RegExp_04 {
    public static void main(String[] args) {
        String content = "可以找到 Windows85中的Windows   Windows98中的Windows % Windows3.1  WindowsNT中的Windows  以及 Windows2000中的Windows，这四个";
        String regex = "Windows(?=85|98|NT|2000)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("找到： " + matcher.group());
        }
    }
}
