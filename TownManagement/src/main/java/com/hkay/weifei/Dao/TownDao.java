package com.hkay.weifei.Dao;

import java.util.List;

import com.hkay.weifei.pojo.Tb_zhongxinzhen;

public interface TownDao {

	int inserttowninfo(Tb_zhongxinzhen tb_zhongxinzhen);

	List<Tb_zhongxinzhen> querytowninfo(Tb_zhongxinzhen tb_zhongxinzhen);

	int querytowninfocnt(Tb_zhongxinzhen tb_zhongxinzhen);

	List<Tb_zhongxinzhen> querytowndetail(Tb_zhongxinzhen tb_zhongxinzhen);

}
