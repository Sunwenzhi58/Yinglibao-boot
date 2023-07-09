package com.bjpowernode.front.controller;

import com.bjpowernode.api.model.User;
import com.bjpowernode.common.enums.RCode;
import com.bjpowernode.common.util.CommonUtil;
import com.bjpowernode.common.util.JwtUtil;
import com.bjpowernode.front.service.SmsService;
import com.bjpowernode.front.view.RespResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户功能")
@RestController
@RequestMapping("v1/user")
public class UserController extends BaseController{

    @Resource(name = "smsCodeRegisterImpl")
    private SmsService smsService;

    @Resource(name = "smsCodeLoginImpl")
    private SmsService loginSmsService;

//    @Resource
//    private RealnameServiceImpl realnameService;

    @Resource
    private JwtUtil jwtUtil;

    //手机号注册用户
    @ApiOperation(value = "手机号注册用户")
    @PostMapping("/register")
    public RespResult userRegister(@RequestParam String phone,
                                   @RequestParam String pword,
                                   @RequestParam String scode){
        RespResult result = RespResult.fail();
        //1.检查参数
        if (CommonUtil.checkPhone(phone)){
           if (pword != null &&pword.length()==32){
                //检查短信验证码
               if(smsService.checkSmsCode(phone,scode)){
                   //可以注册
                   int registerResult = userService.userRegister(phone,pword);
                   if (registerResult==1){
                       result = RespResult.ok();
                   }else if(registerResult==2){
                       result.setRCode(RCode.PHONE_EXISTS);
                   }else {
                       result.setRCode(RCode.REQUEST_PARAM_ERR);
                   }
               }else {
                   //验证码无效
                   result.setRCode(RCode.SMS_CODE_INVALID);
               }
           }else {
               result.setRCode(RCode.REQUEST_PARAM_ERR);
           }
        }else {
            //手机号格式不正确
            result.setRCode(RCode.PHONE_FORMAT_ERR);
        }
        return result;
    }

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

    /** 登录，获取token-jwt*/
    @ApiOperation(value = "用户登录-获取访问token")
    @PostMapping("/login")
    public RespResult userLogin(@RequestParam String phone,
                                @RequestParam String pword,
                                @RequestParam String scode) throws Exception{
        RespResult result = RespResult.fail();
        if(CommonUtil.checkPhone(phone) && (pword != null && pword.length() == 32) ){
            if(loginSmsService.checkSmsCode(phone,scode)){
                //访问data-service
                User user = userService.userLogin(phone,pword);
                if( user != null){
                    //登录成功，生成token
                    Map<String, Object> data = new HashMap<>();
                    data.put("uid",user.getId());
                    String jwtToken = jwtUtil.createJwt(data,120);

                    result = RespResult.ok();
                    result.setAccessToken(jwtToken);

                    Map<String,Object> userInfo = new HashMap<>();
                    userInfo.put("uid",user.getId());
                    userInfo.put("phone",user.getPhone());
                    userInfo.put("name",user.getName());
                    result.setData(userInfo);
                } else {
                    result.setRCode(RCode.PHONE_LOGIN_PASSWORD_INVALID);
                }
            } else {
                result.setRCode(RCode.SMS_CODE_INVALID);
            }
        } else {
            result.setRCode(RCode.REQUEST_PARAM_ERR);
        }


        return result;
    }
}
