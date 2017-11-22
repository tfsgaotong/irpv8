package com.tfs.irp.leavechecker.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tfs.irp.leavechecker.dao.IrpCheckerLinkDAO;
import com.tfs.irp.leavechecker.entity.IrpCheckerLink;
import com.tfs.irp.leavechecker.entity.IrpCheckerLinkExample;
import com.tfs.irp.leavechecker.entity.IrpCheckerLinkExample.Criteria;
import com.tfs.irp.leavechecker.service.IrpCheckerLinkService;
import com.tfs.irp.leaveoper.entity.IrpLeaveoperExample;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.DateUtils;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;


public class IrpCheckerLinkServiceImpl implements IrpCheckerLinkService {
	private IrpCheckerLinkDAO irpCheckerLinkDAO;

	public IrpCheckerLinkDAO getIrpCheckerLinkDAO() {
		return irpCheckerLinkDAO;
	}

	public void setIrpCheckerLinkDAO(IrpCheckerLinkDAO irpCheckerLinkDAO) {
		this.irpCheckerLinkDAO = irpCheckerLinkDAO;
	}

	@Override
	public int addIrpCheckerLink(IrpCheckerLink irpCheckerLink) {
		int msg = 0;
		Date nowtime = DateUtils.getNOWTime();
		Long checkerlinkid = TableIdUtil.getNextId(IrpCheckerLink.TABLENAME);
		irpCheckerLink.setCrtime(nowtime);
		irpCheckerLink.setStatus(IrpCheckerLink.UNPASS);
		irpCheckerLink.setCruserid(LoginUtil.getLoginUserId());
		irpCheckerLink.setCheckerlinkid(checkerlinkid);
		
		try {
			irpCheckerLinkDAO.insert(irpCheckerLink);
			msg=1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public List<IrpCheckerLink> getAllCheckersByleaveapplyid(Long leaveapplyid) {
		List<IrpCheckerLink> list=new ArrayList<IrpCheckerLink>();
		IrpCheckerLinkExample example=new IrpCheckerLinkExample();
		example.createCriteria().andLeaveapplyidEqualTo(leaveapplyid).andNextuseridEqualTo(0L);
		example.setOrderByClause("crtime ");
		
		try {
			list=this.irpCheckerLinkDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public IrpCheckerLink getIrpCheckerLinkByNext(Long leaveapplyid, Long userid) {
		List<IrpCheckerLink> list=new ArrayList<IrpCheckerLink>();
		IrpCheckerLink irpCheckerLink=new IrpCheckerLink();
		IrpCheckerLinkExample example=new IrpCheckerLinkExample();
		example.createCriteria().andLeaveapplyidEqualTo(leaveapplyid).andNextuseridEqualTo(userid);
		try {
			list= this.irpCheckerLinkDAO.selectByExample(example);
			if(list.size()>0){
				irpCheckerLink=list.get(0);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return irpCheckerLink;
	}

	@Override
	public List<Long> getAllCheckUserID(Long leaveapplyid) {
		IrpCheckerLinkExample example = new IrpCheckerLinkExample();
		example.createCriteria().andLeaveapplyidEqualTo(leaveapplyid);
		List<IrpCheckerLink> list = null;
		List<Long> checkid = new ArrayList<Long>();
		try {
			list = irpCheckerLinkDAO.selectByExample(example);
			if(list!=null){
				for(IrpCheckerLink link:list){
					checkid.add(link.getNextuserid());
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return checkid;
	}

	@Override
	public int deleteLinkByleaveId(long parseLong) {
		int msg=0;
		IrpCheckerLinkExample example = new IrpCheckerLinkExample();
		example.createCriteria().andLeaveapplyidEqualTo(parseLong);
		try {
			msg=this.irpCheckerLinkDAO.deleteByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	} 
	@Override
	public boolean isBeginCheck(Long leaveapplyid) {
		boolean res=true;
		int count = 0;
		IrpCheckerLinkExample example = new IrpCheckerLinkExample();
		example.createCriteria().andLeaveapplyidEqualTo(leaveapplyid);
		try {
			count = irpCheckerLinkDAO.countByExample(example);
			if(count>0){
				res = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
}
