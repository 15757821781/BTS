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
import com.hkay.weifei.service.OrganizeService;
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
	public Map<String,Object> queryOrgList(@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			@RequestParam(value = "search", required = false) String search) throws UnsupportedEncodingException {
		Map<String,Object> map = new HashMap<String,Object>();
		Tb_shehuizuzhidanwei tb_shehuizuzhidanwei = new Tb_shehuizuzhidanwei();
		 if (search != null) {
			 search = URLDecoder.decode(search, "utf-8");
			 tb_shehuizuzhidanwei.setSearch(search);
		 } else {
			 tb_shehuizuzhidanwei.setSearch("");
		 }
		Page<?> page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		List<Tb_shehuizuzhidanwei> tb_shehuizuzhidanweis = this.organizeService.queryOrgList(tb_shehuizuzhidanwei);
		int count = this.organizeService.queryOrgcnt(tb_shehuizuzhidanwei);
		map.put("rows", tb_shehuizuzhidanweis);
		map.put("total", count);
		return map;
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
			int flag = this.organizeService.updateOrgInfo(tb_shehuizuzhidanwei);
			result = RetAjax.onDataBase(flag, 3);
		} catch (Exception e) {
			Log.error("error-------------updateOrgInfo:"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
	

