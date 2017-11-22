package com.tfs.irp.microblog.web;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.noggit.JSONParser;
import org.apache.struts2.ServletActionContext;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.group.entity.IrpGroup;
import com.tfs.irp.group.service.IrpGroupService;
import com.tfs.irp.inform.entity.IrpInform;
import com.tfs.irp.inform.service.IrpInformService;
import com.tfs.irp.informtype.entity.IrpInformType;
import com.tfs.irp.informtype.service.IrpInformTypeService;
import com.tfs.irp.medal.entity.IrpMedal;
import com.tfs.irp.medal.service.IrpMedalService;
import com.tfs.irp.messagecontent.entity.IrpMessageContent;
import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.microblog.entity.IrpMicroblogView;
import com.tfs.irp.microblog.service.IrpMicroblogService;
import com.tfs.irp.microblogcollection.service.IrpMicroblogCollectionKeyService;
import com.tfs.irp.microblogcomment.entity.IrpMicroblogComment;
import com.tfs.irp.microblogcomment.service.IrpMicroblogCommentService;
import com.tfs.irp.microblogfocus.service.IrpMicroblogFocusService;
import com.tfs.irp.mobile.web.mobileAction;
import com.tfs.irp.topic.entity.IrpTopic;
import com.tfs.irp.topic.service.IrpTopicService;
import com.tfs.irp.topiclink.entity.IrpTopicLink;
import com.tfs.irp.topiclink.service.IrpTopicLinkService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.usermedal.entity.IrpUserMedal;
import com.tfs.irp.usermedal.service.IrpUserMedalService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.AtmeUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.TableIdUtil;
import com.tfs.irp.util.ThumbnailPic;

public class IrpMicroblogAction extends ActionSupport {

	private IrpMicroblogService irpMicroBlogService;
	private String publishInfo;
	private IrpMicroblog irpMicroblog;
	private List<String> irpMicrobloglist;
	private IrpMicroblogCollectionKeyService irpMicroblogCollectionKeyService;
	private IrpMicroblogFocusService  irpMicroblogFocusService;
	private IrpInformService irpInformService;
	private List<String> collectionOfUseridlist;
	private String microblogid;
	private String loginuserid;
	private String microblogType;
	private String showname;
    private IrpUserService irpUserService;
    private String userid;
	private List  allUseridByFocusUserId;
    private List  allUseridByUserid;
    private String microblogidCard;
    private IrpMicroblogCommentService  irpMicroblogCommentService; 
    private Long personid;
    private int microblognump;
    private int microblogminep;
    private int microblogcollectnump;
    private List<String> mobilemicroblogcomment;
    
    private long micropicblogid;
    
    public IrpInformService getIrpInformService() {
		return irpInformService;
	}
	public void setIrpInformService(IrpInformService irpInformService) {
		this.irpInformService = irpInformService;
	}
	public long getMicropicblogid() {
		return micropicblogid;
	}
	public void setMicropicblogid(long micropicblogid) {
		this.micropicblogid = micropicblogid;
	}
	private int boolcid;
   
	public int getBoolcid() {
		return boolcid;
	}
	public void setBoolcid(int boolcid) {
		this.boolcid = boolcid;
	}
	public List<String> getMobilemicroblogcomment() {
		return mobilemicroblogcomment;
	}
	public void setMobilemicroblogcomment(List<String> mobilemicroblogcomment) {
		this.mobilemicroblogcomment = mobilemicroblogcomment;
	}
	public int getMicroblogcollectnump() {
		return microblogcollectnump;
	}
	public void setMicroblogcollectnump(int microblogcollectnump) {
		this.microblogcollectnump = microblogcollectnump;
	}
	public int getMicroblogminep() {
		return microblogminep;
	}
	public void setMicroblogminep(int microblogminep) {
		this.microblogminep = microblogminep;
	}
	//得到登陆人的信息列表
    private List loginUserList;
    private Integer messagecount;
    private String picfile;
    private IrpGroupService irpGroupService;
    private Long selectuser;
    private String selectgroups;
	//推荐关注
    private List RecommentList;
    private String topicname;
    
    private List<IrpMicroblog> topicmicroblog;
    private IrpTopicService irpTopicService;
    
    

    
    private IrpTopicLinkService irpTopicLinkService;
    private IrpInformTypeService irpInformTypeService;
    private String picfilenamelist;
    private String originalpic;
	private List<IrpInformType> irpInformType;
	private String infomsorttypekey;
	private String microbloggroup;
    private String skiptab;
    //Mobile
    private  List<String>  irpMicrobloglistmine;
    private  List<String>  collectionOfUseridlistmine;
    private  String pageHTMLmine = "";
    private Long mobilemicroblogid;
    private Long mobileuserid;
    private int micromobilecommentsize;
    private int mobilecommpnums;
    private long mobileloginuserid;
    private String trueid;
    private String publishInfoimg;
    public String getPublishInfoimg() {
		return publishInfoimg;
	}
	public void setPublishInfoimg(String publishInfoimg) {
		this.publishInfoimg = publishInfoimg;
	}
	public String getTrueid() {
		return trueid;
	}
	public void setTrueid(String trueid) {
		this.trueid = trueid;
	}
	public long getMobileloginuserid() {
		return mobileloginuserid;
	}
	public void setMobileloginuserid(long mobileloginuserid) {
		this.mobileloginuserid = mobileloginuserid;
	}
	public int getMobilecommpnums() {
		return mobilecommpnums;
	}
	public void setMobilecommpnums(int mobilecommpnums) {
		this.mobilecommpnums = mobilecommpnums;
	}
	public int getMicromobilecommentsize() {
		return micromobilecommentsize;
	}
	public void setMicromobilecommentsize(int micromobilecommentsize) {
		this.micromobilecommentsize = micromobilecommentsize;
	}
	public Long getMobileuserid() {
		return mobileuserid;
	}
	public void setMobileuserid(Long mobileuserid) {
		this.mobileuserid = mobileuserid;
	}
	public Long getMobilemicroblogid() {
		return mobilemicroblogid;
	}
	public void setMobilemicroblogid(Long mobilemicroblogid) {
		this.mobilemicroblogid = mobilemicroblogid;
	}
	public List<String> getIrpMicrobloglistmine() {
		return irpMicrobloglistmine;
	}
	public void setIrpMicrobloglistmine(List<String> irpMicrobloglistmine) {
		this.irpMicrobloglistmine = irpMicrobloglistmine;
	}
	public List<String> getCollectionOfUseridlistmine() {
		return collectionOfUseridlistmine;
	}
	public void setCollectionOfUseridlistmine(
			List<String> collectionOfUseridlistmine) {
		this.collectionOfUseridlistmine = collectionOfUseridlistmine;
	}
	public String getPageHTMLmine() {
		return pageHTMLmine;
	}
	public void setPageHTMLmine(String pageHTMLmine) {
		this.pageHTMLmine = pageHTMLmine;
	}
	public String getSkiptab() {
		return skiptab;
	}
	public void setSkiptab(String skiptab) {
		this.skiptab = skiptab;
	}
	 public int getMicroblognump() {
			return microblognump;
		}
		public void setMicroblognump(int microblognump) {
			this.microblognump = microblognump;
		}
	
