package com.zichen.admin.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * class: UserNameTooMany
 * description:
 * author: ~~~
 * date: 2021/5/19 - 17:24
 */
@Slf4j
@ResponseStatus(value =  HttpStatus.FORBIDDEN, reason = "名字太长！")
public class UserNameTooMany extends RuntimeException {

    public UserNameTooMany() {
    }

    public UserNameTooMany(String message) {
        super(message);
    }
}
