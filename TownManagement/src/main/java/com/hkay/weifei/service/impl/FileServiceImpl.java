package com.hkay.weifei.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkay.weifei.Dao.FileDao;
import com.hkay.weifei.pojo.Tb_wenjianguanli;
import com.hkay.weifei.service.FileService;

@Service("FileService")

public class FileServiceImpl implements FileService{
	@Resource
	private FileDao fileDao;
	
	@Override
	public int insertFileInfo(Tb_wenjianguanli wenjianguanli) {
		// TODO Auto-generated method stub
		return this.fileDao.insertFileInfo(wenjianguanli);
	}

	@Override
	public List<Tb_wenjianguanli> queryFileList(Tb_wenjianguanli tb_wenjianguanli) {
		// TODO Auto-generated method stub
		return this.fileDao.queryFileList(tb_wenjianguanli);
	}

	@Override
	public int queryfilecnt(Tb_wenjianguanli tb_wenjianguanli) {
		// TODO Auto-generated method stub
		return this.fileDao.queryfilecnt(tb_wenjianguanli);
	}

	@Override
	public List<Tb_wenjianguanli> queryFileDetail(Tb_wenjianguanli tb_wenjianguanli) {
		// TODO Auto-generated method stub
		return this.fileDao.queryFileDetail(tb_wenjianguanli);
	}

	@Override
	public int updateFileInfo(Tb_wenjianguanli tb_wenjianguanli) {
		// TODO Auto-generated method stub
		return this.fileDao.updateFileInfo(tb_wenjianguanli);
	}

	@Override
	public int updateFileState(String fileObj) {
		// TODO Auto-generated method stub
		return this.fileDao.updateFileState(fileObj);
	}
}
