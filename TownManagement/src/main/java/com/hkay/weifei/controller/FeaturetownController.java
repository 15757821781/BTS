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
import com.hkay.weifei.util.CommonUtil;
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
	public RetAjax queryfeaturetown(HttpServletRequest request,@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			Tb_tesexiaozhen tb_tesexiaozhen) throws UnsupportedEncodingException {
		HttpSession session = request.getSession();
		Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
		String number = user.getNumber();
		String userdata = user.getUserdata();
		Page<?> page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		//处理高级搜索
		tb_tesexiaozhen.setSupersearch(GetSuperSearchSql(tb_tesexiaozhen));
		List<Tb_tesexiaozhen> tb_zhongxinzhens = this.featuretownservice.queryfeaturetown(tb_tesexiaozhen);
		if(tb_zhongxinzhens!=null){
			for(int i=0;i<tb_zhongxinzhens.size();i++){
				if(userdata.equals("3")||number.equals(tb_zhongxinzhens.get(i).getFeacreator())){
					tb_zhongxinzhens.get(i).setOperation("<a href='javascript:void(0)' onclick='queryDetail("+tb_zhongxinzhens.get(i).getFeaid()+")'>查看</a>"+
							"&nbsp"+"<a href='javascript:void(0)' onclick='updateInfo("+tb_zhongxinzhens.get(i).getFeaid()+")'>修改</a>");
				}else{
					tb_zhongxinzhens.get(i).setOperation("<a href='javascript:void(0)' onclick='queryDetail("+tb_zhongxinzhens.get(i).getFeaid()+")'>查看</a>");
				}
			}
		}
		int count = this.featuretownservice.queryfeaturetowncnt(tb_tesexiaozhen);
		result = RetAjax.onGrid(tb_zhongxinzhens, count);
		return result;
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
			tb_tesexiaozhens.get(0).setFeacontact("******");
			tb_tesexiaozhens.get(0).setFeapost("******");
			tb_tesexiaozhens.get(0).setFealeadname("******");
			tb_tesexiaozhens.get(0).setFeapartname("******");
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
			HttpSession session = request.getSession();
			Tb_user user=(Tb_user)session.getAttribute("town_LoginData");
			tb_tesexiaozhen.setFeaupdator(user.getNumber());
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
	
	/**
	 * 
	 *方法名称:
	 *内容：处理高级搜索内容
	 *创建人:caixuyang
	 *创建日期:2017年8月14日下午1:51:34
	 */
	public String GetSuperSearchSql(Tb_tesexiaozhen tsxz) {
		StringBuilder sql = new StringBuilder();
		if(CommonUtil.JudgeEmpty(tsxz.getSearch())){
			String search = tsxz.getSearch();
			sql.append(" and (a.feanumber like '%"+search+"%' or a.feaname like '%"+search+"%' or a.fealevel like '%"+search+"%')");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFeaname())){
			sql.append(" and a.feaname like '%"+tsxz.getFeaname()+"%'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFeanumber())){
			sql.append(" and a.feanumber like '%"+tsxz.getFeanumber()+"%'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFealevel())){
			sql.append(" and a.fealevel = '"+tsxz.getFealevel()+"'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFeabatch())){
			sql.append(" and a.feabatch = '"+tsxz.getFeabatch()+"'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFearelation())){
			sql.append(" and a.fearelation = '"+tsxz.getFearelation()+"'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFeaprovince())){
			sql.append(" and a.feaprovince = '"+tsxz.getFeaprovince()+"'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFeacity())){
			sql.append(" and a.feacity = '"+tsxz.getFeacity()+"'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFeatown())){
			sql.append(" and a.featown = '"+tsxz.getFeatown()+"'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFeagenre())){
			sql.append(" and a.feagenre like '%"+tsxz.getFeagenre()+"%'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFeaindustry())){
			sql.append(" and a.feaindustry = '"+tsxz.getFeaindustry()+"'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFeaplaninvests())){
			sql.append(CommonUtil.HandleNum("feaplaninvest", tsxz.getFeaplaninvests()));
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFeaplanareas())){
			sql.append(CommonUtil.HandleNum("feaplanarea", tsxz.getFeaplanareas()));
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFeapartmoneys())){
			sql.append(CommonUtil.HandleNum("feapartmoney", tsxz.getFeapartmoneys()));
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFeaschedule())){
			sql.append(" and a.feaschedule = '"+tsxz.getFeaschedule()+"'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFeatarget())){
			sql.append(" and a.featarget like '%"+tsxz.getFeatarget()+"%'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFeaplancontent())){
			sql.append(" and a.feaplancontent like '%"+tsxz.getFeaplancontent()+"%'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFeacoreindustry())){
			sql.append(" and a.feacoreindustry like '%"+tsxz.getFeacoreindustry()+"%'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFealeadcom())){
			sql.append(" and a.fealeadcom like '%"+tsxz.getFealeadcom()+"%'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFealeadname())){
			sql.append(" and a.fealeadname like '%"+tsxz.getFealeadname()+"%'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFealeadtel())){
			sql.append(" and a.fealeadtel like '%"+tsxz.getFealeadtel()+"%'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFeacooperate())){
			sql.append(" and a.feacooperate = "+tsxz.getFeacooperate()+"");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFeapartner())){
			sql.append(" and a.feapartner like '%"+tsxz.getFeapartner()+"%'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFeapartname())){
			sql.append(" and a.feapartname like '%"+tsxz.getFeapartname()+"%'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFeaparttel())){
			sql.append(" and a.feaparttel like '%"+tsxz.getFeaparttel()+"%'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFeapartway())){
			sql.append(" and a.feapartway like '%"+tsxz.getFeapartway()+"%'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFearegtime())){
			sql.append(" and a.fearegtime like '%"+tsxz.getFearegtime()+"%'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFeaendtime())){
			sql.append(" and a.feaendtime like '%"+tsxz.getFeaendtime()+"%'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFeapartconten())){
			sql.append(" and a.feapartconten like '%"+tsxz.getFeapartconten()+"%'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFeacontact())){
			sql.append(" and a.feacontact like '%"+tsxz.getFeacontact()+"%'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFeapost())){
			sql.append(" and a.feapost like '%"+tsxz.getFeapost()+"%'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFeacontacttel())){
			sql.append(" and a.feacontacttel like '%"+tsxz.getFeacontacttel()+"%'");
		}
		if(CommonUtil.JudgeEmpty(tsxz.getFeacreator())){
			sql.append(" and a.feacreator like '%"+tsxz.getFeacreator()+"%'");
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
	@RequestMapping(value="/updatefeatownState")
	@ResponseBody
	public RetAjax updatefeatownState(HttpServletRequest request,Tb_tesexiaozhen tb_tesexiaozhen,@RequestParam("featownObj[]") String featownObj) { 
		try {
			int flag = this.featuretownservice.updatefeatownState(featownObj);
			if(flag!=0){
				flag=1;
			}
			result = RetAjax.onDataBase(flag,3);
		} catch (Exception e) {
			Log.error("error----------updatefeatownState:" + e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 3);
		}
		return result; 
	} 
}
