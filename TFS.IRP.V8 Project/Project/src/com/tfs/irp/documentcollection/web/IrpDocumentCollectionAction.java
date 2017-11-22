package com.tfs.irp.documentcollection.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.service.IrpDocumentService;
import com.tfs.irp.documentcollection.service.IrpDocumentCollectionService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;

public class IrpDocumentCollectionAction extends ActionSupport {
private IrpDocumentCollectionService irpDocumentCollectionServiceImpl;
private Long documentid;//关注的文档id
private String crtimesort;

private List<IrpChnlDocLink> chnlDocLinks;
private IrpUserService irpUserService;
private IrpDocumentService irpDocumentService;

public IrpDocumentService getIrpDocumentService() {
	return irpDocumentService;
}
public void setIrpDocumentService(IrpDocumentService irpDocumentService) {
	this.irpDocumentService = irpDocumentService;
}
public IrpUserService getIrpUserService() {
	return irpUserService;
}
public void setIrpUserService(IrpUserService irpUserService) {
	this.irpUserService = irpUserService;
}
private int pageNum=1; 
private int pageSize=15; 
private String pageHTML;
public String getPageHTML() {
	return pageHTML;
}
public List<IrpChnlDocLink> getChnlDocLinks() {
	return chnlDocLinks;
}
public void setChnlDocLinks(List<IrpChnlDocLink> chnlDocLinks) {
	this.chnlDocLinks = chnlDocLinks;
}
public void setPageHTML(String pageHTML) {
	this.pageHTML = pageHTML;
}
public int getPageNum() {
	return pageNum;
}
public void setPageNum(int pageNum) {
	this.pageNum = pageNum;
}
public int getPageSize() {
	return pageSize;
}
public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
}
//得到自己收藏的所有文档
public String myAllDocCollection(){
	HashMap < String,Object> map=new HashMap<String, Object>();
	//按照创建时间排序
		String sOrderByClause=null;
		if(crtimesort!=null ){
			if(crtimesort.equals("asc")){
				  sOrderByClause = "cdate asc";  
			}
			if(crtimesort.equals("desc")){
				  sOrderByClause = "cdate desc";  
			} 
		} 
		map.put("sOrderByClause", sOrderByClause);
		int aDataCount= irpDocumentCollectionServiceImpl.count(map);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);  
		chnlDocLinks=irpDocumentCollectionServiceImpl.myAllDocCollection(map,pageUtil);
		this.pageHTML = pageUtil.getClientPageHtml("allMydocCollection(#PageNum#)"); 
		return SUCCESS;
}

//得到自己收藏的前条文档
public String myDocCollection(){
	HashMap < String,Object> map=new HashMap<String, Object>();
	//按照创建时间排序
		String sOrderByClause=null;
		if(crtimesort!=null ){
			if(crtimesort.equals("asc")){
				  sOrderByClause = "cdate asc";  
			}
			if(crtimesort.equals("desc")){
				  sOrderByClause = "cdate desc";  
			} 
		} 
		map.put("sOrderByClause", sOrderByClause);
		int aDataCount= irpDocumentCollectionServiceImpl.count(map);
		PageUtil pageUtil = new PageUtil(this.pageNum, 3, aDataCount);  
		chnlDocLinks=irpDocumentCollectionServiceImpl.myAllDocCollection(map,pageUtil);
		return SUCCESS;
}

/**
 * 手机端收藏知识
 * mobile
 * @return
 */
