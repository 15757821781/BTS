package com.hkay.weifei.service;

import java.util.List;

import com.hkay.weifei.pojo.Tb_wenjianguanli;

public interface FileService {

	int insertFileInfo(Tb_wenjianguanli wenjianguanli);

	List<Tb_wenjianguanli> queryFileList(Tb_wenjianguanli tb_wenjianguanli);

	int queryfilecnt(Tb_wenjianguanli tb_wenjianguanli);

	List<Tb_wenjianguanli> queryFileDetail(Tb_wenjianguanli tb_wenjianguanli);

	int updateFileInfo(Tb_wenjianguanli tb_wenjianguanli);

	int updateFileState(String fileObj);

}
