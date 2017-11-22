package com.tfs.irp.job.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.SchedulerException;

import com.tfs.irp.job.dao.IrpJobDAO;
import com.tfs.irp.job.entity.IrpJob;
import com.tfs.irp.job.entity.IrpJobExample;
import com.tfs.irp.job.service.IrpJobService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;
import com.tfs.irp.util.job.CronTriggerExample;

public class IrpJobServiceImpl implements IrpJobService {
	private IrpJobDAO irpJobDAO;

	public IrpJobDAO getIrpJobDAO() {
		return irpJobDAO;
	}

	public void setIrpJobDAO(IrpJobDAO irpJobDAO) {
		this.irpJobDAO = irpJobDAO;
	}

	@Override
	public int findJobCountBySearch(String searchWord, String searchType) {
		int nCount = 0;
		IrpJobExample example = new IrpJobExample();
		if (searchWord != null && searchWord.length() > 0) {
			if ("all".equals(searchType)) {
				example.or(example.createCriteria().andJobnameLike(
						"%" + searchWord + "%"));
				example.or(example.createCriteria().andJobdescLike(
						"%" + searchWord + "%"));
			} else if ("jobname".equals(searchType)) {
				example.createCriteria().andJobnameLike("%" + searchWord + "%");
			} else if ("jobdesc".equals(searchType)) {
				example.createCriteria().andJobdescLike("%" + searchWord + "%");
			}
		}
		try {
			nCount = irpJobDAO.countByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nCount;
	}

	@Override
	public List<IrpJob> findJobsBySearch(PageUtil pageUtil, String searchWord,
			String searchType, String _sOrderBy) {
		List<IrpJob> list = new ArrayList<IrpJob>();
		IrpJobExample example = new IrpJobExample();
		if (searchWord != null && searchWord.length() > 0) {
			if ("all".equals(searchType)) {
				example.or(example.createCriteria().andJobnameLike(
						"%" + searchWord + "%"));
				example.or(example.createCriteria().andJobdescLike(
						"%" + searchWord + "%"));
			} else if ("jobname".equals(searchType)) {
				example.createCriteria().andJobnameLike("%" + searchWord + "%");
			} else if ("jobdesc".equals(searchType)) {
				example.createCriteria().andJobdescLike("%" + searchWord + "%");
			}
		}
		if (_sOrderBy == null || _sOrderBy.equals("")) {
			_sOrderBy = "jobid desc";
		}
		example.setOrderByClause(_sOrderBy);
		try {
			list = irpJobDAO.selectByExample(example, pageUtil);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<IrpJob> findAllJobsBySearch() {
		List<IrpJob> list = new ArrayList<IrpJob>();
		IrpJobExample example = new IrpJobExample();
		example.createCriteria().andStatusEqualTo(1);
		try {
			list = irpJobDAO.selectByExample(example);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public IrpJob findJobByJobId(Long _nJobId) {
		IrpJob job = null;
		try {
			job = irpJobDAO.selectByPrimaryKey(_nJobId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return job;
	}

	@Override
	public void jobEdit(IrpJob irpJob) {
		if (irpJob.getJobid() == null || irpJob.getJobid() == 0L) {
			irpJob.setJobid(TableIdUtil.getNextId(IrpJob.TABLE_NAME));
			irpJob.setCrtime(new Date());
			IrpUser loginUser = LoginUtil.getLoginUser();
			irpJob.setCruser(loginUser.getName());
			irpJob.setCruserid(loginUser.getId());
			try {
				irpJobDAO.insertSelective(irpJob);
				//添加计划任务
				if(irpJob.getStatus().intValue()==1){
					CronTriggerExample.addJobTime(irpJob.getId().toString(), irpJob.getJobclass(), irpJob.getJobtime());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		} else {
			try {
				irpJobDAO.updateByPrimaryKeySelective(irpJob);
				//修改计划任务
				//先删除当前计划调度任务
				CronTriggerExample.delJob(irpJob.getId().toString());
				if(irpJob.getStatus().intValue()==1){
					CronTriggerExample.addJobTime(irpJob.getId().toString(), irpJob.getJobclass(), irpJob.getJobtime());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (SchedulerException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public int deleteJobByJobIds(String _sJobIds) {
		int nCount = 0;
		List<Long> values = new ArrayList<Long>();
		String[] array = _sJobIds.split(",");
		for (String sJobId : array) {
			if (sJobId == null || sJobId.equals("")) {
				continue;
			}
			Long nJobId = Long.parseLong(sJobId);
			values.add(nJobId);
		}
		if (values.size() > 0) {
			IrpJobExample example = new IrpJobExample();
			example.createCriteria().andJobidIn(values);
			try {
				nCount = irpJobDAO.deleteByExample(example);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//删除计划任务
		if(nCount>0){
			for (String sJobId : array) {
				if (sJobId == null || sJobId.equals("")) {
					continue;
				}
				try {
					CronTriggerExample.delJob(sJobId);
				} catch (SchedulerException e) {
					e.printStackTrace();
				}
			}
		}
		return nCount;
	}
}
