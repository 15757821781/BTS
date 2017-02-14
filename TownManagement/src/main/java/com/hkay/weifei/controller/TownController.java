package com.hkay.weifei.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hkay.weifei.pojo.Tb_zhongxinzhen;
import com.hkay.weifei.service.TownService;
import com.hkay.weifei.util.PageUtil;

@Controller
@RequestMapping("/townmanage")
public class TownController {
	@Resource
	private TownService townservice;

	/**
	 * 新增中心镇信息
	 * 
	 * @param request
	 * @param tb_zhongxinzhen
	 * @return
	 */
	@RequestMapping(value="/inserttowninfo")
	@ResponseBody
	public Map<String, String> inserttowninfo(Tb_zhongxinzhen tb_zhongxinzhen){
		int flag = this.townservice.inserttowninfo(tb_zhongxinzhen);
		Map<String, String> map=new HashMap<String, String>();
		if(flag==1){
			map.put("returnInfo", "success");
			return map;
		}else {
			map.put("returnInfo", "fail");
			return map;
		}
	}

	@RequestMapping("/querytowninfo")
	@ResponseBody
	public Map querytowninfo(@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			@RequestParam(value = "search", required = false) String search) throws UnsupportedEncodingException {
		Map map = new HashMap();
		Tb_zhongxinzhen tb_zhongxinzhen = new Tb_zhongxinzhen();
		 if (search != null) {
			 search = URLDecoder.decode(search, "utf-8");
			 tb_zhongxinzhen.setSearch(search);
		 } else {
			 tb_zhongxinzhen.setSearch("");
		 }
		Page page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		List<Tb_zhongxinzhen> tb_zhongxinzhens = this.townservice.querytowninfo(tb_zhongxinzhen);
		int count = this.townservice.querytowninfocnt(tb_zhongxinzhen);
		map.put("rows", tb_zhongxinzhens);
		map.put("total", count);
		return map;
	}

	@RequestMapping("/querytowndetail")
	@ResponseBody
	public List<Tb_zhongxinzhen> querytowndetail(HttpServletRequest request,Tb_zhongxinzhen tb_zhongxinzhen) throws UnsupportedEncodingException{
		List<Tb_zhongxinzhen> tb_zhongxinzhens = this.townservice.querytowndetail(tb_zhongxinzhen);
		return tb_zhongxinzhens;
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
	public Map<String, String> updatetowninfo(Tb_zhongxinzhen tb_zhongxinzhen){
		Map<String, String> map=new HashMap<String, String>();
		int flag = this.townservice.updatetowninfo(tb_zhongxinzhen);
		if(flag==1){
			map.put("returnInfo", "success");
		}else{
			map.put("returnInfo", "fail");
		}
		return map;
	}
}
