package com.tfs.irp.project.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.project.entity.IrpProject;
import com.tfs.irp.project.entity.IrpProjectExample;
import com.tfs.irp.util.PageUtil;

public interface IrpProjectService {

	/**
	 * 增加项目
	 * @param irpProject
	 * @return
	 */
	public int addProject(IrpProject irpProject);
	/**
	 * 增加bug项目手机端
	 * @param irpProject
	 * @return
	 */
	public int addProjectPhone(IrpProject irpProject);
	/**
	 * 根据主键删除项目
	 * @param _projectid
	 * @return
	 */
	public int deleteProject(Long _projectId);
	/**
	 * 修改项目
	 * @param irpProject
	 * @return
	 */
	public int updateProject(IrpProject irpProject);
	/**
	 * 根据主键查询某个项目
	 * @param _projectId
	 * @return
	 */
	public IrpProject findProjectById(Long _projectId);
	
	/**
	 * 查询系统中的所有项目
	 */
	public List<IrpProject> findProject();
	/**
	 * 根据条件查询项目列表
	 */
	public List<IrpProject> findProjectByMap(PageUtil pageUtil, HashMap< String, Object> map);
	/**
	 * 根据条件查询项目列表
	 */
	public List<IrpProject> findProjectByMap(HashMap< String, Object> map);
	/**
	 * 根据所给条件查询数量
	 * @param map
	 */
	public int countProjectList(HashMap<String, Object> map);
	/***
	 * 获取项目附件
	 * @param projectid
	 * @return
	 */
	public List<IrpAttached> findAllFileByProjectid(Long projectid);
	/**
	 * 获取项目附件数量
	 * @param projectid
	 */
	public int countFileByProjectId(Long projectid);
	/**
	 * 获取项目附件，分页
	 * @param projectid
	 * @param pageUtil
	 * @return
	 */
	List<IrpAttached> findAllFileByProjectid(Long projectid, PageUtil pageUtil);
	
	
	public List<IrpProject> selectByExample(IrpProjectExample example)
			throws SQLException;
	
	/**
	 * 根据主键修改项目信息
	 * @param record
	 * @return
	 * @throws SQLException
	 */
	int updateByPrimaryKeySelective(IrpProject record) throws SQLException;
	/**根据任务查询附件
	 * @param taskid
	 * @param pageUtil
	 * @return
	 */
	public List findFileByTaskid(Long taskid);
	/**查询公开圈子，正在进行中，未过期的圈子
	 * @param map
	 * @return
	 */
	public List<IrpProject> findProjectByMapAndTime(HashMap<String, Object> map);
}
