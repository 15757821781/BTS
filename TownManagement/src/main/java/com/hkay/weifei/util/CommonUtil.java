package com.hkay.weifei.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class CommonUtil {

	/**
	 * 
	 *方法名称:
	 *内容：判断是否为空
	 *创建人:zhuwenjie
	 *创建日期:2017年8月8日下午5:01:56
	 */
	public Boolean JudgeEmpty(Object object) {
		if(object!=null && !object.equals("")){
			return true;
		}else{
			return false;
		}
	}
	
	public String decodeUtf8(String object) {
		try {
			object = URLDecoder.decode(object, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
		return object;
	}
}
