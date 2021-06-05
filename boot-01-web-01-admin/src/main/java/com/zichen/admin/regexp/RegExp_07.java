package com.zichen.admin.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @name: RegExp_07
 * @description:  .   and   ?  中括号里面的点和问号，可以不用转义，其他位置的点或者问号需要转义
 * @author: zichen
 * @date: 2021/6/5  13:44
 */
public class RegExp_07 {
    public static void main(String[] args) {
        String content = "11133.344566.?534543";
        //String regex = "(.)";// 1
        //String regex = "(.)"; // 1
        //String regex = "[.]";//  .
        String regex = "[\\.]";// .
        //String regex = "(?)";//
        //String regex = "(\\?)"; // ?
        //String regex = "[?]";// ?
        //String regex = "[\\?]"; // ?
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        boolean b = matcher.find();
        System.out.println("" + (b == true ? matcher.group() : -1));
    }
}
