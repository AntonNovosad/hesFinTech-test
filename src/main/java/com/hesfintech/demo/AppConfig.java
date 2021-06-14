package com.hesfintech.demo;

import com.hesfintech.demo.interceptor.UserInSystemInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class AppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private UserInSystemInterceptor userInSystemInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInSystemInterceptor).addPathPatterns("/store/*", "/store/**", "/pet/*", "/pet/**");
    }
}
