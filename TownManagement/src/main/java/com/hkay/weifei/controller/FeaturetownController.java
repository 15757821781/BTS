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
import com.hkay.weifei.pojo.Tb_tesexiaozhen;
import com.hkay.weifei.pojo.Tb_user;
import com.hkay.weifei.pojo.Tb_zhongxinzhen;
import com.hkay.weifei.service.FeaturetownService;
import com.hkay.weifei.util.FileUpload;
import com.hkay.weifei.util.PageUtil;
import com.hkay.weifei.util.RetAjax;
import com.hkay.weifei.util.TypeStatusConstant;

@Controller
@RequestMapping("/featuretownmanage")
public class FeaturetownController {
	@Resource
	private FeaturetownService featuretownservice;
	private RetAjax result;
	private FileUpload fileupload = new FileUpload();
	private static  Logger Log =Logger.getLogger(FeaturetownController.class);
	/**
	 * 新增特色小镇信息
	 * 
	 * @param request
	 * @param tb_tesexiaozhen
	 * @return
	 */
	@RequestMapping(value="/insertfeaturetown")
	@ResponseBody
	public RetAjax insertfeaturetown(HttpServletRequest request,Tb_tesexiaozhen tb_tesexiaozhen, @RequestParam("feafile1") MultipartFile[] files1,
			@RequestParam("feafile2") MultipartFile[] files2, @RequestParam("feafile3") MultipartFile[] files3,
			@RequestParam("feafile4") MultipartFile[] files4, @RequestParam("feafile5") MultipartFile[] files5,
			@RequestParam("feafile6") MultipartFile[] files6) {
		try {
			HttpSession session = request.getSession();
			Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
			String number = user.getNumber();
			tb_tesexiaozhen.setFeacreator(number);
			String imgpath1 = fileupload.fileUpload(files1, request, TypeStatusConstant.fea_statusmap, "");
			String imgpath2 = fileupload.fileUpload(files2, request, TypeStatusConstant.fea_statusmap, "");
			String imgpath3 = fileupload.fileUpload(files3, request, TypeStatusConstant.fea_statusmap, "");
			String imgpath4 = fileupload.fileUpload(files4, request, TypeStatusConstant.fea_planmap, "");
			String imgpath5 = fileupload.fileUpload(files5, request, TypeStatusConstant.fea_planmap, "");
			String imgpath6 = fileupload.fileUpload(files6, request, TypeStatusConstant.fea_planmap, "");
			tb_tesexiaozhen.setFeacitypic(imgpath1);
			tb_tesexiaozhen.setFeatownpic(imgpath2);
			tb_tesexiaozhen.setFeascopeopic(imgpath3);
			tb_tesexiaozhen.setFeaplanpic(imgpath4);
			tb_tesexiaozhen.setFeatotalplanpic(imgpath5);
			tb_tesexiaozhen.setFeadetailplanpic(imgpath6);
			int flag = this.featuretownservice.insertfeaturetown(tb_tesexiaozhen);
			result = RetAjax.onDataBase(flag, 1);
		} catch (Exception e) {
			Log.error("insertfeaturetown:" + e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 1);
		}
		return result;
	}
	
