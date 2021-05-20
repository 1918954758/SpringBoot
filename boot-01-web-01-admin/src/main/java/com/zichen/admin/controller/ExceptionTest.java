package com.zichen.admin.controller;

import com.zichen.admin.bean.User;
import com.zichen.admin.exception.UserNameTooMany;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.UnknownServiceException;

/**
 * class: ExceptionTest
 * description:
 * author: ~~~
 * date: 2021/5/20 - 09:06
 */
@Slf4j
@Controller
public class ExceptionTest {

    @ResponseBody
    @RequestMapping(value = "/exception", method = RequestMethod.GET)
    public String exceptionTest(@RequestParam("str") String str) {
        if (str.length() > 5) {
            throw new UserNameTooMany("用户名超过5位！");
        }
        return "<a>Exception</a>";
    }
}
