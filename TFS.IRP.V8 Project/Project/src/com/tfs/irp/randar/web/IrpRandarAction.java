package com.tfs.irp.randar.web;


import java.io.UnsupportedEncodingException;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.document.service.IrpDocumentService;
import com.tfs.irp.randar.service.IrpRandarService;
import com.tfs.irp.randar.entity.IrpRandar;
import com.tfs.irp.randar.entity.TableType;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

@SuppressWarnings("serial")
public class IrpRandarAction extends ActionSupport {
	
	private IrpRandarService irpRandarService;
	private int pageNum=1;	
	private int pageSize=10;	
	private List<IrpRandar> irpRandars;
	private String pageHTML;	
	private List<String> irpt;	
	private String sitname;	
	private String stitle;	
	private IrpRandar ss;	
	private long sitid;	
	private long chanid;
	private int status;
	private String sid;
	private String doccontent;
	private String sFlowIds;
	
	
	public String getsFlowIds() {
		return sFlowIds;
	}

	public void setsFlowIds(String sFlowIds) {
		this.sFlowIds = sFlowIds;
	}

	public IrpRandarService getIrpRandarService() {
		return irpRandarService;
	}

	public void setIrpRandarService(IrpRandarService irpRandarService) {
		this.irpRandarService = irpRandarService;
	}

	public List<IrpRandar> getIrpRandars() {
		return irpRandars;
	}

	public void setIrpRandars(List<IrpRandar> IrpRandars) {
		irpRandars = IrpRandars;
	}

	public IrpRandar getSs() {
		return ss;
	}

	public void setSs(IrpRandar ss) {
		this.ss = ss;
	}

	public String getDoccontent() {
		return doccontent;
	}

