package com.hkay.weifei.aop;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import com.hkay.weifei.model.Tb_user;
import com.hkay.weifei.util.CommonUtils;
import com.hkay.weifei.service.SjglService;
import com.hkay.weifei.service.XtglService;

@Component
@Aspect
public class LogAspect {

	@Resource
	private XtglService xtglService;

	@Resource
	private SjglService sjglService;
	/**
	 * 
		 * 
		 * 内容摘要: type: 1.登入，2.新增，3.更新，4.删除，5.登出
		 * 创建人：zhuwenjie
		 * 创建日期： 2016年7月13日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */

	// 日志管理-更新操作
	@Around("execution(* com.hkay.weifei.service.*.update*(..))")
	public Object addUpdateLog(ProceedingJoinPoint jp) {
		Object o = null;
//		Object[] parames = jp.getArgs();// 获取目标方法体参数
		// 获取方法名
		String methodName = jp.getSignature().getName();
//		MethodSignature signature = (MethodSignature) jp.getSignature();
//		 获取方法
		String methodClass = jp.getTarget().getClass().getSimpleName();
		HashMap<String, String> logHashMap = new HashMap<String, String>();
		try {
			try {
				o = jp.proceed();
				if (o.toString().equals("1")) {
					logHashMap.put("result", "成功");
				}
			} catch (Throwable e) {
				logHashMap.put("result", "失败");
				logHashMap.put("describe", e.toString());
			}
			Tb_user user = CommonUtils.queryLoginData();
			if (user == null) {
				user = new Tb_user();
			}
			logHashMap.put("type", "3");
			logHashMap.put("operator", user.getYhm());
			logHashMap.put("object", methodName);
			logHashMap.put("objectclass", methodClass);
			sjglService.loginsAdjunctionLog(logHashMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	// 日志管理-新增操作
	@Around("execution(* com.hkay.weifei.service.*.insert*(..)) or execution(* com.hkay.weifei.service.*.add*(..))")
	public Object addInsertLog(ProceedingJoinPoint jp) {
		Object o = null;
//		Object[] parames = jp.getArgs();// 获取目标方法体参数
		// 获取方法名
		String methodName = jp.getSignature().getName();
//		 获取方法
		String methodClass = jp.getTarget().getClass().getSimpleName();
		try {
			HashMap<String, String> logHashMap = new HashMap<String, String>();
			Tb_user user = CommonUtils.queryLoginData();
			if (user == null) {
				user = new Tb_user();
			}
			logHashMap.put("type", "2");
			logHashMap.put("operator", user.getYhm());
			logHashMap.put("object", methodName);
			logHashMap.put("objectclass", methodClass);
			try {
				o = jp.proceed();
				if (o.toString().equals("1")) {
					logHashMap.put("result", "成功");
				}
			} catch (Throwable e) {
				logHashMap.put("result", "失败");
				logHashMap.put("describe", e.toString());
			}
			sjglService.loginsAdjunctionLog(logHashMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	// 日志管理-删除操作
	@Around("execution(* com.hkay.weifei.service.*.delete*(..))")
	public Object addDeleteLog(ProceedingJoinPoint jp) {
		Object o = null;
//		Object[] parames = jp.getArgs();// 获取目标方法体参数
		// 获取方法名
		String methodName = jp.getSignature().getName();
//		 获取方法
		String methodClass = jp.getTarget().getClass().getSimpleName();
		try {
			HashMap<String, String> logHashMap = new HashMap<String, String>();
			Tb_user user = CommonUtils.queryLoginData();
			if (user == null) {
				user = new Tb_user();
			}
			logHashMap.put("type", "4");
			logHashMap.put("operator", user.getYhm());
			logHashMap.put("object", methodName);
			logHashMap.put("objectclass", methodClass);
			try {
				o = jp.proceed();
				if (o.toString().equals("1")) {
					logHashMap.put("result", "成功");
				}
			} catch (Throwable e) {
				logHashMap.put("result", "失败");
				logHashMap.put("describe", e.toString());
			}
			sjglService.loginsAdjunctionLog(logHashMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	// 日志管理-登陆操作
	@Around("execution(* com.hkay.weifei.service.LoginTypeService.login*(..))")
	public Object addLoginLog(ProceedingJoinPoint jp) {
		Object o = null;
//		Object[] parames = jp.getArgs();// 获取目标方法体参数
		// 获取方法名
		String methodName = jp.getSignature().getName();
//		 获取方法
		String methodClass = jp.getTarget().getClass().getSimpleName();
		HashMap<String, String> logHashMap = new HashMap<String, String>();
		Map<String, Object> userMap = null;
		try {
			try {
				o = jp.proceed();
				userMap = (Map<String, Object>) o;
				if (userMap.get("isRecorded").toString().equals("1")) {
					logHashMap.put("result", "成功");
				}
			} catch (Throwable e) {
				logHashMap.put("result", "失败");
				logHashMap.put("describe", e.toString());
			}
			Tb_user user = CommonUtils.queryLoginData();
			if (user == null) {
				user = new Tb_user();
			}
			logHashMap.put("type", "1");
			logHashMap.put("operator", ((Tb_user) userMap.get("user")).getYhm());
			logHashMap.put("object", methodName);
			logHashMap.put("objectclass", methodClass);
			sjglService.loginsAdjunctionLog(logHashMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	// 日志管理-登出操作
	@Around("execution(* com.hkay.weifei.action.UserLoginAction.toLogout*(..))")
	public Object addSignOutLog(ProceedingJoinPoint jp) {
		Object o = null;
//		Object[] parames = jp.getArgs();// 获取目标方法体参数
		// 获取方法名
		String methodName = jp.getSignature().getName();
//		 获取方法
		String methodClass = jp.getTarget().getClass().getSimpleName();
		HashMap<String, String> logHashMap = new HashMap<String, String>();
		try {
			Tb_user user = CommonUtils.queryLoginData();
			if (user == null) {
				user = new Tb_user();
			}
			logHashMap.put("type", "5");
			logHashMap.put("operator", user.getYhm());
			logHashMap.put("object", methodName);
			logHashMap.put("objectclass", methodClass);
			try {
				o = jp.proceed();
				if (o.toString().equals("success")) {
					logHashMap.put("result", "成功");
				}
			} catch (Throwable e) {
				logHashMap.put("result", "失败");
				logHashMap.put("describe", e.toString());
			}
			sjglService.loginsAdjunctionLog(logHashMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}
}
