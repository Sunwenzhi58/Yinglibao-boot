package com.bjpowernode.front.settings;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    //处理跨域
    @Override
    public void addCorsMappings(CorsRegistry registry){
        System.out.println("===========addCorsMappings===========");
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:8080")//可跨域的域名，可以为*
                //支持跨域请求的，http方式
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                //支持跨域的请求头，在请求头中包含哪些数据时，可以支持跨域请求
                .allowedHeaders("*");
    }
}