	public void setDoccontent(String doccontent) {
		this.doccontent = doccontent;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	private String orderBy;
	private String orderField;
	private IrpDocumentWithBLOBs irpDocument;
	private IrpDocumentService irpDocumentService;
	
	public IrpDocumentWithBLOBs getIrpDocument() {
		return irpDocument;
	}

	public void setIrpDocument(IrpDocumentWithBLOBs irpDocument) {
		this.irpDocument = irpDocument;
	}

	public IrpDocumentService getIrpDocumentService() {
		return irpDocumentService;
	}

	public void setIrpDocumentService(IrpDocumentService irpDocumentService) {
		this.irpDocumentService = irpDocumentService;
	}

	public long getChanid() {
		return chanid;
	}

	public void setChanid(long chanid) {
		this.chanid = chanid;
	}

	public long getSitid() {
		return sitid;
	}

	public void setSitid(long sitid) {
		this.sitid = sitid;
	}

	public String getStitle() {
		return stitle;
	}

	public void setStitle(String stitle) {
		this.stitle = stitle;
	}

	public String getSitname() {
		return sitname;
	}

	public void setSitname(String sitname) {
		this.sitname = sitname;
	}

	public List<String> getIrpt() {
		return irpt;
	}

	public void setIrpt(List<String> irpt) {
		this.irpt = irpt;
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


	public void checktitle(){
		int i=irpRandarService.upRadar(stitle, status,sid,doccontent);
		 ActionUtil.writer(String.valueOf(i));
	}
	
	public String randarList() {
		int nDataCount = irpRandarService.countRadar();
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
		String sOrderByClause = "";
		if(this.orderField==null || this.orderField.equals("")){
			sOrderByClause = "SYSTIME DESC";
		}else{
			sOrderByClause = this.orderField+" "+this.orderBy;
		}
		//处理排序
		irpRandars = irpRandarService.queryRadar(pageUtil,sOrderByClause);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	
	 public String querMenu(){
		 irpt=irpRandarService.sits();
		 return SUCCESS;
	 }
	 
	 public String querMenus(){
		 int nDataCount = irpRandarService.countRadars(this.sitname);
		 PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount);
		 String sOrderByClause = "";
			if(this.orderField==null || this.orderField.equals("")){
				sOrderByClause = "SYSTIME DESC";
			}else{
				sOrderByClause = this.orderField+" "+this.orderBy;
			}
		 irpRandars=irpRandarService.queryRadars(pageUtil,this.sitname,sOrderByClause);
		 return SUCCESS;
	 }
	 
	 public void impRandar(){
		 int nCount = 0;
		 int ds=0;
		 int satus=0;
		 String sid="";
		 String[] title =stitle.split(",");
		 String tit="";
		 String htmcon="";
		 int num=0;
		 int ernum=0;
		 for(int i =0;i<title.length;i++){
			 String st=title[i];					 
			 irpRandars= irpRandarService.querydoc(st);
			 for(int y=0;y<irpRandars.size();y++){				 
				 sid = irpRandars.get(y).getSid();
				 tit= irpRandars.get(y).getDoctitle();
				 htmcon=irpRandars.get(y).getDoccontent();
				 if(irpRandars.get(y).getSatus()==null||irpRandars.get(y).getSatus()==1){
					 boolean isClient = false;
					 Long _toChannelid = null;
					 IrpDocumentWithBLOBs doc = new IrpDocumentWithBLOBs();
					 Long docid = TableIdUtil.getNextId(IrpDocumentWithBLOBs.TABLE_NAME);// 他的主键值
					 doc.setDocid(docid);
					 doc.setDockeywords("   ");
					 doc.setSiteid(sitid);
					 doc.setChannelid(chanid);
					 doc.setDoctitle(tit);
					 doc.setDochtmlcon(htmcon);
					 nCount = irpDocumentService.addDocument(doc, null,
								isClient, _toChannelid, true, null,
								IrpDocument.DOCTYPE_DOCUMENT);
					 if(nCount>0){
						 
						ds= irpRandarService.ups(sid);
						if(ds>0){
							num++;
						}
					 } 
				 }else{
					 ernum++;
				 }
				
			 }
		 }
		 ActionUtil.writer(String.valueOf(num+","+ernum));
	 }
	 
	 
	 public void delRandar(){
		 int nCount = 0;
		 int ds=0;
		 String sid="";
		 String[] title =stitle.split(",");
		 int num=0;
		 for(int i =0;i<title.length;i++){
			 String st=title[i];					 
			 nCount= irpRandarService.delRandar(st);
			 if(nCount>0){					 
					num++;				
			}
		 }
		 ActionUtil.writer(String.valueOf(num));
	 }
	 
	 public String questRandar(){
		 try {
			sid = new String(sid.getBytes("iso-8859-1"),"UTF-8")  ;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		 irpRandars= irpRandarService.querydoc(sid);
		 for(int i=0;i<irpRandars.size();i++){
		 stitle=irpRandars.get(i).getDoctitle();

		 }
		 return SUCCESS;
	 }
	 private String doct;
	 
	 public String getDoct() {
		return doct;
	}

	public void setDoct(String doct) {
		this.doct = doct;
	}
	private IrpRandar irps;
	private List<TableType> tableListResoure;
	private List<TableType> tableListDestin;
	private String columnString;
	public String getColumnString() {
		return columnString;
	}

	public void setColumnString(String columnString) {
		this.columnString = columnString;
	}

	public List<TableType> getTableListResoure() {
		return tableListResoure;
	}

	public void setTableListResoure(List<TableType> tableListResoure) {
		this.tableListResoure = tableListResoure;
	}

	public List<TableType> getTableListDestin() {
		return tableListDestin;
	}

	public void setTableListDestin(List<TableType> tableListDestin) {
		this.tableListDestin = tableListDestin;
	}

	public IrpRandar getIrps() {
		return irps;
	}

	public void setIrps(IrpRandar irps) {
		this.irps = irps;
	}

	public String descRandar(){
		 try {
			sid = new String(sid.getBytes("iso-8859-1"),"UTF-8")  ;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 irpRandars= irpRandarService.querydoc(sid);

		 irps=irpRandars.get(0);



		 return SUCCESS;
	 }
	
/*	public void radarImport(){
		String msg = irpRandarService.importRadarData();
		ActionUtil.writer(msg);
	}*/
	
	/**
	 * 获取原表字段类型和目标字段类型
	 */
	public String toDataImport(){
		 tableListResoure=irpRandarService.selectResoureColumn();
		 tableListDestin=irpRandarService.selectDestinationColumn();
		return SUCCESS;
	}
	public void radarImport(){
		String msg = irpRandarService.importRadarData(columnString);
		ActionUtil.writer(msg);
	}
}
