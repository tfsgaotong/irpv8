package com.tfs.irp.formcolumn.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.channel.service.IrpChannelService;
import com.tfs.irp.formcolumn.dao.IrpFormColumnDAO;
import com.tfs.irp.formcolumn.entity.IrpFormColumn;
import com.tfs.irp.formcolumn.entity.IrpFormColumnExample;
import com.tfs.irp.formcolumn.service.IrpFormColumnService;
import com.tfs.irp.site.service.IrpSiteService;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;
import com.tfs.irp.util.filter.LoginCheckFilter;


public class IrpFormColumnServiceImpl implements IrpFormColumnService {
	
	private IrpFormColumnDAO irpFormColumnDAO;

	public IrpFormColumnDAO getIrpFormColumnDAO() {
		return irpFormColumnDAO;
	}

	public void setIrpFormColumnDAO(IrpFormColumnDAO irpFormColumnDAO) {
		this.irpFormColumnDAO = irpFormColumnDAO;
	}

	@Override
	public Long saveColumn(IrpFormColumn irpFormColumn) {
		Long msg=0l;
		  LogUtil logUtil=null;
		if(irpFormColumn.getColumnid()==null || irpFormColumn.getColumnid()==0){
			Long columnid = TableIdUtil.getNextId(IrpFormColumn.TABLE_NAME);
			irpFormColumn.setColumnid(columnid);
			Date date = new Date();
			irpFormColumn.setCrtime(date);
			irpFormColumn.setCruserid(LoginUtil.getLoginUserId());
			irpFormColumn.setColumnstatus(IrpFormColumn.NORMAL);
			String type=irpFormColumn.getColumntype();
			String name=LoginCheckFilter.PropertiesInfo();
			if(name.equals("oracle")){	
				if(type.equals("整型")){
					irpFormColumn.setColumntype(IrpFormColumn.OR_INT);
					}else if(type.equals("字符串")){
					irpFormColumn.setColumntype(IrpFormColumn.OR_STRING);
					}else if(type.equals("时间")){
					irpFormColumn.setColumntype(IrpFormColumn.OR_DATE);
					}else if(type.equals("大字段")){
				     irpFormColumn.setColumntype(IrpFormColumn.OR_CLOB);	
					}	
			}
			if(name.equals("mysql")){
				if(type.equals("整型")){
					irpFormColumn.setColumntype(IrpFormColumn.MY_INT);
					}else if(type.equals("字符串")){
					irpFormColumn.setColumntype(IrpFormColumn.MY_STRING);
					}else if(type.equals("时间")){
					irpFormColumn.setColumntype(IrpFormColumn.MY_DATE);
					}	
			}
			if(name.equals("sqlserver")){
				if(type.equals("整型")){
					irpFormColumn.setColumntype(IrpFormColumn.SQL_INT);
					}else if(type.equals("字符串")){
					irpFormColumn.setColumntype(IrpFormColumn.SQL_STRING);
					}else if(type.equals("时间")){
					irpFormColumn.setColumntype(IrpFormColumn.SQL_DATE);
					}					
			}
				try {
					logUtil=new LogUtil("FORMCOLUMN_ADD",irpFormColumn);
					this.irpFormColumnDAO.insertSelective(irpFormColumn);
					logUtil.successLogs( "为["+irpFormColumn.getColumntablaname()+"表]添加字段["+logUtil.getLogUser()+"]成功");
					msg=columnid;
				} catch (SQLException e) {
					e.printStackTrace();
					logUtil.errorLogs( "为["+irpFormColumn.getColumntablaname()+"表]添加字段["+logUtil.getLogUser()+"]失败",e);
				}
				}
		return msg;
		
	}


