package com.tfs.irp.documentlogs.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.tfs.irp.documentlogs.dao.IrpDocumentLogsDAO;
import com.tfs.irp.documentlogs.entity.IrpDocumentLogs;
import com.tfs.irp.documentlogs.entity.IrpDocumentLogsExample;
import com.tfs.irp.documentlogs.service.IrpDocumentLogsService;
import com.tfs.irp.opertype.entity.IrpOpertype;
import com.tfs.irp.opertype.service.IrpOpertypeService;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpDocumentLogsServiceImpl implements IrpDocumentLogsService {
	private IrpDocumentLogsDAO irpDocumentLogsDAO;
	
	private IrpOpertypeService irpOpertypeService;

	public IrpOpertypeService getIrpOpertypeService() {
		return irpOpertypeService;
	}

	public void setIrpOpertypeService(IrpOpertypeService irpOpertypeService) {
		this.irpOpertypeService = irpOpertypeService;
	}

	public IrpDocumentLogsDAO getIrpDocumentLogsDAO() {
		return irpDocumentLogsDAO;
	}

	public void setIrpDocumentLogsDAO(IrpDocumentLogsDAO irpDocumentLogsDAO) {
		this.irpDocumentLogsDAO = irpDocumentLogsDAO;
	}
	
	@Override
	public int countIrpDocumentLogsByDocId(Long _nDocId){
		int nDataCount=0;
		IrpOpertype irpOpertype = irpOpertypeService.findOperTypeByOperType("SYS_DOCUMENT_SELECT");
		if(irpOpertype==null){
			return nDataCount;
		}
		IrpDocumentLogsExample example = new IrpDocumentLogsExample();
		example.createCriteria()
			.andDocidEqualTo(_nDocId)
			.andOpertypeidEqualTo(irpOpertype.getId());
		try {
			nDataCount = irpDocumentLogsDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nDataCount;
	}
	
	@Override
	public List<IrpDocumentLogs> findIrpDocumentLogsByDocId(Long _nDocId, PageUtil pageUtil){
		List<IrpDocumentLogs> docLogs = null;
		IrpOpertype irpOpertype = irpOpertypeService.findOperTypeByOperType("SYS_DOCUMENT_SELECT");
		if(irpOpertype==null){
			return docLogs;
		}
		IrpDocumentLogsExample example = new IrpDocumentLogsExample();
		example.createCriteria()
			.andDocidEqualTo(_nDocId)
			.andOpertypeidEqualTo(irpOpertype.getId());
		example.setOrderByClause(" OPERTIME DESC ");
		try {
			docLogs = irpDocumentLogsDAO.selectByExample(example, pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return docLogs;
	}
	
	@Override
	public int editDocumentLogs(long _nDocId) {
		int nDataCount = 0;
		long nLoginUserId = LoginUtil.getLoginUserId();
		Date now = new Date();
		IrpOpertype irpOpertype = irpOpertypeService.findOperTypeByOperType("SYS_DOCUMENT_SELECT");
		if(irpOpertype==null){
			return nDataCount;
		}
		long nOperTypeId = irpOpertype.getOpertypeid();
		IrpDocumentLogs doclogs = findLoginUserReadDoc(_nDocId, nLoginUserId, nOperTypeId);
		if(doclogs==null){
			doclogs = new IrpDocumentLogs();
			doclogs.setDoclogid(TableIdUtil.getNextId(doclogs));
			doclogs.setDocid(_nDocId);
			doclogs.setCruserid(nLoginUserId);
			doclogs.setCrtime(now);
			doclogs.setOpertime(now);
			doclogs.setOpertypeid(nOperTypeId);
			doclogs.setOpername(irpOpertype.getOpername());
			doclogs.setHitscount(1);
			try {
				irpDocumentLogsDAO.insertSelective(doclogs);
				nDataCount = 1;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			doclogs.setOpertime(now);
			doclogs.setHitscount((doclogs.getHitscount()+1));
			try {
				nDataCount = irpDocumentLogsDAO.updateByPrimaryKeySelective(doclogs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return nDataCount;
	}
	
	private IrpDocumentLogs findLoginUserReadDoc(long _nDocId, long _nUserId, long _nOperTypeId){
		IrpDocumentLogs doclogs = null;
		IrpDocumentLogsExample example = new IrpDocumentLogsExample();
		example.createCriteria().andDocidEqualTo(_nDocId)
			.andCruseridEqualTo(_nUserId)
			.andOpertypeidEqualTo(_nOperTypeId);
		try {
			List<IrpDocumentLogs> list = irpDocumentLogsDAO.selectByExample(example);
			if(list!=null && !list.isEmpty()){
				doclogs = list.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doclogs;
	}
}
