package com.bjpowernode.front.service;


public interface SmsService {


    /**
     * @param phone 手机号
     * @return true：发送成功，false 其他情况
     */
    boolean sendSms(String phone);

    boolean checkSmsCode(String phone,String code);
}
