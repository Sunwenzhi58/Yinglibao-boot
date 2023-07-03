package com.bjpowernode.ajaxdemo.controller;

import com.bjpowernode.ajaxdemo.model.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//当前的controller支持跨域
@CrossOrigin
@RestController
public class UserController {

    //get请求
    @GetMapping("/user/query")
    public User queryUser(){
        System.out.println("user/query 接收前端的请求");
        User user = new User(1001, "张强", 20, "男");
        return user;
    }

    //两个参数
    @GetMapping("/user/get")
    public User queryUser2(Integer id,String name){
        System.out.println("user/get 接收前端的请求,id="+id+",name="+name);
        User user = new User(id, "name", 20, "男");
        return user;
    }

    //Post请求
    @PostMapping("/user/add")
    public User addUser(Integer id,String name){
        System.out.println("user/add 接收前端的请求,id="+id+",name="+name);
        User user = new User(id, "name", 20, "男");
        return user;
    }


}
