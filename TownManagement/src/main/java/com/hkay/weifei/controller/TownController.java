package com.hkay.weifei.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hkay.weifei.pojo.Tb_user;
import com.hkay.weifei.pojo.Tb_zhongxinzhen;
import com.hkay.weifei.service.TownService;
import com.hkay.weifei.util.CommonUtil;
import com.hkay.weifei.util.FileUpload;
import com.hkay.weifei.util.PageUtil;
import com.hkay.weifei.util.RetAjax;
import com.hkay.weifei.util.TypeStatusConstant;

@Controller
@RequestMapping("/townmanage")
public class TownController {
	@Resource
	private TownService townservice;
	private RetAjax result;
	private FileUpload fileupload = new FileUpload();
	private static  Logger Log =Logger.getLogger(TownController.class);
	/**
	 * 新增中心镇信息
	 * 
	 * @param request
	 * @param tb_zhongxinzhen
	 * @param session 
	 * @return
	 */
	@RequestMapping(value="/inserttowninfo")
	@ResponseBody
	public RetAjax inserttowninfo(HttpServletRequest request, Tb_zhongxinzhen tb_zhongxinzhen,
			@RequestParam("statusfile1") MultipartFile[] files1, @RequestParam("statusfile2") MultipartFile[] files2,
			@RequestParam("statusfile3") MultipartFile[] files3,@RequestParam("planfile1") MultipartFile[] files4,
			@RequestParam("planfile2") MultipartFile[] files5) {
		try {
			HttpSession session = request.getSession();
			Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
			String number = user.getNumber();
			tb_zhongxinzhen.setCreator(number);
			String imgpath1 = fileupload.fileUpload(files1, request, TypeStatusConstant.town_statusmap, "");
			String imgpath2 = fileupload.fileUpload(files2, request, TypeStatusConstant.town_statusmap, "");
			String imgpath3 = fileupload.fileUpload(files3, request, TypeStatusConstant.town_statusmap, "");
			String imgpath4 = fileupload.fileUpload(files4, request, TypeStatusConstant.town_planmap, "");
			String imgpath5 = fileupload.fileUpload(files5, request, TypeStatusConstant.town_planmap, "");
			tb_zhongxinzhen.setCitypic(imgpath1);
			tb_zhongxinzhen.setTownpic(imgpath2);
			tb_zhongxinzhen.setScopeopic(imgpath3);
			tb_zhongxinzhen.setTotalplanpic(imgpath4);
			tb_zhongxinzhen.setDetailplanpic(imgpath5);
			int flag = this.townservice.inserttowninfo(tb_zhongxinzhen);
			result = RetAjax.onDataBase(flag, 1);
		} catch (Exception e) {
			Log.error("error----------inserttowninfo:" + e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 1);
		}
		return result;
	}

	@RequestMapping("/querytowninfo")
	@ResponseBody
	public RetAjax querytowninfo(HttpServletRequest request,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			Tb_zhongxinzhen tb_zhongxinzhen) throws UnsupportedEncodingException {
		HttpSession session = request.getSession();
		Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
		String number = user.getNumber();
		String userdata = user.getUserdata();
		Page<?> page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		//处理高级搜索
		tb_zhongxinzhen.setSupersearch(GetSuperSearchSql(tb_zhongxinzhen));
		List<Tb_zhongxinzhen> tb_zhongxinzhens = this.townservice.querytowninfo(tb_zhongxinzhen);
		if(tb_zhongxinzhens!=null){
			for(int i=0;i<tb_zhongxinzhens.size();i++){
				if(userdata.equals("3")||number.equals(tb_zhongxinzhens.get(i).getCreator())){
					tb_zhongxinzhens.get(i).setOperation("<a href='javascript:void(0)' onclick='querytowndetail("+tb_zhongxinzhens.get(i).getCentertownid()+")'>查看</a>"+
							"&nbsp"+"<a href='javascript:void(0)' onclick='updatetowninfo("+tb_zhongxinzhens.get(i).getCentertownid()+")'>修改</a>");
				}else{
					tb_zhongxinzhens.get(i).setOperation("<a href='javascript:void(0)' onclick='querytowndetail("+tb_zhongxinzhens.get(i).getCentertownid()+")'>查看</a>");
				}
			}
		}
		int count = this.townservice.querytowninfocnt(tb_zhongxinzhen);
		result = RetAjax.onGrid(tb_zhongxinzhens, count);
		return result;
	}

