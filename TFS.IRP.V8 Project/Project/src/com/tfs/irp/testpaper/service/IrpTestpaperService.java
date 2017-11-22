package com.tfs.irp.testpaper.service;

import java.util.List;

import com.tfs.irp.testpaper.entity.IrpTestpaper;
import com.tfs.irp.util.PageUtil;

public interface IrpTestpaperService {
	/**
	 * 增加试卷
	 * @param _irpTestpaper
	 * @return
	 */
	int addTestPaper(IrpTestpaper _irpTestpaper);
	/**
	 * 卷子集合总个数
	 * @param _pageutil
	 * @return
	 */	
	int getIrpTestpaperListNum(Long _cateid,Integer _status,String _sSearchWord, String _sSearchType);
	/**
	 * 卷子集合
	 * @param _pageutil
	 * @return
	 */
	List<IrpTestpaper> getIrpTestpaperList(Long _cateid,Integer _status,PageUtil _pageutil,String _orderbystr, String _sSearchWord, String _sSearchType);
	/**
	 * 根据id获得卷子对象
	 * @param _paperid
	 * @return
	 */
	IrpTestpaper getIrpTestpaper(Long _paperid);
	/**
	 * 获得该分类下的试卷数量
	 * @param _cateid
	 * @return
	 */
	int getPCountByCate(Long _cateid);
	/**
	 * 根据id删除
	 * @param _paperid
	 * @return
	 */
	int deleteTestPaper(Long _paperid);
	/**
	 * 修改试卷
	 * @param _irpTestpaper
	 * @return
	 */
	int updatePaper(IrpTestpaper _irpTestpaper);

}
