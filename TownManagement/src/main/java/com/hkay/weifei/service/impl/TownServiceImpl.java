package com.hkay.weifei.service.impl;

import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkay.weifei.Dao.TownDao;
import com.hkay.weifei.pojo.Tb_zhongxinzhen;
import com.hkay.weifei.service.TownService;

@Service("TownService")
public class TownServiceImpl implements TownService{
	@Resource
	private TownDao towndao;
	@Override
	public int inserttowninfo(Tb_zhongxinzhen tb_zhongxinzhen) {
		String towncode = tb_zhongxinzhen.getSys_town();
		int seq = this.towndao.querytowninfocnt(tb_zhongxinzhen);
		towncode += new DecimalFormat("00").format(seq + 1);
		// 如果不是中心镇
		if (tb_zhongxinzhen.getTownlevel().equals("0")) {
			towncode += "F";
		} else {
			towncode += "Z";
		}
		//如果是国家特色小镇
//		if(!tb_zhongxinzhen.getTownfeature().equals("0")){
//			towncode+="G";
//		}
		tb_zhongxinzhen.setNumber(towncode);
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
