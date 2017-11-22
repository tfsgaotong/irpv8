package com.tfs.irp.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;

import com.tfs.irp.base.IrpBaseObj;
import com.tfs.irp.group.entity.IrpGroup;
import com.tfs.irp.group.service.IrpGroupService;
import com.tfs.irp.managementoper.entity.IrpManagementOper;
import com.tfs.irp.managementoper.service.IrpManagementOperService;
import com.tfs.irp.mobile.web.mobileAction;
import com.tfs.irp.opertype.dao.IrpOpertypeDAO;
import com.tfs.irp.opertype.entity.IrpOpertype;
import com.tfs.irp.opertype.entity.IrpOpertypeExample;
import com.tfs.irp.right.entity.IrpRight;
import com.tfs.irp.right.service.IrpRightService;
import com.tfs.irp.role.entity.IrpRole;
import com.tfs.irp.role.service.IrpRoleService;
import com.tfs.irp.user.entity.IrpUser;

public class RightUtil {
	IrpRightService irpRightService;
	
	IrpGroupService irpGroupService;
	
	IrpRoleService irpRoleService;
	
	IrpOpertypeDAO irpOpertypeDAO;
	
	IrpManagementOperService irpManagementOperService;
	
	public RightUtil() {
		//初始化服务
		ApplicationContext ac = ApplicationContextHelper.getContext();
		irpRightService = (IrpRightService) ac.getBean("irpRightService");
		irpGroupService = (IrpGroupService) ac.getBean("irpGroupService");
		irpRoleService = (IrpRoleService) ac.getBean("irpRoleService");
		irpOpertypeDAO = (IrpOpertypeDAO) ac.getBean("irpOpertypeDAO");
		irpManagementOperService = (IrpManagementOperService) ac.getBean("irpManagementOperService");
	}
	
	/**
	 * 验证当前登录用户是否对对象有权限
	 * @param _operObj
	 * @param _nOperTypeId
	 * @return
	 */
	public boolean right(IrpBaseObj _operObj, Long _nOperTypeId){
		return right(LoginUtil.getLoginUser(), _operObj, _nOperTypeId);
	}
	
	/**
	 * 验证对象是否操作对象有权限
	 * @param _obj
	 * @param _operObj
	 * @param _nOperTypeId
	 * @return
	 */
	public boolean right(IrpUser _obj, IrpBaseObj _operObj, Long _nOperTypeId) {
		boolean isRight = false;
		List<Long> arrObjIds = new ArrayList<Long>();
		if (_obj == null) {
			return isRight;
		} else {
			//先验证当前登录用户是否有权限
			isRight = rightObj(_obj, _operObj, _nOperTypeId);
			//验证当前登录用户的组织是否有权限
			if(!isRight){
				arrObjIds.clear();
				arrObjIds = irpGroupService.findGroupIdListByUserId(_obj.getId(),IrpGroup.GROUP_TYPE_PUBLIC);
				isRight = rightObj(arrObjIds, IrpGroup.TABLE_NAME, _operObj, _nOperTypeId);
			}
			//验证当前登录用户的角色是否有权限
			if(!isRight){
				arrObjIds.clear();
				arrObjIds = irpRoleService.findRoleIdListByUserId(_obj.getId());
				isRight = rightObj(arrObjIds, IrpRole.TABLE_NAME, _operObj, _nOperTypeId);
			}
		}
		return isRight;
	}
	
	/**
	 * 获得登录用户对对象的操作权限SQL语句 
	 * @param _operObj
	 * @param _nOperTypeId
	 * @return
	 */
	public String getRightExistsSQL(IrpBaseObj _operObj, Long _nOperTypeId) {
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		if(token != null){
			return getRightExistsSQL(mobileAction.getlogin(token), _operObj, _nOperTypeId, false);
		}else{
			return getRightExistsSQL(LoginUtil.getLoginUser(), _operObj, _nOperTypeId, false);
		}
		
	}
	
