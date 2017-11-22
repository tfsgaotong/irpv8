package com.tfs.irp.usermedal.service.impl;

import java.sql.SQLException;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tfs.irp.goods.entity.IrpGoods;
import com.tfs.irp.goods.entity.IrpGoodsExample;
import com.tfs.irp.goods.service.IrpGoodsService;
import com.tfs.irp.medal.dao.IrpMedalDAO;
import com.tfs.irp.medal.entity.IrpMedal;
import com.tfs.irp.medal.entity.IrpMedalExample;
import com.tfs.irp.usergoodslink.dao.IrpUserCovertGoodsDAO;
import com.tfs.irp.usergoodslink.service.IrpUserCovertGoodsService;
import com.tfs.irp.usermedal.dao.IrpUserMedalDAO;
import com.tfs.irp.usermedal.entity.IrpUserMedal;
import com.tfs.irp.usermedal.entity.IrpUserMedalExample;
import com.tfs.irp.usermedal.entity.IrpUserMedalExample.Criteria;
import com.tfs.irp.usermedal.service.IrpUserMedalService;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.PageUtil;


public class IrpUserMedalServiceImpl implements IrpUserMedalService {
	private IrpUserMedalDAO irpUserMedalDAO;
	private IrpMedalDAO irpMedalDAO;
	private IrpGoodsService irpGoodsService;
	
	
	public IrpMedalDAO getIrpMedalDAO() {
		return irpMedalDAO;
	}

	public void setIrpMedalDAO(IrpMedalDAO irpMedalDAO) {
		this.irpMedalDAO = irpMedalDAO;
	}

	public IrpGoodsService getIrpGoodsService() {
		return irpGoodsService;
	}

	public void setIrpGoodsService(IrpGoodsService irpGoodsService) {
		this.irpGoodsService = irpGoodsService;
	}

	public IrpUserMedalDAO getIrpUserMedalDAO() {
		return irpUserMedalDAO;
	}

	public void setIrpUserMedalDAO(IrpUserMedalDAO irpUserMedalDAO) {
		this.irpUserMedalDAO = irpUserMedalDAO;
	}

	@Override
	public int addUserMedal(IrpUserMedal userMedal) {
		int msg = 0;
		LogUtil logUtil=new LogUtil("USERMEDAL_ADD",userMedal);
		try {
			irpUserMedalDAO.insert(userMedal);
			msg=1;
			logUtil.successLogs( "为["+userMedal.getUsername()+"]颁发勋章["+userMedal.getMedalname()+"]成功");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs( "为["+userMedal.getUsername()+"]颁发勋章["+userMedal.getMedalname()+"]失败",e);
		}
		return msg;
	}

