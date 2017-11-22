package com.tfs.irp.exam.service;

import java.util.List;

import com.tfs.irp.exam.entity.IrpExam;
import com.tfs.irp.util.PageUtil;

public interface IrpExamService {
	
	
	
	/**
	 * 获得该分类下的考试数量
	 * @param _cateid
	 * @return
	 */
	public int getExamNumByCate(Long _cateid);
	/**
	 * 分类查询
	 * @param _cateid
	 * @param _sSearchWord
	 * @param _sSearchType
	 * @param _pageutil
	 * @return
	 */
	public List<IrpExam> getExamByCateId(Long _cateid,String _sSearchWord, String _sSearchType,String _orderstr,PageUtil _pageutil);
	/**
	 * 分类查询数量
	 * @param _cateid
	 * @param _sSearchWord
	 * @param _sSearchType
	 * @return
	 */
	public int getExamByCateIdNum(Long _cateid,String _sSearchWord, String _sSearchType);
	/**
	 * 增加考试
	 * @param _irpexam
	 * @return
	 */
	public int addExam(IrpExam _irpexam);
	/**
	 * 根据id获得试卷的对象
	 * @param _examid
	 * @return
	 */
	public IrpExam getIrpExamById(Long _examid);
	
	/**
	 * 删除考试
	 * @param _examid
	 * @return
	 */
	public int deleteExamById(Long _examid);
	/**
	 * 修改考试
	 * @param _examid
	 * @return
	 */
	public int updateExamById(IrpExam _irpexam);
	/**
	 * 查找用户考试
	 * @param cruserid
	 * @param Groupid
	 * @param IsDel
	 * @param pageutile
	 * @return
	 * @author   npz
	 * @date 2017年10月16日
	 */
	public List<IrpExam> findAllList(Long cruserid,Long Groupid,Integer IsDel,PageUtil pageutile);
	public int findAllList(Long cruserid,Long Groupid,Integer IsDel);

}
