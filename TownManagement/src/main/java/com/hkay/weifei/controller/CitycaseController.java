package com.hkay.weifei.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hkay.weifei.pojo.Tb_shehuizuzhidanwei;
import com.hkay.weifei.pojo.Tb_user;
import com.hkay.weifei.pojo.Tb_xianshiqingkuang;
import com.hkay.weifei.service.CitycaseService;
import com.hkay.weifei.util.PageUtil;
import com.hkay.weifei.util.RetAjax;

@Controller
@RequestMapping("/citymanage")
public class CitycaseController {
	private static Logger Log = Logger.getLogger(CitycaseController.class);
	@Resource
	private CitycaseService citycaseService;
	private RetAjax result;
	
	@RequestMapping("/insertCityInfo")
	@ResponseBody
	public RetAjax insertCityInfo(HttpServletRequest request, Tb_xianshiqingkuang tb_xianshiqingkuang){
		try {
			HttpSession session = request.getSession();
			Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
			String number = user.getNumber();
			tb_xianshiqingkuang.setCountryentry(number);
			int flag = this.citycaseService.insertCityInfo(tb_xianshiqingkuang);
			result = RetAjax.onDataBase(flag,1);
		} catch (Exception e) {
			Log.error("error----------insertorginfo:" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
		 * 方法名称: queryCityList
		 * 内容摘要: 查询县市基本情况列表
		 * 创建人：caixuyang
		 * 创建日期： 2017年8月24日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryCityList")
	@ResponseBody
	public Map<String,Object> queryCityList(HttpServletRequest request,@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			Tb_xianshiqingkuang tb_xianshiqingkuang) throws UnsupportedEncodingException {
		Map<String,Object> map = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
		String number = user.getNumber();
		String userdata = user.getUserdata();
		Page<?> page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		tb_xianshiqingkuang.setSupersearch(GetSuperSearchSql(tb_xianshiqingkuang));
		List<Tb_xianshiqingkuang> tb_xianshiqingkuangs = this.citycaseService.queryCityList(tb_xianshiqingkuang);
		if(tb_xianshiqingkuangs!=null){
			for(int i=0;i<tb_xianshiqingkuangs.size();i++){
				if(userdata.equals("3")||number.equals(tb_xianshiqingkuangs.get(i).getCountryentry())){
					tb_xianshiqingkuangs.get(i).setOperation("<a href='javascript:void(0)' onclick='querydetail("+tb_xianshiqingkuangs.get(i).getCountryid()+")'>查看</a>"+
							"&nbsp"+"<a href='javascript:void(0)' onclick='updateinfo("+tb_xianshiqingkuangs.get(i).getCountryid()+")'>修改</a>");
				}else{
					tb_xianshiqingkuangs.get(i).setOperation("<a href='javascript:void(0)' onclick='querydetail("+tb_xianshiqingkuangs.get(i).getCountryid()+")'>查看</a>");
				}
			}
		}
		int count = this.citycaseService.queryCitycnt(tb_xianshiqingkuang);
		map.put("rows", tb_xianshiqingkuangs);
		map.put("total", count);
		return map;
	}
	/**
	 * 
		 * 方法名称: queryCityDetail
		 * 内容摘要: 查询县市基本情况详细
		 * 创建人：caixuyang
		 * 创建日期： 2017年5月24日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryCityDetail")
	@ResponseBody
	public RetAjax queryCityDetail(HttpServletRequest request,Tb_xianshiqingkuang tb_xianshiqingkuang)throws UnsupportedEncodingException {
		HttpSession session = request.getSession();
		Tb_user user=(Tb_user)session.getAttribute("town_LoginData");
		List<Tb_xianshiqingkuang> tb_xianshiqingkuangs = this.citycaseService.queryCityDetail(tb_xianshiqingkuang);
//		if(user.getUserdata().equals("1")&&tb_xianshiqingkuangs!=null){
//			tb_xianshiqingkuangs.get(0).setOrgcontact("******");
//			tb_xianshiqingkuangs.get(0).setOrgpost("******");
//			tb_xianshiqingkuangs.get(0).setOrgcontacttel("******");
//		}
		result = RetAjax.onQueryDetail(tb_xianshiqingkuangs);
		return result;
	}
	/**
	 * 
		 * 方法名称: updateCityInfo
		 * 内容摘要: 更新县市基本情况信息
		 * 创建人：caixuyang
		 * 创建日期： 2017年5月24日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/updateCityInfo")
	@ResponseBody
	public RetAjax updateCityInfo(HttpServletRequest request,Tb_xianshiqingkuang tb_xianshiqingkuang ){
		try {
			HttpSession session = request.getSession();
			Tb_user user=(Tb_user)session.getAttribute("town_LoginData");
			tb_xianshiqingkuang.setCountryupdator(user.getNumber());
			int flag = this.citycaseService.updateCityInfo(tb_xianshiqingkuang);
			result = RetAjax.onDataBase(flag, 3);
		} catch (Exception e) {
			Log.error("error-------------updateCityInfo:"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 *方法名称:
	 *内容：处理高级搜索内容
	 *创建人:caixuyang
	 *创建日期:2017年8月24日
	 */
	
	private String GetSuperSearchSql(Tb_xianshiqingkuang tb_xianshiqingkuang) {
		// TODO Auto-generated method stub
		return null;
	}
}
