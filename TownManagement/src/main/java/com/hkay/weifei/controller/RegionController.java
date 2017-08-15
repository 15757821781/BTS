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
import com.hkay.weifei.pojo.Tb_quyuxingxiangmu;
import com.hkay.weifei.pojo.Tb_user;
import com.hkay.weifei.service.RegionService;
import com.hkay.weifei.util.FileUpload;
import com.hkay.weifei.util.PageUtil;
import com.hkay.weifei.util.RetAjax;
import com.hkay.weifei.util.TypeStatusConstant;

@Controller
@RequestMapping("/regionmanage")
public class RegionController {
	@Resource
	private RegionService regionservice;
	private RetAjax result;
	private FileUpload fileupload = new FileUpload();
	private static  Logger Log =Logger.getLogger(RegionController.class);
	/**
	 * 新增区域性项目
	 * @param tb_quyuxingxiangmu
	 * @return
	 */
	@RequestMapping(value="/insertregion")
	@ResponseBody
	public RetAjax insertregion(HttpServletRequest request,Tb_quyuxingxiangmu tb_quyuxingxiangmu, @RequestParam("regfile1") MultipartFile[] files1,
			@RequestParam("regfile2") MultipartFile[] files2, @RequestParam("regfile3") MultipartFile[] files3,
			@RequestParam("regfile4") MultipartFile[] files4, @RequestParam("regfile5") MultipartFile[] files5,
			@RequestParam("regfile6") MultipartFile[] files6){
		try{
			HttpSession session = request.getSession();
			Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
			tb_quyuxingxiangmu.setRegentry(user.getNumber());
			String imgpath1 = fileupload.fileUpload(files1, request, TypeStatusConstant.reg_statusmap, "");
			String imgpath2 = fileupload.fileUpload(files2, request, TypeStatusConstant.reg_statusmap, "");
			String imgpath3 = fileupload.fileUpload(files3, request, TypeStatusConstant.reg_statusmap, "");
			String imgpath4 = fileupload.fileUpload(files4, request, TypeStatusConstant.reg_planmap, "");
			String imgpath5 = fileupload.fileUpload(files5, request, TypeStatusConstant.reg_planmap, "");
			String imgpath6 = fileupload.fileUpload(files6, request, TypeStatusConstant.reg_planmap, "");
			tb_quyuxingxiangmu.setRegcitypic(imgpath1);
			tb_quyuxingxiangmu.setRegtownpic(imgpath2);
			tb_quyuxingxiangmu.setRegscopeopic(imgpath3);
			tb_quyuxingxiangmu.setRegplanpic(imgpath4);
			tb_quyuxingxiangmu.setRegallplanpic(imgpath5);
			tb_quyuxingxiangmu.setRegdetailplanpic(imgpath6);
			int flag = this.regionservice.insertregion(tb_quyuxingxiangmu);
			result=RetAjax.onDataBase(flag, 1);
		}catch(Exception e){
			Log.error("insertregion:"+e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 1);
		}
		return result;
	}
	
