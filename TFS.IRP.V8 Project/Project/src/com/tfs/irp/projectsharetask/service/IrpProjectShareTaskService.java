package com.tfs.irp.projectsharetask.service;

import java.util.HashMap;
import java.util.List;

import com.tfs.irp.projectsharetask.entity.IrpProjectShareTask;
import com.tfs.irp.projectsharetask.entity.IrpProjectShareTaskExample;
import com.tfs.irp.util.PageUtil;

public interface IrpProjectShareTaskService {
	
	/***
	 * 根据条件查询动态表内容
	 */
	public List<IrpProjectShareTask> findShareTaskByMap(HashMap<String, Object> map,PageUtil pageUtil);
	
	public List<IrpProjectShareTask> findShareTaskByMap(HashMap<String, Object> map,PageUtil pageUtil,Boolean _time);
	/**
	 * 添加动态
	 * @param shareTask 动态对象
	 * @param _attFileList附件数组
	 * @return
	 */
	public int addShareTask(IrpProjectShareTask shareTask,String[] _attFileList); 
	/**
	 * 添加动态 这个动态指的是任务，,含有任务成员的
	 * @param shareTask 任务对象
	 * @param _attFileList附件数组
	 * @param _taskPersons//任务参与成员
	 * @return
	 */
	public int addShareTask(IrpProjectShareTask shareTask,String[] _attFileList,String[] _taskPersons ); 
	/**
	 * 添加动态(回复)
	 */
	public int addShareTask(IrpProjectShareTask shareTask,String _personString); 
	
	/**
	 * 根据parentid查询动态的回复
	 */
	public List<IrpProjectShareTask> findReplayShareTask(Long _parentId);
	/**
	 * 根据主键删除动态
	 * @param _shareTaskId
	 * @return
	 */
	public int deleteShareTask(Long _shareTaskId); 
	/**
	 * 查询某个项目动态数量
	 */
	int countShareTaskByExample(IrpProjectShareTaskExample  example);
	/**
	 * 查看详细信息
	 * @param _shareTaskId
	 * @return
	 */
	public IrpProjectShareTask findShareTaskById(Long _shareTaskId);
	/**
	 * 根据条件查询数量
	 * @param map
	 * @return
	 */
	public Integer countShareTaskByMap(HashMap<String,Object> map);
	/**
	 * 修改信息
	 */
	public int updateShareTask(IrpProjectShareTask _irpProjectShareTask,String[] taskPersons);
	/**
	 * 根据条件删除对象
	 * @param example
	 */
	public void deleteShareTaskByExample(IrpProjectShareTaskExample example);
	/***
	 * 查询动态的所有附件
	 * @param projectid
	 * @param sharetaskId
	 * @return
	 */
	public List findAllFileByProjectIdAndShareId(Long projectid,Long sharetaskId);
	
	public List<IrpProjectShareTask> findallshare(Long projectid);

	/**通过项目id获取任务
	 * @param projectIdList
	 * @return
	 */
	public List<IrpProjectShareTask> findShareTaskByProjectid(List<Long> projectIdList);
	
}
