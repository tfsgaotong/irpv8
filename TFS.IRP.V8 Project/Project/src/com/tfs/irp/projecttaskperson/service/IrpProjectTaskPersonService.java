package com.tfs.irp.projecttaskperson.service;

import java.util.List;

import com.tfs.irp.projectsharetask.entity.IrpProjectShareTask;
import com.tfs.irp.projecttaskperson.entity.IrpProjectTaskPerson;
import com.tfs.irp.projecttaskperson.entity.IrpProjectTaskPersonExample;

public interface IrpProjectTaskPersonService {
	/**
	 * 添加成员
	 * @param _personId 成员id
	 * @param _shareTaskId任务id
	 * @param _projectId项目id
	 */
	public void addPerson(Long _personId,Long _shareTaskId, Long _projectId);
	
	/**
	 * 我参与的任务
	 */
	public List<Long> myJoinProjectTask();
	/**
	 * 根据条件删除任务成员
	 * @param example
	 */
	public  void deleteTaskPerson(IrpProjectTaskPersonExample example);
	
	 /**
	  * 查看任务成员
	  * @param shareTaskId
	  * @param projectId
	  * @return
	  */
	public List<IrpProjectTaskPerson> findTaskPerson(Long shareTaskId,Long projectId);
	/**
	 * 查看他任务成员所有主键
	 * 
	 */
	public List<Long> findTaskPersonIds(Long shareTaskId,Long projectId);
	
}
