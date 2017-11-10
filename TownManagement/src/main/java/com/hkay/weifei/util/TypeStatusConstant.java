package com.hkay.weifei.util;

/**
 * 状态标志和常量
 * 
 * @author zhu
 *
 */
public class TypeStatusConstant {
	/**
	 * 常用
	 */
	// 操作成功
	public static final String success = "success";
	// 操作失败
	public static final String fail = "fail";
	// 登出
	public static final String login_out = "loginout";
	// 登录信息丢失
	public static final String session_lost = "登录信息丢失";
	// 登录成功
	public static final String loginsuccess = "loginsuccess";
	public static final String login_success = "登录成功";
	// 登录失败
	public static final String login_fail = "帐号或密码错误";
	public static final String loginfail = "loginfail";

	/**
	 * 数据库相关
	 */
	// message
	public static final String insert_success="新增成功";
	public static final String insert_fail="新增失败";
	public static final String delete_success="删除成功";
	public static final String delete_fail="删除成功";
	public static final String update_success="更新成功";
	public static final String update_fail="更新失败";
	// state
	public static final String insertsuccess="insertsuccess";
	public static final String insertfail="insertfail";
	public static final String deletesuccess="deletesuccess";
	public static final String deletefail="deletefail";
	public static final String updatesuccess="updatesuccess";
	public static final String updatefail="updatefail";
	
	/**
	 * 文件上传
	 */
	/**
	 * 乡镇街道区位图
	 */
	public static final String town_statusmap="TownStatusMap/";
	/**
	 * 特色小镇区位图
	 */
	public static final String fea_statusmap="FeaStatusMap/";
	/**
	 * 乡镇街道规划图
	 */
	public static final String town_planmap="TownPlanMap/";
	/**
	 * 特色小镇规划图
	 */
	public static final String fea_planmap="FeaPlanMap/";
	/**
	 * 招商项目规划图
	 */
	public static final String inv_planmap="InvPlanMap/";
	/**
	 * /**
	 * 招商项目区位图
	 */
	public static final String inv_statusmap="InvStatusMap/";
	/**
	 * 区域性项目规划图
	 */
	public static final String reg_planmap="RegPlanMap/";
	/**
	 * 区域性项目区位图
	 */
	public static final String reg_statusmap="RegStatusMap/";
	/**
	 * 储备性项目规划图
	 */
	public static final String res_planmap="ResPlanMap/";
	/**
	 * 储备性项目区位图
	 */
	public static final String res_statusmap="ResStatusMap/";

	/**
	 * 证书
	 */
	public static final String proof="proof/";
	/**
	 * 文件
	 */
	public static final String file="file/";
	
}
