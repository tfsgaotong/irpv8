package com.tfs.irp.value.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;
import com.tfs.irp.value.dao.IrpValueConfigDAO;
import com.tfs.irp.value.entity.IrpValueConfig;
import com.tfs.irp.value.entity.IrpValueConfigExample;
import com.tfs.irp.value.entity.IrpValueConfigExample.Criteria;
import com.tfs.irp.value.service.IrpValueConfigService;

public class IrpValueConfigServiceImpl implements IrpValueConfigService {

	private IrpValueConfigDAO irpValueConfigDAO;
	public IrpValueConfigDAO getIrpValueConfigDAO() {
		return irpValueConfigDAO;
	}
	public void setIrpValueConfigDAO(IrpValueConfigDAO irpValueConfigDAO) {
		this.irpValueConfigDAO = irpValueConfigDAO;
	}



	@Override
	public List<IrpValueConfig> findAllValueConfigOfPage(PageUtil pageUtil,
			String _oOrderby , String _sSearchWord, String _sSearchType) {
		List<IrpValueConfig> irpValueConfigList = null;
		IrpValueConfigExample example = new IrpValueConfigExample();
		if("all".equals(_sSearchType)){
			example.or(example.createCriteria().andValuekeyLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andValuedescLike("%"+_sSearchWord+"%"));
		} else if("valuekey".equals(_sSearchType)){
			example.createCriteria().andValuekeyLike("%"+_sSearchWord+"%");
		} else if("valuedesc".equals(_sSearchType)){
			example.createCriteria().andValuedescLike("%"+_sSearchWord+"%");
		} 
		if(_oOrderby==null || _oOrderby.equals("")){
			_oOrderby="VALUEID DESC";
		}
		example.setOrderByClause(_oOrderby);

        try {
        irpValueConfigList=this.irpValueConfigDAO.selectByExample(example, pageUtil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return irpValueConfigList;
	}
	@Override
	public int valueConfigListSize(String _sSearchWord,String _sSearchType) {
		// TODO Auto-generated method stub
		int nValueConfigCount = 0;
		IrpValueConfigExample example = new IrpValueConfigExample();
		if("all".equals(_sSearchType)){
			example.or(example.createCriteria().andValuekeyLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andValuedescLike("%"+_sSearchWord+"%"));
		} else if("valuekey".equals(_sSearchType)){
			example.createCriteria().andValuekeyLike("%"+_sSearchWord+"%");
		} else if("valuedesc".equals(_sSearchType)){
			example.createCriteria().andValuedescLike("%"+_sSearchWord+"%");
		} 
		try {
			nValueConfigCount=this.irpValueConfigDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nValueConfigCount;
	}
	@Override
	public int valueConfigAdd(IrpValueConfig _irpValueConfig) {
		// 0：失败 1：成功
		LogUtil logUtil = null;
		int nStatus = 0;
		IrpValueConfig irpValueConfig = new IrpValueConfig();	
		irpValueConfig.setValueid(TableIdUtil.getNextId(IrpValueConfig.TABLE_NAME));
		if (_irpValueConfig.getValuekey()==null) {
			_irpValueConfig.setValuekey("");
		}
		irpValueConfig.setValuekey(_irpValueConfig.getValuekey());
		if (_irpValueConfig.getValuedesc()==null) {
			_irpValueConfig.setValuedesc("");
		}
		irpValueConfig.setValuedesc(_irpValueConfig.getValuedesc());
		irpValueConfig.setScore(_irpValueConfig.getScore());
		irpValueConfig.setExperience(_irpValueConfig.getExperience());
		irpValueConfig.setStartusing(_irpValueConfig.getStartusing());
		irpValueConfig.setMethodname(_irpValueConfig.getMethodname());
		irpValueConfig.setParameters(_irpValueConfig.getParameters());
		irpValueConfig.setBeandao(_irpValueConfig.getBeandao());
		irpValueConfig.setBeanidname(_irpValueConfig.getBeanidname());
		irpValueConfig.setUsername(_irpValueConfig.getUsername());
		try {
		 logUtil = new LogUtil("CONTRIBUTE_TYPE_ADD", irpValueConfig);	
		this.irpValueConfigDAO.insertSelective(irpValueConfig);
		logUtil.successLogs(logUtil.getOpername()+"["+_irpValueConfig.getValuekey()+"]");
		nStatus=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logUtil.errorLogs(logUtil.getOpername(),e);
		}
		return nStatus;
	}
	@Override
	public int valueConfigUpdate(long _nValueid,IrpValueConfig _irpValueConfig) {
		// 0：失败 1：成功
		LogUtil logUtil = null;
		int nStatus = 0;
		IrpValueConfig irpValueConfig = new IrpValueConfig();	
		irpValueConfig.setValueid(_nValueid);
		if (_irpValueConfig.getValuekey()==null) {
			_irpValueConfig.setValuekey("");
		}
		irpValueConfig.setValuekey(_irpValueConfig.getValuekey());
		if (_irpValueConfig.getValuedesc()==null) {
			_irpValueConfig.setValuedesc("");
		}
		irpValueConfig.setValuedesc(_irpValueConfig.getValuedesc());
		irpValueConfig.setScore(_irpValueConfig.getScore());
		irpValueConfig.setExperience(_irpValueConfig.getExperience());
		irpValueConfig.setStartusing(_irpValueConfig.getStartusing());
		irpValueConfig.setMethodname(_irpValueConfig.getMethodname());
		irpValueConfig.setParameters(_irpValueConfig.getParameters());
		irpValueConfig.setBeandao(_irpValueConfig.getBeandao());
		irpValueConfig.setBeanidname(_irpValueConfig.getBeanidname());
		irpValueConfig.setUsername(_irpValueConfig.getUsername());
		try {
		logUtil = new LogUtil("CONTRIBUTE_TYPE_UPDATE", irpValueConfig);
		nStatus=this.irpValueConfigDAO.updateByPrimaryKeySelective(irpValueConfig);
		logUtil.successLogs(logUtil.getOpername()+"["+_irpValueConfig.getValuekey()+"]");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logUtil.errorLogs(logUtil.getOpername(),e);
		}
		return nStatus;
	}
	@Override
	public int valueConfigDelete(long _nValueid) {
		// 0：失败 1：成功
		int nStatus = 0;
		LogUtil logUtil = null;
		IrpValueConfig irpValueConfig = this.irpValueConfig(_nValueid);
		try {
			logUtil = new LogUtil("ONTRIBUTE_TYPE_DELETE", irpValueConfig);
			nStatus=this.irpValueConfigDAO.deleteByPrimaryKey(_nValueid);
			logUtil.successLogs(logUtil.getOpername()+"["+irpValueConfig.getValuekey()+"]");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logUtil.errorLogs(logUtil.getOpername(),e);
		}
		return nStatus;
	}
	@Override
	public IrpValueConfig irpValueConfig(long _nValueid) {
		// TODO Auto-generated method stub
		IrpValueConfig irpValueConfig = null;
		try {
			irpValueConfig=this.irpValueConfigDAO.selectByPrimaryKey(_nValueid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return irpValueConfig;
	}
	@Override
	public String[] valueIdAll(String _sValueid) {
		String[] configAllIds=null;
		String _operone=_sValueid.substring(9);
		configAllIds=_operone.split(",");
		return configAllIds;
	}
	@Override
	public int boolValueKey(String _valueKey) {
		//1存在  2不存在
		int nStatus = 0;
		IrpValueConfigExample example = new IrpValueConfigExample();
		Criteria criteria = example.createCriteria();
		criteria.andValuekeyEqualTo(_valueKey);
		try {
		List list=this.irpValueConfigDAO.selectByExample(example);
		if (list.size()>=1) {
			nStatus=1;
		}else{
			nStatus=2;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nStatus;
	}
	
	@Override
	public List<IrpValueConfig> findValueConfigByMethodName(String _sMethodName) {
		List<IrpValueConfig> list = null;
		IrpValueConfigExample example = new IrpValueConfigExample();
		example.createCriteria().andMethodnameEqualTo(_sMethodName).andStartusingEqualTo(1);
		try {
			list = irpValueConfigDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public IrpValueConfig irpValueConfigByValueKey(String _valuekey) {
		// TODO Auto-generated method stub
		IrpValueConfig irpValueConfig = null;
		IrpValueConfigExample example = new IrpValueConfigExample();
		example.createCriteria().andValuekeyEqualTo(_valuekey);
	try {
		irpValueConfig = this.irpValueConfigDAO.selectByExample(example).get(0);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	
		return irpValueConfig;
	}


}
