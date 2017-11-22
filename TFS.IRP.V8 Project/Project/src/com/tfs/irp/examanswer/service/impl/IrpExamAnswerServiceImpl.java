package com.tfs.irp.examanswer.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tfs.irp.examanswer.dao.IrpExamAnswerDAO;
import com.tfs.irp.examanswer.entity.IrpExamAnswer;
import com.tfs.irp.examanswer.entity.IrpExamAnswerExample;
import com.tfs.irp.examanswer.service.IrpExamAnswerService;

public class IrpExamAnswerServiceImpl implements IrpExamAnswerService {
	
	private IrpExamAnswerDAO irpExamAnswerDAO;

	public IrpExamAnswerDAO getIrpExamAnswerDAO() {
		return irpExamAnswerDAO;
	}

	public void setIrpExamAnswerDAO(IrpExamAnswerDAO irpExamAnswerDAO) {
		this.irpExamAnswerDAO = irpExamAnswerDAO;
	}

	@Override
	public int addExamAnswer(IrpExamAnswer _irpExamAnswer) {
		// TODO Auto-generated method stub
		int status = 0;
		try {
			if (_irpExamAnswer!=null) {
				this.irpExamAnswerDAO.insertSelective(_irpExamAnswer);
				status = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public IrpExamAnswer getIrpExamAnswer(Long _examid, Long paperid,
			Long questionbankid,Long _cruserid,String _examtiemss) {
		// TODO Auto-generated method stub
		IrpExamAnswer irpexamanswer = null;
		IrpExamAnswerExample example = new IrpExamAnswerExample();
		example.createCriteria().andExamidEqualTo(_examid)
								.andPaperidEqualTo(paperid)
								.andQuestionbankidEqualTo(questionbankid)
								.andCruseridEqualTo(_cruserid)
								.andExamidtimesEqualTo(_examtiemss);
		try {					
			List<IrpExamAnswer> list =  this.irpExamAnswerDAO.selectByExample(example);
			if (list.size()>0) {
				irpexamanswer = list.get(0);
			}
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return irpexamanswer;
	}

	@Override
	public int updateExamAnswer(IrpExamAnswer _irpExamAnswer) {
		// TODO Auto-generated method stub
				int status = 0;
				try {
					if (_irpExamAnswer!=null) {
						this.irpExamAnswerDAO.updateByPrimaryKey(_irpExamAnswer);
						status = 1;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return status;
	}

	@Override
	public IrpExamAnswer getIrpExamAnswer(Long _examid, Long paperid,
			Long questionbankid, Long _cruserid) {
		IrpExamAnswer irpexamanswer = null;
		IrpExamAnswerExample example = new IrpExamAnswerExample();
		example.createCriteria().andExamidEqualTo(_examid)
								.andPaperidEqualTo(paperid)
								.andQuestionbankidEqualTo(questionbankid)
								.andCruseridEqualTo(_cruserid);
		try {					
			List<IrpExamAnswer> list =  this.irpExamAnswerDAO.selectByExample(example);
			if (list.size()>0) {
				irpexamanswer = list.get(0);
			}
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return irpexamanswer;
	}
	
}
