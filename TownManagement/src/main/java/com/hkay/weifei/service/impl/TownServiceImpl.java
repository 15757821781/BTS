package com.hkay.weifei.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkay.weifei.Dao.TownDao;
import com.hkay.weifei.pojo.Tb_wflx_new;
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
	@Override
	public List<Tb_zhongxinzhen> querytowninfo(Tb_zhongxinzhen tb_zhongxinzhen) {
		// TODO Auto-generated method stub
		return this.towndao.querytowninfo(tb_zhongxinzhen);
	}
	@Override
	public int querytowninfocnt(Tb_zhongxinzhen tb_zhongxinzhen) {
		// TODO Auto-generated method stub
		return this.towndao.querytowninfocnt(tb_zhongxinzhen);
	}
	@Override
	public List<Tb_zhongxinzhen> querytowndetail(Tb_zhongxinzhen tb_zhongxinzhen) {
		// TODO Auto-generated method stub
		return this.towndao.querytowndetail(tb_zhongxinzhen);
	}
	@Override
	public int updatetowninfo(Tb_zhongxinzhen tb_zhongxinzhen) {
		// TODO Auto-generated method stub
		return this.towndao.updatetowninfo(tb_zhongxinzhen);
	}

}
