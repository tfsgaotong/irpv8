package com.tfs.irp.mobile.web;

import java.io.UnsupportedEncodingException;

import java.net.URLDecoder;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.messagecontent.entity.IrpMessageContent;
import com.tfs.irp.messagecontent.service.IrpMessageContentService;
import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.microblog.service.IrpMicroblogService;
import com.tfs.irp.microblogatme.entity.IrpMicroblogAtmeKey;
import com.tfs.irp.microblogatme.service.IrpMicroblogAtmeKeyService;
import com.tfs.irp.microblogcomment.entity.IrpMicroblogComment;
import com.tfs.irp.microblogcomment.service.IrpMicroblogCommentService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;

public class messageAction extends ActionSupport {
	private IrpMessageContentService irpMessageContentService;
	private IrpMicroblogCommentService irpMicroblogCommentService;
	private IrpMicroblogAtmeKeyService irpMicroblogAtmeKeyService;
	private IrpMicroblogService irpMicroBlogService;
	private IrpUserService irpUserService;
	private List irpMessageContentlist;
	private Integer messagecount;
	private Integer messagedetailcount;
	private String pageHTML = "";
	private int pageNumber = 1;
	private int pageSize = 10;
	private List<IrpMicroblogAtmeKey> irpMicroblogAtmeList;
	private List<IrpMicroblogComment> irpMicroblogCommentList;
	
	
	
