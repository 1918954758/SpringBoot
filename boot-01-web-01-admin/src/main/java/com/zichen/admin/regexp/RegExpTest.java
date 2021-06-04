package com.zichen.admin.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @name: RegExpTest
 * @description:
 * @author: zichen
 * @date: 2021/6/5  0:26
 */
public class RegExpTest {
    public static void main(String[] args) {
        String content = "abcaB-cEGBfdaBC";
        //String regex = "a((?i)b)c";
        String regex = "\\-";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("找到：" + matcher.group(0));
        }
    }

}
