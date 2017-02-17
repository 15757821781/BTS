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
import com.hkay.weifei.pojo.Tb_tesexiaozhen;
import com.hkay.weifei.pojo.Tb_zhongxinzhen;
import com.hkay.weifei.service.FeaturetownService;
import com.hkay.weifei.util.PageUtil;

@Controller
@RequestMapping("/featuretownmanage")
public class FeaturetownController {
	@Resource
	private FeaturetownService featuretownservice;
	
	/**
	 * 新增特色小镇信息
	 * 
	 * @param request
	 * @param tb_tesexiaozhen
	 * @return
	 */
	@RequestMapping(value="/insertfeaturetown")
	@ResponseBody
	public Map<String, String> insertfeaturetown(Tb_tesexiaozhen tb_tesexiaozhen){
		int flag = this.featuretownservice.insertfeaturetown(tb_tesexiaozhen);
		Map<String, String> map=new HashMap<String, String>();
		if(flag==1){
			map.put("returnInfo", "success");
			return map;
		}else {
			map.put("returnInfo", "fail");
			return map;
		}
	}
	
	@RequestMapping("/queryfeaturetown")
	@ResponseBody
	public Map queryfeaturetown(@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			@RequestParam(value = "search", required = false) String search) throws UnsupportedEncodingException {
		Map map = new HashMap();
		Tb_tesexiaozhen tb_tesexiaozhen = new Tb_tesexiaozhen();
		 if (search != null) {
			 search = URLDecoder.decode(search, "utf-8");
			 tb_tesexiaozhen.setSearch(search);
		 } else {
			 tb_tesexiaozhen.setSearch("");
		 }
		Page page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		List<Tb_tesexiaozhen> tb_zhongxinzhens = this.featuretownservice.queryfeaturetown(tb_tesexiaozhen);
		int count = this.featuretownservice.queryfeaturetowncnt(tb_tesexiaozhen);
		map.put("rows", tb_zhongxinzhens);
		map.put("total", count);
		return map;
	}
	/**
	 * 
		 * 方法名称: queryfeaturetowndetail
		 * 内容摘要: 
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年2月17日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryfeaturetowndetail")
	@ResponseBody
	public List<Tb_tesexiaozhen> queryfeaturetowndetail(HttpServletRequest request,Tb_tesexiaozhen tb_tesexiaozhen) throws UnsupportedEncodingException{
		List<Tb_tesexiaozhen> tb_tesexiaozhens = this.featuretownservice.queryfeaturetowndetail(tb_tesexiaozhen);
		return tb_tesexiaozhens;
	}
	
	/**
	 * 
		 * 方法名称: updatefeaturetown
		 * 内容摘要: 
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年2月17日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping(value="/updatefeaturetown")
	@ResponseBody
	public Map<String, String> updatefeaturetown(Tb_tesexiaozhen tb_tesexiaozhen){
		Map<String, String> map=new HashMap<String, String>();
		int flag = this.featuretownservice.updatefeaturetown(tb_tesexiaozhen);
		if(flag==1){
			map.put("returnInfo", "success");
		}else{
			map.put("returnInfo", "fail");
		}
		return map;
	}
}
