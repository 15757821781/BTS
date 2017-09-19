package com.hkay.weifei.Dao;

import java.util.List;

import com.hkay.weifei.pojo.Tb_qiyedanwei;

public interface CompanyDao {

	int insertcominfo(Tb_qiyedanwei qiyedanwei);

	int querycomcnt(Tb_qiyedanwei qiyedanwei);

	List<Tb_qiyedanwei> queryComList(Tb_qiyedanwei tb_qiyedanwei);

	List<Tb_qiyedanwei> queryComDetail(Tb_qiyedanwei tb_qiyedanwei);

	int updateComInfo(Tb_qiyedanwei tb_qiyedanwei);

	int updateComState(String comObj);

}
