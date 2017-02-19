package com.hkay.weifei.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkay.weifei.Dao.RegionDao;
import com.hkay.weifei.pojo.Tb_quyuxingxiangmu;
import com.hkay.weifei.service.RegionService;

@Service("RegionService")
public class RegionServiceImpl implements RegionService{
	@Resource
	private RegionDao regiondao;

	@Override
	public int insertregion(Tb_quyuxingxiangmu tb_quyuxingxiangmu) {
		// TODO Auto-generated method stub
		return this.regiondao.insertregion(tb_quyuxingxiangmu);
	}
	
}
