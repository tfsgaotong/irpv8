package com.tfs.irp.informtype.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLinkExample.Criteria;
import com.tfs.irp.informtype.dao.IrpInformTypeDAO;
import com.tfs.irp.informtype.entity.IrpInformType;
import com.tfs.irp.informtype.entity.IrpInformTypeExample;
import com.tfs.irp.informtype.service.IrpInformTypeService;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpInformTypeServiceImpl implements IrpInformTypeService{

	private IrpInformTypeDAO irpInformTypeDAO;
	
	public IrpInformTypeDAO getIrpInformTypeDAO() {
		return irpInformTypeDAO;
	}

	public void setIrpInformTypeDAO(IrpInformTypeDAO irpInformTypeDAO) {
		this.irpInformTypeDAO = irpInformTypeDAO;
	}

	@Override
	public List<IrpInformType> findAllIrpInformTypePage(PageUtil pageUtil,
			String _oOrderby,String _sSearchWord,
			String _sSearchType) {
		// TODO Auto-generated method stub
		List<IrpInformType> list = null;
		IrpInformTypeExample example = new IrpInformTypeExample();
		List<Integer> listtype = new ArrayList();
		listtype.add(IrpInformType.REPORT_TYPE);
		listtype.add(IrpInformType.REPORT_TYPE_KNOW);
		
		if("all".equals(_sSearchType)){
			example.or(example.createCriteria().andInformtypeIn(listtype).andInformkeyLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andInformtypeIn(listtype).andInformvalueLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andInformtypeIn(listtype).andInformdescLike("%"+_sSearchWord+"%"));
		} else if("ckey".equals(_sSearchType)){
			example.createCriteria().andInformtypeIn(listtype).andInformkeyLike("%"+_sSearchWord+"%");
		} else if("cvalue".equals(_sSearchType)){
			example.createCriteria().andInformtypeIn(listtype).andInformvalueLike("%"+_sSearchWord+"%");
		}else if("cdesc".equals(_sSearchType)){
			example.createCriteria().andInformtypeIn(listtype).andInformdescLike("%"+_sSearchWord+"%");
		}else{
			example.createCriteria().andInformtypeIn(listtype);
		}
		if(_oOrderby==null || _oOrderby.equals("")){
			_oOrderby="informtypeid desc";
		}
		example.setOrderByClause(_oOrderby);
		list = irpInformTypeDAO.selectByExample(example, pageUtil);
		
		return list;
	}

	@Override
	public int findAllIrpInformTypeCount(
			String _sSearchWord, String _sSearchType) {
		// TODO Auto-generated method stub
		int num = 0;
		IrpInformTypeExample example = new IrpInformTypeExample();
		List<Integer> listtype = new ArrayList();
		listtype.add(IrpInformType.REPORT_TYPE);
		listtype.add(IrpInformType.REPORT_TYPE_KNOW);
		
		if("all".equals(_sSearchType)){
			example.or(example.createCriteria().andInformtypeIn(listtype).andInformkeyLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andInformtypeIn(listtype).andInformvalueLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andInformtypeIn(listtype).andInformdescLike("%"+_sSearchWord+"%"));
		} else if("ckey".equals(_sSearchType)){
			example.createCriteria().andInformtypeIn(listtype).andInformkeyLike("%"+_sSearchWord+"%");
		} else if("cvalue".equals(_sSearchType)){
			example.createCriteria().andInformtypeIn(listtype).andInformvalueLike("%"+_sSearchWord+"%");
		}else if("cdesc".equals(_sSearchType)){
			example.createCriteria().andInformtypeIn(listtype).andInformdescLike("%"+_sSearchWord+"%");
		}else{
			example.createCriteria().andInformtypeIn(listtype);
		}

		try {
			num = this.irpInformTypeDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return num;
	}

	@Override
	public int addInformType(IrpInformType _irpInformType) {
		// TODO Auto-generated method stub
		int msg = 0;
		IrpInformType irpInformType = new IrpInformType();
		
		irpInformType.setInformtypeid(TableIdUtil.getNextId(IrpInformType.TABLE_NAME));
		irpInformType.setInformtype(_irpInformType.getInformtype());
		irpInformType.setInformkey(_irpInformType.getInformkey());
		irpInformType.setInformvalue(_irpInformType.getInformvalue());
		irpInformType.setInformdesc(_irpInformType.getInformdesc());
		irpInformType.setUserid(LoginUtil.getLoginUserId());
		irpInformType.setCrtime(new Date());
		
		try {
			this.irpInformTypeDAO.insert(irpInformType);
			msg = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return msg;
	}

	@Override
	public int searchInformType(String _informkey) {
		// TODO Auto-generated method stub
		int status = 1;
		List<IrpInformType> list = null;
		IrpInformTypeExample example = new IrpInformTypeExample();
		example.createCriteria().andInformkeyEqualTo(_informkey);
		try {
			list = this.irpInformTypeDAO.selectByExample(example);
			if (list.size()>=1) {
				status = 1;
			}else{
				status = 2;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public int deleteInformTypeById(Long _informtypeid) {
		// TODO Auto-generated method stub
		int msg = 0;
		try {
			msg = this.irpInformTypeDAO.deleteByPrimaryKey(_informtypeid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}

	@Override
	public IrpInformType irpInformType(Long _informtypeid) {
		// TODO Auto-generated method stub
		IrpInformType irpInformType = null;
		
		try {
			irpInformType = this.irpInformTypeDAO.selectByPrimaryKey(_informtypeid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return irpInformType;
	}

	@Override
	public int updateInformType(Long _informtypeid,IrpInformType _irpInformType) {
		// TODO Auto-generated method stub
		int status = 0;
		IrpInformType irpInformType = new IrpInformType();
		irpInformType.setInformtypeid(_informtypeid);
		irpInformType.setInformtype(_irpInformType.getInformtype());
		irpInformType.setInformkey(_irpInformType.getInformkey());
		irpInformType.setInformvalue(_irpInformType.getInformvalue());
		irpInformType.setInformdesc(_irpInformType.getInformdesc());
		
		try {
			status = this.irpInformTypeDAO.updateByPrimaryKeySelective(irpInformType);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	@Override
	public String[] configIdAll(String _configid) {
		String[] configAllIds=null;
		String _operone=_configid.substring(9);
		configAllIds=_operone.split(",");
		return configAllIds;
	}

	@Override
	public List<IrpInformType> findAllIrpInformType(Integer _informtype) {
		// TODO Auto-generated method stub
		List<IrpInformType> list = null;
		IrpInformTypeExample example = new IrpInformTypeExample();
		example.createCriteria().andInformtypeEqualTo(_informtype);
		try {
			list = this.irpInformTypeDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public IrpInformType irpInformType(String _informkey) {
		// TODO Auto-generated method stub
		IrpInformType irpInformType = null;
		IrpInformTypeExample example = new IrpInformTypeExample();
		example.createCriteria().andInformkeyEqualTo(_informkey);
		try {
			irpInformType = this.irpInformTypeDAO.selectByExample(example).get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return irpInformType;
	}
	
	@Override
	public List<IrpInformType> findOffenMenuIrpInformType(PageUtil pageutil,Integer IrpInformType,String _oOrderby,String _sSearchWord, String _sSearchType) {
		List<IrpInformType> list = null;
		IrpInformTypeExample example = new IrpInformTypeExample();
		List<Integer> listtype = new ArrayList();
		listtype.add(IrpInformType);
		if("all".equals(_sSearchType)){
			example.or(example.createCriteria().andInformtypeIn(listtype).andInformkeyLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andInformtypeIn(listtype).andInformvalueLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andInformtypeIn(listtype).andInformdescLike("%"+_sSearchWord+"%"));
		} else if("ckey".equals(_sSearchType)){
			example.createCriteria().andInformtypeIn(listtype).andInformkeyLike("%"+_sSearchWord+"%");
		} else if("cvalue".equals(_sSearchType)){
			example.createCriteria().andInformtypeIn(listtype).andInformvalueLike("%"+_sSearchWord+"%");
		}else if("cdesc".equals(_sSearchType)){
			example.createCriteria().andInformtypeIn(listtype).andInformdescLike("%"+_sSearchWord+"%");
		}else{
			example.createCriteria().andInformtypeIn(listtype);
		}

		if(_oOrderby==null || _oOrderby.equals("")){
			_oOrderby="informtypeid desc";
		}
		example.setOrderByClause(_oOrderby);
		list = irpInformTypeDAO.selectByExample(example, pageutil);
		return list;
	}
	@Override
	public int findIrpInformTypeProjectMenuCount(String _sSearchWord, String _sSearchType,Integer infoType) {
		int num = 0;
		IrpInformTypeExample example = new IrpInformTypeExample();
		List<Integer> listtype = new ArrayList();
		if(infoType!=null ){
			listtype.add(infoType);
		}
		if("all".equals(_sSearchType)){
			example.or(example.createCriteria().andInformtypeIn(listtype).andInformkeyLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andInformtypeIn(listtype).andInformvalueLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andInformtypeIn(listtype).andInformdescLike("%"+_sSearchWord+"%"));
		} else if("ckey".equals(_sSearchType)){
			example.createCriteria().andInformtypeIn(listtype).andInformkeyLike("%"+_sSearchWord+"%");
		} else if("cvalue".equals(_sSearchType)){
			example.createCriteria().andInformtypeIn(listtype).andInformvalueLike("%"+_sSearchWord+"%");
		}else if("cdesc".equals(_sSearchType)){
			example.createCriteria().andInformtypeIn(listtype).andInformdescLike("%"+_sSearchWord+"%");
		}else{
			example.createCriteria().andInformtypeIn(listtype);
		}
		try {
			num = this.irpInformTypeDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
}
