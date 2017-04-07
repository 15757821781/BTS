package com.hkay.weifei.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hkay.weifei.pojo.Tb_lianmengdanwei;
import com.hkay.weifei.pojo.Tb_user;
import com.hkay.weifei.service.AllianceService;
import com.hkay.weifei.util.RetAjax;

@Controller
@RequestMapping("/alliance")
public class AllianceController {
	private static Logger Log = Logger.getLogger(sheetController.class);
	@Resource
	private AllianceService allianceService;
	private RetAjax result;

	@RequestMapping("/insertallinfo")
	@ResponseBody
	public RetAjax insertallinfo(HttpServletRequest request, Tb_lianmengdanwei tb_lianmengdanwei) {
		HttpSession session = request.getSession();
		Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
		if (user == null) {
			result = RetAjax.lostLoginInfo();
		}
		try {
			int flag = this.allianceService.insertallinfo(tb_lianmengdanwei);
			if(flag==1){
				result = RetAjax.onSuccess(flag,"新增成功");
			}else{
				result = RetAjax.onFail("新增失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("error----------insertallinfo:" + e.getMessage());
		}
		return result;
	}
}
