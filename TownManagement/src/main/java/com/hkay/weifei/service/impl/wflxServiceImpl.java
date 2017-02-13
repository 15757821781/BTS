package com.hkay.weifei.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkay.weifei.Dao.wflxDao;
import com.hkay.weifei.pojo.Pages;
import com.hkay.weifei.pojo.Tb_wflx_new;
import com.hkay.weifei.service.wflxService;

@Service("wflxService")
public class wflxServiceImpl implements wflxService {
	@Resource
	private wflxDao wflxdao;

	@Override
	public List<Tb_wflx_new> queryWflx(Tb_wflx_new tb_wflx_new) {
		// TODO Auto-generated method stub
		return this.wflxdao.queryWflx(tb_wflx_new);
	}

	@Override
	public int queryCountOfWflx(Tb_wflx_new tb_wflx_new) {
		// TODO Auto-generated method stub
		return this.wflxdao.queryCountOfWflx(tb_wflx_new);
	}

	@Override
	public int updateWflx(Tb_wflx_new tb_wflx_new) {
		// TODO Auto-generated method stub
		return this.wflxdao.updateWflx(tb_wflx_new);
	}

	@Override
	public int insertWflx(Tb_wflx_new tb_wflx_new) {
		// TODO Auto-generated method stub
		return this.wflxdao.insertWflx(tb_wflx_new);
	}

	@Override
	public List<Pages> loadPages(Pages pages) {
		// TODO Auto-generated method stub
		return this.wflxdao.loadPages(pages);
	}

}
