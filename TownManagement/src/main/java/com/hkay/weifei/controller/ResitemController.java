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
import com.hkay.weifei.pojo.Tb_chubeixiangmu;
import com.hkay.weifei.service.ResitemService;
import com.hkay.weifei.util.PageUtil;

@Controller
@RequestMapping("/resitemmanage")
public class ResitemController {
	@Resource
	private ResitemService resitemservice;
	
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
	public Map<String, String> insertresitem(Tb_chubeixiangmu tb_chubeixiangmu){
		int flag = this.resitemservice.insertresitem(tb_chubeixiangmu);
		Map<String, String> map=new HashMap<String, String>();
		if(flag==1){
			map.put("returnInfo", "success");
			return map;
		}else {
			map.put("returnInfo", "fail");
			return map;
		}
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
	public List<Tb_chubeixiangmu> queryresitemdetail(HttpServletRequest request,Tb_chubeixiangmu tb_chubeixiangmu) throws UnsupportedEncodingException{
		Tb_chubeixiangmu resitem=new Tb_chubeixiangmu();
		resitem.setResid(tb_chubeixiangmu.getResid());
		List<Tb_chubeixiangmu> tb_chubeixiangmus = this.resitemservice.queryresitemdetail(resitem);
		return tb_chubeixiangmus;
	}
	
	@RequestMapping(value="/updateres")
	@ResponseBody
	public Map<String, String> updateres(Tb_chubeixiangmu tb_chubeixiangmu){
		Map<String, String> map=new HashMap<String, String>();
		int flag = this.resitemservice.updateres(tb_chubeixiangmu);
		if(flag==1){
			map.put("returnInfo", "success");
		}else{
			map.put("returnInfo", "fail");
		}
		return map;
	}
}
