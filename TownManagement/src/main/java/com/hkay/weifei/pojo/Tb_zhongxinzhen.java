package com.hkay.weifei.pojo;

public class Tb_zhongxinzhen {
	private String centertownid;// 中心镇id
	private String centertownname;// 中心镇名称
	private String number;// 编号
	private String citypilot;// 是否为小城市试点
	private String townlevel;// 中心镇等级
//	private String townfeature;//是否为特色小镇
	private String sys_province;//省
	private String sys_city;//市
	private String sys_town;//县
	private String cooperation;// 关系情况
	private String towndatayear;// 数据年度
	private String hundredcounties;// 是否为百强区县
	private Double countygdp;// 区县级GDP
	private Double countyrevenue;// 区县级财政收入
	private Double townpopulation;// 所在区县总人口
	private Double townpgdi;// 区县人均可支配收入
	private Double townarea;//行政面积
	private Integer community;// 社区数量
	private Integer adminvillage;// 行政村数量
	private Double townlocalgdp;// 地方gdp
	private Double townrevenue;// 财政总收入
	private Double totalpopulation;// 总人口
	private Double farmingoutvalue;// 农业生产总值
	private Double industryoutvalue;// 工业生产总值
	private Double serviceoutvalue;// 服务业总产值
	private String weather;// 基本气候
	private String terrain;// 基本地形
	private String traffic;// 交通条件
	private String currentindustry;// 优势产业
	private String specialindustry;//主要特色产业
	private String specialindustryway;//特色产业方向
	private String industrialorientation;// 产业方向
	private String historyculture;// 历史文化和旅游资源
	private String honorarytitle;// 荣誉称号
	private String partycommittee;// 党委书记
	private String committelnumber;// 党委书记电话
	private String committel;// 党委书记手机
	private String mayor;// 镇长/主任
	private String mayortelnumber;// 镇长电话
	private String mayortel;// 党委书记手机
	private String contacts;// 联系人
	private String post;// 职务
	private String contactstel;// 联系方式
	private String citypic;//地市背景
	private String townpic;//区县背景
	private String scopeopic;//行政范围图
	private String totalplanpic;// 城镇总体规划
	private String detailplanpic;// 控制性详细规划
	private String creator;// 录入员
	private String updator;// 更新者
	
	private String search;// 搜索
	private String supersearch;// 高级搜索
	private String operation;// 操作
	/**
	 * 
	 *方法名称:
	 *内容：高级搜索参数
	 *创建人:zhuwenjie
	 *创建日期:2017年8月11日上午11:34:33
	 */
	private String countygdps;// 区县级GDP
	private String countyrevenues;// 区县级财政收入
	private String townpopulations;// 所在区县总人口
	private String townpgdis;// 区县人均可支配收入
	private String townareas;//行政面积
	private String communitys;// 社区数量
	private String adminvillages;// 行政村数量
	private String townlocalgdps;// 地方gdp
	private String townrevenues;// 财政总收入
	private String totalpopulations;// 总人口
	private String farmingoutvalues;// 农业生产总值
	private String industryoutvalues;// 工业生产总值
	private String serviceoutvalues;// 服务业总产值
	
	
	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public Double getCountygdp() {
		return countygdp;
	}

	public void setCountygdp(Double countygdp) {
		this.countygdp = countygdp;
	}

	public Double getCountyrevenue() {
		return countyrevenue;
	}

	public void setCountyrevenue(Double countyrevenue) {
		this.countyrevenue = countyrevenue;
	}

	public Double getTownpopulation() {
		return townpopulation;
	}

	public void setTownpopulation(Double townpopulation) {
		this.townpopulation = townpopulation;
	}

	public Double getTownpgdi() {
		return townpgdi;
	}

	public void setTownpgdi(Double townpgdi) {
		this.townpgdi = townpgdi;
	}

	public Double getTownarea() {
		return townarea;
	}

	public void setTownarea(Double townarea) {
		this.townarea = townarea;
	}

	public Integer getCommunity() {
		return community;
	}

