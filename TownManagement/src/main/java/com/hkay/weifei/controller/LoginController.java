package com.hkay.weifei.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hkay.weifei.pojo.Tb_user;
import com.hkay.weifei.service.LoginService;
import com.hkay.weifei.util.MD5;

@Controller
@RequestMapping("/Login")
public class LoginController {
	@Resource
	private LoginService loginservice;

	@RequestMapping(value = "/login")
	@ResponseBody
	public Map<String, String> login(HttpServletRequest request,Tb_user tb_user)throws UnsupportedEncodingException {
		Map<String, String> map = new HashMap<String, String>();
		Tb_user user = new Tb_user();
		user.setPassword(new MD5().encryption(tb_user.getPassword()));
		user.setAccount(tb_user.getAccount());
		List<Tb_user> users = this.loginservice.queryuserinfo(user);
		if (users.size() == 1) {
			map.put("returnInfo", "success");
			return map;
		} else {
			map.put("returnInfo", "fail");
			return map;
		}
	}
}
