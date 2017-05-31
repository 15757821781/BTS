package com.hkay.weifei.service.impl;

import java.text.DecimalFormat;
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
		String towncode = tb_tesexiaozhen.getFeatown();
		int seq = this.featuretowndao.queryfeaturetowncnt(tb_tesexiaozhen);
		towncode += new DecimalFormat("00").format(seq + 1);
		//如果不是省市特色小镇
		if (tb_tesexiaozhen.getFealevel().equals("0")) {
			towncode +="FT";
		}else{
			towncode +="ST";
		}
		tb_tesexiaozhen.setFeanumber(towncode);
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
		String towncode = tb_tesexiaozhen.getFeatown();
		int seq = Integer.valueOf(tb_tesexiaozhen.getFeaid());
		towncode += new DecimalFormat("00").format(seq);
		//如果不是省市特色小镇
		if (tb_tesexiaozhen.getFealevel().equals("0")) {
			towncode +="FT";
		}else{
			towncode +="ST";
		}
		tb_tesexiaozhen.setFeanumber(towncode);
		return this.featuretowndao.updatefeaturetown(tb_tesexiaozhen);
	}

}