	public void setCommunity(Integer community) {
		this.community = community;
	}

	public Integer getAdminvillage() {
		return adminvillage;
	}

	public void setAdminvillage(Integer adminvillage) {
		this.adminvillage = adminvillage;
	}

	public Double getTownlocalgdp() {
		return townlocalgdp;
	}

	public void setTownlocalgdp(Double townlocalgdp) {
		this.townlocalgdp = townlocalgdp;
	}

	public Double getTownrevenue() {
		return townrevenue;
	}

	public void setTownrevenue(Double townrevenue) {
		this.townrevenue = townrevenue;
	}

	public Double getTotalpopulation() {
		return totalpopulation;
	}

	public void setTotalpopulation(Double totalpopulation) {
		this.totalpopulation = totalpopulation;
	}

	public Double getFarmingoutvalue() {
		return farmingoutvalue;
	}

	public void setFarmingoutvalue(Double farmingoutvalue) {
		this.farmingoutvalue = farmingoutvalue;
	}

	public Double getIndustryoutvalue() {
		return industryoutvalue;
	}

	public void setIndustryoutvalue(Double industryoutvalue) {
		this.industryoutvalue = industryoutvalue;
	}

	public Double getServiceoutvalue() {
		return serviceoutvalue;
	}

	public void setServiceoutvalue(Double serviceoutvalue) {
		this.serviceoutvalue = serviceoutvalue;
	}

	public String getCountygdps() {
		return countygdps;
	}

	public void setCountygdps(String countygdps) {
		this.countygdps = countygdps;
	}

	public String getCountyrevenues() {
		return countyrevenues;
	}

	public void setCountyrevenues(String countyrevenues) {
		this.countyrevenues = countyrevenues;
	}

	public String getTownpopulations() {
		return townpopulations;
	}

	public void setTownpopulations(String townpopulations) {
		this.townpopulations = townpopulations;
	}

	public String getTownpgdis() {
		return townpgdis;
	}

	public void setTownpgdis(String townpgdis) {
		this.townpgdis = townpgdis;
	}

	public String getTownareas() {
		return townareas;
	}

	public void setTownareas(String townareas) {
		this.townareas = townareas;
	}

	public String getCommunitys() {
		return communitys;
	}

	public void setCommunitys(String communitys) {
		this.communitys = communitys;
	}

	public String getAdminvillages() {
		return adminvillages;
	}

	public void setAdminvillages(String adminvillages) {
		this.adminvillages = adminvillages;
	}

	public String getTownlocalgdps() {
		return townlocalgdps;
	}

	public void setTownlocalgdps(String townlocalgdps) {
		this.townlocalgdps = townlocalgdps;
	}

	public String getTownrevenues() {
		return townrevenues;
	}

	public void setTownrevenues(String townrevenues) {
		this.townrevenues = townrevenues;
	}

	public String getTotalpopulations() {
		return totalpopulations;
	}

	public void setTotalpopulations(String totalpopulations) {
		this.totalpopulations = totalpopulations;
	}

	public String getFarmingoutvalues() {
		return farmingoutvalues;
	}

	public void setFarmingoutvalues(String farmingoutvalues) {
		this.farmingoutvalues = farmingoutvalues;
	}

	public String getIndustryoutvalues() {
		return industryoutvalues;
	}

	public void setIndustryoutvalues(String industryoutvalues) {
		this.industryoutvalues = industryoutvalues;
	}

	public String getServiceoutvalues() {
		return serviceoutvalues;
	}

