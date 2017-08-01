package com.hkay.weifei.service;

import java.util.List;

import com.hkay.weifei.pojo.Condition;
import com.hkay.weifei.pojo.Tb_notice;
import com.hkay.weifei.pojo.Tb_user;

public interface ConditionService {

	List<Condition> queryProvince();

	List<Condition> queryCity(Condition condition);

	List<Condition> queryTown(Condition condition);

	List<Condition> queryClimate();

	List<Condition> queryTerrain();

	List<Condition> queryAdvIndustry();

	List<Condition> queryDirIndustry();

	int updatePic(Condition condition);

	List<Condition> queryMajorIndustry();

	List<Condition> queryDevelopDir();

	List<Condition> queryBusinessDir();

	List<Condition> queryAdvantage();

	Condition queryPicValue(Condition condition);

	int insertNotice(List<Tb_notice> notices);

	List<Tb_notice> queryNotice(Tb_user user);

	int updateNoticeState(Tb_notice notice);

}
