package com.tfs.irp.expert.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tfs.irp.expert.dao.IrpExpertQuestionDAO;
import com.tfs.irp.expert.entity.IrpExpertQuestion;
import com.tfs.irp.expert.entity.IrpExpertQuestionExample;
import com.tfs.irp.expert.service.IrpExpertQuestionService;
import com.tfs.irp.question.dao.IrpQuestionDAO;
import com.tfs.irp.question.entity.IrpQuestion;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.PageUtil;

public class IrpExpertQuestionServiceImpl implements IrpExpertQuestionService {
	private IrpExpertQuestionDAO irpExpertQuestionDAO;
	private List<IrpExpertQuestion> irpQuestionList;
	private IrpQuestionDAO irpQuestionDAO;

	public IrpQuestionDAO getIrpQuestionDAO() {
		return irpQuestionDAO;
	}

	public void setIrpQuestionDAO(IrpQuestionDAO irpQuestionDAO) {
		this.irpQuestionDAO = irpQuestionDAO;
	}

	public List<IrpExpertQuestion> getIrpQuestionList() {
		return irpQuestionList;
	}

	public void setIrpQuestionList(List<IrpExpertQuestion> irpQuestionList) {
		this.irpQuestionList = irpQuestionList;
	}

	public IrpExpertQuestionDAO getIrpExpertQuestionDAO() {
		return irpExpertQuestionDAO;
	}

	public void setIrpExpertQuestionDAO(
			IrpExpertQuestionDAO irpExpertQuestionDAO) {
		this.irpExpertQuestionDAO = irpExpertQuestionDAO;
	}

	@Override
	public Integer totalQuestion(Long expertid) {
		int total = 0;
		try {
			Map<String, Object> mParam = new HashMap<String, Object>();
			mParam.put("loginUserId", expertid);
			total = irpExpertQuestionDAO.countExpertQuestion(mParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	@Override
	public List<IrpQuestion> questionsOfExpertResolute(Long loginUserId)
			throws Exception {
		List<IrpQuestion> questions = null;
		Map<String, Object> mParam = new HashMap<String, Object>();
		mParam.put("loginUserId", loginUserId);
		mParam.put("orderStr", "crtime desc");
		try {
			questions = irpExpertQuestionDAO.findQuestionsOfExpert(mParam);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questions;
	}

	@Override
	public List<IrpQuestion> questionsOfExpertResolute(Long loginUserId,
			PageUtil pageUtil) throws Exception {
		List<IrpQuestion> questions = null;
		Map<String, Object> mParam = new HashMap<String, Object>();
		mParam.put("loginUserId", loginUserId);
		mParam.put("orderStr", "crtime desc");
		try {
			questions = irpExpertQuestionDAO.findQuestionsOfExpert(mParam,pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questions;
	}

	@Override
	public IrpExpertQuestion irpExpertQuestion(Long _questionid) {
		// TODO Auto-generated method stub
		IrpExpertQuestion irpExpertQuestion = null;
		IrpExpertQuestionExample example = new IrpExpertQuestionExample();
		example.createCriteria().andCruseridIsNotNull()
		                        .andQuestionidEqualTo(_questionid);
		try {
			if(this.irpExpertQuestionDAO.countByExample(example)==0){
				return irpExpertQuestion;
			}
			irpExpertQuestion = this.irpExpertQuestionDAO.selectByExample(example).get(0);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return irpExpertQuestion;
	}

}
