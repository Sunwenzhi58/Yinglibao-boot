package com.bjpowernode.dataservice.service;

import com.bjpowernode.api.model.FinanceAccount;
import com.bjpowernode.api.model.User;
import com.bjpowernode.api.service.UserService;
import com.bjpowernode.common.util.CommonUtil;
import com.bjpowernode.dataservice.mapper.FinanceAccountMapper;
import com.bjpowernode.dataservice.mapper.UserMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@DubboService(interfaceClass = UserService.class,version = "1.0")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private FinanceAccountMapper financeAccountMapper;

    @Value("${ylb.config.password-salt}")
    private String passwordSalt;

    @Override
    public User queryByPhone(String phone) {

        User user  = null;
        if(CommonUtil.checkPhone(phone)){
            user = userMapper.selectByPhone(phone);
        }
        return user;
    }

    //用户注册
    @Transactional(rollbackFor = Exception.class)
    @Override
    public synchronized int userRegister(String phone, String password) {
        int result = 0;//默认
        if (CommonUtil.checkPhone(phone)
                &&(password!=null&&password.length()==32)){
            //判断手机号在库中是否存在
            User queryUser = userMapper.selectByPhone(phone);

            if (queryUser==null){
                //注册密码的md5二次加密。给原始密码加盐
                String newPassword = DigestUtils.md5Hex(password + passwordSalt);
                //1.注册u_user
                User user = new User();
                user.setPhone(phone);
                user.setLoginPassword(newPassword);
                user.setAddTime(new Date());
                userMapper.insertReturnPrimaryKey(user);

                //获取主键 user.getId()
                FinanceAccount account = new FinanceAccount();
                account.setUid(user.getId());
                account.setAvailableMoney(new BigDecimal(0));
                financeAccountMapper.insertSelective(account);

                //成功result=1
                result=1;
            }else {
                //手机号存在
                result = 2;
            }

        }
        return result;
    }
}
