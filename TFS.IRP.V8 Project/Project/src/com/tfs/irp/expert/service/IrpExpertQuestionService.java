package com.tfs.irp.expert.service;

import java.util.List;

import com.tfs.irp.expert.entity.IrpExpertQuestion;
import com.tfs.irp.question.entity.IrpQuestion;
import com.tfs.irp.util.PageUtil;

public interface IrpExpertQuestionService {
	/**
	 * 待解答的问题数
	 * @return
	 */
	public Integer totalQuestion(Long expertid) throws Exception;
	/**
	 * 待解答的问题列表
	 * @return
	 */
	public List<IrpQuestion> questionsOfExpertResolute(Long loginUserId) throws Exception;
	/**
	 * 待解答的问题列表
	 * @return
	 */
	public List<IrpQuestion> questionsOfExpertResolute(Long loginUserId,PageUtil pageUtil) throws Exception;
	
	/**
	 * 根据问题id获取相对应的对象
	 * @param _questionid
	 * @return
	 */
	public IrpExpertQuestion irpExpertQuestion(Long _questionid);
}