	@Override
	public int deleteUserMedal(Long usermedalid) {
		try {
			irpUserMedalDAO.deleteByPrimaryKey(usermedalid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public IrpUserMedal findUserMedalById(Long usermedalid) {
		IrpUserMedal irpUserMedal=null;
		try {
			irpUserMedal=irpUserMedalDAO.selectByPrimaryKey(usermedalid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return irpUserMedal;
	}

	@Override
	public int updateUserMedalById(IrpUserMedal userMedal) {
		int msg = 1;
		try {
			msg=irpUserMedalDAO.updateByPrimaryKey(userMedal);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public List<IrpUserMedal> showAllUserMedal(PageUtil pageUtil,String orderField,String orderBy) {
		List<IrpUserMedal> list = null;
		try {
			IrpUserMedalExample example = new IrpUserMedalExample();
			if(orderField!=null&&orderBy!=null&&!orderField.equals("")&&!orderBy.equals("")){
				example.setOrderByClause(orderField+" "+orderBy);
			}else{
				example.setOrderByClause("coverttime desc");
			}
			list=irpUserMedalDAO.selectAllByExample(pageUtil,example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<IrpUserMedal> getUserMedalByUserid(PageUtil pageUtil,Long userid) {
		List<IrpUserMedal> list = null;
		try {
			IrpUserMedalExample example = new IrpUserMedalExample();
			String orderby="coverttime desc";
			example.createCriteria().andUseridEqualTo(userid);
			example.setOrderByClause(orderby);
			if(pageUtil==null){
				list=irpUserMedalDAO.selectByExample(example);
			}else{
				list=irpUserMedalDAO.selectAllByExample(pageUtil,example);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<IrpUserMedal> listUserMedal(Long userid,Long medalid,Long medalstate) {
		List<IrpUserMedal> list = null;
		try {
			IrpUserMedalExample example = new IrpUserMedalExample();
			String orderby="coverttime desc";
			if(medalid==null){
				example.createCriteria().andUseridEqualTo(userid).andMedalstateEqualTo(medalstate);
			}else{
				example.createCriteria().andUseridEqualTo(userid).andMedalidEqualTo(medalid).andMedalstateEqualTo(medalstate);
			}
			example.setOrderByClause(orderby);
			list=irpUserMedalDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int countUserMedal() {
		int msg = 0;
		try {
			IrpUserMedalExample example = new IrpUserMedalExample();
			msg=irpUserMedalDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	@Override
	public int countUserMedal(Long userid) {
		int msg = 0;
		try {
			IrpUserMedalExample example = new IrpUserMedalExample();
			example.createCriteria().andUseridEqualTo(userid);
			msg=irpUserMedalDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	@Override
	public int countUserMedal(Long userid,Long goodsid,Long medalstate) {
		int msg = 0;
		try {
			StringBuffer sql = new StringBuffer();
			IrpUserMedalExample example = new IrpUserMedalExample();
			IrpGoods irpGoods=irpGoodsService.findGoodsByGoodsid(goodsid);
			Long medalid=irpGoods.getMedalid();
			if(medalid==0||medalid.equals("")){
				sql.append("select * from irp_medal where minworth<"+irpGoods.getNeedscore()+"and maxworth>"+irpGoods.getNeedscore());
				medalid=(long) irpMedalDAO.selectMedal(sql.toString());
				example.createCriteria().andUseridEqualTo(userid).andMedalidEqualTo(medalid).andMedalstateEqualTo(medalstate);
			}else{
				example.createCriteria().andUseridEqualTo(userid).andMedalidEqualTo(medalid).andMedalstateEqualTo(medalstate);
			}
			msg=irpUserMedalDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}
	@Override
	public int selectMedalid(Long goodsid) {
		int medalid = 0;
		try {
			StringBuffer sql = new StringBuffer();
			IrpGoods irpGoods=irpGoodsService.findGoodsByGoodsid(goodsid);
			sql.append("select * from irp_medal where minworth<"+irpGoods.getNeedscore()+"and maxworth>"+irpGoods.getNeedscore());
			medalid=irpMedalDAO.selectMedal(sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medalid;
	}
	@Override
	public List<IrpUserMedal> findUserMedalOfPageSize(
			PageUtil pageUtil, String _oOrderby, IrpUserMedal _userMedal,Date _starttime,Date _endtime) {
		List<IrpUserMedal> list=new ArrayList<IrpUserMedal>();
		IrpUserMedalExample example=new IrpUserMedalExample();
		Criteria criteria=example.createCriteria();
		example.setOrderByClause(_oOrderby);
		//用户名称
		if (_userMedal.getUsername()!=null&&!_userMedal.getUsername().equals("")){
			criteria.andUsernameEqualTo(_userMedal.getUsername());
		}
		//勋章名称
		if (_userMedal.getMedalname()!=null&&!_userMedal.getMedalname().equals("")){
			criteria.andMedalnameEqualTo(_userMedal.getMedalname());
		}
	    //时间段   
		if(_starttime!=null && _endtime!=null){
		    criteria.andCoverttimeBetween(_starttime,_endtime);	
	
		}
		try {
			list=this.irpUserMedalDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return list;
	}
	//检索
		@Override
		public List<IrpUserMedal> findUserMedalOfPage(
				PageUtil pageUtil, String _oOrderby,IrpUserMedal _userMedal,Date _starttime,Date _endtime) {
			List<IrpUserMedal> list=new ArrayList<IrpUserMedal>();
			IrpUserMedalExample example=new IrpUserMedalExample();
			example.setOrderByClause(_oOrderby);
			Criteria criteria=example.createCriteria();
			//用户名称
			if (_userMedal.getUsername()!=null&&!_userMedal.getUsername().equals("")){
				criteria.andUsernameEqualTo(_userMedal.getUsername());
			}
			//勋章名称
			if (_userMedal.getMedalname()!=null&&!_userMedal.getMedalname().equals("")){
				criteria.andMedalnameEqualTo(_userMedal.getMedalname());
			}
		    //时间段   
			if(_starttime!=null && _endtime!=null){
			    criteria.andCoverttimeBetween(_starttime,_endtime);	
		
			}
			try {
				list=this.irpUserMedalDAO.selectAllByExample(pageUtil, example);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return list;
		}
}
