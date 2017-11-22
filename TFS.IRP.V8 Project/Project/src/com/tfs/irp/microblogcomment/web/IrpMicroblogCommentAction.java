package com.tfs.irp.microblogcomment.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.microblog.service.IrpMicroblogService;
import com.tfs.irp.microblogcomment.entity.IrpMicroblogComment;
import com.tfs.irp.microblogcomment.service.IrpMicroblogCommentService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.AtmeUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.ThumbnailPic;

public class IrpMicroblogCommentAction extends ActionSupport {

	private IrpMicroblogCommentService irpMicroblogCommentService;
	private List<String> microBlogCommentList;
	private String microblogid;
	private String commentid;
	private String replyuserid;
	private String replaymicroblogid;
	private String replaycontent;
	private List<String> rePlyList;
	private List<IrpMicroblogComment> irpMicroblogCommentList;
	private IrpUserService irpUserService;
	private Integer commentCount;
	private String loginUsername;
	private IrpMicroblogComment irpMicroblogComment;
	private IrpMicroblogService irpMicroBlogService;

	
	public IrpMicroblogService getIrpMicroBlogService() {
		return irpMicroBlogService;
	}

	public void setIrpMicroBlogService(IrpMicroblogService irpMicroBlogService) {
		this.irpMicroBlogService = irpMicroBlogService;
	}

	public IrpMicroblogComment getIrpMicroblogComment() {
		return irpMicroblogComment;
	}

	public void setIrpMicroblogComment(IrpMicroblogComment irpMicroblogComment) {
		this.irpMicroblogComment = irpMicroblogComment;
	}
	private Long replycommentidpage;
	public Long getReplycommentidpage() {
		return replycommentidpage;
	}

	public void setReplycommentidpage(Long replycommentidpage) {
		this.replycommentidpage = replycommentidpage;
	}

	public String getLoginUsername() {
		return loginUsername;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	/**
	 * 分页
	 * @return
	 */
	private String pageHTML = "";
	private int pageNum = 1;

	private int pageSize = 10;


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

	public List<String> getRePlyList() {
		return rePlyList;
	}

	public void setRePlyList(List<String> rePlyList) {
		this.rePlyList = rePlyList;
	}

	public String getCommentid() {
		return commentid;
	}

	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}

	public String getMicroblogid() {
		return microblogid;
	}

	public void setMicroblogid(String microblogid) {
		this.microblogid = microblogid;
	}




	public List<String> getMicroBlogCommentList() {
		return microBlogCommentList;
	}

	public void setMicroBlogCommentList(List<String> microBlogCommentList) {
		this.microBlogCommentList = microBlogCommentList;
	}

	public IrpMicroblogCommentService getIrpMicroblogCommentService() {
		return irpMicroblogCommentService;
	}

	public void setIrpMicroblogCommentService(
			IrpMicroblogCommentService irpMicroblogCommentService) {
		this.irpMicroblogCommentService = irpMicroblogCommentService;
	}

	public String getReplyuserid() {
		return replyuserid;
	}

	public void setReplyuserid(String replyuserid) {
		this.replyuserid = replyuserid;
	}

	public String getReplaymicroblogid() {
		return replaymicroblogid;
	}

	public void setReplaymicroblogid(String replaymicroblogid) {
		this.replaymicroblogid = replaymicroblogid;
	}

	public String getReplaycontent() {
		return replaycontent;
	}

	public void setReplaycontent(String replaycontent) {
		this.replaycontent = replaycontent;
	}
	private IrpMicroblogService irpMicroblogService;

	public IrpMicroblogService getIrpMicroblogService() {
		return irpMicroblogService;
	}

	public void setIrpMicroblogService(IrpMicroblogService irpMicroblogService) {
		this.irpMicroblogService = irpMicroblogService;
	}

