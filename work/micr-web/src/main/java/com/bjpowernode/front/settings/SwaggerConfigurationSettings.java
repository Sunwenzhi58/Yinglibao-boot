package com.bjpowernode.front.settings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurationSettings {

    //创建Docker对象
    @Bean
    public Docket docket(){
        //1.创建docket对象
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        //2.创建api信息，接口文档的总体描述
        ApiInfo apiInfo = new ApiInfoBuilder() .title("动力节点金融项目").version("1.0")
                .description("前后端分离，前端Vue，后端Springboot+Dubbo分布式项目")
                .build();

        //3.设置使用Apiinfo
        docket = docket.apiInfo(apiInfo);

        //4.设置参与文档生成的包
        docket = docket.select()
                .apis(RequestHandlerSelectors.basePackage("com.bjpowernode.front.controller"))
                .build();

        return docket;
    }

}
