package com.hkay.weifei.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import com.hkay.weifei.pojo.Tb_qiyedanwei;
import com.hkay.weifei.pojo.Tb_shehuizuzhidanwei;
import com.hkay.weifei.pojo.Tb_user;
import com.hkay.weifei.pojo.Tb_zhongxinzhen;
import com.hkay.weifei.service.OrganizeService;
import com.hkay.weifei.util.CommonUtil;
import com.hkay.weifei.util.PageUtil;
import com.hkay.weifei.util.RetAjax;
import com.hkay.weifei.util.TypeStatusConstant;

@Controller
@RequestMapping("/orgmanage")
public class OrganizeController {
	private static Logger Log = Logger.getLogger(OrganizeController.class);
	@Resource
	private OrganizeService organizeService;
	private RetAjax result;
	
	@RequestMapping("/insertOrgInfo")
	@ResponseBody
	public RetAjax insertOrgInfo(HttpServletRequest request, Tb_shehuizuzhidanwei tb_shehuizuzhidanwei){
		try {
			HttpSession session = request.getSession();
			Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
			String number = user.getNumber();
			tb_shehuizuzhidanwei.setOrgentry(number);
			int flag = this.organizeService.insertOrgInfo(tb_shehuizuzhidanwei);
			result = RetAjax.onDataBase(flag,1);
		} catch (Exception e) {
			Log.error("error----------insertorginfo:" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
		 * 方法名称: queryOrgList
		 * 内容摘要: 查询社会组织单位列表
		 * 创建人：caixuyang
		 * 创建日期： 2017年5月11日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryOrgList")
	@ResponseBody
	public RetAjax queryOrgList(HttpServletRequest request,@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			Tb_shehuizuzhidanwei tb_shehuizuzhidanwei) throws UnsupportedEncodingException {
		HttpSession session = request.getSession();
		Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
		String number = user.getNumber();
		String userdata = user.getUserdata();
		Page<?> page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		tb_shehuizuzhidanwei.setSupersearch(GetSuperSearchSql(tb_shehuizuzhidanwei));
		List<Tb_shehuizuzhidanwei> tb_shehuizuzhidanweis = this.organizeService.queryOrgList(tb_shehuizuzhidanwei);
		if(tb_shehuizuzhidanweis!=null){
			for(int i=0;i<tb_shehuizuzhidanweis.size();i++){
				if(userdata.equals("3")||number.equals(tb_shehuizuzhidanweis.get(i).getOrgentry())){
					tb_shehuizuzhidanweis.get(i).setOperation("<a href='javascript:void(0)' onclick='querydetail("+tb_shehuizuzhidanweis.get(i).getOrgid()+")'>查看</a>"+
							"&nbsp"+"<a href='javascript:void(0)' onclick='updateinfo("+tb_shehuizuzhidanweis.get(i).getOrgid()+")'>修改</a>");
				}else{
					tb_shehuizuzhidanweis.get(i).setOperation("<a href='javascript:void(0)' onclick='querydetail("+tb_shehuizuzhidanweis.get(i).getOrgid()+")'>查看</a>");
				}
			}
		}
		int count = this.organizeService.queryOrgcnt(tb_shehuizuzhidanwei);
		result = RetAjax.onGrid(tb_shehuizuzhidanweis, count);
		return result;
	}
	/**
	 * 
		 * 方法名称: queryOrgDetail
		 * 内容摘要: 查询社会组织单位详细
		 * 创建人：caixuyang
		 * 创建日期： 2017年5月11日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryOrgDetail")
	@ResponseBody
	public RetAjax queryOrgDetail(HttpServletRequest request,Tb_shehuizuzhidanwei tb_shehuizuzhidanwei)throws UnsupportedEncodingException {
		HttpSession session = request.getSession();
		Tb_user user=(Tb_user)session.getAttribute("town_LoginData");
		List<Tb_shehuizuzhidanwei> tb_shehuizuzhidanweis = this.organizeService.queryOrgDetail(tb_shehuizuzhidanwei);
		if(user.getUserdata().equals("1")&&tb_shehuizuzhidanweis!=null){
			tb_shehuizuzhidanweis.get(0).setOrgcontact("******");
			tb_shehuizuzhidanweis.get(0).setOrgpost("******");
			tb_shehuizuzhidanweis.get(0).setOrgcontacttel("******");
		}
		result = RetAjax.onQueryDetail(tb_shehuizuzhidanweis);
		return result;
	}
	
	/**
	 * 
		 * 方法名称: updateOrgInfo
		 * 内容摘要: 更新社会组织信息
		 * 创建人：caixuyang
		 * 创建日期： 2017年5月11日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/updateOrgInfo")
	@ResponseBody
	public RetAjax updateOrgInfo(HttpServletRequest request,Tb_shehuizuzhidanwei tb_shehuizuzhidanwei ){
		try {
			HttpSession session = request.getSession();
			Tb_user user=(Tb_user)session.getAttribute("town_LoginData");
			tb_shehuizuzhidanwei.setOrgupdator(user.getNumber());
			int flag = this.organizeService.updateOrgInfo(tb_shehuizuzhidanwei);
			result = RetAjax.onDataBase(flag, 3);
		} catch (Exception e) {
			Log.error("error-------------updateOrgInfo:"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 *方法名称:
	 *内容：处理高级搜索内容
	 *创建人:caixuyang
	 *创建日期:2017年8月15日下午1:14:34
	 */
	public String GetSuperSearchSql(Tb_shehuizuzhidanwei shzz) {
		StringBuilder sql = new StringBuilder();
		if(CommonUtil.JudgeEmpty(shzz.getSearch())){
			String search = shzz.getSearch();
			sql.append(" and (a.orgnumber like '%"+search+"%' or a.orgname like '%"+search+"%' or a.orgcategory like '%"+search+"%')");
		}
		if(CommonUtil.JudgeEmpty(shzz.getOrgname())){
			sql.append(" and a.orgname like '%"+shzz.getOrgname()+"%'");
		}
		if(CommonUtil.JudgeEmpty(shzz.getOrgnumber())){
			sql.append(" and a.orgnumber like '%"+shzz.getOrgnumber()+"%'");
		}
		if(CommonUtil.JudgeEmpty(shzz.getOrgnature())){
			sql.append(" and a.orgnature = '"+shzz.getOrgnature()+"'");
		}
		if(CommonUtil.JudgeEmpty(shzz.getOrgcategory())){
			sql.append(" and a.orgcategory = '"+shzz.getOrgcategory()+"'");
		}
		if(CommonUtil.JudgeEmpty(shzz.getOrgtype())){
			sql.append(" and a.orgtype = '"+shzz.getOrgtype()+"'");
		}
		if(CommonUtil.JudgeEmpty(shzz.getOrgsponsor())){
			sql.append(" and a.orgsponsor like '%"+shzz.getOrgsponsor()+"%'");
		}
		if(CommonUtil.JudgeEmpty(shzz.getOrgrelation())){
			sql.append(" and a.orgrelation = '"+shzz.getOrgrelation()+"'");
		}
		if(CommonUtil.JudgeEmpty(shzz.getOrgcompetent())){
			sql.append(" and a.orgcompetent like '%"+shzz.getOrgcompetent()+"%'");
		}
		if(CommonUtil.JudgeEmpty(shzz.getOrgoffice())){
			sql.append(" and a.orgoffice like '%"+shzz.getOrgoffice()+"%'");
		}
		if(CommonUtil.JudgeEmpty(shzz.getOrgestablish())){
			sql.append(" and DATE_FORMAT(a.orgestablish,'%Y-%m-%d') = '"+shzz.getOrgestablish()+"'");
		}
		if(CommonUtil.JudgeEmpty(shzz.getOrgrepresent())){
			sql.append(" and a.orgrepresent like '%"+shzz.getOrgrepresent()+"%'");
		}
		if(CommonUtil.JudgeEmpty(shzz.getOrgprovince())){
			sql.append(" and a.orgprovince = '"+shzz.getOrgprovince()+"'");
		}
		if(CommonUtil.JudgeEmpty(shzz.getOrgcity())){
			sql.append(" and a.orgcity = '"+shzz.getOrgcity()+"'");
		}
		if(CommonUtil.JudgeEmpty(shzz.getOrgtown())){
			sql.append(" and a.orgtown = '"+shzz.getOrgtown()+"'");
		}
		if(CommonUtil.JudgeEmpty(shzz.getOrgaddress())){
			sql.append(" and a.orgaddress like '%"+shzz.getOrgaddress()+"%'");
		}
		if(CommonUtil.JudgeEmpty(shzz.getOrgcreditcode())){
			sql.append(" and a.orgcreditcode like '%"+shzz.getOrgcreditcode()+"%'");
		}
		if(CommonUtil.JudgeEmpty(shzz.getOrgscope())){
			sql.append(" and a.orgscope like '%"+shzz.getOrgscope()+"%'");
		}
		if(CommonUtil.JudgeEmpty(shzz.getOrgadvantaget())){
			sql.append(" and a.orgadvantaget in ('"+shzz.getOrgadvantaget()+"')");
		}
		if(CommonUtil.JudgeEmpty(shzz.getOrgofficeweb())){
			sql.append(" and a.orgofficeweb = '"+shzz.getOrgofficeweb()+"'");
		}
		if(CommonUtil.JudgeEmpty(shzz.getOrgcontact())){
			sql.append(" and a.orgcontact = '"+shzz.getOrgcontact()+"'");
		}
		if(CommonUtil.JudgeEmpty(shzz.getOrgcontacttel())){
			sql.append(" and a.orgcontacttel = '"+shzz.getOrgcontacttel()+"'");
		}
		if(CommonUtil.JudgeEmpty(shzz.getOrgpost())){
			sql.append(" and a.orgpost = '"+shzz.getOrgpost()+"'");
		}
		if(CommonUtil.JudgeEmpty(shzz.getOrgentry())){
			sql.append(" and a.orgentry = '"+shzz.getOrgentry()+"'");
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
	@RequestMapping(value="/updateOrgState")
	@ResponseBody
	public RetAjax updateOrgState(HttpServletRequest request,Tb_shehuizuzhidanwei tb_shehuizuzhidanwei,@RequestParam("orgObj[]") String orgObj) { 
		try {
			HttpSession session = request.getSession();
			Tb_user user=(Tb_user)session.getAttribute("town_LoginData");
			// 若是管理员
			if(user.getUserdata().equals("3")){
				int flag = this.organizeService.updateOrgState(orgObj);
				if(flag!=0){
					flag=1;
				}
				result = RetAjax.onDataBase(flag,3);
			}else{
				result = RetAjax.onFail("权限不足！");
			}
		} catch (Exception e) {
			Log.error("error----------updateOrgState:" + e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 3);
		}
		return result; 
	} 

}
