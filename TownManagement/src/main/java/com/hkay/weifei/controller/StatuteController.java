package com.hkay.weifei.controller;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hkay.weifei.pojo.Tb_user;
import com.hkay.weifei.pojo.Tb_zhengcefagui;
import com.hkay.weifei.service.StatuteService;
import com.hkay.weifei.util.CommonUtil;
import com.hkay.weifei.util.FileUpload;
import com.hkay.weifei.util.PageUtil;
import com.hkay.weifei.util.RetAjax;
import com.hkay.weifei.util.TypeStatusConstant;

@Controller
@RequestMapping("/statutemanage")
public class StatuteController {
	private static Logger Log = Logger.getLogger(StatuteController.class);
	@Resource
	private StatuteService statuteservice;
	private RetAjax result;
	private FileUpload fileupload = new FileUpload();
	/**
	 * 
		 * 方法名称: insertStaInfo
		 * 内容摘要: 新增政法正规信息
		 * 创建人：caixuyang	
		 * 创建日期： 2017年10月25日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/insertStaInfo")
	@ResponseBody
	public RetAjax insertstainfo(HttpServletRequest request, Tb_zhengcefagui zhengcefagui) {
		try {
			HttpSession session = request.getSession();
			Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
//			String imgpath = fileupload.fileUploadForCn(files, request, TypeStatusConstant.file, "");
			zhengcefagui.setStacreator(user.getNumber());
			int flag = this.statuteservice.insertstainfo(zhengcefagui);
			result = RetAjax.onDataBase(flag,1);
		} catch (Exception e) {
			Log.error("error----------inserstainfo:" + e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 1);
		}
		return result;
	}
	/**
	 * 
		 * 方法名称: queryStaList
		 * 内容摘要: 查询政策法规列表
		 * 创建人：caixuyang
		 * 创建日期： 2017年11月1日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryStaList")
	@ResponseBody
	public RetAjax queryStaList(HttpServletRequest request,@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			Tb_zhengcefagui tb_zhengcefagui) throws UnsupportedEncodingException {
		HttpSession session = request.getSession();
		Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
		String number = user.getNumber();
		String userdata = user.getUserdata();
		Page<?> page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		//处理高级搜索
		tb_zhengcefagui.setSupersearch(GetSuperSearchSql(tb_zhengcefagui));
		List<Tb_zhengcefagui> tb_zhengcefaguis = this.statuteservice.queryStaList(tb_zhengcefagui);
		if(tb_zhengcefaguis!=null){
			for(int i=0;i<tb_zhengcefaguis.size();i++){
				if(userdata.equals("3")||number.equals(tb_zhengcefaguis.get(i).getStacreator())){
					tb_zhengcefaguis.get(i).setOperation("<a href='javascript:void(0)' onclick='querydetail("+tb_zhengcefaguis.get(i).getStaid()+")'>查看</a>"+
							"&nbsp"+"<a href='javascript:void(0)' onclick='updateinfo("+tb_zhengcefaguis.get(i).getStaid()+")'>修改</a>");
				}else{
					tb_zhengcefaguis.get(i).setOperation("<a href='javascript:void(0)' onclick='querydetail("+tb_zhengcefaguis.get(i).getStaid()+")'>查看</a>");
				}
			}
		}
		int count = this.statuteservice.querystacnt(tb_zhengcefagui);
		result = RetAjax.onGrid(tb_zhengcefaguis, count);
		return result;
	}
	/**
	 * 
	 *方法名称:
	 *内容：处理高级搜索内容
	 *创建人:caixuyang
	 *创建日期:2017年11月2日
	 */
	private String GetSuperSearchSql(Tb_zhengcefagui zcfg) {
		StringBuilder sql = new StringBuilder();
		if(CommonUtil.JudgeEmpty(zcfg.getSearch())){
			String search = zcfg.getSearch();
			sql.append(" and (a.statitle like '%"+search+"%' or a.stanumber like '%"+search+"%' or a.statheme like '%"+search+"%')");
		}
		if(CommonUtil.JudgeEmpty(zcfg.getStatitle())){
			sql.append(" and a.statitle like '%"+zcfg.getStatitle()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zcfg.getStanumber())){
			sql.append(" and a.stanumber like '%"+zcfg.getStanumber()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zcfg.getStatheme())){
			sql.append(" and a.statheme = '"+zcfg.getStatheme()+"'");
		}
		if(CommonUtil.JudgeEmpty(zcfg.getStatext())){
			sql.append(" and a.statext like '%"+zcfg.getStatext()+"%'");
		}
		if(CommonUtil.JudgeEmpty(zcfg.getStainscribe())){
			sql.append(" and a.stainscribe like '%"+zcfg.getStainscribe()+"%'");
		}
		return sql.toString();
	}

	/**
	 * 
		 * 方法名称: queryStaDetail
		 * 内容摘要: 查询政策法规详细
		 * 创建人：caixuyang
		 * 创建日期： 2017年11月1日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryStaDetail")
	@ResponseBody
	public RetAjax queryStaDetail(HttpServletRequest request,Tb_zhengcefagui tb_zhengcefagui) {
		List<Tb_zhengcefagui> tb_zhengcefaguis = this.statuteservice.queryStaDetail(tb_zhengcefagui);
		result = RetAjax.onQueryDetail(tb_zhengcefaguis);
		return result;
	}
	
	/**
	 * 
		 * 方法名称: updateStaInfo
		 * 内容摘要: 更新政策法规信息
		 * 创建人：caixuyang
		 * 创建日期： 2017年11月2日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/updateStaInfo")
	@ResponseBody
	public RetAjax updateStaInfo(HttpServletRequest request,Tb_zhengcefagui tb_zhengcefagui) {
		try {
			HttpSession session = request.getSession();
			Tb_user user=(Tb_user)session.getAttribute("town_LoginData");
			tb_zhengcefagui.setStaupdator(user.getNumber());
			int flag = this.statuteservice.updateStaInfo(tb_zhengcefagui);
			result = RetAjax.onDataBase(flag, 3);
		} catch (Exception e) {
			Log.error("error-------------updateStaInfo:"+e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 3);
		}
		return result;
	}
	/**
	 * 删除信息时更新信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/updateStaState")
	@ResponseBody
	public RetAjax updateStaState(HttpServletRequest request,Tb_zhengcefagui Tb_zhengcefagui,@RequestParam("staObj[]") String staObj) { 
		try {
			int flag = this.statuteservice.updateStaState(staObj);
			if(flag!=0){
				flag=1;
			}
			result = RetAjax.onDataBase(flag,3);
		} catch (Exception e) {
			Log.error("error----------updateStaState:" + e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 3);
		}
		return result; 
	}
}
