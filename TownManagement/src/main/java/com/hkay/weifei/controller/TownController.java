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
import com.hkay.weifei.pojo.Tb_zhongxinzhen;
import com.hkay.weifei.service.TownService;
import com.hkay.weifei.util.PageUtil;
import com.hkay.weifei.util.RetAjax;

@Controller
@RequestMapping("/townmanage")
public class TownController {
	@Resource
	private TownService townservice;
	private RetAjax result;
	private static  Logger Log =Logger.getLogger(TownController.class);
	/**
	 * 新增中心镇信息
	 * 
	 * @param request
	 * @param tb_zhongxinzhen
	 * @return
	 */
	@RequestMapping(value="/inserttowninfo")
	@ResponseBody
	public RetAjax inserttowninfo(HttpServletRequest request,Tb_zhongxinzhen tb_zhongxinzhen){
		try {
			int flag = this.townservice.inserttowninfo(tb_zhongxinzhen);
			result = RetAjax.onDataBase(flag, 1);
		} catch (Exception e) {
			Log.error("error----------inserttowninfo:" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/querytowninfo")
	@ResponseBody
	public Map<String,Object> querytowninfo(@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			@RequestParam(value = "search", required = false) String search) throws UnsupportedEncodingException {
		Map<String,Object> map = new HashMap<String,Object>();
		Tb_zhongxinzhen tb_zhongxinzhen = new Tb_zhongxinzhen();
		 if (search != null) {
			 search = URLDecoder.decode(search, "utf-8");
			 tb_zhongxinzhen.setSearch(search);
		 } else {
			 tb_zhongxinzhen.setSearch("");
		 }
		Page<?> page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		List<Tb_zhongxinzhen> tb_zhongxinzhens = this.townservice.querytowninfo(tb_zhongxinzhen);
		int count = this.townservice.querytowninfocnt(tb_zhongxinzhen);
		map.put("rows", tb_zhongxinzhens);
		map.put("total", count);
		return map;
	}

	@RequestMapping("/querytowndetail")
	@ResponseBody
	public RetAjax querytowndetail(HttpServletRequest request,Tb_zhongxinzhen tb_zhongxinzhen) throws UnsupportedEncodingException{
		List<Tb_zhongxinzhen> tb_zhongxinzhens = this.townservice.querytowndetail(tb_zhongxinzhen);
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
	public RetAjax updatetowninfo(HttpServletRequest request,Tb_zhongxinzhen tb_zhongxinzhen){
		try {
			int flag = this.townservice.updatetowninfo(tb_zhongxinzhen);
			result = RetAjax.onDataBase(flag, 3);
		} catch (Exception e) {
			Log.error("error----------updatetowninfo:" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
