package com.hkay.weifei.service.impl;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkay.weifei.Dao.SystemManageDao;
import com.hkay.weifei.pojo.Pages;
import com.hkay.weifei.pojo.Tb_role;
import com.hkay.weifei.pojo.Tb_user;
import com.hkay.weifei.service.SystemManageService;
import com.hkay.weifei.util.MD5;

@Service("SystemManageService")
public class SystemManageServiceImpl<UserRoleDao> implements SystemManageService{
	
	@Resource
	private SystemManageDao systemManageDao;
	
	@Override
	public List<Tb_user> queryUserList(Tb_user user) {
		// TODO Auto-generated method stub
		return this.systemManageDao.queryUserList(user);
	}

	@Override
	public int queryUsercnt(Tb_user user) {
		// TODO Auto-generated method stub
		return this.systemManageDao.queryUsercnt(user);
	}

	@Override
	public List<Tb_role> queryRoleForCombo(Tb_user tb_user) {
		// TODO Auto-generated method stub
		return this.systemManageDao.queryRoleForCombo(tb_user);
	}

	@Override
	public Tb_user queryAccountExist(Tb_user tb_user) {
		// TODO Auto-generated method stub
		return this.systemManageDao.queryAccountExist(tb_user);
	}

	@Override
	public int insertUserInfo(Tb_user tb_user) {
		if(tb_user!=null){
			tb_user.setPassword(new MD5().encryption(tb_user.getPassword()));
//			int cnt = this.systemManageDao.queryUsercnt(new Tb_user());
//			String number = new DecimalFormat("0000").format(cnt+1);
//			tb_user.setNumber(number);
			int flag = this.systemManageDao.insertUserInfo(tb_user);
			return flag;
		}else{
			return 0;
		}
	}

	@Override
	public List<Tb_user> queryUserDetail(Tb_user tb_user) {
		// TODO Auto-generated method stub
		return this.systemManageDao.queryUserDetail(tb_user);
	}

	@Override
	public int updateUserInfo(Tb_user tb_user) {
		if(!tb_user.getPassword().equals("******")){
			tb_user.setPassword(new MD5().encryption(tb_user.getPassword()));
		}else{
			tb_user.setPassword(null);
		}
		return this.systemManageDao.updateUserInfo(tb_user);
	}

	@Override
	public List<Pages> queryMenusPage(Pages pages) {
		// TODO Auto-generated method stub
		return this.systemManageDao.queryMenusPage(pages);
	}

	@Override
	public List<Pages> queryMenusChild(Pages pages) {
		// TODO Auto-generated method stub
		return this.systemManageDao.queryMenusChild(pages);
	}

	@Override
	public int insertRole(Tb_role tb_role) {
		// TODO Auto-generated method stub
		return this.systemManageDao.insertRole(tb_role);
	}

	@Override
	public int insertRoleMenuMapping(List<Map<String, String>> pages) {
		// TODO Auto-generated method stub
		return this.systemManageDao.insertRoleMenuMapping(pages);
	}

	@Override
	public List<Tb_user> queryRoleList(Tb_role role) {
		// TODO Auto-generated method stub
		return this.systemManageDao.queryRoleList(role);
	}

	@Override
	public int queryRoleCnt(Tb_role role) {
		// TODO Auto-generated method stub
		return this.systemManageDao.queryRoleCnt(role);
	}

	@Override
	public List<Tb_role> queryRoleDetail(Tb_role role) {
		// TODO Auto-generated method stub
		return this.systemManageDao.queryRoleDetail(role);
	}

	@Override
	public int updateRoleInfo(Tb_role tb_role) {
		// TODO Auto-generated method stub
		return this.systemManageDao.updateRoleInfo(tb_role);
	}

	@Override
	public int deleteRolePageMapping(Tb_role tb_role) {
		// TODO Auto-generated method stub
		return this.systemManageDao.deleteRolePageMapping(tb_role);
	}

	@Override
	public int deleteUserInfo(Tb_user tb_user) {
		// TODO Auto-generated method stub
		return this.systemManageDao.deleteUserInfo(tb_user);
	}

	@Override
	public int deleteRoleInfo(Tb_role tb_role) {
		// TODO Auto-generated method stub
		return this.systemManageDao.deleteRoleInfo(tb_role);
	}

}
