package com.tfs.irp.projectperson.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.tfs.irp.bug.entity.IrpBug;
import com.tfs.irp.messagecontent.entity.IrpMessageContent;
import com.tfs.irp.messagecontent.service.IrpMessageContentService;
import com.tfs.irp.project.entity.IrpProject;
import com.tfs.irp.projectperson.dao.IrpProjectPersonDAO;
import com.tfs.irp.projectperson.entity.IrpProjectPerson;
import com.tfs.irp.projectperson.entity.IrpProjectPersonExample;
import com.tfs.irp.projectperson.entity.IrpProjectPersonExample.Criteria;
import com.tfs.irp.projectperson.service.IrpProjectPersonService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.entity.IrpUserExample;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpProjectPersonServiceImpl implements IrpProjectPersonService {
	private IrpProjectPersonDAO irpProjectPersonDAO;
	 
	 private IrpMessageContentService irpMessageContentService;
	 
	private  IrpUserService irpUserService;
	public IrpProjectPersonDAO getIrpProjectPersonDAO() {
		return irpProjectPersonDAO;
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public void setIrpProjectPersonDAO(IrpProjectPersonDAO irpProjectPersonDAO) {
		this.irpProjectPersonDAO = irpProjectPersonDAO;
	}
	@Override
	public int addProjectCruer(Long _personId,Long _state,Long _projectId,Long _status) {
		int nCount=0;
		try {
			IrpUser irpUser=LoginUtil.getLoginUser();
			IrpProjectPerson projectPerson=new IrpProjectPerson();
			projectPerson.setProperid(TableIdUtil.getNextId(IrpProjectPerson.TABLE_NAME));
			projectPerson.setCrtime(new Date());
			projectPerson.setPrid(_projectId);
			projectPerson.setCruserid(irpUser.getUserid());
			projectPerson.setPersonid(_personId);
			projectPerson.setIsstate(_state);
			projectPerson.setStatus(_status);// 账号 状态
			irpProjectPersonDAO.insertSelective(projectPerson);
			nCount=1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public int addProjectCruer1(Long _personId,Long _state,Long _projectId,Long _status) {
		int nCount=0;
		try {
			IrpUser irpUser=LoginUtil.getLoginUser();
			IrpProjectPerson projectPerson=new IrpProjectPerson();
			projectPerson.setProperid(TableIdUtil.getNextId(IrpProjectPerson.TABLE_NAME));
			projectPerson.setCrtime(new Date());
			projectPerson.setPrid(_projectId);
			projectPerson.setCruserid(_personId);
			projectPerson.setPersonid(_personId);
			projectPerson.setIsstate(_state);
			projectPerson.setStatus(_status);// 账号 状态
			irpProjectPersonDAO.insertSelective(projectPerson);
			nCount=1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public List<Long> findProjectIdByPersonId(Long _personId,Long _isState) {
		 List<Long>  list=null;
		try {
			IrpProjectPersonExample example=new IrpProjectPersonExample();
			Criteria criteria = example.createCriteria();
			
			criteria.andPersonidEqualTo(_personId);
			if(_isState!=null && _isState!=0L){
				criteria.andIsstateEqualTo(_isState);
			}
			criteria.andStatusEqualTo(IrpProjectPerson.IS_STATUS);
			list=irpProjectPersonDAO.findProjectIdByExample(example); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public void deleteProjectPersonByProjectId(Long _projectId) {
		// TODO Auto-generated method stub 
		try {
			IrpProjectPersonExample example=new IrpProjectPersonExample();
			example.createCriteria().andPridEqualTo(_projectId);
			irpProjectPersonDAO.deleteByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public List<Long> findPersonIdByProjectId(Long _projectId, Long _isState) {
				 List<Long>  list=null;
				try {
					IrpProjectPersonExample example=new IrpProjectPersonExample();
					Criteria criteria = example.createCriteria();
					if(_projectId!=null &&_projectId!=0L){
						criteria.andPridEqualTo(_projectId);
					} 
					if(_isState!=null && _isState!=0L){
						criteria.andIsstateEqualTo(_isState);
					}
					criteria.andStatusEqualTo(IrpProjectPerson.IS_STATUS);
					list=irpProjectPersonDAO.selectPersonIdByProjectId(example); 
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return list;
	}
	@Override
	public List<Long> findPersonIdByProjectId(Long _projectId, Long _isState,Long isStatus) {
		
		if(isStatus==null) 
			return findPersonIdByProjectId(_projectId, _isState);
		List<Long>  list=null;
		try {
			IrpProjectPersonExample example=new IrpProjectPersonExample();
			Criteria criteria = example.createCriteria();
			if(_projectId!=null &&_projectId!=0L){
				criteria.andPridEqualTo(_projectId);
			} 
			if(_isState!=null && _isState!=0L){
				criteria.andIsstateEqualTo(_isState);
			}
			criteria.andStatusEqualTo(isStatus);
			criteria.andStatusEqualTo(IrpProjectPerson.IS_STATUS);
			list=irpProjectPersonDAO.selectPersonIdByProjectId(example); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				return list;
	}
	@Override
	public List<IrpProjectPerson> findPersonByExample(
			IrpProjectPersonExample example) {
		// TODO Auto-generated method stub
		List<IrpProjectPerson> list=null;
		try {
			list=irpProjectPersonDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int deletePersonByProjectId(Long _projectId, Long _userId) {
		// TODO Auto-generated method stub
		int nCount=0;
		try {
			if(_userId!=null){
				IrpProjectPersonExample example=new IrpProjectPersonExample();
				example.createCriteria().andPridEqualTo(_projectId)
										.andPersonidEqualTo(_userId);
				nCount=irpProjectPersonDAO.deleteByExample(example);
			} 
		} catch (SQLException e) { 
			e.printStackTrace();
		} 
		return nCount;
	}
	@Override
	public Integer findProjectPersonCount(Long _projectId, Long _isState,Long _status) {
		// TODO Auto-generated method stub
		Integer nCount=null;
		
		IrpProjectPersonExample example=new IrpProjectPersonExample();
		example.createCriteria().andPridEqualTo(_projectId)
								.andIsstateEqualTo(_isState)
								.andStatusEqualTo(_status);
		try {
			nCount= irpProjectPersonDAO.countByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public int updateProjectPerson(IrpProjectPerson person) {
		// TODO Auto-generated method stub
		int nCount=0;
		try {
			nCount=irpProjectPersonDAO.updateByPrimaryKeySelective(person);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public int addProjectPerson(Long _personId, Long _state, Long _projectId, Long _status, Long _code,String _prname) {
				int nCount=0;
				try { 
					IrpUser irpUser=LoginUtil.getLoginUser();
					
					IrpProjectPersonExample example=new IrpProjectPersonExample();
						example.createCriteria().andPridEqualTo(_projectId)
												.andPersonidEqualTo(_personId); 
						List<IrpProjectPerson> irpProjectPersons=irpProjectPersonDAO.selectByExample(example);
						//查询有没有这个人，若查到判断他的状态是成员还是关注者
						if(irpProjectPersons!=null && irpProjectPersons.size()>0){
							IrpProjectPerson person=irpProjectPersons.get(0); 
							
							Long _statusL=person.getStatus();
							Long _isstate=person.getIsstate();
							if(_state.longValue()==IrpProjectPerson.IS_STATE.longValue()){//如果是添加成员,将这个人修改成成员
								if(_statusL!=null ||_isstate!=null){
									if(IrpProjectPerson.NOT_STATE.longValue()==_isstate.longValue()){//当前数据库人员为关注者，修改为成员
										person.setStatus(IrpProjectPerson.IS_STATUS);//开通
										person.setIsstate(IrpProjectPerson.IS_STATE);
									} 
								} 
							}else{//如果是关注者状态,修改他的注册码
								person.setCode(_code);
								if(_prname!=null && _prname.length()>0){
									String cValue=SysConfigUtil.getSysConfigValue("SHARE_PROJECT_ATTENTION"); 
									cValue=cValue.replace("#user#",LoginUtil.getUserNameString(irpUser));//昵称
									Date date=new Date();
				                	SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
				                	cValue=cValue.replace("#time#",dateFormat.format(date)); 
				                	String path=SysConfigUtil.getSysConfigValue("domain_name_address");
				                	String href="<a href='"+path+"/project/projectinfo.action?projectId="+_projectId+"'  class='linkbh14'><strong>”"+_prname+"“</strong>通行码为"+_code+"</a>";
				                	cValue=cValue.replace("#projectname#",href);   
				        			IrpMessageContent _irpMessageContent=new IrpMessageContent(); 
									_irpMessageContent.setFromUid(_personId); 
									_irpMessageContent.setContent(cValue);
									 irpMessageContentService.addMessageContent(_irpMessageContent);
								}
							}
							nCount=irpProjectPersonDAO.updateByPrimaryKeySelective(person);
						}else{//如果没有查到人员
							IrpProjectPerson projectPerson=new IrpProjectPerson();
							projectPerson.setProperid(TableIdUtil.getNextId(IrpProjectPerson.TABLE_NAME));
							projectPerson.setCrtime(new Date());
							projectPerson.setPrid(_projectId);
							projectPerson.setPersonid(_personId);
							projectPerson.setCruserid(irpUser.getUserid());
							projectPerson.setStatus(_status);// 账号 状态
							projectPerson.setIsstate(_state);
							//判断添加状态是成员还是关注者
							if(_state==IrpProjectPerson.IS_STATE){//成员 
									//添加私信
									if(_prname!=null && _prname.length()>0){
									String cValue=SysConfigUtil.getSysConfigValue("ADD_PROJECT_PERSON");
									cValue=cValue.replace("#user#",LoginUtil.getUserNameString(irpUser));//昵称
									SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
									Date date=new Date();
							    	cValue=cValue.replace("#time#",dateFormat.format(date));
							    	String path=SysConfigUtil.getSysConfigValue("domain_name_address");
							    	cValue=cValue.replace("#projectname#", "<a href='"+path+"/project/projectinfo.action?projectId="+_projectId+"'><strong>"+_prname+"</strong></a>");
							    	IrpMessageContent _irpMessageContent=new IrpMessageContent(); 
							    	_irpMessageContent.setFromUid(_personId); 
									_irpMessageContent.setContent(cValue); 
									nCount=irpMessageContentService.addMessageContent(_irpMessageContent); 
								}
							}else{//关注者
									projectPerson.setCode(_code);//账号通行码
									if(_prname!=null && _prname.length()>0){
									String cValue=SysConfigUtil.getSysConfigValue("SHARE_PROJECT_ATTENTION"); 
									cValue=cValue.replace("#user#",LoginUtil.getUserNameString(irpUser));//昵称
									Date date=new Date();
				                	SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
				                	cValue=cValue.replace("#time#",dateFormat.format(date)); 
				                	String path=SysConfigUtil.getSysConfigValue("domain_name_address");
				                	String href="<a href='"+path+"/project/projectinfo.action?projectId="+_projectId+"'  class='linkbh14'><strong>”"+_prname+"“</strong>通行码为"+_code+"</a>";
				                	cValue=cValue.replace("#projectname#",href);   
				        			IrpMessageContent _irpMessageContent=new IrpMessageContent(); 
									_irpMessageContent.setFromUid(_personId); 
									_irpMessageContent.setContent(cValue);
									 irpMessageContentService.addMessageContent(_irpMessageContent);
								}
							}
							irpProjectPersonDAO.insertSelective(projectPerson);
							nCount=1;
						}
				} catch (Exception e) {
					e.printStackTrace();
				} 
				return nCount;
	}

	public IrpMessageContentService getIrpMessageContentService() {
		return irpMessageContentService;
	}

	public void setIrpMessageContentService(
			IrpMessageContentService irpMessageContentService) {
		this.irpMessageContentService = irpMessageContentService;
	}
	
	@Override
	public List<IrpUser> projectAllPerson(Long projectId, Long isState,Long isStatus,PageUtil pageUtil) {
		// TODO Auto-generated method stub
		List<IrpUser> users=null;
		IrpProjectPersonExample example=new IrpProjectPersonExample();
		example.createCriteria().andPridEqualTo(projectId)
								.andIsstateEqualTo(isState)
								.andStatusEqualTo(isStatus);
		try {
			List<Long> personIds=irpProjectPersonDAO.selectPersonIdByProjectId(example);
			if(personIds!=null &&personIds.size()>0 ){
				IrpUserExample userExample=new IrpUserExample();
				userExample.createCriteria().andUseridIn(personIds);
				userExample.setOrderByClause(" userid desc ");
				if(pageUtil!=null){
					users=irpUserService.findUserByExample(userExample,pageUtil);
				}else{

					users=irpUserService.findUserByExample(userExample);
				}
				if(users!=null && users.size()>0){
 					for (int i = 0; i <users.size(); i++) {
						
						IrpUser user=users.get(i);
						//判断，昵称  
						String cValue =LoginUtil.getUserNameString(user);
						user.setUsername(cValue);
						
						users.set(i, user);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	public int addProjectPerson(Long _personId,Long _state,Long _projectId,Long _status) {
		int nCount=0;
		try {
			/**
			 * 首先查询这个项目是不是有这个人，不管他是 关注者还是成员，只要这个项目含有这个人都不再添加
			 */
			IrpProjectPersonExample example=new IrpProjectPersonExample();
				example.createCriteria().andPridEqualTo(_projectId)
										.andPersonidEqualTo(_personId); 
				List<IrpProjectPerson> irpProjectPersons=irpProjectPersonDAO.selectByExample(example);
				if(irpProjectPersons!=null && irpProjectPersons.size()>0){
					IrpProjectPerson p=irpProjectPersons.get(0);
					if(IrpProjectPerson.NOT_STATE.longValue()==p.getIsstate().longValue()&& IrpProjectPerson.NOT_STATUS.longValue()==p.getStatus().longValue()){
						//如果他是未开通的关注者,就删除
						irpProjectPersonDAO.deleteByPrimaryKey(p.getProperid());
					}else{
						return nCount;
					}
				}  
			IrpUser irpUser=LoginUtil.getLoginUser();
			IrpProjectPerson projectPerson=new IrpProjectPerson();
			projectPerson.setProperid(TableIdUtil.getNextId(IrpProjectPerson.TABLE_NAME));
			projectPerson.setCrtime(new Date());
			projectPerson.setPrid(_projectId);
			projectPerson.setCruserid(irpUser.getUserid());
			projectPerson.setPersonid(_personId);
			projectPerson.setIsstate(_state);
			projectPerson.setStatus(_status);// 账号 状态
			irpProjectPersonDAO.insertSelective(projectPerson);
			nCount=1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	/**
	 * 获取当前路径
	 * @return
	 */
	public String getRootPath(){
		String _location = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		_location = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/";
		return _location;
	}

	@Override
	public int deleteByExample(IrpProjectPersonExample example)
			throws SQLException {
		return irpProjectPersonDAO.deleteByExample(example);
	}

	@Override
	public void insert(IrpProjectPerson record) throws SQLException {
		irpProjectPersonDAO.insert(record);
	}

	@Override
	public List<Long> findPersonBySql(List<IrpProject> irpProjectList) {
		List<Long> idsList=new ArrayList<Long>();
		
		 if(irpProjectList!=null && irpProjectList.size()>0){
		    	for (int i = 0; i < irpProjectList.size(); i++) {
		    		IrpProject project = irpProjectList.get(i);
		    		idsList.add(project.getProjectid());
				}
		    	if(idsList.size()>0){
		    		String idString=idsList.toString();
		    		idString=idString.substring(1, idString.length()-1);
		    		String sql=" select t.pid from (select PRID as pid,count(PRID) as num from IRP_PROJECT_PERSON t  where ISSTATE=2 and PRID in("+idString+")  GROUP BY PRID  ORDER BY num desc) t where rownum<=10 ";
		    		idsList.clear();
		    		idsList=irpProjectPersonDAO.excuteSQl(sql);
		    	}
		    }  
		return idsList;
	}


}
