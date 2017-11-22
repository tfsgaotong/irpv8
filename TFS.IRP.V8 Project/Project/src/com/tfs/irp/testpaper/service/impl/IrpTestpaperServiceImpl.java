package com.tfs.irp.testpaper.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.testpaper.dao.IrpTestpaperDAO;
import com.tfs.irp.testpaper.entity.IrpTestpaper;
import com.tfs.irp.testpaper.entity.IrpTestpaperExample;
import com.tfs.irp.testpaper.service.IrpTestpaperService;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.PageUtil;

public class IrpTestpaperServiceImpl implements IrpTestpaperService {
	
	private IrpTestpaperDAO irpTestpaperDAO;

	public IrpTestpaperDAO getIrpTestpaperDAO() {
		return irpTestpaperDAO;
	}

	public void setIrpTestpaperDAO(IrpTestpaperDAO irpTestpaperDAO) {
		this.irpTestpaperDAO = irpTestpaperDAO;
	}

	@Override
	public int addTestPaper(IrpTestpaper _irpTestpaper) {
		int msg = 0;
		 LogUtil logUtil=new LogUtil("TESTPAPER_ADD",_irpTestpaper);
		if (_irpTestpaper!=null) {
			
			try {
				this.irpTestpaperDAO.insertSelective(_irpTestpaper);
				msg = 1;
				logUtil.successLogs( "为分类id["+_irpTestpaper.getPapercate()+"]添加试卷["+logUtil.getLogUser()+"]成功");
			} catch (SQLException e) {
				e.printStackTrace();
				logUtil.errorLogs("为分类id["+_irpTestpaper.getPapercate()+"]添加试卷["+logUtil.getLogUser()+"]失败",e);
			}
		}
		
		return msg;
	}


	@Override
	public IrpTestpaper getIrpTestpaper(Long _paperid) {
		// TODO Auto-generated method stub
		IrpTestpaper irpTestpaper = null;
		
		if (_paperid!=null) {
			try {
				irpTestpaper = this.irpTestpaperDAO.selectByPrimaryKey(_paperid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return irpTestpaper;
	}

	@Override
	public int getPCountByCate(Long _cateid) {
		// TODO Auto-generated method stub
		int num = 0;
		IrpTestpaperExample example = new IrpTestpaperExample();
		example.createCriteria().andPapercateEqualTo(_cateid);
		
		try {
			num = this.irpTestpaperDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return num;
	}

	@Override
	public int getIrpTestpaperListNum(Long _cateid, Integer _status,
			String _sSearchWord, String _sSearchType) {
		// TODO Auto-generated method stub
		int num = 0;
		IrpTestpaperExample example = new IrpTestpaperExample();
		if (_sSearchType.equals("title")) {
			if (_status!=null) {
				example.createCriteria().andPaperstatusEqualTo(_status).andPapercateEqualTo(_cateid)
				.andPapertitleLike("%"+_sSearchWord+"%");
			}else{
				example.createCriteria().andPapercateEqualTo(_cateid).andPapertitleLike("%"+_sSearchWord+"%");	
			}
		}else{
			if (_status!=null) {
				example.createCriteria().andPaperstatusEqualTo(_status).andPapercateEqualTo(_cateid);
			}else{
				example.createCriteria().andPapercateEqualTo(_cateid);
				
			}
		}
		try {
			num = this.irpTestpaperDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public List<IrpTestpaper> getIrpTestpaperList(Long _cateid,
			Integer _status, PageUtil _pageutil, String _orderbystr,
			String _sSearchWord, String _sSearchType) {
		// TODO Auto-generated method stub
		List<IrpTestpaper> list = null;
		IrpTestpaperExample example = new IrpTestpaperExample();
		if (_sSearchType.equals("title")) {
			if (_status!=null) {
				example.createCriteria().andPaperstatusEqualTo(_status).andPapercateEqualTo(_cateid)
				.andPapertitleLike("%"+_sSearchWord+"%");
			}else{
				example.createCriteria().andPapercateEqualTo(_cateid).andPapertitleLike("%"+_sSearchWord+"%");	
			}
		}else{
			if (_status!=null) {
				example.createCriteria().andPaperstatusEqualTo(_status).andPapercateEqualTo(_cateid);
			}else{
				example.createCriteria().andPapercateEqualTo(_cateid);
				
			}
		}					
		example.setOrderByClause(_orderbystr);
		try {
			list = this.irpTestpaperDAO.selectByExample(example, _pageutil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public int deleteTestPaper(Long _paperid) {
		// TODO Auto-generated method stub
		int status = 0;
		if (_paperid!=null) {
			  LogUtil logUtil=null;
			  IrpTestpaper _irpTestpaper=null;
			try {
				_irpTestpaper=irpTestpaperDAO.selectByPrimaryKey(_paperid);
				logUtil=new LogUtil("TESTPAPER_DEL",_irpTestpaper);
				status = this.irpTestpaperDAO.deleteByPrimaryKey(_paperid);
				logUtil.successLogs( "为分类id["+_irpTestpaper.getPapercate()+"]删除试卷["+logUtil.getLogUser()+"]成功");
			} catch (SQLException e) {
				e.printStackTrace();
				logUtil.errorLogs("为分类id["+_irpTestpaper.getPapercate()+"]删除试卷["+logUtil.getLogUser()+"]失败",e);
			}
		}
		return status;
	}

	@Override
	public int updatePaper(IrpTestpaper _irpTestpaper) {
		// TODO Auto-generated method stub
		int status = 0;
		if (_irpTestpaper!=null) {
			LogUtil logUtil=new LogUtil("TESTPAPER_UPDATE",_irpTestpaper);
			try {
				status = this.irpTestpaperDAO.updateByPrimaryKeySelective(_irpTestpaper);
				logUtil.successLogs( "修改试卷["+logUtil.getLogUser()+"]成功");
			} catch (SQLException e) {
				e.printStackTrace();
				logUtil.errorLogs("修改试卷["+logUtil.getLogUser()+"]失败",e);
			}
		}
		return status;
	}


}
