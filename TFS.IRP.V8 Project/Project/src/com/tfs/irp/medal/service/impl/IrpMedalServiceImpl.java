package com.tfs.irp.medal.service.impl;

import java.sql.SQLException;


import java.util.List;

import com.tfs.irp.form.entity.IrpForm;
import com.tfs.irp.goods.dao.IrpGoodsDAO;
import com.tfs.irp.goods.entity.IrpGoods;
import com.tfs.irp.goods.entity.IrpGoodsExample;
import com.tfs.irp.goods.service.IrpGoodsService;
import com.tfs.irp.medal.dao.IrpMedalDAO;
import com.tfs.irp.medal.entity.IrpMedal;
import com.tfs.irp.medal.entity.IrpMedalExample;
import com.tfs.irp.medal.service.IrpMedalService;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.PageUtil;

public class IrpMedalServiceImpl implements IrpMedalService {
	private IrpMedalDAO irpMedalDAO;
	
	
	public IrpMedalDAO getIrpMedalDAO() {
		return irpMedalDAO;
	}

	public void setIrpMedalDAO(IrpMedalDAO irpMedalDAO) {
		this.irpMedalDAO = irpMedalDAO;
	}

	@Override
	public int addMedal(IrpMedal medal) {
		int msg = 0;
		LogUtil logUtil=new LogUtil("MEDAL_ADD",medal);
		try {
			irpMedalDAO.insert(medal);
			msg=1;
			logUtil.successLogs( "添加勋章["+logUtil.getLogUser()+"]成功");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs( "添加勋章["+logUtil.getLogUser()+"]失败",e);
		}
		return msg;
	}

	@Override
	public int deleteMedal(Long medalid) {
		int msg=0;
		IrpMedal irpMedal=null;
		LogUtil logUtil=null;
		try {
			irpMedal=irpMedalDAO.selectByPrimaryKey(medalid);
			logUtil=new LogUtil("MEDAL_DELETE",irpMedal);
			irpMedalDAO.deleteByPrimaryKey(medalid);
			msg=1;
			logUtil.successLogs( "删除勋章["+logUtil.getLogUser()+"]成功");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs( "删除勋章["+logUtil.getLogUser()+"]失败",e);
		}
		return msg;
	}

	@Override
	public int deleteMoreMedal(Long medalids) {
		int msg = 0;
		IrpMedal irpMedal=null;
		LogUtil logUtil=null;
		try {
			irpMedal=irpMedalDAO.selectByPrimaryKey(medalids);
			logUtil=new LogUtil("MEDAL_DELETE",irpMedal);
			irpMedalDAO.deleteByPrimaryKey(medalids);
			msg++;
			logUtil.successLogs( "删除勋章["+logUtil.getLogUser()+"]成功");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs( "删除勋章["+logUtil.getLogUser()+"]失败",e);
		}
		return msg;
	}

	@Override
	public IrpMedal findMedalByMedalid(Long medalid) {
		IrpMedal irpMedal=null;
		try {
			irpMedal=irpMedalDAO.selectByPrimaryKey(medalid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return irpMedal;
	}

	@Override
	public int updateMedalByMedalid(IrpMedal irpMedal) {
		int msg = 0;
		LogUtil logUtil=new LogUtil("MEDAL_UPDATE",irpMedal);
		try {
			irpMedalDAO.updateByPrimaryKey(irpMedal);
			msg=1;
			logUtil.successLogs( "修改勋章["+logUtil.getLogUser()+"]成功");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs( "修改勋章["+logUtil.getLogUser()+"]失败",e);
		}
		return msg;
	}

	@Override
	public List<IrpMedal> showAllMedal(PageUtil pageUtil, String _orderby,String searchType,String searchWord,String orderField) {
		List<IrpMedal> list = null;
		try {
			IrpMedalExample example = new IrpMedalExample();
			if(searchWord!=null&&searchWord.trim().length()>0){
				if("medalname".equals(searchType)){
					example.or(example.createCriteria().andMedalnameLike("%"+searchWord+"%"));
				}
			}
			if(orderField!=null&&_orderby!=null&&!orderField.equals("")&&!_orderby.equals("")){
				example.setOrderByClause(orderField+" "+_orderby);
			}else{
				example.setOrderByClause("crtime desc");
			}
			list=irpMedalDAO.selectallByExample(pageUtil,example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<IrpMedal> showAllMedalByCategory(PageUtil pageUtil, String _orderby,String searchType,String searchWord,String orderField,String categoryId) {
		List<IrpMedal> list = null;
		try {
			IrpMedalExample example = new IrpMedalExample();
			if(categoryId!=null&&!categoryId.equals("")){
				if(searchWord!=null&&searchWord.trim().length()>0){
					if("medalname".equals(searchType)){
						example.createCriteria().andCategoryidEqualTo(categoryId).andMedalnameLike("%"+searchWord+"%");
					}
				}else{
					example.createCriteria().andCategoryidEqualTo(categoryId);

				}
			}else{
				if(searchWord!=null&&searchWord.trim().length()>0){
					if("medalname".equals(searchType)){
						example.createCriteria().andMedalnameLike("%"+searchWord+"%");
					}
				}
			}
			if(orderField!=null&&_orderby!=null&&!orderField.equals("")&&!_orderby.equals("")){
				example.setOrderByClause(orderField+" "+_orderby);
			}else{
				example.setOrderByClause("crtime desc");
			}
			list=irpMedalDAO.selectallByExample(pageUtil,example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int countshowAllMedalByCategory(String searchType,String searchWord,String categoryId) {
		IrpMedalExample example = new IrpMedalExample();
		int count = 0;
		if(categoryId!=null&&!categoryId.equals("")){
			if(searchWord!=null&&searchWord.trim().length()>0){
				if("medalname".equals(searchType)){
					example.createCriteria().andCategoryidEqualTo(categoryId).andMedalnameLike("%"+searchWord+"%");
				}
			}else{
				example.createCriteria().andCategoryidEqualTo(categoryId);

			}
		}else{
			if(searchWord!=null&&searchWord.trim().length()>0){
				if("medalname".equals(searchType)){
					example.createCriteria().andMedalnameLike("%"+searchWord+"%");
				}
			}
		}
		try {
			count = irpMedalDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	@Override
	public List<IrpMedal> listMedal() {
		List<IrpMedal> list = null;
		try {
			IrpMedalExample example = new IrpMedalExample();
			list=irpMedalDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int countMedal(String searchType,String searchWord) {
		int msg = 0;
		try {
			IrpMedalExample example = new IrpMedalExample();
			if(searchWord!=null&&searchWord.trim().length()>0){
				if("medalname".equals(searchType)){
					example.createCriteria().andMedalnameLike("%"+searchWord+"%");
				}
			}
			msg=irpMedalDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public List<IrpMedal> findMedalByMedalidList(List<Long> list) {
		List<IrpMedal> IrpMedal = null;
		try {
			IrpMedalExample example = new IrpMedalExample();
			example.createCriteria().andMedalidIn(list);
			IrpMedal=irpMedalDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return IrpMedal;
	}
}
