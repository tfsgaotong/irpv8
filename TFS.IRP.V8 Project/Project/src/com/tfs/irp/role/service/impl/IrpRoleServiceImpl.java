package com.tfs.irp.role.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import com.tfs.irp.role.dao.IrpRoleDAO;
import com.tfs.irp.role.dao.IrpUserroleLinkDAO;
import com.tfs.irp.role.entity.IrpRole;
import com.tfs.irp.role.entity.IrpRoleExample;
import com.tfs.irp.role.entity.IrpUserroleLink;
import com.tfs.irp.role.entity.IrpUserroleLinkExample;
import com.tfs.irp.role.entity.IrpUserroleLinkExample.Criteria;
import com.tfs.irp.role.service.IrpRoleService;
import com.tfs.irp.user.dao.IrpUserDAO;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.LogUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpRoleServiceImpl implements IrpRoleService {
	private IrpRoleDAO irpRoleDAO;
	
	private IrpUserroleLinkDAO irpUserroleLinkDAO;
	
	private IrpUserDAO irpUserDAO;

	public IrpUserDAO getIrpUserDAO() {
		return irpUserDAO;
	}

	public void setIrpUserDAO(IrpUserDAO irpUserDAO) {
		this.irpUserDAO = irpUserDAO;
	}

	public IrpUserroleLinkDAO getIrpUserroleLinkDAO() {
		return irpUserroleLinkDAO;
	}

	public void setIrpUserroleLinkDAO(IrpUserroleLinkDAO irpUserroleLinkDAO) {
		this.irpUserroleLinkDAO = irpUserroleLinkDAO;
	}

	public IrpRoleDAO getIrpRoleDAO() {
		return irpRoleDAO;
	}

	public void setIrpRoleDAO(IrpRoleDAO irpRoleDAO) {
		this.irpRoleDAO = irpRoleDAO;
	}
	
	@Override
	public List<IrpRole> findRolesOfPageByRoleType(PageUtil _pageUtil,int _nRoleType,String _sOrderBy,String _sSearchWord, String _sSearchType){
		List<IrpRole> roles = null;
		IrpRoleExample example = new IrpRoleExample();
		if("all".equals(_sSearchType)){
			example.or(example.createCriteria().andRoletypeEqualTo(new Long(_nRoleType)).andRolenameLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andRoletypeEqualTo(new Long(_nRoleType)).andRoledescLike("%"+_sSearchWord+"%"));
		} else if("rolename".equals(_sSearchType)){
			example.createCriteria().andRoletypeEqualTo(new Long(_nRoleType)).andRolenameLike("%"+_sSearchWord+"%");
		} else if("roledesc".equals(_sSearchType)){
			example.createCriteria().andRoletypeEqualTo(new Long(_nRoleType)).andRoledescLike("%"+_sSearchWord+"%");
		} else{
			example.createCriteria().andRoletypeEqualTo(new Long(_nRoleType));
		}
		if(_sOrderBy==null || _sOrderBy.equals("")){
			_sOrderBy = "roleid desc";
		}
		example.setOrderByClause(_sOrderBy);
		try {
			roles = irpRoleDAO.selectByExample(example, _pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roles;
	}
	
	@Override
	public List<IrpRole> findRolesByRoleType(int _nRoleType){
		List<IrpRole> roles = null;
		IrpRoleExample example = new IrpRoleExample();
		example.createCriteria().andRoletypeEqualTo(new Long(_nRoleType));
		example.setOrderByClause("roleid desc");
		try {
			roles = irpRoleDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roles;
	}
	
	@Override
	public int findRolesCountByRoleType(int _nRoleType, String _sSearchWord, String _sSearchType){
		int nCount = 0;
		IrpRoleExample example = new IrpRoleExample();
		if("all".equals(_sSearchType)){
			example.or(example.createCriteria().andRoletypeEqualTo(new Long(_nRoleType)).andRolenameLike("%"+_sSearchWord+"%"));
			example.or(example.createCriteria().andRoletypeEqualTo(new Long(_nRoleType)).andRoledescLike("%"+_sSearchWord+"%"));
		} else if("rolename".equals(_sSearchType)){
			example.createCriteria().andRoletypeEqualTo(new Long(_nRoleType)).andRolenameLike("%"+_sSearchWord+"%");
		} else if("roledesc".equals(_sSearchType)){
			example.createCriteria().andRoletypeEqualTo(new Long(_nRoleType)).andRoledescLike("%"+_sSearchWord+"%");
		} else{
			example.createCriteria().andRoletypeEqualTo(new Long(_nRoleType));
		}
		try {
			nCount = irpRoleDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	
	@Override
	public IrpRole findRoleByRoleId(Long _nRoleId) {
		IrpRole irpRole = null;
		try {
			irpRole = irpRoleDAO.selectByPrimaryKey(_nRoleId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return irpRole;
	}

	@Override
	public int roleEdit(IrpRole _irpRole) {
		int nResult = 0;
		//检查角色名称是否唯一
		if(!checkRoleName(_irpRole.getRolename(),_irpRole.getRoleid())){
			return nResult;
		}
		if(_irpRole.getRoleid()==null || _irpRole.getRoleid()==0){
			IrpUser loginUser = LoginUtil.getLoginUser();
			_irpRole.setRoleid(TableIdUtil.getNextId(IrpRole.TABLE_NAME));
			_irpRole.setCruser(loginUser.getUsername());
			_irpRole.setCruserid(loginUser.getUserid());
			_irpRole.setCrtime(new Date());
			LogUtil logUtil = new LogUtil("ROLE_ADD", _irpRole);
			try {
				irpRoleDAO.insertSelective(_irpRole);
				logUtil.successLogs();
				nResult = 1;
			} catch (SQLException e) {
				e.printStackTrace();
				logUtil.errorLogs(e);
			}
		}else{
			LogUtil logUtil = new LogUtil("ROLE_EDIT", _irpRole);
			try {
				irpRoleDAO.updateByPrimaryKeySelective(_irpRole);
				logUtil.successLogs();
				nResult = 1;
			} catch (SQLException e) {
				e.printStackTrace();
				logUtil.errorLogs(e);
			}
		}
		return nResult;
	}

	@Override
	public boolean checkRoleName(String _sRoleName, Long roleId) {
		int nCount = 0;
		IrpRoleExample example = new IrpRoleExample();
		example.createCriteria().andRoleidNotEqualTo(roleId).andRolenameEqualTo(_sRoleName);
		try {
			nCount = irpRoleDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount>0?false:true;
	}
	
	@Override
	public List<IrpUser> findUsersOfPageByRoleId(PageUtil _pageUtil, Long _nRoleId, String _sOrderBy, String _sSearchWord, String _sSearchType) {
		List<IrpUser> users = null;
		Map<String, Object> mParam = new HashMap<String, Object>();
		mParam.put("roleId", _nRoleId);
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
			users = irpRoleDAO.findUsersOfPageByRoleId(mParam, _pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	@Override
	public List<IrpUser> findUsersOfPageByRoleId(Long _nRoleId) {
		List<IrpUser> users = null;
		Map<String, Object> mParam = new HashMap<String, Object>();
		mParam.put("roleId", _nRoleId);
		mParam.put("status", IrpUser.USER_STATUS_REG);
		mParam.put("orderStr", "userid desc");
		try {
			users = irpRoleDAO.findUsersOfPageByRoleId(mParam);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	@Override
	public Integer findUsersCountByRoleId(Long _nRoleId, String _sSearchWord, String _sSearchType) {
		int nCount = 0;
		try {
			Map<String, Object> mParam = new HashMap<String, Object>();
			mParam.put("roleId", _nRoleId);
			if("all".equals(_sSearchType)){
				mParam.put("username", "%"+_sSearchWord+"%");
				mParam.put("truename", "%"+_sSearchWord+"%");
			} else if("username".equals(_sSearchType)){
				mParam.put("username", "%"+_sSearchWord+"%");
			} else if("truename".equals(_sSearchType)){
				mParam.put("truename", "%"+_sSearchWord+"%");
			} 
			nCount = irpRoleDAO.findUsersCountByRoleId(mParam);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public Integer findExpertCountByRoleId(Long _nCategoryId,Long _nRoleId, String _sSearchWord, String _sSearchType) {
		int nCount = 0;
		try {
			Map<String, Object> mParam = new HashMap<String, Object>();
			mParam.put("roleId", _nRoleId);
			if("all".equals(_sSearchType)){
				mParam.put("username", "%"+_sSearchWord+"%");
				mParam.put("truename", "%"+_sSearchWord+"%");
			} else if("username".equals(_sSearchType)){
				mParam.put("username", "%"+_sSearchWord+"%");
			} else if("truename".equals(_sSearchType)){
				mParam.put("truename", "%"+_sSearchWord+"%");
			} 
			if(_nCategoryId!=null && _nCategoryId>0L){
				mParam.put("classifyid", _nCategoryId);
			}
			nCount = irpRoleDAO.findExpertCountByRoleId(mParam);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}
	@Override
	public String findRoleUserIdsByRoleId(Long _nRoleId) {
		String sUserIds = "";
		IrpUserroleLinkExample example = new IrpUserroleLinkExample();
		example.createCriteria().andRoleidEqualTo(_nRoleId);
		try {
			List<IrpUserroleLink> irpUserroleLinks = irpUserroleLinkDAO.selectByExample(example);
			for (int i = 0; i < irpUserroleLinks.size(); i++) {
				IrpUserroleLink irpUserroleLink =  irpUserroleLinks.get(i);
				if(i>0){
					sUserIds += ","+irpUserroleLink.getUserid();
				}else{
					sUserIds += irpUserroleLink.getUserid();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sUserIds;
	}
	
	@Override
	public int importRoleUserByRoleId(Long _nRoleId, String _sUserIds) {
		int nResult = 1;
		//获得组织下的所有用户
		IrpUserroleLinkExample example = new IrpUserroleLinkExample();
		example.createCriteria().andRoleidEqualTo(_nRoleId);
		List<IrpUserroleLink> irpUserroleLinks = null;
		IrpRole role = new IrpRole();
		role.setRoleid(_nRoleId);
		LogUtil logUtil = new LogUtil("ROLE_EDIT", role);
		try {
			irpUserroleLinks = irpUserroleLinkDAO.selectByExample(example);
			if(irpUserroleLinks==null){
				irpUserroleLinks = new ArrayList<IrpUserroleLink>();
			}
			
			String[] arrUserIds = _sUserIds.split(",");
			//如果当前组织下有用户，则筛选出已有的用户数组
			if(irpUserroleLinks.size()>0){
				for(int i=0;i<irpUserroleLinks.size();i++){
					IrpUserroleLink irpUserroleLink = irpUserroleLinks.get(i);
					if(irpUserroleLink==null){
						continue;
					}
					//在数组中删除元素
					arrUserIds =(String[]) ArrayUtils.removeElement(arrUserIds, irpUserroleLink.getUserid().toString());
				}
			}
			//添加筛选后的用户到组织中
			insertRoleUserByRoleId(_nRoleId, arrUserIds, LoginUtil.getLoginUser());
			//删除数据库中未选择的用户
			deleteNotInRoleUserByRoleId(_nRoleId, _sUserIds);
			logUtil.successLogs("为角色ID["+_nRoleId+"]添加用户ID["+_sUserIds+"]");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs("为角色ID["+_nRoleId+"]添加用户ID["+_sUserIds+"]",e);
			nResult = 0;
		}
		return nResult;
	}
	
	/**
	 * 添加用户到角色中
	 * @param _nRoleId
	 * @param _arrUserIds
	 * @param _crUser
	 */
	private void insertRoleUserByRoleId(Long _nRoleId, String[] _arrUserIds, IrpUser _crUser){
		if(_crUser==null){
			_crUser = new IrpUser();
			_crUser.setUserid(1L);
			_crUser.setUsername("admin");
		}
		for(int i=0;i<_arrUserIds.length;i++){
			String sUserId = _arrUserIds[i];
			if(sUserId==null || "".equals(sUserId)){
				continue;
			}
			IrpUserroleLink irpUserroleLink = new IrpUserroleLink();
			irpUserroleLink.setUserroleid(TableIdUtil.getNextId(IrpUserroleLink.TABLE_NAME));
			irpUserroleLink.setRoleid(_nRoleId);
			irpUserroleLink.setUserid(Long.parseLong(sUserId));
			irpUserroleLink.setCrtime(new Date());
			irpUserroleLink.setCruser(_crUser.getUsername());
			irpUserroleLink.setCruserid(_crUser.getUserid());
			try {
				irpUserroleLinkDAO.insertSelective(irpUserroleLink);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 删除用户角色中没有选择的用户
	 * @param _nRoleId
	 * @param _sUserIds
	 */
	private void deleteNotInRoleUserByRoleId(Long _nRoleId, String _sUserIds){
		String[] arrUserIds = _sUserIds.split(",");
		List<Long> values = new ArrayList<Long>();
		for(int i=0;i<arrUserIds.length;i++){
			String sUserId = arrUserIds[i];
			if(sUserId==null || "".equals(sUserId)){
				continue;
			}
			values.add(Long.parseLong(sUserId));
		}
		IrpUserroleLinkExample example = new IrpUserroleLinkExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleidEqualTo(_nRoleId);
		if(values.size()>0){
			criteria.andUseridNotIn(values);
		}
		try {
			irpUserroleLinkDAO.deleteByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int removeUserRole(Long _nRoleId, String _sUserIds) {
		//构造用户ID集合
		List<Long> values = new ArrayList<Long>();
		String[] array = _sUserIds.split(",");
		for (int i = 0; i < array.length; i++) {
			String userid = array[i];
			if(userid==null || userid.equals("") || (_nRoleId.intValue()==1 && Integer.parseInt(userid)==1)){
				continue;
			}
			values.add(Long.parseLong(userid));
		}
		
		//构造条件
		IrpUserroleLinkExample example = new IrpUserroleLinkExample();
		example.createCriteria().andRoleidEqualTo(_nRoleId).andUseridIn(values);
		int nRow = 0;
		IrpRole role = new IrpRole();
		role.setRoleid(_nRoleId);
		LogUtil logUtil = new LogUtil("ROLE_EDIT", role);
		try {
			nRow = irpUserroleLinkDAO.deleteByExample(example);
			logUtil.successLogs("将用户ID["+_sUserIds+"]从角色ID["+_nRoleId+"]移除");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs("将用户ID["+_sUserIds+"]从角色ID["+_nRoleId+"]移除", e);
		}
		return nRow;
	}
	
	@Override
	public int deleteRole(String _sRoleIds) {
		List<Long> values = new ArrayList<Long>();
		String[] array = _sRoleIds.split(",");
		for (int i = 0; i < array.length; i++) {
			String sRoleId = array[i];
			if(sRoleId==null || sRoleId.equals("")){
				continue;
			}
			values.add(Long.parseLong(sRoleId));
		}
		IrpRoleExample example = new IrpRoleExample();
		example.createCriteria().andRoleidIn(values);
		IrpUserroleLinkExample example1 = new IrpUserroleLinkExample();
		example1.createCriteria().andRoleidIn(values);
		int nRow = 0;
		IrpRole role = new IrpRole();
		role.setRoleid(Long.parseLong(_sRoleIds.split(",")[0]));
		LogUtil logUtil = new LogUtil("ROLE_EDIT", role);
		try {
			nRow = irpRoleDAO.deleteByExample(example);
			irpUserroleLinkDAO.deleteByExample(example1);
			logUtil.successLogs("批量删除角色ID["+_sRoleIds+"]");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs("批量删除角色ID["+_sRoleIds+"]",e);
		}
		return nRow;
	}

	@Override
	public int importRoleUserByUserId(Long _nUserId, String _sRoleIds) {
		int nResult = 1;
		//获得组织下的所有用户
		IrpUserroleLinkExample example = new IrpUserroleLinkExample();
		example.createCriteria().andUseridEqualTo(_nUserId);
		List<IrpUserroleLink> irpUserroleLinks = null;
		IrpUser user = new IrpUser();
		user.setUserid(_nUserId);
		LogUtil logUtil = new LogUtil("USER_EDIT", user);
		try {
			irpUserroleLinks = irpUserroleLinkDAO.selectByExample(example);
			if(irpUserroleLinks==null){
				irpUserroleLinks = new ArrayList<IrpUserroleLink>();
			}
			
			String[] arrRoleIds = _sRoleIds.split(",");
			//如果当前组织下有用户，则筛选出已有的用户数组
			if(irpUserroleLinks.size()>0){
				for(int i=0;i<irpUserroleLinks.size();i++){
					IrpUserroleLink irpUserroleLink = irpUserroleLinks.get(i);
					if(irpUserroleLink==null){
						continue;
					}
					//在数组中删除元素
					arrRoleIds =(String[]) ArrayUtils.removeElement(arrRoleIds, irpUserroleLink.getRoleid().toString());
				}
			}
			//添加筛选后的用户到组织中
			insertRoleUserByUserId(_nUserId, arrRoleIds, LoginUtil.getLoginUser());
			//删除数据库中未选择的用户
			deleteNotInRoleUserByUserId(_nUserId, _sRoleIds);
			logUtil.successLogs("为用户ID["+_nUserId+"]添加角色ID["+_sRoleIds+"]");
		} catch (SQLException e) {
			e.printStackTrace();
			logUtil.errorLogs("为用户ID["+_nUserId+"]添加角色ID["+_sRoleIds+"]",e);
			nResult = 0;
		}
		return nResult;
	}
	
	@Override
	public String findRoleIdsByUserId(Long _nUserId) {
		String sRoleIds = "";
		IrpUserroleLinkExample example = new IrpUserroleLinkExample();
		example.createCriteria().andUseridEqualTo(_nUserId);
		try {
			List<IrpUserroleLink> irpUserroleLinks = irpUserroleLinkDAO.selectByExample(example);
			for (int i = 0; i < irpUserroleLinks.size(); i++) {
				IrpUserroleLink irpUserroleLink =  irpUserroleLinks.get(i);
				if(i>0){
					sRoleIds += ","+irpUserroleLink.getRoleid();
				}else{
					sRoleIds += irpUserroleLink.getRoleid();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sRoleIds;
	}
	
	/**
	 * 添加用户到角色中
	 * @param _nRoleId
	 * @param _arrUserIds
	 * @param _crUser
	 */
	private void insertRoleUserByUserId(Long _nUserId, String[] _arrRoleIds, IrpUser _crUser){
		if(_crUser==null){
			try {
				_crUser = irpUserDAO.selectByPrimaryKey(_nUserId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		for(int i=0;i<_arrRoleIds.length;i++){
			String sRoleId = _arrRoleIds[i];
			if(sRoleId==null || "".equals(sRoleId)){
				continue;
			}
			IrpUserroleLink irpUserroleLink = new IrpUserroleLink();
			irpUserroleLink.setUserroleid(TableIdUtil.getNextId(IrpUserroleLink.TABLE_NAME));
			irpUserroleLink.setRoleid(Long.parseLong(sRoleId));
			irpUserroleLink.setUserid(_nUserId);
			irpUserroleLink.setCrtime(new Date());
			irpUserroleLink.setCruser(_crUser.getUsername());
			irpUserroleLink.setCruserid(_crUser.getUserid());
			try {
				irpUserroleLinkDAO.insertSelective(irpUserroleLink);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 删除用户角色中没有选择的用户
	 * @param _nRoleId
	 * @param _sUserIds
	 */
	private void deleteNotInRoleUserByUserId(Long _nUserId, String _sRoleIds){
		String[] arrRoleIds = _sRoleIds.split(",");
		List<Long> values = new ArrayList<Long>();
		for(int i=0;i<arrRoleIds.length;i++){
			String sRoleId = arrRoleIds[i];
			if(sRoleId==null || "".equals(sRoleId)){
				continue;
			}
			values.add(Long.parseLong(sRoleId));
		}
		IrpUserroleLinkExample example = new IrpUserroleLinkExample();
		Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(_nUserId);
		if(values.size()>0){
			criteria.andRoleidNotIn(values);
		}
		try {
			irpUserroleLinkDAO.deleteByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Long> findRoleIdListByUserId(Long _nUserId) {
		List<Long> arrRoleIds = new ArrayList<Long>();
		try {
			arrRoleIds = irpUserroleLinkDAO.findRoleIdByUserId(_nUserId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrRoleIds;
	}

	@Override
	public List<IrpUser> findUsersOfPageByRoleId(Long _nRoleId,
			PageUtil pageUtil, String orderBy) {
		List<IrpUser> users = null;
		Map<String, Object> mParam = new HashMap<String, Object>();
		mParam.put("roleId", _nRoleId);
		if(orderBy==null || orderBy.equals("")){
			orderBy = "userid desc";
		}
		mParam.put("orderStr", orderBy);
		try {
			users = irpRoleDAO.findUsersOfPageByRoleId(mParam, pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public List<IrpUser> findUsersOfPageByRoleIdAndName(Long _nRoleId,
			PageUtil pageUtil, String orderBy, String expertName) {
		List<IrpUser> users = null;
		Map<String, Object> mParam = new HashMap<String, Object>();
		mParam.put("roleId", _nRoleId);
		mParam.put("expertName", expertName);
		if(orderBy==null || orderBy.equals("")){
			orderBy = "userid desc";
		}
		mParam.put("orderStr", orderBy);
		try {
			users = irpRoleDAO.findUsersOfpageByRoleIdAndName(mParam, pageUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

}
