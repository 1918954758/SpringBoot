package com.zichen.regexp.regexp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @name: RegExp_05
 * @description: 分组： (?!pattern)  mmm(?!a|b|c) 找不到mmma中的mmm  找不到mmmb中的mmm   找不到mmmc中的mmm
 * @author: zichen
 * @date: 2021/6/5  12:54
 */
public class RegExp_05 {
    private final static Logger log = LoggerFactory.getLogger(RegExp_05.class);
    public static void main(String[] args) {
        String content = "可以找到 Windows85中的Windows   Windows98中的Windows % Windows3.1  WindowsNT中的Windows  以及 Windows2000中的Windows，这四个";
        String regex = "Windows(?!85|98|NT|2000)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            log.info("找到： " + matcher.group());
        }
    }
}
