package com.hkay.weifei.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	public String fileUpload(MultipartFile files, HttpServletRequest request) {
		String targetFilePath = "";
		if (files != null) {
			// 获得文件路径
			// String filePath = file.getPath();
			// 获得文件名称
			String fileName = files.getOriginalFilename();
			// 获得系统文件夹路径
			String path = request.getSession().getServletContext().getRealPath("upload");
			File sysFile = new File(path);
			if (!sysFile.exists()) {
				sysFile.mkdirs();
			}
			// 生成目标文件名
			Date sysDate = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			String targetFileName = format.format(sysDate) + fileName.substring(fileName.lastIndexOf("."));
			// 生成目标文件
			File targetFile = new File(sysFile, targetFileName);
			try {
				files.transferTo(targetFile);
				targetFilePath = request.getContextPath() + "/upload/" + targetFileName;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return targetFilePath;
		} else {
			return "";
		}
	}
}
