package com.hkay.weifei.service;

import java.util.List;

import com.hkay.weifei.pojo.Tb_qiyedanwei;
import com.hkay.weifei.pojo.Tb_shehuizuzhidanwei;

public interface OrganizeService {

	int insertOrgInfo(Tb_shehuizuzhidanwei tb_shehuizuzhidanwei);

	int queryOrgcnt(Tb_shehuizuzhidanwei tb_shehuizuzhidanwei);

	List<Tb_shehuizuzhidanwei> queryOrgList(Tb_shehuizuzhidanwei tb_shehuizuzhidanwei);

	List<Tb_shehuizuzhidanwei> queryOrgDetail(Tb_shehuizuzhidanwei tb_shehuizuzhidanwei);

	int updateOrgInfo(Tb_shehuizuzhidanwei tb_shehuizuzhidanwei);

	int updateOrgState(String orgObj);

}
