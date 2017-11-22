package com.tfs.irp.chat.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.proxy.dwr.Util;

import uk.ltd.getahead.dwr.WebContextFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.chat.entity.IrpChat;
import com.tfs.irp.chat.service.IrpChatService;
import com.tfs.irp.group.service.IrpGroupService;
import com.tfs.irp.microblogfocus.entity.IrpMicroblogFocus;
import com.tfs.irp.microblogfocus.service.IrpMicroblogFocusService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.JsonUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.TableIdUtil;

public class IrpChatAction extends ActionSupport{
	
	
		private IrpChatService irpChatService;
		
		private String chatcontent;
		
		private Long receiveuserid;
		
		private Long boolgp;
		
		private Long fromuserid;
		
		private List<IrpChat> chatlist; 
		
		private Long loginuserid;
		
		private IrpUserService irpUserService;
		
		private IrpMicroblogFocusService irpMicroblogFocusService;
		
		private List<IrpUser> userlist;
		
		private int myfriefdstnum;
		
		private Long groupmarkid;
		
		private IrpGroupService irpGroupService;
		
		public IrpGroupService getIrpGroupService() {
			return irpGroupService;
		}

		public void setIrpGroupService(IrpGroupService irpGroupService) {
			this.irpGroupService = irpGroupService;
		}

		public Long getGroupmarkid() {
			return groupmarkid;
		}

		public void setGroupmarkid(Long groupmarkid) {
			this.groupmarkid = groupmarkid;
		}

		public int getMyfriefdstnum() {
			return myfriefdstnum;
		}

		public void setMyfriefdstnum(int myfriefdstnum) {
			this.myfriefdstnum = myfriefdstnum;
		}

		public List<IrpUser> getUserlist() {
			return userlist;
		}

		public void setUserlist(List<IrpUser> userlist) {
			this.userlist = userlist;
		}

		public IrpMicroblogFocusService getIrpMicroblogFocusService() {
			return irpMicroblogFocusService;
		}

		public void setIrpMicroblogFocusService(
				IrpMicroblogFocusService irpMicroblogFocusService) {
			this.irpMicroblogFocusService = irpMicroblogFocusService;
		}

		public IrpUserService getIrpUserService() {
			return irpUserService;
		}

		public void setIrpUserService(IrpUserService irpUserService) {
			this.irpUserService = irpUserService;
		}

		public Long getLoginuserid() {
			return loginuserid;
		}

		public void setLoginuserid(Long loginuserid) {
			this.loginuserid = loginuserid;
		}

		public List<IrpChat> getChatlist() {
			return chatlist;
		}

		public void setChatlist(List<IrpChat> chatlist) {
			this.chatlist = chatlist;
		}

		public Long getFromuserid() {
			return fromuserid;
		}

		public void setFromuserid(Long fromuserid) {
			this.fromuserid = fromuserid;
		}

		public Long getBoolgp() {
			return boolgp;
		}

		public void setBoolgp(Long boolgp) {
			this.boolgp = boolgp;
		}

		public String getChatcontent() {
			return chatcontent;
		}

		public void setChatcontent(String chatcontent) {
			this.chatcontent = chatcontent;
		}
		public Long getReceiveuserid() {
			return receiveuserid;
		}

		public void setReceiveuserid(Long receiveuserid) {
			this.receiveuserid = receiveuserid;
		}

		public IrpChatService getIrpChatService() {
			return irpChatService;
		}

		public void setIrpChatService(IrpChatService irpChatService) {
			this.irpChatService = irpChatService;
		}
		

		
		
		/**
		 * 增加聊天数据 insert chat data
		 * @return
		 */
		public void insertChartData(Long receiveuserid,Long boolgp,String chatcontent,Long _loginuid,HttpServletRequest request){
			
			boolean flag = false;
			if(boolgp==20l && chatcontent.length()>=1){
				flag = addData(receiveuserid, boolgp, chatcontent, _loginuid,IrpChat.CHATALREADYREAD);
			}else{
				flag = addData(receiveuserid, boolgp, chatcontent, _loginuid,IrpChat.CHATUNREAD);
			}
			if(flag==true && boolgp==20l){
				//证明是组织
				//组内信息  找出组内所有成员  不包括发送者的  
				
				List<IrpUser> listuser = this.irpGroupService.findUsersByGroupId(receiveuserid);
				if (listuser!=null && listuser.size()>0) {
					for (int i = 0; i < listuser.size(); i++) {
						IrpUser irpuser =  listuser.get(i);
						
						addData(irpuser.getUserid(),boolgp,"",receiveuserid,IrpChat.CHATUNREAD);
						
					}
					
				}
				
				
			}
			if(flag==true){
				callForthWithMess(receiveuserid,request,_loginuid,boolgp);
			}
		}
		/**
		 * 增加数据
		 * @param receiveuserid
		 * @param boolgp
		 * @param chatcontent
		 * @param _loginuid
		 * @return
		 */
		public boolean  addData(Long receiveuserid,Long boolgp,String chatcontent,Long _loginuid,Integer _unread){
			
			IrpChat chat = new IrpChat();
			
			boolean flag = false;
			if (receiveuserid!=null && _loginuid!=null) {
				chat.setChatid(TableIdUtil.getNextId(IrpChat.TABLENAME));
				chat.setReceiveruserid(receiveuserid);	
				chat.setSenderuserid(_loginuid);
				chat.setSenddate(Calendar.getInstance().getTime());
				chat.setChatpointtype(boolgp);
				chat.setChatcontent(chatcontent);
				chat.setChatstatus(IrpChat.STATUSNORMAL);
				chat.setCharreadstatus(_unread);
				
				chat.setSenderboolrecord(IrpChat.HOTERECORD);
				chat.setChataccessory(IrpChat.NOATTED);
			}
			
			flag = this.irpChatService.addChat(chat);
			
			
			return flag;
		}
		