	public IrpUserService getIrpUserService() {
		return irpUserService;
	}


	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}


	public List<IrpMicroblogComment> getIrpMicroblogCommentList() {
		return irpMicroblogCommentList;
	}


	public void setIrpMicroblogCommentList(
			List<IrpMicroblogComment> irpMicroblogCommentList) {
		this.irpMicroblogCommentList = irpMicroblogCommentList;
	}


	public List<IrpMicroblogAtmeKey> getIrpMicroblogAtmeList() {
		return irpMicroblogAtmeList;
	}


	public void setIrpMicroblogAtmeList(
			List<IrpMicroblogAtmeKey> irpMicroblogAtmeList) {
		this.irpMicroblogAtmeList = irpMicroblogAtmeList;
	}


	public IrpMicroblogService getIrpMicroBlogService() {
		return irpMicroBlogService;
	}


	public void setIrpMicroBlogService(IrpMicroblogService irpMicroBlogService) {
		this.irpMicroBlogService = irpMicroBlogService;
	}


	public IrpMicroblogCommentService getIrpMicroblogCommentService() {
		return irpMicroblogCommentService;
	}


	public void setIrpMicroblogCommentService(
			IrpMicroblogCommentService irpMicroblogCommentService) {
		this.irpMicroblogCommentService = irpMicroblogCommentService;
	}


	public IrpMicroblogAtmeKeyService getIrpMicroblogAtmeKeyService() {
		return irpMicroblogAtmeKeyService;
	}


	public void setIrpMicroblogAtmeKeyService(
			IrpMicroblogAtmeKeyService irpMicroblogAtmeKeyService) {
		this.irpMicroblogAtmeKeyService = irpMicroblogAtmeKeyService;
	}


	public IrpMessageContentService getIrpMessageContentService() {
		return irpMessageContentService;
	}


	public void setIrpMessageContentService(
			IrpMessageContentService irpMessageContentService) {
		this.irpMessageContentService = irpMessageContentService;
	}


	public List getIrpMessageContentlist() {
		return irpMessageContentlist;
	}


	public void setIrpMessageContentlist(List irpMessageContentlist) {
		this.irpMessageContentlist = irpMessageContentlist;
	}


	public Integer getMessagecount() {
		return messagecount;
	}


	public void setMessagecount(Integer messagecount) {
		this.messagecount = messagecount;
	}


	public Integer getMessagedetailcount() {
		return messagedetailcount;
	}


	public void setMessagedetailcount(Integer messagedetailcount) {
		this.messagedetailcount = messagedetailcount;
	}


	public String getPageHTML() {
		return pageHTML;
	}


	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}


	public int getPageNumber() {
		return pageNumber;
	}


	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	/**
	 * 查看所有分类
	 * @return
	 */
	public void findAllType(){
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
   		Long userid = irpuser.getUserid();
		//int countnum=this.irpMessageContentService.findGroupUseridCount(userid,IrpMessageContent.BOTHSTATUS,userid);
		//PageUtil pageUtil = new PageUtil(pageNumber, pageSize, countnum);
		Map<Object,Object> map1 = new HashMap<Object, Object>();
		IrpMessageContent messageContent=irpMessageContentService.findNewIrpMessageContent(userid,0);
		if(messageContent==null){
			messageContent=irpMessageContentService.findFcIrpMessageContent(userid);
		}
		if(messageContent==null){
			//map1.put("typeid", "1");
			map1.put("chatpic", "/view/images/type/secritymsgnew.png");
			map1.put("chatName", "私信");
			map1.put("chatTimes", "");
			map1.put("chatSmiple", "");
			map1.put("chatMsid", "");
			map1.put("redStatus", "1");
		}else{
			//map1.put("typeid", "1");
			map1.put("chatpic", "/view/images/type/secritymsgnew.png");
			map1.put("chatName", "私信");
			map1.put("chatTimes", messageContent.getCrtime());
			map1.put("chatSmiple", messageContent.getContent());
			map1.put("chatMsid", messageContent.getMessageid());
			int count = irpMessageContentService.selectUnReadMsg(userid,0,0,0);
			if(count>0){
				map1.put("redStatus", "0");
			}else{
				map1.put("redStatus", "1");
			}
		}
		Map<Object,Object> map2 = new HashMap<Object, Object>();
		IrpMicroblogComment irpMicroblogComment =irpMicroblogCommentService.findSdNewWestCommentByUserid(userid, IrpMicroblogComment.ISDELTRUE);
		if(irpMicroblogComment==null){
			irpMicroblogComment =irpMicroblogCommentService.findNewWestCommentByUserid(userid,IrpMicroblogComment.ISDELTRUE);
		}
		if(irpMicroblogComment==null){
			//map2.put("typeid", "2");
			map2.put("chatpic", "/view/images/type/commentnew.png");
			map2.put("chatName", "评论");
			map2.put("chatTimes", "");
			map2.put("chatSmiple", "");
			map2.put("chatMsid", "");
			map2.put("redStatus", "1");
		}else{
			//map2.put("typeid", "2");
			map2.put("chatpic", "/view/images/type/commentnew.png");
			map2.put("chatName", "评论");
			map2.put("chatTimes", irpMicroblogComment.getCrtime());
			String commentcon=irpMicroblogComment.getContent();
			commentcon=commentcon.replaceAll("<((?!>).)*>|<\\/((?!<).)*>", "");
			map2.put("chatSmiple", commentcon);
			map2.put("chatMsid", irpMicroblogComment.getCommentid());
			int count = irpMicroblogCommentService.selectUnReadComment(userid, 0, 0);
			if(count>0){
				map2.put("redStatus", "0");
			}else{
				map2.put("redStatus", "1");
			}
		}
		Map<Object,Object> map3 = new HashMap<Object, Object>();
		IrpMicroblogAtmeKey irpMicroblogAtmeKey =irpMicroblogAtmeKeyService.getIrpMicroblogAtmeKey(userid);
		if(irpMicroblogAtmeKey==null){
			//map3.put("typeid", "3");
			map3.put("chatpic", "/view/images/type/clickmenew.png");
			map3.put("chatName", "@我");
			map3.put("chatTimes", "");
			map3.put("chatSmiple", "");
			map3.put("chatMsid", "");
			map3.put("redStatus", "1");
		}else{
			//map3.put("typeid", "3");
			map3.put("chatpic", "/view/images/type/clickmenew.png");
			map3.put("chatName", "@我");
			map3.put("chatTimes", irpMicroblogAtmeKey.getCrtime());
			IrpMicroblog microblog=irpMicroBlogService.irpMicroblog(irpMicroblogAtmeKey.getMicroblogid());
			String con=microblog.getMicroblogcontent();
			con=con.replaceAll("<((?!>).)*>|<\\/((?!<).)*>", "");
			if(con.contains("//")){
				con=con.substring(0, con.indexOf("//"));
			}else if(con.contains("@")){
				con=con.substring(0, con.indexOf("@"));
			}else{
				
			}
			map3.put("chatSmiple", con);
			map3.put("chatMsid", irpMicroblogAtmeKey.getAtmeid());
			int count = irpMicroblogAtmeKeyService.selectUnReadAtme(userid, 0);
			if(count>0){
				map3.put("redStatus", "0");
			}else{
				map3.put("redStatus", "1");
			}
		}
		Map<Object,Object> map4 = new HashMap<Object, Object>();
		//map4.put("typeid", "4");
		map4.put("chatpic", "/view/images/type/knowloge.png");
		map4.put("chatName", "知识库");
		map4.put("chatTimes", "");
		map4.put("chatSmiple", "知识库——你身边的百科全书");
		map4.put("chatMsid", "");
		map4.put("redStatus", "1");
		Map<Object,Object> map5 = new HashMap<Object, Object>();
		IrpMessageContent bugMessageContent=irpMessageContentService.findNewIrpMessageContent(userid,1);
		if(bugMessageContent==null){
			//map5.put("typeid", "5");
			map5.put("chatpic", "/view/images/type/EasyBug.png");
			map5.put("chatName", "Eazy Bug");
			map5.put("chatTimes", "");
			map5.put("chatSmiple", "");
			map5.put("chatMsid", "");
			map5.put("redStatus", "1");
		}else{
			map5.put("chatpic", "/view/images/type/EasyBug.png");
			map5.put("chatName", "Eazy Bug");
			map5.put("chatTimes", bugMessageContent.getCrtime());
			map5.put("chatSmiple", bugMessageContent.getContent());
			map5.put("chatMsid", bugMessageContent.getMessageid());
			int count = irpMessageContentService.selectUnReadMsg(userid,0,0,1);
			if(count>0){
				map5.put("redStatus", "0");
			}else{
				map5.put("redStatus", "1");
			}
		}
		Map<Object,Object> map6 = new HashMap<Object, Object>();
		//map6.put("typeid", "6");
		map6.put("chatpic", "/view/images/type/systemmsgnew.png");
		map6.put("chatName", "系统消息");
		map6.put("chatTimes", "");
		map6.put("chatSmiple", "");
		map6.put("chatMsid", "");
		map6.put("redStatus", "1");
		list.add(map4);
		list.add(map5);
		list.add(map6);
		list.add(map1);
		list.add(map2);
		list.add(map3);
		
		ActionUtil.writer(JSON.toJSONString(list));
	}
	private Long chatMsid;
	
	public Long getChatMsid() {
		return chatMsid;
	}


	public void setChatMsid(Long chatMsid) {
		this.chatMsid = chatMsid;
	}


	/**
	 * 改变私信状态
	 * @return
	 */
	public void chanageMessageStatus(){
		int msg = 0;
		int resultstatus=0;
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		Long userid = irpuser.getUserid();
		msg = this.irpMessageContentService.chanageMessageStatusByMsgId(userid,chatMsid);
		if(msg!=0){
			resultstatus=1000;
		}else{
			resultstatus=0;
		}
		JSONObject j = JSON.parseObject("{\"resultstatus\":\"" + resultstatus +"\"}");
		ActionUtil.writer(j.toJSONString());	
	}
	/**
	 * 改变评论状态
	 * @return
	 */
	public void chanageCommentStatus(){
		int msg = 0;
		int resultstatus=0;
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		Long userid = irpuser.getUserid();
		msg = this.irpMicroblogCommentService.chanageCommentStatusByMsgId(userid,chatMsid);
		if(msg!=0){
			resultstatus=1000;
		}else{
			resultstatus=0;
		}
		JSONObject j = JSON.parseObject("{\"resultstatus\":\"" + resultstatus +"\"}");
		ActionUtil.writer(j.toJSONString());	
	}
	/**
	 * 改变@我的消息状态
	 * @return
	 */
	public void chanageAtmeStatus(){
		int msg = 0;
		int resultstatus=0;
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		Long userid = irpuser.getUserid();
		msg = this.irpMicroblogAtmeKeyService.chanageAtmeStatusByMsgId(userid,chatMsid);
		if(msg!=0){
			resultstatus=1000;
		}else{
			resultstatus=0;
		}
		JSONObject j = JSON.parseObject("{\"resultstatus\":\"" + resultstatus +"\"}");
		ActionUtil.writer(j.toJSONString());	
	}
	public String getcontent(IrpMicroblog microblog){
		String con=microblog.getMicroblogcontent();
		con=con.replaceAll("<((?!>).)*>|<\\/((?!<).)*>", "");
		return con;
		
	}
	/**
	 * 显示@列表
	 * @return
	 */
	public void findMicroblogAtmeView(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		Long loginUserid = irpuser.getUserid();
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		IrpMicroblog microblog=null;
		IrpMicroblog irpMicroblog=null;
		String con=null;
		int atmecount=this.irpMicroblogAtmeKeyService.IrpMicroblogAtmeKeyCount(loginUserid);
		PageUtil pageUtil = new PageUtil(this.pageNumber, this.pageSize,atmecount);
		int count=0;
		IrpUser user=null;
		String showName=null;
		if(atmecount%pageSize==0){
			count=atmecount/pageSize;
		}else{
			count=atmecount/pageSize+1;
		}
		if(this.pageNumber<=count){
			irpMicroblogAtmeList =	this.irpMicroblogAtmeKeyService.IrpMicroblogAtmeKey(loginUserid, pageUtil);
		}
		if(irpMicroblogAtmeList==null){
			
		}else{
			for(int i = 0 ; i < irpMicroblogAtmeList.size();i++){
				Map<Object,Object> map = new HashMap<Object, Object>();
				microblog=irpMicroBlogService.irpMicroblog(irpMicroblogAtmeList.get(i).getMicroblogid());
				con=this.getcontent(microblog);
				map.put("chatTitle", con);
				map.put("chatTimes", irpMicroblogAtmeList.get(i).getCrtime());
				if(microblog.getTranspondid()==0){
					map.put("chatSmiple", "");
				}else{
					irpMicroblog=irpMicroBlogService.irpMicroblog(microblog.getTranspondid());
					con=this.getcontent(irpMicroblog);
					map.put("chatSmiple", con);
				}
				user=irpUserService.findUserByUserId(irpMicroblogAtmeList.get(i).getUserid());
				if(user.getNickname()==null){
					showName=user.getTruename();
					if(showName==null){
						showName=user.getUsername();
					}
				}else{
					showName=user.getNickname();
				}
				map.put("chatName", showName);
				map.put("chatMsid", irpMicroblogAtmeList.get(i).getAtmeid());
				/*int ct = irpMicroblogAtmeKeyService.selectUnReadAtme(loginUserid, 0);
				if(ct>0){
					map.put("redStatus", "0");
				}else{
					map.put("redStatus", "1");
				}*/
				map.put("redStatus",  irpMicroblogAtmeList.get(i).getBrowsestatus());
				list.add(map);
			}
		}
		ActionUtil.writer(JSON.toJSONString(list));
	}
	/**
	 * 查看收到的私信
	 * @return
	 */
	public void findAllMessage(){
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		Long userid = irpuser.getUserid();
		int countnum=this.irpMessageContentService.findMessageByUserIdCount(0,userid,null);
		PageUtil pageUtil = new PageUtil(pageNumber, pageSize, countnum);
		List<IrpMessageContent> irpMessageContentlist=null;
		int count=0;
		IrpUser user=null;
		String showName=null;
		if(countnum%pageSize==0){
			count=countnum/pageSize;
		}else{
			count=countnum/pageSize+1;
		}
		if(this.pageNumber<=count){
			irpMessageContentlist =this.irpMessageContentService.findMessageByUserId(0,userid,pageUtil,null);
		}
		if(irpMessageContentlist==null){
			
		}else{
			for (int i = 0; i < irpMessageContentlist.size(); i++) {
				Map<Object,Object> map = new HashMap<Object, Object>();
				map.put("chatTimes", irpMessageContentlist.get(i).getCrtime());
				map.put("chatSmiple", irpMessageContentlist.get(i).getContent());
				map.put("chatMsid", irpMessageContentlist.get(i).getMessageid());
				if(irpMessageContentlist.get(i).getBrowsestatus()!=0){
					map.put("redStatus", "1");
				}else{
					map.put("redStatus", "0");
				}
				user=irpUserService.findUserByUserId(irpMessageContentlist.get(i).getCruserid());
				if(user==null){
					showName="";
				}else{
					if(user.getNickname()==null){
						showName=user.getTruename();
						if(showName==null){
							showName=user.getUsername();
						}
					}else{
						showName=user.getNickname();
					}
				}
				map.put("chatName", showName);
				list.add(map);
			}
		}
		
		ActionUtil.writer(JSON.toJSONString(list));
	}
	
	/**
	 * 删除一条私信
	 * @return
	 */
	public void deleteMessageInfoByMessageid(){
		int resultstatus=0;
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		Long userid = irpuser.getUserid();
		 //判断这条私信属于谁
		IrpMessageContent irpMessageContent = this.irpMessageContentService.getUserbyMessageid(chatMsid);
		//判断要删除的id是否属于发送人(1 : 我发的   2：别人发的)
		if(irpMessageContent.getIsdel().equals(IrpMessageContent.FROMSTATUS) || irpMessageContent.getIsdel().equals(IrpMessageContent.CRUSERSTATUS)){
			int msg = this.irpMessageContentService.deleteMessageInfo(chatMsid ,userid,IrpMessageContent.BOTHSTATUS);
			if(msg!=0){
				resultstatus=1000;
			}else{
				resultstatus=0;
			}
		}else{
			if(irpMessageContent.getCruserid()==userid){
				int msg =this.irpMessageContentService.deleteMessageInfo(chatMsid ,userid,IrpMessageContent.CRUSERSTATUS);
				if(msg!=0){
					resultstatus=1000;
				}else{
					resultstatus=0;
				}
			}else{
				int msg =this.irpMessageContentService.deleteMessageInfo(chatMsid,userid,IrpMessageContent.FROMSTATUS);
				if(msg!=0){
					resultstatus=1000;
				}else{
					resultstatus=0;
				}
			}
		}
		JSONObject j = JSON.parseObject("{\"resultstatus\":\"" + resultstatus +"\"}");
		ActionUtil.writer(j.toJSONString());
	}
	/**
	 * 分组查看私信
	 * @return
	 */
	public void findAllMessageGroup(){
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		Long userid = irpuser.getUserid();
		int countnum=this.irpMessageContentService.findGroupUseridCount(userid,IrpMessageContent.BOTHSTATUS,userid);
		PageUtil pageUtil = new PageUtil(pageNumber, pageSize, countnum);
		
		irpMessageContentlist =this.irpMessageContentService.findGroupUserid(userid,IrpMessageContent.BOTHSTATUS,userid,pageUtil);
		List usermessagelist = new ArrayList();
		for (int i = 0; i < irpMessageContentlist.size(); i++) {
			Map<Object,Object> map1 = new HashMap<Object, Object>();
			Map map = (Map) irpMessageContentlist.get(i);
			if(map.keySet().iterator().next().equals("CRUSERID")){
				Object irpuserid=map.values().iterator().next();
				IrpMessageContent messageContent=irpMessageContentService.irpMessageContent(Long.parseLong(irpuserid.toString()),userid);
				map1.put("chatTimes", messageContent.getCrtime());
				map1.put("chatSmiple", messageContent.getContent());
				map1.put("chatMsid", messageContent.getMessageid());
				if(messageContent.getBrowsestatus()!=0){
					map1.put("redStatus", "1");
				}else{
					map1.put("redStatus", "0");
				}
				
			}
			list.add(map1);
			//usermessagelist.add(map.values().toString().replace("[","").replace("]",""));
		}
		messagecount = SysConfigUtil.getSysConfigNumValue(IrpMessageContent.MESSAGEFONTCOUNT);
		messagedetailcount = SysConfigUtil.getSysConfigNumValue(IrpMessageContent.MESSAGEDETAILNUMBER);
		
		this.pageHTML = pageUtil.getClientPageHtml("pageMessage(#PageNum#)");
		ActionUtil.writer(JSON.toJSONString(list));
	}
	
	/**
	 * 查看所发出的私信
	 * @return
	 */
	public void findSendOutMessage(){
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		Long userid = irpuser.getUserid();
		int countnum=this.irpMessageContentService.findSendOutMessageCount(IrpMessageContent.BOTHSTATUS, IrpMessageContent.CRUSERSTATUS,userid);
		PageUtil pageUtil = new PageUtil(pageNumber, pageSize, countnum);
		List<IrpMessageContent> irpMessageContentlist=null;
		int count=0;
		IrpUser user=null;
		String showName=null;
		if(countnum%pageSize==0){
			count=countnum/pageSize;
		}else{
			count=countnum/pageSize+1;
		}
		if(this.pageNumber<=count){
			irpMessageContentlist = this.irpMessageContentService.findSendOutMessage(IrpMessageContent.BOTHSTATUS,IrpMessageContent.CRUSERSTATUS,userid,pageUtil);
		}
		if(irpMessageContentlist==null){
			
		}else{
			for (int i = 0; i < irpMessageContentlist.size(); i++) {
				Map<Object,Object> map = new HashMap<Object, Object>();
				map.put("chatTimes", irpMessageContentlist.get(i).getCrtime());
				map.put("chatSmiple", irpMessageContentlist.get(i).getContent());
				map.put("chatMsid", irpMessageContentlist.get(i).getMessageid());
				map.put("redStatus", "1");
				user=irpUserService.findUserByUserId(irpMessageContentlist.get(i).getFromUid());
				if(user.getNickname()==null){
					showName=user.getTruename();
					if(showName==null){
						showName=user.getUsername();
					}
				}else{
					showName=user.getNickname();
				}
				map.put("chatName", showName);
				list.add(map);
			}
		}
		ActionUtil.writer(JSON.toJSONString(list));
	}
	/**
	 * 收到的评论
	 * @return
	 */
	public void reveserMicroblogComment(){
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		Long userid = irpuser.getUserid();
		int countnum=this.irpMicroblogCommentService.findMicroblogCommentOfUseridCount(userid,IrpMicroblogComment.ISDELTRUE);
		PageUtil pageUtil = new PageUtil(this.pageNumber, this.pageSize,countnum);
		int count=0;
		IrpUser user=null;
		String showName=null;
		if(countnum%pageSize==0){
			count=countnum/pageSize;
		}else{
			count=countnum/pageSize+1;
		}
		if(this.pageNumber<=count){
			irpMicroblogCommentList =this.irpMicroblogCommentService.findMicroblogCommentOfUserid(userid,IrpMicroblogComment.ISDELTRUE,pageUtil);
		}
		if(irpMicroblogCommentList==null){
			
		}else{
			for (int i = 0; i < irpMicroblogCommentList.size(); i++) {
				Map<Object,Object> map = new HashMap<Object, Object>();
				map.put("chatTimes", irpMicroblogCommentList.get(i).getCrtime());
				String commentcon=irpMicroblogCommentList.get(i).getContent();
				commentcon=commentcon.replaceAll("<((?!>).)*>|<\\/((?!<).)*>", "");
				map.put("chatSmiple", commentcon);
				map.put("chatMsid", irpMicroblogCommentList.get(i).getCommentid());
				if(irpMicroblogCommentList.get(i).getBrowsestatus()!=0){
					map.put("redStatus", "1");
				}else{
					map.put("redStatus", "0");
				}
				user=irpUserService.findUserByUserId(irpMicroblogCommentList.get(i).getReplyuserid());
				if(user.getNickname()==null){
					showName=user.getTruename();
					if(showName==null){
						showName=user.getUsername();
					}
				}else{
					showName=user.getNickname();
				}
				map.put("chatName", showName);
				list.add(map);
			}
		}
		ActionUtil.writer(JSON.toJSONString(list));
	}
	/**
	 * 发出的评论
	 */
	public void sendMicroblogComment(){
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		Long userid = irpuser.getUserid();
		int countnum=this.irpMicroblogCommentService.findMicroblogCommentOfSendUseridCount(userid,IrpMicroblogComment.ISDELTRUE);
		PageUtil pageUtil = new PageUtil(this.pageNumber, this.pageSize,countnum);
		int count=0;
		IrpUser user=null;
		String showName=null;
		if(countnum%pageSize==0){
			count=countnum/pageSize;
		}else{
			count=countnum/pageSize+1;
		}
		if(this.pageNumber<=count){
			irpMicroblogCommentList =this.irpMicroblogCommentService.findMicroblogCommentOfSendUserid(userid,IrpMicroblogComment.ISDELTRUE,pageUtil);
		}
		if(irpMicroblogCommentList==null){
			
		}else{
			for (int i = 0; i < irpMicroblogCommentList.size(); i++) {
				Map<Object,Object> map = new HashMap<Object, Object>();
				map.put("chatTimes", irpMicroblogCommentList.get(i).getCrtime());
				String commentcon=irpMicroblogCommentList.get(i).getContent();
				commentcon=commentcon.replaceAll("<((?!>).)*>|<\\/((?!<).)*>", "");
				map.put("chatSmiple", commentcon);
				map.put("chatMsid", irpMicroblogCommentList.get(i).getCommentid());
				map.put("redStatus", "1");
				user=irpUserService.findUserByUserId(irpMicroblogCommentList.get(i).getUserid());
				if(user.getNickname()==null){
					showName=user.getTruename();
					if(showName==null){
						showName=user.getUsername();
					}
				}else{
					showName=user.getNickname();
				}
				map.put("chatName", showName);
				list.add(map);
			}
		}
		ActionUtil.writer(JSON.toJSONString(list));
	}
	
	/**
	 * 通过id删除微知评论回复
	 * @return
	 */
	public void microblogCommentDelete(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		int resultstatus=0;
		int _msg =this.irpMicroblogCommentService.updateMicroBlogCommentIsDel(chatMsid);
		IrpMicroblogComment microblogComment=irpMicroblogCommentService.irpMicroblogComment(chatMsid);
		//更新微知列表
		if(_msg>0){
	
			this.irpMicroBlogService.updateMicroblogCommentCount(microblogComment.getMicroblogid(), irpMicroblogCommentService.selectByMicroBlogComment(microblogComment.getMicroblogid()));
			resultstatus=1000;
		}else{
			resultstatus=0;
		}
		JSONObject j = JSON.parseObject("{\"resultstatus\":\"" + resultstatus +"\"}");
		ActionUtil.writer(j.toJSONString());
	}
	/**
	 * 删除指定的@消息
	 * @return
	 */
	public void deleteAtmeByAtmeid(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		int resultstatus=0;
		int _msg =this.irpMicroblogAtmeKeyService.deleteMicroblogAtmeKeyByAtmeId(chatMsid);
		if(_msg>0){
			resultstatus=1000;
		}else{
			resultstatus=0;
		}
		JSONObject j = JSON.parseObject("{\"resultstatus\":\"" + resultstatus +"\"}");
		ActionUtil.writer(j.toJSONString());
	}
	private String messageContent;
	private Long formUid;
	
	public String getMessageContent() {
		return messageContent;
	}


	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}


	public Long getFormUid() {
		return formUid;
	}


	public void setFormUid(Long formUid) {
		this.formUid = formUid;
	}


	/**
	 * 发送私信
	 * @return
	 */
	public void sendMessage(){
		int resultstatus=0;
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		Long cruserid=irpuser.getUserid();
		try {
			messageContent=URLDecoder.decode(messageContent,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(cruserid.equals(formUid)){
			resultstatus=2000;
		}else{
			resultstatus =  this.irpMessageContentService.addMessageContent(cruserid,messageContent,formUid);
			if(resultstatus==1){
				resultstatus=1000;
			}else{
				resultstatus=0;
			}
		}
		JSONObject j = JSON.parseObject("{\"resultstatus\":\"" + resultstatus +"\"}");
		ActionUtil.writer(j.toJSONString());
	}
	/**
	 * 查看收到的bug私信
	 * @return
	 */
	public void findAllBugMessage(){
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		Long userid = irpuser.getUserid();
		int messagetype=1;
		int countnum=this.irpMessageContentService.findMessageByUserIdCount(0,userid,messagetype);
		PageUtil pageUtil = new PageUtil(pageNumber, pageSize, countnum);
		List<IrpMessageContent> irpMessageContentlist=null;
		int count=0;
		IrpUser user=null;
		String showName=null;
		if(countnum%pageSize==0){
			count=countnum/pageSize;
		}else{
			count=countnum/pageSize+1;
		}
		if(this.pageNumber<=count){
			irpMessageContentlist =this.irpMessageContentService.findMessageByUserId(0,userid,pageUtil,messagetype);
		}
		if(irpMessageContentlist==null){
			
		}else{
			for (int i = 0; i < irpMessageContentlist.size(); i++) {
				Map<Object,Object> map = new HashMap<Object, Object>();
				map.put("chatTimes", irpMessageContentlist.get(i).getCrtime());
				map.put("chatSmiple", irpMessageContentlist.get(i).getContent());
				map.put("chatMsid", irpMessageContentlist.get(i).getMessageid());
				if(irpMessageContentlist.get(i).getBrowsestatus()!=0){
					map.put("redStatus", "1");
				}else{
					map.put("redStatus", "0");
				}
				user=irpUserService.findUserByUserId(irpMessageContentlist.get(i).getCruserid());
				if(user==null){
					showName="";
				}else{
					if(user.getNickname()==null){
						showName=user.getTruename();
						if(showName==null){
							showName=user.getUsername();
						}
					}else{
						showName=user.getNickname();
					}
				}
				map.put("chatName", showName);
				list.add(map);
			}
		}
		
		ActionUtil.writer(JSON.toJSONString(list));
	}
	


	
}