	public String getMicrobloggroup() {
		return microbloggroup;
	}
	public void setMicrobloggroup(String microbloggroup) {
		this.microbloggroup = microbloggroup;
	}
	public List<IrpInformType> getIrpInformType() {
		return irpInformType;
	}
	public String getInfomsorttypekey() {
		return infomsorttypekey;
	}
	public void setInfomsorttypekey(String infomsorttypekey) {
		this.infomsorttypekey = infomsorttypekey;
	}
	public void setIrpInformType(List<IrpInformType> irpInformType) {
		this.irpInformType = irpInformType;
	}
    private Long microblogidill;
    public Long getMicroblogidill() {
		return microblogidill;
	}
	public IrpMicroblogFocusService getIrpMicroblogFocusService() {
		return irpMicroblogFocusService;
	}
	public void setIrpMicroblogFocusService(
			IrpMicroblogFocusService irpMicroblogFocusService) {
		this.irpMicroblogFocusService = irpMicroblogFocusService;
	}
	public void setMicroblogidill(Long microblogidill) {
		this.microblogidill = microblogidill;
	}
	public String getOriginalpic() {
 	return originalpic;
    }
    public void setOriginalpic(String originalpic) {
 	this.originalpic = originalpic;
     }
    
    public String getPicfilenamelist() {
 	return picfilenamelist;
    }
    public void setPicfilenamelist(String picfilenamelist) {
 	this.picfilenamelist = picfilenamelist;
    }
    private String[] picfilenamearray;
    public String[] getPicfilenamearray() {
 	return picfilenamearray;
    }
    public void setPicfilenamearray(String[] picfilenamearray) {
 	this.picfilenamearray = picfilenamearray;
    }
    private String picfileids;
    public String getPicfileids() {
 	return picfileids;
    }
    public void setPicfileids(String picfileids) {
 	 this.picfileids = picfileids;
    }
	public IrpInformTypeService getIrpInformTypeService() {
		return irpInformTypeService;
	}
	public void setIrpInformTypeService(IrpInformTypeService irpInformTypeService) {
		this.irpInformTypeService = irpInformTypeService;
	}
	public IrpTopicLinkService getIrpTopicLinkService() {
		return irpTopicLinkService;
	}
	public void setIrpTopicLinkService(IrpTopicLinkService irpTopicLinkService) {
		this.irpTopicLinkService = irpTopicLinkService;
	}
	public IrpTopicService getIrpTopicService() {
		return irpTopicService;
	}
	public void setIrpTopicService(IrpTopicService irpTopicService) {
		this.irpTopicService = irpTopicService;
	}
	public List<IrpMicroblog> getTopicmicroblog() {
		return topicmicroblog;
	}
	public void setTopicmicroblog(List<IrpMicroblog> topicmicroblog) {
		this.topicmicroblog = topicmicroblog;
	}
	public String getTopicname() {
		return topicname;
	}
	public void setTopicname(String topicname) {
		this.topicname = topicname;
	}
	public List getRecommentList() {
		return RecommentList;
	}
	public void setRecommentList(List recommentList) {
		RecommentList = recommentList;
	}

	public Long getSelectuser() {
		return selectuser;
	}
	public void setSelectuser(Long selectuser) {
		this.selectuser = selectuser;
	}
	public String getSelectgroups() {
		return selectgroups;
	}
	public void setSelectgroups(String selectgroups) {
		this.selectgroups = selectgroups;
	}
	
    

