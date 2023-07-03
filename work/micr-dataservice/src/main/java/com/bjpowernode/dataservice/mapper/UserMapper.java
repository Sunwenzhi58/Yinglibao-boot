package com.bjpowernode.dataservice.mapper;

import com.bjpowernode.api.model.User;

public interface UserMapper {
    //统计注册的人数
    int selectCountUser();


    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}