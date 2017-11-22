package com.tfs.irp.exam.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.exam.dao.IrpExamDAO;
import com.tfs.irp.exam.entity.IrpExam;
import com.tfs.irp.exam.entity.IrpExamExample;
import com.tfs.irp.exam.service.IrpExamService;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.PageUtil;

public class IrpExamServiceImpl implements IrpExamService {
	
	private IrpExamDAO irpExamDAO;

	public IrpExamDAO getIrpExamDAO() {
		return irpExamDAO;
	}

	public void setIrpExamDAO(IrpExamDAO irpExamDAO) {
		this.irpExamDAO = irpExamDAO;
	}

	@Override
	public int getExamNumByCate(Long _cateid) {
		// TODO Auto-generated method stub
		int num = 0;
		IrpExamExample example = new IrpExamExample();
		example.createCriteria().andExamcateEqualTo(_cateid);
		
		try {
			num = this.irpExamDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return num;
	}

	@Override
	public List<IrpExam> getExamByCateId(Long _cateid, String _sSearchWord,
			String _sSearchType,String _orderstr, PageUtil _pageutil) {
		// TODO Auto-generated method stub
		List<IrpExam> list = null;
		IrpExamExample example = new IrpExamExample();
		
		if (_sSearchType.equals("examname")) {
			example.createCriteria().andExamcateEqualTo(_cateid).andExamnameLike("%"+_sSearchWord+"%");
		}else{
			example.createCriteria().andExamcateEqualTo(_cateid);
			
		}
		example.setOrderByClause(_orderstr);
		try {
			list = this.irpExamDAO.selectByExample(example, _pageutil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int getExamByCateIdNum(Long _cateid, String _sSearchWord,
			String _sSearchType) {
		// TODO Auto-generated method stub
		int num = 0;
		IrpExamExample example = new IrpExamExample();
		
		if (_sSearchType.equals("examname")) {
			example.createCriteria().andExamcateEqualTo(_cateid).andExamnameLike("%"+_sSearchWord+"%");
		}else{
			example.createCriteria().andExamcateEqualTo(_cateid);
			
		}
		try {
			num = this.irpExamDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int addExam(IrpExam _irpexam) {
		int msg = 0;
		if (_irpexam!=null) {
			  LogUtil logUtil=new LogUtil("EXAM_ADD",_irpexam);
			 try {
				this.irpExamDAO.insertSelective(_irpexam);
				msg = 1;
				logUtil.successLogs( "添加考试["+logUtil.getLogUser()+"]成功");
				} catch (SQLException e) {
					e.printStackTrace();
					logUtil.errorLogs( "添加考试["+logUtil.getLogUser()+"]失败",e);
				} 
		}
		return msg;
	}

	@Override
	public IrpExam getIrpExamById(Long _examid) {
		// TODO Auto-generated method stub
		IrpExam irpexam = new IrpExam();
		try {
			if (_examid!=null) {
				irpexam = this.irpExamDAO.selectByPrimaryKey(_examid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return irpexam;
	}

	@Override
	public int deleteExamById(Long _examid) {
		int status = 0;
		if (_examid!=null) {
			 LogUtil logUtil=null;
			try {
				IrpExam _irpexam=irpExamDAO.selectByPrimaryKey(_examid);
				logUtil=new LogUtil("EXAM_DEL",_irpexam);
				status = this.irpExamDAO.deleteByPrimaryKey(_examid);
				logUtil.successLogs( "删除考试["+logUtil.getLogUser()+"]成功");
			} catch (SQLException e) {
				e.printStackTrace();
				logUtil.errorLogs( "删除考试["+logUtil.getLogUser()+"]失败",e);
			}
		}
		return status;
	}

	@Override
	public int updateExamById(IrpExam _irpexam) {
		int msg = 0;
		if (_irpexam!=null) {
			 LogUtil logUtil=new LogUtil("EXAM_UPDATE",_irpexam);
			try {
				msg = this.irpExamDAO.updateByPrimaryKeySelective(_irpexam);
				logUtil.successLogs( "修改考试["+logUtil.getLogUser()+"]成功");
			} catch (SQLException e) {
				e.printStackTrace();
				logUtil.errorLogs( "修改考试["+logUtil.getLogUser()+"]失败",e);
			} 
		}
		return msg;
	}

	@Override
	public List<IrpExam> findAllList(Long cruserid, Long Groupid, Integer IsDel,PageUtil pageutile) {
		String condition = "(SELECT * from (SELECT * from IRP_EXAM where EXAMID IN"+ 
						   "(SELECT TESTPAPERID from IRP_GROUP_TESTPAPER WHERE GROUPID="+Groupid+" and ISDEL="+IsDel+")) t WHERE"+
						   "(SYSDATE>BEGINTIME AND ENDTIME IS NULL) OR (BEGINTIME is 			NULL AND ENDTIME is NULL) OR "+
						   "(SYSDATE<ENDTIME)) s WHERE examid NOT IN "+
						   "(SELECT examid from IRP_EXAM_RECORD WHERE cruserid="+cruserid+")";
		List<IrpExam> examlist = irpExamDAO.selectByUser(condition,pageutile);
		return examlist;
	}

	@Override
	public int findAllList(Long cruserid, Long Groupid, Integer IsDel) {
		String condition = "(SELECT * from (SELECT * from IRP_EXAM where EXAMID IN"+ 
				   "(SELECT TESTPAPERID from IRP_GROUP_TESTPAPER WHERE GROUPID="+Groupid+" and ISDEL="+IsDel+")) t WHERE"+
				   "(SYSDATE>BEGINTIME AND ENDTIME IS NULL) OR (BEGINTIME is 			NULL AND ENDTIME is NULL) OR "+
				   "(SYSDATE<ENDTIME)) s WHERE examid NOT IN "+
				   "(SELECT examid from IRP_EXAM_RECORD WHERE cruserid="+cruserid+") ORDER BY BEGINTIME";
		int num = irpExamDAO.selectByUser(condition);
		return num;
	}
	
	

}
