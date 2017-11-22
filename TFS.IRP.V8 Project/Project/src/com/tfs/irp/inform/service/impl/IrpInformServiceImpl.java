package com.tfs.irp.inform.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.tfs.irp.inform.dao.IrpInformDAO;
import com.tfs.irp.inform.entity.IrpInform;
import com.tfs.irp.inform.entity.IrpInformExample;
import com.tfs.irp.inform.service.IrpInformService;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpInformServiceImpl implements IrpInformService {

    private IrpInformDAO irpInformDAO;

	public IrpInformDAO getIrpInformDAO() {
		return irpInformDAO;
	}

	public void setIrpInformDAO(IrpInformDAO irpInformDAO) {
		this.irpInformDAO = irpInformDAO;
	}

	@Override
	public int addInform(IrpInform _irpInform,Integer _informtype,String _informkey) {
		// TODO Auto-generated method stub
		int status = 0;
		IrpInform irpInform = new IrpInform(); 
		irpInform.setInformid(TableIdUtil.getNextId(IrpInform.TABLENAME));
		
		irpInform.setInformtype(_informtype);
		irpInform.setInformnameid(_irpInform.getInformnameid());
		irpInform.setInformcontent(_irpInform.getInformcontent());
		
		irpInform.setInformkey(_informkey);
		irpInform.setCrtime(new Date());
		irpInform.setUserid(LoginUtil.getLoginUserId());
		irpInform.setInformstatus(IrpInform.INFORM_STATUS);
		
		try {
			this.irpInformDAO.insert(irpInform);
			status = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public int changeInformStatus(Long _informid) {
		// TODO Auto-generated method stub
		int _status = 0;
		IrpInform irpinform = new IrpInform();
		irpinform.setInformid(_informid);
		irpinform.setInformstatus(IrpInform.INFORM_STATUSDELETE);
		try {
			_status = this.irpInformDAO.updateByPrimaryKeySelective(irpinform);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return _status;
	}

	@Override
	public int changeInformStatusByillegality(Long _microblog,Integer _status) {
		// TODO Auto-generated method stub
		int status = 0;
		IrpInform record = new IrpInform();
		record.setInformstatus(_status);
		IrpInformExample example = new IrpInformExample();
		example.createCriteria().andInformnameidEqualTo(_microblog)
		                        .andInformtypeEqualTo(IrpInform.INFORMMICRO);
		try {
			status = this.irpInformDAO.updateByExampleSelective(record, example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return status;
	}
	
	@Override
	public int deleteInformByMicroblogId(Long _microblog) {
		int status = 0;
		IrpInformExample example = new IrpInformExample();
		example.createCriteria().andInformnameidEqualTo(_microblog)
		                        .andInformtypeEqualTo(IrpInform.INFORMMICRO);
		try {
			status = this.irpInformDAO.deleteByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return status;
	}
	
	@Override
	public int changeInformStatusByNameIdAndStatus(Long nameid,Integer _status,Integer informType) {
		// TODO Auto-generated method stub
		int status = 0;
		IrpInform record = new IrpInform();
		record.setInformstatus(_status);
		IrpInformExample example = new IrpInformExample();
		example.createCriteria().andInformnameidEqualTo(nameid)
		                        .andInformtypeEqualTo(informType);
		try {
			status = this.irpInformDAO.updateByExampleSelective(record, example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return status;
	}
	@Override
	public int deleteInform(Long informId ) {
		// TODO Auto-generated method stub
		int nCount=0;
		try {
			nCount=irpInformDAO.deleteByPrimaryKey(informId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public int deleteByStatus(Long nameid, Integer _status) {
		// TODO Auto-generated method stub
		int nCount=0;
		IrpInformExample example=new IrpInformExample();
		example.createCriteria().andInformnameidEqualTo(nameid)
								.andInformtypeEqualTo(_status);
		try {
			nCount=irpInformDAO.deleteByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public List<Long> findAllUserIdsByExpert(Integer informType,
			Long infromNameId) {
		// TODO Auto-generated method stub
		List<Long> userIds=null;
		IrpInformExample example=new IrpInformExample();
		example.createCriteria().andInformtypeEqualTo(informType)
								.andInformnameidEqualTo(infromNameId);
		example.setOrderByClause(" crtime desc ");
		try {
			userIds=irpInformDAO.findAllUserIdsByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userIds;
	}
	@Override
	public IrpInform findInformByExpert(Integer informType, Long informNameId,
			Long userId) {
		// TODO Auto-generated method stub
		IrpInform inform=null;
		IrpInformExample example=new IrpInformExample();
		example.createCriteria().andInformtypeEqualTo(informType)
								.andInformnameidEqualTo(informNameId)
								.andUseridEqualTo(userId);
		try {
			List<IrpInform> informs=irpInformDAO.selectByExample(example);
			if(informs!=null && informs.size()>0){
				inform=informs.get(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inform;
	}
	@Override
	public List<IrpInform> findAllInformByExpert(Integer informType,
			Long infromNameId) {
		// TODO Auto-generated method stub
		List<IrpInform> informs=null;
		IrpInformExample example=new IrpInformExample();
		example.createCriteria().andInformtypeEqualTo(informType)
								.andInformnameidEqualTo(infromNameId);
		try {
			informs=irpInformDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return informs;
	}
	@Override
	public int findCountByExpert(Integer informType, Long infromNameId) {
		// TODO Auto-generated method stub
		int nCount=0;
		IrpInformExample example=new IrpInformExample();
		example.createCriteria().andInformtypeEqualTo(informType)
									.andInformnameidEqualTo(infromNameId);
		try {
			nCount=irpInformDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nCount;
	}
}
