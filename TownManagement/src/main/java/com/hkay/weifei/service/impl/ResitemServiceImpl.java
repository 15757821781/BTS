package com.hkay.weifei.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkay.weifei.Dao.ResitemDao;
import com.hkay.weifei.pojo.Tb_chubeixiangmu;
import com.hkay.weifei.service.ResitemService;

@Service("ResitemService")
public class ResitemServiceImpl implements ResitemService{
	@Resource
	private ResitemDao resitemdao;

	@Override
	public int insertresitem(Tb_chubeixiangmu tb_chubeixiangmu) {
		// TODO Auto-generated method stub
		return this.resitemdao.insertresitem(tb_chubeixiangmu);
	}
}
