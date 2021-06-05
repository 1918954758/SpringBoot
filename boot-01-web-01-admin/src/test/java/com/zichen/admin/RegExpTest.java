package com.zichen.admin;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.beans.Transient;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @name: RegExpTest
 * @description:正则表达式练习
 * @author: zichen
 * @date: 2021/6/6  0:34
 */
@Slf4j
@SpringBootTest
public class RegExpTest {


    //===================== split =====================
    /**
     * 对字符串“张三@@@李四@@王五@茅台”进行切割，去掉@符号。
     */
    @Test
    public void test01() {
        String content = "张三@@@李四@@王五@茅台";
        String[] s = content.split("@+");
        for (String s1 : s) {
            System.out.println(s1);
        }
    }

    /**
     * 【以叠词切割】：如字符串"abccsasahhhz"按“叠词”来切割就变成了“ab”，“sasa”，“z”。
     *  因为“cc”、“hhh”都是叠词，需要切割掉。现在请将字符串“张三@@@李四￥￥王五ssssssss江流儿”按照叠词切割。
     */
    @Test
    public void test02() {
        String content = "张三@@@李四￥￥王五ssssssss江流儿";
        //String[] split = content.split("(\\W)\\1+|(\\w)\\2+");
        String[] split = content.split("(.)\\1+");
        for (String s : split) {
            System.out.println(s);
        }
    }


    //===================== replaceAll =====================

    /**
     * 将字符串“张三@@@李四YYY王五*****王尼玛”中的叠词替换为：“、”
     */
    @Test
    public void test03() {
        String content = "张三@@@李四YYY王五*****王尼玛";
        String s = content.replaceAll("(.)\\1+", "、");
        System.out.println(s);
    }

    /**
     * 将“张三@@@李四YYY王五*****王尼玛”中的叠词替换为单字符，即结果为：“张三@李四Y王五*王尼玛”
     */
    @Test
    public void test04() {
        String content = "张三@@@李四YYY王五*****王尼玛";
        String $1 = content.replaceAll("(.)\\1+", "$1");
        System.out.println($1);
    }

    /**
     * 获取字符串“Hi ! Don't be moved by yourself Fzz”中为两个字母的单词。即Hi、be、by
     */
    @Test
    public void test05() {
        String content = "Hi ! Don't be moved by yourself Fzz";
        Pattern compile = Pattern.compile("\\b[\\w]{2}\\b");
        Matcher matcher = compile.matcher(content);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    /**
     * 口吃怎么办？需求：请将下面的字符串“我我我......我我......爱...爱爱...学...学......学编程”改为：“我爱学编程”
     */
    @Test
    public void test06() {
        String content = "我我我......我我......爱...爱爱...学...学......学编程";
        String s = content.replaceAll("[.]+", "");
        System.out.println(s);
        //我我我我我爱爱爱学学学编程
        String $1 = s.replaceAll("(.)\\1+", "$1");
        System.out.println($1);
    }

    /**
     * 获取网页中的URL
     */
    @Test
    public void test07() {
        //url(https://dss1.bdstatic.com/5eN1bjq8AAUYm2zgoY3K/r/www/cache/static/protocol/https/home/img/sugbg_1762fe7.png)
        String regex = "url\\(http[s]?:\\/\\/[\\w-.]+\\/[\\w\\/]*\\/[\\w.]*\\)";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(TXT.content);
        while (matcher.find()) {
            System.out.println(matcher.group().replaceAll("url\\(|\\)", ""));
        }
    }
}
