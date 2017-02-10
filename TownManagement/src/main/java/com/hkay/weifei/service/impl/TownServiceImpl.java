package com.hkay.weifei.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkay.weifei.Dao.TownDao;
import com.hkay.weifei.pojo.Tb_zhongxinzhen;
import com.hkay.weifei.service.TownService;

@Service("TownService")
public class TownServiceImpl implements TownService{
	@Resource
	private TownDao towndao;
	@Override
	public int inserttowninfo(Tb_zhongxinzhen tb_zhongxinzhen) {
		// TODO Auto-generated method stub
		return this.towndao.inserttowninfo(tb_zhongxinzhen);
	}

}
