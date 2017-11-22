package com.tfs.irp.timedtask.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import com.tfs.irp.asseroom.entity.IrpAsseroom;
import com.tfs.irp.asseroom.service.IrpAsseroomService;
import com.tfs.irp.asseroomapply.entity.IrpAsseroomapply;
import com.tfs.irp.asseroomapply.service.IrpAsseroomapplyService;
import com.tfs.irp.asseuser.entity.IrpAsseuser;
import com.tfs.irp.asseuser.service.IrpAsseuserService;
import com.tfs.irp.messagecontent.entity.IrpMessageContent;
import com.tfs.irp.messagecontent.service.IrpMessageContentService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.userRandom.util.EmailUtils;
import com.tfs.irp.util.ApplicationContextHelper;
import com.tfs.irp.util.DateUtils;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.sms.SmsUtil;



public class TimedTaskMetting implements Job {
	
	private ApplicationContext wac;
	
	private IrpAsseroomapplyService irpasseroomapplyservice;
	
	private IrpMessageContentService irpMessageContentService;
	
	private IrpAsseuserService irpasseuserservice;
	
	private IrpAsseroomService irpasseroomservice;
	private IrpUserService irpUserService;

	public TimedTaskMetting(){
		//初始化
		wac = ApplicationContextHelper.getContext();
		irpasseroomapplyservice = (IrpAsseroomapplyService) wac.getBean("asseroomapplyService");
		irpMessageContentService = (IrpMessageContentService) wac.getBean("irpMessageContentServiceImpl");
		irpasseuserservice =  (IrpAsseuserService) wac.getBean("asseuserService");
		irpasseroomservice =   (IrpAsseroomService) wac.getBean("asseroomService");
		irpUserService =   (IrpUserService) wac.getBean("irpUserService");
	} 
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		List<IrpAsseroomapply> irpasseroomapplylist = irpasseroomapplyservice.getIrpAsseroomapplyListByTime();
		if (irpasseroomapplylist!=null && irpasseroomapplylist.size()>0) {
			for (int i = 0; i < irpasseroomapplylist.size(); i++) {
				IrpAsseroomapply irpasseroomapply = irpasseroomapplylist.get(i); 
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String date_1 = sdf.format(irpasseroomapply.getWarntime());
				String date_2 = sdf.format(Calendar.getInstance().getTime());
				if (date_1.equals(date_2)) {
					try {
						getIrpAsseuserList(irpasseroomapply);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
			}
		}   
	}  
	/**
	 * 根据会议id遍历参会人员结果集
	 * @param _assid
	 * @return
	 * @throws SQLException 
	 */
	public List<IrpAsseuser> getIrpAsseuserList(IrpAsseroomapply _irpasseroomapply) throws SQLException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		IrpAsseroom asseroom = irpasseroomservice.selectByPrimaryKey(_irpasseroomapply.getAsseroomid());
		List<IrpAsseuser> list = this.irpasseuserservice.getIrpAsseuserByAssId(_irpasseroomapply.getAsseroomapplyid(), 1);
		if (list!=null && list.size()>0) {
			StringBuffer findAllid=new StringBuffer();
			for (IrpAsseuser irpAsseuser : list) {
				findAllid.append(LoginUtil.findUserById(irpAsseuser.getUserid()).getName()+";");
			}
			for (int i = 0; i < list.size(); i++) { 
				IrpAsseuser irpasseuser = list.get(i);
				IrpUser user=irpUserService.findUserByUserId(irpasseuser.getUserid());
				String warnString=_irpasseroomapply.getWarnid().toString();
				//发送私信
				String demoString=_irpasseroomapply.getAsseroomapplydemo()==null? "无":_irpasseroomapply.getAsseroomapplydemo();
				String message="我对于将在"+DateUtils.getDateByFormat(_irpasseroomapply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"召开的【"+_irpasseroomapply.getAssename()+"】会议的建议是：";
				String content = "会议消息通知:"+LoginUtil.getUserNameString(LoginUtil.findUserById(irpasseuser.getUserid()))+"，您好。“"+_irpasseroomapply.getAssename()+"”，将于"+sdf.format(_irpasseroomapply.getStarttime())+"召开。会议地点为："+asseroom.getAsseroomaddr()+"，会议内容为："+_irpasseroomapply.getAsseroomcontent()+"；会议备注："+_irpasseroomapply.getAsseroomapplydemo()+"；    <a href='javascript:void(0) ' onclick='send("+_irpasseroomapply.getAsselinkmanuserid()+",\""+message+"\")' >回执</a>";
				String content_1=("会议消息通知:"+LoginUtil.getUserNameString(LoginUtil.findUserById(irpasseuser.getUserid()))+"，您好。您为“"+_irpasseroomapply.getAssename()+"”会议的联系人，会议将于"+DateUtils.getDateByFormat(_irpasseroomapply.getStarttime(), DateUtils.YMDHMS_FORMAT)+"召开。会议地点为："+asseroom.getAsseroomaddr()+"，会议内容为："+_irpasseroomapply.getAsseroomcontent()+";参会人员：【"+findAllid.toString()+"】"+"；会议备注："+demoString);
				if(warnString.indexOf("1")!=-1){
					sendMessage(irpasseuser.getCruserid(), irpasseuser.getUserid(), content);
					sendMessage(irpasseuser.getCruserid(), irpasseuser.getUserid(), content_1);
				}
				if(warnString.indexOf("2")!=-1){
					content = "会议消息通知:"+LoginUtil.getUserNameString(LoginUtil.findUserById(irpasseuser.getUserid()))+"，您好。“"+_irpasseroomapply.getAssename()+"”，将于"+sdf.format(_irpasseroomapply.getStarttime())+"召开。会议地点为："+asseroom.getAsseroomaddr()+"，会议内容为："+_irpasseroomapply.getAsseroomcontent()+"；会议备注："+demoString;
					if(null!=user.getMobile()&&!"".equals(user.getMobile()))
						SmsUtil.sendMsg_webchinese(content, user.getMobile());
					user=irpUserService.findUserByUserId(irpasseuser.getUserid());
					if(null!=user.getMobile()&&!"".equals(user.getMobile()))
						SmsUtil.sendMsg_webchinese(content_1, user.getMobile());
				}
				if(warnString.indexOf("3")!=-1){
					content = "会议消息通知:"+LoginUtil.getUserNameString(LoginUtil.findUserById(irpasseuser.getUserid()))+"，您好。“"+_irpasseroomapply.getAssename()+"”，将于"+sdf.format(_irpasseroomapply.getStarttime())+"召开。会议地点为："+asseroom.getAsseroomaddr()+"，会议内容为："+_irpasseroomapply.getAsseroomcontent()+"；会议备注："+demoString;
					if(null!=user.getEmail()&&!"".equals(user.getEmail()))
						EmailUtils.sendMeetingEmail(user, content,"会议通知");
					user=irpUserService.findUserByUserId(irpasseuser.getUserid());
					if(null!=user.getEmail()&&!"".equals(user.getEmail()))
						EmailUtils.sendMeetingEmail(user, content_1,"会议通知");
				}
			}
		}
		
		return list; 
		
	}  
	/**
	 * 发送私信
	 * @param _cruser
	 * @param _tocruser
	 * @param _content
	 * @return
	 */
	public Integer sendMessage(Long _cruser,Long _tocruser,String _content){
		
		int msg = 0;
		if (_cruser!=null && _tocruser!=null) {
			IrpMessageContent _irpMessageContent = new IrpMessageContent();
			_irpMessageContent.setFromUid(_tocruser); 
			_irpMessageContent.setContent(_content); 
			msg = irpMessageContentService.addMessageContent(_irpMessageContent,_cruser);
		} 
		
		return msg;
	}
	
	
	public IrpAsseroomapplyService getIrpasseroomapplyservice() {
		return irpasseroomapplyservice;
	}
	public void setIrpasseroomapplyservice(
			IrpAsseroomapplyService irpasseroomapplyservice) {
		this.irpasseroomapplyservice = irpasseroomapplyservice;
	}
	public ApplicationContext getWac() {
		return wac;
	}
	public void setWac(ApplicationContext wac) {
		this.wac = wac;
	}

	public IrpMessageContentService getIrpMessageContentService() {
		return irpMessageContentService;
	}
	public void setIrpMessageContentService(
			IrpMessageContentService irpMessageContentService) {
		this.irpMessageContentService = irpMessageContentService;
	}
	public IrpAsseuserService getIrpasseuserservice() {
		return irpasseuserservice;
	}
	public void setIrpasseuserservice(IrpAsseuserService irpasseuserservice) {
		this.irpasseuserservice = irpasseuserservice;
	}

	public IrpAsseroomService getIrpasseroomservice() {
		return irpasseroomservice;
	}
	public void setIrpasseroomservice(IrpAsseroomService irpasseroomservice) {
		this.irpasseroomservice = irpasseroomservice;
	}
}
