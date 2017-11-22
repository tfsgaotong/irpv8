package com.tfs.irp.formcolumn.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.form.entity.IrpForm;
import com.tfs.irp.form.service.IrpFormService;
import com.tfs.irp.formcolumn.entity.IrpFormColumn;
import com.tfs.irp.formcolumn.service.IrpFormColumnService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.filter.LoginCheckFilter;


public class IrpFormColumnAction extends ActionSupport{
 private IrpFormColumnService irpFormColumnService;
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
private String columntablaname;

private String columntablenamecol;

private String columnname;

public String getColumntablaname() {
	return columntablaname;
}

public void setColumntablaname(String columntablaname) {
	this.columntablaname = columntablaname;
}

public String getColumntablenamecol() {
	return columntablenamecol;
}

public void setColumntablenamecol(String columntablenamecol) {
	this.columntablenamecol = columntablenamecol;
}

public String getColumnname() {
	return columnname;
}

public void setColumnname(String columnname) {
	this.columnname = columnname;
}
private Long columnid;

public Long getColumnid() {
	return columnid;
}

public void setColumnid(Long columnid) {
	this.columnid = columnid;
}
private IrpFormColumn irpFormColumn;


public IrpFormColumn getIrpFormColumn() {
	return irpFormColumn;
}

public void setIrpFormColumn(IrpFormColumn irpFormColumn) {
	this.irpFormColumn = irpFormColumn;
}
private List<String> displayvaluetype;
private List<String> inttype;

public List<String> getDisplayvaluetype() {
	return displayvaluetype;
}

public void setDisplayvaluetype(List<String> displayvaluetype) {
	this.displayvaluetype = displayvaluetype;
}
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

/**
 * 是否存在显示的同名
 */
public void isExitName(){
	int msg = 0;
	msg =this.irpFormColumnService.isExitColumnName(columnname, columntablaname);
	ActionUtil.writer(msg+"");
}
/**
 * 是否存在数据库表字段名
 */
public void isexitTableNamecol(){
	int msg = 0;
	columntablenamecol=columntablenamecol.toUpperCase();
	msg = this.irpFormColumnService.isExitTableNamecol(columntablenamecol, columntablaname);
	ActionUtil.writer(msg+"");
}
/**
 * 删除字段
 */
public void deleteColumn(){
	int msg = 0 ;
	msg =  this.irpFormColumnService.deleteColumn(columnid);
	ActionUtil.writer(msg+"");
}
/**
 * 获取实体
 */
public String selectIrpFromColumn(){
	HttpServletRequest request =ServletActionContext.getRequest();
	String type = request.getParameter("type");
	irpFormColumn =this.irpFormColumnService.selectIrpFromColumnById(columnid);
	inttype =this.irpFormColumnService.intType();
	displayvaluetype =this.irpFormColumnService.displayValueType();
	stringtype = this.irpFormColumnService.stringType();
	datetype = this.irpFormColumnService.dateType();
	if(type!=null&&type.equals("1")){
		return "check";
	}else{
		return SUCCESS;
	}
}
/**
 * 更新实体
 */
public void updateIrpFromColumn(){
	String msg = "0";	
	irpFormColumn.setDisplaytypevalue(irpFormColumn.getDisplaytypevalue().trim().replace(",", ""));
	int res =this.irpFormColumnService.updateIrpFromColumnById(irpFormColumn);
	irpFormColumn =this.irpFormColumnService.selectIrpFromColumnById(irpFormColumn.getColumnid());
	
	if(res>0){
		msg = irpFormColumn.getColumnname();
	}
	ActionUtil.writer(msg+"");
}

/**
 * 更新数据库字段
 */
public void updateIrpFromColumnSome(){
	String msg = "0";	
	irpFormColumn.setDisplaytypevalue(irpFormColumn.getDisplaytypevalue().trim().replace(",", ""));
	int res =this.irpFormColumnService.updateIrpFromColumnById(irpFormColumn);
	irpFormColumn =this.irpFormColumnService.selectIrpFromColumnById(irpFormColumn.getColumnid());
	String formtablename=irpFormColumn.getColumntablaname();
	IrpForm irpForm=irpFormService.getFormByFormtablename(formtablename);
	columnname=irpFormColumn.getColumntablenamecol();
	String columntype=irpFormColumn.getColumntype();
	Integer columnlength=irpFormColumn.getColumnlongtext();
	StringBuffer sql = new StringBuffer("");
	if(irpForm.getIsexitindb()!=null&&irpForm.getIsexitindb()==10l){
		String name=LoginCheckFilter.PropertiesInfo();
		String ptype=null;
		if(name.equals("oracle")){	
			if(columntype!=null){
				if(columntype.equals(IrpFormColumn.OR_CLOB)){
					sql.append("ALTER TABLE "+formtablename+" MODIFY "+columnname+" "+columntype);
					if(irpFormColumn.getColumndefval()!=null&&!irpFormColumn.getColumndefval().equals("")){
						sql.append(" default '"+irpFormColumn.getColumndefval()+"'");
					}
				}else if(columntype.equals(IrpFormColumn.OR_INT)){
					ptype = IrpFormColumn.OR_INT;
					sql.append("ALTER TABLE "+formtablename+" MODIFY "+columnname+" "+ptype+"("+columnlength+")");
					if(irpFormColumn.getColumndefval()!=null&&!irpFormColumn.getColumndefval().equals("")){
						sql.append(" default "+irpFormColumn.getColumndefval()+"");
					}
				}else if(columntype.equals(IrpFormColumn.OR_STRING)){
					ptype = IrpFormColumn.OR_STRING;
					sql.append("ALTER TABLE "+formtablename+" MODIFY "+columnname+" "+ptype+"("+columnlength+")");
					if(irpFormColumn.getColumndefval()!=null&&!irpFormColumn.getColumndefval().equals("")){
						sql.append(" default '"+irpFormColumn.getColumndefval()+"'");
					}
				}else if(columntype.equals(IrpFormColumn.OR_DATE)){
					ptype = IrpFormColumn.OR_DATE;
					sql.append("ALTER TABLE "+formtablename+" MODIFY "+columnname+" "+ptype+"("+columnlength+")");
					
				}
			}
		}
		if(name.equals("mysql")){
			if(columntype!=null){
				 if(columntype.equals(IrpFormColumn.MY_INT)){
					ptype = IrpFormColumn.MY_INT;
					sql.append("ALTER TABLE "+formtablename+" MODIFY COLUMN  "+columnname+" "+ptype+"("+columnlength+")");
					if(irpFormColumn.getColumndefval()!=null&&!irpFormColumn.getColumndefval().equals("")){
						sql.append(" default "+irpFormColumn.getColumndefval());
					}
				 }else if(columntype.equals(IrpFormColumn.MY_STRING)||columntype.equals(IrpFormColumn.OR_STRING)){
					ptype = IrpFormColumn.MY_STRING;
					sql.append("ALTER TABLE "+formtablename+" MODIFY COLUMN  "+columnname+" "+ptype+"("+columnlength+")");
					if(irpFormColumn.getColumndefval()!=null&&!irpFormColumn.getColumndefval().equals("")){
						sql.append(" default '"+irpFormColumn.getColumndefval()+"'");
					}
				 }else if(columntype.equals(IrpFormColumn.MY_DATE)){
					ptype = IrpFormColumn.MY_DATE;
					if(irpFormColumn.getColumndefval()!=null&&!irpFormColumn.getColumndefval().equals("")){
						sql.append(" default '"+irpFormColumn.getColumndefval()+"'");
					}
					sql.append("ALTER TABLE "+formtablename+" MODIFY COLUMN  "+columnname+" "+ptype+"("+columnlength+")");
				}
			}						
		}
		if(name.equals("sqlserver")){
			if(columntype!=null){
				 if(columntype.equals(IrpFormColumn.SQL_INT)){
					ptype = IrpFormColumn.OR_INT;
					sql.append("ALTER TABLE "+formtablename+" ALTER COLUMN "+columnname+" "+ptype+"("+columnlength+")");
					if(irpFormColumn.getColumndefval()!=null&&!irpFormColumn.getColumndefval().equals("")){
						sql.append(" default "+irpFormColumn.getColumndefval());
					}
				 }else if(columntype.equals(IrpFormColumn.SQL_STRING)){
					ptype = IrpFormColumn.OR_STRING;
					sql.append("ALTER TABLE "+formtablename+" ALTER COLUMN "+columnname+" "+ptype+"("+columnlength+")");
					if(irpFormColumn.getColumndefval()!=null&&!irpFormColumn.getColumndefval().equals("")){
						sql.append(" default '"+irpFormColumn.getColumndefval()+"'");
					}
				 }else if(columntype.equals(IrpFormColumn.SQL_DATE)){
					ptype = IrpFormColumn.OR_DATE;
					sql.append("ALTER TABLE "+formtablename+" ALTER COLUMN "+columnname+" "+ptype+"("+columnlength+")");
					
				 }
			}							
		}
		//sql.append("; commit;");
		LogUtil logUtil=new LogUtil("FORM_DATABASE_UPDATE",irpForm);
		irpFormService.createtable(sql.toString());	
		logUtil.successLogs( "修改数据库["+logUtil.getLogUser()+"]成功");
	}
	if(res>0){
		msg = irpFormColumn.getColumnname();
	}
	ActionUtil.writer(msg+"");
}
/**
 * 是否存在显示的同名
 */
public void isEditName(){
	int msg = 0;
	msg =this.irpFormColumnService.isEditColumnName(columnid,columnname, columntablaname);
	ActionUtil.writer(msg+"");
}
/**
 * 是否存在数据库表字段名
 */
public void isEditTableNamecol(){
	int msg = 0;
	msg = this.irpFormColumnService.isEditTableNamecol(columnid,columntablenamecol, columntablaname);
	ActionUtil.writer(msg+"");
}
}
