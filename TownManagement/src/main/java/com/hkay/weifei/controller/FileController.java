package com.hkay.weifei.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hkay.weifei.pojo.Tb_user;
import com.hkay.weifei.pojo.Tb_wenjianguanli;
import com.hkay.weifei.service.FileService;
import com.hkay.weifei.util.FileUpload;
import com.hkay.weifei.util.PageUtil;
import com.hkay.weifei.util.RetAjax;
import com.hkay.weifei.util.TypeStatusConstant;

@Controller
@RequestMapping("/filemanage")

public class FileController {
	private static Logger Log =Logger.getLogger(FileController.class);
	@Resource
	private FileService  fileService;
	private RetAjax result;
	private FileUpload fileupload = new FileUpload();
	
	/**
	 * 
		 * 方法名称: insertFileInfo
		 * 内容摘要: 新增文件管理信息
		 * 创建人：caixuyang	
		 * 创建日期： 2017年11月10日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/insertFileInfo")
	@ResponseBody
	public RetAjax insertFileInfo(HttpServletRequest request, Tb_wenjianguanli wenjianguanli,
			@RequestParam("comcertificatepic") MultipartFile[] files) {
		try {
			HttpSession session = request.getSession();
			Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
			wenjianguanli.setFilecreator(user.getNumber());
			String imgpath = fileupload.fileUpload(files, request, TypeStatusConstant.file, "");
			wenjianguanli.setFiletext(imgpath);
			int flag = this.fileService.insertFileInfo(wenjianguanli);
			result = RetAjax.onDataBase(flag,1);
		} catch (Exception e) {
			Log.error("error----------insertFileInfo:" + e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 1);
		}
		return result;
	}
	
	/**
	 * 
		 * 方法名称: queryFileList
		 * 内容摘要: 查询系统公告列表
		 * 创建人：caixuyang
		 * 创建日期： 2017年11月10日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryFileList")
	@ResponseBody
	public RetAjax queryFileList(HttpServletRequest request,@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageindex", required = false) Integer pageindex,
			Tb_wenjianguanli tb_wenjianguanli) throws UnsupportedEncodingException {
		HttpSession session = request.getSession();
		Tb_user user = (Tb_user) session.getAttribute("town_LoginData");
		String number = user.getNumber();
		String userdata = user.getUserdata();
		Page<?> page = PageUtil.getPage(pageindex, limit, true);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		//处理高级搜索
//		tb_wenjianguanli.setSupersearch(GetSuperSearchSql(tb_wenjianguanli));
		List<Tb_wenjianguanli> tb_wenjianguanlis = this.fileService.queryFileList(tb_wenjianguanli);
		if(tb_wenjianguanlis!=null){
			for(int i=0;i<tb_wenjianguanlis.size();i++){
				if(userdata.equals("3")||number.equals(tb_wenjianguanlis.get(i).getFilecreator())){
					tb_wenjianguanlis.get(i).setOperation("<a href='javascript:void(0)' onclick='querydetail("+tb_wenjianguanlis.get(i).getFileid()+")'>查看</a>"+
							"&nbsp"+"<a href='javascript:void(0)' onclick='updateinfo("+tb_wenjianguanlis.get(i).getFileid()+")'>修改</a>");
				}else{
					tb_wenjianguanlis.get(i).setOperation("<a href='javascript:void(0)' onclick='querydetail("+tb_wenjianguanlis.get(i).getFileid()+")'>查看</a>");
				}
			}
		}
		int count = this.fileService.queryfilecnt(tb_wenjianguanli);
		result = RetAjax.onGrid(tb_wenjianguanlis, count);
		return result;
	}

//	private String GetSuperSearchSql(Tb_wenjianguanli wjgl) {
//		StringBuilder sql = new StringBuilder();
//		if(CommonUtil.JudgeEmpty(wjgl.getSearch())){
//			String search = wjgl.getSearch();
//			sql.append(" and (a.filetitle like '%"+search+"%')");
//		}
//		if(CommonUtil.JudgeEmpty(wjgl.getFiletitle())){
//			sql.append(" and a.filetitle like '%"+wjgl.getFiletitle()+"%'");
//		}
//		return sql.toString();
//	}
	
	/**
	 * 
		 * 方法名称: queryFileDetail
		 * 内容摘要: 查询企业详细
		 * 创建人：caixuyang
		 * 创建日期： 2017年11月10日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/queryFileDetail")
	@ResponseBody
	public RetAjax queryFileDetail(HttpServletRequest request,Tb_wenjianguanli tb_wenjianguanli) throws UnsupportedEncodingException{
		HttpSession session = request.getSession();
		Tb_user user=(Tb_user)session.getAttribute("town_LoginData");
		List<Tb_wenjianguanli> tb_wenjianguanlis = this.fileService.queryFileDetail(tb_wenjianguanli);
		result = RetAjax.onQueryDetail(tb_wenjianguanlis);
		return result;
	}
	/**
	 * 
		 * 方法名称: updateFileInfo
		 * 内容摘要: 更新文件信息
		 * 创建人：caixuyang
		 * 创建日期： 2017年11月10日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	@RequestMapping("/updateFileInfo")
	@ResponseBody
	public RetAjax updateFileInfo(HttpServletRequest request,Tb_wenjianguanli tb_wenjianguanli,
			@RequestParam("comcertificatepic") MultipartFile[] files) {
		try {
			HttpSession session = request.getSession();
			Tb_user user=(Tb_user)session.getAttribute("town_LoginData");
			tb_wenjianguanli.setFileupdator(user.getNumber());
			String imgpath=fileupload.fileUpload(files, request,TypeStatusConstant.file,tb_wenjianguanli.getFiletext());
			tb_wenjianguanli.setFiletext(imgpath);
			int flag = this.fileService.updateFileInfo(tb_wenjianguanli);
			result = RetAjax.onDataBase(flag, 3);
		} catch (Exception e) {
			Log.error("error-------------updateFileInfo:"+e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 3);
		}
		return result;
	}
	/**
	 * 删除信息时更新信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/updateFileState")
	@ResponseBody
	public RetAjax updateFileState(HttpServletRequest request,Tb_wenjianguanli tb_wenjianguanli,@RequestParam("fileObj[]") String fileObj) { 
		try {
			int flag = this.fileService.updateFileState(fileObj);
			if(flag!=0){
				flag=1;
			}
			result = RetAjax.onDataBase(flag,3);
		} catch (Exception e) {
			Log.error("error----------updateFileState:" + e.getMessage());
			e.printStackTrace();
			result = RetAjax.onDataBase(0, 3);
		}
		return result; 
	} 
}
