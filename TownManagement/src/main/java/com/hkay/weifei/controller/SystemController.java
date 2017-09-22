package com.hkay.weifei.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
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
import com.hkay.weifei.pojo.Pages;
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
	public RetAjax queryUserList(@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			@RequestParam(value = "search", required = false) String search) throws UnsupportedEncodingException {
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
		result = RetAjax.onGrid(tb_users, count);
		return result;
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
	
	/**
	 * 
	 *方法名称:deleteUserInfo
	 *内容：删除用户信息
	 *创建人:caixuyang
	 *创建日期:2017年9月22日上午11:25:07
	 */
	@RequestMapping("/deleteUserInfo")
	@ResponseBody
	public RetAjax deleteUserInfo(Tb_user tb_user) {
		try {
			int flag = this.systemManageService.deleteUserInfo(tb_user);
			result = RetAjax.onDataBase(flag, 3);
		} catch (Exception e) {
			Log.error("error----------deleteUserInfo:" + e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 3);
		}
		return result;
	}
	
	/**
	 * 
	 *方法名称:queryMenusPage
	 *内容：
	 *创建人:zhuwenjie
	 *创建日期:2017年7月19日下午4:08:21
	 */
	@RequestMapping("/queryMenusPage")
	@ResponseBody
	public List<Pages> queryMenusPage(Pages pages){
		List<Pages> list = new ArrayList<Pages>();
//		if(pages.getId()==null||pages.getId().equals("")){
			list = this.systemManageService.queryMenusPage(pages);
//		}else{
//			list = this.systemManageService.queryMenusChild(pages);
//		}
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				if(list.get(i).getUrl()==null||list.get(i).getUrl().equals("")){
					list.get(i).setIsParent(true);
				}else{
					list.get(i).setIsParent(false);
				}
			}
		}
		return list;
	}
	
	/**
	 * 
	 *方法名称:insertRole
	 *内容：插入角色和页面的映射
	 *创建人:zhuwenjie
	 *创建日期:2017年7月24日上午10:31:08
	 */
	@RequestMapping("/insertRole")
	@ResponseBody
	public RetAjax insertRole(Tb_role tb_role) {
		try {
			List<Map<String, String>> pages = tb_role.getNodes();
			int flag = this.systemManageService.insertRole(tb_role);
			if(pages!=null&&tb_role.getId()!=null){
				//循环遍历选择的页面
				for(int i=0;i<pages.size();i++){
					// 设置角色id
					pages.get(i).put("roleid", tb_role.getId());
				}
				//批量插入
				int ff = this.systemManageService.insertRoleMenuMapping(pages);
				if(flag!=0&&ff!=0){
					result = RetAjax.onDataBase(1, 1);
				}else{
					result = RetAjax.onDataBase(0, 1);
				}
			}else{
				result = RetAjax.onDataBase(1, 1);
			}
		} catch (Exception e) {
			Log.error("error----------insertRole:" + e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 1);
		}
		return result;
	}
	
	/**
	 * 
	 *方法名称:queryRoleList
	 *内容：查询角色列表
	 *创建人:zhuwenjie
	 *创建日期:2017年7月25日下午2:12:29
	 */
	@RequestMapping("/queryRoleList")
	@ResponseBody
	public RetAjax queryRoleList(@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			@RequestParam(value = "search", required = false) String search) throws UnsupportedEncodingException {
		Tb_role role = new Tb_role();
		if (search != null) {
			search = URLDecoder.decode(search, "utf-8");
			role.setSearch(search);
		} else {
			role.setSearch("");
		}
		Page<?> page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		List<Tb_user> tb_users = this.systemManageService.queryRoleList(role);
		int count = this.systemManageService.queryRoleCnt(role);
		result = RetAjax.onGrid(tb_users, count);
		return result;
	} 
	
	/**
	 * 
	 *方法名称:queryRoleDetail
	 *内容：查询角色详情
	 *创建人:zhuwenjie
	 *创建日期:2017年7月25日下午5:38:00
	 */
	@RequestMapping("/queryRoleDetail")
	@ResponseBody
	public RetAjax queryRoleDetail(Tb_role role){
		List<Tb_role> roles = this.systemManageService.queryRoleDetail(role);
		result = RetAjax.onQueryDetail(roles);
		return result;
	}
	
	/**
	 * 
	 *方法名称:
	 *内容：更新角色信息
	 *创建人:zhuwenjie
	 *创建日期:2017年7月26日下午1:21:05
	 */
	@RequestMapping("/updateRoleInfo")
	@ResponseBody
	public RetAjax updateRoleInfo(Tb_role tb_role){
		try {
			List<Map<String, String>> pages = tb_role.getNodes();
			// 暂时不允许修改角色名称
			int flag = this.systemManageService.updateRoleInfo(tb_role);
			// 删除角色关联的页面
			int delete = this.systemManageService.deleteRolePageMapping(tb_role);
			if(pages!=null&&tb_role.getId()!=null){
				//循环遍历选择的页面
				for(int i=0;i<pages.size();i++){
					// 设置角色id
					pages.get(i).put("roleid", tb_role.getId());
				}
				//批量插入
				int ff = this.systemManageService.insertRoleMenuMapping(pages);
				if(ff!=0&&flag!=0){
					result = RetAjax.onDataBase(1, 3);
				}else{
					result = RetAjax.onDataBase(0, 3);
				}
			}else{
				result = RetAjax.onDataBase(1, 3);
			}
		} catch (Exception e) {
			Log.error("error----------updateRoleInfo:" + e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 3);
		}
		return result;
	}
	/**
	 * 
	 *方法名称:deleteRoleInfo
	 *内容：删除角色信息
	 *创建人:caixuyang
	 *创建日期:2017年9月22日上午11:25:07
	 */
	@RequestMapping("/deleteRoleInfo")
	@ResponseBody
	public RetAjax deleteRoleInfo(Tb_role tb_role) {
		try {
			int flag = this.systemManageService.deleteRoleInfo(tb_role);
			result = RetAjax.onDataBase(flag, 3);
		} catch (Exception e) {
			Log.error("error----------deleteRoleInfo:" + e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 3);
		}
		return result;
	}
	
}
