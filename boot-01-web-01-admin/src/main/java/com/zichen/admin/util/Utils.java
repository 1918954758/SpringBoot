package com.zichen.admin.util;

public class Utils {

    public static boolean isEmpty4Str(String s) {
        if ("".equals(s) || null == s)
            return true;
        return false;
    }

    public static boolean isNoEmpty4Str(String s) {
        if (isEmpty4Str(s))
            return false;
        return true;
    }
}
