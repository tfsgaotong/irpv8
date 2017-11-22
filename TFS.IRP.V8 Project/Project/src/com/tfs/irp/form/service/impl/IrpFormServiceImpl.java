package com.tfs.irp.form.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import oracle.net.aso.n;

import org.apache.commons.beanutils.BeanMap;
import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.entity.IrpAttachedInfo;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.attachedtype.entity.IrpAttachedType;
import com.tfs.irp.attachedtype.service.IrpAttachedTypeService;
import com.tfs.irp.form.dao.IrpFormDAO;
import com.tfs.irp.form.entity.IrpForm;
import com.tfs.irp.form.entity.IrpFormExample;
import com.tfs.irp.form.service.IrpFormService;
import com.tfs.irp.formcolumn.dao.IrpFormColumnDAO;
import com.tfs.irp.formcolumn.entity.IrpFormColumn;
import com.tfs.irp.formcolumn.entity.IrpFormColumnExample;
import com.tfs.irp.util.FileUtil;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysFileUtil;
import com.tfs.irp.util.filter.LoginCheckFilter;

public class IrpFormServiceImpl implements IrpFormService{
	
	private IrpFormColumnDAO irpFormColumnDAO;
	public IrpFormColumnDAO getIrpFormColumnDAO() {
		return irpFormColumnDAO;
	}

	public void setIrpFormColumnDAO(IrpFormColumnDAO irpFormColumnDAO) {
		this.irpFormColumnDAO = irpFormColumnDAO;
	}

	private IrpFormDAO irpFormDAO;

	public IrpFormDAO getIrpFormDAO() {
		return irpFormDAO;
	}

	public void setIrpFormDAO(IrpFormDAO irpFormDAO) {
		this.irpFormDAO = irpFormDAO;
	}
	private IrpAttachedTypeService irpAttachedTypeService;
	
	
	public IrpAttachedTypeService getIrpAttachedTypeService() {
		return irpAttachedTypeService;
	}

	public void setIrpAttachedTypeService(
			IrpAttachedTypeService irpAttachedTypeService) {
		this.irpAttachedTypeService = irpAttachedTypeService;
	}
	
	private IrpAttachedService irpAttachedService;
	
	
	public IrpAttachedService getIrpAttachedService() {
		return irpAttachedService;
	}

