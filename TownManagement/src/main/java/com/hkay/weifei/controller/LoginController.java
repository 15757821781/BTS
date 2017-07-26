package com.hkay.weifei.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hkay.weifei.pojo.Tb_user;
import com.hkay.weifei.service.LoginService;
import com.hkay.weifei.util.MD5;
import com.hkay.weifei.util.RetAjax;

@Controller
@RequestMapping("/Login")
public class LoginController {
	@Resource
	private LoginService loginservice;
	private RetAjax result;

	@RequestMapping(value = "/login")
	@ResponseBody
	public RetAjax login(HttpServletRequest request, Tb_user tb_user) throws UnsupportedEncodingException {
		Tb_user user = new Tb_user();
		user.setPassword(new MD5().encryption(tb_user.getPassword()));
		user.setAccount(tb_user.getAccount());
		List<Tb_user> users = this.loginservice.queryuserinfo(user);
		HttpSession session = request.getSession();
		result = RetAjax.onLogin(users, session);
		return result;
	}
}
