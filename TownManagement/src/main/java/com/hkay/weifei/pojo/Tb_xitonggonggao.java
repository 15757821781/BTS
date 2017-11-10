package com.hkay.weifei.pojo;

public class Tb_xitonggonggao {
	private String nocid; // id
	private String noctitle; // 大标题
	private String noctext; // 正文
	private String noccreator;//录入员
	private String nocupdator;//修改者
	private String createtime;//发布时间
	
	private String search;// 查询条件
	private String operation;// 操作
	private String supersearch;// 高级搜索
	public String getNocid() {
		return nocid;
	}
	public void setNocid(String nocid) {
		this.nocid = nocid;
	}
	public String getNoctitle() {
		return noctitle;
	}
	public void setNoctitle(String noctitle) {
		this.noctitle = noctitle;
	}
	public String getNoctext() {
		return noctext;
	}
	public void setNoctext(String noctext) {
		this.noctext = noctext;
	}
	public String getNoccreator() {
		return noccreator;
	}
	public void setNoccreator(String noccreator) {
		this.noccreator = noccreator;
	}
	public String getNocupdator() {
		return nocupdator;
	}
	public void setNocupdator(String nocupdator) {
		this.nocupdator = nocupdator;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
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
	
}
