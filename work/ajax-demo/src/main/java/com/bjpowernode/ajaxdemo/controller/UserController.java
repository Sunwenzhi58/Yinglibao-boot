package com.bjpowernode.ajaxdemo.controller;

import com.bjpowernode.ajaxdemo.model.User;
import org.springframework.web.bind.annotation.*;

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
        System.out.println("====user/add 接收前端的请求,id="+id+",name="+name);
        User user = new User(id, "name", 20, "男");
        return user;
    }

    //前端是json格式的数据。例如{id：001，name：“lisi”}
    //后端控制器方法，使用Java对象接收参数，加入@requestbody

    //使用RequestBody请求
    //1.请求头，Content-Type：application/json
    //2.发起的请求是post，请求数据为json格式
    //3.服务器接收JSON转为对象,需要在对象类型的形参前面加入@RequestBody
    @PostMapping("/user/json")
    public User addUserJson(@RequestBody User user){
        System.out.println("====user/json 接收前端的请求,i="+user);
        User user1 = new User(1001,"lisi",20,"男");
        return user;
    }


}
