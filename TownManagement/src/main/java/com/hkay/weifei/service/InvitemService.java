package com.hkay.weifei.service;

import java.util.List;

import com.hkay.weifei.pojo.Tb_zhaoshangxiangmu;

public interface InvitemService {

	int insertinvitem(Tb_zhaoshangxiangmu tb_zhaoshangxiangmu);

	List<Tb_zhaoshangxiangmu> queryinvinfo(Tb_zhaoshangxiangmu tb_zhaoshangxiangmu);

	int queryinvinfocnt(Tb_zhaoshangxiangmu tb_zhaoshangxiangmu);

	List<Tb_zhaoshangxiangmu> queryinvitemdetail(Tb_zhaoshangxiangmu invitem);

	int updateinv(Tb_zhaoshangxiangmu tb_zhaoshangxiangmu);

	int updateinvitemState(String invObj);

}
