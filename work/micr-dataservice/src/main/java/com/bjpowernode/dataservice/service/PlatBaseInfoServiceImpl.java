package com.bjpowernode.dataservice.service;

import com.bjpowernode.api.pojo.BaseInfo;
import com.bjpowernode.api.service.PlatBaseInfoService;
import com.bjpowernode.dataservice.mapper.BidInfoMapper;
import com.bjpowernode.dataservice.mapper.ProductInfoMapper;
import com.bjpowernode.dataservice.mapper.UserMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.math.BigDecimal;

@DubboService(interfaceClass=PlatBaseInfoService.class,version = "1.0")
public class PlatBaseInfoServiceImpl implements PlatBaseInfoService {

    //注入mapper
    @Resource
    private UserMapper userMapper;
    @Resource
    private ProductInfoMapper productInfoMapper;
    @Resource
    private BidInfoMapper bidInfoMapper;
    //平台的基本信息
    @Override
    public BaseInfo queryPlatBaseInfo() {
        //获取注册人数，获取收益率平均值，累计成交金额
        int registerUser = userMapper.selectCountUser();
        //收益率平均值
        BigDecimal avgRate = productInfoMapper.selectAvgRate();
        //累计成交金额
        BigDecimal sumBidMoney = bidInfoMapper.selectSumBigMoney();

        BaseInfo baseInfo = new BaseInfo(avgRate,sumBidMoney,registerUser);
        return baseInfo;
    }
}
