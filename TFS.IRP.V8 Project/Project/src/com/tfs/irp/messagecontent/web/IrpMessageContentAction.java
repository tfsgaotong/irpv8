package com.tfs.irp.messagecontent.web;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.service.IrpDocumentService;
import com.tfs.irp.messagecontent.entity.IrpMessageContent;
import com.tfs.irp.messagecontent.service.IrpMessageContentService;
import com.tfs.irp.microblogfocus.entity.IrpMicroblogFocus;
import com.tfs.irp.microblogfocus.service.IrpMicroblogFocusService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.userprivacy.entity.IrpUserPrivacy;
import com.tfs.irp.userprivacy.service.IrpUserPrivacyService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;

public class IrpMessageContentAction extends ActionSupport {

	
	private IrpMessageContentService irpMessageContentService;
    private String messageuser;
    private Long messageid;
    private IrpMessageContent irpMessageContent;
    private List irpMessageContentlist;
    private IrpUserService irpUserService;
    private String fromidAll;
    private Long fromuid;
    private Integer messagecount;
    private Integer messagedetailcount;
    private IrpMicroblogFocusService irpMicroblogFocusService;
    private IrpUserPrivacyService irpUserPrivacyService;
    private IrpDocumentService irpDocumentService;
    private String userIds;
    private Long docid;
    

	public IrpDocumentService getIrpDocumentService() {
		return irpDocumentService;
	}

	public void setIrpDocumentService(IrpDocumentService irpDocumentService) {
		this.irpDocumentService = irpDocumentService;
	}

	public Long getDocid() {
		return docid;
	}

