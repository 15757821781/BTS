package com.hkay.weifei.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkay.weifei.Dao.AllianceDao;
import com.hkay.weifei.pojo.Tb_lianmengdanwei;
import com.hkay.weifei.service.AllianceService;

@Service("AllianceService")
public class AllianceServiceImpl implements AllianceService{
	@Resource
	private AllianceDao alliancedao;

	@Override
	public int insertallinfo(Tb_lianmengdanwei tb_lianmengdanwei) {
		// TODO Auto-generated method stub
		return this.alliancedao.insertallinfo(tb_lianmengdanwei);
	}
}
