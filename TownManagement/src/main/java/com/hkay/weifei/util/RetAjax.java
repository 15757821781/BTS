package com.hkay.weifei.util;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.hkay.weifei.pojo.Tb_user;

public class RetAjax {
	// 返回信息描述
	private String message;
	// 返回状态
	private String state;
	// 返回数据
	private Object data;
	// 表格数据
	private Object rows;
	// 返回总数
	private Integer total;
	/**
	 * 
		 * 方法名称: onSuccess
		 * 内容摘要: 操作成功时返回
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年4月7日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	public static RetAjax onSuccess(Object object, String mes) {
		RetAjax ret = new RetAjax();
		ret.message = mes;
		ret.data = object;
		ret.state = TypeStatusConstant.success;
		return ret;
	}
	
	/**
	 * 
		 * 方法名称: onFail
		 * 内容摘要: 操作失败
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年4月7日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	public static RetAjax onFail(String mes){
		RetAjax ret = new RetAjax();
		ret.message = mes;
		ret.data = "";
		ret.state = TypeStatusConstant.fail;
		return ret;
	}
	
	/**
	 * 
		 * 方法名称: lostLoginInfo
		 * 内容摘要: 用户登录信息丢失，重新登录
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年4月7日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	public static RetAjax lostLoginInfo(){
		RetAjax ret = new RetAjax();
		ret.message = TypeStatusConstant.session_lost;
		ret.data=null;
		ret.state=TypeStatusConstant.login_out;
		return ret;
	}
	
	/**
	 * 
		 * 方法名称: onDataBase
		 * 内容摘要: 根据数据返回的值判断( 1:增   2:删  3:改  ) 的操作是否成功
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年4月7日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	public static RetAjax onDataBase(int flag,int operation) {
		RetAjax retAjax=new RetAjax();
		retAjax.data=null;
		if(flag==1){
			if(operation==1){
				retAjax.state=TypeStatusConstant.insertsuccess;
				retAjax.message=TypeStatusConstant.insert_success;
			}else if(operation==2){
				retAjax.state=TypeStatusConstant.deletesuccess;
				retAjax.message=TypeStatusConstant.delete_success;
			}else if (operation==3) {
				retAjax.state=TypeStatusConstant.updatesuccess;
				retAjax.message=TypeStatusConstant.update_success;
			}
		}else{
			if(operation==1){
				retAjax.state=TypeStatusConstant.insertfail;
				retAjax.message=TypeStatusConstant.insert_fail;
			}else if(operation==2){
				retAjax.state=TypeStatusConstant.deletefail;
				retAjax.message=TypeStatusConstant.delete_fail;
			}else if (operation==3) {
				retAjax.state=TypeStatusConstant.updatefail;
				retAjax.message=TypeStatusConstant.update_fail;
			}
		}
		return retAjax;
	}
	
	/**
	 * 
		 * 方法名称: onLogin
		 * 内容摘要: 判断登陆
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年4月20日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	public static RetAjax onLogin(List<Tb_user> users,HttpSession session) {
		RetAjax retAjax=new RetAjax();
		if(users.size()==1&&!users.get(0).getAccount().equals("")&&!users.get(0).getPassword().equals("")){
			retAjax.state=TypeStatusConstant.loginsuccess;
			retAjax.message=TypeStatusConstant.login_success;
			retAjax.data=users.get(0);
			session.setAttribute("town_LoginData", users.get(0));
			session.setMaxInactiveInterval(3600);
		}else{
			retAjax.state=TypeStatusConstant.loginfail;
			retAjax.message=TypeStatusConstant.login_fail;
			retAjax.data=null;
		}
		return retAjax;
	}
	
	/**
	 * 
	 *方法名称:
	 *内容：针对表格的返回
	 *创建人:zhuwenjie
	 *创建日期:2017年9月14日上午9:55:15
	 */
	public static RetAjax onGrid(Object object, Integer count) {
		RetAjax ret = new RetAjax();
		ret.total = count;
		ret.rows = object;
		ret.state = TypeStatusConstant.success;
		return ret;
	}
	
	public static RetAjax onQueryDetail(List<?> list) {
		RetAjax retAjax=new RetAjax();
		if(!list.isEmpty()&&list.size()==1){
			retAjax.state=TypeStatusConstant.success;
			retAjax.message="";
			retAjax.data=list;
		}else{
			retAjax.state=TypeStatusConstant.fail;
			retAjax.message="查询失败";
			retAjax.data=null;
		}
		return retAjax;
		
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
