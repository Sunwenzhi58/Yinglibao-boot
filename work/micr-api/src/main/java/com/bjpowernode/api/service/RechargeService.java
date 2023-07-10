package com.bjpowernode.api.service;

import com.bjpowernode.api.model.RechargeRecord;

import java.util.List;


public interface RechargeService {

    /*根据userID查询它的充值记录*/
    List<RechargeRecord> queryByUid(Integer uid,Integer pageNo, Integer pageSize);
}
