package com.hkay.weifei.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hkay.weifei.pojo.Tb_quyuxingxiangmu;
import com.hkay.weifei.pojo.Tb_tesexiaozhen;
import com.hkay.weifei.service.RegionService;

@Controller
@RequestMapping("/regionmanage")
public class RegionController {
	@Resource
	private RegionService regionservice;
	
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
}
