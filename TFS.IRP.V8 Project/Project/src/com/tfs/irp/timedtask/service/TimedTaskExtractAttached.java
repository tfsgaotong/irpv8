package com.tfs.irp.timedtask.service;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.util.ApplicationContextHelper;

public class TimedTaskExtractAttached implements Job {
	private IrpAttachedService irpAttachedService;
	
	public TimedTaskExtractAttached(){
		ApplicationContext ac = ApplicationContextHelper.getContext();
		irpAttachedService = (IrpAttachedService) ac.getBean("irpAttachedService");
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
	}
}
