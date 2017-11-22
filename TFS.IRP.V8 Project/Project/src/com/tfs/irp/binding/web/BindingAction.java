package com.tfs.irp.binding.web;

import java.lang.reflect.Field;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;



import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.binding.entity.IrpBinding;
import com.tfs.irp.binding.service.IrpBindingService;
import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.channel.service.IrpChannelService;
import com.tfs.irp.document.entity.CopyIrpDocument;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.form.entity.IrpForm;
import com.tfs.irp.form.service.IrpFormService;
import com.tfs.irp.formcolumn.entity.IrpFormColumn;
import com.tfs.irp.formcolumn.service.IrpFormColumnService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;


public class BindingAction extends ActionSupport {
	private IrpBindingService irpBindingService;
	private IrpFormColumnService irpFormColumnService;
	private IrpChannelService irpChannelService;
	private IrpFormService irpFormService;
	
	
	public IrpFormService getIrpFormService() {
		return irpFormService;
	}

	public void setIrpFormService(IrpFormService irpFormService) {
		this.irpFormService = irpFormService;
	}

	public IrpFormColumnService getIrpFormColumnService() {
		return irpFormColumnService;
	}

	public void setIrpFormColumnService(IrpFormColumnService irpFormColumnService) {
		this.irpFormColumnService = irpFormColumnService;
	}

	public IrpChannelService getIrpChannelService() {
		return irpChannelService;
	}

	public void setIrpChannelService(IrpChannelService irpChannelService) {
		this.irpChannelService = irpChannelService;
	}

	public IrpBindingService getIrpBindingService() {
		return irpBindingService;
	}

	public void setIrpBindingService(IrpBindingService irpBindingService) {
		this.irpBindingService = irpBindingService;
	}
	
	private Long bindingid;
	private int pageNum=1; 
	private int pageSize=10; 
	private String pageHTML;
	
	
	public Long getBindingid() {
		return bindingid;
	}

	public void setBindingid(Long bindingid) {
		this.bindingid = bindingid;
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
	private IrpBinding irpBinding;
	
	public IrpBinding getIrpBinding() {
		return irpBinding;
	}

	public void setIrpBinding(IrpBinding irpBinding) {
		this.irpBinding = irpBinding;
	}

	public void addBinding(){
		int msg = 0;
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request.getParameter("show").equals("1")){
			IrpChannel irpchannel=irpChannelService.finChannelByChannelid(irpBinding.getChannelid());
			String tablecolumn=null;
			if(irpchannel.getChnltable()==null){
				tablecolumn=irpBinding.getBindingcolumn()+",";
			}else{
				tablecolumn=irpBinding.getBindingcolumn()+","+irpchannel.getChnltable();
			}
			irpchannel.setChnltable(tablecolumn);
			irpChannelService.updateChannelByChannelid(irpchannel);
		}
		irpBinding.setBindingid(TableIdUtil.getNextId(IrpBinding.TABLE_NAME));
		msg = irpBindingService.addBinding(irpBinding);
		ActionUtil.writer(""+msg);
	}
	private Long channelid;
	
	public Long getChannelid() {
		return channelid;
	}

	public void setChannelid(Long channelid) {
		this.channelid = channelid;
	}
	private String formtablename;
	

	public String getFormtablename() {
		return formtablename;
	}

	public void setFormtablename(String formtablename) {
		this.formtablename = formtablename;
	}

	public String listBindingByChannelid(){
		if(channelid==0||channelid.equals("")||channelid==null){
			int aDataCount=irpBindingService.countBinding();
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);
			listbinding = irpBindingService.showAllBinding(pageUtil);
			this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		}else{
			int aDataCount=irpBindingService.countBinding(channelid);
			IrpChannel channel=irpChannelService.finChannelByChannelid(channelid);
			IrpForm irpForm=irpFormService.getFormById(channel.getFormid());
			formtablename=irpForm.getFormtablename();
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);
			listbinding = irpBindingService.listBinding(pageUtil,channelid);
			this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		}
		return SUCCESS;
		
	}
	private List<IrpBinding> listbinding;
	
	public List<IrpBinding> getListbinding() {
		return listbinding;
	}

	public void setListbinding(List<IrpBinding> listbinding) {
		this.listbinding = listbinding;
	}
	
	public String listBinding(){
		int aDataCount=irpBindingService.countBinding(channelid);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, aDataCount);
		listbinding = irpBindingService.showAllBinding(pageUtil);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
		
	}
	
	public void deleteBinding(){
		int msg = 0;
		msg = irpBindingService.deleteBinding(bindingid);
		ActionUtil.writer(""+msg);
	}
	private String bindingids;
	
	public String getBindingids() {
		return bindingids;
	}

	public void setBindingids(String bindingids) {
		this.bindingids = bindingids;
	}

	public void deleteBindings(){
		int msg = 0;
		if(bindingids!=""&&bindingids!=null){
    		String[] array =bindingids.split(",");
    		for(int j=0;j<array.length;j++){
    			irpBindingService.deleteBinding(Long.parseLong(array[j]));
    			msg++;
    		}
		}
		ActionUtil.writer(""+msg);
	}
	private String columnname ;
	private String bindingcolumn ;
	
	
	public String getBindingcolumn() {
		return bindingcolumn;
	}

	public void setBindingcolumn(String bindingcolumn) {
		this.bindingcolumn = bindingcolumn;
	}

	public String getColumnname() {
		return columnname;
	}

	public void setColumnname(String columnname) {
		this.columnname = columnname;
	}

	List<String> columnlist;
	
	
	public List<String> getColumnlist() {
		return columnlist;
	}
	public void setColumnlist(List<String> columnlist) {
		this.columnlist = columnlist;
	}
	private List<IrpFormColumn> irpFormColumnList;
	
	public List<IrpFormColumn> getIrpFormColumnList() {
		return irpFormColumnList;
	}

	public void setIrpFormColumnList(List<IrpFormColumn> irpFormColumnList) {
		this.irpFormColumnList = irpFormColumnList;
	}
	public String findBindingById(){
		columnlist=new ArrayList<String>();
		IrpDocumentWithBLOBs irpdocument=new IrpDocumentWithBLOBs();
		CopyIrpDocument document=new CopyIrpDocument();
		Field[] fields = document.getClass().getDeclaredFields();
		for (int i=0; i < fields.length; i++)  
		{    
			String fieldNames = fields[i].getName();  
			// System.out.println(fieldNames+"=========这是返回的字段=========");
			columnlist.add(fieldNames);
		}
		irpBinding=irpBindingService.findBindingByBindingid(bindingid);
		Long channelid=irpBinding.getChannelid();
		IrpChannel channel=irpChannelService.finChannelByChannelid(channelid);
		IrpForm irpForm=irpFormService.getFormById(channel.getFormid());
		String formtablename=irpForm.getFormtablename();
    	irpFormColumnList = irpFormColumnService.getListBytablename(formtablename);
		return SUCCESS;
	}
	public void updateBinding(){
		int msg = 0;
		msg = irpBindingService.updateBindingByBindingid(irpBinding);
		ActionUtil.writer(""+msg);
	}
	
}
