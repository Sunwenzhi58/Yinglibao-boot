package com.bjpowernode.api.service;

import com.bjpowernode.api.model.User;

public interface UserService {

    /**根据手机号查询数据*/
    User queryByPhone(String phone);

    //用户注册
    int userRegister(String phone, String password);

    /*登录*/
    User userLogin(String phone, String pword);
}
