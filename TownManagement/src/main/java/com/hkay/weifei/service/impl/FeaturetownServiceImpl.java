package com.hkay.weifei.service.impl;

import java.util.List;

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

	@Override
	public List<Tb_tesexiaozhen> queryfeaturetown(Tb_tesexiaozhen tb_tesexiaozhen) {
		// TODO Auto-generated method stub
		return this.featuretowndao.queryfeaturetown(tb_tesexiaozhen);
	}

	@Override
	public int queryfeaturetowncnt(Tb_tesexiaozhen tb_tesexiaozhen) {
		// TODO Auto-generated method stub
		return this.featuretowndao.queryfeaturetowncnt(tb_tesexiaozhen);
	}

	@Override
	public List<Tb_tesexiaozhen> queryfeaturetowndetail(Tb_tesexiaozhen tb_tesexiaozhen) {
		// TODO Auto-generated method stub
		return this.featuretowndao.queryfeaturetowndetail(tb_tesexiaozhen);
	}

	@Override
	public int updatefeaturetown(Tb_tesexiaozhen tb_tesexiaozhen) {
		// TODO Auto-generated method stub
		return this.featuretowndao.updatefeaturetown(tb_tesexiaozhen);
	}

}
