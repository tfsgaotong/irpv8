package com.tfs.irp.topic.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.channel.entity.ComparatorImpl;
import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.channel.entity.IrpChannelExample;
import com.tfs.irp.channel.entity.IrpChannelExample.Criteria;
import com.tfs.irp.channel.service.IrpChannelService;
import com.tfs.irp.documentmap.entity.IrpDocumentMap;
import com.tfs.irp.documentmap.entity.IrpDocumentMapExample;
import com.tfs.irp.documentmap.service.IrpDocumentMapService;
import com.tfs.irp.topic.entity.IrpTopic;
import com.tfs.irp.topic.service.IrpTopicService;
import com.tfs.irp.topiclink.entity.IrpTopicLink;
import com.tfs.irp.topiclink.service.IrpTopicLinkService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;

public class IrpTopicAction extends ActionSupport {

	private IrpTopicService irpTopicService;
	
	private List<IrpTopic> irptopiclist;
	
	private Long topicid;
	
	private IrpTopicLinkService irpTopicLinkService;
	
	private String topicname;
	
	private IrpTopic irpTopic;
	
	private Integer topicdescnum;
	
	private Integer topicnamenum;
	
	private IrpTopic irpTopicObj;

	private String optionvaltopic;
	
	private IrpUserService irpUserService;
	
	private IrpChannelService irpChannelService;
	
	private IrpChannel irpChannel;
	
	private List<IrpChannel> channels;
	
	private IrpDocumentMapService irpDocumentMapService;
	
	public IrpDocumentMapService getIrpDocumentMapService() {
		return irpDocumentMapService;
	}

	public void setIrpDocumentMapService(IrpDocumentMapService irpDocumentMapService) {
		this.irpDocumentMapService = irpDocumentMapService;
	}

	public IrpChannel getIrpChannel() {
		return irpChannel;
	}

	public void setIrpChannel(IrpChannel irpChannel) {
		this.irpChannel = irpChannel;
	}

	public List<IrpChannel> getChannels() {
		return channels;
	}

	public void setChannels(List<IrpChannel> channels) {
		this.channels = channels;
	}

	public IrpChannelService getIrpChannelService() {
		return irpChannelService;
	}

