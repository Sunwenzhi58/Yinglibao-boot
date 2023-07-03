package com.bjpowernode.front.controller;

import com.bjpowernode.api.service.PlatBaseInfoService;
import com.bjpowernode.api.service.ProductService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

public class BaseController {

    //声明公共的方法，属性的等
    @Resource
    protected StringRedisTemplate stringRedisTemplate;

    //平台信息服务
    @DubboReference(interfaceClass = PlatBaseInfoService.class,version = "1.0")
    protected PlatBaseInfoService platBaseInfoService;

    //产品服务
    @DubboReference(interfaceClass = ProductService.class,version = "1.0")
    protected ProductService productService;
}
