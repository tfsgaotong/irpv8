package com.tfs.irp.uservaluelink.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.tfs.irp.leaveapply.dao.IrpLeaveapplyDAO;
import com.tfs.irp.leaveapply.entity.IrpLeaveapply;
import com.tfs.irp.leaveapply.entity.IrpLeaveapplyExample;
import com.tfs.irp.leaveconf.entity.IrpLeaveconfig;
import com.tfs.irp.uservaluelink.dao.IrpUserValueLinkDAO;
import com.tfs.irp.uservaluelink.entity.IrpUserValueLink;
import com.tfs.irp.uservaluelink.entity.IrpUserValueLinkExample;
import com.tfs.irp.uservaluelink.entity.IrpUserValueLinkExample.Criteria;
import com.tfs.irp.uservaluelink.service.IrpUserValueLinkService;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;

public class IrpUserValueLinkServiceImpl implements IrpUserValueLinkService {

    private IrpUserValueLinkDAO irpUserValueLinkDAO;
	
	public IrpUserValueLinkDAO getIrpUserValueLinkDAO() {
		return irpUserValueLinkDAO;
	}

	public void setIrpUserValueLinkDAO(IrpUserValueLinkDAO irpUserValueLinkDAO) {
		this.irpUserValueLinkDAO = irpUserValueLinkDAO;
	}
   
	private IrpLeaveapplyDAO irpLeaveapplyDAO;
	public IrpLeaveapplyDAO getIrpLeaveapplyDAO() {
		return irpLeaveapplyDAO;
	}

	public void setIrpLeaveapplyDAO(IrpLeaveapplyDAO irpLeaveapplyDAO) {
		this.irpLeaveapplyDAO = irpLeaveapplyDAO;
	}

	@Override
	public int addIrpUserValueLink(IrpUserValueLink _irpUserValueLink) {
		// TODO Auto-generated method stub
		int status = 0;
		IrpUserValueLink irpUserValueLink = new IrpUserValueLink();
		irpUserValueLink.setUserid(_irpUserValueLink.getUserid());
		irpUserValueLink.setValueckey(_irpUserValueLink.getValueckey());
		irpUserValueLink.setScore(_irpUserValueLink.getScore());
		irpUserValueLink.setExperience(_irpUserValueLink.getExperience());
		irpUserValueLink.setCrtime(new Date());
		try {
			irpUserValueLinkDAO.insert(irpUserValueLink);
			status = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			status = 0;
			e.printStackTrace();
			
		}
		return status;
	}

