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
import com.hkay.weifei.pojo.Tb_chubeixiangmu;
import com.hkay.weifei.service.ResitemService;
import com.hkay.weifei.util.PageUtil;
import com.hkay.weifei.util.RetAjax;

@Controller
@RequestMapping("/resitemmanage")
public class ResitemController {
	@Resource
	private ResitemService resitemservice;
	private RetAjax result;
	private static  Logger Log =Logger.getLogger(TownController.class);
	
	/**
	 * 
		 * 方法名称: insertresitem
		 * 内容摘要: 新增储备项目
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年2月23日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping(value="/insertresitem")
	@ResponseBody
	public RetAjax insertresitem(Tb_chubeixiangmu tb_chubeixiangmu){
		try {
			int flag = this.resitemservice.insertresitem(tb_chubeixiangmu);
			result=RetAjax.onDataBase(flag, 1);
		} catch (Exception e) {
			Log.error("insertresitem:"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/queryresinfo")
	@ResponseBody
	public Map<String,Object> queryresinfo(@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			@RequestParam(value = "search", required = false) String search) throws UnsupportedEncodingException {
		Map<String,Object> map = new HashMap<String,Object>();
		Tb_chubeixiangmu tb_chubeixiangmu = new Tb_chubeixiangmu();
		 if (search != null) {
			 search = URLDecoder.decode(search, "utf-8");
			 tb_chubeixiangmu.setSearch(search);
		 } else {
			 tb_chubeixiangmu.setSearch("");
		 }
		Page page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		List<Tb_chubeixiangmu> tb_chubeixiangmus = this.resitemservice.queryresinfo(tb_chubeixiangmu);
		int count = this.resitemservice.queryresinfocnt(tb_chubeixiangmu);
		map.put("rows", tb_chubeixiangmus);
		map.put("total", count);
		return map;
	}
	
	@RequestMapping("/queryresitemdetail")
	@ResponseBody
	public RetAjax queryresitemdetail(HttpServletRequest request,Tb_chubeixiangmu tb_chubeixiangmu) throws UnsupportedEncodingException{
		Tb_chubeixiangmu resitem=new Tb_chubeixiangmu();
		resitem.setResid(tb_chubeixiangmu.getResid());
		List<Tb_chubeixiangmu> tb_chubeixiangmus = this.resitemservice.queryresitemdetail(resitem);
		result=RetAjax.onQueryDetail(tb_chubeixiangmus);
		return result;
	}
	
	@RequestMapping(value="/updateres")
	@ResponseBody
	public RetAjax updateres(Tb_chubeixiangmu tb_chubeixiangmu){
		try {
			int flag = this.resitemservice.updateres(tb_chubeixiangmu);
			result=RetAjax.onDataBase(flag, 3);
		} catch (Exception e) {
			Log.error("updateres:"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
