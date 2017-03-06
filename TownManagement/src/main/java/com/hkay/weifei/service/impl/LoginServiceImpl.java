package com.hkay.weifei.service.impl;

import java.util.List;

import org.apache.log4j.lf5.viewer.LogFactor5InputDialog;
import org.springframework.stereotype.Service;

import com.hkay.weifei.Dao.LoginDao;
import com.hkay.weifei.pojo.Tb_user;
import com.hkay.weifei.service.LoginService;

@Service("LoginService")
public class LoginServiceImpl implements LoginService{
	private LoginDao logindao;

	@Override
	public List<Tb_user> queryuserinfo(Tb_user user) {
		// TODO Auto-generated method stub
		return this.logindao.queryuserinfo(user);
	}
	
}
