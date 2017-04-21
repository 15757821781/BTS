package com.hkay.weifei.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hkay.weifei.pojo.Tb_zhaoshangxiangmu;
import com.hkay.weifei.service.InvitemService;
import com.hkay.weifei.util.PageUtil;
import com.hkay.weifei.util.RetAjax;

@Controller
@RequestMapping("/invitemmanage")
public class InvitemController {
	@Resource
	private InvitemService invitemservice;
	private RetAjax result;
	private static  Logger Log =Logger.getLogger(InvitemController.class);
	
	@RequestMapping(value="/insertinvitem")
	@ResponseBody
	public RetAjax insertinvitem(Tb_zhaoshangxiangmu tb_zhaoshangxiangmu){
		try {
			int flag = this.invitemservice.insertinvitem(tb_zhaoshangxiangmu);
			result=RetAjax.onDataBase(flag, 1);
		} catch (Exception e) {
			Log.error("insertinvitem:"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
		 * 方法名称: queryinvinfo
		 * 内容摘要: 表格查询内容
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年2月22日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryinvinfo")
	@ResponseBody
	public Map<String,Object> queryinvinfo(@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			@RequestParam(value = "search", required = false) String search) throws UnsupportedEncodingException {
		Map<String,Object> map = new HashMap<String,Object>();
		Tb_zhaoshangxiangmu tb_zhaoshangxiangmu = new Tb_zhaoshangxiangmu();
		 if (search != null) {
			 search = URLDecoder.decode(search, "utf-8");
			 tb_zhaoshangxiangmu.setSearch(search);
		 } else {
			 tb_zhaoshangxiangmu.setSearch("");
		 }
		Page page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		List<Tb_zhaoshangxiangmu> tb_zhaoshangxiangmus = this.invitemservice.queryinvinfo(tb_zhaoshangxiangmu);
		int count = this.invitemservice.queryinvinfocnt(tb_zhaoshangxiangmu);
		map.put("rows", tb_zhaoshangxiangmus);
		map.put("total", count);
		return map;
	}
	
	/**
	 * 
		 * 方法名称: queryinvitemdetail
		 * 内容摘要: 招商项目详情
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年2月22日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryinvitemdetail")
	@ResponseBody
	public RetAjax queryinvitemdetail(HttpServletRequest request,Tb_zhaoshangxiangmu tb_zhaoshangxiangmu) throws UnsupportedEncodingException{
		Tb_zhaoshangxiangmu invitem=new Tb_zhaoshangxiangmu();
		invitem.setInvid(tb_zhaoshangxiangmu.getInvid());
		List<Tb_zhaoshangxiangmu> tb_zhaoshangxiangmus = this.invitemservice.queryinvitemdetail(invitem);
		result=RetAjax.onQueryDetail(tb_zhaoshangxiangmus);
		return result;
	}
	
	@RequestMapping(value="/updateinv")
	@ResponseBody
	public RetAjax updateinv(Tb_zhaoshangxiangmu tb_zhaoshangxiangmu){
		try {
			int flag = this.invitemservice.updateinv(tb_zhaoshangxiangmu);
			result=RetAjax.onDataBase(flag, 3);
		} catch (Exception e) {
			Log.error("updateinv:"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
