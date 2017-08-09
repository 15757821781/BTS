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
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hkay.weifei.pojo.Tb_user;
import com.hkay.weifei.pojo.Tb_zhongxinzhen;
import com.hkay.weifei.service.TownService;
import com.hkay.weifei.util.FileUpload;
import com.hkay.weifei.util.PageUtil;
import com.hkay.weifei.util.RetAjax;
import com.hkay.weifei.util.TypeStatusConstant;

@Controller
@RequestMapping("/townmanage")
public class TownController {
	@Resource
	private TownService townservice;
	private RetAjax result;
	private FileUpload fileupload = new FileUpload();
	private static  Logger Log =Logger.getLogger(TownController.class);
	/**
	 * 新增中心镇信息
	 * 
	 * @param request
	 * @param tb_zhongxinzhen
	 * @param session 
	 * @return
	 */
	@RequestMapping(value="/inserttowninfo")
	@ResponseBody
	public RetAjax inserttowninfo(HttpServletRequest request, Tb_zhongxinzhen tb_zhongxinzhen,
			@RequestParam("statusfile1") MultipartFile[] files1, @RequestParam("statusfile2") MultipartFile[] files2,
			@RequestParam("statusfile3") MultipartFile[] files3,@RequestParam("planfile1") MultipartFile[] files4,
			@RequestParam("planfile2") MultipartFile[] files5) {
		try {
			HttpSession session = request.getSession();
			Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
			String number = user.getNumber();
			tb_zhongxinzhen.setCreator(number);
			String imgpath1 = fileupload.fileUpload(files1, request, TypeStatusConstant.town_statusmap, "");
			String imgpath2 = fileupload.fileUpload(files2, request, TypeStatusConstant.town_statusmap, "");
			String imgpath3 = fileupload.fileUpload(files3, request, TypeStatusConstant.town_statusmap, "");
			String imgpath4 = fileupload.fileUpload(files4, request, TypeStatusConstant.town_planmap, "");
			String imgpath5 = fileupload.fileUpload(files5, request, TypeStatusConstant.town_planmap, "");
			tb_zhongxinzhen.setCitypic(imgpath1);
			tb_zhongxinzhen.setTownpic(imgpath2);
			tb_zhongxinzhen.setScopeopic(imgpath3);
			tb_zhongxinzhen.setTotalplanpic(imgpath4);
			tb_zhongxinzhen.setDetailplanpic(imgpath5);
			int flag = this.townservice.inserttowninfo(tb_zhongxinzhen);
			result = RetAjax.onDataBase(flag, 1);
		} catch (Exception e) {
			Log.error("error----------inserttowninfo:" + e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 1);
		}
		return result;
	}

	@RequestMapping("/querytowninfo")
	@ResponseBody
	public Map<String,Object> querytowninfo(@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			Tb_zhongxinzhen tb_zhongxinzhen) throws UnsupportedEncodingException {
		Map<String,Object> map = new HashMap<String,Object>();
		
		Page<?> page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		List<Tb_zhongxinzhen> tb_zhongxinzhens = this.townservice.querytowninfo(tb_zhongxinzhen);
		int count = this.townservice.querytowninfocnt(tb_zhongxinzhen);
		map.put("rows", tb_zhongxinzhens);
		map.put("total", count);
		return map;
	}

	@RequestMapping("/querytowndetail")
	@ResponseBody
	public RetAjax querytowndetail(HttpServletRequest request,Tb_zhongxinzhen tb_zhongxinzhen) throws UnsupportedEncodingException{
		HttpSession session = request.getSession();
		Tb_user user=(Tb_user)session.getAttribute("town_LoginData");
		List<Tb_zhongxinzhen> tb_zhongxinzhens = this.townservice.querytowndetail(tb_zhongxinzhen);
		if(user.getUserdata().equals("1")&&tb_zhongxinzhens!=null){
			tb_zhongxinzhens.get(0).setPartycommittee("******");
			tb_zhongxinzhens.get(0).setCommittelnumber("******");
			tb_zhongxinzhens.get(0).setCommittel("******");
			tb_zhongxinzhens.get(0).setMayortelnumber("******");
			tb_zhongxinzhens.get(0).setMayortel("******");
			tb_zhongxinzhens.get(0).setContactstel("******");
		}
		result=RetAjax.onQueryDetail(tb_zhongxinzhens);
		return result;
	}
	
	/**
	 * update中心镇信息
	 * 
	 * @param request
	 * @param tb_zhongxinzhen
	 * @return
	 */
	@RequestMapping(value="/updatetowninfo")
	@ResponseBody
	public RetAjax updatetowninfo(HttpServletRequest request,Tb_zhongxinzhen tb_zhongxinzhen,
			@RequestParam("statusfile1") MultipartFile[] files1, @RequestParam("statusfile2") MultipartFile[] files2,
			@RequestParam("statusfile3") MultipartFile[] files3,@RequestParam("planfile1") MultipartFile[] files4,
			@RequestParam("planfile2") MultipartFile[] files5){
		try {
			String imgpath1=fileupload.fileUpload(files1, request,TypeStatusConstant.town_statusmap,tb_zhongxinzhen.getCitypic());
			String imgpath2=fileupload.fileUpload(files2, request,TypeStatusConstant.town_statusmap,tb_zhongxinzhen.getTownpic());
			String imgpath3=fileupload.fileUpload(files3, request,TypeStatusConstant.town_statusmap,tb_zhongxinzhen.getScopeopic());
			String imgpath4=fileupload.fileUpload(files4, request,TypeStatusConstant.town_planmap,tb_zhongxinzhen.getTotalplanpic());
			String imgpath5=fileupload.fileUpload(files5, request,TypeStatusConstant.town_planmap,tb_zhongxinzhen.getDetailplanpic());
			tb_zhongxinzhen.setCitypic(imgpath1);
			tb_zhongxinzhen.setTownpic(imgpath2);
			tb_zhongxinzhen.setScopeopic(imgpath3);
			tb_zhongxinzhen.setTotalplanpic(imgpath4);
			tb_zhongxinzhen.setDetailplanpic(imgpath5);
			int flag = this.townservice.updatetowninfo(tb_zhongxinzhen);
			result = RetAjax.onDataBase(flag, 3);
		} catch (Exception e) {
			Log.error("error----------updatetowninfo:" + e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 3);
		}
		return result;
	}
}
