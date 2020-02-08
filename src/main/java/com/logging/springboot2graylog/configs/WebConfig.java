package com.logging.springboot2graylog.configs;

import com.logging.springboot2graylog.interceptor.RestControllerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private RestControllerInterceptor restControllerInterceptor;

    public WebConfig(RestControllerInterceptor restControllerInterceptor) {
        this.restControllerInterceptor = restControllerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(restControllerInterceptor).addPathPatterns("/**");
    }
}
