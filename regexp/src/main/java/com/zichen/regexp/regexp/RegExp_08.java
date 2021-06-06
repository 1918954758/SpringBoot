package com.zichen.regexp.regexp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @name: RegExp_08
 * @description:反向引用实例（正则表达式内部）
 * @author: zichen
 * @date: 2021/6/5  14:44
 */
public class RegExp_08 {
    private final static Logger log = LoggerFactory.getLogger(RegExp_08.class);
    public static void main(String[] args) {
        String content = "12534321-33311155574333-33344466699913515411";
        String regex = "\\d{5}-(\\d)\\1{2}(\\d)\\2{2}(\\d)\\3{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            log.info("找到：" + matcher.group());
        }
    }
}
/**
 * 找到：34321-333111555
 * 找到：74333-333444666
 */
