package com.hkay.weifei.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hkay.weifei.pojo.Tb_chubeixiangmu;
import com.hkay.weifei.pojo.Tb_notice;
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
		// 查询储备项目符合提醒的数据
		List<Tb_chubeixiangmu> res = this.resitemservice.queryResForNotice();
		// 如果记录存在
		if(res!=null && res.size()>0){
			// 消息集合
			List<Tb_notice> notices = new ArrayList<Tb_notice>();
			for(int i=0;i<res.size();i++){
				Tb_notice notice = new Tb_notice();
				// 项目名称
				notice.setName(res.get(i).getResitemname());
				// 
				notice.setNumber(res.get(i).getResnumber());
				//
				notice.setMessage("政府储备项目");
				notices.add(notice);
			}
			//批量插入
			
		}else{
			log.debug("NoticeJob定时任务结束:无符合的记录！");
		}
	}
}
