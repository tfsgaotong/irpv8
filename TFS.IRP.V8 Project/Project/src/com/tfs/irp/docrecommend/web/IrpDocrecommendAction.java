package com.tfs.irp.docrecommend.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.opensymphony.xwork2.ActionSupport;
import com.tfs.irp.channel.entity.IrpChannel;
import com.tfs.irp.docrecommend.entity.IrpDocrecommend;
import com.tfs.irp.docrecommend.service.IrpDocrecommendService;
import com.tfs.irp.document.entity.IrpDocument;
import com.tfs.irp.document.entity.IrpDocumentWithBLOBs;
import com.tfs.irp.document.service.IrpDocumentService;
import com.tfs.irp.documentcollection.service.IrpDocumentCollectionService;
import com.tfs.irp.microblog.entity.IrpMicroblog;
import com.tfs.irp.microblog.service.IrpMicroblogService;
import com.tfs.irp.user.entity.IrpUser;
import com.tfs.irp.user.service.IrpUserService;
import com.tfs.irp.util.ActionUtil;
import com.tfs.irp.util.LoginUtil;
import com.tfs.irp.util.PageUtil;
import com.tfs.irp.util.RightUtil;
import com.tfs.irp.util.SysConfigUtil;
import com.tfs.irp.util.TopicUtil;

public class IrpDocrecommendAction extends ActionSupport {
	private IrpDocrecommendService irpDocrecommendServiceImpl;
	private IrpUserService irpUserService;
	public IrpUserService getIrpUserService() {
		return irpUserService;
	}
	public void setIrpUserService(IrpUserService irpUserService) {
		this.irpUserService = irpUserService;
	}
	private Long docid;//文档id
	private String recommend ;//回复内容
	private Long recommendid;//回复id
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
	private Long replayUserId;//回复的回复id
	private List<IrpDocrecommend> irpDocrecommends;
	private Long loginuserid;//获取当前登录用户的id
	private Long createuserid;//用来判断登录用户和文档作者是否一样，一样可以修改，删除权限
	private Integer isMicro;//是否分享微知(将这个文章分享到微知还是将这个评论分享到微知)
	private Integer isCollectAdnMicro;///是否评论并收藏
	
	private int pageNum=1; 
	
	private int pageSize=10; 
	
	private String pageHTML;
	
	private String isXiangGuan;
	
	private long channelid;
	
	
	
	public long getChannelid() {
		return channelid;
	}
	public void setChannelid(long channelid) {
		this.channelid = channelid;
	}
	public String getIsXiangGuan() {
		return isXiangGuan;
	}
	public void setIsXiangGuan(String isXiangGuan) {
		this.isXiangGuan = isXiangGuan;
	}
	private    IrpMicroblogService irpMicroBlogService;
	private   IrpDocumentCollectionService irpDocumentCollectionServiceImpl;
	public IrpDocumentCollectionService getIrpDocumentCollectionServiceImpl() {
		return irpDocumentCollectionServiceImpl;
	}
	public Long getReplayUserId() {
		return replayUserId;
	}
	public void setReplayUserId(Long replayUserId) {
		this.replayUserId = replayUserId;
	}
	public void setIrpDocumentCollectionServiceImpl(
			IrpDocumentCollectionService irpDocumentCollectionServiceImpl) {
		this.irpDocumentCollectionServiceImpl = irpDocumentCollectionServiceImpl;
	}
	private IrpDocumentService irpDocumentService;
	public Integer getIsMicro() {
		return isMicro;
	}
	public void setIsMicro(Integer isMicro) {
		this.isMicro = isMicro;
	}
	public Integer getIsCollectAdnMicro() {
		return isCollectAdnMicro;
	}
	public void setIsCollectAdnMicro(Integer isCollectAdnMicro) {
		this.isCollectAdnMicro = isCollectAdnMicro;
	}
	 
