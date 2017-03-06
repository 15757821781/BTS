package com.hkay.weifei.Dao;

import java.util.List;

import com.hkay.weifei.pojo.Tb_user;

public interface LoginDao {

	List<Tb_user> queryuserinfo(Tb_user user);

}
