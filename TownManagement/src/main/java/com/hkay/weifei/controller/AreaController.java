package com.hkay.weifei.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hkay.weifei.pojo.Area;
import com.hkay.weifei.service.AreaService;
import com.hkay.weifei.util.RetAjax;

@Controller
@RequestMapping("/areamanage")
public class AreaController {
	@Resource
	private AreaService areaservice;
	private RetAjax result;
	private static Logger Log = Logger.getLogger(AreaController.class);

	public RetAjax queryProvince(HttpServletRequest request) {
		List<Area> area = this.areaservice.queryProvince();
		result = RetAjax.onSuccess(area, "");
		return result;
	}
}
