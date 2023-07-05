package com.bjpowernode.front.controller;


import com.bjpowernode.front.service.SmsService;
import com.bjpowernode.front.view.RespResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "短信业务")
@RestController
@RequestMapping("/v1/sms")
public class SmsController extends BaseController{

    @Resource
    private SmsService smsService;

    //注册验证码短信
    @GetMapping("/code/register")
    public RespResult sendCodeRegister(@RequestParam String phone){
        RespResult result= RespResult.fail();
        boolean isSuccess = smsService.sendSms(phone);
        return result;

    }
}