		//绑定session 
		public void setScriptSessionId(Long _userid){
			WebContextFactory.get().getScriptSession().setAttribute("userid",_userid);
			
		}
		public void addScriptSession(String _userid,HttpServletRequest request){
			ServletContext application = request.getServletContext();
			String scriptsessionstr = "scriptsession"+_userid;
			application.setAttribute(scriptsessionstr,WebContextFactory.get().getScriptSession());
		}
		/**
		 * 根据用户id获得指定用户的页面脚本session
		 * @param userid
		 * @param request
		 * @return
		 */
		public ScriptSession getScriptSession(Long _userid,HttpServletRequest request){
			
			ScriptSession scriptsession = null;
			
			ServletContext application = request.getServletContext();
			
			String scriptsessionstr = "scriptsession"+_userid;
			
			scriptsession = (ScriptSession) application.getAttribute(scriptsessionstr);
			
			return scriptsession;
		}
		
		/**
		 * 推送给前台
		 * @reutrn
		 */
		public void callForthWithMess(Long _userid,HttpServletRequest request,Long _loginuid,Long _pointobj){
		
			Collection<ScriptSession> listscsesion = new ArrayList<ScriptSession>();
			ScriptSession scfirst = null;
			if (_pointobj==20l) {
				//组   遍历组里的人
				List<IrpUser> listuser = this.irpGroupService.findUsersByGroupId(_userid);

				if (listuser.size()>0 && listuser!=null) {
					for (int i = 0; i < listuser.size(); i++) {
						scfirst = this.getScriptSession(listuser.get(i).getUserid(),request);
						if (scfirst!=null) {
							if (listuser.get(i).getUserid()!=_loginuid) {
								listscsesion.add(scfirst);
							}
							
						}
					}
				}
			}else{
				//人
				scfirst = this.getScriptSession(_userid,request);
				if (scfirst!=null) {
					listscsesion.add(scfirst);
				}
			}
			ScriptSession second = this.getScriptSession(_loginuid,request);
			if (second!=null) {
				listscsesion.add(second);
			}
			//构建js脚本
			ScriptBuffer sb = new ScriptBuffer();
			sb.appendScript("trueT(");
			sb.appendData(_userid);
			sb.appendScript(",");
			sb.appendData(_loginuid);
			sb.appendScript(",");
			sb.appendData(_pointobj);
			sb.appendScript(")");
			Util util = new Util(listscsesion);
			util.addScript(sb);
			
		}
		  /**
	     * 根据id获得用户在页面显示的名字
	     * @param _userid
	     * @return
	     */
	    public String getShowPageViewNameByUserId(Long _userid){
	    	return this.irpUserService.findShowNameByUserid(_userid);
	    }
	    /**
	     * 
	     * 标记为已读
	     */
	    public void chatMarkAlready(){
	    	
	    	boolean falg= this.irpChatService.changeAlreadyReady(IrpChat.CHATOBJPEOPLE, IrpChat.STATUSNORMAL, LoginUtil.getLoginUserId(), fromuserid,IrpChat.CHATALREADYREAD);
	    	
	    	ActionUtil.writer(falg+"");
	    }
	    /**
	     * 
	     * 标记为已读（组）
	     */
	    public void chatMarkAlreadyGroup(){
	    	boolean falg= this.irpChatService.changeAlreadyReady(IrpChat.CHATOBJGROUP, IrpChat.STATUSNORMAL, LoginUtil.getLoginUserId(), groupmarkid,IrpChat.CHATALREADYREAD);
	    	
	    	ActionUtil.writer(falg+"");
	    }
	  
