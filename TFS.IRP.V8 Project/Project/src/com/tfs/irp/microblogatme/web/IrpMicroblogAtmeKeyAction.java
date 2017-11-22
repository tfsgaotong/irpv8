package com.tfs.irp.microblogatme.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.microblog.service.IrpMicroblogService;
import com.tfs.irp.microblogatme.entity.IrpMicroblogAtmeKey;
import com.tfs.irp.microblogatme.service.IrpMicroblogAtmeKeyService;
import com.tfs.irp.microblogcomment.service.IrpMicroblogCommentService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.ThumbnailPic;

public class IrpMicroblogAtmeKeyAction extends ActionSupport {

	private IrpMicroblogAtmeKeyService irpMicroblogAtmeKeyService;
	private  IrpUserService   irpUserService;
    private  IrpMicroblogService  irpMicroblogService;	
    private IrpMicroblogCommentService irpMicroblogCommentService;
    
    public IrpMicroblogCommentService getIrpMicroblogCommentService() {
		return irpMicroblogCommentService;
	}

	public void setIrpMicroblogCommentService(
			IrpMicroblogCommentService irpMicroblogCommentService) {
		this.irpMicroblogCommentService = irpMicroblogCommentService;
	}
	private Long atmeid;

	public Long getAtmeid() {
		return atmeid;
	}

	public void setAtmeid(Long atmeid) {
		this.atmeid = atmeid;
	}

	public IrpMicroblogAtmeKeyService getIrpMicroblogAtmeKeyService() {
		return irpMicroblogAtmeKeyService;
	}

	public void setIrpMicroblogAtmeKeyService(
			IrpMicroblogAtmeKeyService irpMicroblogAtmeKeyService) {
		this.irpMicroblogAtmeKeyService = irpMicroblogAtmeKeyService;
	}
	private List irpMicroblogAtmeList;

	public List getIrpMicroblogAtmeList() {
		return irpMicroblogAtmeList;
	}

	public void setIrpMicroblogAtmeList(List irpMicroblogAtmeList) {
		this.irpMicroblogAtmeList = irpMicroblogAtmeList;
	}
	public IrpUserService getIrpUserService() {
		return irpUserService;
	}

	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}

	public IrpMicroblogService getIrpMicroblogService() {
		return irpMicroblogService;
	}

	public void setIrpMicroblogService(IrpMicroblogService irpMicroblogService) {
		this.irpMicroblogService = irpMicroblogService;
	}
	/**
	 * 分页
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
	
	/**
	 * 显示@列表
	 * @return
	 */
	public String findMicroblogAtmeView(){
		long loginUserid = LoginUtil.getLoginUserId();
		PageUtil pageUtil = new PageUtil(this.pageNum, this.getPageSize(),
				this.irpMicroblogAtmeKeyService.IrpMicroblogAtmeKeyCount(loginUserid));
		irpMicroblogAtmeList =	this.irpMicroblogAtmeKeyService.IrpMicroblogAtmeKey(loginUserid, pageUtil);
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");
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
	 * @我的列表
	 * mobile
	 * @return 
	 */
	public void findMicroblogAtmeViewMobile(){
		long loginUserid = LoginUtil.getLoginUserId();
		int psize = this.irpMicroblogAtmeKeyService.IrpMicroblogAtmeKeyCount(loginUserid);
		PageUtil pageUtil = new PageUtil(this.pagenummobile, 10,psize);
		irpMicroblogAtmeList =	this.irpMicroblogAtmeKeyService.IrpMicroblogAtmeKey(loginUserid, pageUtil);
		List mobilelist = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for (int i = 0; i < irpMicroblogAtmeList.size(); i++) {
			IrpMicroblogAtmeKey  imak = (IrpMicroblogAtmeKey) irpMicroblogAtmeList.get(i);
			mobilelist.add("{\"showname\":\""+getShowPageViewNameByUserId(imak.getUserid())+"\"");
			mobilelist.add("\"sex\":\""+findIrpUserByFocusId(imak.getUserid()).getSex()+"\"");
			mobilelist.add("\"userpic\":\""+findIrpUserByFocusId(imak.getUserid()).getUserpic()+"\"");
			mobilelist.add("\"userid\":\""+imak.getUserid()+"\"");
			mobilelist.add("\"crtime\":\""+sdf.format(imak.getCrtime())+"\"");
			mobilelist.add("\"content\":\""+findIrpMicroblogByMicroblogid(imak.getMicroblogid()).getMicroblogcontent().replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");
			mobilelist.add("\"microblogid\":\""+imak.getMicroblogid()+"\"");
			mobilelist.add("\"atmeid\":\""+imak.getAtmeid()+"\"}");
		}
		mobilelist.add("{\"mobilenum\":\""+psize+"\"}");
		ActionUtil.writer(mobilelist.toString());
	}
	
	
	
	
	
	/**
	 * 删除指定的@消息
	 * @return
	 */
	public void deleteAtmeByAtmeid(){
		int _msg =this.irpMicroblogAtmeKeyService.deleteMicroblogAtmeKeyByAtmeId(atmeid);
		ActionUtil.writer(""+_msg);
	}
	/**
	 * 清空登录用户的私信
	 * @return
	 */
	public void deleteAtmeByUserid(){
	 int msg = 	this.irpMicroblogAtmeKeyService.deleteMicroblogAtmeKeyByUserid(LoginUtil.getLoginUserId());
		ActionUtil.writer(String.valueOf(msg));
		
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
     * 过滤带图的字符串  缩略图
     * @param _content
     * @return
     */
    public String getMicroblogContent(String _content){
    	_content = ThumbnailPic.searchThumnailPicSrc(_content, "_150X150");
    	return _content;
    }
    /**
     * 通过用户名获得在页面显示的名字
     * @param _username
     * @return
     */
    public String getPageNameForUsername(String _username){
    	 return this.irpUserService.findShowNameByUsername(_username);
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
