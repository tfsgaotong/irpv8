package com.tfs.irp.leaveoper.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.tfs.irp.leaveapply.entity.IrpLeaveapply;
import com.tfs.irp.leaveoper.dao.IrpLeaveoperDAO;
import com.tfs.irp.leaveoper.entity.IrpLeaveoper;
import com.tfs.irp.leaveoper.entity.IrpLeaveoperExample;
import com.tfs.irp.leaveoper.service.IrpLeaveoperService;
import com.tfs.irp.sign.dao.IrpSignInfoDAO;
import com.tfs.irp.user.dao.IrpUserDAO;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.DateUtils;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpLeaveoperServiceImpl implements IrpLeaveoperService {
	private IrpSignInfoDAO signDao;
	public IrpSignInfoDAO getSignDao() {
		return signDao;
	}

	public void setSignDao(IrpSignInfoDAO signDao) {
		this.signDao = signDao;
	}
	private IrpUserDAO irpUserDAO;



	public IrpUserDAO getIrpUserDAO() {
		return irpUserDAO;
	}

	public void setIrpUserDAO(IrpUserDAO irpUserDAO) {
		this.irpUserDAO = irpUserDAO;
	}

private IrpLeaveoperDAO irpLeaveoperDAO;

public IrpLeaveoperDAO getIrpLeaveoperDAO() {
	return irpLeaveoperDAO;
}

public void setIrpLeaveoperDAO(IrpLeaveoperDAO irpLeaveoperDAO) {
	this.irpLeaveoperDAO = irpLeaveoperDAO;
}

@Override
public Long addOper(Long applyid, String userids) {
	Long msg=0l;
	 String[] _userid=userids.split(",");
	 for(int i=0;i<_userid.length;i++){
		 Long userid=Long.parseLong(_userid[i]);
	IrpLeaveoper irpLeaveoper=new IrpLeaveoper();
	Date crtime = DateUtils.getNOWTime();
	 Long operid = TableIdUtil.getNextId(IrpLeaveoper.TABLE_NAME);
	 irpLeaveoper.setCrtime(crtime);
	 irpLeaveoper.setLeaveapplyid(applyid);
	 irpLeaveoper.setCruserid(LoginUtil.getLoginUserId());
	 irpLeaveoper.setOperstatus(IrpLeaveapply.UNPASS);
	 irpLeaveoper.setOperid(operid);
	 irpLeaveoper.setUserid(userid);
		try {
			this.irpLeaveoperDAO.insertSelective(irpLeaveoper);
			msg=1l;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 }
	return msg;
}


@Override
public List<IrpLeaveoper> getListByapplyId(Long applyid) {
	List<IrpLeaveoper> list=null;
	IrpLeaveoperExample example=new  IrpLeaveoperExample();
	example.createCriteria().andLeaveapplyidEqualTo(applyid);
	try {
		list=this.irpLeaveoperDAO.selectByExample(example);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return list;
}
@Override
public List<Long> selLeaveapplyidByUserid(Integer status) {
	IrpUser user  = LoginUtil.getLoginUser();
	IrpLeaveoperExample example = new IrpLeaveoperExample();
	example.createCriteria().andUseridEqualTo(user.getUserid()).andOperstatusEqualTo(status);
	List<IrpLeaveoper> list=null;
	List<Long> ids=new ArrayList<Long>();
	try {
		list = this.irpLeaveoperDAO.selectByExample(example);
		if(list.size()>0){
			for(IrpLeaveoper oper:list){
				ids.add(oper.getLeaveapplyid());
			}
		}		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return ids;

}
@Override
public List<Long> selLeaveapplyidByUserid(Integer status,IrpUser user) {
	IrpLeaveoperExample example = new IrpLeaveoperExample();
	example.createCriteria().andUseridEqualTo(user.getUserid()).andOperstatusEqualTo(status);
	List<IrpLeaveoper> list=null;
	List<Long> ids=new ArrayList<Long>();
	try {
		list = this.irpLeaveoperDAO.selectByExample(example);
		if(list.size()>0){
			for(IrpLeaveoper oper:list){
				ids.add(oper.getLeaveapplyid());
			}
		}		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return ids;

}
@Override
public List<Long> selLeaveapplyidByUseridList(List<Integer> list2) {
	// TODO Auto-generated method stub
	IrpUser user  = LoginUtil.getLoginUser();
	IrpLeaveoperExample example = new IrpLeaveoperExample();
	example.createCriteria().andUseridEqualTo(user.getUserid()).andOperstatusIn(list2);
	List<IrpLeaveoper> list=null;
	List<Long> ids=new ArrayList<Long>();
	try {
		list = this.irpLeaveoperDAO.selectByExample(example);
		if(list.size()>0){
			for(IrpLeaveoper oper:list){
				ids.add(oper.getLeaveapplyid());
			}
		}		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return ids;
	
}
@Override
public List<Long> selLeaveapplyidByUseridList(List<Integer> list2,IrpUser user) {
	IrpLeaveoperExample example = new IrpLeaveoperExample();
	example.createCriteria().andUseridEqualTo(user.getUserid()).andOperstatusIn(list2);
	List<IrpLeaveoper> list=null;
	List<Long> ids=new ArrayList<Long>();
	try {
		list = this.irpLeaveoperDAO.selectByExample(example);
		if(list.size()>0){
			for(IrpLeaveoper oper:list){
				ids.add(oper.getLeaveapplyid());
			}
		}		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return ids;
	
}
@Override
public void updateOperStatus(long applyleaveid,long loginUserId, Integer status) {
	IrpLeaveoperExample example=new IrpLeaveoperExample();
	example.createCriteria().andUseridEqualTo(loginUserId).andLeaveapplyidEqualTo(applyleaveid);
	  try {
		List<IrpLeaveoper> list=this.irpLeaveoperDAO.selectByExample(example);
		if(list!=null && list.size()!=0){
			IrpLeaveoper irpLeaveoper=list.get(0);
			if(irpLeaveoper!=null){
				IrpLeaveoper irpLeaveoper1=new IrpLeaveoper();
				irpLeaveoper1.setOperid(irpLeaveoper.getOperid());
				irpLeaveoper1.setOperstatus(status);
				int msg=this.irpLeaveoperDAO.updateByPrimaryKeySelective(irpLeaveoper1);
				
			}
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

@Override
public List<IrpLeaveoper> getOperByapplyId(Long userid, long leaveapplyid) {
	List<IrpLeaveoper> list=new ArrayList<IrpLeaveoper>();
	IrpLeaveoperExample example=new IrpLeaveoperExample();
	example.createCriteria().andUseridEqualTo(leaveapplyid).andLeaveapplyidEqualTo(userid);
	try {
		list=this.irpLeaveoperDAO.selectByExample(example);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return list;
}

@Override
public String selLeaveapplyidByLeaveapplyid(Long applyleaveid) {
	List<IrpLeaveoper> list=null;
	List<Long> ids=new ArrayList<Long>();
	IrpLeaveoperExample example=new  IrpLeaveoperExample();
	example.createCriteria().andLeaveapplyidEqualTo(applyleaveid);
	String checker2="";
	try {
		list=this.irpLeaveoperDAO.selectByExample(example);
		if(list.size()>0){
			for(IrpLeaveoper oper:list){
				Map uid = new HashMap();
				uid.put("userid", oper.getUserid());
				String userName = signDao.findUserTrueNameById(uid);
				checker2+=","+userName;
			}
		}	
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return checker2;
}

@Override
public List<Long> getCheckuserids(Long applyleaveid) {
	IrpLeaveoperExample example = new IrpLeaveoperExample();
	example.createCriteria().andLeaveapplyidEqualTo(applyleaveid);
	List<Long> list=new ArrayList<Long>();
	List<IrpLeaveoper> listo = null;
	try {
		listo = irpLeaveoperDAO.selectByExample(example);
		if(listo!=null){
			for(IrpLeaveoper oper:listo){
				list.add(oper.getUserid());
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	// TODO Auto-generated method stub
	return list;
}

@Override
public int deleteLeaveoper(Long applyleaveid) {
	int count = 0;
	IrpLeaveoperExample example = new IrpLeaveoperExample();
	example.createCriteria().andLeaveapplyidEqualTo(applyleaveid);
	try {
		count = irpLeaveoperDAO.deleteByExample(example);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return count;
}
@Override
public int deleteoper(long leaveapplyid) {
	int msg=0;
	IrpLeaveoperExample example=new  IrpLeaveoperExample();
	example.createCriteria().andLeaveapplyidEqualTo(leaveapplyid);
	try {
		msg=this.irpLeaveoperDAO.deleteByExample(example);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return msg;
}

@Override
public int upcheckuser(IrpLeaveoper oper) {
	int res = 0;
	try {
		res = irpLeaveoperDAO.updateByPrimaryKey(oper);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return res;
}

@Override
public IrpLeaveoper selIrpLeaveoper(Long applyleaveid) {
	IrpLeaveoper oper = new IrpLeaveoper();
	IrpLeaveoperExample example = new IrpLeaveoperExample();
	example.createCriteria().andLeaveapplyidEqualTo(applyleaveid);
	try {
		oper = irpLeaveoperDAO.selectByExample(example).get(0);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	// TODO Auto-generated method stub
	return oper;
}

}

