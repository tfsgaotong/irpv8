package com.tfs.irp.site.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.tfs.irp.site.dao.IrpSiteDAO;
import com.tfs.irp.site.entity.IrpSite;
import com.tfs.irp.site.entity.IrpSiteExample;
import com.tfs.irp.site.service.IrpSiteService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpSiteServiceImpl implements IrpSiteService {
	 
	private IrpSiteDAO  irpSiteDAO;
	
	@Override
	public Boolean deleteLogoAndBaner(String sPath) { 
		Boolean b=false;
		File file=new File(sPath);
		//判断文件或者目录是否存在 
		if(!file.exists()){ 
			b=true;
		}else{ 
			//判断是否为文件
			if(file.isFile()){//为文件时候调用文件的删除方法
				b= file.delete(); 
			} 
			else{//如果他为目录 //代表没有选择文件，
				b=true;
			}
		} 
		return b;
	}
	
	@Override
	public void saveLogoAndBaner(File _src,File _dst) { 
		try {
			InputStream inputStream=null;
			OutputStream outputStream =null;
			try {
				inputStream=new BufferedInputStream(new FileInputStream(_src));
				outputStream= new BufferedOutputStream(new FileOutputStream(_dst)); 
				byte[] buffer=new byte[(int)_src.length()];
				 while (inputStream.read(buffer) > 0) {  
					 outputStream.write(buffer);  
	             }  
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(inputStream!=null) inputStream.close(); 
				 if(outputStream!=null)outputStream.close();
			} 
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		 
	}
	@Override
	public boolean findSiteBySiteName(String sitename) {
		IrpSiteExample example=new IrpSiteExample(); 
		Boolean b=true;
		if(sitename!=null && sitename.length()>0){
			example.createCriteria().andSitenameEqualTo(sitename);
			try {
				List<IrpSite> sites=irpSiteDAO.selectByExample(example);
				if(sites!=null && sites.size()>0){
					b=false;
				}
			} catch (SQLException e) { 
				e.printStackTrace();
			}
		} 
		return b;
	}
	@Override
	public int deleteSiteBySiteid(Long siteid) {
		
		int nCount=0;
		LogUtil logUtil=null;
		try { 
			IrpUser irpUser=LoginUtil.getLoginUser(); //获得当前登录用户 
			IrpSite site=new IrpSite();
			site.setSiteid(siteid);
			site.setStatus(0);//设置为0代表该站点被删除
			site.setOpertime(new Date());
			site.setOperuser(irpUser.getUsername()); 
			
			IrpSite _irIrpSite=irpSiteDAO.selectByPrimaryKey(siteid);
			logUtil=new LogUtil("SITE_DELETE",_irIrpSite);
			
			nCount=irpSiteDAO.updateByPrimaryKeySelective(site);
			logUtil.successLogs("删除站点["+logUtil.getLogUser()+"]成功"); 
		} catch (SQLException e) {
			logUtil.errorLogs("删除站点"+logUtil.getLogUser()+"失败",e);
			e.printStackTrace();
		} 
		return nCount;
	}
	@Override
	public int addSite(IrpSite site) {
   		int nCount=0;
		LogUtil logUtil=null;
		 try { 
			 //给站点设置主键 
			 site.setSiteid(TableIdUtil.getNextId(IrpSite.TABLE_NAME));
			 site.setSitetype(IrpSite.SITE_TYPE_PUBLISH);
			 IrpUser irpUser=LoginUtil.getLoginUser(); //获得当前登录用户
			 site.setCruser(irpUser.getUsername());
			 site.setCruserid(irpUser.getUserid());
			 Date newdate=new Date();//当前站点创建时间
			 site.setCrtime(newdate);
			 logUtil=new LogUtil("SITE_ADD", site); 
			 irpSiteDAO.insertSelective(site);
			 logUtil.successLogs( "添加站点["+logUtil.getLogUser()+"]成功");
			 nCount=1;
		} catch (SQLException e) {
			nCount=0;
			e.printStackTrace();
			logUtil.errorLogs( "添加站点"+logUtil.getLogUser()+"失败",e);
		}  
		 return nCount;
	}
	@Override
	public int updateSite(IrpSite site) {
		int nCount=0;
		LogUtil logUtil=null;
		 try {   
			 logUtil=new LogUtil("SITE_UPDATE",site);
			 nCount=irpSiteDAO.updateByPrimaryKeySelective(site); 
			 logUtil.successLogs("修改站点["+logUtil.getLogUser()+"]成功");
		} catch (SQLException e) { 
			e.printStackTrace();
			logUtil.errorLogs( "修改站点"+logUtil.getLogUser()+"失败",e); 
		}
		 return nCount;
	}
	@Override
	public IrpSite siteInfo(Long siteid) {
		IrpSite irpSite=null;
		try {
			// irpSite=irpSiteDAO.siteInfo(siteid);
			irpSite=irpSiteDAO.selectByPrimaryKey(siteid);
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return irpSite;
	}
	 /**
	  * 用于修改时候的查询所有站点，不可以查询他自己
	  */
	@Override
	public List allSite(Long status,Long _siteid) { 
		List<IrpSite> list = null;
		try {
			list = irpSiteDAO.allSiteName(status, _siteid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List allSite(Long status ) { 
		List<IrpSite> list = null;
		try {
			list = irpSiteDAO.allSiteName(status);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public String findSiteName(Long siteid) {
		String name=null;
		   try {
			name=irpSiteDAO.findSitename(siteid); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
	
	public IrpSiteDAO getIrpSiteDAO() {
		return irpSiteDAO;
	}
	
	
	public void setIrpSiteDAO(IrpSiteDAO irpSiteDAO) {
		this.irpSiteDAO = irpSiteDAO;
	}
	
	@Override
	public List<IrpSite> findSitesBySiteType(int _nSiteType) {
		List<IrpSite> sites = null;
		IrpSiteExample example = new IrpSiteExample();
		example.createCriteria().andSitetypeEqualTo(_nSiteType);
		example.setOrderByClause("siteorder asc");
		try {
			sites = irpSiteDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sites;
	}
	/**
	 * 根据站点类型查询站点id集合
	 * @return
	 */
	@Override
	public List<Long> findSiteIds(Integer siteType){
		List<Long> siteIds=new ArrayList<Long>();
		List<IrpSite> sites =findSitesBySiteType(siteType);
		if(sites!=null && sites.size()>0){
			for (IrpSite irpSite : sites) {
				siteIds.add(irpSite.getSiteid());
			}
		}
		return siteIds;
	}
}
