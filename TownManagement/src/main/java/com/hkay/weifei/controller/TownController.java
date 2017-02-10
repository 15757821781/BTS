package com.hkay.weifei.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hkay.weifei.pojo.Tb_zhongxinzhen;
import com.hkay.weifei.service.TownService;

@Controller
@RequestMapping("/townmanage")
public class TownController {
	@Resource
	private TownService townservice;

	@RequestMapping("/inserttowninfo")
	@ResponseBody
	public String inserttowninfo(HttpServletRequest request, Tb_zhongxinzhen tb_zhongxinzhen) {
		int flag = this.townservice.inserttowninfo(tb_zhongxinzhen);
		if (flag == 1) {
			return "success";
		} else {
			return "fail";
		}
	}
}
