package com.tfs.irp.docscore.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.tfs.irp.docscore.dao.IrpDocumentScoreDAO;
import com.tfs.irp.docscore.entity.IrpDocumentScore;
import com.tfs.irp.docscore.entity.IrpDocumentScoreExample;
import com.tfs.irp.docscore.service.DocScoreService;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.document.service.IrpDocumentService;
import com.tfs.irp.util.LoginUtil;

public class DocServiceImpl implements DocScoreService {
	
	private IrpDocumentScoreDAO irpDocumentScoreDao;
	private IrpDocumentService irpDocumentService;
	public IrpDocumentScoreDAO getIrpDocumentScoreDao() {
		return irpDocumentScoreDao;
	}

	public IrpDocumentService getIrpDocumentService() {
		return irpDocumentService;
	}

	public void setIrpDocumentService(IrpDocumentService irpDocumentService) {
		this.irpDocumentService = irpDocumentService;
	}

	public void setIrpDocumentScoreDao(IrpDocumentScoreDAO irpDocumentScoreDao) {
		this.irpDocumentScoreDao = irpDocumentScoreDao;
	} 
	@Override
	public int addscore(Long docid,Long score) {
		int nCount=0;
		try {
			//修改doucment的平均分和评分人数字段avgscore和markpersonamount
			IrpDocumentWithBLOBs document=irpDocumentService.findDocumentByDocId(docid);
			if(document!=null){
				IrpDocumentScore documentScore=new IrpDocumentScore();
				documentScore.setCrtime(new Date());
				documentScore.setDocid(docid);
				documentScore.setUserid(LoginUtil.getLoginUserId());
				documentScore.setScore(score);
				irpDocumentScoreDao.insertSelective(documentScore);
				IrpDocumentWithBLOBs currDocument = new IrpDocumentWithBLOBs();
				currDocument.setDocid(docid);
				HashMap<String,Object> map = irpDocumentScoreDao.avgScore(docid);
				if(map!=null && map.get("AVGSCORE")!=null){
					currDocument.setAvgscore(new BigDecimal(map.get("AVGSCORE").toString()));//更换分数
				}else{
					currDocument.setAvgscore(BigDecimal.valueOf(0));
				}
				currDocument.setMarkpersonamount(document.getMarkpersonamount()+1);//增加人数
				nCount= irpDocumentService.updateDocumentByDocId(currDocument);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			 nCount=0;
		}
		return nCount;
	}
	@Override
	public IrpDocumentScore findPersonScore(Long docid, Long userid) {
		// TODO Auto-generated method stub
		IrpDocumentScore documentScore=null;
		
		try {
			IrpDocumentScoreExample example=new IrpDocumentScoreExample();
			example.createCriteria().andDocidEqualTo(docid)
									.andUseridEqualTo(userid);
			List<IrpDocumentScore> list=irpDocumentScoreDao.selectByExample(example);
			if(list!=null && list.size()>0){
				documentScore=list.get(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return documentScore;
	}
	@Override
	public HashMap findAvgByDocid(Long docid) {
		// TODO Auto-generated method stub
		HashMap map=null;
		try {
			 map=irpDocumentScoreDao.avgScore(docid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	@Override
	public List<IrpDocumentScore> allDatas() {
		// TODO Auto-generated method stub
		List<IrpDocumentScore> documentScores=null;
		IrpDocumentScoreExample example=new IrpDocumentScoreExample();
		try {
			documentScores=irpDocumentScoreDao.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return documentScores;
	}

	@Override
	public long getAllCount() {
		long l=0;
		IrpDocumentScoreExample example=new IrpDocumentScoreExample();
		try {
			l=irpDocumentScoreDao.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
}
