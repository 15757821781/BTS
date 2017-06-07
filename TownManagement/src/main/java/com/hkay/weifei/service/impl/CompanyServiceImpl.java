package com.hkay.weifei.service.impl;

import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkay.weifei.Dao.CompanyDao;
import com.hkay.weifei.pojo.Tb_qiyedanwei;
import com.hkay.weifei.service.CompanyService;

@Service("CompanyService")
public class CompanyServiceImpl implements CompanyService{
	@Resource
	private CompanyDao companyDao;

	@Override
	public int insertcominfo(Tb_qiyedanwei qiyedanwei) {
		// 获得企业注册区县
		String number = qiyedanwei.getComtown();
		// 获得企业单位信息总数
		int seq = this.companyDao.querycomcnt(qiyedanwei);
		// 加上企业类别
		number += qiyedanwei.getComcategory();
		// 加上企业类型
		number += qiyedanwei.getComtype();
		// 加上4位序号
		number +=new DecimalFormat("0000").format(seq+1);
		// 加上QY
		number +="QY";
		qiyedanwei.setComnumber(number);
		if(qiyedanwei.getComdevelop()==null){
			qiyedanwei.setComdevelop("");
		}
		if(qiyedanwei.getComindustry()==null){
			qiyedanwei.setComindustry("");
		}
		if(qiyedanwei.getComregpro()==null){
			qiyedanwei.setComregpro("");
		}
		if(qiyedanwei.getComregcity()==null){
			qiyedanwei.setComregcity("");
		}
		if(qiyedanwei.getCominvestment()==null){
			qiyedanwei.setCominvestment("");
		}
		return this.companyDao.insertcominfo(qiyedanwei);
	}

	@Override
	public int querycomcnt(Tb_qiyedanwei qiyedanwei) {
		// TODO Auto-generated method stub
		return this.companyDao.querycomcnt(qiyedanwei);
	}

	@Override
	public List<Tb_qiyedanwei> queryComList(Tb_qiyedanwei tb_qiyedanwei) {
		// TODO Auto-generated method stub
		return this.companyDao.queryComList(tb_qiyedanwei);
	}

	@Override
	public List<Tb_qiyedanwei> queryComDetail(Tb_qiyedanwei tb_qiyedanwei) {
		// TODO Auto-generated method stub
		return this.companyDao.queryComDetail(tb_qiyedanwei);
	}

	@Override
	public int updateComInfo(Tb_qiyedanwei tb_qiyedanwei) {
		// 获得企业注册区县
		String number = tb_qiyedanwei.getComtown();
		// 获得企业单位信息总数
		int seq = Integer.valueOf(tb_qiyedanwei.getComid());
		// 加上企业类别
		number += tb_qiyedanwei.getComcategory();
		// 加上企业类型
		number += tb_qiyedanwei.getComtype();
		// 加上4位序号
		number +=new DecimalFormat("0000").format(seq);
		// 加上QY
		number +="QY";
		tb_qiyedanwei.setComnumber(number);
		if(tb_qiyedanwei.getComdevelop()==null){
			tb_qiyedanwei.setComdevelop("");
		}
		if(tb_qiyedanwei.getComindustry()==null){
			tb_qiyedanwei.setComindustry("");
		}
		if(tb_qiyedanwei.getComregpro()==null){
			tb_qiyedanwei.setComregpro("");
		}
		if(tb_qiyedanwei.getComregcity()==null){
			tb_qiyedanwei.setComregcity("");
		}
		if(tb_qiyedanwei.getCominvestment()==null){
			tb_qiyedanwei.setCominvestment("");
		}
		return this.companyDao.updateComInfo(tb_qiyedanwei);
	}
}
