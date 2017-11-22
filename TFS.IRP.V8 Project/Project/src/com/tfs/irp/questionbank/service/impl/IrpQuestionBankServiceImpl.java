package com.tfs.irp.questionbank.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tfs.irp.questionbank.dao.IrpQuestionBankDAO;
import com.tfs.irp.questionbank.entity.IrpQuestionBank;
import com.tfs.irp.questionbank.entity.IrpQuestionBankExample;
import com.tfs.irp.questionbank.service.IrpQuestionBankService;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.PageUtil;

public class IrpQuestionBankServiceImpl implements IrpQuestionBankService {
	
	
	private IrpQuestionBankDAO irpQuestionBankDAO;

	public IrpQuestionBankDAO getIrpQuestionBankDAO() {
		return irpQuestionBankDAO;
	}

	public void setIrpQuestionBankDAO(IrpQuestionBankDAO irpQuestionBankDAO) {
		this.irpQuestionBankDAO = irpQuestionBankDAO;
	}

	@Override
	public boolean addQBank(IrpQuestionBank _irpQuestionBank) {
		boolean flag = false;
		  LogUtil logUtil=new LogUtil("QUESTION_BANK_ADD",_irpQuestionBank);
		if (_irpQuestionBank!=null) {
		 try {
			this.irpQuestionBankDAO.insertSelective(_irpQuestionBank);
			flag = true;
			logUtil.successLogs( "为分类id["+_irpQuestionBank.getQbcate()+"]添加考试题["+logUtil.getLogUser()+"]成功");
			} catch (SQLException e) {
				e.printStackTrace();
				logUtil.errorLogs( "为分类id["+_irpQuestionBank.getQbcate()+"]添加考试题["+logUtil.getLogUser()+"]失败",e);
			}
		}
		return flag;
	}

