package com.zichen.admin.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * class: CustomerHandlerExceptionResolver
 * description: 自定义异常解析器处理方式二
 * author: ~~~
 * date: 2021/5/20 - 10:32
 */
@Slf4j
//@Component //先注释，不然以后所有异常都不能正常工作了
public class CustomerHandlerExceptionResolver_02 implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        /*try {
            response.sendError(711, "我喜欢的异常");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        int status = response.getStatus();
        if (711 == status) {
            return new ModelAndView("error/7111");
        }
        return null;
    }
}