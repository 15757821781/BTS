package com.hkay.weifei.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkay.weifei.Dao.InvitemDao;
import com.hkay.weifei.pojo.Tb_zhaoshangxiangmu;
import com.hkay.weifei.service.InvitemService;

@Service("InvitemService")
public class InvitemServiceImpl implements InvitemService{
	@Resource
	private InvitemDao invitemdao;
	
	@Override
	public int insertinvitem(Tb_zhaoshangxiangmu tb_zhaoshangxiangmu) {
		// TODO Auto-generated method stub
		return this.invitemdao.insertinvitem(tb_zhaoshangxiangmu);
	}

}
