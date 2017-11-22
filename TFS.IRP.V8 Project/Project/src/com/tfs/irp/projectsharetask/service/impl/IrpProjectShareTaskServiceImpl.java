package com.tfs.irp.projectsharetask.service.impl;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
 
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.attachedtype.entity.IrpAttachedType;
import com.tfs.irp.attachedtype.service.IrpAttachedTypeService;
import com.tfs.irp.projectsharetask.dao.IrpProjectShareTaskDAO;
import com.tfs.irp.projectsharetask.entity.IrpProjectShareTask;
import com.tfs.irp.projectsharetask.entity.IrpProjectShareTaskExample;
import com.tfs.irp.projectsharetask.entity.IrpProjectShareTaskExample.Criteria;
import com.tfs.irp.projectsharetask.service.IrpProjectShareTaskService;
import com.tfs.irp.projecttaskperson.entity.IrpProjectTaskPerson;
import com.tfs.irp.projecttaskperson.entity.IrpProjectTaskPersonExample;
import com.tfs.irp.projecttaskperson.service.IrpProjectTaskPersonService; 
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.FileUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysFileUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpProjectShareTaskServiceImpl implements
		IrpProjectShareTaskService {

		private IrpProjectShareTaskDAO irpProjectShareTaskDAO;

		private IrpProjectTaskPersonService irpProjectTaskPersonService;
		
		private IrpAttachedTypeService irpAttachedTypeService;
		
		private IrpAttachedService irpAttachedService;
		
		
		public IrpAttachedTypeService getIrpAttachedTypeService() {
			return irpAttachedTypeService;
		}

		public IrpAttachedService getIrpAttachedService() {
			return irpAttachedService;
		}

		public void setIrpAttachedTypeService(
				IrpAttachedTypeService irpAttachedTypeService) {
			this.irpAttachedTypeService = irpAttachedTypeService;
		}

		public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
			this.irpAttachedService = irpAttachedService;
		}

		public IrpProjectTaskPersonService getIrpProjectTaskPersonService() {
			return irpProjectTaskPersonService;
		}

		public void setIrpProjectTaskPersonService(
				IrpProjectTaskPersonService irpProjectTaskPersonService) {
			this.irpProjectTaskPersonService = irpProjectTaskPersonService;
		}

		public IrpProjectShareTaskDAO getIrpProjectShareTaskDAO() {
			return irpProjectShareTaskDAO;
		}

		public void setIrpProjectShareTaskDAO(
				IrpProjectShareTaskDAO irpProjectShareTaskDAO) {
			this.irpProjectShareTaskDAO = irpProjectShareTaskDAO;
		}
		
		@Override
		public List<IrpProjectShareTask> findShareTaskByMap( HashMap<String, Object> map,PageUtil pageUtil) {
		// TODO Auto-generated method stub
			 List<IrpProjectShareTask> list=null;
			 try {
				 IrpProjectShareTaskExample irpProjectShareTaskExample=new IrpProjectShareTaskExample();
				 Criteria criteria = irpProjectShareTaskExample.createCriteria();
				 Long parentid=(Long)map.get("parentid");

				 if(parentid!=null){
					  criteria.andParentidEqualTo(parentid);
				 }
				 
				 Long themetype=(Long)map.get("themetype");
				 if(themetype!=null){
					  criteria.andThemetypeEqualTo(themetype);
				 }				 
				 
				 Long projectid=(Long)map.get("projectid");
				 if(projectid!=null && projectid!=0L){
					 criteria.andProjectidEqualTo(projectid);
				 }
				 Object offPersonId=map.get("offPersonId");
				 if(offPersonId!=null ){
					 criteria.andOffpersonidEqualTo((Long)offPersonId);
				 }
				 Object orderByClause=map.get("orderByClause");
				 if(orderByClause!=null){
					 irpProjectShareTaskExample.setOrderByClause(orderByClause.toString());
				 }
				 Object isJoin=(Object)map.get("isJoin");
				 if( isJoin!=null ){//查询我参与的任务调用此方法是执行//当前登陆者不是任务负责人
					 IrpUser irpUser=LoginUtil.getLoginUser();
					 criteria.andOffpersonidNotEqualTo(irpUser.getUserid());
				 }
				 Long isState=(Long)map.get("isState");
				 
				 if(isState!=null && isState==IrpProjectShareTask.IS_TASK_STATE){//如果是任务
					 criteria.andIsstateEqualTo(IrpProjectShareTask.IS_TASK_STATE);
				 } 
				 //这个只有在查询我创建给别人的任务，才会走
				 Object cruserid=map.get("cruserid");//说明查看的是创建者的
				 if(cruserid!=null){
					 criteria.andCruseridEqualTo(new Long(cruserid+""));//创建者的动态
					 criteria.andOffpersonidNotEqualTo(new Long(cruserid+""));
				 }
				//是否关闭
				 Object isClosed=map.get("isClosed");
				 if(isClosed!=null){
					 criteria.andIsclosedEqualTo(Long.parseLong(isClosed.toString()));
				 }
				 Object shareTaskIds=map.get("shareTaskIds");
				 if(shareTaskIds!=null){
					 List<Long> shareTaskIdsList=(List<Long>)shareTaskIds;
					 if(shareTaskIdsList!=null && shareTaskIdsList.size()>0){
						  //处理Oracle在执行in时超过1000个报错
						 if(shareTaskIdsList.size()>1000){
							 List<Long> tempShareTaskIds=new ArrayList<Long>();
							 for (int i = 0; i < shareTaskIdsList.size(); i++) {
								tempShareTaskIds.add(shareTaskIdsList.get(i));
								if(tempShareTaskIds.size()==1000  || (i+1)==shareTaskIdsList.size()){
									irpProjectShareTaskExample.or(irpProjectShareTaskExample.createCriteria().andSharetaskidIn(tempShareTaskIds));
									tempShareTaskIds=new ArrayList<Long>();
								}
							}
						 }else{
							 criteria.andSharetaskidIn(shareTaskIdsList);
						 }
					 }
				 } 
				 if(pageUtil!=null){
					 list=irpProjectShareTaskDAO.selectByExample(irpProjectShareTaskExample,pageUtil);
				 }else{
					 list=irpProjectShareTaskDAO.selectByExample(irpProjectShareTaskExample);
					 
				 }
				 
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		return list;
		}
		
		@Override
		public List<IrpProjectShareTask> findShareTaskByMap( HashMap<String, Object> map,PageUtil pageUtil,Boolean _time) {
		// TODO Auto-generated method stub
			 List<IrpProjectShareTask> list=null;
			 try {
				 IrpProjectShareTaskExample irpProjectShareTaskExample=new IrpProjectShareTaskExample();
				 Criteria criteria = irpProjectShareTaskExample.createCriteria();
				 Long parentid=(Long)map.get("parentid");

				 if(parentid!=null){
					  criteria.andParentidEqualTo(parentid);
				 }
				 
				 String sharedesc =(String) map.get("sharedesc");

				 if(!(sharedesc.equals("启动了任务"))){
					 criteria.andSharedescEqualTo(sharedesc);
				 }

				 Long themetype=(Long)map.get("themetype");
				 if(themetype!=null){
					  criteria.andThemetypeEqualTo(themetype);
				 }				 
			 
				 Long projectid=(Long)map.get("projectid");
				 if(projectid!=null && projectid!=0L){
					 criteria.andProjectidEqualTo(projectid);
				 }
				 
				 Object offPersonId=map.get("offPersonId");
				 if(offPersonId!=null ){
					 criteria.andOffpersonidEqualTo((Long)offPersonId);
				 }
				 
				 Object orderByClause=map.get("orderByClause");
				 if(orderByClause!=null){
					 irpProjectShareTaskExample.setOrderByClause(orderByClause.toString());
				 }
				 
				 Object isJoin=(Object)map.get("isJoin");
				 if( isJoin!=null ){//查询我参与的任务调用此方法是执行//当前登陆者不是任务负责人
					 IrpUser irpUser=LoginUtil.getLoginUser();
					 criteria.andOffpersonidNotEqualTo(irpUser.getUserid());
				 }
				 
				 Long isState=(Long)map.get("isState");				 
				 if(isState!=null && isState==IrpProjectShareTask.IS_TASK_STATE){//如果是任务
					 criteria.andIsstateEqualTo(IrpProjectShareTask.IS_TASK_STATE);
				 } 
			 
				 //这个只有在查询我创建给别人的任务，才会走
				 Object cruserid=map.get("cruserid");//说明查看的是创建者的
				 if(cruserid!=null){
					 criteria.andCruseridEqualTo(new Long(cruserid+""));//创建者的动态
					 criteria.andOffpersonidNotEqualTo(new Long(cruserid+""));
				 }
			 
				//是否关闭
				 Object isClosed=map.get("isClosed");
				 if(isClosed!=null){
					 criteria.andIsclosedEqualTo(Long.parseLong(isClosed.toString()));
				 }
			 
				 Object shareTaskIds=map.get("shareTaskIds");
				 if(shareTaskIds!=null){
					 List<Long> shareTaskIdsList=(List<Long>)shareTaskIds;
					 if(shareTaskIdsList!=null && shareTaskIdsList.size()>0){
						  //处理Oracle在执行in时超过1000个报错
						 if(shareTaskIdsList.size()>1000){
							 List<Long> tempShareTaskIds=new ArrayList<Long>();
							 for (int i = 0; i < shareTaskIdsList.size(); i++) {
								tempShareTaskIds.add(shareTaskIdsList.get(i));
								if(tempShareTaskIds.size()==1000  || (i+1)==shareTaskIdsList.size()){
									irpProjectShareTaskExample.or(irpProjectShareTaskExample.createCriteria().andSharetaskidIn(tempShareTaskIds));
									tempShareTaskIds=new ArrayList<Long>();
								}
							}
						 }else{
							 criteria.andSharetaskidIn(shareTaskIdsList);
						 }
					 }
				 }
				 irpProjectShareTaskExample.setOrderByClause("crtime desc" );
				 PageUtil pageUtils = new PageUtil(1, 1, 0);
				 list=irpProjectShareTaskDAO.selectByExample(irpProjectShareTaskExample,pageUtils);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		return list;
		}
		@Override
		public int addShareTask(IrpProjectShareTask shareTask,String[] _attFileList) {
			int nCount=0;
			try {
				Long _shareTaskId=TableIdUtil.getNextId(IrpProjectShareTask.TABLE_NAME);
				shareTask.setSharetaskid(_shareTaskId);
				irpProjectShareTaskDAO.insertSelective(shareTask);
				//添加附件
				if(_attFileList!=null &&_attFileList.length>0){
					for (int i = 0; i < _attFileList.length; i++) {//attfile#attdesc
						String data=_attFileList[i];
						if(data!=null && data.length()>0){
							String[] names=data.split("#");
							//获得临时文件名 
							String sFilePath = SysFileUtil.getFilePathByFileName(names[0]);
							//拿到真实的名字， 然后放到永久目录下面，然后保存
							String newFileName="";
							String _lastName=FileUtil.findFileExt(names[0]); 
							Long typeId=irpAttachedTypeService.findAttachedTypeIdByFileExt(_lastName);
							if(IrpAttachedType.JPG_FIELD_NAME.intValue()==typeId.intValue()){ //这是创建文档之后的附件名字
								  newFileName=SysFileUtil.saveFile(new File(sFilePath), SysFileUtil.FILE_TYPE_ATTACHED_PIC);
							}else{
								  newFileName=SysFileUtil.saveFile(new File(sFilePath), SysFileUtil.FILE_TYPE_ATTACHED_FILE);
							} 
							String newFilePath=SysFileUtil.getFilePathByFileName(newFileName);
							irpAttachedService.addFile(shareTask.getProjectid(), _shareTaskId, newFileName, names[1], names[1], IrpAttached.PROJECT_DOCIDTYPE, newFilePath,null,typeId,i);
						}
					}
				} 
				nCount=1;
			} catch (Exception e) {
				nCount=0;
				e.printStackTrace();
			}
		return nCount;
		}
		 
		@Override
		public List<IrpProjectShareTask> findReplayShareTask(Long _parentId) {
		// TODO Auto-generated method stub
			List<IrpProjectShareTask> list=null; 
			 try {
				 IrpProjectShareTaskExample irpProjectShareTaskExample=new IrpProjectShareTaskExample();
				 
				 irpProjectShareTaskExample.createCriteria().andParentidEqualTo(_parentId);
				 irpProjectShareTaskExample.setOrderByClause(" crtime desc ");  
				 
				 list=irpProjectShareTaskDAO.selectByExample(irpProjectShareTaskExample);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
		}

		@Override
		public int deleteShareTask(Long _shareTaskId) {
		// TODO Auto-generated method stub
			int nCount=0;
			try {
				IrpProjectShareTask irpProjectShareTask=irpProjectShareTaskDAO.selectByPrimaryKey(_shareTaskId);
				if(irpProjectShareTask!=null){
					//删掉这个动态所有附件
					irpAttachedService.deleteFileByExpt(irpProjectShareTask.getProjectid(), IrpAttached.PROJECT_DOCIDTYPE, _shareTaskId);
					nCount=nCount+irpProjectShareTaskDAO.deleteByPrimaryKey(_shareTaskId);
					//删除掉他下面的所有子回复
					IrpProjectShareTaskExample example=new IrpProjectShareTaskExample();
					example.createCriteria().andParentidEqualTo(_shareTaskId);
					nCount=nCount+irpProjectShareTaskDAO.deleteByExample(example);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return nCount;
		}
		@Override
		public int addShareTask(IrpProjectShareTask shareTask,String projectPersonIdsString) {
		// TODO Auto-generated method stub
			int nCount=0;
			try {
				Long irpUserId=LoginUtil.getLoginUserId();
				
				Long sharetaskId=TableIdUtil.getNextId(IrpProjectShareTask.TABLE_NAME);
				shareTask.setCruserid(irpUserId);
				 
				shareTask.setIscallmessage(IrpProjectShareTask.NO_CALL);
				Date crtime=new Date();
				shareTask.setCrtime(crtime);
				if(shareTask.getStarttime()==null){
					shareTask.setStarttime(crtime);
				}
				if(shareTask.getOffpersonid()==null ||shareTask.getOffpersonid()==0L){
					shareTask.setOffpersonid(irpUserId);
				}
				shareTask.setSharetaskid(sharetaskId);
				if(projectPersonIdsString!=null && projectPersonIdsString.length()!=0){
					//添加任务成员表
					String[] personIdAttr=projectPersonIdsString.split(",");
					if(personIdAttr!=null && personIdAttr.length>0){
						for (int j = 0; j < personIdAttr.length; j++) {
							String personIDStr=personIdAttr[j];
							if(personIDStr!=null && personIDStr.length()>0){ 
								irpProjectTaskPersonService.addPerson(new Long(personIDStr), sharetaskId, shareTask.getProjectid());
							}
						} 
					} 
				}
				irpProjectShareTaskDAO.insertSelective(shareTask);
				nCount=1;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				nCount=0;
				e.printStackTrace();
			}
		return nCount;
		} 
		@Override
		public int countShareTaskByExample(IrpProjectShareTaskExample  example) {
		// TODO Auto-generated method stub
			int nCount=0; 
			try { 
				nCount=irpProjectShareTaskDAO.countByExample(example);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return nCount;
		}
		@Override
		public int addShareTask(IrpProjectShareTask shareTask,String[] _attFileList,String[] _taskPersons) {
		// TODO Auto-generated method stub
			 	 int nCount=0; 
					try {
						Long _shareTaskId=TableIdUtil.getNextId(IrpProjectShareTask.TABLE_NAME);
						shareTask.setSharetaskid(_shareTaskId);
						irpProjectShareTaskDAO.insertSelective(shareTask);
						//判断他是不是任务
						if(shareTask.getIsstate()==IrpProjectShareTask.IS_TASK_STATE){
							//说明有任务成员的参与
							 if(_taskPersons!=null && _taskPersons.length>0){
						 		 /**
						 		  * 添加任务成员，
						 		  * 
						 		  */ 
								 for (int i = 0; i < _taskPersons.length; i++) {
									 if(_taskPersons[i]!=null &&!_taskPersons[i].equals("")){
										 Long personId = new Long(_taskPersons[i]);
										 irpProjectTaskPersonService.addPerson(personId, _shareTaskId, shareTask.getProjectid());
									  }
								} 
						 	 } 
							 /**
							  * 并且任务对象中的负责人也需要添加一次 如果没有选择负责人，则默认自己为负责人
							  */ 
							irpProjectTaskPersonService.addPerson(shareTask.getOffpersonid(), _shareTaskId, shareTask.getProjectid());
						} 
						//添加附件
						if(_attFileList!=null &&_attFileList.length>0){
								
							for (int i = 0; i < _attFileList.length; i++) {//attfile#attdesc
								String data=_attFileList[i];
								if(data!=null && data.length()>0){
									String[] names=data.split("#");
									 
									//获得临时文件名 
									String sFilePath = SysFileUtil.getFilePathByFileName(names[0]);
									//拿到真实的名字， 然后放到永久目录下面，然后保存
									String newFileName="";
									String _lastName=FileUtil.findFileExt(names[0]); 
									Long typeId=irpAttachedTypeService.findAttachedTypeIdByFileExt(_lastName);
									if(IrpAttachedType.JPG_FIELD_NAME.intValue()==typeId.intValue()){ //这是创建文档之后的附件名字
										  newFileName=SysFileUtil.saveFile(new File(sFilePath), SysFileUtil.FILE_TYPE_ATTACHED_PIC);
									}else{
										  newFileName=SysFileUtil.saveFile(new File(sFilePath), SysFileUtil.FILE_TYPE_ATTACHED_FILE);
									} 
									String newFilePath=SysFileUtil.getFilePathByFileName(newFileName);
									irpAttachedService.addFile(shareTask.getProjectid(), _shareTaskId, newFileName, names[1], names[1], IrpAttached.PROJECT_DOCIDTYPE, newFilePath,null,typeId,i);
								}
							}
						} 
						shareTask.setParentid(shareTask.getSharetaskid());//将这个动态的parentid这是为动态主键
						shareTask.setSharedesc("启动了任务"); 
						String str[]=null;
						addShareTask(shareTask,str);
						nCount=1;
					} catch (Exception e) {
						nCount=0;
						e.printStackTrace();
					}  
			 	
			 	 return nCount;
		} 
		 @Override
		public IrpProjectShareTask findShareTaskById(Long _shareTaskId) {
		// TODO Auto-generated method stub
			 IrpProjectShareTask irpProjectShareTask=null;
			 try {
				 irpProjectShareTask=irpProjectShareTaskDAO.selectByPrimaryKey(_shareTaskId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return irpProjectShareTask;
		}
	@Override
		public Integer countShareTaskByMap(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		Integer nCount=0;
		try {
			 IrpProjectShareTaskExample irpProjectShareTaskExample=new IrpProjectShareTaskExample();
			 Criteria criteria = irpProjectShareTaskExample.createCriteria();
			 Long parentid=(Long)map.get("parentid");
			 if(parentid!=null){
				  criteria.andParentidEqualTo(parentid);
			 }
			 Long themetype=(Long)map.get("themetype");
			 if(themetype!=null){
				  criteria.andThemetypeEqualTo(themetype);
			 }
			 Long projectid=(Long)map.get("projectid");
			 if(projectid!=null && projectid!=0L){
				 criteria.andProjectidEqualTo(projectid);
			 }
			 Object offPersonId=map.get("offPersonId");
			 if(offPersonId!=null ){
				 criteria.andOffpersonidEqualTo((Long)offPersonId);
			 }
			 Object orderByClause=map.get("orderByClause");
			 if(orderByClause!=null){
				 irpProjectShareTaskExample.setOrderByClause(orderByClause.toString());
			 }
			 
			 Long isState=(Long)map.get("isState");
			 if(isState!=null && isState==IrpProjectShareTask.IS_TASK_STATE){//如果是任务
				 criteria.andIsstateEqualTo(IrpProjectShareTask.IS_TASK_STATE);
			 } 
			 Object cruserid=map.get("cruserid");
			 if(cruserid!=null){
				 criteria.andCruseridEqualTo(new Long(cruserid+""));//创建者的动态
			 }
			 //是否关闭
			 Object isClosed=map.get("isClosed");
			 if(isClosed!=null){
				 criteria.andIsclosedEqualTo(Long.parseLong(isClosed.toString()));
			 }
			 Object shareTaskIds=map.get("shareTaskIds");
			 if(shareTaskIds!=null){
				 //处理Oracle在执行in时超过1000个报错
				 List<Long> shareTaskIdsList=(List<Long>)shareTaskIds;
				 if(shareTaskIdsList.size()>1000){
					 List<Long> tempShareTaskIds=new ArrayList<Long>();
					 for (int i = 0; i < shareTaskIdsList.size(); i++) {
						 tempShareTaskIds.add(shareTaskIdsList.get(i));
						 if(tempShareTaskIds.size()==1000 || (i+1)==shareTaskIdsList.size()){
							 irpProjectShareTaskExample.or(irpProjectShareTaskExample.createCriteria().andSharetaskidIn(tempShareTaskIds));
							 tempShareTaskIds=new ArrayList<Long>();
						 }
					}
				 }else{
					 criteria.andSharetaskidIn(shareTaskIdsList);
				 }
			 } 
			 
			 nCount= irpProjectShareTaskDAO.countByExample(irpProjectShareTaskExample);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nCount;
		}	 
	@Override
	public int updateShareTask(IrpProjectShareTask _irpProjectShareTask,String[] _taskPersons) {
	// TODO Auto-generated method stub
		int nCount=0;
		IrpProjectShareTaskExample example=new IrpProjectShareTaskExample();
		example.createCriteria().andSharetaskidEqualTo(_irpProjectShareTask.getSharetaskid());
		try {
			//设置此次修改时间
			_irpProjectShareTask.setUpdatetime(new Date());
			nCount=irpProjectShareTaskDAO.updateByExampleSelective(_irpProjectShareTask, example);
			//判断他是不是任务
			long Isstate=_irpProjectShareTask.getIsstate();
			if(Isstate==IrpProjectShareTask.IS_TASK_STATE.longValue()){
				//说明有任务成员的参与
				//查询他之前的任务成员
				List<Long> oldDatas=irpProjectTaskPersonService.findTaskPersonIds(_irpProjectShareTask.getSharetaskid(), _irpProjectShareTask.getProjectid());
				if(_taskPersons!=null){
					for (int i = 0; i < _taskPersons.length; i++) {
						 if(_taskPersons[i]!=null &&!_taskPersons[i].equals("")){
							 Long personId = new Long(_taskPersons[i]);
							 irpProjectTaskPersonService.addPerson(personId, _irpProjectShareTask.getSharetaskid(), _irpProjectShareTask.getProjectid());
						 }
					}
					//他的任务负责人
					irpProjectTaskPersonService.addPerson(_irpProjectShareTask.getOffpersonid(), _irpProjectShareTask.getSharetaskid(), _irpProjectShareTask.getProjectid());
					IrpProjectTaskPersonExample personExample =new IrpProjectTaskPersonExample();
					if(oldDatas!=null && oldDatas.size()>0){
						personExample.createCriteria().andTaskpersonidIn(oldDatas)
															.andProjectidEqualTo(_irpProjectShareTask.getProjectid())
															.andSharetaskidEqualTo(_irpProjectShareTask.getSharetaskid());
							irpProjectTaskPersonService.deleteTaskPerson(personExample);
					}
			 	 } 
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public void deleteShareTaskByExample(IrpProjectShareTaskExample example) {
	try {
		irpProjectShareTaskDAO.deleteByExample(example);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
	@Override
	public List findAllFileByProjectIdAndShareId(Long projectid, Long sharetaskId) {
		return irpAttachedService.getAttachedByAttDocId(projectid, sharetaskId, IrpAttached.PROJECT_DOCIDTYPE);
	}

	@Override
	public List<IrpProjectShareTask> findallshare(Long projectid) {
		 List<IrpProjectShareTask> share=null;
		 IrpProjectShareTaskExample irpProjectShareTaskExample=new IrpProjectShareTaskExample();
		 Criteria criteria = irpProjectShareTaskExample.createCriteria();
		 criteria.andProjectidEqualTo(projectid).andIsstateEqualTo(2l).andParentidEqualTo(0l);
		 try {
			 share=irpProjectShareTaskDAO.selectByExampleWithBLOBs(irpProjectShareTaskExample);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return share;
	}

	@Override
	public List<IrpProjectShareTask> findShareTaskByProjectid(List<Long> projectIdList) {
		 List<IrpProjectShareTask> share=null;
		 IrpProjectShareTaskExample irpProjectShareTaskExample=new IrpProjectShareTaskExample();
		 Criteria criteria = irpProjectShareTaskExample.createCriteria();
		 criteria.andProjectidIn(projectIdList).andIsstateEqualTo(2l).andParentidEqualTo(0l).andOffpersonidEqualTo(LoginUtil.getLoginUserId()).andThemetypeEqualTo(0L);
		 irpProjectShareTaskExample.setOrderByClause("updatetime desc");
		 try {
			 share=irpProjectShareTaskDAO.selectByExampleWithBLOBs(irpProjectShareTaskExample);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return share;
	}
}