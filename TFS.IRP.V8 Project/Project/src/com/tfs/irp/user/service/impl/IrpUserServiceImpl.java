package com.tfs.irp.user.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.tfs.irp.category.entity.IrpCategory;
import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.channel.service.IrpChannelService;
import com.tfs.irp.config.dao.IrpConfigDAO;
import com.tfs.irp.group.entity.IrpGroup;
import com.tfs.irp.group.service.IrpGroupService;
import com.tfs.irp.microblogfocus.service.IrpMicroblogFocusService;
import com.tfs.irp.role.dao.IrpUserroleLinkDAO;
import com.tfs.irp.role.entity.IrpRole;
import com.tfs.irp.role.service.IrpRoleService;
import com.tfs.irp.site.entity.IrpSite;
import com.tfs.irp.site.service.IrpSiteService;
import com.tfs.irp.user.dao.IrpUserDAO;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.entity.IrpUserExample;
import com.tfs.irp.user.entity.IrpUserExample.Criteria;
import com.tfs.irp.user.entity.IrpUserValue;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.userprivacy.entity.IrpUserPrivacy;
import com.tfs.irp.userprivacy.service.IrpUserPrivacyService;
import com.tfs.irp.uservaluelink.dao.IrpUserValueLinkDAO;
import com.tfs.irp.util.DateUtils;
import com.tfs.irp.util.EncryptUtil;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.TableIdUtil;
import com.tfs.irp.util.session.OnlineUserBindingListener;

public class IrpUserServiceImpl implements IrpUserService {
	
	private final String LOGIN_USER_NAME = "LoginUser";

	private IrpUserDAO irpUserDAO;
	
	private IrpGroupService irpGroupService;
	
	private IrpSiteService irpSiteService;
	
	private IrpChannelService irpChannelService;
	
	private IrpRoleService irpRoleService;
	
    private IrpConfigDAO irpConfigDAO;
    
    private IrpUserPrivacyService irpUserPrivacyService;
    
	private IrpMicroblogFocusService irpMicroblogFocusService;
	
	private IrpUserValueLinkDAO irpUserValueLinkDAO;

	public IrpUserValueLinkDAO getIrpUserValueLinkDAO() {
		return irpUserValueLinkDAO;
	}

	public void setIrpUserValueLinkDAO(IrpUserValueLinkDAO irpUserValueLinkDAO) {
		this.irpUserValueLinkDAO = irpUserValueLinkDAO;
	}

	public IrpMicroblogFocusService getIrpMicroblogFocusService() {
		return irpMicroblogFocusService;
	}

	public void setIrpMicroblogFocusService(
			IrpMicroblogFocusService irpMicroblogFocusService) {
		this.irpMicroblogFocusService = irpMicroblogFocusService;
	}

	public IrpUserPrivacyService getIrpUserPrivacyService() {
		return irpUserPrivacyService;
	}

	public void setIrpUserPrivacyService(IrpUserPrivacyService irpUserPrivacyService) {
		this.irpUserPrivacyService = irpUserPrivacyService;
	}

	public IrpConfigDAO getIrpConfigDAO() {
		return irpConfigDAO;
	}

	public void setIrpConfigDAO(IrpConfigDAO irpConfigDAO) {
		this.irpConfigDAO = irpConfigDAO;
	}

	public IrpRoleService getIrpRoleService() {
		return irpRoleService;
	}

	public void setIrpRoleService(IrpRoleService irpRoleService) {
		this.irpRoleService = irpRoleService;
	}

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

	public IrpGroupService getIrpGroupService() {
		return irpGroupService;
	}

	public void setIrpGroupService(IrpGroupService irpGroupService) {
		this.irpGroupService = irpGroupService;
	}

	private IrpUserroleLinkDAO irpUserroleLinkDAO;

	public IrpUserroleLinkDAO getIrpUserroleLinkDAO() {
		return irpUserroleLinkDAO;
	}

	public void setIrpUserroleLinkDAO(IrpUserroleLinkDAO irpUserroleLinkDAO) {
		this.irpUserroleLinkDAO = irpUserroleLinkDAO;
	}

	public IrpUserDAO getIrpUserDAO() {
		return irpUserDAO;
	}

	public void setIrpUserDAO(IrpUserDAO irpUserDAO) {
		this.irpUserDAO = irpUserDAO;
	}

