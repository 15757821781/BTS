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
import com.hkay.weifei.pojo.Tb_xitonggonggao;
import com.hkay.weifei.pojo.Tb_zhengcefagui;
import com.hkay.weifei.service.SystemNoticeService;
import com.hkay.weifei.util.CommonUtil;
import com.hkay.weifei.util.FileUpload;
import com.hkay.weifei.util.PageUtil;
import com.hkay.weifei.util.RetAjax;

@Controller
@RequestMapping("/noticemanage")
public class SysnoticeController {
	private static Logger Log = Logger.getLogger(SysnoticeController.class);
	@Resource
	private SystemNoticeService systemNoticeService;
	private RetAjax result;
	private FileUpload fileupload = new FileUpload();
	
	/**
	 * 
		 * 方法名称: insertNocInfo
		 * 内容摘要: 新增系统公告信息
		 * 创建人：caixuyang	
		 * 创建日期： 2017年11月6日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/insertNocInfo")
	@ResponseBody
	public RetAjax insertnocinfo(HttpServletRequest request, Tb_xitonggonggao xitonggonggao) {
		try {
			HttpSession session = request.getSession();
			Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
			int flag = this.systemNoticeService.insertnocinfo(xitonggonggao);
			result = RetAjax.onDataBase(flag,1);
		} catch (Exception e) {
			Log.error("error----------insertnocinfo:" + e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 1);
		}
		return result;
	}
	/**
	 * 
		 * 方法名称: queryNocList
		 * 内容摘要: 查询系统公告列表
		 * 创建人：caixuyang
		 * 创建日期： 2017年11月6日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryNocList")
	@ResponseBody
	public RetAjax queryNocList(HttpServletRequest request,@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			Tb_xitonggonggao tb_xitonggonggao) throws UnsupportedEncodingException {
		HttpSession session = request.getSession();
		Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
		String number = user.getNumber();
		String userdata = user.getUserdata();
		Page<?> page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		//处理高级搜索
		tb_xitonggonggao.setSupersearch(GetSuperSearchSql(tb_xitonggonggao));
		List<Tb_xitonggonggao> tb_xitonggonggaos = this.systemNoticeService.queryNocList(tb_xitonggonggao);
		if(tb_xitonggonggaos!=null){
			for(int i=0;i<tb_xitonggonggaos.size();i++){
				if(userdata.equals("3")||number.equals(tb_xitonggonggaos.get(i).getNoccreator())){
					tb_xitonggonggaos.get(i).setOperation("<a href='javascript:void(0)' onclick='querydetail("+tb_xitonggonggaos.get(i).getNocid()+")'>查看</a>"+
							"&nbsp"+"<a href='javascript:void(0)' onclick='updateinfo("+tb_xitonggonggaos.get(i).getNocid()+")'>修改</a>");
				}else{
					tb_xitonggonggaos.get(i).setOperation("<a href='javascript:void(0)' onclick='querydetail("+tb_xitonggonggaos.get(i).getNocid()+")'>查看</a>");
				}
			}
		}
		int count = this.systemNoticeService.querynoccnt(tb_xitonggonggao);
		result = RetAjax.onGrid(tb_xitonggonggaos, count);
		return result;
	}
	
	/**
	 * 
	 *方法名称:
	 *内容：处理高级搜索内容
	 *创建人:caixuyang
	 *创建日期:2017年11月6日
	 */
	private String GetSuperSearchSql(Tb_xitonggonggao xtgg) {
		StringBuilder sql = new StringBuilder();
		if(CommonUtil.JudgeEmpty(xtgg.getSearch())){
			String search = xtgg.getSearch();
			sql.append(" and (a.noctitle like '%"+search+"%' or a.noctext like '%"+search+"%')");
		}
		if(CommonUtil.JudgeEmpty(xtgg.getNoctitle())){
			sql.append(" and a.noctitle like '%"+xtgg.getNoctitle()+"%'");
		}
		if(CommonUtil.JudgeEmpty(xtgg.getNoctext())){
			sql.append(" and a.noctext like '%"+xtgg.getNoctext()+"%'");
		}
		return sql.toString();
	}
	/**
	 * 
		 * 方法名称: queryNocDetail
		 * 内容摘要: 查询系统公告详细
		 * 创建人：caixuyang
		 * 创建日期： 2017年11月6日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryNocDetail")
	@ResponseBody
	public RetAjax queryNocDetail(HttpServletRequest request,Tb_xitonggonggao tb_xitonggonggao) {
		HttpSession session = request.getSession();
		Tb_user user=(Tb_user)session.getAttribute("town_LoginData");
		List<Tb_xitonggonggao> tb_xitonggonggaos = this.systemNoticeService.queryNocDetail(tb_xitonggonggao);
		result = RetAjax.onQueryDetail(tb_xitonggonggaos);
		return result;
	}
	
	
	/**
	 * 
		 * 方法名称: updateNocInfo
		 * 内容摘要: 更新政策法规信息
		 * 创建人：caixuyang
		 * 创建日期： 2017年11月6日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/updateNocInfo")
	@ResponseBody
	public RetAjax updateNocInfo(HttpServletRequest request,Tb_xitonggonggao tb_xitonggonggao) {
		try {
			HttpSession session = request.getSession();
			Tb_user user=(Tb_user)session.getAttribute("town_LoginData");
			tb_xitonggonggao.setNocupdator(user.getNumber());
			int flag = this.systemNoticeService.updateNocInfo(tb_xitonggonggao);
			result = RetAjax.onDataBase(flag, 3);
		} catch (Exception e) {
			Log.error("error-------------updateNocInfo:"+e.getMessage());
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
	@RequestMapping(value="/updateNocState")
	@ResponseBody
	public RetAjax updateNocState(HttpServletRequest request,Tb_xitonggonggao tb_xitonggonggao,@RequestParam("nocObj[]") String nocObj) { 
		try {
			int flag = this.systemNoticeService.updateNocState(nocObj);
			if(flag!=0){
				flag=1;
			}
			result = RetAjax.onDataBase(flag,3);
		} catch (Exception e) {
			Log.error("error----------updateNocState:" + e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 3);
		}
		return result; 
	} 
	
}
