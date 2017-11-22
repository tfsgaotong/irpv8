package com.tfs.irp.documentread.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.tfs.irp.documentread.dao.IrpDocumentReadedDAO;
import com.tfs.irp.documentread.entity.IrpDocumentReaded;
import com.tfs.irp.documentread.entity.IrpDocumentReadedExample;
import com.tfs.irp.documentread.service.DocumentReadedService;

public class DocumentReadedServiceImpl implements DocumentReadedService {

		private IrpDocumentReadedDAO irpDocumentReadedDAO;

		public IrpDocumentReadedDAO getIrpDocumentReadedDAO() {
			return irpDocumentReadedDAO;
		}

		public void setIrpDocumentReadedDAO(IrpDocumentReadedDAO irpDocumentReadedDAO) {
			this.irpDocumentReadedDAO = irpDocumentReadedDAO;
		}
		
		@Override
		public List<IrpDocumentReaded> findData(Long _docid, Long userid) {
		// TODO Auto-generated method stub
			 List<IrpDocumentReaded> list=null;
			IrpDocumentReadedExample example=new IrpDocumentReadedExample();
			try {
				list=	irpDocumentReadedDAO.selectByExample(example);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
		}
		
		@Override
		public void addData(Long _docid, Long _userid) {
		// TODO Auto-generated method stub
		IrpDocumentReaded record=new IrpDocumentReaded();
		record.setDocid(_docid);
		record.setUserid(_userid);
		record.setCrtime(new Date());
		try {
			irpDocumentReadedDAO.insertSelective(record);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		@Override
		public int coungData(Long _docid) {
		// TODO Auto-generated method stub
			int nCount=0;
			IrpDocumentReadedExample example=new IrpDocumentReadedExample();
			try {
				nCount=irpDocumentReadedDAO.countByExample(example);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return nCount;
		}
}
