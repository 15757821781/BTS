package com.hkay.weifei.Dao;

import java.util.List;

import com.hkay.weifei.pojo.Tb_shehuizuzhidanwei;

public interface OrganizeDao {

	int insertOrgInfo(Tb_shehuizuzhidanwei tb_shehuizuzhidanwei);

	int queryOrgcnt(Tb_shehuizuzhidanwei tb_shehuizuzhidanwei);

	List<Tb_shehuizuzhidanwei> queryOrgList(Tb_shehuizuzhidanwei tb_shehuizuzhidanwei);

	List<Tb_shehuizuzhidanwei> queryOrgDetail(Tb_shehuizuzhidanwei tb_shehuizuzhidanwei);

	int updateOrgInfo(Tb_shehuizuzhidanwei tb_shehuizuzhidanwei);


}
