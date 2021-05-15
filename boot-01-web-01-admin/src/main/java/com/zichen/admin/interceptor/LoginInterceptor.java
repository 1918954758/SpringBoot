package com.zichen.admin.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @name: LoginInterceptor
 * @description:
 * @author: zichen
 * @date: 2021/5/15  15:14
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 拦截执行目标方法前置处理
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        log.info("拦截器的请求路径是{}", requestURI);

        log.info("preHandle执行{}", request);

        // 登录检查逻辑
        HttpSession session = request.getSession();

        Object loginUser = session.getAttribute("loginUser");

        if (loginUser != null) {
            // session中用户不为空，则放行
            return true;
        }

        // 拦截
        // 使用session，必须是登录之后才能拿到信息
        //session.setAttribute("msg", "请先登录！");
        //response.sendRedirect("/");
        // 使用request域，不需要登录之后就可以拿到消息
        request.setAttribute("msg", "请先登录！");
        request.getRequestDispatcher("/").forward(request, response);
        return false;
    }

    /**
     * 拦截目标方法执行之后
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle执行{}", modelAndView);
    }

    /**
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion执行{}", ex);
    }
}
