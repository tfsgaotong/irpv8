package com.tfs.irp.randar.service.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.tfs.irp.randar.dao.IrpRandarDAO;
import com.tfs.irp.randar.entity.IrpRandar;
import com.tfs.irp.randar.entity.IrpRandarExample;
import com.tfs.irp.randar.entity.TableType;
import com.tfs.irp.randar.entity.IrpRandarExample.Criteria;
import com.tfs.irp.randar.service.IrpRandarService;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.filter.LoginCheckFilter;
import com.tfs.irp.util.importdata.DBConnection;

public class IrpRandarServiceImpl implements IrpRandarService {

	private IrpRandarDAO irpradar;

	public IrpRandarDAO getIrpradar() {
		return irpradar;
	}
	public void setIrpradar(IrpRandarDAO irpradar) {
		this.irpradar = irpradar;
	}
	/**
	 * 鍒嗛〉
	 */
	@Override
	public List<IrpRandar> queryRadar(PageUtil pageUtil,String _orderByctime) {
		List<IrpRandar> list = null;
		IrpRandarExample example = new IrpRandarExample();
		example.setOrderByClause(_orderByctime);
		example.createCriteria();
		try {
			list = irpradar.selectByExampleWithBLOBs(example, pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<IrpRandar> queryRadars(PageUtil pageUtil,String sitename,String _orderByctime) {
		List<IrpRandar> list = null;
		IrpRandarExample example = new IrpRandarExample();
		example.setOrderByClause(_orderByctime);
		Criteria criteria=example.createCriteria();

		criteria.andSitenameEqualTo(sitename);
		try {
			list = irpradar.selectByExampleWithBLOBs(example, pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	@Override
	public int countRadar() {
		int i=0;
		IrpRandarExample example = new IrpRandarExample();
		example.createCriteria();
		
		try {
			i=irpradar.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * SiteName
	 */
	@Override
	public List<String> sits() {
		List<String> ls=null;
		IrpRandarExample example = new IrpRandarExample();
		example.createCriteria();
		try {
			ls=irpradar.findBySite(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	
	@Override
	public int countRadars(String sitename) {
		int i=0;
		IrpRandarExample example = new IrpRandarExample();
		Criteria criteria=example.createCriteria();
		criteria.andSitenameEqualTo(sitename);
		
		try {
			i=irpradar.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int ups(String sid){
		int nRows = 0;
		IrpRandar irpc = new IrpRandar();
		irpc.setSatus(2);
		IrpRandarExample example = new IrpRandarExample();
		Criteria criteria=example.createCriteria();
		criteria.andSidEqualTo(sid);
		try {
			nRows =irpradar.updateByExampleSelective(irpc, example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nRows;
	}
	@Override
	public int upRadar(String titles,int status,String sid,String doccontent){
		int nRows = 0;
		IrpRandar irpc = new IrpRandar();
		irpc.setDoctitle(titles);
		irpc.setSatus(status);
		irpc.setDoccontent(doccontent);
		IrpRandarExample example = new IrpRandarExample();
		Criteria criteria=example.createCriteria();
		criteria.andSidEqualTo(sid);
		try {
			nRows =irpradar.updateByExampleSelective(irpc, example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nRows;
	}
	@Override
	public int delRandar(String sid) {
		int nRows =0;
		IrpRandarExample example = new IrpRandarExample();
		Criteria criteria=example.createCriteria();
		criteria.andSidEqualTo(sid);
		try {
			nRows=irpradar.deleteByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nRows;
	}
	
	@Override
	public List<IrpRandar> querydoc(String sid){
		List<IrpRandar> list = null;
		IrpRandarExample example = new IrpRandarExample();
		Criteria criteria=example.createCriteria();
		criteria.andSidEqualTo(sid);
		try {
			list = irpradar.selectByExampleWithBLOBs(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	@Override
	public String importRadarData() {
		String msg = null;
		Connection connection = DBConnection.getConnection();
		if(connection == null){
			msg = "timeout";
		}else{
			String sqlColumn="SELECT  column_name, data_type,data_length FROM all_tab_cols WHERE table_name = 'IRP_RANDAR'";
			
			String tablename = SysConfigUtil.getSysConfigValue("DMTABLE");
			String sql = "SELECT * FROM " + tablename.trim();// + " ORDER BY DOCPUBDATE DESC";
			Statement statement = null;
			ResultSet resultSet = null;
			try {
				statement = connection.createStatement();
				resultSet = statement.executeQuery(sqlColumn);
				int count = 0;
				while (resultSet.next()) {
					IrpRandar irpRandar = this.createEntity(resultSet);
					try {
						irpradar.insertSelective(irpRandar);
						count++;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				msg = String.valueOf(count);
			} catch (SQLException e) {
				e.printStackTrace();
				msg = "error";
			} finally {
				if(resultSet != null){
					try{resultSet.close();}catch(SQLException e){e.printStackTrace();}
				}
				if(statement != null){
					try{statement.close();}catch(SQLException e){e.printStackTrace();}
				}
				if(connection != null){
					try{connection.close();}catch(SQLException e){e.printStackTrace();}
				}
			}
		}
		return msg;
	}
	
	/*@Override
	public String importRadarData() {
		String msg = null;
		Connection connection = DBConnection.getConnection();
		if(connection == null){
			msg = "timeout";
		}else{
			String sqlColumn="SELECT  column_name, data_type,data_length FROM all_tab_cols WHERE table_name = 'IRP_RANDAR';";
			
			String tablename = SysConfigUtil.getSysConfigValue("DMTABLE");
			String sql = "SELECT * FROM " + tablename.trim();// + " ORDER BY DOCPUBDATE DESC";
			
			
			Statement statement = null;
			ResultSet resultSet = null;
			try {
				statement = connection.createStatement();
				resultSet = statement.executeQuery(sql);
				int count = 0;
				while (resultSet.next()) {
					IrpRandar irpRandar = this.createEntity(resultSet);
					try {
						irpradar.insertSelective(irpRandar);
						count++;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				msg = String.valueOf(count);
			} catch (SQLException e) {
				e.printStackTrace();
				msg = "error";
			} finally {
				if(resultSet != null){
					try{resultSet.close();}catch(SQLException e){e.printStackTrace();}
				}
				if(statement != null){
					try{statement.close();}catch(SQLException e){e.printStackTrace();}
				}
				if(connection != null){
					try{connection.close();}catch(SQLException e){e.printStackTrace();}
				}
			}
		}
		return msg;
	}
	*/
	private IrpRandar createEntity(ResultSet rs) throws SQLException{
		IrpRandar randar = new IrpRandar();
		randar.setAppendixnames(rs.getString("AppendixNames"));
		randar.setCarrytype(rs.getString("CarryType"));
		randar.setDocauthor(rs.getString("docauthor")==null?"admin":rs.getString("docauthor"));
		randar.setDocclick(rs.getString("docclick"));
		randar.setDoccontent(rs.getString("doccontent"));
		randar.setDocpubdate(rs.getString("docpubdate"));
		randar.setDocreply(rs.getString("docreply"));
		randar.setDocsource(rs.getString("docsource"));
		randar.setDoctitle(rs.getString("doctitle"));
		randar.setFoldername(rs.getString("FolderName"));
		randar.setNegative(rs.getString("Negative"));
		randar.setNetflag(rs.getString("NetFlag"));
		randar.setParentid(rs.getString("ParentId"));
		randar.setSid(rs.getString("SID"));
		randar.setSitehot(rs.getString("SiteHot"));
		randar.setSitename(rs.getString("SiteName"));
		randar.setSystime(rs.getString("SysTime"));
		randar.setTaskname(rs.getString("TaskName"));
		randar.setUrl(rs.getString("Url"));
		return randar;
	}
	@Override
	public List<TableType> selectResoureColumn() {
		String dbName=SysConfigUtil.getSysConfigValue("DMDBNAME_BD");
		String sqlColumn="";
		String sqltype=LoginCheckFilter.PropertiesInfo();
		if(sqltype.indexOf("oracle")!=-1){
			 sqlColumn="SELECT  column_name, data_type,data_length FROM user_tab_cols WHERE table_name = 'IRP_RANDAR'";
		}else if (sqltype.indexOf("mysql")!=-1) {
			sqlColumn="select COLUMN_NAME,column_comment from INFORMATION_SCHEMA.Columns where table_name='IRP_RANDAR' and table_schema='"+dbName+"'";
		}
		
		List<Object> list=irpradar.selectTablecolumn(sqlColumn);
		List<TableType> tableList=new ArrayList<TableType>();
		for (Object object : list) {
			TableType tableType=new TableType();
			Map<Object, Object> map=(Map<Object, Object>) object;
			if(map!=null){
					Object o=map.get("COLUMN_NAME");
					if(o!=null){
						tableType.setColumnName(o.toString());
					}
					o=map.get("DATA_TYPE");
					if(o!=null){
						tableType.setDataType(o.toString());
					}
					o=map.get("DATA_LENGTH");
					if(o!=null){
						tableType.setDataLength(o.toString());
					}
					tableList.add(tableType);	
			}
		}
		return tableList;
	}
	@Override
	public List<TableType> selectDestinationColumn() {
		String msg = null;
		List<TableType> tableList=new ArrayList<TableType>();
		Connection connection = DBConnection.getConnection();
		if(connection == null){
			return null;
		}else{
			String tablename = SysConfigUtil.getSysConfigValue("DMTABLE");
			String sqltype=SysConfigUtil.getSysConfigValue("DMDRIVERNAME");
			String dbName=SysConfigUtil.getSysConfigValue("DMDBNAME");
			String sqlColumn="";
			if(sqltype.indexOf("oracle")!=-1){
				sqlColumn="SELECT  column_name, data_type,data_length FROM user_tab_cols WHERE table_name = '"+tablename+"'";
			}else if (sqltype.indexOf("mysql")!=-1) {
				sqlColumn="select COLUMN_NAME,column_comment from INFORMATION_SCHEMA.Columns where table_name='"+tablename+"' and table_schema='"+dbName+"'";
			}
			Statement statement = null;
			ResultSet resultSet = null;
			try {
				statement = connection.createStatement();
				resultSet = statement.executeQuery(sqlColumn);
				int count = 0;
				while (resultSet.next()) {
					TableType tableType=new TableType();
					Object o=resultSet.getString("COLUMN_NAME");
					if(o!=null){
						tableType.setColumnName(o.toString());
					}
					o=resultSet.getString("DATA_TYPE");
					if(o!=null){
						tableType.setDataType(o.toString());
					}
					o=resultSet.getString("DATA_LENGTH");
					if(o!=null){
						tableType.setDataLength(o.toString());
					}
					tableList.add(tableType);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if(resultSet != null){
					try{resultSet.close();}catch(SQLException e){e.printStackTrace();}
				}
				if(statement != null){
					try{statement.close();}catch(SQLException e){e.printStackTrace();}
				}
				if(connection != null){
					try{connection.close();}catch(SQLException e){e.printStackTrace();}
				}
			}
		}
		return tableList;
	}
	@Override
	public String importRadarData(String columnString) {
		String msg = null;
		Connection connection = DBConnection.getConnection();
		if(connection == null){
			msg = "timeout";
		}else{
			String tablename = SysConfigUtil.getSysConfigValue("DMTABLE");
			String sql = "SELECT * FROM " + tablename.trim();// + " ORDER BY DOCPUBDATE DESC";
			
			
			Statement statement = null;
			ResultSet resultSet = null;
			try {
				statement = connection.createStatement();
				resultSet = statement.executeQuery(sql);
				int count = 0;
				while (resultSet.next()) {
					try {
						IrpRandar irpRandar = this.createEntityk(resultSet,columnString);
						irpradar.insertSelective(irpRandar);
						count++;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				msg = String.valueOf(count);
			} catch (SQLException e) {
				e.printStackTrace();
				msg = "error";
			} finally {
				if(resultSet != null){
					try{resultSet.close();}catch(SQLException e){e.printStackTrace();}
				}
				if(statement != null){
					try{statement.close();}catch(SQLException e){e.printStackTrace();}
				}
				if(connection != null){
					try{connection.close();}catch(SQLException e){e.printStackTrace();}
				}
			}
		}
		return msg;
		}
	private IrpRandar createEntityk(ResultSet rs, String columnString) throws SQLException, IllegalArgumentException, IllegalAccessException {
		IrpRandar bean = new IrpRandar();
		List<TableType> list=selectResoureColumn();
		String[] columns=columnString.split(",");
		Class userCla = (Class) bean.getClass();  
		Field[] fs = userCla.getDeclaredFields();  
		for (int j = 0; j < list.size(); j++) {
			System.out.println("-----------"+list.get(j).getColumnName());
		       for(int i = 0 ; i < fs.length; i++){  
		           Field f = fs[i];  
		           f.setAccessible(true); //设置些属性是可以访问的  
		           String filename=f.getName();
		            if(filename.equalsIgnoreCase(list.get(j).getColumnName())){
		            	String type = f.getType().toString();//得到此属性的类型  
		            	String reCol=columns[j].toLowerCase();
		            	if(reCol==null||reCol.trim().equals("null")){
		            		continue;
		            	}
		            	String value =rs.getString(reCol.trim());
		            	if (type.endsWith("String")&&value!=null) {  
		            		f.set(bean,value) ;        //给属性设值  
		            	}else if(type.endsWith("int") || type.endsWith("Integer")){  
		            		if(value!=null){
		            			
		            			f.set(bean,Integer.parseInt(value)) ;       //给属性设值  
		            		}
		            	}else{  
		            		System.out.println(f.getType()+"\t");  
		            	}  
		            }
		            
		       }  
		}
		System.out.println(bean);
		/*randar.setAppendixnames(rs.getString("AppendixNames"));
		randar.setCarrytype(rs.getString("CarryType"));
		randar.setDocauthor(rs.getString("docauthor")==null?"admin":rs.getString("docauthor"));
		randar.setDocclick(rs.getString("docclick"));
		randar.setDoccontent(rs.getString("doccontent"));
		randar.setDocpubdate(rs.getString("docpubdate"));
		randar.setDocreply(rs.getString("docreply"));
		randar.setDocsource(rs.getString("docsource"));
		randar.setDoctitle(rs.getString("doctitle"));
		randar.setFoldername(rs.getString("FolderName"));
		randar.setNegative(rs.getString("Negative"));
		randar.setNetflag(rs.getString("NetFlag"));
		randar.setParentid(rs.getString("ParentId"));
		randar.setSid(rs.getString("SID"));
		randar.setSitehot(rs.getString("SiteHot"));
		randar.setSitename(rs.getString("SiteName"));
		randar.setSystime(rs.getString("SysTime"));
		randar.setTaskname(rs.getString("TaskName"));
		randar.setUrl(rs.getString("Url"));
		*/
		
		return bean;
	}
}
