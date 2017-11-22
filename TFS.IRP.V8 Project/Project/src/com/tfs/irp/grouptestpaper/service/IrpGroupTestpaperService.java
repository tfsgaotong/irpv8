package com.tfs.irp.grouptestpaper.service;

import java.util.List;

import com.tfs.irp.exam.entity.IrpExam;
import com.tfs.irp.grouptestpaper.entity.IrpGroupTestpaper;
import com.tfs.irp.util.PageUtil;

public interface IrpGroupTestpaperService {
	/**
	 * 增加试卷对应的权限关系
	 * @param _irpGroupTestpaper
	 * @return
	 */
	int addIrpGroupTestpaper(IrpGroupTestpaper _irpGroupTestpaper);
	/**
	 * 根据卷子id获得   组织对应关系的集合
	 * @param _testpaperid
	 * @param _isdel
	 * @return
	 */
	List<IrpGroupTestpaper> getGTPaperByTPId(Long _testpaperid,Integer _isdel);
	/**
	 * 根据卷子id删除和它所有的组织联系
	 * @param _testpaperid
	 * @return
	 */
	int deleteGPByTPId(Long _testpaperid);
	/**
	 * 根据试卷id和组织id查询相应的状态
	 * @param _groupid
	 * @param _testpaperid
	 * @return
	 */
	int selectStatusOfGTId(Long _groupid,Long _testpaperid);
	
	/**
	 * 根据组织id  找到相应的考试id号
	 * @param _goupid
	 * @param _isdel
	 * @return
	 */
	List<Long> getGroupTestpapeByGroup(Long _goupid,Integer _isdel,PageUtil _pageutil);
	/**
	 * 根据组织id  找到相应的考试id号数量
	 * @param _goupid
	 * @param _isdel
	 * @return
	 */
	int getGroupTestpapeByGroup(Long _goupid,Integer _isdel);

}
