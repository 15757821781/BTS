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
import com.hkay.weifei.pojo.Tb_qiyedanwei;
import com.hkay.weifei.pojo.Tb_user;
import com.hkay.weifei.pojo.Tb_zhongxinzhen;
import com.hkay.weifei.service.CompanyService;
import com.hkay.weifei.util.FileUpload;
import com.hkay.weifei.util.PageUtil;
import com.hkay.weifei.util.RetAjax;
import com.hkay.weifei.util.TypeStatusConstant;

@Controller
@RequestMapping("/commanage")
public class CompanyController {
	private static Logger Log = Logger.getLogger(CompanyController.class);
	@Resource
	private CompanyService companyService;
	private RetAjax result;
	private FileUpload fileupload = new FileUpload();

	/**
	 * 
		 * 方法名称: insertComInfo
		 * 内容摘要: 新增企业单位信息
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年5月4日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/insertComInfo")
	@ResponseBody
	public RetAjax insertcominfo(HttpServletRequest request, Tb_qiyedanwei qiyedanwei,
			@RequestParam("comcertificatepic") MultipartFile[] files) {
		try {
			HttpSession session = request.getSession();
			Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
			String number = user.getNumber();
			qiyedanwei.setComcreator(number);
			String imgpath = fileupload.fileUpload(files, request, TypeStatusConstant.proof, "");
			qiyedanwei.setComcertificate(imgpath);
			int flag = this.companyService.insertcominfo(qiyedanwei);
			result = RetAjax.onDataBase(flag,1);
		} catch (Exception e) {
			Log.error("error----------insertcominfo:" + e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 1);
		}
		return result;
	}
	
	/**
	 * 
		 * 方法名称: queryComList
		 * 内容摘要: 查询企业单位列表
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年5月4日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryComList")
	@ResponseBody
	public Map<String,Object> queryComList(@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			@RequestParam(value = "search", required = false) String search) throws UnsupportedEncodingException {
		Map<String,Object> map = new HashMap<String,Object>();
		Tb_qiyedanwei tb_qiyedanwei = new Tb_qiyedanwei();
		 if (search != null) {
			 search = URLDecoder.decode(search, "utf-8");
			 tb_qiyedanwei.setSearch(search);
		 } else {
			 tb_qiyedanwei.setSearch("");
		 }
		Page<?> page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		List<Tb_qiyedanwei> tb_qiyedanweis = this.companyService.queryComList(tb_qiyedanwei);
		int count = this.companyService.querycomcnt(tb_qiyedanwei);
		map.put("rows", tb_qiyedanweis);
		map.put("total", count);
		return map;
	}
	
	/**
	 * 
		 * 方法名称: queryComDetail
		 * 内容摘要: 查询企业详细
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年5月4日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryComDetail")
	@ResponseBody
	public RetAjax queryComDetail(Tb_qiyedanwei tb_qiyedanwei) {
		List<Tb_qiyedanwei> tb_qiyedanweis = this.companyService.queryComDetail(tb_qiyedanwei);
		result = RetAjax.onQueryDetail(tb_qiyedanweis);
		return result;
	}
	
	/**
	 * 
		 * 方法名称: updateComInfo
		 * 内容摘要: 更新企业信息
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年5月4日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/updateComInfo")
	@ResponseBody
	public RetAjax updateComInfo(HttpServletRequest request,Tb_qiyedanwei tb_qiyedanwei,
			@RequestParam("comcertificatepic") MultipartFile[] files) {
		try {
			String imgpath=fileupload.fileUpload(files, request,TypeStatusConstant.proof,tb_qiyedanwei.getComcertificate());
			tb_qiyedanwei.setComcertificate(imgpath);
			int flag = this.companyService.updateComInfo(tb_qiyedanwei);
			result = RetAjax.onDataBase(flag, 3);
		} catch (Exception e) {
			Log.error("error-------------updateComInfo:"+e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 3);
		}
		return result;
	}
}
