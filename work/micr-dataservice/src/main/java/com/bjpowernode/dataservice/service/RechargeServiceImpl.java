package com.bjpowernode.dataservice.service;

import com.bjpowernode.api.model.RechargeRecord;
import com.bjpowernode.api.service.RechargeService;
import com.bjpowernode.common.util.CommonUtil;
import com.bjpowernode.dataservice.mapper.RechargeRecordMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@DubboService(interfaceClass = RechargeService.class,version = "1.0")
public class RechargeServiceImpl implements RechargeService {

    @Resource
    private RechargeRecordMapper rechargeMapper;

    /*根据userID查询它的充值记录*/
    @Override
    public List<RechargeRecord> queryByUid(Integer uid, Integer pageNo, Integer pageSize) {
        List<RechargeRecord> records  = new ArrayList<>();
        if( uid != null && uid > 0 ){
            pageNo  = CommonUtil.defaultPageNo(pageNo);
            pageSize = CommonUtil.defaultPageSize(pageSize);
            int offset = (pageNo -1 ) * pageSize;
            records = rechargeMapper.selectByUid(uid, offset, pageSize);
        }
        return records;
    }
}
