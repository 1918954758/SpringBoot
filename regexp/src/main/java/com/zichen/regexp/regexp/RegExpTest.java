package com.zichen.regexp.regexp;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @name: RegExpTest
 * @description:
 * @author: zichen
 * @date: 2021/6/5  0:26
 */
public class RegExpTest {
    private static final Logger log = LoggerFactory.getLogger(RegExpTest.class);
    public static void main(String[] args) {
        String content = "abcaB-cEGBfdaBC";
        //String regex = "a((?i)b)c";
        String regex = "\\-";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            log.info("找到：" + matcher.group(0));
        }
    }
}
