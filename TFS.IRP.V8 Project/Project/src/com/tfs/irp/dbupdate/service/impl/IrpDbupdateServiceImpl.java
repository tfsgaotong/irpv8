package com.tfs.irp.dbupdate.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.dbupdate.dao.IrpDbupdateDAO;
import com.tfs.irp.dbupdate.entity.IrpDbupdate;
import com.tfs.irp.dbupdate.entity.IrpDbupdateExample;
import com.tfs.irp.dbupdate.service.IrpDbupdateService;
import com.tfs.irp.util.PageUtil;

public class IrpDbupdateServiceImpl implements IrpDbupdateService {

	private IrpDbupdateDAO irpDbupdateDAO;

	public IrpDbupdateDAO getIrpDbupdateDAO() {
		return irpDbupdateDAO;
	}

	public void setIrpDbupdateDAO(IrpDbupdateDAO irpDbupdateDAO) {
		this.irpDbupdateDAO = irpDbupdateDAO;
	}

	@Override
	public List<IrpDbupdate> findAllIrpDbupdateTypePage(PageUtil pageUtil,
			String _oOrderby, String _sSearchWord, String _sSearchType) {
		// TODO Auto-generated method stub
		List<IrpDbupdate> list = null;
		IrpDbupdateExample  example = new IrpDbupdateExample();
		if("all".equals(_sSearchType)){
			example.or(example.createCriteria().andDbupinfoLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andDbupuserLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andDbupapptypeLike("%"+_sSearchWord+"%"));
		} else if("ckey".equals(_sSearchType)){
			example.createCriteria().andDbupinfoLike("%"+_sSearchWord+"%");
		} else if("cvalue".equals(_sSearchType)){
			example.createCriteria().andDbupuserLike("%"+_sSearchWord+"%");
		}else if("cdesc".equals(_sSearchType)){
			example.createCriteria().andDbupapptypeLike("%"+_sSearchWord+"%");
		}
		if(_oOrderby==null || _oOrderby.equals("")){
			_oOrderby="dbupid desc";
		}
		example.setOrderByClause(_oOrderby);
		try {
			list = this.irpDbupdateDAO.selectByExample(example, pageUtil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int findAllIrpDbupdateTypePageCount(
			String _sSearchWord, String _sSearchType) {
		// TODO Auto-generated method stub
		int num = 0;
		IrpDbupdateExample  example = new IrpDbupdateExample();
		if("all".equals(_sSearchType)){
			example.or(example.createCriteria().andDbupinfoLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andDbupuserLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andDbupapptypeLike("%"+_sSearchWord+"%"));
		} else if("ckey".equals(_sSearchType)){
			example.createCriteria().andDbupinfoLike("%"+_sSearchWord+"%");
		} else if("cvalue".equals(_sSearchType)){
			example.createCriteria().andDbupuserLike("%"+_sSearchWord+"%");
		}else if("cdesc".equals(_sSearchType)){
			example.createCriteria().andDbupapptypeLike("%"+_sSearchWord+"%");
		}
		try {
			num = this.irpDbupdateDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return num;
	}
	
	
}
