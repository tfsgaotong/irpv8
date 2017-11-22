package com.tfs.irp.tag.web;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.service.IrpDocumentService;
import com.tfs.irp.tag.entity.IrpTag;
import com.tfs.irp.tag.entity.IrpTagType;
import com.tfs.irp.tag.entity.IrpUserTag;
import com.tfs.irp.tag.service.IrpTagService;
import com.tfs.irp.tag.service.IrpTagTypeService;
import com.tfs.irp.tag.service.IrpUserTagService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.StringUtil;

public class TagAction extends ActionSupport {
	private IrpTagService irpTagService;
	
	private IrpUserTagService irpUserTagService;
	
	private IrpTagTypeService irpTagTypeService;
	
	private List<IrpTag> irpTags;
	
	private String tagName;
	
	private String typeName;
	
	private long tagId;
	
	private long typeId;

	private List<IrpTag> tagsearchlist;
	
	private List<IrpTagType> irpTagTypes;
	
	private IrpTagType irpTagType;
	
	private int pageNum=1;
	
	private int pageSize=10;
	
	private String orderField="";
	
	private String orderBy="";
	
	private String pageHTML;
	
	private String tagIds;
	
	public String getTagIds() {
		return tagIds;
	}

	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getPageHTML() {
		return pageHTML;
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

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public List<IrpTag> getTagsearchlist() {
		return tagsearchlist;
	}

	public void setTagsearchlist(List<IrpTag> tagsearchlist) {
		this.tagsearchlist = tagsearchlist;
	}
	
	public List<IrpTagType> getIrpTagTypes() {
		return irpTagTypes;
	}

	public void setIrpTagTypes(List<IrpTagType> irpTagTypes) {
		this.irpTagTypes = irpTagTypes;
	}

	public IrpTagType getIrpTagType() {
		return irpTagType;
	}

	public void setIrpTagType(IrpTagType irpTagType) {
		this.irpTagType = irpTagType;
	}

	public List<IrpTag> getIrpTags() {
		return irpTags;
	}

	public void setIrpTags(List<IrpTag> irpTags) {
		this.irpTags = irpTags;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public long getTagId() {
		return tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	public IrpTagTypeService getIrpTagTypeService() {
		return irpTagTypeService;
	}

	public void setIrpTagTypeService(IrpTagTypeService irpTagTypeService) {
		this.irpTagTypeService = irpTagTypeService;
	}

	public IrpUserTagService getIrpUserTagService() {
		return irpUserTagService;
	}

	public void setIrpUserTagService(IrpUserTagService irpUserTagService) {
		this.irpUserTagService = irpUserTagService;
	}

	public IrpTagService getIrpTagService() {
		return irpTagService;
	}

	public void setIrpTagService(IrpTagService irpTagService) {
		this.irpTagService = irpTagService;
	} 
	/**
	 * 用户标签列表
	 * @return
	 */
	public String userTag() {
		irpTags = irpTagService.findTagsByUserId(LoginUtil.getLoginUserId());
		return SUCCESS;
	}
	
	/**
	 * 添加用户个人标签
	 */
	public void addUserTag() {
		String[] arrTags = StringUtil.cutWord(tagName).split(",");
		for(int i=0;i<arrTags.length;i++){
			String sTag = arrTags[i].trim();
			if(sTag==null || sTag.length()==0){
				continue;
			}
			boolean isAdd = false;
			IrpTag irpTag = irpTagService.findTagByTagName(sTag);
			long nTagId = 0L;
			if(irpTag==null){
				irpTag = new IrpTag();
				irpTag.setTagname(sTag);
				nTagId = irpTagService.addTag(irpTag);
				isAdd = true;
			}else{
				nTagId = irpTag.getTagid();
			}
			//判断填写的个人标签是否已存在与用户
			if(isAdd || irpUserTagService.findUserTagCountByUserIdAndTagId(LoginUtil.getLoginUserId(), nTagId)==0){
				//添加标签与当前登录用户的关系
				IrpUserTag irpUserTag = new IrpUserTag();
				irpUserTag.setTagid(nTagId);
				irpUserTag.setUserid(LoginUtil.getLoginUserId());
				irpUserTagService.userTagEdit(irpUserTag);
			}
		}
		ActionUtil.writer("1");
	}
	
	/**
	 * 删除用户个人标签关系
	 */
	public void deleteUserTag() {
		int nCount = irpUserTagService.deleteUserTagByUserIdAndTagId(LoginUtil.getLoginUserId(), tagId);
		ActionUtil.writer(nCount>0?"1":"0");
	}
	
	
	/**
	 * 获得热门检索词
	 * @return
	 */
	public String findHotIndexWord(){
		
		tagsearchlist = this.irpTagService.findHotIndexWord(10);
		
		return SUCCESS;
	}
	/**
	 * 获得热门检索词 手机版
	 * mobile
	 * @return
	 */
	public void findHotIndexWordMobile(){
		
		 List<IrpTag> list = this.irpTagService.findHotIndexWord(10);
		 if (list.size()>0 && list!=null) {
			 List mobilelist = new ArrayList();
			 for (int i = 0; i < list.size(); i++) {
				 	IrpTag irptag = list.get(i);
				 	mobilelist.add("{\"tagname\":\""+irptag.getTagname()+"\"");
				 	mobilelist.add("\"tagnamenum\":\""+irptag.getNcount()+"\"}");
			 }	
			 ActionUtil.writer(mobilelist.toString());
		 }

		 
	}
	
	
	
	
	
	
	private IrpDocumentService irpDocumentService;
	private List<String> mytags;
	public List<String> getMytags() {
		return mytags;
	}

	public void setMytags(List<String> mytags) {
		this.mytags = mytags;
	}

	public IrpDocumentService getIrpDocumentService() {
		return irpDocumentService;
	}

	public void setIrpDocumentService(IrpDocumentService irpDocumentService) {
		this.irpDocumentService = irpDocumentService;
	}

	/**
	 * 推荐用户标签列表
	 * @return
	 */
	public String userTJtag() {
		mytags=new ArrayList<String>();
		irpTags = irpTagService.findTagsByUserId(LoginUtil.getLoginUserId());
		List<Long> stats=new ArrayList<Long>();
		stats.add(10L);
		List<IrpDocument> listdoc=irpDocumentService.findAllDocByuser(stats);
		if(irpTags.size()>0){
			for(IrpTag tag:irpTags){
				if(mytags.contains(tag.getName())){
					
				}else{
					mytags.add(tag.getName());
				}
			}
		}
		if(listdoc.size()>0){
			for(IrpDocument doc : listdoc){
				if(doc==null || doc.getDockeywords()==null){
					continue;
				}
				String [] strarr=doc.getDockeywords().split(",");
				for(int i=0;i<strarr.length;i++){
					if(mytags.contains(strarr[i])){
						
					}else{
						mytags.add(strarr[i]);
					}
					
				}
			}
		}
		
		return SUCCESS;
	}
	public String myKeywordstag() {
		mytags=new ArrayList<String>();
		List<Long> stats=new ArrayList<Long>();
		stats.add(10L);
		List<IrpDocument> listdoc=irpDocumentService.findAllDocByuser(stats);
		if(listdoc.size()>0){
			for(IrpDocument doc:listdoc){
				if(doc==null || doc.getDockeywords()==null){
					continue;
				}
				String [] strarr=doc.getDockeywords().split(",");
				for(int i=0;i<strarr.length;i++){
					if(mytags.contains(strarr[i])){
						
					}else{
						mytags.add(strarr[i]);
					}
				}
			}
		}
		return SUCCESS;
	}
	
	
	public String userKeywordstag() {
		mytags=new ArrayList<String>();
		List<Long> stats=new ArrayList<Long>();
		stats.add(10L);
		List<IrpDocument> listdoc=irpDocumentService.findAllDocByuser(stats);
		irpTags = irpTagService.findTagsByUserId(LoginUtil.getLoginUserId());
		List<String> myhastag=new ArrayList<String>();
		if(irpTags.size()>0){
			for(IrpTag tag:irpTags){
				myhastag.add(tag.getName());
			}
		}
		if(listdoc.size()>0){
			for(IrpDocument doc:listdoc){
				if(doc==null || doc.getDockeywords()==null){
					continue;
				}
				String [] strarr=doc.getDockeywords().split(",");
				for(int i=0;i<strarr.length;i++){
					if(myhastag.contains(strarr[i])){
						
					}else{
						myhastag.add(strarr[i]);
						mytags.add(strarr[i]);
					}
				}
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 知识标签管理
	 * @return
	 */
	public String docTagManage() {
		return SUCCESS;
	}
	
	public String docTagList() {
		int nDataCount = irpTagTypeService.findIrpTagTypeCount();
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
		//处理排序
		String sOrderByClause = "crtime desc";
		this.irpTagTypes = irpTagTypeService.findIrpTagTypes(pageUtil, sOrderByClause);
		this.pageHTML = pageUtil.getPageHtml("findTagConn(#PageNum#)");
		return SUCCESS;
	}
	
	public void checkTagTypeName() {
		boolean bCount = irpTagTypeService.checkTypeTypeName(typeName, typeId);
		ActionUtil.writer(bCount?"0":"1");
	}
	
	public void tagTypeEditDowith() {
		long nTagId = irpTagTypeService.editTagType(irpTagType);
		ActionUtil.writer(nTagId>0?"1":"0");
	}
	
	public String tagTypeEdit() {
		irpTagType = irpTagTypeService.findIrpTagTypeByTypeId(typeId);
		if(irpTagType==null)
			return ERROR;
		return SUCCESS;
	}
	
	public void deleteTagTypeDowith() {
		int nCount = irpTagTypeService.deleteIrpTagTypeByTypeId(typeId);
		if(nCount>0){
			irpTagService.clearTagTypeByTypeId(typeId);
		}
		ActionUtil.writer(nCount>0?"1":"0");
	}
	
	public String tagList() {
		irpTags = irpTagService.findIrpTagsByTagType(typeId);
		return SUCCESS;
	}
	
	public void removeTagDowith() {
		IrpTag tag = new IrpTag();
		tag.setTagid(tagId);
		tag.setTagtypeid(0L);
		int nCount = irpTagService.updateTagSelectiveByTagId(tag);
		ActionUtil.writer(nCount>0?"1":"0");
	}
	
	public String selectTag() {
		int nDataCount = irpTagService.countNotSelectIrpTagsLikeTagName(tagName);
		PageUtil pageUtil = new PageUtil(pageNum, pageSize, nDataCount);
		String sOrderbyStr = "";
		if (this.orderField == null || this.orderField.equals("")) {
			sOrderbyStr = "tagid asc";
		} else {
			sOrderbyStr = this.orderField + " " + this.orderBy;
		}
		irpTags = irpTagService.findNotSelectIrpTagsLikeTagName(tagName, pageUtil, sOrderbyStr);
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	
	public void selectTagDowith() {
		int nDataCount = irpTagService.importTagsByTypeId(typeId, tagIds);
		ActionUtil.writer(nDataCount>0?"1":"0");
	}
}
