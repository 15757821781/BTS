package com.hkay.weifei.Dao;

import java.util.List;

import com.hkay.weifei.pojo.Tb_role;
import com.hkay.weifei.pojo.Tb_user;

public interface SystemManageDao {

	List<Tb_user> queryUserList(Tb_user user);

	int queryUsercnt(Tb_user user);

	List<Tb_role> queryRoleForCombo(Tb_user tb_user);

	Tb_user queryAccountExist(Tb_user tb_user);

	int insertUserInfo(Tb_user tb_user);

	List<Tb_user> queryUserDetail(Tb_user tb_user);

	int updateUserInfo(Tb_user tb_user);

}
