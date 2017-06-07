package com.hkay.weifei.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkay.weifei.Dao.ConditionDao;
import com.hkay.weifei.pojo.Condition;
import com.hkay.weifei.service.ConditionService;

@Service("ConditionService")
public class ConditionServiceImpl implements ConditionService{

	@Resource
	private ConditionDao conditiondao;
	@Override
	public List<Condition> queryProvince() {
		// TODO Auto-generated method stub
		return this.conditiondao.queryProvince();
	}
	@Override
	public List<Condition> queryCity(Condition condition) {
		// TODO Auto-generated method stub
		return this.conditiondao.queryCity(condition);
	}
	@Override
	public List<Condition> queryTown(Condition condition) {
		// TODO Auto-generated method stub
		return this.conditiondao.queryTown(condition);
	}
	@Override
	public List<Condition> queryClimate() {
		// TODO Auto-generated method stub
		return this.conditiondao.queryClimate();
	}
	@Override
	public List<Condition> queryTerrain() {
		// TODO Auto-generated method stub
		return this.conditiondao.queryTerrain();
	}
	@Override
	public List<Condition> queryAdvIndustry() {
		// TODO Auto-generated method stub
		return this.conditiondao.queryAdvIndustry();
	}
	@Override
	public List<Condition> queryDirIndustry() {
		// TODO Auto-generated method stub
		return this.conditiondao.queryDirIndustry();
	}
	@Override
	public int updatePic(Condition condition) {
		// TODO Auto-generated method stub
		return this.conditiondao.updatePic(condition);
	}
	@Override
	public List<Condition> queryMajorIndustry() {
		// TODO Auto-generated method stub
		return this.conditiondao.queryMajorIndustry();
	}
	@Override
	public List<Condition> queryDevelopDir() {
		// TODO Auto-generated method stub
		return this.conditiondao.queryDevelopDir();
	}
	@Override
	public List<Condition> queryBusinessDir() {
		// TODO Auto-generated method stub
		return this.conditiondao.queryBusinessDir();
	}
	@Override
	public List<Condition> queryAdvantage() {
		// TODO Auto-generated method stub
		return this.conditiondao.queryAdvantage();
	}
	@Override
	public Condition queryPicValue(Condition condition) {
		// TODO Auto-generated method stub
		return this.conditiondao.queryPicValue(condition);
	}
	
}
