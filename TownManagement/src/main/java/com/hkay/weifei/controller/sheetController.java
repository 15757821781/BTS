package com.hkay.weifei.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hkay.weifei.pojo.Pages;
import com.hkay.weifei.pojo.Tb_wflx_new;
import com.hkay.weifei.service.wflxService;
import com.hkay.weifei.util.FileUpload;
import com.hkay.weifei.util.PageUtil;

@Controller
@RequestMapping("/wflx")
public class sheetController {
	@Resource
	private wflxService wflxservice;
	private FileUpload fileupload = new FileUpload();

	@RequestMapping("/showWflx")
	@ResponseBody
	public Map getShowWflx(@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			@RequestParam(value = "search", required = false) String search) throws UnsupportedEncodingException {
		Map map = new HashMap();
		Tb_wflx_new tb_wflx_new = new Tb_wflx_new();
		if (search != null) {
			search = URLDecoder.decode(search, "utf-8");
			tb_wflx_new.setSearch(search);
		} else {
			tb_wflx_new.setSearch("");
		}
		Page page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		List<Tb_wflx_new> tb_wflx_news = this.wflxservice.queryWflx(tb_wflx_new);
		int count = this.wflxservice.queryCountOfWflx(tb_wflx_new);
		map.put("rows", tb_wflx_news);
		map.put("total", count);
		return map;
	}

	@RequestMapping("/showWflx11")
	@ResponseBody
	public List<Tb_wflx_new> getShowWflx111(@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex) {
		Map map = new HashMap();
		Tb_wflx_new tb_wflx_new = new Tb_wflx_new();

		Page page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		List<Tb_wflx_new> tb_wflx_news = this.wflxservice.queryWflx(tb_wflx_new);
		return tb_wflx_news;
	}

	@RequestMapping("/updateWflx")
	@ResponseBody
	public String updateWflx(Tb_wflx_new tb_wflx_new) {

		int flag = this.wflxservice.updateWflx(tb_wflx_new);
		if (flag == 1) {
			return "success";
		} else {
			return "fail";
		}
	}

	@RequestMapping("/insertWflx")
	@ResponseBody
	public String insertWflx(HttpServletRequest request, Tb_wflx_new tb_wflx_new,
			@RequestParam("myFile") MultipartFile[] files) {
		String imgPath = fileupload.fileUpload(files[0], request);
		System.out.println(imgPath);
		tb_wflx_new.setTestImgPath(imgPath);
		int flag = this.wflxservice.insertWflx(tb_wflx_new);
		if (flag == 1) {
			return "success";
		} else {
			return "fail";
		}
	}

	@RequestMapping("/turnPage")
	public ModelAndView login(HttpServletResponse response) {  
		  
		return new ModelAndView("redirect:../html/index.html");  
		}
	
	@RequestMapping("/loadPages")
	@ResponseBody
	public List<Pages> loadPages(Pages page) {
		String li="";
		List<Pages> pages = this.wflxservice.loadPages(page);
//		for(Pages tmp:pages){
//			//若为一级界面
//			if(tmp.getPagelevel()==1){
//				if(tmp.getUrl()==null){
//					li+="<li><a id="+tmp.getPageid()+"><i class='fa fa-dashboard fa-fw'></i> "+tmp.getPagename()+"<span class='fa arrow'></span></a></li>";
//				}else{
//					li+="<li><a target="+tmp.getUrl()+" id="+tmp.getPageid()+"><i class='fa fa-dashboard fa-fw'></i> "+tmp.getPagename()+"<span class='fa arrow'></span></a></li>";
//				}
//			}else if(tmp.getPagelevel()==2){//若为2级界面
//				li+="<li><a target="+tmp.getUrl()+" id="+tmp.getPageid()+"> "+tmp.getPagename()+"</a></li>";
//			}
//		}
		return pages;
	}
}
