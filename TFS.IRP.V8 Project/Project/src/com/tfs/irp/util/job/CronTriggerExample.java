package com.tfs.irp.util.job;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class CronTriggerExample {
	private static final String JOB_GROUP_NAME = "group";
	private static final String TRIGGER_GROUP_NAME = "trigger";
	private static Scheduler scheduler = getScheduler();

	/**
	 * 创建一个调度对象
	 * 
	 * @return
	 */
	private static Scheduler getScheduler() {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler scheduler = null;
		try {
			scheduler = sf.getScheduler();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return scheduler;
	}

	public static Scheduler getInstanceScheduler() {
		return scheduler;
	}

	/**
	 * 启动一个调度对象
	 * 
	 * @throws SchedulerException
	 */
	public static void startup() throws SchedulerException {
		if (!scheduler.isStarted())
			scheduler.start();
	}

	/**
	 * 关闭调度信息
	 * 
	 * @throws SchedulerException
	 */
	public void shutdown() throws SchedulerException {
		if (!scheduler.isShutdown())
			scheduler.shutdown();
	}

	/**
	 * 添加任务和时间
	 * 
	 * @param _sJobName
	 * @param _sJobClass
	 * @param _sJobTime
	 * @throws ClassNotFoundException
	 * @throws SchedulerException
	 */
	@SuppressWarnings("unchecked")
	public static void addJobTime(String _sJobName, String _sJobClass,
			String _sJobTime) throws ClassNotFoundException, SchedulerException {
		Class<?> cClass = Class.forName(_sJobClass);
		if (!Job.class.isAssignableFrom(cClass)) {
			return;
		}
		Class<? extends Job> jobClass = (Class<? extends Job>) cClass;
		JobDetail job = JobBuilder.newJob(jobClass)
				.withIdentity(_sJobName, JOB_GROUP_NAME).build();

		CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger()
				.withIdentity(_sJobName, TRIGGER_GROUP_NAME)
				.withSchedule(CronScheduleBuilder.cronSchedule(_sJobTime))
				.build();
		scheduler.scheduleJob(job, trigger);
	}

	/**
	 * 删除任务
	 * 
	 * @param _sJobName
	 * @throws SchedulerException
	 */
	public static void delJob(String _sJobName) throws SchedulerException {
		JobKey jobKey = new JobKey(_sJobName, JOB_GROUP_NAME);
		scheduler.deleteJob(jobKey);
	}
}
