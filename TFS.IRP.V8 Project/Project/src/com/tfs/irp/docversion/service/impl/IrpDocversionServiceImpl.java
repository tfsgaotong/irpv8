package com.tfs.irp.docversion.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.document.dao.IrpDocumentDAO;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.entity.IrpDocumentExample;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.document.service.IrpDocumentService;
import com.tfs.irp.docversion.dao.IrpDocversionDAO;
import com.tfs.irp.docversion.entity.IrpDocversion;
import com.tfs.irp.docversion.entity.IrpDocversionExample;
import com.tfs.irp.docversion.entity.IrpDocversionWithBLOBs;
import com.tfs.irp.docversion.service.IrpDocversionService;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpDocversionServiceImpl implements IrpDocversionService {

	private IrpDocversionDAO irpDocversionDAO;
	
	private IrpDocumentDAO irpDocumentDAO;
	private IrpDocumentService irpDocumentService;
	
	
	
	public IrpDocumentService getIrpDocumentService() {
		return irpDocumentService;
	}

	public void setIrpDocumentService(IrpDocumentService irpDocumentService) {
		this.irpDocumentService = irpDocumentService;
	}

	public IrpDocumentDAO getIrpDocumentDAO() {
		return irpDocumentDAO;
	}

	public void setIrpDocumentDAO(IrpDocumentDAO irpDocumentDAO) {
		this.irpDocumentDAO = irpDocumentDAO;
	}

	public IrpDocversionDAO getIrpDocversionDAO() {
		return irpDocversionDAO;
	}

	public void setIrpDocversionDAO(IrpDocversionDAO irpDocversionDAO) {
		this.irpDocversionDAO = irpDocversionDAO;
	}

	@Override
	public long addDocVersion(IrpDocumentWithBLOBs _irpDocument,int _version) {
		// TODO Auto-generated method stub
		int status = 0;
		if (_irpDocument!=null) {
			IrpDocversionWithBLOBs irpDocversion = new IrpDocversionWithBLOBs();
			long tableid = TableIdUtil.getNextId(IrpDocversion.TABLENAME);
			irpDocversion.setDocid(tableid);
			irpDocversion.setChannelid(_irpDocument.getChannelid());
			irpDocversion.setSiteid(_irpDocument.getSiteid());
			irpDocversion.setDocversion(_version);
			irpDocversion.setDoctype(_irpDocument.getDoctype());
			irpDocversion.setDoctitle(_irpDocument.getDoctitle());
			irpDocversion.setDocstatus(_irpDocument.getDocstatus());
			irpDocversion.setDoccontent(_irpDocument.getDoccontent());
			irpDocversion.setDochtmlcon(_irpDocument.getDochtmlcon());
			irpDocversion.setDocabstract(_irpDocument.getDocabstract());
			irpDocversion.setDockeywords(_irpDocument.getDockeywords());
			irpDocversion.setDocoutupid(_irpDocument.getDocoutupid());
			irpDocversion.setDocpubtime(_irpDocument.getDocpubtime());
			irpDocversion.setCruser(_irpDocument.getCruser());
			irpDocversion.setCruserid(_irpDocument.getCruserid());
			irpDocversion.setCrtime(_irpDocument.getCrtime());
			irpDocversion.setKnowledgeid(_irpDocument.getDocid());
			irpDocversion.setHitscount(_irpDocument.getHitscount());
			irpDocversion.setReplyflag(_irpDocument.getReplyflag());
			irpDocversion.setReplycount(_irpDocument.getReplycount());
			irpDocversion.setAttachedcontent(_irpDocument.getAttachedcontent());
			irpDocversion.setDocscore(_irpDocument.getDocscore());
			
			try {
				this.irpDocversionDAO.insertSelective(irpDocversion);
				status = 1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return status;
	}

	@Override
	public int getVersionidByDocid(long _docid) {
		// TODO Auto-generated method stub
		int vid = 1;
		if (_docid>0) {
			
			
			IrpDocversionExample example = new IrpDocversionExample();
			example.createCriteria().andKnowledgeidEqualTo(_docid);
			
			example.setOrderByClause("docid desc");
			
			PageUtil pageUtil = new PageUtil(1, 1, 0);
			
			try {
				List<IrpDocversionWithBLOBs> list = this.irpDocversionDAO.selectByExampleWithBLOBs(example,pageUtil);
				if (list.size()>0) {
					IrpDocversion idversion =  list.get(0);
					if (idversion==null) {
						vid = 1;
					}else{
						if (idversion.getDocversion()!=null) {
						    vid = idversion.getDocversion()+1;
						}else{
							vid = 1;	
						}
					}
				}

			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vid;
	}

	@Override
	public List<IrpDocversionWithBLOBs> getAddDataByDocid(Long _docid, PageUtil pageutil) {
		// TODO Auto-generated method stub
		List<IrpDocversionWithBLOBs> list = null;
		IrpDocversionExample example = new IrpDocversionExample();
		example.createCriteria().andKnowledgeidEqualTo(_docid);
		example.setOrderByClause("docversion desc");
		try {
			if (_docid!=null) {
				list = this.irpDocversionDAO.selectByExampleWithBLOBs(example, pageutil);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int getAddDataByDocidNum(Long _docid) {
		// TODO Auto-generated method stub
		
		int msg = 0;
		IrpDocversionExample example = new IrpDocversionExample();
		example.createCriteria().andKnowledgeidEqualTo(_docid);
		try {
			msg = this.irpDocversionDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return msg;
	}

	@Override
	public IrpDocversionWithBLOBs irpDocversion(Long _docid) {
		// TODO Auto-generated method stub
		IrpDocversionWithBLOBs irpDocversion = null;
		if (_docid!=null) {
			
			try {
				irpDocversion = this.irpDocversionDAO.selectByPrimaryKey(_docid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		return irpDocversion;
	}

	@Override
	public List<IrpDocversionWithBLOBs> getDeleDataByDocid(Long _docid,
			PageUtil pageutil) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IrpDocversionWithBLOBs selectDocversion(long _docid) {
		// TODO Auto-generated method stub
		IrpDocversionWithBLOBs irpDocversion = null;
		try {
			irpDocversion = this.irpDocversionDAO.selectByPrimaryKey(_docid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return irpDocversion;
	}
	@Override
	public int insertDocversion(IrpDocversionWithBLOBs docversion) {
		// TODO Auto-generated method stub
		
		int statu = 0;
		
		
		//example.createCriteria().andDocoutupidEqualTo(irpDocument.getDocid());
		try {
			this.irpDocversionDAO.insertSelective(docversion);
			statu = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statu;
	}

}
