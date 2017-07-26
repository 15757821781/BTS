package com.hkay.weifei.pojo;

public class Pages {
	private String pagename;
	private int pagelevel;
	private int parentid;
	private int pageid;
	private String url;
	private int pagegroup;
	
	private String id;
	private String name;
	private String level;
	private Boolean isParent;
	private String roleid;
	private String checked;
	
	private String userdata;
	
	public String getUserdata() {
		return userdata;
	}

	public void setUserdata(String userdata) {
		this.userdata = userdata;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getPagegroup() {
		return pagegroup;
	}

	public void setPagegroup(int pagegroup) {
		this.pagegroup = pagegroup;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPagelevel() {
		return pagelevel;
	}

	public void setPagelevel(int pagelevel) {
		this.pagelevel = pagelevel;
	}

	public int getPageid() {
		return pageid;
	}

	public void setPageid(int pageid) {
		this.pageid = pageid;
	}

	public String getPagename() {
		return pagename;
	}

	public void setPagename(String pagename) {
		this.pagename = pagename;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

}
