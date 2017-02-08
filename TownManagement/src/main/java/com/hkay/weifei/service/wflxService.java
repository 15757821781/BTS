package com.hkay.weifei.service;

import java.util.List;

import com.hkay.weifei.pojo.Pages;
import com.hkay.weifei.pojo.Tb_wflx_new;
import com.hkay.weifei.pojo.User;

public interface wflxService {
	public List<Tb_wflx_new> queryWflx(Tb_wflx_new tb_wflx_new);

	public int queryCountOfWflx(Tb_wflx_new tb_wflx_new);

	public int updateWflx(Tb_wflx_new tb_wflx_new);

	public int insertWflx(Tb_wflx_new tb_wflx_new);

	public List<Pages> loadPages(Pages page);
}
