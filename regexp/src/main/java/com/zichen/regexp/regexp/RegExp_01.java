package com.zichen.regexp.regexp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * class: RegExp_01
 * description:
 * author: ~~~
 * date: 2021/6/3 - 13:41
 */
public class RegExp_01 {
    private final static Logger log = LoggerFactory.getLogger(RegExp_01.class);
    public static void main(String[] args) {
        String s1 = "sfrh\r24 gd35 P_dw\ned_fe3\t34";
        // 1. 匹配 \d \D \w \W \s \S
//        String e1 = "\\d+";
//        test01(s1, e1, "匹配数字", "测试\\d");
//        String e2 = "\\D+";
//        test01(s1, e2, "匹配非数字", "测试\\D");
//        String e3 = "\\w+";
//        test01(s1, e3, "匹配字母数字下划线", "测试\\w");
//        String e4 = "\\W+";
//        test01(s1, e4, "匹配非字母数字下划线", "测试\\W");
//        String e5 = "\\s+";
//        test01(s1, e5, "匹配空白字符", "测试\\s");
//        String e6 = "\\S+";
//        test01(s1, e6, "匹配非空白字符", "测试\\S");
        // 2. 匹配 ^ $ \< \> \b \B
        String ss1 = "world aworld worldb aworldf aworld world";
        String ee1 = "^world";
        test01(ss1, ee1, "匹配行头", "测试^");
        String ee2 = "world$";
        test01(ss1, ee2, "匹配行尾", "测试$");
        String ee3 = "\\bworld";
        test01(ss1, ee3, "匹配单词开头", "测试\\b");
        String ee4 = "world\\b";
        test01(ss1, ee4, "匹配单词结尾", "测试\\b");
        String ee5 = "\\Bworld";
        test01(ss1, ee5, "匹配非单词开头", "测试\\B");
        String ee6 = "world\\B";
        test01(ss1, ee6, "匹配非单词结尾", "测试\\B");
    }

    private static void test01(String s, String e, String msg, String title) {
        Pattern compile = Pattern.compile(e);
        Matcher matcher = compile.matcher(s);
        log.info("==============================【{} start】==============================", title);
        while (matcher.find()) {
            String group = matcher.group(0);
            log.info("【" + msg + "】 : " + group);
        }
        log.info("==============================【{} end】==============================", title);
    }


}
