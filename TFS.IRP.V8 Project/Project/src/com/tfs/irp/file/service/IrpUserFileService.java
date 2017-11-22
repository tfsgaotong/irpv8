package com.tfs.irp.file.service;

import java.util.List;

import com.tfs.irp.file.entity.IrpUserFile;
import com.tfs.irp.util.PageUtil;

public interface IrpUserFileService {
	
	/**
	 * 添加用户文件信息
	 * @param _fileName
	 */
	void insertUserFile(String _fileName);
	void insertUserFile1(String _fileName);
	
	/**
	 * 根据文件名，删除用户文件信息
	 * @param _fileName
	 */
	void deleteUserFileByFileName(String _fileName);
	/**
	 * 根据用户id和类型，查询用户附件
	 * @return
	 */
	List<IrpUserFile> userAllFileByType(Long _userId, Long _typeId ,PageUtil pageUtil);
 
	List<IrpUserFile> userAllFileByType(Long _userId, Long _typeId );
	
	int FileAmount(Long _userId, Long _typeId );

}
