package com.tfs.irp.documentcollection.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.solr.common.util.Hash;
import org.apache.struts2.ServletActionContext;

import com.tfs.irp.chnl_doc_link.dao.IrpChnlDocLinkDAO;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLinkExample;
import com.tfs.irp.document.dao.IrpDocumentDAO;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.entity.IrpDocumentExample;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.documentcollection.dao.IrpDocumentCollectionDAO;
import com.tfs.irp.documentcollection.entity.IrpDocumentCollection;
import com.tfs.irp.documentcollection.entity.IrpDocumentCollectionExample;
import com.tfs.irp.documentcollection.service.IrpDocumentCollectionService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;

public class IrpDocumentCollectionServiceImpl implements IrpDocumentCollectionService {
	private IrpDocumentCollectionDAO irpDocumentCollectionDAO;
	private IrpChnlDocLinkDAO irpChnl_Doc_LinkDAO;
	private IrpDocumentDAO irpDocumentDAO;
	private IrpUserService irpUserService;
	
	@Override
	public int count(HashMap<String, Object> map) {
		int nCount=0;
		
		try {
			IrpUser irpUser=LoginUtil.getLoginUser();
			IrpDocumentCollectionExample collectionExample=new IrpDocumentCollectionExample();
			collectionExample.createCriteria().andUseridEqualTo(new BigDecimal(irpUser.getUserid()));
			Object sOrderByClause=map.get("sOrderByClause");
			if(sOrderByClause==null){
				sOrderByClause="cdate desc";
			} 
			collectionExample.setOrderByClause(sOrderByClause.toString());
			nCount=irpDocumentCollectionDAO.countByExample(collectionExample);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	public List<IrpChnlDocLink> myAllDocCollection(HashMap<String,Object> map,PageUtil pageUtil) {
		List<IrpChnlDocLink> chnlDocLinks=null;
		try {
			IrpUser irpUser=LoginUtil.getLoginUser();
			IrpDocumentCollectionExample collectionExample=new IrpDocumentCollectionExample();
			collectionExample.createCriteria().andUseridEqualTo(new BigDecimal(irpUser.getUserid()));
			Object sOrderByClause=map.get("sOrderByClause");
			if(sOrderByClause==null){
				sOrderByClause="cdate desc";
			} 
			collectionExample.setOrderByClause(sOrderByClause.toString());
			List<Long> docids=irpDocumentCollectionDAO.selectPrimaryKeyByExample(collectionExample, pageUtil);
			if(docids!=null && docids.size()>0){
				IrpChnlDocLinkExample chnlDocLinkExample=new IrpChnlDocLinkExample();
				chnlDocLinkExample.createCriteria().andDocidIn(docids);
				chnlDocLinks=irpChnl_Doc_LinkDAO.selectByExample(chnlDocLinkExample);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chnlDocLinks;
	}
	
	@Override
	public int myDocCollectionCount() {
		int nCount=0;
		try { 
			IrpUser irpUser=LoginUtil.getLoginUser();
			IrpDocumentCollectionExample example=new IrpDocumentCollectionExample();
			example.createCriteria().andUseridEqualTo(new BigDecimal(irpUser.getUserid()));
			nCount=irpDocumentCollectionDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	public int insertFocus(Long documentid) {
		int nCount=0; 
		try {
			IrpUser irpUser=LoginUtil.getLoginUser();
			IrpDocumentCollection record=new IrpDocumentCollection();
			record.setUserid(new BigDecimal(irpUser.getUserid()));
			record.setDocid(new BigDecimal(documentid));
			record.setCdate(new Date());
			irpDocumentCollectionDAO.insertSelective(record);
			//将文档表收藏数量 加1 
			IrpDocumentExample documentExample=new IrpDocumentExample();
			documentExample.createCriteria().andDocidEqualTo(documentid);
			List<IrpDocument > docs=irpDocumentDAO.selectByExampleWithoutBLOBs(documentExample);
			if(docs!=null && docs.size()>0){ 
				IrpDocument document=docs.get(0); 
				document.setCollectioncount(document.getCollectioncount().longValue()+1);
				irpDocumentDAO.updateByPrimaryKey(document);
			}
			// 文档栏目中间表的收藏数量 加1 
			IrpChnlDocLinkExample chnlDocLinkExample=new IrpChnlDocLinkExample();
			chnlDocLinkExample.createCriteria().andDocidEqualTo(documentid);
			List<IrpChnlDocLink> docLinks=irpChnl_Doc_LinkDAO.selectByExample(chnlDocLinkExample);
			if(docLinks!=null && docLinks.size()>0){
				IrpChnlDocLink chnlDocLink=docLinks.get(0);
				chnlDocLink.setCollectcount(chnlDocLink.getCollectcount()+1);
				irpChnl_Doc_LinkDAO.updateByPrimaryKey(chnlDocLink);
			}
			nCount=1;
		} catch (SQLException e) { 
			nCount=0;
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public int deleteFocus(Long documentid) {
		int nCount=0; 
		try {
			//将文档表收藏数量 加1 
			IrpDocumentExample documentExample=new IrpDocumentExample();
			documentExample.createCriteria().andDocidEqualTo(documentid);
			List<IrpDocument > docs=irpDocumentDAO.selectByExampleWithoutBLOBs(documentExample);
			if(docs!=null && docs.size()>0){ 
				IrpDocument  document=docs.get(0);
				document.setCollectioncount(document.getCollectioncount().longValue()-1);
				irpDocumentDAO.updateByPrimaryKey(document);
			}
			// 文档栏目中间表的收藏数量 加1 
			IrpChnlDocLinkExample chnlDocLinkExample=new IrpChnlDocLinkExample();
			chnlDocLinkExample.createCriteria().andDocidEqualTo(documentid);
			List<IrpChnlDocLink> docLinks=irpChnl_Doc_LinkDAO.selectByExample(chnlDocLinkExample);
			if(docLinks!=null && docLinks.size()>0){
				IrpChnlDocLink chnlDocLink=docLinks.get(0);
				chnlDocLink.setCollectcount(chnlDocLink.getCollectcount()-1);
				irpChnl_Doc_LinkDAO.updateByPrimaryKey(chnlDocLink);
			}
			IrpUser irpUser=LoginUtil.getLoginUser(); 
			 IrpDocumentCollectionExample example=new IrpDocumentCollectionExample();
			 example.createCriteria().andUseridEqualTo(new BigDecimal(irpUser.getUserid()))
			 						.andDocidEqualTo(new BigDecimal(documentid));
			 nCount=irpDocumentCollectionDAO.deleteByExample(example); 
		} catch (SQLException e) { 
			nCount=0;
			e.printStackTrace();
		}
		return nCount;
	}
	
	public IrpDocumentCollectionDAO getIrpDocumentCollectionDAO() {
		return irpDocumentCollectionDAO;
	}

	public void setIrpDocumentCollectionDAO(
			IrpDocumentCollectionDAO irpDocumentCollectionDAO) {
		this.irpDocumentCollectionDAO = irpDocumentCollectionDAO;
	}
	public IrpChnlDocLinkDAO getIrpChnl_Doc_LinkDAO() {
		return irpChnl_Doc_LinkDAO;
	}
	public void setIrpChnl_Doc_LinkDAO(IrpChnlDocLinkDAO irpChnl_Doc_LinkDAO) {
		this.irpChnl_Doc_LinkDAO = irpChnl_Doc_LinkDAO;
	}
	public IrpDocumentDAO getIrpDocumentDAO() {
		return irpDocumentDAO;
	}
	public void setIrpDocumentDAO(IrpDocumentDAO irpDocumentDAO) {
		this.irpDocumentDAO = irpDocumentDAO;
	}
	public IrpUserService getIrpUserService() {
		return irpUserService;
	}
	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}
	@Override
	public int boolCollectByDocId(long _docid) {
		// TODO Auto-generated method stub
		int mstatus = 0;
		IrpDocumentCollectionExample example  = new IrpDocumentCollectionExample();
		example.createCriteria().andDocidEqualTo(new BigDecimal(_docid))
								.andUseridEqualTo(new BigDecimal(LoginUtil.getLoginUserId()));
		try {
			int msg = this.irpDocumentCollectionDAO.countByExample(example);
			mstatus = msg;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mstatus;
	}
	
	@Override
	public int countByDocId(long _nDocId) {
		int nCount = 0;
		IrpDocumentCollectionExample example  = new IrpDocumentCollectionExample();
		example.createCriteria().andDocidEqualTo(BigDecimal.valueOf(_nDocId));
		try {
			nCount = irpDocumentCollectionDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
}