	@Override
	public int updateTablenameByIds(String ids, String formtablename) {
		int msg=0;
		if(formtablename!=""){
			String[] array =ids.split(",");
			 for(int j=0;j<array.length;j++)   
		        {           
		           
		          IrpFormColumn irpFormColumn=new IrpFormColumn();
		          irpFormColumn.setColumnid(Long.parseLong(array[j]));
		          irpFormColumn.setColumntablaname(formtablename);
		          try {
					this.irpFormColumnDAO.updateByPrimaryKeySelective(irpFormColumn);
					msg++;
				} catch (SQLException e) {
					e.printStackTrace();
				}
		        
		        	}
			
		}
		
		return msg;
	}
	@Override
	public int updateTablenameBytablenamens(String oldname, String formtablename) {
		int msg=0;
		if(formtablename!=""){
			
			IrpFormColumnExample example=new IrpFormColumnExample();
			example.createCriteria().andColumntablanameEqualTo(formtablename);
				IrpFormColumn irpFormColumn=new IrpFormColumn();
				
				irpFormColumn.setColumntablaname(oldname);
				try {
					this.irpFormColumnDAO.updateByExampleSelective(irpFormColumn, example);
					msg++;
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			
			
		}
		
		return msg;
	}

	@Override
	public int isExitColumnName(String _columnname, String _columntablaname) {
		int count =0;
		IrpFormColumnExample example = new IrpFormColumnExample();
		example.createCriteria().andColumnnameEqualTo(_columnname)
		.andColumntablanameEqualTo(_columntablaname);
		try {
			 count = this.irpFormColumnDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<IrpFormColumn> getListBytablename(String formtablename) {
		List<IrpFormColumn> list=new ArrayList<IrpFormColumn>();
		IrpFormColumnExample example=new IrpFormColumnExample();
		example.createCriteria().andColumntablanameEqualTo(formtablename);
		example.setOrderByClause("columnid asc");
		try {
			list=this.irpFormColumnDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int isExitTableNamecol(String columntablenamecol,
			String _columntablaname) {
		int count =0;
		IrpFormColumnExample example = new IrpFormColumnExample();
		example.createCriteria().andColumntablenamecolEqualTo(columntablenamecol)
		.andColumntablanameEqualTo(_columntablaname);
		try {
			 count = this.irpFormColumnDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int deleteColumn(Long _id) {
		int msg = 0;
		IrpFormColumn column=null;
		try {
			column = irpFormColumnDAO.selectByPrimaryKey(_id);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  LogUtil logUtil=new LogUtil("FORMCOLUMN_DEL",column);
		try {
			msg = this.irpFormColumnDAO.deleteByPrimaryKey(_id);
			logUtil.successLogs( "为["+column.getColumntablaname()+"]删除字段["+logUtil.getLogUser()+"]成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logUtil.errorLogs( "为["+column.getColumntablaname()+"]删除字段["+logUtil.getLogUser()+"]失败");
		}
		return msg;
	}

	@Override
	public IrpFormColumn selectIrpFromColumnById(Long _id) {
		IrpFormColumn column =null;
		try {
			column = this.irpFormColumnDAO.selectByPrimaryKey(_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		return column;
	}

	@Override
	public int updateIrpFromColumnById(IrpFormColumn irpFormColumn) {
		int msg = 0;
		LogUtil logUtil=new LogUtil("FORMCOLUMN_UPDATE",irpFormColumn);
		try {
			String type=irpFormColumn.getColumntype();
			String name=LoginCheckFilter.PropertiesInfo();
			if(name.equals("oracle")){	
				if(type.equals("整型")){
					irpFormColumn.setColumntype(IrpFormColumn.OR_INT);
					}else if(type.equals("字符串")){
					irpFormColumn.setColumntype(IrpFormColumn.OR_STRING);
					}else if(type.equals("时间")){
					irpFormColumn.setColumntype(IrpFormColumn.OR_DATE);
					}else if(type.equals("大字段")){
				     irpFormColumn.setColumntype(IrpFormColumn.OR_CLOB);	
					}	
			}
			if(name.equals("mysql")){
				if(type.equals("整型")){
					irpFormColumn.setColumntype(IrpFormColumn.MY_INT);
					}else if(type.equals("字符串")){
					irpFormColumn.setColumntype(IrpFormColumn.MY_STRING);
					}else if(type.equals("时间")){
					irpFormColumn.setColumntype(IrpFormColumn.MY_DATE);
					}	
			}
			if(name.equals("sqlserver")){
				if(type.equals("整型")){
					irpFormColumn.setColumntype(IrpFormColumn.SQL_INT);
					}else if(type.equals("字符串")){
					irpFormColumn.setColumntype(IrpFormColumn.SQL_STRING);
					}else if(type.equals("时间")){
					irpFormColumn.setColumntype(IrpFormColumn.SQL_DATE);
					}					
			}
			msg = this.irpFormColumnDAO.updateByPrimaryKeySelective(irpFormColumn);
			logUtil.successLogs( "为["+irpFormColumn.getColumntablaname()+"]修改字段["+logUtil.getLogUser()+"]成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logUtil.errorLogs( "为["+irpFormColumn.getColumntablaname()+"]修改字段["+logUtil.getLogUser()+"]失败");
		}
		return msg;
	}

	@Override
	public int isEditColumnName(Long _id, String _columnname,
			String _columntablaname) {
		int count =0;
		IrpFormColumnExample example = new IrpFormColumnExample();
		example.createCriteria().andColumnnameEqualTo(_columnname)
		.andColumntablanameEqualTo(_columntablaname)
		.andColumnidNotEqualTo(_id);
		try {
			 count = this.irpFormColumnDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int isEditTableNamecol(Long _id, String columntablenamecol,
			String _columntablaname) {
		int count =0;
		IrpFormColumnExample example = new IrpFormColumnExample();
		example.createCriteria().andColumntablenamecolEqualTo(columntablenamecol)
		.andColumntablanameEqualTo(_columntablaname)
		.andColumnidNotEqualTo(_id);
		try {
			 count = this.irpFormColumnDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public List<String> intType(){
		List<String> inttype = new ArrayList<String>();
		inttype.add(IrpFormColumn.MY_INT);
		inttype.add(IrpFormColumn.OR_INT);
		inttype.add(IrpFormColumn.SQL_INT);
		return inttype;
	}

	@Override
	public List<String> stringType() {
		List<String> stringtype = new ArrayList<String>();
		stringtype.add(IrpFormColumn.MY_STRING);
		stringtype.add(IrpFormColumn.OR_STRING);
		stringtype.add(IrpFormColumn.SQL_STRING);
		return stringtype;
	}

	@Override
	public List<String> dateType() {
		List<String> datetype = new ArrayList<String>();
		datetype.add(IrpFormColumn.MY_DATE);
		datetype.add(IrpFormColumn.OR_DATE);
		datetype.add(IrpFormColumn.SQL_DATE);
		return datetype;
	}
	private IrpSiteService irpSiteService;
	private IrpChannelService irpChannelService;
	
	public IrpSiteService getIrpSiteService() {
		return irpSiteService;
	}

	public void setIrpSiteService(IrpSiteService irpSiteService) {
		this.irpSiteService = irpSiteService;
	}

	public IrpChannelService getIrpChannelService() {
		return irpChannelService;
	}

	public void setIrpChannelService(IrpChannelService irpChannelService) {
		this.irpChannelService = irpChannelService;
	}

	@Override
	public String conversion(Map _map,List<IrpFormColumn> _irpFormColumn) {
		String strinfo = null;
		String strname = null;
		StringBuffer info = new StringBuffer();
		if(_irpFormColumn!=null){
			for(int i=0;i<_irpFormColumn.size();i++){
				strname = _irpFormColumn.get(i).getColumnname();
				String columndesc=_irpFormColumn.get(i).getColumndesc();
				int columnlength = _irpFormColumn.get(i).getColumnlongtext();
				String readonly=null;
				String missing=null;
				String validType=null;
				
				if(_irpFormColumn.get(i).getColumnisreadonly()==10){
					readonly="readonly='readonly' style='background: #F0F0F0;'";
				}
				if(_irpFormColumn.get(i).getColumnempty()==0){
					missing="required='true' missingMessage='"+columndesc+"'";
				}
				if(_irpFormColumn.get(i).getChecktype()!=null){
					if(_irpFormColumn.get(i).getChecktype().equals("checkage")){
						validType="validType='age'";
					}
					if(_irpFormColumn.get(i).getChecktype().equals("checknumber")){
						validType="validType='number'";
					}
					if(_irpFormColumn.get(i).getChecktype().equals("checkzip")){
						validType="validType='zip'";
					}
					if(_irpFormColumn.get(i).getChecktype().equals("strlen")){
						validType="validType='length[1,"+columnlength+"]'";
					}
					if(_irpFormColumn.get(i).getChecktype().equals("checkmobile")){
						validType="validType='phoneNum'";
					}
					if(_irpFormColumn.get(i).getChecktype().equals("checkidcard")){
						validType="validType='idcard'";
					}
					if(_irpFormColumn.get(i).getChecktype().equals("checkemail")){
						validType="validType='checkEmail'";
					}
				}
				
				if(_irpFormColumn.get(i).getDisplaytype().equals("text")){
					if(strname.equals("栏目编号")||strname.equals("文档编号")){
						strname="";
						String _value="";
						if(_map!=null){
							_value =(String) _map.get(_irpFormColumn.get(i).getColumntablenamecol());
						}
						strinfo ="<input name='"+_irpFormColumn.get(i).getColumntablenamecol()+"' "+readonly+" "+missing+" "+validType+" type='hidden'  value='"+_value+"' />";
						
					}else{
						String _value="";
						if(_irpFormColumn.get(i).getColumnname().equals("创建用户")){
							if(_map!=null){
								_value =(String) _map.get(_irpFormColumn.get(i).getColumntablenamecol());
							}else{
								_value=String.valueOf(LoginUtil.getLoginUserId());
							}
						}else{
							
							if(_map!=null){
								_value =(String) _map.get(_irpFormColumn.get(i).getColumntablenamecol());
							}
						}
						strinfo ="<input name='"+_irpFormColumn.get(i).getColumntablenamecol()+"' "+readonly+" "+missing+" "+validType+" type='text'  value='"+_value+"' />";
						
					}
				}else if(_irpFormColumn.get(i).getDisplaytype().equals("password")){
					String _value="";
					if(_map!=null){
						_value =(String) _map.get(_irpFormColumn.get(i).getColumntablenamecol());
					}
					strinfo ="<input name='"+_irpFormColumn.get(i).getColumntablenamecol()+"' type='password' value='"+_value+"'/>";
				}else if(_irpFormColumn.get(i).getDisplaytype().equals("radio")){
					String[] displayvalue=_irpFormColumn.get(i).getDisplaytypevalue().split("/");
					StringBuffer sb = new StringBuffer();
					String ischecked = "";
					for(int j=0;j<displayvalue.length;j++){
						if(_map!=null&&_map.get(_irpFormColumn.get(i).getColumntablenamecol()).equals(displayvalue[j])){
							ischecked ="checked";
						}else{
							ischecked="";
						}
						sb.append("<input name='"+_irpFormColumn.get(i).getColumntablenamecol()+"' type='radio'  value='"+displayvalue[j]+"'"+ischecked+"/>");
						sb.append(displayvalue[j]);
					}
					strinfo =sb.toString();
				}else if(_irpFormColumn.get(i).getDisplaytype().equals("checkbox")){
					String[] displayvalue=_irpFormColumn.get(i).getDisplaytypevalue().split("/");
					StringBuffer sb = new StringBuffer();
					String ischecked = "";
					for(int j=0;j<displayvalue.length;j++){
						if(_map!=null){
							String cvaluestr =(String) _map.get(_irpFormColumn.get(i).getColumntablenamecol()); 
						
								String[] cvalue=cvaluestr.split(",");
								
									if(Arrays.asList(cvalue).contains(displayvalue[j])){
										ischecked ="checked";
									}else{
										ischecked ="";
									}
								
								
											
						}
						sb.append("<input  type='checkbox' name='j_"+_irpFormColumn.get(i).getColumntablenamecol()+"' value='"+displayvalue[j]+"' onclick=\"setCheckValue(this,'"+_irpFormColumn.get(i).getColumntablenamecol()+"')\" "+ischecked+"/>");
						sb.append(displayvalue[j]);					
					}
					if(_map!=null){
						sb.append("<input name='"+_irpFormColumn.get(i).getColumntablenamecol()+"' type='hidden'  value='"+ _map.get(_irpFormColumn.get(i).getColumntablenamecol())+"' />");
					}else{
						sb.append("<input name='"+_irpFormColumn.get(i).getColumntablenamecol()+"' type='hidden'  value='' />");
					}
					strinfo =sb.toString();
				}else if(_irpFormColumn.get(i).getDisplaytype().equals("date")){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					Date date = new Date();
					String datevalue = "";
					/*if(_irpFormColumn.get(i).getColumnisreadonly()==10){
						readonly="readonly='readonly' style='background: #F0F0F0;'";
					}*/
					if(_map!=null){
						datevalue = (String) _map.get(_irpFormColumn.get(i).getColumntablenamecol());
					}else{
						datevalue = sdf.format(date);
					}
					strinfo ="<input name='"+_irpFormColumn.get(i).getColumntablenamecol()+"' "+readonly+" type='text'  class='easyui-datetimebox' showSeconds='false' editable='false' value='"+datevalue+"'/>";
				}else if(_irpFormColumn.get(i).getDisplaytype().equals("file")){
					String image="";
					String text = "";
					if(_map!=null){
						image = (String) _map.get("imgshow1");
						text = (String) _map.get("fileName");
					}
					strinfo ="<a  href='javascript:void(0);' onclick='saveAttacthedByLeave()'>选择文件</a><input type='hidden' name='jsonFile'/>" +
							"&nbsp;<span id='fileName'>"+text+"</span> <span id='imgshow1'>"+image+"</span>";
				}else if(_irpFormColumn.get(i).getDisplaytype().equals("textarea")){
					String _value="";
					if(_map!=null){
						_value =(String) _map.get(_irpFormColumn.get(i).getColumntablenamecol());
					}
					strinfo ="<textarea name='"+_irpFormColumn.get(i).getColumntablenamecol()+"' >"+_value+"</textarea>";
				}else if(_irpFormColumn.get(i).getDisplaytype().equals("list")){
					String[] displayvalue=new String[1000];
					if(_irpFormColumn.get(i).getColumnname().equals("文档编号")){
						StringBuffer sb = new StringBuffer();
						sb.append("<select id='selectid' name='"+_irpFormColumn.get(i).getColumntablenamecol()+"' style='width:140px;' >");
						strinfo =sb.toString();
						/*Long _channelid=11l;
						List<IrpChnlDocLink> irpdocumentlist=new ArrayList<IrpChnlDocLink>();
						irpdocumentlist=irpChnl_Doc_LinkService.listChannelByChannelidAndDocStatus(_channelid, 10l);
						String docname="";
						for(int j=0;j<irpdocumentlist.size();j++){
							docname=docname+irpdocumentlist.get(j).getDoctitle()+"/";
						}
						displayvalue=docname.split("/");*/
					}else{
						displayvalue=_irpFormColumn.get(i).getDisplaytypevalue().split("/");					
					//}
					StringBuffer sb = new StringBuffer();
					sb.append("<select  name='"+_irpFormColumn.get(i).getColumntablenamecol()+"' >");
					for(int j=0;j<displayvalue.length;j++){	
						if(_map!=null&&_map.get(_irpFormColumn.get(i).getColumntablenamecol()).equals(displayvalue[j])){
							sb.append("<option selected='selected' value='"+displayvalue[j]+"'>"+displayvalue[j]+"</option>" );
						}else{
							sb.append("<option value='"+displayvalue[j]+"'>"+displayvalue[j]+"</option>" );
						}
						
					}
					strinfo =sb.toString();
					}
				}else if(_irpFormColumn.get(i).getDisplaytype().equals("tree")){
					String _url = "";
					String _value ="";
					if(_map!=null){
						_url="url:\"../site/to_load_site_to_check_channel.action?siteid=2\",method:\"get\",onClick:function(rec){$.ajax({url: \"../form/findchannelid.action\",type: \"POST\",data: {channelId: rec.id},success: function (data) {data= eval(\"(\" +data+\")\");$(\"#selectid\").empty();var sel= document.getElementById(\"selectid\"); for(var i in data){var data_1=data[i];var docid=0;var doctitle=\"\";for(var j in data_1){if(j=\"docid\"){docid=data_1[j];}if(j=\"doctitle\"){doctitle=data_1[j];}}sel.options.add(new Option(doctitle,docid));}}, }); }" ;
						if(_map.get(_irpFormColumn.get(i).getColumntablenamecol()).equals("0")){
							String irpsitename = irpSiteService.findSiteName(2l);
							_value=irpsitename;
						}else{
							String channel =(String) _map.get(_irpFormColumn.get(i).getColumntablenamecol());
							IrpChannel irpChannel =new IrpChannel();
							irpChannel = irpChannelService.finChannelByChannelid(Long.parseLong(channel));
							_value=irpChannel.getChnlname();											
						}
					}else{
						_url="url:\"../site/to_load_site_to_check_channel.action?siteid=2\",method:\"get\",onClick:function(rec){$.ajax({url: \"../form/findchannelid.action\",type: \"POST\",data: {channelId: rec.id},success: function (data) {data= eval(\"(\" +data+\")\");$(\"#selectid\").empty();var sel= document.getElementById(\"selectid\"); for(var i in data){var data_1=data[i];var docid=0;var doctitle=\"\";for(var j in data_1){if(j=\"docid\"){docid=data_1[j];}if(j=\"doctitle\"){doctitle=data_1[j];}}sel.options.add(new Option(doctitle,docid));}}, }); }" ;
					}
					strinfo="<select name='"+_irpFormColumn.get(i).getColumntablenamecol()+"'  value='"+_value+"' class='easyui-combotree' data-options='"+_url+"' style='width:140px;' />";
				}/*else if(_irpFormColumn.get(i).getDisplaytype().equals("tree")){
					//String _url = "";
					String _value ="";
					_value=_irpFormColumn.get(i).getDisplaytypevalue();
					strinfo ="<input name='"+_irpFormColumn.get(i).getColumntablenamecol()+"' "+readonly+" "+missing+" "+validType+" type='text'  value='"+_value+"' />";
				}*/
				info.append(this.toPageStr(strname, strinfo));
			}
		}
		return info.toString();
	}
	@Override
	public String newconversion(Map _map,List<IrpFormColumn> _irpFormColumn) {
		String strinfo = null;
		String strname = null;
		StringBuffer info = new StringBuffer();
		if(_irpFormColumn!=null){
			for(int i=0;i<_irpFormColumn.size();i++){
				strname = _irpFormColumn.get(i).getColumnname();
				String columndesc=_irpFormColumn.get(i).getColumndesc();
				int columnlength = _irpFormColumn.get(i).getColumnlongtext();
				String readonly=null;
				String missing=null;
				String validType=null;
				
				if(_irpFormColumn.get(i).getColumnisreadonly()==10){
					readonly="readonly='readonly' style='background: #F0F0F0;'";
				}
				if(_irpFormColumn.get(i).getColumnempty()==0){
					missing="required='true' missingMessage='"+columndesc+"'";
				}
				if(_irpFormColumn.get(i).getChecktype().equals("checkage")){
					validType="validType='age'";
				}
				if(_irpFormColumn.get(i).getChecktype().equals("checknumber")){
					validType="validType='number'";
				}
				if(_irpFormColumn.get(i).getChecktype().equals("checkzip")){
					validType="validType='zip'";
				}
				if(_irpFormColumn.get(i).getChecktype().equals("strlen")){
					validType="validType='length[1,"+columnlength+"]'";
				}
				if(_irpFormColumn.get(i).getChecktype().equals("checkmobile")){
					validType="validType='phoneNum'";
				}
				if(_irpFormColumn.get(i).getChecktype().equals("checkidcard")){
					validType="validType='idcard'";
				}
				if(_irpFormColumn.get(i).getChecktype().equals("checkemail")){
					validType="validType='checkEmail'";
				}
				if(_irpFormColumn.get(i).getDisplaytype().equals("text")){
					if(strname.equals("栏目编号")||strname.equals("文档编号")){
						strname="";
						String _value="<s:property value='"+_irpFormColumn.get(i).getColumntablenamecol()+"'/>";
						strinfo ="<input name='"+_irpFormColumn.get(i).getColumntablenamecol()+"' "+readonly+" "+missing+" "+validType+" type='hidden'  value='"+_value+"' />";
						
					}else{
						String _value="<s:property value='"+_irpFormColumn.get(i).getColumntablenamecol()+"'/>";
						/*if(_irpFormColumn.get(i).getColumnname().equals("创建用户")){
							if(_map!=null){
								_value =(String) _map.get(_irpFormColumn.get(i).getColumntablenamecol());
							}else{
								_value=String.valueOf(LoginUtil.getLoginUserId());
							}
						}else{
							
							if(_map!=null){
								_value =(String) _map.get(_irpFormColumn.get(i).getColumntablenamecol());
							}
						}*/
						strinfo ="<input name='"+_irpFormColumn.get(i).getColumntablenamecol()+"' "+readonly+" "+missing+" "+validType+" type='text'  value='"+_value+"' />";
						
					}
				}else if(_irpFormColumn.get(i).getDisplaytype().equals("password")){
					String _value="<s:property value='"+_irpFormColumn.get(i).getColumntablenamecol()+"'/>";
					strinfo ="<input name='"+_irpFormColumn.get(i).getColumntablenamecol()+"' type='password' value='"+_value+"'/>";
				}else if(_irpFormColumn.get(i).getDisplaytype().equals("radio")){
					String[] displayvalue=_irpFormColumn.get(i).getDisplaytypevalue().split("/");
					StringBuffer sb = new StringBuffer();
					String ischecked = "";
					for(int j=0;j<displayvalue.length;j++){
						if(_map!=null&&_map.get(_irpFormColumn.get(i).getColumntablenamecol()).equals(displayvalue[j])){
							ischecked ="checked";
						}else{
							ischecked="";
						}
						sb.append("<input name='"+_irpFormColumn.get(i).getColumntablenamecol()+"' type='radio'  value='"+displayvalue[j]+"'"+ischecked+"/>");
						sb.append(displayvalue[j]);
					}
					strinfo =sb.toString();
				}else if(_irpFormColumn.get(i).getDisplaytype().equals("checkbox")){
					String[] displayvalue=_irpFormColumn.get(i).getDisplaytypevalue().split("/");
					StringBuffer sb = new StringBuffer();
					String ischecked = "";
					for(int j=0;j<displayvalue.length;j++){
						if(_map!=null){
							String cvaluestr =(String) _map.get(_irpFormColumn.get(i).getColumntablenamecol()); 
						
								String[] cvalue=cvaluestr.split(",");
								
									if(Arrays.asList(cvalue).contains(displayvalue[j])){
										ischecked ="checked";
									}else{
										ischecked ="";
									}
								
								
											
						}
						sb.append("<input  type='checkbox' name='j_"+_irpFormColumn.get(i).getColumntablenamecol()+"' value='"+displayvalue[j]+"' onclick=\"setCheckValue(this,'"+_irpFormColumn.get(i).getColumntablenamecol()+"')\" "+ischecked+"/>");
						sb.append(displayvalue[j]);					
					}
					String _value="<s:property value='"+_irpFormColumn.get(i).getColumntablenamecol()+"'/>";
					sb.append("<input name='"+_irpFormColumn.get(i).getColumntablenamecol()+"' type='hidden'  value='"+_value+"' />");
					strinfo =sb.toString();
				}else if(_irpFormColumn.get(i).getDisplaytype().equals("date")){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					Date date = new Date();
					String datevalue = "";
					/*if(_irpFormColumn.get(i).getColumnisreadonly()==10){
						readonly="readonly='readonly' style='background: #F0F0F0;'";
					}*/
					/*if(_map!=null){
						datevalue = (String) _map.get(_irpFormColumn.get(i).getColumntablenamecol());
					}else{
						datevalue = sdf.format(date);
					}*/
					String _value="<s:property value='"+_irpFormColumn.get(i).getColumntablenamecol()+"'/>";
					strinfo ="<input name='"+_irpFormColumn.get(i).getColumntablenamecol()+"' "+readonly+" type='text'  class='easyui-datetimebox' showSeconds='false' editable='false' value='"+_value+"'/>";
				}else if(_irpFormColumn.get(i).getDisplaytype().equals("file")){
					String image="";
					String text = "";
					if(_map!=null){
						image = (String) _map.get("imgshow1");
						text = (String) _map.get("fileName");
					}
					strinfo ="<a  href='javascript:void(0);' onclick='saveAttacthedByLeave()'>选择文件</a><input type='hidden' name='jsonFile'/>" +
							"&nbsp;<span id='fileName'>"+text+"</span> <span id='imgshow1'>"+image+"</span>";
				}else if(_irpFormColumn.get(i).getDisplaytype().equals("textarea")){
					String _value="<s:property value='"+_irpFormColumn.get(i).getColumntablenamecol()+"'/>";
					strinfo ="<textarea name='"+_irpFormColumn.get(i).getColumntablenamecol()+"' >"+_value+"</textarea>";
				}else if(_irpFormColumn.get(i).getDisplaytype().equals("list")){
					String[] displayvalue=new String[1000];
					if(_irpFormColumn.get(i).getColumnname().equals("文档编号")){
						StringBuffer sb = new StringBuffer();
						sb.append("<select id='selectid' name='"+_irpFormColumn.get(i).getColumntablenamecol()+"' style='width:140px;' >");
						strinfo =sb.toString();
					}else{
						displayvalue=_irpFormColumn.get(i).getDisplaytypevalue().split("/");					
					//}
					StringBuffer sb = new StringBuffer();
					sb.append("<select  name='"+_irpFormColumn.get(i).getColumntablenamecol()+"' >");
					for(int j=0;j<displayvalue.length;j++){	
						if(_map!=null&&_map.get(_irpFormColumn.get(i).getColumntablenamecol()).equals(displayvalue[j])){
							sb.append("<option selected='selected' value='"+displayvalue[j]+"'>"+displayvalue[j]+"</option>" );
						}else{
							sb.append("<option value='"+displayvalue[j]+"'>"+displayvalue[j]+"</option>" );
						}
						
					}
					strinfo =sb.toString();
					}
				}else if(_irpFormColumn.get(i).getDisplaytype().equals("tree")){
					String _url = "";
					String _value="<s:property value='"+_irpFormColumn.get(i).getColumntablenamecol()+"'/>";
					if(_map!=null){
						_url="url:\"../site/to_load_site_to_check_channel.action?siteid=2\",method:\"get\",onClick:function(rec){$.ajax({url: \"../form/findchannelid.action\",type: \"POST\",data: {channelId: rec.id},success: function (data) {data= eval(\"(\" +data+\")\");$(\"#selectid\").empty();var sel= document.getElementById(\"selectid\"); for(var i in data){var data_1=data[i];var docid=0;var doctitle=\"\";for(var j in data_1){if(j=\"docid\"){docid=data_1[j];}if(j=\"doctitle\"){doctitle=data_1[j];}}sel.options.add(new Option(doctitle,docid));}}, }); }" ;
						/*if(_map.get(_irpFormColumn.get(i).getColumntablenamecol()).equals("0")){
							String irpsitename = irpSiteService.findSiteName(2l);
							_value=irpsitename;
						}else{
							String channel =(String) _map.get(_irpFormColumn.get(i).getColumntablenamecol());
							IrpChannel irpChannel =new IrpChannel();
							irpChannel = irpChannelService.finChannelByChannelid(Long.parseLong(channel));
							_value=irpChannel.getChnlname();											
						}*/
					}else{
						_url="url:\"../site/to_load_site_to_check_channel.action?siteid=2\",method:\"get\",onClick:function(rec){$.ajax({url: \"../form/findchannelid.action\",type: \"POST\",data: {channelId: rec.id},success: function (data) {data= eval(\"(\" +data+\")\");$(\"#selectid\").empty();var sel= document.getElementById(\"selectid\"); for(var i in data){var data_1=data[i];var docid=0;var doctitle=\"\";for(var j in data_1){if(j=\"docid\"){docid=data_1[j];}if(j=\"doctitle\"){doctitle=data_1[j];}}sel.options.add(new Option(doctitle,docid));}}, }); }" ;
					}
					strinfo="<select name='"+_irpFormColumn.get(i).getColumntablenamecol()+"'  value='"+_value+"' class='easyui-combotree' data-options='"+_url+"' style='width:140px;' />";
				}
				info.append(this.toPageStr(strname, strinfo));
			}
		}
		return info.toString();
	}
	public String toPageStr(String _columnname,String _showstr){
		String str= "<tr><td align='right' bgcolor='#f5fafe' class='main_bleft'>"
				+_columnname+
				"</td><td bgcolor='#FFFFFF' class='main_bright'>"
				+_showstr+
				"</td></tr>";
		return str;		
	}
	@Override
	public List<String> displayValueType() {
		List<String> datetype = new ArrayList<String>();
		datetype.add("radio");
		datetype.add("checkbox");
		datetype.add("list");
		
		return datetype;
		}

	@Override
	public void saveColumnForUpdate(IrpFormColumn irpFormColumn) {
		
			try {
				this.irpFormColumnDAO.insertSelective(irpFormColumn);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

			
		
	}
	@Override
	public Long addSystemColumn(String formtablename,String columnname, String columntablenamecol,
			String columntype, int columnlongtext,String columndefval, int columnstatus,
			String displaytype,int columninliststatus,int columnisreadonly,int columnempty,String checktype) {
		IrpFormColumn irpformcolumn=new IrpFormColumn();
	 	irpformcolumn.setColumntablaname(formtablename);
		irpformcolumn.setColumnname(columnname);
		irpformcolumn.setColumntablenamecol(columntablenamecol);
		irpformcolumn.setColumntype(columntype);
		irpformcolumn.setColumnlongtext(columnlongtext);
		irpformcolumn.setColumndefval(columndefval);
		irpformcolumn.setColumnstatus(columnstatus);
		irpformcolumn.setDisplaytype(displaytype);
		irpformcolumn.setColumninliststatus(columninliststatus);
		irpformcolumn.setColumnisreadonly(columnisreadonly);
		irpformcolumn.setColumnempty(columnempty);
		irpformcolumn.setChecktype(checktype);
		return saveColumn(irpformcolumn);
	}

	@Override
	public List<IrpFormColumn> getListBytablename(PageUtil pageUtil,String formtablename) {
		List<IrpFormColumn> list=new ArrayList<IrpFormColumn>();
		IrpFormColumnExample example=new IrpFormColumnExample();
		example.createCriteria().andColumntablanameEqualTo(formtablename);
		example.setOrderByClause("columnid asc");
		try {
			list=this.irpFormColumnDAO.selectByExample(pageUtil,example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<IrpFormColumn> getListBytablename(int columninliststatus,String formtablename) {
		List<IrpFormColumn> list=new ArrayList<IrpFormColumn>();
		IrpFormColumnExample example=new IrpFormColumnExample();
		example.createCriteria().andColumntablanameEqualTo(formtablename)
		.andColumninliststatusEqualTo(columninliststatus);
		example.setOrderByClause("columnid asc");
		try {
			list=this.irpFormColumnDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
