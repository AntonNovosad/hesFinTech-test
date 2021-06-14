package com.hesfintech.demo.interceptor;

import com.hesfintech.demo.dao.TokenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInSystemInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenDao tokenDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader("X-Token");
        if (header == null){
            response.sendRedirect("/error");
            return false;
        }

        if (tokenDao.contains(header)){
            return true;
        }else {
            response.sendRedirect("/error");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
