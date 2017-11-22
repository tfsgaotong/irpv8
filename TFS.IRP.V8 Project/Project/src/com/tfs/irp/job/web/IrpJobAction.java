package com.tfs.irp.job.web;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.group.entity.IrpGroup;
import com.tfs.irp.job.entity.IrpJob;
import com.tfs.irp.job.service.IrpJobService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;

public class IrpJobAction extends ActionSupport {
	private IrpJobService irpJobService;
	private List<IrpJob> jobs;
	private String searchWord;
	private String searchType;
	private int pageNum = 1;
	private int pageSize = 10;
	private String orderField = "";
	private String orderBy = "";
	private String pageHTML;
	private long jobId;
	private IrpJob irpJob;
	private String jobIds;

	public String getJobIds() {
		return jobIds;
	}

	public void setJobIds(String jobIds) {
		this.jobIds = jobIds;
	}

	public IrpJob getIrpJob() {
		return irpJob;
	}

	public void setIrpJob(IrpJob irpJob) {
		this.irpJob = irpJob;
	}

	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public String getPageHTML() {
		return pageHTML;
	}

	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public List<IrpJob> getJobs() {
		return jobs;
	}

	public void setJobs(List<IrpJob> jobs) {
		this.jobs = jobs;
	}

	public IrpJobService getIrpJobService() {
		return irpJobService;
	}

	public void setIrpJobService(IrpJobService irpJobService) {
		this.irpJobService = irpJobService;
	}

	public String jobSetList() {
		int nDateCount = irpJobService.findJobCountBySearch(searchWord,
				searchType);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
				nDateCount);
		// 处理排序
		String sOrderByClause = "";
		if (this.orderField == null || this.orderField.equals("")) {
			sOrderByClause = "jobid desc";
		} else {
			sOrderByClause = this.orderField + " " + this.orderBy;
		}
		this.jobs = irpJobService.findJobsBySearch(pageUtil, searchWord,
				searchType, sOrderByClause);
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}

	public String jobEdit() {
		if (jobId == 0) {
			irpJob = new IrpJob();
			irpJob.setJobid(0L);
			irpJob.setStatus(1);
		} else {
			irpJob = irpJobService.findJobByJobId(jobId);
			if (irpJob == null) {
				return ERROR;
			}
		}
		return SUCCESS;
	}

	public void jobEditDowith() {
		if (irpJob.getJobid() == null) {
			irpJob.setJobid(0L);
		}
		irpJobService.jobEdit(irpJob);
		ActionUtil.writer("1");
	}

	public void jobDeleteDowith() {
		int nCount = 0;
		if (jobIds != null && jobIds.length() > 0) {
			nCount = irpJobService.deleteJobByJobIds(jobIds);
		}
		ActionUtil.writer("" + nCount);
	}
}
