package com.tfs.irp.usergoodslink.service.impl;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tfs.irp.goods.entity.IrpGoods;
import com.tfs.irp.goods.entity.IrpGoodsExample;
import com.tfs.irp.logs.entity.IrpLogs;
import com.tfs.irp.logs.entity.IrpLogsExample;
import com.tfs.irp.usergoodslink.dao.IrpUserCovertGoodsDAO;
import com.tfs.irp.usergoodslink.entity.IrpUserCovertGoods;
import com.tfs.irp.usergoodslink.entity.IrpUserCovertGoodsExample;
import com.tfs.irp.usergoodslink.entity.IrpUserCovertGoodsExample.Criteria;
import com.tfs.irp.usergoodslink.service.IrpUserCovertGoodsService;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.PageUtil;


public class IrpUserCovertGoodsServiceImpl implements IrpUserCovertGoodsService {
	private IrpUserCovertGoodsDAO irpUserCovertGoodsDAO;
	
	public IrpUserCovertGoodsDAO getIrpUserCovertGoodsDAO() {
		return irpUserCovertGoodsDAO;
	}

	public void setIrpUserCovertGoodsDAO(IrpUserCovertGoodsDAO irpUserCovertGoodsDAO) {
		this.irpUserCovertGoodsDAO = irpUserCovertGoodsDAO;
	}

	@Override
	public int addCovert(IrpUserCovertGoods covert) {
		int msg = 0;
		LogUtil logUtil=new LogUtil("COVERT_ADD",covert);
		try {
			irpUserCovertGoodsDAO.insert(covert);
			msg=1;
			logUtil.successLogs("兑换商品["+logUtil.getLogUser()+"]成功");
			//logUtil.successLogs( "["+covert.getCovertuser()+"["+covert.getUserid()+"]]兑换商品["+covert.getCovertgoods()+"["+covert.getGoodsid()+"]]成功");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs("兑换商品["+logUtil.getLogUser()+"]失败",e);
		}
		return msg;
	}

	@Override
	public int deleteCovert(Long covertid) {
		try {
			irpUserCovertGoodsDAO.deleteByPrimaryKey(covertid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteMoreCovert(Long covertids) {
		int msg = 0;
		LogUtil logUtil=null;
		IrpUserCovertGoods covert=null;
		try {
			covert=irpUserCovertGoodsDAO.selectByPrimaryKey(covertids);
			logUtil=new LogUtil("COVERT_ADD",covert);
			msg=irpUserCovertGoodsDAO.deleteByPrimaryKey(covertids);
			logUtil.successLogs( "["+covert.getCovertuser()+"["+covert.getUserid()+"]]删除兑换记录["+covert.getCovertgoods()+"["+covertids+"]]成功");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs( "["+covert.getCovertuser()+"["+covert.getUserid()+"]]删除兑换记录["+covert.getCovertgoods()+"["+covertids+"]]失败",e);
		}
		return msg;
	}

	@Override
	public IrpUserCovertGoods findCovertByCovertid(Long covertid) {
		IrpUserCovertGoods irpUserCovertGoods=null;
		try {
			irpUserCovertGoods=irpUserCovertGoodsDAO.selectByPrimaryKey(covertid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return irpUserCovertGoods;
	}

	@Override
	public int updateCovertByCovertid(IrpUserCovertGoods covert) {
		int msg = 1;
		try {
			msg=irpUserCovertGoodsDAO.updateByPrimaryKey(covert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public List<IrpUserCovertGoods> showAllCovert(PageUtil pageUtil,String orderField,String orderBy) {
		List<IrpUserCovertGoods> list = null;
		try {
			IrpUserCovertGoodsExample example = new IrpUserCovertGoodsExample();
			//String orderby="coverttime desc";
			//example.setOrderByClause(orderby);
			if(orderField!=null&&orderBy!=null&&!orderField.equals("")&&!orderBy.equals("")){
				example.setOrderByClause(orderField+" "+orderBy);
			}else{
				example.setOrderByClause("coverttime desc");
			}
			list=irpUserCovertGoodsDAO.selectallByExample(pageUtil,example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<IrpUserCovertGoods> showAllCovertByUserid(PageUtil pageUtil,String orderField,String orderBy,Long userid) {
		List<IrpUserCovertGoods> list = null;
		try {
			IrpUserCovertGoodsExample example = new IrpUserCovertGoodsExample();
			//String orderby="coverttime desc";
			//example.setOrderByClause(orderby);
			example.createCriteria().andUseridEqualTo(userid);
			if(orderField!=null&&orderBy!=null&&!orderField.equals("")&&!orderBy.equals("")){
				example.setOrderByClause(orderField+" "+orderBy);
			}else{
				example.setOrderByClause("coverttime desc");
			}
			list=irpUserCovertGoodsDAO.selectallByExample(pageUtil,example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int countCovert() {
		int msg = 0;
		try {
			IrpUserCovertGoodsExample example = new IrpUserCovertGoodsExample();
			msg=irpUserCovertGoodsDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}
	@Override
	public int countCovertByUserid(Long userid) {
		int msg = 0;
		try {
			IrpUserCovertGoodsExample example = new IrpUserCovertGoodsExample();
			example.createCriteria().andUseridEqualTo(userid);
			msg=irpUserCovertGoodsDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}
	@Override
	public List<IrpUserCovertGoods> findCovertOfPageSize(
			PageUtil pageUtil, String _oOrderby, IrpUserCovertGoods _irpCovert,Date _starttime,Date _endtime) {
		List<IrpUserCovertGoods> list=new ArrayList();
		IrpUserCovertGoodsExample example=new IrpUserCovertGoodsExample();
		Criteria criteria=example.createCriteria();
		example.setOrderByClause(_oOrderby);
		//兑换用户
		if (_irpCovert.getCovertuser()!=null&&!_irpCovert.getCovertuser().equals("")){
			criteria.andCovertuserEqualTo(_irpCovert.getCovertuser());
		}
		//兑换商品
		if (_irpCovert.getCovertgoods()!=null&&!_irpCovert.getCovertgoods().equals("")){
			criteria.andCovertgoodsEqualTo(_irpCovert.getCovertgoods());
		}
	    //时间段   
		if(_starttime!=null && _endtime!=null){
		    criteria.andCoverttimeBetween(_starttime,_endtime);	
	
		}
		try {
			list=this.irpUserCovertGoodsDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return list;
	}
	//检索
		@Override
		public List<IrpUserCovertGoods> findCovertOfPage(
				PageUtil pageUtil, String _oOrderby,IrpUserCovertGoods _irpCovert,Date _starttime,Date _endtime) {
			List<IrpUserCovertGoods> list=new ArrayList();
			IrpUserCovertGoodsExample example=new IrpUserCovertGoodsExample();
			example.setOrderByClause(_oOrderby);
			Criteria criteria=example.createCriteria();
			//兑换用户
			if (_irpCovert.getCovertuser()!=null&&!_irpCovert.getCovertuser().equals("")){
				criteria.andCovertuserEqualTo(_irpCovert.getCovertuser());
			}
			//兑换商品
			if (_irpCovert.getCovertgoods()!=null&&!_irpCovert.getCovertgoods().equals("")){
				criteria.andCovertgoodsEqualTo(_irpCovert.getCovertgoods());
			}
		    //时间段   
			if(_starttime!=null && _endtime!=null){
			    criteria.andCoverttimeBetween(_starttime,_endtime);	
		
			}
			try {
				list=this.irpUserCovertGoodsDAO.selectallByExample(pageUtil, example);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return list;
		}
}
