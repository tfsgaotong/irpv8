package com.tfs.irp.project.service.impl;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.attachedtype.entity.IrpAttachedType;
import com.tfs.irp.attachedtype.service.IrpAttachedTypeService;
import com.tfs.irp.project.dao.IrpProjectDAO;
import com.tfs.irp.project.entity.IrpProject;
import com.tfs.irp.project.entity.IrpProjectExample;
import com.tfs.irp.project.entity.IrpProjectExample.Criteria;
import com.tfs.irp.project.service.IrpProjectService;
import com.tfs.irp.projectperson.entity.IrpProjectPerson;
import com.tfs.irp.projectperson.service.IrpProjectPersonService;
import com.tfs.irp.projectsharetask.entity.IrpProjectShareTaskExample;
import com.tfs.irp.projectsharetask.service.IrpProjectShareTaskService;
import com.tfs.irp.projecttaskperson.entity.IrpProjectTaskPersonExample;
import com.tfs.irp.projecttaskperson.service.IrpProjectTaskPersonService;
import com.tfs.irp.tag.service.IrpTagService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysFileUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpProjectServiceImpl implements IrpProjectService {
	
	private IrpProjectDAO irpProjectDAO;
	
	IrpUserService irpUserService;
	
	IrpProjectPersonService irpProjectPersonService;
	
	IrpAttachedService irpAttachedService;
	
	IrpProjectTaskPersonService irpProjectTaskPersonService;
	
	IrpProjectShareTaskService irpProjectShareTaskService;
	
	IrpTagService irpTagService;
	
	IrpAttachedTypeService irpAttachedTypeService;
	
	
	public IrpAttachedTypeService getIrpAttachedTypeService() {
		return irpAttachedTypeService;
	}

	public void setIrpAttachedTypeService(
			IrpAttachedTypeService irpAttachedTypeService) {
		this.irpAttachedTypeService = irpAttachedTypeService;
	}

	public IrpAttachedService getIrpAttachedService() {
		return irpAttachedService;
	}

	public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
		this.irpAttachedService = irpAttachedService;
	}

	public IrpTagService getIrpTagService() {
		return irpTagService;
	}

	public void setIrpTagService(IrpTagService irpTagService) {
		this.irpTagService = irpTagService;
	}
 
	public IrpProjectTaskPersonService getIrpProjectTaskPersonService() {
		return irpProjectTaskPersonService;
	}

	public void setIrpProjectTaskPersonService(
			IrpProjectTaskPersonService irpProjectTaskPersonService) {
		this.irpProjectTaskPersonService = irpProjectTaskPersonService;
	}

	public IrpProjectShareTaskService getIrpProjectShareTaskService() {
		return irpProjectShareTaskService;
	}

	public void setIrpProjectShareTaskService(
			IrpProjectShareTaskService irpProjectShareTaskService) {
		this.irpProjectShareTaskService = irpProjectShareTaskService;
	}

	public IrpProjectPersonService getIrpProjectPersonService() {
		return irpProjectPersonService;
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public void setIrpProjectPersonService(
			IrpProjectPersonService irpProjectPersonService) {
		this.irpProjectPersonService = irpProjectPersonService;
	}

	public IrpProjectDAO getIrpProjectDAO() {
		return irpProjectDAO;
	}

	public void setIrpProjectDAO(IrpProjectDAO irpProjectDAO) {
		this.irpProjectDAO = irpProjectDAO;
	}
	 
	@Override
	public int addProject(IrpProject irpProject) {
		// TODO Auto-generated method stub
		int nCount=0;
		try { 
			IrpUser irpUser=LoginUtil.getLoginUser();
			irpProject.setProjectid(TableIdUtil.getNextId(IrpProject.TABLE_NAME));
			//处理项目图标
			String tempImg=irpProject.getProjectfile();
			if(tempImg!=null && tempImg.trim().length()>0){
				//获得临时文件名 
				String sFilePath = SysFileUtil.getFilePathByFileName(tempImg);
				//拿到真实的名字， 然后放到永久目录下面，然后保存
				String newFileName=SysFileUtil.saveFile(new File(sFilePath), SysFileUtil.FILE_TYPE_ATTACHED_PIC); 
				//将真实名称保存，并保存一个附件记录
				irpProject.setProjectfile(newFileName);
				String newFilePath=SysFileUtil.getFilePathByFileName(newFileName); 
				irpAttachedService.addFile(irpProject.getProjectid(), 0L, newFileName, newFileName, newFileName, IrpAttached.PROJECT_DOCIDTYPE, newFilePath,null,IrpAttachedType.JPG_FIELD_NAME,0);
			}
			irpProject.setCruserid(irpUser.getUserid());
			irpProject.setIsclosed(IrpProject.NOT_CLOSED);
			irpProject.setOffpersonid(irpUser.getUserid());//默认负责人为创建者
 			//添加关键词
			String ckey=irpTagService.addTag(irpProject.getProkey());
			irpProject.setProkey(ckey);
			
			irpProjectDAO.insertSelective(irpProject);
			
			//默认项目有一个成员为自己
			irpProjectPersonService.addProjectCruer(irpUser.getUserid(),IrpProjectPerson.IS_STATE,irpProject.getProjectid(),IrpProjectPerson.IS_STATUS);
			nCount=1;
		} catch (SQLException e) {
			e.printStackTrace();
			nCount=0;
		}
		return nCount;
	}
	
	@Override
	public int addProjectPhone(IrpProject irpProject) {
		// TODO Auto-generated method stub
		int nCount=0;
		try { 
			irpProject.setProjectid(TableIdUtil.getNextId(IrpProject.TABLE_NAME));
			//处理项目图标
			String tempImg=irpProject.getProjectfile();
			if(tempImg!=null && tempImg.trim().length()>0){
				//获得临时文件名 
				String sFilePath = SysFileUtil.getFilePathByFileName(tempImg);
				//拿到真实的名字， 然后放到永久目录下面，然后保存
				String newFileName=SysFileUtil.saveFile(new File(sFilePath), SysFileUtil.FILE_TYPE_ATTACHED_PIC); 
				//将真实名称保存，并保存一个附件记录
				irpProject.setProjectfile(newFileName);
				String newFilePath=SysFileUtil.getFilePathByFileName(newFileName); 
				irpAttachedService.addFile(irpProject.getProjectid(), 0L, newFileName, newFileName, newFileName, IrpAttached.PROJECT_DOCIDTYPE, newFilePath,null,IrpAttachedType.JPG_FIELD_NAME,0);
			}
			irpProject.setIsclosed(IrpProject.NOT_CLOSED);
 			//添加关键词
			String ckey=irpTagService.addTag(irpProject.getProkey());
			irpProject.setProkey(ckey);
			
			irpProjectDAO.insertSelective(irpProject);
			
			//默认项目有一个成员为自己
			irpProjectPersonService.addProjectCruer1(irpProject.getCruserid(),IrpProjectPerson.IS_STATE,irpProject.getProjectid(),IrpProjectPerson.IS_STATUS);
			nCount=1;
		} catch (SQLException e) {
			e.printStackTrace();
			nCount=0;
		}
		return nCount;
	}
	@Override
	public int deleteProject(Long _projectId) {
		// TODO Auto-generated method stub
		int nCount=0;
		try { 
			nCount=irpProjectDAO.deleteByPrimaryKey(_projectId);
			//删除人
			irpProjectPersonService.deleteProjectPersonByProjectId(_projectId);
			//删除附件
			irpAttachedService.deleteFileByExpt(_projectId, IrpAttached.PROJECT_DOCIDTYPE);
			//删除任务成员
			IrpProjectTaskPersonExample taskPersonExample=new IrpProjectTaskPersonExample();
			taskPersonExample.createCriteria().andProjectidEqualTo(_projectId);
			irpProjectTaskPersonService.deleteTaskPerson(taskPersonExample);
			//删除动态
			IrpProjectShareTaskExample projectShareTaskExample =new IrpProjectShareTaskExample();
			projectShareTaskExample.createCriteria().andProjectidEqualTo(_projectId);
			irpProjectShareTaskService.deleteShareTaskByExample(projectShareTaskExample);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			nCount=0;
		}
		return nCount;
	}

	@Override
	public int updateProject(IrpProject irpProject) {
		int nCount=0;
		try {
			String cvalue=irpTagService.addTag(irpProject.getProkey());
			irpProject.setProkey(cvalue);
			//修改项目图标字段有值
			if(irpProject.getProjectfile()!=null && irpProject.getProjectfile().trim().length()>0){
				IrpProject  dbProject=irpProjectDAO.selectByPrimaryKey(irpProject.getProjectid());
				if(dbProject!=null){
					String dbProjectFile=dbProject.getProjectfile();
					//如果文件名称相同(修改项目状态和时间的时候)
					if(dbProjectFile!=null && dbProjectFile.trim().length()>0 &&irpProject.getProjectfile()!=null&&dbProjectFile.trim().equals(irpProject.getProjectfile().trim())){
					}else{
						//获得临时文件名 
						String sFilePath = SysFileUtil.getFilePathByFileName(irpProject.getProjectfile());
						//获取永久文件名
						String newFileName=SysFileUtil.saveFile(new File(sFilePath), SysFileUtil.FILE_TYPE_ATTACHED_PIC); 
						irpProject.setProjectfile(newFileName);
						//如果有文件名称并且文件名称和老文件名称不相同，就删除
						if(dbProjectFile!=null && dbProjectFile.trim().length()>0 &&irpProject.getProjectfile()!=null&&!dbProjectFile.trim().equals(irpProject.getProjectfile().trim())){
							irpAttachedService.deleteFileByExpt(irpProject.getProjectid(), IrpAttached.PROJECT_DOCIDTYPE, 0L);
						}
						String newFilePath=SysFileUtil.getFilePathByFileName(irpProject.getProjectfile()); 
						irpAttachedService.addFile(irpProject.getProjectid(), 0L,irpProject.getProjectfile(), irpProject.getProjectfile(), irpProject.getProjectfile(), IrpAttached.PROJECT_DOCIDTYPE, newFilePath,null,IrpAttachedType.JPG_FIELD_NAME,0);	
					}
					}
			}
			nCount=irpProjectDAO.updateByPrimaryKeySelective(irpProject);
		} catch (Exception e) { e.printStackTrace(); nCount=0; }
		return nCount;
	}
	@Override
	public IrpProject findProjectById(Long _projectId) {
		// TODO Auto-generated method stub
		IrpProject irpProject=null;
		try {
			irpProject=irpProjectDAO.selectByPrimaryKey(_projectId);
			if(irpProject!=null){
				IrpUser  projectOffPerson=irpUserService.findUserByUserId(irpProject.getOffpersonid()); 
				if(projectOffPerson!=null){
					irpProject.setOffPerson(projectOffPerson);
					irpProject.setUserPicUrl(projectOffPerson.getDefaultUserPic()); 	
				}
			} 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return irpProject;
	} 
	@Override
	public List<IrpProject> findProject() {
		// TODO Auto-generated method stub
		List<IrpProject> irpProjects=null;
		try {
			IrpProjectExample example=new IrpProjectExample();
			example.setOrderByClause(" crtime desc ");
			irpProjects=irpProjectDAO.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return irpProjects;
	}
	
	@Override
	public List<IrpProject> findProjectByMap(PageUtil pageUtil,HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		List<IrpProject> list=null;
		try {
			IrpProjectExample example=new IrpProjectExample();
			
			Criteria criteria = example.createCriteria();
			Long protype=(Long) map.get("protype");
			criteria.andProjecttypeEqualTo(protype);
			Object _offPersonId=map.get("offpersonid");
			if(_offPersonId!=null){
				criteria.andOffpersonidEqualTo((Long)_offPersonId);
			}
			List<Long> projectIdList=(List<Long>)map.get("projectIdList");
			if(projectIdList!=null){
				if(projectIdList.size()>1000){
					//处理Oracle在执行in时超过1000个报错
					List<Long> tempProjectIds=new ArrayList<Long>();
					for (int i = 0; i < projectIdList.size(); i++) {
						 tempProjectIds.add(projectIdList.get(i));
						 if(tempProjectIds.size()==1000|| (i+1)==projectIdList.size()){
							 example.or(example.createCriteria().andProjectidIn(tempProjectIds));
							 tempProjectIds=new ArrayList<Long>();
						 }
					}
				
				}else{
					criteria.andProjectidIn( projectIdList);
				}
			}
			Long isclosed=(Long)map.get("isclosed");
			if(isclosed!=null && isclosed!=0L ){
				criteria.andIsclosedEqualTo(isclosed);
			}
			//按照创建时间倒叙
			example.setOrderByClause(" crtime desc ");
			if(pageUtil!=null){
				
				list=irpProjectDAO.selectByExample(example, pageUtil);
			}else{
				list=irpProjectDAO.selectByExample(example);
			}
			 for (int i = 0; i <list.size(); i++) {
				//初始化用户图片地址
				IrpUser user = irpUserService.findUserByUserId(list.get(i).getOffpersonid());
				if(user!=null){ 
					list.get(i).setUserPicUrl(user.getDefaultUserPic());  
					String cValue=LoginUtil.getUserNameString(user);
					user.setUsername(cValue);
					list.get(i).setOffPerson(user);
				}
			} 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return list;
	}
	@Override
	public List<IrpProject> findProjectByMap(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		List<IrpProject> list=null;
		try {
			IrpProjectExample example=new IrpProjectExample();
			
			Criteria criteria = example.createCriteria();
			Long protype =(Long) map.get("protype");
			criteria.andProjecttypeEqualTo(protype);
			Object _offPersonId=map.get("offpersonid");
			if(_offPersonId!=null){
				criteria.andOffpersonidEqualTo((Long)_offPersonId);
			}
			List<Long> projectIdList=(List<Long>)map.get("projectIdList");
			if(projectIdList!=null){
				if(projectIdList.size()>1000){
					//处理Oracle在执行in时超过1000个报错
					List<Long> tempProjectIds=new ArrayList<Long>();
					for (int i = 0; i < projectIdList.size(); i++) {
						 tempProjectIds.add(projectIdList.get(i));
						 if(tempProjectIds.size()==1000|| (i+1)==projectIdList.size()){
							 example.or(example.createCriteria().andProjectidIn(tempProjectIds));
							 tempProjectIds=new ArrayList<Long>();
						 }
					}
				
				}else{
					criteria.andProjectidIn( projectIdList);
				}
			}
			Long isclosed=(Long)map.get("isclosed");
			if(isclosed!=null && isclosed!=0L ){
				criteria.andIsclosedEqualTo(isclosed);
			}
			//按照创建时间倒叙
			example.setOrderByClause(" crtime desc ");
			list=irpProjectDAO.selectByExample(example);
			for (int i = 0; i <list.size(); i++) {
				//初始化用户图片地址
				IrpUser user = irpUserService.findUserByUserId(list.get(i).getOffpersonid());
				if(user!=null){ 
					list.get(i).setUserPicUrl(user.getDefaultUserPic());  
					String cValue=LoginUtil.getUserNameString(user);
					user.setUsername(cValue);
					list.get(i).setOffPerson(user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	@Override
	public int countProjectList(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		int nCount=0;
		
		try {
			IrpProjectExample example=new IrpProjectExample();
			
		 	Criteria criteria = example.createCriteria();
			Long protype=(Long) map.get("protype");
			criteria.andProjecttypeEqualTo(protype);
			Object _offPersonId=map.get("offpersonid");
			if(_offPersonId!=null){
				criteria.andOffpersonidEqualTo((Long)_offPersonId);
			}
			List<Long> projectIdList=(List<Long>)map.get("projectIdList");
			if(projectIdList!=null){
				if(projectIdList.size()>1000){
					//处理Oracle在执行in时超过1000个报错
					List<Long> tempProjectIds=new ArrayList<Long>();
					for (int i = 0; i < projectIdList.size(); i++) {
						 tempProjectIds.add(projectIdList.get(i));
						 if(tempProjectIds.size()==1000|| (i+1)==projectIdList.size()){
							 example.or(example.createCriteria().andProjectidIn(tempProjectIds));
							 tempProjectIds=new ArrayList<Long>();
						 }
					}
				
				}else{
					criteria.andProjectidIn( projectIdList);
				}
			}
			Long isclosed=(Long)map.get("isclosed");
			if(isclosed!=null && isclosed!=0L ){
				criteria.andIsclosedEqualTo(isclosed);
			}  
			nCount=irpProjectDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public int countFileByProjectId(Long projectid) {
		return irpAttachedService.countFile(projectid, IrpAttached.PROJECT_DOCIDTYPE);
	}
	@Override
	public List<IrpAttached> findAllFileByProjectid(Long projectid) {
		return irpAttachedService.getAttachedByAttDocId(projectid, IrpAttached.PROJECT_DOCIDTYPE);
	}
	@Override
	public List<IrpAttached> findAllFileByProjectid(Long projectid,PageUtil pageUtil) {
		return irpAttachedService.getAttachedByAttDocId(projectid, IrpAttached.PROJECT_DOCIDTYPE,pageUtil);
	}

	@Override
	public List<IrpProject> selectByExample(IrpProjectExample example)
			throws SQLException {
		return irpProjectDAO.selectByExample(example);
		
	}

	@Override
	public int updateByPrimaryKeySelective(IrpProject record)
			throws SQLException {
		return irpProjectDAO.updateByPrimaryKeySelective(record);
	}

	@Override
	public List findFileByTaskid(Long taskid) {
		return irpAttachedService.getAttachedByAttprop(taskid, IrpAttached.PROJECT_DOCIDTYPE);
	}

	@Override
	public List<IrpProject> findProjectByMapAndTime(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		List<IrpProject> list=null;
		try {
			IrpProjectExample example=new IrpProjectExample();
			
			Criteria criteria = example.createCriteria();
			Long protype=(Long) map.get("protype");
			criteria.andProjecttypeEqualTo(protype);
			Object _offPersonId=map.get("offpersonid");
			if(_offPersonId!=null){
				criteria.andOffpersonidEqualTo((Long)_offPersonId);
			}
			List<Long> projectIdList=(List<Long>)map.get("projectIdList");
			if(projectIdList!=null){
				if(projectIdList.size()>1000){
					//处理Oracle在执行in时超过1000个报错
					List<Long> tempProjectIds=new ArrayList<Long>();
					for (int i = 0; i < projectIdList.size(); i++) {
						 tempProjectIds.add(projectIdList.get(i));
						 if(tempProjectIds.size()==1000|| (i+1)==projectIdList.size()){
							 example.or(example.createCriteria().andProjectidIn(tempProjectIds));
							 tempProjectIds=new ArrayList<Long>();
						 }
					}
				
				}else{
					criteria.andProjectidIn( projectIdList);
				}
			}
			Long isclosed=(Long)map.get("isclosed");
			if(isclosed!=null && isclosed!=0L ){
				criteria.andIsclosedEqualTo(isclosed);
			}
			criteria.andEndtimeGreaterThan(new Date()).andIspublishNotEqualTo(IrpProject.NOT_PUBLISH);
			//按照创建时间倒叙
			example.setOrderByClause(" crtime desc ");
			list=irpProjectDAO.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return list;
	}
}
