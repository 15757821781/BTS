package com.hkay.weifei.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import com.hkay.weifei.pojo.Tb_chubeixiangmu;
import com.hkay.weifei.pojo.Tb_user;
import com.hkay.weifei.service.ResitemService;
import com.hkay.weifei.util.FileUpload;
import com.hkay.weifei.util.PageUtil;
import com.hkay.weifei.util.RetAjax;
import com.hkay.weifei.util.TypeStatusConstant;

@Controller
@RequestMapping("/resitemmanage")
public class ResitemController {
	@Resource
	private ResitemService resitemservice;
	private RetAjax result;
	private FileUpload fileupload = new FileUpload();
	private static  Logger Log =Logger.getLogger(ResitemController.class);
	
	/**
	 * 
		 * 方法名称: insertresitem
		 * 内容摘要: 新增储备项目
		 * 创建人：caixuyang
		 * 创建日期： 2017年5月25日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping(value="/insertresitem")
	@ResponseBody
	public RetAjax insertresitem(HttpServletRequest request,Tb_chubeixiangmu tb_chubeixiangmu,@RequestParam("resfile1") MultipartFile[] files1,
			@RequestParam("resfile2") MultipartFile[] files2, @RequestParam("resfile3") MultipartFile[] files3,
			@RequestParam("resfile4") MultipartFile[] files4, @RequestParam("resfile5") MultipartFile[] files5,
			@RequestParam("resfile6") MultipartFile[] files6){
		try {
			HttpSession session = request.getSession();
			Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
			String number = user.getNumber();
			tb_chubeixiangmu.setResentry(number);
			String imgpath1 = fileupload.fileUpload(files1, request, TypeStatusConstant.res_statusmap, "");
			String imgpath2 = fileupload.fileUpload(files2, request, TypeStatusConstant.res_statusmap, "");
			String imgpath3 = fileupload.fileUpload(files3, request, TypeStatusConstant.res_statusmap, "");
			String imgpath4 = fileupload.fileUpload(files4, request, TypeStatusConstant.res_planmap, "");
			String imgpath5 = fileupload.fileUpload(files5, request, TypeStatusConstant.res_planmap, "");
			String imgpath6 = fileupload.fileUpload(files6, request, TypeStatusConstant.res_planmap, "");
			tb_chubeixiangmu.setRescitypic(imgpath1);
			tb_chubeixiangmu.setRestownpic(imgpath2);
			tb_chubeixiangmu.setResscopeopic(imgpath3);
			tb_chubeixiangmu.setResplanpic(imgpath4);
			tb_chubeixiangmu.setResallplanpic(imgpath5);
			tb_chubeixiangmu.setResdetailplanpic(imgpath6);
			int flag = this.resitemservice.insertresitem(tb_chubeixiangmu);
			result=RetAjax.onDataBase(flag, 1);
		} catch (Exception e) {
			Log.error("insertresitem:"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
		 * 方法名称: queryresinfo
		 * 内容摘要: 表格查询内容
		 * 创建人：caixuyang
		 * 创建日期： 2017年5月25日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	
	@RequestMapping("/queryresinfo")
	@ResponseBody
	public Map<String,Object> queryresinfo(@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			@RequestParam(value = "search", required = false) String search) throws UnsupportedEncodingException {
		Map<String,Object> map = new HashMap<String,Object>();
		Tb_chubeixiangmu tb_chubeixiangmu = new Tb_chubeixiangmu();
		 if (search != null) {
			 search = URLDecoder.decode(search, "utf-8");
			 tb_chubeixiangmu.setSearch(search);
		 } else {
			 tb_chubeixiangmu.setSearch("");
		 }
		 Page<?> page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		List<Tb_chubeixiangmu> tb_chubeixiangmus = this.resitemservice.queryresinfo(tb_chubeixiangmu);
		int count = this.resitemservice.queryresinfocnt(tb_chubeixiangmu);
		map.put("rows", tb_chubeixiangmus);
		map.put("total", count);
		return map;
	}
	/**
	 * 
		 * 方法名称: queryresitemdetail
		 * 内容摘要: 储备项目详情
		 * 创建人：caixuyang
		 * 创建日期： 2017年5月25日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	
	@RequestMapping("/queryresitemdetail")
	@ResponseBody	
	public RetAjax queryresitemdetail(HttpServletRequest request,Tb_chubeixiangmu tb_chubeixiangmu) throws UnsupportedEncodingException{
		Tb_chubeixiangmu resitem=new Tb_chubeixiangmu();
		resitem.setResid(tb_chubeixiangmu.getResid());
		List<Tb_chubeixiangmu> tb_chubeixiangmus = this.resitemservice.queryresitemdetail(tb_chubeixiangmu);
		result=RetAjax.onQueryDetail(tb_chubeixiangmus);
		return result;
	}
	
	@RequestMapping(value="/updateres")
	@ResponseBody
	public RetAjax updateres(HttpServletRequest request,Tb_chubeixiangmu tb_chubeixiangmu,@RequestParam("resfile1") MultipartFile[] files1,
			@RequestParam("resfile2") MultipartFile[] files2, @RequestParam("resfile3") MultipartFile[] files3,
			@RequestParam("resfile4") MultipartFile[] files4, @RequestParam("resfile5") MultipartFile[] files5,
			@RequestParam("resfile6") MultipartFile[] files6){
		try {
			String imgpath1 = fileupload.fileUpload(files1, request, TypeStatusConstant.res_statusmap, tb_chubeixiangmu.getRescitypic());
			String imgpath2 = fileupload.fileUpload(files2, request, TypeStatusConstant.res_statusmap,tb_chubeixiangmu.getRestownpic());
			String imgpath3 = fileupload.fileUpload(files3, request, TypeStatusConstant.res_statusmap, tb_chubeixiangmu.getResscopeopic());
			String imgpath4 = fileupload.fileUpload(files4, request, TypeStatusConstant.res_planmap, tb_chubeixiangmu.getResplanpic());
			String imgpath5 = fileupload.fileUpload(files5, request, TypeStatusConstant.res_planmap, tb_chubeixiangmu.getResallplanpic());
			String imgpath6 = fileupload.fileUpload(files6, request, TypeStatusConstant.res_planmap, tb_chubeixiangmu.getResdetailplanpic());
			tb_chubeixiangmu.setRescitypic(imgpath1);
			tb_chubeixiangmu.setRestownpic(imgpath2);
			tb_chubeixiangmu.setResscopeopic(imgpath3);
			tb_chubeixiangmu.setResplanpic(imgpath4);
			tb_chubeixiangmu.setResallplanpic(imgpath5);
			tb_chubeixiangmu.setResdetailplanpic(imgpath6);
			int flag = this.resitemservice.updateres(tb_chubeixiangmu);
			result=RetAjax.onDataBase(flag, 3);
		} catch (Exception e) {
			Log.error("updateres:"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
