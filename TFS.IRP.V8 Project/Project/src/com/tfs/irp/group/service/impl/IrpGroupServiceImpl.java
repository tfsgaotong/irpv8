package com.tfs.irp.group.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tfs.irp.category.entity.IrpCategory;
import com.tfs.irp.group.dao.IrpGroupDAO;
import com.tfs.irp.group.dao.IrpUsergroupLinkDAO;
import com.tfs.irp.group.entity.IrpGroup;
import com.tfs.irp.group.entity.IrpGroupExample;
import com.tfs.irp.group.entity.IrpUsergroupLink;
import com.tfs.irp.group.entity.IrpUsergroupLinkExample;
import com.tfs.irp.group.entity.IrpUsergroupLinkExample.Criteria;
import com.tfs.irp.group.service.IrpGroupService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.entity.IrpUserExample;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpGroupServiceImpl implements IrpGroupService {
	private IrpGroupDAO irpGroupDAO;
	
	private IrpUserService irpUserService;
	
	private IrpUsergroupLinkDAO irpUsergroupLinkDAO;

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public IrpGroupDAO getIrpGroupDAO() {
		return irpGroupDAO;
	}

	public void setIrpGroupDAO(IrpGroupDAO irpGroupDAO) {
		this.irpGroupDAO = irpGroupDAO;
	}
	
	public IrpUsergroupLinkDAO getIrpUsergroupLinkDAO() {
		return irpUsergroupLinkDAO;
	}

	public void setIrpUsergroupLinkDAO(IrpUsergroupLinkDAO irpUsergroupLinkDAO) {
		this.irpUsergroupLinkDAO = irpUsergroupLinkDAO;
	}

	@Override
	public List<IrpGroup> findGroupsByParentId(Long _nParentId){
		return findGroupsByParentId(_nParentId, 0);
	}
	
	@Override
	public List<IrpGroup> findGroupsByParentId(Long _nParentId, long _nGroupType){
		List<IrpGroup> groups = null;
		IrpGroupExample example = new IrpGroupExample();
		com.tfs.irp.group.entity.IrpGroupExample.Criteria criteria = example.createCriteria();
		criteria.andParentidEqualTo(_nParentId);

		//验证组织类型
		if(_nGroupType>0){
			criteria.andGrouptypeEqualTo(_nGroupType);
		}
		
		example.setOrderByClause("grouporder asc");
		try {
			groups = irpGroupDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groups;
	}
	
	@Override
	public List<IrpGroup> findGroupsOfPageByParentId(PageUtil _pageUtil,Long _nParentId,String _sOrderBy, String _sSearchWord, String _sSearchType){
		List<IrpGroup> groups = null;
		IrpGroupExample example = new IrpGroupExample();
		if("all".equals(_sSearchType)){
			example.or(example.createCriteria().andParentidEqualTo(_nParentId).andGroupnameLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andParentidEqualTo(_nParentId).andGdescLike("%"+_sSearchWord+"%"));
		} else if("groupname".equals(_sSearchType)){
			example.createCriteria().andParentidEqualTo(_nParentId).andGroupnameLike("%"+_sSearchWord+"%");
		} else if("gdesc".equals(_sSearchType)){
			example.createCriteria().andParentidEqualTo(_nParentId).andGdescLike("%"+_sSearchWord+"%");
		} else{
			example.createCriteria().andParentidEqualTo(_nParentId);
		}
		if(_sOrderBy==null || _sOrderBy.equals("")){
			_sOrderBy = "groupid desc";
		}
		example.setOrderByClause(_sOrderBy);
		try {
			groups = irpGroupDAO.selectByExample(example, _pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groups;
	}
	
	@Override
	public List<IrpGroup> findGroupsParentId(PageUtil _pageUtil,Long _nParentId,String _sOrderBy, String _sSearchWord, String _sSearchType){
		List<IrpGroup> groups = null;
		IrpGroupExample example = new IrpGroupExample();
		if("all".equals(_sSearchType)){
			example.or(example.createCriteria().andParentidEqualTo(_nParentId).andGroupnameLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andParentidEqualTo(_nParentId).andGdescLike("%"+_sSearchWord+"%"));
		} else if("groupname".equals(_sSearchType)){
			example.createCriteria().andParentidEqualTo(_nParentId).andGroupnameLike("%"+_sSearchWord+"%");
		} else if("gdesc".equals(_sSearchType)){
			example.createCriteria().andParentidEqualTo(_nParentId).andGdescLike("%"+_sSearchWord+"%");
		} else{
			example.createCriteria().andParentidEqualTo(_nParentId);
		}
		if(_sOrderBy==null || _sOrderBy.equals("")){
			_sOrderBy = "groupid desc";
		}
		example.setOrderByClause(_sOrderBy);
		try {
			groups = irpGroupDAO.selectByExample(example, _pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groups;
	}
	
	@Override
	public int findGroupsCountByParentId(Long _nParentId, String _sSearchWord, String _sSearchType){
		int nCount=0;
		IrpGroupExample example = new IrpGroupExample();
		if("all".equals(_sSearchType)){
			example.or(example.createCriteria().andParentidEqualTo(_nParentId).andGroupnameLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andParentidEqualTo(_nParentId).andGdescLike("%"+_sSearchWord+"%"));
		} else if("groupname".equals(_sSearchType)){
			example.createCriteria().andParentidEqualTo(_nParentId).andGroupnameLike("%"+_sSearchWord+"%");
		} else if("gdesc".equals(_sSearchType)){
			example.createCriteria().andParentidEqualTo(_nParentId).andGdescLike("%"+_sSearchWord+"%");
		} else{
			example.createCriteria().andParentidEqualTo(_nParentId);
		}
		try {
			nCount = irpGroupDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	
	@Override
	public int findGroupsCountByGroupId(Long _nGroupId, String _sSearchWord, String _sSearchType){
		int nCount=0;
		IrpGroupExample example = new IrpGroupExample();
		if("all".equals(_sSearchType)){
			example.or(example.createCriteria().andGroupidEqualTo(_nGroupId).andGroupnameLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andGroupidEqualTo(_nGroupId).andGdescLike("%"+_sSearchWord+"%"));
		} else if("groupname".equals(_sSearchType)){
			example.createCriteria().andGroupidEqualTo(_nGroupId).andGroupnameLike("%"+_sSearchWord+"%");
		} else if("gdesc".equals(_sSearchType)){
			example.createCriteria().andGroupidEqualTo(_nGroupId).andGdescLike("%"+_sSearchWord+"%");
		} else{
			example.createCriteria().andGroupidEqualTo(_nGroupId);
		}
		try {
			nCount = irpGroupDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public IrpGroup findGroupByGroupId(Long _nGroupId) {
		IrpGroup irpGroup = null;
		if(_nGroupId>0){
			try {
				irpGroup = irpGroupDAO.selectByPrimaryKey(_nGroupId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return irpGroup;
	}
	
	@Override
	public void groupEdit(IrpGroup _irpGroup, IrpUser _crUser) {
		if(_irpGroup.getGroupid()==null || _irpGroup.getGroupid()==0){
			_irpGroup.setGroupid(TableIdUtil.getNextId(IrpGroup.TABLE_NAME));
			_irpGroup.setCruser(_crUser.getName());
			_irpGroup.setCruserid(_crUser.getId());
			_irpGroup.setCrtime(new Date());
			LogUtil logUtil = new LogUtil("GROUP_ADD", _irpGroup);
			try {
				irpGroupDAO.insertSelective(_irpGroup);
				logUtil.successLogs();
			} catch (SQLException e) {
				e.printStackTrace();
				logUtil.errorLogs(e);
			}
		}else{
			LogUtil logUtil = new LogUtil("GROUP_EDIT", _irpGroup);
			try {
				irpGroupDAO.updateByPrimaryKeySelective(_irpGroup);
				logUtil.successLogs();
			} catch (SQLException e) {
				e.printStackTrace();
				logUtil.errorLogs(e);
				
			}
		}
	}
	
	@Override
	public List<IrpUser> findUsersOfPageByGroupId(PageUtil _pageUtil,Long _nGroupId,String _sOrderBy, String _sSearchWord, String _sSearchType) {
		List<IrpUser> users = null;
		Map<String, Object> mParam = new HashMap<String, Object>();
		if(_nGroupId!=null && _nGroupId!=IrpGroup.GROUP_ROOTID_PUBLIC && _nGroupId!=IrpGroup.GROUP_ROOTID_PRIVATE){
			mParam.put("groupId", _nGroupId);
		}
		mParam.put("status", IrpUser.USER_STATUS_REG);
		if("all".equals(_sSearchType)){
			mParam.put("username", "%"+_sSearchWord+"%");
			mParam.put("truename", "%"+_sSearchWord+"%");
		} else if("username".equals(_sSearchType)){
			mParam.put("username", "%"+_sSearchWord+"%");
		} else if("truename".equals(_sSearchType)){
			mParam.put("truename", "%"+_sSearchWord+"%");
		} 
		if(_sOrderBy==null || _sOrderBy.equals("")){
			_sOrderBy = "userid desc";
		}
		mParam.put("orderStr", _sOrderBy);
		try {
			users = irpGroupDAO.findUsersOfPageByGroupId(mParam, _pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	@Override
	public List<IrpUser> findUsersOfPageByGroupId(PageUtil _pageUtil,Long _nGroupId,String _sOrderBy, String _sSearchWord, String _sSearchType,String sm) {
		List<IrpUser> users = null;
		Map<String, Object> mParam = new HashMap<String, Object>();
		if(_nGroupId!=null && _nGroupId!=IrpGroup.GROUP_ROOTID_PUBLIC && _nGroupId!=IrpGroup.GROUP_ROOTID_PRIVATE){
			mParam.put("groupId", _nGroupId);
		}
		mParam.put("status", IrpUser.USER_STATUS_REG);
		if("all".equals(_sSearchType)){
			mParam.put("username", "%"+_sSearchWord+"%");
			mParam.put("truename", "%"+_sSearchWord+"%");
		} else if("username".equals(_sSearchType)){
			mParam.put("username", "%"+_sSearchWord+"%");
		} else if("truename".equals(_sSearchType)){
			mParam.put("truename", "%"+_sSearchWord+"%");
		} 
		if(!sm.equals("null")){
			mParam.put("sname", sm);
		}
		if(_sOrderBy==null || _sOrderBy.equals("")){
			_sOrderBy = "userid desc";
		}
		mParam.put("orderStr", _sOrderBy);
		try {
			users = irpGroupDAO.findUsersByGroupIdAndSm(mParam, _pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	@Override
	public Integer findUsersCountByGroupId(Long _nGroupId, String _sSearchWord, String _sSearchType) {
		int nCount = 0;
		try {
			Map<String, Object> mParameter = new HashMap<String, Object>();
			if(_nGroupId!=null && _nGroupId!=IrpGroup.GROUP_ROOTID_PUBLIC && _nGroupId!=IrpGroup.GROUP_ROOTID_PRIVATE){
				mParameter.put("groupId", _nGroupId);
			}
			mParameter.put("status", IrpUser.USER_STATUS_REG);
			if("all".equals(_sSearchType)){
				mParameter.put("username", "%"+_sSearchWord+"%");
				mParameter.put("truename", "%"+_sSearchWord+"%");
			} else if("username".equals(_sSearchType)){
				mParameter.put("username", "%"+_sSearchWord+"%");
			} else if("truename".equals(_sSearchType)){
				mParameter.put("truename", "%"+_sSearchWord+"%");
			} 
			nCount = irpGroupDAO.findUsersCountByGroupId(mParameter);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	public Map<Long, String> findAdminUsersByGroupId(Long _nGroupId) {
		Map<Long, String> map = new HashMap<Long, String>();
		IrpUsergroupLinkExample example = new IrpUsergroupLinkExample();
		example.createCriteria().andGroupidEqualTo(_nGroupId);
		try {
			List<IrpUsergroupLink> list = irpUsergroupLinkDAO.selectByExample(example);
			if(list!=null && list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					IrpUsergroupLink irpUsergroupLink = list.get(i);
					map.put(irpUsergroupLink.getUserid(), irpUsergroupLink.getIsadministrator()==1?"是":"否");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@Override
	public String findGrpUserIdsByGroupId(Long nGroupId) {
		String sUserIds = "";
		IrpUsergroupLinkExample example = new IrpUsergroupLinkExample();
		example.createCriteria().andGroupidEqualTo(nGroupId);
		try {
			List<IrpUsergroupLink> irpUsergroupLinks = irpUsergroupLinkDAO.selectByExample(example);
			for (int i = 0; i < irpUsergroupLinks.size(); i++) {
				IrpUsergroupLink irpUsergroupLink =  irpUsergroupLinks.get(i);
				if(i>0){
					sUserIds += ","+irpUsergroupLink.getUserid();
				}else{
					sUserIds += irpUsergroupLink.getUserid();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sUserIds;
	}

	@Override
	public int importGroupUserByGroupId(Long _nGroupId, String _sUserIds) {
		int nResult = 1;
		//获得组织下的所有用户
		IrpUsergroupLinkExample example = new IrpUsergroupLinkExample();
		example.createCriteria().andGroupidEqualTo(_nGroupId);
		List<IrpUsergroupLink> irpUsergroupLinks = null;
		IrpGroup group = new IrpGroup();
		group.setGroupid(_nGroupId);
		LogUtil logUtil = new LogUtil("GROUP_EDIT", group);
		try {
			irpUsergroupLinks = irpUsergroupLinkDAO.selectByExample(example);
			if(irpUsergroupLinks==null){
				irpUsergroupLinks = new ArrayList<IrpUsergroupLink>();
			}
			String[] arrUserIds = _sUserIds.split(",");
			//如果当前组织下有用户，则筛选出已有的用户数组
			if(irpUsergroupLinks.size()>0){
				for(int i=0;i<irpUsergroupLinks.size();i++){
					IrpUsergroupLink irpUsergroupLink = irpUsergroupLinks.get(i);
					if(irpUsergroupLink==null){
						continue;
					}
					//在数组中删除元素
					arrUserIds =(String[]) ArrayUtils.removeElement(arrUserIds, irpUsergroupLink.getUserid().toString());
				}
			}
			//添加筛选后的用户到组织中
			insertGroupUserByGroupId(_nGroupId, arrUserIds, LoginUtil.getLoginUser());
			//删除数据库中未选择的用户
			deleteNotInGroupUserByGroupId(_nGroupId, _sUserIds);
			logUtil.successLogs("为组织ID["+_nGroupId+"]添加用户ID["+_sUserIds+"]");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs("为组织ID["+_nGroupId+"]添加用户ID["+_sUserIds+"]",e);
			nResult = 0;
		}
		return nResult;
	}
	
	/**
	 * 添加用户到组织中
	 * @param _nGroupId
	 * @param _arrUserIds
	 * @param _crUser
	 */
	private void insertGroupUserByGroupId(Long _nGroupId, String[] _arrUserIds, IrpUser _crUser){
		for(int i=0;i<_arrUserIds.length;i++){
			String sUserId = _arrUserIds[i];
			if(sUserId==null || "".equals(sUserId)){
				continue;
			}
			IrpUsergroupLink irpUsergroupLink = new IrpUsergroupLink();
			irpUsergroupLink.setUsergroupid(TableIdUtil.getNextId(IrpUsergroupLink.TABLE_NAME));
			irpUsergroupLink.setGroupid(_nGroupId);
			irpUsergroupLink.setUserid(Long.parseLong(sUserId));
			irpUsergroupLink.setIsadministrator(0);
			irpUsergroupLink.setCrtime(new Date());
			irpUsergroupLink.setCruser(_crUser.getName());
			irpUsergroupLink.setCruserid(_crUser.getId());
			try {
				irpUsergroupLinkDAO.insertSelective(irpUsergroupLink);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 删除用户组织中没有选择的用户
	 * @param _nGroupId
	 * @param _sUserIds
	 */
	private void deleteNotInGroupUserByGroupId(Long _nGroupId, String _sUserIds){
		String[] arrUserIds = _sUserIds.split(",");
		List<Long> values = new ArrayList<Long>();
		for(int i=0;i<arrUserIds.length;i++){
			String sUserId = arrUserIds[i];
			if(sUserId==null || "".equals(sUserId)){
				continue;
			}
			values.add(Long.parseLong(sUserId));
		}
		IrpUsergroupLinkExample example = new IrpUsergroupLinkExample();
		Criteria criteria = example.createCriteria();
		criteria.andGroupidEqualTo(_nGroupId);
		if(values.size()>0){
			criteria.andUseridNotIn(values);
		}
		try {
			irpUsergroupLinkDAO.deleteByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int removeUserGroup(Long _nGroupId, String _sUserIds) {
		//构造用户ID集合
		List<Long> values = new ArrayList<Long>();
		String[] array = _sUserIds.split(",");
		for (int i = 0; i < array.length; i++) {
			String userid = array[i];
			if(userid==null || userid.equals("")){
				continue;
			}
			values.add(Long.parseLong(userid));
		}
		
		//构造条件
		IrpUsergroupLinkExample example = new IrpUsergroupLinkExample();
		example.createCriteria().andGroupidEqualTo(_nGroupId).andUseridIn(values);
		int nRow = 0;
		IrpGroup group = new IrpGroup();
		group.setGroupid(_nGroupId);
		LogUtil logUtil = new LogUtil("GROUP_EDIT", group);
		try {
			nRow = irpUsergroupLinkDAO.deleteByExample(example);
			logUtil.successLogs("将用户ID["+_sUserIds+"]从组织ID["+_nGroupId+"]移除");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs("将用户ID["+_sUserIds+"]从组织ID["+_nGroupId+"]移除",e);
		}
		return nRow;
	}
	
	@Override
	public int setGroupAdmin(Long _nGroupId, String _sUserIds, int _nStatus){
		//构造用户ID集合
		List<Long> values = new ArrayList<Long>();
		String[] array = _sUserIds.split(",");
		for (int i = 0; i < array.length; i++) {
			String userid = array[i];
			if(userid==null || userid.equals("")){
				continue;
			}
			values.add(Long.parseLong(userid));
		}
		
		//构造条件
		IrpUsergroupLinkExample example = new IrpUsergroupLinkExample();
		example.createCriteria().andGroupidEqualTo(_nGroupId).andUseridIn(values);
		int nRow = 0;
		IrpGroup group = new IrpGroup();
		group.setGroupid(_nGroupId);
		LogUtil logUtil = new LogUtil("GROUP_EDIT", group);
		try {
			IrpUsergroupLink record = new IrpUsergroupLink();
			record.setIsadministrator(_nStatus);
			nRow = irpUsergroupLinkDAO.updateByExampleSelective(record, example);
			if(_nStatus==1){
				logUtil.successLogs("设置用户ID["+_sUserIds+"]为组织ID["+_nGroupId+"]的管理员");
			}else{
				logUtil.successLogs("取消用户ID["+_sUserIds+"]为组织ID["+_nGroupId+"]的管理员");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			if(_nStatus==1){
				logUtil.errorLogs("设置用户ID["+_sUserIds+"]为组织ID["+_nGroupId+"]的管理员",e);
			}else{
				logUtil.errorLogs("取消用户ID["+_sUserIds+"]为组织ID["+_nGroupId+"]的管理员",e);
			}
		}
		return nRow;
	}
	
	@Override
	public int deleteGroup(String _sGroupIds, Long _nParentId) {
		List<Long> values = new ArrayList<Long>();
		String[] array = _sGroupIds.split(",");
		for (int i = 0; i < array.length; i++) {
			String sGroupId = array[i];
			if(sGroupId==null || sGroupId.equals("")){
				continue;
			}
			Long nGroupId = Long.parseLong(sGroupId);
			values.add(nGroupId);
			//递归获得组织的所有子组织
			values = findGroupIdOfChildByGroupId(nGroupId, values);
		}
		int nRow = 0;
		IrpGroup group = new IrpGroup();
		group.setGroupid(Long.parseLong(_sGroupIds.split(",")[0]));
		LogUtil logUtil = new LogUtil("GROUP_EDIT", group);
		try {
			nRow = irpGroupDAO.deleteByGroupIds(values, _nParentId);
			logUtil.successLogs("批量删除组织ID["+_sGroupIds+"]");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs("批量删除组织ID["+_sGroupIds+"]",e);
		}
		return nRow;
	}
	
	@Override
	public boolean checkGroupName(String _sGroupName, Long _nGroupId, Long _nParentId) {
		int nCount = 0;
		IrpGroupExample example = new IrpGroupExample();
		example.createCriteria().andParentidEqualTo(_nParentId).andGroupidNotEqualTo(_nGroupId).andGroupnameEqualTo(_sGroupName);
		try {
			nCount = irpGroupDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount>0?false:true;
	}
	
	@Override
	public int importGroupUserByUserId(Long _nUserId, String _sGroupIds) {
		int nResult = 1;
		//获得组织下的所有用户
		IrpUsergroupLinkExample example = new IrpUsergroupLinkExample();
		example.createCriteria().andUseridEqualTo(_nUserId);
		List<IrpUsergroupLink> irpUsergroupLinks = null;
		IrpUser user = new IrpUser();
		user.setUserid(_nUserId);
		LogUtil logUtil = new LogUtil("USER_EDIT", user);
		try {
			irpUsergroupLinks = irpUsergroupLinkDAO.selectByExample(example);
			if(irpUsergroupLinks==null){
				irpUsergroupLinks = new ArrayList<IrpUsergroupLink>();
			}
			
			String[] arrGroupIds = _sGroupIds.split(",");
			//如果当前组织下有用户，则筛选出已有的用户数组
			if(irpUsergroupLinks.size()>0){
				for(int i=0;i<irpUsergroupLinks.size();i++){
					IrpUsergroupLink irpUsergroupLink = irpUsergroupLinks.get(i);
					if(irpUsergroupLink==null){
						continue;
					}
					//在数组中删除元素
					arrGroupIds =(String[]) ArrayUtils.removeElement(arrGroupIds, irpUsergroupLink.getGroupid().toString());
				}
			}
			//添加筛选后的用户到组织中
			insertGroupUserByUserId(_nUserId, arrGroupIds, LoginUtil.getLoginUser());
			//删除数据库中未选择的用户
			deleteNotInGroupUserByUserId(_nUserId, _sGroupIds);
			logUtil.successLogs("为用户ID["+_nUserId+"]添加组织ID["+_sGroupIds+"]");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs("为用户ID["+_nUserId+"]添加组织ID["+_sGroupIds+"]",e);
			nResult = 0;
		}
		return nResult;
	}
	
	@Override
	public Map<Long, String> findGroupIdsByUserId(Long _nUserId) {
		Map<Long, String> map = new HashMap<Long, String>();
		List<Long> arrGroupIds = findGroupIdListByUserId(_nUserId, IrpGroup.GROUP_TYPE_PUBLIC);
		for (int i = 0; i < arrGroupIds.size(); i++) {
			Long nGroupId =  arrGroupIds.get(i);
			String sGroupAllName = findALlGroupDescByGroupId(nGroupId, "");
			map.put(nGroupId, sGroupAllName);
		}
		return map;
	}
	
	@Override
	public List<Long> findGroupIdListByUserId(Long _nUserId, Long _nGroupType) {
		List<Long> arrGroupIds = new ArrayList<Long>();
		try {
			Map<String, Object> mParam = new HashMap<String, Object>();
			mParam.put("grouptype", _nGroupType);
			mParam.put("userid", _nUserId);
			arrGroupIds = irpUsergroupLinkDAO.findGroupIdByUserId(mParam);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrGroupIds;
	}
	
	@Override
	public int findGrpUserCountByGroupIdAndUserId(Long _nGroupId, Long _nUserId) {
		int nCount = 0;
		IrpUsergroupLinkExample example = new IrpUsergroupLinkExample();
		example.createCriteria().andGroupidEqualTo(_nGroupId).andUseridEqualTo(_nUserId);
		try {
			nCount = irpUsergroupLinkDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	/**
	 * 添加组织到用户中
	 * @param _nUserId
	 * @param _arrGroupIds
	 * @param _crUser
	 */
	private void insertGroupUserByUserId(Long _nUserId, String[] _arrGroupIds, IrpUser _crUser){
		for(int i=0;i<_arrGroupIds.length;i++){
			String sGroupId = _arrGroupIds[i];
			if(sGroupId==null || "".equals(sGroupId)){
				continue;
			}
			IrpUsergroupLink irpUsergroupLink = new IrpUsergroupLink();
			irpUsergroupLink.setUsergroupid(TableIdUtil.getNextId(IrpUsergroupLink.TABLE_NAME));
			irpUsergroupLink.setGroupid(Long.parseLong(sGroupId));
			irpUsergroupLink.setUserid(_nUserId);
			irpUsergroupLink.setIsadministrator(0);
			irpUsergroupLink.setCrtime(new Date());
			irpUsergroupLink.setCruser(_crUser.getName());
			irpUsergroupLink.setCruserid(_crUser.getId());
			try {
				irpUsergroupLinkDAO.insertSelective(irpUsergroupLink);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 根据用户ID删除没有选择的组织
	 * @param _nUserId
	 * @param _sGroupIds
	 */
	private void deleteNotInGroupUserByUserId(Long _nUserId, String _sGroupIds){
		String[] arrGroupIds = _sGroupIds.split(",");
		List<Long> values = new ArrayList<Long>();
		for(int i=0;i<arrGroupIds.length;i++){
			String sGroupId = arrGroupIds[i];
			if(sGroupId==null || "".equals(sGroupId)){
				continue;
			}
			values.add(Long.parseLong(sGroupId));
		}
		IrpUsergroupLinkExample example = new IrpUsergroupLinkExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(_nUserId);
		if(values.size()>0){
			criteria.andGroupidNotIn(values);
		}
		try {
			irpUsergroupLinkDAO.deleteByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据组织ID获得组织的全名称
	 * @param _nGroupId
	 * @param _sGroupName
	 * @return
	 */
	private String findALlGroupDescByGroupId(Long _nGroupId, String _sGroupName) {
		if(_nGroupId<=0 && (_sGroupName==null || _sGroupName.length()==0)){
			return "";
		}
		if(_nGroupId<=0){
			return _sGroupName.substring(1);
		}
		try {
			IrpGroup irpGroup = irpGroupDAO.selectByPrimaryKey(_nGroupId);
			if(irpGroup==null){
				return _sGroupName.substring(1);
			}
			_sGroupName = "/" + irpGroup.getGdesc() + _sGroupName;
			_sGroupName = findALlGroupDescByGroupId(irpGroup.getParentid(), _sGroupName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return _sGroupName;
	}
	
	@Override
	public List<IrpGroup> findOrderGroupsByParentId(Long _nGroupId,	Long _nParentId) {
		List<IrpGroup> groups = null;
		IrpGroupExample example = new IrpGroupExample();
		example.createCriteria().andParentidEqualTo(_nParentId).andGroupidNotEqualTo(_nGroupId);
		example.setOrderByClause("grouporder asc");
		try {
			groups = irpGroupDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groups;
	}
	
	/**
	 * 根据组织ID获得组织的全名称
	 * @param _nGroupId
	 * @param _sGroupName
	 * @return
	 */
	private List<Long> findGroupIdOfChildByGroupId(Long _nParentId, List<Long> _arrGroupIds) {
		try {
			IrpGroupExample example = new IrpGroupExample();
			example.createCriteria().andParentidEqualTo(_nParentId);
			List<IrpGroup> irpGroups = irpGroupDAO.selectByExample(example);
			if(irpGroups==null || irpGroups.size()==0){
				return _arrGroupIds;
			}
			for (IrpGroup irpGroup : irpGroups) {
				if(irpGroup==null){
					continue;
				}
				Long nGroupId = irpGroup.getGroupid();
				_arrGroupIds.add(nGroupId);
				_arrGroupIds = findGroupIdOfChildByGroupId(nGroupId, _arrGroupIds);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return _arrGroupIds;
	}

	@Override
	public IrpGroup findRootGroupByGroupType(Long _nGroupType) {
		IrpGroup group = null;
		IrpGroupExample example = new IrpGroupExample();
		example.createCriteria().andGrouptypeEqualTo(_nGroupType).andParentidEqualTo(0L);
		try {
			List<IrpGroup> list = irpGroupDAO.selectByExample(example);
			if(list!=null && list.size()>0){
				group = list.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return group;
	}
	
	@Override
	public IrpGroup findRootUserGroupByUserId(Long _sUserId) {
		IrpGroup group = null;
		IrpGroupExample example = new IrpGroupExample();
		example.createCriteria().andGroupnameEqualTo(String.valueOf(_sUserId)).andGrouptypeEqualTo(IrpGroup.GROUP_TYPE_PRIVATE);
		try {
			List<IrpGroup> list = irpGroupDAO.selectByExample(example);
			if(list!=null && list.size()>0){
				group = list.get(0);
			}else{
				//如果没有用户个人组织节点，则添加。
				IrpUser user = irpUserService.findUserByUserId(_sUserId);
				irpUserService.addUserGroup(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return group;
	}
	
	@Override
	public List<IrpGroup> findUserGroup(Long _sUserId) {
		IrpGroup rootGroup = findRootUserGroupByUserId(_sUserId);
		if(rootGroup==null){
			return null;
		}
		
		List<IrpGroup> list = null;
		IrpGroupExample example = new IrpGroupExample();
		example.createCriteria().andParentidEqualTo(rootGroup.getGroupid()).andGrouptypeEqualTo(IrpGroup.GROUP_TYPE_PRIVATE);
		example.setOrderByClause("grouporder asc");
		try {
			list = irpGroupDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int findUserGroupCount(Long _sUserId) {
		int nDataCount = 0;
		IrpGroup rootGroup = findRootUserGroupByUserId(_sUserId);
		if(rootGroup==null){
			return nDataCount;
		}
		
		IrpGroupExample example = new IrpGroupExample();
		example.createCriteria().andParentidEqualTo(rootGroup.getGroupid()).andGrouptypeEqualTo(IrpGroup.GROUP_TYPE_PRIVATE);
		try {
			nDataCount = irpGroupDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nDataCount;
	}
	
	@Override
	public boolean userGroupEdit(IrpGroup group, Long _sUserId) {
		int nCount = 0;
		
		if(group.getParentid()==null || group.getParentid()==0L){
			IrpGroup rootGroup = findRootUserGroupByUserId(_sUserId);
			if(rootGroup==null){
				return false;
			}
			group.setParentid(rootGroup.getGroupid());
		}
		
		if(group.getGroupid()==null || group.getGroupid()==0L){
			group.setGroupid(TableIdUtil.getNextId(IrpGroup.TABLE_NAME));
			IrpUser loginUser = LoginUtil.getLoginUser();
			group.setGrouptype(IrpGroup.GROUP_TYPE_PRIVATE);
			group.setCrtime(new Date());
			group.setCruser(loginUser.getUsername());
			group.setCruserid(loginUser.getUserid());
			try {
				irpGroupDAO.insertSelective(group);
				nCount = 1;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			try {
				nCount = irpGroupDAO.updateByPrimaryKeySelective(group);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return nCount>0;
	}

	@Override
	public List<IrpUser> findUsersByGroupId(Long _nGroupId) {
		Map<String, Object> mParam = new HashMap<String, Object>();
		mParam.put("groupId", _nGroupId);
		mParam.put("status", IrpUser.USER_STATUS_REG);
		mParam.put("orderStr", "userid asc");
		List<IrpUser> list = null;
		try {
			list = irpGroupDAO.findUsersByGroupId(mParam);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<IrpUsergroupLink> findUserByGroupIdList(List<Long> _groupids) { 
		List<IrpUsergroupLink> list = null;
		try {
			IrpUsergroupLinkExample example=new IrpUsergroupLinkExample();
			example.createCriteria().andGroupidIn(_groupids);
			example.setOrderByClause( "userid asc");
			list = irpUsergroupLinkDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Long findGroupIdByGroup(String _groupname, Long _cruserid,
			Long _grouptype) {
		// TODO Auto-generated method stub
		Long groupid = 0l;
		IrpGroupExample example = new IrpGroupExample();
		example.createCriteria().andGroupnameEqualTo(_groupname)
								.andCruseridEqualTo(_cruserid)
								.andGrouptypeEqualTo(_grouptype);
		try {
			groupid = this.irpGroupDAO.selectByExample(example).get(0).getGroupid();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return groupid;
	}

	@Override
	public List<IrpGroup> ceshi(Long _printId) {
		List<IrpGroup> lis=null;
		IrpGroupExample example = new IrpGroupExample();
		example.createCriteria().andParentidEqualTo(_printId);
		try {
			lis=this.irpGroupDAO.selectByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lis;
	}

	@Override
	public Long findGroupIdByNPT(String _groupname, Long _parentid,
			Long _grouptype) {
		// TODO Auto-generated method stub
		Long  groupid = null;
		
		IrpGroupExample example = new IrpGroupExample();
		example.createCriteria().andGroupnameEqualTo(_groupname)
								.andParentidEqualTo(_parentid)
								.andGrouptypeEqualTo(_grouptype);
		
		PageUtil pageutil = new PageUtil(1, 1, 0);
		
		try {
			List<IrpGroup> list = this.irpGroupDAO.selectByExample(example,pageutil);
			if (list.size()>0) {
				groupid = list.get(0).getGroupid();
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return groupid;
	}

	@Override
	public List<Long> selectIdsByGrouplist(List<Long> _groupids) {
		List<Long> userids = null;
		Map<String,Object> map = new HashMap<String, Object>();	
		map.put("groupids", _groupids);
		try {
			userids = irpUsergroupLinkDAO.findUserIdByGroupId(map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userids;
	}
	@Override
	public List<IrpGroup> currentGroup(Long groupId, List<IrpGroup> list, long _nRootId) {
		IrpGroup irpGroup = null;
		if(list==null)
			list = new ArrayList<IrpGroup>();
		try {
			irpGroup = irpGroupDAO.selectByPrimaryKey(groupId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(irpGroup==null){
			return list;
		}else if(irpGroup.getParentid().intValue()==_nRootId){
			list.add(0,irpGroup);
			return list;
		}
		list.add(0,irpGroup);
		return currentGroup(irpGroup.getParentid(),list,_nRootId);
	}
}
