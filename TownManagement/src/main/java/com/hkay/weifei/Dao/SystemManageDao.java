package com.hkay.weifei.Dao;

import java.util.List;
import java.util.Map;

import com.hkay.weifei.pojo.Pages;
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

	List<Pages> queryMenusPage(Pages pages);

	List<Pages> queryMenusChild(Pages pages);

	int insertRoleMenuMapping(List<Map<String, String>> pages);

	int insertRole(Tb_role tb_role);

	List<Tb_user> queryRoleList(Tb_role role);

	int queryRoleCnt(Tb_role role);

	List<Tb_role> queryRoleDetail(Tb_role role);

	int deleteRolePageMapping(Tb_role tb_role);

	int updateRoleInfo(Tb_role tb_role);

	int deleteUserInfo(Tb_user tb_user);

	int deleteRoleInfo(Tb_role tb_role);

}
