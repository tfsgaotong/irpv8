package com.tfs.irp.docstatus.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.tfs.irp.docstatus.dao.IrpDocstatusDAO;
import com.tfs.irp.docstatus.entity.IrpDocstatus;
import com.tfs.irp.docstatus.entity.IrpDocstatusExample;
import com.tfs.irp.docstatus.service.IrpDocStatusService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpDocStatusServiceImpl implements IrpDocStatusService {
	private IrpDocstatusDAO irpDocstatusDAO;

	public IrpDocstatusDAO getIrpDocstatusDAO() {
		return irpDocstatusDAO;
	}

	public void setIrpDocstatusDAO(IrpDocstatusDAO irpDocstatusDAO) {
		this.irpDocstatusDAO = irpDocstatusDAO;
	}
	 @Override
		public List<IrpDocstatus> findAllStatus() {
			List<IrpDocstatus> status=null;
			try {
				status = irpDocstatusDAO.findAllStatus();
			} catch (SQLException e) { 
				e.printStackTrace();
			}
			return status;
		}

	@Override
	public int insertDocstatus(IrpDocstatus docstatus) { 
		int nCount=0;
		LogUtil logUtil=null;
		try {
			logUtil=new LogUtil("DOCSTATUS_ADD", docstatus);
			 docstatus.setStatusid(TableIdUtil.getNextId(IrpDocstatus.TABLE_NAME));
			 docstatus.setCrtime(new Date());
			 IrpUser irpUser=LoginUtil.getLoginUser();
			 docstatus.setCruser(irpUser.getUsername());
			 docstatus.setCruserid(irpUser.getUserid());
			 irpDocstatusDAO.insertSelective(docstatus); 
			 logUtil.successLogs("添加知识状态["+logUtil.getLogUser()+"],成功");
				nCount=1;
		} catch (SQLException e) {
			logUtil.errorLogs("添加知识状态["+logUtil.getLogUser()+"],失败",e);
			nCount=0;
			e.printStackTrace();
		} 
		return nCount;
	}

	@Override
	public int deleteDocustatusByStatusId(Long statusid) {
		int nCount=0;
		LogUtil logUtil=null;
		 try {
			 IrpDocstatus _IrpDocstatus=irpDocstatusDAO.selectByPrimaryKey(statusid);
			 logUtil=new LogUtil("DOCSTATUS_DELETE", _IrpDocstatus);
			 
			 if(statusid<=20){
				 nCount=0;
			 }else{ 
				 nCount=irpDocstatusDAO.deleteByPrimaryKey(statusid);
			 }
			 logUtil.successLogs("删除知识状态["+logUtil.getLogUser()+"],成功");
		} catch (SQLException e) {
			logUtil.errorLogs("删除知识状态["+logUtil.getLogUser()+"],失败",e);
			e.printStackTrace();
		}
		return nCount;
	}

	@Override
	public int updateDocustatus(IrpDocstatus docstatus) {
		int nCount=0;
		LogUtil logUtil=null;
		try {
			IrpDocstatus irpDocstatus=irpDocstatusDAO.selectByPrimaryKey(docstatus.getStatusid());
			logUtil=new LogUtil("DOCSTATUS_UPDATE", irpDocstatus);
			nCount=irpDocstatusDAO.updateByPrimaryKeySelective(docstatus);
			 logUtil.successLogs("修改知识状态["+logUtil.getLogUser()+"],成功");
		} catch (SQLException e) {
			logUtil.errorLogs("修改知识状态["+logUtil.getLogUser()+"],失败",e);
			e.printStackTrace();
		}
		return nCount;
	}
		
@Override
public IrpDocstatus finDocstatusByStatusId(Long docstatus) {
	IrpDocstatus irpDocstatus=null;
	try {
		irpDocstatus= irpDocstatusDAO.selectByPrimaryKey(docstatus);
	} catch (SQLException e) { 
		e.printStackTrace();
	}
	return irpDocstatus;
}
@Override
public List<IrpDocstatus> findAllStatusinfo(PageUtil pageUtil,HashMap<String,Object> map,String sOrderByClause) {
	List<IrpDocstatus> status=null;
	try {
		 IrpDocstatusExample example=new IrpDocstatusExample(); 
	  	 if(map.get("searchWord")!=null && map.get("searchType")!=null ){
			 String _sSearchWord=map.get("searchWord").toString(); 
			 String _sSearchType=map.get("searchType").toString();  
			 if("all".equals(_sSearchType)){ 
					example.or(example.createCriteria().andSnameLike("%"+_sSearchWord+"%"));
					example.or(example.createCriteria().andSdispLike("%"+_sSearchWord+"%"));
			 } else if("sname".equals(_sSearchType)){
				    example.createCriteria().andSnameLike("%"+_sSearchWord+"%") ;
			 } else if("sdisp".equals(_sSearchType)){
				    example.createCriteria().andSdispLike("%"+_sSearchWord+"%");
			 }
		 }   
	  	  //排序
		 if(sOrderByClause==null || sOrderByClause.equals("")){
			 sOrderByClause = "docorder desc";
		 }
		 example.setOrderByClause(sOrderByClause);  
		status = irpDocstatusDAO.selectByExample(pageUtil,example);
	} catch (SQLException e) { 
		e.printStackTrace();
	}
	return status;
}
	@Override
	public boolean findStatusBySname(String docstatusname) {
		boolean b=true;
		try {
			IrpDocstatusExample example=new IrpDocstatusExample();
			example.createCriteria().andSnameEqualTo(docstatusname);
			List<IrpDocstatus> list=irpDocstatusDAO.selectByExample(example); 
			if(list!=null && list.size()>0){
				b=false;
			}
		} catch (SQLException e) { 
			e.printStackTrace();
		} 
		return b;
	}
	@Override
	public int selectCountByMap(HashMap<String, Object> map) {
		int nCount=0; 
		try {  
			 IrpDocstatusExample example=new IrpDocstatusExample(); 
		  	 if(map.get("searchWord")!=null && map.get("searchType")!=null ){
				 String _sSearchWord=map.get("searchWord").toString(); 
				 String _sSearchType=map.get("searchType").toString();  
				 if("all".equals(_sSearchType)){ 
						example.or(example.createCriteria().andSnameLike("%"+_sSearchWord+"%"));
						example.or(example.createCriteria().andSdispLike("%"+_sSearchWord+"%"));
				 } else if("sname".equals(_sSearchType)){
					    example.createCriteria().andSnameLike("%"+_sSearchWord+"%") ;
				 } else if("sdisp".equals(_sSearchType)){
					    example.createCriteria().andSdispLike("%"+_sSearchWord+"%");
				 }
			 }  
			nCount=irpDocstatusDAO.countByExample(example); 
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return nCount;
	}
}
