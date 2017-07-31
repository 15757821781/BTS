package com.hkay.weifei.task;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hkay.weifei.pojo.Tb_chubeixiangmu;
import com.hkay.weifei.service.ResitemService;

@Component
public class NoticeTask {
	// 日志
	private static final Logger log = Logger.getLogger(NoticeTask.class);
	
	private ResitemService resitemservice;
	/**
	 * 
	 *方法名称:NoticeJob
	 *内容：每天钟执行一次
	 *创建人:zhuwenjie
	 *创建日期:2017年7月28日下午3:08:19
	 */
	@Scheduled(cron = "0 0/1 * * * ?" )
	public void  NoticeJob() {
		log.debug("NoticeJob定时任务启动！");
		List<Tb_chubeixiangmu> res = this.resitemservice.queryResForNotice();
	}
}
