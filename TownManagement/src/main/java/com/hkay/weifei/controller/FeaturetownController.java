package com.hkay.weifei.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hkay.weifei.pojo.Tb_tesexiaozhen;
import com.hkay.weifei.service.FeaturetownService;

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
}
