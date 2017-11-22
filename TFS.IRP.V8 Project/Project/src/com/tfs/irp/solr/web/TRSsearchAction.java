package com.tfs.irp.solr.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.selectkey.service.IrpSelectKeyService;
import com.tfs.irp.solr.service.TRSSearchService;
import com.tfs.irp.tag.service.IrpTagService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;

public class TRSsearchAction extends ActionSupport {
	/**
	 * 
	 */
	private String pageHTML = "";

	private int pageNum = 0;

	private int pageSize = 3;

	private String searchInfo;
	private String searchtype;
	// TRS检索
	private TRSSearchService trsSearchService;

	private List<IrpDocumentWithBLOBs> bloBs;

	private List<IrpMicroblog> irpMicroblogs;

	private IrpSelectKeyService irpSelectKeyService;

	private IrpTagService irpTagService;

	private IrpUserService irpUserService;

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public IrpSelectKeyService getIrpSelectKeyService() {
		return irpSelectKeyService;
	}

	public void setIrpSelectKeyService(IrpSelectKeyService irpSelectKeyService) {
		this.irpSelectKeyService = irpSelectKeyService;
	}

	public IrpTagService getIrpTagService() {
		return irpTagService;
	}

	public void setIrpTagService(IrpTagService irpTagService) {
		this.irpTagService = irpTagService;
	}

	public String getPageHTML() {
		return pageHTML;
	}

	public String getSearchtype() {
		return searchtype;
	}

	public void setSearchtype(String searchtype) {
		this.searchtype = searchtype;
	}

	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}

	public List<IrpDocumentWithBLOBs> getBloBs() {
		return bloBs;
	}

	public void setBloBs(List<IrpDocumentWithBLOBs> bloBs) {
		this.bloBs = bloBs;
	}

	public List<IrpMicroblog> getIrpMicroblogs() {
		return irpMicroblogs;
	}

	public void setIrpMicroblogs(List<IrpMicroblog> irpMicroblogs) {
		this.irpMicroblogs = irpMicroblogs;
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

	public String getSearchInfo() {
		return searchInfo;
	}

	public void setSearchInfo(String searchInfo) {
		this.searchInfo = searchInfo;
	}

	private String strDocWhere = "DOCCONTENT";
	private String strMicWhere = "MICOROBLOGCONTENT";

	public TRSSearchService getTrsSearchService() {
		return trsSearchService;
	}

	public void setTrsSearchService(TRSSearchService trsSearchService) {
		this.trsSearchService = trsSearchService;
	}

	// 检索文档
	public String TRSseach() {
		try {
			this.setSearchInfo(new String(this.searchInfo
					.getBytes("ISO-8859-1"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		docTRSsearch();
		return SUCCESS;
	}

	private void docTRSsearch() {
		irpSelectKeyService.save(searchInfo);
		irpTagService.addTag(searchInfo);
		PageUtil pageutil = new PageUtil();
		pageutil.setPageNum(this.getPageNum());
		pageutil.setPageSize(this.getPageSize());
		String keyword = searchInfo;
		String orderBy = "-crtime";
		bloBs = trsSearchService.selectDocbypage(pageutil, "IRP_DOC", keyword,
				strDocWhere, orderBy);
		this.pageHTML = pageutil.getClientPageHtml("page(#PageNum#)");
	}

	// TRS检索
	public String TRSseachDocument() {
		docTRSsearch();
		return SUCCESS;
	}

	// TRS检索微知 TRSseachMicroblog
	public String TRSseachMicroblog() {
		irpSelectKeyService.save(searchInfo);
		irpTagService.addTag(searchInfo);
		PageUtil pageutil = new PageUtil();
		pageutil.setPageNum(this.getPageNum());
		pageutil.setPageSize(this.getPageSize());
		String keyword = searchInfo;
		String orderBy = "-crtime";
		irpMicroblogs = trsSearchService.selectMicroblogbypage(pageutil,
				"IRP_MIC", keyword, strMicWhere, orderBy);
		this.pageHTML = pageutil.getClientPageHtml("page(#PageNum#)");
		return SUCCESS;
	}

	/**
	 * 根据用户名寻找用户对象
	 * 
	 * @return
	 */
	public IrpUser getIrpUserByUsername(Long _userid) {
		IrpUser irpUser = null;
		irpUser = this.irpUserService.findUserByUserId(_userid);
		return irpUser;
	}

	/**
	 * 查询检索的开关
	 * 
	 * @return
	 */
	public String selectConfigSearch() {
		String sear = SysConfigUtil.getSysConfigValue("searchOptions");
		this.setMessage(sear);
		return SUCCESS;
	}

}
