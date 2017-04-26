package com.hkay.weifei.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.hkay.weifei.pojo.Tb_user;
import com.hkay.weifei.util.RetAjax;
import com.hkay.weifei.util.SysContent;

@Component
@Aspect
public class SessionAspect {

	/**
	 * 
		 * 方法名称: DealSession
		 * 内容摘要: 处理session会话
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年4月17日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 * @throws Throwable 
	 */
	@Around("execution(* com.hkay.weifei.controller.*.*(..)) and !execution(* com.hkay.weifei.controller.LoginController.*(..))")
	public Object DealSession(ProceedingJoinPoint pj) throws Throwable {
        try {
        	HttpServletRequest request = SysContent.getRequest();
        	// 获得session
        	HttpSession session = request.getSession();
			// 判断用户session
			if (session != null) {
				Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
				if(user!=null){
					return pj.proceed();
				}
			} 
			// session过期，返回超时信息
			System.out.println("session过期，返回超时信息");
			return RetAjax.lostLoginInfo();
		} catch (Exception e) {
			System.out.println("DealSession:"+e.getMessage());
			e.printStackTrace();
			return RetAjax.lostLoginInfo();
		}
	}
}
