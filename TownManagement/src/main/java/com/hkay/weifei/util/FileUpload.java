package com.hkay.weifei.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	public String fileUpload(MultipartFile[] files, HttpServletRequest request,String syspath) {
		String targetFilePath = "";
		if (files != null) {
			// 获得日期
			Date sysDate = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			// 获得系统文件夹路径
			String path = request.getSession().getServletContext().getRealPath("upload/"+syspath);
			File sysFile = new File(path);
			if (!sysFile.exists()) {
				sysFile.mkdirs();
			}
			for (int i = 0; i < files.length; i++) {
				// 获得文件名称，判断是否存在
				String fileName = files[i].getOriginalFilename();
				// 获得文件在服务器上的路径
				String filePath = request.getSession().getServletContext().getRealPath("upload/"+syspath+fileName);
				File file = new File(filePath);
				if (file.exists()) {
					continue;
				}
				// 生成目标文件名
				String targetFileName = format.format(sysDate) +"_"+FileUpload.getSixRandom()+ fileName.substring(fileName.lastIndexOf("."));
				// 生成目标文件
				File targetFile = new File(sysFile, targetFileName);
				try {
					files[i].transferTo(targetFile);
					System.out.println("保存的文件名:"+targetFileName);
					targetFilePath += request.getContextPath() + "/upload/"+syspath + targetFileName+",";
				} catch (IOException e1) {
					System.out.println("图片上传失败!");
					e1.printStackTrace();
				}
			}
			return targetFilePath;
		} else {
			return "";
		}
	}
	
	/**
	 * 
		 * 方法名称: getSixRandom
		 * 内容摘要: 获得6位随机数
		 * 创建人：zhuwenjie
		 * 创建日期： 2017年4月28日
		 * 修改人：
		 * 修改内容：
		 * 修改日期：
	 */
	public static String getSixRandom() {
		long rand = Math.round(Math.random() * 100000 + 1);
		String result = String.format("%1$06d", rand);
		return result;
	}
}
