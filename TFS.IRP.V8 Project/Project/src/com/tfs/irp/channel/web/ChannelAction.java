package com.tfs.irp.channel.web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.attached.entity.IrpAttached;
import com.tfs.irp.attached.service.IrpAttachedService;
import com.tfs.irp.attachedtype.entity.IrpAttachedType;
import com.tfs.irp.attachedtype.service.IrpAttachedTypeService;
import com.tfs.irp.category.entity.IrpCategory;
import com.tfs.irp.category.service.IrpCategoryService;
import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.channel.entity.IrpChannelExample;
import com.tfs.irp.channel.entity.IrpChannelExample.Criteria;
import com.tfs.irp.channel.service.IrpChannelService;
import com.tfs.irp.chnl_doc_link.entity.IrpChnlDocLink;
import com.tfs.irp.chnl_doc_link.service.IrpChnl_Doc_LinkService;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.service.IrpDocumentService;
import com.tfs.irp.expert.entity.IrpExpertClassifyLink;
import com.tfs.irp.expert.service.IrpExpertClassifyLinkService;
import com.tfs.irp.file.entity.IrpUserFile;
import com.tfs.irp.file.service.IrpUserFileService;
import com.tfs.irp.form.entity.IrpForm;
import com.tfs.irp.form.service.IrpFormService;
import com.tfs.irp.formcolumn.entity.IrpFormColumn;
import com.tfs.irp.formcolumn.service.IrpFormColumnService;
import com.tfs.irp.medal.entity.IrpMedal;
import com.tfs.irp.medal.service.IrpMedalService;
import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.microblog.service.IrpMicroblogService;
import com.tfs.irp.microblogfocus.entity.IrpMicroblogFocus;
import com.tfs.irp.microblogfocus.service.IrpMicroblogFocusService;
import com.tfs.irp.personalsearch.entity.IrpPersonalSearch;
import com.tfs.irp.personalsearch.service.IrpPersonalSearchService;
import com.tfs.irp.site.entity.IrpSite;
import com.tfs.irp.site.service.IrpSiteService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.usermedal.entity.IrpUserMedal;
import com.tfs.irp.usermedal.service.IrpUserMedalService;
import com.tfs.irp.uservaluelink.service.IrpUserValueLinkService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.FileUtil;
import com.tfs.irp.util.JsonUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.SysFileUtil;
import com.tfs.irp.util.TableIdUtil;
import com.tfs.irp.util.ThumbnailPic;
import com.tfs.irp.valuesetting.entity.IrpValueSetting;
import com.tfs.irp.valuesetting.service.IrpValueSettingService;
import com.tfs.irp.workflow.entity.IrpWorkflow;
import com.tfs.irp.workflow.service.IrpWorkFlowService;

public class ChannelAction extends ActionSupport {
	
	private IrpChannelService irpChannelService;
	private IrpFormService irpFormService;///////付燕妮
	
	private IrpSiteService irpSiteService;
	
	private IrpUserFileService irpUserFileService;
	
	private String isChannelOrMapAddOrUp;
	
	private IrpUserValueLinkService irpUserValueLinkService;
	
	private IrpValueSettingService irpValueSettingService;
	
	private IrpPersonalSearchService irpPersonalSearchService;
	
	private Long siteid;
	
	private String sitename;
	
	private Long id = -1L;
	
	private IrpChannel irpChannel;
	
	private List<IrpChannel> irpChannels;
	private IrpDocumentService irpDocumentService;
	
	private IrpMicroblogFocusService irpMicroblogFocusService;
	private List<IrpChnlDocLink> newChnlDocs;
	private List<IrpMicroblogFocus> microblogFocus;
	private IrpExpertClassifyLinkService irpExpertClassifyLinkService;
	private IrpCategoryService irpCategoryService;
	private List<IrpMicroblogFocus> microblogFans;
	int userfocuscount;
	int userfanscount;
	public List<IrpMicroblogFocus> getMicroblogFocus() {
		return microblogFocus;
	}
	public void setMicroblogFocus(List<IrpMicroblogFocus> microblogFocus) {
		this.microblogFocus = microblogFocus;
	}
	public List<IrpMicroblogFocus> getMicroblogFans() {
		return microblogFans;
	}
	public void setMicroblogFans(List<IrpMicroblogFocus> microblogFans) {
		this.microblogFans = microblogFans;
	}
	public IrpDocumentService getIrpDocumentService() {
		return irpDocumentService;
	}
	public void setIrpDocumentService(IrpDocumentService irpDocumentService) {
		this.irpDocumentService = irpDocumentService;
	}
	public String getJsonFile() {
		return jsonFile;
	}
	public void setJsonFile(String jsonFile) {
		this.jsonFile = jsonFile;
	}
	public IrpAttachedTypeService getIrpAttachedTypeService() {
		return irpAttachedTypeService;
	}
	public void setIrpAttachedTypeService(
			IrpAttachedTypeService irpAttachedTypeService) {
		this.irpAttachedTypeService = irpAttachedTypeService;
	}
	public IrpAttachedService getIrpAttachedService() {
		return irpAttachedService;
	}
	public void setIrpAttachedService(IrpAttachedService irpAttachedService) {
		this.irpAttachedService = irpAttachedService;
	}
	
	///////////////////////////付燕妮 
	private List<IrpForm> irpForms;
	
	public IrpFormService getIrpFormService() {
		return irpFormService;
	}
	public void setIrpFormService(IrpFormService irpFormService) {
		this.irpFormService = irpFormService;
	}
	public List<IrpForm> getIrpForms() {
		return irpForms;
	}
	public void setIrpForms(List<IrpForm> irpForms) {
		this.irpForms = irpForms;
	}
	private String chnlName;
	
	private String chnlNameToConfim;
	
	private Long channelid;
	
	private String channelorDocument;
	
	private int isGCChannel=1;	//设定一个变量,标示当前是否为栏目回收站,若为栏目回收站则为0 否则就是正常位1
	
	private int pageNum=1; 
	
	private int pageSize=20; 
	
	private String pageHTML;
	
	private String orderField=""; 
	
	private String orderBy="";
	
	private String searchWord; 
	
	private String searchType;
	
	private Long[] channelids;//定义一个栏目id的数组 
	
	private Long personid;//关注的人的主键id
	
	private Long focusonfus;
	
	private Long microblogid;//查看原微知的id
	
	private Long micruserid;
	
	private Long loginUserId;
	
	private List<IrpUserFile> personAllFile;
	
	private IrpMicroblogService irpMicroblogService;
	
	private Integer chnlType;
	
	private String personaltabval;
	
	private List<IrpPersonalSearch> personalSearchs;
	 
	public IrpPersonalSearchService getIrpPersonalSearchService() {
		return irpPersonalSearchService;
	}
	public void setIrpPersonalSearchService(
			IrpPersonalSearchService irpPersonalSearchService) {
		this.irpPersonalSearchService = irpPersonalSearchService;
	}
	public List<IrpPersonalSearch> getPersonalSearchs() {
		return personalSearchs;
	}
	public void setPersonalSearchs(List<IrpPersonalSearch> personalSearchs) {
		this.personalSearchs = personalSearchs;
	}
	public String getPersonaltabval() {
		return personaltabval;
	}
	public void setPersonaltabval(String personaltabval) {
		this.personaltabval = personaltabval;
	}
	public Integer getChnlType() {
		return chnlType;
	}
	public void setChnlType(Integer chnlType) {
		this.chnlType = chnlType;
	}
	private List userinfolistpersonal;
	
