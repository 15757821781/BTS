package com.hkay.weifei.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hkay.weifei.pojo.Tb_zhaoshangxiangmu;
import com.hkay.weifei.service.InvitemService;

@Controller
@RequestMapping("/invitemmanage")
public class InvitemController {
	@Resource
	private InvitemService invitemservice;
	
	@RequestMapping(value="/insertinvitem")
	@ResponseBody
	public Map<String, String> insertinvitem(Tb_zhaoshangxiangmu tb_zhaoshangxiangmu){
		int flag = this.invitemservice.insertinvitem(tb_zhaoshangxiangmu);
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
