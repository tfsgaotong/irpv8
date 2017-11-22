package com.tfs.irp.personalsearch.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.channel.service.IrpChannelService;
import com.tfs.irp.personalsearch.entity.IrpPersonalSearch;
import com.tfs.irp.personalsearch.service.IrpPersonalSearchService;
import com.tfs.irp.solr.entity.IrpSolr;
import com.tfs.irp.solr.service.SolrService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
 
public class PersonalSearchAction extends ActionSupport {
	private IrpPersonalSearchService irpPersonalSearchService;
	
	private SolrService solrService;
	
	private IrpChannelService irpChannelService;
	
	private IrpUserService irpUserService;
	
	private IrpPersonalSearch personalSearch;
	
	private long personalSearchId;
	
	private List documentlist;
	
	private String pageHTML;
	
	private int pageNum=1; 

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
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

	public IrpChannelService getIrpChannelService() {
		return irpChannelService;
	}

	public void setIrpChannelService(IrpChannelService irpChannelService) {
		this.irpChannelService = irpChannelService;
	}

	public SolrService getSolrService() {
		return solrService;
	}

	public void setSolrService(SolrService solrService) {
		this.solrService = solrService;
	}

	public List getDocumentlist() {
		return documentlist;
	}

	public void setDocumentlist(List documentlist) {
		this.documentlist = documentlist;
	}

	public long getPersonalSearchId() {
		return personalSearchId;
	}

	public void setPersonalSearchId(long personalSearchId) {
		this.personalSearchId = personalSearchId;
	}

	public IrpPersonalSearch getPersonalSearch() {
		return personalSearch;
	}

	public void setPersonalSearch(IrpPersonalSearch personalSearch) {
		this.personalSearch = personalSearch;
	}

	public IrpPersonalSearchService getIrpPersonalSearchService() {
		return irpPersonalSearchService;
	}

	public void setIrpPersonalSearchService(
			IrpPersonalSearchService irpPersonalSearchService) {
		this.irpPersonalSearchService = irpPersonalSearchService;
	}
	
	/**
	 * 添加个性化检索页面
	 * @return
	 */
	public String personalSearchAdd() {
		return SUCCESS;
	}
	
	public String searchSubjectEdit() {
		if(personalSearchId>0L){
			personalSearch = irpPersonalSearchService.findPersonalSearchById(personalSearchId);
		}
		return SUCCESS;
	}
	
	/**
	 * 处理添加个性化检索
	 */
	public void personalSearchAddDowith() {
		long nResult = irpPersonalSearchService.addEditPersonalSearch(personalSearch);
		ActionUtil.writer(nResult>0?"1":"0");
	}
	
	/**
	 * 删除个性化检索
	 */
	public void deletePersonalSearch() {
		Integer result = irpPersonalSearchService.deletePersonalSearchById(personalSearchId);
		ActionUtil.writer(result.toString());
	}
	
	public String searchSubjectDetail() {
		this.personalSearch = irpPersonalSearchService.findPersonalSearchById(personalSearchId);
		if(this.personalSearch==null)
			return ERROR;
		//权限
		IrpUser user = LoginUtil.getLoginUser();
		boolean result = false;
		StringBuffer stringBuffer = null;
		if(!user.isAdministrator()){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("status", 1);
			List<IrpChannel> channelList = irpChannelService.JsonRightAllChannelMore(map);
			if(channelList!=null && channelList.size()>0){
				stringBuffer = new StringBuffer();
				for(IrpChannel irpChannel : channelList){
					if(irpChannel==null)
						continue;
					stringBuffer.append(" CHANNELID:"+irpChannel.getChannelid());
				}
				result = true;
			}
		}else{
			result = true;
		}
		if(result){
			try {
				long docnum = this.solrService.searchSubjectCount(this.personalSearch, SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE),stringBuffer==null?null:stringBuffer.toString());
				if(docnum>0){
					PageUtil pageUtildocument = new PageUtil(this.pageNum, this.personalSearch.getSearchdpagesize(), new Long(docnum).intValue());
					documentlist= this.solrService.searchSubject(this.personalSearch,SysConfigUtil.getSysConfigValue(IrpSolr.COLLECTION_DOCUMENT_ENTERPRISE),pageUtildocument,stringBuffer==null?null:stringBuffer.toString());
					this.pageHTML = pageUtildocument.getClientPageHtml("page(#PageNum#)");
				}
			} catch (SolrServerException e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	public IrpUser findUserByUserId(long _nUserId) {
		return irpUserService.findUserByUserId(_nUserId);
	}
	
	public IrpUser getIrpUserByUsername(String _userid){
		if(_userid==null || _userid.length()<3)
			return null;
		String sUserId = _userid.substring(1,_userid.length()-1);
		if(sUserId.length()==0)
			return null;
		long nUserId = 0l;
		try {
			nUserId = Long.parseLong(sUserId);
		} catch (NumberFormatException e) {
			return null;
		}
		IrpUser irpUser = null;
		if (nUserId>0l) {
			irpUser = this.irpUserService.findUserByUserId(nUserId);
		}
		return irpUser;
	}
}
