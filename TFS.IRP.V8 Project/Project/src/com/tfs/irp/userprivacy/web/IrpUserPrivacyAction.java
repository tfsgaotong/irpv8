package com.tfs.irp.userprivacy.web;

import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.microblogfocus.entity.IrpMicroblogFocus;
import com.tfs.irp.microblogfocus.service.IrpMicroblogFocusService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.userprivacy.entity.IrpUserPrivacy;
import com.tfs.irp.userprivacy.service.IrpUserPrivacyService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;
/**
 * 隐私配置
 * @author Administrator
 *
 */
public class IrpUserPrivacyAction extends ActionSupport {
	
	private Integer comment;
	
	private Integer letter;
	
	private Integer atme;
	
	private Integer onLineMessageMail;
	
	private Integer hotwordstatus;
	
	public Integer getHotwordstatus() {
		return hotwordstatus;
	}
	public void setHotwordstatus(Integer hotwordstatus) {
		this.hotwordstatus = hotwordstatus;
	}
	private IrpUserPrivacyService irpUserPrivacyService;
	
	private IrpUserService irpUserService;
	
	private IrpUser irpUser;
	
	private IrpUserPrivacy irpUserPrivacyComment;
	
	private IrpUserPrivacy irpUserPrivacyMessage;
	
	private IrpUserPrivacy irpUserPrivacyAtme;
	
	private IrpUserPrivacy irpUserPriVacyRecommend;
	
	private IrpUserPrivacy irpUserPrivacyPageLoaction;
	
	private IrpUserPrivacy onLineMessageMailObj;
	
	private IrpUserPrivacy irpUserPrivacyhotword;
	
	public IrpUserPrivacy getIrpUserPrivacyhotword() {
		return irpUserPrivacyhotword;
	}
	public void setIrpUserPrivacyhotword(IrpUserPrivacy irpUserPrivacyhotword) {
		this.irpUserPrivacyhotword = irpUserPrivacyhotword;
	}
	public IrpUserPrivacy getIrpUserPrivacyPageLoaction() {
		return irpUserPrivacyPageLoaction;
	}
	public void setIrpUserPrivacyPageLoaction(
			IrpUserPrivacy irpUserPrivacyPageLoaction) {
		this.irpUserPrivacyPageLoaction = irpUserPrivacyPageLoaction;
	}
	private Long userid;
	
	private Integer recommend;
	
	private Integer beginlocation;
	
	public Integer getBeginlocation() {
		return beginlocation;
	}
	public void setBeginlocation(Integer beginlocation) {
		this.beginlocation = beginlocation;
	}
	public Integer getRecommend() {
		return recommend;
	}
	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
	private IrpMicroblogFocusService irpMicroblogFocusService;
	
