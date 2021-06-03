package com.zichen.admin.util;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * class: ValidateIdNo
 * description: 居民身份证校验
 * author: ~~~
 * date: 2021/6/3 - 08:30
 */
@Slf4j
public class ValidateIdNo {

    // 第一位 - 一级地区
    // 第二位 - 二级地区
    // 第三位 - 三级地区
    private String REGEXP_18_ID = "^\\d{6}[12][\\d]{3}[01][\\d][0123][\\d][\\d]{3}[Xx\\d]$";
    private static final String[] RATIO_ID = new String[]{"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"};
    private static final List CITY_CODE = Arrays.asList("11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71", "81", "82", "91");

    private static final Map<String, String> CITY_CODE_PARAMS = new HashMap(){{
        put("11", "北京");
        put("12", "天津");
        put("13", "河北");
        put("14", "山西");
        put("15", "内蒙古");
        put("21", "辽宁");
        put("22", "吉林");
        put("23", "黑龙江");
        put("31", "上海");
        put("32", "江苏");
        put("33", "浙江");
        put("34", "安徽");
        put("35", "福建");
        put("36", "江西");
        put("37", "山东");
        put("41", "河南");
        put("42", "湖北");
        put("43", "湖南");
        put("44", "广东");
        put("45", "广西");
        put("46", "海南");
        put("50", "重庆");
        put("51", "四川");
        put("52", "贵州");
        put("53", "云南");
        put("54", "西藏");
        put("61", "陕西");
        put("62", "甘肃");
        put("63", "青海");
        put("64", "宁夏");
        put("65", "新疆");
        put("71", "台湾");
        put("81", "香港");
        put("82", "澳门");
        put("91", "国外");
    }};
        /*
        11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",
        21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",
        33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",
        42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",
        51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",
        63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"
        */

    private static final Map<String, String> PART = new HashMap(){{
        put("1", "华北地区");
        put("2", "东北地区");
        put("3", "华东地区");
        put("4", "中南地区");
        put("5", "西南地区");
        put("6", "西北地区");
    }};
    public static void validateId(String id) {
        regExpValidate(id);
        idLen(id);
        lastCharValidate(id);
    }

    //校验系数位
    private static boolean lastCharValidate(String id) {
        // 1. 将身份证号码前面的17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7－9－10－5－8－4－2－1－6－3－7－9－10－5－8－4－2。
        String lastChar = "";
        int sum = 0;
        String[] idArrayStr = id.split("");
        //第1 位
        String partCode = idArrayStr[0];
        //第1，第2位校验
        String cityCode = idArrayStr[0] + idArrayStr[1];
        if (CITY_CODE.contains(cityCode)) {
            log.info("二级地区校验通过");
            String partCodeStr = PART.get(partCode);
            String cityCodeStr = CITY_CODE_PARAMS.get(cityCode);
            log.info("该号码归是【{}】, 【{}】的号码", partCodeStr, cityCodeStr);
        } else {
            log.info("二级地区校验不通过");
            return false;
        }
        // 最后一位校验
        for (int i = 0; i < 17; i++) {
            sum += Integer.valueOf(idArrayStr[i]) * Integer.valueOf(RATIO_ID[i]);
        }
        //余数只可能有0、1、2、3、4、5、6、7、8、9、10这11个数字。
        // 其分别对应的最后一位身份证的号码为1、0、X、9、8、7、6、5、4、3、2，其中的X是罗马数字10;
        int remainder = sum % 11;
        switch (remainder){
            case  0:
                lastChar = String.valueOf(1);
                break;
            case 1:
                lastChar = String.valueOf(0);
                break;
            case 2:
                lastChar = "X";
                break;
            case 3:
                lastChar = String.valueOf(9);
                break;
            case 4:
                lastChar = String.valueOf(8);
                break;
            case 5:
                lastChar = String.valueOf(7);
                break;
            case 6:
                lastChar = String.valueOf(6);
                break;
            case 7:
                lastChar = String.valueOf(5);
                break;
            case 8:
                lastChar = String.valueOf(4);
                break;
            case 9:
                lastChar = String.valueOf(3);
                break;
            case 10:
                lastChar = String.valueOf(2);
                break;
            default:
                log.info("没有位数与最后一位数字对应！！");
                break;
        }

        if (idArrayStr[17].equals(lastChar)) {
            log.info("最后一位参数位校验通过。。。");
            return true;
        } else {
            log.info("最后一位参数为校验不通过！！！");
            return false;
        }
    }

    //正则校验
    private static boolean regExpValidate(String id) {
        Pattern compile = Pattern.compile(id);
        Matcher matcher = compile.matcher(id);
        if (matcher.find()) {
            log.info("正则校验通过。。。。");
            return true;
        } else {
            log.info("正则校验不通过！！！");
            return false;
        }
    }

    //校验长度
    private static boolean idLen(String id) {
        if (id.length() == 18) {
            log.info("长度校验通过。。。。");
            return true;
        } else {
            int length = id.length();
            log.info("【{}】的度校验不通过！！！长度为【{}】", id, length);
            return false;
        }
    }

    public static void main(String[] args) {
        String strs = "320500199905297746, 320500199905293593, 320500199905291336, 320500199905292734, 320500199905294182, 32050019990529721X, 320500199905297658, 320500199905294342, 320500199905297746, 320500199905293091, 320500199905293526, 320500199905291379, 320500199905297084, 320500199905297500, 32050019990529174X, 320500199905291002, 320500199905293278, 320500199905291328, 320500199905293665, 320500199905299362, 320500199905296284, 320500199905293550, 320500199905290456, 32050019990529780X, 320500199905294713, 320500199905292312, 320500199905299565, 320500199905290149, 320500199905291037, 320500199905291512, 320500199905294916, 320500199905299477, 320500199905294449, 320500199905290763, 320500199905290704, 320500199905295628, 320500199905299709, 320500199905294270, 320500199905292101, 320500199905290819, 320500199905292996, 320500199905291416, 32050019990529094X, 320500199905291387, 320500199905295142, 32050019990529828X, 320500199905290747, 320500199905298482, 320500199905298757, 320500199905294764, 320500199905293534, 320500199905298626, 320500199905297885, 320500199905296081, 320500199905290392, 320500199905298343, 320500199905298124, 320500199905291256, 320500199905298458, 320500199905293542, 320500199905290691, 320500199905293446, 320500199905290261, 320500199905295089, 320500199905295863, 320500199905298941, 320500199905294887, 320500199905297105, 320500199905290261, 320500199905291934, 320500199905296639, 320500199905293462, 320500199905297607, 320500199905292064, 320500199905293948, 320500199905290675, 320500199905294182, 320500199905290819, 32050019990529035X, 320500199905292873, 320500199905299151, 320500199905290149, 320500199905299469, 320500199905295484, 320500199905294297, 320500199905294182, 32050019990529836X, 320500199905299389, 320500199905299645, 320500199905290421, 320500199905296866, 320500199905296612, 320500199905299514, 320500199905292187, 320500199905295695, 320500199905292283, 320500199905297981, 320500199905298079, 32050019990529297X, 320500199905294254";
        int num = 0;
        int numNew = 0;
        String[] split = strs.split(", ");
        for (String s : split) {
            log.info("第" + (numNew = ++num) + "校验 start ");
            validateId(s);
            log.info("第" + numNew + "校验 end ");
        }
    }
}
