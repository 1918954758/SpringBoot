package com.zichen.admin.util;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * class: IdCardGeneratorUtil
 * description: 身份证生成器
 * author: ~~~
 * date: 2021/6/3 - 10:02
 */
@Slf4j
public class IdCardGenerator {




    public String generate(Integer city,  String time) {
        StringBuilder generater = new StringBuilder();
        //generater.append(this.randomAreaCode());
        generater.append(city);
        //generater.append(this.randomBirthday());
        generater.append(time);
        generater.append(this.randomCode());
        generater.append(this.calcTrailingNumber(generater.toString().toCharArray()));
        return generater.toString();
    }

    public int randomAreaCode() {
        int index = (int) (Math.random() * IdCardGeneratorUtil.areaCode.size());
        Collection<Integer> values = IdCardGeneratorUtil.areaCode.values();
        Iterator<Integer> it = values.iterator();
        int i = 0;
        int code = 0;
        while (i < index && it.hasNext()) {
            i++;
            code = it.next();
        }
        return code;
    }

    public String randomBirthday() {
        Calendar birthday = Calendar.getInstance();
        birthday.set(Calendar.YEAR, (int) (Math.random() * 60) + 1950);
        birthday.set(Calendar.MONTH, (int) (Math.random() * 12));
        birthday.set(Calendar.DATE, (int) (Math.random() * 31));
        StringBuilder builder = new StringBuilder();
        builder.append(birthday.get(Calendar.YEAR));
        long month = birthday.get(Calendar.MONTH) + 1;
        if (month < 10) {
            builder.append("0");
        }
        builder.append(month);
        long date = birthday.get(Calendar.DATE);
        if (date < 10) {
            builder.append("0");
        }
        builder.append(date);
        return builder.toString();
    }

    /*
     * <p>18位身份证验证</p>
     * 根据〖中华人民共和国国家标准 GB 11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。
     * 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
     * 第十八位数字(校验码)的计算方法为：
     * 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
     * 2.将这17位数字和系数相乘的结果相加。
     * 3.用加出来和除以11，看余数是多少？
     * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2。
     * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。
     */
    public char calcTrailingNumber(char[] chars) {
        if (chars.length < 17) {
            return ' ';
        }
        int[] c = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
        char[] r = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
        int[] n = new int[17];
        int result = 0;
        for (int i = 0; i < n.length; i++) {
            n[i] = Integer.parseInt(chars[i] + "");
        }
        for (int i = 0; i < n.length; i++) {
            result += c[i] * n[i];
        }
        return r[result % 11];
    }

    public String randomCode() {
        int code = (int) (Math.random() * 1000);
        if (code < 10) {
            return "00" + code;
        } else if (code < 100) {
            return "0" + code;
        } else {
            return "" + code;
        }
    }

    public static void main(String[] args) {
        IdCardGenerator g = new IdCardGenerator();
        //住的地址
        Integer city=IdCardGeneratorUtil.areaCode.get("苏州市");
        //出生时间
        String time="1999-05-29".replaceAll("-", "");
        //校检日期身份证上的后四位分别是3个顺序和最后一位的校验
        StringBuffer buffer=new StringBuffer();
        buffer.append(city).append(time);
        //System.out.println(city+time+g.randomCode()+g.calcTrailingNumber(buffer.toString().toCharArray()));

        for (int i = 0; i < 100; i++) {
            // System.out.print(IdcardUtils.validateCard(g.generate()));
            //System.out.print("\t");
            System.out.print(g.generate(city, time));
            System.out.print(", ");
        }
    }

}
