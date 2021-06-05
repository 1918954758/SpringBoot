package com.zichen.admin.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @name: RegExp_12
 * @description: 分组实例
 * @author: zichen
 * @date: 2021/6/5  16:24
 */
public class RegExp_12 {
    public static void main(String[] args) {
//        String content = "http://www.sohu.com:8080/abc/index.htm";
//        String regex = "^([a-zA-Z]+)://([\\w-.]+):(\\d+)[\\w-/]*/([\\w.]+)$";
//        Matcher matcher = Pattern.compile(regex).matcher(content);
//        while (matcher.find()) {
//            System.out.println("ALL：" + matcher.group(0));
//            System.out.println("协议：" + matcher.group(1));
//            System.out.println("域名：" + matcher.group(2));
//            System.out.println("端口号：" + matcher.group(3));
//            System.out.println("页面地址：" + matcher.group(4));
//        }

        //命名捕获
        String content = "http://www.sohu.com:8080/abc/index.htm";
        String regex = "^(?<protocol>[a-zA-Z]+):\\/\\/(?<dumainname>[\\w-.]+):(?<port>\\d+)[\\w-/]*\\/(?<pageuri>[\\w.]+)$";
        Matcher matcher = Pattern.compile(regex).matcher(content);
        while (matcher.find()) {
            System.out.println("ALL：" + matcher.group(0));
            System.out.println("协议：" + matcher.group("protocol"));
            System.out.println("域名：" + matcher.group("dumainname"));
            System.out.println("端口号：" + matcher.group("port"));
            System.out.println("页面地址：" + matcher.group("pageuri"));
        }
    }
}
/**
 * ALL：http://www.sohu.com:8080/abc/index.htm
 * 协议：http
 * 域名：www.sohu.com
 * 端口号：8080
 * 页面地址：index.htm
 */
