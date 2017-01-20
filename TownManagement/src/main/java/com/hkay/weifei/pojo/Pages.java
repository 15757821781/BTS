package com.hkay.weifei.pojo;

public class Pages {
	private String pagename;
	private int pagelevel;
	private int parentid;
	private int pageid;
	private String url;
	
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
