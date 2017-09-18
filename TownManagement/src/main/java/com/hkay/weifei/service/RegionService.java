package com.hkay.weifei.service;

import java.util.List;

import com.hkay.weifei.pojo.Tb_quyuxingxiangmu;

public interface RegionService {

	int insertregion(Tb_quyuxingxiangmu tb_quyuxingxiangmu);

	List<Tb_quyuxingxiangmu> queryregioninfo(Tb_quyuxingxiangmu tb_quyuxingxiangmu);

	int queryregioninfocnt(Tb_quyuxingxiangmu tb_quyuxingxiangmu);

	List<Tb_quyuxingxiangmu> queryregionitemdetail(Tb_quyuxingxiangmu tb_quyuxingxiangmu);

	int updateregion(Tb_quyuxingxiangmu tb_quyuxingxiangmu);

	List<Tb_quyuxingxiangmu> queryRegForNotice();

	int updateregioniteState(String regObj);

}
