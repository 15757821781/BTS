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
import com.hkay.weifei.pojo.Tb_quyuxingxiangmu;
import com.hkay.weifei.pojo.Tb_tesexiaozhen;
import com.hkay.weifei.service.RegionService;
import com.hkay.weifei.util.PageUtil;
import com.hkay.weifei.util.RetAjax;

@Controller
@RequestMapping("/regionmanage")
public class RegionController {
	@Resource
	private RegionService regionservice;
	private RetAjax result;
	private static  Logger Log =Logger.getLogger(RegionController.class);
	/**
	 * 新增区域性项目
	 * @param tb_quyuxingxiangmu
	 * @return
	 */
	@RequestMapping(value="/insertregion")
	@ResponseBody
	public RetAjax insertregion(Tb_quyuxingxiangmu tb_quyuxingxiangmu){
		try{
			int flag = this.regionservice.insertregion(tb_quyuxingxiangmu);
			result=RetAjax.onDataBase(flag, 1);
		}catch(Exception e){
			Log.error("insertregion:"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * @param limit
	 * @param pageindex
	 * @param search
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/queryregioninfo")
	@ResponseBody
	public Map queryregioninfo(@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			@RequestParam(value = "search", required = false) String search) throws UnsupportedEncodingException {
		Map map = new HashMap();
		Tb_quyuxingxiangmu tb_quyuxingxiangmu = new Tb_quyuxingxiangmu();
		 if (search != null) {
			 search = URLDecoder.decode(search, "utf-8");
			 tb_quyuxingxiangmu.setSearch(search);
		 } else {
			 tb_quyuxingxiangmu.setSearch("");
		 }
		Page page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		List<Tb_quyuxingxiangmu> tb_quyuxingxiangmus = this.regionservice.queryregioninfo(tb_quyuxingxiangmu);
		int count = this.regionservice.queryregioninfocnt(tb_quyuxingxiangmu);
		map.put("rows", tb_quyuxingxiangmus);
		map.put("total", count);
		return map;
	}
	
	/**
	 * 查看详情
	 * @param request
	 * @param tb_quyuxingxiangmu
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/queryregionitemdetail")
	@ResponseBody
	public RetAjax queryregionitemdetail(HttpServletRequest request,Tb_quyuxingxiangmu tb_quyuxingxiangmu) throws UnsupportedEncodingException{
		Tb_quyuxingxiangmu regionitme=new Tb_quyuxingxiangmu();
		regionitme.setRegid(tb_quyuxingxiangmu.getRegid());
		List<Tb_quyuxingxiangmu> tb_quyuxingxiangmus = this.regionservice.queryregionitemdetail(regionitme);
		result=RetAjax.onQueryDetail(tb_quyuxingxiangmus);
		return result;
	}
	
	/**
	 * 更新区域性项目
	 * @param tb_quyuxingxiangmu
	 * @return
	 */
	@RequestMapping(value="/updateregion")
	@ResponseBody
	public RetAjax updateregion(Tb_quyuxingxiangmu tb_quyuxingxiangmu){
		try{
			int flag = this.regionservice.updateregion(tb_quyuxingxiangmu);
			result=RetAjax.onDataBase(flag, 3);
		}catch(Exception e){
			Log.error("updateregion:"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
