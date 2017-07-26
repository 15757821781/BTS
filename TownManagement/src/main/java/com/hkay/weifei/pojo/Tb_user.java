package com.hkay.weifei.pojo;

public class Tb_user {
	private Integer userid;//用户ID
	
	private String name;//用户名称

	private String account;//用户帐号

	private String password;//密码
	
	private String number;//编号
	
	private String search;//查询条件
	
	private String state;// 是否可用
	
	private String value;// 值
	
	private String userdata;//用户数据权限
	
	private String roleid;//角色id
	
	private String repassword;// 重复密码
	
	private String rolename;// 角色名称
	
	
	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUserdata() {
		return userdata;
	}

	public void setUserdata(String userdata) {
		this.userdata = userdata;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}