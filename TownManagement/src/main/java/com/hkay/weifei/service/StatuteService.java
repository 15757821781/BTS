package com.hkay.weifei.service;
import java.util.List;

import com.hkay.weifei.pojo.Tb_zhengcefagui;;

public interface StatuteService {

	int insertstainfo(Tb_zhengcefagui zhengcefagui);

	List<Tb_zhengcefagui> queryStaList(Tb_zhengcefagui tb_zhengcefagui);

	int querystacnt(Tb_zhengcefagui tb_zhengcefagui);

	List<Tb_zhengcefagui> queryStaDetail(Tb_zhengcefagui tb_zhengcefagui);

	int updateStaInfo(Tb_zhengcefagui tb_zhengcefagui);

	int updateStaState(String staObj);

	List<Tb_zhengcefagui> queryhomestatute();

	

}
