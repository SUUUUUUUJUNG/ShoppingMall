package com.shoppingmall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploaded-images/**").
                addResourceLocations("file:///C:/Users/SHIN SUJUNG/IdeaProjects/ShoppingMall/uploaded-images/");
    }
}
