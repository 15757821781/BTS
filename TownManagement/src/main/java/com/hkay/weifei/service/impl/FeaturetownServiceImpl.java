package com.hkay.weifei.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkay.weifei.Dao.FeaturetownDao;
import com.hkay.weifei.pojo.Tb_tesexiaozhen;
import com.hkay.weifei.service.FeaturetownService;

@Service("FeaturetownService")
public class FeaturetownServiceImpl implements FeaturetownService{
	@Resource
	private FeaturetownDao featuretowndao;
	
	@Override
	public int insertfeaturetown(Tb_tesexiaozhen tb_tesexiaozhen) {
		// TODO Auto-generated method stub
		return this.featuretowndao.insertfeaturetown(tb_tesexiaozhen);
	}

}
