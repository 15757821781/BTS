package com.hkay.weifei.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkay.weifei.Dao.LoginDao;
import com.hkay.weifei.pojo.Tb_user;
import com.hkay.weifei.service.LoginService;

@Service("LoginService")
public class LoginServiceImpl implements LoginService{
	@Resource
	private LoginDao logindao;

	@Override
	public List<Tb_user> queryuserinfo(Tb_user user) {
		// TODO Auto-generated method stub
		return this.logindao.queryuserinfo(user);
	}
	
}