	/**
	 * 获得登录用户对对象的操作权限SQL语句 ,是否显示表名.列名
	 * @param _operObj
	 * @param _nOperTypeId
	 * @param _bOperObjTableName
	 * @return
	 */
	public String getRightExistsSQL(IrpBaseObj _operObj, Long _nOperTypeId, boolean _bOperObjTableName) {
		return getRightExistsSQL(LoginUtil.getLoginUser(), _operObj, _nOperTypeId, _bOperObjTableName);
	}
	
	/**
	 * 获得用户对对象的操作权限SQL语句
	 * @param _obj
	 * @param _operObj
	 * @param _nOperTypeId
	 * @return
	 */
	public String getRightExistsSQL(IrpUser _obj, IrpBaseObj _operObj, Long _nOperTypeId){
		return getRightExistsSQL(_obj, _operObj, _nOperTypeId, false);
	}
	
	/**
	 * 获得用户对对象的操作权限SQL语句
	 * @param _obj
	 * @param _operObj
	 * @param _nOperTypeId
	 * @return
	 */
	public String getRightExistsSQL(IrpUser _obj, IrpBaseObj _operObj, Long _nOperTypeId, boolean _bOperObjTableName) {
		StringBuffer sExistsSql = new StringBuffer();
		sExistsSql.append(" exists(select 1 from ")
			.append(IrpRight.TABLE_NAME)
			.append(" where ")
			.append(IrpRight.TABLE_NAME)
			.append(".opertype='")
			.append(_operObj.getTableName())
			.append("' and ")
			.append(IrpRight.TABLE_NAME)
			.append(".operid=");
		if(_bOperObjTableName){
			sExistsSql.append(_operObj.getTableName())
			.append(".");
		}
		sExistsSql.append(_operObj.getIdFieldName())
			.append(" and ")
			.append(IrpRight.TABLE_NAME)
			.append(".opertypeid=")
			.append(_nOperTypeId)
			.append(" and (");
		//添加用户条件
		sExistsSql.append("(")
			.append(IrpRight.TABLE_NAME)
			.append(".objtype='")
			.append(IrpUser.TABLE_NAME)
			.append("' and ")
			.append(IrpRight.TABLE_NAME)
			.append(".objid=")
			.append(_obj.getId())
			.append(")");
		//添加用户所属组织权限
		List<Long> arrGroupIds = irpGroupService.findGroupIdListByUserId(_obj.getId(),IrpGroup.GROUP_TYPE_PUBLIC);
		if(arrGroupIds!=null && arrGroupIds.size()>0){
			sExistsSql.append(" or (")
				.append(IrpRight.TABLE_NAME)
				.append(".objtype='")
				.append(IrpGroup.TABLE_NAME)
				.append("' and ")
				.append(IrpRight.TABLE_NAME);
			if(arrGroupIds.size()==1){
				sExistsSql.append(".objid=").append(arrGroupIds.get(0));
			}else{
				sExistsSql.append(".objid in (");
				for (int i = 0; i < arrGroupIds.size(); i++) {
					if(i>0){
						sExistsSql.append(",").append(arrGroupIds.get(i));
					}else{
						sExistsSql.append(arrGroupIds.get(i));
					}
				}
				sExistsSql.append(")");
			}
			sExistsSql.append(")");
		}
		//添加用户所属角色权限
		List<Long> arrRoleIds = irpRoleService.findRoleIdListByUserId(_obj.getId());
		if(arrRoleIds!=null && arrRoleIds.size()>0){
			sExistsSql.append(" or (")
				.append(IrpRight.TABLE_NAME)
				.append(".objtype='")
				.append(IrpRole.TABLE_NAME)
				.append("' and ")
				.append(IrpRight.TABLE_NAME);
			if(arrRoleIds.size()==1){
				sExistsSql.append(".objid=").append(arrRoleIds.get(0));
			}else{
				sExistsSql.append(".objid in (");
				for (int i = 0; i < arrRoleIds.size(); i++) {
					if(i>0){
						sExistsSql.append(",").append(arrRoleIds.get(i));
					}else{
						sExistsSql.append(arrRoleIds.get(i));
					}
				}
				sExistsSql.append(")");
			}
			sExistsSql.append(")");
		}
		sExistsSql.append("))");
		return sExistsSql.toString();
	}
	
	
	/**
	 * 判断对象是否有权限
	 * @param _obj
	 * @param _operObj
	 * @param _nOperType
	 * @return
	 */
	private boolean rightObj(IrpBaseObj _obj, IrpBaseObj _operObj, Long _nOperTypeId){
		return irpRightService.isExistOfId(_obj.getId(), _obj.getTableName(), _operObj.getId(), _operObj.getTableName(), _nOperTypeId);
	}
	
