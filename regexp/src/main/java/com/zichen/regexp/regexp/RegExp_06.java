package com.zichen.regexp.regexp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @name: RegExp_06
 * @description: 贪婪匹配 和 非贪婪匹配
 * @author: zichen
 * @date: 2021/6/5  13:06
 */
public class RegExp_06 {
    private final static Logger log = LoggerFactory.getLogger(RegExp_06.class);
    public static void main(String[] args) {
        String content = "11133344566534543";
        //String regex = ".+";//贪婪匹配   11133344566534543
        String regex = ".+?";//非贪婪匹配   1
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        boolean b = matcher.find();
        log.info("" + (b == true ? matcher.group() : -1));
    }
}
