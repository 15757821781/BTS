package com.hkay.weifei.service;

import java.util.List;

import com.hkay.weifei.pojo.Tb_qiyedanwei;

public interface CompanyService {

	int insertcominfo(Tb_qiyedanwei qiyedanwei);

	int querycomcnt(Tb_qiyedanwei tb_qiyedanwei);

	List<Tb_qiyedanwei> queryComList(Tb_qiyedanwei tb_qiyedanwei);

	List<Tb_qiyedanwei> queryComDetail(Tb_qiyedanwei tb_qiyedanwei);

	int updateComInfo(Tb_qiyedanwei tb_qiyedanwei);

	int updateComState(String comObj);

}
