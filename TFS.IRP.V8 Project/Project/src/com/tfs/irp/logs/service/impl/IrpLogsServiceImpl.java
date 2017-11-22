package com.tfs.irp.logs.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tfs.irp.chnl_doc_link.dao.IrpChnlDocLinkDAO;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.document.dao.IrpDocumentDAO;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.logs.dao.IrpLogsDAO;
import com.tfs.irp.logs.entity.IrpLogs;
import com.tfs.irp.logs.entity.IrpLogsExample;
import com.tfs.irp.logs.entity.IrpLogsExample.Criteria;
import com.tfs.irp.logs.service.IrpLogsService;
import com.tfs.irp.opertype.dao.IrpOpertypeDAO;
import com.tfs.irp.opertype.entity.IrpOpertype;
import com.tfs.irp.opertype.entity.IrpOpertypeExample;
import com.tfs.irp.opertype.service.IrpOpertypeService;
import com.tfs.irp.tableid.dao.IrpTableidDAO;
import com.tfs.irp.tableid.entity.IrpTableid;
import com.tfs.irp.tableid.entity.IrpTableidExample;
import com.tfs.irp.util.PageUtil;

public class IrpLogsServiceImpl implements IrpLogsService {
	
	private IrpLogsDAO irpLogsDAO;
	
	

	public IrpLogsDAO getIrpLogsDAO() {
		return irpLogsDAO;
	}


	public void setIrpLogsDAO(IrpLogsDAO irpLogsDAO) {
		this.irpLogsDAO = irpLogsDAO;
	}
	private IrpOpertypeDAO irpOpertypeDAO;
	public IrpOpertypeDAO getIrpOpertypeDAO() {
		return irpOpertypeDAO;
	}
	public void setIrpOpertypeDAO(IrpOpertypeDAO irpOpertypeDAO) {
		this.irpOpertypeDAO = irpOpertypeDAO;
	}
	//irpopertype service
	private IrpOpertypeService irpOpertypeService;
	public IrpOpertypeService getIrpOpertypeService() {
		return irpOpertypeService;
	}


	public void setIrpOpertypeService(IrpOpertypeService irpOpertypeService) {
		this.irpOpertypeService = irpOpertypeService;
	}
	//document dao
	private IrpDocumentDAO irpDocumentDAO;
	public IrpDocumentDAO getIrpDocumentDAO() {
		return irpDocumentDAO;
	}
	public void setIrpDocumentDAO(IrpDocumentDAO irpDocumentDAO) {
		this.irpDocumentDAO = irpDocumentDAO;
	}
	//chnl doc link
	private IrpChnlDocLinkDAO irpChnlDocLinkDAO;
	
	public IrpChnlDocLinkDAO getIrpChnlDocLinkDAO() {
		return irpChnlDocLinkDAO;
	}


	public void setIrpChnlDocLinkDAO(IrpChnlDocLinkDAO irpChnlDocLinkDAO) {
		this.irpChnlDocLinkDAO = irpChnlDocLinkDAO;
	}
    //tableid 
	private IrpTableidDAO irpTableidDAO;

	public IrpTableidDAO getIrpTableidDAO() {
		return irpTableidDAO;
	}


	public void setIrpTableidDAO(IrpTableidDAO irpTableidDAO) {
		this.irpTableidDAO = irpTableidDAO;
	}


