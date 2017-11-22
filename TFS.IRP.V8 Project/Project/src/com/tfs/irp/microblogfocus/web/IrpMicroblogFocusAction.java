package com.tfs.irp.microblogfocus.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.messagecontent.entity.IrpMessageContent;
import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.microblog.service.IrpMicroblogService;
import com.tfs.irp.microblogfocus.entity.IrpMicroblogFocus;
import com.tfs.irp.microblogfocus.service.IrpMicroblogFocusService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.ThumbnailPic;

public class IrpMicroblogFocusAction extends ActionSupport {

	private IrpMicroblogFocusService irpMicroblogFocusService;
    private List<IrpMicroblogFocus> irpMicroblogFocuslist; 
	private IrpUserService irpUserService;
	private IrpMicroblogService irpMicroblogService;
    private String userid;
    private String loginUserid;
    private String eachuserid;
    private Long docid;//关注文档的id
    private String focususermenu;
    private String focususermenucss;
    
    //检索用户
    private String searchInfo;
    //检索用户集
    private List searchList;
	/*
	 * 分页
	 */
	private String pageHTML = "";
	private int pageNum = 1;

	private int pageSize = 10;

	private Long personid;
	private Integer messagecount;
	private String defaultallfocusval;
	
	public String getDefaultallfocusval() {
		return defaultallfocusval;
	}

	public void setDefaultallfocusval(String defaultallfocusval) {
		this.defaultallfocusval = defaultallfocusval;
	}

	public Integer getMessagecount() {
		return messagecount;
	}

	public void setMessagecount(Integer messagecount) {
		this.messagecount = messagecount;
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

    
    

	public List getSearchList() {
		return searchList;
	}

	public void setSearchList(List searchList) {
		this.searchList = searchList;
	}

	public String getSearchInfo() {
		return searchInfo;
	}

	public void setSearchInfo(String searchInfo) {
		this.searchInfo = searchInfo;
	}
	private List<IrpMicroblogFocus> irpMicroblogFocusUserlist; 
	public List<IrpMicroblogFocus> getIrpMicroblogFocusUserlist() {
		return irpMicroblogFocusUserlist;
	}

	public void setIrpMicroblogFocusUserlist(
			List<IrpMicroblogFocus> irpMicroblogFocusUserlist) {
		this.irpMicroblogFocusUserlist = irpMicroblogFocusUserlist;
	}

	public String getFocususermenucss() {
		return focususermenucss;
	}

	public void setFocususermenucss(String focususermenucss) {
		this.focususermenucss = focususermenucss;
	}

	public String getFocususermenu() {
		return focususermenu;
	}

	public void setFocususermenu(String focususermenu) {
		this.focususermenu = focususermenu;
	}

	public String getEachuserid() {
		return eachuserid;
	}

	public void setEachuserid(String eachuserid) {
		this.eachuserid = eachuserid;
	}
	private List  allUseridByFocusUserId;

	public Long getDocid() { 
		return docid;
	}

	public void setDocid(Long docid) {
		this.docid = docid;
	}

	public List getAllUseridByFocusUserId() {
		return allUseridByFocusUserId;
	}

	public void setAllUseridByFocusUserId(List allUseridByFocusUserId) {
		this.allUseridByFocusUserId = allUseridByFocusUserId;
	}



	public String getLoginUserid() {
		return loginUserid;
	}

	public void setLoginUserid(String loginUserid) {
		this.loginUserid = loginUserid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public IrpMicroblogService getIrpMicroblogService() {
		return irpMicroblogService;
	}

	public void setIrpMicroblogService(IrpMicroblogService irpMicroblogService) {
		this.irpMicroblogService = irpMicroblogService;
	}

	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public List<IrpMicroblogFocus> getIrpMicroblogFocuslist() {
		return irpMicroblogFocuslist;
	}

	public void setIrpMicroblogFocuslist(
			List<IrpMicroblogFocus> irpMicroblogFocuslist) {
		this.irpMicroblogFocuslist = irpMicroblogFocuslist;
	}

	public IrpMicroblogFocusService getIrpMicroblogFocusService() {
		return irpMicroblogFocusService;
	}

	public void setIrpMicroblogFocusService(
			IrpMicroblogFocusService irpMicroblogFocusService) {
		this.irpMicroblogFocusService = irpMicroblogFocusService;
	}
	
	/**
	 * 求出所有我关注的人的id
	 * @return
	 */
	public String findAllMicroblogFocusByUserId(){
		//1:全部关注  关注  3：粉丝    else：相互关注	
		if(eachuserid.equals("1")){   
			PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
					this.irpMicroblogFocusService.findEachMicroblogFocusUserIdCount(personid));
			irpMicroblogFocuslist = this.irpMicroblogFocusService.findEachMicroblogFocusUserId(personid,pageUtil);
			this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		}else if(eachuserid.equals("3")){
		    PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
			this.irpMicroblogFocusService.findMicroblogUserIdCount(personid));
			irpMicroblogFocusUserlist=this.irpMicroblogFocusService.findMicroblogUserId(personid,pageUtil);
			this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
		}else{      
			PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
					this.irpMicroblogFocusService.findMicroblogFocusUserIdCount(personid));
			irpMicroblogFocuslist = this.irpMicroblogFocusService.findMicroblogFocusUserId(personid,pageUtil);
			this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
			
		}
		
