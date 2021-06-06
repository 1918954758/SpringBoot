package com.zichen.regexp.regexp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @name: RegExp_03
 * @description: 分组：(?:pattern)  等价于 'mmma|mmmb' mmm(?:a|b)
 * @author: zichen
 * @date: 2021/6/5  12:37
 */
public class RegExp_03 {
    private final static Logger log = LoggerFactory.getLogger(RegExp_03.class);
    public static void main(String[] args) {
        String content = "industry_345_industriesy5heg";
        String regex = "industr(?:y|ies)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            log.info("找到： " + matcher.group());
        }
    }
}