	public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
		this.irpAttachedService = irpAttachedService;
	}

	@Override
	public List<IrpForm> getAllNormal(PageUtil _pageUtil,String _searchType,
			String _searchWord, Integer _formType,String _orderField,String _orderBy) {
		IrpFormExample example=new IrpFormExample();
		if(_searchWord != null && _searchType != null && !_searchWord.equals("") && !_searchType.equals("")){
			Integer status = IrpForm.NOPUBLISH;
			if("all".equals(_searchType)){
				example.or(example.createCriteria().andFormnameLike("%"+_searchWord+"%").andFormisdelEqualTo(_formType));
				example.or(example.createCriteria().andFormdescLike("%"+_searchWord+"%").andFormisdelEqualTo(_formType));
				example.or(example.createCriteria().andFormtablenameLike("%"+_searchWord+"%").andFormisdelEqualTo(_formType));
			} else if("formname".equals(_searchType)){
				example.createCriteria().andFormnameLike("%"+_searchWord+"%").andFormisdelEqualTo(_formType);				
			} else if("formdesc".equals(_searchType)){
				example.createCriteria().andFormdescLike("%"+_searchWord+"%").andFormisdelEqualTo(_formType);
			}else if("formtablename".equals(_searchType)){
				example.createCriteria().andFormtablenameLike("%"+_searchWord+"%").andFormisdelEqualTo(_formType);
			}else if("formstatus".equals(_searchType)){
				if(_searchWord.contains("已发布")){
					status =IrpForm.PUBLISH ;
				}
				example.createCriteria().andFormstatusEqualTo(status).andFormisdelEqualTo(_formType);
			}
		}else{
			example.createCriteria().andFormisdelEqualTo(_formType);
		}
		if(_orderField!=null&&_orderBy!=null&&!_orderField.equals("")&&!_orderBy.equals("")){
			example.setOrderByClause(_orderField+" "+_orderBy);
		}else{
			example.setOrderByClause("crtime desc");
		}
		List<IrpForm>  irpForms=null;
		try {
			irpForms=this.irpFormDAO.selectByExampleWithoutBLOBsByPage(_pageUtil,example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return irpForms;
	}

	@Override
	public int countBySearchtypeAndWords(String _searchType,
			String _searchWord, Integer _formType) {
		int count = 0;
		IrpFormExample example = new IrpFormExample();
		if(_searchWord != null && _searchType != null && !_searchWord.equals("") && !_searchType.equals("")){
			Integer status = IrpForm.NOPUBLISH;
			if("all".equals(_searchType)){
				example.or(example.createCriteria().andFormnameLike("%"+_searchWord+"%").andFormisdelEqualTo(_formType));
				example.or(example.createCriteria().andFormdescLike("%"+_searchWord+"%").andFormisdelEqualTo(_formType));
				example.or(example.createCriteria().andFormtablenameLike("%"+_searchWord+"%").andFormisdelEqualTo(_formType));
			} else if("formname".equals(_searchType)){
				example.createCriteria().andFormnameLike("%"+_searchWord+"%").andFormisdelEqualTo(_formType);				
			} else if("formdesc".equals(_searchType)){
				example.createCriteria().andFormdescLike("%"+_searchWord+"%").andFormisdelEqualTo(_formType);
			}else if("formtablename".equals(_searchType)){
				example.createCriteria().andFormtablenameLike("%"+_searchWord+"%").andFormisdelEqualTo(_formType);
			}else if("formstatus".equals(_searchType)){
				if(_searchWord.contains("已发布")){
					status =IrpForm.PUBLISH ;
				}
				example.createCriteria().andFormstatusEqualTo(status).andFormisdelEqualTo(_formType);
			}
		}else{
			example.createCriteria().andFormisdelEqualTo(_formType);
		}
		try {
			count = irpFormDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int saveForm(IrpForm irpForm) {
		int  msg=0;
        LogUtil logUtil=new LogUtil("FORM_ADD",irpForm);
			try {
				this.irpFormDAO.insertSelective(irpForm);
				msg=1;
				logUtil.successLogs( "添加表单["+logUtil.getLogUser()+"]成功");
			} catch (SQLException e) {
				e.printStackTrace();
				logUtil.errorLogs( "添加表单["+logUtil.getLogUser()+"]失败",e);
			}
			return msg;
				
	}

	@Override
	public int createtable(String sql) {
		int msg=0;		
			msg=this.irpFormDAO.createSql(sql);
		
		return msg;
	}

	@Override
	public boolean isExitFormName(String formname) {
		boolean msg = true;
		int count =0;
		IrpFormExample example = new IrpFormExample();
		example.createCriteria().andFormnameEqualTo(formname);
		try {
			 count = this.irpFormDAO.countByExample(example);
			 if(count>0){
				 msg = false; 
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public boolean isExitFormTableName(String formtablename) {
		boolean msg = true;
		int count =0;
		 count=this.irpFormDAO.selectTableName(formtablename);
		 if(count>0){
			 msg = false; 
		 }
		return msg;
	}

	@Override
	public int deleteToRecover(String columnids) {
		int msg=0;
		LogUtil logUtil=null;
		String[] array =columnids.split(",");
		 for(int j=0;j<array.length;j++)   
	        {           
	           
	          IrpForm irpForm=new IrpForm();
	          irpForm.setFormid(Long.parseLong(array[j]));
	          irpForm.setFormisdel(IrpForm.CANRECOVER);
	          try {
				this.irpFormDAO.updateByPrimaryKeySelective(irpForm);
				irpForm=getFormById(Long.parseLong(array[j]));
				 logUtil=new LogUtil("FORM_DEL",irpForm);
				logUtil.successLogs( "删除表单到回收站["+irpForm.getFormname()+"]["+logUtil.getLogUser()+"]成功");
				msg++;
			} catch (SQLException e) {
				e.printStackTrace();
				logUtil.errorLogs( "删除表单到回收站["+irpForm.getFormname()+"]["+logUtil.getLogUser()+"]失败");
			}
	        
	        	}
		return msg;
	}

	@Override
	public int recoverForm(String columnids) {
		int msg=0;
		String[] array =columnids.split(",");
		  LogUtil logUtil=null;
		 for(int j=0;j<array.length;j++)   
	        {           
	           
	          IrpForm irpForm=new IrpForm();
	          irpForm.setFormid(Long.parseLong(array[j]));
	          irpForm.setFormisdel(IrpForm.NORMAL);
	          try {
				this.irpFormDAO.updateByPrimaryKeySelective(irpForm);
				irpForm=getFormById(Long.parseLong(array[j]));
				 logUtil=new LogUtil("FORM_RECOVERY",irpForm);
				logUtil.successLogs( "恢复表单["+irpForm.getFormname()+"]["+logUtil.getLogUser()+"]成功");
				msg++;
			} catch (SQLException e) {
				e.printStackTrace();
				logUtil.errorLogs( "恢复表单["+irpForm.getFormname()+"]["+logUtil.getLogUser()+"]失败");
	        
	        	}
	        }
		return msg;
	}

	@Override
	public int realDelete(String columnids) {
		int msg=0;
		String[] array =columnids.split(",");
		 for(int j=0;j<array.length;j++)   
	        {           
			
		     try {
		   IrpForm irpForm=  this.irpFormDAO.selectByPrimaryKey(Long.parseLong(array[j]));
		   
			 //检测原来的表是否存在
	       	 boolean isexist=this.isExitFormTableName(irpForm.getFormtablename());
	       	 if(!isexist){
	       		 //检测这张表是否有数据
	       		StringBuffer sql = new StringBuffer(""); 
	    		sql.append("select COUNT(*) from  "+irpForm.getFormtablename());
	       		 int count=this.irpFormDAO.countBySql(sql.toString());
	       		 if(count>0){
	       			 msg=-2;//数据库中存在数据
	       		
	       		 }else{
	       			 LogUtil logUtil=new LogUtil("FORM_DEL_GARBAGE",irpForm);
	       			 int ss=this.realeTableByTableName(irpForm.getFormtablename());
	       			 if(ss>0){
	       				logUtil.successLogs( "删除回收站表单["+logUtil.getLogUser()+"]成功");
	       			  //先删除字段表
	     	        	IrpFormColumnExample example=new IrpFormColumnExample();
	     	        	example.createCriteria().andColumntablanameEqualTo(irpForm.getFormtablename());
	     	        	this.irpFormColumnDAO.deleteByExample(example);
	     	        	  //删除表单
	     				this.irpFormDAO.deleteByPrimaryKey(Long.parseLong(array[j]));
	     				
	       			 }
	       		 }
	       	 }else{
	       		 msg=-1;
	       	 }
		   
				        	
	        	
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	        
	        	}
		return msg;
	}

	@Override
	public IrpForm getFormById(long parseLong) {
		IrpForm irpForm=new IrpForm();
		try {
			irpForm=this.irpFormDAO.selectByPrimaryKey(parseLong);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return irpForm;
	}

	@Override
	public List<Object> selectFormInfo(PageUtil _pageUtil,String _searchType,String _searchWord,String tablename,String _orderField,String _orderBy,Long id) {
		List<Object> list = null;
		StringBuffer sql = new StringBuffer();
		StringBuffer col = new StringBuffer();
		IrpForm irpForm=null;
		IrpFormColumnExample example=new IrpFormColumnExample();
		example.createCriteria().andColumntablanameEqualTo(tablename);
		example.setOrderByClause("crtime asc");
		try {
			List<IrpFormColumn> listcol=this.irpFormColumnDAO.selectByExample(example);
			col.append("forminfoid,");
			for(int i=0;i<listcol.size();i++){
				if(listcol.get(i).getColumntype().equals(IrpFormColumn.OR_DATE)){
					col.append("to_char("+listcol.get(i).getColumntablenamecol()+",'yyyy-mm-dd HH:mm')");
				}else{
					col.append(listcol.get(i).getColumntablenamecol());
				}				
				if(listcol.size()-1!=i){
					col.append(",");
				}
			}
			if(tablename!=null&&!tablename.equals("")){
				irpForm=getFormByFormtablename(tablename);
				String name=LoginCheckFilter.PropertiesInfo();
				if(name.equals("oracle")){	
					sql.append("select "+col.toString()+" from ( select A.*,rownum rn from (");
					sql.append("select * from "+tablename);
					if(id!=null){
						sql.append(" where channeiid="+id);
						if(_searchType!=null&&_searchWord!=null&&!_searchWord.equals("") && !_searchType.equals("")){
							sql.append(" and "+_searchType+" like '%"+_searchWord+"%'");
						}
					}else{
						if(_searchType!=null&&_searchWord!=null&&!_searchWord.equals("") && !_searchType.equals("")){
							sql.append(" where "+_searchType+" like '%"+_searchWord+"%'");
						}
					}
					if(_orderField!=null&&_orderBy!=null&&!_orderField.equals("") && !_orderBy.equals("")){
						sql.append(" order by "+_orderField+" "+_orderBy);
					}else{
						sql.append(" order by forminfoid desc");
					}
					int begin = _pageUtil.getPageNum();
					int end =_pageUtil.getPageSize();
					if(_pageUtil.getPageIndex()!=0){
						begin = (_pageUtil.getPageNum()-1)*end+1;
						end = _pageUtil.getPageNum()*_pageUtil.getPageSize();
					}
					sql.append(") A where rownum <= "+end+") where rn >="+begin);					
				}
				if(name.equals("mysql")){
					sql.append("select * from "+tablename);
					if(_searchType!=null&&_searchWord!=null&&!_searchWord.equals("") && !_searchType.equals("")){
						sql.append(" where "+_searchType+" like '%"+_searchWord+"%'");
					}
					if(_orderField!=null&&_orderBy!=null&&!_orderField.equals("") && !_orderBy.equals("")){
						sql.append(" order by "+_orderField+" "+_orderBy);
					}else{
						sql.append(" order by forminfoid desc");
					}
					int begin = _pageUtil.getPageNum();
					int end =_pageUtil.getPageSize();
					if(_pageUtil.getPageIndex()!=0){
						begin = (_pageUtil.getPageNum()-1)*end;
					}
					sql.append("LIMIT "+begin+","+end);
				}
				if(name.equals("sqlserver")){
												
				}
				list = irpFormDAO.selectFromInfo(sql.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return list;
	}
	@Override
	public List<Object> selectFormInfo1(PageUtil _pageUtil,String _searchType,String _searchWord,String tablename,String _orderField,String _orderBy) {
		List<Object> list = null;
		StringBuffer sql = new StringBuffer();
		StringBuffer col = new StringBuffer();
		IrpFormColumnExample example=new IrpFormColumnExample();
		example.createCriteria().andColumntablanameEqualTo(tablename);
		example.setOrderByClause("crtime asc");
		String columnname=null;
		try {
			List<IrpFormColumn> listcol=this.irpFormColumnDAO.selectByExample(example);
			col.append("forminfoid,");
			for(int i=0;i<listcol.size();i++){
				if(listcol.get(i).getColumntype().equals(IrpFormColumn.OR_DATE)){
					columnname="n."+listcol.get(i).getColumntablenamecol();
					col.append("to_char("+listcol.get(i).getColumntablenamecol()+",'yyyy-mm-dd HH:mm')");
				}else{
					columnname="n."+listcol.get(i).getColumntablenamecol();
					col.append(columnname);
				}				
				if(listcol.size()-1!=i){
					col.append(",");
				}
			}
			//col.append(",c.DOCSTATUS");
			if(tablename!=null&&!tablename.equals("")){
				String name=LoginCheckFilter.PropertiesInfo();
				if(name.equals("oracle")){	
					sql.append("select "+col.toString()+" from ( select A.*,rownum rn from (");
					sql.append("select * from "+tablename);
					if(_searchType!=null&&_searchWord!=null&&!_searchWord.equals("") && !_searchType.equals("")){
						sql.append(" where "+_searchType+" like '%"+_searchWord+"%'");
					}
					if(_orderField!=null&&_orderBy!=null&&!_orderField.equals("") && !_orderBy.equals("")){
						sql.append(" order by "+_orderField+" "+_orderBy);
					}else{
						sql.append(" order by forminfoid desc");
					}
					int begin = _pageUtil.getPageNum();
					int end =_pageUtil.getPageSize();
					if(_pageUtil.getPageIndex()!=0){
						begin = (_pageUtil.getPageNum()-1)*end+1;
						end = _pageUtil.getPageNum()*_pageUtil.getPageSize();
					}
					sql.append(") A where rownum <= "+end+") n LEFT JOIN IRP_CHNL_DOC_LINK c ON n.DOCID=c.DOCID where rn >="+begin+" and DOCSTATUS="+10);					
				}
				if(name.equals("mysql")){
					sql.append("select * from "+tablename);
					if(_searchType!=null&&_searchWord!=null&&!_searchWord.equals("") && !_searchType.equals("")){
						sql.append(" where "+_searchType+" like '%"+_searchWord+"%'");
					}
					if(_orderField!=null&&_orderBy!=null&&!_orderField.equals("") && !_orderBy.equals("")){
						sql.append(" order by "+_orderField+" "+_orderBy);
					}else{
						sql.append(" order by forminfoid desc");
					}
					int begin = _pageUtil.getPageNum();
					int end =_pageUtil.getPageSize();
					if(_pageUtil.getPageIndex()!=0){
						begin = (_pageUtil.getPageNum()-1)*end;
					}
					sql.append("LIMIT "+begin+","+end);
				}
				if(name.equals("sqlserver")){
					
				}
				System.out.println("aaa"+sql.toString());
				list = irpFormDAO.selectFromInfo(sql.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return list;
	}

	@Override
	public int countFormInfo(String tablename, String _searchType,
			String _searchWord,Long id) {
		int count = 0;
		StringBuffer sql = new StringBuffer();
		IrpForm irpForm=null;
		if(tablename!=null&&!tablename.equals("")){
			irpForm=getFormByFormtablename(tablename);
			sql.append("select count(*) from "+tablename);
			if(id!=null){
				sql.append(" where channeiid="+id);
				if(_searchType!=null&&_searchWord!=null&&!_searchWord.equals("") && !_searchType.equals("")){
					sql.append(" and "+_searchType+" like '%"+_searchWord+"%'");
				}
			}else{
				if(_searchType!=null&&_searchWord!=null&&!_searchWord.equals("") && !_searchType.equals("")){
					sql.append(" where "+_searchType+" like '%"+_searchWord+"%'");
				}
			}
			try {
				count = irpFormDAO.countBySql(sql.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}


	@Override
	public int addFormInfo(String _jsonFile,String _insertValue, String _tablename) {
		int msg = 0;
		int count = 0;
		StringBuffer sql_checkid = new StringBuffer();
		StringBuffer sql_insert = new StringBuffer();
		StringBuffer filename = new StringBuffer();
		sql_checkid.append("select count(*) from "+_tablename);
		try {
			List<Object>  list=  irpFormDAO.selectFromInfo("select max(forminfoid)+1 from "+_tablename);
			Map<Object,Object> map = (Map<Object, Object>) list.get(0);
			if (_jsonFile != null && !_jsonFile.equals("")) {    		
				// 如果存在附件，则添加附件
				JSONArray _Array = JSONArray.fromObject(_jsonFile);
				for (int i = 0; i < _Array.size(); i++) {
					JSONObject jsonObject = (JSONObject) _Array
							.getJSONObject(i);
					String sattfile = jsonObject.getString("attfile");
					String sattdesc = jsonObject.getString("attdesc");
					String sattlinkalt = jsonObject.getString("attlinkalt");
					String seditversions = jsonObject.getString("editversions");
					String attflag = jsonObject.getString("attflag");
					filename.append(","+sattlinkalt);
					// 获得文件类型
					Long typeid = irpAttachedTypeService
							.findAttachedTypeIdByFileExt(FileUtil
									.findFileExt(sattfile));
					// 入库
					addAttQuestionFile(Integer.parseInt(attflag), sattfile,
							typeid,Long.parseLong(map.get("MAX(FORMINFOID)+1").toString()), sattdesc, sattlinkalt, seditversions,
							false, null, false);
				}
			}
			if(filename.length()>0){
				_insertValue=_insertValue.replace("——",filename.toString().substring(1));
			}
			count = irpFormDAO.countBySql(sql_checkid.toString());
			if(count>0){
				sql_insert.append("insert into "+_tablename+" values((select max(forminfoid)+1 from "+_tablename+"),"+_insertValue+")");
			}else{
				sql_insert.append("insert into "+_tablename+" values(1,"+_insertValue+")");
			}	
			msg = irpFormDAO.dropTable(sql_insert.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public int updateForm(IrpForm irpForm) {
		 int  msg=0;
		  LogUtil logUtil=new LogUtil("FORM_UPDATE",irpForm);
			try {
				this.irpFormDAO.updateByPrimaryKeySelective(irpForm);
				msg=1;
				logUtil.successLogs( "修改表单["+logUtil.getLogUser()+"]成功");
			} catch (SQLException e) {
				e.printStackTrace();
				logUtil.errorLogs( "修改表单["+logUtil.getLogUser()+"]失败");
			}
			return msg;
	}

	@Override
	public int realeTableByTableName(String formtablename) {
		int msg=0;
		 StringBuffer sql = new StringBuffer(""); 
			sql.append("drop table  ");
			if(formtablename!=""&&formtablename!=null){
				sql.append(formtablename);
				
				
			}
			
			int s=this.irpFormDAO.dropTable(sql.toString());
			return s;	        	
     	 
     	
	}

	@Override
	public int deleteFormInfoById(Long _forminfoid,String _tablename) {
		int msg = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("delete from "+_tablename+" where forminfoid="+_forminfoid);
		msg = this.irpFormDAO.dropTable(sql.toString());
		return msg;
	}

	@Override
	public Object findObjectById(Long _forminfoid, String _tablename) {
		Object object = new Object();	
		StringBuffer sql = new StringBuffer();
		StringBuffer col = new StringBuffer();
		IrpFormColumnExample example=new IrpFormColumnExample();
		example.createCriteria().andColumntablanameEqualTo(_tablename);
		example.setOrderByClause("crtime asc");		
		try {
			List<IrpFormColumn> listcol=this.irpFormColumnDAO.selectByExample(example);
			col.append("forminfoid,");
			for(int i=0;i<listcol.size();i++){
				if(listcol.get(i).getColumntype().equals(IrpFormColumn.OR_DATE)){
					col.append("to_char("+listcol.get(i).getColumntablenamecol()+",'yyyy-mm-dd HH:mm')");
				}else{
					col.append(listcol.get(i).getColumntablenamecol());
				}				
				if(listcol.size()-1!=i){
					col.append(",");
				}
			}
			sql.append("select "+col.toString()+" from "+_tablename+" where forminfoid="+_forminfoid);
			object = this.irpFormDAO.findObject(sql.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public int updateFromInfoByID(String _jsonFile,Long _forminfoid, String _tablename,String _insertValue) {
		int msg = 0;
		StringBuffer filename = new StringBuffer();
		if (_jsonFile != null && !_jsonFile.equals("")) {    		
			// 如果存在附件，则添加附件
			JSONArray _Array = JSONArray.fromObject(_jsonFile);
			List<Long> _arrAttachedid = new ArrayList<Long>();
			_arrAttachedid.add(0L);//加入集合
			for (int i = 0; i < _Array.size(); i++) {
				JSONObject jsonObject = (JSONObject) _Array
						.getJSONObject(i);
				String sattfile = jsonObject.getString("attfile");
				String sattdesc = jsonObject.getString("attdesc");
				String sattlinkalt = jsonObject.getString("attlinkalt");
				String seditversions = jsonObject.getString("editversions");
				String attflag = jsonObject.getString("attflag");
				Long attachedid =Long.parseLong(jsonObject.getString("attachedid"));
				filename.append(","+sattlinkalt);
				if(attachedid!=0L){
				}
				// 获得文件类型
				Long typeid = irpAttachedTypeService
						.findAttachedTypeIdByFileExt(FileUtil
								.findFileExt(sattfile));
				// 入库
				if(attachedid==0L){
					List<Long> id =addAttQuestionFile(Integer.parseInt(attflag), sattfile,
							typeid, _forminfoid, sattdesc, sattlinkalt, seditversions,
							false, null, false);
					_arrAttachedid.add(id.get(0));
				}else{
					 irpAttachedService.udpateAttachedByExprt(_forminfoid, attachedid, Integer.parseInt(seditversions),Integer.parseInt(attflag));
					    _arrAttachedid.add(attachedid);//加入集合
				}			
			}
			if(filename.length()>0){
				_insertValue=_insertValue.replace("——",filename.toString().substring(1));
			}
			//删除数据库文件 不在集合中的文件
			irpAttachedService.deleteFileNotInList(_arrAttachedid, _forminfoid,IrpAttached.CUSTOMERFORM);
		}
		StringBuffer sql = new StringBuffer();
		sql.append("update "+_tablename+" set "+_insertValue+" where forminfoid="+_forminfoid);
		msg = this.irpFormDAO.dropTable(sql.toString());
		return msg;
	}



	 /***
		 * 添加附件信息到数据库
		 * 
		 * @param _attflag
		 * @param _sAttFile
		 * @param TypeId
		 * @param document
		 * @param _sAttDesc
		 * @param _sAttLinkAlt
		 * @param _sEditversions
		 * @param isClient
		 * @param _touChannelid
		 * @param addUserFile
		 * @return
		 */
		private ArrayList<Long> addAttQuestionFile(Integer _attflag,
				String _sAttFile, Long TypeId, Long _docid, String _sAttDesc,
				String _sAttLinkAlt, String _sEditversions, boolean isClient,
				Long _touChannelid, Boolean addUserFile) {
			ArrayList<Long> _attachedids = new ArrayList<Long>();
			String filePath = SysFileUtil.getFilePathByFileName(_sAttFile);
			File newFile = new File(filePath);
			String newFileName = "";
			if (newFile.exists()) {
				if (IrpAttachedType.JPG_FIELD_NAME.toString().equals(
						TypeId.toString())) { // 这是创建文档之后的附件名字
					newFileName = SysFileUtil.saveFileDoc(newFile,
							SysFileUtil.FILE_TYPE_ATTACHED_PIC, addUserFile);
				} else {
					newFileName = SysFileUtil.saveFileDoc(newFile,
							SysFileUtil.FILE_TYPE_ATTACHED_FILE, addUserFile);
				}
				// 删除临时表中的文件
				String newFilePath = SysFileUtil.getFilePathByFileName(newFileName);

				Long _attachedid = irpAttachedService.addFile(_docid, 0L,
						newFileName, _sAttLinkAlt, _sAttDesc,
						IrpAttached.CUSTOMERFORM, newFilePath,
						Integer.parseInt(_sEditversions), TypeId, _attflag);
				_attachedids.add(_attachedid);
			}
			return _attachedids;

		}


		@Override
		public Map object2map(List<IrpFormColumn> _column,Object _obj) {
			if(_obj == null)  
	            return null;
			@SuppressWarnings("unchecked")
			Map<Object, Object> map=(Map<Object, Object>)_obj;
			List<String> list = new ArrayList<String>();
			Set set = map.keySet();
			List<IrpFormColumn> column_1=new ArrayList<IrpFormColumn>();
			IrpFormColumn fc=new IrpFormColumn();
			fc.setColumntablenamecol("FORMINFOID");
			column_1.add(fc);
			column_1.addAll(_column);
			for (IrpFormColumn c : column_1) {
				for(Object obj:set ){
					String scr=(String) obj;
					if(obj.equals(c.getColumntablenamecol())){
						Object o=map.get(obj);
						if(null==o){
							list.add("");
						}else{
							list.add(map.get(obj).toString());
						}
					}else if(scr.indexOf(c.getColumntablenamecol())!=-1){
						Object o=map.get(obj);
						if(null==o){
							list.add("");
						}else{
							list.add(map.get(obj).toString());
						}
					}
				}
			}
			Map hmap = new HashMap();
			hmap.put("FORMINFOID",list.get(0));
			for(int i =0;i<_column.size();i++){
				if(_column.get(i).getDisplaytype().equals("file")){
					StringBuffer sbtext = new StringBuffer();
					StringBuffer sbimage = new StringBuffer();
					List<IrpAttached>  attacheds = this.irpAttachedService.getAttachedByAttDocId(Long.parseLong(list.get(0)),
							IrpAttached.CUSTOMERFORM);
					if(attacheds!=null && attacheds.size()>0){ 					
						List<IrpAttachedInfo> _attachedinfos = new ArrayList<IrpAttachedInfo>();
						HttpServletRequest request = ServletActionContext.getRequest();
						String rootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
						for (int j = 0; j < attacheds.size(); j++) {
							IrpAttachedInfo s=new IrpAttachedInfo();
							s.setAttachedid(attacheds.get(j).getAttachedid());
							s.setAttdocid(attacheds.get(j).getAttdocid());
							s.setAttdesc(attacheds.get(j).getAttdesc());
							s.setAttfile(attacheds.get(j).getAttfile());
							if(isRadio(attacheds.get(j).getAttfile())==1){
								
								s.setFlag("true");
							}else{
								s.setFlag("false");
								
							}
							_attachedinfos.add(s);
						}
						for(int j=0;j<_attachedinfos.size();j++){
							String download = "<a  href='"+rootPath+"file/readfile.action?fileName=" +
									_attachedinfos.get(j).getAttfile() +
									"&fileTrueName="+_attachedinfos.get(j).getAttdesc()+"'>下载</a>";
							if(_attachedinfos.get(j).getFlag().equals("true")){
								sbimage.append("<img alt='显示图片' src='"+rootPath+"file/readfile.action?fileName="
										+_attachedinfos.get(j).getAttfile()+
										"' id='mypic' width='100' height='100' title='"
										+_attachedinfos.get(j).getAttdesc()+"'/>");
								sbimage.append(download);
							}else{
								sbtext.append("<font>"+_attachedinfos.get(j).getAttdesc()+"</font>");
								sbtext.append(download);
							}
							
						}
					}
					hmap.put("fileName",sbtext.toString());
					hmap.put("imgshow1",sbimage.toString());
				}else{
					String s=_column.get(i).getColumntablenamecol();
					String str=list.get(i+1);
					hmap.put(_column.get(i).getColumntablenamecol(), list.get(i+1));
				}
			}
			return hmap;
	    }     
	//是否显示时候为附件的按钮
	 public int  isRadio(String fileName)
	 {   
	 	 
	 	/**
	 	 * 根据扩展名查询他的id看他是不是图片，如果是图片，返回一个常量，
	 	 * 在页面可以利用他来显示因此时候封面的按钮
	 	 * 字段editversions
	 	 */
	 	int isTrue=0;
	 	String _sFileExt=FileUtil.findFileExt(fileName);
	 	Long atttypeid=irpAttachedTypeService.findAttachedTypeIdByFileExt(_sFileExt); //查询所附件类型id
	 	/**
	 	 * 如果附件id==0，则没有找到
	 	 * 若果附件类型等于图片类型，则标示
	 	 */ 
	 	if(atttypeid!=null &&atttypeid.toString()!="0"){
	 		isTrue=0; 
	 	}
	 	if(atttypeid==IrpAttachedType.JPG_FIELD_NAME){ 
	 		isTrue=1;
	 	}
		return isTrue;
	 	
	 }
		@Override
		public IrpForm getFormByFormtablename(String formtablename) {
			IrpFormExample example =new IrpFormExample();
			IrpForm irpForm=new IrpForm();
			example.createCriteria().andFormtablenameEqualTo(formtablename);
			try {
				List<IrpForm> list=this.irpFormDAO.selectByExampleWithoutBLOBs(example);
				if(list.size()>0){
					irpForm=list.get(0);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return irpForm;
		}
		//统计字段个数
		@Override
		public int countFormColumn1(IrpFormColumnExample example) {
			int count = 0;
				try {
					count = irpFormDAO.countByColumn(example);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return count;
		}
		@Override
		public IrpForm selectformByTableName(String formtablename) {
			List<IrpForm> formList=new ArrayList<IrpForm>();
			IrpFormExample example=new IrpFormExample();
			IrpFormExample.Criteria criteria=example.createCriteria();
			criteria.andFormtablenameEqualTo(formtablename);
			try {
				formList=irpFormDAO.selectByExampleWithBLOBs(example);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return formList.size()>0?formList.get(0):null;
		}
		
		@Override
		public void copyFile(String content, String path, String formtablename) {
			 try {  
		            if (!"".equals(path)) {  
		                // 检查目录是否存在  
		                File fileDir = new File(path);  
		                if (!fileDir.exists()) {  
		                	fileDir.mkdirs();
		                }
		                String aaa="\\$\\(\\'\\#tbinfo\\'\\)\\.html\\(str\\);";
		                String bbb="<a onclick=\"addDocument\\(\\)\"></a>";
		                String ccc="<a onclick=\"closeWindow\\(\\)\"></a>";
		                String bb="<a href=\"javascript:void(0)\" onclick=\"addDocument()\">保存</a>";
		                String cc="<a href=\"javascript:void(0)\" onclick=\"closeWindow()\">关闭</a>";
		                //if(content.contains(aaa)){
		                	content=content.replaceAll(aaa, "");
		                	content=content.replaceAll(bbb, bb);
		                	content=content.replaceAll(ccc, cc);
		                //}
		                byte b[] = content.getBytes("UTF-8");
		                ByteArrayInputStream bais = new ByteArrayInputStream(b);  
		                
		                String form=formtablename.toLowerCase();
		                FileOutputStream ostream = new FileOutputStream(path+form+"_info_add.jsp");  
		                ostream.write(b); 
		                bais.close();  
		                ostream.close();  
		                }  
		        } catch (IOException e) {  
		            e.printStackTrace();  
		      }  
		}
		@Override
		public void copyUpdatejsp(String content, String path, String formtablename) {
			try {  
				if (!"".equals(path)) {  
					// 检查目录是否存在  
					File fileDir = new File(path);  
					if (!fileDir.exists()) {  
						fileDir.mkdirs();
					}
					String aaa="\\$\\(\\'\\#tbinfo\\'\\)\\.html\\(str\\);";
					String bbb="<a onclick=\"uppdateDocument\\(\\)\"></a>";
					String ccc="<a onclick=\"closeWindow\\(\\)\"></a>";
					String bb="<a href=\"javascript:void(0)\" onclick=\"uppdateDocument()\">保存</a>";
					String cc="<a href=\"javascript:void(0)\" onclick=\"closeWindow()\">关闭</a>";
					//if(content.contains(aaa)){
					content=content.replaceAll(aaa, "");
					content=content.replaceAll(bbb, bb);
					content=content.replaceAll(ccc, cc);
					//}
				byte b[] = content.getBytes("UTF-8");
				ByteArrayInputStream bais = new ByteArrayInputStream(b);  
				
				String form=formtablename.toLowerCase();
				FileOutputStream ostream = new FileOutputStream(path+form+"_info_update.jsp");  
				ostream.write(b); 
				bais.close();  
				ostream.close();  
				}  
			} catch (IOException e) {  
				e.printStackTrace();  
			}  
		}
		
		@Override
		public List<Object> selectFormInfo(PageUtil _pageUtil,String _searchType,String _searchWord,int columninliststatus,String tablename,String _orderField,String _orderBy) {
			List<Object> list = null;
			StringBuffer sql = new StringBuffer();
			StringBuffer col = new StringBuffer();
			IrpFormColumnExample example=new IrpFormColumnExample();
			example.createCriteria().andColumntablanameEqualTo(tablename)
			.andColumninliststatusEqualTo(columninliststatus);
			example.setOrderByClause("crtime asc");
			try {
				List<IrpFormColumn> listcol=this.irpFormColumnDAO.selectByExample(example);
				col.append("forminfoid,");
				for(int i=0;i<listcol.size();i++){
					
					if(listcol.get(i).getColumntype().equals(IrpFormColumn.OR_DATE)){
						col.append("to_char("+listcol.get(i).getColumntablenamecol()+",'yyyy-MM-dd HH:mm')");
					}else{
						col.append(""+listcol.get(i).getColumntablenamecol());
					}				
					if(listcol.size()-1!=i){
						col.append(",");
					}
				}
				if(tablename!=null&&!tablename.equals("")){
					String name=LoginCheckFilter.PropertiesInfo();
					if(name.equals("oracle")){	
						sql.append("select "+col.toString()+" from ( select A.*,rownum rn from (");
						sql.append("select * from "+tablename);
						if(_searchType!=null&&_searchWord!=null&&!_searchWord.equals("") && !_searchType.equals("")){
							sql.append(" where "+_searchType+" like '%"+_searchWord+"%'");
						}
						if(_orderField!=null&&_orderBy!=null&&!_orderField.equals("") && !_orderBy.equals("")){
							sql.append(" order by "+_orderField+" "+_orderBy);
						}else{
							sql.append(" order by forminfoid desc");
						}
						int begin = _pageUtil.getPageNum();
						int end =_pageUtil.getPageSize();
						if(_pageUtil.getPageIndex()!=0){
							begin = (_pageUtil.getPageNum()-1)*end+1;
							end = _pageUtil.getPageNum()*_pageUtil.getPageSize();
						}
						sql.append(") A where rownum <= "+end+") where rn >="+begin);					
					}
					if(name.equals("mysql")){
						sql.append("select * from "+tablename);
						if(_searchType!=null&&_searchWord!=null&&!_searchWord.equals("") && !_searchType.equals("")){
							sql.append(" where "+_searchType+" like '%"+_searchWord+"%'");
						}
						if(_orderField!=null&&_orderBy!=null&&!_orderField.equals("") && !_orderBy.equals("")){
							sql.append(" order by "+_orderField+" "+_orderBy);
						}else{
							sql.append(" order by forminfoid desc");
						}
						int begin = _pageUtil.getPageNum();
						int end =_pageUtil.getPageSize();
						if(_pageUtil.getPageIndex()!=0){
							begin = (_pageUtil.getPageNum()-1)*end;
						}
						sql.append("LIMIT "+begin+","+end);
					}
					if(name.equals("sqlserver")){
						
					}
					list = irpFormDAO.selectFromInfo(sql.toString());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			return list;
		}
		@Override
		public int findFormInfoById(Long _docid,String _tablename) {
			int forminfoid = 0;
			StringBuffer sql = new StringBuffer();
			sql.append("select FORMINFOID from "+_tablename+" where docid="+_docid);
			forminfoid = this.irpFormDAO.deleteSql(sql.toString());
			return forminfoid;
		}
		@Override
		public List<IrpForm> getAllForm(Integer _formisdel,Integer _formstatus,Integer _formisexitindb){
			IrpFormExample example=new IrpFormExample();
			example.createCriteria().andFormisdelEqualTo(_formisdel).andFormstatusEqualTo(_formstatus).andIsexitindbEqualTo(_formisexitindb);
			List<IrpForm>  irpForms=null;
			try {
				irpForms=this.irpFormDAO.getAllForm(example);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return irpForms;
		}

		@Override
		public List<IrpForm> selectByformtablename(String formtablename) {
			List<IrpForm> formList=new ArrayList<IrpForm>();
			IrpFormExample example=new IrpFormExample();
			IrpFormExample.Criteria criteria=example.createCriteria();
			criteria.andFormtablenameEqualTo(formtablename);
			try {
				formList=irpFormDAO.selectByExampleWithBLOBs(example);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return formList;
		}
}
