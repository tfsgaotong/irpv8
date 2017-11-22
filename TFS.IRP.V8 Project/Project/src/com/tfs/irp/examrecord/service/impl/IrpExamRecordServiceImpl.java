package com.tfs.irp.examrecord.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tfs.irp.examrecord.dao.IrpExamRecordDAO;
import com.tfs.irp.examrecord.entity.IrpExamRecord;
import com.tfs.irp.examrecord.entity.IrpExamRecordExample;
import com.tfs.irp.examrecord.service.IrpExamRecordService;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.PageUtil;

public class IrpExamRecordServiceImpl implements IrpExamRecordService {
	
	private IrpExamRecordDAO irpExamRecordDAO;

	public IrpExamRecordDAO getIrpExamRecordDAO() {
		return irpExamRecordDAO;
	}

	public void setIrpExamRecordDAO(IrpExamRecordDAO irpExamRecordDAO) {
		this.irpExamRecordDAO = irpExamRecordDAO;
	}

	@Override
	public int addIrpExamRecord(IrpExamRecord _irpExamRecord) {
		int msg = 0;
		
		if (_irpExamRecord!=null) {
			  LogUtil logUtil=new LogUtil("EXAM_RECORD_ADD",_irpExamRecord);
			try {
				this.irpExamRecordDAO.insertSelective(_irpExamRecord);
				logUtil.successLogs( "参加考试id为["+_irpExamRecord.getExamid()+"]记录["+logUtil.getLogUser()+"]成功");
				msg = 1;
			} catch (SQLException e) {
				e.printStackTrace();
				logUtil.errorLogs( "参加考试id为["+_irpExamRecord.getExamid()+"]记录["+logUtil.getLogUser()+"]失败",e);
			}
			
		}
		
		
		return msg;
	}

	@Override
	public List<IrpExamRecord> getExamRecordList(PageUtil _pageutil,
			Long _status, Long _userid) {
		// TODO Auto-generated method stub
		List<IrpExamRecord> list = null;
		
		IrpExamRecordExample example = new IrpExamRecordExample();
		example.createCriteria().andExtendoneEqualTo(_status)
								.andCruseridEqualTo(_userid);
		example.setOrderByClause("recordid desc");
		try {
			list = this.irpExamRecordDAO.selectByExample(example, _pageutil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int getExamRecordListnum(Long _status, Long _userid) {
		// TODO Auto-generated method stub
		int num = 0;
		IrpExamRecordExample example = new IrpExamRecordExample();
			example.createCriteria().andExtendoneEqualTo(_status)
									.andCruseridEqualTo(_userid);
		
		try {
			num = this.irpExamRecordDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return num;
	}

	@Override
	public IrpExamRecord getIrpExamRecordById(Long _recordid) {
		// TODO Auto-generated method stub
		IrpExamRecord irpExamRecord = null;
		
		if (_recordid!=null) {
			try {
				irpExamRecord = this.irpExamRecordDAO.selectByPrimaryKey(_recordid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return irpExamRecord;
	}

	@Override
	public List<IrpExamRecord> getExamRecordListMenu(PageUtil _pageutil,
			String _sSearchWord, String _sSearchType, String _orderstr) {
		// TODO Auto-generated method stub
		List<IrpExamRecord> list = null;
		
		IrpExamRecordExample example = new IrpExamRecordExample();
		example.setOrderByClause(_orderstr);
		
		try {
			list = this.irpExamRecordDAO.selectByExample(example, _pageutil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int getExamRecordListnumMeun(String _sSearchWord, String _sSearchType) {
		// TODO Auto-generated method stub
		int num = 0;
		IrpExamRecordExample example = new IrpExamRecordExample();
		try {
			num = this.irpExamRecordDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return num;
	}

	@Override
	public int deleteExamRecord(Long _recordid) {
		int msg = 0;
		if (_recordid!=null) {
			  LogUtil logUtil=null;
			  IrpExamRecord _irpExamRecord =null;
			try {
				 _irpExamRecord = irpExamRecordDAO.selectByPrimaryKey(_recordid);
				logUtil=new LogUtil("EXAM_RECORD_DEL",_irpExamRecord);
				msg = this.irpExamRecordDAO.deleteByPrimaryKey(_recordid);
				logUtil.successLogs( "删除考试id为["+_irpExamRecord.getExamid()+"]记录["+logUtil.getLogUser()+"]成功");
			} catch (SQLException e) {
				e.printStackTrace();
				logUtil.errorLogs( "删除考试id为["+_irpExamRecord.getExamid()+"]记录["+logUtil.getLogUser()+"]失败",e);
			}
			
		}
		return msg;
	}

	@Override
	public int updateExamRecordStatus(Long _recordid, Long _upmsg) {
		int msg = 0;
		IrpExamRecord record = new IrpExamRecord();
		record.setRecordid(_recordid);
		record.setExtendone(_upmsg);
		  LogUtil logUtil=null;
		  IrpExamRecord _irpExamRecord =null;
		try {
			 _irpExamRecord = irpExamRecordDAO.selectByPrimaryKey(_recordid);
				logUtil=new LogUtil("EXAM_RECORD_UPDATE",_irpExamRecord);
			msg = this.irpExamRecordDAO.updateByPrimaryKeySelective(record);
			logUtil.successLogs( "发布考试id为["+_irpExamRecord.getExamid()+"]记录["+logUtil.getLogUser()+"]成功");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs( "发布考试id为["+_irpExamRecord.getExamid()+"]记录["+logUtil.getLogUser()+"]失败",e);
		}
		return msg;
	}

	@Override
	public int findExamRecordByexamidanduserid(Long examid, Long userid) {
		IrpExamRecordExample example = new IrpExamRecordExample();
		example.createCriteria().andExamidEqualTo(examid).andCruseridEqualTo(userid);
		try {
			int record = this.irpExamRecordDAO.countByExample(example);
			return record;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public IrpExamRecord findExamRecordbyexamidanduserid(Long examid,
			Long userid) {
		IrpExamRecordExample example = new IrpExamRecordExample();
		example.createCriteria().andExamidEqualTo(examid).andCruseridEqualTo(userid);
		List<IrpExamRecord> irpExamRecord = new ArrayList<IrpExamRecord>();
		IrpExamRecord record=null;
		try {
			irpExamRecord = this.irpExamRecordDAO.selectByExample(example);
			if(irpExamRecord.size()>0){
				record = irpExamRecord.get(0); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return record;
	}

	@Override
	public int updateIrpExamRecord(IrpExamRecord _irpExamRecord) {
		int msg = 0;
		
		if (_irpExamRecord!=null) {
			  LogUtil logUtil=new LogUtil("EXAM_RECORD_ADD",_irpExamRecord);
			try {
				this.irpExamRecordDAO.updateByPrimaryKey(_irpExamRecord);
				msg = 1;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		return msg;
	}

}
