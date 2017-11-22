package com.tfs.irp.valuesetting.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;
import com.tfs.irp.valuesetting.dao.IrpValueSettingDAO;
import com.tfs.irp.valuesetting.entity.IrpValueSetting;
import com.tfs.irp.valuesetting.entity.IrpValueSettingExample;
import com.tfs.irp.valuesetting.service.IrpValueSettingService;

public class IrpValueSettingServiceImpl implements IrpValueSettingService {

	
	private IrpValueSettingDAO irpValueSettingDAO;
	public IrpValueSettingDAO getIrpValueSettingDAO() {
		return irpValueSettingDAO;
	}
	public void setIrpValueSettingDAO(IrpValueSettingDAO irpValueSettingDAO) {
		this.irpValueSettingDAO = irpValueSettingDAO;
	}
	@Override
	public List<IrpValueSetting> getAllIrpValueSetting(PageUtil pageUtil,String _oOrderby, String _sSearchWord, String _sSearchType) {
		// TODO Auto-generated method stub
		List<IrpValueSetting> valuesettinglist = null;
		IrpValueSettingExample example = new IrpValueSettingExample();
		if("all".equals(_sSearchType)){
			example.or(example.createCriteria().andRanknameLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andRankdescLike("%"+_sSearchWord+"%"));
		} else if("rankname".equals(_sSearchType)){
			example.or(example.createCriteria().andRanknameLike("%"+_sSearchWord+"%"));	
		} else if("rankdesc".equals(_sSearchType)){
            example.or(example.createCriteria().andRankdescLike("%"+_sSearchWord+"%"));	
		}
		if(_oOrderby==null || _oOrderby.equals("")){
			_oOrderby="endscore desc";
		}
		example.setOrderByClause(_oOrderby);
		try {
			valuesettinglist = this.irpValueSettingDAO.selectByExample(example,pageUtil);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return valuesettinglist;
	}
	@Override
	public int addIrpValueSetting(IrpValueSetting _irpValueSetting) {
		// TODO Auto-generated method stub
		int status = 0;
		IrpValueSetting irpValueSetting = new IrpValueSetting();
		irpValueSetting.setSettingid(TableIdUtil.getNextId(IrpValueSetting.TABLE_NAME));
		irpValueSetting.setRankname(_irpValueSetting.getRankname());
		irpValueSetting.setRankdesc(_irpValueSetting.getRankdesc());
		irpValueSetting.setBeginscore(_irpValueSetting.getBeginscore());
		irpValueSetting.setEndscore(_irpValueSetting.getEndscore());
		irpValueSetting.setCrgroupnum(_irpValueSetting.getCrgroupnum());
		irpValueSetting.setCrchannelnum(_irpValueSetting.getCrchannelnum());
		try {
			this.irpValueSettingDAO.insertSelective(irpValueSetting);
			status = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			status = 0;
			e.printStackTrace();
		}
		
		
		return status;
	}
	@Override
	public int deleteIrpValueSetting(long _settingid) {
		// TODO Auto-generated method stub
		int status = 0;
		IrpValueSettingExample example = new IrpValueSettingExample();
		example.createCriteria().andSettingidEqualTo(_settingid);
		try {
			status = this.irpValueSettingDAO.deleteByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	@Override
	public int updateIrpValueSetting(IrpValueSetting _irpValueSetting) {
		// TODO Auto-generated method stub
		int status = 0;
		IrpValueSetting irpValueSetting = new IrpValueSetting(); 
		irpValueSetting.setSettingid(_irpValueSetting.getSettingid());
		irpValueSetting.setRankname(_irpValueSetting.getRankname());
		irpValueSetting.setRankdesc(_irpValueSetting.getRankdesc());
		irpValueSetting.setBeginscore(_irpValueSetting.getBeginscore());
		irpValueSetting.setEndscore(_irpValueSetting.getEndscore());
		irpValueSetting.setCrgroupnum(_irpValueSetting.getCrgroupnum());
		irpValueSetting.setCrchannelnum(_irpValueSetting.getCrchannelnum());
		try {
			status =  this.irpValueSettingDAO.updateByPrimaryKeySelective(irpValueSetting);
			status = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	@Override
	public String findRanknameBySumScore(long _sumscore) {
		// TODO Auto-generated method stub
		String rankname = "";
		try {
			rankname = this.irpValueSettingDAO.findRankNameBySumScore(_sumscore);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rankname;
	}
	
	@Override
	public int searchValueSettingCount(String _sSearchWord, String _sSearchType) {
		// TODO Auto-generated method stub
		int num = 0;
		IrpValueSettingExample example = new IrpValueSettingExample();  
		if("all".equals(_sSearchType)){
			example.or(example.createCriteria().andRanknameLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andRankdescLike("%"+_sSearchWord+"%"));
		} else if("rankname".equals(_sSearchType)){
			example.or(example.createCriteria().andRanknameLike("%"+_sSearchWord+"%"));	
		} else if("rankdesc".equals(_sSearchType)){
            example.or(example.createCriteria().andRankdescLike("%"+_sSearchWord+"%"));	
		}
		
		try {
			num = this.irpValueSettingDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return num;
	}
	@Override
	public IrpValueSetting irpValueSetting(long _settingid) {
		// TODO Auto-generated method stub
		IrpValueSetting irpValueSetting = null;
		try {
			irpValueSetting = this.irpValueSettingDAO.selectByPrimaryKey(_settingid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return irpValueSetting;
	}
	@Override
	public String findRankdescBySumScore(long _sumscore) {
		// TODO Auto-generated method stub
		String rankdesc = "";
		try {
			rankdesc = this.irpValueSettingDAO.findDescNameBySumScore(_sumscore);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rankdesc;
	}
	@Override
	public IrpValueSetting irpValueSettingOfGroupChannel(long _score) {
		// TODO Auto-generated method stub
		IrpValueSetting irpValueSetting = null;
		
		IrpValueSettingExample example = new IrpValueSettingExample();
		
		example.createCriteria().andBeginscoreLessThanOrEqualTo(_score);
		example.setOrderByClause("BEGINSCORE DESC");
		
		try {
				List<IrpValueSetting> setttings=this.irpValueSettingDAO.selectByExample(example);
				if(setttings!=null){
					irpValueSetting=setttings.get(0);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return irpValueSetting;
	}

}