private int pagenummobile;
public int getPagenummobile() {
	return pagenummobile;
}
public void setPagenummobile(int pagenummobile) {
	this.pagenummobile = pagenummobile;
}
public void myAllDocCollectionByMobile(){
	
	HashMap < String,Object> map=new HashMap<String, Object>();
	map.put("sOrderByClause", "cdate desc");
	int aDataCount= irpDocumentCollectionServiceImpl.count(map);
	PageUtil pageUtil = new PageUtil(this.pagenummobile, 10, aDataCount);  
	chnlDocLinks = irpDocumentCollectionServiceImpl.myAllDocCollection(map,pageUtil);
	List mobilelist = new ArrayList();
	for (int i = 0; i < chnlDocLinks.size(); i++) {
		IrpChnlDocLink icldl = chnlDocLinks.get(i);
		mobilelist.add("{\"showname\":\""+getShowPageViewNameByUserId(icldl.getCruserid())+"\"");
		mobilelist.add("\"sex\":\""+getIrpUserByUserid(icldl.getCruserid()).getSex()+"\"");
		mobilelist.add("\"userpic\":\""+getIrpUserByUserid(icldl.getCruserid()).getUserpic()+"\"");
		mobilelist.add("\"docid\":\""+icldl.getDocid()+"\"");
		String doctitles = icldl.getDoctitle();
		if (doctitles!=null) {
			mobilelist.add("\"doctitle\":\""+doctitles.replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");	
		}
		mobilelist.add("\"kwywords\":\""+getDocById(icldl.getDocid()).getDockeywords()+"\"");
		String coretexts = getDocById(icldl.getDocid()).getDocabstract();
		if (coretexts!=null) {
			mobilelist.add("\"coretext\":\""+coretexts.replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");
		}
		
		mobilelist.add("\"chnldocid\":\""+icldl.getChnldocid()+"\"}");
	}
	mobilelist.add("{\"mobilenum\":\""+aDataCount+"\"}");
	ActionUtil.writer(mobilelist.toString());
}



/**
 * 更具用户id获得用户的对象
 * 
 * @param _cruserid
 * @return
 */
public IrpUser getIrpUserByUserid(long _cruserid) {
	IrpUser irpUser = null;
	irpUser = this.irpUserService.findUserByUserId(_cruserid);
	return irpUser;

}
/**
 * 根据id去文档对象
 * @param _docid
 * @return
 */
public IrpDocument getDocById(long _docid){
	IrpDocument irpdocument = null;
	if (_docid>0) {
		irpdocument = this.irpDocumentService.findDocumentByDocId(_docid);
	}
	return irpdocument;
}


/**
 * 根据id获得用户在页面显示的名字
 * @param _userid
 * @return
 */
public String getShowPageViewNameByUserId(Long _userid){
	return this.irpUserService.findShowNameByUserid(_userid);
}
//得到收藏的数量
public void myDocCollectionCount(){
	int nCount=irpDocumentCollectionServiceImpl.myDocCollectionCount();
	ActionUtil.writer(String.valueOf(nCount));
}
//收藏关注人的文档
public void addFocusDocLink(){ 
	int nCount=irpDocumentCollectionServiceImpl.insertFocus(this.documentid);
	ActionUtil.writer(String.valueOf(nCount));
}
//取消收藏
public void deleteFocusDocLink(){ 
	int nCount=irpDocumentCollectionServiceImpl.deleteFocus(documentid);
	ActionUtil.writer(String.valueOf(nCount)); 
}

public IrpDocumentCollectionService getIrpDocumentCollectionServiceImpl() {
	return irpDocumentCollectionServiceImpl;
}

public void setIrpDocumentCollectionServiceImpl(
		IrpDocumentCollectionService irpDocumentCollectionServiceImpl) {
	this.irpDocumentCollectionServiceImpl = irpDocumentCollectionServiceImpl;
}


public Long getDocumentid() {
	return documentid;
} 
public void setDocumentid(Long documentid) {
	this.documentid = documentid;
}
public String getCrtimesort() {
	return crtimesort;
}
public void setCrtimesort(String crtimesort) {
	this.crtimesort = crtimesort;
} 
/**
 * 判断传入的id是否被当前用户收藏
 * mobile
 * @reutrn
 */
public void boolFocusdoclinkMobile(){

	if(documentid!=null){
		ActionUtil.writer(this.irpDocumentCollectionServiceImpl.boolCollectByDocId(documentid)+"");
		
	}
}

}
