package com.tfs.irp.subscribe.web;

import java.util.HashMap;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.service.IrpDocumentService;
import com.tfs.irp.subscribe.entity.IrpSubscribe;
import com.tfs.irp.subscribe.service.IrpSubscribeService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;

public class SubscribeAction extends ActionSupport {
	private IrpSubscribeService irpSubscribeService;
	private IrpDocumentService irpDocumentService;// 文档service
	
	public IrpDocumentService getIrpDocumentService() {
		return irpDocumentService;
	}

	public void setIrpDocumentService(IrpDocumentService irpDocumentService) {
		this.irpDocumentService = irpDocumentService;
	}

	public IrpSubscribeService getIrpSubscribeService() {
		return irpSubscribeService;
	}

	public void setIrpSubscribeService(IrpSubscribeService irpSubscribeService) {
		this.irpSubscribeService = irpSubscribeService;
	}
	
	private long objId;
	private String crtime;
	private int pageNum;
	private String pageHTML;
	private List<IrpSubscribe> subscribes;
	private List<IrpChnlDocLink> chnlDocLinks;
	private int pageSize=10;
	
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getPageHTML() {
		return pageHTML;
	}

	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}

	public List<IrpSubscribe> getSubscribes() {
		return subscribes;
	}

	public void setSubscribes(List<IrpSubscribe> subscribes) {
		this.subscribes = subscribes;
	}

	public List<IrpChnlDocLink> getChnlDocLinks() {
		return chnlDocLinks;
	}

	public void setChnlDocLinks(List<IrpChnlDocLink> chnlDocLinks) {
		this.chnlDocLinks = chnlDocLinks;
	}

	public long getObjId() {
		return objId;
	}

	public void setObjId(long objId) {
		this.objId = objId;
	}

	public String getCrtime() {
		return crtime;
	}

	public void setCrtime(String crtime) {
		this.crtime = crtime;
	}

	public void addSubscribeDoc() {
		IrpDocument document = new IrpDocument();
		document.setDocid(objId);
		long nResult = irpSubscribeService.addSubscribe(document);
		ActionUtil.writer(nResult>0?"1":"0");
	}
	
	public void delSubscribeDoc() {
		IrpDocument document = new IrpDocument();
		document.setDocid(objId);
		long nResult = irpSubscribeService.deleteSubscribeByBaseObjAndUserId(document, LoginUtil.getLoginUserId());
		ActionUtil.writer(nResult>0?"1":"0");
	}
	public String selectAllSubscribeDoc() {
		HashMap < String,Object> map=new HashMap<String, Object>();
		//按照创建时间排序
			String sOrderByClause=null;
			if(crtime!=null ){
				if(crtime.equals("asc")){
					  sOrderByClause = "crtime asc";  
				}
				if(crtime.equals("desc")){
					  sOrderByClause = "crtime desc";  
				} 
			}else{
				 sOrderByClause = "subReadStatus asc";
			}
			
			map.put("sOrderByClause", sOrderByClause);
			int aDataCount= irpSubscribeService.count(map);
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);  
			//chnlDocLinks =irpSubscribeService.myAllDocSubscribe(map,pageUtil);
			subscribes =irpSubscribeService.selectSubscribeByPage(map,pageUtil);
			this.pageHTML = pageUtil.getClientPageHtml("allMydocSubscribe(#PageNum#)"); 
			return SUCCESS;
}
	
	public String selectSubscribeDoc() {
		HashMap < String,Object> map=new HashMap<String, Object>();
		//按照创建时间排序
			String sOrderByClause=null;
			if(crtime!=null ){
				if(crtime.equals("asc")){
					  sOrderByClause = "crtime asc";  
				}
				if(crtime.equals("desc")){
					  sOrderByClause = "crtime desc";  
				} 
			}else{
				 sOrderByClause = "subReadStatus asc";
			} 
			
			map.put("sOrderByClause", sOrderByClause);
			int aDataCount= irpSubscribeService.count(map);
			PageUtil pageUtil = new PageUtil(this.pageNum, 3, aDataCount);  
			subscribes =irpSubscribeService.selectSubscribeByPage(map,pageUtil);
			if(subscribes==null||subscribes.size()<=0){
				chnlDocLinks =irpSubscribeService.myAllDocSubscribe(map,pageUtil);
				return SUCCESS;
			}
			return "subscribes";
		}
	
	
	/**
	 * 根据ID获得知识
	 * @param _docid
	 * @return
	 */
	public IrpDocument getIrpDocumentById(Long _docid){
		IrpDocument irpDocument = null;
		if (_docid!=null) {
			irpDocument = this.irpDocumentService.irpDocument(_docid);
		}
		return irpDocument;
	}
}