	/**
	 * 判断对象Id集合是否有权限
	 * @param arrIds
	 * @param _sObjType
	 * @param _operObj
	 * @param _nOperTypeId
	 * @return
	 */
	private boolean rightObj(List<Long> arrIds, String _sObjType,IrpBaseObj _operObj, Long _nOperTypeId){
		if(arrIds==null || arrIds.size()==0)
			return false;
		return irpRightService.isExistOfIds(arrIds, _sObjType, _operObj.getId(), _operObj.getTableName(), _nOperTypeId);
	}
	
	/**
	 * 根据操作类型Key获得操作类型ID
	 * @param _sKey
	 * @return
	 */
	public Long findOperTypeIdByKey(String _sKey) {
		Long nOperTypeId = null;
		IrpOpertypeExample example = new IrpOpertypeExample();
		example.createCriteria().andOpertypeEqualTo(_sKey);
		try {
			List<IrpOpertype> list = irpOpertypeDAO.selectByExample(example);
			if(list!=null && list.size()>0){
				nOperTypeId = list.get(0).getOpertypeid();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nOperTypeId;
	}
	
	/**
	 * 获得当前用户可见的后台管理集合
	 * @return
	 */
	public List<IrpManagementOper> findManagementOper(){
		return findManagementOper(LoginUtil.getLoginUser());
	}
	
	public List<IrpManagementOper> findManagementOper(IrpUser loginUser) {
		List<IrpManagementOper> list = new ArrayList<IrpManagementOper>();
		//管理员不进行权限验证
		if(loginUser.isAdministrator()){
			list = irpManagementOperService.findManagementOpersByParentId(0L,IrpManagementOper.FOOT);
		}else{
			long nOperTypeId = findOperTypeIdByKey("MANAGEMENT_OPER");
			list = irpManagementOperService.findRightManagementOpersByParentId(loginUser, 0L, nOperTypeId,IrpManagementOper.FOOT);
		}
		return list;
	}
	
	public boolean isToManagement() {
		return isToManagement(LoginUtil.getLoginUser());
	}
	/**
	 * 获得当前用户可见的前台管理集合
	 * @return
	 */
	public List<IrpManagementOper> findHeadManagementOper(){
		List<IrpManagementOper> list = new ArrayList<IrpManagementOper>();
		list = irpManagementOperService.findManagementOpersByParentId(0L,IrpManagementOper.HEAD);
		return list;
	}
	/**
	 * 获得当前用户可见的后台管理集合数量
	 * @return
	 */
	public boolean isToManagement(IrpUser loginUser) {
		boolean success = false;
		if(loginUser.isAdministrator()){
			success = true;
		}else{
			long nOperTypeId = findOperTypeIdByKey("MANAGEMENT_OPER");
			if(irpManagementOperService.findRightManagementOpersCountByParentId(loginUser, 0L, nOperTypeId,IrpManagementOper.FOOT)>0){
				success = true;
			}
		}
		return success;
	}
}
