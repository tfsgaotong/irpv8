package com.tfs.irp.examrecord.service;

import java.util.List;

import com.tfs.irp.examrecord.entity.IrpExamRecord;
import com.tfs.irp.util.PageUtil;

public interface IrpExamRecordService {
	/**
	 * 增加考试记录
	 * @param _irpExamRecord
	 * @return
	 */
	int addIrpExamRecord(IrpExamRecord _irpExamRecord);
	/**
	 * 考试记录集合
	 * @param _pageutil
	 * @param _status
	 * @param _userid
	 * @return
	 */
	List<IrpExamRecord> getExamRecordList(PageUtil _pageutil,Long _status,Long _userid);
	/**
	 * 考试记录集合数量
	 * @param _pageutil
	 * @param _status
	 * @param _userid
	 * @return
	 */
	int getExamRecordListnum(Long _status,Long _userid);
	/**
	 * 后台考试记录集合
	 * @param _pageutil
	 * @return
	 */
	List<IrpExamRecord> getExamRecordListMenu(PageUtil _pageutil,String _sSearchWord, String _sSearchType,String _orderstr);
	/**
	 * 后台考试记录集合数量
	 * @return
	 */
	int getExamRecordListnumMeun(String _sSearchWord, String _sSearchType);
	/**
	 * 根据主键获取对象
	 * @param _recordid
	 * @return
	 */
	IrpExamRecord getIrpExamRecordById(Long _recordid);
	/**
	 * 删除考试记录
	 * @param _recordid
	 * @return
	 */
	int deleteExamRecord(Long _recordid);
	/**
	 * 发布考试记录
	 * @param _recordid
	 * @param _upmsg
	 * @return
	 */
	int updateExamRecordStatus(Long _recordid,Long _upmsg);
	/**
	 * 根据考试id和用户id查找考试记录
	 * @param examid
	 * @param userid
	 * @return
	 * @author   npz
	 * @date 2017年10月25日
	 */
	int findExamRecordByexamidanduserid(Long examid,Long userid);
	IrpExamRecord findExamRecordbyexamidanduserid(Long examid,Long userid);
	/**
	 * 更新考试记录
	 * @param _irpExamRecord
	 * @return
	 * @author   npz
	 * @date 2017年11月13日
	 */
	int updateIrpExamRecord(IrpExamRecord _irpExamRecord);
}
