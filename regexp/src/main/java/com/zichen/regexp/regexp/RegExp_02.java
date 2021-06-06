package com.zichen.regexp.regexp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @name: RegExp_02
 * @description: 分组 (?<name>pattern)   (?'name'pattern)
 * @author: zichen
 * @date: 2021/6/5  12:31
 */
public class RegExp_02 {
    private final static Logger log = LoggerFactory.getLogger(RegExp_02.class);
    public static void main(String[] args) {
        String content = "abcdefA BCe t35$13g adf#43qg";
        String regex = "(?<g1>abc)(?<g2>def)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            log.info("找到所有: " + matcher.group(0));
            log.info("找到第1组: " + matcher.group(1));
            log.info("找到第1组【命名捕获】: " + matcher.group("g1"));
            log.info("找到第2组: " + matcher.group(2));
            log.info("找到第2组【命名捕获】: " + matcher.group("g2"));
        }
    }
}
