package com.bjpowernode.dataservice;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// Generated by https://start.springboot.io
// 优质的 spring/boot/data/security/cloud 框架中文文档尽在 => https://springdoc.cn
//启动dubbo服务
@EnableDubbo
//Mapper扫描包
@MapperScan(basePackages = "com.bjpowernode.dataservice.mapper")
@SpringBootApplication
public class MicrDataserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicrDataserviceApplication.class, args);
    }

}
