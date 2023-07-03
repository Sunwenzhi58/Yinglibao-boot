package com.bjpowernode.dataservice.mapper;

import com.bjpowernode.api.model.BidInfo;

import java.math.BigDecimal;

public interface BidInfoMapper {
    //累计成交金额
    BigDecimal selectSumBigMoney();
    int deleteByPrimaryKey(Integer id);

    int insert(BidInfo record);

    int insertSelective(BidInfo record);

    BidInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BidInfo record);

    int updateByPrimaryKey(BidInfo record);
}