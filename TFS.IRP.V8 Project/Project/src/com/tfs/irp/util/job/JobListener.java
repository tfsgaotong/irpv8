package com.tfs.irp.util.job;

import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.tfs.irp.job.entity.IrpJob;
import com.tfs.irp.job.service.IrpJobService;

public class JobListener implements ApplicationListener<ApplicationEvent> {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println("==========加载系统计划调度==========");
		ApplicationContext ac = (ApplicationContext) event.getSource();
		IrpJobService irpJobService = (IrpJobService) ac.getBean("irpJobService");
		List<IrpJob> jobs = irpJobService.findAllJobsBySearch();
		for (IrpJob irpJob : jobs) {
			if(irpJob==null)
				continue;
			try {
				CronTriggerExample.addJobTime(irpJob.getId().toString(), irpJob.getJobclass(), irpJob.getJobtime());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SchedulerException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			CronTriggerExample.startup();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
