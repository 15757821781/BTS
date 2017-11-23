package com.hkay.weifei.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkay.weifei.Dao.SystemNoticeDao;
import com.hkay.weifei.pojo.Tb_xitonggonggao;
import com.hkay.weifei.service.SystemNoticeService;

@Service("SystemNoticeService")
public class SystemNoticeServiceImpl implements SystemNoticeService{
	@Resource
	private SystemNoticeDao systemnoticeDao;

	@Override
	public int insertnocinfo(Tb_xitonggonggao xitonggonggao) {
		// TODO Auto-generated method stub
		return this.systemnoticeDao.insertnocinfo(xitonggonggao);
	}

	@Override
	public List<Tb_xitonggonggao> queryNocList(Tb_xitonggonggao tb_xitonggonggao) {
		// TODO Auto-generated method stub
		return this.systemnoticeDao.queryNocList(tb_xitonggonggao);
	}

	@Override
	public int querynoccnt(Tb_xitonggonggao tb_xitonggonggao) {
		// TODO Auto-generated method stub
		return this.systemnoticeDao.querynoccnt(tb_xitonggonggao);
	}

	@Override
	public List<Tb_xitonggonggao> queryNocDetail(Tb_xitonggonggao tb_xitonggonggao) {
		// TODO Auto-generated method stub
		return this.systemnoticeDao.queryNocDetail(tb_xitonggonggao);
	}

	@Override
	public int updateNocState(String nocObj) {
		// TODO Auto-generated method stub
		return this.systemnoticeDao.updateNocState(nocObj);
	}

	@Override
	public int updateNocInfo(Tb_xitonggonggao tb_xitonggonggao) {
		// TODO Auto-generated method stub
		return this.systemnoticeDao.updateNocInfo(tb_xitonggonggao);
	}

	@Override
	public List<Tb_xitonggonggao> queryhomenotice() {
		// TODO Auto-generated method stub
		return this.systemnoticeDao.queryhomenotice();
	}
}