	public void setServiceoutvalues(String serviceoutvalues) {
		this.serviceoutvalues = serviceoutvalues;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getSupersearch() {
		return supersearch;
	}

	public void setSupersearch(String supersearch) {
		this.supersearch = supersearch;
	}

	public String getTotalplanpic() {
		return totalplanpic;
	}

	public void setTotalplanpic(String totalplanpic) {
		this.totalplanpic = totalplanpic;
	}

	public String getDetailplanpic() {
		return detailplanpic;
	}

	public void setDetailplanpic(String detailplanpic) {
		this.detailplanpic = detailplanpic;
	}

	public String getCitypic() {
		return citypic;
	}

	public void setCitypic(String citypic) {
		this.citypic = citypic;
	}

	public String getTownpic() {
		return townpic;
	}

	public void setTownpic(String townpic) {
		this.townpic = townpic;
	}

	public String getScopeopic() {
		return scopeopic;
	}

	public void setScopeopic(String scopeopic) {
		this.scopeopic = scopeopic;
	}

	public String getCentertownid() {
		return centertownid;
	}

	public void setCentertownid(String centertownid) {
		this.centertownid = centertownid;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getCentertownname() {
		return centertownname;
	}

	public void setCentertownname(String centertownname) {
		this.centertownname = centertownname;
	}

	public String getTownlevel() {
		return townlevel;
	}

	public void setTownlevel(String townlevel) {
		this.townlevel = townlevel;
	}

	public String getCooperation() {
		return cooperation;
	}

	public void setCooperation(String cooperation) {
		this.cooperation = cooperation;
	}

	public String getHundredcounties() {
		return hundredcounties;
	}

	public void setHundredcounties(String hundredcounties) {
		this.hundredcounties = hundredcounties;
	}

	public String getCitypilot() {
		return citypilot;
	}

	public void setCitypilot(String citypilot) {
		this.citypilot = citypilot;
	}

	public String getPartycommittee() {
		return partycommittee;
	}

	public void setPartycommittee(String partycommittee) {
		this.partycommittee = partycommittee;
	}

	public String getMayor() {
		return mayor;
	}

	public void setMayor(String mayor) {
		this.mayor = mayor;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public String getTraffic() {
		return traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}

	public String getCurrentindustry() {
		return currentindustry;
	}

	public void setCurrentindustry(String currentindustry) {
		this.currentindustry = currentindustry;
	}

	public String getIndustrialorientation() {
		return industrialorientation;
	}

	public void setIndustrialorientation(String industrialorientation) {
		this.industrialorientation = industrialorientation;
	}

	public String getHonorarytitle() {
		return honorarytitle;
	}

	public void setHonorarytitle(String honorarytitle) {
		this.honorarytitle = honorarytitle;
	}

	public String getHistoryculture() {
		return historyculture;
	}

	public void setHistoryculture(String historyculture) {
		this.historyculture = historyculture;
	}

	public String getTowndatayear() {
		return towndatayear;
	}

	public void setTowndatayear(String towndatayear) {
		this.towndatayear = towndatayear;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}


	public String getContactstel() {
		return contactstel;
	}

	public void setContactstel(String contactstel) {
		this.contactstel = contactstel;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public String getSys_province() {
		return sys_province;
	}

	public void setSys_province(String sys_province) {
		this.sys_province = sys_province;
	}

	public String getSys_city() {
		return sys_city;
	}

	public void setSys_city(String sys_city) {
		this.sys_city = sys_city;
	}

	public String getSys_town() {
		return sys_town;
	}

	public void setSys_town(String sys_town) {
		this.sys_town = sys_town;
	}

	public String getCommittelnumber() {
		return committelnumber;
	}

	public void setCommittelnumber(String committelnumber) {
		this.committelnumber = committelnumber;
	}

	public String getCommittel() {
		return committel;
	}

	public void setCommittel(String committel) {
		this.committel = committel;
	}

	public String getMayortelnumber() {
		return mayortelnumber;
	}

	public void setMayortelnumber(String mayortelnumber) {
		this.mayortelnumber = mayortelnumber;
	}

	public String getMayortel() {
		return mayortel;
	}

	public void setMayortel(String mayortel) {
		this.mayortel = mayortel;
	}

	public String getSpecialindustry() {
		return specialindustry;
	}

	public void setSpecialindustry(String specialindustry) {
		this.specialindustry = specialindustry;
	}

	public String getSpecialindustryway() {
		return specialindustryway;
	}

	public void setSpecialindustryway(String specialindustryway) {
		this.specialindustryway = specialindustryway;
	}

}