	@Override
	public int findQBankByCate(Long _cateid, Integer _status) {
		// TODO Auto-generated method stub
		int num = 0;
		IrpQuestionBankExample example = new IrpQuestionBankExample();
		if (_cateid!=null) {
			example.createCriteria().andQbstatusEqualTo(_status)
								.andQbcateEqualTo(_cateid);
		}else{
			example.createCriteria().andQbstatusEqualTo(_status);
			
		}

		
		try {
			num = this.irpQuestionBankDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return num;
	}

	@Override
	public List<IrpQuestionBank> questionBankList(Long _cateid, Integer _status,PageUtil _pageutil,String _orderbystr, String _sSearchWord, String _sSearchType) {
		// TODO Auto-generated method stub
		List<IrpQuestionBank> list = null;
		IrpQuestionBankExample example = new IrpQuestionBankExample();
		if (_sSearchType.equals("all")) {
			if (_cateid!=null) {
				example.or(example.createCriteria().andQuestiontextLike("%"+_sSearchWord+"%").andQbcateEqualTo(_cateid).andQbstatusEqualTo(_status));
				example.or(example.createCriteria().andQexplainLike("%"+_sSearchWord+"%").andQbcateEqualTo(_cateid).andQbstatusEqualTo(_status));
			}else{
				example.or(example.createCriteria().andQuestiontextLike("%"+_sSearchWord+"%").andQbstatusEqualTo(_status));
				example.or(example.createCriteria().andQexplainLike("%"+_sSearchWord+"%").andQbstatusEqualTo(_status));
			}
		}else if(_sSearchType.equals("qtext")){
			if (_cateid!=null) {
				example.createCriteria().andQbcateEqualTo(_cateid)
										.andQbstatusEqualTo(_status)
										.andQuestiontextLike("%"+_sSearchWord+"%");
			}else{
				example.createCriteria().andQbstatusEqualTo(_status).andQuestiontextLike("%"+_sSearchWord+"%");
			}
		}else if(_sSearchType.equals("qexplain")){
			if (_cateid!=null) {
				example.createCriteria().andQbcateEqualTo(_cateid)
										.andQbstatusEqualTo(_status)
										.andQexplainLike("%"+_sSearchWord+"%");
			}else{
				example.createCriteria().andQbstatusEqualTo(_status).andQexplainLike("%"+_sSearchWord+"%");
			}
		}else{
			if (_cateid!=null) {
				example.createCriteria().andQbcateEqualTo(_cateid)
										.andQbstatusEqualTo(_status);
			}else{
				example.createCriteria().andQbstatusEqualTo(_status);
			}
		}	

		
		example.setOrderByClause(_orderbystr);
		try {
			list = this.irpQuestionBankDAO.selectByExample(example, _pageutil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int deleteQBankById(Long _qbid) {
		int status = 0;
		if (_qbid!=null) {
			LogUtil logUtil=null;
			IrpQuestionBank	_irpQuestionBank=null;
			try {
					_irpQuestionBank=irpQuestionBankDAO.selectByPrimaryKey(_qbid);
				logUtil=new LogUtil("QUESTION_BANK_ADD",_irpQuestionBank);
				status = this.irpQuestionBankDAO.deleteByPrimaryKey(_qbid);
				logUtil.successLogs( "为分类id["+_irpQuestionBank.getQbcate()+"]删除考试题["+logUtil.getLogUser()+"]成功");
			} catch (SQLException e) {
				e.printStackTrace();
				logUtil.errorLogs( "为分类id["+_irpQuestionBank.getQbcate()+"]删除考试题["+logUtil.getLogUser()+"]失败");
			}
		}
		return status;
	}

	@Override
	public int questionBankListNum(Long _cateid, Integer _status,
			String _sSearchWord, String _sSearchType) {
		int num = 0;
		IrpQuestionBankExample example = new IrpQuestionBankExample();
		if (_sSearchType.equals("all")) {
			if (_cateid!=null) {
				example.or(example.createCriteria().andQuestiontextLike("%"+_sSearchWord+"%").andQbcateEqualTo(_cateid).andQbstatusEqualTo(_status));
				example.or(example.createCriteria().andQexplainLike("%"+_sSearchWord+"%").andQbcateEqualTo(_cateid).andQbstatusEqualTo(_status));
			}else{
				example.or(example.createCriteria().andQuestiontextLike("%"+_sSearchWord+"%").andQbstatusEqualTo(_status));
				example.or(example.createCriteria().andQexplainLike("%"+_sSearchWord+"%").andQbstatusEqualTo(_status));
			}
		}else if(_sSearchType.equals("qtext")){
			if (_cateid!=null) {
				example.createCriteria().andQbcateEqualTo(_cateid)
										.andQbstatusEqualTo(_status)
										.andQuestiontextLike("%"+_sSearchWord+"%");
			}else{
				example.createCriteria().andQbstatusEqualTo(_status).andQuestiontextLike("%"+_sSearchWord+"%");
			}
		}else if(_sSearchType.equals("qexplain")){
			if (_cateid!=null) {
				example.createCriteria().andQbcateEqualTo(_cateid)
										.andQbstatusEqualTo(_status)
										.andQexplainLike("%"+_sSearchWord+"%");
			}else{
				example.createCriteria().andQbstatusEqualTo(_status).andQexplainLike("%"+_sSearchWord+"%");
			}
		}else{
			if (_cateid!=null) {
				example.createCriteria().andQbcateEqualTo(_cateid)
										.andQbstatusEqualTo(_status);
			}else{
				example.createCriteria().andQbstatusEqualTo(_status);
			}
		}	

		
		try {
			num = this.irpQuestionBankDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return num;
	}

	@Override
	public IrpQuestionBank getQuestionBankById(Long _qbankid) {
		// TODO Auto-generated method stub
		IrpQuestionBank irpQuestionBank = null;
		if (_qbankid!=null) {
			try {
				irpQuestionBank = this.irpQuestionBankDAO.selectByPrimaryKey(_qbankid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return irpQuestionBank;
	}

	@Override
	public int updateQBank(IrpQuestionBank _irpQuestionBank) {
		  LogUtil logUtil=new LogUtil("QUESTION_BANK_UPDATE",_irpQuestionBank);
		int status = 0;
		if (_irpQuestionBank!=null) {
			try {
				status = this.irpQuestionBankDAO.updateByPrimaryKeySelective(_irpQuestionBank);
				logUtil.successLogs( "修改考试题["+logUtil.getLogUser()+"]成功");
			} catch (SQLException e) {
				e.printStackTrace();
				logUtil.errorLogs( "修改考试题["+logUtil.getLogUser()+"]失败");
			}
		}
		return status;
	}

	@Override
	public List<IrpQuestionBank> randomquestion(int Multiselect,int choicenum, int boolquesnum,
			int fullquesnum) {
		List<IrpQuestionBank> list = new ArrayList<IrpQuestionBank>();
		try {
			List<IrpQuestionBank> Multiselectlist = this.irpQuestionBankDAO.randomtestpaper(10,Multiselect);
			List<IrpQuestionBank> choicelist = this.irpQuestionBankDAO.randomtestpaper(20,choicenum);
			List<IrpQuestionBank> boolqueslist = this.irpQuestionBankDAO.randomtestpaper(40, boolquesnum);
			List<IrpQuestionBank> fullqueslist = this.irpQuestionBankDAO.randomtestpaper(30, fullquesnum);
			list.addAll(Multiselectlist);
			list.addAll(choicelist);
			list.addAll(boolqueslist);
			list.addAll(fullqueslist);
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
