package com.tfs.irp.userlove.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.userlove.dao.IrpUserLoveDAO;
import com.tfs.irp.userlove.entity.IrpUserLove;
import com.tfs.irp.userlove.entity.IrpUserLoveExample;
import com.tfs.irp.userlove.entity.IrpUserLoveExample.Criteria;
import com.tfs.irp.userlove.service.IrpUserLoveService;

public class IrpUserLoveServiceImpl implements IrpUserLoveService{
	
	public IrpUserLoveDAO irpUserLoveDao;
	public IrpUserLoveDAO getIrpUserLoveDao() {
		return irpUserLoveDao;
	}
	public void setIrpUserLoveDao(IrpUserLoveDAO irpUserLoveDao) {
		this.irpUserLoveDao = irpUserLoveDao;
	}
	@Override
	public List<Long> docIdsByUserid(Long userId,Integer status) {
		List<Long> docIds=null;
		try {
			IrpUserLoveExample example=new IrpUserLoveExample();
			example.createCriteria().andUseridEqualTo(userId)
									.andStatusEqualTo(new BigDecimal(status));
			docIds=irpUserLoveDao.selectDocIdsByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return docIds;
	}
	@Override
	public List<Long> xDocids(Long userId, Integer status, Long docId) {
		List<Long> docIds=null;
		try {
			IrpUserLoveExample example=new IrpUserLoveExample();
			Criteria criteria = example.createCriteria();
			if(userId!=null && userId>0){
				criteria.andUseridEqualTo(userId);
			}
			criteria.andStatusEqualTo(new BigDecimal(status))
					.andDocidEqualTo(docId);
			docIds=irpUserLoveDao.xDocidsByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return docIds;
	}
	@Override
	public void save(Long userId, Long docid,Integer status) {
		try {
			IrpUserLove record=new IrpUserLove();
			record.setDocid(docid);
			record.setUserid(userId);
			record.setStatus(status);
			irpUserLoveDao.insertSelective(record);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void save(Long userId, Long docid, Long xDocid, Integer status) {
		try {
			IrpUserLove record=new IrpUserLove();
			record.setDocid(docid);
			record.setXdocid(xDocid);
			record.setUserid(userId);
			record.setStatus(status);
			irpUserLoveDao.insertSelective(record);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void delteDocByUserIds(Long userId,Integer status) {
		try {
			IrpUserLoveExample example=new IrpUserLoveExample();
			example.createCriteria().andUseridEqualTo(userId)
									.andStatusEqualTo(new BigDecimal(status));
			 irpUserLoveDao.deleteByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void delteDocByUserIds(Long userId,Long docId,Integer status) {
		try {
			IrpUserLoveExample example=new IrpUserLoveExample();
			Criteria criteria = example.createCriteria();
			if(userId!=null && userId.longValue()==0L){
				criteria.andUseridEqualTo(userId);
			}
			criteria.andStatusEqualTo(new BigDecimal(status));
			if(docId!=null && docId!=0L){
				criteria.andDocidEqualTo(docId);
			} 
			 irpUserLoveDao.deleteByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
@Override
public List<IrpUserLove> allData(Long userId, Integer status) {
	List<IrpUserLove> datas=null;
	try {
		IrpUserLoveExample example=new IrpUserLoveExample();
		example.createCriteria().andUseridEqualTo(userId) 
								.andStatusEqualTo(new BigDecimal(status));
		datas=irpUserLoveDao.selectByExample(example);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return datas;
}
@Override
public List<IrpUserLove> allData(Long userId,Long docId, Integer status) {
	List<IrpUserLove> datas=null;
	try {
		IrpUserLoveExample example=new IrpUserLoveExample();
		Criteria criteria = example.createCriteria();
		if(userId!=null){
			criteria.andUseridEqualTo(userId);
		} 
		if(docId!=null && docId.longValue()!=0L){
			criteria.andStatusEqualTo(new BigDecimal(status));
		}
		if(status!=null && status.intValue()!=0){
			criteria.andDocidEqualTo(docId);
		}
		datas=irpUserLoveDao.selectByExample(example);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return datas;
}
	@Override
	public void deletedoc(List<IrpUserLove> irpUserLoves) { 
		if(irpUserLoves!=null){
			for (int i = 0; i <irpUserLoves.size(); i++) {
				IrpUserLove irpUserLove=irpUserLoves.get(i); 
				delteDocByUserIds(irpUserLove.getUserid(), irpUserLove.getDocid(), irpUserLove.getStatus().intValue());
			} 
		}
	}
}
