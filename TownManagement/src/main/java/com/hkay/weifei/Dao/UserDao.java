package com.hkay.weifei.Dao;

import com.hkay.weifei.pojo.User;

public interface UserDao {
    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(User user);
}