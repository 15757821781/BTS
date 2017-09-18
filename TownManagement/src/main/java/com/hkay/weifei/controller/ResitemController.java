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
import com.hkay.weifei.pojo.Tb_quyuxingxiangmu;
import com.hkay.weifei.pojo.Tb_user;
import com.hkay.weifei.pojo.Tb_zhongxinzhen;
import com.hkay.weifei.service.ResitemService;
import com.hkay.weifei.util.CommonUtil;
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
	public Map<String,Object> queryresinfo(HttpServletRequest request,@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			Tb_chubeixiangmu tb_chubeixiangmu) throws UnsupportedEncodingException {
		Map<String,Object> map = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
		String number = user.getNumber();
		String userdata = user.getUserdata();
		Page<?> page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		//处理高级搜索
		tb_chubeixiangmu.setSupersearch(GetSuperSearchSql(tb_chubeixiangmu));
		List<Tb_chubeixiangmu> tb_chubeixiangmus = this.resitemservice.queryresinfo(tb_chubeixiangmu);
		if(tb_chubeixiangmus!=null){
			for(int i=0;i<tb_chubeixiangmus.size();i++){
				if(userdata.equals("3")||number.equals(tb_chubeixiangmus.get(i).getResentry())){
					tb_chubeixiangmus.get(i).setOperation("<a href='javascript:void(0)' onclick='querydetail("+tb_chubeixiangmus.get(i).getResid()+")'>查看</a>"+
							"&nbsp"+"<a href='javascript:void(0)' onclick='updateinfo("+tb_chubeixiangmus.get(i).getResid()+")'>修改</a>");
				}else{
					tb_chubeixiangmus.get(i).setOperation("<a href='javascript:void(0)' onclick='querydetail("+tb_chubeixiangmus.get(i).getResid()+")'>查看</a>");
				}
			}
		}
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
		HttpSession session = request.getSession();
		Tb_user user=(Tb_user)session.getAttribute("town_LoginData");
		List<Tb_chubeixiangmu> tb_chubeixiangmus = this.resitemservice.queryresitemdetail(tb_chubeixiangmu);
		if(user.getUserdata().equals("1")&&tb_chubeixiangmus!=null){
			tb_chubeixiangmus.get(0).setRescontactunit("******");
			tb_chubeixiangmus.get(0).setRescontacts("******");
			tb_chubeixiangmus.get(0).setRescontactway("******");
			tb_chubeixiangmus.get(0).setReschargetel("******");
			tb_chubeixiangmus.get(0).setRescharge("******");
		}
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
			HttpSession session = request.getSession();
			Tb_user user=(Tb_user)session.getAttribute("town_LoginData");
			tb_chubeixiangmu.setResupdator(user.getNumber());
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
	/**
	 * 
	 *方法名称:
	 *内容：处理高级搜索内容
	 *创建人:caixuyang
	 *创建日期:2017年8月14日下午4:14:34
	 */
	
	public String GetSuperSearchSql(Tb_chubeixiangmu cbxm) {
		StringBuilder sql = new StringBuilder();
		if(CommonUtil.JudgeEmpty(cbxm.getSearch())){
			String search = cbxm.getSearch();
			sql.append(" and (a.resnumber like '%"+search+"%' or a.resitemname like '%"+search+"%' or a.rescompetentunit like '%"+search+"%')");
		}
		if(CommonUtil.JudgeEmpty(cbxm.getResitemname())){
			sql.append(" and a.resitemname like '%"+cbxm.getResitemname()+"%'");
		}
		if(CommonUtil.JudgeEmpty(cbxm.getResnumber())){
			sql.append(" and a.resnumber like '%"+cbxm.getResnumber()+"%'");
		}
		if(CommonUtil.JudgeEmpty(cbxm.getResprovince())){
			sql.append(" and a.resprovince = '"+cbxm.getResprovince()+"'");
		}
		if(CommonUtil.JudgeEmpty(cbxm.getRescity())){
			sql.append(" and a.rescity = '"+cbxm.getRescity()+"'");
		}
		if(CommonUtil.JudgeEmpty(cbxm.getRestown())){
			sql.append(" and a.restown = '"+cbxm.getRestown()+"'");
		}
		if(CommonUtil.JudgeEmpty(cbxm.getRestownship())){
			sql.append(" and a.restownship like '%"+cbxm.getRestownship()+"%'");
		}
		if(CommonUtil.JudgeEmpty(cbxm.getResjoinway())){
			sql.append(" and a.resjoinway in ('"+cbxm.getResjoinway()+"')");
		}
		if(CommonUtil.JudgeEmpty(cbxm.getRescontract())){
			sql.append(" and a.rescontract = '"+cbxm.getRescontract()+"'");
		}
		if(CommonUtil.JudgeEmpty(cbxm.getResplanareas())){
			sql.append(CommonUtil.HandleNum("resplanarea", cbxm.getResplanareas()));
		}
		if(CommonUtil.JudgeEmpty(cbxm.getResplaninvests())){
			sql.append(CommonUtil.HandleNum("resplaninvest", cbxm.getResplaninvests()));
		}
		if(CommonUtil.JudgeEmpty(cbxm.getReslandareas())){
			sql.append(CommonUtil.HandleNum("reslandarea", cbxm.getReslandareas()));
		}
		if(CommonUtil.JudgeEmpty(cbxm.getResposition())){
			sql.append(" and a.resposition like '%"+cbxm.getResposition()+"%'");
		}
		if(CommonUtil.JudgeEmpty(cbxm.getRebasic())){
			sql.append(" and a.rebasic like '%"+cbxm.getRebasic()+"%'");
		}
		if(CommonUtil.JudgeEmpty(cbxm.getResbuildcontent())){
			sql.append(" and a.resbuildcontent like '%"+cbxm.getResbuildcontent()+"%'");
		}
		if(CommonUtil.JudgeEmpty(cbxm.getResintentions())){
			sql.append(" and a.resintentions like '%"+cbxm.getResintentions()+"%'");
		}
		if(CommonUtil.JudgeEmpty(cbxm.getResdockingtime())){
			sql.append(" and DATE_FORMAT(a.resdockingtime,'%Y-%m') = '"+cbxm.getResdockingtime()+"'");
		}
		if(CommonUtil.JudgeEmpty(cbxm.getResdockingstage())){
			sql.append(" and a.resdockingstage like '%"+cbxm.getResdockingstage()+"%'");
		}
		if(CommonUtil.JudgeEmpty(cbxm.getResfeedbacknode())){
			sql.append(" and DATE_FORMAT(a.resfeedbacknode,'%Y-%m-%d') = '"+cbxm.getResfeedbacknode()+"'");
		}
		if(CommonUtil.JudgeEmpty(cbxm.getRescompetentunit())){
			sql.append(" and a.rescompetentunit like '%"+cbxm.getRescompetentunit()+"%'");
		}
		if(CommonUtil.JudgeEmpty(cbxm.getRescharge())){
			sql.append(" and a.rescharge like '%"+cbxm.getRescharge()+"%'");
		}
		if(CommonUtil.JudgeEmpty(cbxm.getReschargetel())){
			sql.append(" and a.reschargetel like '%"+cbxm.getReschargetel()+"%'");
		}
		if(CommonUtil.JudgeEmpty(cbxm.getRescontactunit())){
			sql.append(" and a.rescontactunit like '%"+cbxm.getRescontactunit()+"%'");
		}
		if(CommonUtil.JudgeEmpty(cbxm.getRescontacts())){
			sql.append(" and a.rescontacts like '%"+cbxm.getRescontacts()+"%'");
		}
		if(CommonUtil.JudgeEmpty(cbxm.getRescontactway())){
			sql.append(" and a.rescontactway like '%"+cbxm.getRescontactway()+"%'");
		}
		if(CommonUtil.JudgeEmpty(cbxm.getResentry())){
			sql.append(" and a.resentry like '%"+cbxm.getResentry()+"%'");
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
	@RequestMapping(value="/updateresitemState")
	@ResponseBody
	public RetAjax updateresitemState(HttpServletRequest request,Tb_chubeixiangmu tb_chubeixiangmu,@RequestParam("resObj[]") String resObj) { 
		try {
			int flag = this.resitemservice.updateresitemState(resObj);
			if(flag!=0){
				flag=1;
			}
			result = RetAjax.onDataBase(flag,3);
		} catch (Exception e) {
			Log.error("error----------updateresitemState:" + e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 3);
		}
		return result; 
	} 
}
