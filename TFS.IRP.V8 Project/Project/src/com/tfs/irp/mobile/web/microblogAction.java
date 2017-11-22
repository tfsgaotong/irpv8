package com.tfs.irp.mobile.web;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import weibo4j.model.User;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.attachedtype.entity.IrpAttachedType;
import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.channel.service.IrpChannelService;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.chnl_doc_link.service.IrpChnl_Doc_LinkService;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.service.IrpDocumentService;
import com.tfs.irp.file.entity.IrpUserFile;
import com.tfs.irp.file.service.IrpUserFileService;
import com.tfs.irp.group.entity.IrpGroup;
import com.tfs.irp.inform.entity.IrpInform;
import com.tfs.irp.inform.service.IrpInformService;
import com.tfs.irp.informtype.entity.IrpInformType;
import com.tfs.irp.informtype.service.IrpInformTypeService;
import com.tfs.irp.messagecontent.entity.IrpMessageContent;
import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.microblog.entity.IrpMicroblogView;
import com.tfs.irp.microblog.entity.IrpUserInfoView;
import com.tfs.irp.microblog.service.IrpMicroblogService;
import com.tfs.irp.microblogcollection.service.IrpMicroblogCollectionKeyService;
import com.tfs.irp.microblogcomment.entity.IrpMicroblogComment;
import com.tfs.irp.microblogcomment.entity.IrpMicroblogCommentView;
import com.tfs.irp.microblogcomment.service.IrpMicroblogCommentService;
import com.tfs.irp.microblogfocus.entity.IrpMicroblogFocus;
import com.tfs.irp.microblogfocus.service.IrpMicroblogFocusService;
import com.tfs.irp.topic.entity.IrpTopic;
import com.tfs.irp.topic.service.IrpTopicService;
import com.tfs.irp.topiclink.entity.IrpTopicLink;
import com.tfs.irp.topiclink.service.IrpTopicLinkService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.AtmeUtil;
import com.tfs.irp.util.FileUtil;
import com.tfs.irp.util.JsonUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.SysFileUtil;
import com.tfs.irp.util.TableIdUtil;

public class microblogAction extends ActionSupport  {
	private IrpMicroblogService irpMicroBlogService;
	private IrpChannelService irpChannelService;
	private IrpChnl_Doc_LinkService irpChnl_Doc_LinkService;
	private IrpDocumentService irpDocumentService;
	private IrpTopicService irpTopicService;
	private String publishInfo;
	private IrpMicroblog irpMicroblog;
	private List<String> irpMicrobloglist;
	private IrpMicroblogCollectionKeyService irpMicroblogCollectionKeyService;
	private IrpMicroblogFocusService  irpMicroblogFocusService;
	private IrpInformService irpInformService;
	private List<String> collectionOfUseridlist;
	private String wbmsgid;
	private Long loginuserid;
	private String showname;
    private IrpUserService irpUserService;
    private IrpUserFileService irpUserFileService;
    private String userid;
	private List  allUseridByFocusUserId;
    private List  allUseridByUserid;
    private String microblogidCard;
    private IrpMicroblogCommentService  irpMicroblogCommentService; 
    /*private Long personid;*/
    private int microblognump;
    private int microblogminep;
    private int microblogcollectnump;
    private List<String> mobilemicroblogcomment;
    
    private long micropicblogid;
    private List<IrpMicroblogComment> commentList;
    
    
    
	public IrpTopicService getIrpTopicService() {
		return irpTopicService;
	}

	public void setIrpTopicService(IrpTopicService irpTopicService) {
		this.irpTopicService = irpTopicService;
	}

	public IrpChnl_Doc_LinkService getIrpChnl_Doc_LinkService() {
		return irpChnl_Doc_LinkService;
	}

	public void setIrpChnl_Doc_LinkService(
			IrpChnl_Doc_LinkService irpChnl_Doc_LinkService) {
		this.irpChnl_Doc_LinkService = irpChnl_Doc_LinkService;
	}

	public IrpChannelService getIrpChannelService() {
		return irpChannelService;
	}

	public void setIrpChannelService(IrpChannelService irpChannelService) {
		this.irpChannelService = irpChannelService;
	}

