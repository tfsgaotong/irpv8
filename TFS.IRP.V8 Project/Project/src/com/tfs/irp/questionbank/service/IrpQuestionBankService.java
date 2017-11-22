package com.tfs.irp.questionbank.service;

import java.util.List;

import com.tfs.irp.questionbank.entity.IrpQuestionBank;
import com.tfs.irp.util.PageUtil;

public interface IrpQuestionBankService {
	
	/**
	 * 题库中增加试题
	 * @param _irpQuestionBank
	 * @return
	 */
	boolean addQBank(IrpQuestionBank _irpQuestionBank);
	/**
	 * 分类下的题目个数(总数)
	 * @param _cateid
	 * @param _status
	 * @return
	 */
	int findQBankByCate(Long _cateid,Integer _status);
	/**
	 * 获取所有题库
	 * @param _cateid
	 * @param _status
	 * @return
	 */
	List<IrpQuestionBank> questionBankList(Long _cateid,Integer _status,PageUtil _pageutil,String _orderbystr, String _sSearchWord, String _sSearchType);
	/**
	 * 
	 * @param _cateid
	 * @param _status
	 * @param _sSearchWord
	 * @param _sSearchType
	 * @return
	 */
	int questionBankListNum(Long _cateid,Integer _status,String _sSearchWord, String _sSearchType);
	/**
	 * 根据id删除题目
	 * @param _qbid
	 * @return
	 */
	int deleteQBankById(Long _qbid);
	/**
	 * 根据id查对象
	 * @param _qbankid
	 * @return
	 */
	IrpQuestionBank getQuestionBankById(Long _qbankid);
	/**
	 * 根据id修改题库
	 * @param _qbankid
	 * @return
	 */
	int updateQBank(IrpQuestionBank _irpQuestionBank);
	/**
	 * 随机组卷
	 * @param choicenum
	 * @param boolquesnum
	 * @param fullquesnum
	 * @return
	 * @author   npz
	 * @date 2017年8月3日
	 */
	List<IrpQuestionBank> randomquestion(int Multiselect,int choicenum,int boolquesnum,int fullquesnum);
	
	
}