	public void setIrpChannelService(IrpChannelService irpChannelService) {
		this.irpChannelService = irpChannelService;
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public String getOptionvaltopic() {
		return optionvaltopic;
	}

	public void setOptionvaltopic(String optionvaltopic) {
		this.optionvaltopic = optionvaltopic;
	}

	public Integer getTopicnamenum() {
		return topicnamenum;
	}

	public void setTopicnamenum(Integer topicnamenum) {
		this.topicnamenum = topicnamenum;
	}

	public Integer getTopicdescnum() {
		return topicdescnum;
	}

	public void setTopicdescnum(Integer topicdescnum) {
		this.topicdescnum = topicdescnum;
	}

	public IrpTopic getIrpTopic() {
		return irpTopic;
	}

	public void setIrpTopic(IrpTopic irpTopic) {
		this.irpTopic = irpTopic;
	}

	public String getTopicname() {
		return topicname;
	}

	public void setTopicname(String topicname) {
		this.topicname = topicname;
	}

	public IrpTopicLinkService getIrpTopicLinkService() {
		return irpTopicLinkService;
	}

	public void setIrpTopicLinkService(IrpTopicLinkService irpTopicLinkService) {
		this.irpTopicLinkService = irpTopicLinkService;
	}

	public Long getTopicid() {
		return topicid;
	}

	public void setTopicid(Long topicid) {
		this.topicid = topicid;
	}

	public List<IrpTopic> getIrptopiclist() {
		return irptopiclist;
	}

	public void setIrptopiclist(List<IrpTopic> irptopiclist) {
		this.irptopiclist = irptopiclist;
	}

	public IrpTopicService getIrpTopicService() {
		return irpTopicService;
	}

	public void setIrpTopicService(IrpTopicService irpTopicService) {
		this.irpTopicService = irpTopicService;
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

	private String pageHTML = "";
	private int pageNum = 1;

	private int pageSize = 10;
	
	
	
	/**
	 * 获得所有专题列表
	 * @return
	 */
	public String getAllTopicMenu(){
		PageUtil pageUtil = new PageUtil(pageNum, pageSize, this.irpTopicService.getAllIrpTopicLength(IrpTopic.TOPICTYPE_NORMAL,optionvaltopic));
		irptopiclist = this.irpTopicService.getAllIrpTopic(pageUtil,IrpTopic.TOPICTYPE_NORMAL,optionvaltopic);
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		return SUCCESS;
	}
	/**
	 * 删除专题
	 * @return
	 */
	public void deleteTopicById(){
		int status = 0;
		IrpUser irpUser = LoginUtil.getLoginUser();
		if(irpUser.isTopicManager()) {
			int msg = this.irpTopicService.deleteTopicById(topicid);
			List<IrpTopicLink> list = this.irpTopicLinkService.getIrpTopicLinkByName(topicname);
			if(list.size()>0){
				if(msg==1){
					status =this.irpTopicLinkService.updateTopicLink(topicname);
				}
			}else{
				status = 1;
			}
		}
		ActionUtil.writer(status+"");
	}
	/**
	 * 加载修改描述框
	 * @return
	 */
	public String initUpateDescFrame(){
		
		irpTopic = this.irpTopicService.getIrpTopicById(topicid);
		topicdescnum = SysConfigUtil.getSysConfigNumValue(IrpTopic.TOPICDESCNUM);
		return SUCCESS;
	}
	/**
	 * 加载增加专题框
	 * @return
	 */
	public String initAddDescFrame(){
		irpTopic = new IrpTopic();
		topicnamenum = SysConfigUtil.getSysConfigNumValue(IrpTopic.TOPICNAMENUM);
		topicdescnum = SysConfigUtil.getSysConfigNumValue(IrpTopic.TOPICDESCNUM);
		return SUCCESS;
	}
	
	/**
	 * 保存修改后的主题
	 * @return
	 */
	public void saveIrpTopic(){
     int msg = this.irpTopicService.updateTopicDesc(irpTopic);
		ActionUtil.writer(msg+"");
	}
	/**
	 * 管理员增加新的主题
	 * @return
	 */
	public String saveIrpTopicNameDesc(){
		
		long status = this.irpTopicService.addTopic(irpTopic);
		if(status > 0){
			irpTopicObj = this.irpTopicService.getIrpTopicById(status);
		}
		
		
		return SUCCESS;
	}

	public IrpTopic getIrpTopicObj() {
		return irpTopicObj;
	}

	public void setIrpTopicObj(IrpTopic irpTopicObj) {
		this.irpTopicObj = irpTopicObj;
	}
	/**
	 * 验证名字是否已经存在
	 */
	public void getCheckingTopicName(){
	
		int msg = 0;
		IrpTopic irpTopic = null;
		irpTopic = this.irpTopicService.getIrpTopic(topicname.trim());
		if(irpTopic!=null){
			msg = 1;
		}
		ActionUtil.writer(""+msg);
	}
	/**
	 * 判断此专题是否通过了审核
	 */
	public void topicAuditBool(){
	   int msgs = 0;
	   IrpTopic irpTopic = this.irpTopicService.getIrpTopicById(Long.parseLong(topicname));
	   if(irpTopic!=null){
		   msgs = irpTopic.getTopictype();
	   }
	   ActionUtil.writer(msgs+"");
	}
	/**
	 * 查看所有的未通过审核的专题
	 * @return
	 */
	public String getAllAuditMenu(){
		
		PageUtil pageUtil = new PageUtil(pageNum, pageSize, this.irpTopicService.getAllIrpTopicLength(IrpTopic.TOPICTYPE_ILLEGALITY,optionvaltopic));
		irptopiclist = this.irpTopicService.getAllIrpTopic(pageUtil,IrpTopic.TOPICTYPE_ILLEGALITY,optionvaltopic);
		this.pageHTML = pageUtil.getClientPageHtml("pagetopic(#PageNum#)");

		
		return SUCCESS;
	}
	/**
	 * 查看我的专题
	 * @return
	 */
	public String topicMineMenu(){
		
		PageUtil pageUtil = new PageUtil(pageNum, pageSize, this.irpTopicService.getAllIrpTopicByMineCount(LoginUtil.getLoginUserId(),optionvaltopic));
		irptopiclist = this.irpTopicService.getAllIrpTopicByMine(pageUtil,LoginUtil.getLoginUserId(),optionvaltopic);
		this.pageHTML = pageUtil.getClientPageHtml("pagetopic(#PageNum#)");

		return SUCCESS;
		
	}
	
	/**
	 * 查看我的知识专题
	 * @return
	 */
	public String subjectmenumine(){
		//查询用户创建的专题
		IrpChannelExample example1 = new IrpChannelExample();
		Criteria criteria1 = example1.createCriteria();
		criteria1.andParentidEqualTo(Long.valueOf(SysConfigUtil.getSysConfigValue("DOCUMENT_PERSON_SUBJECT_ID")));
		criteria1.andChnlnameEqualTo(LoginUtil.getLoginUser().getUsername());
		criteria1.andCruseridEqualTo(LoginUtil.getLoginUserId());
		criteria1.andChnltypeEqualTo(IrpChannel.CHANNEL_TYPE_SUBJECT);
		criteria1.andSiteidEqualTo(0L);
		List<IrpChannel> channelAllList = irpChannelService.findChannelByExample(example1);
		
		if(channelAllList!=null && channelAllList.size()>0){
			IrpChannel irpChannel = channelAllList.get(0);
			IrpChannelExample example2 = new IrpChannelExample();
			Criteria criteria2 = example2.createCriteria();
			if(optionvaltopic!=null && "crtime desc".equals(optionvaltopic)){
				example2.setOrderByClause(optionvaltopic);
			}
			criteria2.andStatusEqualTo(IrpChannel.IS_DOCSTATIUS);
			criteria2.andParentidEqualTo(irpChannel.getChannelid());
			List<IrpChannel> listChannels = irpChannelService.findChannelByExample(example2);
			if(listChannels!=null && listChannels.size()>0){
				PageUtil pageUtil = new PageUtil(pageNum, pageSize, listChannels.size());
				channels = irpChannelService.selectByExample(pageUtil, example2);
				if(channels!=null && channels.size()>0){
					for(IrpChannel ele : channels){
						ele.setKnowledgeNum(getKnowledgeNum(ele.getChannelid().toString()));
					}
					if(optionvaltopic==null || "topicclicknum desc".equals(optionvaltopic)){
						//按照知识数量排序
						Comparator<IrpChannel> compare = new ComparatorImpl();
						Collections.sort(channels, compare);
					}
					this.pageHTML = pageUtil.getClientPageHtml("pagetopic(#PageNum#)");
				}else{
					channels = null;
				}
				
			}else{
				channels = null;
			}
		}else{
			channels = null;
		}
		return SUCCESS;
	}
	
	
	/**
	 * 通过一个专题
	 */
	public void topicItemPass(){
		
       int status =  this.irpTopicService.updateTopicType(topicid,IrpTopic.TOPICTYPE_NORMAL);
       if(status>0){
    	   this.irpTopicLinkService.updateIrpTopicLink(topicname,IrpTopicLink.TOPICLINKNORMAL);
       }
		ActionUtil.writer(status+"");
	}
	/**
	 * 根据用户名获得页面显示的名字
	 * @return
	 */
	public String getShowName(String _username){
		return this.irpUserService.findShowNameByUsername(_username);
	}
	
	/**
	 * 根据id查询名称
	 * @param userid
	 * @return
	 */
	public String getNickName(String userid){
		IrpUser user = irpUserService.findUserByUserId(Long.valueOf(userid));
		return user.getNickname()==null?user.getUsername():user.getNickname();
	}
	
	/**
	 * 获得专题所引用知识的数量
	 * @param channelid
	 * @return
	 */
	public Integer getKnowledgeNum(String channelid){
		IrpDocumentMapExample example = new IrpDocumentMapExample();
		example.createCriteria().andChannelidEqualTo(Long.valueOf(channelid))
								.andTypeEqualTo(IrpDocumentMap.KNOWLEDGE_SUBJECT);
		List list = irpDocumentMapService.selectMapByExample(example);
		if(list!=null && list.size()>0){
			return list.size();
		}else{
			return 0;
		}
	}
	
	/**
	 * 初始化待审核topic
	 */
	public String getUnauditTopic(){
		PageUtil pageUtil = new PageUtil(pageNum, pageSize, this.irpTopicService.getAllIrpTopicLength(IrpTopic.TOPICTYPE_ILLEGALITY,optionvaltopic));
		irptopiclist = this.irpTopicService.getAllIrpTopic(pageUtil,IrpTopic.TOPICTYPE_ILLEGALITY,optionvaltopic);
		this.pageHTML = pageUtil.getClientPageHtml("pagetopic(#PageNum#)");
		return SUCCESS;
	}
	/**
	 * 初始化待已审核topic
	 */
	public String getAuditTopic(){
		PageUtil pageUtil = new PageUtil(pageNum, pageSize, this.irpTopicService.getAllIrpTopicLength(IrpTopic.TOPICTYPE_NORMAL,optionvaltopic));
		irptopiclist = this.irpTopicService.getAllIrpTopic(pageUtil,IrpTopic.TOPICTYPE_NORMAL,optionvaltopic);
		this.pageHTML = pageUtil.getClientPageHtml("pagetopic(#PageNum#)");
		return SUCCESS;
	}
	public IrpUser findIrpUserByFocusId(long _userid){
		return this.irpUserService.findUserByUserId(_userid);
	}
	
	/**
	 * 微知专题管理
	 * @return
	 */
	public String microtopicManage() {
		return SUCCESS;
	}
	
	/**
	 * 获得所有待审核专题
	 * @return
	 */
	public String microtopicReview(){
		PageUtil pageUtil = new PageUtil(pageNum, pageSize, this.irpTopicService.getAllIrpTopicLength(IrpTopic.TOPICTYPE_ILLEGALITY,optionvaltopic));
		irptopiclist = this.irpTopicService.getAllIrpTopic(pageUtil,IrpTopic.TOPICTYPE_ILLEGALITY,optionvaltopic);
		this.pageHTML = pageUtil.getClientPageHtml("findMicrotopicConn(#PageNum#)");
		return SUCCESS;
	}
	
	/**
	 * 获得所有已审核专题
	 * @return
	 */
	public String microtopicApproved(){
		PageUtil pageUtil = new PageUtil(pageNum, pageSize, this.irpTopicService.getAllIrpTopicLength(IrpTopic.TOPICTYPE_NORMAL,optionvaltopic));
		irptopiclist = this.irpTopicService.getAllIrpTopic(pageUtil,IrpTopic.TOPICTYPE_NORMAL,optionvaltopic);
		this.pageHTML = pageUtil.getClientPageHtml("findMicrotopicConn(#PageNum#)");
		return SUCCESS;
	}
}
