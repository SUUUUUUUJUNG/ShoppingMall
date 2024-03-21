package com.shoppingmall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new LoginCheckInterceptor()).addPathPatterns("/**")//적용할 패턴 설정
               .excludePathPatterns("/login","/member/register","/","/images/**");// 적용 제외할 패턴 설정
    }
}
