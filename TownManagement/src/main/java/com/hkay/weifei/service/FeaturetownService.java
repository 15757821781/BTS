package com.hkay.weifei.service;

import java.util.List;

import com.hkay.weifei.pojo.Tb_tesexiaozhen;

public interface FeaturetownService {

	int insertfeaturetown(Tb_tesexiaozhen tb_tesexiaozhen);

	List<Tb_tesexiaozhen> queryfeaturetown(Tb_tesexiaozhen tb_tesexiaozhen);

	int queryfeaturetowncnt(Tb_tesexiaozhen tb_tesexiaozhen);

	List<Tb_tesexiaozhen> queryfeaturetowndetail(Tb_tesexiaozhen tb_tesexiaozhen);

	int updatefeaturetown(Tb_tesexiaozhen tb_tesexiaozhen);

}
