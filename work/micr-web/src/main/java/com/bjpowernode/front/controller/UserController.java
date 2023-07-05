package com.bjpowernode.front.controller;

import com.bjpowernode.api.model.User;
import com.bjpowernode.common.enums.RCode;
import com.bjpowernode.common.util.CommonUtil;
import com.bjpowernode.front.view.RespResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户功能")
@RestController
@RequestMapping("v1/user")
public class UserController extends BaseController{

    //手机号是否存在
    @ApiOperation(value = "手机号是否注册过",notes = "在注册功能中,判断手机号是否可以注册")
    @ApiImplicitParam(name = "phone",value = "手机号")
    @GetMapping("/phone/exists")
    public RespResult phoneExists(@RequestParam("phone") String phone){
        RespResult result = new RespResult();
        result.setRCode(RCode.PHONE_EXISTS);
        //1.检查请求参数是否符合要求
        if (CommonUtil.checkPhone(phone)) {
            //可以执行逻辑，查询数据库，调用数据服务
            User user = userService.queryByPhone(phone);
            if(user == null){
                //可以注册
                result=RespResult.ok();
            }
            //把查询到的手机号放入到redis，然后检查手机号是否存在，可以查询redis
        }else {
            result.setRCode(RCode.PHONE_FORMAT_ERR);
        }

        return result;
    }
}
