package com.tfs.irp.config.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.config.dao.IrpConfigDAO;
import com.tfs.irp.config.entity.IrpConfig;
import com.tfs.irp.config.entity.IrpConfigExample;
import com.tfs.irp.config.entity.IrpConfigExample.Criteria;
import com.tfs.irp.config.service.IrpConfigService;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpConfigServiceImpl implements IrpConfigService {
	
	private IrpConfigDAO irpConfigDAO;
	

	public IrpConfigDAO getIrpConfigDAO() {
		return irpConfigDAO;
	}


	public void setIrpConfigDAO(IrpConfigDAO irpConfigDAO) {
		this.irpConfigDAO = irpConfigDAO;
	}


	@Override
	public List<IrpConfig> findAllOfPage(PageUtil pageUtil, String _oOrderby,
			Integer _cType,String _sSearchWord, String _sSearchType) {
		 List<IrpConfig> irpconfig=null;
		  IrpConfigExample example=new IrpConfigExample();
			if("all".equals(_sSearchType)){
				example.or(example.createCriteria().andCtypeEqualTo(_cType).andCkeyLike("%"+_sSearchWord+"%"));
				example.or(example.createCriteria().andCtypeEqualTo(_cType).andCvalueLike("%"+_sSearchWord+"%"));
				example.or(example.createCriteria().andCtypeEqualTo(_cType).andCdescLike("%"+_sSearchWord+"%"));
			} else if("ckey".equals(_sSearchType)){
				example.createCriteria().andCtypeEqualTo(_cType).andCkeyLike("%"+_sSearchWord+"%");
			} else if("cvalue".equals(_sSearchType)){
				example.createCriteria().andCtypeEqualTo(_cType).andCvalueLike("%"+_sSearchWord+"%");
			}else if("cdesc".equals(_sSearchType)){
				example.createCriteria().andCtypeEqualTo(_cType).andCdescLike("%"+_sSearchWord+"%");
			}else{
				example.createCriteria().andCtypeEqualTo(_cType);
			} 
			if(_oOrderby==null || _oOrderby.equals("")){
				_oOrderby="configid desc";
			}
			example.setOrderByClause(_oOrderby);
		  try {
			  irpconfig=this.irpConfigDAO.selectByExample(example,pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  return   irpconfig;
	}


	@Override
	public int IrpConfigCount(Integer _nCType,String _sSearchWord, String _sSearchType) {
		int _configcount=0;
		IrpConfigExample example=new IrpConfigExample();
		if("all".equals(_sSearchType)){
			example.or(example.createCriteria().andCtypeEqualTo(_nCType).andCkeyLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andCtypeEqualTo(_nCType).andCvalueLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andCtypeEqualTo(_nCType).andCdescLike("%"+_sSearchWord+"%"));
		} else if("ckey".equals(_sSearchType)){
			example.createCriteria().andCtypeEqualTo(_nCType).andCkeyLike("%"+_sSearchWord+"%");
		} else if("cvalue".equals(_sSearchType)){
			example.createCriteria().andCtypeEqualTo(_nCType).andCvalueLike("%"+_sSearchWord+"%");
		}else if("cdesc".equals(_sSearchType)){
			example.createCriteria().andCtypeEqualTo(_nCType).andCdescLike("%"+_sSearchWord+"%");
		}else{
			example.createCriteria().andCtypeEqualTo(_nCType);
		} 
      try {
    	  _configcount=this.irpConfigDAO.countByExample(example);
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return _configcount;
	}


	@Override
	public int addConfigCatalogue(IrpConfig irpConfig, Integer _nCType) {
		// 返回增加目录状态 1成功 0失败
		LogUtil logUtil = null;
		int cataStatus = 0;
		IrpConfig irpconfig = new IrpConfig();
		irpconfig.setConfigid(TableIdUtil.getNextId(IrpConfig.TABLE_NAME));
		irpconfig.setCtype(_nCType);
		if (irpConfig.getCkey() == null) {
			irpConfig.setCkey("");
		}
		irpconfig.setCkey(irpConfig.getCkey().trim());
		if (irpConfig.getCvalue() == null) {
			irpConfig.setCvalue("");
		}
		irpconfig.setCvalue(irpConfig.getCvalue().trim());
		if (irpConfig.getCdesc() == null) {
			irpConfig.setCdesc("");
		}
		irpconfig.setCdesc(irpConfig.getCdesc().trim());
		irpconfig.setEncrypted(irpConfig.getEncrypted());
		irpconfig.setModified(irpConfig.getModified());
		irpconfig.setSiteid(irpConfig.getSiteid());
		if (_nCType == IrpConfig.CATALOGUE) {
			logUtil = new LogUtil("CONFIG_CATALOGUE_ADD", irpconfig);
		} else if (_nCType == IrpConfig.OTHERSET) {
			logUtil = new LogUtil("OTHER_ADD", irpconfig);
		} else if (_nCType == IrpConfig.DATASETTING) {
			logUtil = new LogUtil("OTHER_ADD", irpconfig);
		} else if (_nCType == IrpConfig.SEARCHTYPE) {
			logUtil = new LogUtil("SEARCH_OTHER_ADD", irpconfig);
		} else if (_nCType == IrpConfig.SEARCHTRSTYPE) {
			logUtil = new LogUtil("SEARCH_OTHER_ADD", irpconfig);
		} else if (_nCType == IrpConfig.SYS_EMAIL) {
			logUtil = new LogUtil("SEARCH_OTHER_ADD", irpconfig);
		}
		irpConfigDAO.insertSelective(irpconfig);
		SysConfigUtil.editSysConfigOfCache(irpconfig.getCkey(), irpconfig.getCvalue());
		logUtil.successLogs(logUtil.getOpername() + "[" + irpConfig.getCkey() + "]");
		cataStatus = 1;
		return cataStatus;
	}


	@Override
	public int findConfigCataCkey(String _ckey) {
		// 1:名称已存在  2：名称不存在
		int _cKeyState=0;
		IrpConfigExample example=new IrpConfigExample();
		 Criteria criteria=example.createCriteria();
		 criteria.andCkeyEqualTo(_ckey);
		try {
		List list=this.irpConfigDAO.selectByExample(example);
		if (list.size()>=1) {
			_cKeyState=1;
		}else{
			_cKeyState=2;
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return _cKeyState;
	}


	@Override
	public IrpConfig irpConfig(Long _configid) {
		IrpConfig irpConfig=null;
		try {
			irpConfig=this.irpConfigDAO.selectByPrimaryKey(_configid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return irpConfig;
	}


	@Override
	public int updateConfigCatalogue(IrpConfig irpConfig,Long _configid,Integer _nCtype) {
		// 1:成功  0未成功
	   LogUtil logUtil=null;
		int updatecatastatus=0;
		IrpConfig updateirpConfig=new IrpConfig();
		updateirpConfig.setConfigid(_configid);
		//updateirpConfig.setCtype(_nCtype);
		updateirpConfig.setCkey(irpConfig.getCkey()==null?irpConfig.getCkey():irpConfig.getCkey().trim());
		updateirpConfig.setCvalue(irpConfig.getCvalue()==null?irpConfig.getCvalue():irpConfig.getCvalue().trim());
		updateirpConfig.setCdesc(irpConfig.getCdesc()==null?irpConfig.getCdesc():irpConfig.getCdesc().trim());
		updateirpConfig.setEncrypted(irpConfig.getEncrypted());
		updateirpConfig.setModified(irpConfig.getModified());
		updateirpConfig.setSiteid(irpConfig.getSiteid());
		try {
			if (_nCtype==IrpConfig.CATALOGUE) {
				logUtil = new LogUtil("CONFIG_CATALOGUE_UPDATE", updateirpConfig);
			}else if(_nCtype==IrpConfig.OTHERSET){
				logUtil = new LogUtil("OTHER_UPDATE", updateirpConfig);
			}else if(_nCtype==IrpConfig.SEARCHTYPE){
				logUtil = new LogUtil("SEARCH_CONF_UPDATE", updateirpConfig);
			}else if(_nCtype==IrpConfig.SEARCHTRSTYPE){
				logUtil = new LogUtil("SEARCH_CONF_UPDATE", updateirpConfig);
			}else if(_nCtype==IrpConfig.SYS_EMAIL){
				logUtil = new LogUtil("SEARCH_CONF_UPDATE", updateirpConfig);
			}
			updatecatastatus=this.irpConfigDAO.updateByPrimaryKeySelective(updateirpConfig);
			SysConfigUtil.editSysConfigOfCache(updateirpConfig.getCkey(), updateirpConfig.getCvalue());
			if(logUtil!=null){
				logUtil.successLogs(logUtil.getOpername()+"["+irpConfig.getCkey()+"]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			  logUtil.errorLogs(logUtil.getOpername(), e);
		}
		return updatecatastatus;
	}


	@Override
	public int deleteConfigCatalogue(Long _configid,Integer _nCtype) {
		// 0:失败 1:成功
		int deletecatastatue = 0;
		LogUtil logUtil = null;
		IrpConfig irpConfig = this.irpConfig(_configid);
		if (_nCtype == IrpConfig.CATALOGUE) {
			logUtil = new LogUtil("CONFIG_CATALOGUE_DELETE", irpConfig);
		} else if (_nCtype == IrpConfig.OTHERSET) {
			logUtil = new LogUtil("OTHER_DELETE", irpConfig);
		} else if (_nCtype == IrpConfig.SEARCHTYPE) {
			logUtil = new LogUtil("OTHER_SEARCH_DELETE", irpConfig);
		} else if (_nCtype == IrpConfig.DATASETTING) {
			logUtil = new LogUtil("OTHER_datasetting_DELETE", irpConfig);
		}
		deletecatastatue = this.irpConfigDAO.deleteByPrimaryKey(_configid);
		SysConfigUtil.deleteSysConfigOfCache(irpConfig.getCkey());
		logUtil.successLogs(logUtil.getOpername() + "[" + irpConfig.getCkey() + "]");
		return deletecatastatue;
	}


	@Override
	public String[] configIdAll(String _configid) {
		String[] configAllIds=null;
		String _operone=_configid.substring(9);
		configAllIds=_operone.split(",");
		return configAllIds;
	}


	@Override
	public String selectCValueByCKey(String _ckey) {
		// TODO Auto-generated method stub
		String val = "";
		try {
			val = this.irpConfigDAO.selectCValueByCKey(_ckey);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return val;
	}




	
	
	
}
