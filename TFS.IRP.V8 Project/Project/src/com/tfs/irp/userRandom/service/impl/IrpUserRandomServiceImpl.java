package com.tfs.irp.userRandom.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.userRandom.dao.IrpUserrandomDAO;
import com.tfs.irp.userRandom.entity.IrpUserrandom;
import com.tfs.irp.userRandom.entity.IrpUserrandomExample;
import com.tfs.irp.userRandom.entity.IrpUserrandomExample.Criteria;
import com.tfs.irp.userRandom.service.IrpUserRandomService;
import com.tfs.irp.userRandom.util.GenerateLinkUtils;
import com.tfs.irp.util.TableIdUtil;

public class IrpUserRandomServiceImpl implements IrpUserRandomService {

	private IrpUserrandom irpUserrandom;
	private IrpUserrandomDAO irpUserrandomDAO; 
	private IrpUser userId;
	public GenerateLinkUtils getGenerateLinkUtils() {
		return generateLinkUtils;
	}
	public void setGenerateLinkUtils(GenerateLinkUtils generateLinkUtils) {
		this.generateLinkUtils = generateLinkUtils;
	}

	private GenerateLinkUtils generateLinkUtils;
	
	public IrpUser getUserId() {
		return userId;
	}
	public void setUserId(IrpUser userId) {
		this.userId = userId;
	}

	public IrpUserrandom getIrpUserrandom() {
		return irpUserrandom;
	}
	public void setIrpUserrandom(IrpUserrandom irpUserrandom) {
		this.irpUserrandom = irpUserrandom;
	}

	public IrpUserrandomDAO getIrpUserrandomDAO() {
		return irpUserrandomDAO;
	}
	public void setIrpUserrandomDAO(IrpUserrandomDAO irpUserrandomDAO) {
		this.irpUserrandomDAO = irpUserrandomDAO;
	}
	

	@SuppressWarnings("finally")
	@Override
	public int addUserRandom(IrpUser irpuser,String randoms) {
		int i=0;
		IrpUserrandom irpUserRandom = new IrpUserrandom();
		List<IrpUserrandom> list =findUserRandom(irpuser.getUserid());
		if(list.size()>0){			
				Long tableid = list.get(0).getRandomid();
				irpUserRandom.setRandomid(tableid);
				irpUserRandom.setUserid(list.get(0).getUserid());
				String random =GenerateLinkUtils.generateCheckcode(irpuser,randoms);
				irpUserRandom.setRandoms(random);
				Date sendtime = new Date();
				irpUserRandom.setSendtime(sendtime);
				try {
					i=irpUserrandomDAO.updateByPrimaryKey(irpUserRandom);					
				} catch (SQLException e) {
					i=0;
				}finally{
					return i;
				}			
		}else{
			@SuppressWarnings("static-access")
			Long tableid = TableIdUtil.getNextId(irpUserRandom.TABLE_NAME);
			irpUserRandom.setRandomid(tableid);
			irpUserRandom.setUserid(irpuser.getUserid());
			String random =GenerateLinkUtils.generateCheckcode(irpuser,randoms);
			irpUserRandom.setRandoms(random);
			Date sendtime = new Date();
			irpUserRandom.setSendtime(sendtime);
			try {
				irpUserrandomDAO.insert(irpUserRandom);
				i=1;
			} catch (SQLException e) {
				i=0;
			}finally{
				return i;
			}
		}		
	}
	
	@Override
	public List<IrpUserrandom> findUserRandom(long _userid) {
		List<IrpUserrandom> irpUserRandom = null;
		IrpUserrandomExample example = new IrpUserrandomExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(_userid);
		try {
			irpUserRandom= this.irpUserrandomDAO.selectByExample(example);
		} catch (SQLException e) {
			
		}
		return irpUserRandom;
	}
	
	@SuppressWarnings("finally")
	@Override
	public int delUserRandom(long _id){
		int i=0;
		IrpUserrandomExample example = new IrpUserrandomExample();
		Criteria criteria =example.createCriteria();
		criteria.andRandomidEqualTo(_id);
		try {
			i=this.irpUserrandomDAO.deleteByExample(example);
		} catch (SQLException e) {
			i=0;
		}finally{
			return i;
		}
	}

}
