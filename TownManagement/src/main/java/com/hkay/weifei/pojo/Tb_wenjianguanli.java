package com.hkay.weifei.pojo;

public class Tb_wenjianguanli {
	private String fileid; // id
	private String filetitle; // 大标题
	private String filetext; // 有关文件
	private String filecreator;//录入员
	private String fileupdator;//修改者
	private String createtime;//创建时间
	
	
	private String search;// 查询条件
	private String operation;// 操作
	private String supersearch;// 高级搜索
	public String getFileid() {
		return fileid;
	}
	public void setFileid(String fileid) {
		this.fileid = fileid;
	}
	public String getFiletitle() {
		return filetitle;
	}
	public void setFiletitle(String filetitle) {
		this.filetitle = filetitle;
	}
	public String getFiletext() {
		return filetext;
	}
	public void setFiletext(String filetext) {
		this.filetext = filetext;
	}
	public String getFilecreator() {
		return filecreator;
	}
	public void setFilecreator(String filecreator) {
		this.filecreator = filecreator;
	}
	public String getFileupdator() {
		return fileupdator;
	}
	public void setFileupdator(String fileupdator) {
		this.fileupdator = fileupdator;
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