	public List getUserinfolistpersonal() {
		return userinfolistpersonal;
	}
	public String getIsChannelOrMapAddOrUp() {
		return isChannelOrMapAddOrUp;
	}
	public void setIsChannelOrMapAddOrUp(String isChannelOrMapAddOrUp) {
		this.isChannelOrMapAddOrUp = isChannelOrMapAddOrUp;
	}
	public void setUserinfolistpersonal(List userinfolistpersonal) {
		this.userinfolistpersonal = userinfolistpersonal;
	}
	public IrpMicroblogService getIrpMicroblogService() {
		return irpMicroblogService;
	}
	public void setIrpMicroblogService(IrpMicroblogService irpMicroblogService) {
		this.irpMicroblogService = irpMicroblogService;
	}
	private IrpChnl_Doc_LinkService irpChnl_Doc_LinkService;
	public IrpChnl_Doc_LinkService getIrpChnl_Doc_LinkService() {
		return irpChnl_Doc_LinkService;
	}
	public IrpUserValueLinkService getIrpUserValueLinkService() {
		return irpUserValueLinkService;
	}
	public IrpValueSettingService getIrpValueSettingService() {
		return irpValueSettingService;
	}
	public void setIrpUserValueLinkService(
			IrpUserValueLinkService irpUserValueLinkService) {
		this.irpUserValueLinkService = irpUserValueLinkService;
	}
	public void setIrpValueSettingService(
			IrpValueSettingService irpValueSettingService) {
		this.irpValueSettingService = irpValueSettingService;
	}
	public void setIrpChnl_Doc_LinkService(
			IrpChnl_Doc_LinkService irpChnl_Doc_LinkService) {
		this.irpChnl_Doc_LinkService = irpChnl_Doc_LinkService;
	}

	
	public Long getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(Long loginUserId) {
		this.loginUserId = loginUserId;
	}
	public Long getMicruserid() {
		return micruserid;
	}
	public void setMicruserid(Long micruserid) {
		this.micruserid = micruserid;
	}
	public Long getMicroblogid() {
		return microblogid;
	}
	public void setMicroblogid(Long microblogid) {
		this.microblogid = microblogid;
	}
	public IrpUserFileService getIrpUserFileService() {
		return irpUserFileService;
	}
	public void setIrpUserFileService(IrpUserFileService irpUserFileService) {
		this.irpUserFileService = irpUserFileService;
	}
	public Long getFocusonfus() {
		return focusonfus;
	}
	public void setFocusonfus(Long focusonfus) {
		this.focusonfus = focusonfus;
	}
	private IrpUserService irpUserService;
	private IrpUser irpUser;
	
	private List<IrpWorkflow> workFlows;
	
	private IrpWorkFlowService irpWorkFlowService;
	
