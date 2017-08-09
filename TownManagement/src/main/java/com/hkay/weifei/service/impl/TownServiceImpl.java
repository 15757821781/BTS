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
		zxz.setSearch(CommonUtil.decodeUtf8(zxz.getSearch()));
		
		StringBuilder sql = new StringBuilder();
		if(CommonUtil.JudgeEmpty(zxz.getCentertownname())){
			sql.append(" and a.centertownname like '%${"+CommonUtil.decodeUtf8(zxz.getCentertownname())+"}%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getNumber())){
			sql.append(" and a.number like '%${"+zxz.getNumber()+"}%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getCitypic())){
			sql.append(" and a.citypilot = #{"+zxz.getCitypic()+"}");
		}
		if(CommonUtil.JudgeEmpty(zxz.getTownlevel())){
			sql.append(" and a.townlevel = #{"+zxz.getTownlevel()+"}");
		}
		if(CommonUtil.JudgeEmpty(zxz.getSys_province())){
			sql.append(" and a.sys_province = #{"+zxz.getSys_province()+"}");
		}
		if(CommonUtil.JudgeEmpty(zxz.getSys_city())){
			sql.append(" and a.sys_city = #{"+zxz.getSys_city()+"}");
		}
		if(CommonUtil.JudgeEmpty(zxz.getSys_town())){
			sql.append(" and a.sys_town = #{"+zxz.getSys_town()+"}");
		}
		if(CommonUtil.JudgeEmpty(zxz.getCooperation())){
			sql.append(" and a.cooperation = #{"+zxz.getCooperation()+"}");
		}
		if(CommonUtil.JudgeEmpty(zxz.getTowndatayear())){
			sql.append(" and a.towndatayear = #{"+zxz.getTowndatayear()+"}");
		}
		if(CommonUtil.JudgeEmpty(zxz.getHundredcounties())){
			sql.append(" and a.hundredcounties = #{"+zxz.getHundredcounties()+"}");
		}
		if(CommonUtil.JudgeEmpty(zxz.getCountygdp())){
			sql.append(CommonUtil.HandleNum("countygdp", zxz.getCountygdp()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getCountyrevenue())){
			sql.append(CommonUtil.HandleNum("countyrevenue", zxz.getCountyrevenue()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getTownpopulation())){
			sql.append(CommonUtil.HandleNum("townpopulation", zxz.getTownpopulation()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getTownpgdi())){
			sql.append(CommonUtil.HandleNum("townpgdi", zxz.getTownpgdi()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getTownarea())){
			sql.append(CommonUtil.HandleNum("townarea", zxz.getTownarea()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getCommunity())){
			sql.append(CommonUtil.HandleNum("community", zxz.getCommunity()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getAdminvillage())){
			sql.append(CommonUtil.HandleNum("adminvillage", zxz.getAdminvillage()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getTownlocalgdp())){
			sql.append(CommonUtil.HandleNum("townlocalgdp", zxz.getTownlocalgdp()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getTownlocalgdp())){
			sql.append(CommonUtil.HandleNum("townlocalgdp", zxz.getTownlocalgdp()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getTownrevenue())){
			sql.append(CommonUtil.HandleNum("townrevenue", zxz.getTownrevenue()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getTotalpopulation())){
			sql.append(CommonUtil.HandleNum("totalpopulation", zxz.getTotalpopulation()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getFarmingoutvalue())){
			sql.append(CommonUtil.HandleNum("farmingoutvalue", zxz.getFarmingoutvalue()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getIndustryoutvalue())){
			sql.append(CommonUtil.HandleNum("industryoutvalue", zxz.getIndustryoutvalue()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getServiceoutvalue())){
			sql.append(CommonUtil.HandleNum("serviceoutvalue", zxz.getServiceoutvalue()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getWeather())){
			sql.append(" and a.weather in ("+zxz.getWeather()+")");
		}
		if(CommonUtil.JudgeEmpty(zxz.getTerrain())){
			sql.append(" and a.terrain in ("+zxz.getTerrain()+")");
		}
		if(CommonUtil.JudgeEmpty(zxz.getTraffic())){
			sql.append(" and a.traffic like '%${"+CommonUtil.decodeUtf8(zxz.getTraffic())+"}%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getCurrentindustry())){
			sql.append(" and a.currentindustry in ("+zxz.getCurrentindustry()+")");
		}
		if(CommonUtil.JudgeEmpty(zxz.getSpecialindustry())){
			sql.append(" and a.specialindustry like '%${"+CommonUtil.decodeUtf8(zxz.getSpecialindustry())+"}%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getSpecialindustryway())){
			sql.append(" and a.specialindustryway like '%${"+CommonUtil.decodeUtf8(zxz.getSpecialindustryway())+"}%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getIndustrialorientation())){
			sql.append(" and a.industrialorientation in ("+zxz.getIndustrialorientation()+")");
		}
		if(CommonUtil.JudgeEmpty(zxz.getHistoryculture())){
			sql.append(" and a.historyculture like '%${"+CommonUtil.decodeUtf8(zxz.getHistoryculture())+"}%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getHonorarytitle())){
			sql.append(" and a.honorarytitle like '%${"+CommonUtil.decodeUtf8(zxz.getHonorarytitle())+"}%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getPartycommittee())){
			sql.append(" and a.partycommittee like '%${"+CommonUtil.decodeUtf8(zxz.getPartycommittee())+"}%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getCommittelnumber())){
			sql.append(" and a.committelnumber like '%${"+zxz.getCommittelnumber()+"}%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getCommittel())){
			sql.append(" and a.committel like '%${"+zxz.getCommittel()+"}%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getMayor())){
			sql.append(" and a.mayor like '%${"+CommonUtil.decodeUtf8(zxz.getMayor())+"}%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getMayortelnumber())){
			sql.append(" and a.mayortelnumber like '%${"+zxz.getMayortelnumber()+"}%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getMayortel())){
			sql.append(" and a.mayortel like '%${"+zxz.getMayortel()+"}%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getContacts())){
			sql.append(" and a.contacts like '%${"+CommonUtil.decodeUtf8(zxz.getContacts())+"}%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getPost())){
			sql.append(" and a.post like '%${"+CommonUtil.decodeUtf8(zxz.getPost())+"}%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getContactstel())){
			sql.append(" and a.contactstel like '%${"+zxz.getContactstel()+"}%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getCreator())){
			sql.append(" and a.creator like '%${"+CommonUtil.decodeUtf8(zxz.getCreator())+"}%'");
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
