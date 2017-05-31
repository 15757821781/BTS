package com.hkay.weifei.service.impl;

import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkay.weifei.Dao.ResitemDao;
import com.hkay.weifei.pojo.Tb_chubeixiangmu;
import com.hkay.weifei.service.ResitemService;
import com.hkay.weifei.util.DateUtil;

@Service("ResitemService")
public class ResitemServiceImpl implements ResitemService{
	@Resource
	private ResitemDao resitemdao;

	@Override
	public int insertresitem(Tb_chubeixiangmu tb_chubeixiangmu) {
		// 获得企业注册区县
		String number = "CB";
		number +=DateUtil.nowDate().substring(0, 4);
		// 获得企业单位信息总数
		int seq = this.resitemdao.queryresinfocnt(tb_chubeixiangmu);
		// 加上3位序号
		number +=new DecimalFormat("000").format(seq+1);
		tb_chubeixiangmu.setResnumber(number);
		return this.resitemdao.insertresitem(tb_chubeixiangmu);
	}

	@Override
	public List<Tb_chubeixiangmu> queryresinfo(Tb_chubeixiangmu tb_chubeixiangmu) {
		// TODO Auto-generated method stub
		return this.resitemdao.queryresinfo(tb_chubeixiangmu);
	}

	@Override
	public int queryresinfocnt(Tb_chubeixiangmu tb_chubeixiangmu) {
		// TODO Auto-generated method stub
		return this.resitemdao.queryresinfocnt(tb_chubeixiangmu);
	}

	@Override
	public List<Tb_chubeixiangmu> queryresitemdetail(Tb_chubeixiangmu resitem) {
		// TODO Auto-generated method stub
		return this.resitemdao.queryresitemdetail(resitem);
	}

	@Override
	public int updateres(Tb_chubeixiangmu tb_chubeixiangmu) {
		// 获得企业注册区县
		String number = "CB";
		number +=DateUtil.nowDate().substring(0, 4);
		// 获得企业单位信息总数
		int seq = Integer.valueOf(tb_chubeixiangmu.getResid());
		// 加上3位序号
		number +=new DecimalFormat("000").format(seq);
		tb_chubeixiangmu.setResnumber(number);
		return this.resitemdao.updateres(tb_chubeixiangmu);
	}
}