	@RequestMapping("/queryfeaturetown")
	@ResponseBody
	public Map<String,Object> queryfeaturetown(@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			@RequestParam(value = "search", required = false) String search) throws UnsupportedEncodingException {
		Map<String,Object> map = new HashMap<String,Object>();
		Tb_tesexiaozhen tb_tesexiaozhen = new Tb_tesexiaozhen();
		 if (search != null) {
			 search = URLDecoder.decode(search, "utf-8");
			 tb_tesexiaozhen.setSearch(search);
		 } else {
			 tb_tesexiaozhen.setSearch("");
		 }
		Page<?> page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		List<Tb_tesexiaozhen> tb_zhongxinzhens = this.featuretownservice.queryfeaturetown(tb_tesexiaozhen);
		int count = this.featuretownservice.queryfeaturetowncnt(tb_tesexiaozhen);
		map.put("rows", tb_zhongxinzhens);
		map.put("total", count);
		return map;
	}
	/**
	 * 
		 * 方法名称: queryfeaturetowndetail
		 * 内容摘要: 
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年2月17日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryfeaturetowndetail")
	@ResponseBody
	public RetAjax queryfeaturetowndetail(HttpServletRequest request,Tb_tesexiaozhen tb_tesexiaozhen) throws UnsupportedEncodingException{
		HttpSession session = request.getSession();
		Tb_user user=(Tb_user)session.getAttribute("town_LoginData");
		List<Tb_tesexiaozhen> tb_tesexiaozhens = this.featuretownservice.queryfeaturetowndetail(tb_tesexiaozhen);
		if(user.getUserdata().equals("1")&&tb_tesexiaozhens!=null){
			tb_tesexiaozhens.get(0).setFealeadtel("******");
			tb_tesexiaozhens.get(0).setFeaparttel("******");
			tb_tesexiaozhens.get(0).setFeacontacttel("******");
			tb_tesexiaozhens.get(0).setFeapartway("******");
			tb_tesexiaozhens.get(0).setFeapartconten("******");
			tb_tesexiaozhens.get(0).setFeacontact("******");
			tb_tesexiaozhens.get(0).setFeapost("******");
		}
		result=RetAjax.onQueryDetail(tb_tesexiaozhens);
		return result;
	}
	
	/**
	 * 
		 * 方法名称: updatefeaturetown
		 * 内容摘要: 
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年2月17日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping(value="/updatefeaturetown")
	@ResponseBody
	public RetAjax updatefeaturetown(HttpServletRequest request,Tb_tesexiaozhen tb_tesexiaozhen, @RequestParam("feafile1") MultipartFile[] files1,
			@RequestParam("feafile2") MultipartFile[] files2, @RequestParam("feafile3") MultipartFile[] files3,
			@RequestParam("feafile4") MultipartFile[] files4, @RequestParam("feafile5") MultipartFile[] files5,
			@RequestParam("feafile6") MultipartFile[] files6){
		try{
			String imgpath1 = fileupload.fileUpload(files1, request, TypeStatusConstant.fea_statusmap, tb_tesexiaozhen.getFeacitypic());
			String imgpath2 = fileupload.fileUpload(files2, request, TypeStatusConstant.fea_statusmap, tb_tesexiaozhen.getFeatownpic());
			String imgpath3 = fileupload.fileUpload(files3, request, TypeStatusConstant.fea_statusmap, tb_tesexiaozhen.getFeascopeopic());
			String imgpath4 = fileupload.fileUpload(files4, request, TypeStatusConstant.fea_planmap, tb_tesexiaozhen.getFeaplanpic());
			String imgpath5 = fileupload.fileUpload(files5, request, TypeStatusConstant.fea_planmap, tb_tesexiaozhen.getFeatotalplanpic());
			String imgpath6 = fileupload.fileUpload(files6, request, TypeStatusConstant.fea_planmap, tb_tesexiaozhen.getFeadetailplanpic());
			tb_tesexiaozhen.setFeacitypic(imgpath1);
			tb_tesexiaozhen.setFeatownpic(imgpath2);
			tb_tesexiaozhen.setFeascopeopic(imgpath3);
			tb_tesexiaozhen.setFeaplanpic(imgpath4);
			tb_tesexiaozhen.setFeatotalplanpic(imgpath5);
			tb_tesexiaozhen.setFeadetailplanpic(imgpath6);
			int flag = this.featuretownservice.updatefeaturetown(tb_tesexiaozhen);
			result=RetAjax.onDataBase(flag, 3);
		}catch(Exception e){
			Log.error("updatefeaturetown:"+e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 3);
		}
		return result;
	}
}
