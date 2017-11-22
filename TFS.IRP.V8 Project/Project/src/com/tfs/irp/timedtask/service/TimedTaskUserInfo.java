package com.tfs.irp.timedtask.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import com.tfs.irp.chnl_doc_link.service.IrpChnl_Doc_LinkService;
import com.tfs.irp.docrecommend.service.IrpDocrecommendService;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.messagecontent.entity.IrpMessageContent;
import com.tfs.irp.messagecontent.service.IrpMessageContentService;
import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.microblog.service.IrpMicroblogService;
import com.tfs.irp.site.entity.IrpSite;
import com.tfs.irp.timedtask.entity.TimedTask;
import com.tfs.irp.user.dao.IrpUserDAO;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ApplicationContextHelper;
import com.tfs.irp.util.LogTimeUtil;
import com.tfs.irp.util.SysConfigUtil;

public class TimedTaskUserInfo implements Job {
	
	private IrpMicroblogService irpMicroBlogService;
	private IrpUserService	irpUserService;
	private IrpUserDAO irpUserDAO;
	private IrpMessageContentService irpMessageContentService;
	private IrpChnl_Doc_LinkService irpChnl_Doc_LinkService;
	private IrpDocrecommendService irpDocrecommendService;
	private ApplicationContext wac;
	public TimedTaskUserInfo(){
	wac = ApplicationContextHelper.getContext();
	irpMicroBlogService = (IrpMicroblogService) wac.getBean("irpMicroBlogService");
	irpUserService = (IrpUserService) wac.getBean("irpUserService");
	irpUserDAO = (IrpUserDAO) wac.getBean("irpUserDAO");
	irpMessageContentService = (IrpMessageContentService) wac.getBean("irpMessageContentServiceImpl");
	irpChnl_Doc_LinkService = (IrpChnl_Doc_LinkService) wac.getBean("irpChnl_Doc_LinkService");
	irpDocrecommendService = (IrpDocrecommendService) wac.getBean("irpDocrecommendServiceImpl");
	}
	/**
	 * 定时发送用户统计信息
	 * @return
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		//获得所有的用户
		List list = irpUserDAO.findAllUseridTask(IrpUser.USER_STATUS_REG);
		Iterator iterator = list.iterator();
		Long addresser = Long.valueOf(SysConfigUtil.getSysConfigValue(IrpUser.SYS_TIMETASK_MESSAGE_USER));
		while (iterator.hasNext()) {
			Map map = (Map) iterator.next();
			Object objUserid = map.get("USERID");
			if(objUserid==null)
				continue;
			Long userid = Long.valueOf(objUserid.toString());
			if(!userid.equals(addresser)){
				Object objUsername = map.get("USERNAME");
				if(objUsername==null)
					continue;
				String username = objUsername.toString();
				int microblognum = irpMicroBlogService.getMicroNumByUseridAndDate(userid,IrpMicroblog.ISDELFALSE,LogTimeUtil.getLastFirstWeek(),LogTimeUtil.getLastEndWeek());
				int userscore = (int) this.irpUserService.findSumScoreByUserId(userid);
				IrpMessageContent _irpMessageContent = new IrpMessageContent();
				int ranking = this.irpUserDAO.findRankingByUserid(userid);
				int knownum = irpChnl_Doc_LinkService.selectCountByExpert(userid, LogTimeUtil.getLastFirstWeek(), LogTimeUtil.getLastEndWeek(),IrpSite.SITE_TYPE_PRIVATE,null);
				int confknownum = irpChnl_Doc_LinkService.selectCountByExpert(userid, LogTimeUtil.getLastFirstWeek(), LogTimeUtil.getLastEndWeek(),IrpSite.SITE_TYPE_PUBLISH,null);
				int knowadopt = irpChnl_Doc_LinkService.selectCountByExpert(userid, LogTimeUtil.getLastFirstWeek(), LogTimeUtil.getLastEndWeek(),IrpSite.SITE_TYPE_PUBLISH,IrpDocument.PUBLISH_STATUS);
				int allknowcomment = irpDocrecommendService.selectCountByExpert(userid,LogTimeUtil.getLastFirstWeek(),LogTimeUtil.getLastEndWeek());
				
				_irpMessageContent.setFromUid(userid);
				_irpMessageContent.setContent(TimedTask.sendInfotemplate(username, microblognum, knownum, confknownum,knowadopt,allknowcomment, userscore, ranking));
				irpMessageContentService.addMessageContent(_irpMessageContent,addresser);
			}
		}
	}
}