	    /**
	     * delection unread message by loginuser
	     * 检测未读消息   登录用户
	     * @return
	     */
	    public void detectionUnRead(){

	    	List<Map> lists = irpChatService.findUnreadUserList(IrpChat.STATUSNORMAL, LoginUtil.getLoginUserId(), IrpChat.CHATUNREAD);
	    	
	    	String jsondata = "";
	    	
	    	List list = new ArrayList();
	    	if (lists.size()>0) {
				for (int i = 0; i < lists.size(); i++) {
					Map map = new HashMap();
					Map maps  = lists.get(i);
					Long chatids = Long.parseLong(maps.get("CHATID").toString());
					IrpChat irpchatobj = null;
					if (chatids!=null) {
						 irpchatobj = this.irpChatService.irpChatByCId(chatids);
					}
					if (irpchatobj.getChatpointtype()==10l) {
						//人
						Long userid = irpchatobj.getSenderuserid();
						map.put("userid",userid);
						map.put("uname",getShowPageViewNameByUserId(userid));
						map.put("status",10);
					}else if(irpchatobj.getChatpointtype()==20l){
						//组
						Long groupids = irpchatobj.getSenderuserid();
						map.put("userid",groupids);
						map.put("uname",this.irpGroupService.findGroupByGroupId(groupids).getGroupname());
						map.put("status",20);
					}
					

					list.add(map);
				}
				jsondata = JsonUtil.list2json(list);
			}
	    	ActionUtil.writer(jsondata);
	    }
	    /**
		 * link chat pages
		 * 链接到谈话页面
		 * @return
		 */
	    private Integer chatbyuserpages = 10;
	    
	    private Integer chatbyusercontentmaxnum;
		public Integer getChatbyusercontentmaxnum() {
			return chatbyusercontentmaxnum;
		}

		public void setChatbyusercontentmaxnum(Integer chatbyusercontentmaxnum) {
			this.chatbyusercontentmaxnum = chatbyusercontentmaxnum;
		}

		public Integer getChatbyuserpages() {
			return chatbyuserpages;
		}

		public void setChatbyuserpages(Integer chatbyuserpages) {
			this.chatbyuserpages = chatbyuserpages;
		}

		public String findChatFrame(){
			
			chatbyusercontentmaxnum = this.irpChatService.getAllChatContentByIdNum(IrpChat.CHATOBJPEOPLE, IrpChat.STATUSNORMAL, LoginUtil.getLoginUserId(), fromuserid);
		   
		   PageUtil pageutil = new PageUtil(1, chatbyuserpages, chatbyusercontentmaxnum);
		   
		   loginuserid = LoginUtil.getLoginUserId();
		   
		   List<IrpChat>  chatlists = this.irpChatService.getAllChatContentById(IrpChat.CHATOBJPEOPLE, IrpChat.STATUSNORMAL, LoginUtil.getLoginUserId(), fromuserid, "chatid desc",pageutil);
		   
		   List<IrpChat>  chatlista = new ArrayList();
		   for (int i = chatlists.size()-1; i >=0; i--) {
			   //chatlist.add();
			   chatlista.add(chatlists.get(i));
		   }
		   
		   this.chatlist = chatlista;
		   
		   
		   return SUCCESS;
		}
	    /**
	     * find my friends  
	     * logic: I pay attention to users
	     * 我关注的用户
	     * 我的好友
	     * @return
	     */
		private int pageNummyfriend=1;
		
		private Long loginonlineid;
		
		public Long getLoginonlineid() {
			return loginonlineid;
		}

		public void setLoginonlineid(Long loginonlineid) {
			this.loginonlineid = loginonlineid;
		}

		public int getPageNummyfriend() {
			return pageNummyfriend;
		}

		public void setPageNummyfriend(int pageNummyfriend) {
			this.pageNummyfriend = pageNummyfriend;
		}

		public String findMyFriend(){
	    	Long userid = LoginUtil.getLoginUserId();
	    	
	    	myfriefdstnum = irpMicroblogFocusService.findMicroblogFocusUserIdCount(userid);
	    	
	    	PageUtil pageUtil = new PageUtil(pageNummyfriend, 10, myfriefdstnum);
	    	
	    	List<IrpMicroblogFocus> list = irpMicroblogFocusService.findMicroblogFocusUserId(userid, pageUtil);
	    	
	    	List<IrpUser> userlists = null;
	    	
	    	if (list!=null && list.size()>0) {
	    		userlists = new ArrayList();
	    		Iterator iterator = list.iterator();
	    		while (iterator.hasNext()) {
	    			IrpMicroblogFocus imf = (IrpMicroblogFocus) iterator.next();
	    			userlists.add(this.irpUserService.findUserByUserId(imf.getUserid()));
				}
			}
	    	loginonlineid = LoginUtil.getLoginUserId();
	    	userlist = userlists;
	    	
	    	
	    	return SUCCESS;
	    }
	    
		
	    
		
}
