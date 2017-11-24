package com.hkay.weifei.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hkay.weifei.pojo.Tb_wenjianguanli;
import com.hkay.weifei.pojo.Tb_xitonggonggao;
import com.hkay.weifei.pojo.Tb_zhengcefagui;
import com.hkay.weifei.service.FileService;
import com.hkay.weifei.service.StatuteService;
import com.hkay.weifei.service.SystemNoticeService;
import com.hkay.weifei.util.PageUtil;
import com.hkay.weifei.util.RetAjax;

@Controller
@RequestMapping("/homepagemanage")
public class HomepageContyoller {
//	private static Logger Log = Logger.getLogger(HomepageContyoller.class);
	
	@Resource
	private StatuteService statuteservice;
	@Resource
	private FileService  fileService;
	@Resource
	private SystemNoticeService systemNoticeService;
	private RetAjax result;
	
//	private FileUpload fileupload = new FileUpload();
	
	/**
	 * 
	 *方法名称:queryHomePage
	 *内容：首页详细信息初始化
	 *创建人:caixuyang
	 *创建日期:2017年11月22日
	 */
	@RequestMapping("/queryHomePage")
	@ResponseBody
	public RetAjax queryHomePage(HttpServletRequest request) {
		HashMap<String, List<?>> param=new HashMap<String, List<?>>();
		Page<?> page = PageUtil.getPage(1, 5, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		List<Tb_zhengcefagui> statute = this.statuteservice.queryhomestatute();
		Page<?> page1 = PageUtil.getPage(1, 5, true);
		PageHelper.startPage(page1.getPageNum(), page1.getPageSize());
		List<Tb_wenjianguanli> file = this.fileService.queryhomefile();
		Page<?> page11 = PageUtil.getPage(1, 5, true);
		PageHelper.startPage(page11.getPageNum(), page11.getPageSize());
		List<Tb_xitonggonggao> notice = this.systemNoticeService.queryhomenotice();
		param.put("statute",statute);
		param.put("file",file);
		param.put("notice",notice);
		
		result = RetAjax.onSuccess(param, "");
		
		return result;
		
	}
}
