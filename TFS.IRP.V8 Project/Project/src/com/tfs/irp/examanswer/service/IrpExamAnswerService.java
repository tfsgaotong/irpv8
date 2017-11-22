package com.tfs.irp.examanswer.service;

import com.tfs.irp.examanswer.entity.IrpExamAnswer;

public interface IrpExamAnswerService {
	/**
	 * 增加考生考试答案记录
	 * @param _irpExamAnswer
	 * @return
	 */
	public int addExamAnswer(IrpExamAnswer _irpExamAnswer);
	/**
	 * 根据条件获得一条考生答案记录
	 * @param _examid
	 * @param paperid
	 * @param questionbankid
	 * @return
	 */
	public IrpExamAnswer getIrpExamAnswer(Long _examid,Long paperid,Long questionbankid,Long _cruserid,String _examtiemss);
	/**
	 * 更新用户答案
	 * @param _irpExamAnswer
	 * @return
	 * @author   npz
	 * @date 2017年11月13日
	 */
	public int updateExamAnswer(IrpExamAnswer _irpExamAnswer);
	/**
	 * 根据条件获取考生答案
	 * @param _examid
	 * @param paperid
	 * @param questionbankid
	 * @param _cruserid
	 * @return
	 * @author   npz
	 * @date 2017年11月13日
	 */
	public IrpExamAnswer getIrpExamAnswer(Long _examid,Long paperid,Long questionbankid,Long _cruserid);
	

}
