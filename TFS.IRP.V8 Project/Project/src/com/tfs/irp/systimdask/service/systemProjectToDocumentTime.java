package com.tfs.irp.systimdask.service;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import com.tfs.irp.config.dao.IrpConfigDAO;
import com.tfs.irp.documentmap.service.IrpDocumentMapService;
import com.tfs.irp.project.entity.IrpProject;
import com.tfs.irp.project.service.IrpProjectService;
import com.tfs.irp.solr.entity.IrpSolr;
import com.tfs.irp.solr.service.SolrService;
import com.tfs.irp.userlove.entity.IrpUserLove;
import com.tfs.irp.userlove.service.IrpUserLoveService;
import com.tfs.irp.util.ApplicationContextHelper;

public class systemProjectToDocumentTime implements Job {
	private IrpUserLoveService irpUserLoveService;//猜你喜欢service
	private IrpProjectService irpProjectService;//项目service
	private SolrService solrService;//solrservice
	private IrpConfigDAO irpConfigDAO;
	private IrpDocumentMapService irpDocumentMapService; //知识中间表service
	
	public IrpUserLoveService getIrpUserLoveService() {
		return irpUserLoveService;
	}
	public IrpProjectService getIrpProjectService() {
		return irpProjectService;
	}
	public SolrService getSolrService() {
		return solrService;
	}
	public IrpConfigDAO getIrpConfigDAO() {
		return irpConfigDAO;
	}
	public void setIrpUserLoveService(IrpUserLoveService irpUserLoveService) {
		this.irpUserLoveService = irpUserLoveService;
	}
	public IrpDocumentMapService getIrpDocumentMapService() {
		return irpDocumentMapService;
	}
	public void setIrpDocumentMapService(IrpDocumentMapService irpDocumentMapService) {
		this.irpDocumentMapService = irpDocumentMapService;
	}
	public void setIrpProjectService(IrpProjectService irpProjectService) {
		this.irpProjectService = irpProjectService;
	}
	public void setSolrService(SolrService solrService) {
		this.solrService = solrService;
	}
	public void setIrpConfigDAO(IrpConfigDAO irpConfigDAO) {
		this.irpConfigDAO = irpConfigDAO;
	}
	public systemProjectToDocumentTime(){
		ApplicationContext ac = ApplicationContextHelper.getContext();
		irpUserLoveService = (IrpUserLoveService) ac.getBean("irpUserLoveService");
		irpProjectService = (IrpProjectService) ac.getBean("irpProjectService");
		irpDocumentMapService= (IrpDocumentMapService)ac.getBean("irpDocumentMapService");
		solrService = (SolrService) ac.getBean("solrService");
		irpConfigDAO = (IrpConfigDAO) ac.getBean("irpConfigDAO");
	}
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		List<IrpProject> projects=irpProjectService.findProject();
		if(projects!=null && projects.size()>0){
			for (IrpProject irpProject : projects) {
				String prokey=irpProject.getProkey();
				List<IrpUserLove> oldDatas=irpUserLoveService.allData(null, irpProject.getProjectid(), IrpUserLove.PROJECT_XIANG_GUAN_DOCUMENT);//查询旧数据
				if(prokey!=null && !"".equals(prokey.trim())){//如果标签有值的情况
					int nCount=ProjectcollectionDocumentSave(irpProject.getProjectid(),prokey,irpProject.getProstatus());//调用方法保存到表中
					if(nCount>0){
						irpUserLoveService.deletedoc(oldDatas);//删除旧数据
					}
				}
			}
		}
	}
	/**  项目的相关知识的逻辑 */
	public int  ProjectcollectionDocumentSave(Long _projectId,String prokey,Long _proStatus){
		int count=0;
		try {
			if(prokey.length()>38){
				prokey=prokey.substring(0,37);
			}
			String searchUrl=irpConfigDAO.selectCValueByCKey(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE);
			List<Long> docIdsList=solrService.searchIdByStr(prokey, 50,searchUrl);
			if(docIdsList!=null && docIdsList.size()>0){
				//将在这些list结合项目所在阶段去查询返回知识保存5条
				if(_proStatus!=null && _proStatus>0){
					docIdsList=irpDocumentMapService.docidsByChannelid(_proStatus,docIdsList);
				} 
				if(docIdsList==null ||  docIdsList.size()==0)return 0;
				for (int i = 0; i < docIdsList.size(); i++) {
					//if(count>=5)return count;
					Long docid=docIdsList.get(i);
					if(docid!=null &&docid.longValue()!=0L){
						irpUserLoveService.save(null, _projectId, docid, IrpUserLove.PROJECT_XIANG_GUAN_DOCUMENT);//保存
					}
					count++;
				}
			} 
		} catch (Exception e) {e.printStackTrace();}
		return count;
	}
		
}
