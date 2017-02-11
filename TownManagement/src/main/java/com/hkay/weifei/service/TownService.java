package com.hkay.weifei.service;

import java.util.List;

import com.hkay.weifei.pojo.Tb_zhongxinzhen;

public interface TownService {

	public int inserttowninfo(Tb_zhongxinzhen tb_zhongxinzhen);

	public List<Tb_zhongxinzhen> querytowninfo(Tb_zhongxinzhen tb_zhongxinzhen);

	public int querytowninfocnt(Tb_zhongxinzhen tb_zhongxinzhen);

}
