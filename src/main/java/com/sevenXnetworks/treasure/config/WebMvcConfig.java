package com.sevenXnetworks.treasure.config;

import com.sevenXnetworks.treasure.web.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private HandlerInterceptor userInterceptor;

    @Bean
    public HandlerInterceptor userInterceptor() {
        return new UserInterceptor();
    }


    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/oauth/token/**")

        ;
        super.addInterceptors(registry);
    }
}