package com.hkay.weifei.task;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hkay.weifei.pojo.Tb_chubeixiangmu;
import com.hkay.weifei.pojo.Tb_notice;
import com.hkay.weifei.pojo.Tb_quyuxingxiangmu;
import com.hkay.weifei.service.ConditionService;
import com.hkay.weifei.service.RegionService;
import com.hkay.weifei.service.ResitemService;

@Component
public class NoticeTask {
	// 日志
	private static final Logger log = Logger.getLogger(NoticeTask.class);
	@Resource
	private ResitemService resitemservice;
	@Resource
	private ConditionService conditionService;
	@Resource
	private RegionService regionService;
	/**
	 * 
	 *方法名称:NoticeJob
	 *内容：每天1点执行一次
	 *创建人:zhuwenjie
	 *创建日期:2017年7月28日下午3:08:19
	 */
	@Scheduled(cron = "0 0 1 * * ?" )
	public void  NoticeJob() {
		log.debug("NoticeJob定时任务启动！");
		// 消息集合
		List<Tb_notice> notices = new ArrayList<Tb_notice>();
		// 查询储备项目符合提醒的数据
		List<Tb_chubeixiangmu> res = this.resitemservice.queryResForNotice();
		// 如果储备项目记录存在
		if(res!=null && res.size()>0){
			for(int i=0;i<res.size();i++){
				Tb_notice notice = new Tb_notice();
				// 创建者
				notice.setUser(res.get(i).getResentry());
				// 项目名称
				notice.setName(res.get(i).getResitemname());
				// 项目编号
				notice.setNumber(res.get(i).getResnumber());
				// 项目描述
				notice.setMessage("政府储备项目");
				// 项目类型
				notice.setType("7");
				notices.add(notice);
			}
		}
		// 查询区域性项目符合提醒的数据
		List<Tb_quyuxingxiangmu> reg = this.regionService.queryRegForNotice();
		// 如果区域性项目记录存在
		if(reg!=null && reg.size()>0){
			for(int i=0;i<reg.size();i++){
				Tb_notice notice = new Tb_notice();
				// 创建者
				notice.setUser(reg.get(i).getRegentry());
				// 项目名称
				notice.setName(reg.get(i).getRegnumber());
				// 项目编号
				notice.setNumber(reg.get(i).getRegname());
				// 项目描述
				notice.setMessage("区域性项目");
				// 项目类型
				notice.setType("5");
				notices.add(notice);
			}
		}
		//批量插入
		try {
			// 若存在消息
			if(notices!=null && notices.size()>0){
				int flag = this.conditionService.insertNotice(notices);
				if(flag!=0){
					log.debug("通知新增成功");
				}
			}else {
				log.debug("NoticeJob结束:无需要提醒的消息!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("NoticeJob失败!");
		}
	}
}
