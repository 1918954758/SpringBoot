package com.zichen.admin.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * class: GobalExceptionHandler
 * description:
 * author: ~~~
 * date: 2021/5/19 - 16:25
 */
@ControllerAdvice
@Slf4j
public class GobalExceptionHandler {

    @ExceptionHandler({ArithmeticException.class, NullPointerException.class})
    public String handleArithException() {
        log.info("handleArithException execing...");
        return "login";//路径地址
    }
}
