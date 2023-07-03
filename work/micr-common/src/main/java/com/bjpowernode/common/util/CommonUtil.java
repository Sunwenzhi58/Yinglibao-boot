package com.bjpowernode.common.util;

public class CommonUtil {

    //处理pageNo
    public static int defaultPageNo(Integer pageNo){
        int pNo = pageNo;
        if(pageNo == null||pageNo<1){
            pNo=1;
        }
        return pNo;
    }

    //处理pageSize
    public static int defaultPageSize(Integer pageSize){
        int pSize = pageSize;
        if (pageSize==null||pageSize<1){
            pageSize = 1;
        }
        return pageSize;
    }

    //手机号脱敏
    public static String tuoMinPhone(String phone){
        String result = "***********";
        if (phone != null && phone.trim().length()==11){
            result = phone.substring(0,3)+"******"+phone.substring(9,11);
        }
        return result;
    }
}