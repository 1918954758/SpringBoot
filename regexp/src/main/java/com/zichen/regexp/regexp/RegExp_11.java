package com.zichen.regexp.regexp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @name: RegExp_11
 * @description: 验证整数或者小数，要求考虑正负号  不匹配 00.343
 * @author: zichen
 * @date: 2021/6/5  15:59
 */
public class RegExp_11 {
    private static final Logger log = LoggerFactory.getLogger(RegExpTest.class);
    public static void main(String[] args) {
        //String content = "-1 2.453 14.65 0.34 3@54";
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个数（正数、负数、小数、整数...）");
        String content = scanner.nextLine();
        //String regex = "^[-]?(([0])|([^0][0-9]+))(\\.\\d+)?$";
        String regex = "^[-]?([1-9]\\d*|0)+(\\.\\d+)?$";
        Matcher matcher = Pattern.compile(regex).matcher(content);
        if (matcher.find()) {
            log.info("匹配成功！");
            log.info(matcher.group());
        } else  {
            log.info("匹配失败！");
        }
    }
}
