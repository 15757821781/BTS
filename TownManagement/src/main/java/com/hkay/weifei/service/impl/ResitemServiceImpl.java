package com.hkay.weifei.service.impl;

import java.util.List;

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

	@Override
	public List<Tb_chubeixiangmu> queryresinfo(Tb_chubeixiangmu tb_chubeixiangmu) {
		// TODO Auto-generated method stub
		return this.resitemdao.queryresinfo(tb_chubeixiangmu);
	}

	@Override
	public int queryresinfocnt(Tb_chubeixiangmu tb_chubeixiangmu) {
		// TODO Auto-generated method stub
		return this.resitemdao.queryresinfocnt(tb_chubeixiangmu);
	}

	@Override
	public List<Tb_chubeixiangmu> queryresitemdetail(Tb_chubeixiangmu resitem) {
		// TODO Auto-generated method stub
		return this.resitemdao.queryresitemdetail(resitem);
	}

	@Override
	public int updateres(Tb_chubeixiangmu tb_chubeixiangmu) {
		// TODO Auto-generated method stub
		return this.resitemdao.updateres(tb_chubeixiangmu);
	}
}
