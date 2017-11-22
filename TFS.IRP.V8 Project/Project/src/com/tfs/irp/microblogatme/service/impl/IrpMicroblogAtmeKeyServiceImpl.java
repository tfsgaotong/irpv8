package com.tfs.irp.microblogatme.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tfs.irp.microblogatme.dao.IrpMicroblogAtmeDAO;
import com.tfs.irp.microblogatme.entity.IrpMicroblogAtmeExample;
import com.tfs.irp.microblogatme.entity.IrpMicroblogAtmeExample.Criteria;
import com.tfs.irp.microblogatme.entity.IrpMicroblogAtmeKey;
import com.tfs.irp.microblogatme.service.IrpMicroblogAtmeKeyService;
import com.tfs.irp.microblogcomment.entity.IrpMicroblogComment;
import com.tfs.irp.microblogcomment.entity.IrpMicroblogCommentExample;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpMicroblogAtmeKeyServiceImpl implements
		IrpMicroblogAtmeKeyService {
    private IrpMicroblogAtmeDAO  irpMicroblogAtmeDAO;
	public IrpMicroblogAtmeDAO getIrpMicroblogAtmeDAO() {
		return irpMicroblogAtmeDAO;
	}
	public void setIrpMicroblogAtmeDAO(IrpMicroblogAtmeDAO irpMicroblogAtmeDAO) {
		this.irpMicroblogAtmeDAO = irpMicroblogAtmeDAO;
	}
	@Override
	public int addMicroblogAtmeKey(IrpMicroblogAtmeKey _irpMicroblogAtmeKey) {
       int _nStatus = 0;
       IrpMicroblogAtmeKey irpMicroblogAtmeKey = new IrpMicroblogAtmeKey();
       irpMicroblogAtmeKey.setAtmeid(TableIdUtil.getNextId(IrpMicroblogAtmeKey.TABLE_NAME));
       irpMicroblogAtmeKey.setMicroblogid(_irpMicroblogAtmeKey.getMicroblogid());
       irpMicroblogAtmeKey.setUserid(_irpMicroblogAtmeKey.getUserid());
       irpMicroblogAtmeKey.setCrtime(new Date());
       irpMicroblogAtmeKey.setBrowsestatus(0);
       try {
		irpMicroblogAtmeDAO.insert(irpMicroblogAtmeKey);
		_nStatus=1;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
		return _nStatus;
	}
	@Override
	public List<IrpMicroblogAtmeKey> IrpMicroblogAtmeKey(
			Long userid,PageUtil pageUtil) {
		// TODO Auto-generated method stub
		List<IrpMicroblogAtmeKey> irpMicroblogAtmeKey = null;
		IrpMicroblogAtmeExample example = new IrpMicroblogAtmeExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userid);
        example.setOrderByClause("crtime desc"); 
		try {
		irpMicroblogAtmeKey = this.irpMicroblogAtmeDAO.selectByExample(example,pageUtil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return irpMicroblogAtmeKey;
	}
	@Override
	public IrpMicroblogAtmeKey getIrpMicroblogAtmeKey(
			Long userid) {
		IrpMicroblogAtmeKey irpMicroblogAtmeKey = null;
		IrpMicroblogAtmeExample example = new IrpMicroblogAtmeExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userid);
		example.setOrderByClause("crtime desc"); 
		try {
			if(irpMicroblogAtmeDAO.selectByExample(example).size()>0){
				
				irpMicroblogAtmeKey = this.irpMicroblogAtmeDAO.selectByExample(example).get(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return irpMicroblogAtmeKey;
	}
	@Override
	public int IrpMicroblogAtmeKeyCount(Long userid) {
		// TODO Auto-generated method stub
		int size = 0;
		IrpMicroblogAtmeExample example = new IrpMicroblogAtmeExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userid);
		
		try {
		size = this.irpMicroblogAtmeDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return size;
	}
	@Override
	public int deleteMicroblogAtmeKeyByAtmeId(Long _atmeid) {
		// TODO Auto-generated method stub
		int _status = 0;
		IrpMicroblogAtmeExample  example = new IrpMicroblogAtmeExample();
		Criteria criteria = example.createCriteria();
		criteria.andAtmeidEqualTo(_atmeid);
		try {
			_status =	this.irpMicroblogAtmeDAO.deleteByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _status;
	}
	@Override
	public int deleteMicroblogAtmeKeyByUserid(Long _userid) {
		// TODO Auto-generated method stub
		int _status = 0;
		IrpMicroblogAtmeExample  example = new IrpMicroblogAtmeExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(_userid);
		try {
			_status =  this.irpMicroblogAtmeDAO.deleteByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return _status;
	}
	@Override
	public int selectUnReadAtme(long _userid, int _browsestatus) {
		int _readStatus = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", _userid);
		map.put("browsestatus", _browsestatus);
		try {
			_readStatus = this.irpMicroblogAtmeDAO.selectUnReadAtme(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return _readStatus;
	}
	@Override
	public int chanageAtmeStatusByMsgId(long _userid,Long _chatMsid) {
		int _readStatus = 0;
		try {
			IrpMicroblogAtmeKey key=new IrpMicroblogAtmeKey();
			key.setAtmeid(_chatMsid);
			IrpMicroblogAtmeKey irpMicroblogAtmeKey = irpMicroblogAtmeDAO.selectAtmeByPrimaryKey(key);
			IrpMicroblogAtmeExample example = new IrpMicroblogAtmeExample();
			Criteria criteria = example.createCriteria();
			criteria.andUseridEqualTo(_userid);	
			if(irpMicroblogAtmeKey.getBrowsestatus()==0){
				irpMicroblogAtmeKey.setBrowsestatus(1);
				_readStatus = irpMicroblogAtmeDAO.updateByPrimaryKey(irpMicroblogAtmeKey);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _readStatus;
	}
}