	@RequestMapping("/querytowndetail")
	@ResponseBody
	public RetAjax querytowndetail(HttpServletRequest request,Tb_zhongxinzhen tb_zhongxinzhen) throws UnsupportedEncodingException{
		HttpSession session = request.getSession();
		Tb_user user=(Tb_user)session.getAttribute("town_LoginData");
		List<Tb_zhongxinzhen> tb_zhongxinzhens = this.townservice.querytowndetail(tb_zhongxinzhen);
		if(user.getUserdata().equals("1")&&tb_zhongxinzhens!=null){
			tb_zhongxinzhens.get(0).setPartycommittee("******");
			tb_zhongxinzhens.get(0).setCommittelnumber("******");
			tb_zhongxinzhens.get(0).setCommittel("******");
			tb_zhongxinzhens.get(0).setMayortelnumber("******");
			tb_zhongxinzhens.get(0).setMayortel("******");
			tb_zhongxinzhens.get(0).setMayor("******");
			tb_zhongxinzhens.get(0).setContactstel("******");
			tb_zhongxinzhens.get(0).setContacts("******");
			tb_zhongxinzhens.get(0).setPost("******");
		}
		result=RetAjax.onQueryDetail(tb_zhongxinzhens);
		return result;
	}
	
	/**
	 * update中心镇信息
	 * 
	 * @param request
	 * @param tb_zhongxinzhen
	 * @return
	 */
	@RequestMapping(value="/updatetowninfo")
	@ResponseBody
	public RetAjax updatetowninfo(HttpServletRequest request,Tb_zhongxinzhen tb_zhongxinzhen,
			@RequestParam("statusfile1") MultipartFile[] files1, @RequestParam("statusfile2") MultipartFile[] files2,
			@RequestParam("statusfile3") MultipartFile[] files3,@RequestParam("planfile1") MultipartFile[] files4,
			@RequestParam("planfile2") MultipartFile[] files5){
		try {
			HttpSession session = request.getSession();
			Tb_user user=(Tb_user)session.getAttribute("town_LoginData");
			tb_zhongxinzhen.setUpdator(user.getNumber());
			String imgpath1=fileupload.fileUpload(files1, request,TypeStatusConstant.town_statusmap,tb_zhongxinzhen.getCitypic());
			String imgpath2=fileupload.fileUpload(files2, request,TypeStatusConstant.town_statusmap,tb_zhongxinzhen.getTownpic());
			String imgpath3=fileupload.fileUpload(files3, request,TypeStatusConstant.town_statusmap,tb_zhongxinzhen.getScopeopic());
			String imgpath4=fileupload.fileUpload(files4, request,TypeStatusConstant.town_planmap,tb_zhongxinzhen.getTotalplanpic());
			String imgpath5=fileupload.fileUpload(files5, request,TypeStatusConstant.town_planmap,tb_zhongxinzhen.getDetailplanpic());
			tb_zhongxinzhen.setCitypic(imgpath1);
			tb_zhongxinzhen.setTownpic(imgpath2);
			tb_zhongxinzhen.setScopeopic(imgpath3);
			tb_zhongxinzhen.setTotalplanpic(imgpath4);
			tb_zhongxinzhen.setDetailplanpic(imgpath5);
			int flag = this.townservice.updatetowninfo(tb_zhongxinzhen);
			result = RetAjax.onDataBase(flag, 3);
		} catch (Exception e) {
			Log.error("error----------updatetowninfo:" + e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 3);
		}
		return result;
	}
	
