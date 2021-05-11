package com.zichen.admin.util;

import com.zichen.admin.bean.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

public class Validate {

    private static List<String> userList = null;
    private static List<String> passWordList = null;

    static {
        userList = Arrays.asList("zhangsan", "lisi", "admin");
        passWordList = Arrays.asList("123456", "a11111", "admin");
    }

    public static boolean validatelogin(HttpSession session, User user, Model model) {
        String userName = user.getUserName();
        String passWord = user.getPassWord();
        if (Utils.isNoEmpty4Str(userName)) {
            if (validate4User(model, userName)) {
                if (Utils.isNoEmpty4Str(passWord)) {
                    if (validate4PassWord(model, passWord)) {
                        session.setAttribute("userName", userName);
                        session.setAttribute("passWord", passWord);
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    model.addAttribute("msg", "密码不可以为空");
                    return false;
                }
            } else {
                return false;
            }
        } else {
            model.addAttribute("msg", "用户名不可以为空");
            return false;
        }
    }

    public static boolean validate4User(Model model, String s) {
        if (userList.contains(s))
            return true;
        model.addAttribute("msg", "用户名不存在");
        return false;
    }

    public static boolean validate4PassWord(Model model, String s) {
        if (passWordList.contains(s))
            return true;
        model.addAttribute("msg", "密码不正确");
        return false;
    }
}
