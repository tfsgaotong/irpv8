
package com.tfs.irp.template.web;

import java.util.Calendar;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.category.entity.IrpCategory;
import com.tfs.irp.category.service.IrpCategoryService;
import com.tfs.irp.template.entity.IrpTemplate;
import com.tfs.irp.template.service.IrpTemplateService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.JsonUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpTemplateAction extends ActionSupport{

	private IrpTemplateService irpTemplateService;
	
	private String orderField;
	
	private String orderBy;
	
	private String searchWord;
	
	private String searchType;
	
	private int pageNum = 1;
	
	private int pageSize = 10;
	
	private String pageHTML = "";
	
	private List<IrpTemplate> templatelist;
	
	private List<IrpTemplate> templatecatelist;
	
	public List<IrpTemplate> getTemplatecatelist() {
		return templatecatelist;
	}

	public void setTemplatecatelist(List<IrpTemplate> templatecatelist) {
		this.templatecatelist = templatecatelist;
	}

	private IrpTemplate irptemplate;
	
	private IrpCategoryService irpCategoryService;

	public IrpCategoryService getIrpCategoryService() {
		return irpCategoryService;
	}

	public void setIrpCategoryService(IrpCategoryService irpCategoryService) {
		this.irpCategoryService = irpCategoryService;
	}

	public IrpTemplate getIrptemplate() {
		return irptemplate;
	}

	public void setIrptemplate(IrpTemplate irptemplate) {
		this.irptemplate = irptemplate;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public IrpTemplateService getIrpTemplateService() {
		return irpTemplateService;
	}

	public void setIrpTemplateService(IrpTemplateService irpTemplateService) {
		this.irpTemplateService = irpTemplateService;
	}
	
	/**
	 * 链接到模版页面
	 * @return
	 */
	public String findECPages(){
		
		String _oOrderby = "";
		
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "tid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		if (cateid!=null) {
			int num = this.irpTemplateService.findTemplateListNum(IrpTemplate.TSTATUSISCITIAO, IrpTemplate.TISDELNORMAL,cateid, searchType, searchWord);
			
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize,num);
			
			templatelist = this.irpTemplateService.findTemplateList(pageUtil, IrpTemplate.TSTATUSISCITIAO,  IrpTemplate.TISDELNORMAL, _oOrderby, cateid,searchType, searchWord);

			
			this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
			
		}

		
		return SUCCESS;
	}
	
	
	public String findECPagesKnow(){
		
	String _oOrderby = "";
		
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "tid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		if (cateid!=null) {
			int num = this.irpTemplateService.findTemplateListNum(IrpTemplate.TSKNOWSISCIAO, IrpTemplate.TISDELNORMAL,cateid, searchType, searchWord);
			
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize,num);
			
			templatelist = this.irpTemplateService.findTemplateList(pageUtil, IrpTemplate.TSKNOWSISCIAO,  IrpTemplate.TISDELNORMAL, _oOrderby, cateid,searchType, searchWord);

			
			this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
			
		}
		
		return SUCCESS;
	}
	/**
	 * 增加分类模版
	 * @return
	 */
	public void addTemplate(){
		IrpTemplate itp = new IrpTemplate();
		itp.setTid(TableIdUtil.getNextId(IrpTemplate.TABLENAME));
		itp.setTvalue(irptemplate.getTvalue());
		itp.setTcate(cateid);
		itp.setTstatus(IrpTemplate.TSTATUSISCITIAO);
		itp.setTisdel(IrpTemplate.TISDELNORMAL);
		itp.setCrtime(Calendar.getInstance().getTime());
		itp.setCruserid(LoginUtil.getLoginUserId());
		itp.setTvaluedesc(irptemplate.getTvaluedesc());
		int msg = this.irpTemplateService.addTemplate(itp);
		ActionUtil.writer(msg+"");
	}
	
	
	public void addTemplateKnow(){
		IrpTemplate itp = new IrpTemplate();
		itp.setTid(TableIdUtil.getNextId(IrpTemplate.TABLENAME));
		itp.setTvalue(irptemplate.getTvalue());
		itp.setTcate(cateid);
		itp.setTstatus(IrpTemplate.TSKNOWSISCIAO);
		itp.setTisdel(IrpTemplate.TISDELNORMAL);
		itp.setCrtime(Calendar.getInstance().getTime());
		itp.setCruserid(LoginUtil.getLoginUserId());
		itp.setTvaluedesc(irptemplate.getTvaluedesc());
		int msg = this.irpTemplateService.addTemplate(itp);
		ActionUtil.writer(msg+"");
	}
	/**
	 * 链接到修改模版jsp
	 * @return
	 */
	public String updateTemplate(){
		
		if (tid!=null) {
			irptemplate = this.irpTemplateService.irpTemplateById(tid);
		}
		
		return SUCCESS;
	}
	/**
	 * 修改模版
	 * @return 
	 */
	public void updateTemplateById(){
		int msg = 0;
		
		IrpTemplate itp = new IrpTemplate();
		itp.setTid(tid);
		itp.setTvalue(irptemplate.getTvalue());
		itp.setTvaluedesc(irptemplate.getTvaluedesc());
		
		msg = this.irpTemplateService.updateTemplate(itp);
		
		ActionUtil.writer(msg+"");
	}
	/**
	 * 删除模版
	 * (修改模版状态  假删除)
	 * @return 
	 */
	public void deletetemplateByIds(){
		int msg = 0;
		if (tids!=null && tids.length()>0) {
			String dtarray[] = tids.split(",");
			if (dtarray.length>0) {
				for (int i = 0; i < dtarray.length; i++) {
					IrpTemplate itp = new IrpTemplate();
					itp.setTid(Long.parseLong(dtarray[i]));
					itp.setTisdel(IrpTemplate.TISDELDELETE);
					msg = this.irpTemplateService.updateTemplate(itp);
				}
			}
		}
		ActionUtil.writer(msg+"");
	}
	/**
	 * 链接到引入模版
	 * @return
	 */
	public String quoteDiTemplate(){
		
		
		return SUCCESS;
	}
	/**
	 * 链接到引入模版知识
	 * @return
	 */
	public String quoteDiTemplateKnow(){
		
		
		return SUCCESS;
	}
	public String linkTemplateCate(){
		
		return 	SUCCESS;
	}
	
	private String tids;
	
	public String getTids() {
		return tids;
	}

	public void setTids(String tids) {
		this.tids = tids;
	}

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	private Long tid;

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
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

	public String getPageHTML() {
		return pageHTML;
	}

	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}

	public List<IrpTemplate> getTemplatelist() {
		return templatelist;
	}

	public void setTemplatelist(List<IrpTemplate> templatelist) {
		this.templatelist = templatelist;
	}
	/**
	 * 根据分类id获取分类对象
	 * @param _cid
	 * @return
	 */
	public String findCateById(Long _cid){
		 IrpCategory irpcategory = this.irpCategoryService.findCategoryByPrimaryKey(_cid);
		 if(irpcategory!=null){
			 
			 return irpcategory.getCname();
		 }else{
			 return "";
		 }
	}
	/**
	 * 根据分类获取模版集合
	 */
	public String findTemByCate(){
		if (cateid!=null) {
			 templatequotenums =  this.irpTemplateService.findTemListByCIdNum(cateid, IrpTemplate.TSTATUSISCITIAO, IrpTemplate.TISDELNORMAL);

			if (templatequotenums>0) {
				
				
				PageUtil pageutil = new PageUtil(pagetemnums, 1,templatequotenums);
				templatecatelist = this.irpTemplateService.findTemListByCId(cateid, IrpTemplate.TSTATUSISCITIAO, IrpTemplate.TISDELNORMAL, pageutil);
				pagetemhtml = pageutil.getClientPageHtml("pageQTemplate("+cateid+",#PageNum#)");
			}
		}
		return SUCCESS;
	}
	/**
	 * 根据分类获取模版集合 （知识模版）
	 */
	public String findTemByCateKnow(){
		if (cateid!=null) {
			 templatequotenums =  this.irpTemplateService.findTemListByCIdNum(cateid, IrpTemplate.TSKNOWSISCIAO, IrpTemplate.TISDELNORMAL);

			if (templatequotenums>0) {
				
				
				PageUtil pageutil = new PageUtil(pagetemnums, 1,templatequotenums);
				templatecatelist = this.irpTemplateService.findTemListByCId(cateid, IrpTemplate.TSKNOWSISCIAO, IrpTemplate.TISDELNORMAL, pageutil);
				pagetemhtml = pageutil.getClientPageHtml("pageQTemplate("+cateid+",#PageNum#)");
			}
		}
		return SUCCESS;
	}
	/**
	 * 找到获取主键的id的对象
	 * @return
	 */
	public void findQTemCate(){

		IrpTemplate irptemplate =	this.irpTemplateService.irpTemplateById(tid);
		
		ActionUtil.writer(irptemplate.getTvalue());
	}
	/**
	 * 根据分类获取所有的模版
	 * @return
	 */
	public String linkQTemLidByCId(){
		
		return SUCCESS;
	}
	/**
	 * 根据分类获取所有的模版data
	 * 
	 * @return
	 */
	public String templateKnowList(){

		String _oOrderby = "";
		
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "tid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		if (cateid!=null) {
			int num = this.irpTemplateService.findTemplateListNum(IrpTemplate.TSTATUSISCITIAO, IrpTemplate.TISDELNORMAL,cateid, searchType, searchWord);
			
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize,num);
			
			templatelist = this.irpTemplateService.findTemplateList(pageUtil, IrpTemplate.TSTATUSISCITIAO,  IrpTemplate.TISDELNORMAL, _oOrderby,cateid, searchType, searchWord);
			this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		}

		return SUCCESS;
	}
	
	public String templateKnowListKnow(){
		
	String _oOrderby = "";
		
		if (this.orderField == null || this.orderField.equals("")) {
			_oOrderby = "tid desc";
		} else {
			_oOrderby = this.orderField + " " + this.orderBy;
		}
		if (cateid!=null) {
			int num = this.irpTemplateService.findTemplateListNum(IrpTemplate.TSKNOWSISCIAO, IrpTemplate.TISDELNORMAL,cateid, searchType, searchWord);
			
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize,num);
			
			templatelist = this.irpTemplateService.findTemplateList(pageUtil, IrpTemplate.TSKNOWSISCIAO,  IrpTemplate.TISDELNORMAL, _oOrderby,cateid, searchType, searchWord);
			this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		}
		
		return SUCCESS;
	}
	
	private Long cateid;
	
	private int templatequotenums;
	
	private String pagetemhtml;
	
	private int  pagetemnums = 1;


	public int getPagetemnums() {
		return pagetemnums;
	}

	public void setPagetemnums(int pagetemnums) {
		this.pagetemnums = pagetemnums;
	}

	public String getPagetemhtml() {
		return pagetemhtml;
	}

	public void setPagetemhtml(String pagetemhtml) {
		this.pagetemhtml = pagetemhtml;
	}

	public int getTemplatequotenums() {
		return templatequotenums;
	}

	public void setTemplatequotenums(int templatequotenums) {
		this.templatequotenums = templatequotenums;
	}

	public Long getCateid() {
		return cateid;
	}

	public void setCateid(Long cateid) {
		this.cateid = cateid;
	}
}
