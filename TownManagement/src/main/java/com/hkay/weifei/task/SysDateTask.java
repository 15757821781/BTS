package com.hkay.weifei.task;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hkay.weifei.service.ConditionService;

@Component
public class SysDateTask {
	// 日志
	private static final Logger log = Logger.getLogger(NoticeTask.class);
	@Resource
	private ConditionService conditionService;
	/**
	 * 
	 *方法名称:NoticeJob
	 *内容：每5分钟执行一次
	 *创建人:zhuwenjie
	 *创建日期:2017年7月28日下午3:08:19
	 */
	@Scheduled(cron = "0 0/5 * * * ?" )
	public void  SysDateJob() {
		log.debug("SysDateJob定时任务启动！");
		//批量插入
		try {
			String time = this.conditionService.querySysTime();
			System.out.println("系统时间："+time);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SysDateJob失败!");
		}
	}
}