	public void setDocid(Long docid) {
		this.docid = docid;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public IrpUserPrivacyService getIrpUserPrivacyService() {
		return irpUserPrivacyService;
	}

	public void setIrpUserPrivacyService(IrpUserPrivacyService irpUserPrivacyService) {
		this.irpUserPrivacyService = irpUserPrivacyService;
	}

	public IrpMicroblogFocusService getIrpMicroblogFocusService() {
		return irpMicroblogFocusService;
	}

	public void setIrpMicroblogFocusService(
			IrpMicroblogFocusService irpMicroblogFocusService) {
		this.irpMicroblogFocusService = irpMicroblogFocusService;
	}

   
	public Integer getMessagedetailcount() {
		return messagedetailcount;
	}

	public void setMessagedetailcount(Integer messagedetailcount) {
		this.messagedetailcount = messagedetailcount;
	}

	public Integer getMessagecount() {
		return messagecount;
	}

	public void setMessagecount(Integer messagecount) {
		this.messagecount = messagecount;
	}

	public Long getFromuid() {
		return fromuid;
	}

	public void setFromuid(Long fromuid) {
		this.fromuid = fromuid;
	}
	/*
     * 分页
     */
	private String pageHTML = "";
	private int pageNum = 1;
	private int pageSize = 10;
	

	private Long cruserid;
	
	private String content;
    
	private Long cruseridid;
	
    
	 
	public Long getCruseridid() {
		return cruseridid;
	}

	public void setCruseridid(Long cruseridid) {
		this.cruseridid = cruseridid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCruserid() {
		return cruserid;
	}

	public void setCruserid(Long cruserid) {
		this.cruserid = cruserid;
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

	public String getFromidAll() {
		return fromidAll;
	}

	public void setFromidAll(String fromidAll) {
		this.fromidAll = fromidAll;
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public List getIrpMessageContentlist() {
		return irpMessageContentlist;
	}

	public void setIrpMessageContentlist(List irpMessageContentlist) {
		this.irpMessageContentlist = irpMessageContentlist;
	}

	public IrpMessageContent getIrpMessageContent() {
		return irpMessageContent;
	}

	public void setIrpMessageContent(IrpMessageContent irpMessageContent) {
		this.irpMessageContent = irpMessageContent;
	}

	public String getMessageuser() {
		return messageuser;
	}

	public void setMessageuser(String messageuser) {
		this.messageuser = messageuser;
	}

	

	public Long getMessageid() {
		return messageid;
	}

	public void setMessageid(Long messageid) {
		this.messageid = messageid;
	}

	public IrpMessageContentService getIrpMessageContentService() {
		return irpMessageContentService;
	}

	public void setIrpMessageContentService(
			IrpMessageContentService irpMessageContentService) {
		this.irpMessageContentService = irpMessageContentService;
	}
	/**
	 * 构建私信发送页面
	 * @return
	 */
	public String messageContentPageView(){
		messagecount = SysConfigUtil.getSysConfigNumValue(IrpMessageContent.MESSAGEFONTCOUNT);
		return SUCCESS;
	}
	/**
	 * 张银珠 发送私信
	 */
	public void sendMessageToUser(){
		if(this.docid!=null && this.docid!=0L){
			IrpDocument document=irpDocumentService.findDocumentByDocId(this.docid);
			if(document!=null){
				String message=SysConfigUtil.getSysConfigValue("SEND_MESSAGE_TO_CRUSER");
				IrpUser irpUser=LoginUtil.getLoginUser(); 
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String date=dateFormat.format(new Date());
				message=message.replace("#user#", LoginUtil.getUserNameString(irpUser));
				message=message.replace("#time#", date);//documentview
				String path=SysConfigUtil.getSysConfigValue("domain_name_address");
            	String href="<a href='"+path+"/document/document_detail.action?docid="+document.getDocid()+"'  class='linkbh14' ><strong>”"+document.getDoctitle()+"“</strong></a>";
				message=message.replace("#doctitle#",href);
				message=message.replace("#content#", irpMessageContent.getContent());
				irpMessageContent.setContent(message);
			}
		}
		sendMessage();
	}
	/**
	 * 发送私信
	 * @return
	 */
	public void sendMessage(){
		 
		int msg = 2;
		int status = 0;
		if(irpMessageContent.getFromUid()==null){
			this.irpMessageContent.setFromUid(0l);
		}
        if(irpMessageContent.getFromUid()!=0 && messageid!=0l){
        	
        	if(irpMessageContent.getFromUid()!=LoginUtil.getLoginUserId()){
        		int replaystatus = this.irpMessageContentService.addMessageContent(irpMessageContent);
        		if (replaystatus==1) {
        			 msg = 0;
				}
        	}
			this.irpMessageContent.setFromUid(0l);
        }else{
          String fromidAlls =  fromidAll.replace(","," ").replace(";"," ").replace(":"," ").replace("，"," ").replace("；"," ").replace("："," ");
           String[] fromidArray = fromidAlls.split(" ");
           for(int i = 0;i< fromidArray.length; i++){
        	List<IrpUser> irpUser = irpUserService.findUserByUsername(irpUserService.findUsernameByNicknameTruenameUsername(fromidArray[i]));
              if(irpUser.size()>=1){
            	  Long userids = irpUser.get(0).getUserid();
                  
                	  boolean privacyconf = this.getWhetherMessage(userids);

                	  if(privacyconf != false && userids!=LoginUtil.getLoginUserId()){
                		  this.irpMessageContent.setFromUid(userids); 
                    	   int statusresult =  this.irpMessageContentService.addMessageContent(irpMessageContent);
                    	   if(statusresult==1){
                    		   msg = status;  
                    	   }
                	  }else{
                          status = 1;
                          if(msg!=2){
                        	  msg = status;  
                          }  
                	  }
                	  
                	   
            	  
              }
        	     
           }
           
        }
        
		ActionUtil.writer(msg+"");
	}
	/**
	 * 两人互发私信
	 * @return
	 */
	public String sendPersonalMessage(){

	IrpMessageContent _irpMessageContent = new IrpMessageContent();
		_irpMessageContent.setFromUid(cruseridid);
		_irpMessageContent.setContent(content);
	int msg = this.irpMessageContentService.addMessageContent(_irpMessageContent);
	if(msg==1){
		irpMessageContent = this.irpMessageContentService.irpMessageContent(LoginUtil.getLoginUserId(),cruseridid);
		
	}
		return SUCCESS;
	}
	
	
	
	/**
	 * 查看私信
	 * @return
	 */
	public String findAllMessage(){
		
		PageUtil pageUtil = new PageUtil(pageNum, pageSize, this.irpMessageContentService.findGroupUseridCount(LoginUtil.getLoginUserId(),IrpMessageContent.BOTHSTATUS,LoginUtil.getLoginUserId()));
		
		irpMessageContentlist =this.irpMessageContentService.findGroupUserid(LoginUtil.getLoginUserId(),IrpMessageContent.BOTHSTATUS,LoginUtil.getLoginUserId(),pageUtil);
		 
		messagecount = SysConfigUtil.getSysConfigNumValue(IrpMessageContent.MESSAGEFONTCOUNT);
		messagedetailcount = SysConfigUtil.getSysConfigNumValue(IrpMessageContent.MESSAGEDETAILNUMBER);
		
		this.pageHTML = pageUtil.getClientPageHtml("pageMessage(#PageNum#)");
		return SUCCESS;
	}
	/**
	 * 查看私信 手机端
	 * mobile
	 * @return
	 */
	private int pagenumbymenum;
	public int getPagenumbymenum() {
		return pagenumbymenum;
	}
	public void setPagenumbymenum(int pagenumbymenum) {
		this.pagenumbymenum = pagenumbymenum;
	}
	public void findAllMessageMobile(){
		
		int psize = this.irpMessageContentService.findGroupUseridCount(LoginUtil.getLoginUserId(),IrpMessageContent.BOTHSTATUS,LoginUtil.getLoginUserId());
		PageUtil pageUtil = new PageUtil(pagenumbymenum, 10, psize);
		irpMessageContentlist =this.irpMessageContentService.findGroupUserid(LoginUtil.getLoginUserId(),IrpMessageContent.BOTHSTATUS,LoginUtil.getLoginUserId(),pageUtil);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List mobilelist = new ArrayList();
		for (int i = 0; i < irpMessageContentlist.size(); i++) {
			Map map = (Map) irpMessageContentlist.get(i);	
			long userid =Long.parseLong(map.get("CRUSERID").toString());
			mobilelist.add("{\"showname\":\""+getShowPageViewNameByUserId(userid)+"\"");
			mobilelist.add("\"date\":\""+sdf.format(map.get("CRTIME"))+"\"");
			mobilelist.add("\"content\":\""+getIrpMicroblogContent(userid).getContent().replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");
			mobilelist.add("\"unread\":\""+selectUnReadMessageDetail(userid)+"\"");
			mobilelist.add("\"messageid\":\""+getIrpMicroblogContent(userid).getMessageid()+"\"");
			mobilelist.add("\"userid\":\""+userid+"\"");
			mobilelist.add("\"sex\":\""+findIrpUserByFocusId(userid).getSex()+"\"");
			
			
			mobilelist.add("\"userpic\":\""+findIrpUserByFocusId(userid).getUserpic()+"\"}");
		}
		mobilelist.add("{\"mobilenum\":\""+psize+"\"}");
		ActionUtil.writer(mobilelist.toString());
	}
	/**
	 * 通过对象id求出相应的user对象
	 * @param _userid
	 * @return
	 */
	public IrpUser findIrpUserByFocusId(long _userid){
		return this.irpUserService.findUserByUserId(_userid);
	}
	/**
	 * 根据用户的id找出某个用户给我发的私信和我给某个用户发的私信
	 * @return
	 */
	public String findUserByMessageId(){
		PageUtil pageUtil = new PageUtil(pageNum, pageSize, this.irpMessageContentService.findMessageByUserCount(IrpMessageContent.BOTHSTATUS,cruserid,LoginUtil.getLoginUserId(),LoginUtil.getLoginUserId()));
       irpMessageContentlist = this.irpMessageContentService.findMessageByUser(IrpMessageContent.BOTHSTATUS,cruserid,LoginUtil.getLoginUserId(),LoginUtil.getLoginUserId(),pageUtil);
       this.pageHTML = pageUtil.getClientPageHtml("pageMessageInfo(#PageNum#)");
     
       messagecount = SysConfigUtil.getSysConfigNumValue(IrpMessageContent.MESSAGEFONTCOUNT);
       
       this.irpMessageContentService.changeMessageReadStatus(LoginUtil.getLoginUserId(),IrpMessageContent.MESSAGEREADSTATUS,IrpMessageContent.MESSAGEREADSTATUSDETAIL,cruserid);
       messagedetailcount = SysConfigUtil.getSysConfigNumValue(IrpMessageContent.MESSAGEDETAILNUMBER);
       
		return SUCCESS;
	}
	/**
	 * 
	 * mobile
	 * 私信细览
	 */
	
	public void findUserByMessageIdMobile(){
		
		int psize  =  this.irpMessageContentService.findMessageByUserCount(IrpMessageContent.BOTHSTATUS,cruserid,LoginUtil.getLoginUserId(),LoginUtil.getLoginUserId());
		PageUtil pageUtil = new PageUtil(pagenumbymenum, 10,psize);
	    irpMessageContentlist = this.irpMessageContentService.findMessageByUser(IrpMessageContent.BOTHSTATUS,cruserid,LoginUtil.getLoginUserId(),LoginUtil.getLoginUserId(),pageUtil);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List mobilelist = new ArrayList();
		for (int i = 0; i < irpMessageContentlist.size(); i++) {
			Map map = (Map) irpMessageContentlist.get(i);	
			long userid =Long.parseLong(map.get("CRUSERID").toString());
			mobilelist.add("{\"showname\":\""+getShowPageViewNameByUserId(userid)+"\"");
			mobilelist.add("\"userid\":\""+userid+"\"");
			mobilelist.add("\"content\":\""+map.get("CONTENT").toString().replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");
			mobilelist.add("\"sex\":\""+findIrpUserByFocusId(userid).getSex()+"\"");
			mobilelist.add("\"messageid\":\""+map.get("MESSAGEID")+"\"");
			mobilelist.add("\"date\":\""+sdf.format(map.get("CRTIME"))+"\"");
			mobilelist.add("\"userpic\":\""+findIrpUserByFocusId(userid).getUserpic()+"\"}");
		}
		mobilelist.add("{\"mobilenum\":\""+psize+"\"}");
		ActionUtil.writer(mobilelist.toString());
	}
	/**
	 * 根据用户id获得用户最新的一条私信内容
	 * @return
	 */
	public IrpMessageContent getIrpMicroblogContent(Long _cruserid){
		return this.irpMessageContentService.irpMessageContent(_cruserid,LoginUtil.getLoginUserId());
	}
	/**
	 * 删除一条私信（详细里的）
	 * @return
	 */
	public void deleteMessageInfoByMessageid(){
		 //判断这条私信属于谁
		IrpMessageContent   irpMessageContent = this.irpMessageContentService.getUserbyMessageid(messageid);
		//判断要删除的id是否属于发送人(1 : 我发的   2：别人发的)
		if(irpMessageContent.getIsdel().equals(IrpMessageContent.FROMSTATUS) || irpMessageContent.getIsdel().equals(IrpMessageContent.CRUSERSTATUS)){
			int msg = this.irpMessageContentService.deleteMessageInfo(Long.valueOf(messageid),LoginUtil.getLoginUserId(),IrpMessageContent.BOTHSTATUS);
			ActionUtil.writer(msg+"");
		}else{
		if(irpMessageContent.getCruserid()==LoginUtil.getLoginUserId()){
			int msg =this.irpMessageContentService.deleteMessageInfo(Long.valueOf(messageid),LoginUtil.getLoginUserId(),IrpMessageContent.CRUSERSTATUS);
			ActionUtil.writer(msg+"");
		}else{
			int msg =this.irpMessageContentService.deleteMessageInfo(Long.valueOf(messageid),LoginUtil.getLoginUserId(),IrpMessageContent.FROMSTATUS);
			ActionUtil.writer(msg+"");
		}
		}
	}
	/**
	 * 删除一组私信
	 * @return
	 */
	public void deleteMessageByCruserid(){
		int msg = 0;
	List list =	this.irpMessageContentService.getUserByFromUidOrCruserid(fromuid, cruserid);
	if(list.size()<=0){
		 msg = 0;
	}else{
	Iterator iterator = list.iterator();
	
	while (iterator.hasNext()) {
		Map map =(Map) iterator.next();
	  Long messageid_all =Long.parseLong(map.values().toString().replace("["," ").replace("]","").trim());
	  //判断这条私信属于谁
		IrpMessageContent   irpMessageContent = this.irpMessageContentService.getUserbyMessageid(messageid_all);
		//判断要删除的id是否属于发送人(1 : 我发的   2：别人发的)
		if(irpMessageContent.getIsdel().equals(IrpMessageContent.FROMSTATUS) || irpMessageContent.getIsdel().equals(IrpMessageContent.CRUSERSTATUS)){
			 msg = this.irpMessageContentService.deleteMessageInfo(messageid_all,LoginUtil.getLoginUserId(),IrpMessageContent.BOTHSTATUS);
			
		}else{
		if(irpMessageContent.getCruserid()==LoginUtil.getLoginUserId()){
			 msg =this.irpMessageContentService.deleteMessageInfo(messageid_all,LoginUtil.getLoginUserId(),IrpMessageContent.CRUSERSTATUS);
			
		}else{
			 msg =this.irpMessageContentService.deleteMessageInfo(messageid_all,LoginUtil.getLoginUserId(),IrpMessageContent.FROMSTATUS);
			
		}
		}
	  
	  }
	}
	ActionUtil.writer(msg+"");
	}
	/**
	 * 清空所有私信(与登录用户有关的)
	 * @return
	 */
	public void messageClearByLoginUser(){
		int msg = 0;
	List list =	this.irpMessageContentService.getUserMessageidByLogin(LoginUtil.getLoginUserId());
	if(list.size()<=0){
		msg=0;
	}else{
	Iterator iterator = list.iterator();
	while (iterator.hasNext()) {
		Map map =(Map) iterator.next();
	  Long messageid_all =Long.parseLong(map.values().toString().replace("["," ").replace("]","").trim());
	  //判断这条私信属于谁
		IrpMessageContent   irpMessageContent = this.irpMessageContentService.getUserbyMessageid(messageid_all);
		//判断要删除的id是否属于发送人(1 : 我发的   2：别人发的)
		if(irpMessageContent.getIsdel().equals(IrpMessageContent.FROMSTATUS) || irpMessageContent.getIsdel().equals(IrpMessageContent.CRUSERSTATUS)){
			 msg = this.irpMessageContentService.deleteMessageInfo(messageid_all,LoginUtil.getLoginUserId(),IrpMessageContent.BOTHSTATUS);
			
		}else{
		if(irpMessageContent.getCruserid()==LoginUtil.getLoginUserId()){
			 msg =this.irpMessageContentService.deleteMessageInfo(messageid_all,LoginUtil.getLoginUserId(),IrpMessageContent.CRUSERSTATUS);
			
		}else{
			 msg =this.irpMessageContentService.deleteMessageInfo(messageid_all,LoginUtil.getLoginUserId(),IrpMessageContent.FROMSTATUS);
			
		}
		}
	  
	  }
	}
	ActionUtil.writer(msg+"");
	
	}
	/**
	 * 查看所发出的私信
	 * @return
	 */
	public String findSendOutMessage(){
		PageUtil pageUtil = new PageUtil(pageNum, pageSize, this.irpMessageContentService.findSendOutMessageCount(IrpMessageContent.BOTHSTATUS, IrpMessageContent.CRUSERSTATUS, LoginUtil.getLoginUserId()));
		irpMessageContentlist = this.irpMessageContentService.findSendOutMessage(IrpMessageContent.BOTHSTATUS,IrpMessageContent.CRUSERSTATUS,LoginUtil.getLoginUserId(),pageUtil);
		  this.pageHTML = pageUtil.getClientPageHtml("pageMessageSendOut(#PageNum#)");
		return SUCCESS;
	}
	/**
	 * 获得未读私信
	 * @return
	 */
	public void selectUnReadMessage(){
		int readMsg = this.irpMessageContentService.selectUnReadMessage(LoginUtil.getLoginUserId(), IrpMessageContent.MESSAGEUNREADSTATUS);
		ActionUtil.writer(readMsg+"");
	}
	/**
	 * 改变私信状态
	 * @return
	 */
	public void messageUnReadChanageStatus(){
	 int msg = 0;
	 msg = this.irpMessageContentService.changeMessageReadStatus(LoginUtil.getLoginUserId(),IrpMessageContent.MESSAGEUNREADSTATUS,IrpMessageContent.MESSAGEREADSTATUS,IrpMessageContent.NORMALSTATUS);
	 ActionUtil.writer(msg+"");	
	}
	/**
	 * 查看某个会话有多少未读的私信
	 * @param _cruserid
	 * @return
	 */
	public int selectUnReadMessageDetail(long _cruserid){
		int _readStatus = 0;
		_readStatus = this.irpMessageContentService.selectUnReadMessageDetail(LoginUtil.getLoginUserId(), IrpMessageContent.MESSAGEREADSTATUS, _cruserid);
		return  _readStatus;
	}
		
   
   /**
	 * 判断当前登录用户是否能给指定的用户发送私信
	 * @return
	 */
	public boolean  getWhetherMessage(Long _userid){
		
		boolean conftypemessage = false;	
		
		IrpUserPrivacy irpUserPrivacy = this.irpUserPrivacyService.irpUserPrivacy(_userid, IrpUserPrivacy.MICROBLOGMESSAGE);
			
		int commenttype =  irpUserPrivacy.getPrivacyvalue();
		
		Long loginuserid = LoginUtil.getLoginUserId();
		
		if(commenttype==IrpUserPrivacy.ALLUSER){
			//不包括黑名单里的所有人
			conftypemessage = true;
		}else if(commenttype==IrpUserPrivacy.BYFOCUSUSER){
			//关注我的人
		if(_userid.equals(loginuserid)){
				
			conftypemessage = true;
				
			}else{
				
				List<IrpMicroblogFocus> focuslist =	this.irpMicroblogFocusService.findMicroblogUserId(_userid);
				
		          for (int i = 0; i <focuslist.size(); i++) {
					
					if(loginuserid.equals(focuslist.get(i).getFocususerid())){
						
						conftypemessage = true;
						break;
					 }
				}
				
			}
		}else if(commenttype==IrpUserPrivacy.FOCUSUSER){

			//我关注的人
			if(_userid.equals(loginuserid)){
				
				conftypemessage = true;
				
			}else{
				
				List<IrpMicroblogFocus> byfocuslist =	this.irpMicroblogFocusService.findMicroblogFocusUserId(_userid);

				for (int i = 0; i <byfocuslist.size(); i++) {

					if(loginuserid.equals(byfocuslist.get(i).getUserid())){				
						conftypemessage = true;
						break;
					 }	
				}	
				
			}
		}else{
			conftypemessage = true;
		}
			
			return conftypemessage;
		
	}
	/**
	 * 找出所选择的用户的用户名
	 */
   public void findMessageSelectUser(){
	   String userid[] = userIds.split(",");
	   
	   StringBuffer usernames = new StringBuffer("");
		for (int i = 0; i < userid.length; i++) {
		if(!userid[i].equals("")){
			IrpUser irpUser =	irpUserService.findUserByUserId(Long.parseLong(userid[i]));
			usernames.append(irpUser.getUsername()+",");
		}	
           }
		if(usernames.toString().equals("")){
			ActionUtil.writer("2");
		}else{
			ActionUtil.writer(usernames.toString());	
		}
		
   }
   /**
    * 根据id获得用户在页面显示的名字
    * @param _userid
    * @return
    */
   public String getShowPageViewNameByUserId(Long _userid){
   	return this.irpUserService.findShowNameByUserid(_userid);
   }
}