	public IrpMicroblogFocusService getIrpMicroblogFocusService() {
		return irpMicroblogFocusService;
	}
	public void setIrpMicroblogFocusService(
			IrpMicroblogFocusService irpMicroblogFocusService) {
		this.irpMicroblogFocusService = irpMicroblogFocusService;
	}
	public IrpUserPrivacy getOnLineMessageMailObj() {
		return onLineMessageMailObj;
	}
	public void setOnLineMessageMailObj(IrpUserPrivacy onLineMessageMailObj) {
		this.onLineMessageMailObj = onLineMessageMailObj;
	}
	public IrpUserPrivacy getIrpUserPriVacyRecommend() {
		return irpUserPriVacyRecommend;
	}
	public void setIrpUserPriVacyRecommend(IrpUserPrivacy irpUserPriVacyRecommend) {
		this.irpUserPriVacyRecommend = irpUserPriVacyRecommend;
	}
	public Long getUserid() {
		return userid;
	}
	public Integer getOnLineMessageMail() {
		return onLineMessageMail;
	}
	public void setOnLineMessageMail(Integer onLineMessageMail) {
		this.onLineMessageMail = onLineMessageMail;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public IrpUserPrivacy getIrpUserPrivacyComment() {
		return irpUserPrivacyComment;
	}
	public void setIrpUserPrivacyComment(IrpUserPrivacy irpUserPrivacyComment) {
		this.irpUserPrivacyComment = irpUserPrivacyComment;
	}
	public IrpUser getIrpUser() {
		return irpUser;
	}
	public void setIrpUser(IrpUser irpUser) {
		this.irpUser = irpUser;
	}
	public IrpUserPrivacy getIrpUserPrivacyMessage() {
		return irpUserPrivacyMessage;
	}
	public void setIrpUserPrivacyMessage(IrpUserPrivacy irpUserPrivacyMessage) {
		this.irpUserPrivacyMessage = irpUserPrivacyMessage;
	}
	public IrpUserPrivacy getIrpUserPrivacyAtme() {
		return irpUserPrivacyAtme;
	}
	public void setIrpUserPrivacyAtme(IrpUserPrivacy irpUserPrivacyAtme) {
		this.irpUserPrivacyAtme = irpUserPrivacyAtme;
	}
	public IrpUserPrivacyService getIrpUserPrivacyService() {
		return irpUserPrivacyService;
	}
	public void setIrpUserPrivacyService(IrpUserPrivacyService irpUserPrivacyService) {
		this.irpUserPrivacyService = irpUserPrivacyService;
	}
	public Integer getComment() {
		return comment;
	}
	public IrpUserService getIrpUserService() {
		return irpUserService;
	}
	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}
	public void setComment(Integer comment) {
		this.comment = comment;
	}
	public Integer getLetter() {
		return letter;
	}
	public void setLetter(Integer letter) {
		this.letter = letter;
	}
	public Integer getAtme() {
		return atme;
	}
	public void setAtme(Integer atme) {
		this.atme = atme;
	}
	/**
	 * 得到隐私详情
	 * @return
	 */
	public String currentPrivacy(){
		
		this.irpUserPrivacyComment = irpUserPrivacyService.irpUserPrivacy(LoginUtil.getLoginUserId(),IrpUserPrivacy.MICROBLOGCOMMENT);
		
	    this.irpUserPrivacyMessage =  irpUserPrivacyService.irpUserPrivacy(LoginUtil.getLoginUserId(),IrpUserPrivacy.MICROBLOGMESSAGE);
		
        this.irpUserPrivacyAtme = irpUserPrivacyService.irpUserPrivacy(LoginUtil.getLoginUserId(),IrpUserPrivacy.MICROBLOGATME);
        
        this.irpUserPriVacyRecommend = irpUserPrivacyService.irpUserPrivacy(LoginUtil.getLoginUserId(),IrpUserPrivacy.USERRECOMMEND);
		
        this.irpUserPrivacyPageLoaction = irpUserPrivacyService.irpUserPrivacy(LoginUtil.getLoginUserId(),IrpUserPrivacy.USERLOGINLOCATION);
        
        this.irpUserPrivacyhotword = this.irpUserPrivacyService.irpUserPrivacy(LoginUtil.getLoginUserId(),IrpUserPrivacy.BOOLDOCWORDHOT);
        //在线消息推送位置
        this.onLineMessageMailObj = irpUserPrivacyService.irpUserPrivacy(LoginUtil.getLoginUserId(),IrpUserPrivacy.ONLINEMESSAGEFORWARDMAIL);
		//初始化用户邮箱
        irpUser = irpUserService.findUserByUserId(LoginUtil.getLoginUserId());
        
        return SUCCESS;
	}
	/**
	 * 保存隐私配置项
	 * @return
	 */
	public void savePrivacyConfig(){
       int nStatusComm =  this.irpUserPrivacyService.updateIrpUserPrivacy(LoginUtil.getLoginUserId(),IrpUserPrivacy.MICROBLOGCOMMENT,comment);
		if(nStatusComm>0){
			int nStatusMess =  this.irpUserPrivacyService.updateIrpUserPrivacy(LoginUtil.getLoginUserId(),IrpUserPrivacy.MICROBLOGMESSAGE,letter);
			if(nStatusMess>0){
				int nStatusAtme = this.irpUserPrivacyService.updateIrpUserPrivacy(LoginUtil.getLoginUserId(),IrpUserPrivacy.MICROBLOGATME,atme);
				if(nStatusAtme>0){
				   int nStatusRecommend = this.irpUserPrivacyService.updateIrpUserPrivacy(LoginUtil.getLoginUserId(),IrpUserPrivacy.USERRECOMMEND,recommend);
				   if(nStatusRecommend>0){
					   int nStatusPageLocation = this.irpUserPrivacyService.updateIrpUserPrivacy(LoginUtil.getLoginUserId(), IrpUserPrivacy.USERLOGINLOCATION, beginlocation);
					   if(nStatusPageLocation>0){
						 irpUserPrivacyService.judgePrivacyIfEx(LoginUtil.getLoginUserId(),IrpUserPrivacy.ONLINEMESSAGEFORWARDMAIL);
						 int nStatusOnlineMessage = this.irpUserPrivacyService.updateIrpUserPrivacy(LoginUtil.getLoginUserId(), IrpUserPrivacy.ONLINEMESSAGEFORWARDMAIL, onLineMessageMail);
						 if (nStatusOnlineMessage>0) {
							int nStatusOnMesshotword = this.irpUserPrivacyService.updateIrpUserPrivacy(LoginUtil.getLoginUserId(), IrpUserPrivacy.BOOLDOCWORDHOT, hotwordstatus);
							int nStatus = nStatusComm+nStatusMess+nStatusAtme+nStatusRecommend+nStatusPageLocation+nStatusOnlineMessage+nStatusOnMesshotword;
							 ActionUtil.writer(nStatus+"");  
						 }
						 
						 
 
					   }
				   }
				}
						
			}
		}
	}
	/**
	 * 判断当前登录用户是否能给某条微知评论
	 * @return
	 */
	public void getWhetherComment(){
	
	String conftype = "false";	
		
	IrpUserPrivacy irpUserPrivacy = this.irpUserPrivacyService.irpUserPrivacy(userid, IrpUserPrivacy.MICROBLOGCOMMENT);
		
	int commenttype =  irpUserPrivacy.getPrivacyvalue();
	
	Long loginuserid = LoginUtil.getLoginUserId();
	
	if(commenttype==IrpUserPrivacy.ALLUSER){
		//不包括黑名单里的所有人
		conftype = "true";
	}else if(commenttype==IrpUserPrivacy.BYFOCUSUSER){
		//关注我的人
	if(userid.equals(loginuserid)){
			
			conftype = "true";
			
		}else{
			
			List<IrpMicroblogFocus> focuslist =	this.irpMicroblogFocusService.findMicroblogUserId(userid);
			
	          for (int i = 0; i <focuslist.size(); i++) {
				
				if(loginuserid.equals(focuslist.get(i).getFocususerid())){
					
					conftype = "true";
					break;
				 }
			}
			
		}
	}else if(commenttype==IrpUserPrivacy.FOCUSUSER){

		//我关注的人
		if(userid.equals(loginuserid)){
			
			conftype = "true";
			
		}else{
			
			List<IrpMicroblogFocus> byfocuslist =	this.irpMicroblogFocusService.findMicroblogFocusUserId(userid);

			for (int i = 0; i <byfocuslist.size(); i++) {

				if(loginuserid.equals(byfocuslist.get(i).getUserid())){				
					conftype = "true";
					break;
				 }	
			}	
			
		}
	}else{
		conftype = "true";
	}
		
		ActionUtil.writer(conftype);
	}
	/**
	 * 判断当前登录用户是否能给指定的用户发送私信
	 * @return
	 */
	public void getWhetherMessage(){
		
		String conftypemessage = "false";	
		
		IrpUserPrivacy irpUserPrivacy = this.irpUserPrivacyService.irpUserPrivacy(userid, IrpUserPrivacy.MICROBLOGMESSAGE);
			
		int commenttype =  irpUserPrivacy.getPrivacyvalue();
		
		Long loginuserid = LoginUtil.getLoginUserId();
		
		if(commenttype==IrpUserPrivacy.ALLUSER){
			//不包括黑名单里的所有人
			conftypemessage = "true";
		}else if(commenttype==IrpUserPrivacy.BYFOCUSUSER){
			//关注我的人
		if(userid.equals(loginuserid)){
				
			conftypemessage = "true";
				
			}else{
				
				List<IrpMicroblogFocus> focuslist =	this.irpMicroblogFocusService.findMicroblogUserId(userid);
				
		          for (int i = 0; i <focuslist.size(); i++) {
					
					if(loginuserid.equals(focuslist.get(i).getFocususerid())){
						
						conftypemessage = "true";
						break;
					 }
				}
				
			}
		}else if(commenttype==IrpUserPrivacy.FOCUSUSER){

			//我关注的人
			if(userid.equals(loginuserid)){
				
				conftypemessage = "true";
				
			}else{
				
				List<IrpMicroblogFocus> byfocuslist =	this.irpMicroblogFocusService.findMicroblogFocusUserId(userid);

				for (int i = 0; i <byfocuslist.size(); i++) {

					if(loginuserid.equals(byfocuslist.get(i).getUserid())){				
						conftypemessage = "true";
						break;
					 }	
				}	
				
			}
		}else{
			conftypemessage = "false";
		}
			
			ActionUtil.writer(conftypemessage);
		
	}
	/**
	 * 找到登录用户的推荐状态
	 * @return
	 */
	public void selectRecommendStatus(){
		
	IrpUserPrivacy irpUserPrivacy = this.irpUserPrivacyService.irpUserPrivacy(LoginUtil.getLoginUserId(),IrpUserPrivacy.USERRECOMMEND);
	ActionUtil.writer(irpUserPrivacy.getPrivacyvalue().toString());

	}
    /**
     * 改变推荐用户的状态
     * @return
     */
	public void saveRecommendStatus(){
		
	int status =  this.irpUserPrivacyService.updateIrpUserPrivacy(LoginUtil.getLoginUserId(),IrpUserPrivacy.USERRECOMMEND,IrpUserPrivacy.RECOMMENDCLOSE);
		ActionUtil.writer(status+"");
	}
}