	public List<IrpMicroblogComment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<IrpMicroblogComment> commentList) {
		this.commentList = commentList;
	}
	
	public IrpUserFileService getIrpUserFileService() {
		return irpUserFileService;
	}

	public void setIrpUserFileService(IrpUserFileService irpUserFileService) {
		this.irpUserFileService = irpUserFileService;
	}

	public IrpMicroblogService getIrpMicroBlogService() {
		return irpMicroBlogService;
	}

	public void setIrpMicroBlogService(IrpMicroblogService irpMicroBlogService) {
		this.irpMicroBlogService = irpMicroBlogService;
	}

	public String getPublishInfo() {
		return publishInfo;
	}

	public void setPublishInfo(String publishInfo) {
		this.publishInfo = publishInfo;
	}

	public IrpMicroblog getIrpMicroblog() {
		return irpMicroblog;
	}

	public void setIrpMicroblog(IrpMicroblog irpMicroblog) {
		this.irpMicroblog = irpMicroblog;
	}

	public List<String> getIrpMicrobloglist() {
		return irpMicrobloglist;
	}

	public void setIrpMicrobloglist(List<String> irpMicrobloglist) {
		this.irpMicrobloglist = irpMicrobloglist;
	}

	public IrpMicroblogCollectionKeyService getIrpMicroblogCollectionKeyService() {
		return irpMicroblogCollectionKeyService;
	}

	public void setIrpMicroblogCollectionKeyService(
			IrpMicroblogCollectionKeyService irpMicroblogCollectionKeyService) {
		this.irpMicroblogCollectionKeyService = irpMicroblogCollectionKeyService;
	}

	public IrpMicroblogFocusService getIrpMicroblogFocusService() {
		return irpMicroblogFocusService;
	}

	public void setIrpMicroblogFocusService(
			IrpMicroblogFocusService irpMicroblogFocusService) {
		this.irpMicroblogFocusService = irpMicroblogFocusService;
	}

	public IrpInformService getIrpInformService() {
		return irpInformService;
	}

	public void setIrpInformService(IrpInformService irpInformService) {
		this.irpInformService = irpInformService;
	}

	public List<String> getCollectionOfUseridlist() {
		return collectionOfUseridlist;
	}

	public void setCollectionOfUseridlist(List<String> collectionOfUseridlist) {
		this.collectionOfUseridlist = collectionOfUseridlist;
	}

	
	public String getWbmsgid() {
		return wbmsgid;
	}

	public void setWbmsgid(String wbmsgid) {
		this.wbmsgid = wbmsgid;
	}

	
	public Long getLoginuserid() {
		return loginuserid;
	}

	public void setLoginuserid(Long loginuserid) {
		this.loginuserid = loginuserid;
	}


	public String getShowname() {
		return showname;
	}

	public void setShowname(String showname) {
		this.showname = showname;
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public List getAllUseridByFocusUserId() {
		return allUseridByFocusUserId;
	}

	public void setAllUseridByFocusUserId(List allUseridByFocusUserId) {
		this.allUseridByFocusUserId = allUseridByFocusUserId;
	}

	public List getAllUseridByUserid() {
		return allUseridByUserid;
	}

	public void setAllUseridByUserid(List allUseridByUserid) {
		this.allUseridByUserid = allUseridByUserid;
	}

	public String getMicroblogidCard() {
		return microblogidCard;
	}

	public void setMicroblogidCard(String microblogidCard) {
		this.microblogidCard = microblogidCard;
	}

	public IrpMicroblogCommentService getIrpMicroblogCommentService() {
		return irpMicroblogCommentService;
	}

	public void setIrpMicroblogCommentService(
			IrpMicroblogCommentService irpMicroblogCommentService) {
		this.irpMicroblogCommentService = irpMicroblogCommentService;
	}

	/*public Long getPersonid() {
		return personid;
	}

	public void setPersonid(Long personid) {
		this.personid = personid;
	}
*/
	public int getMicroblognump() {
		return microblognump;
	}

	public void setMicroblognump(int microblognump) {
		this.microblognump = microblognump;
	}

	public int getMicroblogminep() {
		return microblogminep;
	}

	public void setMicroblogminep(int microblogminep) {
		this.microblogminep = microblogminep;
	}

	public int getMicroblogcollectnump() {
		return microblogcollectnump;
	}

	public void setMicroblogcollectnump(int microblogcollectnump) {
		this.microblogcollectnump = microblogcollectnump;
	}

	public List<String> getMobilemicroblogcomment() {
		return mobilemicroblogcomment;
	}

	public void setMobilemicroblogcomment(List<String> mobilemicroblogcomment) {
		this.mobilemicroblogcomment = mobilemicroblogcomment;
	}

	public long getMicropicblogid() {
		return micropicblogid;
	}

	public void setMicropicblogid(long micropicblogid) {
		this.micropicblogid = micropicblogid;
	}
	private String pageHTML = "";
	private int pageNumber = 1;
	private int pageSize =10;
	private int pagemobilenum = 1;
	private int pageminemobilenum = 1;
	private int pagecollectnum = 1;
	
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

	public int getPagemobilenum() {
		return pagemobilenum;
	}

	public void setPagemobilenum(int pagemobilenum) {
		this.pagemobilenum = pagemobilenum;
	}

	public int getPageminemobilenum() {
		return pageminemobilenum;
	}

	public void setPageminemobilenum(int pageminemobilenum) {
		this.pageminemobilenum = pageminemobilenum;
	}

	public int getPagecollectnum() {
		return pagecollectnum;
	}

	public void setPagecollectnum(int pagecollectnum) {
		this.pagecollectnum = pagecollectnum;
	}
	public String getUserPicUrl(IrpUser user){
		String sUserPicPath = null;
		String sPath = ServletActionContext.getRequest().getContextPath();
		if(user.getUserpic()!=null && !user.getUserpic().isEmpty()){
			sUserPicPath = sPath+"/file/readfile.action?fileName="+user.getUserpic();
		}else{
			if(user.getSex()==null || user.getSex()==1){
				sUserPicPath = sPath+"/view/images/male.jpg";
			} else {
				sUserPicPath = sPath+"/view/images/female.jpg";
			}
		}
		return sUserPicPath;
	}
	public void microblogAllFocus() throws Exception{
		List<IrpMicroblogView> irpMicrobloglist = new ArrayList<IrpMicroblogView>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		Long nUserid=irpuser.getUserid();
		//获取微知长度
		int countnum=this.irpMicroBlogService.findMicroblogOfFocusCount(IrpMicroblog.ISDELFALSE, nUserid);
		//this.setPageSize(Integer.parseInt(this.irpMicroBlogService.findIrpConfigCvalue(IrpMicroblog.IRPMICROLOGVIEWPIECE)));
		PageUtil pageUtil = new PageUtil(this.pageNumber,this.pageSize,countnum);
		int count=0;
		if(countnum%pageSize==0){
			count=countnum/pageSize;
		}else{
			count=countnum/pageSize+1;
		}
		if(this.pageNumber<=count){
			irpMicrobloglist= this.irpMicroBlogService.findMicroblogOfFocus(IrpMicroblog.ISDELFALSE, nUserid,pageUtil,nUserid);
		}
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		collectionOfUseridlist=	this.irpMicroblogCollectionKeyService.selectMicroblogidOfUserid(nUserid);
		List<Map<Object,Object>> first = new ArrayList<Map<Object,Object>>();
		String[] qq=new String[]{};
		IrpMicroblog microblog=null;
		IrpUser user=null;
		for(int i = 0 ; i < irpMicrobloglist.size();i++){
			Map<Object,Object> map = new HashMap<Object, Object>();
			String content=irpMicrobloglist.get(i).getMICROBLOGCONTENT();
			content=content.replaceAll("<((?!>).)*>|<\\/((?!<).)*>", "");
			String sFilePath=null;
			if(irpMicrobloglist.get(i).getUSERPIC()==null){
				if(irpMicrobloglist.get(i).getSEX()==null){
					sFilePath="/view/images/male.jpg?1"; 
				}else{
					if(irpMicrobloglist.get(i).getSEX()==2){
						sFilePath="/view/images/female.jpg?1"; 
					}else{
						sFilePath="/view/images/male.jpg?1"; 
					}
				}
			}else{
				sFilePath="/file/readfile.action?fileName="+irpMicrobloglist.get(i).getUSERPIC(); 
			}
			if (irpMicrobloglist.get(i).getTRANSPONDID()==0) {
				map.put("wbuserid", irpMicrobloglist.get(i).getUSERID());
				map.put("wbblogmsid", irpMicrobloglist.get(i).getMICROBLOGID());
				map.put("wbhead", sFilePath);
				map.put("wbusername", irpMicrobloglist.get(i).getSHOWNAME());
				map.put("wbtimes", irpMicrobloglist.get(i).getCRTIME());
				map.put("wbsource", irpMicrobloglist.get(i).getFROMDATA());
				map.put("wbcontext", content);
				String ss=irpMicrobloglist.get(i).getMICROBLOGCONTENTIMG();
				if(ss==null){
					map.put("wbimg", qq);
				}else{
					String jieguo=ss.substring(ss.indexOf("name=")+5, ss.indexOf("height")-4);
					String[] aaa=jieguo.split(",");
					List<Map<Object,Object>> sec = new ArrayList<Map<Object,Object>>();
					for(int j=0;j<aaa.length;j++){
						Map<Object, Object> map1=new HashMap<Object,Object>();
						String path="/file/readfile.action?fileName="+aaa[j];
						map1.put("img0", path);
						sec.add(map1);
					}
					map.put("wbimg", sec);
				}
				if(collectionOfUseridlist.contains(irpMicrobloglist.get(i).getMICROBLOGID().toString())){
					map.put("wbcollec", "1");
				}else{
					map.put("wbcollec", "0");
				}
				map.put("wbReblogStatus", "0");
				map.put("wbReblogName", "");
				map.put("wbReblogContxt", "");
				map.put("wbReblogImg", qq);
			}else{
				microblog=irpMicroBlogService.irpMicroblog(irpMicrobloglist.get(i).getTRANSPONDID());
				user=irpUserService.findUserByUserId(microblog.getUserid());
				map.put("wbuserid", irpMicrobloglist.get(i).getUSERID());
				map.put("wbblogmsid", irpMicrobloglist.get(i).getMICROBLOGID());
				if(microblog.getIsdel()==2){
					map.put("wbReblogContxt", "抱歉，由于此微知内容不符合规定，无法查看。");
					map.put("wbtimes", "");
					map.put("wbimg", qq);
					map.put("wbReblogName", "");
					map.put("wbReblogImg", qq);
				}else if(microblog.getIsdel()==1){
					map.put("wbReblogContxt", "抱歉，此微知已被作者删除。");
					map.put("wbtimes", "");
					map.put("wbimg", qq);
					map.put("wbReblogName", "");
					map.put("wbReblogImg", qq);
				}else{
					String con=microblog.getMicroblogcontent();
					con=con.replaceAll("<((?!>).)*>|<\\/((?!<).)*>", "");
					String showName=null;
					if(user.getNickname()==null){
						showName=user.getTruename();
						if(showName==null){
							showName=user.getUsername();
						}
					}else{
						showName=user.getNickname();
					}
					map.put("wbtimes", irpMicrobloglist.get(i).getCRTIME());
					map.put("wbReblogContxt", con);
					map.put("wbimg", qq);
					map.put("wbReblogName", showName);
					String ss=microblog.getMicroblogcontentimg();
					ss=microblog.getMicroblogcontentimg();
					if(ss==null){
						map.put("wbReblogImg", qq);
					}else{
						String jieguo=ss.substring(ss.indexOf("name=")+5, ss.indexOf("height")-4);
						String[] aaa=jieguo.split(",");
						List<Map<Object,Object>> sec = new ArrayList<Map<Object,Object>>();
						for(int j=0;j<aaa.length;j++){
							Map<Object, Object> map1=new HashMap<Object,Object>();
							String path="/file/readfile.action?fileName="+aaa[j];
							map1.put("img0", path);
							sec.add(map1);
						}
						map.put("wbReblogImg", sec);
					}
				}
				map.put("wbhead", sFilePath);
				String showName=null;
				if(user.getNickname()==null){
					showName=user.getTruename();
					if(showName==null){
						showName=user.getUsername();
					}
				}else{
					showName=user.getNickname();
				}
				map.put("wbusername", irpMicrobloglist.get(i).getSHOWNAME());
				map.put("wbtimes", irpMicrobloglist.get(i).getCRTIME());
				map.put("wbcontext", content);
				if(collectionOfUseridlist.contains(irpMicrobloglist.get(i).getMICROBLOGID().toString())){
					map.put("wbcollec", "1");
				}else{
					map.put("wbcollec", "0");
				}
				map.put("wbReblogStatus", "1");
				map.put("wbsource", irpMicrobloglist.get(i).getFROMDATA());
			}
			first.add(map);
		}
		ActionUtil.writer(JSON.toJSONString(first));
	}
	private int wbcollecstatus;
	private String wbReblogcontent;
	
	 public int getWbcollecstatus() {
		return wbcollecstatus;
	}

	public void setWbcollecstatus(int wbcollecstatus) {
		this.wbcollecstatus = wbcollecstatus;
	}
	
	
	public String getWbReblogcontent() {
		return wbReblogcontent;
	}

	public void setWbReblogcontent(String wbReblogcontent) {
		this.wbReblogcontent = wbReblogcontent;
	}

	/**
	   *收藏与取消收藏 
	*/
	public void microblogCollectAddOrDel(){
		int msg=0;
		int resultstatus=0;
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);	
		Long userid=irpuser.getUserid();
		if(wbcollecstatus==1){
			msg=this.irpMicroblogCollectionKeyService.addTfsMicroblogCollectionKey(Long.parseLong(wbmsgid), userid);
			if(msg!=0){
				resultstatus=1000;
			}else{
				resultstatus=0;
			}
			JSONObject j = JSON.parseObject("{\"resultstatus\":\"" + resultstatus +"\"}");
			ActionUtil.writer(j.toJSONString());	
		}else{
			msg=this.irpMicroblogCollectionKeyService.deleteTfsMicroblogCollectionKey(Long.parseLong(wbmsgid));
			if(msg!=0){
				resultstatus=1000;
			}else{
				resultstatus=0;
			}
			JSONObject j = JSON.parseObject("{\"resultstatus\":\"" + resultstatus +"\"}");
			ActionUtil.writer(j.toJSONString());	
		}
			
	}
	private int concernedstatus;
	
	public int getConcernedstatus() {
		return concernedstatus;
	}

	public void setConcernedstatus(int concernedstatus) {
		this.concernedstatus = concernedstatus;
	}

	/**
	 *关注与取消关注 
	 */
	public void microblogFocusAddOrDel(){
		int msg=0;
		int resultstatus=0;
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		Long userid=irpuser.getUserid();
		if(concernedstatus==1){
			msg = this.irpMicroblogFocusService.addMicroblogFocusUserid(userid, wbuserid);
			if(msg!=0){
				resultstatus=1000;
			}else{
				resultstatus=0;
			}
			JSONObject j = JSON.parseObject("{\"resultstatus\":\"" + resultstatus +"\"}");
			ActionUtil.writer(j.toJSONString());	
		}else{
			msg = this.irpMicroblogFocusService.deleteMicroblogFocusUserid(userid,wbuserid);
			if(msg!=0){
				resultstatus=1000;
			}else{
				resultstatus=0;
			}
			JSONObject j = JSON.parseObject("{\"resultstatus\":\"" + resultstatus +"\"}");
			ActionUtil.writer(j.toJSONString());	
		}
		
	}
	/**
	 * 增加转发信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void addMicroblogTranspond(){
		int resultstatus=0;
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		Long userid=irpuser.getUserid();
		irpMicroblog=irpMicroBlogService.irpMicroblog(Long.parseLong(wbmsgid));
		try {
			wbReblogcontent=URLDecoder.decode(wbReblogcontent,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(irpMicroblog.getTranspondid()!=0){
			irpMicroblog.setTranspondid(irpMicroblog.getTranspondid());
			IrpUser user=irpUserService.findUserByUserId(irpMicroblog.getUserid());
			String showName=null;
			if(user.getNickname()==null){
				showName=user.getTruename();
				if(showName==null){
					showName=user.getUsername();
				}
			}else{
				showName=user.getNickname();
			}
			wbReblogcontent=wbReblogcontent+"@"+showName+"//"+irpMicroblog.getMicroblogcontent();
		}else{
			irpMicroblog.setTranspondid(Long.parseLong(wbmsgid));
		}
		irpMicroblog.setMicroblogcontent(wbReblogcontent);
		irpMicroblog.setUserid(userid);
		int msg = this.irpMicroBlogService.addMicroBlogTranSpondMobile(irpMicroblog);
		if(msg!=0){
			resultstatus=1000;//转发成功
		}else{
			resultstatus=0;//转发失败
		}
		JSONObject j = JSON.parseObject("{\"resultstatus\":\"" + resultstatus +"\"}");
		ActionUtil.writer(j.toJSONString());
		/*AtmeUtil au = new AtmeUtil();
			//处理@
			if (msg==1) {
				
				IrpMicroblog irpMicroblogFirct = this.irpMicroBlogService.findFirstMicroblog(LoginUtil.getLoginUserId());
				au.disposeATME(irpMicroblog.getMicroblogcontent(),irpMicroblogFirct.getMicroblogid());	
			}
			if(msg==1){
				this.irpMicroBlogService.updateMicroblogTranspondByMicroblogid(Long.parseLong(msgidid));
			}	
			try {
				irpMicrobloglist =  this.irpMicroBlogService.findMicroblogOfFocus(IrpMicroblog.ISDELFALSE, LoginUtil.getLoginUserId(),TableIdUtil.getNextId(IrpMicroblog.TABLE_NAME)-1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	}
	/**
     * 通过文章id得到评论的集合
     * @return
     */
	public void microblogListByMicroBlogId(){
		int commentCount=this.irpMicroblogCommentService.findMicroBlogCommentCount(Long.parseLong(wbmsgid),IrpMicroblogComment.ISDELTRUE);
		PageUtil pageUtil = new PageUtil(this.pageNumber, this.pageSize,
				commentCount);
		int count=0;
		String filePath=null;
		IrpUser user=null;
		if(commentCount%pageSize==0){
			count=commentCount/pageSize;
		}else{
			count=commentCount/pageSize+1;
		}
		if(this.pageNumber<=count){
			commentList =this.irpMicroblogCommentService.selectCommentlistByWbid(Long.parseLong(wbmsgid),IrpMicroblogComment.ISDELTRUE,pageUtil);
		}
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		if(commentList==null){
			
		}else{
			for(int i = 0 ; i < commentList.size();i++){
				user=irpUserService.findUserByUserId(commentList.get(i).getUserid());
				Map<Object,Object> map = new HashMap<Object, Object>();
				
				if(user.getUserpic()!=null){
					filePath="/file/readfile.action?fileName="+user.getUserpic();
				}else{
					if(user.getSex()!=null){
						if(user.getSex()==2){
							filePath="/view/images/female.jpg?1"; 
						}else{
							filePath="/view/images/male.jpg?1"; 
						}
					}else{
						filePath="/view/images/male.jpg?1"; 
					}
				}
				map.put("wbcommenthead", filePath);
				String showName=null;
				if(user.getNickname()==null){
					showName=user.getTruename();
					if(showName==null){
						showName=user.getUsername();
					}
				}else{
					showName=user.getNickname();
				}
				map.put("wbcommentname", showName);
				map.put("wbcommentimes", commentList.get(i).getCrtime());
				String content=commentList.get(i).getContent();
				content=content.replaceAll("<((?!>).)*>|<\\/((?!<).)*>", "");
				map.put("wbcommentcontext", content);
				list.add(map);
			}
		}
		ActionUtil.writer(JSON.toJSONString(list));
	}
	private String wbcommentcontxt;
	private IrpMicroblogComment irpMicroblogComment;
	
	public IrpMicroblogComment getIrpMicroblogComment() {
		return irpMicroblogComment;
	}

	public void setIrpMicroblogComment(IrpMicroblogComment irpMicroblogComment) {
		this.irpMicroblogComment = irpMicroblogComment;
	}

	public String getWbcommentcontxt() {
		return wbcommentcontxt;
	}

	public void setWbcommentcontxt(String wbcommentcontxt) {
		this.wbcommentcontxt = wbcommentcontxt;
	}
	/**
	 * 字数限制
	 * @return
	 */
    public void wordLimit(){	
		JSONObject j = JSON.parseObject("{\"count\":\"" + 120 +"\"}");
		ActionUtil.writer(j.toJSONString());
    }
	/**
	 * 增加微知评论
	 * @return
	 */
    public void addComment(){	
    	int resultstatus=0;
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		Long userid=irpuser.getUserid();
		irpMicroblog=irpMicroBlogService.irpMicroblog(Long.parseLong(wbmsgid));
		try {
			wbcommentcontxt=URLDecoder.decode(wbcommentcontxt,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int _nStatus = this.irpMicroblogCommentService.addBlogComment(irpMicroblog.getUserid(),Long.parseLong(wbmsgid),wbcommentcontxt,0,userid);
     
		if(_nStatus==1){
			//更新微知表
         	//this.irpMicroBlogService.updateMicroblogCommentCount(Long.parseLong(wbmsgid), irpMicroblogCommentService.selectByMicroBlogComment(Long.parseLong(wbmsgid)));
         	resultstatus=1000;
		}else{
			resultstatus=0;
		}
		irpMicroblogComment=this.irpMicroblogCommentService.findNewWestCommentByUserid(userid, IrpMicroblogComment.ISDELTRUE);
		JSONObject j = JSON.parseObject("{\"resultstatus\":\"" + resultstatus +"\"}");
		ActionUtil.writer(j.toJSONString());
    }
    private Long wbuserid;
    
    
	public Long getWbuserid() {
		return wbuserid;
	}

	public void setWbuserid(Long wbuserid) {
		this.wbuserid = wbuserid;
	}

	/**
	 * 点击微知头像详情
	 */
	@SuppressWarnings("unchecked")
	public void findMicroblogPersonalCard(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		if(wbuserid==null||wbuserid==0){
			wbuserid=irpuser.getUserid();
		}
		List<IrpUserInfoView> userInfoList = new ArrayList<IrpUserInfoView>();
		IrpUserInfoView userInfo = null;
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		List<String> aa= new ArrayList<String>();
		//irpMicrobloglist=this.irpMicroBlogService.findMicroblogOfUseridInCard(wbuserid,IrpMicroblog.ISDELFALSE);
		userInfoList=this.irpMicroBlogService.findMicroblogOfUserid(wbuserid,IrpMicroblog.ISDELFALSE);
		Map<Object,Object> map = new HashMap<Object, Object>();
		String sFilePath=null;
		if(userInfoList.get(0).getUSERPIC()==null){
			if(userInfoList.get(0).getSEX()==null){
				sFilePath="/view/images/male.jpg?1"; 
			}else{
				if(userInfoList.get(0).getSEX()==2){
					sFilePath="/view/images/female.jpg?1"; 
				}else{
					sFilePath="/view/images/male.jpg?1"; 
				}
			}
		}else{
			sFilePath="/file/readfile.action?fileName="+userInfoList.get(0).getUSERPIC(); 
		}
		map.put("wbdetailhead", sFilePath);
		map.put("wbdetailname", userInfoList.get(0).getSHOWNAME());
		map.put("wbdetailid", userInfoList.get(0).getUSERID());
		map.put("wbdetailattension", userInfoList.get(0).getMICROBLOGFOCUSID());
		map.put("wbdetailfuns", userInfoList.get(0).getMICROBLOGUSERID());
		list.add(map);
		ActionUtil.writer(JSON.toJSONString(list));
	}
	private String eachuserid;
	
	public String getEachuserid() {
		return eachuserid;
	}

	public void setEachuserid(String eachuserid) {
		this.eachuserid = eachuserid;
	}
	private List<IrpMicroblogFocus> irpMicroblogFocuslist; 
	
	public List<IrpMicroblogFocus> getIrpMicroblogFocuslist() {
		return irpMicroblogFocuslist;
	}

	public void setIrpMicroblogFocuslist(
			List<IrpMicroblogFocus> irpMicroblogFocuslist) {
		this.irpMicroblogFocuslist = irpMicroblogFocuslist;
	}

	/**
	 * 求出所有我关注的人的id
	 * @return
	 */
	public void findConcernPersonByUserId(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		Long userid=irpuser.getUserid();
		if(wbuserid==null||wbuserid==0){
			wbuserid=userid;
		}
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		int countnum=this.irpMicroblogFocusService.findEachMicroblogFocusUserIdCountById(wbuserid);
		PageUtil pageUtil = new PageUtil(this.pageNumber, this.getPageSize(),countnum);
		int count=0;
		IrpUser user=null;
		if(countnum%pageSize==0){
			count=countnum/pageSize;
		}else{
			count=countnum/pageSize+1;
		}
		if(this.pageNumber<=count){
			irpMicroblogFocuslist = this.irpMicroblogFocusService.findMicroblogFocusUserIdById(wbuserid,pageUtil);
		}
		//List<IrpMicroblogFocus> irpMicroblogFocuslist1 = this.irpMicroblogFocusService.findMicroblogFocusUserIdById(userid,pageUtil);
		List irpMicroblogFocuslist1 = this.irpMicroblogFocusService.selectUseridByLoginuserId(userid);
		if(irpMicroblogFocuslist==null){
			
		}else{
			String filePath=null;
			for(int i=0;i<irpMicroblogFocuslist.size();i++){
				Map<Object,Object> map = new HashMap<Object, Object>();
				//System.out.println(irpMicroblogFocuslist.get(i).getUserid());
				user=irpUserService.findUserByUserId(irpMicroblogFocuslist.get(i).getUserid());
				//System.out.println(user);
				//System.out.println(user.getUserid());
				if(user.getUserpic()==null||user.getUserpic().equals("")){
					if(user.getSex()!=null){
						if(user.getSex()==2){
							filePath="/view/images/female.jpg?1"; 
						}else{
							filePath="/view/images/male.jpg?1"; 
						}
					}else{
						filePath="/view/images/male.jpg?1"; 
					}
				}else{
					filePath="/file/readfile.action?fileName="+user.getUserpic();
				}
				map.put("afansuserid", irpMicroblogFocuslist.get(i).getUserid());
				map.put("afunsheadpic", filePath);
				if(user.getNickname()==null){
					if(user.getTruename()==null){
						if(user.getUsername()==null){
							map.put("afunsname", "");
						}else{
							map.put("afunsname", user.getUsername());
						}
					}else{
						map.put("afunsname", user.getTruename());
					}
				}else{
					map.put("afunsname", user.getNickname());
				}
				if(user.getExpertintro()==null){
					map.put("afunswellknow", "");
				}else{
					map.put("afunswellknow", user.getExpertintro());
				}
				if(user.getEmail()==null){
					map.put("afunsmail", "");
				}else{
					map.put("afunsmail", user.getEmail());
				}if(user.getLocation()==null){
					map.put("afunsarea", "");
				}else{
					map.put("afunsarea", user.getLocation());
				}
				if(irpMicroblogFocuslist1.contains(user.getUserid().toString())){
					map.put("afunsstatus", "1");
				}else{
					map.put("afunsstatus", "0");
				}
				list.add(map);
			}
		}
		ActionUtil.writer(JSON.toJSONString(list));
	}
	/**
	 * 求出所有我的粉丝
	 * @return
	 */
	public void findFansByUserId(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		Long userid=irpuser.getUserid();
		if(wbuserid==null||wbuserid==0){
			wbuserid=userid;
		}
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		int countnum=this.irpMicroblogFocusService.findMicroblogUserIdCount(wbuserid);
		PageUtil pageUtil = new PageUtil(this.pageNumber, this.getPageSize(),countnum);
		int count=0;
		IrpUser user=null;
		if(countnum%pageSize==0){
			count=countnum/pageSize;
		}else{
			count=countnum/pageSize+1;
		}
		if(this.pageNumber<=count){
			irpMicroblogFocuslist=this.irpMicroblogFocusService.findMicroblogUserId(wbuserid,pageUtil);
		}
		//List<IrpMicroblogFocus> irpMicroblogFocuslist1 = this.irpMicroblogFocusService.selectFansByLoginuserId(userid);
		List irpMicroblogFocuslist1 = this.irpMicroblogFocusService.selectUseridByLoginuserId(userid);
		if(irpMicroblogFocuslist==null){
			
		}else{
			String filePath=null;
			for(int i=0;i<irpMicroblogFocuslist.size();i++){
				Map<Object,Object> map = new HashMap<Object, Object>();
				user=irpUserService.findUserByUserId(irpMicroblogFocuslist.get(i).getFocususerid());
				if(user.getUserpic()==null||user.getUserpic().equals("")){
					if(user.getSex()!=null){
						if(user.getSex()==2){
							filePath="/view/images/female.jpg?1"; 
						}else{
							filePath="/view/images/male.jpg?1"; 
						}
					}else{
						filePath="/view/images/male.jpg?1"; 
					}
				}else{
					filePath="/file/readfile.action?fileName="+user.getUserpic();
				}
				map.put("afansuserid", irpMicroblogFocuslist.get(i).getFocususerid());
				map.put("afunsheadpic", filePath);
				if(user.getNickname()==null){
					if(user.getTruename()==null){
						if(user.getUsername()==null){
							map.put("afunsname", "");
						}else{
							map.put("afunsname", user.getUsername());
						}
					}else{
						map.put("afunsname", user.getTruename());
					}
				}else{
					map.put("afunsname", user.getNickname());
				}
				if(user.getExpertintro()==null){
					map.put("afunswellknow", "");
				}else{
					map.put("afunswellknow", user.getExpertintro());
				}
				if(user.getEmail()==null){
					map.put("afunsmail", "");
				}else{
					map.put("afunsmail", user.getEmail());
				}if(user.getLocation()==null){
					map.put("afunsarea", "");
				}else{
					map.put("afunsarea", user.getLocation());
				}
				if(irpMicroblogFocuslist1.contains(user.getUserid().toString())){
					map.put("afunsstatus", "1");
				}else{
					map.put("afunsstatus", "0");
				}
				list.add(map);
			}
		}
		ActionUtil.writer(JSON.toJSONString(list));
	}
	private List<IrpUserFile> personAllFile;
	
	public List<IrpUserFile> getPersonAllFile() {
		return personAllFile;
	}

	public void setPersonAllFile(List<IrpUserFile> personAllFile) {
		this.personAllFile = personAllFile;
	}

	/**
	 * 查看自己所有的图片附件
	 * @return
	 */
	public void findAllPhotosByUserId(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		if(wbuserid==null||wbuserid==0){
			wbuserid=irpuser.getUserid();
		}
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		int nDataCount =irpUserFileService.FileAmount(wbuserid, IrpAttachedType.JPG_FIELD_NAME);
		PageUtil pageUtil = new PageUtil(this.pageNumber, this.pageSize, nDataCount); 
		/*int count=0;
		if(nDataCount%pageSize==0){
			count=nDataCount/pageSize;
		}else{
			count=nDataCount/pageSize+1;
		}
		if(this.pageNumber<=count){
		}*/
		personAllFile=irpUserFileService.userAllFileByType(wbuserid, IrpAttachedType.JPG_FIELD_NAME);//查询所有附件
		if(personAllFile==null){
			
		}else{
			String filePath=null;
			for(int i=0;i<personAllFile.size();i++){
				Map<Object,Object> map = new HashMap<Object, Object>();
				filePath="/file/readfile.action?fileName="+personAllFile.get(i).getFilename();
				map.put("img0", filePath);
				list.add(map);
			}
		}
		ActionUtil.writer(JSON.toJSONString(list));
	}
	/**
	 * 得到微知个人列表
	 * @return
	 */
	public void findMicroblogByUserid(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		if(wbuserid==null||wbuserid==0){
			wbuserid=irpuser.getUserid();
		}
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		List<IrpMicroblog> irpMicrobloglist =null;
		IrpUser user=irpUserService.findUserByUserId(wbuserid);
		IrpMicroblog microblog=null;
		IrpUser wbuser=null;
		try {
			collectionOfUseridlist=	this.irpMicroblogCollectionKeyService.selectMicroblogidOfUserid(irpuser.getUserid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(wbuserid.equals(irpuser.getUserid())){
			int countnum=this.irpMicroBlogService.coutnMicroblogOfUserid(wbuserid,IrpMicroblog.ISDELFALSE);
			PageUtil pageUtil = new PageUtil(this.pageNumber,this.pageSize,countnum);
			int count=0;
			if(countnum%pageSize==0){
				count=countnum/pageSize;
			}else{
				count=countnum/pageSize+1;
			}
			if(this.pageNumber<=count){
				irpMicrobloglist = this.irpMicroBlogService.findMicroblogListByUserid(wbuserid,IrpMicroblog.ISDELFALSE,pageUtil);
			}
		}else{
			int countnum=this.irpMicroBlogService.coutnMicroblogOfUserid(wbuserid,IrpMicroblog.ISDELFALSE,IrpMicroblog.PUBLICMICROBLOG);
			PageUtil pageUtil = new PageUtil(this.pageNumber,this.pageSize,countnum);
			int count=0;
			if(countnum%pageSize==0){
				count=countnum/pageSize;
			}else{
				count=countnum/pageSize+1;
			}
			if(this.pageNumber<=count){
				irpMicrobloglist =	this.irpMicroBlogService.findMicroblogListByUserid(wbuserid,IrpMicroblog.ISDELFALSE,pageUtil,IrpMicroblog.PUBLICMICROBLOG);
			}
		}
		if(irpMicrobloglist==null){
			
		}else{
			
			String[] qq=new String[]{};
			for(int i = 0 ; i < irpMicrobloglist.size();i++){
				Map<Object,Object> map = new HashMap<Object, Object>();
				String content=irpMicrobloglist.get(i).getMicroblogcontent();
				if(content!=null){
					content=content.replaceAll("<((?!>).)*>|<\\/((?!<).)*>", "");
				}else{
					content = "";
				}
				String sFilePath=null;
				if(user.getUserpic()==null){
					if(user.getSex()==null){
						sFilePath="/view/images/male.jpg?1"; 
					}else{
						if(user.getSex()==2){
							sFilePath="/view/images/female.jpg?1"; 
						}else{
							sFilePath="/view/images/male.jpg?1"; 
						}
					}
				}else{
					sFilePath="/file/readfile.action?fileName="+user.getUserpic(); 
				}
				if (irpMicrobloglist.get(i).getTranspondid()==0) {
					map.put("wbuserid", wbuserid);
					map.put("wbblogmsid", irpMicrobloglist.get(i).getMicroblogid());
					map.put("wbhead", sFilePath);
					String showName=null;
					if(user.getNickname()==null){
						showName=user.getTruename();
						if(showName==null){
							showName=user.getUsername();
						}
					}else{
						showName=user.getNickname();
					}
					map.put("wbusername", showName);
					map.put("wbtimes", irpMicrobloglist.get(i).getCrtime());
					map.put("wbsource", irpMicrobloglist.get(i).getFromdata());
					map.put("wbcontext", content);
					String ss=irpMicrobloglist.get(i).getMicroblogcontentimg();
					if(ss==null){
						map.put("wbimg", qq);
					}else{
						String jieguo=ss.substring(ss.indexOf("name=")+5, ss.indexOf("height")-4);
						String[] aaa=jieguo.split(",");
						List<Map<Object,Object>> sec = new ArrayList<Map<Object,Object>>();
						for(int j=0;j<aaa.length;j++){
							Map<Object, Object> map1=new HashMap<Object,Object>();
							String path="/file/readfile.action?fileName="+aaa[j];
							map1.put("img0", path);
							sec.add(map1);
						}
						map.put("wbimg", sec);
					}
					if(collectionOfUseridlist.contains(irpMicrobloglist.get(i).getMicroblogid().toString())){
						map.put("wbcollec", "1");
					}else{
						map.put("wbcollec", "0");
					}
					map.put("wbReblogStatus", "0");
					map.put("wbReblogName", "");
					map.put("wbReblogContxt", "");
					map.put("wbReblogImg", qq);
				}else{
					microblog=irpMicroBlogService.irpMicroblog(irpMicrobloglist.get(i).getTranspondid());
					wbuser=irpUserService.findUserByUserId(microblog.getUserid());
					map.put("wbuserid", wbuserid);
					map.put("wbblogmsid", irpMicrobloglist.get(i).getMicroblogid());
					if(microblog.getIsdel()==2){
						map.put("wbReblogContxt", "抱歉，由于此微知内容不符合规定，无法查看。");
						map.put("wbtimes", "");
						map.put("wbimg", qq);
						map.put("wbReblogName", "");
						map.put("wbReblogImg", qq);
					}else if(microblog.getIsdel()==1){
						map.put("wbReblogContxt", "抱歉，此微知已被作者删除。");
						map.put("wbtimes", "");
						map.put("wbimg", qq);
						map.put("wbReblogName", "");
						map.put("wbReblogImg", qq);
					}else{
						String con=microblog.getMicroblogcontent();
						con=con.replaceAll("<((?!>).)*>|<\\/((?!<).)*>", "");
						String reblogshowName=null;
						if(wbuser.getNickname()==null){
							reblogshowName=wbuser.getTruename();
							if(reblogshowName==null){
								reblogshowName=wbuser.getUsername();
							}
						}else{
							reblogshowName=wbuser.getNickname();
						}
						map.put("wbtimes", irpMicrobloglist.get(i).getCrtime());
						map.put("wbReblogContxt", con);
						map.put("wbimg", qq);
						map.put("wbReblogName", reblogshowName);
						String gg=microblog.getMicroblogcontentimg();
						if(gg==null){
							map.put("wbReblogImg", qq);
						}else{
							String jieguo=gg.substring(gg.indexOf("name=")+5, gg.indexOf("height")-4);
							String[] bbb=jieguo.split(",");
							List<Map<Object,Object>> sec = new ArrayList<Map<Object,Object>>();
							for(int j=0;j<bbb.length;j++){
								Map<Object, Object> map1=new HashMap<Object,Object>();
								String path="/file/readfile.action?fileName="+bbb[j];
								map1.put("img0", path);
								sec.add(map1);
							}
							map.put("wbReblogImg", sec);
						}
					}
					map.put("wbhead", sFilePath);
					String showName=null;
					if(user.getNickname()==null){
						showName=user.getTruename();
						if(showName==null){
							showName=user.getUsername();
						}
					}else{
						showName=user.getNickname();
					}
					map.put("wbusername", showName);
					map.put("wbtimes", irpMicrobloglist.get(i).getCrtime());
					map.put("wbcontext", content);
					if(collectionOfUseridlist.contains(irpMicrobloglist.get(i).getMicroblogid().toString())){
						map.put("wbcollec", "1");
					}else{
						map.put("wbcollec", "0");
					}
					map.put("wbReblogStatus", "1");
					map.put("wbsource", irpMicrobloglist.get(i).getFromdata());
				}
				list.add(map);
			}
		}
		ActionUtil.writer(JSON.toJSONString(list));
	}
	private List<IrpChnlDocLink>  chnlDocLinks;
	
	public List<IrpChnlDocLink> getChnlDocLinks() {
		return chnlDocLinks;
	}

	public void setChnlDocLinks(List<IrpChnlDocLink> chnlDocLinks) {
		this.chnlDocLinks = chnlDocLinks;
	}
	private String userPicUrl;
	
	/*public String getUserPicUrl() {
		return userPicUrl;
	}*/

	public void setUserPicUrl(String userPicUrl) {
		this.userPicUrl = userPicUrl;
	}
	
		public IrpDocumentService getIrpDocumentService() {
		return irpDocumentService;
	}

	public void setIrpDocumentService(IrpDocumentService irpDocumentService) {
		this.irpDocumentService = irpDocumentService;
	}

		//查看我投稿的知识
		public void myallTouGao(){
			HttpServletRequest request = ServletActionContext.getRequest();
			String token = request.getParameter("token");
			IrpUser irpuser = mobileAction.getlogin(token);
			loginuserid=irpuser.getUserid();//设置userid权限页面使用  
			//得到当前用户的所有栏目对象 
			List<IrpChannel> irpChannels=new ArrayList<IrpChannel>(); 
			List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
			//按照创建时间排序
		 		String sOrderByClause=null;
		 		String crtimesort="desc";
		 		if(crtimesort!=null ){
		 			if(crtimesort.equals("asc")){
		 				  sOrderByClause = "crtime asc";  
		 			}
		 			if(crtimesort.equals("desc")){
		 				  sOrderByClause = "crtime desc";  
		 			} 
		 		}   
			 if(wbuserid!=null && wbuserid!=0L){
				 irpChannels= irpChannelService.findChannelIdsByPerson(wbuserid,0L, irpChannels); 
			}else{
				 irpChannels= irpChannelService.findChannelIdsByPerson(loginuserid,0L, irpChannels); 
			}
			 if(irpChannels!=null && irpChannels.size()>0){
				 List<Long> channelidsList=new ArrayList<Long>();
				 for (IrpChannel channel:irpChannels) {
					 if(channel!=null){
						 channelidsList.add(channel.getChannelid());
					 }
				 }
				 if(channelidsList!=null && channelidsList.size()>0){
					//先查询数量
						HashMap<String,Object> map=new HashMap<String, Object>();
						map.put("docstatus",IrpChnlDocLink.DOC_STATUS_CAOGAO);   
						map.put("channelidsList", channelidsList);
				 		//得到自己创建的所有知识的id
				 		List<Long> myallDociIds=irpChnl_Doc_LinkService.allDocids(map,IrpDocument.DOCUMENT_NOT_INFORM);
				 		//到企业知识库查寻oldid在这个集合里面的所有知识 
				 		int aDataCount=irpChnl_Doc_LinkService.alltougaoCount(myallDociIds,IrpDocument.DOCUMENT_NOT_INFORM,irpuser);
				 		PageUtil pageUtil= new PageUtil(this.pageNumber, this.pageSize, aDataCount); 
				 		int count=0;
						if(aDataCount%pageSize==0){
							count=aDataCount/pageSize;
						}else{
							count=aDataCount/pageSize+1;
						}
						if(this.pageNumber<=count){
							chnlDocLinks=irpChnl_Doc_LinkService.alltougaodocument(pageUtil,myallDociIds,sOrderByClause,IrpDocument.DOCUMENT_NOT_INFORM);
						}
				 		//初始化用户图片地址
						//IrpUser user = irpUserService.findUserByUserId(loginuserid);
						/*this.userPicUrl = user.getDefaultUserPic();*/
						if(chnlDocLinks==null){
							
						}else{
							for(int i = 0 ; i < chnlDocLinks.size();i++){
								Map<Object,Object> docmap = new HashMap<Object, Object>();
								docmap.put("contrititle", chnlDocLinks.get(i).getDoctitle());
								IrpDocument document=irpDocumentService.irpDocument(chnlDocLinks.get(i).getDocid());
								docmap.put("contrilabel", document.getDockeywords());
								docmap.put("contricontext", document.getDocabstract());
								docmap.put("contritimes", chnlDocLinks.get(i).getCrtime());
								docmap.put("contriid", chnlDocLinks.get(i).getChnldocid());
								docmap.put("contriurl", chnlDocLinks.get(i).getDocid());
								list.add(docmap);
							}
						}
				 }
			 }
			 ActionUtil.writer(JSON.toJSONString(list));
		}
		/**
		    * 获得所有收藏的微知 
		    * mobile  
		    * @reutrn
		    */
		public void findCollectMicroblogList(){
			HttpServletRequest request = ServletActionContext.getRequest();
			String token = request.getParameter("token");
			IrpUser irpuser = mobileAction.getlogin(token);
	   		Long userid = irpuser.getUserid();
	   		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
	   		IrpMicroblog microblog=null;
			IrpUser wbuser=null;
	   		microblogcollectnump =  this.irpMicroBlogService.findMicroblogOfFocusCollectCount(userid);
			PageUtil pageUtil = new PageUtil(this.pageNumber,this.pageSize,
					microblogcollectnump);
			int count=0;
			if(microblogcollectnump%pageSize==0){
				count=microblogcollectnump/pageSize;
			}else{
				count=microblogcollectnump/pageSize+1;
			}
			List<IrpMicroblogView> collectionMicroblogList=null;
			if(this.pageNumber<=count){
				collectionMicroblogList=this.irpMicroBlogService.findMicroblogOfFocusCollect(pageUtil,userid);
			}
	   		/*try {
	   			collectionOfUseridlist = this.irpMicroblogCollectionKeyService.selectMicroblogidOfUserid(userid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	   		if(collectionMicroblogList==null){
				
			}else{
				String[] qq=new String[]{};
				for(int i = 0 ; i < collectionMicroblogList.size();i++){
					Map<Object,Object> map = new HashMap<Object, Object>();
					//微知内容
					String content=collectionMicroblogList.get(i).getMICROBLOGCONTENT();
					content=content.replaceAll("<((?!>).)*>|<\\/((?!<).)*>", "");
					//微知头像路径
					String sFilePath=null;
					if(irpuser.getUserpic()==null){
						if(irpuser.getSex()==null){
							sFilePath="/view/images/male.jpg?1"; 
						}else{
							if(irpuser.getSex()==2){
								sFilePath="/view/images/female.jpg?1"; 
							}else{
								sFilePath="/view/images/male.jpg?1"; 
							}
						}
					}else{
						sFilePath="/file/readfile.action?fileName="+irpuser.getUserpic(); 
					}
					//判断是否为转发微知
					if (collectionMicroblogList.get(i).getTRANSPONDID()==0) {
						map.put("wbuserid", userid);
						map.put("wbblogmsid", collectionMicroblogList.get(i).getMICROBLOGID());
						map.put("wbhead", sFilePath);
						//原微知作者
						String showName=null;
						if(irpuser.getNickname()==null){
							showName=irpuser.getTruename();
							if(showName==null){
								showName=irpuser.getUsername();
							}
						}else{
							showName=irpuser.getNickname();
						}
						map.put("wbusername", showName);
						map.put("wbtimes", collectionMicroblogList.get(i).getCRTIME());
						map.put("wbsource", collectionMicroblogList.get(i).getFROMDATA());
						map.put("wbcontext", content);
						//判断微知是否有图片
						String ss=collectionMicroblogList.get(i).getMICROBLOGCONTENTIMG();
						if(ss==null){
							map.put("wbimg", qq);
						}else{
							String jieguo=ss.substring(ss.indexOf("name=")+5, ss.indexOf("height")-4);
							String[] aaa=jieguo.split(",");
							List<Map<Object,Object>> sec = new ArrayList<Map<Object,Object>>();
							for(int j=0;j<aaa.length;j++){
								Map<Object, Object> map1=new HashMap<Object,Object>();
								String path="/file/readfile.action?fileName="+aaa[j];
								map1.put("img0", path);
								sec.add(map1);
							}
							map.put("wbimg", sec);
						}
						map.put("wbcollec", "1");
						//转发状态为0转发内容为空
						map.put("wbReblogStatus", "0");
						map.put("wbReblogName", "");
						map.put("wbReblogContxt", "");
						map.put("wbReblogImg", qq);
					}else{
						microblog=irpMicroBlogService.irpMicroblog(collectionMicroblogList.get(i).getTRANSPONDID());
						wbuser=irpUserService.findUserByUserId(microblog.getUserid());
						map.put("wbuserid", userid);
						map.put("wbblogmsid", collectionMicroblogList.get(i).getMICROBLOGID());
						if(microblog.getIsdel()==2){
							map.put("wbReblogContxt", "抱歉，由于此微知内容不符合规定，无法查看。");
							map.put("wbtimes", "");
							map.put("wbimg", qq);
							map.put("wbReblogName", "");
							map.put("wbReblogImg", qq);
						}else if(microblog.getIsdel()==1){
							map.put("wbReblogContxt", "抱歉，此微知已被作者删除。");
							map.put("wbtimes", "");
							map.put("wbimg", qq);
							map.put("wbReblogName", "");
							map.put("wbReblogImg", qq);
						}else{
							//原微知内容
							String con=microblog.getMicroblogcontent();
							con=con.replaceAll("<((?!>).)*>|<\\/((?!<).)*>", "");
							//原微知作者
							String reblogshowName=null;
							if(wbuser.getNickname()==null){
								reblogshowName=wbuser.getTruename();
								if(reblogshowName==null){
									reblogshowName=wbuser.getUsername();
								}
							}else{
								reblogshowName=wbuser.getNickname();
							}
							map.put("wbtimes", collectionMicroblogList.get(i).getCRTIME());
							map.put("wbReblogContxt", con);
							map.put("wbimg", qq);
							map.put("wbReblogName", reblogshowName);
							String gg=microblog.getMicroblogcontentimg();
							if(gg==null){
								map.put("wbReblogImg", qq);
							}else{
								String jieguo=gg.substring(gg.indexOf("name=")+5, gg.indexOf("height")-4);
								String[] bbb=jieguo.split(",");
								List<Map<Object,Object>> sec = new ArrayList<Map<Object,Object>>();
								for(int j=0;j<bbb.length;j++){
									Map<Object, Object> map1=new HashMap<Object,Object>();
									String path="/file/readfile.action?fileName="+bbb[j];
									map1.put("img0", path);
									sec.add(map1);
								}
								map.put("wbReblogImg", sec);
							}
						}
						map.put("wbhead", sFilePath);
						String showName=null;
						if(wbuser.getNickname()==null){
							showName=wbuser.getTruename();
							if(showName==null){
								showName=wbuser.getUsername();
							}
						}else{
							showName=wbuser.getNickname();
						}
						map.put("wbusername", showName);
						map.put("wbtimes", collectionMicroblogList.get(i).getCRTIME());
						map.put("wbcontext", content);
						map.put("wbcollec", "1");
						map.put("wbReblogStatus", "1");
						map.put("wbsource", collectionMicroblogList.get(i).getFROMDATA());
					}
					list.add(map);
				}
			}
	   		ActionUtil.writer(JSON.toJSONString(list));
	   	}
		private List<IrpTopic> irptopiclist;
		private String optionvaltopic;
		
		public String getOptionvaltopic() {
			return optionvaltopic;
		}

		public void setOptionvaltopic(String optionvaltopic) {
			this.optionvaltopic = optionvaltopic;
		}

		public List<IrpTopic> getIrptopiclist() {
			return irptopiclist;
		}

		public void setIrptopiclist(List<IrpTopic> irptopiclist) {
			this.irptopiclist = irptopiclist;
		}

		/**
		 * 查看我的专题
		 * @return
		 */
		public void topicMineMenu(){
			HttpServletRequest request = ServletActionContext.getRequest();
			String token = request.getParameter("token");
			IrpUser irpuser = mobileAction.getlogin(token);
	   		Long userid = irpuser.getUserid();
	   		if(wbuserid==null||wbuserid==0){
				wbuserid=userid;
			}
	   		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
	   		int countnum=this.irpTopicService.getAllIrpTopicByMineCount(wbuserid,optionvaltopic);
			PageUtil pageUtil = new PageUtil(pageNumber, pageSize, countnum);
			int count=0;
			if(countnum%pageSize==0){
				count=countnum/pageSize;
			}else{
				count=countnum/pageSize+1;
			}
			if(this.pageNumber<=count){
				irptopiclist = this.irpTopicService.getAllIrpTopicByMine(pageUtil,wbuserid,optionvaltopic);
			}
			if(irptopiclist==null){
				
			}else{
				for (int i = 0; i < irptopiclist.size(); i++) {
					Map<Object,Object> map = new HashMap<Object, Object>();
					map.put("contrititle", irptopiclist.get(i).getTopicname());
					map.put("contriname", irptopiclist.get(i).getExpandfield());
					map.put("contritimes", irptopiclist.get(i).getCrtime());
					map.put("contriid", irptopiclist.get(i).getTopicid());
					map.put("contriurl", irptopiclist.get(i).getTopicid());
					list.add(map);
				}
			}
			ActionUtil.writer(JSON.toJSONString(list));
			
		}
		private String wbcontext;
		private int microblogType;
		
		public int getMicroblogType() {
			return microblogType;
		}

		public void setMicroblogType(int microblogType) {
			this.microblogType = microblogType;
		}

		public String getWbcontext() {
			return wbcontext;
		}

		public void setWbcontext(String wbcontext) {
			this.wbcontext = wbcontext;
		}
		//private File picFile;
		
		private String picFileFileName;
		private File[] picFile;
		
		/*public File getPicFile() {
			return picFile;
		}

		public void setPicFile(File picFile) {
			this.picFile = picFile;
		}*/

		public File[] getPicFile() {
			return picFile;
		}

		public void setPicFile(File[] picFile) {
			this.picFile = picFile;
		}

		public String getPicFileFileName() {
			return picFileFileName;
		}

		public void setPicFileFileName(String picFileFileName) {
			this.picFileFileName = picFileFileName;
		}
		/**
		 * 
		 * uploadImage:上传图片. <br/> 
		 * 前端选取图片后，执行此方法，图片的在数据库中的内容。<br/> 
		 * 
		 * @author yijin  
		 * @since JDK 1.8
		 */
		public void uploadImage(){
			HttpServletRequest request = ServletActionContext.getRequest();
		    if(picFileFileName!=null){
                picFileFileName=picFileFileName.replaceAll(" ", "");
                String[] picFileName=picFileFileName.split(",");
                String microblogimg=null;
                String savename=null;
                String sSaveName =null;
                String sPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
                for(int i=0;i<picFileName.length;i++){
                    File tempFile = null;
                    for(File file : picFile){
                        if(file.isFile()){
                            tempFile = file;
                            break;
                        }
                    }
                    String sSaveName1 = SysFileUtil.saveFile1(tempFile,SysFileUtil.FILE_TYPE_TEMP_FILE, FileUtil.findFileExt(picFileName[i]), false, true);
                    String sPicPath = SysFileUtil.getFilePathByFileName(sSaveName1);
                    File imgfile = new File(sPicPath);  
                    sSaveName = SysFileUtil.saveFile1(imgfile, SysFileUtil.FILE_TYPE_DOC_FILE,FileUtil.findFileExt(sSaveName1), false, true);           
                    String disposeiddics =  sSaveName.substring(0, sSaveName.indexOf("."))+i;
                    String url=""+sPath+"/file/readfile.action?fileName="+sSaveName+"";
                    String imgStr ="<img src=\""+url+"\" id="+disposeiddics+" name=\"\"    height=\"150px\" width=\"140px\"  style=\"float:none;margin:3px;cursor:url(<%=rootPath%>view/images/maxpic_03.cur),auto;\" onclick=\"checkMaxPic(this.src,this.name,this.id,1)\" />";
                    System.out.println(imgStr);
                    if(savename==null){
                        savename=sSaveName;
                    }else{
                        savename=savename+","+sSaveName;
                    }
                    if(microblogimg==null){
                        microblogimg=imgStr;
                    }else{
                        microblogimg=microblogimg+imgStr;
                    }
                }
                if(microblogimg!=null){
                    String aaa="name="+savename;
                    String bbb="name=\"\"";
                    microblogimg=microblogimg.replaceAll(bbb, aaa);
                    ActionUtil.writer(microblogimg);
                }
            }
		}
		//用来接收手机端发来的图片元素内容，存入数据库
				private String imageStr;
				
				/**
		         * @return the imageStr
		         */
		        public String getImageStr() {
		            return imageStr;
		        }

		        /**
		         * @param imageStr the imageStr to set
		         */
		        public void setImageStr(String imageStr) {
		            this.imageStr = imageStr;
		        }

		        /**
		         * 
		         * sendWeibo:安卓端发送微知 <br/> 
		         * 
		         * @author yijin  
		         * @since JDK 1.8
		         */
				public void sendWeibo(){
				    IrpMicroblog _irpMicroblog = new IrpMicroblog();
		            int resultstatus=0;
		            HttpServletRequest request = ServletActionContext.getRequest();
		            String token = request.getParameter("token");
		            IrpUser irpuser = mobileAction.getlogin(token);
		            _irpMicroblog.setUserid(irpuser.getUserid());
		            try {
		                wbcontext=URLDecoder.decode(wbcontext,"UTF-8");
		            } catch (UnsupportedEncodingException e) {
		                e.printStackTrace();
		            }
		            _irpMicroblog.setMicroblogcontentimg(imageStr);
		            int _nStatus = this.irpMicroBlogService.addMicroblogByMobile(wbcontext,microblogType, _irpMicroblog);
		            if(_nStatus==1){
		                resultstatus=1000;
		            }else{
		                resultstatus=0;
		            }
		            JSONObject j = JSON.parseObject("{\"resultstatus\":\"" + resultstatus +"\"}");
		            ActionUtil.writer(j.toJSONString());
				}
				
	 private InputStream[] ffss;

		public InputStream[] getFfss() {
		return ffss;
	}

	public void setFfss(InputStream[] ffss) {
		this.ffss = ffss;
	}	
		private String ImageArray;
		

		public String getImageArray() {
			return ImageArray;
		}

		public void setImageArray(String imageArray) {
			ImageArray = imageArray;
		}

		/**
		 * 发表微知
		 * mobile
		 * @return
		 */
		public void microBlogAddMobile(){
			IrpMicroblog _irpMicroblog = new IrpMicroblog();
			int resultstatus=0;
			HttpServletRequest request = ServletActionContext.getRequest();
			String token = request.getParameter("token");
			IrpUser irpuser = mobileAction.getlogin(token);
			_irpMicroblog.setUserid(irpuser.getUserid());
			try {
				wbcontext=URLDecoder.decode(wbcontext,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if(picFileFileName!=null){
				picFileFileName=picFileFileName.replaceAll(" ", "");
				String[] picFileName=picFileFileName.split(",");
				String microblogimg=null;
				String savename=null;
				String sSaveName =null;
				String sPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
				//File parentFile = picFile.getParentFile();
				for(int i=0;i<picFileName.length;i++){
					//File[] files = parentFile.listFiles();
					File tempFile = null;
					for(File file : picFile){
						if(file.isFile()){
							tempFile = file;
							break;
						}
					}
					String sSaveName1 = SysFileUtil.saveFile1(tempFile,SysFileUtil.FILE_TYPE_TEMP_FILE, FileUtil.findFileExt(picFileName[i]), false, true);
					String sPicPath = SysFileUtil.getFilePathByFileName(sSaveName1);
					File imgfile = new File(sPicPath);	
					sSaveName = SysFileUtil.saveFile1(imgfile, SysFileUtil.FILE_TYPE_DOC_FILE,FileUtil.findFileExt(sSaveName1), false, true);			
					String disposeiddics =  sSaveName.substring(0, sSaveName.indexOf("."))+i;
					String url=""+sPath+"/file/readfile.action?fileName="+sSaveName+"";
					String imgStr ="<img src=\""+url+"\" id="+disposeiddics+" name=\"\"    height=\"150px\" width=\"140px\"  style=\"float:none;margin:3px;cursor:url(<%=rootPath%>view/images/maxpic_03.cur),auto;\" onclick=\"checkMaxPic(this.src,this.name,this.id,1)\" />";
					//String imgStr1 ="<img src="+sPath+"file/readfile.action?fileName="+sSaveName+"\" id="+disposeiddics+" name="+picFileName[i]+" height=\"150px\" width=\"140px\" style="float:none;margin:3px;cursor:url(<%=rootPath%>view/images/maxpic_03.cur),auto;"  onclick=\"checkMaxPic(this.src,this.name,this.id,1)\" />"
					if(savename==null){
						savename=sSaveName;
					}else{
						savename=savename+","+sSaveName;
					}
					if(microblogimg==null){
						microblogimg=imgStr;
					}else{
						microblogimg=microblogimg+imgStr;
					}
					/*try {
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}*/
				}
				if(microblogimg!=null){
					String aaa="name="+savename;
					String bbb="name=\"\"";
					microblogimg=microblogimg.replaceAll(bbb, aaa);
					_irpMicroblog.setMicroblogcontentimg(microblogimg);
				}
			}
			int _nStatus = this.irpMicroBlogService.addMicroblogByMobile(wbcontext,microblogType, _irpMicroblog);
			if(_nStatus==1){
	         	resultstatus=1000;
			}else{
				resultstatus=0;
			}
			JSONObject j = JSON.parseObject("{\"resultstatus\":\"" + resultstatus +"\"}");
			ActionUtil.writer(j.toJSONString());
		}
		
		
		/**
		 * iphone增加微知
		 * @return
		 * @throws IOException 
		 */
		public void iMicroPhone() throws IOException{
			System.out.println("init new action.......");
			System.out.println("iphonetoken=="+getIphonetoken());
			System.out.println("iphonecontext=="+iphonecontext);
			System.out.println("iphonefile=="+getIphonefile());
			System.out.println("iphonefilename=="+iphonefilename); 
			
			
		}
		private String iphonetoken;
		private String iphonecontext;	
		private File[] iphonefile; 
		private String iphonefilename;
		
		
		
		public File[] getIphonefile() {
			return iphonefile;
		}

		public void setIphonefile(File[] iphonefile) {
			this.iphonefile = iphonefile;
		}
		 
		public String getIphonefilename() {
			return iphonefilename;
		}

		public void setIphonefilename(String iphonefilename) {
			this.iphonefilename = iphonefilename;
		} 
		public String getIphonetoken() {
			return iphonetoken;
		}

	 
		public void setIphonetoken(String iphonetoken) {
			this.iphonetoken = iphonetoken;
		}

		public String getIphonecontext() {
			return iphonecontext;
		}

		public void setIphonecontext(String iphonecontext) {
			this.iphonecontext = iphonecontext;
		}
		//private String fileTrueName;
		private String fileName;
		private InputStream inputStream;
		
		public InputStream getInputStream() {
			return inputStream;
		}

		public void setInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		/*public String getFileTrueName() {
			return fileTrueName;
		}

		public void setFileTrueName(String fileTrueName) {
			this.fileTrueName = fileTrueName;
		}*/

		/**
		 * 直接输出Stream
		 * @return
		 * @throws FileNotFoundException
		 */
		public String readFile(String fileTrueName) throws FileNotFoundException{
			//获得文件路径
			String sFilePath = SysFileUtil.getFilePathByFileName(fileName);
			File file = new File(sFilePath);
			if(file.isFile() && file.exists()){
				//判断是否传入fileTrueName
				if(fileTrueName!=null && fileTrueName.length()>0){
			        String sAgent = ServletActionContext.getRequest().getHeader("User-Agent");  
			        if (sAgent!=null) {  
			        	sAgent = sAgent.toLowerCase();
			            try {
			            	
							if (sAgent.indexOf("msie") != -1) {
								this.fileName = java.net.URLEncoder.encode(new String(fileTrueName.getBytes("ISO-8859-1"),"UTF-8"),"UTF-8");
								this.fileName = StringUtils.replace(this.fileName, "+", "%20");
							} else if (sAgent.indexOf("firefox") != -1) {
								this.fileName = fileTrueName;
							} else if (sAgent.indexOf("chrome") != -1) {  
								this.fileName = fileTrueName;
							} else if (sAgent.indexOf("safari") != -1) {  
								this.fileName = fileTrueName;
							} 
							fileName = new String(fileName.getBytes(),"UTF-8"); 
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
			        }
				}
				inputStream = new FileInputStream(file);
			}else{
				inputStream = new ByteArrayInputStream("文件不存在！".getBytes());
			}
			return SUCCESS;
		}
		private long personid;
		private IrpTopicLinkService irpTopicLinkService;
		public long getPersonid() {
			return personid;
		}


		public void setPersonid(long personid) {
			this.personid = personid;
		}
		
		public IrpTopicLinkService getIrpTopicLinkService() {
			return irpTopicLinkService;
		}

		public void setIrpTopicLinkService(IrpTopicLinkService irpTopicLinkService) {
			this.irpTopicLinkService = irpTopicLinkService;
		}

		public void isloginuser(){
			int resultstatus=0;
			HttpServletRequest request = ServletActionContext.getRequest();
			String token = request.getParameter("token");
			IrpUser irpuser = mobileAction.getlogin(token);
			Long userid = irpuser.getUserid();
			if(userid==personid){
				resultstatus=0;
			}else{
				resultstatus=1;
			}
			JSONObject j = JSON.parseObject("{\"resultstatus\":\"" + resultstatus +"\"}");
			ActionUtil.writer(j.toJSONString());	
		}
		/**
		 * 删除微知
		 * @return
		 */
		public void deleteMicroBlogIsDel(){
			HttpServletRequest request = ServletActionContext.getRequest();
			String token = request.getParameter("token");
			IrpUser irpuser = mobileAction.getlogin(token);
			int msg =0;
			int resultstatus=0;
			long nMicroblogId = Long.parseLong(wbmsgid);
			//boolean falg = this.irpMicroBlogService.microblogBelongUserid(nMicroblogId);
			msg = this.irpMicroBlogService.deleteMicroblogOfMicroblogid(nMicroblogId);
			if(msg!=0){
				resultstatus=1000;
			}else{
				resultstatus=0;
			}
			//同时删除该微知下所有的评论	  
			this.irpMicroblogCommentService.updateMicroblogCommentMicroblog(nMicroblogId);
			//同时删除该微知所属的专题
			IrpTopicLink irpTopicLink = irpTopicLinkService.getTopicListLink(nMicroblogId);
			if(irpTopicLink!=null){
				this.irpTopicService.updateTopicHotNum(irpTopicLink.getTopicname());
			}
			//删除微知举报的所有信息
			this.irpInformService.deleteInformByMicroblogId(nMicroblogId);
			JSONObject j = JSON.parseObject("{\"resultstatus\":\"" + resultstatus +"\"}");
			ActionUtil.writer(j.toJSONString());
			/*if(falg == true){
			}else{
				JSONObject j = JSON.parseObject("{\"resultstatus\":\"" + resultstatus +"\"}");
				ActionUtil.writer(j.toJSONString());
			}*/
		}
		private Integer informdescnum;
		private List<IrpInformType> irpInformTypelist;
		private IrpInformTypeService irpInformTypeService;
		private String informcontent;
		private String token;
		private Long microblogid;
		
		
		public Long getMicroblogid() {
			return microblogid;
		}

		public void setMicroblogid(Long microblogid) {
			this.microblogid = microblogid;
		}

		public Integer getInformdescnum() {
			return informdescnum;
		}

		public void setInformdescnum(Integer informdescnum) {
			this.informdescnum = informdescnum;
		}

		public List<IrpInformType> getIrpInformTypelist() {
			return irpInformTypelist;
		}

		public void setIrpInformTypelist(List<IrpInformType> irpInformTypelist) {
			this.irpInformTypelist = irpInformTypelist;
		}

		public IrpInformTypeService getIrpInformTypeService() {
			return irpInformTypeService;
		}

		public void setIrpInformTypeService(IrpInformTypeService irpInformTypeService) {
			this.irpInformTypeService = irpInformTypeService;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public String getInformcontent() {
			return informcontent;
		}

		public void setInformcontent(String informcontent) {
			this.informcontent = informcontent;
		}

		/**
	     * 加载举报页面
	     * @return
	     */
	    public String getInformFrame(){
	    	IrpMicroblog microblog=irpMicroBlogService.irpMicroblog(microblogid);
	    	wbcontext=microblog.getMicroblogcontent();
	    	wbcontext=wbcontext.replaceAll("<((?!>).)*>|<\\/((?!<).)*>", "");
	    	if(wbcontext.length()>50){
	    		wbcontext=wbcontext.substring(0, 50)+"...";
	    	}
	    	showname=irpUserService.findShowNameByUserid(microblog.getUserid());
	    	this.informdescnum = SysConfigUtil.getSysConfigNumValue(IrpInform.INFORMDESCNUM);
	    	irpInformTypelist = this.irpInformTypeService.findAllIrpInformType(IrpInformType.REPORT_TYPE);
	    	
	    	
	    	return SUCCESS;
	    }
	    private String informkeykind;
		
		public String getInformkeykind() {
			return informkeykind;
		}

		public void setInformkeykind(String informkeykind) {
			this.informkeykind = informkeykind;
		}
	    /**
	     * 增加举报内容
	     */
	    public void saveInform(){
	    	IrpInform irpInform=new IrpInform();
	    	irpInform.setInformnameid(Long.parseLong(wbmsgid));
	    	irpInform.setInformcontent(informcontent);
	    	int msg =  this.irpInformService.addInform(irpInform,IrpInform.INFORMMICRO,informkeykind);
	    	ActionUtil.writer(msg+"");
	    }
	    
	    private Long votegoupid;
		private String publishInfoimg;
		public Long getVotegoupid() {
			return votegoupid;
		}

		public void setVotegoupid(Long votegoupid) {
			this.votegoupid = votegoupid;
		}

		public String getPublishInfoimg() {
			return publishInfoimg;
		}

		public void setPublishInfoimg(String publishInfoimg) {
			this.publishInfoimg = publishInfoimg;
		}
		/**
		 * 投票发表观点
		 * @return
		 * @author   npz
		 * @date 2017年9月20日
		 */
		public String  microBlogAddpl(){
			HttpServletRequest request = ServletActionContext.getRequest();
			String token = request.getParameter("token");
			IrpUser irpuser = mobileAction.getlogin(token);
		    IrpMicroblog _irpMicroblog = new IrpMicroblog();
		    Long groupid =votegoupid;
		    _irpMicroblog.setGroupid(groupid);
			AtmeUtil au = new AtmeUtil();
		    int nMsg = this.irpMicroBlogService.addMicroBlog(au.getAtmeStr(this.publishInfo),this.microblogType, _irpMicroblog,publishInfoimg);
		    if(nMsg==1){
	          IrpMicroblog irpMicroblogFirct = this.irpMicroBlogService.findFirstMicroblogbytype(irpuser.getUserid(),IrpMicroblog.PINGMICROBLOG);
	          if(irpMicroblogFirct!=null){
	        	  au.disposeATME(publishInfo,irpMicroblogFirct.getMicroblogid());
	           }
		    }
		    if(nMsg==1){
			    try {
				    irpMicrobloglist =  this.irpMicroBlogService.findMicroblogOfFocus(IrpMicroblog.ISDELFALSE, irpuser.getUserid(),TableIdUtil.getNextId(IrpMicroblog.TABLE_NAME)-1);
				 } catch (Exception e) {
					 e.printStackTrace();
				 }
		    }
		     return SUCCESS;
			}
}
