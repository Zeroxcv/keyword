package com.lisk.keyword.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyConfig extends WebMvcConfigurerAdapter {
    //配置一个静态文件的路径 否则css和js无法使用，虽然默认的静态资源是放在static下，但是没有配置里面的文件夹
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Bean
    public WebMvcConfigurerAdapter WebMvcConfigurerAdapter() {

        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                //addResourceHandler是指你想在url请求的路径
                //addResourceLocations是图片存放的真实路径
                //registry.addResourceHandler("/pic/**").addResourceLocations("file:/www/server/communityphoto/");
                registry.addResourceHandler("/pic/**").addResourceLocations("file:D://uploadimgtest/");
                super.addResourceHandlers(registry);
            }
        };
        return adapter;
    }
}