package com.hkay.weifei.service;

import java.util.List;

import com.hkay.weifei.pojo.Tb_xianshiqingkuang;

public interface CitycaseService {

	int insertCityInfo(Tb_xianshiqingkuang tb_xianshiqingkuang);

	List<Tb_xianshiqingkuang> queryCityList(Tb_xianshiqingkuang tb_xianshiqingkuang);

	int queryCitycnt(Tb_xianshiqingkuang tb_xianshiqingkuang);

	List<Tb_xianshiqingkuang> queryCityDetail(Tb_xianshiqingkuang tb_xianshiqingkuang);

	int updateCityInfo(Tb_xianshiqingkuang tb_xianshiqingkuang);

}
