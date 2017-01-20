package com.hkay.weifei.Dao;

import java.util.List;

import com.hkay.weifei.pojo.Pages;
import com.hkay.weifei.pojo.Tb_wflx_new;
import com.hkay.weifei.pojo.User;

public interface wflxDao {

	List<Tb_wflx_new> queryWflx(Tb_wflx_new tb_wflx_new);

	int queryCountOfWflx(Tb_wflx_new tb_wflx_new);

	int updateWflx(Tb_wflx_new tb_wflx_new);

	int insertWflx(Tb_wflx_new tb_wflx_new);

	List<Pages> loadPages(Pages pages);
}