	@Override
	public boolean irpUserValueLinkByUseridValueid(long _userid,String _valueckey) {
		// TODO Auto-generated method stub
		boolean flag = false;
		
		IrpUserValueLinkExample example = new IrpUserValueLinkExample();
		Criteria criteria =  example.createCriteria();
		criteria.andUseridEqualTo(_userid);
		criteria.andValueckeyEqualTo(_valueckey);
		
		try {
		int countvaluelink  =	this.irpUserValueLinkDAO.countByExample(example);
		
		if(countvaluelink>=1){
			flag = true;
		}else{
			flag = false;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public int updateIrpUserValueLinkByUseridValueid(
			IrpUserValueLink _irpUserValueLink) {
		// TODO Auto-generated method stub
		int _status = 0;
		IrpUserValueLinkExample example = new IrpUserValueLinkExample();
		Criteria criteria =  example.createCriteria();
		criteria.andUseridEqualTo(_irpUserValueLink.getUserid());
		criteria.andValueckeyEqualTo(_irpUserValueLink.getValueckey());
		IrpUserValueLink  record = new IrpUserValueLink();
		record.setScore(_irpUserValueLink.getScore());
		record.setExperience(_irpUserValueLink.getExperience());
		
		try {
			_status = this.irpUserValueLinkDAO.updateByExampleSelective(record, example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return _status;
	}

	@Override
	public long scoreByUseridValueid(long _userid,String _valuekey) {
		// TODO Auto-generated method stub
		long  score = 0l;
		IrpUserValueLinkExample example = new IrpUserValueLinkExample();
		Criteria criteria =  example.createCriteria();
		criteria.andUseridEqualTo(_userid);
		criteria.andValueckeyEqualTo(_valuekey);
		try {
			IrpUserValueLink irpUserValueLink =	this.irpUserValueLinkDAO.selectByExample(example).get(0);
			score = irpUserValueLink.getScore();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return score;
	}

	@Override
	public long experienceByUseridValueid(long _userid,String _valuekey) {
		// TODO Auto-generated method stub
		long  experience = 0l;
		IrpUserValueLinkExample example = new IrpUserValueLinkExample();
		Criteria criteria =  example.createCriteria();
		criteria.andUseridEqualTo(_userid);
		criteria.andValueckeyEqualTo(_valuekey);
		try {
			IrpUserValueLink irpUserValueLink =	this.irpUserValueLinkDAO.selectByExample(example).get(0);
			experience = irpUserValueLink.getExperience();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return experience;
	}

	@Override
	public List<IrpUserValueLink> getIrpUserValueLinkListByTime(
			Date _starttime, Date _endtime, long _userid,PageUtil pageUtil,String _oOrderby) {
		// TODO Auto-generated method stub
		
		List<IrpUserValueLink> list = null;	
		IrpUserValueLinkExample example = new IrpUserValueLinkExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(_userid);

			criteria.andCrtimeBetween(_starttime, _endtime);

	
		if(_oOrderby==null || _oOrderby.equals("")){
			_oOrderby="crtime desc";
		}
		example.setOrderByClause(_oOrderby);
		try {
			list = this.irpUserValueLinkDAO.selectByExample(example,pageUtil);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	@Override
	public List<IrpLeaveapply> getLeaveDaysListByTime(
			Date _starttime, Date _endtime, long _userid,PageUtil pageUtil,String _oOrderby) {
		
		List<IrpLeaveapply> list = null;	
		IrpLeaveapplyExample example = new IrpLeaveapplyExample();
		Long yearId=Long.parseLong(SysConfigUtil.getSysConfigValue("HOLIDAYCONFIG"));
		example.createCriteria().andCrtimeBetween(_starttime, _endtime).andCruseridEqualTo(_userid).andApplytypeidEqualTo(yearId);
		
		
		if(_oOrderby==null || _oOrderby.equals("")){
			_oOrderby="crtime desc";
		}
		example.setOrderByClause(_oOrderby);
		list = this.irpLeaveapplyDAO.selectByExample(example,pageUtil);
		
		return list;
	}

	@Override
	public int getIrpUserValueLinkListByTimeCount(Date _starttime,
			Date _endtime, long _userid) {
		// TODO Auto-generated method stub
		int num = 0;
		IrpUserValueLinkExample example = new IrpUserValueLinkExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(_userid);
			criteria.andCrtimeBetween(_starttime, _endtime);
		try {
			num = this.irpUserValueLinkDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return num;
	}
	@Override
	public int getLeaveDaysByTimeCount(Date _starttime,
			Date _endtime, long _userid) {
		int num = 0;
		IrpLeaveapplyExample example=new IrpLeaveapplyExample();
		example.createCriteria().andCrtimeBetween(_starttime, _endtime).andCruseridEqualTo(_userid).andApplytypeidEqualTo(IrpLeaveconfig.YEAR_HOLIDAY);
		try {
			num = this.irpLeaveapplyDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return num;
	}

	@Override
	public int sumScoreByUserid(long _userid) {
		// TODO Auto-generated method stub
		int score = 0;
		try {
		 score =  this.irpUserValueLinkDAO.sumScoreByUserid(_userid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return score;
	}
	@Override
	public int getScoreByUserid(long _userid) {
		// TODO Auto-generated method stub
		int score = 0;
		try {
		 score =  this.irpUserValueLinkDAO.getScoreByUserid(_userid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return score;
	}

	@Override
	public int sumExperienceByUserid(long _userid) {
		// TODO Auto-generated method stub
		int experience = 0;
		try {
		experience = this.irpUserValueLinkDAO.sumExperienceByUserid(_userid);
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
	    	e.printStackTrace();
	}
	
		return experience;
	}
	@Override
	public List<IrpUserValueLink> findDataByExpert(Date startTime, Date endTime,Long userId) {
		List<IrpUserValueLink> list=null;
		IrpUserValueLinkExample example=new IrpUserValueLinkExample();
		try {
		 Criteria criteria = example.createCriteria();
		 
		 criteria.andCrtimeGreaterThanOrEqualTo(startTime);
		 criteria.andCrtimeLessThan(endTime);
		 if(userId!=null && userId>0L){
			 criteria.andUseridEqualTo(userId);
		 }
		 list=this.irpUserValueLinkDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