	public IrpUserService getIrpUserService() {
		return irpUserService;
	}
	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}
	public IrpUser getIrpUser() {
		return irpUser;
	}
	public void setIrpUser(IrpUser irpUser) {
		this.irpUser = irpUser;
	}
	public IrpWorkFlowService getIrpWorkFlowService() {
		return irpWorkFlowService;
	}
	public void setIrpWorkFlowService(IrpWorkFlowService irpWorkFlowService) {
		this.irpWorkFlowService = irpWorkFlowService;
	}
	public List<IrpWorkflow> getWorkFlows() {
		return workFlows;
	}
	public void setWorkFlows(List<IrpWorkflow> workFlows) {
		this.workFlows = workFlows;
	} 
	/**
	 * 检验栏目名称唯一性
	 */
	public void clientCheckChnlName(){ 
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("siteid",IrpSite.PRIVATE_SITE_ID); 
		map.put("parentid",0L); 
		map.put("channelid",id);
		if(chnlName!=null && chnlName.length()>0){
			map.put("chnlname",chnlName);   
			boolean b=irpChannelService.clientCheckChnlNameByName(IrpSite.PRIVATE_SITE_ID,0L,this.id,chnlName); 
			ActionUtil.writer(b+"");  
		}
	}
	
	/**
	 * 判断自己的栏目的数量和配置里面的栏目数量比较大小
	 */
	public void isNotCreateChannel(){
		int nCount=0;
		Long loginUserId=LoginUtil.getLoginUserId();
		int _score=irpUserValueLinkService.sumScoreByUserid(loginUserId);//获取积分
		IrpValueSetting set=irpValueSettingService.irpValueSettingOfGroupChannel(_score);
		if(set!=null){
			String tValue=set.getCrchannelnum().toString();
			if(tValue!=null && tValue.length()>0){
				List<IrpChannel> list=new ArrayList<IrpChannel>();
				irpChannels=irpChannelService.findChannelIdsByParent(0L, list);//递归自己   //得到所有的子栏目 
				if(irpChannels!=null){
				 int cValue=Integer.parseInt(tValue);
					if(irpChannels.size()<=cValue){
						nCount=1;
					}
				}
			} 
		}
		ActionUtil.writer(String.valueOf(nCount)); 
	}
	/**
	 * 前台的删除栏目
	 */
	public void clientDeleteChannel(){
		int nCount=irpChannelService.clientDeleteChannel(this.id);
		ActionUtil.writer(String.valueOf(nCount));
	}
	
	private List<IrpUserMedal> listusermedal;
	private IrpUserMedalService irpUserMedalService;
	private IrpMedalService irpMedalService;
	private List<IrpMedal> listmedal;
	
	public List<IrpMedal> getListmedal() {
		return listmedal;
	}
	public void setListmedal(List<IrpMedal> listmedal) {
		this.listmedal = listmedal;
	}
	public IrpMedalService getIrpMedalService() {
		return irpMedalService;
	}
	public void setIrpMedalService(IrpMedalService irpMedalService) {
		this.irpMedalService = irpMedalService;
	}
	public IrpUserMedalService getIrpUserMedalService() {
		return irpUserMedalService;
	}
	public void setIrpUserMedalService(IrpUserMedalService irpUserMedalService) {
		this.irpUserMedalService = irpUserMedalService;
	}
	
	
	public List<IrpUserMedal> getListusermedal() {
		return listusermedal;
	}
	public void setListusermedal(List<IrpUserMedal> listusermedal) {
		this.listusermedal = listusermedal;
	}
	private int sumscore;
	private int isCurrentUser;
	private Long maptype;
	

	public Long getMaptype() {
		return maptype;
	}
	public void setMaptype(Long maptype) {
		this.maptype = maptype;
	}
	public int getIsCurrentUser() {
		return isCurrentUser;
	}
	public void setIsCurrentUser(int isCurrentUser) {
		this.isCurrentUser = isCurrentUser;
	}
	public int getSumscore() {
		return sumscore;
	}
	public void setSumscore(int sumscore) {
		this.sumscore = sumscore;
	}
	/**
	 * 前台，查询当前登录用户关注的人的所有栏目
	 * @return
	 */
	public String clientToIndexPerson(){
		
		
		if(personid==null)return ERROR; 
		loginUserId=LoginUtil.getLoginUser().getUserid(); 
		long loginUserIdl=loginUserId;
		long personidl=personid;
		if(loginUserIdl==personidl){
			isCurrentUser=1;
		}else{
			isCurrentUser=0;
		}
		irpUser = irpUserService.findUserByUserId(personid);
		sumscore=irpUserValueLinkService.getScoreByUserid(personid);
		//查询该用户所有勋章
		IrpUserMedal userMedal=null;
		IrpMedal medal=null;
		listusermedal=irpUserMedalService.getUserMedalByUserid(null, personid);
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
		if(irpUser==null)return ERROR;
		PageUtil pageUtil=new PageUtil(1, 8, 8);
		personAllFile=irpUserFileService.userAllFileByType(personid, IrpAttachedType.JPG_FIELD_NAME,pageUtil);//查询所有附件
		userinfolistpersonal =  this.irpMicroblogService.findLoginUserPersonalInfo((long)IrpChnlDocLink.DOC_STATUS_CAOGAO, IrpMicroblog.ISDELFALSE,personid);
		
		return SUCCESS;
	}
	
	
	private List<IrpCategory> cateGorylist;
	private String focusStatus;
    public List<IrpCategory> getCateGorylist() {
		return cateGorylist;
	}
	public void setCateGorylist(List<IrpCategory> cateGorylist) {
		this.cateGorylist = cateGorylist;
	}
	private IrpMicroblogService irpMicroBlogService;
	private List<String> irpMicrobloglist;
	private String isuser;
	public String getIsuser() {
		return isuser;
	}
	public void setIsuser(String isuser) {
		this.isuser = isuser;
	}
	/**
     * 跳到专家个人页面
     * @return
     * @author   npz
     * @date 2017年10月27日
     */
    public String clientToExpertPerson(){
    	cateGorylist = new ArrayList<IrpCategory>();
    	if(personid==null)return ERROR; 
    	loginUserId=LoginUtil.getLoginUser().getUserid(); 
    	if(loginUserId.equals(personid)){
    		isuser = "true";
    	}else{
    		isuser="false";
    	}
		long loginUserIdl=loginUserId;
		long personidl=personid;
		if(loginUserIdl==personidl){
			isCurrentUser=1;
		}else{
			isCurrentUser=0;
		}
    	irpUser = irpUserService.findUserByUserId(personid);
    	//查找专家擅长领域
    	List<IrpExpertClassifyLink> expertClassifylist = irpExpertClassifyLinkService.findCategorysByUserId(personid);
    	for(IrpExpertClassifyLink expertClassify:expertClassifylist){
    		IrpCategory irpCategory= irpCategoryService.findCategoryByPrimaryKey(expertClassify.getClassifyid());
    		if(irpCategory!=null){
    			cateGorylist.add(irpCategory);
    		}
    	}
    	//关注
    	userfocuscount = irpMicroblogFocusService.findMicroblogFocusUserIdCount(personid);
    	PageUtil pageUtil = new PageUtil(this.pageNum, SysConfigUtil.getSysConfigNumValue("HOT_NEW_DOCUMENT"), userfocuscount);
    	microblogFocus = irpMicroblogFocusService.findMicroblogFocusUserId(personidl, pageUtil);
    	//粉丝
    	userfanscount = irpMicroblogFocusService.findMicroblogUserIdCount(personid);
    	pageUtil = new PageUtil(this.pageNum,SysConfigUtil.getSysConfigNumValue("HOT_NEW_DOCUMENT"), userfanscount);
    	microblogFans = irpMicroblogFocusService.findMicroblogUserId(personidl, pageUtil);
    	//遍历专家粉丝，和用户id对比，看用户是否关注
    	for(IrpMicroblogFocus fans:microblogFans){
    		if(fans.getFocususerid().equals(loginUserId)){
    			focusStatus = "true";
    			break;
    		}else{
    			focusStatus = "false";
    		}
    	}
    	//专家最新微知
    	pageUtil = new PageUtil(this.pageNum,SysConfigUtil.getSysConfigNumValue("HOT_NEW_DOCUMENT"),
				this.irpMicroBlogService.coutnMicroblogOfUserid(personid,IrpMicroblog.ISDELFALSE,IrpMicroblog.PUBLICMICROBLOG));
	     irpMicrobloglist =	this.irpMicroBlogService.findMicroblogListByUserid(personid,IrpMicroblog.ISDELFALSE,pageUtil,IrpMicroblog.PUBLICMICROBLOG);
    	//查找专家投稿知识
    	//得到当前用户的所有栏目对象 
		 List<IrpChannel> irpChannels=new ArrayList<IrpChannel>(); 
		//按照创建时间排序
	 	String sOrderByClause="crtime desc";
	 	irpChannels= irpChannelService.findChannelIdsByPerson(personid,0L, irpChannels); 
		if(irpChannels!=null && irpChannels.size()>0){
			 List<Long> channelidsList=new ArrayList<Long>();
			 for (IrpChannel channel:irpChannels) {
				 if(channel!=null){
					 channelidsList.add(channel.getChannelid());
				 }
			 }
			 if(channelidsList!=null && channelidsList.size()>0){
				//先查询数量
					HashMap<String,Object> map1=new HashMap<String, Object>();
					map1.put("docstatus",IrpChnlDocLink.DOC_STATUS_CAOGAO);   
			 		//map.put("channelid",this.id); 
					map1.put("channelidsList", channelidsList);
			 		//得到自己创建的所有知识的id
			 		List<Long> myallDociIds=irpChnl_Doc_LinkService.allDocids(map1,IrpDocument.DOCUMENT_NOT_INFORM);
			 		//到企业知识库查寻oldid在这个集合里面的所有知识 
			 		int aDataCount=irpChnl_Doc_LinkService.alltougaodocumentCount(myallDociIds,IrpDocument.DOCUMENT_NOT_INFORM);
			 		pageUtil= new PageUtil(this.pageNum, SysConfigUtil.getSysConfigNumValue("HOT_NEW_DOCUMENT"), aDataCount); 
			 		newChnlDocs=irpChnl_Doc_LinkService.alltougaodocument(pageUtil,myallDociIds,sOrderByClause,IrpDocument.DOCUMENT_NOT_INFORM);
			}
		}
		
		return SUCCESS;
    }
    public IrpMicroblogService getIrpMicroBlogService() {
		return irpMicroBlogService;
	}
	public void setIrpMicroBlogService(IrpMicroblogService irpMicroBlogService) {
		this.irpMicroBlogService = irpMicroBlogService;
	}
	public List<String> getIrpMicrobloglist() {
		return irpMicrobloglist;
	}
	public void setIrpMicrobloglist(List<String> irpMicrobloglist) {
		this.irpMicrobloglist = irpMicrobloglist;
	}
	public String getFocusStatus() {
		return focusStatus;
	}
	public void setFocusStatus(String focusStatus) {
		this.focusStatus = focusStatus;
	}
	public IrpExpertClassifyLinkService getIrpExpertClassifyLinkService() {
		return irpExpertClassifyLinkService;
	}
	public void setIrpExpertClassifyLinkService(
			IrpExpertClassifyLinkService irpExpertClassifyLinkService) {
		this.irpExpertClassifyLinkService = irpExpertClassifyLinkService;
	}
	public IrpCategoryService getIrpCategoryService() {
		return irpCategoryService;
	}
	public void setIrpCategoryService(IrpCategoryService irpCategoryService) {
		this.irpCategoryService = irpCategoryService;
	}
	/**
	 * 通过id找出相对应的个人信息
	 * @return
	 */
	public IrpUser findUserByUserId(long _userid){
	  IrpUser irpUser = null;
	  irpUser = this.irpUserService.findUserByUserId(_userid);
	  return irpUser;
	}
	
	public List<IrpChnlDocLink> getNewChnlDocs() {
		return newChnlDocs;
	}
	public void setNewChnlDocs(List<IrpChnlDocLink> newChnlDocs) {
		this.newChnlDocs = newChnlDocs;
	}
	public IrpMicroblogFocusService getIrpMicroblogFocusService() {
		return irpMicroblogFocusService;
	}
	public void setIrpMicroblogFocusService(
			IrpMicroblogFocusService irpMicroblogFocusService) {
		this.irpMicroblogFocusService = irpMicroblogFocusService;
	}
	public int getUserfocuscount() {
		return userfocuscount;
	}
	public void setUserfocuscount(int userfocuscount) {
		this.userfocuscount = userfocuscount;
	}
	public int getUserfanscount() {
		return userfanscount;
	}
	public void setUserfanscount(int userfanscount) {
		this.userfanscount = userfanscount;
	}
	/**
	 * 查看自己所有的图片附件
	 * @return
	 */
	public String userAllPicFile(){
		if(personid==null)return ERROR; 
		int nDataCount =irpUserFileService.FileAmount(personid, IrpAttachedType.JPG_FIELD_NAME);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount); 
		personAllFile=irpUserFileService.userAllFileByType(personid, IrpAttachedType.JPG_FIELD_NAME,pageUtil);//查询所有附件
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	public String personAllChannel(){
		IrpUser irpUser=LoginUtil.getLoginUser();
		irpChannel=irpChannelService.findChannelByPerson(irpUser.getUserid()); 
		irpChannels=new ArrayList<IrpChannel>(); 
		irpChannels= irpChannelService.findChannelIdsByPerson(personid,0L, irpChannels); 
		return SUCCESS;
	}
	/**
	 * 前台添加栏目
	 */
	public void clientAddChannel(){
		irpChannel = irpChannelService.clientAddChannel(irpChannel);  
		if(irpChannel!=null){ 
			JSONObject jsonObject=JSONObject.fromObject(irpChannel); 
			ActionUtil.writer(jsonObject.toString());
		}else{
			ActionUtil.writer("");
		}
	}
	/**
	 * 前台 跳到添加栏目页面
	 * @return
	 */
	public String toAddPersonChannel(){  
		IrpUser irpUser=LoginUtil.getLoginUser();
		irpChannel=irpChannelService.findChannelByPerson(irpUser.getUserid());
		return SUCCESS;
	}
	/**
	 * 前台  查看当前登录用户的所有栏目
	 * @return
	 */
	public String userAllChannel(){ 
		//查询不是栏目回收站的栏目 
		List<IrpChannel> list=new ArrayList<IrpChannel>(); 
		irpChannels=irpChannelService.findChannelIdsByParent(0L, list);//递归自己   //得到所有的子栏目 
		return SUCCESS;
	}
	/**
	 * 前台  查看当前登录用户的所有栏目下拉列表显示
	 */
	public void userAllChannelSelect(){ 
		//查询不是栏目回收站的栏目 
		List<IrpChannel> list=new ArrayList<IrpChannel>(); 
		irpChannels=irpChannelService.findChannelIdsByParent(0L, list);//递归自己   //得到所有的子栏目 
		ActionUtil.writer(JsonUtil.list2json(list)); 
	}
	/**
	 * 检查栏目标识的唯一性
	 */
	public void checkChnlName(){  
		//如果是修改页面的验证      
		boolean boo=false;
		try {
			chnlName=ActionUtil.decode(chnlName); 
			if(chnlNameToConfim!=null &&chnlNameToConfim.length()>0){ 
				chnlNameToConfim=ActionUtil.decode(chnlNameToConfim);
				if(chnlName.equals(chnlNameToConfim)){
					boo=true;
				}else{
					HashMap<String, Object> map=new HashMap<String, Object>();
					map.put("siteid",siteid); 
					map.put("parentid",id);
					if(chnlName!=null && chnlName.length()>0){
						map.put("chnlname",chnlName);
					}
					boolean b=irpChannelService.checkChnlNameByName(map);  
					boo=b;
				}
			}else{
				HashMap<String, Object> map=new HashMap<String, Object>();
				map.put("siteid",siteid); 
				map.put("parentid",id);
				if(chnlName!=null && chnlName.length()>0){
					map.put("chnlname",chnlName);
				}
				boolean b=irpChannelService.checkChnlNameByName(map);  
				boo=b;
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}  
		ActionUtil.writer(String.valueOf(boo));
	}
	/**
	 * 跳到修改栏目页面
	 * @return
	 */
	public String clientToUpdateChannel(){   
		irpChannel=irpChannelService.finChannelByChannelid(id);  
		return SUCCESS;
	}
	/**
	 * 跳到修改栏目页面
	 * @return
	 */
	public String toUpdateChannel(){ 
		HashMap<String,Object> map=new HashMap<String, Object>();  
		map.put("siteid",siteid);  
		map.put("status",1); 
		//以供添加的栏目在这个级别中的排序之用
		irpChannel=irpChannelService.finChannelByChannelid(channelid);
		if(irpChannel!=null){
			this.id=irpChannel.getParentid();
		}
		this.chnlType=irpChannel.getChnltype();
		
		if(id==null || id==-1) map.put("parentid",0L);
		else  map.put("parentid",this.id);   
		
		irpChannelService.finChannelByChannelid(this.id);
		irpChannels=irpChannelService.siteAllChannel(null,map,channelid,0);//他里面的所有一级栏目   
		workFlows = irpWorkFlowService.findAllWorkFlow();
		this.isChannelOrMapAddOrUp="updatechannel";
		int formisdel=10;
		int formstatus=20;
		int isexitindb=10;
		irpForms=irpFormService.getAllForm(formisdel,formstatus,isexitindb);
		return SUCCESS;
	}
	/**
	 * 跳到修改栏目页面
	 * @return
	 */
	public String toUpdateMap(){  
		HashMap<String,Object> map=new HashMap<String, Object>();  
		map.put("siteid",siteid);  
		map.put("status",1); 
		//以供添加的栏目在这个级别中的排序之用
		irpChannel=irpChannelService.finChannelByChannelid(channelid);
		if(irpChannel!=null){
			this.id=irpChannel.getParentid();
			this.chnlType=irpChannel.getChnltype();
		}
		if(id==null || id==-1){
			map.put("parentid",0L);
		}else{
			map.put("parentid",this.id);  
		} 
		if(irpChannel!=null){
			maptype=irpChannel.getMaptype();
		}
		irpChannels=irpChannelService.siteAllChannel(null,map,channelid,IrpChannel.CHANNEL_TYPE_MAP);//他里面的所有一级栏目   
		this.isChannelOrMapAddOrUp="updatemap";
		return SUCCESS;
	}
	/**
	 * 修改栏目信息
	 */
	public void  updateChannel(){ 
		int nCount=0; 
		if(irpChannel!=null){  
			
			long tableid=irpChannel.getChannelid();
			if (jsonFile != null && jsonFile != "") { 
				try{
					// 如果存在附件，则添加附件
					JSONArray _Array = JSONArray.fromObject(jsonFile);
					for (int i = 0; i < _Array.size(); i++) {
						JSONObject jsonObject = (JSONObject) _Array
								.getJSONObject(i);
						String sattfile = jsonObject.getString("attfile");
						String sattdesc = jsonObject.getString("attdesc");
						String sattlinkalt = jsonObject.getString("attlinkalt");
						String seditversions = jsonObject.getString("editversions");
						String attflag = jsonObject.getString("attflag");
						// 获得文件类型
						Long typeid = irpAttachedTypeService
								.findAttachedTypeIdByFileExt(FileUtil
										.findFileExt(sattfile));
						// 入库
					List<Long> 	_attachedids=addAttQuestionFile(Integer.parseInt(attflag), sattfile,
								typeid, tableid, sattdesc, sattlinkalt, seditversions,
								false, null, false);
					if(_attachedids!=null){
						String medalimage="";
						for (Long long1 : _attachedids) {
							medalimage=long1+","+medalimage;
						}
						irpChannel.setImageid(medalimage);
					}
					irpAttachedService.deleteFileNotInList(_attachedids, tableid, IrpAttached.MAP_CHANNEL);
					}
				}catch (Exception e) {
						// TODO: handle exception
				}
			}else{
				irpChannel.setImageid("");
			}
			nCount = irpChannelService.updateChannelByChannelid(irpChannel);
			Long form=irpChannel.getFormid();
			if(form!=null){
				IrpForm irpForm = irpFormService.getFormById(form);
				if(irpForm!=null&&!irpForm.equals("")){
					irpForm.setChannelid(irpChannel.getChannelid());
					irpFormService.updateForm(irpForm);
				}
			}
		} 
		ActionUtil.writer(String.valueOf(nCount));  
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
	/**
	 * 查看栏目回收站里面的栏目
	 * @return
	 */
	public String  GCChannelInfo(){
		HashMap<String,Object> map=new HashMap<String, Object>(); 
		map.put("siteid",this.siteid);
		map.put("parentid",this.id);
		map.put("status",0); 
		if(searchWord!=null && searchWord.length()>0){
			searchWord = ActionUtil.decode(searchWord);
			map.put("searchWord", searchWord);
			map.put("searchType", searchType);
		}
		int nDataCount = irpChannelService.channelCountMap(map); 
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount); 
	 	irpChannels= irpChannelService.GCtoAllChannel(pageUtil,map);  
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	
	public String gcalldocumentsublist(){
		HashMap<String,Object> map=new HashMap<String, Object>(); 
		map.put("siteid",this.siteid);
		map.put("parentid",this.id);
		map.put("status",0); 
		if(searchWord!=null && searchWord.length()>0){
			searchWord = ActionUtil.decode(searchWord);
			map.put("searchWord", searchWord);
			map.put("searchType", searchType);
		}
		int nDataCount = irpChannelService.channelCountMap(map); 
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount); 
	 	irpChannels= irpChannelService.GCtoAllChannel(pageUtil,map);  
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;	
	}
	/**
	 * 将选中的栏目删除到垃圾回收站里面去
	 */
	public void deleteChannelToGC(){  
		int ncount=irpChannelService.updateChannelStatusByChannelIds(channelids,IrpChannel.NO_DOCSTATUS,IrpSite.SITE_TYPE_PUBLISH);
		ActionUtil.writer(String.valueOf(ncount)); 
	}
	/**
	 * 将选中的栏目 从垃圾回收站里面恢复回来
	 */
	public void HuiFuChannelToGC(){ 
		int ncount=irpChannelService.updateChannelStatusByChannelIds2(channelids,IrpChannel.IS_DOCSTATIUS,IrpSite.SITE_TYPE_PUBLISH);
		ActionUtil.writer(String.valueOf(ncount)); 
	}
	/**
	 * 彻底删除
	 */
	public void deleteChannelFromGC(){
		int ncount=irpChannelService.deleteChannelFromGC(channelids, id, siteid, IrpSite.SITE_TYPE_PUBLISH);
		ActionUtil.writer(String.valueOf(ncount)); 
	}
	 /**
	  * 确定选中的栏目之后到添加文档页面
	  * @return
	  */
	public String checkchneltrue(){ 
		 return SUCCESS;
	}
	/**
	 * 显示站点下的栏目，进行选择，目的是为了添加文档 
	 */
	public void toLoadSiteToCheckChannel(){  
		HashMap<String,Object> map=new HashMap<String, Object>();
		map.put("status",1 );
		List<Map<String, Object>> treeNode = new ArrayList<Map<String,Object>>();
		sitename=irpSiteService.findSiteName(siteid); 
		if(id==-1){//第一次进来加载根节点
			Map<String,Object> item=new HashMap<String, Object>();
			item.put("id",0);//即将作为下一节点的parentid
			item.put("text",sitename);
			item.put("state", "closed");
			treeNode.add(item);  
		}else{ 
			map.put("siteid",siteid); 
			map.put("parentid",this.id); 
			//查询不是栏目回收站的栏目
			map.put("status",1);

			List<Integer> chnlTypes=new ArrayList<Integer>(); 
			List<IrpChannel> chanls=irpChannelService.siteAllChannel(null,map,chnlTypes);   
			if(chanls!=null){
				for (int i = 0; i < chanls.size(); i++) {
					IrpChannel chan=(IrpChannel)chanls.get(i);
					if(chan==null){
						continue;
					} 
					Map<String,Object> item=new HashMap<String, Object>();
					item.put("id",chan.getChannelid());//里面放的应该是siteid的值将它和parentid比较即当做他的父id
					item.put("text",chan.getChnldesc()); 
					//根据他自己查询自己表中是否还有别的字段的父id是他，若有，则他应该是栏目，显示文件夹的形式open
					int nCount = irpChannelService.findChild_Channl_CountByParentId(chan.getChannelid());
					if(nCount>0){
						item.put("state", "closed");
					}else{
						item.put("state", "open");
				} 
					treeNode.add(item);
		 }
		}
		}
		ActionUtil.writer(JsonUtil.list2json(treeNode));  
	} 
	
/*	*//**
	 * 显示站点下的栏目，进行选择，目的是为了添加文档 
	 *//*
	public void toLoadSiteToCheckChannel(){  
		HashMap<String,Object> map=new HashMap<String, Object>();
		map.put("status",1 );
		List<Map<String, Object>> treeNode = new ArrayList<Map<String,Object>>();
		sitename=irpSiteService.findSiteName(siteid); 
		if(id==-1){//第一次进来加载根节点
			Map<String,Object> item=new HashMap<String, Object>();
			item.put("id",0);//即将作为下一节点的parentid
			item.put("text",sitename);
			item.put("state", "closed");
			treeNode.add(item);  
		}else{ 
			map.put("siteid",siteid); 
			map.put("parentid",this.id); 
			//查询不是栏目回收站的栏目
			map.put("status",1);

			List<Integer> chnlTypes=new ArrayList<Integer>(); 
			List<IrpChannel> chanls=irpChannelService.siteAllChannel(null,map,chnlTypes);   
			if(chanls!=null){
				for (int i = 0; i < chanls.size(); i++) {
					IrpChannel chan=(IrpChannel)chanls.get(i);
					if(chan==null){
						continue;
					} 
					Map<String,Object> item=new HashMap<String, Object>();
					item.put("id",chan.getChannelid());//里面放的应该是siteid的值将它和parentid比较即当做他的父id
					item.put("text",chan.getChnldesc()); 
					//根据他自己查询自己表中是否还有别的字段的父id是他，若有，则他应该是栏目，显示文件夹的形式open
					int nCount = irpChannelService.findChild_Channl_CountByParentId(chan.getChannelid());
					if(nCount>0){
						item.put("state", "closed");
					}else{
						item.put("state", "open");
				} 
					treeNode.add(item);
		 }
		}
		}
		ActionUtil.writer(JsonUtil.list2json(treeNode));  
	} */

	public void toStaticSiteToCheckChannel(){  
		HashMap<String,Object> map=new HashMap<String, Object>();
		map.put("status",1 );
		List<Map<String, Object>> treeNode = new ArrayList<Map<String,Object>>();
		sitename=irpSiteService.findSiteName(siteid);   
		if(id==-1){//第一次进来加载根节点
			Map<String,Object> item=new HashMap<String, Object>();
			item.put("id",0);//即将作为下一节点的parentid
			item.put("text",sitename);
			item.put("state", "closed");
			treeNode.add(item);  
		}else{ 
			map.put("siteid",siteid); 
			map.put("parentid",this.id); 
			//查询不是栏目回收站的栏目
			map.put("status",1);

			List<Integer> chnlTypes=new ArrayList<Integer>(); 
			List<IrpChannel> chanls=irpChannelService.siteAllChannel(null,map,chnlTypes);   
			if(chanls!=null){
				for (int i = 0; i < chanls.size(); i++) {
					IrpChannel chan=(IrpChannel)chanls.get(i);
					if(chan==null){
						continue;
					}
					if(chan.getChannelid()!=1&chan.getChannelid()!=2){
					
						Map<String,Object> item=new HashMap<String, Object>();
						item.put("id",chan.getChannelid());//里面放的应该是siteid的值将它和parentid比较即当做他的父id
						item.put("text",chan.getChnldesc()); 
						//根据他自己查询自己表中是否还有别的字段的父id是他，若有，则他应该是栏目，显示文件夹的形式open
						int nCount = irpChannelService.findChild_Channl_CountByParentId(chan.getChannelid());
						if(nCount>0){
							item.put("state", "closed");
							item.put("checked", false);
						}else{
							item.put("checked", false);
							item.put("state", "open");
						}
						treeNode.add(item);
					}
				}	 
				}
		}
		ActionUtil.writer(JsonUtil.list2json(treeNode));  
	} 
	
	 //跳转到添加栏目页面后台
	public String toAddChannel(){ 
		if(this.id==null|| this.id.longValue()==0L){
			 chnlType=IrpChannel.CHANNEL_TYPE_PUBLIC;
		}else{
			IrpChannel parentChannel=irpChannelService.finChannelByChannelid(this.id);
			chnlType=parentChannel.getChnltype();
		} 
		HashMap<String,Object> map=new HashMap<String, Object>();  
		map.put("siteid",siteid); 
		map.put("parentid",this.id);  
		map.put("status",1); 
		//以供添加的栏目在这个级别中的排序之用
		workFlows = irpWorkFlowService.findAllWorkFlow();
		List<Integer> chnlTypes=new ArrayList<Integer>(); 
		irpChannels=irpChannelService.siteAllChannel(null,map,chnlTypes);//他里面的所有一级栏目 
		isChannelOrMapAddOrUp="addchannel";
		int formisdel=10;
		int formstatus=20;
		int isexitindb=10;
		irpForms=irpFormService.getAllForm(formisdel,formstatus,isexitindb);
		return SUCCESS;
	}
	 /**
	  * 跳转到添加栏目页面SDF22
	  * @return
	  */
	public String toAddMap(){  
		if(this.id==null || this.id.longValue()==0L) return ERROR;
		IrpChannel parentChannel= irpChannelService.finChannelByChannelid(this.id);
		this.chnlType=parentChannel.getChnltype();
		HashMap<String,Object> map=new HashMap<String, Object>();  
		map.put("siteid",siteid); 
		map.put("parentid",this.id);  
		map.put("status",1); 
		//以供添加的栏目在这个级别中的排序之用
		List<Integer> chnlTypes=new ArrayList<Integer>();
		chnlTypes.add(IrpChannel.CHANNEL_TYPE_MAP);
		maptype=maptype;
		irpChannels=irpChannelService.siteAllChannel(null,map,chnlTypes);//他里面的所有一级栏目 
		isChannelOrMapAddOrUp="addmap";
		return SUCCESS;
	}
	
	/**
	 * 查询出本级所有专题
	 * @return
	 */
	public String toAddsub(){
		if(this.id==null || this.id.longValue()==0L) return ERROR;
		IrpChannel parentChannel= irpChannelService.finChannelByChannelid(this.id);
		this.chnlType=parentChannel.getChnltype();
		HashMap<String,Object> map=new HashMap<String, Object>();  
		map.put("siteid",siteid); 
		map.put("parentid",this.id);  
		map.put("status",1); 
		//以供添加的栏目在这个级别中的排序之用
		List<Integer> chnlTypes=new ArrayList<Integer>();
		chnlTypes.add(IrpChannel.CHANNEL_TYPE_SUBJECT);
		irpChannels=irpChannelService.siteAllChannel(null,map,chnlTypes);//他里面的所有一级栏目 
		isChannelOrMapAddOrUp="addmap";
		return SUCCESS;
	}
	
	/**
	 * 查询出本级所有专题(super)
	 * @return
	 */
	public String toAddSuperSub(){
		if(this.id==null || this.id.longValue()==0L) return ERROR;
		IrpChannel parentChannel= irpChannelService.finChannelByChannelid(this.id);
		this.chnlType=parentChannel.getChnltype();
		HashMap<String,Object> map=new HashMap<String, Object>();  
		map.put("siteid",siteid); 
		map.put("parentid",this.id);  
		map.put("status",1); 
		//以供添加的栏目在这个级别中的排序之用
		List<Integer> chnlTypes=new ArrayList<Integer>();
		chnlTypes.add(IrpChannel.CHANNEL_TYPE_SUBJECT);
		chnlTypes.add(IrpChannel.CHANNEL_TYPE_SUPER_SUBJECT);
		irpChannels=irpChannelService.siteAllChannel(null,map,chnlTypes);//他里面的所有一级栏目 
		isChannelOrMapAddOrUp="addmap";
		return SUCCESS;
	}
	
	private String formTableName;
	
	
	public String getFormTableName() {
		return formTableName;
	}
	public void setFormTableName(String formTableName) {
		this.formTableName = formTableName;
	}
	private Long formid;
	private String jsonFile;
	private IrpAttachedTypeService irpAttachedTypeService;
	private IrpAttachedService irpAttachedService;
	public Long getFormid() {
		return formid;
	}
	public void setFormid(Long formid) {
		this.formid = formid;
	}
	/**
	 * 添加栏目
	 */
	public void adminAddChannel(){
		long tableid=TableIdUtil.getNextId(IrpChannel.TABLE_NAME);
		irpChannel.setChannelid(tableid);
		if (jsonFile != null && jsonFile != "") { 
			try{
				// 如果存在附件，则添加附件
				JSONArray _Array = JSONArray.fromObject(jsonFile);
				for (int i = 0; i < _Array.size(); i++) {
					JSONObject jsonObject = (JSONObject) _Array
							.getJSONObject(i);
					String sattfile = jsonObject.getString("attfile");
					String sattdesc = jsonObject.getString("attdesc");
					String sattlinkalt = jsonObject.getString("attlinkalt");
					String seditversions = jsonObject.getString("editversions");
					String attflag = jsonObject.getString("attflag");
					// 获得文件类型
					Long typeid = irpAttachedTypeService
							.findAttachedTypeIdByFileExt(FileUtil
									.findFileExt(sattfile));
					// 入库
				List<Long> 	_attachedids=addAttQuestionFile(Integer.parseInt(attflag), sattfile,
							typeid, tableid, sattdesc, sattlinkalt, seditversions,
							false, null, false);
				if(_attachedids!=null){
					String medalimage="";
					for (Long long1 : _attachedids) {
						medalimage=long1+","+medalimage;
					}
					irpChannel.setImageid(medalimage);
				}
				irpAttachedService.deleteFileNotInList(_attachedids, tableid, IrpAttached.MAP_CHANNEL);
				}
			}catch (Exception e) {
					// TODO: handle exception
			}
		}else{
			irpChannel.setImageid("");
		}
		int nRows = irpChannelService.adminAddChannel(irpChannel);
		Long form=irpChannel.getFormid();
		if(form!=null){
			IrpForm irpForm = irpFormService.getFormById(form);
			if(irpForm!=null&&!irpForm.equals("")){
				irpForm.setChannelid(irpChannel.getChannelid());
				irpFormService.updateForm(irpForm);
			}
		}
		ActionUtil.writer(String.valueOf(nRows));
	} 
	 /***
		 * 添加附件信息到数据库
		 * 
		 * @param _attflag
		 * @param _sAttFile
		 * @param TypeId
		 * @param document
		 * @param _sAttDesc
		 * @param _sAttLinkAlt
		 * @param _sEditversions
		 * @param isClient
		 * @param _touChannelid
		 * @param addUserFile
		 * @return
		 */
		private ArrayList<Long> addAttQuestionFile(Integer _attflag,
				String _sAttFile, Long TypeId, Long _docid, String _sAttDesc,
				String _sAttLinkAlt, String _sEditversions, boolean isClient,
				Long _touChannelid, Boolean addUserFile) {
			ArrayList<Long> _attachedids = new ArrayList<Long>();
			String filePath = SysFileUtil.getFilePathByFileName(_sAttFile);
			File newFile = new File(filePath);
			String newFileName = "";
			if (newFile.exists()) {
				if (IrpAttachedType.JPG_FIELD_NAME.toString().equals(
						TypeId.toString())) { // 这是创建文档之后的附件名字
					newFileName = SysFileUtil.saveFileDoc(newFile,
							SysFileUtil.FILE_TYPE_ATTACHED_PIC, addUserFile);
				} else {
					newFileName = SysFileUtil.saveFileDoc(newFile,
							SysFileUtil.FILE_TYPE_ATTACHED_FILE, addUserFile);
				}
				// 删除临时表中的文件
				String newFilePath = SysFileUtil.getFilePathByFileName(newFileName);

				Long _attachedid = irpAttachedService.addFile(_docid, 0L,
						newFileName, _sAttLinkAlt, _sAttDesc,
						IrpAttached.MAP_CHANNEL, newFilePath,
						Integer.parseInt(_sEditversions), TypeId, _attflag);
				_attachedids.add(_attachedid);
			}
			return _attachedids;

		}
	/**
	 * 新建检索专题
	 */
	public void addnormalchannel(){
		irpChannel.setChnltype(IrpChannel.CHANNEL_TYPE_SUBJECT);
		int nRows = irpChannelService.adminAddChannel(irpChannel);
		ActionUtil.writer(String.valueOf(nRows));
	}
	
	/**
	 * 查询地图列表
	 * @return
	 */
	public String allMapList(){
		HashMap<String,Object> map=new HashMap<String, Object>();     
		map.put("siteid",siteid); 
		map.put("parentid",this.id);  
		map.put("status",1);
		if(searchWord!=null && searchWord.length()>0){
			searchWord = ActionUtil.decode(searchWord);
			map.put("searchWord", searchWord);
			map.put("searchType", searchType); 
		} 
		int nDataCount = irpChannelService.channelCountMap(map); 
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount); 
		List<Integer> chnlTypes=new ArrayList<Integer>(); 
		chnlTypes.add(IrpChannel.CHANNEL_TYPE_MAP); 
		irpChannels=irpChannelService.siteAllChannel(pageUtil,map,chnlTypes);//他里面的所有一级栏目  
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	/**
	 * 查询专题列表
	 * @return
	 */
	public String allSubjectList(){
		HashMap<String,Object> map=new HashMap<String, Object>();     
		map.put("siteid",siteid); 
		map.put("parentid",this.id);  
		map.put("status",1);
		if(searchWord!=null && searchWord.length()>0){
			searchWord = ActionUtil.decode(searchWord);
			map.put("searchWord", searchWord);
			map.put("searchType", searchType); 
		} 
		int nDataCount = irpChannelService.channelCountMap(map); 
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount); 
		List<Integer> chnlTypes=new ArrayList<Integer>(); 
		chnlTypes.add(IrpChannel.CHANNEL_TYPE_SUBJECT); 
		chnlTypes.add(IrpChannel.CHANNEL_TYPE_SUPER_SUBJECT);
		irpChannels=irpChannelService.siteAllChannel(pageUtil,map,chnlTypes);//他里面的所有一级栏目  
		this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	/**
	 * 查看栏目或站点里面一级栏目
	 * @return
	 */ 
	public String channelInfo(){   
		HashMap<String,Object> map=new HashMap<String, Object>();     
		if(id!=null && id!=0 && id!=-1){ //栏目&& id!=-1 &&id!=0
			irpChannel=irpChannelService.finChannelByChannelid(id);
			map.put("siteid",siteid); 
			map.put("parentid",this.id);  
			map.put("status",1);
			if(searchWord!=null && searchWord.length()>0){
				searchWord = ActionUtil.decode(searchWord);
				map.put("searchWord", searchWord);
				map.put("searchType", searchType); 
			} 
			int nDataCount = irpChannelService.channelCountMap(map); 
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount); 
			List<Integer> chnlTypes=new ArrayList<Integer>(); 
			irpChannels=irpChannelService.siteAllChannel(pageUtil,map,chnlTypes);//他里面的所有一级栏目  
			this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		}else{ //站点
			map.put("siteid",siteid); 
			map.put("parentid",new Long(0));   
			map.put("status",1); 
			if(searchWord!=null && searchWord.length()>0){
				searchWord = ActionUtil.decode(searchWord);
				map.put("searchWord", searchWord);
				map.put("searchType", searchType); 
			} 
			int nDataCount = irpChannelService.channelCountMap(map); 
			PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize, nDataCount); 
			this.id=new Long(0); //后来加的，目的是为了不让站点的操作栏目出错
			List<Integer> chnlTypes=new ArrayList<Integer>(); 
			irpChannels=irpChannelService.siteAllChannel(pageUtil,map,chnlTypes);//他里面的所有一级栏目  
			this.pageHTML = pageUtil.getPageHtml("page(#PageNum#)");
		}
		return SUCCESS;
	}
	
	public String toDocumentSubject(){
		return SUCCESS;
	}
	public String toChannelInfo(){   
		return SUCCESS;
	}
	public String to_documentSub(){
		return SUCCESS;
	}
	/***
	 * 前台根据各个栏目id获取下面的子栏目用于选择知识地图
	 * 获取他的知识地图
	 */
	public void clientToCheckdocumentMap(){
		HashMap<String,Object> map=new HashMap<String, Object>();
		List<Map<String, Object>> treeNode = new ArrayList<Map<String,Object>>();
		map.put("status",1);//查询没有进入栏目回收站的栏目
		if(id==null || id==-1 && (channelid!=null && channelid>0)){
			Map<String,Object> item=new HashMap<String, Object>();
			IrpChannel rootChannel=irpChannelService.finChannelByChannelid(this.channelid);
			String rootName=rootChannel.getChnldesc();
			item.put("id",rootChannel.getChannelid());//即将作为下一节点的parentid
			item.put("text",rootName);
			int rootCount = irpChannelService.findChild_Channl_CountByParentId(this.channelid);
			if(rootCount>0){
				item.put("state", "closed");
			}else{
				item.put("state", "open");
			}
			treeNode.add(item);  
		}
		else{
			map.put("parentid",this.id);
			map.put("publishpro", 1);
			List<Integer> chnlTypes=new ArrayList<Integer>(); 
			chnlTypes.add(IrpChannel.CHANNEL_TYPE_MAP);
			List<IrpChannel> chanls=irpChannelService.siteAllChannel(null,map,chnlTypes); 
			if(chanls!=null){
				for (int i = 0; i < chanls.size(); i++) {
					IrpChannel chan=(IrpChannel)chanls.get(i);
					if(chan==null){
						continue;
					} 
					Map<String,Object> item=new HashMap<String, Object>();
					item.put("id",chan.getChannelid());//里面放的应该是siteid的值将它和parentid比较即当做他的父id
					item.put("text",chan.getChnldesc());
					//根据他自己查询自己表中是否还有别的字段的父id是他，若有，则他应该是栏目，显示文件夹的形式open
					int nCount = irpChannelService.findChild_Channl_CountByParentId(chan.getChannelid());
					if(nCount>0){
						item.put("state", "closed");
					}else{
						item.put("state", "open");
					}
					treeNode.add(item);
				} 
			} 
		}
		ActionUtil.writer(JsonUtil.list2json(treeNode));
	}
	//获取他的知识地图
	public void documentMap(){
		HashMap<String,Object> map=new HashMap<String, Object>();
		List<Map<String, Object>> treeNode = new ArrayList<Map<String,Object>>();
		map.put("status",1);//查询没有进入栏目回收站的栏目
		if(this.id!=null && this.id==-1){
			//将跟栏目设置为根节点
			IrpChannel rootChannel=irpChannelService.finChannelByChannelid(Long.parseLong(SysConfigUtil.getSysConfigValue("DOCUMENT_MAP_ID")));
			
			String rootName=rootChannel.getChnldesc();
			Map<String,Object> item=new HashMap<String, Object>();
			item.put("id",rootChannel.getChannelid());//即将作为下一节点的parentid
			item.put("text",rootName);
			item.put("state", "closed");
			Long maptype= rootChannel.getMaptype();
			if(maptype!=null){
				if(maptype==IrpChannel.CHANNEL_TYPE_MAP_ONE){
					item.put("iconCls", "icon-tip");
				}else if(maptype==IrpChannel.CHANNEL_TYPE_MAP_TWO){
					item.put("iconCls", "icon-redo");
				}else if(maptype==IrpChannel.CHANNEL_TYPE_MAP_THREE){
					item.put("iconCls", "icon-star");
				}else if(maptype==IrpChannel.CHANNEL_TYPE_MAP_OTHER){
					
				}
			}
			treeNode.add(item);
		} else{
			map.put("parentid",this.id); 
			List<Integer> chnlTypes=new ArrayList<Integer>();
			chnlTypes.add(IrpChannel.CHANNEL_TYPE_MAP);
			List<IrpChannel> chanls=irpChannelService.siteAllChannel(null,map,chnlTypes); 
			if(chanls!=null){
				for (int i = 0; i < chanls.size(); i++) {
					IrpChannel chan=(IrpChannel)chanls.get(i);
					if(chan==null){
						continue;
					}
					Map<String,Object> item=new HashMap<String, Object>();
					item.put("id",chan.getChannelid());//里面放的应该是siteid的值将它和parentid比较即当做他的父id
					item.put("text",chan.getChnldesc());
					Long maptype= chan.getMaptype();
					if(maptype!=null){
						if(maptype.toString().equals(IrpChannel.CHANNEL_TYPE_MAP_ONE.toString())){
							item.put("iconCls", "icon-tip");
						}else if(maptype.toString().equals(IrpChannel.CHANNEL_TYPE_MAP_TWO.toString())){
							item.put("iconCls", "icon-star");
						}else if(maptype.toString().equals(IrpChannel.CHANNEL_TYPE_MAP_THREE.toString())){
							item.put("iconCls", "icon-sum");
						}else if(maptype.toString().equals(IrpChannel.CHANNEL_TYPE_MAP_OTHER.toString())){
							
						}
					}
					//根据他自己查询自己表中是否还有别的字段的父id是他，若有，则他应该是栏目，显示文件夹的形式open
					int nCount = irpChannelService.findChild_Channl_CountByParentId(chan.getChannelid());
					if(nCount>0){
						item.put("state", "closed");
					}else{
						item.put("state", "open");
					}
					treeNode.add(item);
				} 
			}
		} 
		ActionUtil.writer(JsonUtil.list2json(treeNode));
	}
	
	//获取知识专题
	public void documentSubjectMap(){
		HashMap<String,Object> map=new HashMap<String, Object>();
		List<Map<String, Object>> treeNode = new ArrayList<Map<String,Object>>();
		map.put("status",1);//查询没有进入栏目回收站的栏目
		if(this.id!=null && this.id==-1){
			//将跟栏目设置为根节点
			IrpChannel rootChannel=irpChannelService.finChannelByChannelid(Long.parseLong(SysConfigUtil.getSysConfigValue("DOCUMENT_SUBJECT_ID")));
			String rootName=rootChannel.getChnlname();
			Map<String,Object> item=new HashMap<String, Object>();
			item.put("id",rootChannel.getChannelid());//即将作为下一节点的parentid
			item.put("text",rootName);
			item.put("state", "closed");
			treeNode.add(item);
		} else{
			map.put("parentid",this.id); 
			List<Integer> chnlTypes=new ArrayList<Integer>();
			chnlTypes.add(IrpChannel.CHANNEL_TYPE_SUBJECT);
			chnlTypes.add(IrpChannel.CHANNEL_TYPE_SUPER_SUBJECT);
			List<IrpChannel> chanls=irpChannelService.siteAllChannel(null,map,chnlTypes); 
			if(chanls!=null){
				for (int i = 0; i < chanls.size(); i++) {
					IrpChannel chan=(IrpChannel)chanls.get(i);
					if(chan==null){
						continue;
					}
					Map<String,Object> item=new HashMap<String, Object>();
					item.put("id",chan.getChannelid());//里面放的应该是siteid的值将它和parentid比较即当做他的父id
					item.put("text",chan.getChnlname());
					//根据他自己查询自己表中是否还有别的字段的父id是他，若有，则他应该是栏目，显示文件夹的形式open
					int nCount = irpChannelService.findChild_Channl_CountByParentId(chan.getChannelid());
					if(nCount>0){
						item.put("state", "closed");
					}else{
						item.put("state", "open");
					}
					if(chan.getChnltype()!=null && chan.getChnltype()==IrpChannel.CHANNEL_TYPE_SUPER_SUBJECT){
						item.put("iconCls", "icon-star");
					}
					treeNode.add(item);
				} 
			}
		}
		ActionUtil.writer(JsonUtil.list2json(treeNode));
	}
	
	public void siteAllChannel(){  
		HashMap<String,Object> map=new HashMap<String, Object>();
		List<Map<String, Object>> treeNode = new ArrayList<Map<String,Object>>();
		map.put("siteid",siteid);
		map.put("parentid",this.id);  
		if(id==-1){  
			sitename=irpSiteService.findSiteName(siteid);//查询站点名称构造根节点 
			Map<String,Object> item=new HashMap<String, Object>();
			item.put("id",0);//即将作为下一节点的parentid
			item.put("text",sitename);
			item.put("state", "closed");
			treeNode.add(item);
		}else{ 
			map.put("status",1);//查询没有进入栏目回收站的栏目
			List<Integer> chnlTypes=new ArrayList<Integer>(); 
			List<IrpChannel> chanls=irpChannelService.siteAllChannel(null,map,chnlTypes); 
			if(chanls!=null){
				for (int i = 0; i < chanls.size(); i++) {
					IrpChannel chan=(IrpChannel)chanls.get(i);
					if(chan==null){
						continue;
					}
					Map<String,Object> item=new HashMap<String, Object>();
					item.put("id",chan.getChannelid());//里面放的应该是siteid的值将它和parentid比较即当做他的父id
					item.put("text",chan.getChnldesc());
					if(chan.getChnltype()!=null && chan.getChnltype()!=0){
						if(chan.getChnltype().toString().equals(String.valueOf(IrpChannel.CHANNEL_TYPE_MONTH_TOP)) || chan.getChnltype().toString().equals(String.valueOf(IrpChannel.CHANNEL_TYPE_MAP))|| chan.getChnltype().toString().equals(String.valueOf(IrpChannel.CHANNEL_TYPE_GONGGAO))){
							item.put("iconCls", "icon-tip");
						}
					}
					//根据他自己查询自己表中是否还有别的字段的父id是他，若有，则他应该是栏目，显示文件夹的形式open
					int nCount = irpChannelService.findChild_Channl_CountByParentId(chan.getChannelid());
					if(nCount>0){
						item.put("state", "closed");
					}else{
						item.put("state", "open");
					}
					treeNode.add(item);
				} 
			}
		} 
		ActionUtil.writer(JsonUtil.list2json(treeNode));
	} 
	/**
	 * 查看栏目子类数量
	 */
	public void findChild_Channl_CountByParentId(){
		//查看这个以这个id为父id的子栏目的数量
		int nCount = irpChannelService.findChild_Channl_CountByParentId(channelid);
 		ActionUtil.writer(String.valueOf(nCount));
	}
	
	/**
	 * 获取用户专题
	 * @return
	 */
	public String userSubject(){
		//查询用户创建的专题
		IrpUser loginUser = LoginUtil.getLoginUser();
		IrpChannelExample example1 = new IrpChannelExample();
		Criteria criteria1 = example1.createCriteria();
		criteria1.andParentidEqualTo(Long.valueOf(SysConfigUtil.getSysConfigValue("DOCUMENT_PERSON_SUBJECT_ID")));
		criteria1.andChnlnameEqualTo(loginUser.getUsername());
		criteria1.andCruseridEqualTo(loginUser.getUserid());
		criteria1.andChnltypeEqualTo(IrpChannel.CHANNEL_TYPE_SUBJECT);
		List<IrpChannel> channelAllList = irpChannelService.findChannelByExample(example1);
		if(channelAllList!=null && channelAllList.size()>0){
			irpChannel = channelAllList.get(0);
			IrpChannelExample example2 = new IrpChannelExample();
			Criteria criteria2 = example2.createCriteria();
			criteria2.andParentidEqualTo(irpChannel.getChannelid());
			criteria2.andChnltypeEqualTo(IrpChannel.CHANNEL_TYPE_SUBJECT);
			example2.setOrderByClause("channelid desc");
			irpChannels = irpChannelService.findChannelByExample(example2);
			if(irpChannels!=null && irpChannels.size()==0){
				irpChannels = null;
			}
		}else{
			irpChannels = null;
		}
		personalSearchs = irpPersonalSearchService.findPersonalSearchByUserId(loginUser.getUserid());
		return SUCCESS;
	}
	private String formname;
	
	public String getFormname() {
		return formname;
	}
	public void setFormname(String formname) {
		this.formname = formname;
	}
	private IrpFormColumnService irpFormColumnService;
	
	public IrpFormColumnService getIrpFormColumnService() {
		return irpFormColumnService;
	}
	public void setIrpFormColumnService(IrpFormColumnService irpFormColumnService) {
		this.irpFormColumnService = irpFormColumnService;
	}
	public void findchannelbyformid(){
		irpChannel=irpChannelService.finChannelByChannelid(channelid);
		IrpForm irpForm=irpFormService.getFormById(irpChannel.getFormid());
		formname=irpForm.getFormtablename();
		List<IrpFormColumn> list=irpFormColumnService.getListBytablename(formname);
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(list);
		ActionUtil.writer(jsonArray.toString());
	}
	public String todocumentSub(){
		return SUCCESS;
	}
	public String site_word(){
		return SUCCESS;
	} 
	public IrpChannelService getIrpChannelService() {
		return irpChannelService;
	} 
	public void setIrpChannelService(IrpChannelService irpChannelService) {
		this.irpChannelService = irpChannelService;
	} 
	public IrpChannel getIrpChannel() {
		return irpChannel;
	} 
	public void setIrpChannel(IrpChannel irpChannel) {
		this.irpChannel = irpChannel;
	} 
	public List<IrpChannel> getIrpChannels() {
		return irpChannels;
	} 
	public void setIrpChannels(List<IrpChannel> irpChannels) {
		this.irpChannels = irpChannels;
	}
	public List<IrpUserFile> getPersonAllFile() {
		return personAllFile;
	}
	public void setPersonAllFile(List<IrpUserFile> personAllFile) {
		this.personAllFile = personAllFile;
	}
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public IrpSiteService getIrpSiteService() {
		return irpSiteService;
	}
	public void setIrpSiteService(IrpSiteService irpSiteService) {
		this.irpSiteService = irpSiteService;
	}


	public Long getSiteid() {
		return siteid;
	}


	public void setSiteid(Long siteid) {
		this.siteid = siteid;
	}

	public String getSitename() {
		return sitename;
	}
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}
	public Long[] getChannelids() {
		return channelids;
	}
	public void setChannelids(Long[] channelids) {
		this.channelids = channelids;
	}
	public int getIsGCChannel() {
		return isGCChannel;
	}
	public void setIsGCChannel(int isGCChannel) {
		this.isGCChannel = isGCChannel;
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
	public String getPageHTML() {
		return pageHTML;
	}
	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}
	public String getOrderField() {
		return orderField;
	}
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getChnlName() {
		return chnlName;
	}
	public void setChnlName(String chnlName) {
		this.chnlName = chnlName;
	}
	public Long getChannelid() {
		return channelid;
	}
	public void setChannelid(Long channelid) {
		this.channelid = channelid;
	}
	public String getChnlNameToConfim() {
		return chnlNameToConfim;
	}
	public void setChnlNameToConfim(String chnlNameToConfim) {
		this.chnlNameToConfim = chnlNameToConfim;
	}

	public String getChannelorDocument() {
		return channelorDocument;
	}

	public void setChannelorDocument(String channelorDocument) {
		this.channelorDocument = channelorDocument;
	}
	public Long getPersonid() {
		return personid;
	}
	public void setPersonid(Long personid) {
		this.personid = personid;
	}
	//知识专题
	public String subjectManage() {
		return SUCCESS;
	}
}
