package com.hkay.weifei.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

@Controller
@RequestMapping("/regionmanage")
public class RegionController {
	@Resource
	private RegionService regionservice;
	
	/**
	 * 新增区域性项目
	 * @param tb_quyuxingxiangmu
	 * @return
	 */
	@RequestMapping(value="/insertregion")
	@ResponseBody
	public Map<String, String> insertregion(Tb_quyuxingxiangmu tb_quyuxingxiangmu){
		int flag = this.regionservice.insertregion(tb_quyuxingxiangmu);
		Map<String, String> map=new HashMap<String, String>();
		if(flag==1){
			map.put("returnInfo", "success");
			return map;
		}else {
			map.put("returnInfo", "fail");
			return map;
		}
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
	public List<Tb_quyuxingxiangmu> queryregionitemdetail(HttpServletRequest request,Tb_quyuxingxiangmu tb_quyuxingxiangmu) throws UnsupportedEncodingException{
		Tb_quyuxingxiangmu regionitme=new Tb_quyuxingxiangmu();
		regionitme.setRegid(tb_quyuxingxiangmu.getRegid());
		List<Tb_quyuxingxiangmu> tb_quyuxingxiangmus = this.regionservice.queryregionitemdetail(regionitme);
		return tb_quyuxingxiangmus;
	}
	
	/**
	 * 更新区域性项目
	 * @param tb_quyuxingxiangmu
	 * @return
	 */
	@RequestMapping(value="/updateregion")
	@ResponseBody
	public Map<String, String> updateregion(Tb_quyuxingxiangmu tb_quyuxingxiangmu){
		Map<String, String> map=new HashMap<String, String>();
		int flag = this.regionservice.updateregion(tb_quyuxingxiangmu);
		if(flag==1){
			map.put("returnInfo", "success");
		}else{
			map.put("returnInfo", "fail");
		}
		return map;
	}
}
