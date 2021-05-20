package com.zichen.admin.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * class: CustomerHandlerExceptionResolver
 * description: 自定义异常解析器处理方式一
 * author: ~~~
 * date: 2021/5/20 - 10:32
 */
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Slf4j
//@Component //先注释，不然以后所有异常都不能正常工作了
public class CustomerHandlerExceptionResolver_01 implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        try {
            response.sendError(711, "我喜欢的异常");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
