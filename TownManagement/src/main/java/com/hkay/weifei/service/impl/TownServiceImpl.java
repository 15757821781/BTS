package com.hkay.weifei.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkay.weifei.Dao.TownDao;
import com.hkay.weifei.pojo.Tb_zhongxinzhen;
import com.hkay.weifei.service.TownService;
import com.hkay.weifei.util.CommonUtil;

@Service("TownService")
public class TownServiceImpl implements TownService{
	@Resource
	private TownDao towndao;
	private CommonUtil common;
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
		if(tb_zhongxinzhen.getFarmingoutvalue().equals("")){
			tb_zhongxinzhen.setFarmingoutvalue("0");
		}
		if(tb_zhongxinzhen.getIndustryoutvalue().equals("")){
			tb_zhongxinzhen.setIndustryoutvalue("0");
		}
		if(tb_zhongxinzhen.getServiceoutvalue().equals("")){
			tb_zhongxinzhen.setServiceoutvalue("0");
		}
		return this.towndao.inserttowninfo(tb_zhongxinzhen);
	}
	@Override
	public List<Tb_zhongxinzhen> querytowninfo(Tb_zhongxinzhen zxz) {
		zxz.setSearch(common.decodeUtf8(zxz.getSearch()));
		
		StringBuilder sql = new StringBuilder();
		if(common.JudgeEmpty(zxz.getCentertownname())){
			sql.append(" and a.centertownname like '%${"+common.decodeUtf8(zxz.getCentertownname())+"}%'");
		}
		if(common.JudgeEmpty(zxz.getNumber())){
			sql.append(" and a.number like '%${"+zxz.getNumber()+"}%'");
		}
		if(common.JudgeEmpty(zxz.getCitypic())){
			sql.append(" and a.citypilot = #{"+zxz.getCitypic()+"}");
		}
		if(common.JudgeEmpty(zxz.getTownlevel())){
			sql.append(" and a.townlevel = #{"+zxz.getTownlevel()+"}");
		}
		if(common.JudgeEmpty(zxz.getSys_province())){
			sql.append(" and a.sys_province = #{"+zxz.getSys_province()+"}");
		}
		if(common.JudgeEmpty(zxz.getSys_city())){
			sql.append(" and a.sys_city = #{"+zxz.getSys_city()+"}");
		}
		if(common.JudgeEmpty(zxz.getSys_town())){
			sql.append(" and a.sys_town = #{"+zxz.getSys_town()+"}");
		}
		if(common.JudgeEmpty(zxz.getCooperation())){
			sql.append(" and a.cooperation = #{"+zxz.getCooperation()+"}");
		}
		if(common.JudgeEmpty(zxz.getTowndatayear())){
			sql.append(" and a.towndatayear = #{"+zxz.getTowndatayear()+"}");
		}
		if(common.JudgeEmpty(zxz.getHundredcounties())){
			sql.append(" and a.hundredcounties = #{"+zxz.getHundredcounties()+"}");
		}
		if(common.JudgeEmpty(zxz.getCountygdp())){
			if(){
				
			}
			sql.append(" and a.countygdp = #{"+zxz.getCountygdp()+"}");
		}
		if(common.JudgeEmpty(zxz.getCountyrevenue())){
			sql.append(" and a.countyrevenue = #{"+zxz.getCountyrevenue()+"}");
		}
		return this.towndao.querytowninfo(zxz);
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
		if(tb_zhongxinzhen.getFarmingoutvalue().equals("")){
			tb_zhongxinzhen.setFarmingoutvalue("0");
		}
		if(tb_zhongxinzhen.getIndustryoutvalue().equals("")){
			tb_zhongxinzhen.setIndustryoutvalue("0");
		}
		if(tb_zhongxinzhen.getServiceoutvalue().equals("")){
			tb_zhongxinzhen.setServiceoutvalue("0");
		}
		String towncode = tb_zhongxinzhen.getSys_town();
		int seq = Integer.valueOf(tb_zhongxinzhen.getCentertownid());
		towncode += new DecimalFormat("00").format(seq);
		// 如果不是中心镇
		if (tb_zhongxinzhen.getTownlevel().equals("0")) {
			towncode += "F";
		} else {
			towncode += "Z";
		}
		tb_zhongxinzhen.setNumber(towncode);
		return this.towndao.updatetowninfo(tb_zhongxinzhen);
	}

}