	public String getPageHTML() {
		return pageHTML;
	}
	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}
	//删除文档回复
	public void deleteDocRecommend(Long _docid){
		int nCount=irpDocrecommendServiceImpl.deleteDocReommend(_docid, recommendid);
		ActionUtil.writer(String.valueOf(nCount));
	}
	//删除文档回复
		public void deleteDocRecommend(){
			int nCount=irpDocrecommendServiceImpl.deleteDocReommend(docid, recommendid);
			ActionUtil.writer(String.valueOf(nCount));
		}  
	//评论文档
	public void addDocRecommend(){  
		//添加微知 
		if(isMicro!=null && isMicro.toString().equals("1")){
			String cValue=SysConfigUtil.getSysConfigValue("DOCUMENT_DOCRECOMMEND_BLOG");
			IrpUser irpUser=LoginUtil.getLoginUser();

			cValue=cValue.replace("#user#",LoginUtil.getUserNameString(irpUser));//昵称
			
			Date date=new Date();
	    	SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    	cValue=cValue.replace("#time#",dateFormat.format(date));
	    	IrpDocumentWithBLOBs document=irpDocumentService.findDocumentByDocId(docid);
	    	String path=SysConfigUtil.getSysConfigValue("domain_name_address");
        	String href="<a href='"+path+"/document/document_detail.action?docid="+document.getDocid()+"'  class='linkbh14' ><strong>”"+document.getDoctitle()+"“</strong></a>";
	    	cValue=cValue.replace("#doctitle#",href );
	    	String content="<a linkc12>评论内容： &nbsp;"+recommend+"</a>";
	    	cValue=cValue.replace("#content#",content );
			IrpMicroblog _irpMicroblog=new IrpMicroblog();
			TopicUtil topicUtil = new TopicUtil();
			irpMicroBlogService.addMicroBlog(cValue, IrpMicroblog.ISDELFALSE, _irpMicroblog,null);
		}
		//收藏并 
		if(isCollectAdnMicro!=null && isCollectAdnMicro.toString().equals("1")){ 
			irpDocumentCollectionServiceImpl.insertFocus(docid);
		}
		int nCount=irpDocrecommendServiceImpl.addDocRrecommend(docid,replayUserId, recommend);
		if(nCount==1){
			IrpUser irpUser=LoginUtil.getLoginUser();
			String str="<a href='javascript:void(0)' class='linkb14' >"+irpUser.getUsername()+"：</a>"+recommend+"<br>";
			ActionUtil.writer(str);
		} 
		replayUserId=0L;//添加回来之后需要将值设置为0，这样查询评论不会出问题
	} 
	/**
	 * 
	 * 增加文章评论
	 * @mobile
	 * @return
	 */
	public void addDocRecommendMobile(){
		int nCount=irpDocrecommendServiceImpl.addDocRrecommend(docid,replayUserId, recommend);
		ActionUtil.writer(nCount+"");	
	}
	
	
	
	//查看关注对象某一个文档所有的评论
	public void findDocRecommend(){ 
		loginuserid=LoginUtil.getLoginUser().getUserid();  
		int aDataCount=irpDocrecommendServiceImpl.countRecommend(docid, replayUserId);
		PageUtil pageUtil= new PageUtil(this.pageNum, this.pageSize, aDataCount); 
		List<IrpDocrecommend> docs=irpDocrecommendServiceImpl.findDocReommend(docid,replayUserId,IrpDocrecommend.ISDEL_PRO,pageUtil); //查看正常情况下的评论
		JSONArray array=JSONArray.fromObject(docs);
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");  
		ActionUtil.writer(array.toString());
	}
	//查看回复的回复
	public String findReplayRecommend(){
		loginuserid=LoginUtil.getLoginUser().getUserid();   
		int aDataCount=irpDocrecommendServiceImpl.countRecommend(docid, replayUserId);
		PageUtil pageUtil= new PageUtil(this.pageNum, this.pageSize, aDataCount); 
		irpDocrecommends=irpDocrecommendServiceImpl.findDocReommend(docid,replayUserId,IrpDocrecommend.ISDEL_PRO,pageUtil); //查看正常情况下的
		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");  
		return SUCCESS;
	}
	//查看自己某一个文档所有的评论
	public String findMyDocRecommend(){ 
		IrpUser irpUser=LoginUtil.getLoginUser();
		loginuserid = irpUser.getUserid();
		//此时没有传回复的回复id  
		if (irpUser.isAdministrator()) {
			isXiangGuan = true + "";
		}else{
			RightUtil rightUtil = new RightUtil();
			IrpChannel channle = new IrpChannel();
			channle.setChannelid(channelid);
			Long nOperTypeId = rightUtil.findOperTypeIdByKey("DOCUMENT_DOCCORRELATIONCOMMENT");//相关评论
			isXiangGuan = rightUtil.right(channle, nOperTypeId)+"";
		}
		int aDataCount=irpDocrecommendServiceImpl.countRecommend(docid, replayUserId);
		PageUtil pageUtil= new PageUtil(this.pageNum, this.pageSize, aDataCount); 
		irpDocrecommends=irpDocrecommendServiceImpl.findDocReommend(docid,replayUserId,IrpDocrecommend.ISDEL_PRO,pageUtil); //查看正常情况下的

		this.pageHTML = pageUtil.getClientPageHtml("page(#PageNum#)");   
		return SUCCESS;
	}
	/**
	 * 获得该文章下所有的评论
	 * mobile
	 * @return
	 */
	private int pagerecommendnummobile;
	public int getPagerecommendnummobile() {
		return pagerecommendnummobile;
	}
	public void setPagerecommendnummobile(int pagerecommendnummobile) {
		this.pagerecommendnummobile = pagerecommendnummobile;
	}
	public void findMyDocRecommendOfMobile(){
		
		loginuserid=LoginUtil.getLoginUser().getUserid();  
		int aDataCount=irpDocrecommendServiceImpl.countRecommend(docid, replayUserId);
		PageUtil pageUtil= new PageUtil(pagerecommendnummobile,10, aDataCount); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List<IrpDocrecommend> list = irpDocrecommendServiceImpl.findDocReommend(docid,replayUserId,IrpDocrecommend.ISDEL_PRO,pageUtil);
		
		List mobilelist = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			IrpDocrecommend irpDocrecommend = list.get(i);
			mobilelist.add("{\"showname\":\""+getShowPageViewNameByUserId(irpDocrecommend.getCruserid())+"\"");
			mobilelist.add("\"userid\":\""+irpDocrecommend.getCruserid()+"\"");
			mobilelist.add("\"sex\":\""+getIrpUserByUserid(irpDocrecommend.getCruserid()).getSex()+"\"");
			mobilelist.add("\"crtime\":\""+sdf.format(irpDocrecommend.getCrtime())+"\"");
			mobilelist.add("\"recommend\":\""+irpDocrecommend.getRecommend().replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");
			mobilelist.add("\"userpic\":\""+getIrpUserByUserid(irpDocrecommend.getCruserid()).getUserpic()+"\"");
			IrpDocrecommend replyrecommend = irpDocrecommend.getParentDocRecommend();
			if(replyrecommend!=null){
				mobilelist.add("\"replyname\":\""+getShowPageViewNameByUserId(replyrecommend.getCruserid())+"\"");
				mobilelist.add("\"replyrecommend\":\""+replyrecommend.getRecommend().replace("\"", "\\\"").replace("\\\\\"","\\\"")+"\"");
				mobilelist.add("\"replydate\":\""+sdf.format(replyrecommend.getCrtime())+"\"");
			}
			mobilelist.add("\"recommendid\":\""+irpDocrecommend.getRecommendid()+"\"}");
		}
		mobilelist.add("{\"mobilenum\":\""+aDataCount+"\"}");
		
		ActionUtil.writer(mobilelist.toString());
	}
	/**
	 * 更具用户id获得用户的对象
	 * 
	 * @param _cruserid
	 * @return
	 */
	public IrpUser getIrpUserByUserid(long _cruserid) {
		IrpUser irpUser = null;
		irpUser = this.irpUserService.findUserByUserId(_cruserid);
		return irpUser;

	}

    /**
     * 根据id获得用户在页面显示的名字
     * @param _userid
     * @return
     */
    public String getShowPageViewNameByUserId(Long _userid){
    	return this.irpUserService.findShowNameByUserid(_userid);
    }
	
	
	public IrpDocrecommendService getIrpDocrecommendServiceImpl() {
		return irpDocrecommendServiceImpl;
	}

	public void setIrpDocrecommendServiceImpl(
			IrpDocrecommendService irpDocrecommendServiceImpl) {
		this.irpDocrecommendServiceImpl = irpDocrecommendServiceImpl;
	}


	public List<IrpDocrecommend> getIrpDocrecommends() {
		return irpDocrecommends;
	}
	public void setIrpDocrecommends(List<IrpDocrecommend> irpDocrecommends) {
		this.irpDocrecommends = irpDocrecommends;
	}
	public Long getDocid() {
		return docid;
	}


	public IrpDocumentService getIrpDocumentService() {
		return irpDocumentService;
	}
	public void setIrpDocumentService(IrpDocumentService irpDocumentService) {
		this.irpDocumentService = irpDocumentService;
	}
	public void setDocid(Long docid) {
		this.docid = docid;
	}


	public String getRecommend() {
		return recommend;
	}


	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	public Long getRecommendid() {
		return recommendid;
	}
	public void setRecommendid(Long recommendid) {
		this.recommendid = recommendid;
	}
	public Long getLoginuserid() {
		return loginuserid;
	}
	public void setLoginuserid(Long loginuserid) {
		this.loginuserid = loginuserid;
	}
	public Long getCreateuserid() {
		return createuserid;
	}
	public void setCreateuserid(Long createuserid) {
		this.createuserid = createuserid;
	}
	public IrpMicroblogService getIrpMicroBlogService() {
		return irpMicroBlogService;
	}
	public void setIrpMicroBlogService(IrpMicroblogService irpMicroBlogService) {
		this.irpMicroBlogService = irpMicroBlogService;
	}
 
}