	@Override
	public List<IrpLogs> findIrpLogsOfPage(
			PageUtil pageUtil, String _oOrderby,long _logtype) {
		List<IrpLogs> list=new ArrayList();
		IrpLogsExample example=new IrpLogsExample();
		if(_oOrderby==null || _oOrderby.equals("")){
			_oOrderby="logid desc";
		}
		example.setOrderByClause(_oOrderby);
		Criteria criteria= example.createCriteria();
			criteria.andLogtypeEqualTo(_logtype);
		try {
			list=this.irpLogsDAO.selectByExample(example,pageUtil);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return list;
	}

	@Override
	public int findLogsCountByStatus() {
		int _nCount=0;
		IrpLogsExample example=new IrpLogsExample();
		try {
			_nCount= this.irpLogsDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _nCount;
	}
	@Override
	public void logsUserBehaviour(IrpLogs _irpLogs) {
		// TODO Auto-generated method stub
		try {
			this.irpLogsDAO.insertSelective(_irpLogs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public IrpLogs findIrpLogsByLogid(long _logId) {
		// TODO Auto-generated method stub
		IrpLogs irpLogs=null;
		try {
			irpLogs=this.irpLogsDAO.selectByPrimaryKey(_logId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return irpLogs;
	}
	@Override
	public List<IrpOpertype> getIrpOpertype_opertype() {
		// TODO Auto-generated method stub
		List<IrpOpertype> list=new ArrayList();
		IrpOpertypeExample example=new IrpOpertypeExample();
		try {
			list=this.irpOpertypeDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}


	//检索
	@Override
	public List<IrpLogs> findIrpLogsOfPage(
			PageUtil pageUtil, String _oOrderby,IrpLogs _irpLogs,Date _starttime,Date _endtime) {
		List<IrpLogs> list=new ArrayList();
		IrpLogsExample example=new IrpLogsExample();
		if(_oOrderby==null || _oOrderby.equals("")){
			_oOrderby="logid desc";
		}
		example.setOrderByClause(_oOrderby);
		Criteria criteria=example.createCriteria();
		//日志类型
		if (_irpLogs.getLogtype()!=0) {
			criteria.andLogtypeEqualTo(_irpLogs.getLogtype());
		}
		//操作对象
		if ((!_irpLogs.getLogobjtype().equals("0"))){
			criteria.andLogobjtypeEqualTo(_irpLogs.getLogobjtype());
		}
		//操作对象id
		if (_irpLogs.getLogobjid()!=-1) {
				criteria.andLogobjidEqualTo(_irpLogs.getLogobjid());			
		}
		//操作用户
		if ((!_irpLogs.getLoguser().equals(""))){		
			criteria.andLoguserEqualTo(_irpLogs.getLoguser());
		}		
		//操作类型
		if ((!_irpLogs.getLogoptype().equals("0"))) {
			criteria.andLogoptypeEqualTo(_irpLogs.getLogoptype());
		}
	    //时间段   
		if(_starttime!=null && _endtime!=null){
		    criteria.andLogoptimeBetween(_starttime,_endtime);	
		    
		    
		}
		//结果
		if (_irpLogs.getLogresult()!=0) {
			criteria.andLogresultEqualTo(_irpLogs.getLogresult());
		}
		try {
			list=this.irpLogsDAO.selectByExample(example,pageUtil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return list;
	}

	@Override
	public int irpLogSizeOfLogtype(long _logtype) {
		int logsize=0;
		IrpLogsExample example=new IrpLogsExample();
		Criteria criteria=example.createCriteria();
			criteria.andLogtypeEqualTo(_logtype);	

		try {
			logsize=this.irpLogsDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return logsize;
	}

	//日志检索长度
	@Override
	public List<IrpLogs> findIrpLogsOfPageSize(
			PageUtil pageUtil, String _oOrderby, IrpLogs _irpLogs,Date _starttime,Date _endtime) {
		List<IrpLogs> list=new ArrayList();
		IrpLogsExample example=new IrpLogsExample();
		Criteria criteria=example.createCriteria();
		//日志类型
		
		if (_irpLogs.getLogtype()!=0) {
			criteria.andLogtypeEqualTo(_irpLogs.getLogtype());
		}
		//操作对象
		if ((!_irpLogs.getLogobjtype().equals("0"))){
			criteria.andLogobjtypeEqualTo(_irpLogs.getLogobjtype());
		}
		//操作对象id
		if (_irpLogs.getLogobjid()>=1 && _irpLogs.getLogobjid()<1000000) {
			criteria.andLogobjidEqualTo(_irpLogs.getLogobjid());
		}
		//操作用户
		if ((!_irpLogs.getLoguser().equals(""))){
			criteria.andLoguserEqualTo(_irpLogs.getLoguser());
		}
		//操作类型
		if ((!_irpLogs.getLogoptype().equals("0"))) {
			criteria.andLogoptypeEqualTo(_irpLogs.getLogoptype());
		}
	    //时间段   
		if(_starttime!=null && _endtime!=null){
		    criteria.andLogoptimeBetween(_starttime,_endtime);	
	
		}
		//结果
		if (_irpLogs.getLogresult()!=0) {
			criteria.andLogresultEqualTo(_irpLogs.getLogresult());
		}
		try {
			list=this.irpLogsDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return list;
	}
	@Override
	public int findIrpLogsOfPageSize(String logOpType,Date _starttime,Date _endtime) {
		int nCount=0;
		IrpLogsExample example=new IrpLogsExample();
		Criteria criteria=example.createCriteria();
		
		criteria.andLogoptypeEqualTo(logOpType);
	    //时间段   
		if(_starttime!=null && _endtime!=null){
		    criteria.andLogoptimeBetween(_starttime,_endtime);	
		}
		try {
			nCount=this.irpLogsDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nCount;
	}
	//document dao 通过id取得对象
	@Override
	public IrpDocument irpDocument(long _documentid){
		IrpDocument irpDocument=null;
		try {
			irpDocument=this.irpDocumentDAO.selectByPrimaryKey(_documentid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return irpDocument;
	}

   //irp chnl doc link
	@Override
	public IrpChnlDocLink irpChnlDocLink(long _channeldocid) {
       IrpChnlDocLink irpChnlDocLink=null;
       try {
		irpChnlDocLink=this.irpChnlDocLinkDAO.selectByPrimaryKey(_channeldocid);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
		return irpChnlDocLink;
	}


	@Override
	public IrpTableid irpTableid(String _tablename) {
		// TODO Auto-generated method stub
		IrpTableid irpTableid=null;
		try {
			irpTableid=this.irpTableidDAO.selectByPrimaryKey(_tablename);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return irpTableid;
	}



	@Override
	public String getOpername(String _opertype) {
		// TODO Auto-generated method stub
		return this.irpOpertypeService.findOpername(_opertype);
		
		
	}


	@Override
	public List<IrpTableid> findAllIrpTableid() {
		// TODO Auto-generated method stub
		List<IrpTableid> listIrpTableid=null;
		IrpTableidExample irpTableidExample=new IrpTableidExample();
		
		 com.tfs.irp.tableid.entity.IrpTableidExample.Criteria criteria= irpTableidExample.createCriteria();
		 criteria.andTableobjtypeIsNotNull();
		try {
		listIrpTableid=this.irpTableidDAO.selectByExample(irpTableidExample);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listIrpTableid;
	}


	@Override
	public List<IrpTableid> findAllTableObjType() {
		// TODO Auto-generated method stub
	  List list=this.irpTableidDAO.finalAllTableObjType();
		return list;
	}


	@Override
	public List<IrpLogs> findIrpLogsByDate(Date _startTime, Date _endTime) {
		// TODO Auto-generated method stub
		List list = new ArrayList();
		IrpLogsExample example = new IrpLogsExample();
		example.createCriteria().andLogoptimeBetween(_startTime, _endTime).andLogresultEqualTo(IrpLogs.LOGSUCCESS).andLogoptypeEqualTo(irpOpertypeService.findOpername(IrpLogs.USERLOGIN));
		
		try {
			Set setcount = new HashSet();
			List alllist =  this.irpLogsDAO.selectByExample(example);
			
		
			for(int i = 0;i<alllist.size();i++){
			IrpLogs irpLogs = (IrpLogs) alllist.get(i);
	        	  setcount.add(irpLogs.getLoguser());
	        	  list.add(irpLogs.getLoguser());
	   
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public int findCountOfUserByDate(String _uname, Date _startTime,
			Date _endTime) {
		// TODO Auto-generated method stub
		int num = 0;
		IrpLogsExample example = new IrpLogsExample();
		 example.createCriteria().andLogoptimeBetween(_startTime, _endTime).andLoguserEqualTo(_uname).andLogresultEqualTo(IrpLogs.LOGSUCCESS).andLogoptypeEqualTo(irpOpertypeService.findOpername(IrpLogs.USERLOGIN));
		try {
			num = this.irpLogsDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return num;
	}


	@Override
	public int findIrpLogsByDateCount(Date _startTime, Date _endTime) {
		// TODO Auto-generated method stub
		int num = 0;
		
		IrpLogsExample example = new IrpLogsExample();
		example.createCriteria().andLogoptimeBetween(_startTime, _endTime).andLogresultEqualTo(IrpLogs.LOGSUCCESS).andLogoptypeEqualTo(irpOpertypeService.findOpername(IrpLogs.USERLOGIN));
		try {
			Set setcount = new HashSet();
			List list = this.irpLogsDAO.selectByExample(example);
			for(int i =0;i<list.size();i++){
			IrpLogs irpLogs = (IrpLogs) list.get(i);
			 
			 setcount.add(irpLogs.getLoguser());
			}
			num = setcount.size();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}


	@Override
	public List<IrpLogs>  getAllDateByLogin(Date startTime,Date endTime) {
		List<IrpLogs>  list = null;
		IrpLogsExample example = new IrpLogsExample();
		example.createCriteria()
		.andLogoptimeBetween(startTime, endTime)
		.andLogresultEqualTo(IrpLogs.LOGSUCCESS).andLogoptypeEqualTo(irpOpertypeService.findOpername(IrpLogs.USERLOGIN));
		
		try {
			list = this.irpLogsDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public int  findCountByExpert(Date startTime, Date endTime,
			String _logOpType) {
		int nCount=0;
		IrpLogsExample example=new IrpLogsExample();
		Criteria criteria= example.createCriteria();
		criteria.andLogoptypeEqualTo(_logOpType);
		criteria.andLogoptimeBetween(startTime, endTime);
		try {
			List<Long> list=this.irpLogsDAO.selectUserIdsByExample(example);
			if(list!=null){
				nCount=list.size();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	public List<IrpLogs> findDocumentLogs(long _nDocId) {
		List<IrpLogs> irpLogs = null;
		IrpLogsExample example=new IrpLogsExample();
		example.createCriteria().andLogobjidEqualTo(_nDocId).andLogoptypeEqualTo("查看知识");
		example.setOrderByClause("logid desc");
		PageUtil pageUtil = new PageUtil(1, 20, 20);
		try {
			irpLogs=this.irpLogsDAO.selectByExample(example, pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return irpLogs;
	}
}
