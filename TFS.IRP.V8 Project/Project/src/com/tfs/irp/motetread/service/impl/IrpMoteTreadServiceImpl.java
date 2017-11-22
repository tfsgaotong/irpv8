package com.tfs.irp.motetread.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.tfs.irp.document.dao.IrpDocumentDAO;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.mobile.web.mobileAction;
import com.tfs.irp.motetread.dao.IrpMostTreadDAO;
import com.tfs.irp.motetread.entity.IrpMostTread;
import com.tfs.irp.motetread.entity.IrpMostTreadExample;
import com.tfs.irp.motetread.service.IrpMoteTreadService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.LoginUtil;

public class IrpMoteTreadServiceImpl implements IrpMoteTreadService {
	private IrpMostTreadDAO irpMoteTreadDAOImpl;
	private IrpDocumentDAO irpDocumentDAO;
	public IrpDocumentDAO getIrpDocumentDAO() {
		return irpDocumentDAO;
	}

	public void setIrpDocumentDAO(IrpDocumentDAO irpDocumentDAO) {
		this.irpDocumentDAO = irpDocumentDAO;
	}

	public IrpMostTreadDAO getIrpMoteTreadDAOImpl() {
		return irpMoteTreadDAOImpl;
	}

	public void setIrpMoteTreadDAOImpl(IrpMostTreadDAO irpMoteTreadDAOImpl) {
		this.irpMoteTreadDAOImpl = irpMoteTreadDAOImpl;
	}
	@Override
	public int addMoteThread(Long _docid,Integer _status,Integer _mostType) {
		int nCount=0;  
		try {
			IrpUser irpUser=LoginUtil.getLoginUser();
			IrpMostTread record=new IrpMostTread();
			record.setDocid(_docid);
			record.setCruserid(irpUser.getUserid());
			record.setCrtime(new Date());
			record.setStatus(new BigDecimal(_status));
			record.setMosttype(new BigDecimal( _mostType));
			irpMoteTreadDAOImpl.insertSelective(record);
			
			if(IrpMostTread.MOTE_DOCUMENT_TYPE.equals(_mostType)){//如果是顶知识
				
				//根据—status 的值来判断是给文档表的有用和无用添加值
				IrpDocumentWithBLOBs document=irpDocumentDAO.selectByPrimaryKey(_docid); 
				if(_status.toString().equals(IrpMostTread.MOTE_TREAD_STATUS_DING.toString())){
					document.setIsvalue(document.getIsvalue()+1);
				}
				if(_status.toString().equals(IrpMostTread.MOTE_TREAD_STATUS_CAI.toString())){
					document.setNovalue(document.getNovalue()+1);
				}
				irpDocumentDAO.updateByPrimaryKeySelective(document);
				
			}
			
			
			nCount=1;
		} catch (SQLException e) {
			e.printStackTrace();
			nCount=0;
		}
		return nCount;
	}
	@Override
	public int addMoteThreadMobile(Long _docid,Integer _status,Integer _mostType) {
		int nCount=0;  
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String token = request.getParameter("token");
			IrpUser irpuser = mobileAction.getlogin(token);
			IrpMostTread record=new IrpMostTread();
			record.setDocid(_docid);
			record.setCruserid(irpuser.getUserid());
			record.setCrtime(new Date());
			record.setStatus(new BigDecimal(_status));
			record.setMosttype(new BigDecimal( _mostType));
			irpMoteTreadDAOImpl.insertSelective(record);
			
			if(IrpMostTread.MOTE_DOCUMENT_TYPE.equals(_mostType)){//如果是顶知识
				
				//根据—status 的值来判断是给文档表的有用和无用添加值
				IrpDocumentWithBLOBs document=irpDocumentDAO.selectByPrimaryKey(_docid); 
				if(_status.toString().equals(IrpMostTread.MOTE_TREAD_STATUS_DING.toString())){
					document.setIsvalue(document.getIsvalue()+1);
				}
				if(_status.toString().equals(IrpMostTread.MOTE_TREAD_STATUS_CAI.toString())){
					document.setNovalue(document.getNovalue()+1);
				}
				irpDocumentDAO.updateByPrimaryKeySelective(document);
				
			}
			
			
			nCount=1;
		} catch (SQLException e) {
			e.printStackTrace();
			nCount=0;
		}
		return nCount;
	}
	@Override
	public int IrpMoteThreadCountByQuestionid(Long _id, Integer _status,
			Integer _mostType) {
		// TODO Auto-generated method stub
		int num = 0;
		IrpMostTreadExample example = new IrpMostTreadExample();
		example.createCriteria().andDocidEqualTo(_id)
								.andStatusEqualTo(new BigDecimal(_status))
								.andMosttypeEqualTo(new BigDecimal(_mostType));
		try {
			num = this.irpMoteTreadDAOImpl.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int IrpMoteThreadCountByUser(Long _id, Integer _status,
			Integer _mostType,Long _userid) {
		// TODO Auto-generated method stub
		int status = 0;
		IrpMostTreadExample example = new IrpMostTreadExample();
		example.createCriteria().andDocidEqualTo(_id)
								.andMosttypeEqualTo(new BigDecimal(_mostType))
								.andCruseridEqualTo(_userid);
		try {
		status = this.irpMoteTreadDAOImpl.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return status;
	}
	@Override
	public int deleteIrpMoteThreadCountByUser(Long _id, Integer _status,
			Integer _mostType, Long _userid) {
		// TODO Auto-generated method stub
		int status = 0;
		IrpMostTreadExample example = new IrpMostTreadExample();
		example.createCriteria().andDocidEqualTo(_id)
								.andStatusEqualTo(new BigDecimal(_status))
								.andMosttypeEqualTo(new BigDecimal(_mostType))
								.andCruseridEqualTo(_userid);
		
		try {
			status = this.irpMoteTreadDAOImpl.deleteByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}
}