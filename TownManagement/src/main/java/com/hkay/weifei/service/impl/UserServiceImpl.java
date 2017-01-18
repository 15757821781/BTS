package com.hkay.weifei.service.impl;  
  
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkay.weifei.Dao.UserDao;
import com.hkay.weifei.pojo.User;
import com.hkay.weifei.service.UserService;  
  
@Service("userService")  
public class UserServiceImpl implements UserService {  
    @Resource  
    private UserDao userDao; 
    public User getUserById(User user) {  
        // TODO Auto-generated method stub  
        return this.userDao.selectByPrimaryKey(user);  
    }  
  
}