	/**
	 * 增加并返回微知评论列表
	 * @return
	 */
    public String addCommentAndViewCommentList(){	
    	AtmeUtil au = new AtmeUtil();
     int _nStatus = this.irpMicroblogCommentService.addMicroBlogComment(Long.parseLong(this.replyuserid),Long.parseLong(this.replaymicroblogid),au.getAtmeStr(this.replaycontent),replycommentidpage);
     
     au.disposeATME(this.replaycontent,Long.parseLong(replaymicroblogid));
     if(_nStatus==1){
    	 //更新微知表
         	this.irpMicroblogService.updateMicroblogCommentCount(Long.parseLong(replaymicroblogid), irpMicroblogCommentService.selectByMicroBlogComment(Long.parseLong(replaymicroblogid)));
     }
     irpMicroblogComment=this.irpMicroblogCommentService.findNewWestCommentByUserid(LoginUtil.getLoginUserId(), IrpMicroblogComment.ISDELTRUE);
     loginUsername=LoginUtil.getLoginUser().getUsername();
     return SUCCESS;
    }
    /**
     * 增加微知评论
     * mobile
     * @return
     */
    public void addCommentAndViewCommentListmobile(){
    	AtmeUtil au = new AtmeUtil();
    	int _nStatus = this.irpMicroblogCommentService.addMicroBlogComment(Long.parseLong(this.replyuserid),Long.parseLong(this.replaymicroblogid),au.getAtmeStr(this.replaycontent),replycommentidpage);
        if(_nStatus==1){
       	 //更新微知表
            	this.irpMicroblogService.updateMicroblogCommentCount(Long.parseLong(replaymicroblogid), irpMicroblogCommentService.selectByMicroBlogComment(Long.parseLong(replaymicroblogid)));
        }
        ActionUtil.writer(_nStatus+"");
    }
    /**
     * 增加评论  -----细览页面
     * @return
     */
    public String addCommentAndViewCommentListFineAndLook(){
    	AtmeUtil au = new AtmeUtil();
    	int _nStatus = this.irpMicroblogCommentService.addMicroBlogComment(LoginUtil.getLoginUserId(),Long.parseLong(this.replaymicroblogid),au.getAtmeStr(this.replaycontent),replycommentidpage);

    	au.disposeATME(this.replaycontent,Long.parseLong(replaymicroblogid));
    	if(_nStatus==1){
    	 irpMicroblogComment =	(IrpMicroblogComment) this.irpMicroblogCommentService.findMicrCommentByUserid(LoginUtil.getLoginUserId(),IrpMicroblogComment.ISDELTRUE).get(0);
				this.irpMicroblogService.updateMicroblogCommentCount(Long.parseLong(replaymicroblogid), irpMicroblogCommentService.selectByMicroBlogComment(Long.parseLong(replaymicroblogid)));
    	 
    	}
    	return SUCCESS;
    }
    /**
     * 通过回复id得到评论的集合
     * @return
     */
	public String microblogReplyListByMicroBlogId(){
		commentCount=this.irpMicroblogCommentService.findMicroBlogCommentCount(Long.parseLong(microblogid),IrpMicroblogComment.ISDELTRUE);
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
				commentCount);
	   rePlyList =	this.irpMicroblogCommentService.findMicroBlogComment(Long.parseLong(microblogid),IrpMicroblogComment.ISDELTRUE,pageUtil);
	   this.pageHTML = pageUtil.getPageHtml("pageComment(#PageNum#)");
	   loginUsername=LoginUtil.getLoginUser().getUsername();
	  return SUCCESS;
	}
	/**
	 * 加载评论限制次数
	 * @return
	 */
	public void findMicroblogCommentCount(){
		String _commentnum= "";
		_commentnum =  this.irpMicroblogCommentService.findMicroblogCommentNumberWord(IrpMicroblogComment.MICROBLOGCOMMENTNUMBER);
		ActionUtil.writer(_commentnum);
	}
	/**
	 * 加载细览微知评论字数限制
	 * @return
	 */
	public void findMicroblogFineAndLookCount(){
		String _commentfinenum ="";
		_commentfinenum =this.irpMicroblogCommentService.findMicroblogCommentNumberWord(IrpMicroblogComment.MICROBLOGCOMMENTFINENUMBER);
		ActionUtil.writer(_commentfinenum);
	}
	
	
	
	/**
	 * 通过id删除微知评论回复
	 * @return
	 */
	public void microblogCommentDelete(){
	int _msg =this.irpMicroblogCommentService.updateMicroBlogCommentIsDel(Long.parseLong(commentid));
	//更新微知列表
	if(_msg>0){
	
		this.irpMicroblogService.updateMicroblogCommentCount(Long.parseLong(replaymicroblogid), irpMicroblogCommentService.selectByMicroBlogComment(Long.parseLong(replaymicroblogid)));

	}
		ActionUtil.writer(_msg+"");
	}
	/**
	 * 回复  “回复” 评论的内容
	 * @return
	 */
	public void microblogCommentReply(){
	int msg = this.irpMicroblogCommentService.addMicroBlogReplyCommentReply(Long.parseLong(commentid),replaycontent);
	if(msg==1){
			this.irpMicroblogService.updateMicroblogCommentCount(Long.parseLong(replaymicroblogid), irpMicroblogCommentService.selectByMicroBlogComment(Long.parseLong(replaymicroblogid)));
	}
	 //显示微知列表
    this.microblogReplyListByMicroBlogId();
	}
	/**
	 * 返回评论页面
	 * @return
	 */
	public String microblogCommentViewByLoginUser(){
		PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize,
	  this.irpMicroblogCommentService.findMicroblogCommentOfUseridCount(LoginUtil.getLoginUserId(),IrpMicroblogComment.ISDELTRUE));
	  irpMicroblogCommentList =this.irpMicroblogCommentService.findMicroblogCommentOfUserid(LoginUtil.getLoginUserId(),IrpMicroblogComment.ISDELTRUE,pageUtil);
	  this.pageHTML = pageUtil.getClientPageHtml("pageShow(#PageNum#)");
		return SUCCESS;
	}
	private int pagenummobile;
	public int getPagenummobile() {
		return pagenummobile;
	}
	public void setPagenummobile(int pagenummobile) {
		this.pagenummobile = pagenummobile;
	}
	/**
	 * 收到的评论
	 * mobile
	 * @return
	 */
	public void mCVByMobile(){
		int ndatacount = this.irpMicroblogCommentService.findMicroblogCommentOfUseridCount(LoginUtil.getLoginUserId(),IrpMicroblogComment.ISDELTRUE);
		PageUtil pageUtil = new PageUtil(this.pagenummobile, this.pageSize,ndatacount);
		irpMicroblogCommentList =this.irpMicroblogCommentService.findMicroblogCommentOfUserid(LoginUtil.getLoginUserId(),IrpMicroblogComment.ISDELTRUE,pageUtil);
		List mobilelist = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for (int i = 0; i < irpMicroblogCommentList.size(); i++) {
			IrpMicroblogComment imc = irpMicroblogCommentList.get(i);
			mobilelist.add("{\"showname\":\""+getShowPageViewNameByUserId(imc.getUserid())+"\"");
			
			mobilelist.add("\"sex\":\""+findIrpUserByFocusId(imc.getUserid()).getSex()+"\"");
			mobilelist.add("\"userpic\":\""+findIrpUserByFocusId(imc.getUserid()).getUserpic()+"\"");
			mobilelist.add("\"userid\":\""+imc.getUserid()+"\"");
			mobilelist.add("\"content\":\""+imc.getContent().replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");
			mobilelist.add("\"crtime\":\""+sdf.format(imc.getCrtime())+"\"");
			
			mobilelist.add("\"tranname\":\""+getShowPageViewNameByUserId(findIrpMicroblogByMicroblogid(imc.getMicroblogid()).getUserid())+"\"");
			mobilelist.add("\"trancontent\":\""+findIrpMicroblogByMicroblogid(imc.getMicroblogid()).getMicroblogcontent().replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");
			mobilelist.add("\"trancrtime\":\""+sdf.format(findIrpMicroblogByMicroblogid(imc.getMicroblogid()).getCrtime())+"\"");
			mobilelist.add("\"tranfomdata\":\""+findIrpMicroblogByMicroblogid(imc.getMicroblogid()).getFromdata()+"\"");
			
			
			mobilelist.add("\"commentid\":\""+imc.getCommentid()+"\"}");
		}
		mobilelist.add("{\"mobilenum\":\""+ndatacount+"\"}");
		ActionUtil.writer(mobilelist.toString());
	}
	/**
	 * 发出的评论
	 * mobile
	 * @return
	 */
	public void mCVBySendMobile(){
		int ndatacount = this.irpMicroblogCommentService.findMicroblogCommentOfSendUseridCount(LoginUtil.getLoginUserId(),IrpMicroblogComment.ISDELTRUE);
		PageUtil pageUtil = new PageUtil(pagenummobile,10,ndatacount);
		irpMicroblogCommentList =this.irpMicroblogCommentService.findMicroblogCommentOfSendUserid(LoginUtil.getLoginUserId(),IrpMicroblogComment.ISDELTRUE,pageUtil);	
		List mobilelist = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for (int i = 0; i < irpMicroblogCommentList.size(); i++) {
			IrpMicroblogComment imc = irpMicroblogCommentList.get(i);
			mobilelist.add("{\"showname\":\""+getShowPageViewNameByUserId(imc.getUserid())+"\"");
			
			mobilelist.add("\"sex\":\""+findIrpUserByFocusId(imc.getUserid()).getSex()+"\"");
			mobilelist.add("\"userpic\":\""+findIrpUserByFocusId(imc.getUserid()).getUserpic()+"\"");
			mobilelist.add("\"userid\":\""+imc.getUserid()+"\"");
			mobilelist.add("\"content\":\""+imc.getContent().replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");
			mobilelist.add("\"crtime\":\""+sdf.format(imc.getCrtime())+"\"");
			
			mobilelist.add("\"tranname\":\""+getShowPageViewNameByUserId(findIrpMicroblogByMicroblogid(imc.getMicroblogid()).getUserid())+"\"");
			mobilelist.add("\"trancontent\":\""+findIrpMicroblogByMicroblogid(imc.getMicroblogid()).getMicroblogcontent().replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");
			mobilelist.add("\"trancrtime\":\""+sdf.format(findIrpMicroblogByMicroblogid(imc.getMicroblogid()).getCrtime())+"\"");
			mobilelist.add("\"tranfomdata\":\""+findIrpMicroblogByMicroblogid(imc.getMicroblogid()).getFromdata()+"\"");
			
			
			mobilelist.add("\"commentid\":\""+imc.getCommentid()+"\"}");
		}
		mobilelist.add("{\"mobilenum\":\""+ndatacount+"\"}");
		ActionUtil.writer(mobilelist.toString());
		
	}
	
	
	
	
	
	
	/**
	 * 返回我发出的评论
	 */
	public String microblogCommentViewByLoginUserSend(){
	PageUtil pageUtil = new PageUtil(this.pageNum, this.pageSize,
	this.irpMicroblogCommentService.findMicroblogCommentOfSendUseridCount(LoginUtil.getLoginUserId(),IrpMicroblogComment.ISDELTRUE));
	irpMicroblogCommentList =this.irpMicroblogCommentService.findMicroblogCommentOfSendUserid(LoginUtil.getLoginUserId(),IrpMicroblogComment.ISDELTRUE,pageUtil);
	this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
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
	 * 通过微知的id得到微知的对象
	 */
	public IrpMicroblog findIrpMicroblogByMicroblogid(long _microblogid){
		return this.irpMicroblogService.irpMicroblog(_microblogid);
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
	 * 通过传入的id求出显示在转发页面的名字
	 * @return
	 */
	public String showPageName(long _transpond){
		IrpUser irpUser = null;
	    irpUser = this.irpUserService.findUserByUserId(Long.valueOf(_transpond));
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
	 * 获得当前登录用户的id
	 * @return
	 */
	public Long getLoginUserId(){
		return LoginUtil.getLoginUserId();
	}
	/**
	 * 返回某条微知下的评论个数
	 * @return
	 */
	public Integer getCountCommentByMicroblog(long _microblogid){
		int countComment = 0;
		countComment = this.irpMicroblogCommentService.getCountCommentByMicroblog(_microblogid,IrpMicroblogComment.ISDELTRUE);
		
		return countComment;
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
