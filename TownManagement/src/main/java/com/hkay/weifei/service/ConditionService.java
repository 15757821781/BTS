package com.hkay.weifei.service;

import java.util.List;

import com.hkay.weifei.pojo.Condition;

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

}
