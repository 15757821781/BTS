package com.hkay.weifei.pojo;

public class Tb_zhaoshangxiangmu {
	private int invid;
	private String invname;//项目名称
	private String invnumber;//项目编号
	private String invprovince;// 省份
	private String invcity;// 城市
	private String invtown;// 乡县
	private String invtownship ;//乡镇街道
	private String invjoinway;//合作方式
	private String invcharge;//主管单位
	private String invindustry;//所属行业
	private Double invlandscale;//用地规模
	private Double invplotratio;//容积率
	private String invplanuse;//土地用途
	private Double investment;//投资强度
	private Double investmentall;//投资总额
	private String invexpectbuild;//预计建设期
	private String invplanaddress;//规划地址
	private String invbuildcontent;//建设内容
	private String invbuildcondition;//建设条件
	private String invincentives;//优惠政策
	private String invessential;//要素分析
	private String invbenefit;//效益分析
//	private String invremark;//备注
	private String invcitypic;// 城市区位图
	private String invtownpic;// 区县区位图
	private String invscopeopic;// 规划范围图
	private String invplanpic;// 规划方案图
	private String invallplanpic;// 城市总体规划
	private String invdetailplanpic;// 控制性详细规划
//	private String invunit;//联系单位
	private String invcontact;//联系人
	private String invcontacttel;//联系电话
	private String search;//搜索内容
	private String invpost;//职务
	private String inventry;//录入员
	private String invupdator;//修改者
	
	private String operation;// 操作
	private String supersearch;// 高级搜索
	
	/*
	 * 高级搜索用到的参数
	 */
	private String invlandscales;//用地规模
	private String invplotratios;//容积率
	private String investments;//投资强度
	private String investmentalls;//投资总额
	
	
	
	public String getInvlandscales() {
		return invlandscales;
	}
	public void setInvlandscales(String invlandscales) {
		this.invlandscales = invlandscales;
	}
	public String getInvplotratios() {
		return invplotratios;
	}
	public void setInvplotratios(String invplotratios) {
		this.invplotratios = invplotratios;
	}
	public String getInvestments() {
		return investments;
	}
	public void setInvestments(String investments) {
		this.investments = investments;
	}
	public String getInvestmentalls() {
		return investmentalls;
	}
	public void setInvestmentalls(String investmentalls) {
		this.investmentalls = investmentalls;
	}
	public String getSupersearch() {
		return supersearch;
	}
	public void setSupersearch(String supersearch) {
		this.supersearch = supersearch;
  }
	public String getInvupdator() {
		return invupdator;
	}
	public void setInvupdator(String invupdator) {
		this.invupdator = invupdator;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public int getInvid() {
		return invid;
	}
	public void setInvid(int invid) {
		this.invid = invid;
	}
	public String getInvname() {
		return invname;
	}
	public void setInvname(String invname) {
		this.invname = invname;
	}
	public void setInvjoinway(String invjoinway) {
		this.invjoinway = invjoinway;
	}
	public String getInvcharge() {
		return invcharge;
	}
	public void setInvcharge(String invcharge) {
		this.invcharge = invcharge;
	}
	public String getInvindustry() {
		return invindustry;
	}
	public void setInvindustry(String invindustry) {
		this.invindustry = invindustry;
	}
	public Double getInvlandscale() {
		return invlandscale;
	}
	public void setInvlandscale(Double invlandscale) {
		this.invlandscale = invlandscale;
	}
	public Double getInvplotratio() {
		return invplotratio;
	}
	public void setInvplotratio(Double invplotratio) {
		this.invplotratio = invplotratio;
	}
	public String getInvplanuse() {
		return invplanuse;
	}
	public void setInvplanuse(String invplanuse) {
		this.invplanuse = invplanuse;
	}
	public Double getInvestment() {
		return investment;
	}
	public void setInvestment(Double investment) {
		this.investment = investment;
	}
	public Double getInvestmentall() {
		return investmentall;
	}
	public void setInvestmentall(Double investmentall) {
		this.investmentall = investmentall;
	}
	public String getInvexpectbuild() {
		return invexpectbuild;
	}
	public void setInvexpectbuild(String invexpectbuild) {
		this.invexpectbuild = invexpectbuild;
	}
	public String getInvplanaddress() {
		return invplanaddress;
	}
	public void setInvplanaddress(String invplanaddress) {
		this.invplanaddress = invplanaddress;
	}
	public String getInvbuildcontent() {
		return invbuildcontent;
	}
	public void setInvbuildcontent(String invbuildcontent) {
		this.invbuildcontent = invbuildcontent;
	}
	public String getInvbuildcondition() {
		return invbuildcondition;
	}
	public void setInvbuildcondition(String invbuildcondition) {
		this.invbuildcondition = invbuildcondition;
	}
	public String getInvincentives() {
		return invincentives;
	}
	public void setInvincentives(String invincentives) {
		this.invincentives = invincentives;
	}
	public String getInvessential() {
		return invessential;
	}
	public void setInvessential(String invessential) {
		this.invessential = invessential;
	}
	public String getInvbenefit() {
		return invbenefit;
	}
	public void setInvbenefit(String invbenefit) {
		this.invbenefit = invbenefit;
	}
	public String getInvcontact() {
		return invcontact;
	}
	public void setInvcontact(String invcontact) {
		this.invcontact = invcontact;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getInvpost() {
		return invpost;
	}
	public void setInvpost(String invpost) {
		this.invpost = invpost;
	}
	public String getInventry() {
		return inventry;
	}
	public void setInventry(String inventry) {
		this.inventry = inventry;
	}
	public String getInvcitypic() {
		return invcitypic;
	}
	public void setInvcitypic(String invcitypic) {
		this.invcitypic = invcitypic;
	}
	public String getInvtownpic() {
		return invtownpic;
	}
	public void setInvtownpic(String invtownpic) {
		this.invtownpic = invtownpic;
	}
	public String getInvscopeopic() {
		return invscopeopic;
	}
	public void setInvscopeopic(String invscopeopic) {
		this.invscopeopic = invscopeopic;
	}
	public String getInvplanpic() {
		return invplanpic;
	}
	public void setInvplanpic(String invplanpic) {
		this.invplanpic = invplanpic;
	}
	public String getInvallplanpic() {
		return invallplanpic;
	}
	public void setInvallplanpic(String invallplanpic) {
		this.invallplanpic = invallplanpic;
	}
	public String getInvdetailplanpic() {
		return invdetailplanpic;
	}
	public void setInvdetailplanpic(String invdetailplanpic) {
		this.invdetailplanpic = invdetailplanpic;
	}
	public String getInvprovince() {
		return invprovince;
	}
	public void setInvprovince(String invprovince) {
		this.invprovince = invprovince;
	}
	public String getInvcity() {
		return invcity;
	}
	public void setInvcity(String invcity) {
		this.invcity = invcity;
	}
	public String getInvtown() {
		return invtown;
	}
	public void setInvtown(String invtown) {
		this.invtown = invtown;
	}
	public String getInvtownship() {
		return invtownship;
	}
	public void setInvtownship(String invtownship) {
		this.invtownship = invtownship;
	}
	public String getInvcontacttel() {
		return invcontacttel;
	}
	public void setInvcontacttel(String invcontacttel) {
		this.invcontacttel = invcontacttel;
	}
	public String getInvjoinway() {
		return invjoinway;
	}
	public void setInvnumber(String invnumber) {
		this.invnumber = invnumber;
	}
	public String getInvnumber() {
		return invnumber;
	}
	
	
}
