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

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hkay.weifei.pojo.Tb_shehuizuzhidanwei;
import com.hkay.weifei.pojo.Tb_user;
import com.hkay.weifei.pojo.Tb_xianshiqingkuang;
import com.hkay.weifei.pojo.Tb_zhongxinzhen;
import com.hkay.weifei.service.CitycaseService;
import com.hkay.weifei.util.CommonUtil;
import com.hkay.weifei.util.PageUtil;
import com.hkay.weifei.util.RetAjax;

@Controller
@RequestMapping("/citymanage")
public class CitycaseController {
	private static Logger Log = Logger.getLogger(CitycaseController.class);
	@Resource
	private CitycaseService citycaseService;
	private RetAjax result;
	
	@RequestMapping("/insertCityInfo")
	@ResponseBody
	public RetAjax insertCityInfo(HttpServletRequest request, Tb_xianshiqingkuang tb_xianshiqingkuang){
		try {
			HttpSession session = request.getSession();
			Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
			String number = user.getNumber();
			tb_xianshiqingkuang.setCountryentry(number);
			int flag = this.citycaseService.insertCityInfo(tb_xianshiqingkuang);
			result = RetAjax.onDataBase(flag,1);
		} catch (Exception e) {
			Log.error("error----------insertCityInfo:" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
		 * 方法名称: queryCityList
		 * 内容摘要: 查询县市基本情况列表
		 * 创建人：caixuyang
		 * 创建日期： 2017年8月24日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryCityList")
	@ResponseBody
	public Map<String,Object> queryCityList(HttpServletRequest request,@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			Tb_xianshiqingkuang tb_xianshiqingkuang) throws UnsupportedEncodingException {
		Map<String,Object> map = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
		String number = user.getNumber();
		String userdata = user.getUserdata();
		Page<?> page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		tb_xianshiqingkuang.setSupersearch(GetSuperSearchSql(tb_xianshiqingkuang));
		List<Tb_xianshiqingkuang> tb_xianshiqingkuangs = this.citycaseService.queryCityList(tb_xianshiqingkuang);
		if(tb_xianshiqingkuangs!=null){
			for(int i=0;i<tb_xianshiqingkuangs.size();i++){
				if(userdata.equals("3")||number.equals(tb_xianshiqingkuangs.get(i).getCountryentry())){
					tb_xianshiqingkuangs.get(i).setOperation("<a href='javascript:void(0)' onclick='querydetail("+tb_xianshiqingkuangs.get(i).getCountryid()+")'>查看</a>"+
							"&nbsp"+"<a href='javascript:void(0)' onclick='updateinfo("+tb_xianshiqingkuangs.get(i).getCountryid()+")'>修改</a>");
				}else{
					tb_xianshiqingkuangs.get(i).setOperation("<a href='javascript:void(0)' onclick='querydetail("+tb_xianshiqingkuangs.get(i).getCountryid()+")'>查看</a>");
				}
			}
		}
		int count = this.citycaseService.queryCitycnt(tb_xianshiqingkuang);
		map.put("rows", tb_xianshiqingkuangs);
		map.put("total", count);
		return map;
	}
	/**
	 * 
		 * 方法名称: queryCityDetail
		 * 内容摘要: 查询县市基本情况详细
		 * 创建人：caixuyang
		 * 创建日期： 2017年5月24日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryCityDetail")
	@ResponseBody
	public RetAjax queryCityDetail(HttpServletRequest request,Tb_xianshiqingkuang tb_xianshiqingkuang)throws UnsupportedEncodingException {
		HttpSession session = request.getSession();
		Tb_user user=(Tb_user)session.getAttribute("town_LoginData");
		List<Tb_xianshiqingkuang> tb_xianshiqingkuangs = this.citycaseService.queryCityDetail(tb_xianshiqingkuang);
//		if(user.getUserdata().equals("1")&&tb_xianshiqingkuangs!=null){
//			tb_xianshiqingkuangs.get(0).setOrgcontact("******");
//			tb_xianshiqingkuangs.get(0).setOrgpost("******");
//			tb_xianshiqingkuangs.get(0).setOrgcontacttel("******");
//		}
		result = RetAjax.onQueryDetail(tb_xianshiqingkuangs);
		return result;
	}
	/**
	 * 
		 * 方法名称: updateCityInfo
		 * 内容摘要: 更新县市基本情况信息
		 * 创建人：caixuyang
		 * 创建日期： 2017年5月24日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/updateCityInfo")
	@ResponseBody
	public RetAjax updateCityInfo(HttpServletRequest request,Tb_xianshiqingkuang tb_xianshiqingkuang ){
		try {
			HttpSession session = request.getSession();
			Tb_user user=(Tb_user)session.getAttribute("town_LoginData");
			tb_xianshiqingkuang.setCountryupdator(user.getNumber());
			int flag = this.citycaseService.updateCityInfo(tb_xianshiqingkuang);
			result = RetAjax.onDataBase(flag, 3);
		} catch (Exception e) {
			Log.error("error-------------updateCityInfo:"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 *方法名称:
	 *内容：处理高级搜索内容
	 *创建人:caixuyang
	 *创建日期:2017年8月24日
	 */
	
	private String GetSuperSearchSql(Tb_xianshiqingkuang xsqk) {
		StringBuilder sql = new StringBuilder();
		if(CommonUtil.JudgeEmpty(xsqk.getSearch())){
			String search = xsqk.getSearch();
			sql.append(" and (a.countryname like '%"+search+"%' or a.countryyear like '%"+search+"%' )");
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountryname())){
			sql.append(" and a.countryname like '%"+xsqk.getCountryname()+"%'");
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountryyear())){
			sql.append(" and a.countryyear like '%"+xsqk.getCountryyear()+"'");
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountryprovince())){
			sql.append(" and a.countryprovince = '"+xsqk.getCountryprovince()+"'");
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountrycity())){
			sql.append(" and a.countrycity = '"+xsqk.getCountrycity()+"'");
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountryposition())){
			sql.append(" and a.countryposition like '%"+xsqk.getCountryposition()+"%'");
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountryareas())){
			sql.append(CommonUtil.HandleNum("countryarea", xsqk.getCountryareas()));
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountryallpeoples())){
			sql.append(CommonUtil.HandleNum("countryallpeople", xsqk.getCountryallpeoples()));
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountryregion())){
			sql.append(" and a.countryregion like '%"+xsqk.getCountryregion()+"%'");
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountryother())){
			sql.append(" and a.countryother like '%"+xsqk.getCountryother()+"%'");
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountrygdps())){
			sql.append(CommonUtil.HandleNum("countrygdp", xsqk.getCountrygdps()));
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountryratea())){
			sql.append(CommonUtil.HandleNum("countryrate", xsqk.getCountryratea()));
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountryfinances())){
			sql.append(CommonUtil.HandleNum("countryfinance", xsqk.getCountryfinances()));
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountryratesb())){
			sql.append(CommonUtil.HandleNum("countryrates", xsqk.getCountryratesb()));
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountryfisheriess())){
			sql.append(CommonUtil.HandleNum("countryfisheries", xsqk.getCountryfisheriess()));
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountryindustrys())){
			sql.append(CommonUtil.HandleNum("countryindustry", xsqk.getCountryindustrys()));
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountryinvests())){
			sql.append(CommonUtil.HandleNum("countryinvest", xsqk.getCountryinvests()));
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountryratessc())){
			sql.append(CommonUtil.HandleNum("countryratess", xsqk.getCountryratessc()));
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountryincomes())){
			sql.append(CommonUtil.HandleNum("countryincome", xsqk.getCountryincomes()));
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountrytownincomes())){
			sql.append(CommonUtil.HandleNum("countrytownincome", xsqk.getCountrytownincomes()));
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountryothers())){
			sql.append(" and a.countryothers like '%"+xsqk.getCountryothers()+"%'");
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountryhistory())){
			sql.append(" and a.countryhistory like '%"+xsqk.getCountryhistory()+"%'");
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountrysight())){
			sql.append(" and a.countrysight like '%"+xsqk.getCountrysight()+"%'");
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountrynative())){
			sql.append(" and a.countrynative like '%"+xsqk.getCountrynative()+"%'");
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountryfumin())){
			sql.append(" and a.countryfumin like '%"+xsqk.getCountryfumin()+"%'");
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountryservice())){
			sql.append(" and a.countryservice like '%"+xsqk.getCountryservice()+"%'");
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountryagriculture())){
			sql.append(" and a.countryagriculture like '%"+xsqk.getCountryagriculture()+"%'");
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountryotherss())){
			sql.append(" and a.countryotherss like '%"+xsqk.getCountryotherss()+"%'");
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountrytarget())){
			sql.append(" and a.countrytarget like '%"+xsqk.getCountrytarget()+"%'");
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountrytask())){
			sql.append(" and a.countrytask like '%"+xsqk.getCountrytask()+"%'");
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountrycapital())){
			sql.append(" and a.countrycapital like '%"+xsqk.getCountrycapital()+"%'");
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountryothersss())){
			sql.append(" and a.countryothersss like '%"+xsqk.getCountryothersss()+"%'");
		}
		if(CommonUtil.JudgeEmpty(xsqk.getCountryentry())){
			sql.append(" and a.countryentry like '%"+xsqk.getCountryentry()+"%'");
		}
		return sql.toString();
	}
	/**
	 * 删除信息时更新信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/updateCityState")
	@ResponseBody
	public RetAjax updateCityState(HttpServletRequest request,Tb_xianshiqingkuang tb_xianshiqingkuang ,@RequestParam("cityObj[]") String cityObj) { 
		try {
			int flag = this.citycaseService.updateCityState(cityObj);
			if(flag!=0){
				flag=1;
			}
			result = RetAjax.onDataBase(flag,3);
		} catch (Exception e) {
			Log.error("error----------updateCityState:" + e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 3);
		}
		return result; 
	} 
}
