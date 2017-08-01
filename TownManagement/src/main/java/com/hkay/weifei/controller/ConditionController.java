package com.hkay.weifei.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hkay.weifei.pojo.Condition;
import com.hkay.weifei.pojo.Tb_notice;
import com.hkay.weifei.pojo.Tb_user;
import com.hkay.weifei.service.ConditionService;
import com.hkay.weifei.util.RetAjax;

@Controller
@RequestMapping("/conditionmanage")
public class ConditionController {
	@Resource
	private ConditionService conditionservice;
	private RetAjax result;
	private static Logger Log = Logger.getLogger(ConditionController.class);

	/**
	 * 
		 * 方法名称: queryProvince
		 * 内容摘要: 查询省份信息
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年4月25日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryProvince")
	@ResponseBody
	public RetAjax queryProvince(HttpServletRequest request) {
		List<Condition> conditions = this.conditionservice.queryProvince();
		result = RetAjax.onSuccess(conditions, "");
		return result;
	}
	
	/**
	 * 
		 * 方法名称: queryCity
		 * 内容摘要: 查询城市信息
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年4月25日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryCity")
	@ResponseBody
	public RetAjax queryCity(HttpServletRequest request, Condition condition) {
		List<Condition> conditions = this.conditionservice.queryCity(condition);
		result = RetAjax.onSuccess(conditions, "");
		return result;
	}
	
	/**
	 * 
		 * 方法名称: queryTown
		 * 内容摘要: 查询乡县信息
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年4月25日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryTown")
	@ResponseBody
	public RetAjax queryTown(HttpServletRequest request, Condition condition) {
		List<Condition> conditions = this.conditionservice.queryTown(condition);
		result = RetAjax.onSuccess(conditions, "");
		return result;
	}
	
	/**
	 * 
		 * 方法名称: queryClimate
		 * 内容摘要: 查询气候信息
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年4月26日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryClimate")
	@ResponseBody
	public RetAjax queryClimate(HttpServletRequest request, Condition condition) {
		List<Condition> conditions = this.conditionservice.queryClimate();
		result = RetAjax.onSuccess(conditions, "");
		return result;
	}
	
	/**
	 * 
		 * 方法名称: queryTerrain
		 * 内容摘要: 查询地形信息
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年4月26日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryTerrain")
	@ResponseBody
	public RetAjax queryTerrain(HttpServletRequest request, Condition condition) {
		List<Condition> conditions = this.conditionservice.queryTerrain();
		result = RetAjax.onSuccess(conditions, "");
		return result;
	}
	
	/**
	 * 
		 * 方法名称: queryAdvIndustry
		 * 内容摘要: 查询优势企业
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年4月26日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryAdvIndustry")
	@ResponseBody
	public RetAjax queryAdvIndustry(HttpServletRequest request, Condition condition) {
		List<Condition> conditions = this.conditionservice.queryAdvIndustry();
		result = RetAjax.onSuccess(conditions, "");
		return result;
	}
	
	/**
	 * 
		 * 方法名称: queryDirIndustry
		 * 内容摘要: 查询产业方向
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年4月26日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryDirIndustry")
	@ResponseBody
	public RetAjax queryDirIndustry(HttpServletRequest request, Condition condition) {
		List<Condition> conditions = this.conditionservice.queryDirIndustry();
		result = RetAjax.onSuccess(conditions, "");
		return result;
	}
	
	/**
	 * 
		 * 方法名称: queryMajorIndustry
		 * 内容摘要: 查询主要产业信息
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年5月2日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryMajorIndustry")
	@ResponseBody
	public RetAjax queryMajorIndustry(HttpServletRequest request, Condition condition) {
		List<Condition> conditions = this.conditionservice.queryMajorIndustry();
		result = RetAjax.onSuccess(conditions, "");
		return result;
	}
	
	/**
	 * 
		 * 方法名称: queryDevelopDir
		 * 内容摘要: 查询发展方向
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年5月3日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryDevelopDir")
	@ResponseBody
	public RetAjax queryDevelopDir(HttpServletRequest request) {
		List<Condition> conditions = this.conditionservice.queryDevelopDir();
		result = RetAjax.onSuccess(conditions, "");
		return result;
	}
	/**
	 * 
		 * 方法名称: queryAdvantage
		 * 内容摘要: 机构优势查询
		 * 创建人：caixuyang
		 * 创建日期： 2017年5月10日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryAdvantage")
	@ResponseBody
	public RetAjax queryAdvantage(HttpServletRequest request) {
		List<Condition> conditions = this.conditionservice.queryAdvantage();
		result = RetAjax.onSuccess(conditions, "");
		return result;
	}
	
	/**
	 * 
		 * 方法名称: queryBusinessDir
		 * 内容摘要: 查询行业方向信息
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年5月3日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryBusinessDir")
	@ResponseBody
	public RetAjax queryBusinessDir(HttpServletRequest request) {
		List<Condition> conditions = this.conditionservice.queryBusinessDir();
		result = RetAjax.onSuccess(conditions, "");
		return result;
	}
	
	/**
	 * 删除图片
	 * @param request
	 * @param picname
	 * @param extra
	 * @return
	 */
	@RequestMapping("/deletePic")
	@ResponseBody
	public RetAjax deletePic(HttpServletRequest request,Condition condition) {
		try {
			if(!condition.getTbname().equals("")&&condition.getId()!=0){
				Condition cond = this.conditionservice.queryPicValue(condition);
				String key = condition.getKey()+",";
				String value = cond.getValue();
				value = value.replace(key, "");
				condition.setValue(value);
				int flag = this.conditionservice.updatePic(condition);
				if(flag==1){
					result = RetAjax.onSuccess(value, "删除成功");
					System.out.println(condition.getValue());
				}
			}
		} catch (Exception e) {
			Log.error("图片删除失败---deletePic:"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 *方法名称:queryNotice
	 *内容：查询通知
	 *创建人:zhuwenjie
	 *创建日期:2017年7月31日下午5:40:17
	 */
	@RequestMapping("/queryNotice")
	@ResponseBody
	public RetAjax queryNotice(HttpServletRequest request){
		HttpSession session = request.getSession();
		Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
		List<Tb_notice> notices = this.conditionservice.queryNotice(user);
		StringBuilder html = new StringBuilder();
		if(notices!=null&&notices.size()>0){
			for(int i =0;i<notices.size();i++){
				html.append(" <li><a id=sysalertli_"+notices.get(i).getId()+" name="+notices.get(i).getName());
				html.append(" message="+notices.get(i).getMessage());
				html.append(" number="+notices.get(i).getNumber());
				if(notices.get(i).getState().equals("1")){
					html.append(" state=已读");
				}else{
					html.append(" state=未读");
				}
				html.append(" onclick='showAlertDetail(this)'>");
				html.append(" <i class='fa fa-tasks fa-fw'></i>");
				html.append(notices.get(i).getMessage());
				html.append(" <span class='pull-right text-muted small'>"+notices.get(i).getName()+"</span>");
				html.append(" </a></li><li class='divider'></li>");
			}
		}else{
			html.append("<li><a><i class='fa fa-comment fa-fw'></i>暂无通知</a></li><li class='divider'></li>");
		}
//		html.append("<li><a class='text-center'><strong>See All Alerts</strong><i class='fa fa-angle-right'></i></a></li>");
		result = RetAjax.onSuccess(html, null);
		return result;
	}
	
	/**
	 * 
	 *方法名称:updateNoticeState
	 *内容：更新通知状态
	 *创建人:zhuwenjie
	 *创建日期:2017年8月1日下午4:31:34
	 */
	@RequestMapping("/updateNoticeState")
	@ResponseBody
	public RetAjax updateNoticeState(Tb_notice notice){
		try {
			int flag = this.conditionservice.updateNoticeState(notice);
			result = RetAjax.onSuccess(flag, null);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("updateNoticeState失败!"+e.getMessage());
		}
		return result;
	}
}
