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
import com.hkay.weifei.pojo.Tb_tesexiaozhen;
import com.hkay.weifei.pojo.Tb_zhongxinzhen;
import com.hkay.weifei.service.FeaturetownService;
import com.hkay.weifei.util.PageUtil;
import com.hkay.weifei.util.RetAjax;

@Controller
@RequestMapping("/featuretownmanage")
public class FeaturetownController {
	@Resource
	private FeaturetownService featuretownservice;
	private RetAjax result;
	private static  Logger Log =Logger.getLogger(FeaturetownController.class);
	/**
	 * 新增特色小镇信息
	 * 
	 * @param request
	 * @param tb_tesexiaozhen
	 * @return
	 */
	@RequestMapping(value="/insertfeaturetown")
	@ResponseBody
	public RetAjax insertfeaturetown(Tb_tesexiaozhen tb_tesexiaozhen){
		try{
			int flag = this.featuretownservice.insertfeaturetown(tb_tesexiaozhen);
			result=RetAjax.onDataBase(flag, 1);
		}catch(Exception e){
			Log.error("insertfeaturetown:"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/queryfeaturetown")
	@ResponseBody
	public Map<String,Object> queryfeaturetown(@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			@RequestParam(value = "search", required = false) String search) throws UnsupportedEncodingException {
		Map<String,Object> map = new HashMap<String,Object>();
		Tb_tesexiaozhen tb_tesexiaozhen = new Tb_tesexiaozhen();
		 if (search != null) {
			 search = URLDecoder.decode(search, "utf-8");
			 tb_tesexiaozhen.setSearch(search);
		 } else {
			 tb_tesexiaozhen.setSearch("");
		 }
		Page<?> page = PageUtil.getPage(pageindex, limit, true);
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
	public RetAjax queryfeaturetowndetail(HttpServletRequest request,Tb_tesexiaozhen tb_tesexiaozhen) throws UnsupportedEncodingException{
		List<Tb_tesexiaozhen> tb_tesexiaozhens = this.featuretownservice.queryfeaturetowndetail(tb_tesexiaozhen);
		result=RetAjax.onQueryDetail(tb_tesexiaozhens);
		return result;
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
	public RetAjax updatefeaturetown(Tb_tesexiaozhen tb_tesexiaozhen){
		try{
			int flag = this.featuretownservice.updatefeaturetown(tb_tesexiaozhen);
			result=RetAjax.onDataBase(flag, 3);
		}catch(Exception e){
			Log.error("updatefeaturetown:"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
