package com.hkay.weifei.service.impl;

import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkay.weifei.Dao.OrganizeDao;
import com.hkay.weifei.pojo.Tb_qiyedanwei;
import com.hkay.weifei.pojo.Tb_shehuizuzhidanwei;
import com.hkay.weifei.service.OrganizeService;

@Service("OrganizeService")
public class OrganizeServiceImpl implements OrganizeService{
	@Resource
	private OrganizeDao organizeDao;
	@Override
	public int insertOrgInfo(Tb_shehuizuzhidanwei tb_shehuizuzhidanwei) {
		// 获得企业注册区县
		String number = tb_shehuizuzhidanwei.getOrgtown();
		// 获得企业单位信息总数
		int seq = this.organizeDao.queryOrgcnt(tb_shehuizuzhidanwei);
		// 加上企业性质
		number += tb_shehuizuzhidanwei.getOrgnature();
		// 加上企业类别
		number += tb_shehuizuzhidanwei.getOrgcategory();
		// 加上企业类型
		number += tb_shehuizuzhidanwei.getOrgtype();
		// 加上3位序号
		number +=new DecimalFormat("000").format(seq+1);
		// 加上SH或者SY
		if(tb_shehuizuzhidanwei.getOrgnature().equals("1")){
			number +="SH";
		}else
		number +="SY";
		tb_shehuizuzhidanwei.setOrgnumber(number);
		return this.organizeDao.insertOrgInfo(tb_shehuizuzhidanwei);
	}


@Override
public int queryOrgcnt(Tb_shehuizuzhidanwei tb_shehuizuzhidanwei) {
	// TODO Auto-generated method stub
	return this.organizeDao.queryOrgcnt(tb_shehuizuzhidanwei);
}


@Override
public List<Tb_shehuizuzhidanwei> queryOrgList(Tb_shehuizuzhidanwei tb_shehuizuzhidanwei) {
	// TODO Auto-generated method stub
	return this.organizeDao.queryOrgList(tb_shehuizuzhidanwei);
}


@Override
public List<Tb_shehuizuzhidanwei> queryOrgDetail(Tb_shehuizuzhidanwei tb_shehuizuzhidanwei) {
	// TODO Auto-generated method stub
	return this.organizeDao.queryOrgDetail(tb_shehuizuzhidanwei);
}


@Override
public int updateOrgInfo(Tb_shehuizuzhidanwei tb_shehuizuzhidanwei) {
	// TODO Auto-generated method stub
	return this.organizeDao.updateOrgInfo(tb_shehuizuzhidanwei);
}
}
