package com.hkay.weifei.service.impl;

import java.text.DecimalFormat;
import java.util.List;

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
		String number = tb_quyuxingxiangmu.getRegtown();
		int seq = this.regiondao.queryregioninfocnt(tb_quyuxingxiangmu);
		number += new DecimalFormat("00").format(seq + 1);
		number += "QY";
		tb_quyuxingxiangmu.setRegnumber(number);
		return this.regiondao.insertregion(tb_quyuxingxiangmu);
	}

	@Override
	public List<Tb_quyuxingxiangmu> queryregioninfo(Tb_quyuxingxiangmu tb_quyuxingxiangmu) {
		// TODO Auto-generated method stub
		return this.regiondao.queryregioninfo(tb_quyuxingxiangmu);
	}

	@Override
	public int queryregioninfocnt(Tb_quyuxingxiangmu tb_quyuxingxiangmu) {
		// TODO Auto-generated method stub
		return this.regiondao.queryregioninfocnt(tb_quyuxingxiangmu);
	}

	@Override
	public List<Tb_quyuxingxiangmu> queryregionitemdetail(Tb_quyuxingxiangmu tb_quyuxingxiangmu) {
		// TODO Auto-generated method stub
		return this.regiondao.queryregionitemdetail(tb_quyuxingxiangmu);
	}

	@Override
	public int updateregion(Tb_quyuxingxiangmu tb_quyuxingxiangmu) {
		// TODO Auto-generated method stub
		return this.regiondao.updateregion(tb_quyuxingxiangmu);
	}
	
}
