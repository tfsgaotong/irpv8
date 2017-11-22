package com.tfs.irp.microblogfocus.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tfs.irp.microblogfocus.dao.IrpMicroblogFocusDAO;
import com.tfs.irp.microblogfocus.entity.IrpMicroblogFocus;
import com.tfs.irp.microblogfocus.entity.IrpMicroblogFocusExample;
import com.tfs.irp.microblogfocus.entity.IrpMicroblogFocusExample.Criteria;
import com.tfs.irp.microblogfocus.service.IrpMicroblogFocusService;
import com.tfs.irp.user.dao.IrpUserDAO;
import com.tfs.irp.user.entity.IrpUserExample;
import com.tfs.irp.util.DateUtils;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpMicroblogFocusServiceImpl implements IrpMicroblogFocusService {

	private IrpMicroblogFocusDAO   irpMicroblogFocusDAO;

	public IrpMicroblogFocusDAO getIrpMicroblogFocusDAO() {
		return irpMicroblogFocusDAO;
	}

	public void setIrpMicroblogFocusDAO(IrpMicroblogFocusDAO irpMicroblogFocusDAO) {
		this.irpMicroblogFocusDAO = irpMicroblogFocusDAO;
	}
	private IrpUserDAO irpUserDAO;



	public IrpUserDAO getIrpUserDAO() {
		return irpUserDAO;
	}

	public void setIrpUserDAO(IrpUserDAO irpUserDAO) {
		this.irpUserDAO = irpUserDAO;
	}

	@Override
	public List<IrpMicroblogFocus> findMicroblogFocusUserId(long _focususerid,PageUtil pageUtil) {
		// TODO Auto-generated method stub
		List<IrpMicroblogFocus> _irpMicroblogfocuslist = null;
		IrpMicroblogFocusExample example = new IrpMicroblogFocusExample();
		Criteria criteria = example.createCriteria();
		criteria.andFocususeridEqualTo(_focususerid);
		try {
			_irpMicroblogfocuslist =  this.irpMicroblogFocusDAO.selectByExample(example,pageUtil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _irpMicroblogfocuslist;
	}
	@Override
	public List<IrpMicroblogFocus> findMicroblogFocusUserId(long _focususerid) {
		// TODO Auto-generated method stub
		List<IrpMicroblogFocus> _irpMicroblogfocuslist = null;
		IrpMicroblogFocusExample example = new IrpMicroblogFocusExample();
		Criteria criteria = example.createCriteria();
		criteria.andFocususeridEqualTo(_focususerid);
		try {
			_irpMicroblogfocuslist =  this.irpMicroblogFocusDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _irpMicroblogfocuslist;
	}

	@Override
	public int countMicroblogFocusFocusUserid(long _userid) {
		// TODO Auto-generated method stub
		int focususeridcount = 0;
		IrpMicroblogFocusExample example = new IrpMicroblogFocusExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(_userid);
		try {
			focususeridcount =	this.irpMicroblogFocusDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return focususeridcount;
	}

	@Override
	public int deleteMicroblogFocusUserid(long _focususerid,long _userid) {
		// TODO Auto-generated method stub
		int _nStatus = 0;
		IrpMicroblogFocusExample example = new IrpMicroblogFocusExample();
		Criteria criteria = example.createCriteria();
		criteria.andFocususeridEqualTo(_focususerid);
		criteria.andUseridEqualTo(_userid);
		try {
			_nStatus = this.irpMicroblogFocusDAO.deleteByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _nStatus;
	}

	@Override
	public int addMicroblogFocusUserid(long _focususerid, long _userid) {
		// TODO Auto-generated method stub
		int _status = 0;
		IrpMicroblogFocus irpMicroblogFocus = new IrpMicroblogFocus();
		irpMicroblogFocus.setFocusid(TableIdUtil.getNextId(IrpMicroblogFocus.TABLE_NAME));
		irpMicroblogFocus.setUserid(_userid);
		irpMicroblogFocus.setFocususerid(_focususerid);
		irpMicroblogFocus.setType(IrpMicroblogFocus.FOCUSTYPE);
		try {
			this.irpMicroblogFocusDAO.insertSelective(irpMicroblogFocus);
			_status = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _status;
	}

	@Override
	public int countMicroblogFocusUserid(long _focususerid) {
		// TODO Auto-generated method stub
		int _userid = 0;
		IrpMicroblogFocusExample example = new IrpMicroblogFocusExample();
		Criteria criteria = example.createCriteria();
		criteria.andFocususeridEqualTo(_focususerid);
		try {
			_userid  =	this.irpMicroblogFocusDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _userid;
	}

	@Override
	public List<IrpMicroblogFocus> findEachMicroblogFocusUserId(
			long _focususerid,PageUtil pageUtil) {
		// TODO Auto-generated method stub
		List<IrpMicroblogFocus> _irpMicroblogfocuslist = null;
		IrpMicroblogFocusExample example = new IrpMicroblogFocusExample();
		Criteria criteria = example.createCriteria();
		criteria.andFocususeridEqualTo(_focususerid);
		
        try {
        	if(this.irpMicroblogFocusDAO.selectUseridByFocususerId(_focususerid).toString()!="[]"){
        		criteria.andUseridIn(this.irpMicroblogFocusDAO.selectUseridByFocususerId(_focususerid));
        	}else{
        		return _irpMicroblogfocuslist;
        	}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			_irpMicroblogfocuslist =  this.irpMicroblogFocusDAO.selectByExample(example,pageUtil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _irpMicroblogfocuslist;

	}
	@Override
	public List<IrpMicroblogFocus> findMicroblogFocusUserIdById(
			long _focususerid,PageUtil pageUtil) {
		List<IrpMicroblogFocus> _irpMicroblogfocuslist = null;
		IrpMicroblogFocusExample example = new IrpMicroblogFocusExample();
		Criteria criteria = example.createCriteria();
		criteria.andFocususeridEqualTo(_focususerid);
		try {
			_irpMicroblogfocuslist =  this.irpMicroblogFocusDAO.selectByExample(example,pageUtil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _irpMicroblogfocuslist;
		
	}

	@Override
	public List<IrpMicroblogFocus> findMicroblogUserId(long _userid,PageUtil pageUtil) {
		// TODO Auto-generated method stub
		List<IrpMicroblogFocus> _irpMicroblogfocuslist = null;
		IrpMicroblogFocusExample example = new IrpMicroblogFocusExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(_userid);
		try {
			_irpMicroblogfocuslist=this.irpMicroblogFocusDAO.selectByExample(example,pageUtil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _irpMicroblogfocuslist;
	}
	@Override
	public List<IrpMicroblogFocus> findMicroblogUserId(long _userid) {
		// TODO Auto-generated method stub
		List<IrpMicroblogFocus> _irpMicroblogfocuslist = null;
		IrpMicroblogFocusExample example = new IrpMicroblogFocusExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(_userid);
		try {
			_irpMicroblogfocuslist=this.irpMicroblogFocusDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _irpMicroblogfocuslist;
	}
	@Override
	public List findSearchUser(String _info,long _userid,PageUtil pageUtil) {
		// TODO Auto-generated method stub
		List list = new ArrayList();
		try {
			list=this.irpUserDAO.findUserByNickNameTrueName(_info,_userid,pageUtil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public List findSearchUserByParam(String _info,long _userid,PageUtil pageUtil,Map<String,String> paramMap){
		// TODO Auto-generated method stub
		List list = new ArrayList();
		Date begintime = null ;
		Date endtime = DateUtils.getNOWTime();
		if(paramMap!=null){
			if(paramMap.get("CRTIME")!=null){//最近一周
				if("WEEK".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					begintime = DateUtils.dateTimeAdd(DateUtils.getNOWTime(), Calendar.WEDNESDAY, -7);
				}else if("MONTH".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					begintime = DateUtils.dateTimeAdd(DateUtils.getNOWTime(), Calendar.MONTH, -1);
				}else if("YEAR".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					begintime = DateUtils.dateTimeAdd(DateUtils.getNOWTime(), Calendar.YEAR, -1);
				}else {
					begintime = DateUtils.dateTimeAdd(DateUtils.getNOWTime(), Calendar.YEAR, -100);
				}
			}
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userid", _userid);
		map.put("begintime", begintime);
		map.put("endtime", endtime);
		map.put("nickname", "%"+_info+"%");
		map.put("truename", "%"+_info+"%");
		map.put("username", "%"+_info+"%");
		list=this.irpUserDAO.findUserByNickNameTrueNameUserNameDate(map,pageUtil);
		return list;
	}
	@Override
	public int findSearchUserNum(String _info,long _userid){
		// TODO Auto-generated method stub
		int nPageCount = 0;
		try {
			nPageCount=this.irpUserDAO.findUserByNickNameTrueName(_info,_userid).size();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nPageCount;
	}
	public int findSearchUserNumByParam(String _info,long _userid,Map<String,String> paramMap){
		// TODO Auto-generated method stub
		int nPageCount = 0;
		Date begintime = null ;
		Date endtime = DateUtils.getNOWTime();
		List list = new ArrayList();
		if(paramMap!=null){
			if(paramMap.get("CRTIME")!=null){//最近一周
				if("WEEK".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					begintime = DateUtils.dateTimeAdd(DateUtils.getNOWTime(), Calendar.WEDNESDAY, -7);
				}else if("MONTH".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					begintime = DateUtils.dateTimeAdd(DateUtils.getNOWTime(), Calendar.MONTH, -1);
				}else if("YEAR".equalsIgnoreCase(paramMap.get("CRTIME").toString())){
					begintime = DateUtils.dateTimeAdd(DateUtils.getNOWTime(), Calendar.YEAR, -1);
				}else {
					begintime = DateUtils.dateTimeAdd(DateUtils.getNOWTime(), Calendar.YEAR, -100);
				}
			}
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userid", _userid);
		map.put("begintime", begintime);
		map.put("endtime", endtime);
		map.put("nickname", "%"+_info+"%");
		map.put("truename", "%"+_info+"%");
		map.put("username", "%"+_info+"%");
		list=this.irpUserDAO.findUserByNickNameTrueNameUserNameDate(map,null);
		nPageCount = list.size();
		return nPageCount;
	}
	@Override
	public int findMicroblogFocusUserIdCount(long _focususerid) {
		// TODO Auto-generated method stub
		int _size = 0;
		IrpMicroblogFocusExample example = new IrpMicroblogFocusExample();
		Criteria criteria = example.createCriteria();
		criteria.andFocususeridEqualTo(_focususerid);
		try {
			_size =  this.irpMicroblogFocusDAO.selectByExample(example).size();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _size;
	}

	@Override
	public int findEachMicroblogFocusUserIdCount(long _focususerid) {
		// TODO Auto-generated method stub
       int _size = 0;
       IrpMicroblogFocusExample example = new IrpMicroblogFocusExample();
		Criteria criteria = example.createCriteria();
		criteria.andFocususeridEqualTo(_focususerid);
		
       try {
       	if(this.irpMicroblogFocusDAO.selectUseridByFocususerId(_focususerid).toString()!="[]"){
       		criteria.andUseridIn(this.irpMicroblogFocusDAO.selectUseridByFocususerId(_focususerid));
       	}else{
       		return _size;
       	}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			try {
				_size =  this.irpMicroblogFocusDAO.selectByExample(example).size();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return _size;
	}
	@Override
	public int findEachMicroblogFocusUserIdCountById(long _focususerid) {
		// TODO Auto-generated method stub
		int _size = 0;
		IrpMicroblogFocusExample example = new IrpMicroblogFocusExample();
		Criteria criteria = example.createCriteria();
		criteria.andFocususeridEqualTo(_focususerid);
		
		try {
			_size =  this.irpMicroblogFocusDAO.selectByExample(example).size();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _size;
	}

	@Override
	public int findMicroblogUserIdCount(long _userid) {
		// TODO Auto-generated method stub
       int _size = 0;
   	IrpMicroblogFocusExample example = new IrpMicroblogFocusExample();
	Criteria criteria = example.createCriteria();
	criteria.andUseridEqualTo(_userid);
	try {
		_size=this.irpMicroblogFocusDAO.selectByExample(example).size();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return _size;
	}

	@Override
	public List<IrpMicroblogFocus> findFocusByUserId(long _userId){
		List<IrpMicroblogFocus> list=null;
		try {
			IrpMicroblogFocusExample example=new IrpMicroblogFocusExample();
			Criteria criteria = example.createCriteria();
			criteria.andFocusidEqualTo(LoginUtil.getLoginUserId());//关注者
			criteria.andUseridEqualTo(_userId);  
			list=irpMicroblogFocusDAO.selectByExample(example);  
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
@Override
public List selectUseridByFocuserId(long _loginuserid) {
	// TODO Auto-generated method stub
	List userids=null;
	try {
		userids=irpMicroblogFocusDAO.selectUseridByFocuserId(_loginuserid);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return userids;
}
@Override
public List selectUseridByLoginuserId(long _loginuserid) {
	// TODO Auto-generated method stub
	List userids=null;
	try {
		userids=irpMicroblogFocusDAO.selectUseridByLoginuserId(_loginuserid);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return userids;
}
@Override
public List selectFansByLoginuserId(long _loginuserid) {
	// TODO Auto-generated method stub
	List userids=null;
	try {
		userids=irpMicroblogFocusDAO.selectFansByLoginuserId(_loginuserid);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return userids;
}

@Override
public List selectUseridByFocususerId(long _loginuserid) {
	// TODO Auto-generated method stub
	List list = null;
	try {
		list = this.irpMicroblogFocusDAO.selectUseridByFocususerId(_loginuserid);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
}

@Override
public List<IrpMicroblogFocus> selectByExample(
		IrpMicroblogFocusExample example, PageUtil pageUtil) {
	// TODO Auto-generated method stub
	List<IrpMicroblogFocus> list = null;
	try {
		list = this.irpMicroblogFocusDAO.selectByExample(example, pageUtil);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
}
	
	
}
