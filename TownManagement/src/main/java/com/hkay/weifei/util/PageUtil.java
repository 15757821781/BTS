package com.hkay.weifei.util;
import com.github.pagehelper.Page;
public class PageUtil {
	
	public static  Page getPage(Integer pageNum, Integer pageSize,boolean count){
		if(pageNum==null){
			pageNum=1;
		}
		if(pageSize==null){
			pageSize=10;
		}
		Page page=new Page(pageNum,pageSize,true);
		return page;
	}

}
