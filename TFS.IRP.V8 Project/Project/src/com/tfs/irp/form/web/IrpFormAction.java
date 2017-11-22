package com.tfs.irp.form.web;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import oracle.net.aso.a;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.struts2.ServletActionContext;

import javassist.tools.framedump;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.entity.IrpAttachedInfo;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.attachedtype.entity.IrpAttachedType;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.entity.IrpAttachedInfo;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.attachedtype.entity.IrpAttachedType;
import com.tfs.irp.binding.entity.IrpBinding;
import com.tfs.irp.binding.service.IrpBindingService;
import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.channel.service.IrpChannelService;
import com.tfs.irp.document.entity.CopyIrpDocument;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.document.service.IrpDocumentService;
import com.tfs.irp.form.entity.IrpForm;
import com.tfs.irp.form.service.IrpFormService;

import com.tfs.irp.formcolumn.entity.IrpFormColumn;
import com.tfs.irp.formcolumn.entity.IrpFormColumnExample;
import com.tfs.irp.formcolumn.entity.IrpFormColumnExample.Criteria;
import com.tfs.irp.formcolumn.service.IrpFormColumnService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.JsonUtil;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysFileUtil;
import com.tfs.irp.util.TableIdUtil;

import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.filter.LoginCheckFilter;


public class IrpFormAction extends ActionSupport{
	private IrpChannelService irpChannelService;
	
    public IrpChannelService getIrpChannelService() {
		return irpChannelService;
	}
	public void setIrpChannelService(IrpChannelService irpChannelService) {
		this.irpChannelService = irpChannelService;
	}
	private List<IrpAttached> attacheds;
	public List<IrpAttached> getAttacheds() {
		return attacheds;
	}
	public void setAttacheds(List<IrpAttached> attacheds) {
		this.attacheds = attacheds;
	}
	private IrpAttachedService irpAttachedService;
	
	public IrpAttachedService getIrpAttachedService() {
		return irpAttachedService;
	}

