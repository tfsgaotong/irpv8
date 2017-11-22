package com.tfs.irp.projecttaskperson.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tfs.irp.projectsharetask.entity.IrpProjectShareTask;
import com.tfs.irp.projecttaskperson.dao.IrpProjectTaskPersonDAO;
import com.tfs.irp.projecttaskperson.entity.IrpProjectTaskPerson;
import com.tfs.irp.projecttaskperson.entity.IrpProjectTaskPersonExample;
import com.tfs.irp.projecttaskperson.service.IrpProjectTaskPersonService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpProjectTaskPersonServiceImpl implements
		IrpProjectTaskPersonService {
	
	private IrpProjectTaskPersonDAO irpProjectTaskPersonDAO;

	public IrpProjectTaskPersonDAO getIrpProjectTaskPersonDAO() {
		return irpProjectTaskPersonDAO;
	}

	public void setIrpProjectTaskPersonDAO(
			IrpProjectTaskPersonDAO irpProjectTaskPersonDAO) {
		this.irpProjectTaskPersonDAO = irpProjectTaskPersonDAO;
	}
@Override
public void addPerson(Long _personId, Long _shareTaskId, Long _projectId) {
	// TODO Auto-generated method stub  
	try {
		IrpProjectTaskPerson irpProjectTaskPerson=new IrpProjectTaskPerson();
		
		Long taskpersonid=TableIdUtil.getNextId(IrpProjectTaskPerson.TABLE_NAME);
		
		irpProjectTaskPerson.setTaskpersonid(taskpersonid);
		irpProjectTaskPerson.setProjectid(_projectId);
		irpProjectTaskPerson.setPersonid(_personId);
		irpProjectTaskPerson.setSharetaskid(_shareTaskId);
		irpProjectTaskPerson.setCrtime(new Date());
		
		irpProjectTaskPersonDAO.insertSelective(irpProjectTaskPerson);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	@Override
	public List<Long> myJoinProjectTask() {
		// TODO Auto-generated method stub
		 List<Long> taskIds=null;
		IrpUser irpUser=LoginUtil.getLoginUser();  
		try {
			IrpProjectTaskPersonExample example =new IrpProjectTaskPersonExample();
			example.createCriteria().andPersonidEqualTo(irpUser.getUserid());
			taskIds=irpProjectTaskPersonDAO.myJoinTaskId(example); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taskIds;
	}
	@Override
	public void deleteTaskPerson(IrpProjectTaskPersonExample example) {
		// TODO Auto-generated method stub
		try {
			irpProjectTaskPersonDAO.deleteByExample(example);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public List<IrpProjectTaskPerson> findTaskPerson(Long shareTaskId, Long projectId) {
		 List<IrpProjectTaskPerson> list=null;
		 IrpProjectTaskPersonExample example=new IrpProjectTaskPersonExample();
		 example.createCriteria().andProjectidEqualTo(projectId)
		 						.andSharetaskidEqualTo(shareTaskId);
		 try {
			list= irpProjectTaskPersonDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<Long> findTaskPersonIds(Long shareTaskId, Long projectId) {
		 List<Long> list=new ArrayList<Long>();
		 IrpProjectTaskPersonExample example=new IrpProjectTaskPersonExample();
		 example.createCriteria().andProjectidEqualTo(projectId)
		 						.andSharetaskidEqualTo(shareTaskId);
		 try {
			List<IrpProjectTaskPerson> persons= irpProjectTaskPersonDAO.selectByExample(example);
			if(persons!=null && persons.size()>0){
				for (IrpProjectTaskPerson irpProjectTaskPerson : persons) {
					list.add(irpProjectTaskPerson.getTaskpersonid());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		 return list;
	}
}
