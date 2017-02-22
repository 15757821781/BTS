package com.hkay.weifei.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hkay.weifei.pojo.Tb_chubeixiangmu;
import com.hkay.weifei.pojo.Tb_zhaoshangxiangmu;
import com.hkay.weifei.service.ResitemService;

@Controller
@RequestMapping("/resitemmanage")
public class ResitemController {
	@Resource
	private ResitemService resitemservice;
	
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
}
