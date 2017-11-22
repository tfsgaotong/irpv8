package com.tfs.irp.systimdask.service;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import com.tfs.irp.chnl_doc_link.service.IrpChnl_Doc_LinkService;
import com.tfs.irp.config.dao.IrpConfigDAO;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.solr.entity.IrpSolr;
import com.tfs.irp.solr.service.SolrService;
import com.tfs.irp.userlove.entity.IrpUserLove;
import com.tfs.irp.userlove.service.IrpUserLoveService;
import com.tfs.irp.util.ApplicationContextHelper;

public class systemDocToDocTime implements Job {
	private IrpChnl_Doc_LinkService irpChnl_Doc_LinkService; //知识中间表service
	private IrpUserLoveService irpUserLoveService;//猜你喜欢service
	private IrpConfigDAO irpConfigDAO;
	private SolrService solrService;//solrservice
	/**
	 * 利用构造方法初始化参数
	 */
	public systemDocToDocTime(){
		ApplicationContext ac = ApplicationContextHelper.getContext();
		irpUserLoveService = (IrpUserLoveService) ac.getBean("irpUserLoveService");
		irpChnl_Doc_LinkService = (IrpChnl_Doc_LinkService) ac.getBean("irpChnl_Doc_LinkService");
		solrService = (SolrService) ac.getBean("solrService");
		irpConfigDAO = (IrpConfigDAO) ac.getBean("irpConfigDAO");
	}
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		List<IrpDocument> allDocumentList=irpChnl_Doc_LinkService.allDatas(null);//所有知识的相关知识
		if(allDocumentList!=null && allDocumentList.size()>0){
			for (IrpDocument irpDocument : allDocumentList) {
				Long docid=irpDocument.getDocid();
				String keyWords=irpDocument.getDockeywords();
				//查询旧数据
				List<IrpUserLove> oldDatas=irpUserLoveService.allData(null, docid, IrpUserLove.XIANG_GUAN_DOCUMENT);
				int nCount=collectionDocumentSave(keyWords, docid);//保存相关知识
				if(nCount>0){ //删除
					irpUserLoveService.deletedoc(oldDatas);
				}
			}
		}
	}
	/**  知识相关知识逻辑  */ 
	public int  collectionDocumentSave(String keyWords,Long _docid){
		int nCount=0;
		try {
			if(keyWords!=null && !"".equals(keyWords.trim())){
				if(keyWords.length()>38){
					keyWords=keyWords.substring(0,37);
				}
				String searchUrl=irpConfigDAO.selectCValueByCKey(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE);
				List<Long> docIds=solrService.searchIdByStr(keyWords,50, searchUrl);
				if(docIds!=null && docIds.size()>0){
					for (Long docid : docIds) {
						//if(nCount>=5)return nCount;
						irpUserLoveService.save(null, _docid, docid, IrpUserLove.XIANG_GUAN_DOCUMENT);//保存
						nCount++;
					}
				}
			}
		} catch (Exception e) {e.printStackTrace();}
		return nCount;
	}
	public IrpChnl_Doc_LinkService getIrpChnl_Doc_LinkService() {
		return irpChnl_Doc_LinkService;
	}
	public IrpUserLoveService getIrpUserLoveService() {
		return irpUserLoveService;
	}
	public IrpConfigDAO getIrpConfigDAO() {
		return irpConfigDAO;
	}
	public SolrService getSolrService() {
		return solrService;
	}
	public void setIrpChnl_Doc_LinkService(
			IrpChnl_Doc_LinkService irpChnl_Doc_LinkService) {
		this.irpChnl_Doc_LinkService = irpChnl_Doc_LinkService;
	}
	public void setIrpUserLoveService(IrpUserLoveService irpUserLoveService) {
		this.irpUserLoveService = irpUserLoveService;
	}
	public void setIrpConfigDAO(IrpConfigDAO irpConfigDAO) {
		this.irpConfigDAO = irpConfigDAO;
	}
	public void setSolrService(SolrService solrService) {
		this.solrService = solrService;
	} 
	
}
