package com.hkay.weifei.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hkay.weifei.pojo.Condition;
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
				String key = condition.getKey()+",";
				String value = condition.getValue();
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
}