	/**
	 * 
	 *方法名称:
	 *内容：处理高级搜索内容
	 *创建人:zhuwenjie
	 *创建日期:2017年8月9日下午3:54:34
	 */
	public String GetSuperSearchSql(Tb_zhongxinzhen zxz) {
		StringBuilder sql = new StringBuilder();
		if(CommonUtil.JudgeEmpty(zxz.getSearch())){
			String search = zxz.getSearch();
			sql.append(" and (a.number like '%"+search+"%' or a.centertownname like '%"+search+"%' or a.townlevel like '%"+search+"%')");
		}
		if(CommonUtil.JudgeEmpty(zxz.getCentertownname())){
			sql.append(" and a.centertownname like '%"+zxz.getCentertownname()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getNumber())){
			sql.append(" and a.number like '%"+zxz.getNumber()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getCitypic())){
			sql.append(" and a.citypilot = '"+zxz.getCitypic()+"'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getTownlevel())){
			sql.append(" and a.townlevel = '"+zxz.getTownlevel()+"'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getSys_province())){
			sql.append(" and a.province = '"+zxz.getSys_province()+"'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getSys_city())){
			sql.append(" and a.city = '"+zxz.getSys_city()+"'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getSys_town())){
			sql.append(" and a.town = '"+zxz.getSys_town()+"'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getCooperation())){
			sql.append(" and a.cooperation = '"+zxz.getCooperation()+"'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getTowndatayear())){
			sql.append(" and a.towndatayear = '"+zxz.getTowndatayear()+"'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getHundredcounties())){
			sql.append(" and a.hundredcounties = '"+zxz.getHundredcounties()+"'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getCountygdps())){
			sql.append(CommonUtil.HandleNum("countygdp", zxz.getCountygdps()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getCountyrevenues())){
			sql.append(CommonUtil.HandleNum("countyrevenue", zxz.getCountyrevenues()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getTownpopulations())){
			sql.append(CommonUtil.HandleNum("townpopulation", zxz.getTownpopulations()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getTownpgdis())){
			sql.append(CommonUtil.HandleNum("townpgdi", zxz.getTownpgdis()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getTownareas())){
			sql.append(CommonUtil.HandleNum("townarea", zxz.getTownareas()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getCommunitys())){
			sql.append(CommonUtil.HandleNum("community", zxz.getCommunitys()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getAdminvillages())){
			sql.append(CommonUtil.HandleNum("adminvillage", zxz.getAdminvillages()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getTownlocalgdps())){
			sql.append(CommonUtil.HandleNum("townlocalgdp", zxz.getTownlocalgdps()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getTownrevenues())){
			sql.append(CommonUtil.HandleNum("townrevenue", zxz.getTownrevenues()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getTotalpopulations())){
			sql.append(CommonUtil.HandleNum("totalpopulation", zxz.getTotalpopulations()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getFarmingoutvalues())){
			sql.append(CommonUtil.HandleNum("farmingoutvalue", zxz.getFarmingoutvalues()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getIndustryoutvalues())){
			sql.append(CommonUtil.HandleNum("industryoutvalue", zxz.getIndustryoutvalues()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getServiceoutvalues())){
			sql.append(CommonUtil.HandleNum("serviceoutvalue", zxz.getServiceoutvalues()));
		}
		if(CommonUtil.JudgeEmpty(zxz.getWeather())){
			sql.append(" and a.weather in ('"+zxz.getWeather()+"')");
		}
		if(CommonUtil.JudgeEmpty(zxz.getTerrain())){
			sql.append(" and a.terrain in ('"+zxz.getTerrain()+"')");
		}
		if(CommonUtil.JudgeEmpty(zxz.getTraffic())){
			sql.append(" and a.traffic like '%"+zxz.getTraffic()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getCurrentindustry())){
			sql.append(" and a.currentindustry in ('"+zxz.getCurrentindustry()+"')");
		}
		if(CommonUtil.JudgeEmpty(zxz.getSpecialindustry())){
			sql.append(" and a.specialindustry like '%"+zxz.getSpecialindustry()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getSpecialindustryway())){
			sql.append(" and a.specialindustryway like '%"+zxz.getSpecialindustryway()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getIndustrialorientation())){
			sql.append(" and a.industrialorientation in ('"+zxz.getIndustrialorientation()+"')");
		}
		if(CommonUtil.JudgeEmpty(zxz.getHistoryculture())){
			sql.append(" and a.historyculture like '%"+zxz.getHistoryculture()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getHonorarytitle())){
			sql.append(" and a.honorarytitle like '%"+zxz.getHonorarytitle()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getPartycommittee())){
			sql.append(" and a.partycommittee like '%"+zxz.getPartycommittee()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getCommittelnumber())){
			sql.append(" and a.committelnumber like '%"+zxz.getCommittelnumber()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getCommittel())){
			sql.append(" and a.committel like '%"+zxz.getCommittel()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getMayor())){
			sql.append(" and a.mayor like '%"+zxz.getMayor()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getMayortelnumber())){
			sql.append(" and a.mayortelnumber like '%"+zxz.getMayortelnumber()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getMayortel())){
			sql.append(" and a.mayortel like '%"+zxz.getMayortel()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getContacts())){
			sql.append(" and a.contacts like '%"+zxz.getContacts()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getPost())){
			sql.append(" and a.post like '%"+zxz.getPost()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getContactstel())){
			sql.append(" and a.contactstel like '%"+zxz.getContactstel()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zxz.getCreator())){
			sql.append(" and a.creator like '%"+zxz.getCreator()+"%'");
		}
		return sql.toString();
	}
}
