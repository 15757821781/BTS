package com.hkay.weifei.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hkay.weifei.pojo.Tb_role;
import com.hkay.weifei.pojo.Tb_user;
import com.hkay.weifei.service.SystemManageService;
import com.hkay.weifei.util.PageUtil;
import com.hkay.weifei.util.RetAjax;

@Controller
@RequestMapping("/systemmanage")
public class SystemController {
	private static Logger Log = Logger.getLogger(SystemController.class);
	@Resource
	private SystemManageService systemManageService;
	private RetAjax result;
	
	/**
	 * 
	 *方法名称:queryUserList
	 *内容：查询用户列表
	 *创建人:zhuwenjie
	 *创建日期:2017年6月30日下午4:02:44
	 */
	@RequestMapping("/queryUserList")
	@ResponseBody
	public Map<String, Object> queryUserList(@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			@RequestParam(value = "search", required = false) String search) throws UnsupportedEncodingException {
		Map<String, Object> map = new HashMap<String, Object>();
		Tb_user user = new Tb_user();
		if (search != null) {
			search = URLDecoder.decode(search, "utf-8");
			user.setSearch(search);
		} else {
			user.setSearch("");
		}
		Page<?> page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		List<Tb_user> tb_users = this.systemManageService.queryUserList(user);
		int count = this.systemManageService.queryUsercnt(user);
		map.put("rows", tb_users);
		map.put("total", count);
		return map;
	}
	
	/**
	 * 
	 *方法名称:queryRoleForCombo
	 *内容：查询角色组成下拉框
	 *创建人:zhuwenjie
	 *创建日期:2017年7月5日下午2:34:34
	 */
	@RequestMapping("/queryRoleForCombo")
	@ResponseBody
	public RetAjax queryRoleForCombo(Tb_user tb_user){
		List<Tb_role> tb_roles = this.systemManageService.queryRoleForCombo(tb_user);
		result = RetAjax.onSuccess(tb_roles, "");
		return result;
	}
	
	/**
	 * 
	 *方法名称:queryAccountExist
	 *内容：查询账号是否存在
	 *创建人:zhuwenjie
	 *创建日期:2017年7月6日上午10:07:21
	 */
	@RequestMapping("/queryAccountExist")
	@ResponseBody
	public Map<String, Boolean> queryAccountExist(Tb_user tb_user) {
		Tb_user user = this.systemManageService.queryAccountExist(tb_user);
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		if(user!=null){
			map.put("valid", false);
		}else{
			map.put("valid", true);
		}
		return map;
	}
	
	/**
	 * 
	 *方法名称:insertUserInfo
	 *内容：新增用户
	 *创建人:zhuwenjie
	 *创建日期:2017年7月6日上午11:14:08
	 */
	@RequestMapping("/insertUserInfo")
	@ResponseBody
	public RetAjax insertUserInfo(Tb_user tb_user) {
		try {
			int flag = this.systemManageService.insertUserInfo(tb_user);
			result = RetAjax.onDataBase(flag, 1);
		} catch (Exception e) {
			Log.error("error----------insertUserInfo:" + e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 1);
		}
		return result;
	}
	
	/**
	 * 
	 *方法名称:queryUserDetail
	 *内容：查询用户详情
	 *创建人:zhuwenjie
	 *创建日期:2017年7月6日下午5:24:51
	 */
	@RequestMapping("/queryUserDetail")
	@ResponseBody
	public RetAjax queryUserDetail(Tb_user tb_user) {
		List<Tb_user> user = this.systemManageService.queryUserDetail(tb_user);
		if(user.size()>0){
			user.get(0).setPassword("******");
			user.get(0).setRepassword("******");
		}
		result = RetAjax.onQueryDetail(user);
		return result;
	}
	
	/**
	 * 
	 *方法名称:updateUserInfo
	 *内容：更新用户信息
	 *创建人:zhuwenjie
	 *创建日期:2017年7月7日上午11:25:07
	 */
	@RequestMapping("/updateUserInfo")
	@ResponseBody
	public RetAjax updateUserInfo(Tb_user tb_user) {
		try {
			int flag = this.systemManageService.updateUserInfo(tb_user);
			result = RetAjax.onDataBase(flag, 3);
		} catch (Exception e) {
			Log.error("error----------updateUserInfo:" + e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 3);
		}
		return result;
	}
}
