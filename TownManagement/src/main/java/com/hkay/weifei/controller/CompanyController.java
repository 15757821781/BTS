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
import com.hkay.weifei.util.CommonUtil;
import com.hkay.weifei.util.FileUpload;
//import com.hkay.weifei.util.ImportExcel;
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
	public Map<String,Object> queryComList(HttpServletRequest request,@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			Tb_qiyedanwei tb_qiyedanwei) throws UnsupportedEncodingException {
		Map<String,Object> map = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
		String number = user.getNumber();
		String userdata = user.getUserdata();
		Page<?> page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		//处理高级搜索
		tb_qiyedanwei.setSupersearch(GetSuperSearchSql(tb_qiyedanwei));
		List<Tb_qiyedanwei> tb_qiyedanweis = this.companyService.queryComList(tb_qiyedanwei);
		if(tb_qiyedanweis!=null){
			for(int i=0;i<tb_qiyedanweis.size();i++){
				if(userdata.equals("3")||number.equals(tb_qiyedanweis.get(i).getComcreator())){
					tb_qiyedanweis.get(i).setOperation("<a href='javascript:void(0)' onclick='querydetail("+tb_qiyedanweis.get(i).getComid()+")'>查看</a>"+
							"&nbsp"+"<a href='javascript:void(0)' onclick='updateinfo("+tb_qiyedanweis.get(i).getComid()+")'>修改</a>");
				}else{
					tb_qiyedanweis.get(i).setOperation("<a href='javascript:void(0)' onclick='querydetail("+tb_qiyedanweis.get(i).getComid()+")'>查看</a>");
				}
			}
		}
		int count = this.companyService.querycomcnt(tb_qiyedanwei);
		map.put("rows", tb_qiyedanweis);
		map.put("total", count);
		return map;
	}
	/**
	 * 
	 *方法名称:
	 *内容：处理高级搜索内容
	 *创建人:caixuyang
	 *创建日期:2017年8月14日下午4:14:34
	 */
	
	public String GetSuperSearchSql(Tb_qiyedanwei qydw) {
		StringBuilder sql = new StringBuilder();
		if(CommonUtil.JudgeEmpty(qydw.getSearch())){
			String search = qydw.getSearch();
			sql.append(" and (a.comnumber like '%"+search+"%' or a.comname like '%"+search+"%' or a.comcategory like '%"+search+"%')");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComname())){
			sql.append(" and a.comname like '%"+qydw.getComname()+"%'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComnumber())){
			sql.append(" and a.comnumber like '%"+qydw.getComnumber()+"%'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComcategory())){
			sql.append(" and a.comcategory = '"+qydw.getComcategory()+"'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComtype())){
			sql.append(" and a.comtype = '"+qydw.getComtype()+"'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getCombustype())){
			sql.append(" and a.combustype = '"+qydw.getCombustype()+"'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComlisted())){
			sql.append(" and a.comlisted = '"+qydw.getComlisted()+"'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComstockcode())){
			sql.append(" and a.comstockcode like '%"+qydw.getComstockcode()+"%'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComrelation())){
			sql.append(" and a.comrelation = '"+qydw.getComrelation()+"'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComworldfive())){
			sql.append(" and a.comworldfive = '"+qydw.getComworldfive()+"'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComcountryfive())){
			sql.append(" and a.comcountryfive = '"+qydw.getComcountryfive()+"'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComprivatefive())){
			sql.append(" and a.comprivatefive = '"+qydw.getComprivatefive()+"'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComrepresent())){
			sql.append(" and a.comrepresent like '%"+qydw.getComrepresent()+"%'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComcapitals())){
			sql.append(CommonUtil.HandleNum("comcapital", qydw.getComcapitals()));
		}
		if(CommonUtil.JudgeEmpty(qydw.getComcapitalunit())){
			sql.append(" and a.comcapitalunit = '"+qydw.getComcapitalunit()+"'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComestablish())){
			sql.append(" and DATE_FORMAT(a.comestablish,'%Y-%m-%d') = '"+qydw.getComestablish()+"'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComprovince())){
			sql.append(" and a.comprovince = '"+qydw.getComprovince()+"'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComcity())){
			sql.append(" and a.comcity = '"+qydw.getComcity()+"'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComtown())){
			sql.append(" and a.comtown = '"+qydw.getComtown()+"'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComaddress())){
			sql.append(" and a.comaddress like '%"+qydw.getComaddress()+"%'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComoffice())){
			sql.append(" and a.comoffice like '%"+qydw.getComoffice()+"%'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComshareholder())){
			sql.append(" and a.comshareholder like '%"+qydw.getComshareholder()+"%'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComindustrytype())){
			sql.append(" and a.comindustrytype in ('"+qydw.getComindustrytype()+"')");
		}
		if(CommonUtil.JudgeEmpty(qydw.getCommajorindustry())){
			sql.append(" and a.commajorindustry in ('"+qydw.getCommajorindustry()+"')");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComscope())){
			sql.append(" and a.comscope = '"+qydw.getComscope()+"'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComabstract())){
			sql.append(" and a.comabstract = '"+qydw.getComabstract()+"'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComhonor())){
			sql.append(" and a.comhonor = '"+qydw.getComhonor()+"'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComdatayear())){
			sql.append(" and a.comdatayear = '"+qydw.getComdatayear()+"'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComlassetss())){
			sql.append(CommonUtil.HandleNum("comlassets", qydw.getComlassetss()));
		}
		if(CommonUtil.JudgeEmpty(qydw.getComlassetsunit())){
			sql.append(" and a.comlassetsunit = '"+qydw.getComlassetsunit()+"'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComliabilitiess())){
			sql.append(CommonUtil.HandleNum("comliabilities", qydw.getComliabilitiess()));
		}
		if(CommonUtil.JudgeEmpty(qydw.getComliabunit())){
			sql.append(" and a.comliabunit = '"+qydw.getComliabunit()+"'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComincomeyears())){
			sql.append(CommonUtil.HandleNum("comincomeyear", qydw.getComincomeyears()));
		}
		if(CommonUtil.JudgeEmpty(qydw.getCominyearunit())){
			sql.append(" and a.cominyearunit = '"+qydw.getCominyearunit()+"'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComnetprofiyears())){
			sql.append(CommonUtil.HandleNum("comnetprofiyear", qydw.getComnetprofiyears()));
		}
		if(CommonUtil.JudgeEmpty(qydw.getComnetyearunit())){
			sql.append(" and a.comnetyearunit = '"+qydw.getComnetyearunit()+"'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComtaxesyears())){
			sql.append(CommonUtil.HandleNum("comtaxesyear", qydw.getComtaxesyears()));
		}
		if(CommonUtil.JudgeEmpty(qydw.getComtaxyearunit())){
			sql.append(" and a.comtaxyearunit = '"+qydw.getComtaxyearunit()+"'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComdevelop())){
			sql.append(" and a.comdevelop in ('"+qydw.getComdevelop()+"')");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComindustry())){
			sql.append(" and a.comindustry in ('"+qydw.getComindustry()+"')");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComregpro())){
			sql.append(" and a.comregpro = '"+qydw.getComregpro()+"'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComregcity())){
			sql.append(" and a.comregcity = '"+qydw.getComregcity()+"'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getCominvestment())){
			sql.append(" and a.cominvestment in ('"+qydw.getCominvestment()+"')");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComcontact())){
			sql.append(" and a.comcontact like '%"+qydw.getComcontact()+"%'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getCompost())){
			sql.append(" and a.compost like '%"+qydw.getCompost()+"%'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComcontacttel())){
			sql.append(" and a.comcontacttel in ('"+qydw.getComcontacttel()+"')");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComofficeweb())){
			sql.append(" and a.comofficeweb like '%"+qydw.getComofficeweb()+"%'");
		}
		if(CommonUtil.JudgeEmpty(qydw.getComcreator())){
			sql.append(" and a.comcreator like '%"+qydw.getComcreator()+"%'");
		}
		return sql.toString();
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
	public RetAjax queryComDetail(HttpServletRequest request,Tb_qiyedanwei tb_qiyedanwei) throws UnsupportedEncodingException{
		HttpSession session = request.getSession();
		Tb_user user=(Tb_user)session.getAttribute("town_LoginData");
		List<Tb_qiyedanwei> tb_qiyedanweis = this.companyService.queryComDetail(tb_qiyedanwei);
		if(user.getUserdata().equals("1")&&tb_qiyedanweis!=null){
			tb_qiyedanweis.get(0).setComcontact("******");
			tb_qiyedanweis.get(0).setCompost("******");
			tb_qiyedanweis.get(0).setComcontacttel("******");
		}
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
			HttpSession session = request.getSession();
			Tb_user user=(Tb_user)session.getAttribute("town_LoginData");
			tb_qiyedanwei.setComupdator(user.getNumber());
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
	
	@RequestMapping("/importComInfo")
	@ResponseBody
	public RetAjax importComInfo(HttpServletRequest request,@RequestParam("comFile") MultipartFile[] files) {
		try {
//			String imgpath=fileupload.fileUpload(files, request,TypeStatusConstant.proof,tb_qiyedanwei.getComcertificate());
//			tb_qiyedanwei.setComcertificate(imgpath);
//			int flag = this.companyService.updateComInfo(tb_qiyedanwei);
//			result = RetAjax.onDataBase(flag, 3);
//			ImportExcel.getImportStream(request, files, companyService);
		} catch (Exception e) {
			Log.error("error-------------importComInfo:"+e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 3);
		}
		return result;
	}
	
	
}