		messagecount = SysConfigUtil.getSysConfigNumValue(IrpMessageContent.MESSAGEFONTCOUNT);
		loginUserid = ""+personid;
		  List listArraylist =new ArrayList();
		  List 	listHashMaplist = this.irpMicroblogFocusService.selectUseridByFocuserId(personid);
			  for(int i =0;i<listHashMaplist.size();i++){
				  Map map = (Map) listHashMaplist.get(i);
				  listArraylist.add(map.values().toString().replace("[","").replace("]",""));
			  } 
			  allUseridByFocusUserId= listArraylist;
			  focususermenucss=this.focususermenu;
		return SUCCESS;
	}
	/**
	 * 求出所有我关注的人的id(登录用户的)
	 * @return
	 */
	public String   findAllMicroblogFocusByUserIdByLogin(){
		//1:全部关注  关注  3：粉丝    else：相互关注	
				if(eachuserid.equals("1")){   
					PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
							this.irpMicroblogFocusService.findEachMicroblogFocusUserIdCount(LoginUtil.getLoginUserId()));
					irpMicroblogFocuslist = this.irpMicroblogFocusService.findEachMicroblogFocusUserId(LoginUtil.getLoginUserId(),pageUtil);
					this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
				}else if(eachuserid.equals("3")){
				    PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
					this.irpMicroblogFocusService.findMicroblogUserIdCount(LoginUtil.getLoginUserId()));
					irpMicroblogFocusUserlist=this.irpMicroblogFocusService.findMicroblogUserId(LoginUtil.getLoginUserId(),pageUtil);
					this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
				}else{      
					PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
							this.irpMicroblogFocusService.findMicroblogFocusUserIdCount(LoginUtil.getLoginUserId()));
					irpMicroblogFocuslist = this.irpMicroblogFocusService.findMicroblogFocusUserId(LoginUtil.getLoginUserId(),pageUtil);
					this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
					
				}
				
				messagecount = SysConfigUtil.getSysConfigNumValue(IrpMessageContent.MESSAGEFONTCOUNT);
				loginUserid = ""+LoginUtil.getLoginUserId();
				  List listArraylist =new ArrayList();
				  List 	listHashMaplist = this.irpMicroblogFocusService.selectUseridByFocuserId(LoginUtil.getLoginUserId());
					  for(int i =0;i<listHashMaplist.size();i++){
						  Map map = (Map) listHashMaplist.get(i);
						  listArraylist.add(map.values().toString().replace("[","").replace("]",""));
					  } 
					  allUseridByFocusUserId= listArraylist;
					  focususermenucss=this.focususermenu;			
		return SUCCESS;
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
	 * 查看当前用户所关注的用户的id
	 * @return
	 */
	public List allUseridByUserid(long _userid){
		  //被关注的用户关注了谁
		  List listArraylistUserid =new ArrayList();
		  List listHashMaplistUserid;
			listHashMaplistUserid = this.irpMicroblogFocusService.selectUseridByFocuserId(_userid);
			  for(int i =0;i<listHashMaplistUserid.size();i++){
				  Map map = (Map) listHashMaplistUserid.get(i);
				  listArraylistUserid.add(map.values().toString().replace("[","").replace("]",""));
			  } 
		return listArraylistUserid;	  
	}
	
	
	/**
	 * 获得显示在页面的粉丝的名称
	 * @return
	 */
	public String showPageName(long _userid){
		IrpUser irpUser = null;
	    irpUser = this.irpUserService.findUserByUserId(Long.valueOf(_userid));
	    if (irpUser.getNickname()==null || irpUser.getNickname().equals("")) {
	    	if(irpUser.getTruename()==null || irpUser.getTruename().equals("")){
	    		return irpUser.getUsername();		
	    	}else{
	    		return irpUser.getTruename();
	    	}
		}else{
			return irpUser.getNickname();
		}
	    
	}
	/**
	 * 通过用户ID获取用户最新的一条微知
	 */
	public IrpMicroblog findIrpMicroblogByUserid(long _userid){
		
		
		return this.irpMicroblogService.findFirstMicroblog(_userid);
	}
	
	/**
	 * 通过用户的id计算出有多少个用户关注wo
	 * @return
	 */
	public int MicroblogFocusCountFocusUserid(long _userid){
		return this.irpMicroblogFocusService.countMicroblogFocusFocusUserid(_userid);
	}
	/**
	 * 通过当前的用户删除关注的用户
	 * @return
	 */
	public void deleteMicroblogFocusid(){
		int msg = this.irpMicroblogFocusService.deleteMicroblogFocusUserid(LoginUtil.getLoginUserId(),Long.parseLong(userid));
		ActionUtil.writer(msg+"");
		
	}
	/**
	 * 增加关注对象
	 * @return
	 */
	public void addMicroblogFocusid(){
		int msg = this.irpMicroblogFocusService.addMicroblogFocusUserid(LoginUtil.getLoginUserId(), Long.parseLong(userid));
		ActionUtil.writer(msg+"");
	}
	/**
	 * 默认增加关注
	 * @return
	 */
	public void addMicroblogFocusidDefault(){
		String strArray[] = defaultallfocusval.split(",");
		for (int i = 0; i < strArray.length; i++) {
			String struserid = strArray[i];
			if(!struserid.equals("")){
				this.irpMicroblogFocusService.addMicroblogFocusUserid(LoginUtil.getLoginUserId(), Long.parseLong(struserid));	
			}
		}
	}
	
	/**
	 * 通过用户的id获得用户共关注了多少个用户
	 */
	public int MicroblogFocusCountUserid(long _focususerid){
		return this.irpMicroblogFocusService.countMicroblogFocusUserid(_focususerid);
	}
	/**
	 * 通过用户的id获得用户共有多少条微知
	 */
	public int MicroblogCountByUserid(long _userid){
	 return this.irpMicroblogService.coutnMicroblogOfUserid(_userid, IrpMicroblog.ISDELFALSE);	
	}
	/**
	 * 求出所有我关注的人的id(给张银珠用的)
	 * @return
	 */
	public String findAllMicroblogFocusByUserIdJson(){
	    irpMicroblogFocuslist = this.irpMicroblogFocusService.findMicroblogFocusUserId(LoginUtil.getLoginUserId());
		 return SUCCESS;
	}
   
	/**
	 * 获得登录用户的id
	 * @return
	 */
	public long getLoginUserId(){
		return LoginUtil.getLoginUserId();
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
}
