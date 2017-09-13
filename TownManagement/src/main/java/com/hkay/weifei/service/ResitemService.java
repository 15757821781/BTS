package com.hkay.weifei.service;

import java.util.List;

import com.hkay.weifei.pojo.Tb_chubeixiangmu;

public interface ResitemService {

	int insertresitem(Tb_chubeixiangmu tb_chubeixiangmu);

	List<Tb_chubeixiangmu> queryresinfo(Tb_chubeixiangmu tb_chubeixiangmu);

	int queryresinfocnt(Tb_chubeixiangmu tb_chubeixiangmu);

	List<Tb_chubeixiangmu> queryresitemdetail(Tb_chubeixiangmu resitem);

	int updateres(Tb_chubeixiangmu tb_chubeixiangmu);

	List<Tb_chubeixiangmu> queryResForNotice();

	int updateresitemState(String resObj);

}
