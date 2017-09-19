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
import com.hkay.weifei.pojo.Tb_zhaoshangxiangmu;
import com.hkay.weifei.pojo.Tb_zhongxinzhen;
import com.hkay.weifei.service.InvitemService;
import com.hkay.weifei.util.CommonUtil;
import com.hkay.weifei.util.FileUpload;
import com.hkay.weifei.util.PageUtil;
import com.hkay.weifei.util.RetAjax;
import com.hkay.weifei.util.TypeStatusConstant;

@Controller
@RequestMapping("/invitemmanage")
public class InvitemController {
	@Resource
	private InvitemService invitemservice;
	private RetAjax result;
	private FileUpload fileupload = new FileUpload();
	private static  Logger Log =Logger.getLogger(InvitemController.class);
	
	@RequestMapping(value="/insertinvitem")
	@ResponseBody
	public RetAjax insertinvitem(HttpServletRequest request,Tb_zhaoshangxiangmu tb_zhaoshangxiangmu,@RequestParam("invfile1") MultipartFile[] files1,
			@RequestParam("invfile2") MultipartFile[] files2, @RequestParam("invfile3") MultipartFile[] files3,
			@RequestParam("invfile4") MultipartFile[] files4, @RequestParam("invfile5") MultipartFile[] files5,
			@RequestParam("invfile6") MultipartFile[] files6){
		try {
			HttpSession session = request.getSession();
			Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
			String number = user.getNumber();
			tb_zhaoshangxiangmu.setInventry(number);
			String imgpath1 = fileupload.fileUpload(files1, request, TypeStatusConstant.inv_statusmap, "");
			String imgpath2 = fileupload.fileUpload(files2, request, TypeStatusConstant.inv_statusmap, "");
			String imgpath3 = fileupload.fileUpload(files3, request, TypeStatusConstant.inv_statusmap, "");
			String imgpath4 = fileupload.fileUpload(files4, request, TypeStatusConstant.inv_planmap, "");
			String imgpath5 = fileupload.fileUpload(files5, request, TypeStatusConstant.inv_planmap, "");
			String imgpath6 = fileupload.fileUpload(files6, request, TypeStatusConstant.inv_planmap, "");
			tb_zhaoshangxiangmu.setInvcitypic(imgpath1);
			tb_zhaoshangxiangmu.setInvtownpic(imgpath2);
			tb_zhaoshangxiangmu.setInvscopeopic(imgpath3);
			tb_zhaoshangxiangmu.setInvplanpic(imgpath4);
			tb_zhaoshangxiangmu.setInvallplanpic(imgpath5);
			tb_zhaoshangxiangmu.setInvdetailplanpic(imgpath6);
			int flag = this.invitemservice.insertinvitem(tb_zhaoshangxiangmu);
			result=RetAjax.onDataBase(flag, 1);
		} catch (Exception e) {
			Log.error("insertinvitem:"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
		 * 方法名称: queryinvinfo
		 * 内容摘要: 表格查询内容
		 * 创建人：caixuyang
		 * 创建日期： 2017年5月15日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryinvinfo")
	@ResponseBody
	public RetAjax queryinvinfo(HttpServletRequest request,@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			Tb_zhaoshangxiangmu tb_zhaoshangxiangmu) throws UnsupportedEncodingException {
		HttpSession session = request.getSession();
		Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
		String number = user.getNumber();
		String userdata = user.getUserdata();
		Page<?> page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		//处理高级搜索
		tb_zhaoshangxiangmu.setSupersearch(GetSuperSearchSql(tb_zhaoshangxiangmu));
		List<Tb_zhaoshangxiangmu> tb_zhaoshangxiangmus = this.invitemservice.queryinvinfo(tb_zhaoshangxiangmu);
		if(tb_zhaoshangxiangmus!=null){
			for(int i=0;i<tb_zhaoshangxiangmus.size();i++){
				if(userdata.equals("3")||number.equals(tb_zhaoshangxiangmus.get(i).getInventry())){
					tb_zhaoshangxiangmus.get(i).setOperation("<a href='javascript:void(0)' onclick='querydetail("+tb_zhaoshangxiangmus.get(i).getInvid()+")'>查看</a>"+
							"&nbsp"+"<a href='javascript:void(0)' onclick='updateinfo("+tb_zhaoshangxiangmus.get(i).getInvid()+")'>修改</a>");
				}else{
					tb_zhaoshangxiangmus.get(i).setOperation("<a href='javascript:void(0)' onclick='querydetail("+tb_zhaoshangxiangmus.get(i).getInvid()+")'>查看</a>");
				}
			}
		}
		int count = this.invitemservice.queryinvinfocnt(tb_zhaoshangxiangmu);
		result = RetAjax.onGrid(tb_zhaoshangxiangmus, count);
		return result;
	}
	
	/**
	 * 
		 * 方法名称: queryinvitemdetail
		 * 内容摘要: 招商项目详情
		 * 创建人：caixuyang
		 * 创建日期： 2017年5月15日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryinvitemdetail")
	@ResponseBody
	public RetAjax queryinvitemdetail(HttpServletRequest request,Tb_zhaoshangxiangmu tb_zhaoshangxiangmu) throws UnsupportedEncodingException{
//		Tb_zhaoshangxiangmu invitem=new Tb_zhaoshangxiangmu();
//		invitem.setInvid(tb_zhaoshangxiangmu.getInvid());
		HttpSession session = request.getSession();
		Tb_user user=(Tb_user)session.getAttribute("town_LoginData");
		List<Tb_zhaoshangxiangmu> tb_zhaoshangxiangmus = this.invitemservice.queryinvitemdetail(tb_zhaoshangxiangmu);
		if(user.getUserdata().equals("1")&&tb_zhaoshangxiangmus!=null){
			tb_zhaoshangxiangmus.get(0).setInvcontact("******");
			tb_zhaoshangxiangmus.get(0).setInvpost("******");
			tb_zhaoshangxiangmus.get(0).setInvcontacttel("******");
		}
		result=RetAjax.onQueryDetail(tb_zhaoshangxiangmus);
		return result;
	}
	
	@RequestMapping(value="/updateinv")
	@ResponseBody
	public RetAjax updateinv(HttpServletRequest request,Tb_zhaoshangxiangmu tb_zhaoshangxiangmu,@RequestParam("invfile1") MultipartFile[] files1,
			@RequestParam("invfile2") MultipartFile[] files2, @RequestParam("invfile3") MultipartFile[] files3,
			@RequestParam("invfile4") MultipartFile[] files4, @RequestParam("invfile5") MultipartFile[] files5,
			@RequestParam("invfile6") MultipartFile[] files6){
		try {
			HttpSession session = request.getSession();
			Tb_user user=(Tb_user)session.getAttribute("town_LoginData");
			tb_zhaoshangxiangmu.setInvupdator(user.getNumber());
			String imgpath1 = fileupload.fileUpload(files1, request, TypeStatusConstant.inv_statusmap, tb_zhaoshangxiangmu.getInvcitypic());
			String imgpath2 = fileupload.fileUpload(files2, request, TypeStatusConstant.inv_statusmap,tb_zhaoshangxiangmu.getInvtownpic());
			String imgpath3 = fileupload.fileUpload(files3, request, TypeStatusConstant.inv_statusmap, tb_zhaoshangxiangmu.getInvscopeopic());
			String imgpath4 = fileupload.fileUpload(files4, request, TypeStatusConstant.inv_planmap, tb_zhaoshangxiangmu.getInvplanpic());
			String imgpath5 = fileupload.fileUpload(files5, request, TypeStatusConstant.inv_planmap, tb_zhaoshangxiangmu.getInvallplanpic());
			String imgpath6 = fileupload.fileUpload(files6, request, TypeStatusConstant.inv_planmap, tb_zhaoshangxiangmu.getInvdetailplanpic());
			tb_zhaoshangxiangmu.setInvcitypic(imgpath1);
			tb_zhaoshangxiangmu.setInvtownpic(imgpath2);
			tb_zhaoshangxiangmu.setInvscopeopic(imgpath3);
			tb_zhaoshangxiangmu.setInvplanpic(imgpath4);
			tb_zhaoshangxiangmu.setInvallplanpic(imgpath5);
			tb_zhaoshangxiangmu.setInvdetailplanpic(imgpath6);
			int flag = this.invitemservice.updateinv(tb_zhaoshangxiangmu);
			result=RetAjax.onDataBase(flag, 3);
		} catch (Exception e) {
			Log.error("error-------------updateinv:"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 *方法名称:
	 *内容：处理高级搜索内容
	 *创建人:caixuyang
	 *创建日期:2017年8月14日下午4:14:34
	 */
	
	public String GetSuperSearchSql(Tb_zhaoshangxiangmu zsxm) {
		StringBuilder sql = new StringBuilder();
		if(CommonUtil.JudgeEmpty(zsxm.getSearch())){
			String search = zsxm.getSearch();
			sql.append(" and (a.invnumber like '%"+search+"%' or a.invname like '%"+search+"%' or a.invcharge like '%"+search+"%')");
		}
		if(CommonUtil.JudgeEmpty(zsxm.getInvname())){
			sql.append(" and a.invname like '%"+zsxm.getInvname()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zsxm.getInvnumber())){
			sql.append(" and a.invnumber like '%"+zsxm.getInvnumber()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zsxm.getInvprovince())){
			sql.append(" and a.invprovince = '"+zsxm.getInvprovince()+"'");
		}
		if(CommonUtil.JudgeEmpty(zsxm.getInvcity())){
			sql.append(" and a.invcity = '"+zsxm.getInvcity()+"'");
		}
		if(CommonUtil.JudgeEmpty(zsxm.getInvtown())){
			sql.append(" and a.invtown = '"+zsxm.getInvtown()+"'");
		}
		if(CommonUtil.JudgeEmpty(zsxm.getInvtownship())){
			sql.append(" and a.invtownship like '%"+zsxm.getInvtownship()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zsxm.getInvcharge())){
			sql.append(" and a.invcharge like '%"+zsxm.getInvcharge()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zsxm.getInvjoinway())){
			sql.append(" and a.invjoinway in ('"+zsxm.getInvjoinway()+"')");
		}
		if(CommonUtil.JudgeEmpty(zsxm.getInvindustry())){
			sql.append(" and a.invindustry like '%"+zsxm.getInvindustry()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zsxm.getInvlandscales())){
			sql.append(CommonUtil.HandleNum("invlandscale", zsxm.getInvlandscales()));
		}
		if(CommonUtil.JudgeEmpty(zsxm.getInvplotratios())){
			sql.append(CommonUtil.HandleNum("invplotratio", zsxm.getInvplotratios()));
		}
		if(CommonUtil.JudgeEmpty(zsxm.getInvestments())){
			sql.append(CommonUtil.HandleNum("investment", zsxm.getInvestments()));
		}
		if(CommonUtil.JudgeEmpty(zsxm.getInvestmentalls())){
			sql.append(CommonUtil.HandleNum("investmentall", zsxm.getInvestmentalls()));
		}
		if(CommonUtil.JudgeEmpty(zsxm.getInvplanuse())){
			sql.append(" and a.invplanuse like '%"+zsxm.getInvplanuse()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zsxm.getInvexpectbuild())){
			sql.append(" and a.invexpectbuild = '"+zsxm.getInvexpectbuild()+"'");
		}
		if(CommonUtil.JudgeEmpty(zsxm.getInvplanaddress())){
			sql.append(" and a.invplanaddress like '%"+zsxm.getInvplanaddress()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zsxm.getInvbuildcontent())){
			sql.append(" and a.invbuildcontent like '%"+zsxm.getInvbuildcontent()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zsxm.getInvbuildcondition())){
			sql.append(" and a.invbuildcondition like '%"+zsxm.getInvbuildcondition()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zsxm.getInvincentives())){
			sql.append(" and a.invincentives like '%"+zsxm.getInvincentives()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zsxm.getInvessential())){
			sql.append(" and a.invessential like '%"+zsxm.getInvessential()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zsxm.getInvbenefit())){
			sql.append(" and a.invbenefit like '%"+zsxm.getInvbenefit()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zsxm.getInvcontact())){
			sql.append(" and a.invcontact like '%"+zsxm.getInvcontact()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zsxm.getInvcontacttel())){
			sql.append(" and a.invcontacttel like '%"+zsxm.getInvcontacttel()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zsxm.getInvpost())){
			sql.append(" and a.invpost like '%"+zsxm.getInvpost()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zsxm.getInventry())){
			sql.append(" and a.inventry like '%"+zsxm.getInventry()+"%'");
		}
		return sql.toString();
	}
	/**
	 * 删除信息时更新信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/updateinvitemState")
	@ResponseBody
	public RetAjax updateinvitemState(HttpServletRequest request,Tb_zhaoshangxiangmu tb_zhaoshangxiangmu,@RequestParam("invObj[]") String invObj) { 
		try {
			int flag = this.invitemservice.updateinvitemState(invObj);
			if(flag!=0){
				flag=1;
			}
			result = RetAjax.onDataBase(flag,3);
		} catch (Exception e) {
			Log.error("error----------updateinvitemState:" + e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 3);
		}
		return result; 
	} 
}
