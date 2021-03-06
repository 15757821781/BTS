package com.hkay.weifei.pojo;

public class Tb_quyuxingxiangmu {
	private int regid;//区域性项目id
	private String regname;//项目名称
	private String regnumber;//编号
	private String regprovince;// 省份
	private String regcity;// 城市
	private String regtown;// 乡县
	private String regtownship ;//乡镇街道
	private String regposition;//地理位置
	private String regschedule;//项目阶段
	private String regrelation;//关系情况
	private Double regplanarea;//规划面积
	private Double regplaninvest;//计划投资
	private Double reglandarea;//征地面积
	private String regplanareas;//多期规划面积
	private String regplaninvests;//多期投资
	private String reglandareas;//多期征地面积
	private String regbasic;//基本情况
	private String regspeed;//进度情况
	private String regdockingtime;//对接时间
	private String regcontractdate;//反馈节点
	private String regnowindustry;//优势产业
	private String regprimeindustry;//产业方向
	private String regprojectcost;//项目费用
	private String regdeveloper;//牵头单位
	private String regcharge;//牵头负责人
	private String regchargetel;//牵头负责人电话
	private String regbegtime;//合作期限开始
	private String regendtime;//合作期限结束
	private String regdevelopment;//合作开发情况
	private String regcontract;//合同
	private String regpartner;//合作开发单位
	private String regpartcharge;//合作单位负责人
	private String regparttel;//合作单位负责人电话
	private String regterms;//合作方式
	private Double reinvest;//合作投资额
	private String regcontent;//合作内容
	private String regcontact;//联系人
	private String regpost;//职务
	private String regcontenttel;//联系电话
	private String regcitypic;// 城市区位图
	private String regtownpic;// 区县区位图
	private String regscopeopic;// 规划范围图
	private String regplanpic;// 规划方案图
	private String regallplanpic;// 城市总体规划
	private String regdetailplanpic;// 控制性详细规划
	private String regentry;//录入员
	private String regupdator;//修改者
	
	private String search;//搜索内容
	private String operation;// 操作
	private String supersearch;// 高级搜索
	
	/*
	 * 高级搜索用到的参数
	 */
	private String regplanareass;//规划面积
	private String regplaninvestss;//计划投资
	private String reglandareass;//征地面积
	private String reinvests;//合作投资额

