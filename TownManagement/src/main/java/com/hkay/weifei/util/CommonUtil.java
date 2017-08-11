package com.hkay.weifei.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.ibatis.jdbc.SQL;

public class CommonUtil {

	/**
	 * 
	 *方法名称:
	 *内容：判断是否为空
	 *创建人:zhuwenjie
	 *创建日期:2017年8月8日下午5:01:56
	 */
	public static Boolean JudgeEmpty(Object object) {
		if(object!=null && !object.equals("")){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 *方法名称:
	 *内容：处理前端中文编码
	 *创建人:zhuwenjie
	 *创建日期:2017年8月9日上午10:06:23
	 */
	public static String decodeUtf8(String object) {
		try {
			object = URLDecoder.decode(object, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
		return object;
	}
	
	/**
	 * 
	 *方法名称:
	 *内容：处理前端传值中的数值转成相应的sql
	 *创建人:zhuwenjie
	 *创建日期:2017年8月9日上午11:02:13
	 */
	public static String HandleNum(String field,Object object) {
		String sql = "";
		try {
			if(object!=null&&!object.toString().equals("")){
				String str = object.toString();
				int i = str.indexOf("-");
				// 开头位置
				if(i+1==1){
					sql = " and a."+field+" < "+str.replace("-", "");
				// 结束位置
				}else if(i+1==str.length()){
					sql = " and a."+field+" > "+str.replace("-", "");
				// 不存在
				}else if(i==-1){
					sql = " and a."+field+" like '"+str+"'";
				// 处于中间位置
				}else{
					sql = " and a." + field + " > " + str.split("-")[0] 
						+ " and a." + field + " < " + str.split("-")[1];
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return sql="";
		}
		return sql;
	}
}