	public IrpGroupService getIrpGroupService() {
		return irpGroupService;
	}
	public void setIrpGroupService(IrpGroupService irpGroupService) {
		this.irpGroupService = irpGroupService;
	}
	public String getPicfile() {
		return picfile;
	}
	public void setPicfile(String picfile) {
		this.picfile = picfile;
	}
	public Integer getMessagecount() {
		return messagecount;
	}
	public void setMessagecount(Integer messagecount) {
		this.messagecount = messagecount;
	}
	public List getLoginUserList() {
		return loginUserList;
	}
	public void setLoginUserList(List loginUserList) {
		this.loginUserList = loginUserList;
	}
	/*
     *分页
     */
	private String pageHTML = "";
	private int pageNum = 1;
	private int pageSize = 15;
	private int pagemobilenum = 1;
	private int pageminemobilenum = 1;
	private int pagecollectnum = 1;

    
	public int getPagecollectnum() {
		return pagecollectnum;
	}
	public void setPagecollectnum(int pagecollectnum) {
		this.pagecollectnum = pagecollectnum;
	}
	public int getPageminemobilenum() {
		return pageminemobilenum;
	}
	public void setPageminemobilenum(int pageminemobilenum) {
		this.pageminemobilenum = pageminemobilenum;
	}
	public int getPagemobilenum() {
		return pagemobilenum;
	}
	public void setPagemobilenum(int pagemobilenum) {
		this.pagemobilenum = pagemobilenum;
	}
	public Long getPersonid() {
		return personid;
	}
	public void setPersonid(Long personid) {
		this.personid = personid;
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
	public IrpMicroblogCommentService getIrpMicroblogCommentService() {
		return irpMicroblogCommentService;
	}
	
	public void setIrpMicroblogCommentService(
			IrpMicroblogCommentService irpMicroblogCommentService) {
		this.irpMicroblogCommentService = irpMicroblogCommentService;
	}
	public String getMicroblogidCard() {
		return microblogidCard;
	}
	public void setMicroblogidCard(String microblogidCard) {
		this.microblogidCard = microblogidCard;
	}
	public List getAllUseridByUserid() {
		return allUseridByUserid;
	}
	public void setAllUseridByUserid(List allUseridByUserid) {
		this.allUseridByUserid = allUseridByUserid;
	}
	public List getAllUseridByFocusUserId() {
		return allUseridByFocusUserId;
	}
	public void setAllUseridByFocusUserId(List allUseridByFocusUserId) {
		this.allUseridByFocusUserId = allUseridByFocusUserId;
	}
	private String micrtranspondid;
	
	public String getMicrtranspondid() {
		return micrtranspondid;
	}
	public void setMicrtranspondid(String micrtranspondid) {
		this.micrtranspondid = micrtranspondid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public IrpUserService getIrpUserService() {
		return irpUserService;
	}
	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}
	public String getShowname() {
		return showname;
	}
	public void setShowname(String showname) {
		this.showname = showname;
	}
	public String getMicroblogType() {
		return microblogType;
	}
	public void setMicroblogType(String microblogType) {
		this.microblogType = microblogType;
	}
	public String getLoginuserid() {
		return loginuserid;
	}
	public void setLoginuserid(String loginuserid) {
		this.loginuserid = loginuserid;
	}
	public String getMicroblogid() {
		return microblogid;
	}
	public void setMicroblogid(String microblogid) {
		this.microblogid = microblogid;
	}

	public List<String> getIrpMicrobloglist() {
		return irpMicrobloglist;
	}
	public void setIrpMicrobloglist(List<String> irpMicrobloglist) {
		this.irpMicrobloglist = irpMicrobloglist;
	}
	public List<String> getCollectionOfUseridlist() {
		return collectionOfUseridlist;
	}
	public void setCollectionOfUseridlist(List<String> collectionOfUseridlist) {
		this.collectionOfUseridlist = collectionOfUseridlist;
	}

	public IrpMicroblog getIrpMicroblog() {
		return irpMicroblog;
	}

	public IrpMicroblogCollectionKeyService getIrpMicroblogCollectionKeyService() {
		return irpMicroblogCollectionKeyService;
	}
	public void setIrpMicroblogCollectionKeyService(
			IrpMicroblogCollectionKeyService irpMicroblogCollectionKeyService) {
		this.irpMicroblogCollectionKeyService = irpMicroblogCollectionKeyService;
	}
	public void setIrpMicroblog(IrpMicroblog irpMicroblog) {
		this.irpMicroblog = irpMicroblog;
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
	private Long votegoupid;
	private List<IrpUser> irpUsers;
	
	public List<IrpUser> getIrpUsers() {
		return irpUsers;
	}
	public void setIrpUsers(List<IrpUser> irpUsers) {
		this.irpUsers = irpUsers;
	}
	public Long getVotegoupid() {
		return votegoupid;
	}
	public void setVotegoupid(Long votegoupid) {
		this.votegoupid = votegoupid;
	}
	/**
	 * 增加微知(发布,分享)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String  microBlogAdd(){
		  IrpMicroblog _irpMicroblog = new IrpMicroblog();
			if (Integer.parseInt(this.microblogType)==2) {
				  Long groupid =	this.irpGroupService.findGroupIdByGroup(microbloggroup, LoginUtil.getLoginUserId(), IrpGroup.GROUP_TYPE_PRIVATE);
				  _irpMicroblog.setGroupid(groupid);
			}
		AtmeUtil au = new AtmeUtil();
		
	   int nMsg = this.irpMicroBlogService.addMicroBlog(au.getAtmeStr(this.publishInfo),Integer.parseInt(this.microblogType), _irpMicroblog,publishInfoimg);
	   
	   if(nMsg==1){
          IrpMicroblog irpMicroblogFirct = this.irpMicroBlogService.findFirstMicroblog(LoginUtil.getLoginUserId());
          if(irpMicroblogFirct!=null){
        	  au.disposeATME(publishInfo,irpMicroblogFirct.getMicroblogid());   
          }
	   }
	   if(nMsg==1){
		   try {
			   
		irpMicrobloglist =  this.irpMicroBlogService.findMicroblogOfFocus(IrpMicroblog.ISDELFALSE, LoginUtil.getLoginUserId(),TableIdUtil.getNextId(IrpMicroblog.TABLE_NAME)-1);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   return SUCCESS;
	}
	/**
	 * 发表微知
	 * mobile
	 * @return
	 */
	public void microBlogAddMobile(){
	  IrpMicroblog _irpMicroblog = new IrpMicroblog();
		if (Integer.parseInt(this.microblogType)==2) {
			  Long groupid =  this.irpGroupService.findGroupIdByGroup(microbloggroup, LoginUtil.getLoginUserId(), IrpGroup.GROUP_TYPE_PRIVATE);
			  _irpMicroblog.setGroupid(groupid);
		}
		AtmeUtil au = new AtmeUtil();
		
	   int nMsg = this.irpMicroBlogService.addMicroblogByMobile(au.getAtmeStr(this.publishInfo),Integer.parseInt(this.microblogType), _irpMicroblog);
	   ActionUtil.writer(nMsg+"");
	}
	/**
	 * 判断页面用户id
	 * mobile
	 * @return
	 */
	public void boolMobileUserid(){
		int msg = 0;
		if(LoginUtil.getLoginUserId()==mobileloginuserid ){
			msg = 1;
		}else{
			msg = 2;
		}
		ActionUtil.writer(msg+"");
	}
	
	/**
	 * 逻辑删除微知信息
	 * @return
	 */
	public void deleteMicroBlogIsDel(){
		int msg =0;
		long nMicroblogId = Long.parseLong(microblogid);
		boolean falg = this.irpMicroBlogService.microblogBelongUserid(nMicroblogId);
		if(falg == true){
			msg = this.irpMicroBlogService.deleteMicroblogOfMicroblogid(nMicroblogId);
			//同时删除该微知下所有的评论	  
			this.irpMicroblogCommentService.updateMicroblogCommentMicroblog(nMicroblogId);
			//同时删除该微知所属的专题
			IrpTopicLink irpTopicLink = irpTopicLinkService.getTopicListLink(nMicroblogId);
			if(irpTopicLink!=null){
				this.irpTopicService.updateTopicHotNum(irpTopicLink.getTopicname());
			}
			//删除微知举报的所有信息
			this.irpInformService.deleteInformByMicroblogId(nMicroblogId);
			ActionUtil.writer(""+msg);	
		}else{
			ActionUtil.writer(""+msg);
		}
	}
	/**
	 * 构建转发页面
	 * @return
	 */
	public String findTranspondPage(){
 
		
		return SUCCESS;
	}
	/**
	 * 增加转发信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String addMicroblogBolgTranspond(){
		int msg = this.irpMicroBlogService.addMicroBlogTranSpond(irpMicroblog);
		AtmeUtil au = new AtmeUtil();
		//处理@
         if (msg==1) {
        
        	 IrpMicroblog irpMicroblogFirct = this.irpMicroBlogService.findFirstMicroblog(LoginUtil.getLoginUserId());
        	 au.disposeATME(irpMicroblog.getMicroblogcontent(),irpMicroblogFirct.getMicroblogid());	
		}
		if(msg==1){
			this.irpMicroBlogService.updateMicroblogTranspondByMicroblogid(Long.parseLong(microblogid));
		}	
		try {
			irpMicrobloglist =  this.irpMicroBlogService.findMicroblogOfFocus(IrpMicroblog.ISDELFALSE, LoginUtil.getLoginUserId(),TableIdUtil.getNextId(IrpMicroblog.TABLE_NAME)-1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 增加转发微知
	 * mobile
	 * @returen
	 */
	public void addMicroblogBolgMobileTranspond(){
		int msg = this.irpMicroBlogService.addMicroBlogTranSpond(irpMicroblog);
		if(msg==1){
			this.irpMicroBlogService.updateMicroblogTranspondByMicroblogid(Long.parseLong(microblogid));
		}	
		ActionUtil.writer(msg+"");
		
	}
	/**
	 * 获得图片
	 * @param _docid
	 * @return
	 */
	public String coverPath(String attachedids){
		String filePath="";
		String[] _attachedid=attachedids.split(",");
		for(int j=0;j<_attachedid.length;j++){
			Long attachedid=Long.parseLong(_attachedid[j]);
			IrpAttached attached=irpAttachedService.getAttachedByPrimary(attachedid); 
			if(attached!=null){
				String myFileName=attached.getAttfile(); 
				//获得文件路径 
				filePath= ServletActionContext.getRequest().getContextPath() + "/file/readfile.action?fileName="+ThumbnailPic.searchFileName(myFileName, "_300X300");
			}else{
				filePath = ServletActionContext.getRequest().getContextPath()+"/view/images/rand/rand1.jpg";
			} 
		}
		return filePath;
	}
	private IrpAttachedService irpAttachedService;
	public IrpAttachedService getIrpAttachedService() {
		return irpAttachedService;
	}
	public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
		this.irpAttachedService = irpAttachedService;
	}
	private List<IrpUserMedal> listusermedal;
	private IrpUserMedalService irpUserMedalService;
	private IrpMedalService irpMedalService;
	private List<IrpMedal> listmedal;
	public List<IrpUserMedal> getListusermedal() {
		return listusermedal;
	}
	public void setListusermedal(List<IrpUserMedal> listusermedal) {
		this.listusermedal = listusermedal;
	}
	public IrpUserMedalService getIrpUserMedalService() {
		return irpUserMedalService;
	}
	public void setIrpUserMedalService(IrpUserMedalService irpUserMedalService) {
		this.irpUserMedalService = irpUserMedalService;
	}
	public IrpMedalService getIrpMedalService() {
		return irpMedalService;
	}
	public void setIrpMedalService(IrpMedalService irpMedalService) {
		this.irpMedalService = irpMedalService;
	}
	public List<IrpMedal> getListmedal() {
		return listmedal;
	}
	public void setListmedal(List<IrpMedal> listmedal) {
		this.listmedal = listmedal;
	}
	/**
	 * 微知个人卡片
	 */
	@SuppressWarnings("unchecked")
	public String findMicroblogPersonalCard(){
	this.irpMicrobloglist=this.irpMicroBlogService.findMicroblogOfUseridInCard(Long.valueOf(userid),IrpMicroblog.ISDELFALSE);
	microblogidCard=microblogid;	
	 List listArraylist =new ArrayList();
	 List listHashMaplist  = this.irpMicroblogFocusService.selectUseridByFocuserId(LoginUtil.getLoginUserId());
	  for(int i =0;i<listHashMaplist.size();i++){
		  Map map = (Map) listHashMaplist.get(i);
		  listArraylist.add(map.values().toString().replace("[","").replace("]",""));
	  } 
	  this.allUseridByFocusUserId= listArraylist;
	  this.loginuserid=""+LoginUtil.getLoginUserId();
		messagecount = SysConfigUtil.getSysConfigNumValue(IrpMessageContent.MESSAGEFONTCOUNT);
	  //被关注的用户关注了谁
	  List listArraylistUserid =new ArrayList();
	  List listHashMaplistUserid  = this.irpMicroblogFocusService.selectUseridByFocuserId(Long.parseLong(userid));
	  for(int i =0;i<listHashMaplistUserid.size();i++){
		  Map map = (Map) listHashMaplistUserid.get(i);
		  listArraylistUserid.add(map.values().toString().replace("[","").replace("]",""));
	  } 
	  allUseridByUserid=listArraylistUserid;
	  
		//查询该用户所有勋章
		IrpUserMedal userMedal=null;
		IrpMedal medal=null;
		listusermedal=irpUserMedalService.getUserMedalByUserid(null, Long.valueOf(userid));
		listmedal=new ArrayList<IrpMedal>();
		List<Long> list=new ArrayList<Long>();
		for(int i=0;i<listusermedal.size();i++){
			userMedal=listusermedal.get(i);
			list.add(userMedal.getMedalid());
		}
		if(list.size()>0){
			listmedal=irpMedalService.findMedalByMedalidList(list);
			if(listmedal!=null&&listmedal.size()>0){
				for (IrpMedal m : listmedal) {
					int count=0;
					for (IrpUserMedal u : listusermedal) {
						if((u.getMedalid()+"").equals(m.getMedalid()+"")){
							count++;
						}
					}
					m.setUserCount(count);
				}
			}
		}
	  
		return SUCCESS;
	}
	
	//ajax加载微知列表
	public String microblogAllFocus() throws Exception{
		Long nUserid = LoginUtil.getLoginUserId();
		loginuserid = nUserid.toString();

			//获取微知长度	
		  this.setPageSize(Integer.parseInt(this.irpMicroBlogService.findIrpConfigCvalue(IrpMicroblog.IRPMICROLOGVIEWPIECE)));
			PageUtil pageUtil = new PageUtil(this.pageNum,this.pageSize,
					this.irpMicroBlogService.findMicroblogOfFocusCount(IrpMicroblog.ISDELFALSE, nUserid));
			irpMicrobloglist= this.irpMicroBlogService.findMicroblogOfFocus(IrpMicroblog.ISDELFALSE, nUserid,pageUtil,LoginUtil.getLoginUserId());
			this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");

		
			collectionOfUseridlist=	this.irpMicroblogCollectionKeyService.selectMicroblogidOfUserid(nUserid);
	
		return SUCCESS;
	}
	//加载微知列表 mobile
		public void microblogAllFocusMobile() throws Exception{

			Long nUserid = LoginUtil.getLoginUserId();
			loginuserid = nUserid.toString();
				//获取微知长度	
			  microblognump = this.irpMicroBlogService.findMicroblogOfFocusCount(IrpMicroblog.ISDELFALSE, nUserid);
			  
				PageUtil pageUtil = new PageUtil(this.pagemobilenum,10,
						microblognump);
				List<IrpMicroblogView> list= this.irpMicroBlogService.findMicroblogOfFocus(IrpMicroblog.ISDELFALSE, nUserid,pageUtil,LoginUtil.getLoginUserId());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				
				List disposelist = new ArrayList();	
				for (int i = 0; i < list.size(); i++) {
					IrpMicroblogView imv = list.get(i);
					disposelist.add("{\"name\":\""+imv.getSHOWNAME()+"\"");
					disposelist.add("\"userpic\":\""+imv.getUSERPIC()+"\"");
					disposelist.add("\"sex\":\""+imv.getSEX()+"\"");	
					disposelist.add("\"crtime\":\""+sdf.format(imv.getCRTIME())+"\"");
					disposelist.add("\"fromdata\":\""+imv.getFROMDATA()+"\"");
					disposelist.add("\"microblogid\":\""+imv.getMICROBLOGID().toString()+"\"");
					disposelist.add("\"userid\":\""+imv.getUSERID().toString()+"\"");
					disposelist.add("\"content\":\""+getMicroblogContent(imv.getMICROBLOGCONTENT()).replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");
					
					
					disposelist.add("\"commentcount\":\""+imv.getCOMMENTCOUNT()+"\"");
					disposelist.add("\"trancount\":\""+imv.getTRANSPONDCOUNT()+"\"");
					long tranid = imv.getTRANSPONDID();
					if(tranid!=0){
						IrpMicroblog imb = this.irpMicroBlogService.irpMicroblog(tranid);
						disposelist.add("\"tran_name\":\""+getShowPageViewNameByUserId(imb.getUserid())+"\"");
						disposelist.add("\"tran_content\":\""+getMicroblogContent(imb.getMicroblogcontent()).replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");
						disposelist.add("\"tran_crtime\":\""+sdf.format(imb.getCrtime())+"\"");
						disposelist.add("\"tran_fromdata\":\""+imb.getFromdata()+"\"");
					}
					disposelist.add("\"transpondid\":\""+tranid+"\"}");
				}
				
				collectionOfUseridlist=	this.irpMicroblogCollectionKeyService.selectMicroblogidOfUserid(nUserid);
				disposelist.add("{\"collectmine\":\""+collectionOfUseridlist.toString()+"\"");
				disposelist.add("\"microblognump\":\""+microblognump+"\"}");
				ActionUtil.writer(disposelist.toString());
				
				
		}
		//加载微知列表 自己的 mobile
	public void microblogMineFocusMobile(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List disposelist = new ArrayList();
		try {
			microblogminep =this.irpMicroBlogService.coutnMicroblogOfUserid(LoginUtil.getLoginUserId(),IrpMicroblog.ISDELFALSE);
			PageUtil pageUtil = new PageUtil(this.pageminemobilenum,10,microblogminep);
			List<IrpMicroblog> list =	this.irpMicroBlogService.findMicroblogListByUserid(LoginUtil.getLoginUserId(),IrpMicroblog.ISDELFALSE,pageUtil);
			for (int i = 0; i < list.size(); i++) {
				IrpMicroblog imv = list.get(i);
				disposelist.add("{\"name\":\""+getShowPageViewNameByUserId(imv.getUserid())+"\"");
				disposelist.add("\"userpic\":\""+findUserByUserId(imv.getUserid()).getUserpic()+"\"");
				disposelist.add("\"sex\":\""+findUserByUserId(imv.getUserid()).getSex()+"\"");	
				disposelist.add("\"crtime\":\""+sdf.format(imv.getCrtime())+"\"");
				disposelist.add("\"fromdata\":\""+imv.getFromdata()+"\"");
				disposelist.add("\"microblogid\":\""+imv.getMicroblogid().toString()+"\"");
				disposelist.add("\"userid\":\""+imv.getUserid().toString()+"\"");
				disposelist.add("\"content\":\""+getMicroblogContent(imv.getMicroblogcontent()).replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");
				disposelist.add("\"commentcount\":\""+imv.getCommentcount()+"\"");
				disposelist.add("\"trancount\":\""+imv.getTranspondcount()+"\"");
				long tranid = imv.getTranspondid();
				if(tranid!=0){
					IrpMicroblog imb = this.irpMicroBlogService.irpMicroblog(tranid);
					disposelist.add("\"tran_name\":\""+getShowPageViewNameByUserId(imb.getUserid())+"\"");
					disposelist.add("\"tran_content\":\""+getMicroblogContent(imb.getMicroblogcontent()).replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");
					disposelist.add("\"tran_crtime\":\""+sdf.format(imb.getCrtime())+"\"");
					disposelist.add("\"tran_fromdata\":\""+imb.getFromdata()+"\"");
				}
				disposelist.add("\"transpondid\":\""+tranid+"\"}");
			}
			
			
		    collectionOfUseridlistmine=	this.irpMicroblogCollectionKeyService.selectMicroblogidOfUserid(personid);
			disposelist.add("{\"collectmine\":\""+collectionOfUseridlistmine.toString()+"\"");
			disposelist.add("\"microblognump\":\""+microblogminep+"\"}");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		ActionUtil.writer(disposelist.toString());
	}
	
	/**
	 * 进入微知管理页面
	 * @return
	 */
	public String microblogAllMagager(){
		Long nUserid = LoginUtil.getLoginUserId();
		loginuserid = nUserid.toString();
		try {
			//获取微知长度	
		  this.setPageSize(Integer.parseInt(this.irpMicroBlogService.findIrpConfigCvalue(IrpMicroblog.IRPMICROLOGVIEWPIECE)));
			PageUtil pageUtil = new PageUtil(this.pageNum,this.pageSize,
					this.irpMicroBlogService.findMicroblogOfAllCount(IrpMicroblog.ISDELFALSE));
			irpMicrobloglist= this.irpMicroBlogService.findMicroblogOfAll(IrpMicroblog.ISDELFALSE,pageUtil,LoginUtil.getLoginUserId());
			this.pageHTML = pageUtil.getClientPageHtml("findMicroblogConn(#PageNum#)");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			collectionOfUseridlist=	this.irpMicroblogCollectionKeyService.selectMicroblogidOfUserid(nUserid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		irpInformType = this.irpInformTypeService.findAllIrpInformType(IrpInformType.REPORT_TYPE);
		
		return SUCCESS;
	}
	
	public String microblogAllMagager_app(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String token = request.getParameter("token");
		IrpUser irpuser = mobileAction.getlogin(token);
		Long nUserid = irpuser.getUserid();
		userid = nUserid.toString();
		try {
			//获取微知长度	
		  this.setPageSize(Integer.parseInt(this.irpMicroBlogService.findIrpConfigCvalue(IrpMicroblog.IRPMICROLOGVIEWPIECE)));
			PageUtil pageUtil = new PageUtil(this.pageNum,this.pageSize,
					this.irpMicroBlogService.findMicroblogOfAllCount(IrpMicroblog.ISDELFALSE));
			irpMicrobloglist= this.irpMicroBlogService.findMicroblogOfAll(IrpMicroblog.ISDELFALSE,pageUtil,LoginUtil.getLoginUserId());
			this.pageHTML = pageUtil.getClientPageHtml("findMicroblogConn(#PageNum#)");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			collectionOfUseridlist=	this.irpMicroblogCollectionKeyService.selectMicroblogidOfUserid(nUserid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		irpInformType = this.irpInformTypeService.findAllIrpInformType(IrpInformType.REPORT_TYPE);
		
		return SUCCESS;
	}
	/**
	 * 管理删除
	 * @return
	 */
	public String microblogDeleteMagager(){
		Long nUserid = LoginUtil.getLoginUserId();
		loginuserid = nUserid.toString();
		try {
			//获取微知长度	
		  this.setPageSize(Integer.parseInt(this.irpMicroBlogService.findIrpConfigCvalue(IrpMicroblog.IRPMICROLOGVIEWPIECE)));
			PageUtil pageUtil = new PageUtil(this.pageNum,this.pageSize,
					this.irpMicroBlogService.findMicroblogOfAllCount(IrpMicroblog.ISDELTRUE));
			irpMicrobloglist= this.irpMicroBlogService.findMicroblogOfAll(IrpMicroblog.ISDELTRUE,pageUtil,LoginUtil.getLoginUserId());
			this.pageHTML = pageUtil.getClientPageHtml("findMicroblogConn(#PageNum#)");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 管理举报(举报)
	 * @return
	 */

	public String microblogReportMagager(){
		Long nUserid = LoginUtil.getLoginUserId();
		loginuserid = nUserid.toString();
		  this.setPageSize(Integer.parseInt(this.irpMicroBlogService.findIrpConfigCvalue(IrpMicroblog.IRPMICROLOGVIEWPIECE)));
			PageUtil pageUtil = new PageUtil(this.pageNum,this.pageSize,
					this.irpMicroBlogService.findMicroblogOfInformCount(IrpMicroblog.ISDELFALSE,IrpInform.INFORMMICRO,IrpInform.INFORM_STATUS,infomsorttypekey));
			irpMicrobloglist= this.irpMicroBlogService.findMicroblogOfInform(IrpMicroblog.ISDELFALSE,IrpInform.INFORMMICRO,IrpInform.INFORM_STATUS,pageUtil,infomsorttypekey);
			this.pageHTML = pageUtil.getClientPageHtml("findMicroblogConn(#PageNum#)");
			//获得所有的举报类型
		irpInformType = this.irpInformTypeService.findAllIrpInformType(IrpInformType.REPORT_TYPE);
	
		return SUCCESS;
	}
	/**
	 * 管理举报(非法微知)
	 * @return
	 */
	public String microblogIllegalMagager(){
		Long nUserid = LoginUtil.getLoginUserId();
		loginuserid = nUserid.toString();
			//获取微知长度	
		  this.setPageSize(Integer.parseInt(this.irpMicroBlogService.findIrpConfigCvalue(IrpMicroblog.IRPMICROLOGVIEWPIECE)));
			PageUtil pageUtil = new PageUtil(this.pageNum,this.pageSize,
					this.irpMicroBlogService.findMicroblogOfInformCount(IrpMicroblog.ISDELINFORM,IrpInform.INFORMMICRO,IrpInform.INFORM_STATUSDELETE_ILLEGALITY,infomsorttypekey));
			irpMicrobloglist= this.irpMicroBlogService.findMicroblogOfInform(IrpMicroblog.ISDELINFORM,IrpInform.INFORMMICRO,IrpInform.INFORM_STATUSDELETE_ILLEGALITY,pageUtil,infomsorttypekey);
			this.pageHTML = pageUtil.getClientPageHtml("findMicroblogConn(#PageNum#)");
		return SUCCESS;
	}
	/**
	 * 得到微知个人列表
	 * @return
	 */
	public String findMicroblogByUserid(){
		//获取微知长度	

		
		try {
			Long nUserid=LoginUtil.getLoginUserId();
			loginuserid = nUserid.toString();//给loginuserid赋值 2017-8-1
			if(personid==LoginUtil.getLoginUserId()){
				PageUtil pageUtil = new PageUtil(this.pageNum,this.pageSize,
						this.irpMicroBlogService.coutnMicroblogOfUserid(personid,IrpMicroblog.ISDELFALSE));
			     irpMicrobloglist =	this.irpMicroBlogService.findMicroblogListByUserid(personid,IrpMicroblog.ISDELFALSE,pageUtil);
			     this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
			}else{
				PageUtil pageUtil = new PageUtil(this.pageNum,this.pageSize,
						this.irpMicroBlogService.coutnMicroblogOfUserid(personid,IrpMicroblog.ISDELFALSE,IrpMicroblog.PUBLICMICROBLOG));
			     irpMicrobloglist =	this.irpMicroBlogService.findMicroblogListByUserid(personid,IrpMicroblog.ISDELFALSE,pageUtil,IrpMicroblog.PUBLICMICROBLOG);
			     this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
			}
		    collectionOfUseridlist=	this.irpMicroblogCollectionKeyService.selectMicroblogidOfUserid(personid);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return SUCCESS;
	}
	/**
	 * 通过id找出相对应的个人信息
	 * @return
	 */
	public IrpUser findUserByUserId(long _userid){
	  IrpUser irpUser = null;
	  irpUser =  this.irpUserService.findUserByUserId(_userid);
	  return irpUser;
	}
	/**
	 * 通过传入的id求出转发的微知的对象
	 * @return
	 */
	public IrpMicroblog transpondIrpMicroblog(long _transpond){
		IrpMicroblog irpMicroblog = null;
		irpMicroblog=this.irpMicroBlogService.irpMicroblog(_transpond);
		return irpMicroblog;
	}
	/**
	 * 获得当前登录的id
	 * @return
	 */
	public Long getLoginUserId(){
		return LoginUtil.getLoginUserId();
	}
	
	
	
	
	/**
	 * 通过传入的id求出显示在转发页面的名字
	 * @return
	 */
	public String showPageName(long _transpond){
	
	    return this.irpUserService.findShowNameByUserid(_transpond);
	    
	}
	/**
	 * 查看转发原微知
	 * @return
	 */
	public String microblogByTranComm(){
		//microblogid=c;
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
		this.irpMicroblogCommentService.findMicroBlogCommentCount(Long.parseLong(microblogid),IrpMicroblogComment.ISDELTRUE));
		
		
		irpMicrobloglist = this.irpMicroblogCommentService.findMicroBlogComment(Long.parseLong(microblogid),IrpMicroblogComment.ISDELTRUE,pageUtil);
		this.pageHTML = pageUtil.getClientPageHtml("pageFineAndLook(#PageNum#)");
		try {
			collectionOfUseridlist=	this.irpMicroblogCollectionKeyService.selectMicroblogidOfUserid(LoginUtil.getLoginUserId());
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return SUCCESS;
	}
	/**
	 * 查看单个微知
	 * mobile
	 * @return
	 */
	public void microblogSingalMobile(){
		micromobilecommentsize = this.irpMicroblogCommentService.findMicroBlogCommentCount(mobilemicroblogid,IrpMicroblogComment.ISDELTRUE);		
		PageUtil pageUtil = new PageUtil(mobilecommpnums,10,micromobilecommentsize);
		List list = this.irpMicroblogCommentService.findMicroBlogComment(mobilemicroblogid,IrpMicroblogComment.ISDELTRUE,pageUtil);
		
		List singlelist = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			singlelist.add("{\"commentshowname\":\""+getShowPageViewNameByUserId(Long.parseLong(map.get("USERID").toString()))+"\"");
			singlelist.add("\"commentuserpic\":\""+findUserByUserId(Long.parseLong(map.get("USERID").toString())).getUserpic()+"\"");
			singlelist.add("\"commentsex\":\""+findUserByUserId(Long.parseLong(map.get("USERID").toString())).getSex()+"\"");
			singlelist.add("\"commentcontent\":\""+map.get("CONTENT")+"\"");
			singlelist.add("\"commentcommentid\":\""+map.get("COMMENTID")+"\"");
			singlelist.add("\"commentcrtime\":\""+sdf.format(map.get("CRTIME"))+"\"}");
		}
		singlelist.add("{\"mcsize\":\""+micromobilecommentsize+"\"");
		singlelist.add("\"userpic\":\""+findUserByUserId(mobileuserid).getUserpic()+"\"");
		singlelist.add("\"sex\":\""+findUserByUserId(mobileuserid).getSex()+"\"");
		singlelist.add("\"showname\":\""+getShowPageViewNameByUserId(mobileuserid)+"\"");
		singlelist.add("\"crtime\":\""+sdf.format(transpondIrpMicroblog(mobilemicroblogid).getCrtime())+"\"");
		singlelist.add("\"fromdata\":\""+transpondIrpMicroblog(mobilemicroblogid).getFromdata()+"\"");
		singlelist.add("\"microcontent\":\""+getMicroblogContent(transpondIrpMicroblog(mobilemicroblogid).getMicroblogcontent()).replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");
		singlelist.add("\"transpondid\":\""+transpondIrpMicroblog(mobilemicroblogid).getTranspondid()+"\"");
		long transpondids = transpondIrpMicroblog(mobilemicroblogid).getTranspondid();
		if(transpondids>0){
			singlelist.add("\"isdel\":\""+transpondIrpMicroblog(transpondids).getIsdel()+"\"");
			singlelist.add("\"tranuser\":\""+getShowPageViewNameByUserId(transpondIrpMicroblog(transpondIrpMicroblog(mobilemicroblogid).getTranspondid()).getUserid())+"\"");
			singlelist.add("\"tranmicrocontent\":\""+getMicroblogContent(transpondIrpMicroblog(transpondIrpMicroblog(mobilemicroblogid).getTranspondid()).getMicroblogcontent()).replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");
			singlelist.add("\"trancrtime\":\""+sdf.format(transpondIrpMicroblog(transpondIrpMicroblog(mobilemicroblogid).getTranspondid()).getCrtime())+"\"");
			singlelist.add("\"tranfromdata\":\""+transpondIrpMicroblog(transpondIrpMicroblog(mobilemicroblogid).getTranspondid()).getFromdata()+"\"");
		}
		singlelist.add("\"microcommcount\":\""+transpondIrpMicroblog(mobilemicroblogid).getCommentcount()+"\"}");
		
		ActionUtil.writer(singlelist.toString());
		
	}
	   /**
	    * 获得所有收藏的微知 
	    * mobile  
	    * @reutrn
	    */
	   	public void findCollectMicroblogListOfMobile(){
	   		
	   		Long userid = LoginUtil.getLoginUserId();
	   		microblogcollectnump =  this.irpMicroBlogService.findMicroblogOfFocusCollectCount(userid);
			PageUtil pageUtil = new PageUtil(this.pagecollectnum,10,
					microblogcollectnump);
			List<IrpMicroblogView> list=this.irpMicroBlogService.findMicroblogOfFocusCollect(pageUtil,LoginUtil.getLoginUserId());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			List disposelist = new ArrayList();	
			for (int i = 0; i < list.size(); i++) {
				IrpMicroblogView imv = list.get(i);
				disposelist.add("{\"name\":\""+imv.getSHOWNAME()+"\"");
				disposelist.add("\"userpic\":\""+imv.getUSERPIC()+"\"");
				disposelist.add("\"sex\":\""+imv.getSEX()+"\"");	
				disposelist.add("\"crtime\":\""+sdf.format(imv.getCRTIME())+"\"");
				disposelist.add("\"fromdata\":\""+imv.getFROMDATA()+"\"");
				disposelist.add("\"microblogid\":\""+imv.getMICROBLOGID().toString()+"\"");
				disposelist.add("\"userid\":\""+imv.getUSERID().toString()+"\"");
				disposelist.add("\"content\":\""+getMicroblogContent(imv.getMICROBLOGCONTENT()).replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");
				disposelist.add("\"commentcount\":\""+imv.getCOMMENTCOUNT()+"\"");
				disposelist.add("\"trancount\":\""+imv.getTRANSPONDCOUNT()+"\"");
				long tranid = imv.getTRANSPONDID();
				if(tranid!=0){
					IrpMicroblog imb = this.irpMicroBlogService.irpMicroblog(tranid);
					disposelist.add("\"tran_name\":\""+getShowPageViewNameByUserId(imb.getUserid())+"\"");
					disposelist.add("\"tran_content\":\""+getMicroblogContent(imb.getMicroblogcontent()).replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");
					disposelist.add("\"tran_crtime\":\""+sdf.format(imb.getCrtime())+"\"");
					disposelist.add("\"tran_fromdata\":\""+imb.getFromdata()+"\"");
				}
				disposelist.add("\"transpondid\":\""+tranid+"\"}");
			}
	   		try {
	   			collectionOfUseridlist = this.irpMicroblogCollectionKeyService.selectMicroblogidOfUserid(userid);
				disposelist.add("{\"collectmine\":\""+collectionOfUseridlist.toString()+"\"");
				disposelist.add("\"microblognump\":\""+microblogcollectnump+"\"}");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   		ActionUtil.writer(disposelist.toString());
	   	}
	  /**
	   * 加载登陆人基本信息
	   * @return
	   */
	public String initUserInfo(){
		
	this.loginUserList = this.irpMicroBlogService.findLoginUserPersonalInfo((long)IrpChnlDocLink.DOC_STATUS_CAOGAO, IrpMicroblog.ISDELFALSE, LoginUtil.getLoginUserId());
 
		return SUCCESS;
	}
	/**
	 * 配置微知字数
	 * @return
	 */
	public void findMicroblogCount(){
		String microblogcount = "140";
		microblogcount =this.irpMicroBlogService.findIrpConfigCvalue(IrpMicroblog.IRPMICROBLOGFONTCOUNT);
		ActionUtil.writer(microblogcount);
	}
	/**
	 * 配置图片上传张数
	 * @return
	 */
	public void findMicroblogUploadpicNum(){
		String microbloguploadpicnum =this.irpMicroBlogService.findIrpConfigCvalue(IrpMicroblog.IRPMICROBLOGUPLOADPICNUM);
		ActionUtil.writer(microbloguploadpicnum);
	}
	/**
	 * 配置转发微知字数限制
	 * @return
	 */
	public void findTranMicroblogCount(){
		String trannumber = "140";
		trannumber=this.irpMicroBlogService.findIrpConfigCvalue(IrpMicroblog.IRPTRANMICROBLOGCOUNT);
		ActionUtil.writer(trannumber);
	}
	/**
	 * 获得所有收藏的微知
	 * @return
	 */
   public String findCollectMicroblogList(){
		Long nUserid = LoginUtil.getLoginUserId();
		loginuserid = nUserid.toString();
			//获取微知长度
			PageUtil pageUtil = new PageUtil(this.pageNum,this.pageSize,
					this.irpMicroBlogService.findMicroblogOfFocusCollectCount(personid));//修改用户id 2017-8-1
			irpMicrobloglist=this.irpMicroBlogService.findMicroblogOfFocusCollect(pageUtil,personid); //修改用户id 2017-8-1
			this.pageHTML = pageUtil.getClientPageHtml("AllCollection_wb(#PageNum#)");
		try {
			collectionOfUseridlist=	this.irpMicroblogCollectionKeyService.selectMicroblogidOfUserid(nUserid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		 
		
		return SUCCESS;
   }

   
   /**
    * 查看图片
    * @return
    */
   public String lookUpMicroblogPic(){
	picfilenamelist = picfilenamelist.replace(".","_150X150.");
	picfilenamearray = picfilenamelist.split(",");
	picfile = ThumbnailPic.disposeThumnail(picfile);
	   return SUCCESS;
   }
  
   /**
    * 查看原图
    * @return
    */
    public String checkMicroblogOriginalPic(){
	
    	
	   return SUCCESS;
   }
	/**
	 * 设置分组（用户）
	 * @return
	 */
   public String microblogUserGroup(){

      return SUCCESS;
   }
   /**
    * 添加某个用户到组织中
    * @return
    */
   public void microblogAddUserOfGroups(){
	   int msg = this.irpGroupService.importGroupUserByUserId(selectuser, selectgroups);
	   ActionUtil.writer(""+msg);
   }
   /**
    * 返回到默认的选择用户页面
    * @return
    */
   public String defaultSelectFocusUser(){
	   RecommentList =  this.irpMicroBlogService.findDefaultRecommend(LoginUtil.getLoginUserId());
				   List listArraylist =new ArrayList();
					  List 	listHashMaplist = this.irpMicroblogFocusService.selectUseridByFocuserId(LoginUtil.getLoginUserId());
						  for(int i =0;i<listHashMaplist.size();i++){
							  Map map = (Map) listHashMaplist.get(i);
							  listArraylist.add(map.values().toString().replace("[","").replace("]",""));
						  } 
						  this.allUseridByFocusUserId=listArraylist;
	   return SUCCESS;
   }
   
   /**
    * 根据用户查看此用户有多少条微知
    * @param _userid
    * @return
    */
   public Integer getMicrCountOfUserid(long _userid){
	   int micrcount = 0;
	   micrcount = this.irpMicroBlogService.searchMicrCountOfUserid(_userid);
	   return micrcount;
   }
   /**
    * 根据用户查看此用户有多少个关注
    * @param _userid
    * @return
    */
   public Integer getFocusCountOfUserid(long _userid){
	   int focuscount = 0;
	   focuscount = this.irpMicroBlogService.searchFocusCountOfUserid(_userid);
	   return focuscount;
   }
 
   /**
    * 根据用户查看此用户有多少个粉丝
    * @param _userid
    * @return
    */
   public Integer getFusCountOfUserid(long _userid){
	   int fuscount = 0;
	   fuscount = this.irpMicroBlogService.searchFusCountOfUserid(_userid);
	   return fuscount;
   }
   /**
    * 查看登录用户关注了多少人
    */
   public void findLoginFocusNum(){
		int msg = this.irpMicroBlogService.searchFocusCountOfUserid(LoginUtil.getLoginUserId());
		ActionUtil.writer(msg+"");
   }
   /**
    * 获得用户组织
    * @return
    */
   public List<String> getIrpGroup(Long _userid){
	   
	   List<String> groupValues = new ArrayList();
	   Map groupMap = this.irpGroupService.findGroupIdsByUserId(_userid);
	    Collection groupvalue= groupMap.values();
	    Iterator iterator = groupvalue.iterator();
	    while(iterator.hasNext()){
	    	String groupObject = iterator.next().toString();
     	    	groupValues.add(groupObject.substring(7).replace("\\",">")+"\n");
	    	
	    }
	    
	   return  groupValues;
	   
   }
   /**
    * 获得某个话题里的微知
    * @return
    */
    public String getSearchTopic(){
    	//专题名字
    	//topicname	
    	
    	Long nUserid = LoginUtil.getLoginUserId();
		loginuserid = nUserid.toString();
		//获取微知长度	
    		 PageUtil pageUtil = new PageUtil(this.pageNum,this.pageSize,
   				  this.irpMicroBlogService.getIrpMicroblogOfTopicValueCount(this.getTopicname()));
    		topicmicroblog = this.irpMicroBlogService.getIrpMicroblogOfTopicValue(this.getTopicname(),pageUtil);
    		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
    	return SUCCESS;
    }  
    /**
     * 查看关于这个主题没删除的有多少个
     * @return
     */
    public void getSearchTopicNum(){
       int num = 0;
       IrpTopic irpTopic = null;
       irpTopic = this.irpTopicService.getIrpTopicById(Long.parseLong(this.getTopicname().trim()));
       if(irpTopic!=null){
    	   num = 1;
       }
        ActionUtil.writer(num+"");
    }
    
    public IrpTopic getIrpTopic(String _topicname){
    	IrpTopic irpTopic = this.irpTopicService.getIrpTopicById(Long.valueOf(_topicname));
    	return irpTopic;
    }
    /**
     * 通过key获得对象
     * @return
     */
    public IrpInformType getIrpInformType(String _informkey){
    	IrpInformType irpInformType = null;
    	
    	irpInformType = irpInformTypeService.irpInformType(_informkey);
    	
    	return irpInformType;
    }
    
    public void deleteMicroblogTure(){
    	//同时删除该微知下所有的图片
    	this.irpMicroBlogService.deleteMicroblogContentPic(microblogidill);
    	int msg =this.irpMicroBlogService.deleteMicroblogByIdTrue(microblogidill);
		
    	ActionUtil.writer(msg+"");
    }
    
    public void emptyMicroblogIll(){
    	this.irpMicroBlogService.deleteMicroblogContentPicAll();
    	int msg = this.irpMicroBlogService.deleteMicroblogAllillegality(IrpMicroblog.ISDELTRUE);
    	ActionUtil.writer(msg+"");
    }
    /**
     * 过滤带图的字符串  缩略图
     * @param _content
     * @return
     */
    public String getMicroblogContent(String _content){
    	_content = ThumbnailPic.searchThumnailPicSrc(_content, "_150X150");
    	return _content;
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
     * 获得微知页面显示名字
     * mobile
     */
    public void findMobileViewName(){
    	ActionUtil.writer(this.irpUserService.findShowNameByUserid(LoginUtil.getLoginUserId()));
    }
    
    /**
     * 链接到首页
     * @return
     */
	public String linkSkipHomePageFirst(){
    	return SUCCESS;
    }
	
    /**
     * 链接到评论
     * @return
     */
	public String linkCommentView(){
    	return SUCCESS;
    }
	
	/**
     * 链接到私信
     * @return
     */
	public String linkMessageView(){
    	return SUCCESS;
    }
	
	/**
     * 链接到@
     * @return
     */
	public String linkAtmeView(){
    	return SUCCESS;
    }
	
	public String  microBlogAddpl(){
		   IrpMicroblog _irpMicroblog = new IrpMicroblog();
		   Long groupid =votegoupid;
		   _irpMicroblog.setGroupid(groupid);
			AtmeUtil au = new AtmeUtil();
		   int nMsg = this.irpMicroBlogService.addMicroBlog(au.getAtmeStr(this.publishInfo),Integer.parseInt(this.microblogType), _irpMicroblog,publishInfoimg);
		   if(nMsg==1){
	          IrpMicroblog irpMicroblogFirct = this.irpMicroBlogService.findFirstMicroblogbytype(LoginUtil.getLoginUserId(),IrpMicroblog.PINGMICROBLOG);
	          if(irpMicroblogFirct!=null){
	        	  au.disposeATME(publishInfo,irpMicroblogFirct.getMicroblogid());
	          }
		   }
		   if(nMsg==1){
			   try {
				   irpMicrobloglist =  this.irpMicroBlogService.findMicroblogOfFocus(IrpMicroblog.ISDELFALSE, LoginUtil.getLoginUserId(),TableIdUtil.getNextId(IrpMicroblog.TABLE_NAME)-1);
				} catch (Exception e) {
					e.printStackTrace();
				}
		   }
		   return SUCCESS;
		}
	public IrpUser findIrpUserByFocusId(long _userid){
		return this.irpUserService.findUserByUserId(_userid);
	}
	
	public String microblogManage() {
		return SUCCESS;
	}
	
	
	/**
	 * 必须关注限制
	 */
	public void mustFocus() {
		int status=0;
		 List listHashMaplist  = this.irpMicroblogFocusService.selectUseridByFocuserId(LoginUtil.getLoginUserId());
		 long sysCount=irpUserService.findAllUserCountByStatus(30)-1;
		 if(listHashMaplist!=null){
			long  focusCount=listHashMaplist.size();
			if(focusCount==sysCount){
				status=1;
			}
			String s=SysConfigUtil.getSysConfigValue("MICROBLOG_FOCUS");
			long mustFocus=Long.parseLong(s);
			if(focusCount>=mustFocus){
				status=1;
			}
		 }
		ActionUtil.writer(""+status);
	}
	/**
	 * 推荐关注
	 * 本月积分高的人
	 * 
	 */
	public String recommendFocus() {
		int status=0;
		List<Long> listArraylist=new ArrayList<Long>();
		 List listHashMaplist  = this.irpMicroblogFocusService.selectUseridByFocuserId(LoginUtil.getLoginUserId());
		 for(int i =0;i<listHashMaplist.size();i++){
			  Map map = (Map) listHashMaplist.get(i);
			  listArraylist.add(Long.parseLong(map.values().toString().replace("[","").replace("]","")));
		  }
		 String s=SysConfigUtil.getSysConfigValue("MICROBLOG_FOCUS");
		 long mustFocus=0;
		 if(listHashMaplist==null){
			 listArraylist=new ArrayList<Long>();
		 }
		 listArraylist.add(LoginUtil.getLoginUserId());
		 mustFocus=Long.parseLong(s)-listArraylist.size()+1;
		 irpUsers = irpUserService.userRankByDateNotInList(4,listArraylist,mustFocus);
		return SUCCESS;
	}
	
	public boolean boolUserFUser(Long _userid){
		  boolean falg = false;
		  List  listHashMaplist = this.irpMicroblogFocusService.selectUseridByFocuserId(LoginUtil.getLoginUserId());
		  for(int i =0;i<listHashMaplist.size();i++){
			  Map map = (Map) listHashMaplist.get(i);
			  if (_userid==Long.parseLong(map.values().toString().replace("[","").replace("]",""))) {
				  falg = true;
				  break;
			  }
		  } 
		return falg;	
	}
	
}
