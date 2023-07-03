package com.bjpowernode.api.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class BaseInfo implements Serializable {

    //收益率平均值
    private BigDecimal historyAvgRate;
    //累计成交金额
    private BigDecimal sumBidMoney;
    //注册人数
    private Integer reqisterUsers;

    public BaseInfo(BigDecimal historyAvgRate, BigDecimal sumBidMoney, Integer reqisterUsers) {
        this.historyAvgRate = historyAvgRate;
        this.sumBidMoney = sumBidMoney;
        this.reqisterUsers = reqisterUsers;
    }

    public BigDecimal getHistoryAvgRate() {
        return historyAvgRate;
    }

    public void setHistoryAvgRate(BigDecimal historyAvgRate) {
        this.historyAvgRate = historyAvgRate;
    }

    public BigDecimal getSumBidMoney() {
        return sumBidMoney;
    }

    public void setSumBidMoney(BigDecimal sumBidMoney) {
        this.sumBidMoney = sumBidMoney;
    }

    public Integer getReqisterUsers() {
        return reqisterUsers;
    }

    public void setReqisterUsers(Integer reqisterUsers) {
        this.reqisterUsers = reqisterUsers;
    }
}
