package com.tfs.irp.document.dao.impl;

import com.tfs.irp.document.dao.IrpDocumentDAO;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.entity.IrpDocumentExample;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.SysFileUtil;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IrpDocumentDAOImpl extends SqlMapClientDaoSupport implements IrpDocumentDAO {
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCUMENT
	 * @ibatorgenerated  Mon Nov 04 10:19:08 CST 2013
	 */
	public int countByExample(IrpDocumentExample example) throws SQLException {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"IRP_DOCUMENT.ibatorgenerated_countByExample", example);
		return count;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCUMENT
	 * @ibatorgenerated  Mon Nov 04 10:19:08 CST 2013
	 */
	public int deleteByExample(IrpDocumentExample example) throws SQLException {
		int rows = getSqlMapClientTemplate().delete(
				"IRP_DOCUMENT.ibatorgenerated_deleteByExample", example);
		return rows;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCUMENT
	 * @ibatorgenerated  Mon Nov 04 10:19:08 CST 2013
	 */
	public int deleteByPrimaryKey(Long docid) throws SQLException {
		IrpDocument key = new IrpDocument();
		key.setDocid(docid);
		int rows = getSqlMapClientTemplate().delete(
				"IRP_DOCUMENT.ibatorgenerated_deleteByPrimaryKey", key);
		return rows;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCUMENT
	 * @ibatorgenerated  Mon Nov 04 10:19:08 CST 2013
	 */
	public void insert(IrpDocumentWithBLOBs record) throws SQLException {
		//处理正文图片链接
		htmlConDowith(record);
		getSqlMapClientTemplate().insert("IRP_DOCUMENT.ibatorgenerated_insert", record);
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCUMENT
	 * @ibatorgenerated  Mon Nov 04 10:19:08 CST 2013
	 */
	public void insertSelective(IrpDocumentWithBLOBs record)
			throws SQLException {
		//处理正文图片链接
		htmlConDowith(record);
		getSqlMapClientTemplate().insert("IRP_DOCUMENT.ibatorgenerated_insertSelective",
				record);
	}
	
	public void insertSelective(IrpDocument record)
			throws SQLException {
		//处理正文图片链接
		//htmlConDowith(record);
		getSqlMapClientTemplate().insert("IRP_DOCUMENT.ibatorgenerated_insertSelective",
				record);
	}
	
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCUMENT
	 * @ibatorgenerated  Mon Nov 04 10:19:08 CST 2013
	 */
	@SuppressWarnings("unchecked")
	public List<IrpDocumentWithBLOBs> selectByExampleWithBLOBs(
			IrpDocumentExample example) throws SQLException {
		List<IrpDocumentWithBLOBs> list = getSqlMapClientTemplate().queryForList(
				"IRP_DOCUMENT.ibatorgenerated_selectByExampleWithBLOBs",
				example);
		return list;
	}
	@Override
	public List<IrpDocument> selectSctterDocumentByExample( IrpDocumentExample example) throws SQLException {
		List<IrpDocument> list = getSqlMapClientTemplate().queryForList( "IRP_DOCUMENT.ibatorgenerated_SctterDocByExample",example);
		return list;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCUMENT
	 * @ibatorgenerated  Mon Nov 04 10:19:08 CST 2013
	 */
	@SuppressWarnings("unchecked")
	public List<IrpDocument> selectByExampleWithoutBLOBs(
			IrpDocumentExample example) throws SQLException {
		List<IrpDocument> list = getSqlMapClientTemplate().queryForList(
				"IRP_DOCUMENT.ibatorgenerated_selectByExample", example);
		return list;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCUMENT
	 * @ibatorgenerated  Mon Nov 04 10:19:08 CST 2013
	 */
	public IrpDocumentWithBLOBs selectByPrimaryKey(Long docid)
			throws SQLException {
		IrpDocument key = new IrpDocument();
		key.setDocid(docid);
		IrpDocumentWithBLOBs record = (IrpDocumentWithBLOBs) getSqlMapClientTemplate()
				.queryForObject(
						"IRP_DOCUMENT.ibatorgenerated_selectByPrimaryKey", key);
		return record;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCUMENT
	 * @ibatorgenerated  Mon Nov 04 10:19:08 CST 2013
	 */
	public int updateByExampleSelective(IrpDocumentWithBLOBs record,
			IrpDocumentExample example) throws SQLException {
		//处理正文图片链接
		htmlConDowith(record);
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"IRP_DOCUMENT.ibatorgenerated_updateByExampleSelective", parms);
		return rows;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCUMENT
	 * @ibatorgenerated  Mon Nov 04 10:19:08 CST 2013
	 */
	public int updateByExample(IrpDocumentWithBLOBs record,
			IrpDocumentExample example) throws SQLException {
		//处理正文图片链接
		htmlConDowith(record);
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"IRP_DOCUMENT.ibatorgenerated_updateByExampleWithBLOBs", parms);
		return rows;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCUMENT
	 * @ibatorgenerated  Mon Nov 04 10:19:08 CST 2013
	 */
	public int updateByExample(IrpDocument record, IrpDocumentExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"IRP_DOCUMENT.ibatorgenerated_updateByExample", parms);
		return rows;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCUMENT
	 * @ibatorgenerated  Mon Nov 04 10:19:08 CST 2013
	 */
	public int updateByPrimaryKeySelective(IrpDocumentWithBLOBs record)
			throws SQLException {
		//处理正文图片链接
		htmlConDowith(record);
		int rows = getSqlMapClientTemplate().update(
				"IRP_DOCUMENT.ibatorgenerated_updateByPrimaryKeySelective",
				record);
		return rows;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCUMENT
	 * @ibatorgenerated  Mon Nov 04 10:19:08 CST 2013
	 */
	public int updateByPrimaryKey(IrpDocumentWithBLOBs record)
			throws SQLException {
		//处理正文图片链接
		htmlConDowith(record);
		int rows = getSqlMapClientTemplate().update(
				"IRP_DOCUMENT.ibatorgenerated_updateByPrimaryKeyWithBLOBs",
				record);
		return rows;
	}
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table IRP_DOCUMENT
	 * @ibatorgenerated  Mon Nov 04 10:19:08 CST 2013
	 */
	public int updateByPrimaryKey(IrpDocument record) throws SQLException {
		int rows = getSqlMapClientTemplate().update(
				"IRP_DOCUMENT.ibatorgenerated_updateByPrimaryKey", record);
		return rows;
	}
	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table IRP_DOCUMENT
	 * @ibatorgenerated  Mon Nov 04 10:19:08 CST 2013
	 */
	private static class UpdateByExampleParms extends IrpDocumentExample {
		private Object record;

		public UpdateByExampleParms(Object record, IrpDocumentExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
	@Override 
	 public int sumHit(IrpDocumentExample example)throws SQLException {
		 Integer count = 0;
		 count= (Integer) getSqlMapClientTemplate().queryForObject(
					"IRP_DOCUMENT.ibatorgenerated_findSumByExample", example);
			return count;
	 }
	@Override
	public List<Long> selectDocIdsByExample(
			IrpDocumentExample example) throws SQLException {
		List<Long> list = getSqlMapClientTemplate().queryForList(
				"IRP_DOCUMENT.selectDocids",
				example);
		return list;
	}
	
	/**
	 * 处理html正文中的图片文件地址
	 * @param record
	 */
	private void htmlConDowith(IrpDocumentWithBLOBs record){
		//处理正文图片链接
	    String sConfigValue = SysConfigUtil.getSysConfigValue("SAVE_HTMLCON_PIC");
	    if(sConfigValue!=null && "true".equals(sConfigValue.toLowerCase())){
	    	record.setDochtmlcon(SysFileUtil.saveImgSrcPic(record.getDochtmlcon()));
	    }
	    //处理正文中的临时目录文件
	    htmlConTempFileDowith(record);
	}
	
	/**
	 * 处理正文中的临时文件
	 * @param _sConn
	 * @return
	 */
	private void htmlConTempFileDowith(IrpDocumentWithBLOBs record) {
		String sHtmlCon = record.getDochtmlcon();
		//起始位置
		int nIndex = 0;
		String sFileMark = "/readfile.action?fileName="+SysFileUtil.FILE_TYPE_TEMP_FILE;
		
		//非空判断
		if(sHtmlCon!=null && sHtmlCon.trim().length()>0){
			//判断正文中是否有要处理的临时文件
			if(sHtmlCon.indexOf(sFileMark)==-1){
				return;
			}
			//批量替换正文中的文件
			while (sHtmlCon.indexOf(sFileMark, nIndex)>-1) {
				nIndex = (sHtmlCon.indexOf(sFileMark, nIndex) + sFileMark.length() - SysFileUtil.FILE_TYPE_TEMP_FILE.length());
				//查找结束下标
				int endIndex = sHtmlCon.indexOf('"', nIndex);
				if(endIndex==-1){
					endIndex = sHtmlCon.indexOf("'", nIndex);
				}
				if(endIndex==-1){
					endIndex = sHtmlCon.indexOf(' ', nIndex);
				}
				//获得临时文件名称
				String sTFFileName = sHtmlCon.substring(nIndex, endIndex);
				//去掉图片链接参数
				if(sTFFileName.indexOf('&')>-1){
					sTFFileName = sTFFileName.substring(0,sTFFileName.indexOf('&'));
				}
				
				String sFilePath = SysFileUtil.getFilePathByFileName(sTFFileName);
				//保存为正文文件，返回新的文件名称
				String sNewFileName = SysFileUtil.saveFile(false, new File(sFilePath), SysFileUtil.FILE_TYPE_DOC_FILE);
				sHtmlCon = sHtmlCon.replaceAll(sTFFileName, sNewFileName);
			}
		}
		record.setDochtmlcon(sHtmlCon);
	}
	@Override
	public List<IrpDocument> selectByExample(PageUtil page,
			IrpDocumentExample example) {
		List<IrpDocument> list=null;
		if(page==null){
			list = getSqlMapClientTemplate().queryForList("IRP_DOCUMENT.ibatorgenerated_selectByExample", example);
		}else{
			list = getSqlMapClientTemplate().queryForList("IRP_DOCUMENT.ibatorgenerated_selectByExample", example,page.getPageIndex(),page.getPageSize());
		}
		return list;
	}
	
	@Override
	public int updateDocscoreByPrimaryKey(IrpDocument irpDocument) {
		int ncount = getSqlMapClientTemplate().update("IRP_DOCUMENT.updateDocscoreByPrimaryKey", irpDocument);
		if(ncount>0){
			ncount+=getSqlMapClientTemplate().update("IRP_CHNL_DOC_LINK.updateDocscoreByPrimaryKey", irpDocument);
		}
		return ncount;
	}
}