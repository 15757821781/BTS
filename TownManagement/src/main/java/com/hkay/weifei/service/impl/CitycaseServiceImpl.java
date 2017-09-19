package com.hkay.weifei.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkay.weifei.Dao.CitycaseDao;
import com.hkay.weifei.pojo.Tb_xianshiqingkuang;
import com.hkay.weifei.service.CitycaseService;

@Service("CitycaseService")
public class CitycaseServiceImpl implements CitycaseService{
	@Resource
	private CitycaseDao citycaseDao;

	@Override
	public int insertCityInfo(Tb_xianshiqingkuang tb_xianshiqingkuang) {
		// TODO Auto-generated method stub
		return this.citycaseDao.insertCityInfo(tb_xianshiqingkuang);
	}

	@Override
	public List<Tb_xianshiqingkuang> queryCityList(Tb_xianshiqingkuang tb_xianshiqingkuang) {
		// TODO Auto-generated method stub
		return this.citycaseDao.queryCityList(tb_xianshiqingkuang);
	}

	@Override
	public int queryCitycnt(Tb_xianshiqingkuang tb_xianshiqingkuang) {
		// TODO Auto-generated method stub
		return this.citycaseDao.queryCitycnt(tb_xianshiqingkuang);
	}

	@Override
	public List<Tb_xianshiqingkuang> queryCityDetail(Tb_xianshiqingkuang tb_xianshiqingkuang) {
		// TODO Auto-generated method stub
		return this.citycaseDao.queryCityDetail(tb_xianshiqingkuang);
	}

	@Override
	public int updateCityInfo(Tb_xianshiqingkuang tb_xianshiqingkuang) {
		// TODO Auto-generated method stub
		return this.citycaseDao.updateCityInfo(tb_xianshiqingkuang);
	}

	@Override
	public int updateCityState(String cityObj) {
		// TODO Auto-generated method stub
		return this.citycaseDao.updateCityState(cityObj);
	}
}
