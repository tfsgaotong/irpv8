package com.tfs.irp.opertype.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.tfs.irp.opertype.dao.IrpOpertypeDAO;
import com.tfs.irp.opertype.entity.IrpOpertype;
import com.tfs.irp.opertype.entity.IrpOpertypeExample;
import com.tfs.irp.opertype.entity.IrpOpertypeExample.Criteria;
import com.tfs.irp.opertype.service.IrpOpertypeService;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpOpertypeServiceImpl implements IrpOpertypeService {

	private IrpOpertypeDAO irpOpertypeDAO;
	
	
	public IrpOpertypeDAO getIrpOpertypeDAO() {
		return irpOpertypeDAO;
	}


	public void setIrpOpertypeDAO(IrpOpertypeDAO irpOpertypeDAO) {
		this.irpOpertypeDAO = irpOpertypeDAO;
	}


	@Override
	public List<IrpOpertype> findOpertypeOfPage(PageUtil pageUtil,String _oOrderby,String _sSearchWord, String _sSearchType) {
		List<IrpOpertype> Opertypelist=null;
		//1.判断当前用户是否登录
		if (LoginUtil.getLoginUser()==null) {
			return Opertypelist;
		}
		try {
		//2.返回数据
		IrpOpertypeExample example=new IrpOpertypeExample();
		if("all".equals(_sSearchType)){
			example.or(example.createCriteria().andOpertypeLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andOpernameLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andCruserLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andOperdescLike("%"+_sSearchWord+"%"));
		} else if("opertype".equals(_sSearchType)){
			example.createCriteria().andOpertypeLike("%"+_sSearchWord+"%");
		} else if("opername".equals(_sSearchType)){
			example.createCriteria().andOpernameLike("%"+_sSearchWord+"%");
		} else if("cruser".equals(_sSearchType)){
			example.createCriteria().andCruserLike("%"+_sSearchWord+"%");
		}else if("operdesc".equals(_sSearchType)){
			example.createCriteria().andOperdescLike("%"+_sSearchWord+"%");
		}
		if(_oOrderby==null || _oOrderby.equals("")){
			_oOrderby="opertypeid desc";
		}
		example.setOrderByClause(_oOrderby);
		Opertypelist=this.irpOpertypeDAO.selectByExample(example,pageUtil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Opertypelist;
	}


	@Override
	public int findOpertypeCountByStatus(String _sSearchWord, String _sSearchType) {
     int nCount=0;
      try {
    	IrpOpertypeExample example=new IrpOpertypeExample();
    	if("all".equals(_sSearchType)){
			example.or(example.createCriteria().andOpertypeLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andOpernameLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andCruserLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andOperdescLike("%"+_sSearchWord+"%"));
		} else if("opertype".equals(_sSearchType)){
			example.createCriteria().andOpertypeLike("%"+_sSearchWord+"%");
		} else if("opername".equals(_sSearchType)){
			example.createCriteria().andOpernameLike("%"+_sSearchWord+"%");
		} else if("cruser".equals(_sSearchType)){
			example.createCriteria().andCruserLike("%"+_sSearchWord+"%");
		}else if("operdesc".equals(_sSearchType)){
			example.createCriteria().andOperdescLike("%"+_sSearchWord+"%");
		}
		nCount= this.irpOpertypeDAO.countByExample(example);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      	 return nCount;
	}


	@Override
	public int InsertOpertype(IrpOpertype irpOpertype) {
		/*
		 * 添加操作类型  0 存在  1 成功  2失败
		 * 检查操作名称是否为空 
		 */
		LogUtil logUtil = null;
		IrpOpertypeExample example=new IrpOpertypeExample();
		Criteria criteria=example.createCriteria();
		criteria.andOpernameEqualTo(irpOpertype.getOpername());
		try {	
			List list=this.irpOpertypeDAO.selectByExample(example);
		if (list.size()>0) {
			return 0;
		}
		//添加操作类型
		IrpOpertype record=new IrpOpertype();
		record.setOpertypeid(TableIdUtil.getNextId(IrpOpertype.TABLE_NAME));
		record.setOpertype(irpOpertype.getOpertype());
		record.setOpername(irpOpertype.getOpername());
		record.setOperdesc(irpOpertype.getOperdesc());
		record.setModified(irpOpertype.getModified());
		record.setCrtime(new Date());
		record.setCruser(LoginUtil.getLoginUser().getUsername());
		this.irpOpertypeDAO.insertSelective(record);
		//检查添加是否成功
		IrpOpertypeExample example2=new IrpOpertypeExample();
		Criteria criteria1= example2.createCriteria();
		criteria1.andOpernameEqualTo(irpOpertype.getOpername());
	    logUtil= new LogUtil("OPERTYPE_ADD", record);
		if( this.irpOpertypeDAO.selectByExample(example2).size()>0){
		logUtil.successLogs(logUtil.getOpername()+":["+irpOpertype.getOpertype()+"]");
			return 1;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			logUtil.errorLogs(logUtil.getOpername()+"失败",e);
		
		}
		
		return 2;
	}


	@Override
	public IrpOpertype irpOpertype(Long _opertypeId) {
		IrpOpertype irpOpertype=null;
		try {
			irpOpertype=this.irpOpertypeDAO.selectByPrimaryKey(_opertypeId);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return irpOpertype;
	}


	@Override
	public int UpdateOpertype(Long _opertypeId, IrpOpertype irpOpertype) {
		//返回更新状况 0:失败 1:成功		
		int _nCount=0;
		LogUtil logUtil=null;
		IrpOpertype irpOpertypes=new IrpOpertype();
		irpOpertypes.setOpertypeid(_opertypeId);
		irpOpertypes.setOpertype(irpOpertype.getOpertype());
		irpOpertypes.setOpername(irpOpertype.getOpername());
		irpOpertypes.setOperdesc(irpOpertype.getOperdesc());
		irpOpertypes.setModified(irpOpertype.getModified());
		try {			
			logUtil =new LogUtil("OPERTYPE_UPDATE",irpOpertypes);
			_nCount=this.irpOpertypeDAO.updateByPrimaryKeySelective(irpOpertypes);
			if (_nCount>0) {			
			logUtil.successLogs(logUtil.getOpername()+":["+irpOpertype.getOpertype()+"]");
				_nCount=1;
			}
		} catch (SQLException e) { 
			e.printStackTrace();
			logUtil.errorLogs(logUtil.getOpername()+"失败",e);
			
		}
		//修改操作类型
		return _nCount;
	}


	@Override
	public int DeleteOpertype(Long _opertypeId) {
		//删除操作类型
		LogUtil logUtil=null;
		IrpOpertype irpOpertype=this.irpOpertype(_opertypeId);
		int nCount=0;
		try {
			logUtil = new LogUtil("OPERTYPE_DELETE", irpOpertype);
			nCount= this.irpOpertypeDAO.deleteByPrimaryKey(_opertypeId);
			if (nCount>0) {
			  logUtil.successLogs(logUtil.getOpername()+"["+irpOpertype.getOpertype()+"]");	
				nCount=1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logUtil.errorLogs(logUtil.getOpername()+"失败",e);
			
		}
		return nCount;
	}


	@Override
	public String[] opertypeAllId(String _opertypeAllId) {
		String[] opertypeAllIds=null;
		String _operone=_opertypeAllId.substring(9);
		opertypeAllIds=_operone.split(",");
		return opertypeAllIds;
	}


	@Override
	public int findOpernameCountByStatus(String _opername) {
		//检查操作是否已经存在 1：可以添加 2：操作名称已存在 0：执行出错
 		IrpOpertypeExample example=new IrpOpertypeExample();
		Criteria criteria=example.createCriteria();
		criteria.andOpertypeEqualTo(_opername);
		try {
			if(this.irpOpertypeDAO.selectByExample(example).size()==0){
				return 1;
			}else{
				return 2;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return 0;
	}


	@Override
	public String findOpername(String _Opertype) {
		// TODO Auto-generated method stub
		String _opername="";
		IrpOpertypeExample example=new IrpOpertypeExample();
		Criteria criteria=example.createCriteria();
		criteria.andOpertypeEqualTo(_Opertype);
		try {
		List<IrpOpertype> list=	this.irpOpertypeDAO.selectByExample(example);
		Iterator it=list.iterator();
		while (it.hasNext()) {
			IrpOpertype irpOpertype=(IrpOpertype) it.next();
			_opername=irpOpertype.getOpername();	
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _opername;
	}


	@Override
	public List<IrpOpertype> findOperTypeByLikeOperType(String _sOperType) {
		IrpOpertypeExample example=new IrpOpertypeExample();
		example.createCriteria().andOpertypeLike(_sOperType+"%");
		List<IrpOpertype> list = new ArrayList<IrpOpertype>();
		try {
			list = irpOpertypeDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public IrpOpertype findOperTypeByOperType(String _sOperType) {
		IrpOpertype operType = null;
		IrpOpertypeExample example=new IrpOpertypeExample();
		example.createCriteria().andOpertypeEqualTo(_sOperType);
		List<IrpOpertype> list = new ArrayList<IrpOpertype>();
		try {
			list = irpOpertypeDAO.selectByExample(example);
			if(list!=null && !list.isEmpty()){
				operType = list.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return operType;
	}


}