	public String getRegupdator() {
		return regupdator;
	}
	public void setRegupdator(String regupdator) {
		this.regupdator = regupdator;
  }
	public String getRegplanareass() {
		return regplanareass;
	}
	public void setRegplanareass(String regplanareass) {
		this.regplanareass = regplanareass;
	}
	public String getRegplaninvestss() {
		return regplaninvestss;
	}
	public void setRegplaninvestss(String regplaninvestss) {
		this.regplaninvestss = regplaninvestss;
	}
	public String getReglandareass() {
		return reglandareass;
	}
	public void setReglandareass(String reglandareass) {
		this.reglandareass = reglandareass;
	}
	public String getReinvests() {
		return reinvests;
	}
	public void setReinvests(String reinvests) {
		this.reinvests = reinvests;
	}
	public String getSupersearch() {
		return supersearch;
	}
	public void setSupersearch(String supersearch) {
		this.supersearch = supersearch;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getRegcontract() {
		return regcontract;
	}
	public void setRegcontract(String regcontract) {
		this.regcontract = regcontract;
	}
	public String getRegbegtime() {
		return regbegtime;
	}
	public void setRegbegtime(String regbegtime) {
		this.regbegtime = regbegtime;
	}
	public String getRegendtime() {
		return regendtime;
	}
	public void setRegendtime(String regendtime) {
		this.regendtime = regendtime;
	}
	public String getRegplanareas() {
		return regplanareas;
	}
	public void setRegplanareas(String regplanareas) {
		this.regplanareas = regplanareas;
	}
	public String getRegplaninvests() {
		return regplaninvests;
	}
	public void setRegplaninvests(String regplaninvests) {
		this.regplaninvests = regplaninvests;
	}
	public String getReglandareas() {
		return reglandareas;
	}
	public void setReglandareas(String reglandareas) {
		this.reglandareas = reglandareas;
	}
	public String getRegnumber() {
		return regnumber;
	}
	public void setRegnumber(String regnumber) {
		this.regnumber = regnumber;
	}
	public String getRegcontact() {
		return regcontact;
	}
	public void setRegcontact(String regcontact) {
		this.regcontact = regcontact;
	}
	public String getRegcontenttel() {
		return regcontenttel;
	}
	public void setRegcontenttel(String regcontenttel) {
		this.regcontenttel = regcontenttel;
	}
	public int getRegid() {
		return regid;
	}
	public void setRegid(int regid) {
		this.regid = regid;
	}
	public String getRegname() {
		return regname;
	}
	public void setRegname(String regname) {
		this.regname = regname;
	}
	public String getRegprovince() {
		return regprovince;
	}
	public void setRegprovince(String regprovince) {
		this.regprovince = regprovince;
	}
	public String getRegcity() {
		return regcity;
	}
	public void setRegcity(String regcity) {
		this.regcity = regcity;
	}
	public String getRegtown() {
		return regtown;
	}
	public void setRegtown(String regtown) {
		this.regtown = regtown;
	}
	public String getRegtownship() {
		return regtownship;
	}
	public void setRegtownship(String regtownship) {
		this.regtownship = regtownship;
	}
	public String getRegposition() {
		return regposition;
	}
	public void setRegposition(String regposition) {
		this.regposition = regposition;
	}
	public String getRegschedule() {
		return regschedule;
	}
	public void setRegschedule(String regschedule) {
		this.regschedule = regschedule;
	}
	public String getRegrelation() {
		return regrelation;
	}
	public void setRegrelation(String regrelation) {
		this.regrelation = regrelation;
	}
	public String getRegbasic() {
		return regbasic;
	}
	public void setRegbasic(String regbasic) {
		this.regbasic = regbasic;
	}
	public String getRegspeed() {
		return regspeed;
	}
	public void setRegspeed(String regspeed) {
		this.regspeed = regspeed;
	}
	public String getRegnowindustry() {
		return regnowindustry;
	}
	public void setRegnowindustry(String regnowindustry) {
		this.regnowindustry = regnowindustry;
	}
	public String getRegprimeindustry() {
		return regprimeindustry;
	}
	public void setRegprimeindustry(String regprimeindustry) {
		this.regprimeindustry = regprimeindustry;
	}
	public String getRegprojectcost() {
		return regprojectcost;
	}
	public void setRegprojectcost(String regprojectcost) {
		this.regprojectcost = regprojectcost;
	}
	public String getRegdeveloper() {
		return regdeveloper;
	}
	public void setRegdeveloper(String regdeveloper) {
		this.regdeveloper = regdeveloper;
	}
	public String getRegcharge() {
		return regcharge;
	}
	public void setRegcharge(String regcharge) {
		this.regcharge = regcharge;
	}
	public String getRegchargetel() {
		return regchargetel;
	}
	public void setRegchargetel(String regchargetel) {
		this.regchargetel = regchargetel;
	}
	public String getRegdevelopment() {
		return regdevelopment;
	}
	public void setRegdevelopment(String regdevelopment) {
		this.regdevelopment = regdevelopment;
	}
	public String getRegpartner() {
		return regpartner;
	}
	public void setRegpartner(String regpartner) {
		this.regpartner = regpartner;
	}
	public String getRegpartcharge() {
		return regpartcharge;
	}
	public void setRegpartcharge(String regpartcharge) {
		this.regpartcharge = regpartcharge;
	}
	public String getRegparttel() {
		return regparttel;
	}
	public void setRegparttel(String regparttel) {
		this.regparttel = regparttel;
	}
	public String getRegterms() {
		return regterms;
	}
	public void setRegterms(String regterms) {
		this.regterms = regterms;
	}
	public String getRegcontent() {
		return regcontent;
	}
	public void setRegcontent(String regcontent) {
		this.regcontent = regcontent;
	}
	public String getRegpost() {
		return regpost;
	}
	public void setRegpost(String regpost) {
		this.regpost = regpost;
	}
	public String getRegcitypic() {
		return regcitypic;
	}
	public void setRegcitypic(String regcitypic) {
		this.regcitypic = regcitypic;
	}
	public String getRegtownpic() {
		return regtownpic;
	}
	public void setRegtownpic(String regtownpic) {
		this.regtownpic = regtownpic;
	}
	public String getRegscopeopic() {
		return regscopeopic;
	}
	public void setRegscopeopic(String regscopeopic) {
		this.regscopeopic = regscopeopic;
	}
	public String getRegplanpic() {
		return regplanpic;
	}
	public void setRegplanpic(String regplanpic) {
		this.regplanpic = regplanpic;
	}
	public String getRegallplanpic() {
		return regallplanpic;
	}
	public void setRegallplanpic(String regallplanpic) {
		this.regallplanpic = regallplanpic;
	}
	public String getRegdetailplanpic() {
		return regdetailplanpic;
	}
	public void setRegdetailplanpic(String regdetailplanpic) {
		this.regdetailplanpic = regdetailplanpic;
	}
	public String getRegentry() {
		return regentry;
	}
	public void setRegentry(String regentry) {
		this.regentry = regentry;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public Double getRegplanarea() {
		return regplanarea;
	}
	public void setRegplanarea(Double regplanarea) {
		this.regplanarea = regplanarea;
	}
	public Double getRegplaninvest() {
		return regplaninvest;
	}
	public void setRegplaninvest(Double regplaninvest) {
		this.regplaninvest = regplaninvest;
	}
	public Double getReglandarea() {
		return reglandarea;
	}
	public void setReglandarea(Double reglandarea) {
		this.reglandarea = reglandarea;
	}
	public Double getReinvest() {
		return reinvest;
	}
	public void setReinvest(Double reinvest) {
		this.reinvest = reinvest;
	}
	public String getRegdockingtime() {
		return regdockingtime;
	}
	public void setRegdockingtime(String regdockingtime) {
		this.regdockingtime = regdockingtime;
	}
	public String getRegcontractdate() {
		return regcontractdate;
	}
	public void setRegcontractdate(String regcontractdate) {
		this.regcontractdate = regcontractdate;
	}

	
}
