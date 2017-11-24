package com.hkay.weifei.service;

import java.util.List;

import com.hkay.weifei.pojo.Tb_xitonggonggao;

public interface SystemNoticeService {

	int insertnocinfo(Tb_xitonggonggao xitonggonggao);

	List<Tb_xitonggonggao> queryNocList(Tb_xitonggonggao tb_xitonggonggao);


	int querynoccnt(Tb_xitonggonggao tb_xitonggonggao);

	List<Tb_xitonggonggao> queryNocDetail(Tb_xitonggonggao tb_xitonggonggao);

	int updateNocState(String nocObj);

	int updateNocInfo(Tb_xitonggonggao tb_xitonggonggao);

	List<Tb_xitonggonggao> queryhomenotice();


}