	@Override
	public int regUser(IrpUser _irpUser) {
		//返回注册状态，0=失败，1=成功，2=用户已存在。
		LogUtil logUtil = new LogUtil("USER_ADD", _irpUser);;
		int nRegStatus = 0;
		try {
			// 判断注册用户是否已存在
			IrpUserExample example = new IrpUserExample();
			example.createCriteria().andUsernameEqualTo(_irpUser.getUsername());
			List<IrpUser> users = irpUserDAO.selectByExample(example);
			if(users==null || users.size()==0){
				// 处理添加用户的默认参数
				Long tableid = TableIdUtil.getNextId(IrpUser.TABLE_NAME);
				_irpUser.setUserid(tableid);
				_irpUser.setPassword(EncryptUtil.encryptMD5(_irpUser.getPassword()));
				String sRegStatus = SysConfigUtil.getSysConfigValue("REGISTER_STATUS");
				if(Boolean.parseBoolean(sRegStatus.trim())){
					_irpUser.setStatus(IrpUser.USER_STATUS_REG);
				}else{
					_irpUser.setStatus(IrpUser.USER_STATUS_APPLY);
				}
				_irpUser.setCrtime(new Date());
				_irpUser.setCruserid(1L);
				_irpUser.setCruser("admin");
				irpUserDAO.insertSelective(_irpUser);
				//添加个人组织和个人栏目
				addUserGroup(_irpUser);
				addUserChannel(_irpUser);
				
				//添加everyone角色
				irpRoleService.importRoleUserByUserId(tableid, IrpRole.EVERYONE_ROLE_ID.toString());
				
				logUtil.successLogs("用户注册成功");
				//初始化隐私配置
				try {
					irpUserPrivacyService.addUserPrivacy(tableid,IrpUserPrivacy.MICROBLOGCOMMENT,IrpUserPrivacy.MICROBLOGVALUE);
					irpUserPrivacyService.addUserPrivacy(tableid,IrpUserPrivacy.MICROBLOGMESSAGE,IrpUserPrivacy.MICROBLOGVALUE);
					irpUserPrivacyService.addUserPrivacy(tableid,IrpUserPrivacy.MICROBLOGATME,IrpUserPrivacy.MICROBLOGVALUE);
					irpUserPrivacyService.addUserPrivacy(tableid,IrpUserPrivacy.USERRECOMMEND,IrpUserPrivacy.RECOMMENDOPEN);
					irpUserPrivacyService.addUserPrivacy(tableid,IrpUserPrivacy.USERLOGINLOCATION,SysConfigUtil.getSysConfigNumValue(IrpUser.USERREGISTERSUCCDEFAULTPAGE));
					irpUserPrivacyService.addUserPrivacy(tableid,IrpUserPrivacy.ONLINEMESSAGEFORWARDMAIL,IrpUserPrivacy.MICROBLOGVALUE);
					irpUserPrivacyService.addUserPrivacy(tableid,IrpUserPrivacy.BOOLDOCWORDHOT, IrpUserPrivacy.BOOLDWTRUE);
					//默认关注 cko
					irpMicroblogFocusService.addMicroblogFocusUserid(tableid, IrpUser.CKO);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if(Boolean.parseBoolean(sRegStatus.trim())){
					nRegStatus=1;
				}else{
					nRegStatus=2;
				}
			}else{
				logUtil.errorLogs("用户注册失败");
				nRegStatus=3;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs("用户注册失败", e);
			return nRegStatus;
		}
		return nRegStatus;
	}

	/* (non-Javadoc)
	 * @see com.tfs.irp.user.service.IrpUserService#login(com.tfs.irp.user.entity.IrpUser)
	 */
	/* (non-Javadoc)
	 * @see com.tfs.irp.user.service.IrpUserService#login(com.tfs.irp.user.entity.IrpUser)
	 */
	@Override
	public int LoginEW(IrpUser _irpUser){
		IrpUserPrivacy irpUserPrivacy =	null;
		LogUtil logUtil = new LogUtil("USER_LOGIN", _irpUser);
		List<IrpUser> users = null;
		IrpUser currIrpUser =  _irpUser;
		try {
			//获取知识管理
			long documentRoleId=Long.parseLong(SysConfigUtil.getSysConfigValue("INFORM_DOCUMENT_ROLE_ID"));
			//获得专家角色
			long expertRoleId=Long.parseLong(SysConfigUtil.getSysConfigValue("EXPERT_ROLE_ID"));
			//获取微知管理员
			long informRoleId=Long.parseLong(SysConfigUtil.getSysConfigValue("INFORM_ROLE_ID"));
			//获取专题管理员
			long topicRoleId=Long.parseLong(SysConfigUtil.getSysConfigValue("TOPIC_ROLE_ID"));
			//获得词条管理员
			long termsroleid = Long.parseLong(SysConfigUtil.getSysConfigValue("TERMS_ROLE_ID"));
			//获取问答管理员
			long questionRoleId = Long.parseLong(SysConfigUtil.getSysConfigValue("QUESTION_ROLE_ID"));
			//获取教师角色
			long teacherroleId = Long.parseLong(SysConfigUtil.getSysConfigValue("TEACHER_ROLE_ID"));
			//获取请假管理员
			long leaveapplyroleId = Long.parseLong(SysConfigUtil.getSysConfigValue("LEAVEAPPLY_ROLE_ID"));

			//获取会议室管理员
			long asseroomroleId = Long.parseLong(SysConfigUtil.getSysConfigValue("ASSEROOM_ROLE_ID"));
			//获取加班管理员
			long workapplyroleId = Long.parseLong(SysConfigUtil.getSysConfigValue("WORKAPPLY_ROLE_ID"));
		

			//用户id为1(admin用户)的默认设置成系统管理员
			if(currIrpUser.getUserid()==1L){
				currIrpUser.setAdministrator(true);
			}
			
			//获得用户角色
			List<Long> roleIds = irpUserroleLinkDAO.findRoleIdByUserId(currIrpUser.getUserid());
			
			for (long nRoleId : roleIds) {
				if(nRoleId==1L && !currIrpUser.isAdministrator()){
					
					currIrpUser.setAdministrator(true);
				}else if(nRoleId==questionRoleId){
					currIrpUser.setQuestionManager(true);
				}else if(nRoleId==termsroleid){
					currIrpUser.setTermswordManager(true);
				}else if(nRoleId==topicRoleId){
					currIrpUser.setTopicManager(true);
				}else if(nRoleId==informRoleId){
					currIrpUser.setMicroblogManager(true);
				}else if(nRoleId==expertRoleId){
					currIrpUser.setExpert(true);
				}else if(nRoleId==documentRoleId){
					currIrpUser.setDocumentManager(true);
				}else if(nRoleId==teacherroleId){
					currIrpUser.setTeacherManager(true);
				}else if(nRoleId==leaveapplyroleId){
					currIrpUser.setLeaveApplyManager(true);		
				}else if(nRoleId==asseroomroleId){
					currIrpUser.setAsseroomManager(true);
					
				}else if(nRoleId==workapplyroleId){
					currIrpUser.setWorkApplyManager(true);		
					
				}
			}
			
			//获取隐私设置
			irpUserPrivacy = this.irpUserPrivacyService.irpUserPrivacy(currIrpUser.getUserid(),IrpUserPrivacy.USERLOGINLOCATION);
			if(irpUserPrivacy!=null){
				currIrpUser.setPrivacy(irpUserPrivacy.getPrivacyvalue().intValue());
			}
			
			//设置session
			LoginUtil.getSession().setAttribute(LOGIN_USER_NAME, new OnlineUserBindingListener(currIrpUser));
			
			//修改用户最后登录时间
			IrpUser updateIrpUser = new IrpUser();
			updateIrpUser.setUserid(currIrpUser.getUserid());
			updateIrpUser.setLastlogintime(new Date());
			logUtil = new LogUtil("USER_LOGIN", currIrpUser);
			irpUserDAO.updateByPrimaryKeySelective(updateIrpUser);
			logUtil.successLogs();
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs("登录失败", e);
		}
		if(irpUserPrivacy!=null){
			if(irpUserPrivacy.getPrivacyvalue().intValue()==IrpUserPrivacy.ENTERPRICEPAGE.intValue()){
				//企业首页
				return 3;
			}else{
				//个人首页
				return 2;
			}
		}
		return 1;
	}
	
	@Override
	public int login(IrpUser _irpUser) {
		IrpUserExample example = new IrpUserExample();
		example.createCriteria().andUsernameEqualTo(_irpUser.getUsername());
		IrpUserPrivacy irpUserPrivacy =	null;
		
		LogUtil logUtil = new LogUtil("USER_LOGIN", _irpUser);
		List<IrpUser> users = null;
		try {
			users = irpUserDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (users == null || users.size() <= 0) {
			logUtil.errorLogs("用户名不存在");
			return -10;
		}
		IrpUser currIrpUser = (IrpUser) users.get(0);
		if(currIrpUser.getStatus()!=IrpUser.USER_STATUS_REG){
			logUtil.errorLogs("用户未开通");
			return -1;
		} else if (currIrpUser.getPassword().equals(EncryptUtil.encryptMD5(_irpUser.getPassword()))) {
			try {
				//获取知识管理
				long documentRoleId=Long.parseLong(SysConfigUtil.getSysConfigValue("INFORM_DOCUMENT_ROLE_ID"));
				//获得专家角色
				long expertRoleId=Long.parseLong(SysConfigUtil.getSysConfigValue("EXPERT_ROLE_ID"));
				//获取微知管理员
				long informRoleId=Long.parseLong(SysConfigUtil.getSysConfigValue("INFORM_ROLE_ID"));
				//获取专题管理员
				long topicRoleId=Long.parseLong(SysConfigUtil.getSysConfigValue("TOPIC_ROLE_ID"));
				//获得词条管理员
				long termsroleid = Long.parseLong(SysConfigUtil.getSysConfigValue("TERMS_ROLE_ID"));
				//获取问答管理员
				long questionRoleId = Long.parseLong(SysConfigUtil.getSysConfigValue("QUESTION_ROLE_ID"));
				//获取教师角色
				long teacherroleId = Long.parseLong(SysConfigUtil.getSysConfigValue("TEACHER_ROLE_ID"));
				//获取请假管理员
				long leaveapplyroleId = Long.parseLong(SysConfigUtil.getSysConfigValue("LEAVEAPPLY_ROLE_ID"));

				//获取会议室管理员
				long asseroomroleId = Long.parseLong(SysConfigUtil.getSysConfigValue("ASSEROOM_ROLE_ID"));
				//获取加班管理员
				long workapplyroleId = Long.parseLong(SysConfigUtil.getSysConfigValue("WORKAPPLY_ROLE_ID"));
			

				//用户id为1(admin用户)的默认设置成系统管理员
				if(currIrpUser.getUserid()==1L){
					currIrpUser.setAdministrator(true);
				}
				
				//获得用户角色
				List<Long> roleIds = irpUserroleLinkDAO.findRoleIdByUserId(currIrpUser.getUserid());
				
				for (long nRoleId : roleIds) {
					if(nRoleId==1L && !currIrpUser.isAdministrator()){
						
						currIrpUser.setAdministrator(true);
					}else if(nRoleId==questionRoleId){
						currIrpUser.setQuestionManager(true);
					}else if(nRoleId==termsroleid){
						currIrpUser.setTermswordManager(true);
					}else if(nRoleId==topicRoleId){
						currIrpUser.setTopicManager(true);
					}else if(nRoleId==informRoleId){
						currIrpUser.setMicroblogManager(true);
					}else if(nRoleId==expertRoleId){
						currIrpUser.setExpert(true);
					}else if(nRoleId==documentRoleId){
						currIrpUser.setDocumentManager(true);
					}else if(nRoleId==teacherroleId){
						currIrpUser.setTeacherManager(true);
					}else if(nRoleId==leaveapplyroleId){
						currIrpUser.setLeaveApplyManager(true);		
					}else if(nRoleId==asseroomroleId){
						currIrpUser.setAsseroomManager(true);
						
					}else if(nRoleId==workapplyroleId){
						currIrpUser.setWorkApplyManager(true);		
						
					}
				}
				
				//获取隐私设置
				irpUserPrivacy = this.irpUserPrivacyService.irpUserPrivacy(currIrpUser.getUserid(),IrpUserPrivacy.USERLOGINLOCATION);
				if(irpUserPrivacy!=null){
					currIrpUser.setPrivacy(irpUserPrivacy.getPrivacyvalue().intValue());
				}
				
				//设置session
				LoginUtil.getSession().setAttribute(LOGIN_USER_NAME, new OnlineUserBindingListener(currIrpUser));
				
				//修改用户最后登录时间
				IrpUser updateIrpUser = new IrpUser();
				updateIrpUser.setUserid(currIrpUser.getUserid());
				updateIrpUser.setLastlogintime(new Date());
				logUtil = new LogUtil("USER_LOGIN", currIrpUser);
				irpUserDAO.updateByPrimaryKeySelective(updateIrpUser);
				logUtil.successLogs();
			} catch (SQLException e) {
				e.printStackTrace();
				logUtil.errorLogs("登录失败", e);
			}
			if(irpUserPrivacy!=null){
				if(irpUserPrivacy.getPrivacyvalue().intValue()==IrpUserPrivacy.ENTERPRICEPAGE.intValue()){
					//企业首页
					return 3;
				}else{
					//个人首页
					return 2;
				}
			}
			return 1;
		} else {
			logUtil.errorLogs("用户密码不一致");
			return 0;
		}
	}
	
	@Override
	public int loginByName(String _sUserName) {
		IrpUserExample example = new IrpUserExample();
		example.createCriteria().andUsernameEqualTo(_sUserName);
		IrpUser currIrpUser = new IrpUser();
		currIrpUser.setUsername(_sUserName);
		LogUtil logUtil = new LogUtil("USER_LOGIN", currIrpUser);
		List<IrpUser> users = null;
		try {
			users = irpUserDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (users == null || users.size() <= 0) {
			logUtil.errorLogs("用户名不存在");
			return 0;
		}
		currIrpUser = (IrpUser) users.get(0);
		if(currIrpUser.getStatus()!=IrpUser.USER_STATUS_REG){
			logUtil.errorLogs("用户未开通");
			return -1;
		} else {
			try {
				//获取知识管理
				long documentRoleId=Long.parseLong(SysConfigUtil.getSysConfigValue("INFORM_DOCUMENT_ROLE_ID"));
				//获得专家角色
				long expertRoleId=Long.parseLong(SysConfigUtil.getSysConfigValue("EXPERT_ROLE_ID"));
				//获取微知管理员
				long informRoleId=Long.parseLong(SysConfigUtil.getSysConfigValue("INFORM_ROLE_ID"));
				//获取专题管理员
				long topicRoleId=Long.parseLong(SysConfigUtil.getSysConfigValue("TOPIC_ROLE_ID"));
				//获得词条管理员
				long termsroleid = Long.parseLong(SysConfigUtil.getSysConfigValue("TERMS_ROLE_ID"));
				//获取问答管理员
				long questionRoleId = Long.parseLong(SysConfigUtil.getSysConfigValue("QUESTION_ROLE_ID"));
				//获取请假加班管理员
				long leaveapplyroleId = Long.parseLong(SysConfigUtil.getSysConfigValue("LEAVEAPPLY_ROLE_ID"));

				//获取会议管理员
				long asseroomroleId = Long.parseLong(SysConfigUtil.getSysConfigValue("ASSEROOM_ROLE_ID"));
				//获取加班管理员
				long workapplyroleId = Long.parseLong(SysConfigUtil.getSysConfigValue("WORKAPPLY_ROLE_ID"));
				
				//获得用户角色
				List<Long> roleIds = irpUserroleLinkDAO.findRoleIdByUserId(currIrpUser.getUserid());
				for (long nRoleId : roleIds) {
					if(nRoleId==1L){
						currIrpUser.setAdministrator(true);
					}else if(nRoleId==questionRoleId){
						currIrpUser.setQuestionManager(true);
					}else if(nRoleId==termsroleid){
						currIrpUser.setTermswordManager(true);
					}else if(nRoleId==topicRoleId){
						currIrpUser.setTopicManager(true);
					}else if(nRoleId==informRoleId){
						currIrpUser.setMicroblogManager(true);
					}else if(nRoleId==expertRoleId){
						currIrpUser.setExpert(true);
					}else if(nRoleId==documentRoleId){
						currIrpUser.setDocumentManager(true);
					}else if(nRoleId==leaveapplyroleId){
						currIrpUser.setLeaveApplyManager(true);						
					}else if(nRoleId==workapplyroleId){
						currIrpUser.setWorkApplyManager(true);		
						
					}else if(nRoleId==asseroomroleId){
						currIrpUser.setAsseroomManager(true);
						
					}
				}
				
				//设置session
				LoginUtil.getSession().setAttribute(LOGIN_USER_NAME, new OnlineUserBindingListener(currIrpUser));
				
				//修改用户最后登录时间
				IrpUser updateIrpUser = new IrpUser();
				updateIrpUser.setUserid(currIrpUser.getUserid());
				updateIrpUser.setLastlogintime(new Date());
				logUtil = new LogUtil("USER_LOGIN", currIrpUser);
				irpUserDAO.updateByPrimaryKeySelective(updateIrpUser);
				logUtil.successLogs();
			} catch (SQLException e) {
				e.printStackTrace();
				logUtil.errorLogs("登录失败", e);
			}
			//获取隐私设置
			IrpUserPrivacy irpUserPrivacy =	this.irpUserPrivacyService.irpUserPrivacy(currIrpUser.getUserid(),IrpUserPrivacy.USERLOGINLOCATION);
			if(irpUserPrivacy!=null){
				if(irpUserPrivacy.getPrivacyvalue().equals(IrpUserPrivacy.ENTERPRICEPAGE)){
					//企业首页
					return 3;
				}else{
					//个人首页
					return 2;
				}
			}	
			return 1;
		}
	}
	
	@Override
	public void logout() {
		LoginUtil.logout();
	}

	@Override
	public List<IrpUser> findUsersOfPageByStatus(PageUtil pageUtil,int _nStatus, String _sOrderBy) {
		List<IrpUser> userList = null;
		try {
			IrpUserExample example = new IrpUserExample();
			example.createCriteria().andStatusEqualTo(_nStatus);
			if(_sOrderBy==null || _sOrderBy.equals("")){
				_sOrderBy = "userid desc";
			}
			example.setOrderByClause(_sOrderBy);
			userList = irpUserDAO.selectByExample(example, pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	@Override
	public List<IrpUser> findUsersOfPageByStatus(PageUtil pageUtil, int _nStatus, String _sOrderBy, String _sSearchWord, String  _sSearchType) {
		List<IrpUser> userList = null;
		try {
			IrpUserExample example = new IrpUserExample();
			if("all".equals(_sSearchType)){
				example.or(example.createCriteria().andStatusEqualTo(_nStatus).andUsernameLike("%"+_sSearchWord+"%"));
				example.or(example.createCriteria().andStatusEqualTo(_nStatus).andTruenameLike("%"+_sSearchWord+"%"));
			} else if("username".equals(_sSearchType)){
				example.createCriteria().andStatusEqualTo(_nStatus).andUsernameLike("%"+_sSearchWord+"%");
			} else if("truename".equals(_sSearchType)){
				example.createCriteria().andStatusEqualTo(_nStatus).andTruenameLike("%"+_sSearchWord+"%");
			} else{
				example.createCriteria().andStatusEqualTo(_nStatus);
			}
			if(_sOrderBy==null || _sOrderBy.equals("")){
				_sOrderBy = "userid desc";
			}
			example.setOrderByClause(_sOrderBy);
			userList = irpUserDAO.selectByExample(example, pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	@Override
	public int findUsersCountByStatus(int _nStatus) {
		int nCount = 0;
		try {
			IrpUserExample example = new IrpUserExample();
			example.createCriteria().andStatusEqualTo(_nStatus);
			nCount = irpUserDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	public int findUsersCountByStatus(int _nStatus, String _sSearchWord, String _sSearchType) {
		int nCount = 0;
		try {
			IrpUserExample example = new IrpUserExample();
			if("all".equals(_sSearchType)){
				example.or(example.createCriteria().andStatusEqualTo(_nStatus).andUsernameLike("%"+_sSearchWord+"%"));
				example.or(example.createCriteria().andStatusEqualTo(_nStatus).andTruenameLike("%"+_sSearchWord+"%"));
			} else if("username".equals(_sSearchType)){
				example.createCriteria().andStatusEqualTo(_nStatus).andUsernameLike("%"+_sSearchWord+"%");
			} else if("truename".equals(_sSearchType)){
				example.createCriteria().andStatusEqualTo(_nStatus).andTruenameLike("%"+_sSearchWord+"%");
			} else{
				example.createCriteria().andStatusEqualTo(_nStatus);
			}
			nCount = irpUserDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	public IrpUser findUserByUserId(Long _nUserId) {
		IrpUser user = null;
		try {
			if(_nUserId!=null){
				user = irpUserDAO.selectByPrimaryKey(_nUserId);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public int updateStatusByUserids(String _sUserIds, int _nStatus) {
		int nRows = 0;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("status", Integer.valueOf(_nStatus));
		if(_nStatus == IrpUser.USER_STATUS_REG){
			param.put("regtime", new Date());
		}
		param.put("userids", _sUserIds.split(","));
		IrpUser user = new IrpUser();
		user.setUserid(Long.parseLong(_sUserIds.split(",")[0]));
		LogUtil logUtil = new LogUtil("USER_EDIT", user);
		try {
			nRows = irpUserDAO.updateStatusByUserids(param);
			logUtil.successLogs("批量修改用户ID["+_sUserIds+"]状态为["+_nStatus+"]");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs("批量修改用户ID["+_sUserIds+"]状态为["+_nStatus+"]", e);
		}
		return nRows;
	}

	@Override
	public int updatePassWordByUserids(String _sUserIds, String _sPassWord) {
		int nRows = 0;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("password", EncryptUtil.encryptMD5(_sPassWord));
		param.put("userids", _sUserIds.split(","));
		IrpUser user = new IrpUser();
		user.setUserid(Long.parseLong(_sUserIds.split(",")[0]));
		LogUtil logUtil = new LogUtil("USER_EDIT", user);
		try {
			nRows = irpUserDAO.updatePassWordByUserids(param);
			logUtil.successLogs("批量修改用户ID["+_sUserIds+"]密码为["+_sPassWord+"]");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs("批量修改用户ID["+_sUserIds+"]密码为["+_sPassWord+"]", e);
		}
		return nRows;
	}

	@Override
	public boolean checkUserName(String _sUserName, Long _nUserId) {
		int nCount = 0;
		IrpUserExample example = new IrpUserExample();
		example.createCriteria().andUseridNotEqualTo(_nUserId).andUsernameEqualTo(_sUserName);
		try {
			nCount = irpUserDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount>0?false:true;
	}

	@Override
	public Long userEdit(IrpUser _irpUser) {
		Long nUserId = 0L;
		if(_irpUser.getUserid()==null || _irpUser.getUserid()==0){
			Long nTempId = TableIdUtil.getNextId(IrpUser.TABLE_NAME);
			_irpUser.setUserid(nTempId);
			_irpUser.setPassword(EncryptUtil.encryptMD5(_irpUser.getPassword()));
			_irpUser.setStatus(IrpUser.USER_STATUS_REG);
			Date date = new Date();
			_irpUser.setCrtime(date);
			_irpUser.setRegtime(date);
			IrpUser loginUser = LoginUtil.getLoginUser();
			_irpUser.setCruserid(loginUser.getCruserid());
			_irpUser.setCruser(loginUser.getUsername());
			LogUtil logUtil = new LogUtil("USER_ADD", _irpUser);
			try {
				irpUserDAO.insertSelective(_irpUser);
				//初始化隐私配置
				try{
					irpUserPrivacyService.addUserPrivacy(nTempId,IrpUserPrivacy.MICROBLOGCOMMENT,IrpUserPrivacy.MICROBLOGVALUE);
					irpUserPrivacyService.addUserPrivacy(nTempId,IrpUserPrivacy.MICROBLOGMESSAGE,IrpUserPrivacy.MICROBLOGVALUE);
					irpUserPrivacyService.addUserPrivacy(nTempId,IrpUserPrivacy.MICROBLOGATME,IrpUserPrivacy.MICROBLOGVALUE);
					irpUserPrivacyService.addUserPrivacy(nTempId,IrpUserPrivacy.USERRECOMMEND,IrpUserPrivacy.RECOMMENDOPEN);
					irpUserPrivacyService.addUserPrivacy(nTempId,IrpUserPrivacy.USERLOGINLOCATION,SysConfigUtil.getSysConfigNumValue(IrpUser.USERREGISTERSUCCDEFAULTPAGE));
					irpUserPrivacyService.addUserPrivacy(nTempId,IrpUserPrivacy.ONLINEMESSAGEFORWARDMAIL,IrpUserPrivacy.MICROBLOGVALUE);
					irpUserPrivacyService.addUserPrivacy(nTempId,IrpUserPrivacy.BOOLDOCWORDHOT, IrpUserPrivacy.BOOLDWTRUE);
					//默认关注 cko
					irpMicroblogFocusService.addMicroblogFocusUserid(nTempId, IrpUser.CKO);
				}finally{
					logUtil.successLogs();
					nUserId = nTempId;
					//添加个人组织和个人栏目
					addUserGroup(_irpUser);
					addUserChannel(_irpUser);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				logUtil.errorLogs(e);
			}
		}else{
			LogUtil logUtil = new LogUtil("USER_EDIT", _irpUser);
			try {
				irpUserDAO.updateByPrimaryKeySelective(_irpUser);
				if(LoginUtil.getLoginUser() != null){
				    LoginUtil.updateCacheUser(_irpUser);
				}
				logUtil.successLogs();
				nUserId = _irpUser.getUserid();
			} catch (SQLException e) {
				e.printStackTrace();
				logUtil.errorLogs(e);
			}
		}
		return nUserId;
	}
	
	@Override
	public void addUserGroup(IrpUser irpUser){
		//添加个人组织
		IrpGroup rootGroup = irpGroupService.findRootGroupByGroupType(IrpGroup.GROUP_TYPE_PRIVATE);
		IrpGroup group = new IrpGroup();
		group.setGroupid(0L);
		group.setGroupname(irpUser.getUserid().toString());
		group.setGdesc(irpUser.getUsername());
		group.setParentid(rootGroup.getGroupid());
		group.setGrouptype(IrpGroup.GROUP_TYPE_PRIVATE);
		group.setGrouporder(0L);
		IrpUser cruser = LoginUtil.getLoginUser()==null?irpUser:LoginUtil.getLoginUser();
		irpGroupService.groupEdit(group, cruser);
	}
	
	/**
	 * 添加个人栏目信息
	 * @param irpUser
	 */
	private void addUserChannel(IrpUser irpUser){
		//获得个人栏目的站点对象
		IrpSite site = null;
		List<IrpSite> sites = irpSiteService.findSitesBySiteType(IrpSite.SITE_TYPE_PRIVATE);
		if(sites!=null && sites.size()>0){
			site = sites.get(0);
		}
		if(site == null){
			return;
		}
		IrpChannel channel = new IrpChannel();
		channel.setChnlname(irpUser.getUserid().toString());
		channel.setChnldesc(irpUser.getUsername());
		channel.setSiteid(site.getSiteid());
		channel.setStatus(1);
		channel.setParentid(0L);
		channel.setChnltype(IrpChannel.CHANNEL_TYPE_PRIVATE);
		IrpUser cruser = LoginUtil.getLoginUser()==null?irpUser:LoginUtil.getLoginUser();
		irpChannelService.addChannel(channel, cruser);
	}

	@Override
	public List<IrpUser> findUserByUsername(String _username) {
		List<IrpUser> irpUser = null;
		IrpUserExample example = new IrpUserExample();
		Criteria criteria = example.createCriteria();
		if(_username==null){
			_username="";
		}
		criteria.andUsernameEqualTo(_username);
		try {
			irpUser= this.irpUserDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return irpUser;
	}

	@Override
	public String findUsernameByNicknameTruenameUsername(String _name) {
		String username = "";
		try {
			username = this.irpUserDAO.findUsernameByNickNameTrueNameUsername(_name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return username;
	}
	@Override
	public List<IrpUser> findUserByExample(IrpUserExample example) {
		 List<IrpUser> list=null;
		try {
			list=irpUserDAO.selectByExample(example);
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<IrpUser> findUserByExample(IrpUserExample example, PageUtil pageUtil) {
		List<IrpUser> list=null;
		try {
			list=irpUserDAO.selectByExample(example,pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int updateSumScoreExperience(IrpUser _irpUser) {
		int _status = 0;
		IrpUser irpUser = new IrpUser();
		irpUser.setUserid(_irpUser.getUserid());
		irpUser.setSumscore(_irpUser.getSumscore());
		irpUser.setSumexperience(_irpUser.getSumexperience());
		try {
			_status = this.irpUserDAO.updateByPrimaryKeySelective(irpUser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return _status;
	}
	@Override
	public int updateLeaveYearDays(IrpUser _irpUser) {
		
		int _status = 0;
		IrpUser irpUser = new IrpUser();
		irpUser.setUserid(_irpUser.getUserid());
		irpUser.setHoliday(_irpUser.getHoliday());
		LogUtil logUtil = new LogUtil("USER_EDITYEAR", irpUser);
		try {
			_status = this.irpUserDAO.updateYearDays(irpUser);
			logUtil.successLogs("修改用户ID["+irpUser.getUserid()+"]年假天数为["+_irpUser.getHoliday()+"]成功");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs("修改用户ID["+irpUser.getUserid()+"]年假天数为["+_irpUser.getHoliday()+"]失败");
		}
		return _status;
	}
	@Override
	public int updateAllLeaveYearDays(IrpUser _irpUser) {
		int _status = 0;
		IrpUser irpUser = new IrpUser();
		
		irpUser.setHoliday(_irpUser.getHoliday());
		LogUtil logUtil = new LogUtil("USER_EDITYEAR", LoginUtil.getLoginUser());
		try {
			_status = this.irpUserDAO.updateAllYearDays(irpUser);
			
			logUtil.successLogs("一键修改用户年假天数为["+_irpUser.getHoliday()+"]成功");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs("一键修改用户年假天数为["+_irpUser.getHoliday()+"]失败");
		}
		return _status;
	}
	@Override
	public int countUserByExample(IrpUserExample example) {
		int nCount=0;
		try {
			nCount=irpUserDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	public List<IrpUser> findAllRegUsers() {
		List<IrpUser> irpUser = null;
		IrpUserExample example = new IrpUserExample();
		example.createCriteria().andStatusEqualTo(IrpUser.USER_STATUS_REG);
		try {
			irpUser = this.irpUserDAO.selectByExampleWithRoleId(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return irpUser;
	}
	
	@Override
	public List<IrpUser> findAllIrpUser(PageUtil pageUtil,String _oOrderby, String _sSearchWord, String _sSearchType) {
		List<IrpUser> irpUser = null;
		IrpUserExample example = new IrpUserExample();
		
		if(_sSearchWord!=null && _sSearchType != null){
			if("username".equals(_sSearchType)){
				example.createCriteria().andUsernameLike("%"+_sSearchWord+"%");
			} else if("score".equals(_sSearchType)){
				if(isInteger(_sSearchWord)==true){
					example.createCriteria().andSumscoreEqualTo(Long.parseLong(_sSearchWord));	
				}else{
					example.createCriteria().andSumscoreEqualTo(IrpUser.SUMSCORE);
				}
				
			} else if("experience".equals(_sSearchType)){
				if(isInteger(_sSearchWord)==true){
					example.createCriteria().andSumexperienceEqualTo(Long.parseLong(_sSearchWord));	
				}else{
					example.createCriteria().andSumexperienceEqualTo(IrpUser.SUMEXPERIENCE);
				}
			}
		}
		if(_oOrderby==null || _oOrderby.equals("")){
			_oOrderby="holiday desc";
		}
		example.setOrderByClause(_oOrderby);
		try {
			irpUser = this.irpUserDAO.selectByExample(example,pageUtil);
		  
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return irpUser;
	}
	/*@Override
	public List<IrpUser> findAllIrpUser(PageUtil pageUtil,String _oOrderby, String _sSearchWord, String _sSearchType,Date _starttime,Date _endtime) {
		List<IrpUser> irpUser = null;
		IrpUserExample example = new IrpUserExample();
		Criteria criteria=example.createCriteria();
		if(_sSearchWord!=null && _sSearchType != null){
			if("username".equals(_sSearchType)){
				criteria.andUsernameLike("%"+_sSearchWord+"%");
			} else if("score".equals(_sSearchType)){
				if(isInteger(_sSearchWord)==true){
					criteria.andSumscoreEqualTo(Long.parseLong(_sSearchWord));	
				}else{
					criteria.andSumscoreEqualTo(IrpUser.SUMSCORE);
				}
				
			} else if("experience".equals(_sSearchType)){
				if(isInteger(_sSearchWord)==true){
					criteria.andSumexperienceEqualTo(Long.parseLong(_sSearchWord));	
				}else{
					criteria.andSumexperienceEqualTo(IrpUser.SUMEXPERIENCE);
				}
			}
		}
		if(_oOrderby==null || _oOrderby.equals("")){
			_oOrderby="holiday desc";
		}
		example.setOrderByClause(_oOrderby);
		//时间段   
		if(_starttime!=null && _endtime!=null){
		    criteria.andCrtimeBetween(_starttime,_endtime);	
	
		}
		try {
			irpUser = this.irpUserDAO.selectByExample(example,pageUtil);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return irpUser;
	}*/
	private IrpUserService irpUserService;
	
	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	@Override
	public List<IrpUserValue> findAllIrpUser(PageUtil pageUtil,String _oOrderby, String _sSearchWord, String _sSearchType,Date _starttime,Date _endtime) {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from ( select A.*,rownum rn from (");
		if(_starttime==null&&_endtime==null){
			sql.append("select DISTINCT(u.USERID),u.USERNAME,u.SUMSCORE,u.HOLIDAY,u.SUMEXPERIENCE,(SELECT SUM(SCORE) FROM IRP_USER_VALUE_LINK where USERID=u.USERID ) as NUMSCORE ,(SELECT SUM(EXPERIENCE) FROM IRP_USER_VALUE_LINK where USERID=u.USERID) as NUMEXPERIENCE from IRP_USER u");
		}else{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String begindate = formatter.format(_starttime);
			String enddate = formatter.format(_endtime);
			sql.append("select DISTINCT(u.USERID),u.USERNAME,u.SUMSCORE,u.HOLIDAY,u.SUMEXPERIENCE,(SELECT SUM(SCORE) FROM IRP_USER_VALUE_LINK where USERID=u.USERID and CRTIME > to_date('"+begindate+"','YYYY-MM-DD') and CRTIME < to_date('"+enddate+"','YYYY-MM-DD')) as NUMSCORE ,(SELECT SUM(EXPERIENCE) FROM IRP_USER_VALUE_LINK where USERID=u.USERID and CRTIME > to_date('"+begindate+"','YYYY-MM-DD') and CRTIME < to_date('"+enddate+"','YYYY-MM-DD')) as NUMEXPERIENCE from IRP_USER u");
		}
		List<IrpUserValue> irpUser = null;
		if(_sSearchWord!=null && _sSearchType != null){
			if("username".equals(_sSearchType)){
				sql.append(" where u.USERNAME like '%"+_sSearchWord+"%'");
			} else if("score".equals(_sSearchType)){
				if(isInteger(_sSearchWord)==true){
					sql.append(" WHERE u.SUMSCORE like '%"+_sSearchWord+"%'");
				}
			} else if("experience".equals(_sSearchType)){
				if(isInteger(_sSearchWord)==true){
					sql.append(" WHERE u.SUMEXPERIENCE like '%"+_sSearchWord+"%'");
				}
			}
		}
		if(_oOrderby==null || _oOrderby.equals("")){
			_oOrderby="holiday desc";
		}
		int begin = pageUtil.getPageNum();
		int end =pageUtil.getPageSize();
		if(pageUtil.getPageIndex()!=0){
			begin = (pageUtil.getPageNum()-1)*end+1;
			end = pageUtil.getPageNum()*pageUtil.getPageSize();
		}
		IrpUser user=null;
		try {
			sql.append(" order by "+_oOrderby);
			sql.append(") A where rownum <= "+end+") where rn >="+begin);
			irpUser = this.irpUserDAO.selectByExample(sql.toString());
			for(int i=0;i<irpUser.size();i++){
				if(irpUser.get(i).getNumscore()==null){
					irpUser.get(i).setNumscore(0l);
				}
				if(irpUser.get(i).getNumexperience()==null){
					irpUser.get(i).setNumexperience(0l);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return irpUser;
	}
	@Override
	public List<IrpUser> findAllIrpUserHoloday(PageUtil pageUtil,String _oOrderby, String _sSearchWord, String _sSearchType) {
		List<IrpUser> irpUser = null;
		IrpUserExample example = new IrpUserExample();
		
		if(_sSearchWord!=null && _sSearchType != null){
			if("username".equals(_sSearchType)){
				example.or(example.createCriteria().andUsernameLike("%"+_sSearchWord+"%"));
			} else if("holiday".equals(_sSearchType)){
				if(isInteger(_sSearchWord)==true){
					example.createCriteria().andHolidayEqualTo(Integer.parseInt(_sSearchWord));	
				}else{
					example.createCriteria().andHolidayEqualTo(IrpUser.HOLIDAY);
				}
				
			} 
		}
		if(_oOrderby==null || _oOrderby.equals("")){
			_oOrderby="holiday desc";
		}
		example.setOrderByClause(_oOrderby);
		try {
			irpUser = this.irpUserDAO.selectByExample(example,pageUtil);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return irpUser;
	}
	
	@Override
	public List<IrpUser> findSpecialIrpUser(PageUtil pageUtil,String _oOrderby, String _sSearchWord, String _sSearchType) {
		List<IrpUser> irpUser = null;
		IrpUserExample example = new IrpUserExample();
		
		if(_sSearchWord!=null && _sSearchType != null){
			if("username".equals(_sSearchType)){
					example.createCriteria().andUsernameLike("%"+_sSearchWord+"%").andSpecialtypeEqualTo(IrpUser.SPECIAL_USER);
					
			} else if("score".equals(_sSearchType)){
				if(isInteger(_sSearchWord)==true){
					example.createCriteria().andSumscoreEqualTo(Long.parseLong(_sSearchWord)).andSpecialtypeEqualTo(IrpUser.SPECIAL_USER);
				}else{
					example.createCriteria().andSumscoreEqualTo(IrpUser.SUMSCORE).andSpecialtypeEqualTo(IrpUser.SPECIAL_USER);
				}
			
			} else if("experience".equals(_sSearchType)){
				
				if(isInteger(_sSearchWord)==true){
					example.createCriteria().andSumexperienceEqualTo(Long.parseLong(_sSearchWord)).andSpecialtypeEqualTo(IrpUser.SPECIAL_USER);
				}else{
					example.createCriteria().andSumexperienceEqualTo(IrpUser.SUMEXPERIENCE).andSpecialtypeEqualTo(IrpUser.SPECIAL_USER);
				}
			}else{
				
				example.createCriteria().andSpecialtypeEqualTo(IrpUser.SPECIAL_USER);
			}
			
		}else{
			example.createCriteria().andSpecialtypeEqualTo(IrpUser.SPECIAL_USER);
			
		}
		
		if(_oOrderby==null || _oOrderby.equals("")){
			_oOrderby="userid desc";
		}
		example.setOrderByClause(_oOrderby);
		
		try {
			irpUser = this.irpUserDAO.selectByExample(example,pageUtil);
		  
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return irpUser;
	}
	
	
    /**
     * 判断字符串是否为整数
     * @return
     */
	private boolean isInteger(String value){
		try{
			Integer.parseInt(value);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	@Override
	public long findAllUserCountByStatus(int _nStatus) {
		int usercount = 0;
		IrpUserExample example = new IrpUserExample();
		example.createCriteria().andStatusEqualTo(_nStatus);
		try {
			usercount = this.irpUserDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usercount;
	}

	@Override
	public int updateUserSumScoreEx(Long _userid,long _sumscore,long _sumexperience) {
		int status = 0;
		if(_userid!=null){
			IrpUser getIrpUser = this.findUserByUserId(_userid);
			if(getIrpUser!=null){
				IrpUser irpUser = new IrpUser();
				irpUser.setUserid(_userid);
				irpUser.setSumscore(getIrpUser.addSumscore(_sumscore));
				irpUser.setSumexperience(getIrpUser.addSumexperience(_sumexperience));
				try {
					status =this.irpUserDAO.updateByPrimaryKeySelective(irpUser);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return status;
	}

	@Override
	public int emptyUserScoreExperInfo(long _userid,long _sumscore,long _sumexperience) {
		int status = 0;
		IrpUser record = new IrpUser();
		record.setUserid(_userid);
		record.setSumscore(_sumscore);
		record.setSumexperience(_sumexperience);
		
		try {
			status = this.irpUserDAO.updateByPrimaryKeySelective(record);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return status;
	}
	@Override
	public int searchUserValueLinkSize(String _sSearchWord, String _sSearchType) {
		int num = 0;
		IrpUserExample example = new IrpUserExample();
		if(_sSearchWord != null && _sSearchType != null){
			if("username".equals(_sSearchType)){
				example.or(example.createCriteria().andUsernameLike("%"+_sSearchWord+"%"));
			} else if("score".equals(_sSearchType)){
				if(isInteger(_sSearchWord)==true){
					example.createCriteria().andSumscoreEqualTo(Long.parseLong(_sSearchWord));	
				}else{
					example.createCriteria().andSumscoreEqualTo(IrpUser.SUMSCORE);
				}
				
			} else if("experience".equals(_sSearchType)){
				if(isInteger(_sSearchWord)==true){
					example.createCriteria().andSumexperienceEqualTo(Long.parseLong(_sSearchWord));	
				}else{
					example.createCriteria().andSumexperienceEqualTo(IrpUser.SUMEXPERIENCE);
				}
			} 
			
		}
		try {
			num = this.irpUserDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	@Override
	public int searchUserValueLinkSize(String _sSearchWord, String _sSearchType,Date _starttime,Date _endtime) {
		int num = 0;
		IrpUserExample example = new IrpUserExample();
		Criteria criteria=example.createCriteria();
		if(_sSearchWord != null && _sSearchType != null){
			if("username".equals(_sSearchType)){
				example.or(example.createCriteria().andUsernameLike("%"+_sSearchWord+"%"));
			} else if("score".equals(_sSearchType)){
				if(isInteger(_sSearchWord)==true){
					criteria.andSumscoreEqualTo(Long.parseLong(_sSearchWord));	
				}else{
					criteria.andSumscoreEqualTo(IrpUser.SUMSCORE);
				}
				
			} else if("experience".equals(_sSearchType)){
				if(isInteger(_sSearchWord)==true){
					criteria.andSumexperienceEqualTo(Long.parseLong(_sSearchWord));	
				}else{
					criteria.andSumexperienceEqualTo(IrpUser.SUMEXPERIENCE);
				}
			} 
			
		}
		//时间段   
				if(_starttime!=null && _endtime!=null){
				    criteria.andCrtimeBetween(_starttime,_endtime);	
			
				}
		try {
			num = this.irpUserDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	@Override
	public int searchYearDays(String _sSearchWord, String _sSearchType) {
		int num = 0;
		IrpUserExample example = new IrpUserExample();
		if(_sSearchWord != null && _sSearchType != null){
			if("username".equals(_sSearchType)){
				example.or(example.createCriteria().andUsernameLike("%"+_sSearchWord+"%"));
			} else if("holiday".equals(_sSearchType)){
				if(isInteger(_sSearchWord)==true){
					example.createCriteria().andHolidayEqualTo(Integer.parseInt(_sSearchWord));	
				}else{
					example.createCriteria().andHolidayEqualTo(IrpUser.HOLIDAY);
				}
				
			} 
			
		}
		try {
			num = this.irpUserDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	@Override
	public int searchSpecialUserValueLinkSize(String _sSearchWord, String _sSearchType) {
		int num = 0;
		IrpUserExample example = new IrpUserExample();
		if(_sSearchWord!=null && _sSearchType != null){
			if("username".equals(_sSearchType)){
					example.createCriteria().andUsernameLike("%"+_sSearchWord+"%").andSpecialtypeEqualTo(IrpUser.SPECIAL_USER);
			} else if("score".equals(_sSearchType)){
				if(isInteger(_sSearchWord)==true){
					example.createCriteria().andSumscoreEqualTo(Long.parseLong(_sSearchWord)).andSpecialtypeEqualTo(IrpUser.SPECIAL_USER);
				}else{
					example.createCriteria().andSumscoreEqualTo(IrpUser.SUMSCORE).andSpecialtypeEqualTo(IrpUser.SPECIAL_USER);
				}
				
			} else if("experience".equals(_sSearchType)){
				if(isInteger(_sSearchWord)==true){
					example.createCriteria().andSumexperienceEqualTo(Long.parseLong(_sSearchWord)).andSpecialtypeEqualTo(IrpUser.SPECIAL_USER);
				}else{
					example.createCriteria().andSumexperienceEqualTo(IrpUser.SUMEXPERIENCE).andSpecialtypeEqualTo(IrpUser.SPECIAL_USER);
				}
			}else{
				
				example.createCriteria().andSpecialtypeEqualTo(IrpUser.SPECIAL_USER);
			}
		}else{
			example.createCriteria().andSpecialtypeEqualTo(IrpUser.SPECIAL_USER);
		}
		try {
			num = this.irpUserDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	
	
	
	@Override
	public List shareRank(PageUtil pageUtil) {
		List<IrpUser> list = new ArrayList<IrpUser>();
		IrpUserExample example = new IrpUserExample();
		try {
			List<Long> speciallist = irpUserDAO.findAllUseridSpecialType(IrpUser.USER_STATUS_REG,IrpUser.SPECIAL_USER);
			if(speciallist.size()>0){
				 example.createCriteria().andUseridNotIn(speciallist)
                 						 .andStatusEqualTo(IrpUser.USER_STATUS_REG);				
			}else{
				 example.createCriteria().andStatusEqualTo(IrpUser.USER_STATUS_REG);
			}
			example.setOrderByClause(irpConfigDAO.selectCValueByCKey(IrpUser.SHARERANKINGBY));
 
			list = this.irpUserDAO.selectByExample(example,pageUtil);
			if(list!=null && list.size()!=0){
				 for (int i = 0; i < list.size(); i++) {
					//初始化用户图片地址
				    IrpUser u=list.get(i);
					list.get(i).setUserpic(u.getDefaultUserPic());
					list.get(i).setUsername(LoginUtil.getUserNameString(u));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List findUsernamebyquestionid(Integer _status, Long _docid,
			Integer _statusmost, Integer _mosttype) {
		// TODO Auto-generated method stub
		List list = null;
		list = this.irpUserDAO.findUsernamebyquestionid(_status, _docid, _statusmost, _mosttype);
		return list;
	}
	@Override
	public List<IrpUser> finds(String _email,String _username){	
		List<IrpUser> list=null;
		IrpUserExample example = new IrpUserExample();	
		example.createCriteria().andEmailEqualTo(_email).andUsernameEqualTo(_username);
		try {
			list=irpUserDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}	
	@Override
	public long  findSumScoreByUserId(Long _userid) {
		long sumscore = 0l;
		
		try {
			IrpUser irpUser =this.irpUserDAO.selectByPrimaryKey(_userid);
			sumscore = irpUser.getSumscore();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return sumscore;
	}

	@Override
	public int changeUserSpecialType(Long _userid, Integer _specialtype) {
		int status = 0;
		IrpUser irpUser = new IrpUser();		
		irpUser.setUserid(_userid);
		irpUser.setSpecialtype(_specialtype);
		try {
			status = this.irpUserDAO.updateByPrimaryKeySelective(irpUser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	@Override
	public List<Long> findAllSystemUserIds() {
		return irpUserDAO.findAllUserid(IrpUser.USER_STATUS_REG);
		 
	}
	@Override
	public List<IrpUser> likeUser(String username) {
		List<IrpUser> list=null;
		IrpUserExample example = new IrpUserExample();	
		example.createCriteria().andUsernameLike("%"+username+"%").andStatusEqualTo(IrpUser.USER_STATUS_REG);
		try {
			list= irpUserDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<IrpUser> findUserbySM(String sm,String searchWord,PageUtil pageUtil) {
		List<IrpUser> list=null;
		if(sm.equals("null")){
			sm="";
		}
		IrpUserExample example = new IrpUserExample();	
		if(searchWord!=null&&searchWord.trim().length()>0){
			example.createCriteria().andUsersmLike("%"+sm+"%").andTruenameLike("%"+searchWord+"%").andStatusEqualTo(IrpUser.USER_STATUS_REG);
		}else{
			example.createCriteria().andUsersmLike("%"+sm+"%").andStatusEqualTo(IrpUser.USER_STATUS_REG);
		}
		try {
			//用户状态为正常状态
			example.createCriteria().andStatusEqualTo(IrpUser.USER_STATUS_REG);
			example.setOrderByClause("userid");
			list= irpUserDAO.selectByExample(example,pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int findUserbySMcount(String sm,String searchWord) {
		IrpUserExample example = new IrpUserExample();	
		int count=0;
		if(sm.equals("null")){
			sm="";
		}
		if(searchWord!=null&&searchWord.trim().length()>0){
			example.createCriteria().andUsersmLike("%"+sm+"%").andTruenameLike("%"+searchWord+"%").andStatusEqualTo(IrpUser.USER_STATUS_REG);
		}else{
			example.createCriteria().andUsersmLike("%"+sm+"%").andStatusEqualTo(IrpUser.USER_STATUS_REG);
		}
		try {
			count= irpUserDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public String findShowNameByUserid(Long _userid) {
		return this.irpUserDAO.findShowNameByUserid(_userid);
	}

	@Override
	public String findShowNameByUsername(String _username) {
		String sShowName = _username;
		List<IrpUser> users = findUserByUsername(_username);
		if(users.size()>0){
			IrpUser user = users.get(0);
			if(user.getTruename()!=null && user.getTruename().trim().length()>0){
				sShowName = user.getTruename();
			}
		}
		return sShowName;
	}
	
	@Override
	public IrpUser findSumScoreAndSumExperence() {
		return irpUserDAO.findSumScoreAndSumExperence();
	}
	
	@Override
	public IrpUser findIrpUserByQQLogin(String accessToken, String qqUserId) {
		IrpUser user = null;
		IrpUserExample example = new IrpUserExample();
		example.createCriteria().andQqtokenEqualTo(accessToken).andQquseridEqualTo(qqUserId);
		try {
			List<IrpUser> list = irpUserDAO.selectByExample(example);
			if(list!=null && list.size()>0){
				user = list.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public IrpUser findIrpUserByWeiboLogin(String accessToken, String weiboUserId) {
		IrpUser user = null;
		IrpUserExample example = new IrpUserExample();
		example.createCriteria().andWeibotokenEqualTo(accessToken).andWeibouseridEqualTo(weiboUserId);
		try {
			List<IrpUser> list = irpUserDAO.selectByExample(example);
			if(list!=null && list.size()>0){
				user = list.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int userBindingEdit(IrpUser irpuser) {
		int nCount=0;
		try {
			nCount = irpUserDAO.updateByPrimaryKeySelective(irpuser);
			LoginUtil.updateCacheUser(irpuser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	public List<IrpUser> findExpertList(Long _nCategoryId, PageUtil pageUtil, String _sSearchWord, String _sSearchType) {
		Map<String, Object> mParam = new HashMap<String, Object>();
		mParam.put("roleId", Long.parseLong(SysConfigUtil.getSysConfigValue("EXPERT_ROLE_ID")));
		mParam.put("status", IrpUser.USER_STATUS_REG);
		if(_nCategoryId!=null && _nCategoryId>0L){
			mParam.put("classifyid", _nCategoryId);
		}
		if(_sSearchWord!=null && _sSearchType!=null){
			if("all".equals(_sSearchType)){
				mParam.put("username", "%"+_sSearchWord+"%");
				mParam.put("truename", "%"+_sSearchWord+"%");
			} else if("username".equals(_sSearchType)){
				mParam.put("username", "%"+_sSearchWord+"%");
			} else if("truename".equals(_sSearchType)){
				mParam.put("truename", "%"+_sSearchWord+"%");
			}
		}
		//排序字段
		mParam.put("orderStr", "userid desc");
		List<IrpUser> list = irpUserDAO.findExpertList(mParam, pageUtil);
		return list;
	}
	
	@Override
	public int findExpertCount(Long _nCategoryId, String _sSearchWord, String _sSearchType) {
		Map<String, Object> mParam = new HashMap<String, Object>();
		mParam.put("roleId", Long.parseLong(SysConfigUtil.getSysConfigValue("EXPERT_ROLE_ID")));
		mParam.put("status", IrpUser.USER_STATUS_REG);
		if(_nCategoryId!=null && _nCategoryId>0L){
			mParam.put("classifyid", _nCategoryId);
		}
		if(_sSearchWord!=null && _sSearchType!=null){
			if("all".equals(_sSearchType)){
				mParam.put("username", "%"+_sSearchWord+"%");
				mParam.put("truename", "%"+_sSearchWord+"%");
			} else if("username".equals(_sSearchType)){
				mParam.put("username", "%"+_sSearchWord+"%");
			} else if("truename".equals(_sSearchType)){
				mParam.put("truename", "%"+_sSearchWord+"%");
			}
		}
		int count = irpUserDAO.findExpertCount(mParam);
		return count;
	}
	
	@Override
	public List<IrpUser> userRankByDate(int _nDateType) {
		List<IrpUser> users = null;
		//获得不进行积分排行的用户ID集合
		List<Long> speciallist = irpUserDAO.findAllUseridSpecialType(IrpUser.USER_STATUS_REG,IrpUser.SPECIAL_USER);
		//积分用户数量
		int nUserCount = SysConfigUtil.getSysConfigNumValue(IrpUser.SHARERANKINGVIEWNUM);
		//获得开始时间和结束时间（1.本周  2.本月  3.本年  4.全部积分）
		Date beginDate = null;
		Date endDate = new Date();
		if(_nDateType==1){
			beginDate = DateUtils.dateTimeAdd(endDate, Calendar.WEDNESDAY, -1);
		}else if(_nDateType==2){
			beginDate = DateUtils.dateTimeAdd(endDate, Calendar.MONTH, -1);
		}else if(_nDateType==3){
			beginDate = DateUtils.dateTimeAdd(endDate, Calendar.YEAR, -1);
		}else if(_nDateType==4){
			IrpUserExample example = new IrpUserExample();
			if(speciallist.size()>0){
				 example.createCriteria().andUseridNotIn(speciallist)
                 						 .andStatusEqualTo(IrpUser.USER_STATUS_REG);				
			}else{
				 example.createCriteria().andStatusEqualTo(IrpUser.USER_STATUS_REG);
			}
			example.setOrderByClause("sumscore desc");
			PageUtil pageUtil = new PageUtil(1, nUserCount, nUserCount);
			try {
				users = irpUserDAO.selectByExample(example,pageUtil);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return users;
		}else{
			return null;
		}
		Map<String, Object> mParam = new HashMap<String, Object>();
		mParam.put("beginDate", beginDate);
		mParam.put("endDate", endDate);
		mParam.put("userids", speciallist);
		mParam.put("status", IrpUser.USER_STATUS_REG);
		
		List<Map<String, Object>> list = null;
		try {
			list = irpUserValueLinkDAO.userRankByDate(mParam, nUserCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(list!=null && list.size()>0){
			users = new ArrayList<IrpUser>();
			for (Map<String, Object> map : list) {
				if(map==null)
					continue;
				IrpUser irpuser = null;
				try {
					irpuser = irpUserDAO.selectByPrimaryKey(Long.parseLong(map.get("USERID").toString()));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(irpuser==null)
					continue;
				try {
					irpuser.setSumscore(Long.parseLong(map.get("TOTAL_SCORE").toString()));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				users.add(irpuser);
			}
		}else{
			//如果当前选择的时间类型没有数据，则选择下一种类型，知道类型大于4
			return userRankByDate(_nDateType+1);
		}
		return users;
	}

	@Override
	public List<IrpUser> findUsersByGroupIdAndRoleId(String _cruserid,Long _groupId,
			Long _roleId, PageUtil page,String _sSearchWord, String _sSearchType) {
		// TODO Auto-generated method stub
		List<IrpUser> list =new ArrayList<IrpUser>();
		List<Long> userids = new ArrayList<Long>();
		Map<String, Object> mParam = new HashMap<String, Object>();
		Long userid = LoginUtil.getLoginUserId();
		if(_groupId!=null && _groupId!=IrpGroup.GROUP_ROOTID_PUBLIC && _groupId!=IrpGroup.GROUP_ROOTID_PRIVATE){
			mParam.put("groupId", _groupId);
		}
		mParam.put("roleId", _roleId);
		mParam.put("status", IrpUser.USER_STATUS_REG);
		userids.add(userid);
		if(_cruserid!=null&&!_cruserid.equals("")){
			Long cruserid = Long.parseLong(_cruserid);
			userids.add(cruserid);
		}	
		mParam.put("userids", userids);	
		if(_sSearchWord!=null && _sSearchType!=null){
			if("all".equals(_sSearchType)){
				mParam.put("username", "%"+_sSearchWord+"%");
				mParam.put("truename", "%"+_sSearchWord+"%");
			} else if("username".equals(_sSearchType)){
				mParam.put("username", "%"+_sSearchWord+"%");
			} else if("truename".equals(_sSearchType)){
				mParam.put("truename", "%"+_sSearchWord+"%");
			}
		}
		try {
			list= irpUserDAO.findUserByGroupIdAndRoleId(mParam, page);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int countByByGroupIdAndRoleId(String _cruserid,Long _groupId, Long _roleId,
			String _sSearchWord, String _sSearchType) {
		// TODO Auto-generated method stub
				int count = 0;
				Map<String, Object> mParam = new HashMap<String, Object>();
				List<Long> userids = new ArrayList<Long>();
				Long userid = LoginUtil.getLoginUserId();
				if(_groupId!=null && _groupId!=IrpGroup.GROUP_ROOTID_PUBLIC && _groupId!=IrpGroup.GROUP_ROOTID_PRIVATE){
					mParam.put("groupId", _groupId);
				}
				mParam.put("roleId", _roleId);
				mParam.put("status", IrpUser.USER_STATUS_REG);
				userids.add(userid);
				if(_cruserid!=null&&!_cruserid.equals("")){
					Long cruserid = Long.parseLong(_cruserid);
					userids.add(cruserid);
				}			
				mParam.put("userids", userids);	
				if(_sSearchWord!=null && _sSearchType!=null){
					if("all".equals(_sSearchType)){
						mParam.put("username", "%"+_sSearchWord+"%");
						mParam.put("truename", "%"+_sSearchWord+"%");
					} else if("username".equals(_sSearchType)){
						mParam.put("username", "%"+_sSearchWord+"%");
					} else if("truename".equals(_sSearchType)){
						mParam.put("truename", "%"+_sSearchWord+"%");
					}
				}
				try {
					count= irpUserDAO.findCountByGroupIdAndRoleId(mParam);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return count;
	}

	@Override
	public int updateUserWithChoose(Long userid, Integer holiday) {
		int _status = 0;
		IrpUser irpUser = new IrpUser();
		irpUser.setUserid(userid);
		irpUser.setHoliday(holiday);
		LogUtil logUtil = new LogUtil("USER_EDITYEAR", irpUser);
		try {
			_status=this.irpUserDAO.updateYearDays(irpUser);
			logUtil.successLogs("修改用户ID["+irpUser.getUserid()+"]年假天数为["+irpUser.getHoliday()+"]成功");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs("修改用户ID["+irpUser.getUserid()+"]年假天数为["+irpUser.getHoliday()+"]失败");
		}
		return _status;
	}
	
	public List<IrpUser> getByToken(String token){
		List<IrpUser> irpuser = new ArrayList<IrpUser>();
		IrpUserExample example = new IrpUserExample();
		example.createCriteria().andTokenEqualTo(token);
		try {
			irpuser = irpUserDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return irpuser;
	}

	@Override
	public IrpUser getRolebyUser(IrpUser currIrpUser) {
		//获取知识管理
		long documentRoleId=Long.parseLong(SysConfigUtil.getSysConfigValue("INFORM_DOCUMENT_ROLE_ID"));
		//获得专家角色
		long expertRoleId=Long.parseLong(SysConfigUtil.getSysConfigValue("EXPERT_ROLE_ID"));
		//获取微知管理员
		long informRoleId=Long.parseLong(SysConfigUtil.getSysConfigValue("INFORM_ROLE_ID"));
		//获取专题管理员
		long topicRoleId=Long.parseLong(SysConfigUtil.getSysConfigValue("TOPIC_ROLE_ID"));
		//获得词条管理员
		long termsroleid = Long.parseLong(SysConfigUtil.getSysConfigValue("TERMS_ROLE_ID"));
		//获取问答管理员
		long questionRoleId = Long.parseLong(SysConfigUtil.getSysConfigValue("QUESTION_ROLE_ID"));
		//获取教师角色
		long teacherroleId = Long.parseLong(SysConfigUtil.getSysConfigValue("TEACHER_ROLE_ID"));
		//获取请假管理员
		long leaveapplyroleId = Long.parseLong(SysConfigUtil.getSysConfigValue("LEAVEAPPLY_ROLE_ID"));

		//获取会议室管理员
		long asseroomroleId = Long.parseLong(SysConfigUtil.getSysConfigValue("ASSEROOM_ROLE_ID"));
		//获取加班管理员
		long workapplyroleId = Long.parseLong(SysConfigUtil.getSysConfigValue("WORKAPPLY_ROLE_ID"));
	

		//用户id为1(admin用户)的默认设置成系统管理员
		if(currIrpUser.getUserid()==1L){
			currIrpUser.setAdministrator(true);
		}
		
		//获得用户角色
		List<Long> roleIds;
		try {
			roleIds = irpUserroleLinkDAO.findRoleIdByUserId(currIrpUser.getUserid());
			for (long nRoleId : roleIds) {
				if(nRoleId==1L && !currIrpUser.isAdministrator()){
					
					currIrpUser.setAdministrator(true);
				}else if(nRoleId==questionRoleId){
					currIrpUser.setQuestionManager(true);
				}else if(nRoleId==termsroleid){
					currIrpUser.setTermswordManager(true);
				}else if(nRoleId==topicRoleId){
					currIrpUser.setTopicManager(true);
				}else if(nRoleId==informRoleId){
					currIrpUser.setMicroblogManager(true);
				}else if(nRoleId==expertRoleId){
					currIrpUser.setExpert(true);
				}else if(nRoleId==documentRoleId){
					currIrpUser.setDocumentManager(true);
				}else if(nRoleId==teacherroleId){
					currIrpUser.setTeacherManager(true);
				}else if(nRoleId==leaveapplyroleId){
					currIrpUser.setLeaveApplyManager(true);		
				}else if(nRoleId==asseroomroleId){
					currIrpUser.setAsseroomManager(true);
					
				}else if(nRoleId==workapplyroleId){
					currIrpUser.setWorkApplyManager(true);		
				}
			}
			return currIrpUser;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currIrpUser;
		
	}

	@Override
	public IrpUser boolUserTokenAndUser(String _token) {
		// TODO Auto-generated method stub
		IrpUser user = null;
		IrpUserExample example = new IrpUserExample();
		example.createCriteria().andTokenEqualTo(_token);
		
		try {
			List<IrpUser> userlist = this.irpUserDAO.selectByExample(example);
			if (userlist.isEmpty()==false) {
				user = userlist.get(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	@Override
	public List<IrpUser> findHotExpertList(Long _nCategoryId,PageUtil _pageUtil) {
		List<IrpUser> users = null;
		Map<String, Object> mParam = new HashMap<String, Object>();
		mParam.put("roleId", 2);
		if(_nCategoryId!=null && _nCategoryId>0L){
			mParam.put("classifyid", _nCategoryId);
		}
		String _sOrderBy = "answercount desc";
		mParam.put("orderStr", _sOrderBy);
		try {
			users = irpUserDAO.findUsersByRoleId(mParam,_pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	@Override
	public List<IrpUser> findRecExpertList(PageUtil _pageUtil) {
		List<IrpUser> users = null;
		Map<String, Object> mParam = new HashMap<String, Object>();
		mParam.put("roleId", 2);
		mParam.put("recommend", 1);
		String _sOrderBy = "crtime desc";
		mParam.put("orderStr", _sOrderBy);
		try {
			users = irpUserDAO.findRecExpertByRoleId(mParam,_pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void setExpertRecommend(Long nUserid, String experttype) {
		if(experttype==null || experttype==""){
			experttype="0";
		}
		int expert = Integer.valueOf(experttype);
		IrpUser irpuser = null;
		try {
			irpuser = irpUserDAO.selectByPrimaryKey(nUserid); 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(expert==0){
			irpuser.setExperttype(null);
			try {
				irpUserDAO.updateByPrimaryKey(irpuser);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			irpuser.setExperttype(expert);
			try {
				irpUserDAO.updateByPrimaryKeySelective(irpuser);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<IrpUser> userRankByDateNotInList(int _nDateType,
			List<Long> listArraylist, long mustFocus) {
		List<IrpUser> users = null;
		//获得不进行积分排行的用户ID集合
		List<Long> speciallist = irpUserDAO.findAllUseridSpecialType(IrpUser.USER_STATUS_REG,IrpUser.SPECIAL_USER);
		if(speciallist!=null){
			speciallist.addAll(listArraylist);
		}else{
			speciallist=new ArrayList<Long>();
			speciallist.addAll(listArraylist);
		}
		int nUserCount = Integer.parseInt(mustFocus+"");
		//获得开始时间和结束时间（1.本周  2.本月  3.本年  4.全部积分）
		Date beginDate = null;
		Date endDate = new Date();
		if(_nDateType==1){
			beginDate = DateUtils.dateTimeAdd(endDate, Calendar.WEDNESDAY, -1);
		}else if(_nDateType==2){
			beginDate = DateUtils.dateTimeAdd(endDate, Calendar.MONTH, -1);
		}else if(_nDateType==3){
			beginDate = DateUtils.dateTimeAdd(endDate, Calendar.YEAR, -1);
		}else if(_nDateType==4){
			IrpUserExample example = new IrpUserExample();
			if(speciallist.size()>0){
				 example.createCriteria().andUseridNotIn(speciallist)
                 						 .andStatusEqualTo(IrpUser.USER_STATUS_REG);				
			}else{
				 example.createCriteria().andStatusEqualTo(IrpUser.USER_STATUS_REG);
			}
			example.setOrderByClause("sumscore desc");
			PageUtil pageUtil = new PageUtil(1, nUserCount, nUserCount);
			try {
				users = irpUserDAO.selectByExample(example,pageUtil);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return users;
		}else{
			return null;
		}
		Map<String, Object> mParam = new HashMap<String, Object>();
		mParam.put("beginDate", beginDate);
		mParam.put("endDate", endDate);
		mParam.put("userids", speciallist);
		mParam.put("status", IrpUser.USER_STATUS_REG);
		
		List<Map<String, Object>> list = null;
		try {
			list = irpUserValueLinkDAO.userRankByDate(mParam, nUserCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(list!=null && list.size()>0){
			users = new ArrayList<IrpUser>();
			for (Map<String, Object> map : list) {
				if(map==null)
					continue;
				IrpUser irpuser = null;
				try {
					irpuser = irpUserDAO.selectByPrimaryKey(Long.parseLong(map.get("USERID").toString()));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(irpuser==null)
					continue;
				try {
					irpuser.setSumscore(Long.parseLong(map.get("TOTAL_SCORE").toString()));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				users.add(irpuser);
			}
		}else{
			//如果当前选择的时间类型没有数据，则选择下一种类型，知道类型大于4
			return userRankByDate(_nDateType+1);
		}
		return users;
	}

	@Override
	public List<IrpUser> getUserBycategoryid(Long categoryId, Long expertId) {
		String  sql="select * from IRP_USER where userid in (select userid from  IRP_EXPERT_CLASSIFY_LINK where classifyid="+categoryId+" and userid !="+expertId+")";
		List<IrpUser> list = new ArrayList<IrpUser>();
	    list = this.irpUserDAO.getUserBycategoryid(sql);
	    return list;
	}
}
