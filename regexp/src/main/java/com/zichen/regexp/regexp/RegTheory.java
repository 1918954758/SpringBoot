package com.zichen.regexp.regexp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @name: RegTheory
 * @description: 正则表达式原理
 * @author: zichen
 * @date: 2021/6/4  23:22
 */
public class RegTheory {
    private static final Logger log = LoggerFactory.getLogger(RegExpTest.class);
    public static void main(String[] args) {
        String content = "1998年12月8日，第二代Java平台的企业版J2EE发布。1999年6月，Sun公司发布了第二代Java平" +
                "台（简称为Java2）的3个版本：J2ME（Java2 Micro Edition，Java2平台的微型版），应用于移动、无" +
                "线及有限资源的环境；J2SE（Java 2 Standard Edition，Java 2平台的标准版），应用于桌面环境" +
                "；J2EE（Java 2Enterprise Edition，Java 2平台的企业版），应用于基于Java的应用服务器。Java 2平" +
                "台的发布，是Java发展过程中最重要的一个里程碑，标志着Java的应用开始普及。\n" +
                "1999年4月27日，HotSpot虚拟机发布。HotSpot虚拟机发布时是作为JDK 1.2的附加程序提供的，后来它成" +
                "为了JDK 1.3及之后所有版本的Sun JDK的默认虚拟机 [11]  。\n" +
                "Java创始人之一：詹姆斯·高斯林\n" +
                "Java创始人之一：詹姆斯·高斯林\n" +
                "2000年5月，JDK1.3、JDK1.4和J2SE1.3相继发布，几周后其获得了Apple公司Mac OS X的工" +
                "业标准的支持。2001年9月24日，J2EE1.3发布。2002年2月26日，J2SE1.4发布。自此Java的计算能力有了大幅提" +
                "升，与J2SE1.3相比，其多了近62%的类和接口。在这些新特性当中，还提供了广泛的XML支持、安全套接字（Socket）支持（通过SSL与TLS协议" +
                "）、全新的I/OAPI、正则表达式、日志与断言。2004年9月30日，J2SE1.5发布，成为Java语言发展史上的又一里程碑。为了表示该版本的重要性，J2SE 1.5更名为Java S" +
                "E 5.0（内部版本号1.5.0），代号为“Tiger”，Tiger包含了从1996年发布1.0版本以来的最重大的更新，其中包括泛型支持、基本类型的自动装箱、改进的循环、枚举类型、格式化I/O及可变参数。";

        // 1. \\d 表示一个数字
        String regStr = "\\d\\d\\d\\d";
        // 2. 创建 模式对象【即正则表达式对象】
        Pattern pattern = Pattern.compile(regStr);
        // 3. 创建匹配器
        // 创建匹配器 macher   按照正则表达式的规则，去匹配 content 对象
        Matcher matcher = pattern.matcher(content);
        /**
         * matcher.find() 完成的任务
         * 1. 根据指定的规则，定位满足规则的子字符串(比如 1998)
         * 2. 找到后，将 子字符串的开始的索引记录到 matcher 对象的属性 int[] groups;
         *      groups[0] = 0， 把该子字符串的结束的索引+1的值记录到 groups[1] = 4
         * 3. 同时记录oldLast 的值为 子字符串的结束的索引+1的值 即4， 即下次执行find时，就从4开始匹配
         *
         * matcher.groups()分析：
         * 原码：
         *  public String group(int group) {
         *     if (first < 0)
         *     throw new IllegalStateException("No match found");
         *     if (group < 0 || group > groupCount())
         *         throw new IndexOutOfBoundsException("No group " + group);
         *     if ((groups[group*2] == -1) || (groups[group*2+1] == -1))
         *         return null;
         *     return getSubSequence(groups[group * 2], groups[group * 2 + 1]).toString();
         *  }
         * 1. 根据 groups[0] = 0 和 groups[1] = 4 的记录的位置，从content截取子字符串，也就是 [0, 4) - 左闭右开区间
         *
         * 如果再次执行 matcher.find()方法，任然按照上面的分析来执行
         */
        // 4. 开始匹配
        while (matcher.find()) {
            log.info("找到：" + matcher.group(0));
        }


        System.out.println("============================================================");
        // ==========================考虑分组========================

        // 1. \\d 表示一个数字
        String regStrGroup = "(\\d\\d)(\\d\\d)";
        // 2. 创建 模式对象【即正则表达式对象】
        Pattern patternGroup = Pattern.compile(regStrGroup);
        // 3. 创建匹配器
        // 创建匹配器 macherGroup   按照正则表达式的规则，去匹配 content 对象
        Matcher matcherGroup = patternGroup.matcher(content);
        /**
         * matcher.find() 完成的任务(考虑分组)
         * 什么是分组：比如 (\\d\\d)(\\d\\d) ， 正则表达式中有() 表示分组，第1个()表示第一组，第2个()表示第2组，...
         * 1. 根据指定的规则，定位满足规则的子字符串(比如 1998)
         * 2. 找到后，将 子字符串的开始的索引记录到 matcher 对象的属性 int[] groups;
         *      2.1. groups[0] = 0， 把该子字符串的结束的索引+1的值记录到 groups[1] = 3+1
         *      2.2. 记录第1组()匹配到的字符串 groups[2] = 0  groups[3] = 1+1
         *      2.3. 记录第2组()匹配到的字符串 groups[4] = 2  groups[5] = 3+1
         *      2.4. 如果有更多的分组，以此类推...
         *      2.5. ...
         * 3. 同时记录oldLast 的值为 子字符串的结束的索引+1的值 即4， 即下次执行find时，就从4开始匹配
         *
         * matcher.groups()分析：
         * 原码：
         *  public String group(int group) {
         *     if (first < 0)
         *     throw new IllegalStateException("No match found");
         *     if (group < 0 || group > groupCount())
         *         throw new IndexOutOfBoundsException("No group " + group);
         *     if ((groups[group*2] == -1) || (groups[group*2+1] == -1))
         *         return null;
         *     return getSubSequence(groups[group * 2], groups[group * 2 + 1]).toString();
         *  }
         * 1. 根据 groups[0] = 0 和 groups[1] = 4 的记录的位置，从content截取子字符串，也就是 [0, 4) - 左闭右开区间
         *
         * 如果再次执行 matcher.find()方法，任然按照上面的分析来执行
         */
        // 4. 开始匹配
        while (matcherGroup.find()) {
            log.info("找到：" + matcherGroup.group(0));
        }
    }

}
