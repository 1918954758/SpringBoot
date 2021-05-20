package com.zichen.admin.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * class: GobalExceptionHandler
 * description:
 * author: ~~~
 * date: 2021/5/19 - 16:25
 */
@ControllerAdvice
@Slf4j
public class GobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler({ArithmeticException.class, NullPointerException.class})
    public String handleArithException() {
        log.info("handleArithException execing...");
        return "Exception";//也可以指定路径地址
    }
}