	public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
		this.irpAttachedService = irpAttachedService;
	}

	private String displaytype ;
	private String columnname ;
	private String displaytypevalue ;
	public String getDisplaytype() {
		return displaytype;
	}

	public void setDisplaytype(String displaytype) {
		this.displaytype = displaytype;
	}

	public String getDisplaytypevalue() {
		return displaytypevalue;
	}

	public void setDisplaytypevalue(String displaytypevalue) {
		this.displaytypevalue = displaytypevalue;
	}

	private String columntablenamecol ;
	private String columndesc ;
	private String columnlongtext ;
	private String columndefval ;
	private String columntype ;
	public String getColumnname() {
		return columnname;
	}

	public void setColumnname(String columnname) {
		this.columnname = columnname;
	}

	public String getColumntablenamecol() {
		return columntablenamecol;
	}

	public void setColumntablenamecol(String columntablenamecol) {
		this.columntablenamecol = columntablenamecol;
	}

	public String getColumndesc() {
		return columndesc;
	}

	public void setColumndesc(String columndesc) {
		this.columndesc = columndesc;
	}

	public String getColumnlongtext() {
		return columnlongtext;
	}

	public void setColumnlongtext(String columnlongtext) {
		this.columnlongtext = columnlongtext;
	}

	public String getColumndefval() {
		return columndefval;
	}

	public void setColumndefval(String columndefval) {
		this.columndefval = columndefval;
	}

	public String getColumntype() {
		return columntype;
	}

	public void setColumntype(String columntype) {
		this.columntype = columntype;
	}

	public String getColumnid() {
		return columnid;
	}

	public void setColumnid(String columnid) {
		this.columnid = columnid;
	}

	private String columnid;
	private String formid;
	public String getFormid() {
		return formid;
	}

	public void setFormid(String formid) {
		this.formid = formid;
	}

	private List<String> inttype;

	private List<String> stringtype;

	private List<String> datetype;
	public List<String> getInttype() {
		return inttype;
	}

	public void setInttype(List<String> inttype) {
		this.inttype = inttype;
	}

	public List<String> getStringtype() {
		return stringtype;
	}

	public void setStringtype(List<String> stringtype) {
		this.stringtype = stringtype;
	}

	public List<String> getDatetype() {
		return datetype;
	}

	public void setDatetype(List<String> datetype) {
		this.datetype = datetype;
	}

	private List<IrpFormColumn> irpFormColumns;
	public List<IrpFormColumn> getIrpFormColumns() {
		return irpFormColumns;
	}

	public void setIrpFormColumns(List<IrpFormColumn> irpFormColumns) {
		this.irpFormColumns = irpFormColumns;
	}

	private IrpForm irpForm;
	
	public IrpForm getIrpForm() {
		return irpForm;
	}

	public void setIrpForm(IrpForm irpForm) {
		this.irpForm = irpForm;
	}

	private String formname;
	private String formdesc;
	private String formtablename;
	private String formhtml;
	private Integer formstatus;
	private String columnids;
	public String getFormname() {
		return formname;
	}

	public void setFormname(String formname) {
		this.formname = formname;
	}

	public String getFormdesc() {
		return formdesc;
	}

	public void setFormdesc(String formdesc) {
		this.formdesc = formdesc;
	}

	public String getFormtablename() {
		return formtablename;
	}

	public void setFormtablename(String formtablename) {
		this.formtablename = formtablename;
	}

	public String getFormhtml() {
		return formhtml;
	}

	public void setFormhtml(String formhtml) {
		this.formhtml = formhtml;
	}

	public Integer getFormstatus() {
		return formstatus;
	}

	public void setFormstatus(Integer formstatus) {
		this.formstatus = formstatus;
	}

	public String getColumnids() {
		return columnids;
	}

	public void setColumnids(String columnids) {
		this.columnids = columnids;
	}

	private IrpFormColumn irpFormColumn;
	
	public IrpFormColumn getIrpFormColumn() {
		return irpFormColumn;
	}

	public void setIrpFormColumn(IrpFormColumn irpFormColumn) {
		this.irpFormColumn = irpFormColumn;
	}

	private List<IrpForm>  irpForms;
	public List<IrpForm> getIrpForms() {
		return irpForms;
	}

	public void setIrpForms(List<IrpForm> irpForms) {
		this.irpForms = irpForms;
	}

	private IrpFormService irpFormService;
	
	private IrpFormColumnService irpFormColumnService;

	public IrpFormColumnService getIrpFormColumnService() {
		return irpFormColumnService;
	}

	public void setIrpFormColumnService(IrpFormColumnService irpFormColumnService) {
		this.irpFormColumnService = irpFormColumnService;
	}

	public IrpFormService getIrpFormService() {
		return irpFormService;
	}

	public void setIrpFormService(IrpFormService irpFormService) {
		this.irpFormService = irpFormService;
	}
	
	private Integer formType;//表单状态：正常或者被删除

	public Integer getFormType() {
		return formType;
	}

	public void setFormType(Integer formType) {
		this.formType = formType;
	}
	private String searchType;
	

	private String searchWord;
	
	private int pageNum=1;	
	
	private int pageSize=10;
	
	private String pageHTML;
	
	private String orderField;
	
	private String orderBy;
	
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

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
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

	private List<IrpFormColumn> irpFormColumnList;
	
	public List<IrpFormColumn> getIrpFormColumnList() {
		return irpFormColumnList;
	}

	public void setIrpFormColumnList(List<IrpFormColumn> irpFormColumnList) {
		this.irpFormColumnList = irpFormColumnList;
	}
    
	private List<Object> formInfoList;
	
	public List<Object> getFormInfoList() {
		return formInfoList;
	}

	public void setFormInfoList(List<Object> formInfoList) {
		this.formInfoList = formInfoList;
	}
	private Object forminfo;
	
	public Object getForminfo() {
		return forminfo;
	}

	public void setForminfo(Object forminfo) {
		this.forminfo = forminfo;
	}
	
	private String jsonFile;
	
	public String getJsonFile() {
		return jsonFile;
	}

	public void setJsonFile(String jsonFile) {
		this.jsonFile = jsonFile;
	}

	public String formlist(){
		int count  = this.irpFormService.countBySearchtypeAndWords(searchType, searchWord, formType);
		PageUtil pageUtil = new PageUtil(pageNum, pageSize, count);
		irpForms=this.irpFormService.getAllNormal(pageUtil,searchType, searchWord, formType,orderField,orderBy);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		if(formType.equals(IrpForm.NORMAL)){			
			return SUCCESS;
		}else{
			return INPUT;
		}		
	}
	/**
	 * 表单跳转
	 * @return
	 */
	public String toadd(){
		irpForm=new IrpForm();
		String base = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";     
		Random random = new Random();     
		StringBuffer formname = new StringBuffer("ZD_");     
		for (int i = 0; i < 5; i++) {     
		    int number = random.nextInt(base.length());     
		    formname.append(base.charAt(number));     
		}  
		irpForm.setFormname(formname.toString());
		return SUCCESS;
		
	}
	/**
	 * 增加字段表单跳转
	 */
    public String toaddcolumn(){
    	 irpFormColumn=new IrpFormColumn();
    	 irpFormColumn.setColumnid(0l);
    	 formtablename=formtablename;
    	 if(formType.equals(10)){
    		 
    		 return INPUT;
    	 }else{
    		 return SUCCESS;
    		 
    	 }
    	
    }
    
    public void savecolumn(){
    	Long msg=0l;
    	irpFormColumn.setColumntablenamecol(irpFormColumn.getColumntablenamecol().toUpperCase());
    	String Displaytypevalue=irpFormColumn.getDisplaytypevalue();
    	if(Displaytypevalue!=null&&!Displaytypevalue.equals("")){
    		String ch=Displaytypevalue.trim().replace(",", "");
    		irpFormColumn.setDisplaytypevalue(ch);
    	}
    	msg=this.irpFormColumnService.saveColumn(irpFormColumn);
    	formtablename=irpFormColumn.getColumntablaname();
    	columnname=irpFormColumn.getColumntablenamecol();
    	columntype=irpFormColumn.getColumntype();
    	Integer columnlength=irpFormColumn.getColumnlongtext();
		StringBuffer sql = new StringBuffer("");
		irpForm=irpFormService.getFormByFormtablename(formtablename);
		if(irpForm.getIsexitindb()!=null&&irpForm.getIsexitindb()==10l){
			String name=LoginCheckFilter.PropertiesInfo();
			String ptype=null;
			
			if(columntype!=null){
				if(columntype.equals(IrpFormColumn.OR_CLOB)){
					sql.append("ALTER TABLE "+formtablename+" ADD "+columnname+" "+columntype+" default '"+irpFormColumn.getColumndefval()+"'");
				}else if(columntype.equals(IrpFormColumn.OR_INT)){
					if(name.equals("oracle")){	
						ptype = IrpFormColumn.OR_INT;
					}
					if(name.equals("mysql")){
						ptype = IrpFormColumn.MY_INT;						
					}
					if(name.equals("sqlserver")){
						ptype = IrpFormColumn.SQL_INT;							
					}
					sql.append("ALTER TABLE "+formtablename+" ADD "+columnname+" "+ptype+"("+columnlength+")");
					if(irpFormColumn.getColumndefval()!=null&&!irpFormColumn.getColumndefval().equals("")){
						sql.append(" default "+irpFormColumn.getColumndefval());
					}
				}else if(columntype.equals(IrpFormColumn.OR_STRING)){
					if(name.equals("oracle")){	
						ptype = IrpFormColumn.OR_STRING;
					}
					if(name.equals("mysql")){
						ptype = IrpFormColumn.MY_STRING;						
					}
					if(name.equals("sqlserver")){
						ptype = IrpFormColumn.SQL_STRING;							
					}
					sql.append("ALTER TABLE "+formtablename+" ADD "+columnname+" "+ptype+"("+columnlength+")");
					if(irpFormColumn.getColumndefval()!=null&&!irpFormColumn.getColumndefval().equals("")){
						sql.append(" default '"+irpFormColumn.getColumndefval()+"'");
					}
				}else if(columntype.equals(IrpFormColumn.OR_DATE)){
					if(name.equals("oracle")){	
						ptype = IrpFormColumn.OR_DATE;
					}
					if(name.equals("mysql")){
						ptype = IrpFormColumn.MY_DATE;						
					}
					if(name.equals("sqlserver")){
						ptype = IrpFormColumn.SQL_DATE;							
					}
					sql.append("ALTER TABLE "+formtablename+" ADD "+columnname+" "+ptype);
				}
				
			}
			//sql.append("; commit;");
			LogUtil logUtil=new LogUtil("FORM_DATABASE_UPDATE",irpForm);
			irpFormService.createtable(sql.toString());	
			logUtil.successLogs( "修改数据库["+logUtil.getLogUser()+"]成功");
		}
    	ActionUtil.writer(msg+ "");
    }
    private String formscript;
	private String formparem;
	
    public String getFormscript() {
		return formscript;
	}
	public void setFormscript(String formscript) {
		this.formscript = formscript;
	}
	public String getFormparem() {
		return formparem;
	}
	public void setFormparem(String formparem) {
		this.formparem = formparem;
	}
	/**
     * 保存表单信息
     */
    public void saveform(){
        
    	int msg=0;
    	Long columnid = TableIdUtil.getNextId(IrpForm.TABLE_NAME);
    	IrpForm	irpForm=new IrpForm();
    	irpForm.setFormid(columnid);
		Date date = new Date();
		irpForm.setCrtime(date);
		irpForm.setCruserid(LoginUtil.getLoginUserId());
		irpForm.setFormstatus(formstatus);
		irpForm.setFormdesc(formdesc);
		irpForm.setFormhtml(formhtml);
		irpForm.setFormisdel(IrpForm.NORMAL);
		irpForm.setFormtablename(formtablename);
		irpForm.setFormname(formname);
		irpForm.setFormscript(formscript);
		irpForm.setFormparem(formparem);
    	 msg=this.irpFormService.saveForm(irpForm);
    	
    	
		int columnstatus=0;
		Long n1=irpFormColumnService.addSystemColumn(formtablename,"栏目编号","CHANNEIID","NUMBER",10,"0",columnstatus,"text",0,0,0,"strlen");
		Long n2=irpFormColumnService.addSystemColumn(formtablename,"文档编号","DOCID","NUMBER",10,"0",columnstatus,"text",0,0,0,"strlen");
		Long n3=irpFormColumnService.addSystemColumn(formtablename,"创建用户","CRUSER","NUMBER",10,"0",columnstatus,"text",10,10,0,"0");
		Long n4=irpFormColumnService.addSystemColumn(formtablename,"创建时间","CRTIME","DATE",10,"0",columnstatus,"date",10,10,0,"strlen");
    }
    private Long channelid;
    
	public Long getChannelid() {
		return channelid;
	}
	public void setChannelid(Long channelid) {
		this.channelid = channelid;
	}
	private String content;
    
    public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	/**
     * 复制jsp页面/////////付燕妮
     */
    public void copyjsp() throws Exception{
    	StringBuffer sb=new StringBuffer();
    	sb.append("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"	pageEncoding=\"UTF-8\"%>");
    	sb.append("<%@ taglib uri=\"/struts-tags\" prefix=\"s\" %>");
    	sb.append("<% String rootPath = request.getScheme()+\"://\"+request.getServerName()+\":\"+request.getServerPort()+request.getContextPath()+\"/\"; java.util.UUID uuid  = java.util.UUID.randomUUID();%>");
    	sb.append(content);
    	//sb.append("<a href=\"javascript:void(0)\" onclick=\"addDocument()\">保存</a>");
    	String st=sb.toString();
		HttpServletRequest _request =ServletActionContext.getRequest();
		String realpath=_request.getRealPath("/");
		//定义临时文件结构，并发 
		//String file_temp = "temp\\"+randomdata.replace(".","")+"_"+_request.getLocalAddr().toString().replace(".","");
		if(formid!=null&&!formid.equals("")&&formid!="0"){
    		irpForm=irpFormService.getFormById(Long.parseLong(formid));
    		if(irpForm!=null&&!irpForm.equals("")){
    			formtablename = irpForm.getFormtablename();
    		}
    	}
		IrpForm irpForm=irpFormService.selectformByTableName(formtablename);
		channelid=irpForm.getChannelid();
		if(channelid!=null){
			Long irpformid=irpForm.getFormid();
			String file_temp = "admin\\channel"+channelid+"\\";
			String form=formtablename.toLowerCase();
			String path=realpath+file_temp;
			IrpChannel irpChannel=irpChannelService.finChannelByChannelid(channelid);
			irpChannel.setLinkurl("admin/channel"+channelid+"/"+form+"_info_add.jsp");
			irpChannelService.updateChannelByChannelid(irpChannel);
			irpFormService.copyFile(st, path, formtablename);
		}
    }  
    /**
     * 复制jsp修改页面/////////付燕妮
     */
    private String newPath;
	private Object oldname;
    
    public Object getOldname() {
		return oldname;
	}
	public void setOldname(Object oldname) {
		this.oldname = oldname;
	}
	public String getNewPath() {
		return newPath;
	}
	public void setNewPath(String newPath) {
		this.newPath = newPath;
	}
	public void copyupdatejsp() throws Exception{
    	StringBuffer sb=new StringBuffer();
    	sb.append("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"	pageEncoding=\"UTF-8\"%>");
    	sb.append("<%@ taglib uri=\"/struts-tags\" prefix=\"s\" %>");
    	sb.append("<% String rootPath = request.getScheme()+\"://\"+request.getServerName()+\":\"+request.getServerPort()+request.getContextPath()+\"/\"; java.util.UUID uuid  = java.util.UUID.randomUUID();%>");
    	sb.append(content);
    	//sb.append("<a href=\"javascript:void(0)\" onclick=\"addDocument()\">保存</a>");
    	String st=sb.toString();
    	HttpServletRequest _request =ServletActionContext.getRequest();
    	String realpath=_request.getRealPath("/");
    	//定义临时文件结构，并发 
    	if(formid!=null&&!formid.equals("")&&formid!="0"){
    		irpForm=irpFormService.getFormById(Long.parseLong(formid));
    		if(irpForm!=null&&!irpForm.equals("")){
    			formtablename = irpForm.getFormtablename();
    		}
    	}
    	IrpForm irpForm=irpFormService.selectformByTableName(formtablename);
    	channelid=irpForm.getChannelid();
    	String file_temp = "admin\\channel"+channelid+"\\";
    	String path=realpath+file_temp;
    	irpFormService.copyUpdatejsp(st, path, formtablename);
    	/*if(iszip==1){
			irpFormService.craeteZipPath(path+"\\",name);
			ActionUtil.writer(file_temp+"\\");
		}else{
			ActionUtil.writer("0");
		}*/
    	String form=formtablename.toLowerCase();
    	newPath="admin/channel"+channelid+"/"+form+"_info_update.jsp";
    	ActionUtil.writer(newPath);
    }  
	 public void findurl(){
		 IrpForm irpForm=irpFormService.selectformByTableName(formtablename);
	    	channelid=irpForm.getChannelid();
		String form=formtablename.toLowerCase();
	    newPath="admin/channel"+channelid+"/"+form+"_info_update.jsp";
	    ActionUtil.writer(newPath);
	 }
	/**
     * 创建数据库表单
     */
    public void createtables(){
  /*  	int msg=0;
    	Long columnid = TableIdUtil.getNextId(IrpForm.TABLE_NAME);
    	IrpForm	irpForm=new IrpForm();
    	irpForm.setFormid(columnid);
		Date date = new Date();
		irpForm.setCrtime(date);
		irpForm.setCruserid(LoginUtil.getLoginUserId());
		irpForm.setFormstatus(formstatus);
		irpForm.setFormdesc(formdesc);
		irpForm.setFormhtml(formhtml);
		irpForm.setFormisdel(IrpForm.NORMAL);
		irpForm.setFormtablename(formtablename);
		irpForm.setFormname(formname);
		irpForm.setFormscript(formscript);
		irpForm.setFormparem(formparem);
    	 msg=this.irpFormService.saveForm(irpForm);*/
    
    	 
    	/* //更新字段的数据库表名
    		String sd= columnids.substring(1);
		if(sd!=""&&sd!=null){	       
    	 int g=this.irpFormColumnService.updateTablenameByIds(sd,formtablename);// System.out.println(g+"这是更新字段的数量");
    	
    	 }*/
		//拼接sql
			int result=0;
				List<IrpFormColumn> list=this.irpFormColumnService.getListBytablename(formtablename);
				StringBuffer sql = new StringBuffer("");  
				if(list.size()>0){	
					String name=LoginCheckFilter.PropertiesInfo();
					String ptype= null;
					String numType=null;
					if(name.equals("oracle")){	
						ptype = IrpFormColumn.OR_INT;
					}
					if(name.equals("mysql")){
						ptype = IrpFormColumn.MY_INT;						
					}
					if(name.equals("sqlserver")){
						ptype = IrpFormColumn.SQL_INT;							
					}
					sql.append(" CREATE TABLE " + formtablename + " ("); 
					sql.append("forminfoid "+ptype+"(10) PRIMARY KEY,");
						for(int i=0;i<list.size();i++){
							IrpFormColumn s=list.get(i);
						String col=s.getColumntablenamecol();
						String type=s.getColumntype();					
						int length=s.getColumnlongtext();				
						sql.append(col+" "+type);
						if(!type.equals(IrpFormColumn.OR_DATE)&&!type.equals(IrpFormColumn.OR_CLOB)){
							
							if(s.getColumnlongtext()>0&&s.getColumnlongtext()!=null){
								/*if(ptype.equals(type)){
									sql.append("("+length+",3)");
								}else{
								}*/
								sql.append("("+length+")");
								
							}
						}
						if(i!=list.size()-1){
							
							sql.append(",");
						}
					}
					sql.append(")");
					//System.out.println(sql.toString());
					IrpForm irpForm = irpFormService.selectformByTableName(formtablename);
					irpForm.setIsexitindb(10l);
					irpFormService.updateForm(irpForm);
				//	sql.append("; commit;");
					 result=this.irpFormService.createtable(sql.toString());
					//System.out.println(result+"============这是返回结果===========");
					/*//生成Excel模版
					if(result>0){
						ActionContext ac = ActionContext.getContext();     
					      ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);     
					      String WEB_ROOT_PATH = sc.getRealPath("/"); 
					      String app_path=WEB_ROOT_PATH.replace("\\", "/");    
					        //创建文件路径
					         String path=app_path+"admin";  
							//  System.out.println(path+"这是文件路径");
							
							// 创建文件夹;
							createFile(path);
							// 创建excel文件
							Date date1 = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
							String _realname ="AF"+sdf.format(date1);
							String fileName=formname+"导入模版";
							WritableWorkbook workbook;
							try {
								workbook = Workbook.createWorkbook(new File(path+"/"
										+ _realname+".xls"));
								WritableSheet sheet = workbook.createSheet(fileName, 1);
								for(int i=0;i<list.size();i++){
									IrpFormColumn s=list.get(i);
								Label label = new Label(i, 0, s.getColumndesc());
								
								sheet.addCell(label);
								}
								workbook.write();
								
								// 关闭流;
								workbook.close();
								
								
							} catch (IOException e) {
								e.printStackTrace();
							} catch (RowsExceededException e) {
								e.printStackTrace();
							} catch (WriteException e) {
								e.printStackTrace();
							}
						 String realpath=path+"/"+ _realname+".xls";
							Long _attachedid=irpAttachedService.addFileForForm(columnid, _realname+".xls", fileName+".xls", IrpAttached.FORMEXCEL, realpath);
							//System.out.println(_attachedid+"=这是ID===");
							// 删除目录下所有的文件;
							//File file = new File(path);
							// 删除文件;
							//deleteExcelPath(file);
					}*/
					 ///////////////////////付燕妮
						 /*try {
							int bytesum = 0; 
							   int byteread = 0; 
							   String form=formtablename.toLowerCase();
							   String filename=form+"_info_add.jsp";
							 String oldPath = "C:/tomcat7/webapps/6sigma/admin/form/form_info_add.jsp";
							 String newPath="c:/"+filename;
							 File oldfile = new File(oldPath);
							 if (oldfile.exists()) { 
								 InputStream inStream = new FileInputStream(oldPath); //读入原文件 
							     FileOutputStream outStream = new FileOutputStream(newPath); 
							     byte[] buffer = new byte[1444]; 
							     //int length;
							     while ( (byteread = inStream.read(buffer)) != -1) { 
							    	 bytesum += byteread; //字节数 文件大小 
							    	 outStream.write(buffer, 0, byteread); 
							     } 
							     inStream.close(); 
							 }
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
					
				}
				
			
			
			 ActionUtil.writer(result+ "");
		
    	
    	
    }
   /**
    * 验证显示名称是否存在 
    */
    public void isexitformname(){
         int msg=0;
    	if(formname!=null&&formname!=""){
    		
    		boolean flag=this.irpFormService.isExitFormName(formname);
    		if(!flag){
    			msg=1;//意思是可用
    		}
    		
    	}
    	String words=LoginCheckFilter.PropertiesKeyWord();
    	String[] array=words.split(",");
    	 for(int j=0;j<array.length;j++)   
	        {   
    		 if(formname.equalsIgnoreCase(array[j])){
    			 msg=2;//不能是关键字
    		 }
	        }
    	
    	ActionUtil.writer(msg+"");
    }
    
    
    
    /**
     * 验证数据库名称是否存在 
     */
    public void isexitformtablename(){
        int msg=0;
   	    if(formtablename!=null&&formtablename!=""){
   	    	if(formtablename.length()>=4){
    			if(formtablename.substring(0, 4).equalsIgnoreCase("IRP_")){
    				 msg=3;
    			}
    		}
   		boolean flag=this.irpFormService.isExitFormTableName(formtablename);
   		if(!flag){
   			msg=1;//意思是不可用
   		}
   		List<IrpForm> s = irpFormService.selectByformtablename(formtablename);
   		if(s!=null&&s.size()>0){
   			if(oldname.equals("")){
	   			if(s.get(0).getFormtablename().equals(formtablename)){
	   					msg=1;//意思是不可用
	   			}
   			}else{
   				if(s.get(0).getFormtablename().equals(formtablename)&&!oldname.equals(formtablename)){
   					msg=1;//意思是不可用
   				}
   			}
   		}
   	}
   	String words=LoginCheckFilter.PropertiesKeyWord();
   	String[] array=words.split(",");
   	 for(int j=0;j<array.length;j++)   
	        {   
   		 if(formtablename.equalsIgnoreCase(array[j])){
   			 msg=2;//不能是关键字
   		 }
	        }
   
   	ActionUtil.writer(msg+"");
   
    	
    }
    /**
     * 删除表单到回收站
     */
    public void deleteFormTo(){
    	int msg=0;
    	if(columnids!=""&&columnids!=null){
    		msg=this.irpFormService.deleteToRecover(columnids);
    	}
    	ActionUtil.writer(msg+"");
    	
    	
    }
    
    /**
     * 从回收站恢复表单
     */
    public void recoverForm(){
    	int msg=0;
    	if(columnids!=""&&columnids!=null){
    		msg=this.irpFormService.recoverForm(columnids);
    	}
    	ActionUtil.writer(msg+"");
    	
    }
    /**
     * 从回收站删除表单
     */
    public void realDelete(){
    	int msg=0;
    	if(columnids!=""&&columnids!=null){
    
    		
    		
    		msg=this.irpFormService.realDelete(columnids);
    	}
    	ActionUtil.writer(msg+"");
    	
    }
    
    /**
     * 查看表单
     */
    public String toformview(){
    	 irpForm=this.irpFormService.getFormById(Long.parseLong(columnids));
    	 if(irpForm!=null){
    		 
    		irpFormColumns= this.irpFormColumnService.getListBytablename(irpForm.getFormtablename());
    	 }
    		inttype =this.irpFormColumnService.intType();
    		stringtype = this.irpFormColumnService.stringType();
    		datetype = this.irpFormColumnService.dateType();	
    	 
    	return SUCCESS;
    }
    /**
     * 跳转修改表单
     */
    public String toformupdate(){
    	
    	irpForm=this.irpFormService.getFormById(Long.parseLong(columnids));
   	 if(irpForm!=null){
   		 
   		irpFormColumns= this.irpFormColumnService.getListBytablename(irpForm.getFormtablename());
   	 }
   	inttype =this.irpFormColumnService.intType();
	stringtype = this.irpFormColumnService.stringType();
	datetype = this.irpFormColumnService.dateType();	
    	return SUCCESS;	
    }
    private int columninliststatus;
	
	public int getColumninliststatus() {
		return columninliststatus;
	}
	public void setColumninliststatus(int columninliststatus) {
		this.columninliststatus = columninliststatus;
	}
    private String newpage;
	public String getNewpage() {
		return newpage;
	}
	public void setNewpage(String newpage) {
		this.newpage = newpage;
	}
    /**
     * 查看表单相关数据
     */
    public String formInfoView(){
    	int count = irpFormService.countFormInfo(formtablename, searchType, searchWord,null);
    	PageUtil pageUtil = new PageUtil(pageNum, pageSize, count);
    	 irpForm=this.irpFormService.getFormByFormtablename(formtablename);
    	channelid=irpForm.getChannelid();
    	///////判断是走新建页面还是默认页面
    	if(channelid!=null&&!channelid.equals("")){
    		IrpChannel irpChannel=irpChannelService.finChannelByChannelid(channelid);
    		HttpServletRequest _request =ServletActionContext.getRequest();
 			String realpath=_request.getRealPath("/");
    		String newpath=irpChannel.getLinkurl();
 			if(newpath!=null&&!newpath.equals("")){
 				newpath=newpath.replace("/", "\\");
 				String path=realpath+newpath;
	 			File file = new File(path);
	 			if(file.exists()){
	 				newpage=irpChannel.getLinkurl();
	 			}
	 			else{
	 				newpage="form/toaddforminfopage.action?formtablename='+tablename+'";
	 			}
 			}
 			else{
 				newpage="form/toaddforminfopage.action?formtablename='+tablename+'";
 			}
    	}else{
				newpage="form/toaddforminfopage.action?formtablename='+tablename+'";
		}
    	/*newpage=irpForm.getNewpagepath();
    	if(newpage==null||newpage==""){
    		newpage="form/toaddforminfopage.action?formtablename='+tablename+'";
    	}*/
    	irpFormColumnList = irpFormColumnService.getListBytablename(columninliststatus,formtablename);
    	formInfoList = irpFormService.selectFormInfo(pageUtil,searchType,searchWord,columninliststatus,formtablename,orderField,orderBy);
    	this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
    	attacheds = this.irpAttachedService.getAttachedByAttDocId(irpForm.getFormid(),
				IrpAttached.FORMEXCEL);
    	return SUCCESS;
    }
    private int tableIsExit;
	
	public int getTableIsExit() {
		return tableIsExit;
	}
	public void setTableIsExit(int tableIsExit) {
		this.tableIsExit = tableIsExit;
	}
    private String columntablaname;
    
    public String getColumntablaname() {
		return columntablaname;
	}
	public void setColumntablaname(String columntablaname) {
		this.columntablaname = columntablaname;
	}

    /**
     * 查看表单所有字段
     */
    public String formInfoColumn(){
    	IrpFormColumnExample example=new IrpFormColumnExample();
		Criteria criteria=example.createCriteria();
		String searchColumn = "";
		if(searchType!=null&&searchWord!=null&&!searchWord.equals("") && !searchType.equals("")){
			searchColumn="and "+searchType+" like '%"+searchWord+"%'";
		}
		criteria.andColumntablanameEqualTo(formtablename);
		example.setOrderByClause(searchColumn);
    	int count = irpFormService.countFormColumn1(example);
    	tableIsExit=irpFormService.isExitFormTableName(formtablename)==true? 1:0;
    	PageUtil pageUtil = new PageUtil(pageNum, pageSize, count);
    	 irpForm=this.irpFormService.getFormByFormtablename(formtablename);
    	irpFormColumnList = irpFormColumnService.getListBytablename(pageUtil,formtablename);
    	//formInfoList = irpFormService.selectFormInfo(pageUtil,searchType,searchWord,formtablename,orderField,orderBy);
    	IrpFormColumn irpformcolumn=new IrpFormColumn();
    	irpformcolumn.setColumntablaname(formtablename);
    	columntablaname=irpformcolumn.getColumntablaname();
    	this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
    	attacheds = this.irpAttachedService.getAttachedByAttDocId(irpForm.getFormid(),
				IrpAttached.FORMEXCEL);
    	
    	return SUCCESS;
    } 
    
    /**
     * 多条删除字段
     */
    public void deletecolumn(){
    	int msg = 0 ;
    	if(columnids!=""&&columnids!=null){
    		String[] array =columnids.split(",");
   		 for(int j=0;j<array.length;j++)   
   	        { 
   			irpFormColumn=irpFormColumnService.selectIrpFromColumnById(Long.parseLong(array[j]));
   			formtablename=irpFormColumn.getColumntablaname();
   			columnname=irpFormColumn.getColumntablenamecol();
   			StringBuffer sql = new StringBuffer("");
   			irpForm=irpFormService.getFormByFormtablename(formtablename);
   			if(irpForm.getIsexitindb()==10l){
   				sql.append("ALTER TABLE "+formtablename+" DROP COLUMN "+columnname+" ");
   				irpFormService.createtable(sql.toString());			
   			}
      this.irpFormColumnService.deleteColumn(Long.parseLong(array[j]));
      msg++;
    		}
    		
    	}
    	ActionUtil.writer(msg+"");
    	
    	
    }
  
   
    /**
     * 刷新字段
     */
    
    public String refreshcolumn(){
    	 if(formtablename!=null&&formtablename!=""){
    		 irpFormColumns=this.irpFormColumnService.getListBytablename(formtablename);
    	 }
		return SUCCESS;
    	
    	
    }
    /**
     * 这是更新表单的操作
     */
    public void updateform(){ 
    	   
		
		int delteresult=this.irpAttachedService.deleteByFormExcel(Long.parseLong(formid),
    			IrpAttached.FORMEXCEL);
	
	 

//如果有要删除的就先删除字段
boolean flag3=false;
if(!columnids.equals("a")){
	String[] array =columnids.split(",");
		 for(int j=0;j<array.length;j++)   
	        {           
 this.irpFormColumnService.deleteColumn(Long.parseLong(array[j]));
 
		}
}else{
	flag3=true;
}
boolean flag2=false;
//如果有单个修改的操作则进行修改
boolean flag=false;
IrpForm irpForm2=this.irpFormService.getFormById(Long.parseLong(formid));
String oldname= irpForm2.getFormtablename();
	columntablenamecol=columntablenamecol.toUpperCase();
	String[] displaytypes =displaytype.split(",") ;    		
	String[] displaytypevalues =displaytypevalue.split(",") ;
	String[] columnnames =columnname.split(",") ;
	String[] columntablenamecols =columntablenamecol.split(",") ;
	String[] columndescs =columndesc.split(",") ;
	String[] columnlongtexts =columnlongtext.split(",") ;
	String[] columndefvals =columndefval.split(",") ;
	String[] columntypes =columntype.split(",") ;
	String[] columnids1 =columnid.split(",") ;
	for(int i=0;i<columnids1.length;i++){
		if(flag3){
			
			if(columnids.contains(columnids1[i])){
				flag2=false;
			}else{
				flag2=true;
			}
		}
		if(flag2){
			IrpFormColumn irpFormColumn=new IrpFormColumn();
			irpFormColumn.setDisplaytype(displaytypes[i]); 
			if(!displaytypevalues[i].equals("blank")){
				
				irpFormColumn.setDisplaytypevalue(displaytypevalues[i]);
			}else{
				irpFormColumn.setDisplaytypevalue("");
				
			}
			if(!columnlongtexts[i].equals("blank")){
				
				irpFormColumn.setColumnlongtext(Integer.parseInt(columnlongtexts[i]));
			}else{
				irpFormColumn.setColumnlongtext(0);
				
			}
			irpFormColumn.setColumndefval(columndefvals[i]);
			irpFormColumn.setColumndesc(columndescs[i]);
			irpFormColumn.setColumnname(columnnames[i]);
			irpFormColumn.setColumntablenamecol(columntablenamecols[i]);
			
			
			String type=columntypes[i];
			if(type.equals("整型")){
				irpFormColumn.setColumntype(IrpFormColumn.OR_INT);
				}else if(type.equals("字符串")){
				irpFormColumn.setColumntype(IrpFormColumn.OR_STRING);
				}else if(type.equals("时间")){
				irpFormColumn.setColumntype(IrpFormColumn.OR_DATE);
				}else if(type.equals("大字段")){
			     irpFormColumn.setColumntype(IrpFormColumn.OR_CLOB);	
				}	
			if(columnids1[i].contains("a")){
				//这段数据要增加到数据库
				Long columnid = TableIdUtil.getNextId(IrpFormColumn.TABLE_NAME);
				irpFormColumn.setColumnid(columnid);
				Date date = new Date();
				irpFormColumn.setCrtime(date);
				irpFormColumn.setCruserid(LoginUtil.getLoginUserId());
				irpFormColumn.setColumnstatus(IrpFormColumn.NORMAL);
				irpFormColumn.setColumntablaname(oldname);
				this.irpFormColumnService.saveColumnForUpdate(irpFormColumn);
				
			}else{
				irpFormColumn.setColumnid(Long.parseLong(columnids1[i]));
				this.irpFormColumnService.updateIrpFromColumnById(irpFormColumn);
			}
		}
		
		
	}

String result="success";
IrpForm	irpForm=new IrpForm();
irpForm.setFormid(Long.parseLong(formid));		
irpForm.setFormdesc(formdesc);
irpForm.setFormhtml(formhtml);
irpForm.setFormtablename(formtablename);
irpForm.setFormname(formname);
irpForm.setFormscript(formscript);
irpForm.setFormparem(formparem);
 if(irpForm2!=null){
	 if(oldname.equals(formtablename)){
		 //相等则不更新
		 flag=true;
	 }else{    			
		 if(oldname!=""&&oldname!=null){
			 List<IrpFormColumn> list=this.irpFormColumnService.getListBytablename(oldname);
			 
				if(list.size()>0){
					String ids="";
					for(IrpFormColumn s:list){
						
						ids+=","+s.getColumnid();
					}
					int g=this.irpFormColumnService.updateTablenameByIds(ids.substring(1),formtablename);
					
				}
			
		 }
		flag=true;
	 }
	 
 }else{
	 result="noBean";
 }
 
 //删除原来的表
 boolean isexist=this.irpFormService.isExitFormTableName(oldname);
 if(!isexist){
	 
	 int ss=this.irpFormService.realeTableByTableName(oldname);
	 LogUtil logUtil=new LogUtil("FORM_DATABASE_DROP",irpForm2);
	 if(ss==0){
		 logUtil.successLogs( "删除数据库["+logUtil.getLogUser()+"]成功");
    		 result="notable";
    	 
	 }
 }
	
	//拼接sql	
	 List<IrpFormColumn> list=new ArrayList<IrpFormColumn>();
	 if(flag){  
		  list=this.irpFormColumnService.getListBytablename(formtablename);
	 }
	StringBuffer sql = new StringBuffer("");  
	if(list.size()>0){	
		String name=LoginCheckFilter.PropertiesInfo();
		String ptype= null;
		if(name.equals("oracle")){	
			ptype = IrpFormColumn.OR_INT;
		}
		if(name.equals("mysql")){
			ptype = IrpFormColumn.MY_INT;						
		}
		if(name.equals("sqlserver")){
			ptype = IrpFormColumn.SQL_INT;							
		}
		sql.append(" CREATE TABLE " + formtablename + " ("); 
		sql.append("forminfoid "+ptype+"(10) PRIMARY KEY,");
			for(int i=0;i<list.size();i++){
				IrpFormColumn s=list.get(i);
			String col=s.getColumntablenamecol();
			String type=s.getColumntype();					
			int length=s.getColumnlongtext();				
			sql.append(col+" "+type);
			if(!type.equals(IrpFormColumn.OR_DATE)&&!type.equals(IrpFormColumn.OR_CLOB)){
				
				if(s.getColumnlongtext()>0&&s.getColumnlongtext()!=null){
					/*if(ptype.equals(type)){
						sql.append("("+length+",3)");
					}else{
					}*/
					sql.append("("+length+")");
					
				}
			}
			if(i!=list.size()-1){
				
				sql.append(",");
			}
		}
		sql.append(")");
	//	System.out.println(sql.toString());
		 LogUtil logUtil=new LogUtil("FORM_DATABASE_ADD",irpForm2);
		 int result1=this.irpFormService.createtable(sql.toString());
		 irpForm.setIsexitindb(10L);
		 logUtil.successLogs( "生成数据库["+logUtil.getLogUser()+"]成功");
		 //更新表单
		 this.irpFormService.updateForm(irpForm);
	//	System.out.println(result1+"============这是导入的返回结果===========");
		//生成Excel模版
		if(result1>0){
			ActionContext ac = ActionContext.getContext();     
		      ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);     
		      String WEB_ROOT_PATH = sc.getRealPath("/"); 
		      String app_path=WEB_ROOT_PATH.replace("\\", "/");    
		        //创建文件路径
		         String path=app_path+"admin";  
				
				
				// 创建文件夹;
				createFile(path);
				// 创建excel文件
				Date date1 = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String _realname ="AF"+sdf.format(date1);
				String fileName=formname+"导入模版";
				WritableWorkbook workbook;
				try {
					workbook = Workbook.createWorkbook(new File(path+"/"
							+ _realname+".xls"));
					WritableSheet sheet = workbook.createSheet(fileName, 1);
					for(int i=0;i<list.size();i++){
						IrpFormColumn s=list.get(i);
					Label label = new Label(i, 0, s.getColumndesc());
					
					sheet.addCell(label);
					}
					workbook.write();
					
					// 关闭流;
					workbook.close();
					
					
				} catch (IOException e) {
					e.printStackTrace();
				} catch (RowsExceededException e) {
					e.printStackTrace();
				} catch (WriteException e) {
					e.printStackTrace();
				}
			 String realpath=path+"/"+ _realname+".xls";
				Long _attachedid=irpAttachedService.addFileForForm(Long.parseLong(formid), _realname+".xls", fileName+".xls", IrpAttached.FORMEXCEL, realpath);
				// 删除目录下所有的文件;
				//File file = new File(path);
				// 删除文件;
				//deleteExcelPath(file);
		}
	}
	


 ActionUtil.writer(result);
	
}
    
    /**
     * 跳转添加表单信息页面
     * @return
     */
    public void toAddFormInfo(){
    	irpFormColumnList = irpFormColumnService.getListBytablename(formtablename);
    	String showinfo =irpFormColumnService.conversion(null,irpFormColumnList); 
    	ActionUtil.writer(showinfo);
    }
    
    private IrpBindingService irpBindingService;
    private IrpDocumentService irpDocumentService;
    
	public IrpDocumentService getIrpDocumentService() {
		return irpDocumentService;
	}
	public void setIrpDocumentService(IrpDocumentService irpDocumentService) {
		this.irpDocumentService = irpDocumentService;
	}
	public IrpBindingService getIrpBindingService() {
		return irpBindingService;
	}

	public void setIrpBindingService(IrpBindingService irpBindingService) {
		this.irpBindingService = irpBindingService;
	}
    /**
     * 添加表单信息
     */
    public void addFormInfo(){
    	String strname = null;
    	String columninfo=null;
    	Map<String,String> map=new HashMap<String, String>();
    	HttpServletRequest request = ServletActionContext.getRequest();
    	//System.out.println(itemflowid);
    	StringBuffer sb = new StringBuffer();
    	irpFormColumnList = irpFormColumnService.getListBytablename(formtablename);
    	irpForm=irpFormService.getFormByFormtablename(formtablename);
    	//Long channelid=irpForm.getChannelid();
    	IrpDocumentWithBLOBs irpDocument=new IrpDocumentWithBLOBs();
    	Long docid=null;
    	if(channelid!=null){
    		irpDocument.setDocid(TableIdUtil.getNextId(IrpDocument.TABLE_NAME));
    	}
    	irpDocument.setChannelid(channelid);
    	docid=irpDocument.getDocid();
    	irpDocument.setDoctitle("0");
		boolean isClient = false;
		Long _toChannelid = null;
		
		String colunm = null;
    	int msg = 0;
    	stringtype = this.irpFormColumnService.stringType();
    	datetype = this.irpFormColumnService.dateType();	
    	for(int i=0;i<irpFormColumnList.size();i++){
    		colunm = irpFormColumnList.get(i).getColumntablenamecol();
    		if(stringtype.contains(irpFormColumnList.get(i).getColumntype())||irpFormColumnList.get(i).getColumntype().equals(IrpFormColumn.OR_CLOB)){
    			if(request.getParameter(colunm)==null){
    				sb.append("'——'");
    			}else{
    				sb.append("'"+request.getParameter(colunm)+"'");
    			}
    			
    		}else if(datetype.contains(irpFormColumnList.get(i).getColumntype())){   			
    			sb.append("to_date ('"+request.getParameter(colunm)+"',"+"'YYYY-MM-DD HH24:MI')");
    		}else{
    			if(request.getParameter(colunm)==null){
    				sb.append("0");
    			}else{
    				if(colunm=="CHANNEIID"||colunm.equals("CHANNEIID")){
    					sb.append(channelid);
    				}else
    				if(colunm=="DOCID"||colunm.equals("DOCID")){
    				////付燕妮
    					sb.append(docid);
    				}else{
    					sb.append(request.getParameter(colunm));    				}
    			}
    		}    		
    		if(irpFormColumnList.size()-1!=i){
    			sb.append(",");
    		}
    	}
    	if(channelid!=null){
    		for(int i=0;i<irpFormColumnList.size();i++){
    			strname = irpFormColumnList.get(i).getColumnname();
        		columninfo=request.getParameter(irpFormColumnList.get(i).getColumntablenamecol());
        		List<IrpBinding> listbinding=irpBindingService.listBindings(channelid);
        		Map<String,String> map1=new HashMap<String, String>();
        		for(int j=0;j<listbinding.size();j++){
        			if(listbinding.get(j).getBindingcolumn().contains(strname)){
        				map.put(strname, columninfo);
        				String bindingname=listbinding.get(j).getDoccolumn();
        				map1.put(bindingname, map.get(strname));
        				Iterator<String> iterator = map.keySet().iterator(); 
        				while (iterator.hasNext()) {
        					 String key = (String) iterator.next(); 
        					 map.remove(key);
        				}
        				Iterator<String> iterator1 = map1.keySet().iterator(); 
        				while (iterator1.hasNext()) {
        					String key1 = (String) iterator1.next();
        					System.out.println("sss"+key1);
       					 	if(key1.equals("doctitle")){
       					 		irpDocument.setDoctitle(map1.get(bindingname));
       					 	}
       					 	if(key1.equals("doccontent")){
       					 		irpDocument.setDoccontent(map1.get(bindingname));
       					 	}
       					 	if(key1.equals("dockeywords")){
       					 		irpDocument.setDockeywords(map1.get(bindingname));
       					 	}
       					 	if(key1.equals("docauthor")){
       					 		irpDocument.setDocauthor(map1.get(bindingname));
       					 	}
        				}
        			}
        		}
    		}
    		//String title=request.getParameter(irpFormColumnList.get(4).getColumntablenamecol());
    		//irpDocument.setDoctitle(aaa);
    		System.out.println("文章标题为aaaaa："+irpDocument.getDoctitle());
    		irpDocumentService.addDocument(irpDocument, jsonFile,isClient, _toChannelid, true, null,IrpDocument.DOCTYPE_DOCUMENT);
    	}
    	msg = irpFormService.addFormInfo(jsonFile,sb.toString(), formtablename);
    	ActionUtil.writer(""+msg);
    }  
    /**
     * 获取字段数量
     */
    public void countColumn(){
    	int count = 0;
    	irpFormColumnList = irpFormColumnService.getListBytablename(formtablename);
    	count = irpFormColumnList.size();
    	ActionUtil.writer(count+"");
    }
    /**
     * 删除表单数据
     */
    public void deleteFormInfo(){
    	int msg = 0;
    	msg = irpFormService.deleteFormInfoById(Long.parseLong(formid), formtablename);
    	ActionUtil.writer(msg+"");
    }
    /**
     * 删除表单多条数据
     */
    public void deleteFormInfos(){
    	int count = 0;
    	int msg = 0;
    	String[] forminfoids = formid.split(",");
    	for(int i=0;i<forminfoids.length;i++){
    		count = irpFormService.deleteFormInfoById(Long.parseLong(forminfoids[i]), formtablename);
    		if(count>0){
    			msg++;
    		}   		
    	}    	
    	ActionUtil.writer(msg+"");
    }
    /**
     * 查询实体
     */
    public void findObjet(){
    	irpFormColumnList = irpFormColumnService.getListBytablename(formtablename);
    	forminfo = this.irpFormService.findObjectById(Long.parseLong(formid), formtablename);
    	Map map = this.irpFormService.object2map(irpFormColumnList,forminfo);
    	String showinfo =irpFormColumnService.conversion(map,irpFormColumnList); 
    	ActionUtil.writer(showinfo);
    }
    /**
     * 更新数据
     */
    public void updateFormInfo(){
    	HttpServletRequest request = ServletActionContext.getRequest();
    	StringBuffer sb = new StringBuffer();
    	irpFormColumnList = irpFormColumnService.getListBytablename(formtablename);
    	irpForm=irpFormService.getFormByFormtablename(formtablename);
    	Long channelid=irpForm.getChannelid();
    	IrpDocumentWithBLOBs irpDocument=new IrpDocumentWithBLOBs();
    	String docid=null;
    	boolean isClient = false;
		Long _touChannelid = null;
		String[] ids = request.getParameterValues("subjectselect");
    	String colunm = null;
    	int msg = 0;
    	stringtype = this.irpFormColumnService.stringType();
    	datetype = this.irpFormColumnService.dateType();
    	for(int i=0;i<irpFormColumnList.size();i++){
    		colunm = irpFormColumnList.get(i).getColumntablenamecol();
    		if(stringtype.contains(irpFormColumnList.get(i).getColumntype())){
    			sb.append(colunm+"='"+request.getParameter(colunm)+"'");
    		}else if(datetype.contains(irpFormColumnList.get(i).getColumntype())){   			
    			sb.append(colunm+"=to_date ('"+request.getParameter(colunm)+"',"+"'YYYY-MM-DD HH24:MI')");
    		}else{
    			if(channelid==null){   				
    				if(colunm=="CHANNEIID"||colunm.equals("CHANNEIID")){
    					sb.append(colunm+"="+null);
    				}else
    					if(colunm=="DOCID"||colunm.equals("DOCID")){
    						sb.append(colunm+"="+null);
    					}
    					else{
    						sb.append(colunm+"="+request.getParameter(colunm));    				
    					}
    			}else{
    				if(colunm=="DOCID"||colunm.equals("DOCID")){  		
    		    		docid=request.getParameter(colunm);
    		    	}
    				sb.append(colunm+"="+request.getParameter(colunm));
    			}
    		}     		
    		if(irpFormColumnList.size()-1!=i){
    			sb.append(",");
    		}
    	}
    	if(channelid!=null){
    		Long documentid=Long.parseLong(docid);
    		String title=request.getParameter(irpFormColumnList.get(4).getColumntablenamecol());
    		irpDocument.setChannelid(channelid);
    		irpDocument.setDocid(documentid);
    		irpDocument.setDoctitle(title);
    		
    		irpDocumentService.updateDocumentByDocId(irpDocument, jsonFile, isClient, _touChannelid, true, null,ids,null);
    	}
    	msg = irpFormService.updateFromInfoByID(jsonFile,Long.parseLong(formid), formtablename, sb.toString());
    	ActionUtil.writer(""+msg);
    }
    private String showinfo;
    
    public String getShowinfo() {
		return showinfo;
	}
	public void setShowinfo(String showinfo) {
		this.showinfo = showinfo;
	}
    /**
     * 跳转添加表单信息页面
     * @return
     */
    public String toAddFormInfoPage(){
    	/*List<IrpFormColumn> irpFormColumnList=irpFormColumnService.getListBytablename(formtablename);
    	String showinfo =irpFormColumnService.columnchecktype(irpFormColumnList); 
    	ActionUtil.writer(showinfo);*/
    	if(formid!=null&&!formid.equals("")&&formid!="0"){
    		irpForm=irpFormService.getFormById(Long.parseLong(formid));
    		if(irpForm!=null&&!irpForm.equals("")){
    			formtablename=irpForm.getFormtablename();
    			channelid=irpForm.getChannelid();
    			irpFormColumnList = irpFormColumnService.getListBytablename(formtablename);
    			showinfo =irpFormColumnService.conversion(null,irpFormColumnList); 
    		}
    	}else{
    		irpFormColumnList = irpFormColumnService.getListBytablename(formtablename);
    		showinfo =irpFormColumnService.conversion(null,irpFormColumnList); 
    	}
    	return SUCCESS;
    }    
    
    /**
	 * 创建文件夹;
	 * 
	 * @param path
	 * @return
	 */
	public String createFile(String path) {
		File file = new File(path);
		// 判断文件是否存在;
		if (!file.exists()) {
			// 创建文件;
			boolean bol = file.mkdirs();
			if (bol) {
				// System.out.println(path+" 路径创建成功!");
			} else {
				// System.out.println(path+" 路径创建失败!");
			}
		} else {
			// System.out.println(path+" 文件已经存在!");
		}
		return path;
	}
	private Long docid;
	
	   public Long getDocid() {
			return docid;
		}
		public void setDocid(Long docid) {
			this.docid = docid;
		}
	public String findObjetPage(){
		if(docid!=null){
			formid=irpFormService.findFormInfoById(docid, formtablename)+"";
			///////为复制页面返回数据
			irpFormColumnList = irpFormColumnService.getListBytablename(formtablename);
	    	forminfo = this.irpFormService.findObjectById(Long.parseLong(formid), formtablename);
	    	Map map = this.irpFormService.object2map(irpFormColumnList,forminfo);
	    	showinfo =irpFormColumnService.conversion(map,irpFormColumnList); 
		}
		   return SUCCESS;
	   } 
 //查看某个文档的所有附件
 	public void allAttachedToDocument() {
 		List<IrpAttached> attacheds = irpAttachedService.getAttachedByAttDocId(Long.parseLong(formid), IrpAttached.CUSTOMERFORM);
 		List list = new ArrayList();
 		if (attacheds != null && attacheds.size() > 0) {
 			for (int i = 0; i < attacheds.size(); i++) {
 				IrpAttached attached = new IrpAttached();
 				IrpAttached att = attacheds.get(i);
 				attached.setAttachedid(att.getAttachedid());
 				attached.setAttdesc(att.getAttdesc());
 				attached.setEditversions(att.getEditversions());
 				attached.setAttlinkalt(att.getAttlinkalt());
 				list.add(attached);
 			}
 		}
 		ActionUtil.writer(JsonUtil.list2json(attacheds));
 	}  
	// 跳转到修改附件的页面
	public String toUpdateAttached() {
		return SUCCESS;
	}   
	List<String> columnlist;
	
	
	public List<String> getColumnlist() {
		return columnlist;
	}
	public void setColumnlist(List<String> columnlist) {
		this.columnlist = columnlist;
	}
	private List<IrpChannel> listChannel;
	
	public List<IrpChannel> getListChannel() {
		return listChannel;
	}
	public void setListChannel(List<IrpChannel> listChannel) {
		this.listChannel = listChannel;
	}
    
/////////查询某一表单所有字段以及document表的字段
public String listcolumn() throws Throwable{
	listChannel=new ArrayList<IrpChannel>();
	if(channelid==0){
		List<IrpChannel> channel=irpChannelService.findChannelsBySiteId(2);
		/////筛选表单不为空的栏目
		for(int i=0;i<channel.size();i++){
			try {
				if(channel.get(i).getFormid()!=null||!channel.get(i).getFormid().equals("")){
					IrpChannel irpchannel=irpChannelService.finChannelByChannelid(channel.get(i).getChannelid());
					listChannel.add(irpchannel);
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}
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
	/*Field[] fieldBLOBs = irpdocument.getClass().getDeclaredFields();
	String[] fieldBLOBNames = new String[fieldBLOBs.length]; 
	for (int i=0; i < fieldBLOBs.length; i++)  
	{    
		fieldBLOBNames[i] = fieldBLOBs[i].getName();    
		columnlist.add(fieldBLOBNames[i]);
	}*/
	irpFormColumnList = irpFormColumnService.getListBytablename(formtablename);
	return SUCCESS;
}
    
    
    
}
