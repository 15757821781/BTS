package com.hkay.weifei.service.impl;

import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkay.weifei.Dao.StatuteDao;
import com.hkay.weifei.pojo.Tb_zhengcefagui;
import com.hkay.weifei.service.StatuteService;

@Service("StatuteService")
public class StatuteServiceImpl implements StatuteService{
	@Resource
	private StatuteDao statuteDao;

	@Override
	public int insertstainfo(Tb_zhengcefagui zhengcefagui) {
		// TODO Auto-generated method stub
		return this.statuteDao.insertstainfo(zhengcefagui);
	}

	@Override
	public List<Tb_zhengcefagui> queryStaList(Tb_zhengcefagui tb_zhengcefagui) {
		// TODO Auto-generated method stub
		return this.statuteDao.queryStaList(tb_zhengcefagui);
	}

	@Override
	public int querystacnt(Tb_zhengcefagui tb_zhengcefagui) {
		// TODO Auto-generated method stub
		return this.statuteDao.querystacnt(tb_zhengcefagui);
	}

	@Override
	public List<Tb_zhengcefagui> queryStaDetail(Tb_zhengcefagui tb_zhengcefagui) {
		// TODO Auto-generated method stub
		return this.statuteDao.queryStaDetail(tb_zhengcefagui);
	}

	@Override
	public int updateStaInfo(Tb_zhengcefagui tb_zhengcefagui) {
		// TODO Auto-generated method stub
		return this.statuteDao.updateStaInfo(tb_zhengcefagui);
	}

	@Override
	public int updateStaState(String staObj) {
		// TODO Auto-generated method stub
		return this.statuteDao.updateStaState(staObj);
	}

	@Override
	public List<Tb_zhengcefagui> queryhomestatute() {
		// TODO Auto-generated method stub
		return this.statuteDao.queryhomestatute();
	}

}
