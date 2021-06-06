package com.zichen.regexp.regexp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

/**
 * @name: RegExp_10
 * @description:结巴程序
 * @author: zichen
 * @date: 2021/6/5  15:02
 */
public class RegExp_10 {
    private static final Logger log = LoggerFactory.getLogger(RegExpTest.class);
    public static void main(String[] args) {
        String content ="1..22...333....4444.....55555......666666.......7777777.....8888888.....";

        String s1 = content.replaceAll("(\\d*)(\\.*)", "$1$2");
        String s2 = content.replaceAll("(\\d)*(\\.)*", "$1$2");
        String s3 = content.replaceAll("(.)\\1+", "$1");
        System.out.println(s1);
        //1..22...333....4444.....55555......666666.......7777777.....8888888.....
        log.info(s2);
        //1.2.3.4.5.6.7.8.
        log.info(s3);
        //1.2.3.4.5.6.7.8.

        String s = Pattern.compile("(.)\\1+").matcher(content).replaceAll("$1");
        log.info(s);
        //1.2.3.4.5.6.7.8.
    }
}
