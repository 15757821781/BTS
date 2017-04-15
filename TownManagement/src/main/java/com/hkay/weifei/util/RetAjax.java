package com.hkay.weifei.util;

public class RetAjax {
	// 返回信息描述
	private String message;
	// 返回状态
	private String state;
	// 返回数据
	private Object data;

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
		 * 内容摘要: 根据数据返回的值判断 1、增2、删3、改的操作是否成功
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
			retAjax.state=TypeStatusConstant.success;
			if(operation==1){
				retAjax.message=TypeStatusConstant.insert_success;
			}else if(operation==2){
				retAjax.message=TypeStatusConstant.delete_success;
			}else if (operation==3) {
				retAjax.message=TypeStatusConstant.update_success;
			}
		}else{
			retAjax.state=TypeStatusConstant.fail;
			if(operation==1){
				retAjax.message=TypeStatusConstant.insert_fail;
			}else if(operation==2){
				retAjax.message=TypeStatusConstant.delete_fail;
			}else if (operation==3) {
				retAjax.message=TypeStatusConstant.update_fail;
			}
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

}