	/**
	 * 
	 * @param limit
	 * @param pageindex
	 * @param search
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/queryregioninfo")
	@ResponseBody
	public Map<String,Object> queryregioninfo(HttpServletRequest request,@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			@RequestParam(value = "search", required = false) String search) throws UnsupportedEncodingException {
		Map<String,Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
		String number = user.getNumber();
		String userdata = user.getUserdata();
		Tb_quyuxingxiangmu tb_quyuxingxiangmu = new Tb_quyuxingxiangmu();
		 if (search != null) {
			 search = URLDecoder.decode(search, "utf-8");
			 tb_quyuxingxiangmu.setSearch(search);
		 } else {
			 tb_quyuxingxiangmu.setSearch("");
		 }
		Page<?> page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		List<Tb_quyuxingxiangmu> tb_quyuxingxiangmus = this.regionservice.queryregioninfo(tb_quyuxingxiangmu);
		if(tb_quyuxingxiangmus!=null){
			for(int i=0;i<tb_quyuxingxiangmus.size();i++){
				if(userdata.equals("3")||number.equals(tb_quyuxingxiangmus.get(i).getRegentry())){
					tb_quyuxingxiangmus.get(i).setOperation("<a href='javascript:void(0)' onclick='querydetail("+tb_quyuxingxiangmus.get(i).getRegid()+")'>查看</a>"+
							"&nbsp"+"<a href='javascript:void(0)' onclick='updateinfo("+tb_quyuxingxiangmus.get(i).getRegid()+")'>修改</a>");
				}else{
					tb_quyuxingxiangmus.get(i).setOperation("<a href='javascript:void(0)' onclick='querydetail("+tb_quyuxingxiangmus.get(i).getRegid()+")'>查看</a>");
				}
			}
		}
		int count = this.regionservice.queryregioninfocnt(tb_quyuxingxiangmu);
		map.put("rows", tb_quyuxingxiangmus);
		map.put("total", count);
		return map;
	}
	
	/**
	 * 查看详情
	 * @param request
	 * @param tb_quyuxingxiangmu
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/queryregionitemdetail")
	@ResponseBody
	public RetAjax queryregionitemdetail(HttpServletRequest request,Tb_quyuxingxiangmu tb_quyuxingxiangmu) throws UnsupportedEncodingException{
		Tb_quyuxingxiangmu regionitme=new Tb_quyuxingxiangmu();
		regionitme.setRegid(tb_quyuxingxiangmu.getRegid());
		HttpSession session = request.getSession();
		Tb_user user=(Tb_user)session.getAttribute("town_LoginData");
		List<Tb_quyuxingxiangmu> tb_quyuxingxiangmus = this.regionservice.queryregionitemdetail(regionitme);
		if(user.getUserdata().equals("1")&&tb_quyuxingxiangmus!=null){
			tb_quyuxingxiangmus.get(0).setRegcontact("******");
			tb_quyuxingxiangmus.get(0).setRegpost("******");
			tb_quyuxingxiangmus.get(0).setRegcontenttel("******");
			tb_quyuxingxiangmus.get(0).setRegchargetel("******");
			tb_quyuxingxiangmus.get(0).setRegparttel("******");
			tb_quyuxingxiangmus.get(0).setRegpartcharge("******");
			tb_quyuxingxiangmus.get(0).setRegcharge("******");
		}
		result=RetAjax.onQueryDetail(tb_quyuxingxiangmus);
		return result;
	}
	
	/**
	 * 更新区域性项目
	 * @param tb_quyuxingxiangmu
	 * @return
	 */
	@RequestMapping(value="/updateregion")
	@ResponseBody
	public RetAjax updateregion(HttpServletRequest request,Tb_quyuxingxiangmu tb_quyuxingxiangmu, @RequestParam("regfile1") MultipartFile[] files1,
			@RequestParam("regfile2") MultipartFile[] files2, @RequestParam("regfile3") MultipartFile[] files3,
			@RequestParam("regfile4") MultipartFile[] files4, @RequestParam("regfile5") MultipartFile[] files5,
			@RequestParam("regfile6") MultipartFile[] files6){
		try{
			HttpSession session = request.getSession();
			Tb_user user=(Tb_user)session.getAttribute("town_LoginData");
			tb_quyuxingxiangmu.setRegupdator(user.getNumber());
			String imgpath1 = fileupload.fileUpload(files1, request, TypeStatusConstant.reg_statusmap,tb_quyuxingxiangmu.getRegcitypic());
			String imgpath2 = fileupload.fileUpload(files2, request, TypeStatusConstant.reg_statusmap, tb_quyuxingxiangmu.getRegtownpic());
			String imgpath3 = fileupload.fileUpload(files3, request, TypeStatusConstant.reg_statusmap, tb_quyuxingxiangmu.getRegscopeopic());
			String imgpath4 = fileupload.fileUpload(files4, request, TypeStatusConstant.reg_planmap, tb_quyuxingxiangmu.getRegplanpic());
			String imgpath5 = fileupload.fileUpload(files5, request, TypeStatusConstant.reg_planmap, tb_quyuxingxiangmu.getRegallplanpic());
			String imgpath6 = fileupload.fileUpload(files6, request, TypeStatusConstant.reg_planmap, tb_quyuxingxiangmu.getRegdetailplanpic());
			tb_quyuxingxiangmu.setRegcitypic(imgpath1);
			tb_quyuxingxiangmu.setRegtownpic(imgpath2);
			tb_quyuxingxiangmu.setRegscopeopic(imgpath3);
			tb_quyuxingxiangmu.setRegplanpic(imgpath4);
			tb_quyuxingxiangmu.setRegallplanpic(imgpath5);
			tb_quyuxingxiangmu.setRegdetailplanpic(imgpath6);
			int flag = this.regionservice.updateregion(tb_quyuxingxiangmu);
			result=RetAjax.onDataBase(flag, 3);
		}catch(Exception e){
			Log.error("updateregion:"+e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 3);
		}
		return result;
	}
}
