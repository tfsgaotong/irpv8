package com.tfs.irp.projectperson.service;

import java.sql.SQLException;
import java.util.List;

import com.tfs.irp.bug.entity.IrpBug;
import com.tfs.irp.project.entity.IrpProject;
import com.tfs.irp.projectperson.entity.IrpProjectPerson;
import com.tfs.irp.projectperson.entity.IrpProjectPersonExample;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.PageUtil;

public interface IrpProjectPersonService {
	/**
	 * 添加未开通的关注者
	 * @param _personId 人员id
	 * @param _state  人员状态，关注者
	 * @param _projectId  所属项目id
	 * @param _status  人员开通状态
	 * @param _code  人员通行码
	 * @return
	 */
	public int  addProjectPerson(Long _personId,Long _state,Long _projectId,Long _status,Long _code,String _prname);
	/**
	 * 查询某个人所在项目的id或者关注项目的id
	 * 根据这个人在项目中的角色
	 * @param _personId
	 * @param _isState
	 * @return
	 */
	public List< Long > findProjectIdByPersonId(Long _personId,Long _isState);
	/**
	 * 删除某个项目所有成员
	 */
	public void deleteProjectPersonByProjectId(Long _projectId);
	/**
	 * 查看某个项目中的所有personid
	 * @param _projectId
	 * @param _isState
	 * @return
	 */
	public List< Long > findPersonIdByProjectId(Long _projectId,Long _isState);
	/**
	 * 查看某个项目中的所有personid
	 * @param _projectId
	 * @param _isState对象状态
	 * @param _isStatus是否开通
	 * @return
	 */
	public List< Long > findPersonIdByProjectId(Long _projectId,Long _isState,Long _isStatus);
	
	/**
	 * 查询符合条件的对象集合
	 */
	public List<IrpProjectPerson> findPersonByExample(IrpProjectPersonExample example);
	/**
	 * 删除某个项目中的某个人
	 * @param _projectId
	 */
	public int deletePersonByProjectId(Long _projectId,Long _userId);

/**
 * 查看项目中某个状态下人员的数量
 * @param _projectId
 * @param _isState
 * @param _status 
 * @return
 */
	public Integer findProjectPersonCount(Long _projectId,Long _isState,Long _status);
	/**
	 * 修改数据信息
	 * @param person
	 * @return
	 */
	public int updateProjectPerson(IrpProjectPerson person);
	int addProjectCruer(Long _personId, Long _state, Long _projectId,
			Long _status);
	int addProjectCruer1(Long _personId, Long _state, Long _projectId,
			Long _status);
	/**
	 * 查询项目有关人员列表
	 * @param project
	 * @param isState
	 * @param isStatus
	 * @return
	 */
	List<IrpUser> projectAllPerson(Long project, Long isState,Long isStatus,PageUtil pageUtil);
	/**
	 * 修改状态
	 * @param state
	 * @param status
	 * @param projectid
	 * @param userId
	 * @return
	 */
	public int addProjectPerson(Long _personId,Long _state,Long _projectId,Long _status);
	
	/**
	 * 删除项目成员
	 * @param example
	 * @return
	 * @throws SQLException
	 */
	int deleteByExample(IrpProjectPersonExample example) throws SQLException;
	
	/**
	 * 添加项目人员
	 * @param record
	 * @throws SQLException
	 */
	void insert(IrpProjectPerson record) throws SQLException;
	/**获取最热的圈子
	 * @param irpProjectList
	 * @return
	 */
	public List<Long> findPersonBySql(List<IrpProject> irpProjectList);
}
